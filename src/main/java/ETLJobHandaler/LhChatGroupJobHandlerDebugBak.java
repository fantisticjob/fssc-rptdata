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
            log.error("???????????????????????????:{}"+e.toString());
            return FAIL;
        }
        return SUCCESS;
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
        //???????????????????????????
        List<OdsHrDirct> odsHrDircts = odsHrDirctService.searchUserByOaAccct();

        log.info("????????????????????????????????????" + (odsHrDircts != null ? odsHrDircts.size() : "null"));
        //???????????????????????????
        if (odsHrDircts == null) {
            odsHrDircts = new ArrayList<OdsHrDirct>();
        }

        //?????????????????????list
        List<OdsHrDirct> updListC1 = new ArrayList<OdsHrDirct>();
        List<OdsHrDirct> updListC2 = new ArrayList<OdsHrDirct>();
        List<OdsHrDirct> updListC4 = new ArrayList<OdsHrDirct>();
        List<OdsHrDirct> updListC5 = new ArrayList<OdsHrDirct>();
        //???????????????list????????????
        for (OdsHrDirct os: odsHrDircts) {
/*            if (StringUtils.isNotBlank(os.getAreaName()) && "??????".equals(os.getAreaName())) {
                //??????????????????????????????
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
        //C1???C3?????????????????????25?????????????????????
        getLastAreaGroupList(updListC1,"C1");
        //C2,C4,C5 ????????????????????????
        getLastRegionGroupList(updListC2,"C2");
        getLastRegionGroupList(updListC4,"C4");
        getLastRegionGroupList(updListC5,"C5");
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
                        List<OdsDepartmetClear> deptRecordList = prcessDeptmentList(areaRecordList);
                        JSONObject amProcessResult = this.getManagersByRoleAndArea(lastRegionId, channalNm, true);
                        List<String> managerList = null;
                        if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                            managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                        }
                        createGroupByUserList(channalNm,userList, managerList, lastRegionName, deptRecordList,number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
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
                List<OdsDepartmetClear> deptRecordList = prcessDeptmentList(areaRecordList);
                JSONObject amProcessResult = this.getManagersByRoleAndArea(lastRegionId, channalNm, true);
                List<String> managerList = null;
                if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                    managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                }
                createGroupByUserList(channalNm,userList, managerList, lastRegionName, deptRecordList,number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
            } catch (Exception e) {
                log.error("???" + lastRegionName + "?????????????????? {}", e.toString());
            }
        }
    }

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
            } else {
                if (!lastArea.equals(currArea)) {
                    //????????????????????????
                    try {
                        List<OdsDepartmetClear> deptRecordList = prcessDeptmentList(areaRecordList);
                        JSONObject amProcessResult = this.getManagersByRoleAndArea(lastArea, channalNm, false);
                        List<String> managerList = null;
                        if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                            managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                        }
                        createGroupByUserList(channalNm,userList, managerList,lastArea, deptRecordList,number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
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
                List<OdsDepartmetClear> deptRecordList = prcessDeptmentList(areaRecordList);
                JSONObject amProcessResult = this.getManagersByRoleAndArea(lastArea, channalNm, false);
                List<String> managerList = null;
                if (amProcessResult != null && "0".equals(amProcessResult.getString("resultCode"))) {
                    managerList = JSONArray.parseArray(amProcessResult.getString("userList"), String.class);
                }
                createGroupByUserList(channalNm,userList, managerList, lastArea, deptRecordList,number, total.divide(divide).setScale(2,BigDecimal.ROUND_HALF_UP));
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
     * ??????????????????
     */
    public void createGroupByUserList(
            String channalNm, List<String> userList, List<String> managerList, String areaName, List<OdsDepartmetClear> deptList, int totalNum, BigDecimal totalAmount) {
        //????????????????????????????????????
        if (managerList != null) {
            for(String manager : managerList) {
                if (!userList.contains(manager)) {
                    userList.add(manager);
                }
            }
        }
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
            userList = new ArrayList<>();
            managerList = new ArrayList<>();


            List<String> testLongForGroupList = new ArrayList<String>();
            testLongForGroupList.add("monitor_admin");
            testLongForGroupList.add("w_liuyingshuang");
            testLongForGroupList.add("w_zhaobo14");
            CreateGroupDTO createGroupDTO = CreateGroupDTO.builder()
                    .groupName("??????/??????????????????????????????-???" + areaName + "?????????")
                    .members(testLongForGroupList)
                    .usercode("monitor_admin")
                    .build();

            Result createGroupResult = OpenApiClient.createGroup(createGroupDTO,
                    "https://api.longhu.net/longchat-open-api-prod/createGroup",
                    "c30aeb8b-b4e0-4217-91a1-63190589248d", null);
            log.info(JSONObject.toJSONString(createGroupResult));
            //?????????????????????????????????
            if ("0".equals(createGroupResult.getCode())) {
                //??????????????????????????????
                String content = appendMsgContent(areaName, totalAmount, deptList);

                content = content + "\n" + "??????????????????????????????????????????" + userStr + "\n" + "????????????????????????" + managerStr;

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
    public void sendMsg(List<String> groupId, String content,String areaName,String channalName){
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
                  .build();
         //??????API
          Result sendMessageResult = OpenApiClient.pushMessage(pushMessageDTO,
                  "https://api.longhu.net/longchat-open-api-prod/pushMessage",
                  "c30aeb8b-b4e0-4217-91a1-63190589248d", 20);
          //log.info(JSONObject.toJSONString(sendMessageResult));
         // log.info("?????????????????????????????????{}"+JSONObject.toJSONString(sendMessageResult));
          //???????????????????????? CHANAL   --????????????   AREA      --????????????
          ImgTextMsgContent imgTextMsgContent = ImgTextMsgContent.builder()
                .title("???????????????/?????????????????????")
                .content(content)
                .imageUri("http://frtest.longhu.net/webroot/decision?ticket=ST-545-HCH1p7dNFfp8oNjQU6MFxE-JK4cALI-PRD00108-APP-T1#directory")
                .url(pcUrl+"?CHANAL="+channalName+"&AREA="+areaName)
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
                "https://api.longhu.net/longchat-open-api-prod/pushMessage",
                "c30aeb8b-b4e0-4217-91a1-63190589248d", 20);
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
        return  quater + "???????????????/??????????????????????????????"+
                groupDate + "????????????????????????????????????"+sendDate+"??????"+areaName+"???????????????"+totalAmount+"??????????????????????????????????????????????????????\n" +
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
        String areaCode = "";
        List<String> managerList = new ArrayList<String>();
        if (isRegion) {
            areaCode = areaName;
        } else {
            if (StringUtils.isNotBlank((areaName))) {
                areaCode = areaMapping.get(areaName);
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
                //?????????????????????
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
                            //log.info("????????????????????????" + getUser);
                            String[] dirctUsr = whiteUsrStr.split(",");
                            for (String s : dirctUsr){
                                whiteList.add(s);
                            }
                        } else {
                            whiteList.add(whiteUsrStr);
                        }
                    }
                } catch (Exception e) {
                    log.error("??????????????????????????????{}", e.toString());
                }
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
                result.put("resultMsg", "AM request ??????:" + respCode != null ? respCode.toJSONString(): "response code ??????");
            }
        } else {
            result.put("resultCode", "-1");
            result.put("resultMsg", "AM??????response??????");
        }
        return result;
    }
}
