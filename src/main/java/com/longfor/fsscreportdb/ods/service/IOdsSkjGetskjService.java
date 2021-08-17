package com.longfor.fsscreportdb.ods.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.entity.OdsSkjGetskj;

/**
 * <p>
 * 收款机接口 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-19
 */
public interface IOdsSkjGetskjService extends IService<OdsSkjGetskj> {

	JSONObject saveList();

}
