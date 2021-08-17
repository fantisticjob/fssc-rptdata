package com.longfor.fsscreportdb.ods.service;

import com.longfor.fsscreportdb.ods.entity.OdsDaDocumentAnalysis;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 单据退回分析明细 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-10
 */
public interface IOdsDaDocumentAnalysisService extends IService<OdsDaDocumentAnalysis> {

	JSONObject saveList();
	
	JSONObject saveList(String year, String month);
}
