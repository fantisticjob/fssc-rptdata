package com.longfor.fsscreportdb.oa.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.oa.entity.RoleUserRights;

/**
 * <p>
 * 角色用户权限表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2021-01-13
 */
public interface IRoleUserRightsService extends IService<RoleUserRights> {

	public  List<RoleUserRights> selectList();

}
