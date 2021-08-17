package com.longfor.fsscreportdb.ods.nc.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcBalanceDetailsbd;

/**
 * <p>
 * NC外系统查询辅助余额明细表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-12
 */
public interface IOdsNcBalanceDetailsbdService extends IService<OdsNcBalanceDetailsbd> {

	// JSONObject saveList(Integer cointing);
	JSONObject saveList(List<String> orgCodeList);
	
	JSONObject saveList(List<String> orgCodeList,
			String beginYear, 
			String beginMonth, 
			String endYear, 
			String endMonth);

}
