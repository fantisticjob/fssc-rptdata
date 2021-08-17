package com.longfor.fsscreportdb.ods.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.longfor.fsscreportdb.ods.entity.OdsZjzcAccountBalance;
import com.longfor.fsscreportdb.ods.mapper.OdsZjzcbankbalancesMapper;
import com.longfor.fsscreportdb.ods.service.IOdsZjzcbankbalancesService;
import com.longfor.fsscreportdb.utils.GetProperties;
import com.longfor.fsscreportdb.utils.HttpUtils;
import com.longfor.fsscreportdb.utils.StringUtil;
/**
 * <p>
 * 资金自查账户余额表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-05
 */
@Service
@SuppressWarnings("all")
	
public class OdsZjzcbankbalancesServiceImpl extends ServiceImpl<OdsZjzcbankbalancesMapper, OdsZjzcAccountBalance> implements IOdsZjzcbankbalancesService {
	
	private static final  String STATUS="status";

    private final Logger log = LoggerFactory.getLogger(getClass());

	private  GetProperties properties = new GetProperties();
	@Autowired
	private CommonDao dao;
	
	@Override
	public JSONObject saveList(){
		
		boolean flag =true;
		Integer total=0;
		Integer count=0;
		int errorCount = 0;
		HttpUtils utils = new  HttpUtils();
		JSONObject json = new JSONObject();
		String date=StringUtil.getHalfAYearAgo();
		String sql="select count(*) from ODS_ZJZC_ACCOUNT_BALANCE";
		if(dao.selectCount(sql) >0) { //大于0就选昨天日期 否则选半年前的
			date=StringUtil.getYesterday();
		}
		try {
			do {
				StringBuilder buffer = new StringBuilder();
				JSONObject token = utils.getToken(utils.getParmMap());
				if(token.get(CommonConstant.ACCESSTOKEN)!=null 
						&& !"".equals(token.get(CommonConstant.ACCESSTOKEN))) {
					buffer.append(properties.getProperties("zjzcyeb"));
					buffer.append("?access_token=");
					buffer.append(token.get(CommonConstant.ACCESSTOKEN));
					buffer.append("&offset=");
					buffer.append(total);
					buffer.append("&fromdate=");
					buffer.append(date);
					buffer.append("&todate=");
					buffer.append(StringUtil.getToday());
					
				}
				JSONObject datas = utils.getDataByToken(buffer.toString());
				if("-1".equals(String.valueOf(datas.get("result")))) {
					errorCount ++;
					continue;
				}
				if("200".equals(String.valueOf(datas.get("resultcode")))) {
					List<OdsZjzcAccountBalance> object = StringToDate( datas.get("records"));
					total += Integer.parseInt(datas.get("total").toString());
					count = Integer.parseInt(datas.get("total").toString());
					boolean batch = true;
					if(!object.isEmpty()) {
						batch = saveBatch(object,count);
					}
					if(!batch) {
						log.error("资金自查账户余额表数据湖取数保存失败！报错条数为{}",total);
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
			log.error("资金自查账户余额表数据湖取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
	}
	public List<OdsZjzcAccountBalance>  StringToDate(Object object) {
		JSONArray array = JSONArray.parseArray( object.toString());
		ArrayList<OdsZjzcAccountBalance> list = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			Object o = array.get(i);
			OdsZjzcAccountBalance parseObject = JSON.parseObject(o.toString(),OdsZjzcAccountBalance.class);
			String zhtrancount = parseObject.getZhtrancount();
			if(StringUtils.isBlank(zhtrancount)) {
				zhtrancount="0";
				parseObject.setZhtrancount(zhtrancount);
			}
			list.add(parseObject);
		}
		return list;
	}
}
