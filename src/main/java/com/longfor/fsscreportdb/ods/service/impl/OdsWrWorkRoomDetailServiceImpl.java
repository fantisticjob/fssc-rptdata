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
import com.longfor.fsscreportdb.constant.CommonConstant;
import com.longfor.fsscreportdb.ods.entity.OdsWrWorkRoomDetail;
import com.longfor.fsscreportdb.ods.mapper.OdsWrWorkRoomDetailMapper;
import com.longfor.fsscreportdb.ods.service.IOdsWrWorkRoomDetailService;
import com.longfor.fsscreportdb.utils.GetProperties;
import com.longfor.fsscreportdb.utils.HttpUtils;

/**
 * <p>
 * ods层工抵房明细表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-08
 */
@Service
@SuppressWarnings("all")
public class OdsWrWorkRoomDetailServiceImpl extends ServiceImpl<OdsWrWorkRoomDetailMapper, OdsWrWorkRoomDetail> implements IOdsWrWorkRoomDetailService {
	
	@Autowired
	private CommonDao dao;
	
	private static final  String STATUS="status";

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
		try {
			
			String delete ="delete from ODS_WR_WORK_ROOM_DETAIL";
			int deleteCount = dao.delete(delete);
			log.info("工抵房明细表DELTETE条数为：{}" , deleteCount);
			
			do {
				StringBuilder buffer = new StringBuilder();
				JSONObject token = utils.getToken(utils.getParmMap());
				if(token.get(CommonConstant.ACCESSTOKEN)!=null 
						&& !"".equals(token.get(CommonConstant.ACCESSTOKEN))) {
					buffer.append(properties.getProperties("gdf"));
					buffer.append("?access_token=");
					buffer.append(token.get(CommonConstant.ACCESSTOKEN));
					buffer.append("&offset=");
					buffer.append(total);
				}
				JSONObject datas = utils.getDataByToken(buffer.toString());
				if(CommonConstant.HTTP_ERROR
						.equals(String.valueOf(datas.get(CommonConstant.HTTP_RESULT)))) {
					errorCount ++;
					continue;
				}
				if(CommonConstant.HTTP_OK
						.equals(String.valueOf(datas.get(CommonConstant.HTTP_RESULTCODE)))) {
					
					List<OdsWrWorkRoomDetail> list = StringToDate(datas.get(CommonConstant.HTTP_RECORD));
					total += Integer.parseInt(datas.get(CommonConstant.TOTAL).toString());
					count = Integer.parseInt(datas.get(CommonConstant.TOTAL).toString());  
					boolean batch = true;
					if(!list.isEmpty()) {
						batch = saveBatch(list,count);
					}
					if(!batch) {
						log.error("工抵房明细表数据湖取数保存失败！报错条数为{}",total);
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
			log.error("工抵房明细表数据湖取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
	}
	
	
	
	public List<OdsWrWorkRoomDetail>  StringToDate(Object object) {
		JSONArray array = JSONArray.parseArray( object.toString());
		ArrayList<OdsWrWorkRoomDetail> list = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			Object o = array.get(i);
			if(i<10) {
				log.info("工抵房明细表数据{}", o.toString());
			}
			OdsWrWorkRoomDetail parseObject = JSON.parseObject(o.toString(),OdsWrWorkRoomDetail.class);
			if(i<10) {
				log.info("工抵房明细表数据Itemname{}", parseObject.getItemname());
			}
			// add by zhaoxin ===============20201118============
			list.add(parseObject);

		}
		return list;
	}
	
}
