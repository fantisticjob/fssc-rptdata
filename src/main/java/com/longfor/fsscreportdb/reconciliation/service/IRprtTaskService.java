package com.longfor.fsscreportdb.reconciliation.service;

import com.longfor.fsscreportdb.reconciliation.entity.RprtTask;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-28
 */
public interface IRprtTaskService extends IService<RprtTask> {

	public List<RprtTask> getListBean();

}
