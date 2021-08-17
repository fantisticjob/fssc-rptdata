package ETLJobHandaler;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.longfor.fsscreportdb.oa.entity.DinDim;
import com.longfor.fsscreportdb.oa.entity.DinDimValue;
import com.longfor.fsscreportdb.oa.entity.DwCpMonthQuarter;
import com.longfor.fsscreportdb.oa.service.IDinDimService;
import com.longfor.fsscreportdb.oa.service.IDwCpMonthQuarterService;
import com.longfor.fsscreportdb.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.longfor.fsscreportdb.Thread.entity.NcThread;
import com.longfor.fsscreportdb.Thread.entity.OdsNcNcthread;
import com.longfor.fsscreportdb.Thread.service.IOdsNcNcthreadService;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.utils.CommandLineUtils;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.longfor.fsscreportdb.utils.NCUtils;
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
@JobHandler(value = "NCXxlJobHandler")
@Component
@Slf4j
public class NCXxlJobHandler extends IJobHandler {

	private static final    Logger log = LoggerFactory.getLogger(NCXxlJobHandler.class);

	@Value("${kettel.host1}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	@Autowired
	private CommonDao dao;
	
	@Autowired
	private IOdsNcNcthreadService odsNcNcthreadService;

	@Autowired
	private IDwCpMonthQuarterService iDwCpMonthQuarterService;

	@Autowired
	private IDinDimService iDinDimService;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        // 初期化
        if("0".equals(param)) {
        	log.info("初期化=====================");
        	List<String> endYearList = new ArrayList<>();
        	endYearList.add("2021");
        	List<String> endMonthList = new ArrayList<>();
        	endMonthList.add("03");

        	log.info("初期化=====================");
        	StringBuilder sb = new StringBuilder();
            sb.append(" select count(*) from ODS_ACCOUNTING_ORG_AREA ");
            Integer count = dao.selectCount(sb.toString());
            
            Date startTime = new Date();
    		log.info("NcThread1线程开始时间:{}", startTime);
    		OdsNcNcthread ncthread = new OdsNcNcthread();
    		ncthread.setStarttime(startTime);
    		BigDecimal saveGetId = odsNcNcthreadService.saveGetId(ncthread);
    		
    		DecimalFormat dF = new DecimalFormat("0");
    		int parseInt = Integer.parseInt(dF.format((float)count/5));
    		
    		sb = new StringBuilder();
    		sb.append(" select * from ODS_ACCOUNTING_ORG_AREA where  ROWNUM<="+parseInt);
    		
        	NcThread thread1 
    		= (NcThread)App.configurableApplicationContext.getBean("NcThread");
    		thread1.setKemuCodeListBalance(NCUtils.initKemuCodeListBalance());
    		thread1.setKemuCodeListBalanceDetail(NCUtils.initKemuCodeListBalanceDetail());
    		thread1.setKemuCodeListDetail(NCUtils.initKemuCodeListDetail());
    		thread1.setKemuFuzhu(NCUtils.initKemuFuzhu());
    		thread1.setSql(sb.toString());
    		thread1.setTid(saveGetId);
    		thread1.setCountNcThread("1");
    		
    		thread1.setEndYearList(endYearList);
    		thread1.setEndMonthList(endMonthList);
    		
    		thread1.run();
    		log.info("初期化 thread1=========F===========");
    		
    		
    		sb = new StringBuilder();
    		sb.append(" select * from ODS_ACCOUNTING_ORG_AREA where  ROWNUM<="+parseInt*2);
    		sb.append(" MINUS select * from ODS_ACCOUNTING_ORG_AREA where  ROWNUM<="+parseInt);
            
    		NcThread thread2 
    		= (NcThread)App.configurableApplicationContext.getBean("NcThread");
    		thread2.setKemuCodeListBalance(NCUtils.initKemuCodeListBalance());
    		thread2.setKemuCodeListBalanceDetail(NCUtils.initKemuCodeListBalanceDetail());
    		thread2.setKemuCodeListDetail(NCUtils.initKemuCodeListDetail());
    		thread2.setKemuFuzhu(NCUtils.initKemuFuzhu());
    		thread2.setSql(sb.toString());
    		thread2.setTid(saveGetId);
    		thread2.setCountNcThread("2");
    		
    		thread2.setEndYearList(endYearList);
    		thread2.setEndMonthList(endMonthList); 
    		
    		thread2.run();
    		log.info("初期化 thread2=========F===========");
    		
    		sb = new StringBuilder();
    		sb.append(" select * from ODS_ACCOUNTING_ORG_AREA where  ROWNUM<="+parseInt*3);
    		sb.append(" MINUS select * from ODS_ACCOUNTING_ORG_AREA where  ROWNUM<="+parseInt*2);
    		
    		NcThread thread3
    		= (NcThread)App.configurableApplicationContext.getBean("NcThread");
    		thread3.setKemuCodeListBalance(NCUtils.initKemuCodeListBalance());
    		thread3.setKemuCodeListBalanceDetail(NCUtils.initKemuCodeListBalanceDetail());
    		thread3.setKemuCodeListDetail(NCUtils.initKemuCodeListDetail());
    		thread3.setKemuFuzhu(NCUtils.initKemuFuzhu());
    		thread3.setSql(sb.toString());
    		thread3.setTid(saveGetId);
    		thread3.setCountNcThread("3");
    		
    		thread3.setEndYearList(endYearList);
    		thread3.setEndMonthList(endMonthList); 
    		
    		thread3.run();
    		log.info("初期化 thread3=========F===========");
    		
    		sb = new StringBuilder();
    		sb.append(" select * from ODS_ACCOUNTING_ORG_AREA where  ROWNUM<="+parseInt*4);
    		sb.append(" MINUS select * from ODS_ACCOUNTING_ORG_AREA where  ROWNUM<="+parseInt*3);
    		
    		NcThread thread4
    		= (NcThread)App.configurableApplicationContext.getBean("NcThread");
    		thread4.setKemuCodeListBalance(NCUtils.initKemuCodeListBalance());
    		thread4.setKemuCodeListBalanceDetail(NCUtils.initKemuCodeListBalanceDetail());
    		thread4.setKemuCodeListDetail(NCUtils.initKemuCodeListDetail());
    		thread4.setKemuFuzhu(NCUtils.initKemuFuzhu());
    		thread4.setSql(sb.toString());
    		thread4.setTid(saveGetId);
    		thread4.setCountNcThread("4");
    		
    		thread4.setEndYearList(endYearList);
    		thread4.setEndMonthList(endMonthList); 
    		
    		thread4.run();
    		log.info("初期化 thread4=========F===========");
    		
    		
    		sb = new StringBuilder();
    		sb.append(" select * from ODS_ACCOUNTING_ORG_AREA where  ROWNUM<="+count);
    		sb.append(" MINUS select * from ODS_ACCOUNTING_ORG_AREA where  ROWNUM<="+parseInt*4);
    				
    		NcThread thread5
    		= (NcThread)App.configurableApplicationContext.getBean("NcThread");
    		thread5.setKemuCodeListBalance(NCUtils.initKemuCodeListBalance());
    		thread5.setKemuCodeListBalanceDetail(NCUtils.initKemuCodeListBalanceDetail());
    		thread5.setKemuCodeListDetail(NCUtils.initKemuCodeListDetail());
    		thread5.setKemuFuzhu(NCUtils.initKemuFuzhu());
    		thread5.setSql(sb.toString());
    		thread5.setTid(saveGetId);
    		thread5.setCountNcThread("5");
    		
    		thread5.setEndYearList(endYearList);
    		thread5.setEndMonthList(endMonthList); 
    		
    		thread5.run();
    		log.info("初期化 thread5=========F===========");
        	
        } else {
			if (getFlag()) {
				log.info("每个月8号取上个月的数据========F===========");
				Date begin = new Date();
				Date startTime = new Date();
				log.info("NcThread1线程开始时间:{}", startTime);
				OdsNcNcthread ncthread = new OdsNcNcthread();
				ncthread.setStarttime(startTime);
				BigDecimal saveGetId = odsNcNcthreadService.saveGetId(ncthread);

				StringBuilder sb = new StringBuilder();
				sb.append(" select * from ODS_ACCOUNTING_ORG_AREA ");

				log.info("初期化 thread1=========F===========");
				NcThread thread1
						= (NcThread)App.configurableApplicationContext.getBean("NcThread");
				thread1.setKemuCodeListBalance(NCUtils.initKemuCodeListBalance());
				thread1.setKemuCodeListBalanceDetail(NCUtils.initKemuCodeListBalanceDetail());
				thread1.setKemuCodeListDetail(NCUtils.initKemuCodeListDetail());
				thread1.setKemuFuzhu(NCUtils.initKemuFuzhu());
				thread1.setSql(sb.toString());
				thread1.setTid(saveGetId);
				thread1.setCountNcThread("1");
				thread1.run();
				log.info("初期化 thread1=========F===========");

				Date end = new Date();
				log.info("NC存储过程调用开始。");
				CommandLineUtils.exeCmd("java -jar WLQL_PL.jar");
				log.info("NC存储过程调用结束。");
				log.info("往来清理===单月-辅助余额===coast=========:{}S" ,  ((end.getTime() - begin.getTime())/1000));

				// 4 ETL调启  往来清理
				KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
				kettleUtilRemote.callRemote(
						ketelHost1,
						ketelPort,  ketelJobPath + "/CP/J_CP_ANALYSE.kjb");
			}
        }
        return SUCCESS;
    }

