package com.longfor.fsscreportdb.ods.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.entity.OdsZdSwglzzsfb4;

/**
 * <p>
 * 增值税纳税申报表附列资料（四） 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2021-01-14
 */
public interface IOdsZdSwglzzsfb4Service extends IService<OdsZdSwglzzsfb4> {

	JSONObject saveList();

}
