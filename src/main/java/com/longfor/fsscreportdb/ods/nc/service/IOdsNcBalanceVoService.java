package com.longfor.fsscreportdb.ods.nc.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcBalanceVo;

/**
 * <p>
 * NC外系统查询科目余额表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-09
 */
public interface IOdsNcBalanceVoService extends IService<OdsNcBalanceVo> {

	JSONObject saveList(List<String> orgCodeList, List<String> kemuCodeList);
	
	JSONObject saveList(List<String> orgCodeList, List<String> kemuCodeList ,
			String beginYear, 
			String beginMonth, 
			String endYear, 
			String endMonth);

}
