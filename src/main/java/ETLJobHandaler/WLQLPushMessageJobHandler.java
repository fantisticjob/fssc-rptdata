package ETLJobHandaler;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longfor.fsscreportdb.oa.entity.DinDim;
import com.longfor.fsscreportdb.oa.entity.DinDimValue;
import com.longfor.fsscreportdb.oa.entity.DwCpClearUpDetail;
import com.longfor.fsscreportdb.oa.entity.DwCpMonthQuarter;
import com.longfor.fsscreportdb.oa.service.IDinDimService;
import com.longfor.fsscreportdb.oa.service.IDinDimValueService;
import com.longfor.fsscreportdb.oa.service.IDwCpClearUpDetailService;
import com.longfor.fsscreportdb.oa.service.IDwCpMonthQuarterService;
import com.longfor.fsscreportdb.utils.StringUtil;
import com.longfor.longchat.openapi.bean.Result;
import com.longfor.longchat.openapi.client.OpenApiClient;
import com.longfor.longchat.openapi.content.ImgTextMsgContent;
import com.longfor.longchat.openapi.content.TxtMsgContent;
import com.longfor.longchat.openapi.dto.PushMessageDTO;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import groovy.util.logging.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务-往来清理龙信消息推送
 * @author Yingshuang
 * @date 2021/5/25
 */
@JobHandler(value = "WLQLPushMessageJobHandler")
@Component
@Slf4j
public class WLQLPushMessageJobHandler extends IJobHandler {

    private static final Logger log = LoggerFactory.getLogger(WLQLPushMessageJobHandler.class);

    public static final String longforChatKey = "c30aeb8b-b4e0-4217-91a1-63190589248d";  //生产
    public static final String longforChatUrl = "https://api.longhu.net/longchat-open-api-prod"; //生产

    @Autowired
    private IDinDimValueService iDinDimValueService;

    @Autowired
    private IDwCpClearUpDetailService iDwCpClearUpDetailService;

    @Autowired
    private IDwCpMonthQuarterService iDwCpMonthQuarterService;

    @Autowired
    private IDinDimService iDinDimService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        //获取PC/Mobile端的跳转URL
        QueryWrapper<DinDimValue> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DIM_ID","1902");
        wrapper1.eq("USE_FLAG",1);
        DinDimValue tp = iDinDimValueService.getSSOToken(wrapper1);
        String pcUrl = tp.getDimValueName();
        log.info("获取跳转URL" + pcUrl);


        wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DIM_ID","1905");
        wrapper1.eq("USE_FLAG",1);
        tp = iDinDimValueService.getSSOToken(wrapper1);
        String responseDate = tp.getDimValueName();
        log.info("获取反馈确认时间" + responseDate);


        log.info("获取往来清理数据-发送的记录 Start");

//        QueryWrapper<DwCpClearUpDetail> wrapper2 = new QueryWrapper<>();
//        if (StringUtils.isNotBlank(param) && "2".equals(param.trim())) {
//            wrapper2.eq("GX_FLAG","2");  //获取需要第2次推送的记录
//        } else {
//            wrapper2.eq("GX_FLAG","1"); //获取需要首次进行推送的记录
//        }
//        wrapper2.orderByDesc("RESPON_USER");
//        List<DwCpClearUpDetail> dccud = iDwCpClearUpDetailService.getListBeanByWrapper(wrapper2);

        List<DwCpClearUpDetail> dccud1 = null;
        //获取符合首次发送龙信消息的地区列表
        List<String> areaList1 = getAreaListByFlag(1);
        //长度为0时不能用in，应该是查不到数据
        if(0 < areaList1.size()) {
            QueryWrapper<DwCpClearUpDetail> wrapper = new QueryWrapper<>();
            wrapper.eq("GX_FLAG", "1");
            wrapper.in("AREA_NAME",areaList1);
            wrapper.orderByDesc("RESPON_USER");
            dccud1 = iDwCpClearUpDetailService.getListBeanByWrapper(wrapper);
            if (dccud1 == null) {
                dccud1 = new ArrayList<DwCpClearUpDetail>();
            }
            log.info("往来清理第一次推送的记录数为：" + dccud1.size());
            //处理第一次龙信推送
            try {
                this.processSendMsg(dccud1, pcUrl, responseDate);
            } catch (Exception e) {
                log.error("往来清理龙信第一次待办推送失败：{}", e.toString());
            }
        }

