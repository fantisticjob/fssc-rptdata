package ETLJobHandaler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.ods.service.IOdsCtCostDetailHistoryService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 成本明细历史数据取得
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "CBMXHISXxlJobHandler")
@Component
@Slf4j
public class CBMXHISXxlJobHandler extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IOdsCtCostDetailHistoryService odsCtCostDetailHistoryService;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	
    	Map<String,Object> map = new HashMap<String, Object>();
    	
    	log.info("信成本明细历史处理开始");

		if(param!=null) {
			
			String[] split = param.split(",");
			
			map.put("year", split[0]);
			map.put("month", split[1]);
			// 2 成本明细表
	        XxlJobLogger.log("{}","成本明细历史表处理开始");
	        JSONObject result = odsCtCostDetailHistoryService.saveList(map);
	        XxlJobLogger.log("成本明细历史表处理结束{}", String.valueOf(result.get("status")));
	        
		}
    		
    	log.info("成本明细历史处理完了");   
        
        return SUCCESS;
    }
}
