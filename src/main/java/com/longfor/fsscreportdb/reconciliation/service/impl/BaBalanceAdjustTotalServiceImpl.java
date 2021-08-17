package com.longfor.fsscreportdb.reconciliation.service.impl;

import com.longfor.fsscreportdb.reconciliation.entity.BaBalanceAdjustTotal;
import com.longfor.fsscreportdb.reconciliation.entity.StoredProcedure;
import com.longfor.fsscreportdb.reconciliation.mapper.BaBalanceAdjustTotalMapper;
import com.longfor.fsscreportdb.reconciliation.service.IBaBalanceAdjustTotalService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 汇总余额调节表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-18
 */
@Service
public class BaBalanceAdjustTotalServiceImpl extends ServiceImpl<BaBalanceAdjustTotalMapper, BaBalanceAdjustTotal> implements IBaBalanceAdjustTotalService {

	@Autowired
	private BaBalanceAdjustTotalMapper mapper;
	
	@Override
	public List<BaBalanceAdjustTotal> getBalanceByAccountId(String sql) {
		
		return mapper.getBalanceByAccountId(sql);
	}
	@Override
	public void updateStoredProcedure(String p_account, String p_date) {
		mapper.updateStoredProcedure(p_account, p_date);
	}
	
	@Override
	public void yueduGuanzhangGetOne(String p_commpany, String p_date ) {
		mapper.yueduGuanzhangGetOne(p_commpany, p_date);
	}
	
	@Override
	public void yueduGuanzhangHZ(String p_commpany, String p_date ) {
		mapper.yueduGuanzhangHZ(p_commpany, p_date);
	}
	
	@Override
	public void yueduGuanzhangMX(String p_commpany, String p_date, String p_check_point ) {
		mapper.yueduGuanzhangMX(p_commpany, p_date, p_check_point);
	}
	
	
	@Override
	public void getvResults(StoredProcedure s) {
		// TODO Auto-generated method stub
		mapper.getvResults(s);
	}
	@Override
	public void initAdjust(StoredProcedure sp) {
		// TODO Auto-generated method stub
		mapper.initAdjust(sp);
	}
	@Override
	public void oneTouchTotal(StoredProcedure sp) {
		// TODO Auto-generated method stub
		mapper.oneTouchTotal(sp);
	}
	@Override
	public void automatic_Update() {
		// TODO Auto-generated method stub
		mapper.automatic_Update();
		
	}
	@Override
	public List<BaBalanceAdjustTotal> getBaBalanceAdjustTotalList(QueryWrapper<BaBalanceAdjustTotal> query) {
		return mapper.selectList(query);
	}
}
