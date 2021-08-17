package com.longfor.fsscreportdb.ods.controller;

import ETLJobHandaler.LhChatGroupJobHandler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longfor.fsscreportdb.oa.entity.DinDimValue;
import com.longfor.fsscreportdb.oa.entity.DwCpClearUpDetail;
import com.longfor.fsscreportdb.oa.service.IDinDimValueService;
import com.longfor.fsscreportdb.oa.service.IDwCpClearUpDetailService;
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
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;


@RestController
@RequestMapping("/longforGroup")
public class SendMsgControllerOrigin {

    private static final Logger log = LoggerFactory.getLogger(LhChatGroupJobHandler.class);

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
    @Autowired
    private IDwCpClearUpDetailService iDwCpClearUpDetailService;

    public static final String longforKey = "4dbb66cc-9dcd-4a94-9ffe-1a9f363f722a";  //预生产
    public static final String longforUrl = "https://api-pre.longhu.net/longchat-open-api-npre"; //预生产
    public static final String amSendKey = "";
    public static final String amSendUrl = "";

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
    private String debugFlag = "1";

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
        //log.info("获取跳转URL" + pcUrl);

        //获取往来清理代办季度
        wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DIM_ID", "1906");
        wrapper1.eq("USE_FLAG", 1);
        tp = iDinDimValueService.getSSOToken(wrapper1);
        quater = tp.getDimValueName();
        //log.info("获取往来清理代办季度" + quater);

        //获取往来清理代办群日期
        wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DIM_ID", "1907");
        wrapper1.eq("USE_FLAG", 1);
        tp = iDinDimValueService.getSSOToken(wrapper1);
        groupDate = tp.getDimValueName();
        //log.info("获取往来清理代办群日期" + groupDate);

        //获取往来清理代办发送日期
        wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DIM_ID", "1908");
        wrapper1.eq("USE_FLAG", 1);
        tp = iDinDimValueService.getSSOToken(wrapper1);
        sendDate = tp.getDimValueName();
        //log.info("获取往来清理代办发送日期" + sendDate);

        //log.info("获取往来清理数据-发送的记录 Start");
        List<OdsHrDirct> odsHrDircts = odsHrDirctService.searchUserByOaAccct();


        //log.info("往来清理的记录数为：" + (odsHrDircts != null ? odsHrDircts.size() : "null"));
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

