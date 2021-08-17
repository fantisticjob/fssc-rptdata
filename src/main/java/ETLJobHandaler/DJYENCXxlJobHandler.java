package ETLJobHandaler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.longfor.fsscreportdb.Thread.entity.NcThread;
import com.longfor.fsscreportdb.Thread.entity.OdsNcNcthread;
import com.longfor.fsscreportdb.Thread.service.IOdsNcNcthreadService;
import com.longfor.fsscreportdb.utils.NCUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

import groovy.util.logging.Slf4j;
import lh.fr.App;

/**
 * 单家科目余额-取得
 * @author zhaoxin  param：ORGCODE, year, Month
 * @date 2021/02/24 
*/
@JobHandler(value = "DJYENCXxlJobHandler")
@Component
@Slf4j
public class DJYENCXxlJobHandler extends IJobHandler {
	
	private  final static   Logger log = LoggerFactory.getLogger(NcThread.class);

	@Value("${kettel.host1}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	@Autowired
	private IOdsNcNcthreadService OdsNcNcthreadService;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	
        	log.info("单家科目余额-取得========KS===========");
        	Date begin = new Date();
        	
        	String input[] = param.split(",");
        	if(input.length != 3) {
        		log.error("单家科目余额-取得=入口参数不对=");
        		log.error("param：" + param);
        	} else {
        		log.info("param：" + param);
        	}
        	
            Date startTime = new Date();
    		log.info("NcThread1线程开始时间"+startTime);
    		OdsNcNcthread ncthread = new OdsNcNcthread();
    		ncthread.setStarttime(startTime);
    		BigDecimal saveGetId = OdsNcNcthreadService.saveGetId(ncthread);
    		
    		StringBuilder sb = new StringBuilder();
    		sb.append(" select * from ODS_ACCOUNTING_ORG_AREA where orgcode = '"+ input[0] + "' ");
    		
    		List<String> endYearList = new ArrayList<String>();
    		List<String> endMonthList = new ArrayList<String>();
    		endYearList.add(input[1]);
    		endMonthList.add(input[2]);
    		
    		log.info("单家科目余额 thread1=========KS===========");
        	NcThread thread1 
    		= (NcThread)App.configurableApplicationContext.getBean("NcThread");
    		thread1.setKemuCodeListBalance(NCUtils.initKemuCodeListBalance());
    		thread1.setKemuCodeListBalanceDetail(NCUtils.initKemuCodeListBalanceDetail());
    		thread1.setKemuCodeListDetail(NCUtils.initKemuCodeListDetail());
    		thread1.setKemuFuzhu(NCUtils.initKemuFuzhu());
    		thread1.setEndYearList(endYearList);
    		thread1.setEndMonthList(endMonthList);
    		thread1.setSql(sb.toString());
    		thread1.setTid(saveGetId);
    		thread1.setCountNcThread("1");
    		thread1.run();
    		log.info("单家科目余额 thread1=========JS===========");
    		
    		Date end  = new Date();
    		log.info("往来清理===单家-余额===coast=========" + ((end.getTime() - begin.getTime())/1000) + "S");
    		
    	
        return SUCCESS;
    }
       
}
