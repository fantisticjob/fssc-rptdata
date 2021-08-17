package com.longfor.fsscreportdb.ods.controller;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.reconciliation.entity.RprtTask;
import com.longfor.fsscreportdb.reconciliation.entity.RprtTaskYg;
import com.longfor.fsscreportdb.reconciliation.service.IRprtTaskService;
import com.longfor.fsscreportdb.reconciliation.service.IRprtTaskYgService;
import com.longfor.fsscreportdb.reconciliation.service.impl.BankMonthlyTask;

import groovy.util.logging.Slf4j;
import lh.fr.App;

/**
 * <p>
 * 对账明细表 前端控制器
 * </p>
 *
 * @author chenziyao
 * @param <E>
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/notic")
@Component
@Slf4j
public class RprtTaskController {
	
    private final Logger log = LoggerFactory.getLogger(getClass());
	private static final  String STATUS="status";
	private static final  String RESULT="result";
	private static final  String MESSAGE="errorMessage";
    
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private IRprtTaskService irs;
	
	@Autowired
	private IRprtTaskYgService irsyg;
	
	
	@Value("${kettel.host2}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping("/bankBalance")
	@ResponseBody
	public JSONObject receiveData(String dealid, String opraterFlag, String abnormalFlag, 
			String HZCOUNT, 
			String DETAILCOUNT ) {

		JSONObject json = new JSONObject();
		
		log.info("receiveData()dealid{}" , dealid);
		log.info("receiveData()opraterFlag{}" , opraterFlag);
		
		if(dealid == null || opraterFlag== null) {
			json.put(STATUS,"-1");
			json.put(RESULT,"参数不合法");
			return json;
		}
		log.info("receiveData()dealid{}" , dealid);
		log.info("receiveData()opraterFlag{}" , opraterFlag);
		log.info("receiveData()abnormalFlag{}" , abnormalFlag);
	
		
		
		RprtTask rprtTask = new RprtTask();
    	UpdateWrapper<RprtTask> wrapper = new UpdateWrapper<RprtTask>();
    	
		switch(opraterFlag){
		    case "ZDK" ://自动开始
		    	
		    	log.info("receiveData()自动开始{}" , dealid);
		    	
		    	rprtTask.setDealid(dealid);
		    	rprtTask.setBegintime(new Date());
		    	rprtTask.setOpraterFlag("1");
		    	rprtTask.setFinishStatus("0");
		    	
		    	if(irs.save(rprtTask)) {
		    		json.put(RESULT,"ZDK处理成功");
		    		json.put(STATUS,200);
		    	} else {
		    		json.put(STATUS,-1);
		    		json.put(RESULT,"ZDK处理失败");
		    		return json;
		    	}
		    	
		    	return json;
		        
		    case "SDF" : //手动结束
		    	
		    	
		    	StringBuilder sb = new StringBuilder();
		    	sb.append(" select count(*) from RPRT_TASK where DEALID = '" + dealid + "'");
		    	int rrr = commonDao.selectCount(sb.toString());
		    	log.info("rrr==={}" , rrr);
		    	
		    	log.info("手动结束==={}" , dealid);
		    	
		    	rprtTask.setEndtimeIra(new Date());
		    	wrapper.eq("DEALID",  dealid);
		    	
		    	// add by zhaoxin===========2021.02.02==========begin
		    	if(StringUtils.isNotBlank(HZCOUNT) 
		    			&& StringUtils.isNotBlank(DETAILCOUNT)) {
		    		
		    		log.info("input HZCOUNT:{}" , HZCOUNT);
		    		log.info("input DETAILCOUNT:{}" , DETAILCOUNT);
		    		
					String select = "select * from RPRT_TASK where DEALID='" + dealid + "'";
					Map<String,Object> rtp_taskMap = 
							commonDao.selectMap(select);
					String accountId = (String)rtp_taskMap.get("ACCOUNT_ID");
					String month = (String)rtp_taskMap.get("YEARMONTH");
					
					// NC_BANKACCNUM   RPRT_BANKDEPOSIT_HZ
					StringBuilder sb02 = new StringBuilder();
					sb02.append("select count(*) from RPRT_BANKDEPOSIT_HZ where SINGLEFLAG = '1'  and NC_BANKACCNUM = '");
					sb02.append(accountId);
					sb02.append("' and DATA_DATE = '");
					sb02.append(month);
					sb02.append("'");
					int countHZ = commonDao.selectCount(sb02.toString());
					
					sb02 = new StringBuilder();
					sb02.append("select count(*) from RPRT_BANKDEPOSIT_DETAILS where DATA_DATE ='" + month + "' and( ACCNUM ='" + accountId +"' OR ZJ_ACCNUM= '"+ accountId +"' )   and SINGLEFLAG = '1'");
					int countDetail = commonDao.selectCount(sb02.toString());
					
					log.info("output HZCOUNT:{}" , countHZ);
		    		log.info("output DETAILCOUNT:{}" , countDetail);
		    	}
		    	// add by zhaoxin===========2021.02.02==========end

		    	
		    	if(irs.update(rprtTask, wrapper)) {
		    		log.info("手动结束数据更新OK===={}" , dealid);
		    		json.put(STATUS, 200);
					json.put(RESULT, "SDF处理成功");
		    	} else {
		    		log.info("手动结束数据更新NG===={}" , dealid);
		    		json.put(STATUS,-1);
		    		json.put(RESULT,"SDF处理失败");
		    		return json;
		    	}
		    	
		    	try {
		    		Thread.sleep(2500);
		    		log.info("手动结束回掉程序SLeep2500");
		    	} catch (Exception e) {
		    		log.error("手动结束回掉程序SLeep2500{}" , e.toString());
		    	}
		    	
		    	// add by zhaoxin 异常结束处理  abnormalFlag 异常标识 若无异常返回“0”，单家异常返回“1”
		    	if("1".equals(abnormalFlag)) {
		    		
					RprtTask rprtTaskT = new RprtTask();
			    	UpdateWrapper<RprtTask> wrapperT = new UpdateWrapper<RprtTask>();
			    	wrapperT.eq("DEALID", dealid);
			    	rprtTaskT.setEndtimeFr(new Date());
			    	rprtTaskT.setFinishStatus("3"); // 异常结束
			    	irs.update(rprtTaskT, wrapperT);
			    	
			    	
		    	} else {
		    	
			    	BankMonthlyTask bankMonthlyTask 
			    		= (BankMonthlyTask)App.configurableApplicationContext.getBean("BankMonthlyTask");
			    	bankMonthlyTask.setDealType("3");
			    	bankMonthlyTask.setDealid(dealid);
			    	bankMonthlyTask.start();
		    	}
				
				log.info("手动结束处理成功{}" , dealid);
				
				return json;
		        
		    case "ZDF" : // 自动结束
		    	
		    	log.info("自动结束{}" , dealid);
		    	
				rprtTask.setEndtimeIra(new Date());
		    	wrapper.eq("DEALID", dealid);
		    	if(irs.update(rprtTask, wrapper)) {
		    		json.put(STATUS,200);
		    		json.put(RESULT,"ZDF处理成功");
		    	} else {
		    		json.put(STATUS,-1);
		    		json.put(RESULT,"ZDF处理失败");
		    		return json;
		    	}
		    	
		    	try {
		    		Thread.sleep(2500);
		    		log.info("自动结束回掉程序SLeep2500");
		    	} catch (Exception e) {
		    		log.error("自动结束回掉程序SLeep2500{}" , e.toString());
		    	}
		    	
		    	BankMonthlyTask bankMonthlyTask02 
	    		= (BankMonthlyTask)App.configurableApplicationContext.getBean("BankMonthlyTask");
		    	bankMonthlyTask02.setDealType("1");
		    	bankMonthlyTask02.setDealid(dealid);
		    	bankMonthlyTask02.start();
	    	
		    	return json;
		}
		json.put(STATUS, -1);
		json.put("errorMessage", "opraterFlag参数不对");
		return json;
		
	}
	
	
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping("/monthlyClose")
	@ResponseBody
	public JSONObject receiveDataYG(String dealid,String opraterFlag, String abnormalFlag) {

		JSONObject json = new JSONObject();
		
		log.info("monthlyClose dealid{}" , dealid);
		log.info("monthlyClose opraterFlag{}" , opraterFlag);
		log.info("monthlyClose abnormalFlag{}" , abnormalFlag);
		
		if(dealid == null || opraterFlag== null) {
			json.put(STATUS,"-1");
			json.put(RESULT,"参数不合法");
			return json;
		}
		
		log.info("monthlyClose dealid{}" , dealid);
		log.info("monthlyClose opraterFlag{}" , opraterFlag);
		
		
		RprtTaskYg rprtTaskYg = new RprtTaskYg();
    	UpdateWrapper<RprtTaskYg> wrapper = new UpdateWrapper<RprtTaskYg>();
    	
		switch(opraterFlag){
		    case "ZDK" ://自动开始
		    	
		    	rprtTaskYg.setDealid(dealid);
		    	rprtTaskYg.setBegintime(new Date());
		    	rprtTaskYg.setOpraterFlag("1");
		    	rprtTaskYg.setFinishStatus("0");
		    	
		    	if(irsyg.save(rprtTaskYg)) {
		    		json.put(RESULT,"ZDK处理成功");
		    		json.put(STATUS,200);
		    	} else {
		    		json.put(RESULT,"ZDK处理失败");
		    		json.put(STATUS, -1);
		    	}
		    	return json;
		        
		    case "SDF" : //手动结束
		    	
		    	rprtTaskYg.setEndtimeIra(new Date());
		    	wrapper.eq("DEALID", dealid);
		    	
		    	if( irsyg.update(rprtTaskYg, wrapper)) {
		    		json.put(STATUS, 200);
					json.put(RESULT, "SDF处理成功");
					
		    	} else {
		    		json.put(STATUS, -1);
					json.put(RESULT, "SDF处理失败");
					return json;
		    	}
		    	
		    	// add by zhaoxin 异常结束处理  abnormalFlag 异常标识 若无异常返回“0”，单家异常返回“1”
		    	if("1".equals(abnormalFlag)) {
		    		
		    		rprtTaskYg.setEndtimeFr(new Date());
		    		rprtTaskYg.setFinishStatus("3"); // 异常结束
			    	irsyg.update(rprtTaskYg, wrapper);
			    	
		    	} else {
		    		
		    		BankMonthlyTask bankMonthlyTask 
		    		= (BankMonthlyTask)App.configurableApplicationContext.getBean("BankMonthlyTask");
			    	bankMonthlyTask.setDealType("4");
			    	bankMonthlyTask.setDealid(dealid);
			    	bankMonthlyTask.start();
		    	}
	    	
				return json;
		        
		    case "ZDF" : // 自动结束
		    	
		    	rprtTaskYg.setEndtimeIra(new Date());
		    	wrapper.eq("DEALID", dealid);
		    	
		    	if( irsyg.update(rprtTaskYg, wrapper)) {
		    		json.put(STATUS,200);
		    		json.put(RESULT,"ZDF处理成功");
		    	} else {
		    		json.put(STATUS, -1);
					json.put(RESULT, "ZDF处理失败");
					return json;
		    	}
		    	
		    	BankMonthlyTask bankMonthlyTask02 
	    		= (BankMonthlyTask)App.configurableApplicationContext.getBean("BankMonthlyTask");
		    	bankMonthlyTask02.setDealType("2");
		    	bankMonthlyTask02.setDealid(dealid);
		    	bankMonthlyTask02.start();
		    	
		    	return json;
		}
		json.put(STATUS, -1);
		json.put(MESSAGE, "opraterFlag参数不对");
		return json;
		
	}
	
}
