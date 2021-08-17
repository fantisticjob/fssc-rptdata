package com.longfor.fsscreportdb.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.oa.entity.DwCpClearUpDetail;
import com.longfor.fsscreportdb.oa.mapper.DwCpClearUpDetailMapper;
import com.longfor.fsscreportdb.oa.service.IDwCpClearUpDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 往来清理-往来明细类 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-28
 */
@Service
public class DwCpClearUpDetailServiceImpl extends ServiceImpl<DwCpClearUpDetailMapper, DwCpClearUpDetail>
        implements IDwCpClearUpDetailService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final  String RESULT = "result";
    private static final  String STATUS = "status";

    @Autowired
    private DwCpClearUpDetailMapper mapper;

    @Override
    public List<DwCpClearUpDetail> getListBeanByWrapper(QueryWrapper<DwCpClearUpDetail> queryWrapper) {

        return mapper.selectList(queryWrapper);
    }



}
