package com.longfor.fsscreportdb.oa.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.oa.entity.DwCpClearUpDetail;

import java.util.List;

/**
 * <p>
 * 往来清理-往来明细类 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-28
 */
public interface IDwCpClearUpDetailService extends IService<DwCpClearUpDetail> {


    List<DwCpClearUpDetail> getListBeanByWrapper(QueryWrapper<DwCpClearUpDetail> queryWrapper);




}
