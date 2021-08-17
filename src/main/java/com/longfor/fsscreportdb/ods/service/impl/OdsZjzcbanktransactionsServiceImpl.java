package com.longfor.fsscreportdb.ods.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.constant.CommonConstant;
import com.longfor.fsscreportdb.ods.entity.OdsZjzcAccountTrade;
import com.longfor.fsscreportdb.ods.mapper.OdsZjzcbanktransactionsMapper;
import com.longfor.fsscreportdb.ods.service.IOdsZjzcbanktransactionsService;
import com.longfor.fsscreportdb.utils.GetProperties;
import com.longfor.fsscreportdb.utils.HttpUtils;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * 资金自查账户交易表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-05
 */
@Service
@SuppressWarnings("all")
public class OdsZjzcbanktransactionsServiceImpl extends ServiceImpl<OdsZjzcbanktransactionsMapper, OdsZjzcAccountTrade> implements IOdsZjzcbanktransactionsService {

	private static final  String STATUS="status";

	@Autowired
	private CommonDao dao;
	
    private final Logger log = LoggerFactory.getLogger(getClass());

	private  GetProperties properties = new GetProperties();
	
	
	
	@Override
	public JSONObject saveList(){
		
		boolean flag =true;
		Integer total=0;
		Integer count=0;
		int errorCount = 0;
		HttpUtils utils = new  HttpUtils();
		JSONObject json = new JSONObject();
		String date=StringUtil.getHalfAYearAgo();
		try {
			
			String sql="select count(*) from ODS_ZJZC_ACCOUNT_TRADE";
			if(dao.selectCount(sql) >0) { //大于0就选昨天日期 否则选半年前的
				date=StringUtil.getYesterday();
			}
			do {
				StringBuilder buffer = new StringBuilder();
				JSONObject token = utils.getToken(utils.getParmMap());
				if(token.get(CommonConstant.ACCESSTOKEN)!=null 
						&& !"".equals(token.get(CommonConstant.ACCESSTOKEN))) {
					buffer.append(properties.getProperties("zjzczhjyb"));
					buffer.append("?access_token=");
					buffer.append(token.get(CommonConstant.ACCESSTOKEN));
					buffer.append("&offset=");
					buffer.append(total);
					buffer.append("&fromDate=");
					buffer.append(date);
					buffer.append("&toDate=");
					buffer.append(StringUtil.getToday());
				}
				
				JSONObject datas = utils.getDataByToken(buffer.toString());
				if(CommonConstant.HTTP_ERROR
						.equals(String.valueOf(datas.get(CommonConstant.HTTP_RESULT)))) {
					errorCount ++;
					continue;
				}
				if(CommonConstant.HTTP_OK
						.equals(String.valueOf(datas.get(CommonConstant.HTTP_RESULTCODE)))) {
					Collection<OdsZjzcAccountTrade> object = StringToDate( datas.get(CommonConstant.HTTP_RECORD));
					total += Integer.parseInt(datas.get(CommonConstant.TOTAL).toString());
					count = Integer.parseInt(datas.get(CommonConstant.TOTAL).toString());
					boolean batch = true;
					if(!object.isEmpty()) {
						batch = saveBatch(object,count);
					}
					if(!batch) {
						log.error(" 资金自查账户交易表据湖取数保存失败！报错条数为{}",total);
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
			log.error("资金自查账户交易表表数据湖取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
	}
	public List<OdsZjzcAccountTrade>  StringToDate(Object object) {
		JSONArray array = JSONArray.parseArray( object.toString());
		ArrayList<OdsZjzcAccountTrade> list = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			Object o = array.get(i);
			OdsZjzcAccountTrade parseObject = JSON.parseObject(o.toString(),OdsZjzcAccountTrade.class);
			list.add(parseObject);
		}
		return list;
	}
}
