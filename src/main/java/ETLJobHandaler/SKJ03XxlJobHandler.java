package ETLJobHandaler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.ods.service.IOdsSkjGetskjService2;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

import groovy.util.logging.Slf4j;

/**
 * 收款机明细定时任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "SKJ03XxlJobHandler")
@Component
@Slf4j
public class SKJ03XxlJobHandler extends IJobHandler {
	
	@Value("${kettel.host2}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IOdsSkjGetskjService2 iOdsSkjGetskjService;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	
    	JSONObject result=null;
    	
    	// 1 收款机接口
    	log.info("{}","收款机03接口处理开始");
    	result = iOdsSkjGetskjService.saveList();
    	log.info("收款机接口03同步处理结束{}", String.valueOf(result.get("status")));
   
        // 3 ETL调启  
        KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
        kettleUtilRemote.callRemote(
        		ketelHost1, 
        		ketelPort,  ketelJobPath + "/PS/J_PS_ANALYSE.kjb");
        
        log.info("收款机接口03ETL处理结束{}", String.valueOf(result.get("status")));
        
        return SUCCESS;
    }
}
