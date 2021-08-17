package com.longfor.fsscreportdb.reconciliation.service;

import com.longfor.fsscreportdb.reconciliation.entity.BaBalanceAdjustTotal;
import com.longfor.fsscreportdb.reconciliation.entity.StoredProcedure;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 汇总余额调节表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-18
 */
public interface IBaBalanceAdjustTotalService extends IService<BaBalanceAdjustTotal> {
	
	List<BaBalanceAdjustTotal>  getBalanceByAccountId(String sql);
	
	void yueduGuanzhangHZ(String p_date, String p_company);
	
	void yueduGuanzhangMX(String p_date, String p_company, String p_check_point);
	
	void updateStoredProcedure(String p_account,String p_date);
	
	void yueduGuanzhangGetOne(String p_date, String p_company);
	
	void getvResults(StoredProcedure s);

	void initAdjust(StoredProcedure sp);

	void oneTouchTotal(StoredProcedure sp);
	
	void automatic_Update();

	List<BaBalanceAdjustTotal> getBaBalanceAdjustTotalList(QueryWrapper<BaBalanceAdjustTotal> query);

}
