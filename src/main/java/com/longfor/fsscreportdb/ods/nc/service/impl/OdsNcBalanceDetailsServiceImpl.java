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
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcAuxiliaryBalance;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcBalanceDetail;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcBalanceDetails;
import com.longfor.fsscreportdb.ods.nc.mapper.OdsNcBalanceDetailsMapper;
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcAuxiliaryBalanceService;
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcBalanceDetailsService;
import com.longfor.fsscreportdb.utils.RemoteCallUtil2;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * NC外系统查询辅助余额明细表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-12
 */
@SuppressWarnings("all")
@Service
public class OdsNcBalanceDetailsServiceImpl extends ServiceImpl<OdsNcBalanceDetailsMapper, OdsNcBalanceDetails> implements IOdsNcBalanceDetailsService {
	
	private  final static   Logger log = LoggerFactory.getLogger(RemoteCallUtil2.class);
	
	@Autowired
	private IOdsNcAuxiliaryBalanceService odsNcAuxiliaryBalanceService;
	@Autowired
	private CommonDao dao;
	

//	@Override
//	public JSONObject saveList(List<String> orgCodeList) {
//		
//		JSONObject result = new JSONObject();
//
//		try {
//			for(int i=0; i<orgCodeList.size(); i++) {
//				List<OdsNcAuxiliaryBalance> list 
//					= odsNcAuxiliaryBalanceService.getAllbyOrgCode(orgCodeList.get(i));
//				if(list.size() > 0) {
//					result = save(list);
//				}
//			}
//			
//		} catch (Exception e) {
//			log.error("NC外系统查询辅助余额明细表异常"+e);
//			result.put("status", "-2");
//			e.printStackTrace();
//		}
//		
//		return  result;
//	}
	
	@Override
	public JSONObject saveList(List<String> orgCodeList,
			String beginYear, 
			String beginMonth, 
			String endYear, 
			String endMonth) {
		
		JSONObject result = new JSONObject();

		try {
			for(int i=0; i<orgCodeList.size(); i++) {
				List<OdsNcAuxiliaryBalance> list 
					= odsNcAuxiliaryBalanceService.getAllbyOrgCode(orgCodeList.get(i));
				if(list.size() > 0) {
					result = save(list , 
							beginYear, 
							beginMonth, 
							endYear, 
							endMonth);
				}
			}
			
		} catch (Exception e) {
			log.error("NC外系统查询辅助余额明细表异常"+e);
			result.put("status", "-2");
			e.printStackTrace();
		}
		
		return  result;
	}
	
