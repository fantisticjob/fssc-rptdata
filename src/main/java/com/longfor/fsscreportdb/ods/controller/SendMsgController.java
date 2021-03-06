package com.longfor.fsscreportdb.ods.controller;

import ETLJobHandaler.LhChatGroupJobHandler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import com.longfor.fsscreportdb.ods.entity.DwCpClearGroupInfo;
import com.longfor.fsscreportdb.ods.entity.MsgData;
import com.longfor.fsscreportdb.ods.entity.OdsDepartmetClear;
import com.longfor.fsscreportdb.ods.entity.OdsHrDirct;
import com.longfor.fsscreportdb.ods.service.IDwCpClearGroupInfoService;
import com.longfor.fsscreportdb.ods.service.IOdsHrDirctService;
import com.longfor.fsscreportdb.utils.StringUtil;
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
public class SendMsgController {

    private static final Logger log = LoggerFactory.getLogger(LhChatGroupJobHandler.class);

    //AM API URL????????????????????????????????????????????? 0-?????????
    private static final String AM_API_GET_USERLIST = "/public/v3/oru/getOrgRoleUsers";
    //???????????????CODE?????????
    private static Map<String, String> areaMapping = new HashMap<String, String>();

    @Autowired
    private IOdsHrDirctService odsHrDirctService;
    @Autowired
    private IDinDimValueService iDinDimValueService;
    @Autowired
    private IDwCpClearGroupInfoService groupInfoService;
    @Autowired
    private IDwCpClearUpDetailService iDwCpClearUpDetailService;
    @Autowired
    private IDinDimService iDinDimService;
    @Autowired
    private IDwCpMonthQuarterService iDwCpMonthQuarterService;

    public static final String longforKey = "4dbb66cc-9dcd-4a94-9ffe-1a9f363f722a";  //?????????
    public static final String longforUrl = "https://api-pre.longhu.net/longchat-open-api-npre"; //?????????
    public static final String amSendKey = "";
    public static final String amSendUrl = "";

