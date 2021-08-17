package com.longfor.fsscreportdb.reconciliation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.reconciliation.entity.RprtTask;
import com.longfor.fsscreportdb.reconciliation.mapper.RprtTaskMapper;
import com.longfor.fsscreportdb.reconciliation.service.IRprtTaskService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-28
 */
@Service
public class RprtTaskServiceImpl extends ServiceImpl<RprtTaskMapper, RprtTask> implements IRprtTaskService {

	
	@Autowired
	private RprtTaskMapper mapper;
	
	@Override
	public List<RprtTask> getListBean(){
		QueryWrapper<RprtTask> wrapper = new  QueryWrapper<RprtTask>();
		wrapper.isNull("ENDTIME_IRA");
		return mapper.selectList(wrapper);
	}
}
