package ETLJobHandaler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

import groovy.util.logging.Slf4j;

/**
 * 银行余额定时任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "YHYEXxlJobHandler")
@Component
@Slf4j
public class YHYEXxlJobHandler extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${kettel.host2}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	
    	log.info("银行余额====自动更新JOB开始====");
    	
        log.info("自动更新调度执行");
		KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
        kettleUtilRemote.callRemote(
        		ketelHost1, 
        		ketelPort,  ketelJobPath + "/BA/J_BA_UPDATA_ZD.kjb");
        
    	log.info("银行余额====自动更新JOB完了====");
        
        return SUCCESS;
    }
}
