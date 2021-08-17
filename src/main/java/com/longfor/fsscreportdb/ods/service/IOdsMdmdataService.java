package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsHzMdmdata;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 项目经营情况主数据数据接口 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-07
 */
public interface IOdsMdmdataService extends IService<OdsHzMdmdata> {
	public JSONObject saveList();
}
