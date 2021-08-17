package com.longfor.fsscreportdb.ods.service.impl;

import com.longfor.fsscreportdb.ods.entity.OdsHrDirct;
import com.longfor.fsscreportdb.ods.mapper.OdsHrDirctMapper;
import com.longfor.fsscreportdb.ods.service.IOdsHrDirctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdsHrDirctServiceImpl implements IOdsHrDirctService {

    @Autowired
    private OdsHrDirctMapper odsHrDirctMapper;

    @Override
    public List<OdsHrDirct> searchLeaderByOaAccct(List<String> paramsList) {
        return odsHrDirctMapper.searchLeaderByOaAccct(paramsList);
    }

    @Override
    public List<OdsHrDirct> searchUserByOaAccct() {
        return odsHrDirctMapper.searchUserByOaAccct();
    }

    /**
     * 按照地区获取龙信拉群二次晾晒的往来清理记录
     *
     * @param paramsList 地区列表
     * @return 需要二次晾晒的往来清理记录集合
     */
    @Override
    public List<OdsHrDirct> searchClearRrdForGroup(List<String> paramsList) {
        return odsHrDirctMapper.searchClearRrdForGroup(paramsList);
    }
}