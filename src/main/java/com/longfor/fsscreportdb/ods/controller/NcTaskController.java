package com.longfor.fsscreportdb.ods.controller;

import java.util.Date;

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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.longfor.fsscreportdb.Thread.entity.NcThread3;
import com.longfor.fsscreportdb.ods.nc.entity.NcTask;
import com.longfor.fsscreportdb.ods.nc.service.INcTaskService;

import groovy.util.logging.Slf4j;
import lh.fr.App;

/**
 * <p>
 * NC_回调
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
public class NcTaskController {

	
    private final Logger log = LoggerFactory.getLogger(getClass());

	private static final  String STATUS="status";
	private static final  String RESULT="errorMessage";
    
	@Autowired
	private INcTaskService ncTaskService;
	
	@Value("${kettel.host2}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping("/ncCallback")
	@ResponseBody
	public JSONObject receiveData(String dealid, String abnormalFlag) {

		JSONObject json = new JSONObject();
		
		log.info("receiveData()dealid{}" , dealid);
		log.info("receiveData()abnormalFlag{}" , abnormalFlag);
		
		try {
	   		QueryWrapper<NcTask> wrapper = new  QueryWrapper<NcTask>();
	    	wrapper.eq("BATCHNUMBER", dealid);
	    	
	    	NcTask one = ncTaskService.getOne(wrapper);
	    	
			
	    	// 手动更新
	    	if(one !=null && "1".equals(one.getOpraterFlag())) {
		    	
		    	if(one !=null) {
		    		one.setEndtimeNc(new Date());
		    		ncTaskService.updateStoredProcedure(one.getQuarter() ,one.getAccountId(),one.getCompanyName());
					
					one.setEndtimeFr(new Date());
					one.setFinishStatus("1");
					UpdateWrapper<NcTask> updateWrapper = new UpdateWrapper<NcTask>();
					updateWrapper.eq("BATCHNUMBER", dealid);
					if(!ncTaskService.update(one,updateWrapper)) {
						log.error("nc手动回调保存校验表失败！");
					}
					json.put(STATUS, "200");
		    	}
				
	    	} else {
	    		
	    		// 自动更新
	    		if(dealid!=null) {
	    			
		    		String auxiliaryBalance = dealid+"_AuxiliaryBalance.csv";
		    		String auxiliaryBalanceDetails = dealid+"_AuxiliaryBalanceDetails.csv";
		    		NcThread3 thread
		    		= (NcThread3)App.configurableApplicationContext.getBean("NcThread3");
		    		thread.setAuxiliaryBalance(auxiliaryBalance);
		    		thread.setAuxiliaryBalanceDetails(auxiliaryBalanceDetails);
		    		thread.start();
		    	}
	    		
	    		// 自动处理NC结束时时间更新
	    		NcTask oneZD = ncTaskService.getOne(wrapper);
	    		oneZD.setEndtimeNc(new Date());
	    		oneZD.setFinishStatus("1");
	    		UpdateWrapper<NcTask> updateWrapper = new UpdateWrapper<NcTask>();
				updateWrapper.eq("BATCHNUMBER", dealid);
				ncTaskService.update(oneZD,updateWrapper);
	    		// ncTaskService.save(oneZD);
	    	}
			
			json.put(STATUS, 200);
			json.put(RESULT, "处理成功");
			return json;
			
		} catch (Exception e) {
			
			json.put(STATUS, "-2");
			log.error("nc手动回调保存校验表异常！",e.toString());
			return json;
		}
		
	}
	
}
