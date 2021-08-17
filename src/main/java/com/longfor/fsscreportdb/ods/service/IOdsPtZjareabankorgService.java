package com.longfor.fsscreportdb.ods.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.entity.OdsPtZjareabankorg;

/**
 * <p>
 * 资金银行账户-地区-组织对照关系 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-13
 */
public interface IOdsPtZjareabankorgService extends IService<OdsPtZjareabankorg> {

	JSONObject saveList();

}
