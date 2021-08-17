package com.longfor.fsscreportdb.ods.nc.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcAuxiliaryBalance;

/**
 * <p>
 * NC外系统查询辅助余额表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-10
 */
public interface IOdsNcAuxiliaryBalanceService extends IService<OdsNcAuxiliaryBalance> {

	JSONObject saveList(List<String> orgCodeList, List<String> kemuCodeList, Map<String, String> fuzhuMap);
	
	JSONObject saveList(List<String> orgCodeList, List<String> kemuCodeList, Map<String, String> fuzhuMap,
			String beginYear, 
			String beginMonth, 
			String endYear, 
			String endMonth);
	

	List<OdsNcAuxiliaryBalance> getAllBean(Integer count, Integer total);
	
	List<OdsNcAuxiliaryBalance> getAllbyOrgCode(String orgCode);

}
