package ETLJobHandaler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.oa.entity.RoleUserRelation;
import com.longfor.fsscreportdb.oa.entity.RoleUserRights;
import com.longfor.fsscreportdb.oa.service.IRoleUserRelationService;
import com.longfor.fsscreportdb.oa.service.IRoleUserRightsService;
import com.longfor.fsscreportdb.utils.PushOAMessage;
import com.longfor.fsscreportdb.utils.StringUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

import groovy.util.logging.Slf4j;

/**
 * 工抵房OA待办定时任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "GDFOADBXxlJobHandler")
@Component
@Slf4j
public class GDFOADBXxlJobHandler extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IRoleUserRightsService roleService;
	
	@Autowired
	private IRoleUserRelationService roleUserRelationService;
	
	@Autowired
	private CommonDao dao;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	if(StringUtil.isSesionLock() || "OADB".equals(param)) {
    		//待办发送
    		//工抵房数据抽取完毕就开始推送oa消息
            List<RoleUserRights> list = roleService.selectList();//得到所有有权限用户
            ArrayList<RoleUserRelation> relationList = new ArrayList<RoleUserRelation>();
            
            String yearMonth = StringUtil.getYestMonth();
            StringBuilder sb = new StringBuilder();
            sb.append(" select area from dw_wr_overdue_analysis_lock t where datekey = '" + yearMonth + "'");
            sb.append(" and expiration_type!='未到期' and  rmbye <> amount group by area ");
            
            List<Map<String,Object>> areaList = dao.selectMaps(sb.toString());
            
            for (int i = 0; i < list.size(); i++) {
            	// log.info("i:=="+list.size());
            	// log.info("i:area:=="+list.get(i).getArea());
            	
            	String userArea = list.get(i).getArea();
            	boolean ishaveRight = false;
            	for(int j = 0; j < areaList.size(); j++) {
            		Map<String,Object> temp = areaList.get(j);
            		String area = (String)temp.get("AREA"); // 需要大写，否则报错
            		//判断是否有地区查看权限
            		if(area.equals(userArea)) {
            			ishaveRight =true;
            			break;
            		}
            	}
            	
            	if(!ishaveRight) {
            		continue;
            	}

            	RoleUserRights rights = list.get(i);
            	String uuid = StringUtil.getUUID();

            	String businessType ="FSSGXYY_GDFZCKB";
            	String title = "【待办】请填写数据截止为";
            	try {
					title = title + StringUtil.getLastDayOfLastMonth() + "的工抵房逾期分析明细表";
				} catch (Exception e) {
					log.error("获取标题中的截止时间失败! {}", e.toString());
					return FAIL;
				}
            	
            	String url ="http://fr.longhu.net/webroot/decision/view/report?viewlet=%E8%B4%A2%E5%8A%A1%E5%85%B1%E4%BA%AB%E5%B9%B3%E5%8F%B0/Work_is_worth_room/LH_overdue_list-bf.cpt&op=write";
                //推送待办消息
            	JSONObject json = JSONObject.parseObject(new String(PushOAMessage.doPostJson(rights.getAccount(),uuid,title,businessType, url)));
                
                String msg = json.getString("msg");
                String code= json.getString("code");
                
                if("success".equals(msg) && "0".equals(code)) {
                	RoleUserRelation relation = new RoleUserRelation();
                	relation.setUuid(uuid);
                	relation.setAccount(rights.getAccount());
                	relation.setArea(rights.getArea());
                	relation.setMonth(StringUtil.getYestMonth());
                	relationList.add(relation);
					//log.error("推送oa待办消息成功:"+ rights.getAccount());
                }else {
                	log.error("推送oa待办消息失败:"+ rights.getAccount());
                }
    		}
            boolean saveBatch = roleUserRelationService.saveBatch(relationList);
            if(saveBatch) {
            	log.info("保存oa待办消息成功！");
            }else {
            	log.error("保存oa待办消息失败!");
            }
    	}
        return SUCCESS;
    }
}
