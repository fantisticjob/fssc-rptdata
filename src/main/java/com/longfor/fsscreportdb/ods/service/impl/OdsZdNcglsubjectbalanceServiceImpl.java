package com.longfor.fsscreportdb.ods.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.constant.CommonConstant;
import com.longfor.fsscreportdb.ods.entity.OdsZdNcglsubjectbalance;
import com.longfor.fsscreportdb.ods.mapper.OdsZdNcglsubjectbalanceMapper;
import com.longfor.fsscreportdb.ods.service.IOdsZdNcglsubjectbalanceService;
import com.longfor.fsscreportdb.utils.GetProperties;
import com.longfor.fsscreportdb.utils.HttpUtils;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * NC科目数据 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-11
 */
@Service
@SuppressWarnings("all")
public class OdsZdNcglsubjectbalanceServiceImpl extends ServiceImpl<OdsZdNcglsubjectbalanceMapper, OdsZdNcglsubjectbalance> implements IOdsZdNcglsubjectbalanceService {
	
	private static final  String STATUS="status";

    private final Logger log = LoggerFactory.getLogger(getClass());

	private  GetProperties properties = new GetProperties();
	
	
	@Override
	public JSONObject saveList(String year, String month){
		
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
					
					if(year==null && month==null) {
						JSONObject date = StringUtil.getNewDate();
						String yesMonth = StringUtil.getYesMonth();
						year = date.getString("year");
						month = yesMonth;
					}
					
					buffer.append(properties.getProperties("ncglsubjectbalance"));
					buffer.append("?access_token=");
					buffer.append(token.get(CommonConstant.ACCESSTOKEN));
					buffer.append("&offset=");
					buffer.append(total);
					buffer.append("&datekey=");
					buffer.append(year+"-"+month);

				}
				
				JSONObject datas = utils.getDataByToken(buffer.toString());
				if(CommonConstant.HTTP_ERROR
						.equals(String.valueOf(datas.get(CommonConstant.HTTP_RESULT)))) {
					errorCount ++;
					continue;
				}
				if(CommonConstant.HTTP_OK
						.equals(String.valueOf(datas.get(CommonConstant.HTTP_RESULTCODE)))) {
					
					List<OdsZdNcglsubjectbalance> object = StringToDate( datas.get(CommonConstant.HTTP_RECORD));
					total += Integer.parseInt(datas.get(CommonConstant.TOTAL).toString());
					count = Integer.parseInt(datas.get(CommonConstant.TOTAL).toString());
					boolean batch = true;
					if(object.size()>0) {
						batch = saveBatch(object,count);
					}
					if(!batch) {
						log.error("增值税纳税申报表主表数据湖取数保存失败！报错条数为{}",total);
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
			log.error("增值税纳税申报表主表数据湖取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
	}
	
	public List<OdsZdNcglsubjectbalance>  StringToDate(Object object) {
		JSONArray array = JSONArray.parseArray( object.toString());
		ArrayList<OdsZdNcglsubjectbalance> list = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			Object o = array.get(i);
			OdsZdNcglsubjectbalance parseObject = JSON.parseObject(o.toString(),OdsZdNcglsubjectbalance.class);
			list.add(parseObject);
		}
		return list;
	}
}
