package com.longfor.fsscreportdb.oa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.oa.entity.DinDim;
import com.longfor.fsscreportdb.oa.mapper.DinDimMapper;
import com.longfor.fsscreportdb.oa.service.IDinDimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DinDimServiceImpl extends ServiceImpl<DinDimMapper, DinDim> implements IDinDimService {
    @Autowired
    private DinDimMapper mapper;

    @Override
    public List<DinDim> getDinDimList(List<String> paramsList) {
        return mapper.getDinDimList(paramsList);
    }
}
