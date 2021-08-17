package com.longfor.fsscreportdb.ods.nc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.ods.nc.entity.NcTask;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcAuxiliaryBalance;
import com.longfor.fsscreportdb.ods.nc.mapper.OdsNcAuxiliaryBalanceMapper;
import com.longfor.fsscreportdb.ods.nc.service.INcTaskService;
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcAuxiliaryBalanceService_s;
import com.longfor.fsscreportdb.utils.NCUtils;
import com.longfor.fsscreportdb.utils.RemoteCallUtil2;
import com.longfor.fsscreportdb.utils.RemoteCallUtil3;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * NC外系统查询辅助余额表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-10
 */
@Service
@SuppressWarnings("all")
public class OdsNcAuxiliaryBalanceServiceImpl_s extends ServiceImpl<OdsNcAuxiliaryBalanceMapper, OdsNcAuxiliaryBalance> implements IOdsNcAuxiliaryBalanceService_s {
	
	private  final   Logger log = LoggerFactory.getLogger(RemoteCallUtil2.class);
	
	@Autowired
	private INcTaskService ncTaskService;
	
	@Override
	public JSONObject saveList( List<String> kemuCodeList) {
		
		JSONObject result = new JSONObject();
		try {
			RemoteCallUtil2 utils = new RemoteCallUtil2();
			
			JSONObject json = new JSONObject();
			String uuid = StringUtil.getUUID();
			
			boolean flag=false;
			for(int j = 0; j<kemuCodeList.size(); j++) {
			
				NcTask nc = new NcTask();
				nc.setAccountId(null);
				nc.setBatchnumber(uuid + "_" + j);
				
				nc.setBegintime(new Date());
				nc.setOpraterFlag("1");
				nc.setCompanyName(kemuCodeList.get(j));
				flag = ncTaskService.save(nc);
				
				Map<String, String> map = setXml( kemuCodeList.get(j),uuid+j);
				log.info("kemuCode==" + kemuCodeList.get(j));
				map.put("type","FRPlugin");
				json = utils.sendSyncRequest(map);
			}
			if(flag) {
				for (int i = 0; i < kemuCodeList.size(); i++) {
					QueryWrapper<NcTask> queryWrapper = new QueryWrapper<NcTask>();
					queryWrapper.eq("BATCHNUMBER", uuid);
					NcTask one = ncTaskService.getOne(queryWrapper);
					if(one.getEndtimeFr()!=null ) {
						result.put("status", "200");
						result.put("data", uuid);
						result.put("result", "nc取数成功！");
						break;
					}
					Thread.sleep(1000);
				}
			}else {
				result.put("status", "0");
			}
		
		} catch (Exception e) {
			log.error("NC外系统查询辅助余额表调用异常"+e);
			result.put("status", "-2");
			e.printStackTrace();
		}

		return  result;
	}
	
	
	
	@Override
	public List<String> saveList( 
			String uuid,
			List<String> kemuCodeList, 
			List<String> yearList, 
			List<String> monthList) {
		
		log.info("yearList==========" + yearList.size());
		
		// JSONObject result = new JSONObject();
		List<String> result = new ArrayList<String>();
		
		try {
			RemoteCallUtil3 utils = new RemoteCallUtil3();
			
			JSONObject json = new JSONObject();
			
			boolean flag=false;
			for(int i=0; i<yearList.size(); i++) {
					for(int j = 0; j<kemuCodeList.size(); j++) {
				
					String uuidsend = uuid + "_" + i +  "_" + j;
					log.info("uuidsend==========" + uuidsend);
					result.add(uuidsend);
					NcTask nc = new NcTask();
					nc.setAccountId(null);
					nc.setBatchnumber(uuidsend);
					nc.setBegintime(new Date());
					nc.setOpraterFlag("2");
					nc.setCompanyName(kemuCodeList.get(j));
					String yearMonth = "";
					yearMonth = yearMonth + yearList.get(i);
					yearMonth = yearMonth + ",";
					yearMonth = yearMonth + monthList.get(i);
					nc.setYearmonthEnd(yearMonth);
					flag = ncTaskService.save(nc);
					
					log.info("kemuCode==" + kemuCodeList.get(j));
					Map<String, String> map = setXml( kemuCodeList.get(j), uuidsend, yearList.get(i), monthList.get(i));
					map.put("type","FRPlugin");
					
					json = utils.sendSyncRequest(map);
				}	
			}
//			if(flag) {
//				for (int i = 0; i < kemuCodeList.size(); i++) {
//					QueryWrapper<NcTask> queryWrapper = new QueryWrapper<NcTask>();
//					queryWrapper.eq("BATCHNUMBER", uuid);
//					NcTask one = ncTaskService.getOne(queryWrapper);
//					if(one.getEndtimeFr()!=null ) {
//						result.put("status", "200");
//						result.put("data", uuid);
//						result.put("result", "nc取数成功！");
//						break;
//					}
//					Thread.sleep(1000);
//				}
//			}else {
//				result.put("status", "0");
//			}
		
		} catch (Exception e) {
			log.error("NC外系统查询辅助余额表调用异常"+e);
			// result.put("status", "-2");
			e.printStackTrace();
		}

		return  result;
	}
	
	private Map<String,String> setXml( String kemuCode, String uuid){
		
		Map<String,String> map = new HashMap<String, String>();
		
		String xml="<ilh:FRPlugin>"
				+ "					<![CDATA["
				+ "	<FRPlugin> " 
				+"<year>2020</year>" + 
				"         <month>09</month>" + 
				"         <accountcode>"+kemuCode+"</accountcode>" + 
				"         <orgcode>all</orgcode>" + 
				"         <FZX>YHZH</FZX><FZX>RY</FZX>" + 
				"         <batchnumber>"+uuid+"</batchnumber>"+
				"</FRPlugin>"
				+ "]]>"+
				"	</ilh:FRPlugin>";
		map.put("xml",xml);
		return map;
	}
	
	
	private Map<String,String> setXml(String kemuCode, String uuid, String year, String month){
		
		Map<String,String> map = new HashMap<String, String>();
		Map<String,String> kumuFuzhu = NCUtils.initKemuFuzhuN();
		
		String xml="<ilh:FRPlugin>"
				+"<year>"+year+"</year>" + 
				"         <month>" + month+"</month>" + 
				"         <accountcode>"+kemuCode+"</accountcode>" + 
				"         <orgcode>All</orgcode>" + 
				"         "+ kumuFuzhu.get(kemuCode) + 
				"         <batchnumber>"+uuid+"</batchnumber>"+
				"	</ilh:FRPlugin>";
		map.put("xml",xml);
		return map;
	}
}
