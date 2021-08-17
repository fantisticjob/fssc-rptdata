package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsCrCreditPayOverdue;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 信用超期明细 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-08
 */
public interface IOdsCrCreditPayOverdueService extends IService<OdsCrCreditPayOverdue> {

	JSONObject saveList();

}
