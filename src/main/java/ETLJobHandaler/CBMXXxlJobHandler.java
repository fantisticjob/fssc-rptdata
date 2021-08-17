package ETLJobHandaler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.ods.service.IOdsCtCostDetailService;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.longfor.fsscreportdb.utils.StringUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 成本明细定时任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "CBMXXxlJobHandler")
@Component
@Slf4j
public class CBMXXxlJobHandler extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private final String KETTEL_JOB_LUJING = "/CT/J_CT_ANALYSE.kjb";
	private final String DEAL_KAISHI = "成本明细表处理开始";
	
	
	@Value("${kettel.host2}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private IOdsCtCostDetailService odsCtCostDetailService;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	
    	Map<String,Object> map = new HashMap<String, Object>();
    	
    	log.info(DEAL_KAISHI);
    	
    	// 成本明细初期化判断
    	int count = commonDao.selectCount("select count(*) from dw_ct_project_payment");
    	
    	if(StringUtils.isNotBlank(param)) {
    	
	    	String[] yearMonth = param.split(",");
	    	String years = yearMonth[0];
	    	String months = yearMonth[1];
	    	int intMonth = Integer.parseInt(months);
	    	
	    	if("2020".equals(years) || "2021".equals(years)) {
	    		
	    		 for(int i=0; i<intMonth; i++) {
	    			String month = "";
	    			if(i+1<10) {
	    				month ="0"+(i+1);
	    			} else {
	    				month = String.valueOf(i+1);
	    			}
	    			
	    			map.put("year", years);
	    			map.put("month", month);
	    			
	    			log.info("2020 year{}" , years);
	    			log.info("2020 month{}" , month);
	    			
	    			// 1 成本明细表
	    	        XxlJobLogger.log("{}",DEAL_KAISHI);
	    	        JSONObject result = odsCtCostDetailService.saveList(map);
	    	        XxlJobLogger.log("成本明细同步处理结束{}", String.valueOf(result.get("status")));
	    	        
	    	        // 3 ETL调启  
	    	        KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
	    	        kettleUtilRemote.callRemote(
	    	        		ketelHost1, 
	    	        		ketelPort,  ketelJobPath + KETTEL_JOB_LUJING);
	    		}
	    		
	    	}  
   		
    	}  else if(count<=0) { 
    		
    		// 初次-数据初期话 
    		List<String> monthList = StringUtil.getBeforeMonth();
    		String year = StringUtil.getYesYear();
    		
    		for(int i=0; i<monthList.size(); i++) {
    			String month = monthList.get(i);
    			
    			map.put("year", year);
    			map.put("month", month);
    			
    			// 1 成本明细表
    	        XxlJobLogger.log("{}", DEAL_KAISHI);
    	        JSONObject result = odsCtCostDetailService.saveList(map);
    	        XxlJobLogger.log("成本明细同步处理结束{}", String.valueOf(result.get("status")));
    	        
    	        // 3 ETL调启  
    	        KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
    	        kettleUtilRemote.callRemote(
    	        		ketelHost1, 
    	        		ketelPort,  ketelJobPath + KETTEL_JOB_LUJING);
    		}
    		
    		
    	} else {
 
    		// 处理成本台账
    		// 设定日期取得============
    		String setei
    		     = commonDao.select(
    				" select t.dim_value_name  from din_dim_value t where t.dim_id = '1401' and t.dim_value_id = '1401001' ");
    		log.info("设定日期取得===={}" , setei);
    		
    		// 当月执行回数
    		String selectCount = "select count(*) from  dw_etl_task where module_name = '成本台账' "
    				+ " and to_char(end_date, 'yyyy-MM') = to_char(sysdate, 'yyyy-MM') ";
    		Integer times = commonDao.selectCount(selectCount);
    		
    		log.info("当月执行回数===={}" , selectCount);
    		
    		if(StringUtil.getDay().equals(setei) && times<=0) { 
    			
    			log.info("成本处理");
    			
	    		String year = StringUtil.getYesYear();
	    		String month = StringUtil.getYesMonth();
	    		
	    		map.put("year", year);
				map.put("month", month);
	
		        // 2 成本明细表
		        XxlJobLogger.log("{}",  DEAL_KAISHI);
		        JSONObject result = odsCtCostDetailService.saveList(map);
		        XxlJobLogger.log("资金自查账户交易表同步处理结束{}", String.valueOf(result.get("status")));
		        
		        // 3 ETL调启  
		        KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
		        kettleUtilRemote.callRemote(
		        		ketelHost1, 
		        		ketelPort,  ketelJobPath + KETTEL_JOB_LUJING);
    		} else {
    			log.info("成本明细不处理");
    		}
    	}
        
    	log.info("成本明细处理完了");   
        
        return SUCCESS;
    }
}
