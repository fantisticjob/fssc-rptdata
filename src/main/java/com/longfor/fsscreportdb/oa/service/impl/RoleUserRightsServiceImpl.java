package com.longfor.fsscreportdb.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.oa.entity.RoleUserRights;
import com.longfor.fsscreportdb.oa.mapper.RoleUserRightsMapper;
import com.longfor.fsscreportdb.oa.service.IRoleUserRightsService;

/**
 * <p>
 * 角色用户权限表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2021-01-13
 */
@Service
public class RoleUserRightsServiceImpl extends ServiceImpl<RoleUserRightsMapper, RoleUserRights> implements IRoleUserRightsService {

	@Autowired
	private RoleUserRightsMapper mapper;
	
	
	@Override
	public List<RoleUserRights> selectList(){
		
		 
        QueryWrapper<RoleUserRights> queryWrapper = new  QueryWrapper<RoleUserRights>();
        queryWrapper.isNotNull("ACCOUNT");
        queryWrapper.isNotNull("RIGHTS");
        queryWrapper.isNotNull("AREA");
        queryWrapper.isNotNull("AREA");
        queryWrapper.like("RIGHTS","编辑权");
		return mapper.selectList(queryWrapper);
        
	}
	
	
}
