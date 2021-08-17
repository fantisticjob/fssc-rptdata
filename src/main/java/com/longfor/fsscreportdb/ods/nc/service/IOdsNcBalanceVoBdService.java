package com.longfor.fsscreportdb.ods.nc.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcBalanceVoBd;

/**
 * <p>
 * NC外系统查询科目余额表-银行直扣 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2021-04-28
 */
public interface IOdsNcBalanceVoBdService extends IService<OdsNcBalanceVoBd> {

	public JSONObject saveList(List<String> orgCodeList, List<String> kemuCodeListDetail);

}
