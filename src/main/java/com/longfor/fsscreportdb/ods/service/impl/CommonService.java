package com.longfor.fsscreportdb.ods.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.constant.CommonConstant;
import com.longfor.fsscreportdb.utils.GetProperties;
import com.longfor.fsscreportdb.utils.HttpUtils;

@Service
public class CommonService {
	

	public JSONObject getRemotePalameter(String url, int total) {
		
		HttpUtils utils = new  HttpUtils();
		
		GetProperties properties = new GetProperties();
		
		StringBuilder builder = new StringBuilder();
		JSONObject token = utils.getToken(utils.getParmMap());
		if(token.get(CommonConstant.ACCESSTOKEN)!=null 
				&& !"".equals(token.get(CommonConstant.ACCESSTOKEN))) {
			builder.append(properties.getProperties("xycq"));
			builder.append("?access_token=");
			builder.append(token.get(CommonConstant.ACCESSTOKEN));
			builder.append("&offset=");
			builder.append(total);
			
		}
		
		return utils.getDataByToken(builder.toString());
	}	
	
}
