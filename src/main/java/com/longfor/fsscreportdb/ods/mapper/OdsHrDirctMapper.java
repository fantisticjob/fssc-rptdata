package com.longfor.fsscreportdb.ods.mapper;


import com.longfor.fsscreportdb.ods.entity.OdsHrDirct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OdsHrDirctMapper {
    /**
     * 按照地区获取需要龙信拉群的往来清理记录
     * @param paramsList 地区列表
     * @return 需要拉群的往来清理记录集合
     */
    public List<OdsHrDirct> searchLeaderByOaAccct(List<String> paramsList);

    /**
     * 获取当前季度所有地区需要龙信拉群的往来清理记录
     *
     * @return 需要拉群的往来清理记录集合
     */
    public List<OdsHrDirct> searchUserByOaAccct();

    /**
     * 按照地区获取龙信拉群二次晾晒的往来清理记录
     * @param paramsList 地区列表
     * @return 需要二次晾晒的往来清理记录集合
     */
    public List<OdsHrDirct> searchClearRrdForGroup(List<String> paramsList);
}
