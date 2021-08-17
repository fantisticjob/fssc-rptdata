package com.longfor.fsscreportdb.ods.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.constant.CommonConstant;
import com.longfor.fsscreportdb.ods.entity.OdsCtCostDetailHistory;
import com.longfor.fsscreportdb.ods.mapper.OdsCtCostDetailHistoryMapper;
import com.longfor.fsscreportdb.ods.service.IOdsCtCostDetailHistoryService;
import com.longfor.fsscreportdb.utils.GetProperties;
import com.longfor.fsscreportdb.utils.HttpUtils;
/**
 * <p>
 * ods层成本明细历史表历史表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-08
 */
@Service
@SuppressWarnings("all")
public class OdsCtCostDetailHistoryServiceImpl extends ServiceImpl<OdsCtCostDetailHistoryMapper, OdsCtCostDetailHistory> implements IOdsCtCostDetailHistoryService {
	
	@Autowired
	private CommonDao dao;
	
	private static final  String STATUS="status";
	
    private final Logger log = LoggerFactory.getLogger(OdsCtCostDetailHistoryServiceImpl.class);

	private  GetProperties properties = new GetProperties();
	
	
	@Override
	public JSONObject saveList(Map<String,Object> map){
		
		boolean flag =true;
		Integer total=0;
		Integer count=0;
		int errorCount = 0;
		HttpUtils utils = new  HttpUtils();
		JSONObject json = new JSONObject();
		
		try {
			String yearMonth = map.get("year")+"-"+ map.get("month");
			String delete ="delete from ODS_CT_COST_DETAIL_HISTORY where DATEKEY='"+yearMonth+"'" ;
			//String delete ="delete from ODS_CT_COST_DETAIL_HISTORY";
			int deleteCount = dao.delete(delete);
			log.info("成本明细历史表DELTETE条数：{}" , deleteCount);
			
			do {
				StringBuilder buffer = new StringBuilder();
				JSONObject token = utils.getToken(utils.getParmMap());
				if(token.get(CommonConstant.ACCESSTOKEN)!=null 
						&& !"".equals(token.get(CommonConstant.ACCESSTOKEN))) {
					
					// JSONObject date = StringUtil.getNewDate();
					buffer.append(properties.getProperties("cbtz"));
					buffer.append("?access_token=");
					buffer.append(token.get(CommonConstant.ACCESSTOKEN));
					buffer.append("&offset=");
					buffer.append(total);
					buffer.append("&yearMonth=");
					buffer.append(yearMonth);
				}
				JSONObject datas = utils.getDataByToken(buffer.toString());
				if("-1".equals(String.valueOf(datas.get("result")))) {
					errorCount ++;
					continue;
				}
				if("200".equals(String.valueOf(datas.get("resultcode")))) {
					
					List<OdsCtCostDetailHistory> list = StringToDate(datas.get("records"));
					total += Integer.parseInt(datas.get("total").toString());
					count = Integer.parseInt(datas.get("total").toString());  
					boolean batch = true;
					if(!list.isEmpty()) {
						batch = saveBatch(list,count);
					}
					if(!batch) {
						log.error("成本明细历史表表数据湖取数保存失败！报错条数为{}",total);
						json.put(STATUS,"0");
						return  json;
					}
					if(count>-1 && count<1000) {
						
						flag=false;
					}
					log.info("当前数量为{}",total);
					
					if(errorCount > 1000) break;
					log.info("error time ====={}",errorCount);
				}else {
					errorCount++;
				}
			}while(flag);
			json.put(STATUS, 200);
		} catch (Exception e) {
			log.error("成本明细历史表表数据湖取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
	}
	public List<OdsCtCostDetailHistory>  StringToDate(Object object) {
		JSONArray array = JSONArray.parseArray(object.toString());
		ArrayList<OdsCtCostDetailHistory> list = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			OdsCtCostDetailHistory obj = JSONObject.parseObject(array.get(i).toString(),OdsCtCostDetailHistory.class);
			list.add(obj);
		}
		return list;
	}
}
