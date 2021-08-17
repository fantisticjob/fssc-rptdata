package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsZdNcglsubjectbalance;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * NC科目数据 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-11
 */
public interface IOdsZdNcglsubjectbalanceService extends IService<OdsZdNcglsubjectbalance> {
	
	JSONObject saveList(String year, String month);

}
