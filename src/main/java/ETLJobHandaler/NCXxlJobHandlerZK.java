package ETLJobHandaler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longfor.fsscreportdb.Thread.entity.NcThread;
import com.longfor.fsscreportdb.Thread.entity.NcThread2;
import com.longfor.fsscreportdb.Thread.entity.OdsNcNcthread;
import com.longfor.fsscreportdb.Thread.service.IOdsNcNcthreadService;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.longfor.fsscreportdb.utils.NCUtils;
import com.longfor.fsscreportdb.utils.StringUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

import groovy.util.logging.Slf4j;
import lh.fr.App;

/**
 * 实时nc定时任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "NCXxlJobHandlerZK")
@Component
@Slf4j
public class NCXxlJobHandlerZK extends IJobHandler {
	
	
	private static final    Logger log = LoggerFactory.getLogger(NcThread.class);

	@Value("${kettel.host1}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	@Autowired
	private CommonDao dao;
	
	@Autowired
	private IOdsNcNcthreadService OdsNcNcthreadService;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	
    	
    	StringBuilder sb = new StringBuilder();
        sb.append("delete from ODS_NC_BALANCE_DETAILS_BD ");//删除NC外系统查询辅助余额明细
        dao.delete(sb.toString());
        
        sb = new StringBuilder();
        sb.append("delete from ODS_NC_AUXILIARY_BALANCE_BD ");//删除NC外系统查询辅助余额表
        dao.delete(sb.toString());
        
        sb = new StringBuilder();
        sb.append("delete from ODS_NC_BALANCE_VO_BD ");//删除NC余额表
        dao.delete(sb.toString());
        
            
        
        
        Date startTime = new Date();
		log.info("NcThread1线程开始时间"+startTime);
		OdsNcNcthread ncthread = new OdsNcNcthread();
		ncthread.setStarttime(startTime);
		BigDecimal saveGetId = OdsNcNcthreadService.saveGetId(ncthread);
		
		sb = new StringBuilder();
		// sb.append(" select * from ODS_ACCOUNTING_ORG_AREA where accountingbookcode = '14047-0001' ");
		sb.append(" select * from ODS_ACCOUNTING_ORG_AREA ");
		
		NcThread2 thread1 
		= (NcThread2)App.configurableApplicationContext.getBean("NcThread2");
    	
    	// 银行直扣
    	List<String> kemuyueList = new ArrayList<String>();
    	kemuyueList.add("11519998"); 
    	
    	Map<String, String> beforeThere 
    		= StringUtil.getBeforeThree();
    	String beginYear = beforeThere.get("beginYear");
        String beginMonth = beforeThere.get("beginMonth");
        String endYear = beforeThere.get("endYear");
        String endMonth = beforeThere.get("endMonth");
        
        // 银行直扣 12月份取得逻辑
        if(StringUtils.isNotBlank(param)
        		&& param.split(",").length == 4) {
        	
        	beginYear = param.split(",")[0];
        	beginMonth = param.split(",")[1];
        	endYear =  param.split(",")[2];
        	endMonth =  param.split(",")[3];
        }
        
        System.out.println("beginYear" + beginYear);
        System.out.println("beginMonth" + beginMonth);
        System.out.println("endYear" + endYear);
        System.out.println("endMonth" + endMonth);
    	
		thread1.setKemuCodeListBalance(kemuyueList);
		thread1.setKemuCodeListBalanceDetail(kemuyueList);
		thread1.setKemuCodeListDetail(kemuyueList);
		thread1.setKemuFuzhu(NCUtils.initKemuFuzhu());
		thread1.setSql(sb.toString());
		thread1.setTid(saveGetId);
		thread1.setCountNcThread("1");
		
		//=======================zhao========
		thread1.setIsHaveYearMonth("1");
		thread1.setBeginYear(beginYear);
		thread1.setEndYear(endYear);
		thread1.setBeginMonth(beginMonth);
		thread1.setEndMonth(endMonth);
		//========================xin=========
		
		// change by zhaoxin begin=========
		// thread1.start();
		thread1.run();
		// change by zhaoxin end=========
		
        // 4 ETL调启  
		KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
        kettleUtilRemote.callRemote(
        		ketelHost1, 
        		ketelPort,   ketelJobPath + "/BD/J_BD_ANALYSE.kjb");
        
        return SUCCESS;
    }
       
}
