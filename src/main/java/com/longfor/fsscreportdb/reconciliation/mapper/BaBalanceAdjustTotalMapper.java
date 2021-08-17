package com.longfor.fsscreportdb.reconciliation.mapper;

import com.longfor.fsscreportdb.reconciliation.entity.BaBalanceAdjustTotal;
import com.longfor.fsscreportdb.reconciliation.entity.StoredProcedure;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 汇总余额调节表 Mapper 接口
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-18
 */
@Repository
public interface BaBalanceAdjustTotalMapper extends BaseMapper<BaBalanceAdjustTotal> {

	@Select("<script>"
			+"${sql}"
			+ "</script>")
	List<BaBalanceAdjustTotal> getBalanceByAccountId(String sql);
	
	
	@Update("{call fsscreport.BA_BALANCE_PKG.update_balance_total(#{p_account,jdbcType=VARCHAR,mode=IN},#{p_date,jdbcType=VARCHAR,mode=IN})}")
	@Options(statementType = StatementType.CALLABLE)
	void updateStoredProcedure(String p_account,String p_date);
	
	@Update("{call fsscreport.ba_balance_pkg.update_ba_data_sd(#{p_account,jdbcType=VARCHAR,mode=IN},#{p_date,jdbcType=VARCHAR,mode=IN},#{v_results,jdbcType=VARCHAR,mode=OUT})}")
	@Options(statementType = StatementType.CALLABLE)
	void getvResults(StoredProcedure s);
	
	
//	@Update("{call fsscreport.ba_balance_pkg.update_ba_data_sd(#{p_account,jdbcType=VARCHAR,mode=IN},#{p_date,jdbcType=VARCHAR,mode=IN},#{p_flag,jdbcType=VARCHAR,mode=IN},#{v_results,jdbcType=VARCHAR,mode=OUT})}")
//	@Options(statementType = StatementType.CALLABLE)
//	void getvResults(StoredProcedure s);
	
	
	@Update("{call fsscreport.ba_balance_pkg.update_initialize(#{p_account,jdbcType=VARCHAR,mode=IN},#{p_date,jdbcType=VARCHAR,mode=IN})}")
	@Options(statementType = StatementType.CALLABLE)
	void initAdjust(StoredProcedure s);

	@Update("{call fsscreport.ba_balance_pkg.ba_balance_adjust(#{p_accounts,jdbcType=VARCHAR,mode=IN},#{p_date,jdbcType=VARCHAR,mode=IN},#{v_results,jdbcType=VARCHAR,mode=OUT})}")
	@Options(statementType = StatementType.CALLABLE)
	void oneTouchTotal(StoredProcedure sp);
	
	@Update("{call fsscreport.ba_balance_pkg.update_ba_data_zd}")
	@Options(statementType = StatementType.CALLABLE)
	void automatic_Update();
	
	@Update("{call fsscreport.ca_list_of_months_pkg.dw_ca_single_run(#{p_date,jdbcType=VARCHAR,mode=IN},#{p_company,jdbcType=VARCHAR,mode=IN})}")
	@Options(statementType = StatementType.CALLABLE)
	void yueduGuanzhangGetOne(String p_company, String p_date);
	
	@Update("{call fsscreport.ca_list_of_months_pkg.dw_ca_single_run_hz(#{p_date,jdbcType=VARCHAR,mode=IN},#{p_company,jdbcType=VARCHAR,mode=IN})}")
	@Options(statementType = StatementType.CALLABLE)
	void yueduGuanzhangHZ(String p_company, String p_date);
	
	@Update("{call fsscreport.ca_list_of_months_pkg.dw_ca_single_run_detail(#{p_date,jdbcType=VARCHAR,mode=IN},#{p_company,jdbcType=VARCHAR,mode=IN}, #{p_check_point,jdbcType=VARCHAR,mode=IN})}")
	@Options(statementType = StatementType.CALLABLE)
	void yueduGuanzhangMX(String p_company, String p_date, String p_check_point);
	
}
