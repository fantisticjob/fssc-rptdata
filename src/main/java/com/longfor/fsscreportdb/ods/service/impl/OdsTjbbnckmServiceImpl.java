package com.longfor.fsscreportdb.ods.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.constant.CommonConstant;
import com.longfor.fsscreportdb.ods.entity.OdsHzTjbbnckm;
import com.longfor.fsscreportdb.ods.mapper.OdsTjbbnckmMapper;
import com.longfor.fsscreportdb.ods.service.IOdsTjbbnckmService;
import com.longfor.fsscreportdb.utils.GetProperties;
import com.longfor.fsscreportdb.utils.HttpUtils;
/**
 * <p>
 *  统计报表NC科目表服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-06
 */
@Service
@SuppressWarnings("all")
public class OdsTjbbnckmServiceImpl extends ServiceImpl<OdsTjbbnckmMapper, OdsHzTjbbnckm> implements IOdsTjbbnckmService {

    private final Logger log = LoggerFactory.getLogger(OdsTjbbnckmServiceImpl.class);
    
	private static final  String STATUS="status";

	private  GetProperties properties = new GetProperties();
	
	
	@Override
	public JSONObject saveList(){
		
		boolean flag =true;
		Integer total=0;
		Integer count=0;
		int errorCount = 0;
		HttpUtils utils = new  HttpUtils();
		JSONObject json = new JSONObject();
		try {
			do {
				StringBuilder buffer = new StringBuilder();
				JSONObject token = utils.getToken(utils.getParmMap());
				if(token.get(CommonConstant.ACCESSTOKEN)!=null 
						&& !"".equals(token.get(CommonConstant.ACCESSTOKEN))) {
					buffer.append(properties.getProperties("ncdata"));
					buffer.append("?access_token=");
					buffer.append(token.get(CommonConstant.ACCESSTOKEN));
					buffer.append("&offset=");
					buffer.append(total);
				}
				
				JSONObject datas = utils.getDataByToken(buffer.toString());
				if("-1".equals(String.valueOf(datas.get("result")))) {
					errorCount ++;
					continue;
				}
				if("200".equals(String.valueOf(datas.get("resultcode")))) {
					Collection<OdsHzTjbbnckm> object =StringToDate( datas.get("records"));
					total += Integer.parseInt(datas.get("total").toString());
					count = Integer.parseInt(datas.get("total").toString());
					boolean batch = true;
					if(!object.isEmpty()) {
						batch = saveBatch(object,count);
					}
					if(!batch) {
						log.error("统计报表NC科目表数据湖取数保存失败！报错条数为{}",total);
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
			log.error("统计报表NC科目表数据湖取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
	}
	public List<OdsHzTjbbnckm>  StringToDate(Object object) {
		JSONArray array = JSONArray.parseArray(object.toString());
		ArrayList<OdsHzTjbbnckm> list = new ArrayList<OdsHzTjbbnckm>();
		for (int i = 0; i < array.size(); i++) {
			OdsHzTjbbnckm obj = JSONObject.parseObject(array.get(i).toString(),OdsHzTjbbnckm.class);
			list.add(obj);
		}
		return list;
	}
	
}
