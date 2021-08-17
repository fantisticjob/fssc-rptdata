package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsHzTjbbnckm;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-06
 */
public interface IOdsTjbbnckmService extends IService<OdsHzTjbbnckm> {

	public  JSONObject saveList();
}
