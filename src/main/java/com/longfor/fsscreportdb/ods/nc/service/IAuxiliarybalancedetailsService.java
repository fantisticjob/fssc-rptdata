package com.longfor.fsscreportdb.ods.nc.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.nc.entity.Auxiliarybalancedetails;

/**
 * <p>
 * 辅助余额明细表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-17
 */
public interface IAuxiliarybalancedetailsService extends IService<Auxiliarybalancedetails> {

	JSONObject saveList(String uuid);
	
	boolean saveByCvs(List<Auxiliarybalancedetails> list);
}
