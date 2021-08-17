package com.longfor.fsscreportdb.ods.nc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcBalanceVo;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcBalanceVoBd;
import com.longfor.fsscreportdb.ods.nc.mapper.OdsNcBalanceVoBdMapper;
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcBalanceVoBdService;
import com.longfor.fsscreportdb.utils.RemoteCallUtil2;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * NC外系统查询科目余额表-银行直扣 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2021-04-28
 */
@Service
public class OdsNcBalanceVoBdServiceImpl extends ServiceImpl<OdsNcBalanceVoBdMapper, OdsNcBalanceVoBd> implements IOdsNcBalanceVoBdService {

	
	private  final   Logger log = LoggerFactory.getLogger(RemoteCallUtil2.class);

	
	@Override
	public JSONObject saveList(List<String> orgCodeList, List<String> kemuCodeList) {
		
		JSONObject result = new JSONObject();
		try {
			log.info("NC外系统查询科目余额表-银行直扣==开始抽数");
			String yestMonth = StringUtil.getYestMonth();
			String[] split = yestMonth.split("-");
			RemoteCallUtil2 utils = new RemoteCallUtil2();
			
			
			for(int i=0 ; i<orgCodeList.size(); i++) {
				log.info("orgCode_COUNT============" + i);
				
				for(int j = 0; j<kemuCodeList.size(); j++) {
					// Map<String, String> map = setXml(orgCodeList.get(i), kemuCodeList.get(j));
					Map<String, String> map = setXml(orgCodeList.get(i), kemuCodeList.get(j),
							split[0],split[1],
							split[0],split[1]);
					
					map.put("type","Balance");
					JSONObject json = utils.sendSyncRequest(map);
					log.info("map==" + map);
					log.info("orgCode==" + orgCodeList.get(i));
					log.info("kemuCode==" + kemuCodeList.get(j));
					log.info("json==" + json.toString());
					
					
					
					ArrayList<OdsNcBalanceVoBd> list = new ArrayList<OdsNcBalanceVoBd>();
					if(json.get("data") !=null ) {
						List<OdsNcBalanceVoBd> object = StringToDate( json.get("data"));
						for (int k= 0; k < object.size(); k++) {
							
							OdsNcBalanceVoBd voBd = object.get(k);
							voBd.setEndyear(split[0]);
							voBd.setEndmonth(split[1]);
							if(!"总计".equals(voBd.getAccountcode())) {
								list.add(voBd);
							}
						}
						this.saveBatch(list);
					}
					
				}
			}
			
			
			
			
			

			result.put("status", "200");
		
		} catch (Exception e) {
			log.error("NC外系统查询科目余额表-银行直扣调用异常"+e);
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
	
	
	/**
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
		
	}*/
	
	public List<OdsNcBalanceVoBd>  StringToDate(Object object) {
		JSONArray array = JSONArray.parseArray(object.toString());
		ArrayList<OdsNcBalanceVoBd> list = new ArrayList<OdsNcBalanceVoBd>();
		for (int i = 0; i < array.size(); i++) {
			OdsNcBalanceVoBd obj = JSONObject.parseObject(array.get(i).toString(),OdsNcBalanceVoBd.class);
			list.add(obj);
		}
		return list;
	}
	
	
}
