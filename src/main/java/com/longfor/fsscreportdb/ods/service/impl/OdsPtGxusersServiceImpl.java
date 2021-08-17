package com.longfor.fsscreportdb.ods.service.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.constant.CommonConstant;
import com.longfor.fsscreportdb.ods.entity.OdsPtGxusers;
import com.longfor.fsscreportdb.ods.mapper.OdsPtGxusersMapper;
import com.longfor.fsscreportdb.ods.service.IOdsPtGxusersService;
import com.longfor.fsscreportdb.utils.GetProperties;
import com.longfor.fsscreportdb.utils.HttpUtils;

/**
 * <p>
 * 共享所有人员接口 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-18
 */
@Service
@SuppressWarnings("all")
public class OdsPtGxusersServiceImpl extends ServiceImpl<OdsPtGxusersMapper, OdsPtGxusers> implements IOdsPtGxusersService {

    private final Logger log = LoggerFactory.getLogger(OdsPtGxusersServiceImpl.class);
    
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
			// ODS_PT_GXUSERS
			String delete ="delete from ODS_PT_GXUSERS" ;
			int deleteCount = dao.delete(delete);
			log.info("共享所有人员接口DELETE：{}" , delete);
			log.info("共享所有人员接口DELTETE条数：{}" , deleteCount);
			
			do {
				StringBuilder buffer = new StringBuilder();
				JSONObject token = utils.getToken(utils.getParmMap());
				if(token.get(CommonConstant.ACCESSTOKEN)!=null 
						&& !"".equals(token.get(CommonConstant.ACCESSTOKEN))) {
					buffer.append(properties.getProperties("user"));
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
					Collection<OdsPtGxusers> object = (Collection<OdsPtGxusers>) datas.get("records");
					total += Integer.parseInt(datas.get("total").toString());
					count = Integer.parseInt(datas.get("total").toString());
					boolean batch = true;
					if(!object.isEmpty()) {
						batch = saveBatch(object,count);
					}
					if(!batch) {
						log.error("共享所有人员接口数据湖取数保存失败！报错条数为{}",total);
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
			log.error("共享所有人员接口数据湖取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
	}

}
