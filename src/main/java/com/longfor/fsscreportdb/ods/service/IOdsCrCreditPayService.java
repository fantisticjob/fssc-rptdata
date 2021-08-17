package com.longfor.fsscreportdb.ods.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.entity.OdsCrCreditPay;

/**
 * <p>
 * 信用超期明细 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-08
 */
public interface IOdsCrCreditPayService extends IService<OdsCrCreditPay> {

	JSONObject saveList();

}
