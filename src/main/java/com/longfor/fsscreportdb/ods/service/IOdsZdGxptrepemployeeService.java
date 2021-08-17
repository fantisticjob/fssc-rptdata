package com.longfor.fsscreportdb.ods.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.entity.OdsZdGxptrepemployee;

/**
 * <p>
 * 共享平台公司职工数表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2021-03-10
 */
public interface IOdsZdGxptrepemployeeService extends IService<OdsZdGxptrepemployee> {

	JSONObject saveList();

}
