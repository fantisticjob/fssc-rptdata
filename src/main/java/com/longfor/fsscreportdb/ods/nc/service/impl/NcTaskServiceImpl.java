package com.longfor.fsscreportdb.ods.nc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.ods.nc.entity.NcTask;
import com.longfor.fsscreportdb.ods.nc.mapper.NcTaskMapper;
import com.longfor.fsscreportdb.ods.nc.service.INcTaskService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-16
 */
@Service
public class NcTaskServiceImpl extends ServiceImpl<NcTaskMapper, NcTask> implements INcTaskService {

	@Autowired
	private NcTaskMapper mapper;
	
	@Override
	public void updateStoredProcedure(String quarter, String accountId, String companyName) throws Exception{
		mapper.updateStoredProcedure(quarter, accountId, companyName);
	}

}
