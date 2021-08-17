package ETLJobHandaler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longfor.fsscreportdb.ods.service.IOdsCrCreditPayOverdueService;
import com.longfor.fsscreportdb.ods.service.IOdsCrCreditPayService2;
import com.longfor.fsscreportdb.ods.service.IOdsPtGxusersService;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 信用超期定时任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "XYCQXxlJobHandler")
@Component
@Slf4j
public class XYCQXxlJobHandler extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${kettel.host1}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	private static final String STATUS = "status";
	private static final String DEAL_START = "信用超期明细表处理开始";
	
	@Autowired
	private IOdsCrCreditPayOverdueService odsCrCreditPayOverdueService;
	
	@Autowired
	private IOdsCrCreditPayService2 odsCrCreditPayService2;
	
	@Autowired
	private IOdsPtGxusersService   odsPtGxusersService;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        
    	log.info("信用超期明细表处理开始");

    	if(StringUtils.isNotBlank(param)) { 
    		
    		// 20210201,20210301
    		String[] params = param.split(",");
    		// 4 信用超期付款表2 
        	XxlJobLogger.log("{}",DEAL_START); 
        	JSONObject result = odsCrCreditPayService2.saveList(params[0], params[1]);
        	XxlJobLogger.log("信用超期明细表处理结束{}", String.valueOf(result.get(STATUS)));
    	} 

		// 4 信用超期明细表 
		XxlJobLogger.log("{}","信用超期明细表处理开始"); 
		JSONObject result = odsCrCreditPayOverdueService.saveList();
		XxlJobLogger.log("信用超期明细表处理结束{}", String.valueOf(result.get(STATUS)));
		 
	    // 共享人员
	    XxlJobLogger.log("{}","共享人员处理开始");
	    result = odsPtGxusersService.saveList();
	    XxlJobLogger.log("共享人员同步处理结束{}", String.valueOf(result.get(STATUS)));

        
        // 4 ETL调启  
        KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
        kettleUtilRemote.callRemote(
        		ketelHost1, 
        		ketelPort,  ketelJobPath + "/CR/J_CR_ANALYSE.kjb");
        
        log.info("信用超期明细表处理完了");
        return SUCCESS;
    }
}
