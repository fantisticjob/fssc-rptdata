package ETLJobHandaler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longfor.fsscreportdb.oa.entity.DinDimValue;
import com.longfor.fsscreportdb.oa.service.IDinDimValueService;
import com.longfor.fsscreportdb.ods.entity.DwCpClearGroupInfo;
import com.longfor.fsscreportdb.ods.entity.MsgData;
import com.longfor.fsscreportdb.ods.entity.OdsDepartmetClear;
import com.longfor.fsscreportdb.ods.entity.OdsHrDirct;
import com.longfor.fsscreportdb.ods.service.IDwCpClearGroupInfoService;
import com.longfor.fsscreportdb.ods.service.IOdsHrDirctService;
import com.longfor.longchat.openapi.bean.Result;
import com.longfor.longchat.openapi.client.OpenApiClient;
import com.longfor.longchat.openapi.content.ImgTextMsgContent;
import com.longfor.longchat.openapi.content.TxtMsgContent;
import com.longfor.longchat.openapi.dto.CreateGroupDTO;
import com.longfor.longchat.openapi.dto.PushMessageDTO;
import com.longfor.longchat.openapi.enums.ObjectNameEnum;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import groovy.util.logging.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Yingshuang
 * @date 2021/6/30
 */
@JobHandler(value = "LhChatGroupJobHandler")
@Component
@Slf4j
public class LhChatGroupJobHandlerDebugBak extends IJobHandler {

    private static final Logger log = LoggerFactory.getLogger(LhChatGroupJobHandlerDebugBak.class);

    //AM API URL：根据地区和角色查询用户信息： 0-旧组架
    private static final String AM_API_GET_USERLIST = "/public/v3/oru/getOrgRoleUsers";
    //地区名称与CODE映射表
    private static Map<String, String> areaMapping = new HashMap<String, String>();

    @Autowired
    private IOdsHrDirctService odsHrDirctService;
    @Autowired
    private IDinDimValueService iDinDimValueService;
    @Autowired
    private IDwCpClearGroupInfoService groupInfoService;

    static {
        areaMapping.put("北京", "1000000801");
        areaMapping.put("天津", "1000004147");
        areaMapping.put("东北", "1000002652");
        areaMapping.put("济南", "1000003108");
        areaMapping.put("青岛", "1000002261");
        areaMapping.put("烟威", "1000001975");
        areaMapping.put("重庆", "1000000081");
        areaMapping.put("成都", "1000001293");
        areaMapping.put("西安", "1000001651");
        areaMapping.put("武汉", "1000004317");
        areaMapping.put("长沙", "1000001941");
        areaMapping.put("郑州", "1000005541");
        areaMapping.put("合肥", "1000004853");
        areaMapping.put("沪苏", "1000002396");
        areaMapping.put("江西", "1000005482");
        areaMapping.put("南京", "1000003166");
        areaMapping.put("苏南", "1000002808");
        areaMapping.put("浙江", "1000001127");
        areaMapping.put("珠海", "1000005393");
        areaMapping.put("深港", "1000004737");
        areaMapping.put("南宁", "1000006506");
        areaMapping.put("海南", "1000006120");
        areaMapping.put("厦门", "1000001872");
        areaMapping.put("福州", "1000005279");
        areaMapping.put("广州", "1000003136");
    }

    private String pcUrl = "";
    private String quater = "";
    private String groupDate ="";
    private String sendDate = "";
    private String debugFlag = "";

    @Autowired
    private RestTemplate template;

    @Value("${am.host}")
    private String hostAm;

    @Value("${am.api-key}")
    private String apiKeyAm;

    public String getHostAm() {
        return hostAm;
    }

    public void setHostAm(String hostAm) {
        this.hostAm = hostAm;
    }

    public String getApiKeyAm() {
        return apiKeyAm;
    }

    public void setApiKeyAm(String apiKeyAm) {
        this.apiKeyAm = apiKeyAm;
    }

    public RestTemplate getTemplate() {
        return template;
    }

