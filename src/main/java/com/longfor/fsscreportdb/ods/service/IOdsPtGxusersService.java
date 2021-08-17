package com.longfor.fsscreportdb.ods.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.entity.OdsPtGxusers;

/**
 * <p>
 * 共享所有人员接口 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-18
 */
public interface IOdsPtGxusersService extends IService<OdsPtGxusers> {

	JSONObject saveList();

}
