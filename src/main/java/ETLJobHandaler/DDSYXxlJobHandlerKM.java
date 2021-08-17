package ETLJobHandaler;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.ods.service.IOdsZdNcglsubjectbalanceService;
import com.longfor.fsscreportdb.utils.StringUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 重点税源定时任务---2020-01-18
 * @author zhaoxin
 * @date 2020-01-18
*/
@JobHandler(value = "DDSYXxlJobHandlerKM")
@Component
@Slf4j
public class DDSYXxlJobHandlerKM extends IJobHandler {
	
	
	@Autowired
	private IOdsZdNcglsubjectbalanceService odsZdNcglsubjectbalanceService;
	
	@Autowired
	private CommonDao dao;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	JSONObject result=null;
    	
    	if(StringUtils.isNotBlank(param)) {
    		 
    		String[] split = param.split("-");
    		String sql="delete from ODS_ZD_NCGLSUBJECTBALANCE t "
    				+ "where t.yearv='"+split[0]+"' "
    				+ "and  t.periodv='"+split[1]+"'";
    		Integer delete = dao.delete(sql);
    		XxlJobLogger.log("删除条数为:{}",delete);
    		result = odsZdNcglsubjectbalanceService.saveList(split[0], split[1]);
    		
    	} else {

    		
	        // 12 NC科目数据
	        XxlJobLogger.log("{}","NC科目数据接口处理开始");
	        JSONObject date = StringUtil.getNewDate();
			String yesMonth = StringUtil.getYesMonth();
			String yearlast = String.valueOf(date.get("year"));
			yearlast = String.valueOf(Integer.parseInt(yearlast) - 1);
			
			// 去年同期
	        result = odsZdNcglsubjectbalanceService.saveList(yearlast, yesMonth);
	        result = odsZdNcglsubjectbalanceService.saveList(null, null);
        
    	}

        XxlJobLogger.log("NC科目数据同步处理结束{}", String.valueOf(result.get("status")));
        return SUCCESS;
    }
}
