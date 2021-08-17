package com.longfor.fsscreportdb.oa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.oa.entity.DinDim;

import java.util.List;

public interface IDinDimService extends IService<DinDim> {
    List<DinDim>  getDinDimList(List<String> paramsList);
}
