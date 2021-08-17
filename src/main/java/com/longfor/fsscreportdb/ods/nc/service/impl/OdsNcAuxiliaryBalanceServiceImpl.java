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
import com.longfor.fsscreportdb.ods.nc.mapper.OdsNcAuxiliaryBalanceMapper;
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcAuxiliaryBalanceService;
import com.longfor.fsscreportdb.utils.RemoteCallUtil2;
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
public class OdsNcAuxiliaryBalanceServiceImpl extends ServiceImpl<OdsNcAuxiliaryBalanceMapper, OdsNcAuxiliaryBalance> implements IOdsNcAuxiliaryBalanceService {
	
	private  final   Logger log = LoggerFactory.getLogger(RemoteCallUtil2.class);

	@Autowired
	private CommonDao dao;
	
	@Override
	public JSONObject saveList(List<String> orgCodeList, List<String> kemuCodeList, Map<String, String> fuzhuMap,
			String beginYear, 
			String beginMonth, 
			String endYear, 
			String endMonth) {
		
		JSONObject result = new JSONObject();
		try {
			RemoteCallUtil2 utils = new RemoteCallUtil2();
			
			JSONObject jsonResult = new JSONObject();
			
			for(int i=0 ; i<orgCodeList.size(); i++) {
				
				for(int j = 0; j<kemuCodeList.size(); j++) {
				
				
					Map<String, String> map = setXml(
							orgCodeList.get(i), 
							kemuCodeList.get(j), 
							fuzhuMap.get(kemuCodeList.get(j)),
							beginYear,
							beginMonth,
							endYear,
							endMonth);
					
					log.info("orgCode==" + orgCodeList.get(i));
					log.info("kemuCode==" + kemuCodeList.get(j));
					log.info("fuzhu==" + fuzhuMap.get(kemuCodeList.get(j)));
					
					map.put("type","AuxiliaryBalance");
					JSONObject json = utils.sendSyncRequest(map);
					
					if(json.get("data") !=null ) {
						List<JSONObject> object = (List<JSONObject>) json.get("data");
						ArrayList<OdsNcAuxiliaryBalance> list = new ArrayList<OdsNcAuxiliaryBalance>();
						for (int k= 0; k < object.size(); k++) {
							OdsNcAuxiliaryBalance vo = JSONObject.toJavaObject(object.get(k), OdsNcAuxiliaryBalance.class);
							 vo.setOrgcode(orgCodeList.get(i));
							 if(StringUtils.isBlank(vo.getAccountcode())) {
								 continue;
							 }
								list.add(vo);
							 
						}
						this.saveBatch(list);
					}
				}
			
			}

			result.put("status", "200");
		
		} catch (Exception e) {
			log.error("NC外系统查询辅助余额表调用异常"+e);
			result.put("status", "-2");
			e.printStackTrace();
		}

		return  result;
	}
	
	
	@Override
	public JSONObject saveList(List<String> orgCodeList, List<String> kemuCodeList, Map<String, String> fuzhuMap) {
		
		JSONObject result = new JSONObject();
		try {
			RemoteCallUtil2 utils = new RemoteCallUtil2();
			
			JSONObject jsonResult = new JSONObject();
			
			for(int i=0 ; i<orgCodeList.size(); i++) {
				
				for(int j = 0; j<kemuCodeList.size(); j++) {
				
				
					Map<String, String> map = setXml(
							orgCodeList.get(i), 
							kemuCodeList.get(j), 
							fuzhuMap.get(kemuCodeList.get(j)));
					
					log.info("orgCode==" + orgCodeList.get(i));
					log.info("kemuCode==" + kemuCodeList.get(j));
					log.info("fuzhu==" + fuzhuMap.get(kemuCodeList.get(j)));
					
					map.put("type","AuxiliaryBalance");
					JSONObject json = utils.sendSyncRequest(map);
					
					if(json.get("data") !=null ) {
						List<JSONObject> object = (List<JSONObject>) json.get("data");
						ArrayList<OdsNcAuxiliaryBalance> list = new ArrayList<OdsNcAuxiliaryBalance>();
						for (int k= 0; k < object.size(); k++) {
							OdsNcAuxiliaryBalance vo = JSONObject.toJavaObject(object.get(k), OdsNcAuxiliaryBalance.class);
							 vo.setOrgcode(orgCodeList.get(i));
							 if(StringUtils.isBlank(vo.getAccountcode())) {
								 continue;
							 }
								list.add(vo);
							 
						}
						this.saveBatch(list);
					}
				}
			
			}

			result.put("status", "200");
		
		} catch (Exception e) {
			log.error("NC外系统查询辅助余额表调用异常"+e);
			result.put("status", "-2");
			e.printStackTrace();
		}

		return  result;
	}
	
