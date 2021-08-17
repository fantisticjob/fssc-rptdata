package com.longfor.fsscreportdb.reconciliation.service.impl;


import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.reconciliation.entity.RprtTask;
import com.longfor.fsscreportdb.reconciliation.entity.RprtTaskYg;
import com.longfor.fsscreportdb.reconciliation.entity.StoredProcedure;
import com.longfor.fsscreportdb.reconciliation.service.IBaBalanceAdjustTotalService;
import com.longfor.fsscreportdb.reconciliation.service.IRprtTaskService;
import com.longfor.fsscreportdb.reconciliation.service.IRprtTaskYgService;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;



/**
 * <p>
 * 银行余额/月度关账 异步任务
 * 
 * 1：银行余额自动落库
 * 2：月度关账自动落库
 * 3：银行余额-手动更新
 * 4：月度关账-手动更新
 * 
 * </p>
 * @author 
 * @since 2020-07-30
 */
@Component("BankMonthlyTask")
@Scope("prototype")
public class BankMonthlyTask extends Thread {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommonDao commonDao;

	@Autowired
	private IRprtTaskService irs;
	
	@Autowired
	private IRprtTaskYgService irsyg;
	
	@Autowired
	private IBaBalanceAdjustTotalService ibal;
	
	
	@Value("${kettel.host2}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	
	
	public String dealType = "";
	public String dealid = "";
	
	public String getDealid() {
		return dealid;
	}
	public void setDealid(String dealid) {
		this.dealid = dealid;
	}


	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public void  run () {
	
		// 1：银行余额自动落库
		if("1".equals(dealType)) {
			
	    	// 自动结束落库（银行余额）处理完了，(机器人处理：1上月   2本月)
	    	// 银行余额总调度：/home/lhadmin/ETL/ACT_ETL/BA/J_BA_ANALYSE.kjb  月一次
	    	// 自动更新调度：/home/lhadmin/ETL/ACT_ETL/BA/J_BA_UPDATA_ZD.kjb  
	    	String selectCount = "select count(*) from RPRT_TASK where OPRATER_FLAG = '1' "
	    			+ " and ENDTIME_IRA is null";
	    	
	    	Integer count = commonDao.selectCount(selectCount);
	    	log.info("机器人处理中任务数量：{}" , count);
	     
	    	// 没有处理中任务， (自动落库只是处理_2)
	    	if((count == null || count == 0) && dealid.endsWith("_2")) {
	    		
	    		log.info("ketelHost1：{}" , ketelHost1); 
	    		log.info("ketelPort：{}" , ketelPort); 
	    		log.info("ketelJobPath：{}" , ketelJobPath); 
	    		
	    		// 当月执行回数
	    		selectCount = "select count(ENDTIME_FR) from RPRT_TASK "
	    				+ " where to_char(ENDTIME_FR, 'yyyy-MM') = to_char(sysdate, 'yyyy-MM') and OPRATER_FLAG = '1'";
	    		Integer times = commonDao.selectCount(selectCount);
	    		log.info("本月帆软处理完成数：{}" , times);
	    		
	    		if(times == null || times == 0) {
	    			
	    			log.info("银行余额总调度执行");
	    			KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
	    	        kettleUtilRemote.callRemote(
	    	        		ketelHost1, 
	    	        		ketelPort,  ketelJobPath + "/BA/J_BA_ANALYSE.kjb");
	    	        
	    		} else {
	    			
	    			log.info("自动更新调度执行");
	    			KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
	    	        kettleUtilRemote.callRemote(
	    	        		ketelHost1, 
	    	        		ketelPort,  ketelJobPath + "/BA/J_BA_UPDATA_ZD.kjb");
	    		}
		
	    		RprtTask rprtTask = new RprtTask();
		    	UpdateWrapper<RprtTask> wrapper = new UpdateWrapper<RprtTask>();
		    	
    	        rprtTask.setEndtimeFr(new Date());
    	        rprtTask.setFinishStatus("1");
    	        wrapper.eq("DEALID", dealid);
    	        irs.update(rprtTask, wrapper);
    	        
	    	} else {
	    		// 处理中任务
	    		log.info("处理中任务有：本次不处理银行余额ETL" );
	    		
	    		// 本次不处理里 FR
	    		RprtTask rprtTask = new RprtTask();
		    	UpdateWrapper<RprtTask> wrapper = new UpdateWrapper<RprtTask>();
		    	
    	        rprtTask.setEndtimeFr(new Date());
    	        rprtTask.setFinishStatus("1");
    	        wrapper.eq("DEALID", dealid);
    	        irs.update(rprtTask, wrapper);
	    	}
		} 
		
		// 2：月度关账自动落库
		if("2".equals(dealType)) {
			
			RprtTaskYg rprtTaskYg = new RprtTaskYg();
	    	UpdateWrapper<RprtTaskYg> wrapper = new UpdateWrapper<RprtTaskYg>();
	    	
			// 自动结束落库（月度关帐）处理完了
	    	String selectCount = "select count(*) from RPRT_TASK_YG where OPRATER_FLAG = '1' "
	    			+ " and ENDTIME_IRA is null";
	    	
	    	Integer count = commonDao.selectCount(selectCount);
	    	log.info("机器人处理中任务数量(月度关帐)：{}" , count);
	     
	    	// 没有处理中任务， 
	    	if(count==null || count == 0) {
	    		
	    		log.info("ketelHost1：" + ketelHost1); 
	    		log.info("ketelPort：" + ketelPort); 
	    		log.info("ketelJobPath：" + ketelJobPath); 
	    		
    			log.info("月度关帐调度执行");
    			KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
    	        kettleUtilRemote.callRemote(
    	        		ketelHost1, 
    	        		ketelPort,  ketelJobPath + "/CA/J_CA_ANALYSE.kjb");
	    	        
    	        rprtTaskYg.setEndtimeFr(new Date());
    	        rprtTaskYg.setFinishStatus("1");
    	        wrapper.eq("DEALID", dealid);
    	        irsyg.update(rprtTaskYg, wrapper);
    	        
	    	} else {
	    		// 处理中任务
	    		log.info("处理中任务有：本次不处理月度关帐ETL" );
	    	}
	    	
		} 
		
		// 3：银行余额-手动更新
		if("3".equals(dealType)) {
			
			RprtTask rprtTask = new RprtTask();
	    	UpdateWrapper<RprtTask> wrapper = new UpdateWrapper<RprtTask>();
	    	
			// 单账户数据更新存储过程调用
			String select = "select * from RPRT_TASK where DEALID='" + dealid + "'";
			Map<String,Object> rtp_taskMap = 
					commonDao.selectMap(select);
			String accountId = (String)rtp_taskMap.get("ACCOUNT_ID");
			String month = (String)rtp_taskMap.get("YEARMONTH");
			
			log.info("银行余额-手动更新-手动结束accountId：{}" , accountId);
			log.info("银行余额-手动更新-手动结束month：{}" , month);
						
			StoredProcedure sp= new StoredProcedure();
			sp.setP_account(accountId);
			sp.setP_date(month);
			ibal.getvResults(sp);
			
			log.info("END-StoredProcedure");
			
	    	wrapper.eq("DEALID", dealid);
	    	rprtTask.setEndtimeFr(new Date());
	    	rprtTask.setFinishStatus("1");
	    	
	    	irs.update(rprtTask, wrapper);
		} 
		
		// 4：月度关账-手动更新
		if("4".equals(dealType)) {
			
			RprtTaskYg rprtTaskYg = new RprtTaskYg();
	    	UpdateWrapper<RprtTaskYg> wrapper = new UpdateWrapper<RprtTaskYg>();
	    	
			String select = "select * from RPRT_TASK_YG where DEALID='" + dealid + "'";
			Map<String,Object> rtp_taskMap = 
					commonDao.selectMap(select);
			String accountId = (String)rtp_taskMap.get("ACCOUNT_ID");
			String month = (String)rtp_taskMap.get("YEARMONTH");
			
			String UPDATE_FLAG = (String)rtp_taskMap.get("UPDATE_FLAG");
			
			if("2".equals(UPDATE_FLAG)) {
				
				String DETAILKEMU = (String)rtp_taskMap.get("DETAILKEMU");
				log.info("明细回掉存储过程开始");
				log.info("DETAILKEMU==" + DETAILKEMU);
				ibal.yueduGuanzhangMX(accountId,  month, DETAILKEMU);
				log.info("明细回掉存储过程结束");
			} else {
				// ibal.yueduGuanzhangGetOne(accountId,  month);
				log.info("汇总回掉存储过程开始");
				ibal.yueduGuanzhangHZ(accountId,  month);
				log.info("汇总回掉存储过程结束");
			}
			
			
			log.info("月度关账-手动更新-手动结束accountId：{}" , accountId);
			log.info("月度关账-手动更新-手动结束month：{}" , month);
			
			wrapper = new UpdateWrapper<RprtTaskYg>();
	    	rprtTaskYg = new RprtTaskYg();
	    	wrapper.eq("DEALID", dealid);
	    	rprtTaskYg.setEndtimeFr(new Date());
	    	rprtTaskYg.setFinishStatus("1");
	    	
	    	irsyg.update(rprtTaskYg, wrapper);
		} 
		
	}
}
