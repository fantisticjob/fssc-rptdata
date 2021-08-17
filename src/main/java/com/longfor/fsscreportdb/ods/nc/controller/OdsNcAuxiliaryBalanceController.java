package com.longfor.fsscreportdb.ods.nc.controller;


import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.longfor.fsscreportdb.ods.nc.entity.NcTask;
import com.longfor.fsscreportdb.ods.nc.service.INcTaskService;

/**
 * <p>
 * 往来清理-往来明细类 前端控制器
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/ncc")
public class OdsNcAuxiliaryBalanceController  {
	
	private  final   Logger log = LoggerFactory.getLogger(OdsNcAuxiliaryBalanceController.class);

	private static final  String STATUS="status";
	private static final  String RESULT="result";
	
	@Autowired
	private INcTaskService ncTaskService;
	
	/**
	 * nc回调
	 */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "automatic")
    @ResponseBody
	public void automatic(String batchnumber) {
		
    	
    	
    	
	}
    
	
	/**
	 * nc手工回调
	 */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "manual")
    @ResponseBody
	public JSONObject batch(String batchnumber) {
		JSONObject json = new JSONObject();
    	
		if(StringUtils.isBlank(batchnumber)) {
			json.put(STATUS, "-1");
			json.put(RESULT, "参数不合法");
			return json;
		}
		
		
		try {
	    	QueryWrapper<NcTask> wrapper = new  QueryWrapper<NcTask>();
	    	wrapper.eq("BATCHNUMBER", batchnumber);
	    	NcTask one = ncTaskService.getOne(wrapper);
	    	
	    	if(one !=null) {
	    		one.setEndtimeNc(new Date());
	    		ncTaskService.updateStoredProcedure(one.getQuarter() ,one.getAccountId(),one.getCompanyName());
				
				one.setEndtimeFr(new Date());
				one.setFinishStatus("1");
				UpdateWrapper<NcTask> updateWrapper = new UpdateWrapper<NcTask>();
				updateWrapper.eq("BATCHNUMBER", batchnumber);
				if(!ncTaskService.update(one,updateWrapper)) {
					log.error("nc手动回调保存校验表失败！");
				}
				json.put(STATUS, "200");
	    	}
	    	
	    	
			
		} catch (Exception e) {
			json.put(STATUS, "-2");
			log.error("nc手动回调保存校验表异常！");
			e.printStackTrace();
		}
    	
    	return json;
	}
}
