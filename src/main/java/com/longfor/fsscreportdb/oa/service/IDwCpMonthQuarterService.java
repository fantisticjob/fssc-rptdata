package com.longfor.fsscreportdb.oa.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.oa.entity.DwCpMonthQuarter;

public interface IDwCpMonthQuarterService extends IService<DwCpMonthQuarter> {
    DwCpMonthQuarter getMinDataDate();
}
