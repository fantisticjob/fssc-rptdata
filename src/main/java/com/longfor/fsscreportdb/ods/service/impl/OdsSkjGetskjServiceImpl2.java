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
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.constant.CommonConstant;
import com.longfor.fsscreportdb.ods.entity.OdsSkjGetskj;
import com.longfor.fsscreportdb.ods.mapper.OdsSkjGetskjMapper;
import com.longfor.fsscreportdb.ods.service.IOdsSkjGetskjService2;
import com.longfor.fsscreportdb.utils.GetProperties;
import com.longfor.fsscreportdb.utils.HttpUtils;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * 收款机接口 服务实现类 
 * </p>
 * 3月份数据从新取得
 * @author zhaoxin
 * @since 2021-03-22
 */
@Service
public class OdsSkjGetskjServiceImpl2 extends ServiceImpl<OdsSkjGetskjMapper, OdsSkjGetskj> implements IOdsSkjGetskjService2 {
	
    private final Logger log = LoggerFactory.getLogger(OdsSkjGetskjServiceImpl2.class);
    
	private static final  String STATUS="status";

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
		try {
			
			String delsql  = "delete from ODS_SKJ_GETSKJ where "
					+ "(substr(GETDATE,0,7)  = '2021-03' or substr(GETDATE,0,7)  = '2021-02' or substr(GETDATE,0,7)  = '2021-01')";
			int resultInt = dao.delete(delsql);
			log.info("3 月份数据删除 resultInt=={}", resultInt);
			
			String fromDate = "2021-01-01";
			String toDate = StringUtil.getYesterday();
			log.info("fromDate=={}", fromDate);
			log.info("toDate=={}", toDate);
			
			
			do {
				StringBuilder buffer = new StringBuilder();
				JSONObject token = utils.getToken(utils.getParmMap());
				if(token.get(CommonConstant.ACCESSTOKEN)!=null 
						&& !"".equals(token.get(CommonConstant.ACCESSTOKEN))) {
					buffer.append(properties.getProperties("getskj"));
					buffer.append("?access_token=");
					buffer.append(token.get(CommonConstant.ACCESSTOKEN));
					buffer.append("&fromDate=");
					buffer.append(fromDate);
					buffer.append("&toDate=");
					buffer.append(toDate);
					buffer.append("&offset=");
					buffer.append(total);
				}
				
				JSONObject datas = utils.getDataByToken(buffer.toString());
				if("-1".equals(String.valueOf(datas.get("result")))) {
					errorCount ++;
					continue;
				}
				if("200".equals(String.valueOf(datas.get("resultcode")))) {
					Collection<OdsSkjGetskj> object = StringToDate( datas.get("records"));
					total += Integer.parseInt(datas.get("total").toString());
					count = Integer.parseInt(datas.get("total").toString());
					
					log.info("count=={}" , count);
					boolean batch = true;
					if(!object.isEmpty()) {
						batch = saveBatch(object,count);
					}
					if(!batch) {
						log.error("收款机明细接口取数保存失败！报错条数为{}",total);
						json.put(STATUS,"0");
						return  json;
					}
					if(count>-1 && count<1000) {
						
						flag=false;
					}
					log.info("当前数量为{}", total);

					if (errorCount > 200)
						break;
					log.info("error time ====={}", errorCount);
				} else {
					errorCount++;
				}
			}while(flag);
			json.put(STATUS, 200);
		} catch (Exception e) {
			log.error("收款机明细接口取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
	}
	public List<OdsSkjGetskj>  StringToDate(Object object) {
		JSONArray array = JSONArray.parseArray( object.toString());
		log.info("array:{}",array.size());
		
		ArrayList<OdsSkjGetskj> list = new ArrayList<OdsSkjGetskj>();
		for (int i = 0; i < array.size(); i++) {
			Object o = array.get(i);
			OdsSkjGetskj parseObject = JSON.parseObject(o.toString(), OdsSkjGetskj.class);
			String projname = parseObject.getProjname();
			if (StringUtils.isNotBlank(projname)) {
				int indexOf = projname.indexOf("项目");
				projname = projname.substring(0, indexOf + 2);
			}
			parseObject.setProjname(projname);

			if (!"作废".equals(parseObject.getStatus())) {
				list.add(parseObject);
			}
		}
		
		log.info("list:{}",list.size());
		return list;
	}
}