	/**
	 * 只要批量取数有符合的日期就执行
	 * @return
	 */
	public boolean getFlag(){
		//查询的参数条件集合
		List<String> paramsList = new ArrayList<>();
		paramsList.add("批量取数");

		//获取最小年月
		DwCpMonthQuarter dwCpMonthQuarter = iDwCpMonthQuarterService.getMinDataDate();
		if (dwCpMonthQuarter == null || org.apache.commons.lang.StringUtils.isBlank(dwCpMonthQuarter.getDataDate())) {
			return false;
		}
		String minDataDate = dwCpMonthQuarter.getDataDate();
		//获取当前日期字符串
		String todayStr = StringUtil.getToday();

		//获取满足条件的批量取数地区集合
		List<DinDim> dinDimList = iDinDimService.getDinDimList(paramsList);
		if(dinDimList != null) {
			for (DinDim dimRecord : dinDimList) {
				List<DinDimValue> dinDimValueList = dimRecord.getDinDimValueList();
				if (dinDimValueList == null || dinDimValueList.size() <= 0) {
					continue;
				} else {
					String concatDay = "";
					String dimValueName = dinDimValueList.get(0).getDimValueName();
					if (Integer.parseInt(dimValueName) < 10) {
						concatDay = minDataDate + "-0" + dimValueName;
					} else {
						concatDay = minDataDate + "-" + dimValueName;
					}
					//如果当前日期等于地区配置的时间
					if (todayStr.equals(concatDay)) {
						return true;
					}
				}
			}
		}
		return  false;
	}
}
