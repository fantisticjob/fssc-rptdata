package ETLJobHandaler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.ods.service.IOdsZjzcAccountsAppliesService;
import com.longfor.fsscreportdb.ods.service.IOdsZjzcAccountsExtendService;
import com.longfor.fsscreportdb.ods.service.IOdsZjzcaccountsService;
import com.longfor.fsscreportdb.ods.service.IOdsZjzcbankbalancesService;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 资金自查定时任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "ZJZCXxlJobHandler")
@Component
@Slf4j
public class ZJZCXxlJobHandler extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${kettel.host1}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	@Autowired
	private IOdsZjzcaccountsService odsZjzcaccountsService;

	@Autowired
	private IOdsZjzcbankbalancesService odsZjzcbankbalancesService;
	
	@Autowired
	private IOdsZjzcAccountsAppliesService odsZjzcAccountsAppliesService;
	
	@Autowired
	private IOdsZjzcAccountsExtendService odsZjzcAccountsExtendService;
	
	@Autowired
	private CommonDao dao;

	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	JSONObject result=null;
    	

    	log.info("ODS_ZJZC_ACCOUNT 删除处理1开始");
    	StringBuilder sb = new StringBuilder();
    	sb.append("delete from ODS_ZJZC_ACCOUNT");                    
    	dao.delete(sb.toString());
    	log.info("ODS_ZJZC_ACCOUNT 删除处理1结束");
    	
    	log.info("ODS_ZJZC_ACCOUNT_BALANCE 删除处理2开始");
    	sb = new StringBuilder();
    	sb.append("delete from ODS_ZJZC_ACCOUNT_BALANCE");                    
    	dao.delete(sb.toString());
    	log.info("ODS_ZJZC_ACCOUNT_BALANCE 删除处理2结束");
    	
    	
    	log.info("ODS_ZJZC_ACCOUNTS_APPLIES 删除处理3开始");
    	sb = new StringBuilder();
    	sb.append("delete from ODS_ZJZC_ACCOUNTS_APPLIES");                    
    	dao.delete(sb.toString());
    	log.info("ODS_ZJZC_ACCOUNTS_APPLIES 删除处理3结束");
    	
    	
    	log.info("ODS_ZJZC_ACCOUNTS_EXTEND 删除处理4开始");
    	sb = new StringBuilder();
    	sb.append("delete from ODS_ZJZC_ACCOUNTS_EXTEND");                    
    	dao.delete(sb.toString());
    	log.info("ODS_ZJZC_ACCOUNTS_EXTEND 删除处理4结束");
    	
    	
    	// 1 资金自查账户表
		log.info("{}","资金自查账户表处理开始");
		XxlJobLogger.log("{}","资金自查账户表同步处理开始"); 
		result = odsZjzcaccountsService.saveList(); 
		XxlJobLogger.log("资金自查账户表同步处理结束{}", String.valueOf(result.get("status")));
    	
    	
    	// 2 资金自查账户表余额
		log.info("{}","资金自查账户表余额");
        XxlJobLogger.log("{}","资金自查账户表余额同步处理开始");
        result = odsZjzcbankbalancesService.saveList();
        XxlJobLogger.log("资金自查账户表余额同步处理结束{}", String.valueOf(result.get("status")));
    	
        
    	//  3资金自查账户申请表
        log.info("{}","资金自查账户申请表");
    	XxlJobLogger.log("{}","资金自查账户表同步处理开始"); 
    	result = odsZjzcAccountsAppliesService.saveList(); 
    	XxlJobLogger.log("资金自查账户表同步处理结束{}", String.valueOf(result.get("status")));
    	
    	// 4 资金自查账户拓展表
    	log.info("{}","资金自查账户拓展表同步处理开始");
		XxlJobLogger.log("{}","资金自查账户表同步处理开始"); 
		result = odsZjzcAccountsExtendService.saveList();
		XxlJobLogger.log("资金自查账户表同步处理结束{}", String.valueOf(result.get("status")));
		log.info("资金自查账户表同步处理结束{}",String.valueOf(result.get("status")));
		
		
        // 5 资金自查账户交易表
		//======================delete by zhaoxin===============2020-12-03=========begin==
//    	log.info("{}","资金自查账户交易表");
//        XxlJobLogger.log("{}","资金自查账户交易表处理开始");
//        result = odsZjzcbanktransactionsService.saveList();
//        XxlJobLogger.log("资金自查账户交易表同步处理结束{}", String.valueOf(result.get("status")));
        //======================delete by zhaoxin===============2020-12-03=========end==
        
        // 4 ETL调启  TODO 资金自查ETL做成
        log.info("{}","BEFORE 资金自查ETL做成");
        KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
        kettleUtilRemote.callRemote(
        		ketelHost1, 
        		ketelPort,  ketelJobPath + "/ZJZC/J_ZJZC_ANALYSE.kjb");
        
        log.info("资金自查处理完了");
        
        return SUCCESS;
    }
    
}
