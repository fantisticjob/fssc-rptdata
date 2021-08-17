package com.longfor.fsscreportdb.oa.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.oa.entity.DinDimValue;

public interface IDinDimValueService extends IService<DinDimValue> {
    DinDimValue getSSOToken(QueryWrapper<DinDimValue> wrapper);
}
