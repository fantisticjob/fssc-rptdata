package com.longfor.fsscreportdb.ods.service.impl;

import java.util.ArrayList;
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
import com.longfor.fsscreportdb.ods.entity.OdsZdGxptrepemployee;
import com.longfor.fsscreportdb.ods.mapper.OdsZdGxptrepemployeeMapper;
import com.longfor.fsscreportdb.ods.service.IOdsZdGxptrepemployeeService;
import com.longfor.fsscreportdb.utils.GetProperties;
import com.longfor.fsscreportdb.utils.HttpUtils;

/**
 * <p>
 * 共享平台公司职工数表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2021-03-10
 */
@Service
public class OdsZdGxptrepemployeeServiceImpl extends ServiceImpl<OdsZdGxptrepemployeeMapper, OdsZdGxptrepemployee> implements IOdsZdGxptrepemployeeService {

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
		try {
			//根据条件删除
			String delete ="delete from (select t.*,rowid from ods_zd_gxptrepemployee t where substr(t.load_date  ,0 ,7)=to_char(sysdate-1,'yyyy-mm'))";
			
			int deleteCount = dao.delete(delete);
			log.info("共享平台公司职工数表DELETE：{}" , delete);
			log.info("共享平台公司职工数表DELTETE条数：{}" , deleteCount);
			
			do {
				StringBuilder buffer = new StringBuilder();
				JSONObject token = utils.getToken(utils.getParmMap());
				if(token.get("access_token")!=null && !"".equals(token.get("access_token"))) {
					buffer.append(properties.getProperties("gxry"));
					buffer.append("?access_token=");
					buffer.append(token.get("access_token"));
					buffer.append("&offset=");
					buffer.append(total);
				}
				
				JSONObject datas = utils.getDataByToken(buffer.toString());
				if("-1".equals(String.valueOf(datas.get("result")))) {
					errorCount ++;
					continue;
				}
				if("200".equals(String.valueOf(datas.get("resultcode")))) {
					List<OdsZdGxptrepemployee> object = StringToDate(datas.get("records"));
					total += Integer.parseInt(datas.get("total").toString());
					count = Integer.parseInt(datas.get("total").toString());
					boolean batch = true;
					if(!object.isEmpty()) {
						batch = saveBatch(object,count);
					}
					if(!batch) {
						log.error("共享平台公司职工数表数据湖取数保存失败！报错条数为{}",total);
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
			
			log.error("共享平台公司职工数表数据湖取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
	}
	public List<OdsZdGxptrepemployee>  StringToDate(Object object) {
		JSONArray array = JSONArray.parseArray( object.toString());
		ArrayList<OdsZdGxptrepemployee> list = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			Object o = array.get(i);
			OdsZdGxptrepemployee parseObject = JSON.parseObject(o.toString(),OdsZdGxptrepemployee.class);
			list.add(parseObject);
		}
		return list;
	}
	
}
