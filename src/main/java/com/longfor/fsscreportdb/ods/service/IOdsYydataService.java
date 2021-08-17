package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsHzYydata;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 项目经营情况运营数据接口 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-07
 */
public interface IOdsYydataService extends IService<OdsHzYydata> {

	JSONObject saveList();

}
