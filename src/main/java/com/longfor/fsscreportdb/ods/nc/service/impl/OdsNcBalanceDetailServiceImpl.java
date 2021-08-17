package com.longfor.fsscreportdb.ods.nc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcBalanceDetail;
import com.longfor.fsscreportdb.ods.nc.mapper.OdsNcBalanceDetailMapper;
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcBalanceDetailService;
import com.longfor.fsscreportdb.utils.RemoteCallUtil2;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * NC外系统查询科目余额明细表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-12
 */
@SuppressWarnings("all")
@Service
public class OdsNcBalanceDetailServiceImpl extends ServiceImpl<OdsNcBalanceDetailMapper, OdsNcBalanceDetail> implements IOdsNcBalanceDetailService {
	
	private  final static   Logger log = LoggerFactory.getLogger(RemoteCallUtil2.class);

	@Autowired
	private CommonDao dao;
	
	@Override
	public JSONObject saveList(
			List<String> orgCodeList, 
			List<String> kemuCodeList, 
			String beginYear, 
			String beginMonth, 
			String endYear, 
			String endMonth) {
		
		JSONObject result = new JSONObject();
		
		try {
			
			RemoteCallUtil2 utils = new RemoteCallUtil2();
			
			for(int i=0 ; i<orgCodeList.size(); i++) {
				log.info("orgCode_COUNT============" + i);
				
				for(int j=0; j< kemuCodeList.size(); j++) {
					
					String kemuCode = kemuCodeList.get(j);
					
					Map<String, String> map = setXml_YEARMONTCH(
							orgCodeList.get(i), kemuCode, beginYear, beginMonth, endYear, endMonth);
							
					map.put("type","BalanceDetail");
					JSONObject json = utils.sendSyncRequest(map);
					log.info("orgCode==" + orgCodeList.get(i));
					log.info("kemuCode==" + kemuCode);
					
					if(json.get("data") !=null ) {
						List<JSONObject> object = (List<JSONObject>) json.get("data");
						ArrayList<OdsNcBalanceDetail> list = new ArrayList<OdsNcBalanceDetail>();
						for (int k = 0; k < object.size(); k++) {
							OdsNcBalanceDetail vo = JSONObject.toJavaObject(object.get(k), OdsNcBalanceDetail.class);
							if("null".equals(vo.getMonth()) || StringUtils.isBlank(vo.getAccountcode())) {
								continue;
							}
							 list.add(vo);
						}
						if(list!=null && list.size()>0) {
							this.saveBatch(list);
						}
					}
				}
					
				}
			
			result.put("status", "200");
		} catch (Exception e) {
			log.error("NC外系统查询科目余额明细表调用异常"+e);
			result.put("status", "-2");
			e.printStackTrace();
		}

		return  result;
		
	}
	
	@Override
	public JSONObject saveList(List<String> orgCodeList, List<String> kemuCodeList) {
		JSONObject result = new JSONObject();
		try {
			
			RemoteCallUtil2 utils = new RemoteCallUtil2();
			
//			String sql="delete from ODS_NC_BALANCE_DETAIL";
//			int resultDelete = dao.delete(sql);
//			log.info("resultDelete==" + resultDelete);
			
			for(int i=0 ; i<orgCodeList.size(); i++) {
				log.info("orgCode_COUNT============" + i);
				
				for(int j = 0; j<kemuCodeList.size(); j++) {
					Map<String, String> map = setXml(orgCodeList.get(i), kemuCodeList.get(j));
					
					map.put("type","BalanceDetail");
					JSONObject json = utils.sendSyncRequest(map);
					log.info("orgCode==" + orgCodeList.get(i));
					log.info("kemuCode==" + kemuCodeList.get(j));
					
					if(json.get("data") !=null ) {
						List<JSONObject> object = (List<JSONObject>) json.get("data");
						ArrayList<OdsNcBalanceDetail> list = new ArrayList<OdsNcBalanceDetail>();
						for (int k = 0; k < object.size(); k++) {
							OdsNcBalanceDetail vo = JSONObject.toJavaObject(object.get(k), OdsNcBalanceDetail.class);
							if("null".equals(vo.getMonth()) || StringUtils.isBlank(vo.getAccountcode())) {
								continue;
							}
							 list.add(vo);
						}
						if(list!=null && list.size()>0) {
							this.saveBatch(list);
						}
					}
					
				}
			}
			
			result.put("status", "200");
		} catch (Exception e) {
			log.error("NC外系统查询科目余额明细表调用异常"+e);
			result.put("status", "-2");
			e.printStackTrace();
		}

		return  result;
	}
	
	private Map<String,String> setXml(String orgName, String kemuCode){
		
		Map<String,String> map = new HashMap<String, String>();
		
		String xml="<ilh:BalanceDetail>"
				+ "					<string> <![CDATA["
				+ "<BalanceDetail>"+
				"		  <orgcode>"+orgName+"</orgcode>  " + 
				"		  <accountcode>"+kemuCode+"</accountcode> " + 
				"		   <beginyear>"+StringUtil.getYear()+"</beginyear>  " + 
				"		  <beginmonth>"+StringUtil.getLastMonth()+"</beginmonth>  " + 
				"		  <endyear>"+StringUtil.getYear()+"</endyear>  " + 
				"		  <endmonth>"+StringUtil.getLastMonth()+"</endmonth> " + 
				"	</BalanceDetail>"
				+ "]]></string>"+
				"	</ilh:BalanceDetail>"	;
		map.put("xml",xml);
		return map;
		
	}
	
	private Map<String,String> setXml_YEARMONTCH(
			String orgName, 
			String kemuCode,
			String beginYear,
			String beginMonth,
			String endYear, 
			String endMonth){
		
		Map<String,String> map = new HashMap<String, String>();
		
		String xml="<ilh:BalanceDetail>"
				+ "					<string> <![CDATA["
				+ "<BalanceDetail>"+
				"		  <orgcode>"+orgName+"</orgcode>  " + 
				"		  <accountcode>"+kemuCode+"</accountcode> " + 
				"		   <beginyear>"+beginYear+"</beginyear>  " + 
				"		  <beginmonth>"+beginMonth+"</beginmonth>  " + 
				"		  <endyear>"+endYear+"</endyear>  " + 
				"		  <endmonth>"+endMonth+"</endmonth> " + 
				"	</BalanceDetail>"
				+ "]]></string>"+
				"	</ilh:BalanceDetail>"	;
		map.put("xml",xml);
		return map;
		
	}

}
