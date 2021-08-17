package com.longfor.fsscreportdb.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.oa.entity.DwZdsyRole;
import com.longfor.fsscreportdb.oa.mapper.DwZdsyRoleMapper;
import com.longfor.fsscreportdb.oa.service.IDwZdsyRoleService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2021-04-13
 */
@Service
public class DwZdsyRoleServiceImpl extends ServiceImpl<DwZdsyRoleMapper, DwZdsyRole> implements IDwZdsyRoleService {

	@Autowired
	private DwZdsyRoleMapper mapper;
	
	@Override
	public List<DwZdsyRole> getlistByParameter(String flag){
		
		QueryWrapper<DwZdsyRole> wrapper = new QueryWrapper<>();
		wrapper.eq("flag", flag);
		return mapper.selectList(wrapper);
		
	}
	
}
