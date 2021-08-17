package com.longfor.fsscreportdb.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longfor.fsscreportdb.oa.entity.DwCpMonthQuarter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DwCpMonthQuarterMapper extends BaseMapper<DwCpMonthQuarter> {
    public DwCpMonthQuarter getMinDataDate();
}
