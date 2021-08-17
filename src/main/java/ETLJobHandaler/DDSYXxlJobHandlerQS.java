package ETLJobHandaler;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.ods.service.IOdsZdNcglsubjectbalanceService;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
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
@JobHandler(value = "DDSYXxlJobHandlerQS")
@Component
@Slf4j
public class DDSYXxlJobHandlerQS extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${kettel.host1}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	
	@Autowired
	private IOdsZdNcglsubjectbalanceService odsZdNcglsubjectbalanceService;

	@Autowired
	private CommonDao dao;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	JSONObject result=null;
    	
    	log.info("重点税源处理开始");
    	
    	log.info("删除处理1开始");
    	StringBuilder sb = new StringBuilder();
    	sb.append("delete from ODS_ZD_NCGLSUBJECTBALANCE t where t.yearv='2020'  and t.periodv!='12' ");
    	dao.delete(sb.toString());
    	
        // 12 NC科目数据
        XxlJobLogger.log("{}","NC科目数据接口处理开始");
        
        ArrayList<String> list = new ArrayList<>();
        list.add("10");
        list.add("11");
		for (int i = 0; i < list.size(); i++) {
			// 去年同期
	        result = odsZdNcglsubjectbalanceService.saveList("2020", list.get(i));
	        XxlJobLogger.log("NC科目数据同步处理第"+i+"遍结束{}", String.valueOf(result.get("status")));
	        result = odsZdNcglsubjectbalanceService.saveList(null, null);
	        XxlJobLogger.log("NC科目数据同步处理第"+i+"遍结束{}", String.valueOf(result.get("status")));
		}	
        
        // 4 ETL调启  
        KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
        kettleUtilRemote.callRemote(
        		ketelHost1, 
        		ketelPort,  ketelJobPath + "/ZDSY/J_ZDSY_ANALYSE.kjb");
        
        log.info("重点税源处理完了");
        
        return SUCCESS;
    }
}