	private Map<String,String> setXml(String orgCode, String kemuCode , String fuzhu){
		
		Map<String,String> map = new HashMap<String, String>();
		
		String xml="<ilh:AuxiliaryBalance>"
				+ "					<string> <![CDATA["
				+ "	<AuxiliaryBalance> " + 
				"  <orgcode>" + 
				"  <String>"+ orgCode +"</String> " + 
				"  </orgcode>  " + 
				"  <accountcodes> " + 
				"  <String>"+ kemuCode +"</String> " + 
				"  </accountcodes>  " + 
				"  <accassitemcode> " + 
				fuzhu +
				"  </accassitemcode>  " + 
				"  <beginyear>"+StringUtil.getYear()+"</beginyear>  " + 
				"  <beginmonth>"+StringUtil.getLastMonth()+"</beginmonth>  " + 
				"  <endyear>"+StringUtil.getYear()+"</endyear>  " + 
				"  <endmonth>"+StringUtil.getLastMonth()+"</endmonth> " + 
				"</AuxiliaryBalance>"
				+ "]]></string>"+
				"	</ilh:AuxiliaryBalance>";
		map.put("xml",xml);
		return map;
		
	}
	
	private Map<String,String> setXml(String orgCode, String kemuCode , String fuzhu, 
			String beginyear, String beginmonth, String endyear, String endmonth){
		
		Map<String,String> map = new HashMap<String, String>();
		
		String xml="<ilh:AuxiliaryBalance>"
				+ "					<string> <![CDATA["
				+ "	<AuxiliaryBalance> " + 
				"  <orgcode>" + 
				"  <String>"+ orgCode +"</String> " + 
				"  </orgcode>  " + 
				"  <accountcodes> " + 
				"  <String>"+ kemuCode +"</String> " + 
				"  </accountcodes>  " + 
				"  <accassitemcode> " + 
				fuzhu +
				"  </accassitemcode>  " + 
				"  <beginyear>"+beginyear+"</beginyear>  " + 
				"  <beginmonth>"+beginmonth+"</beginmonth>  " + 
				"  <endyear>"+endyear+"</endyear>  " + 
				"  <endmonth>"+endmonth+"</endmonth> " + 
				"</AuxiliaryBalance>"
				+ "]]></string>"+
				"	</ilh:AuxiliaryBalance>";
		map.put("xml",xml);
		return map;
		
	}

	@Override
	public List<OdsNcAuxiliaryBalance> getAllBean(Integer count, Integer total) {
		String sql="SELECT *" + 
				"" + 
				"  FROM (SELECT ROWNUM AS rowno, t.*" + 
				"" + 
				"          FROM ODS_NC_AUXILIARY_BALANCE t" + 
				"" + 
				"           where ROWNUM <= "+total+") table_alias" + 
				"" + 
				" WHERE table_alias.rowno >"+count;
		return dao.getListOdsNcAuxiliaryBalance(sql);
	}
	
	@Override
	public List<OdsNcAuxiliaryBalance> getAllbyOrgCode(String orgCode) {
		String sql="SELECT * from ODS_NC_AUXILIARY_BALANCE where ORGCODE='" + orgCode + "'";
		return dao.getListOdsNcAuxiliaryBalance(sql);
	}
}
