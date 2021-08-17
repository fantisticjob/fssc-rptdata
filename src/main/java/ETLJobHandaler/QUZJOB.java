package ETLJobHandaler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QUZJOB {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
    @Scheduled(cron = "0 0 1 * * ?") // 每天夜里1点执行 月度关账批量
    public void callMonthlyCloseALL() {
    	log.info("----每天夜里1点执行 月度关账批量    -- 开始---");
        //执行具体业务逻辑----------  
        
    	log.info("----每天夜里1点执行 月度关账批量    ---完成--");
    }
}
