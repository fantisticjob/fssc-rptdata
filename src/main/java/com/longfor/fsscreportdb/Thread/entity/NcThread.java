package com.longfor.fsscreportdb.Thread.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.longfor.fsscreportdb.Thread.service.IOdsNcNcthreadService;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcBalanceDetailService;
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcBalanceVoService;


@Component("NcThread")
@Scope("prototype")
public class NcThread extends Thread{
	
	private  final static   Logger log = LoggerFactory.getLogger(NcThread.class);

	@Autowired
	private CommonDao dao;
	
	
	@Autowired
	private IOdsNcNcthreadService OdsNcNcthreadService;
	
	@Autowired
	private IOdsNcBalanceVoService odsNcBalanceVoService;
	
	
	@Autowired
	private IOdsNcBalanceDetailService odsNcBalanceDetailService;
    
    public BigDecimal tid;
    
    private List<String> kemuCodeListBalance;
    
  	private List<String> kemuCodeListDetail;
  	
  	private String sql;
  	
  	private String countNcThread;
  	
  	private String isHaveYearMonth;
  	private String beginYear;
	private String endYear;
  	private String beginMonth;
  	private String endMonth;
  	
  	// add by zhaoxin======================2021-01-25=====begin==余额，余额明细
  	private List<String> endYearList;
  	private List<String> endMonthList;
  	// add by zhaoxin======================2021-01-25=====end==余额，余额明细
  	
  	
  	public List<String> getEndYearList() {
		return endYearList;
	}

	public void setEndYearList(List<String> endYearList) {
		this.endYearList = endYearList;
	}

	public List<String> getEndMonthList() {
		return endMonthList;
	}

	public void setEndMonthList(List<String> endMonthList) {
		this.endMonthList = endMonthList;
	}

	public String getBeginYear() {
		return beginYear;
	}

	public void setBeginYear(String beginYear) {
		this.beginYear = beginYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getBeginMonth() {
		return beginMonth;
	}

	public void setBeginMonth(String beginMonth) {
		this.beginMonth = beginMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	
  	public String getIsHaveYearMonth() {
		return isHaveYearMonth;
	}

	public void setIsHaveYearMonth(String isHaveYearMonth) {
		this.isHaveYearMonth = isHaveYearMonth;
	}

	// 科目CODE一览---科目余额明细
  	private List<String> kemuCodeListBalanceDetail;
  	
  	private Map<String, String> kemuFuzhu;
  	
	public BigDecimal getTid() {
		return tid;
	}

	public void setTid(BigDecimal tid) {
		this.tid = tid;
	}

	public String getCountNcThread() {
		return countNcThread;
	}

	public void setCountNcThread(String countNcThread) {
		this.countNcThread = countNcThread;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<String> getKemuCodeListBalance() {
		return kemuCodeListBalance;
	}

	public void setKemuCodeListBalance(List<String> kemuCodeListBalance) {
		this.kemuCodeListBalance = kemuCodeListBalance;
	}

	public List<String> getKemuCodeListDetail() {
		return kemuCodeListDetail;
	}

	public void setKemuCodeListDetail(List<String> kemuCodeListDetail) {
		this.kemuCodeListDetail = kemuCodeListDetail;
	}

	public List<String> getKemuCodeListBalanceDetail() {
		return kemuCodeListBalanceDetail;
	}

	public void setKemuCodeListBalanceDetail(List<String> kemuCodeListBalanceDetail) {
		this.kemuCodeListBalanceDetail = kemuCodeListBalanceDetail;
	}

	public Map<String, String> getKemuFuzhu() {
		return kemuFuzhu;
	}

	public void setKemuFuzhu(Map<String, String> kemuFuzhu) {
		this.kemuFuzhu = kemuFuzhu;
	}
	
	

	@Override
    public void run() {
    	try {
    		
    		log.info("sql===="  + sql);
            List<Map<String,Object>> orgMapList = dao.selectMaps(sql);
            List<String> orgCodeList = new ArrayList<String>();
            for(int i=0; i<orgMapList.size(); i++) {
             Map<String,Object> temp = orgMapList.get(i);
             String ORGCODE = (String)temp.get("ORGCODE");
             orgCodeList.add(ORGCODE);
            }
    		
            if("1".equals(this.isHaveYearMonth)) {
            	
	        		odsNcBalanceVoService.saveList(orgCodeList,kemuCodeListBalance, beginYear, beginMonth, endYear, endMonth);
	        		odsNcBalanceDetailService.saveList(orgCodeList,kemuCodeListBalanceDetail, beginYear, beginMonth, endYear, endMonth);
	        		
            } else {
            	
            	// 赵欣add======2021==========01-26=========begin
            	if(endYearList!=null && endYearList.size()>0) {
            		
            		for(int i=0; i<endYearList.size(); i++) {
            			String year = endYearList.get(i);
            			String month = endMonthList.get(i);
            			odsNcBalanceVoService.saveList(orgCodeList,kemuCodeListBalance, year, month, year, month);
    	        		odsNcBalanceDetailService.saveList(orgCodeList,kemuCodeListBalanceDetail, year, month, year, month);
            		}
            	// 赵欣add======2021==========01-26=========end	
            	} else {
            	
		    		odsNcBalanceVoService.saveList(orgCodeList,kemuCodeListBalance);
		    		odsNcBalanceDetailService.saveList(orgCodeList,kemuCodeListBalanceDetail);
            	}
	    		
            }
    		
    		Date endTime = new Date();
    		OdsNcNcthread ncthread = new OdsNcNcthread();
    		switch (countNcThread){
				case "1":
					ncthread.setEndtime1(endTime);
					break;
				case "2":
					ncthread.setEndtime2(endTime);
					break;
				case "3":
					ncthread.setEndtime3(endTime);
					break;
				case "4":
					ncthread.setEndtime4(endTime);
					break; 
				default: 
					ncthread.setEndtime5(endTime);
					break;
    		}
    		ncthread.setId(tid);
    		OdsNcNcthreadService.updateById(ncthread);
    		log.info("NcThread线程结束时间"+endTime);
		} catch (Exception e) {
			log.error("NcThread1线程异常"+e);
		}
       
    }
	

}
