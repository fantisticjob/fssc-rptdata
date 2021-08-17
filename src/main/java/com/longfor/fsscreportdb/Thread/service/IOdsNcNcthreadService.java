package com.longfor.fsscreportdb.Thread.service;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreportdb.Thread.entity.OdsNcNcthread;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-19
 */
public interface IOdsNcNcthreadService extends IService<OdsNcNcthread> {

	BigDecimal saveGetId(OdsNcNcthread ncthread);

}
