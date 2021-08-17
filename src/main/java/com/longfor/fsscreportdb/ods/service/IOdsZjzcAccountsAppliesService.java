package com.longfor.fsscreportdb.ods.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.entity.OdsZjzcAccountsApplies;

/**
 * <p>
 * 资金自查账户申请表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-09-09
 */
public interface IOdsZjzcAccountsAppliesService extends IService<OdsZjzcAccountsApplies> {

	JSONObject saveList();

}
