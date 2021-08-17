package com.longfor.fsscreportdb.ods.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.longfor.fsscreportdb.constant.CommonConstant;
import com.longfor.fsscreportdb.ods.entity.OdsCrCreditPay;
import com.longfor.fsscreportdb.ods.mapper.OdsCrCreditPayMapper;
import com.longfor.fsscreportdb.ods.service.IOdsCrCreditPayService;
import com.longfor.fsscreportdb.utils.GetProperties;
import com.longfor.fsscreportdb.utils.HttpUtils;
import com.longfor.fsscreportdb.utils.StringUtil;
/**
 * <p>
 * 信用超期明细 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-08
 */
@Service
@SuppressWarnings("all")

public class OdsCrCreditPayServiceImpl extends ServiceImpl<OdsCrCreditPayMapper, OdsCrCreditPay> implements IOdsCrCreditPayService {
	
    private final Logger log = LoggerFactory.getLogger(OdsCrCreditPayServiceImpl.class);
    
	private static final  String STATUS="status";

	private  GetProperties properties = new GetProperties();
	@Autowired
	private CommonDao dao;
	
	@Override
	public JSONObject saveList(){
		
		log.info("信用超期付款 开始了");
		boolean flag =true;
		Integer total=0;
		Integer count=0;
		int errorCount = 0;
		int cishu=0;
		HttpUtils utils = new  HttpUtils();
		JSONObject json = new JSONObject();
		//String date=StringUtil.getHalfAYearAgo();
		try {
			
			String delete ="delete from ODS_CR_CREDIT_PAY";
			int deleteCount = dao.delete(delete);
			log.info("信用超期付款DELTETE条数：{}" , deleteCount);
			
			do {
				StringBuilder buffer = new StringBuilder();
				JSONObject token = utils.getToken(utils.getParmMap());
				if(token.get(CommonConstant.ACCESSTOKEN)!=null 
						&& !"".equals(token.get(CommonConstant.ACCESSTOKEN))) {
					buffer.append(properties.getProperties("xycq"));
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
					List<OdsCrCreditPay> list = StringToDate(datas.get("records"));
					
					count =Integer.parseInt(datas.get("total").toString());
					total += Integer.parseInt(datas.get("total").toString());
					
					boolean batch =true;
					if(!list.isEmpty()) {
						batch = saveBatch(list,list.size());
						
						if(!batch) {
							log.error("信用超期付款表数据湖取数保存失败！报错条数为{}",total);
							json.put(STATUS,"0");
							return  json;
						}
						if(count>-1 && count<1000) {
							
							flag=false;
						}
						cishu=cishu+list.size();
						log.info("信用超期付款当前数量为{}",cishu);
						
					}
					if(errorCount >200) break;
					log.info("error time ====={}",errorCount);
				}else {
					errorCount++;
				}
			}while(flag);
			
			log.info("信用超期付款 结束了");
			json.put(STATUS, 200);
		} catch (Exception e) {
			log.error("信用超期付款表数据湖取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
	}
	public List<OdsCrCreditPay>  StringToDate(Object object) {
		
		Map<String,Date> map = new HashMap<String, Date>();
		JSONArray array = JSONArray.parseArray(object.toString());
		
		List<OdsCrCreditPay> list = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			OdsCrCreditPay obj = JSONObject.parseObject(array.get(i).toString(),OdsCrCreditPay.class);	
			
			String jrgx_time = obj.getJrgx_time();
			String f_entsc_time = obj.getF_entsc_time();
			String f_fkzt = obj.getF_fkzt();
            String f_sfxzf = obj.getF_sfxzf();
            String f_djzt_gx = obj.getF_djzt_gx();
            String f_sta_fvep = obj.getF_sta_fvep();
            String f_djlx= obj.getF_djlx();
            String sys_source= obj.getSys_source();
            String tj= sys_source +f_djlx;
            
			if( "先付款后审核".equals(f_sfxzf)  &&  "已支付".equals(f_fkzt) ) {
				
				if(StringUtils.isNotBlank(jrgx_time) ) {
					
					try {
						jrgx_time=jrgx_time.substring(0,8);
						map = StringUtil.getBetweenEnd();
						Date dateConversion = StringUtil.getDateConversion2(jrgx_time);
						
						if(dateConversion.after(map.get("startTime")) && dateConversion.before(map.get("endTime"))) {
							list.add(obj);
						}
						
					} catch (ParseException e) {
						log.error("信用超期付款时间转换异常!{}",e.toString());
					}
				}else if(StringUtils.isNotBlank(f_entsc_time)) {
					
					try {
						f_entsc_time=f_entsc_time.substring(0,8);
						map = StringUtil.getBetweenEnd();
						Date dateConversion = StringUtil.getDateConversion(f_entsc_time);
						if(dateConversion.after(map.get("startTime")) && dateConversion.before(map.get("endTime"))) {
							list.add(obj);
						}
						
					} catch (ParseException e) {
						log.error("信用超期付款时间转换异常!{}",e.toString());
					}
					
				}else {
					log.error("jrgx_timew和f_entsc_time为空");
				}
			}
		} 
		return list;
	}
}