        //处理第二次龙信推送
        List<DwCpClearUpDetail> dccud2 = null;
        List<String> areaList2 = getAreaListByFlag(2);
        if(0 < areaList2.size()){
            QueryWrapper<DwCpClearUpDetail> wrapper = new QueryWrapper<>();
            wrapper.eq("GX_FLAG", "2");
            wrapper.in("AREA_NAME",areaList2);
            wrapper.orderByDesc("RESPON_USER");
            dccud2 = iDwCpClearUpDetailService.getListBeanByWrapper(wrapper);
            if (dccud2 == null) {
                dccud2 = new ArrayList<DwCpClearUpDetail>();
            }
            log.info("往来清理第二次推送的记录数为：" + dccud2.size());
            //处理第一次龙信推送
            try {
                this.processSendMsg(dccud2, pcUrl, responseDate);
            } catch (Exception e) {
                log.error("往来清理龙信第二次待办推送失败：{}", e.toString());
            }
        }
        return SUCCESS;
    }

    /**
     * 处理龙信消息发送
     * @param dccud
     */
    public void processSendMsg (List<DwCpClearUpDetail> dccud, String pcUrl, String responseDate) {
        String lastOA = ""; //上一条数据的账号
        String lastArea = ""; //上一条记录的地区
        int number = 0;  //往来数据数目
        BigDecimal total = new BigDecimal("0");
        log.info("已获取清理数据，进行计算");

        List<DwCpClearUpDetail> updList = new ArrayList<DwCpClearUpDetail>();
        int batchSize = 0;
        DwCpClearUpDetail updRecord = null;
        QueryWrapper<DinDimValue> wrapper = null;
        DinDimValue tp = null;
        for(int i = 0; i < dccud.size(); i++) {
            updRecord = dccud.get(i);
            String nowOA = "";  //当前用户的账号
            String getUser = updRecord.getResponUser();
            BigDecimal amount = updRecord.getAmount();
            if (amount == null) {
                amount = new BigDecimal(0);
            }
            //如果责任人为空，则跳过处理
            if(StringUtils.isBlank(getUser)){
                continue;
            }
            //判断是否有多个责任人，如果有多个只取第一个
            if(getUser.indexOf(",") != -1){
                //log.info("存在多个责任人：" + getUser);
                nowOA = getUser.split(",")[0];
            } else {
                nowOA = getUser;
            }
            //log.info("责任人是：" + nowOA);
            //第一条记录处理
            if (StringUtils.isBlank(lastOA)) {
                lastOA = nowOA;
                lastArea = updRecord.getAreaName();
                number++;
                total = total.add(amount.abs());
                //log.info("total = " + total);
            } else {
                if(!lastOA.equals(nowOA)){
                    BigDecimal divide = new BigDecimal("10000");
                    //根据地区获取负责人信息
                    String  responseUser = "taonaihui";
                    if (StringUtils.isNotBlank(lastArea)) {
                        wrapper = new QueryWrapper<>();
                        wrapper.eq("DIM_ID","1904");
                        wrapper.eq("USE_FLAG",1);
                        wrapper.eq("DIM_VALUE_ID", lastArea.trim());
                        tp = iDinDimValueService.getSSOToken(wrapper);
                        String dimVal = tp.getDimValueName();
                        if (StringUtils.isNotBlank(dimVal)) {
                            responseUser = dimVal;
                        }
                    }
                    try {
                        sendMessage(lastOA, number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP), pcUrl, responseUser, responseDate);
                    } catch (Exception e) {
                        log.error("龙信消息发送失败： OA=" + lastOA + ", responseUser=" + responseUser);
                    }
                    lastOA = nowOA;
                    lastArea = updRecord.getAreaName();
                    number = 1;
                    total = new BigDecimal("0");
                    total = total.add(amount.abs());
                }else{
                    number++;
                    total = total.add(amount.abs());
                }
            }
            //更新发送状态
            batchSize++;
            updRecord.setGxFlag("2");
            updList.add(updRecord);
            if (batchSize/200 == 0) {
                try {
                    iDwCpClearUpDetailService.updateBatchById(updList, updList.size());
                } catch (Exception e) {
                    log.error("龙信消息发送后状态更新失败：" + e.toString());
                }

                updList.clear();
            }
            //log.info("total = " + total);
        }
        //最后一批记录推送

        BigDecimal divide = new BigDecimal("10000");
        //根据地区获取负责人信息
        String responseUser = "taonaihui";
        if (StringUtils.isNotBlank(lastArea)) {
            wrapper = new QueryWrapper<>();
            wrapper.eq("DIM_ID","1904");
            wrapper.eq("USE_FLAG",1);
            wrapper.eq("DIM_VALUE_ID", lastArea.trim());
            tp = iDinDimValueService.getSSOToken(wrapper);
            String dimVal = tp.getDimValueName();
            if (StringUtils.isNotBlank(dimVal)) {
                responseUser = dimVal;
            }
        }
        try {
            sendMessage(lastOA, number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP),  pcUrl, responseUser, responseDate);
        } catch (Exception e) {
            log.error("龙信消息发送失败： OA=" + lastOA + ", responseUser=" + responseUser);
        }

        if (0 < updList.size()) {
            try {
                iDwCpClearUpDetailService.updateBatchById(updList, updList.size());
            } catch (Exception e) {
                log.error("龙信消息发送后状态更新失败：" + e.toString());
            }
        }
    }


    public void sendMessage(String nowOA,int number,BigDecimal total, String pcUrl, String responseUser, String responseDate) {
        //log.info("token=" + ssoToken);
        log.info("nowOA=" + nowOA);
        log.info("number=" + number);
        log.info("total=" + total.doubleValue());
        log.info("pcUrl=" + pcUrl);
        log.info("responseUser=" + responseUser);
        log.info("responseDate=" + responseDate);
        //send text message
        sendTextMsg(nowOA, number, total, responseUser, responseDate);
        //send mobile message
        //sendMobileMsg(nowOA, number, total);
        //send PC message
        sendPcMsg(nowOA, number, total, pcUrl, responseDate);
    }

    public void sendMobileMsg(String nowOA,int number,BigDecimal total) {
        log.info("发送Mobile图文消息");
        List<String> members = new ArrayList<>();
        //members.add("w_liuyingshuang");
        members.add(nowOA);
        List<String> str =  new ArrayList<String>();
        //ImageContent
        ImgTextMsgContent itmc = ImgTextMsgContent.builder()
                .title("借款清理待办提醒")
                .content("您名下有" + number + "笔借款/保证金押金尚未清理（如领借款未冲账、已支付的保证金/押金未收回等），共计" + total.doubleValue() + "万元。请您预估冲帐或收回时间，并于6月13日前反馈。")
                .imageUri("http://frtest.longhu.net/webroot/decision?ticket=ST-545-HCH1p7dNFfp8oNjQU6MFxE-JK4cALI-PRD00108-APP-T1#directory")
                .url("https://apmobile.longhu.net/MobileH5/app/60bdf0e7e79c9356c9c1ef63/access")
                .build();
        //MessageDTO
        PushMessageDTO pushMessageDTO = PushMessageDTO.builder()
                .fromType(3)
                .isSSO(1)
                .objectName("RC:ImgTextMsg")
                .from("monitor_admin")
                .targetType(1)
                //.ssoToken(ssoToken)
                .includeSender(0)
                //.mentionType(2)
                //.mentionAccounts(str)
                .content(itmc)
                .target(members)
                .pcOpenMode(1)
                //.displayMode("MOBILE")
                .build();
        Result sendMessageResult = OpenApiClient.pushMessage(pushMessageDTO, longforChatUrl+"/pushMessage", longforChatKey, 20);
        log.info("Mobile图文消息Request:" + pushMessageDTO.toString());
        log.info("Mobile图文消息Response:" + JSONObject.toJSONString(sendMessageResult));
    }

    public void sendPcMsg(String nowOA,int number,BigDecimal total, String pcUrl, String responseDate) {
        log.info("发送PC图文消息");
        List<String> members = new ArrayList<>();
        //members.add("w_liuyingshuang");
        members.add(nowOA);
        List<String> str =  new ArrayList<String>();
        //ImageContent
        ImgTextMsgContent itmc = ImgTextMsgContent.builder()
                .title("借款清理待办提醒")
                .content("您名下有" + number + "笔借款/保证金押金尚未清理（如领借款未冲账、已支付的保证金/押金未收回等），共计" + total.doubleValue() + "万元。请您预估冲帐或收回时间，并于" + responseDate + "前反馈。")
                .imageUri("http://frtest.longhu.net/webroot/decision?ticket=ST-545-HCH1p7dNFfp8oNjQU6MFxE-JK4cALI-PRD00108-APP-T1#directory")
                .url(pcUrl)
                .build();
        //MessageDTO
        PushMessageDTO pushMessageDTO = PushMessageDTO.builder()
                .fromType(3)
                .isSSO(1)
                .objectName("RC:ImgTextMsg")
                .from("monitor_admin")
                .targetType(1)
                //.ssoToken(ssoToken)
                .includeSender(0)
                //.mentionType(2)
                //.mentionAccounts(str)
                .content(itmc)
                .target(members)
                .pcOpenMode(1)
                //.displayMode("PC")
                .build();
        Result sendMessageResult = OpenApiClient.pushMessage(pushMessageDTO, longforChatUrl+"/pushMessage", longforChatKey, 20);
        log.info("PC图文消息Request:" + pushMessageDTO.toString());
        log.info("PC图文消息Response:" + JSONObject.toJSONString(sendMessageResult));
    }


    public void sendTextMsg(String nowOA,int number,BigDecimal total, String responseUser, String responseDate) {
        log.info("发送Text消息");
        List<String> members = new ArrayList<>();
        members.add(nowOA);
        //members.add("w_liuyingshuang");
        TxtMsgContent txtMsgContent = TxtMsgContent.builder()
                .content("您名下有" + number + "笔借款/保证金押金尚未清理（如领借款未冲账、已支付的保证金/押金未收回等），共计" + total.doubleValue()
                        + "万元。请您预估冲帐或收回时间，并于" + responseDate + "前点开下列链接反馈。\n" + "如有问题，可咨询【" + responseUser + "】").build();

        PushMessageDTO pushMessageDTO = PushMessageDTO.builder()
                .fromType(3)
                .isSSO(0)
                .objectName("RC:TxtMsg")
                .from("monitor_admin")
                .targetType(1)
                //.ssoToken(ssoToken)
                .includeSender(0)
                //.mentionType(2)
                //.mentionAccounts(str)
                .content(txtMsgContent)
                .target(members)
                .build();
        Result sendMessageResult = OpenApiClient.pushMessage(pushMessageDTO, longforChatUrl+"/pushMessage", longforChatKey, 20);
        log.info("Text消息Request:" + pushMessageDTO.toString());
        log.info("Text消息Response:" + JSONObject.toJSONString(sendMessageResult));
    }

    /**
     * 根据传过来的gx_flag属性返回符合条件的对应城市，用来组成DwCpClearUpDetail的查询条件 in（'地区1','地区2'） gx_flag = 1对应推送代办，gx_flag = 2为代办二次发送
     * 为代办二次推送
     * @return
     */
    public List<String> getAreaListByFlag(int gx_flag){
        //待返回的地区集合
        List<String> areaList = new ArrayList<>();
        //查询的参数条件集合
        List<String> paramsList = new ArrayList<>();
        if (gx_flag == 1) {
            paramsList.add("推送待办");
        } else if (gx_flag == 2) {
            paramsList.add("待办二次发送");
        } else {
            return areaList;
        }

        //获取最小年月
        DwCpMonthQuarter dwCpMonthQuarter = iDwCpMonthQuarterService.getMinDataDate();
        if (dwCpMonthQuarter == null || org.apache.commons.lang.StringUtils.isBlank(dwCpMonthQuarter.getDataDate())) {
            return areaList;
        }
        String minDataDate = dwCpMonthQuarter.getDataDate();
        //获取当前日期字符串
        String todayStr = StringUtil.getToday();
        //获取待提交的地区集合
        List<DinDim> dinDimList = iDinDimService.getDinDimList(paramsList);
        if(dinDimList != null) {
            for (DinDim dimRecord : dinDimList) {
                List<DinDimValue> dinDimValueList = dimRecord.getDinDimValueList();
                if (dinDimValueList == null || dinDimValueList.size() <= 0) {
                    continue;
                } else {
                    for (DinDimValue dimValRecord : dinDimValueList) {
                        String concatDay = "";
                        String dimValueName = dimValRecord.getDimValueName();
                        if (Integer.parseInt(dimValueName) < 10) {
                            concatDay = minDataDate + "-0" + dimValueName;
                        } else {
                            concatDay = minDataDate + "-" + dimValueName;
                        }
                        //如果当前日期等于地区配置的时间
                        if (todayStr.equals(concatDay)) {
                            areaList.add(dimRecord.getDimValue());
                            break;
                        }
                    }
                }
            }
        }
        return areaList;
    }
/*    public static void main(String[] args ) {
        BigDecimal total = new BigDecimal("135423559.123457");
        BigDecimal divide = new BigDecimal("10000");
        System.out.println(total.divide(divide));
        System.out.println(total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
    }*/
}