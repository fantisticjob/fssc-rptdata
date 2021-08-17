package com.longfor.fsscreportdb.ods.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.entity.OdsCrCreditPay;

/**
 * <p>
 * 信用付款 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-08
 */
public interface IOdsCrCreditPayService2 extends IService<OdsCrCreditPay> {


	JSONObject saveList(String startTime, String endTime);

}
