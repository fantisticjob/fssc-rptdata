package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsHzNcdata;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 项目经营情况NC数据接口 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-06
 */
public interface IOdsNcdataService extends IService<OdsHzNcdata> {

	public JSONObject saveList();

}
