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
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcAuxiliaryBalancebdService;
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcBalanceDetailsbdService;
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcBalanceVoBdService;


@Component("NcThread2")
@Scope("prototype")
public class NcThread2 extends Thread{
	
	private  final static   Logger log = LoggerFactory.getLogger(NcThread2.class);

	@Autowired
	private CommonDao dao;
	
	@Autowired
	private IOdsNcNcthreadService OdsNcNcthreadService;
	
	@Autowired
	private IOdsNcAuxiliaryBalancebdService odsNcAuxiliaryBalancebdService;
	
	
	@Autowired
	private IOdsNcBalanceVoBdService odsNcBalanceVoBdService;
	
	
	@Autowired
	private IOdsNcBalanceDetailsbdService odsNcBalanceDetailsbdService;
    
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
    		
            List<Map<String,Object>> orgMapList = dao.selectMaps(sql);
            List<String> orgCodeList = new ArrayList<String>();
            for(int i=0; i<orgMapList.size(); i++) {
             Map<String,Object> temp = orgMapList.get(i);
             String ORGCODE = (String)temp.get("ORGCODE");
             orgCodeList.add(ORGCODE);
            }
    		
            String beginYearYE="";
            String beginMonthYE="";
            //===========add by zhaoxin=========NC辅助余额不能跨年问题处理==begin
            if(!beginYear.equals(endYear)) {
            	beginYearYE = endYear;
            	beginMonthYE = "01";
            } else {
            	beginYearYE = beginYear;
            	beginMonthYE = beginMonth;
            }
            // odsNcAuxiliaryBalancebdService.saveList(orgCodeList,kemuCodeListDetail,kemuFuzhu,  beginYear, beginMonth, endYear, endMonth);
            odsNcAuxiliaryBalancebdService.saveList(orgCodeList,kemuCodeListDetail,kemuFuzhu,  beginYearYE, beginMonthYE, endYear, endMonth);
            //===========add by zhaoxin=========NC辅助余额不能跨年问题处理==end
            	
            System.err.println("辅助余额表跑完了===========");
            odsNcBalanceDetailsbdService.saveList(orgCodeList, beginYear, beginMonth, endYear, endMonth);
            System.err.println("辅助余额明细表跑完了===========");
            //=======陈子瑶===2021-04-28
           odsNcBalanceVoBdService.saveList(orgCodeList,kemuCodeListDetail);
           System.err.println("NC外系统查询科目余额表-银行直扣跑完了===========");
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
    		log.info("NcThread2线程结束时间"+endTime);
		} catch (Exception e) {
			log.error("NcThread2线程异常"+e);
			e.printStackTrace();
		}
       
    }
	

}
