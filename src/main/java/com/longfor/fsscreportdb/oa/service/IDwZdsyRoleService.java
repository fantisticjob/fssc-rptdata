package com.longfor.fsscreportdb.oa.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.oa.entity.DwZdsyRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenziyao
 * @since 2021-04-13
 */
public interface IDwZdsyRoleService extends IService<DwZdsyRole> {

	List<DwZdsyRole> getlistByParameter(String flag);
}