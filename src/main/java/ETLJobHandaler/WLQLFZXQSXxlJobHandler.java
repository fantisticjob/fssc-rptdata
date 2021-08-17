package ETLJobHandaler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.longfor.fsscreportdb.utils.CommandLineUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

import groovy.util.logging.Slf4j;

/**
 * 往来清理辅助项取数
 * @author chenziyao
 * @date 2021/5/20 
*/
@JobHandler(value = "WLQLFZXQSXxlJobHandler")
@Component
@Slf4j
public class WLQLFZXQSXxlJobHandler extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	
    	try {
			Date date = new Date();
			log.info("往来清理辅助项取数开始：{}",date);
			CommandLineUtils.exeCmd("java -jar WLQLFZXQS.jar ");

			date = new Date();
			log.info("往来清理辅助项取数结束：{}",date);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        
        return SUCCESS;
    }
}