        //processRecordIndividual(updListC1,"C1");
        //processRecordIndividual(updListC2,"C2");
        //processRecordIndividual(updListC3,"C3");
        //processRecordIndividual(updListC4,"C4");
        //processRecordIndividual(updListC5,"C5");
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
     */
    private void processRecordIndividual(List<OdsHrDirct> dccud,String channalNm) {
        if (dccud == null || dccud.size() <= 0) {
            return;
        }
        List<String> userList = new ArrayList<String>();

        OdsHrDirct updRecord = null;
        String responsUser = "";
        String userDirct = "";
        String areaName = "";
        for (int i = 0; i < dccud.size(); i++) {
            updRecord = dccud.get(i);
            areaName = updRecord.getAreaName();
            responsUser = updRecord.getOaccount();
            userDirct = updRecord.getOrgDirct();
            JSONObject amProcessResult = this.getManagersByRoleAndArea(areaName, channalNm,false);
            List<String> managerList = null;
            if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
            }
            DwCpClearGroupInfo groupInfo = new DwCpClearGroupInfo();
            String managerStr = "";
            if (managerList != null) {
                for(String manager : managerList) {
                    managerStr += (manager + ",");
                }
            }
            groupInfo.setMgrList(managerStr);
            groupInfo.setUserList(userDirct);
            groupInfo.setGroupId(UUID.randomUUID().toString());
            groupInfo.setArea(areaName);
            groupInfo.setChanal(channalNm);
            groupInfo.setGroupName(responsUser);
            //保存拉群消息
            this.groupInfoService.save(groupInfo);
        }
    }


    /**
     * 按地区分组并处理
     * @param dccud
     */
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
        String areaName = "";
        List<OdsDepartmetClear> deptRecrdList = new ArrayList<OdsDepartmetClear>();
        int deptNumber=0;
        BigDecimal deptTotal = new BigDecimal("0");
        BigDecimal divide = new BigDecimal("10000");
        for (OdsHrDirct updRecord : areaRecordList) {
            areaName = updRecord.getAreaName();
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
     * 根据责任人OA号获取业务部门责任人
     *
     * @return
     * @author qhl 2021-07-14
     */
    public List<OdsHrDirct> searchLeaderByOaAccct() {
        List<OdsHrDirct> odsHrDirct = odsHrDirctService.searchUserByOaAccct();
        return odsHrDirct;
    }


    /**
     * 根据用户拉群
     */
    public void createGroupByUserList(
            String channalNm,List<String> userList, List<String> managerList, String areaName, List<OdsDepartmetClear> deptList, int totalNum, BigDecimal totalAmount) {
        //将区域负责人放入发送列表
        if (managerList != null) {
            for(String manager : managerList) {
                if (!userList.contains(manager)) {
                    userList.add(manager);
                }
            }
        }
        if("1".equals(debugFlag)) {
            log.info("航道=" + channalNm);
            log.info("图文链接=" + pcUrl+"?CHANAL="+channalNm+"&AREA="+areaName);
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
            //保存拉群消息
            this.groupInfoService.save(groupInfo);

        }else {
            DwCpClearGroupInfo groupInfo = new DwCpClearGroupInfo();
            userList.add("w_zhaobo14");
            userList.add("w_liuyingshuang");
            userList.add("guoyu5");
            userList.add("majianjing");
            userList.add("huangjiayi01");
            userList.add("maben01");
            userList.add("ganghonghai");
            userList.add("w_duzhenxing");
            userList.add("monitor_admin");
            CreateGroupDTO createGroupDTO = CreateGroupDTO.builder()
                    .groupName("借款/保证金押金等清理预警-【" + areaName + "】地区")
                    .members(userList)
                    .usercode("monitor_admin")
                    .build();
            Result createGroupResult =
                    OpenApiClient.createGroup(createGroupDTO, longforUrl + "/createGroup", longforKey, null);
            //log.info(JSONObject.toJSONString(createGroupResult));
            //创建群组成功，发送消息
            if ("0".equals(createGroupResult.getCode())) {
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


                //调用方法生成消息主体
                String content = appendMsgContent(areaName, totalAmount, deptList);
                String data = JSONObject.toJSONString(createGroupResult.getData());
                MsgData msgData = JSONObject.parseObject(data, MsgData.class);
                //log.info("新建群组groupID:{}" + msgData.getGroupId());
                List<String> list = new ArrayList<>();
                list.add(msgData.getGroupId());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //sendMsg(list, content, areaName, channalNm);*/
                //groupInfo.setGroupId(msgData.getGroupId());
                groupInfo.setArea(areaName);
                groupInfo.setGroupId(UUID.randomUUID().toString());
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
    public void sendMsg(List<String> groupId,String content,String areaName,String channalName){
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
                .isSSO(0)
                .build();
        String areaCode = areaMapping.get(areaName);
        if (areaCode == null) {
            log.error("地区：" + areaName + ", 找不到对应的地区CODE");
            areaCode = "";
        }
        //调用API
        Result sendMessageResult = OpenApiClient.pushMessage(pushMessageDTO,
                longforUrl + "/pushMessage",
                longforKey, 20);
        //log.info(JSONObject.toJSONString(sendMessageResult));
        //log.info("发送文本消息回调主体：{}"+JSONObject.toJSONString(sendMessageResult));
        //创建图文消息主体 CHANAL   --航道参数   AREA      --地区参数
        ImgTextMsgContent imgTextMsgContent = ImgTextMsgContent.builder()
                .title("待清理借款/保证金押金明细")
                .content(content)
                .imageUri("http://frtest.longhu.net/webroot/decision?ticket=ST-545-HCH1p7dNFfp8oNjQU6MFxE-JK4cALI-PRD00108-APP-T1#directory")
                .url(pcUrl+"?CHANAL="+channalName+"&AREA="+areaCode)
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
                longforUrl + "/pushMessage",
                longforKey, 20);
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
        return  quater+"需清理借款/保证金押金等明细已于"+
                groupDate+"龙信推送给各经办人，截止"+sendDate+"，【"+areaName+"】地区尚有"+totalAmount+"万元待反馈预估冲账或收回时间，其中：\n" +
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
        String areaCode = ""  ;
        List<String> managerList = new ArrayList<String>();
        if (isRegion) {
            areaCode = areaName;
        } else {
            if (StringUtils.isNotBlank((areaName))) {
                String shortName = areaName.substring(0,2);
                String lastName = areaName.substring(areaName.length() - 2);
                log.info("areaName=" + areaName + ", shortName=" + shortName + ",lastName=" + lastName);
                if ("地区".equals(lastName)) {
                    log.info("get area code for "+ shortName);
                    areaCode = areaMapping.get(shortName);
                }
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
                                    managerList.add(userName);
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

    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping("/sendMsg")
    @ResponseBody
    public JSONObject sendMsg(String flag){
        JSONObject result = new JSONObject();
        try {
            debugFlag = flag;
            sendMsgByGroup();
            //log.info("调用方法");
        } catch (Exception e) {
            log.error("发送消息失败： {}", e.toString());
            result.put("code", "-1");
        }
        result.put("code", "1");
        return result;
    }


    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping("/getFinancePerson")
    @ResponseBody
    public JSONObject getFinancePerson(String flag){
        JSONObject result = new JSONObject();
        try {

            //log.info("获取往来清理数据-发送的记录 Start");
            List<OdsHrDirct> odsHrDircts = odsHrDirctService.searchUserByOaAccct();

            for (OdsHrDirct tmpRd : odsHrDircts) {
                String channal = tmpRd.getChannelNm();
                String regionId = tmpRd.getRegionId();
                String regionName = tmpRd.getRegionName();
                boolean isRegion = true;
                if ("C1".equals(channal)) {
                    isRegion = false;
                    regionId = regionName;
                }
                JSONObject amProcessResult =
                        getManagersByRoleAndArea(regionId, channal, isRegion);
                List<String> managerList = null;
                if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                    managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                }
                String managerStr = "";
                if (managerList != null) {
                    for(String manager : managerList) {
                        managerStr += (manager + ",");
                    }
                }
                log.info(channal + "        " + regionId + "         " + regionName + "         " + managerStr);
            }

/*            List<String> lineList = new ArrayList<String>();
            //lineList.add("C1");
            //lineList.add("C2");
            lineList.add("C3");
            //lineList.add("C4");
            //lineList.add("C5");

            for (String lineCode : lineList) {
                for (String area: areaMapping.keySet()) {
                    JSONObject amProcessResult =
                            getManagersByRoleAndArea(area, lineCode, false);
                    List<String> managerList = null;
                    if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                        managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                    }
                    DwCpClearGroupInfo groupInfo = new DwCpClearGroupInfo();
                    String managerStr = "";
                    if (managerList != null) {
                        for(String manager : managerList) {
                            managerStr += (manager + ",");
                        }
                    }
                    groupInfo.setMgrList(managerStr);
                    groupInfo.setGroupId(UUID.randomUUID().toString());
                    groupInfo.setArea(area);
                    groupInfo.setChanal(lineCode);
                    groupInfo.setGroupName("借款/保证金押金等清理预警-【" + area + "】地区");
                    //保存拉群消息
                    this.groupInfoService.save(groupInfo);
                }
            }*/
        } catch (Exception e) {
            log.error("发送消息失败： {}", e.toString());
            result.put("code", "-1");
        }
        result.put("code", "1");
        return result;
    }


    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping("/auditAction")
    @ResponseBody
    public JSONObject auditAction(String flag){

        JSONObject result = new JSONObject();

        try {

            List<String> lineList = new ArrayList<String>();
            //lineList.add("C1");
            //lineList.add("C2");
            lineList.add("C3");
            //lineList.add("C4");
            //lineList.add("C5");

            for (String lineCode : lineList) {
                for (String area: areaMapping.keySet()) {
                    JSONObject amProcessResult =
                            getManagersByRoleAndArea(area, lineCode,false);
                    List<String> managerList = null;
                    if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                        managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                    }
                    DwCpClearGroupInfo groupInfo = new DwCpClearGroupInfo();
                    String managerStr = "";
                    if (managerList != null) {
                        for(String manager : managerList) {
                            managerStr += (manager + ",");
                        }
                    }
                    groupInfo.setMgrList(managerStr);
                    groupInfo.setGroupId(UUID.randomUUID().toString());
                    groupInfo.setArea(area);
                    groupInfo.setChanal(lineCode);
                    groupInfo.setGroupName("借款/保证金押金等清理预警-【" + area + "】地区");
                    //保存拉群消息
                    this.groupInfoService.save(groupInfo);
                }
            }
        } catch (Exception e) {
            log.error("发送消息失败： {}", e.toString());
            result.put("code", "-1");
        }
        result.put("code", "1");
        return result;
    }


    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping("/sendLongforMsg")
    @ResponseBody
    public JSONObject sendLongforMsg(String flag) {
        JSONObject result = new JSONObject();
        try {
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
            QueryWrapper<DwCpClearUpDetail> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("GX_FLAG","1");
            wrapper2.orderByDesc("RESPON_USER");
            List<DwCpClearUpDetail> dccud = iDwCpClearUpDetailService.getListBeanByWrapper(wrapper2);
            log.info("往来清理的记录数为：" + (dccud != null ? dccud.size() : "null"));
            //如果为空进行初始化
            if (dccud == null) {
                dccud = new ArrayList<DwCpClearUpDetail>();
            }

            String lastOA = ""; //上一条数据的账号
            String lastArea = ""; //上一条记录的地区
            int number = 0;  //往来数据数目
            BigDecimal total = new BigDecimal("0");
            log.info("已获取清理数据，进行计算");

            List<DwCpClearUpDetail> updList = new ArrayList<DwCpClearUpDetail>();
            int batchSize = 0;
            DwCpClearUpDetail updRecord = null;
            for(int i = 0; i < dccud.size(); i++) {
                updRecord = dccud.get(i);
                String nowOA = "";  //当前用户的账号
                String getUser = updRecord.getResponUser();
                BigDecimal amount = updRecord.getAmount();
                if (amount == null) {
                    amount = new BigDecimal(0);
                }
                //如果责任人为空，则跳过处理
                if(org.apache.commons.lang3.StringUtils.isBlank(getUser)){
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
                if (org.apache.commons.lang3.StringUtils.isBlank(lastOA)) {
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
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(lastArea)) {
                            wrapper1 = new QueryWrapper<>();
                            wrapper1.eq("DIM_ID","1904");
                            wrapper1.eq("USE_FLAG",1);
                            wrapper1.eq("DIM_VALUE_ID", lastArea.trim());
                            tp = iDinDimValueService.getSSOToken(wrapper1);
                            String dimVal = tp.getDimValueName();
                            if (org.apache.commons.lang3.StringUtils.isNotBlank(dimVal)) {
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
            if (org.apache.commons.lang3.StringUtils.isNotBlank(lastArea)) {
                wrapper1 = new QueryWrapper<>();
                wrapper1.eq("DIM_ID","1904");
                wrapper1.eq("USE_FLAG",1);
                wrapper1.eq("DIM_VALUE_ID", lastArea.trim());
                tp = iDinDimValueService.getSSOToken(wrapper1);
                String dimVal = tp.getDimValueName();
                if (org.apache.commons.lang3.StringUtils.isNotBlank(dimVal)) {
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
        } catch (Exception e) {
            log.error("发送消息失败： {}", e.toString());
            result.put("code", "-1");
        }
        result.put("code", "1");
        return result;
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
        Result sendMessageResult = OpenApiClient.pushMessage(pushMessageDTO, longforUrl + "/pushMessage", longforKey, 20);
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
        Result sendMessageResult = OpenApiClient.pushMessage(pushMessageDTO, longforUrl + "/pushMessage", longforKey, 20);
        log.info("Text消息Request:" + pushMessageDTO.toString());
        log.info("Text消息Response:" + JSONObject.toJSONString(sendMessageResult));
    }

    public static void main(String[] args) {
        List<String> areaList = new ArrayList<>();
        areaList.add("东北地产");
        areaList.add("东北地区");
        areaList.add("人力平台");
        areaList.add("北京地区");
        areaList.add("南京地区");
        areaList.add("南宁地区");
        areaList.add("厦门地区");
        areaList.add("合肥地区");
        areaList.add("天津地区");
        areaList.add("广州地区");
        areaList.add("成都地区");
        areaList.add("武汉地区");
        areaList.add("江西地区");
        areaList.add("沪苏地区");
        areaList.add("济南地区");
        areaList.add("浙江地区");
        areaList.add("海南地区");
        areaList.add("深港地区");
        areaList.add("烟威地区");
        areaList.add("珠海地区");
        areaList.add("福州地区");
        areaList.add("苏南地区");
        areaList.add("西安地区");
        areaList.add("郑州地区");
        areaList.add("重庆地区");
        areaList.add("长沙地区");
        areaList.add("集团总部");
        areaList.add("青岛地区");



        for(String areaFull : areaList) {
            String areaCode = "";
            String shortName = "";
            String lastName = "";
            if (StringUtils.isNotBlank((areaFull))) {
                shortName = areaFull.substring(0,2);
                lastName = areaFull.substring(areaFull.length() - 2);
                if ("地区".equals(lastName)) {
                    areaCode = areaMapping.get(shortName);
                }
            }
            if (StringUtils.isBlank(areaCode)) {
                System.err.println("没有找到对应的地区代码 - " + areaFull);
            } else {
                System.out.println("fullName=" + areaFull + ", shortName=" + shortName + "areaCode=" + areaCode);
            }
        }

    }
}
