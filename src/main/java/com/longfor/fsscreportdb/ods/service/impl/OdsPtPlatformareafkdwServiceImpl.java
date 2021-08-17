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
import com.longfor.fsscreportdb.ods.entity.OdsPtPlatformareafkdw;
import com.longfor.fsscreportdb.ods.mapper.OdsPtPlatformareafkdwMapper;
import com.longfor.fsscreportdb.ods.service.IOdsPtPlatformareafkdwService;
import com.longfor.fsscreportdb.utils.GetProperties;
import com.longfor.fsscreportdb.utils.HttpUtils;

/**
 * <p>
 * 平台地区付款单位 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-13
 */
@Service
@SuppressWarnings("all")
public class OdsPtPlatformareafkdwServiceImpl extends ServiceImpl<OdsPtPlatformareafkdwMapper, OdsPtPlatformareafkdw> implements IOdsPtPlatformareafkdwService {

    private final Logger log = LoggerFactory.getLogger(OdsPtPlatformareafkdwServiceImpl.class);
    
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
			// 平台地区付款单位现有数据-删除
			String delete = "DELETE from ODS_PT_PLATFORMAREAFKDW";
			int deleteCount = dao.delete(delete);
			log.info("平台地区付款单位现有数据-删除DELETE：{}" , delete);
			log.info("平台地区付款单位现有数据-删除DELTETE条数：{}" , deleteCount);
			
			do {
				StringBuilder buffer = new StringBuilder();
				JSONObject token = utils.getToken(utils.getParmMap());
				if(token.get(CommonConstant.ACCESSTOKEN)!=null 
						&& !"".equals(token.get(CommonConstant.ACCESSTOKEN))) {
					buffer.append(properties.getProperties("platformareafkdw"));
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
					
					Collection<OdsPtPlatformareafkdw> object = (Collection<OdsPtPlatformareafkdw>) datas.get("records");
					
					total += Integer.parseInt(datas.get("total").toString());
					count = Integer.parseInt(datas.get("total").toString());
					boolean batch = true;
					if(!object.isEmpty()) {
						batch = saveBatch(object,count);
					}
					if(!batch) {
						log.error("平台地区付款单位数据湖取数保存失败！报错条数为{}",total);
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
			log.error("平台地区付款单位数据湖取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
	}

}
