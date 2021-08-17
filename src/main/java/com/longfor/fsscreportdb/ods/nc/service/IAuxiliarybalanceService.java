package com.longfor.fsscreportdb.ods.nc.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.nc.entity.Auxiliarybalance;

/**
 * <p>
 * 辅助余额表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-17
 */
public interface IAuxiliarybalanceService extends IService<Auxiliarybalance> {


	JSONObject saveList(String uuid);

	boolean saveByCvs(List<Auxiliarybalance> list);
}
