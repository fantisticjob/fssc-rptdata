package com.longfor.fsscreportdb.ods.nc.service;

import java.util.List;

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
public interface IOdsNcAuxiliaryBalanceService_s extends IService<OdsNcAuxiliaryBalance> {

	JSONObject saveList(List<String> kemuCodeList);
	
	// 初期化=======36个月
	List<String> saveList(String uuid, List<String> kemuCodeList, List<String> yearList, List<String> monthList);

}
