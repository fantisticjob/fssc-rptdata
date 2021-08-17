package com.longfor.fsscreportdb.ods.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.entity.OdsZjzcAccountBalance;

/**
 * <p>
 * 资金自查账户余额表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-05
 */
public interface IOdsZjzcbankbalancesByDateService extends IService<OdsZjzcAccountBalance> {


	JSONObject saveList(String time);

}
