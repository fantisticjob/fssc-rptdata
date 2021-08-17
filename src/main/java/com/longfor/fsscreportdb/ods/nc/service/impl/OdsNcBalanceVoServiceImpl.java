package com.longfor.fsscreportdb.ods.nc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcAuxiliaryBalance;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcBalanceVo;
import com.longfor.fsscreportdb.ods.nc.mapper.OdsNcBalanceVoMapper;
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcBalanceVoService;
import com.longfor.fsscreportdb.utils.RemoteCallUtil2;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * NC外系统查询科目余额表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-09
 */
@SuppressWarnings("all")
@Service("OdsBalanceVoServiceImpl")
public class OdsNcBalanceVoServiceImpl extends ServiceImpl<OdsNcBalanceVoMapper, OdsNcBalanceVo> implements IOdsNcBalanceVoService {

	private  final static   Logger log = LoggerFactory.getLogger(RemoteCallUtil2.class);

	@Autowired
	private CommonDao dao;
	
	
	@Override
	public JSONObject saveList(List<String> orgCodeList,  List<String> kemuCodeList) {
		
		JSONObject result = new JSONObject();
		try {
			RemoteCallUtil2 utils = new RemoteCallUtil2();
			
			String year = StringUtil.getYear();
			String lastMonth = StringUtil.getLastMonth();
			for(int i=0 ; i<orgCodeList.size(); i++) {
				log.info("orgCode_COUNT============" + i);
				
				for(int j = 0; j<kemuCodeList.size(); j++) {
					Map<String, String> map = setXml(orgCodeList.get(i), kemuCodeList.get(j),year,lastMonth);
					map.put("type","Balance");
					JSONObject json = utils.sendSyncRequest(map);
					log.info("orgCode==" + orgCodeList.get(i));
					log.info("kemuCode==" + kemuCodeList.get(j));
					
					if(json.get("data") !=null ) {
						List<JSONObject> object = (List<JSONObject>) json.get("data");
						ArrayList<OdsNcBalanceVo> list = new ArrayList<OdsNcBalanceVo>();
						for (int k= 0; k < object.size(); k++) {
							OdsNcBalanceVo vo = JSONObject.toJavaObject(object.get(k), OdsNcBalanceVo.class);
							vo.setEndyear(year);
							vo.setEndmonth(lastMonth);
							if(!"总计".equals(vo.getAccountcode())) {
								list.add(vo);
							}
						}
						this.saveBatch(list);
					}
					
				}
			}
			
			result.put("status", "200");
			
		} catch (Exception e) {
			log.error("NC外系统查询科目余额表调用异常"+e);
			result.put("status", "-2");
			e.printStackTrace();
		}
		
		return  result;
	}
	
	
	@Override
	public JSONObject saveList(List<String> orgCodeList,  List<String> kemuCodeList,
			String beginYear, 
			String beginMonth, 
			String endYear, 
			String endMonth) {
		
		JSONObject result = new JSONObject();
		try {
			RemoteCallUtil2 utils = new RemoteCallUtil2();
			
//			StringBuilder sb = new StringBuilder();
//			sb.append("delete from ");
			
			
			for(int i=0 ; i<orgCodeList.size(); i++) {
				log.info("orgCode_COUNT============" + i);
				
				for(int j = 0; j<kemuCodeList.size(); j++) {
					// Map<String, String> map = setXml(orgCodeList.get(i), kemuCodeList.get(j));
					Map<String, String> map = setXml(orgCodeList.get(i), kemuCodeList.get(j),
							 beginYear, 
							 beginMonth, 
							 endYear, 
							 endMonth);
					
					map.put("type","Balance");
					JSONObject json = utils.sendSyncRequest(map);
					log.info("orgCode==" + orgCodeList.get(i));
					log.info("kemuCode==" + kemuCodeList.get(j));
					
					if(json.get("data") !=null ) {
						List<JSONObject> object = (List<JSONObject>) json.get("data");
						ArrayList<OdsNcBalanceVo> list = new ArrayList<OdsNcBalanceVo>();
						for (int k= 0; k < object.size(); k++) {
							OdsNcBalanceVo vo = JSONObject.toJavaObject(object.get(k), OdsNcBalanceVo.class);
							vo.setEndyear(endYear);
							vo.setEndmonth(endMonth);
							if(!"总计".equals(vo.getAccountcode())) {
								list.add(vo);
							}
						}
						this.saveBatch(list);
					}
					
				}
			}
			
			result.put("status", "200");
			
		} catch (Exception e) {
			log.error("NC外系统查询科目余额表调用异常"+e);
			result.put("status", "-2");
			e.printStackTrace();
		}
		
		return  result;
	}
	
	private Map<String,String> setXml(String orgCode, String kemuCode, String beginYear, 
			String beginMonth, 
			String endYear, 
			String endMonth){
		
		Map<String,String> map = new HashMap<String, String>();
		
		String xml="			<ilh:Balance>" + 
				"					<string> <![CDATA[<BalanceVo> " + 
				"  					<orgcode>" + 
				"  					<String>"+ orgCode + "</String> " + 
				"  					</orgcode>  " + 
				"  					<beginaccountcode>"+ kemuCode +"</beginaccountcode> " + 
				" 					<endaccountcode>"+ kemuCode +"</endaccountcode>  " + 
				"  					<beginyear>"+beginYear+"</beginyear>  " + 
				"  					<beginmonth>"+beginMonth+"</beginmonth>  " + 
				"  					<endyear>"+endYear+"</endyear>  " + 
				" 					 <endmonth>"+endMonth+"</endmonth> " + 
				"				</BalanceVo>]]></string>"+
				"				</ilh:Balance>";
		map.put("xml",xml);
		return map;
	}
	
	
	private Map<String,String> setXml(String orgCode, String kemuCode,String year,String lastMonth){
		
		Map<String,String> map = new HashMap<String, String>();
		
		String xml="			<ilh:Balance>" + 
				"					<string> <![CDATA[<BalanceVo> " + 
				"  					<orgcode>" + 
				"  					<String>"+ orgCode + "</String> " + 
				"  					</orgcode>  " + 
				"  					<beginaccountcode>"+ kemuCode +"</beginaccountcode> " + 
				" 					<endaccountcode>"+ kemuCode +"</endaccountcode>  " + 
				"  					<beginyear>"+year+"</beginyear>  " + 
				"  					<beginmonth>"+lastMonth+"</beginmonth>  " + 
				"  					<endyear>"+year+"</endyear>  " + 
				" 					 <endmonth>"+lastMonth+"</endmonth> " + 
				"				</BalanceVo>]]></string>"+
				"				</ilh:Balance>";
		map.put("xml",xml);
		return map;
	}
}
