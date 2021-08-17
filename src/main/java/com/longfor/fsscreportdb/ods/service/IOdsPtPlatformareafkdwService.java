package com.longfor.fsscreportdb.ods.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.entity.OdsPtPlatformareafkdw;

/**
 * <p>
 * 平台地区付款单位 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-13
 */
public interface IOdsPtPlatformareafkdwService extends IService<OdsPtPlatformareafkdw> {

	JSONObject saveList();

}
