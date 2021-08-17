package ETLJobHandaler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.ods.service.IOdsZjzcbankbalancesByDateService;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 资金自查定时任务      删除资金自查账户余额表-指定日期更新    param : yyyy-MM-DD 例子：2020-09-30
 * @author zhaoxin
 * @date 2020/12/20 
*/
@JobHandler(value = "ZJZCXxlJobHandler_ACBA")
@Component
@Slf4j
public class ZJZCXxlJobHandler_ACBA extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${kettel.host1}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	


	@Autowired
	private IOdsZjzcbankbalancesByDateService odsZjzcbankbalancesByDateService;
	
	
	@Autowired
	private CommonDao dao;

	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	JSONObject result=null;


    	if(StringUtils.isNotBlank(param)) {
    		
	    	log.info("ODS_ZJZC_ACCOUNT_BALANCE 删除资金自查账户表余额");
	    	StringBuilder sb = new StringBuilder();
	    	sb.append("delete from ODS_ZJZC_ACCOUNT_BALANCE where REPORTDATE='"+param+"'");                    
	    	Integer delete = dao.delete(sb.toString());
	    	log.info("ODS_ZJZC_ACCOUNT_BALANCE 删除资金自查账户表余额结束,条数为："+delete);
	    	
	    	// 2 资金自查账户表余额
			log.info("{}","资金自查账户表余额");
	        XxlJobLogger.log("{}","资金自查账户表余额同步处理开始");
	        result = odsZjzcbankbalancesByDateService.saveList(param);
	        XxlJobLogger.log("资金自查账户表余额同步处理结束{}", String.valueOf(result.get("status")));
	    	
	        
	        // 4 ETL调启  TODO 资金自查ETL做成
	        log.info("{}","BEFORE 资金自查ETL做成");
	        KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
	        kettleUtilRemote.callRemote( ketelHost1,  ketelPort,  ketelJobPath + "/ZJZC/J_ZJZC_ANALYSE.kjb");
	        
	        log.info("资金自查处理完了");
    	}
        
        return SUCCESS;
    }
}
