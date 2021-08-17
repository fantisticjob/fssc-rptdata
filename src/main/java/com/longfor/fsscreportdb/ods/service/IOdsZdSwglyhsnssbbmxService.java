package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsZdSwglyhsnssbbmx;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 印花税 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-11
 */
public interface IOdsZdSwglyhsnssbbmxService extends IService<OdsZdSwglyhsnssbbmx> {

	JSONObject saveList();
}
