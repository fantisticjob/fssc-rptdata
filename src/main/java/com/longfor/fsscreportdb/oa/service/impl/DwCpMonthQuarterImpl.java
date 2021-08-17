package com.longfor.fsscreportdb.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.oa.entity.DwCpMonthQuarter;
import com.longfor.fsscreportdb.oa.mapper.DwCpMonthQuarterMapper;
import com.longfor.fsscreportdb.oa.service.IDwCpMonthQuarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DwCpMonthQuarterImpl extends ServiceImpl<DwCpMonthQuarterMapper, DwCpMonthQuarter> implements IDwCpMonthQuarterService {
    @Autowired
    private DwCpMonthQuarterMapper mapper;

    @Override
    public DwCpMonthQuarter getMinDataDate() {
        return mapper.getMinDataDate();
    }
}
