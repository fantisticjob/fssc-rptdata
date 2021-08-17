package com.longfor.fsscreportdb.ods.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.entity.OdsSkjSkjinfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-09-23
 */
public interface IOdsSkjSkjinfoService extends IService<OdsSkjSkjinfo> {

	JSONObject saveList();

}
