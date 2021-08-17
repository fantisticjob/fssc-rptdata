package com.longfor.fsscreportdb.Thread.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.Thread.entity.OdsNcNcthread;
import com.longfor.fsscreportdb.Thread.mapper.OdsNcNcthreadMapper;
import com.longfor.fsscreportdb.Thread.service.IOdsNcNcthreadService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-19
 */
@Service
public class OdsNcNcthreadServiceImpl extends ServiceImpl<OdsNcNcthreadMapper, OdsNcNcthread> implements IOdsNcNcthreadService {

	
	@Override
	public BigDecimal saveGetId(OdsNcNcthread ncthread) {
		if(save(ncthread)) {
			return ncthread.getId();
		}
		return null;
	}

}
