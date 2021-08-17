package com.longfor.fsscreportdb.ods.nc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.ods.nc.entity.NcTask;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-16
 */
public interface INcTaskService extends IService<NcTask> {

	void updateStoredProcedure(String quarter, String accountId, String companyName) throws Exception;

}
