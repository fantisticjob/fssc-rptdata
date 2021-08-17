package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsCtCostDetail;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * ods层成本明细 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-08
 */
public interface IOdsCtCostDetailService extends IService<OdsCtCostDetail> {

	JSONObject saveList(Map<String,Object> map);

}
