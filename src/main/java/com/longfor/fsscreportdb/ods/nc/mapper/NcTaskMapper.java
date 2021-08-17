package com.longfor.fsscreportdb.ods.nc.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longfor.fsscreportdb.ods.nc.entity.NcTask;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-16
 */
public interface NcTaskMapper extends BaseMapper<NcTask> {

	
	@Update("{call fsscreport.cp_clear_up_pkg.update_cp(#{p_quarter,jdbcType=VARCHAR,mode=IN},#{p_accounts,jdbcType=VARCHAR,mode=IN},#{p_subject,jdbcType=VARCHAR,mode=IN})}")
	@Options(statementType = StatementType.CALLABLE)
	void updateStoredProcedure(String quarter, String accounts, String subject);
}
