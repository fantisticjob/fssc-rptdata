package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsZjzcAccount;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 资金自查账户表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-05
 */
public interface IOdsZjzcaccountsService extends IService<OdsZjzcAccount> {

	JSONObject saveList();
}