    public void setTemplate(RestTemplate template) {
        this.template = template;
    }

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        debugFlag = s;
        try {
            sendMsgByGroup();
        } catch (Exception e){
            log.error("龙信拉群发消息失败:{}"+e.toString());
            return FAIL;
        }
        return SUCCESS;
    }

    /**
     * 群消息发送
     *
     * @author qhl 2021-07-14
     */
    public void sendMsgByGroup() {
        //获取PC/Mobile端的跳转URL
        QueryWrapper<DinDimValue> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DIM_ID","1909");
        wrapper1.eq("USE_FLAG",1);
        DinDimValue tp = iDinDimValueService.getSSOToken(wrapper1);
        pcUrl = tp.getDimValueName();
        log.info("获取跳转URL" + pcUrl);

        //获取往来清理代办季度
        wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DIM_ID", "1906");
        wrapper1.eq("USE_FLAG", 1);
        tp = iDinDimValueService.getSSOToken(wrapper1);
        quater = tp.getDimValueName();
        log.info("获取往来清理待办季度" + quater);

        //获取往来清理代办群日期
        wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DIM_ID", "1907");
        wrapper1.eq("USE_FLAG", 1);
        tp = iDinDimValueService.getSSOToken(wrapper1);
        groupDate = tp.getDimValueName();
        log.info("获取往来清理待办群日期" + groupDate);

        //获取往来清理代办发送日期
        wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DIM_ID", "1908");
        wrapper1.eq("USE_FLAG", 1);
        tp = iDinDimValueService.getSSOToken(wrapper1);
        sendDate = tp.getDimValueName();
        log.info("获取往来清理待办消息发送日期" + sendDate);
        //获取需要拉群的记录
        List<OdsHrDirct> odsHrDircts = odsHrDirctService.searchUserByOaAccct();

        log.info("往来清理拉群的记录数为：" + (odsHrDircts != null ? odsHrDircts.size() : "null"));
        //如果为空进行初始化
        if (odsHrDircts == null) {
            odsHrDircts = new ArrayList<OdsHrDirct>();
        }

        //保存不同航道的list
        List<OdsHrDirct> updListC1 = new ArrayList<OdsHrDirct>();
        List<OdsHrDirct> updListC2 = new ArrayList<OdsHrDirct>();
        List<OdsHrDirct> updListC4 = new ArrayList<OdsHrDirct>();
        List<OdsHrDirct> updListC5 = new ArrayList<OdsHrDirct>();
        //将五个航道list添加信息
        for (OdsHrDirct os: odsHrDircts) {
/*            if (StringUtils.isNotBlank(os.getAreaName()) && "集团".equals(os.getAreaName())) {
                //忽略地区是集团的记录
                continue;
            }*/
            if(StringUtils.isNotBlank(os.getChannelNm())) {
                switch (os.getChannelNm()) {
                    case "C1":
                        updListC1.add(os);
                        break;
                    case "C2":
                        updListC2.add(os);
                        break;
                    case "C4":
                        updListC4.add(os);
                        break;
                    case "C5":
                        updListC5.add(os);
                        break;
                }
            }
        }
        //C1和C3合并拉群，按照25个地区进行拉群
        getLastAreaGroupList(updListC1,"C1");
        //C2,C4,C5 根据大区进行拉群
        getLastRegionGroupList(updListC2,"C2");
        getLastRegionGroupList(updListC4,"C4");
        getLastRegionGroupList(updListC5,"C5");
    }

    /**
     * 按地区分组并处理
     * @param dccud
     * @param channalNm
     */
    private void getLastRegionGroupList(List<OdsHrDirct> dccud, String channalNm) {
        if (dccud == null || dccud.size() <= 0) {
            log.info("没有需要处理的往来清理记录");
            return;
        }
        String lastRegionId = ""; //上一条记录的大区
        String lastRegionName = "";
        String currRegionId = "";
        String currentRegionName = "";
        BigDecimal divide = new BigDecimal("10000");
        List<String> userList = new ArrayList<String>();
        List<OdsHrDirct> areaRecordList = new ArrayList<OdsHrDirct>();
        int number=0;
        BigDecimal total = new BigDecimal("0");

        OdsHrDirct updRecord = null;
        String responsUser = "";
        String userDirct = "";
        for (int i = 0; i < dccud.size(); i++) {
            updRecord = dccud.get(i);
            currRegionId = updRecord.getRegionId();
            currentRegionName = updRecord.getRegionName();
            BigDecimal amount = updRecord.getAmount();
            if (amount == null) {
                amount = new BigDecimal(0);
            }
            //处理第一条记录
            if (StringUtils.isBlank(lastRegionId)) {
                lastRegionId = currRegionId;
                lastRegionName = currentRegionName;
                responsUser = updRecord.getOaccount();
                if(!userList.contains(responsUser)){
                    userList.add(responsUser);
                    userDirct = updRecord.getOrgDirct();
                    if (StringUtils.isNotBlank(userDirct)){
                        if(userDirct.indexOf(",") != -1){
                            //log.info("存在多个责任人：" + getUser);
                            String[] dirctOa = userDirct.split(",");
                            for (String s : dirctOa){
                                if(!userList.contains(s)){
                                    userList.add(s);
                                }
                            }
                        } else {
                            if(!userList.contains(userDirct)){
                                userList.add(userDirct);
                            }
                        }
                    }
                }
                areaRecordList.add(updRecord);
                number++;
                total = total.add(amount.abs());
            } else {
                if (!lastRegionId.equals(currRegionId)) {
                    //按照区域进行拉群
                    try {
                        List<OdsDepartmetClear> deptRecordList = prcessDeptmentList(areaRecordList);
                        JSONObject amProcessResult = this.getManagersByRoleAndArea(lastRegionId, channalNm, true);
                        List<String> managerList = null;
                        if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                            managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                        }
                        createGroupByUserList(channalNm,userList, managerList, lastRegionName, deptRecordList,number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
                    } catch (Exception e) {
                        log.error("【" + lastRegionName + "】拉群失败： {}", e.toString());
                    }
                    areaRecordList.clear();
                    userList.clear();
                    areaRecordList.add(updRecord);
                    responsUser = updRecord.getOaccount();
                    if(!userList.contains(responsUser)){
                        userList.add(responsUser);
                        userDirct=updRecord.getOrgDirct();
                        if (StringUtils.isNotBlank(userDirct)){
                            if(userDirct.indexOf(",") != -1){
                                //log.info("存在多个责任人：" + getUser);
                                String[] dirctOa = userDirct.split(",");
                                for (String s : dirctOa){
                                    if(!userList.contains(s)){
                                        userList.add(s);
                                    }
                                }
                            } else {
                                if(!userList.contains(userDirct)){
                                    userList.add(userDirct);
                                }
                            }
                        }
                    }
                    number = 1;
                    total = new BigDecimal("0");
                    total = total.add(amount.abs());
                    lastRegionId = currRegionId;
                    lastRegionName = currentRegionName;
                } else {
                    responsUser = updRecord.getOaccount();
                    if(!userList.contains(responsUser)){
                        userList.add(responsUser);
                        userDirct=updRecord.getOrgDirct();
                        if (StringUtils.isNotBlank(userDirct)){
                            if(userDirct.indexOf(",") != -1){
                                //log.info("存在多个责任人：" + getUser);
                                String[] dirctOa = userDirct.split(",");
                                for (String s : dirctOa){
                                    if(!userList.contains(s)){
                                        userList.add(s);
                                    }
                                }
                            } else {
                                if(!userList.contains(userDirct)){
                                    userList.add(userDirct);
                                }
                            }
                        }
                    }
                    areaRecordList.add(updRecord);
                    number++;
                    total = total.add(amount.abs());
                }
            }
        }
        //处理最后一批记录
        if (0 < areaRecordList.size()) {
            try {
                List<OdsDepartmetClear> deptRecordList = prcessDeptmentList(areaRecordList);
                JSONObject amProcessResult = this.getManagersByRoleAndArea(lastRegionId, channalNm, true);
                List<String> managerList = null;
                if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                    managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                }
                createGroupByUserList(channalNm,userList, managerList, lastRegionName, deptRecordList,number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
            } catch (Exception e) {
                log.error("【" + lastRegionName + "】拉群失败： {}", e.toString());
            }
        }
    }

    /**
     * 按地区分组并处理
     * @param dccud
     * @param channalNm
     */
    private void getLastAreaGroupList(List<OdsHrDirct> dccud, String channalNm) {
        if (dccud == null || dccud.size() <= 0) {
            log.info("没有需要处理的往来清理记录");
            return;
        }
        String lastArea = ""; //上一条记录的地区
        String currArea = "";
        BigDecimal divide = new BigDecimal("10000");
        List<String> userList = new ArrayList<String>();
        List<OdsHrDirct> areaRecordList = new ArrayList<OdsHrDirct>();
        int number=0;
        BigDecimal total = new BigDecimal("0");

        OdsHrDirct updRecord = null;
        String responsUser = "";
        String userDirct = "";
        for (int i = 0; i < dccud.size(); i++) {
            updRecord = dccud.get(i);
            currArea = updRecord.getAreaName();
            BigDecimal amount = updRecord.getAmount();
            if (amount == null) {
                amount = new BigDecimal(0);
            }
            //处理第一条记录
            if (StringUtils.isBlank(lastArea)) {
                lastArea = currArea;
                responsUser = updRecord.getOaccount();
                if(!userList.contains(responsUser)){
                    userList.add(responsUser);
                    userDirct=updRecord.getOrgDirct();
                    if (StringUtils.isNotBlank(userDirct)){
                        if(userDirct.indexOf(",") != -1){
                            //log.info("存在多个责任人：" + getUser);
                            String[] dirctOa = userDirct.split(",");
                            for (String s : dirctOa){
                                if(!userList.contains(s)){
                                    userList.add(s);
                                }
                            }
                        } else {
                            if(!userList.contains(userDirct)){
                                userList.add(userDirct);
                            }
                        }
                    }
                }
                areaRecordList.add(updRecord);
                number++;
                total = total.add(amount.abs());
            } else {
                if (!lastArea.equals(currArea)) {
                    //按照区域进行拉群
                    try {
                        List<OdsDepartmetClear> deptRecordList = prcessDeptmentList(areaRecordList);
                        JSONObject amProcessResult = this.getManagersByRoleAndArea(lastArea, channalNm, false);
                        List<String> managerList = null;
                        if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                            managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                        }
                        createGroupByUserList(channalNm,userList, managerList,lastArea, deptRecordList,number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
                    } catch (Exception e) {
                        log.error("【" + lastArea + "】拉群失败： {}", e.toString());
                    }

                    areaRecordList.clear();
                    userList.clear();
                    areaRecordList.add(updRecord);
                    responsUser = updRecord.getOaccount();
                    if(!userList.contains(responsUser)){
                        userList.add(responsUser);
                        userDirct=updRecord.getOrgDirct();
                        if (StringUtils.isNotBlank(userDirct)){
                            if(userDirct.indexOf(",") != -1){
                                //log.info("存在多个责任人：" + getUser);
                                String[] dirctOa = userDirct.split(",");
                                for (String s : dirctOa){
                                    if(!userList.contains(s)){
                                        userList.add(s);
                                    }
                                }
                            } else {
                                if(!userList.contains(userDirct)){
                                    userList.add(userDirct);
                                }
                            }
                        }
                    }
                    number = 1;
                    total = new BigDecimal("0");
                    total = total.add(amount.abs());
                    lastArea = currArea;
                } else {
                    responsUser = updRecord.getOaccount();
                    if(!userList.contains(responsUser)){
                        userList.add(responsUser);
                        userDirct=updRecord.getOrgDirct();
                        if (StringUtils.isNotBlank(userDirct)){
                            if(userDirct.indexOf(",") != -1){
                                //log.info("存在多个责任人：" + getUser);
                                String[] dirctOa = userDirct.split(",");
                                for (String s : dirctOa){
                                    if(!userList.contains(s)){
                                        userList.add(s);
                                    }
                                }
                            } else {
                                if(!userList.contains(userDirct)){
                                    userList.add(userDirct);
                                }
                            }
                        }
                    }
                    areaRecordList.add(updRecord);
                    number++;
                    total = total.add(amount.abs());
                }
            }
        }
        //处理最后一批记录
        if (0 < areaRecordList.size()) {
            try {
                List<OdsDepartmetClear> deptRecordList = prcessDeptmentList(areaRecordList);
                JSONObject amProcessResult = this.getManagersByRoleAndArea(lastArea, channalNm, false);
                List<String> managerList = null;
                if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                    managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                }
                createGroupByUserList(channalNm,userList, managerList, lastArea, deptRecordList,number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
            } catch (Exception e) {
                log.error("【" + lastArea + "】拉群失败： {}", e.toString());
            }
        }
    }

    /**
     * 处理部门list  计算deptTotal deptNumber
     * @param areaRecordList
     * @return
     */
    private List<OdsDepartmetClear> prcessDeptmentList(List<OdsHrDirct> areaRecordList) {
        String lasDept = ""; //上一条记录的地区
        String currDept = "";
        List<OdsDepartmetClear> deptRecrdList = new ArrayList<OdsDepartmetClear>();
        int deptNumber=0;
        BigDecimal deptTotal = new BigDecimal("0");
        BigDecimal divide = new BigDecimal("10000");
        for (OdsHrDirct updRecord : areaRecordList) {
            currDept = updRecord.getDeptName();
            BigDecimal amount = updRecord.getAmount();
            if (amount == null) {
                amount = new BigDecimal(0);
            }
            if (StringUtils.isBlank(lasDept)) {
                lasDept = currDept;
                deptNumber++;
                deptTotal = deptTotal.add(amount.abs());
            } else {
                if (!lasDept.equals(currDept)) {
                    //
                    OdsDepartmetClear deptClear = new OdsDepartmetClear();
                    deptClear.setDeptAmount(deptTotal.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
                    deptClear.setDepartmentName(lasDept);
                    deptClear.setRecordNum(deptNumber);
                    deptRecrdList.add(deptClear);

                    lasDept = currDept;
                    deptNumber = 1;
                    deptTotal = new BigDecimal("0");
                    deptTotal = deptTotal.add(amount.abs());

                } else {
                    deptNumber++;
                    deptTotal = deptTotal.add(amount.abs());
                }
            }
        }

        OdsDepartmetClear deptClearLast = new OdsDepartmetClear();
        deptClearLast.setDeptAmount(deptTotal.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
        deptClearLast.setDepartmentName(lasDept);
        deptClearLast.setRecordNum(deptNumber);
        deptRecrdList.add(deptClearLast);

        return deptRecrdList;
    }


    /**
     * 根据用户拉群
     */
    public void createGroupByUserList(
            String channalNm, List<String> userList, List<String> managerList, String areaName, List<OdsDepartmetClear> deptList, int totalNum, BigDecimal totalAmount) {
        //将区域负责人放入发送列表
        if (managerList != null) {
            for(String manager : managerList) {
                if (!userList.contains(manager)) {
                    userList.add(manager);
                }
            }
        }
        if("1".equals(debugFlag)) {
            log.info("debug模式");
            log.info("群名称：" + "借款/保证金押金等清理预警-【" + areaName + "】地区");
            log.info("群消息主体：" + appendMsgContent(areaName, totalAmount, deptList));
            log.info("地区=" + areaName);
            log.info("地区总数=" + totalNum);
            log.info("地区总金额=" + totalAmount);

            log.info("龙信发件人：");
            String userStr="";
            for (String s : userList) {
                userStr += (s+",");
            }
            log.info(userStr);
            log.info("部门明细：");
            for (OdsDepartmetClear clear : deptList) {
                log.info(clear.toString());
            }
            DwCpClearGroupInfo groupInfo = new DwCpClearGroupInfo();
            String managerStr = "";
            String userListStr = "";
            if (managerList != null) {
                for(String manager : managerList) {
                    managerStr += (manager + ",");
                }
            }
            groupInfo.setMgrList(managerStr);
            if (userList != null) {
                for(String user : userList) {
                    userListStr += (user + ",");
                }
            }
            groupInfo.setUserList(userListStr);
            groupInfo.setGroupId(UUID.randomUUID().toString());
            groupInfo.setArea(areaName);
            groupInfo.setChanal(channalNm);
            groupInfo.setGroupName("借款/保证金押金等清理预警-【" + areaName + "】地区");
            groupInfo.setDeptNum(new BigDecimal(totalNum));
            groupInfo.setTotalAmt(totalAmount);
            groupInfo.setStatus("-1");
            //保存拉群消息
            this.groupInfoService.save(groupInfo);
        } else {
            log.info("发送消息模式");
            DwCpClearGroupInfo groupInfo = new DwCpClearGroupInfo();
            String managerStr = "";
            String userStr = "";
            if (managerList != null) {
                for(String manager : managerList) {
                    managerStr += (manager + ",");
                }
            }
            groupInfo.setMgrList(managerStr);
            if (userList != null) {
                for(String user : userList) {
                    userStr += (user + ",");
                }
            }
            groupInfo.setUserList(userStr);
            userList = new ArrayList<>();
            managerList = new ArrayList<>();


            List<String> testLongForGroupList = new ArrayList<String>();
            testLongForGroupList.add("monitor_admin");
            testLongForGroupList.add("w_liuyingshuang");
            testLongForGroupList.add("w_zhaobo14");
            CreateGroupDTO createGroupDTO = CreateGroupDTO.builder()
                    .groupName("借款/保证金押金等清理预警-【" + areaName + "】地区")
                    .members(testLongForGroupList)
                    .usercode("monitor_admin")
                    .build();

            Result createGroupResult = OpenApiClient.createGroup(createGroupDTO,
                    "https://api.longhu.net/longchat-open-api-prod/createGroup",
                    "c30aeb8b-b4e0-4217-91a1-63190589248d", null);
            log.info(JSONObject.toJSONString(createGroupResult));
            //创建群组成功，发送消息
            if ("0".equals(createGroupResult.getCode())) {
                //调用方法生成消息主体
                String content = appendMsgContent(areaName, totalAmount, deptList);

                content = content + "\n" + "负责人和业务部门负责人列表：" + userStr + "\n" + "财务负责人列表：" + managerStr;

                String data = JSONObject.toJSONString(createGroupResult.getData());
                MsgData msgData = JSONObject.parseObject(data, MsgData.class);
                log.info("新建群组groupID:{}" + msgData.getGroupId());
                List<String> list = new ArrayList<>();
                list.add(msgData.getGroupId());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    log.error("发消息前休眠失败：" + e.toString());
                }
                sendMsg(list, content, areaName, channalNm);
                groupInfo.setGroupId(msgData.getGroupId());
                groupInfo.setArea(areaName);
                groupInfo.setChanal(channalNm);
                groupInfo.setGroupName("借款/保证金押金等清理预警-【" + areaName + "】地区");
                groupInfo.setDeptNum(new BigDecimal(totalNum));
                groupInfo.setTotalAmt(totalAmount);
                groupInfo.setStatus("1");
                //保存拉群消息
                this.groupInfoService.save(groupInfo);
            }else{
                log.error("创建群组失败：code="+createGroupResult.getCode()+";msg="+createGroupResult.getMsg());
            }
        }
    }
    /*
      调用API发送消息
     */
    public void sendMsg(List<String> groupId, String content,String areaName,String channalName){
        //创建文字消息主体
        TxtMsgContent txtMsgContent = TxtMsgContent.builder()
                  .content(content).build();
        //创建发送消息对象
        PushMessageDTO pushMessageDTO = PushMessageDTO.builder()
                  .content(txtMsgContent)
                  .from("monitor_admin")
                  .fromType(3)
                  .objectName(ObjectNameEnum.TXTMSG.getName())
                  .target(groupId)
                  .targetType(2)
                  .build();
         //调用API
          Result sendMessageResult = OpenApiClient.pushMessage(pushMessageDTO,
                  "https://api.longhu.net/longchat-open-api-prod/pushMessage",
                  "c30aeb8b-b4e0-4217-91a1-63190589248d", 20);
          //log.info(JSONObject.toJSONString(sendMessageResult));
         // log.info("发送文本消息回调主体：{}"+JSONObject.toJSONString(sendMessageResult));
          //创建图文消息主体 CHANAL   --航道参数   AREA      --地区参数
          ImgTextMsgContent imgTextMsgContent = ImgTextMsgContent.builder()
                .title("待清理借款/保证金押金明细")
                .content(content)
                .imageUri("http://frtest.longhu.net/webroot/decision?ticket=ST-545-HCH1p7dNFfp8oNjQU6MFxE-JK4cALI-PRD00108-APP-T1#directory")
                .url(pcUrl+"?CHANAL="+channalName+"&AREA="+areaName)
                .build();
          //创建发送图文消息对象
          PushMessageDTO pushMessageDTOImg = PushMessageDTO.builder()
                .content(imgTextMsgContent)
                .from("monitor_admin")
                .fromType(3)
                .objectName(ObjectNameEnum.IMGTEXTMSG.getName())
                .target(groupId)
                .targetType(2)
                .pcOpenMode(1)
                .isSSO(1)
                .build();
          //调用API
          Result sendImgMsgResult = OpenApiClient.pushMessage(pushMessageDTOImg,
                "https://api.longhu.net/longchat-open-api-prod/pushMessage",
                "c30aeb8b-b4e0-4217-91a1-63190589248d", 20);
        //log.info(JSONObject.toJSONString(sendImgMsgResult));
        //log.info("发送图文消息回调主体：{}"+JSONObject.toJSONString(sendImgMsgResult));
    }

    /*
    生成消息主体
     */
    public String appendMsgContent(String areaName,BigDecimal totalAmount,List<OdsDepartmetClear> deptList){
        StringBuilder dept = new StringBuilder();
        for (OdsDepartmetClear o: deptList) {
            dept.append("【");
            dept.append(o.getDepartmentName());
            dept.append("】");
            dept.append(o.getDeptAmount().toString());
            dept.append("万元，");
            dept.append(o.getRecordNum());
            dept.append("笔；\n");
        }
        return  quater + "需清理借款/保证金押金等明细已于"+
                groupDate + "龙信推送给各经办人，截止"+sendDate+"，【"+areaName+"】地区尚有"+totalAmount+"万元待反馈预估冲账或收回时间，其中：\n" +
                dept.toString()+
                "明细如下，请各经办人查验龙信推送消息，并尽快完成清理：";
    }

    /**
     * 根据责任人OA号获取业务部门责任人
     * @param areaName 地区
     * @param lineCode  航道Code
     * @return
     */
    public JSONObject getManagersByRoleAndArea(String areaName, String lineCode, boolean isRegion) {
        String areaCode = "";
        List<String> managerList = new ArrayList<String>();
        if (isRegion) {
            areaCode = areaName;
        } else {
            if (StringUtils.isNotBlank((areaName))) {
                areaCode = areaMapping.get(areaName);
            }
            if (StringUtils.isBlank(areaCode)) {
                log.error("getManagersByRoleAndArea(): {}", "没有找到对应的地区代码 - " + areaName);
                return null;
            }
        }
        //request url
        String requestURL = hostAm + AM_API_GET_USERLIST;
        //返回结果
        JSONObject result = new JSONObject();
        //输入参数Json
        JSONObject inputParams = new JSONObject();
        //新旧组架标识 0-旧组架，1-新组架
        inputParams.put("newDeptFlag", 1);
        //用户oa账号
        inputParams.put("recursive", true);
        List<String> orgList = new ArrayList<String>();
        orgList.add(areaCode);
        inputParams.put("orgCodes", orgList);
        //根据不同的航道，设置role Code List
        List<String> roleCodes = new ArrayList<String>();
        switch (lineCode) {
            case "C1":
                roleCodes.add("R_UC_201090000");
                roleCodes.add("R_UC_201090100");
                break;
            case "C2" :
                roleCodes.add("R_UC_102024");
                roleCodes.add("R_UC_201130500");
                break;
            case "C4" :
                roleCodes.add("R_UC_201120200");
                break;
            case "C5":
                roleCodes.add("R_AM_03445");
                roleCodes.add("R_AM_03443");
                break;
        }
        inputParams.put("roleCodes", roleCodes);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("X-Gaia-Api-key",apiKeyAm );
        requestHeaders.add("Content-Type", "application/json");
        //生成请求参数
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(inputParams, requestHeaders);
        ResponseEntity<String> response = template.exchange(requestURL, HttpMethod.POST, requestEntity, String.class);
        //log.info("getManagersByRoleAndArea() response：{}",response);
        JSONObject json = JSON.parseObject(response.getBody());
        //判断返回结果
        if (json != null && StringUtils.isNotBlank(json.getString("respCode"))) {
            //
            JSONObject respCode = JSONObject.parseObject(json.getString("respCode"));
            if (respCode != null && StringUtils.isNotBlank(respCode.getString("code")) && "1".equals(respCode.getString("code"))) {
                List<Object> data = JSONArray.parseArray(json.getString("data"));
                //获取白名单人员
                List<String> whiteList = new ArrayList<String>();
                try {
                    String whiteUsrStr = "";
                    QueryWrapper<DinDimValue> wrapper = new QueryWrapper<>();
                    wrapper.eq("DIM_ID","1941");
                    wrapper.eq("USE_FLAG",1);
                    DinDimValue tp = iDinDimValueService.getSSOToken(wrapper);
                    whiteUsrStr = tp.getDimValueName();
                    if (StringUtils.isNotBlank(whiteUsrStr)) {
                        if(whiteUsrStr.indexOf(",") != -1){
                            //log.info("存在多个责任人：" + getUser);
                            String[] dirctUsr = whiteUsrStr.split(",");
                            for (String s : dirctUsr){
                                whiteList.add(s);
                            }
                        } else {
                            whiteList.add(whiteUsrStr);
                        }
                    }
                } catch (Exception e) {
                    log.error("获取人员白名单失败：{}", e.toString());
                }
                for (Object userObj : data) {
                    JSONObject jsonObject = JSONObject.parseObject(userObj.toString());
                    String lineFromAm = jsonObject.getString("orgProductGroup");
                    if (StringUtils.isNotBlank(lineFromAm) && lineFromAm.equals(lineCode)) {
                        //C4航道，只需要获取大区财务部的负责人(orgType=06)
                        if ("C4".equals(lineCode)) {
                            String orgType = jsonObject.getString("orgTypeCode");
                            if (StringUtils.isNotBlank(orgType) && "06".equals(orgType)) {
                                String userName = jsonObject.getString("userName");
                                if (StringUtils.isNotBlank(userName) && !managerList.contains(userName)) {
                                    if (whiteList.size() <= 0 || (0 < whiteList.size() && !whiteList.contains(userName))) {
                                        managerList.add(userName);
                                    }
                                }
                            }
                        } else {
                            String userName = jsonObject.getString("userName");
                            if (StringUtils.isNotBlank(userName) && !managerList.contains(userName)) {
                                managerList.add(userName);
                            }
                        }
                    }
                }
                result.put("resultCode", "0");
                result.put("userList", JSONObject.toJSON(managerList));
            } else {
                result.put("resultCode", "-1");
                result.put("resultMsg", "AM request 失败:" + respCode != null ? respCode.toJSONString(): "response code 为空");
            }
        } else {
            result.put("resultCode", "-1");
            result.put("resultMsg", "AM请求response为空");
        }
        return result;
    }
}
