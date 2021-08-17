package com.longfor.fsscreportdb.reconciliation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.reconciliation.entity.RprtTaskYg;
import com.longfor.fsscreportdb.reconciliation.mapper.RprtTaskYgMapper;
import com.longfor.fsscreportdb.reconciliation.service.IRprtTaskYgService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-28
 */
@Service
public class RprtTaskYgServiceImpl extends ServiceImpl<RprtTaskYgMapper, RprtTaskYg> implements IRprtTaskYgService {

	
	@Autowired
	private RprtTaskYgMapper mapper;
	
	@Override
	public List<RprtTaskYg> getListBean(){
		QueryWrapper<RprtTaskYg> wrapper = new  QueryWrapper<RprtTaskYg>();
		wrapper.isNull("ENDTIME_IRA");
		return mapper.selectList(wrapper);
	}
}
