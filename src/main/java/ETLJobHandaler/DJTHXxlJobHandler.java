package ETLJobHandaler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.ods.service.IOdsDaDocumentAnalysisService;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 单据退回定时任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "DJTHXxlJobHandler")
@Component
@Slf4j
public class DJTHXxlJobHandler extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${kettel.host1}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;

	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	@Autowired
	private IOdsDaDocumentAnalysisService odsDaDocumentAnalysisService;
	
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        
    	
    	log.info("单据退回处理开始");
    	
    	// 年月输入 12月份数据取得
    	if(StringUtils.isNotBlank(param) 
    			&& param.split(",").length == 2) {
    		
	        // 1 单据退回分析明细表
	        XxlJobLogger.log("{}","单据退回分析明细表处理开始(年月)");
	        JSONObject result = odsDaDocumentAnalysisService.saveList(
	        		param.split(",")[0],
	        		param.split(",")[1]);
	        XxlJobLogger.log("单据退回分析明细表处理结束{}(年月)", String.valueOf(result.get("status")));
    		
    	} else {
    	
	        // 1 单据退回分析明细表
	        XxlJobLogger.log("{}","单据退回分析明细表处理开始");
	        JSONObject result = odsDaDocumentAnalysisService.saveList();
	        XxlJobLogger.log("单据退回分析明细表处理结束{}", String.valueOf(result.get("status")));
        
    	}
        

        // 2 ETL调启  
        KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
        kettleUtilRemote.callRemote(
        		ketelHost1, 
        		ketelPort,   ketelJobPath + "/DA/J_DA_ANALYSE.kjb");
        
        log.info("单据退回处理完成");
        
        return SUCCESS;
    }
}
