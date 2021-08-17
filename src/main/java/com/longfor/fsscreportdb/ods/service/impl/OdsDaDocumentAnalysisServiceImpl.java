package com.longfor.fsscreportdb.ods.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.constant.CommonConstant;
import com.longfor.fsscreportdb.ods.entity.OdsDaDocumentAnalysis;
import com.longfor.fsscreportdb.ods.mapper.OdsDaDocumentAnalysisMapper;
import com.longfor.fsscreportdb.ods.service.IOdsDaDocumentAnalysisService;
import com.longfor.fsscreportdb.utils.GetProperties;
import com.longfor.fsscreportdb.utils.HttpUtils;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * 单据退回分析明细 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-10
 */
@Service
@SuppressWarnings("all")
public class OdsDaDocumentAnalysisServiceImpl extends ServiceImpl<OdsDaDocumentAnalysisMapper, OdsDaDocumentAnalysis> implements IOdsDaDocumentAnalysisService {

	
	@Autowired
	private CommonDao dao;
	
	private static final  String STATUS="status";

    private final Logger log = LoggerFactory.getLogger(OdsDaDocumentAnalysisServiceImpl.class);

	private  GetProperties properties = new GetProperties();
	
	
	
	// 单据退回 12月份数据取得
	@Override
	public JSONObject saveList(String year, String month){
		
		boolean flag =true;
		Integer total=0;
		Integer count=0;
		int errorCount = 0;
		HttpUtils utils = new  HttpUtils();
		JSONObject json = new JSONObject();
		try {
			
			JSONObject date = StringUtil.getNewDate();
			// String year = StringUtil.getYesYear();
			// String year = (String)date.getString("year");
			// String month = (String)date.getString("month");

			
			String delete ="delete from ODS_DA_DOCUMENT_ANALYSIS where F_OP_YEAR ='"+ year + "' and F_OP_MONTH = '"+ month+"'";
			
			int deleteCount = dao.delete(delete);
			log.info("单据退回分析DELETE：{}" , delete);
			log.info("单据退回分析DELTETE条数：{}" , deleteCount);
			
			do {
				StringBuilder buffer = new StringBuilder();
				JSONObject token = utils.getToken(utils.getParmMap());
				if(token.get(
						CommonConstant.ACCESSTOKEN)!=null 
						&& !"".equals(token.get(CommonConstant.ACCESSTOKEN))) {
					
					// JSONObject date = StringUtil.getNewDate();
					buffer.append(properties.getProperties("djth"));
					buffer.append("?access_token=");
					buffer.append(token.get("access_token"));
					buffer.append("&offset=");
					buffer.append(total);
					buffer.append("&year=");
					buffer.append(year);
					buffer.append("&month=");
					buffer.append(month);
					String string = buffer.toString();
					log.info("单据退回分析明细 请求URL=={}" , string);
					
				}
				JSONObject datas = utils.getDataByToken(buffer.toString());
				if("-1".equals(String.valueOf(datas.get("result")))) {
					errorCount ++;
					continue;
				}
				if("200".equals(String.valueOf(datas.get("resultcode")))) {
					List<OdsDaDocumentAnalysis> list = StringToDate(datas.get("records"));
					total += Integer.parseInt(datas.get("total").toString());
					count = Integer.parseInt(datas.get("total").toString()); 
					boolean batch=false; 
					if(!list.isEmpty()) {
						batch = saveBatch(list,count);
					}
					if(!batch) {
						log.error("单据退回分析明细 数据湖取数保存失败！报错条数为{}",total);
						json.put(STATUS,"0");
						return  json;
					}
					if(count>-1 && count<1000) {
						
						flag=false;
					}
					log.info("当前数量为{}",total);
					
					if(errorCount >200) break;
					log.info("error time ====={}",errorCount);
				}else {
					errorCount++;
				}
			}while(flag);
			json.put(STATUS, 200);
		} catch (Exception e) {
			log.error("单据退回分析明细 数据湖取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
		
	}
	
	
	@Override
	public JSONObject saveList(){
		
		boolean flag =true;
		Integer total=0;
		Integer count=0;
		int errorCount = 0;
		HttpUtils utils = new  HttpUtils();
		JSONObject json = new JSONObject();
		try {
			
			JSONObject date = StringUtil.getNewDate();
			// String year = StringUtil.getYesYear();
			String year = (String)date.getString("year");
			String month = (String)date.getString("month");

			
			String delete ="delete from ODS_DA_DOCUMENT_ANALYSIS where F_OP_YEAR ='"+ year + "' and F_OP_MONTH = '"+ month+"'";
			
			int deleteCount = dao.delete(delete);
			log.info("单据退回分析DELETE：{}" , delete);
			log.info("单据退回分析DELTETE条数：{}" , deleteCount);
			
			do {
				StringBuilder buffer = new StringBuilder();
				JSONObject token = utils.getToken(utils.getParmMap());
				if(token.get("access_token")!=null && !"".equals(token.get("access_token"))) {
					
					// JSONObject date = StringUtil.getNewDate();
					buffer.append(properties.getProperties("djth"));
					buffer.append("?access_token=");
					buffer.append(token.get("access_token"));
					buffer.append("&offset=");
					buffer.append(total);
					buffer.append("&year=");
					buffer.append(year);
					buffer.append("&month=");
					buffer.append(month);
					
					 String string = buffer.toString();
					log.info("单据退回分析明细 请求URL=={}" , string);
					
				}
				JSONObject datas = utils.getDataByToken(buffer.toString());
				if("-1".equals(String.valueOf(datas.get("result")))) {
					errorCount ++;
					continue;
				}
				if("200".equals(String.valueOf(datas.get("resultcode")))) {
					List<OdsDaDocumentAnalysis> list = StringToDate(datas.get("records"));
					total += Integer.parseInt(datas.get("total").toString());
					count = Integer.parseInt(datas.get("total").toString()); 
					boolean batch=false; 
					if(list.size()>0) {
						batch = saveBatch(list,count);
					}
					if(!batch) {
						log.error("单据退回分析明细 数据湖取数保存失败！报错条数为{}",total);
						json.put(STATUS,"0");
						return  json;
					}
					if(count>-1 && count<1000) {
						
						flag=false;
					}
					log.info("当前数量为{}",total);
					
					if(errorCount >200) break;
					log.info("error time ====={}",errorCount);
				}else {
					errorCount++;
				}
			}while(flag);
			json.put(STATUS, 200);
		} catch (Exception e) {
			log.error("单据退回分析明细 数据湖取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
	}
	public List<OdsDaDocumentAnalysis>  StringToDate(Object object) {
		
		JSONArray array = JSONArray.parseArray(object.toString());
		ArrayList list = new ArrayList();
		int j = 0; 
		
		for (int i = 0; i < array.size(); i++) {
			// log.info("单据退回分析明细 i===:" + array.get(i).toString());
			OdsDaDocumentAnalysis obj = JSONObject.parseObject(array.get(i).toString(),OdsDaDocumentAnalysis.class);
			
			if("重庆".equals(obj.getF_dqmc())
					|| "重庆地区".equals(obj.getF_dqmc())
					|| "重庆地区贵阳事业部".equals(obj.getF_dqmc())) {
				j = j + 1;
			}
			list.add(obj);
		}
		
		log.info("重庆========={}" ,j);
		
		
		return list;
	}
}
