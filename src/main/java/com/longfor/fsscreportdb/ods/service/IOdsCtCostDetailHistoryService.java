package com.longfor.fsscreportdb.ods.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.entity.OdsCtCostDetailHistory;

/**
 * <p>
 * ods层成本明细历史表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-08
 */
public interface IOdsCtCostDetailHistoryService extends IService<OdsCtCostDetailHistory> {

	JSONObject saveList(Map<String,Object> map);

}
