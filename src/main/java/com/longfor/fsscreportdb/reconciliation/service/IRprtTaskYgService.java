package com.longfor.fsscreportdb.reconciliation.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.reconciliation.entity.RprtTaskYg;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-28
 */
public interface IRprtTaskYgService extends IService<RprtTaskYg> {

	public List<RprtTaskYg> getListBean();

}
