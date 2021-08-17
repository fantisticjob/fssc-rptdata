package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsZdSwglqtssmx;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 税务管理其他税收 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-11
 */
public interface IOdsZdSwglqtssmxService extends IService<OdsZdSwglqtssmx> {

	JSONObject saveList();

}
