package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsZjzcAccountTrade;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 资金自查账户交易表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-05
 */
public interface IOdsZjzcbanktransactionsService extends IService<OdsZjzcAccountTrade> {

	JSONObject saveList();

}
