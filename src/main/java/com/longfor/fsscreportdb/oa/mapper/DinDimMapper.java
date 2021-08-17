package com.longfor.fsscreportdb.oa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.longfor.fsscreportdb.oa.entity.DinDim;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DinDimMapper extends BaseMapper<DinDim>{
    public List<DinDim> getDinDimList(List<String> paramsList);
}
