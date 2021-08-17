package ETLJobHandaler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.ods.service.IOdsSkjGetskjService;
import com.longfor.fsscreportdb.ods.service.IOdsSkjSkjinfoService;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 收款机明细定时任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "SKJXxlJobHandler")
@Component
@Slf4j
public class SKJXxlJobHandler extends IJobHandler {
	
	@Value("${kettel.host2}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	
	@Autowired
	private IOdsSkjSkjinfoService IOdsSkjSkjinfoService;
	@Autowired
	private IOdsSkjGetskjService iOdsSkjGetskjService;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	
    	JSONObject result=null;
    	
    	
    	// 1 收款机接口
    	XxlJobLogger.log("{}","收款机接口处理开始");
    	result = iOdsSkjGetskjService.saveList();
    	XxlJobLogger.log("收款机接口同步处理结束{}", String.valueOf(result.get("status")));
    	
		// 1 收款机明细
        XxlJobLogger.log("{}","收款机明细处理开始");
        result = IOdsSkjSkjinfoService.saveList();
        XxlJobLogger.log("收款机明细同步处理结束{}", String.valueOf(result.get("status")));
        
   
        // 3 ETL调启  
        KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
        kettleUtilRemote.callRemote(
        		ketelHost1, 
        		ketelPort,  ketelJobPath + "/PS/J_PS_ANALYSE.kjb");
        
        return SUCCESS;
    }
}
