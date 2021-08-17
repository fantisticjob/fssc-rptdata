package ETLJobHandaler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longfor.fsscreportdb.ods.service.IOdsCrCreditPayService2;
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
@JobHandler(value = "XYCQPULUXxlJobHandler")
@Component
@Slf4j
public class XYCQPULUXxlJobHandler extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	

	@Autowired
	private IOdsCrCreditPayService2 odsCrCreditPayService2;

	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        
    	log.info("信用超期明细表处理开始");

    	if(StringUtils.isNotBlank(param)) { 
    		
    		// 20210201,20210301
    		String[] params = param.split(",");
    		// 4 信用超期付款表2 
        	XxlJobLogger.log("{}","信用超期明细表处理开始"); 
        	JSONObject result = odsCrCreditPayService2.saveList(params[0], params[1]);
        	XxlJobLogger.log("信用超期明细表处理结束{}", String.valueOf(result.get("status")));
    	} 
        
        log.info("信用超期明细表处理完了");
        return SUCCESS;
    }
}
