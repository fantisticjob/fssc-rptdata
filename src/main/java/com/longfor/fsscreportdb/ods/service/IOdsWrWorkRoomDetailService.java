package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsWrWorkRoomDetail;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * ods层工抵房明细表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-08
 */
public interface IOdsWrWorkRoomDetailService extends IService<OdsWrWorkRoomDetail> {

	JSONObject saveList();

}