    static {
        areaMapping.put("??????", "1000000801");
        areaMapping.put("??????", "1000004147");
        areaMapping.put("??????", "1000002652");
        areaMapping.put("??????", "1000003108");
        areaMapping.put("??????", "1000002261");
        areaMapping.put("??????", "1000001975");
        areaMapping.put("??????", "1000000081");
        areaMapping.put("??????", "1000001293");
        areaMapping.put("??????", "1000001651");
        areaMapping.put("??????", "1000004317");
        areaMapping.put("??????", "1000001941");
        areaMapping.put("??????", "1000005541");
        areaMapping.put("??????", "1000004853");
        areaMapping.put("??????", "1000002396");
        areaMapping.put("??????", "1000005482");
        areaMapping.put("??????", "1000003166");
        areaMapping.put("??????", "1000002808");
        areaMapping.put("??????", "1000001127");
        areaMapping.put("??????", "1000005393");
        areaMapping.put("??????", "1000004737");
        areaMapping.put("??????", "1000006506");
        areaMapping.put("??????", "1000006120");
        areaMapping.put("??????", "1000001872");
        areaMapping.put("??????", "1000005279");
        areaMapping.put("??????", "1000003136");
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
     * ???????????????
     *
     * @author qhl 2021-07-14
     */
    public void sendMsgByGroup() {
        //??????PC/Mobile????????????URL
        QueryWrapper<DinDimValue> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DIM_ID","1909");
        wrapper1.eq("USE_FLAG",1);
        DinDimValue tp = iDinDimValueService.getSSOToken(wrapper1);
        pcUrl = tp.getDimValueName();
        log.info("????????????URL" + pcUrl);

        //??????????????????????????????
        wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DIM_ID", "1906");
        wrapper1.eq("USE_FLAG", 1);
        tp = iDinDimValueService.getSSOToken(wrapper1);
        quater = tp.getDimValueName();
        log.info("??????????????????????????????" + quater);

        //?????????????????????????????????
        wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DIM_ID", "1907");
        wrapper1.eq("USE_FLAG", 1);
        tp = iDinDimValueService.getSSOToken(wrapper1);
        groupDate = tp.getDimValueName();
        log.info("?????????????????????????????????" + groupDate);

        //????????????????????????????????????
        wrapper1 = new QueryWrapper<>();
        wrapper1.eq("DIM_ID", "1908");
        wrapper1.eq("USE_FLAG", 1);
        tp = iDinDimValueService.getSSOToken(wrapper1);
        sendDate = tp.getDimValueName();
        log.info("??????????????????????????????????????????" + sendDate);

        //??????????????????????????????????????????
        List<String> areaGroupOne = getAreaList(1);
        if (areaGroupOne != null && 0 < areaGroupOne.size()) {
            List<OdsHrDirct> odsHrDirctOne =
                    odsHrDirctService.searchLeaderByOaAccct(areaGroupOne);
            processClearRecordForGroup(odsHrDirctOne);
            log.info("????????????????????????????????????" + (odsHrDirctOne != null ? odsHrDirctOne.size() : "null"));
        }

        //??????????????????????????????
        List<String> areaGroupTwo = getAreaList(2);
        log.info("????????????????????????:"+areaGroupTwo);
        if (areaGroupTwo != null && 0 < areaGroupTwo.size()) {
            List<OdsHrDirct> odsHrDirctTwo =
                    odsHrDirctService.searchClearRrdForGroup(areaGroupTwo);
            alertGroupForSecondTime(odsHrDirctTwo);
            log.info("??????????????????????????????????????????" + (odsHrDirctTwo != null ? odsHrDirctTwo.size() : "null"));
        }
    }

    /**
     * ?????????????????? - ????????????????????????
     * @param odsHrDircts
     */
    public void processClearRecordForGroup(List<OdsHrDirct> odsHrDircts) {
        if (odsHrDircts == null || odsHrDircts.size() <= 0) {
            return;
        }
        //?????????????????????list
        List<OdsHrDirct> updListC1 = new ArrayList<OdsHrDirct>();
        List<OdsHrDirct> updListC2 = new ArrayList<OdsHrDirct>();
        List<OdsHrDirct> updListC4 = new ArrayList<OdsHrDirct>();
        List<OdsHrDirct> updListC5 = new ArrayList<OdsHrDirct>();
        //???????????????list????????????
        for (OdsHrDirct os: odsHrDircts) {
            if (StringUtils.isNotBlank(os.getAreaName()) && "??????".equals(os.getAreaName())) {
                //??????????????????????????????
                continue;
            }
            if(StringUtils.isNotBlank(os.getChannelNm())) {
                switch (os.getChannelNm()) {
                    case "C1":
                        updListC1.add(os);
                        break;
                    case "C2":
                        updListC2.add(os);
                        break;
                    case "C3":
                        os.setChannelNm("C1");
                        updListC1.add(os);
                        break;
                    case "C4":
                        //C4???????????????"??????"?????????12??????????????????????????????????????????
                        if (StringUtils.isNotBlank(os.getRegionName()) && os.getRegionName().contains("??????")) {
                            updListC4.add(os);
                        }
                        break;
                    case "C5":
                        updListC5.add(os);
                        break;
                }
            }
        }
        //C1???C3?????????????????????25?????????????????????
        log.info("????????????C1???"+updListC1);
        log.info("????????????C2???"+updListC2);
        log.info("????????????C4???"+updListC4);
        log.info("????????????C5???"+updListC5);
        getLastAreaGroupList(updListC1,"C1");
        //C2,C4,C5  ????????????????????????
        getLastRegionGroupList(updListC2,"C2");
        getLastRegionGroupList(updListC4,"C4");
        getLastRegionGroupList(updListC5,"C5");
    }

    /**
     * ?????????????????????
     * @param dccud
     */
    public void alertGroupForSecondTime(List<OdsHrDirct> dccud) {
        if (dccud == null || dccud.size() <= 0) {
            log.info("???????????????????????????????????????");
            return;
        }
        String lastGroupId = ""; //???????????????????????????ID
        String currGroupId = "";
        BigDecimal divide = new BigDecimal("10000");
        List<OdsHrDirct> areaRecordList = new ArrayList<OdsHrDirct>();
        int number=0;
        BigDecimal total = new BigDecimal("0");
        OdsHrDirct updRecord = null;
        String lastChannelName = "";
        String currChannelName = "";
        String lastAreaN = "";
        String currAreaN = "";

        for (int i = 0; i < dccud.size(); i++) {
            updRecord = dccud.get(i);
            currGroupId = updRecord.getGroupId();
            BigDecimal amount = updRecord.getAmount();
            currChannelName = updRecord.getChannelNm();
            if ("C1".equals(currChannelName) || "C3".equals(currChannelName)) {
                currAreaN = updRecord.getAreaName();
            } else {
                currAreaN = updRecord.getRegionName();
            }
            if (amount == null) {
                amount = new BigDecimal(0);
            }
            //?????????????????????
            if (StringUtils.isBlank(lastGroupId)) {
                lastGroupId = currGroupId;
                lastChannelName = currChannelName;
                lastAreaN = currAreaN;
                areaRecordList.add(updRecord);
                number++;
                total = total.add(amount.abs());
            } else {
                if (!lastGroupId.equals(currGroupId)) {
                    //???????????????????????????
                    try {
                        //??????????????????????????????????????????
                        List<OdsDepartmetClear> deptList = prcessDeptmentList(areaRecordList);
                        //??????????????????
                        String content =
                                appendMsgContent(lastAreaN, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP), deptList);
                        //??????????????????????????????ID
                        List<String> list = new ArrayList<>();
                        list.add(lastGroupId);
                        sendMsg(list, content, lastAreaN, lastChannelName);
                    } catch (Exception e) {
                        log.error("???" + lastGroupId + "???????????????????????? {}", e.toString());
                    }

                    //???????????????????????????
                    areaRecordList.clear();
                    areaRecordList.add(updRecord);
                    number = 1;
                    total = new BigDecimal("0");
                    total = total.add(amount.abs());
                    lastGroupId = currGroupId;
                    lastChannelName = currChannelName;
                    lastAreaN = currAreaN;
                } else {
                    areaRecordList.add(updRecord);
                    number++;
                    total = total.add(amount.abs());
                }
            }
        }
        //????????????????????????
        if (0 < areaRecordList.size()) {
            try {
                //??????????????????????????????????????????
                List<OdsDepartmetClear> deptList = prcessDeptmentList(areaRecordList);
                //??????????????????
                String content =
                        appendMsgContent(lastAreaN, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP), deptList);
                //??????????????????????????????ID
                List<String> list = new ArrayList<>();
                list.add(lastGroupId);
                sendMsg(list, content, lastAreaN, lastChannelName);
            } catch (Exception e) {
                log.error("???" + lastGroupId + "???????????????????????? {}", e.toString());
            }
        }
    }

    /**
     * ????????????????????????
     * @param dccud
     * @param channalNm
     */
    private void getLastRegionGroupList(List<OdsHrDirct> dccud, String channalNm) {
        if (dccud == null || dccud.size() <= 0) {
            log.info("???????????????????????????????????????");
            return;
        }
        String lastRegionId = ""; //????????????????????????
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
            //?????????????????????
            if (StringUtils.isBlank(lastRegionId)) {
                lastRegionId = currRegionId;
                lastRegionName = currentRegionName;
                responsUser = updRecord.getOaccount();
                if(!userList.contains(responsUser)){
                    userList.add(responsUser);
                    userDirct = updRecord.getOrgDirct();
                    if (StringUtils.isNotBlank(userDirct)){
                        if(userDirct.indexOf(",") != -1){
                            //log.info("????????????????????????" + getUser);
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
                    //????????????????????????
                    try {
                        //List<OdsDepartmetClear> deptRecordList = prcessDeptmentList(areaRecordList);
                        JSONObject amProcessResult = this.getManagersByRoleAndArea(lastRegionId, channalNm, true);
                        List<String> managerList = null;
                        if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                            managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                        }
                        createGroupByUserList(channalNm,userList, managerList, lastRegionName, areaRecordList,number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
                    } catch (Exception e) {
                        log.error("???" + lastRegionName + "?????????????????? {}", e.toString());
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
                                //log.info("????????????????????????" + getUser);
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
                                //log.info("????????????????????????" + getUser);
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
        //????????????????????????
        if (0 < areaRecordList.size()) {
            try {
                //List<OdsDepartmetClear> deptRecordList = prcessDeptmentList(areaRecordList);
                JSONObject amProcessResult = this.getManagersByRoleAndArea(lastRegionId, channalNm, true);
                List<String> managerList = null;
                if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                    managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                }
                createGroupByUserList(channalNm,userList, managerList, lastRegionName, areaRecordList,number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
            } catch (Exception e) {
                log.error("???" + lastRegionName + "?????????????????? {}", e.toString());
            }
        }
    }

    /**
     * ????????????????????????
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
            //??????????????????
            this.groupInfoService.save(groupInfo);
        }
    }


    /**
     * ????????????????????????
     * @param dccud
     */
    /**
     * ????????????????????????
     * @param dccud
     * @param channalNm
     */
    private void getLastAreaGroupList(List<OdsHrDirct> dccud, String channalNm) {
        if (dccud == null || dccud.size() <= 0) {
            log.info("???????????????????????????????????????");
            return;
        }
        String lastArea = ""; //????????????????????????
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
            //?????????????????????
            if (StringUtils.isBlank(lastArea)) {
                lastArea = currArea;
                responsUser = updRecord.getOaccount();
                if(!userList.contains(responsUser)){
                    userList.add(responsUser);
                    userDirct = updRecord.getOrgDirct();
                    if (StringUtils.isNotBlank(userDirct)){
                        if(userDirct.indexOf(",") != -1){
                            //log.info("????????????????????????" + getUser);
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
                    //????????????????????????
                    try {
                        JSONObject amProcessResult = this.getManagersByRoleAndArea(lastArea, channalNm, false);
                        List<String> managerList = null;
                        if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                            managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                        }
                        createGroupByUserList(
                                channalNm,userList, managerList, lastArea, areaRecordList, number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
                    } catch (Exception e) {
                        log.error("???" + lastArea + "?????????????????? {}", e.toString());
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
                                //log.info("????????????????????????" + getUser);
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
                                //log.info("????????????????????????" + getUser);
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
        //????????????????????????
        if (0 < areaRecordList.size()) {
            try {
                //List<OdsDepartmetClear> deptRecordList = prcessDeptmentList(areaRecordList);
                JSONObject amProcessResult = this.getManagersByRoleAndArea(lastArea, channalNm, false);
                List<String> managerList = null;
                if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                    managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                }
                createGroupByUserList(channalNm,userList, managerList, lastArea, areaRecordList,number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
            } catch (Exception e) {
                log.error("???" + lastArea + "?????????????????? {}", e.toString());
            }
        }
    }

    /**
     * ????????????list  ??????deptTotal deptNumber
     * @param areaRecordList
     * @return
     */
    private List<OdsDepartmetClear> prcessDeptmentList(List<OdsHrDirct> areaRecordList) {
        String lasDept = ""; //????????????????????????
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
     * ???????????????OA??????????????????????????????
     *
     * @return
     * @author qhl 2021-07-14
     */
    public List<OdsHrDirct> searchLeaderByOaAccct() {
        List<OdsHrDirct> odsHrDirct = odsHrDirctService.searchUserByOaAccct();
        return odsHrDirct;
    }


    /**
     * ??????????????????
     */
    public void createGroupByUserList(
            String channalNm, List<String> userList, List<String> managerList, String areaName, List<OdsHrDirct> areaRecordList, int totalNum, BigDecimal totalAmount) {
        //????????????????????????????????????
        if (managerList != null) {
            for(String manager : managerList) {
                if (!userList.contains(manager)) {
                    userList.add(manager);
                }
            }
        }
        //?????????????????????
        List<OdsDepartmetClear> deptList = prcessDeptmentList(areaRecordList);
        if("1".equals(debugFlag)) {
            log.info("debug??????");
            log.info("????????????" + "??????/??????????????????????????????-???" + areaName + "?????????");
            log.info("??????????????????" + appendMsgContent(areaName, totalAmount, deptList));
            log.info("??????=" + areaName);
            log.info("????????????=" + totalNum);
            log.info("???????????????=" + totalAmount);

            log.info("??????????????????");
            String userStr="";
            for (String s : userList) {
                userStr += (s+",");
            }
            log.info(userStr);
            log.info("???????????????");
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
            groupInfo.setGroupName("??????/??????????????????????????????-???" + areaName + "?????????");
            groupInfo.setDeptNum(new BigDecimal(totalNum));
            groupInfo.setTotalAmt(totalAmount);
            groupInfo.setStatus("-1");
            //??????????????????
            this.groupInfoService.save(groupInfo);
        } else {
            log.info("??????????????????");
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

/*          userList.add("w_zhaobo14");
            userList.add("w_liuyingshuang");
            userList.add("guoyu5");
            userList.add("majianjing");
            userList.add("huangjiayi01");
            userList.add("maben01");*/

            userList.add("monitor_admin");
            userList.add("w_zhaobo14");
            CreateGroupDTO createGroupDTO = CreateGroupDTO.builder()
                    .groupName("??????/??????????????????????????????-???" + areaName + "?????????")
                    .members(userList)
                    .usercode("w_zhaobo14")
                    .build();
            Result createGroupResult = OpenApiClient.createGroup(createGroupDTO,
                    "https://api-pre.longhu.net/longchat-open-api-npre/createGroup",
                    "4dbb66cc-9dcd-4a94-9ffe-1a9f363f722a", null);
            log.info(JSONObject.toJSONString(createGroupResult));
            //?????????????????????????????????
            if ("0".equals(createGroupResult.getCode())) {
                //??????????????????????????????
                String content = appendMsgContent(areaName, totalAmount, deptList);
                String data = JSONObject.toJSONString(createGroupResult.getData());
                MsgData msgData = JSONObject.parseObject(data, MsgData.class);
                log.info("????????????groupID:{}" + msgData.getGroupId());
                List<String> list = new ArrayList<>();
                list.add(msgData.getGroupId());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    log.error("???????????????????????????" + e.toString());
                }
                sendMsg(list, content, areaName, channalNm);
                //????????????????????????????????????ID
                List<DwCpClearUpDetail> detailList = new ArrayList<DwCpClearUpDetail>();
                for (OdsHrDirct hrDirct : areaRecordList) {
                    DwCpClearUpDetail detail = new DwCpClearUpDetail();
                    detail.setId(hrDirct.getId());
                    detail.setReMark(msgData.getGroupId());
                    detailList.add(detail);
                }
                //???????????????ID
                iDwCpClearUpDetailService.updateBatchById(detailList);
                groupInfo.setGroupId(msgData.getGroupId());
                groupInfo.setArea(areaName);
                groupInfo.setChanal(channalNm);
                groupInfo.setGroupName("??????/??????????????????????????????-???" + areaName + "?????????");
                groupInfo.setDeptNum(new BigDecimal(totalNum));
                groupInfo.setTotalAmt(totalAmount);
                groupInfo.setStatus("1");
                //??????????????????
                this.groupInfoService.save(groupInfo);
            }else{
                log.error("?????????????????????code="+createGroupResult.getCode()+";msg="+createGroupResult.getMsg());
            }
        }
    }

    /*
      ??????API????????????
     */
    public void sendMsg(List<String> groupId,String content,String areaName,String channalName){
        //????????????????????????
        TxtMsgContent txtMsgContent = TxtMsgContent.builder()
                .content(content).build();
        //????????????????????????
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
            log.error("?????????" + areaName + ", ????????????????????????CODE");
            areaCode = "";
        }
        //??????API
        Result sendMessageResult = OpenApiClient.pushMessage(pushMessageDTO,
                longforUrl + "/pushMessage",
                longforKey, 20);
        //log.info(JSONObject.toJSONString(sendMessageResult));
        //log.info("?????????????????????????????????{}"+JSONObject.toJSONString(sendMessageResult));
        //???????????????????????? CHANAL   --????????????   AREA      --????????????
        ImgTextMsgContent imgTextMsgContent = ImgTextMsgContent.builder()
                .title("???????????????/?????????????????????")
                .content(content)
                .imageUri("http://frtest.longhu.net/webroot/decision?ticket=ST-545-HCH1p7dNFfp8oNjQU6MFxE-JK4cALI-PRD00108-APP-T1#directory")
                .url(pcUrl+"?CHANAL="+channalName+"&AREA="+areaCode)
                .build();
        //??????????????????????????????
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
        //??????API
        Result sendImgMsgResult = OpenApiClient.pushMessage(pushMessageDTOImg,
                longforUrl + "/pushMessage",
                longforKey, 20);
        //log.info(JSONObject.toJSONString(sendImgMsgResult));
        //log.info("?????????????????????????????????{}"+JSONObject.toJSONString(sendImgMsgResult));
    }


    /*
    ??????????????????
     */
    public String appendMsgContent(String areaName,BigDecimal totalAmount,List<OdsDepartmetClear> deptList){
        StringBuilder dept = new StringBuilder();
        for (OdsDepartmetClear o: deptList) {
            dept.append("???");
            dept.append(o.getDepartmentName());
            dept.append("???");
            dept.append(o.getDeptAmount().toString());
            dept.append("?????????");
            dept.append(o.getRecordNum());
            dept.append("??????\n");
        }
        return  quater+"???????????????/??????????????????????????????"+
                groupDate+"????????????????????????????????????"+sendDate+"??????"+areaName+"???????????????"+totalAmount+"??????????????????????????????????????????????????????\n" +
                dept.toString()+
                "?????????????????????????????????????????????????????????????????????????????????";
    }

    /**
     * ???????????????OA??????????????????????????????
     * @param areaName ??????
     * @param lineCode  ??????Code
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
                if ("??????".equals(lastName)) {
                    log.info("get area code for "+ shortName);
                    areaCode = areaMapping.get(shortName);
                }
            }
            if (StringUtils.isBlank(areaCode)) {
                log.error("getManagersByRoleAndArea(): {}", "????????????????????????????????? - " + areaName);
                return null;
            }
        }
        //request url
        String requestURL = hostAm + AM_API_GET_USERLIST;
        //????????????
        JSONObject result = new JSONObject();
        //????????????Json
        JSONObject inputParams = new JSONObject();
        //?????????????????? 0-????????????1-?????????
        inputParams.put("newDeptFlag", 1);
        //??????oa??????
        inputParams.put("recursive", true);
        List<String> orgList = new ArrayList<String>();
        orgList.add(areaCode);
        inputParams.put("orgCodes", orgList);
        //??????????????????????????????role Code List
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
        //??????????????????
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(inputParams, requestHeaders);
        ResponseEntity<String> response = template.exchange(requestURL, HttpMethod.POST, requestEntity, String.class);
        //log.info("getManagersByRoleAndArea() response???{}",response);
        JSONObject json = JSON.parseObject(response.getBody());
        //??????????????????
        if (json != null && StringUtils.isNotBlank(json.getString("respCode"))) {
            //
            JSONObject respCode = JSONObject.parseObject(json.getString("respCode"));
            if (respCode != null && StringUtils.isNotBlank(respCode.getString("code")) && "1".equals(respCode.getString("code"))) {
                List<Object> data = JSONArray.parseArray(json.getString("data"));
                for (Object userObj : data) {
                    JSONObject jsonObject = JSONObject.parseObject(userObj.toString());
                    String lineFromAm = jsonObject.getString("orgProductGroup");
                    if (StringUtils.isNotBlank(lineFromAm) && lineFromAm.equals(lineCode)) {
                        //C4???????????????????????????????????????????????????(orgType=06)
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
                result.put("resultMsg", "AM request ??????:" + respCode != null ? respCode.toJSONString(): "response code ??????");
            }
        } else {
            result.put("resultCode", "-1");
            result.put("resultMsg", "AM??????response??????");
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
            //log.info("????????????");
        } catch (Exception e) {
            log.error("????????????????????? {}", e.toString());
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

            //log.info("????????????????????????-??????????????? Start");
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
                    groupInfo.setGroupName("??????/??????????????????????????????-???" + area + "?????????");
                    //??????????????????
                    this.groupInfoService.save(groupInfo);
                }
            }*/
        } catch (Exception e) {
            log.error("????????????????????? {}", e.toString());
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
                    groupInfo.setGroupName("??????/??????????????????????????????-???" + area + "?????????");
                    //??????????????????
                    this.groupInfoService.save(groupInfo);
                }
            }
        } catch (Exception e) {
            log.error("????????????????????? {}", e.toString());
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
//??????PC/Mobile????????????URL
            QueryWrapper<DinDimValue> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("DIM_ID","1902");
            wrapper1.eq("USE_FLAG",1);
            DinDimValue tp = iDinDimValueService.getSSOToken(wrapper1);
            String pcUrl = tp.getDimValueName();
            log.info("????????????URL" + pcUrl);


            wrapper1 = new QueryWrapper<>();
            wrapper1.eq("DIM_ID","1905");
            wrapper1.eq("USE_FLAG",1);
            tp = iDinDimValueService.getSSOToken(wrapper1);
            String responseDate = tp.getDimValueName();
            log.info("????????????????????????" + responseDate);


            log.info("????????????????????????-??????????????? Start");

//        QueryWrapper<DwCpClearUpDetail> wrapper2 = new QueryWrapper<>();
//        if (StringUtils.isNotBlank(param) && "2".equals(param.trim())) {
//            wrapper2.eq("GX_FLAG","2");  //???????????????2??????????????????
//        } else {
//            wrapper2.eq("GX_FLAG","1"); //???????????????????????????????????????
//        }
//        wrapper2.orderByDesc("RESPON_USER");
//        List<DwCpClearUpDetail> dccud = iDwCpClearUpDetailService.getListBeanByWrapper(wrapper2);

            List<DwCpClearUpDetail> dccud1 = null;
            //???????????????????????????????????????????????????
            List<String> areaList1 = getAreaListByFlag(1);
            //?????????0????????????in???????????????????????????
            if(0 < areaList1.size()) {
                QueryWrapper<DwCpClearUpDetail> wrapper = new QueryWrapper<>();
                wrapper.eq("GX_FLAG", "1");
                wrapper.in("AREA_NAME",areaList1);
                wrapper.orderByDesc("RESPON_USER");
                dccud1 = iDwCpClearUpDetailService.getListBeanByWrapper(wrapper);
                if (dccud1 == null) {
                    dccud1 = new ArrayList<DwCpClearUpDetail>();
                }
                log.info("?????????????????????????????????????????????" + dccud1.size());
                //???????????????????????????
                try {
                    this.processSendMsg(dccud1, pcUrl, responseDate);
                } catch (Exception e) {
                    log.error("????????????????????????????????????????????????{}", e.toString());
                }
            }

            //???????????????????????????
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
                log.info("?????????????????????????????????????????????" + dccud2.size());
                //???????????????????????????
                try {
                    this.processSendMsg(dccud2, pcUrl, responseDate);
                } catch (Exception e) {
                    log.error("????????????????????????????????????????????????{}", e.toString());
                }
            }
        } catch (Exception e) {
            log.error("????????????????????? {}", e.toString());
            result.put("code", "-1");
        }
        result.put("code", "1");
        return result;
    }


    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping("/sendDataMsg")
    @ResponseBody
    public JSONObject sendDataMsg(String flag) {
        JSONObject result = new JSONObject();
        if(getFlag()){
            log.info("??????????????????");
            result.put("code", "1");
            return result;
        }else{
            log.info("?????????????????????");
            result.put("code", "0");
            return result;
        }
    }

    /**
     * ????????????????????????
     * @param dccud
     */
    public void processSendMsg (List<DwCpClearUpDetail> dccud, String pcUrl, String responseDate) {
        String lastOA = ""; //????????????????????????
        String lastArea = ""; //????????????????????????
        int number = 0;  //??????????????????
        BigDecimal total = new BigDecimal("0");
        log.info("????????????????????????????????????");

        List<DwCpClearUpDetail> updList = new ArrayList<DwCpClearUpDetail>();
        int batchSize = 0;
        DwCpClearUpDetail updRecord = null;
        QueryWrapper<DinDimValue> wrapper = null;
        DinDimValue tp = null;
        for(int i = 0; i < dccud.size(); i++) {
            updRecord = dccud.get(i);
            String nowOA = "";  //?????????????????????
            String getUser = updRecord.getResponUser();
            BigDecimal amount = updRecord.getAmount();
            if (amount == null) {
                amount = new BigDecimal(0);
            }
            //???????????????????????????????????????
            if(org.apache.commons.lang3.StringUtils.isBlank(getUser)){
                continue;
            }
            //???????????????????????????????????????????????????????????????
            if(getUser.indexOf(",") != -1){
                //log.info("????????????????????????" + getUser);
                nowOA = getUser.split(",")[0];
            } else {
                nowOA = getUser;
            }
            //log.info("???????????????" + nowOA);
            //?????????????????????
            if (org.apache.commons.lang3.StringUtils.isBlank(lastOA)) {
                lastOA = nowOA;
                lastArea = updRecord.getAreaName();
                number++;
                total = total.add(amount.abs());
                //log.info("total = " + total);
            } else {
                if(!lastOA.equals(nowOA)){
                    BigDecimal divide = new BigDecimal("10000");
                    //?????????????????????????????????
                    String  responseUser = "taonaihui";
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(lastArea)) {
                        wrapper = new QueryWrapper<>();
                        wrapper.eq("DIM_ID","1904");
                        wrapper.eq("USE_FLAG",1);
                        wrapper.eq("DIM_VALUE_ID", lastArea.trim());
                        tp = iDinDimValueService.getSSOToken(wrapper);
                        String dimVal = tp.getDimValueName();
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(dimVal)) {
                            responseUser = dimVal;
                        }
                    }
                    try {
                        sendMessage(lastOA, number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP), pcUrl, responseUser, responseDate);
                    } catch (Exception e) {
                        log.error("??????????????????????????? OA=" + lastOA + ", responseUser=" + responseUser);
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
            //??????????????????
            batchSize++;
            updRecord.setGxFlag("2");
            updList.add(updRecord);
            if (batchSize/200 == 0) {
                try {
                    iDwCpClearUpDetailService.updateBatchById(updList, updList.size());
                } catch (Exception e) {
                    log.error("??????????????????????????????????????????" + e.toString());
                }

                updList.clear();
            }
            //log.info("total = " + total);
        }
        //????????????????????????

        BigDecimal divide = new BigDecimal("10000");
        //?????????????????????????????????
        String responseUser = "taonaihui";
        if (org.apache.commons.lang3.StringUtils.isNotBlank(lastArea)) {
            wrapper = new QueryWrapper<>();
            wrapper.eq("DIM_ID","1904");
            wrapper.eq("USE_FLAG",1);
            wrapper.eq("DIM_VALUE_ID", lastArea.trim());
            tp = iDinDimValueService.getSSOToken(wrapper);
            String dimVal = tp.getDimValueName();
            if (org.apache.commons.lang3.StringUtils.isNotBlank(dimVal)) {
                responseUser = dimVal;
            }
        }
        try {
            sendMessage(lastOA, number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP),  pcUrl, responseUser, responseDate);
        } catch (Exception e) {
            log.error("??????????????????????????? OA=" + lastOA + ", responseUser=" + responseUser);
        }

        if (0 < updList.size()) {
            try {
                iDwCpClearUpDetailService.updateBatchById(updList, updList.size());
            } catch (Exception e) {
                log.error("??????????????????????????????????????????" + e.toString());
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

    public void sendPcMsg(String nowOA,int number,BigDecimal total, String pcUrl, String responseDate) {
        log.info("??????PC????????????");
        List<String> members = new ArrayList<>();
        //members.add("w_liuyingshuang");
        members.add("w_zhaobo14");
        members.add(nowOA);
        List<String> str =  new ArrayList<String>();
        //ImageContent
        ImgTextMsgContent itmc = ImgTextMsgContent.builder()
                .title("????????????????????????")
                .content("????????????" + number + "?????????/???????????????????????????????????????????????????????????????????????????/??????????????????????????????" + total.doubleValue() + "???????????????????????????????????????????????????" + responseDate + "????????????")
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
        log.info("PC????????????Request:" + pushMessageDTO.toString());
        log.info("PC????????????Response:" + JSONObject.toJSONString(sendMessageResult));
    }


    public void sendTextMsg(String nowOA,int number,BigDecimal total, String responseUser, String responseDate) {
        log.info("??????Text??????");
        List<String> members = new ArrayList<>();
        members.add(nowOA);
        //members.add("w_liuyingshuang");
        members.add("w_zhaobo14");
        TxtMsgContent txtMsgContent = TxtMsgContent.builder()
                .content("????????????" + number + "?????????/???????????????????????????????????????????????????????????????????????????/??????????????????????????????" + total.doubleValue()
                        + "???????????????????????????????????????????????????" + responseDate + "??????????????????????????????\n" + "???????????????????????????" + responseUser + "???").build();

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
        log.info("Text??????Request:" + pushMessageDTO.toString());
        log.info("Text??????Response:" + JSONObject.toJSONString(sendMessageResult));
    }

    /**
     * ??????????????????gx_flag??????????????????????????????????????????????????????DwCpClearUpDetail??????????????? in???'??????1','??????2'??? gx_flag = 1?????????????????????gx_flag = 2?????????????????????
     * ?????????????????????
     * @return
     */
    public List<String> getAreaListByFlag(int gx_flag){
        //????????????????????????
        List<String> areaList = new ArrayList<>();
        //???????????????????????????
        List<String> paramsList = new ArrayList<>();
        if (gx_flag == 1) {
            paramsList.add("????????????");
        } else if (gx_flag == 2) {
            paramsList.add("??????????????????");
        } else {
            return areaList;
        }

        //??????????????????
        DwCpMonthQuarter dwCpMonthQuarter = iDwCpMonthQuarterService.getMinDataDate();
        if (dwCpMonthQuarter == null || org.apache.commons.lang.StringUtils.isBlank(dwCpMonthQuarter.getDataDate())) {
            return areaList;
        }
        String minDataDate = dwCpMonthQuarter.getDataDate();
        log.info("????????????:" + minDataDate);
        //???????????????????????????
        //String todayStr = StringUtil.getToday();
        String todayStr = "2021-06-13";
        log.info("????????????:" + todayStr);
        //??????????????????????????????
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
                        //?????????????????????????????????????????????
                        if (todayStr.equals(concatDay)) {
                            areaList.add(dimRecord.getDimValue());
                            break;
                        }
                    }
                }
            }
        }
        log.info("????????????:" + areaList);
        return areaList;
    }

    /**
     * ??????????????????????????????????????????????????????????????????????????????????????????????????????1??????????????????????????????????????????????????????????????????????????????????????????????????????
     * @return
     */
    private List<String> getAreaList(int groupIndex){
        //????????????????????????
        List<String> areaList = new ArrayList<>();
        //???????????????????????????
        List<String> paramsList = new ArrayList<>();
        if (groupIndex == 1) {
            paramsList.add("????????????");
        } else if (groupIndex == 2) {
            paramsList.add("??????????????????");
        } else {
            return null;
        }
        //??????????????????????????????
        DwCpMonthQuarter dwCpMonthQuarter =
                iDwCpMonthQuarterService.getMinDataDate();
        if (dwCpMonthQuarter == null || StringUtils.isBlank(dwCpMonthQuarter.getDataDate())) {
            return null;
        }
        String minDataDate = dwCpMonthQuarter.getDataDate();
        log.info("????????????:" + minDataDate);
        //???????????????????????????
//        String todayStr = StringUtil.getToday();
        String todayStr = "2021-06-13";
        log.info("????????????:" + todayStr);

        //?????????????????????????????????????????????????????????????????????????????????????????????break??????????????????
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
                        //?????????????????????????????????????????????
                        if (todayStr.equals(concatDay)) {
                            areaList.add(dimRecord.getDimValue());
                            break;
                        }
                    }
                }
            }
        }
        log.info("????????????:" + areaList);
        return areaList;
    }

    /**
     * ?????????????????????????????????????????????
     * @return
     */
    public boolean getFlag(){
        //???????????????????????????
        List<String> paramsList = new ArrayList<>();
        paramsList.add("????????????");

        //??????????????????
        DwCpMonthQuarter dwCpMonthQuarter = iDwCpMonthQuarterService.getMinDataDate();
        if (dwCpMonthQuarter == null || org.apache.commons.lang.StringUtils.isBlank(dwCpMonthQuarter.getDataDate())) {
            return false;
        }
        String minDataDate = dwCpMonthQuarter.getDataDate();
        //???????????????????????????
        //String todayStr = StringUtil.getToday();
        String todayStr = "2021-06-21";

        //?????????????????????????????????????????????
        List<DinDim> dinDimList = iDinDimService.getDinDimList(paramsList);
        if(dinDimList != null) {
            for (DinDim dimRecord : dinDimList) {
                List<DinDimValue> dinDimValueList = dimRecord.getDinDimValueList();
                if (dinDimValueList == null || dinDimValueList.size() <= 0) {
                    continue;
                } else {
                    String concatDay = "";
                    String dimValueName = dinDimValueList.get(0).getDimValueName();
                    if (Integer.parseInt(dimValueName) < 10) {
                        concatDay = minDataDate + "-0" + dimValueName;
                    } else {
                        concatDay = minDataDate + "-" + dimValueName;
                    }
                    //?????????????????????????????????????????????
                    if (todayStr.equals(concatDay)) {
                        return true;
                    }
                }
            }
        }
        return  false;
    }

    public static void main(String[] args) {
        List<String> areaList = new ArrayList<>();
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");
        areaList.add("????????????");



        for(String areaFull : areaList) {
            String areaCode = "";
            String shortName = "";
            String lastName = "";
            if (StringUtils.isNotBlank((areaFull))) {
                shortName = areaFull.substring(0,2);
                lastName = areaFull.substring(areaFull.length() - 2);
                if ("??????".equals(lastName)) {
                    areaCode = areaMapping.get(shortName);
                }
            }
            if (StringUtils.isBlank(areaCode)) {
                System.err.println("????????????????????????????????? - " + areaFull);
            } else {
                System.out.println("fullName=" + areaFull + ", shortName=" + shortName + "areaCode=" + areaCode);
            }
        }

    }
}