	public JSONObject save(List<OdsNcAuxiliaryBalance> bean,
			String beginYear, 
			String beginMonth, 
			String endYear, 
			String endMonth) {
		
		JSONObject result = new JSONObject();
		RemoteCallUtil2 utils = new RemoteCallUtil2();
		
		Integer count = 0;
		try {
			for (int i = 0; i < bean.size(); i++) {
				OdsNcAuxiliaryBalance balance = bean.get(i);

				// Map<String, String> map = setXml(balance);
				Map<String, String> map = setXml(balance, beginYear, 
						 beginMonth, 
						 endYear, 
						 endMonth);
				map.put("type","AuxiliaryBalanceDetails");
				JSONObject json = utils.sendSyncRequest(map);
				
				if(json.get("data") !=null ) {
					
					List<JSONObject> object = (List<JSONObject>) json.get("data");
					ArrayList<OdsNcBalanceDetails> list = new ArrayList<OdsNcBalanceDetails>();
					
					for (int j = 0; j < object.size();j++) {
						OdsNcBalanceDetails vo = JSONObject.toJavaObject(object.get(j), OdsNcBalanceDetails.class);
						vo.setBalanceid(balance.getId());
						vo.setEndyear(endYear);
						vo.setEndmonth(endMonth);
						if(StringUtils.isBlank(vo.getAccountcode())) {
							continue;
						}
						list.add(vo);
					}
					
					boolean saveBatch = this.saveBatch(list);
					if(saveBatch) {
						result.put("status", "200");
						log.info("第"+count+++"次保存！");
					}else {
						result.put("status", "0");
					}
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
		
	}
	

//public JSONObject save(List<OdsNcAuxiliaryBalance> bean) {
//		
//		JSONObject result = new JSONObject();
//		RemoteCallUtil2 utils = new RemoteCallUtil2();
//		
//		Integer count = 0;
//		try {
//			for (int i = 0; i < bean.size(); i++) {
//				OdsNcAuxiliaryBalance balance = bean.get(i);
//
//				Map<String, String> map = setXml(balance);
//				map.put("type","AuxiliaryBalanceDetails");
//				JSONObject json = utils.sendSyncRequest(map);
//				
//				if(json.get("data") !=null ) {
//					
//					List<JSONObject> object = (List<JSONObject>) json.get("data");
//					ArrayList<OdsNcBalanceDetails> list = new ArrayList<OdsNcBalanceDetails>();
//					
//					for (int j = 0; j < object.size();j++) {
//						OdsNcBalanceDetails vo = JSONObject.toJavaObject(object.get(j), OdsNcBalanceDetails.class);
//						vo.setBalanceid(balance.getId());
//						if(StringUtils.isBlank(vo.getAccountcode())) {
//							continue;
//						}
//						list.add(vo);
//					}
//					
//					boolean saveBatch = this.saveBatch(list);
//					if(saveBatch) {
//						result.put("status", "200");
//						log.info("第"+count+++"次保存！");
//					}else {
//						result.put("status", "0");
//					}
//				}
//			}
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		return result;
//		
//	}


	private Map<String,String> setXml(OdsNcAuxiliaryBalance balance){
		
		Map<String,String> map = new HashMap<String, String>();
		
		
		
		StringBuilder sb = new StringBuilder();
		
		if(StringUtils.isNotBlank(balance.getKscode())) {
			sb.append("<accassitem> ");
			sb.append("<checktypecode>KS</checktypecode>");
			sb.append("<checktypename>客商</checktypename>");
			sb.append("<checkvaluecode>"+balance.getKscode()+"</checkvaluecode>");
			sb.append("<checkvaluename>"+balance.getKsname()+"</checkvaluename>");
			sb.append("</accassitem> ");
		}
		if(StringUtils.isNotBlank(balance.getXmcode())) {
			sb.append("<accassitem> ");
			sb.append("<checktypecode>XM</checktypecode>");
			sb.append("<checktypename>项目</checktypename>");
			sb.append("<checkvaluecode>"+balance.getXmcode()+"</checkvaluecode>");
			sb.append("<checkvaluename>"+balance.getXmname()+"</checkvaluename>");
			sb.append("</accassitem> ");
		}
		if(StringUtils.isNotBlank(balance.getRycode())) {
			sb.append("<accassitem> ");
			sb.append("<checktypecode>RY</checktypecode>");
			sb.append("<checktypename>人员</checktypename>");
			sb.append("<checkvaluecode>"+balance.getRycode()+"</checkvaluecode>");
			sb.append("<checkvaluename>"+balance.getRyname()+"</checkvaluename>");
			sb.append("</accassitem> ");
		}
		if(StringUtils.isNotBlank(balance.getDjhcode())) {
			sb.append("<accassitem> ");
			sb.append("<checktypecode>DJH</checktypecode>");
			sb.append("<checktypename>单据号</checktypename>");
			sb.append("<checkvaluecode>"+balance.getDjhcode()+"</checkvaluecode>");
			sb.append("<checkvaluename>"+balance.getDjhname()+"</checkvaluename>");
			sb.append("</accassitem> ");
		}
		String xml="				<ilh:AuxiliaryBalanceDetails>"
				+ "					<string> <![CDATA["
				+ "				<AuxiliaryBalanceDetails> " + 
				"  <orgcode>"+balance.getOrgcode()+"</orgcode>  " + 
				"  <accountcode>"+balance.getAccountcode()+"</accountcode>  " + 
				"  <accassitems> " + 
								sb.toString()+
				"  </accassitems>  " + 
				"  <beginyear>"+StringUtil.getYear()+"</beginyear>  " + 
				"  <beginmonth>"+StringUtil.getLastMonth()+"</beginmonth>  " + 
				"  <endyear>"+StringUtil.getYear()+"</endyear>  " + 
				"  <endmonth>"+StringUtil.getLastMonth()+"</endmonth> " + 
				"</AuxiliaryBalanceDetails>"
				+ "]]></string>"
				+ "				</ilh:AuxiliaryBalanceDetails>";
		map.put("xml",xml);
		log.info("xml======" + xml);
		return map;

	}
	
private Map<String,String> setXml(OdsNcAuxiliaryBalance balance,
		String beginYear, 
		String beginMonth, 
		String endYear, 
		String endMonth){
		
		Map<String,String> map = new HashMap<String, String>();
		
		
		
		StringBuilder sb = new StringBuilder();
		
		if(StringUtils.isNotBlank(balance.getKscode())) {
			sb.append("<accassitem> ");
			sb.append("<checktypecode>KS</checktypecode>");
			sb.append("<checktypename>客商</checktypename>");
			sb.append("<checkvaluecode>"+balance.getKscode()+"</checkvaluecode>");
			sb.append("<checkvaluename>"+balance.getKsname()+"</checkvaluename>");
			sb.append("</accassitem> ");
		}
		if(StringUtils.isNotBlank(balance.getXmcode())) {
			sb.append("<accassitem> ");
			sb.append("<checktypecode>XM</checktypecode>");
			sb.append("<checktypename>项目</checktypename>");
			sb.append("<checkvaluecode>"+balance.getXmcode()+"</checkvaluecode>");
			sb.append("<checkvaluename>"+balance.getXmname()+"</checkvaluename>");
			sb.append("</accassitem> ");
		}
		if(StringUtils.isNotBlank(balance.getRycode())) {
			sb.append("<accassitem> ");
			sb.append("<checktypecode>RY</checktypecode>");
			sb.append("<checktypename>人员</checktypename>");
			sb.append("<checkvaluecode>"+balance.getRycode()+"</checkvaluecode>");
			sb.append("<checkvaluename>"+balance.getRyname()+"</checkvaluename>");
			sb.append("</accassitem> ");
		}
		if(StringUtils.isNotBlank(balance.getDjhcode())) {
			sb.append("<accassitem> ");
			sb.append("<checktypecode>DJH</checktypecode>");
			sb.append("<checktypename>单据号</checktypename>");
			sb.append("<checkvaluecode>"+balance.getDjhcode()+"</checkvaluecode>");
			sb.append("<checkvaluename>"+balance.getDjhname()+"</checkvaluename>");
			sb.append("</accassitem> ");
		}
		String xml="				<ilh:AuxiliaryBalanceDetails>"
				+ "					<string> <![CDATA["
				+ "				<AuxiliaryBalanceDetails> " + 
				"  <orgcode>"+balance.getOrgcode()+"</orgcode>  " + 
				"  <accountcode>"+balance.getAccountcode()+"</accountcode>  " + 
				"  <accassitems> " + 
								sb.toString()+
				"  </accassitems>  " + 
				"  <beginyear>"+beginYear+"</beginyear>  " + 
				"  <beginmonth>"+beginMonth+"</beginmonth>  " + 
				"  <endyear>"+endYear+"</endyear>  " + 
				"  <endmonth>"+endMonth+"</endmonth> " + 
				"</AuxiliaryBalanceDetails>"
				+ "]]></string>"
				+ "				</ilh:AuxiliaryBalanceDetails>";
		map.put("xml",xml);
		log.info("xml======" + xml);
		return map;

	}
}
