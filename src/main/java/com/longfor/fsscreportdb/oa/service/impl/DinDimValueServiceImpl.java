package com.longfor.fsscreportdb.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.oa.entity.DinDimValue;
import com.longfor.fsscreportdb.oa.mapper.DinDimValueMapper;
import com.longfor.fsscreportdb.oa.service.IDinDimValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DinDimValueServiceImpl extends ServiceImpl<DinDimValueMapper, DinDimValue> implements IDinDimValueService {
    @Autowired
    private DinDimValueMapper mapper;

    @Override
    public DinDimValue getSSOToken(QueryWrapper<DinDimValue> wrapper) {
        // TODO Auto-generated method stub
        return mapper.selectOne(wrapper);
    }
}
