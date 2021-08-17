package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsZdSwglzzsfb2;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 增值税纳税申报表附列资料（二） 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-11
 */
public interface IOdsZdSwglzzsfb2Service extends IService<OdsZdSwglzzsfb2> {

	JSONObject saveList();

}
