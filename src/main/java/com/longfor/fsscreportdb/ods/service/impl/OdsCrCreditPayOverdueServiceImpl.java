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
import com.longfor.fsscreportdb.ods.entity.OdsCrCreditPayOverdue;
import com.longfor.fsscreportdb.ods.mapper.OdsCrCreditPayOverdueMapper;
import com.longfor.fsscreportdb.ods.service.IOdsCrCreditPayOverdueService;
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

public class OdsCrCreditPayOverdueServiceImpl extends ServiceImpl<OdsCrCreditPayOverdueMapper, OdsCrCreditPayOverdue> implements IOdsCrCreditPayOverdueService {
	
    private final Logger log = LoggerFactory.getLogger(OdsCrCreditPayOverdueServiceImpl.class);
    
	private static final  String STATUS="status";

	private  GetProperties properties = new GetProperties();
	@Autowired
	private CommonDao dao;
	@Autowired
	private IOdsCrCreditPayService service;
	
	@Override
	public JSONObject saveList(){
		
		boolean flag =true;
		Integer total=0;
		Integer count=0;
		int errorCount = 0;
		int dataSize = 0;
		HttpUtils utils = new  HttpUtils();
		JSONObject json = new JSONObject();
		String date=StringUtil.getHalfAYearAgo();
		try {
			
			String delete ="delete from ODS_CR_CREDIT_PAY_OVERDUE";
			int deleteCount = dao.delete(delete);
			log.info("信用超期明细DELTETE条数：{}" , deleteCount);
			delete ="delete from ODS_CR_CREDIT_PAY";
			int deleteCount2 = dao.delete(delete);
			log.info("信用超期支付DELTETE条数：{}" , deleteCount2);
			
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
					log.info("error count ====={}",errorCount);
					//Thread.sleep(10000);
					continue;
				}
				if("200".equals(String.valueOf(datas.get("resultcode")))) {
					List<OdsCrCreditPayOverdue> list = StringToDate(datas.get("records"));
					List<OdsCrCreditPay> list2 = StringToDate2(datas.get("records"));
					
					count =Integer.parseInt(datas.get("total").toString());
					total += Integer.parseInt(datas.get("total").toString());
					boolean batch =true;
					if(!list.isEmpty()) {
						batch = saveBatch(list,list.size());
						dataSize += list.size();
					}

					if(!batch) {
						log.error("信用超期明细表数据湖取数保存失败！报错条数为{}",total);
						json.put(STATUS,"0");
						return  json;
					}
					if(!list2.isEmpty()) {
						batch = service.saveBatch(list2,list2.size());
					}
					
					if(!batch) {
						log.error("信用超期支付表数据湖取数保存失败！报错条数为{}",total);
						json.put(STATUS,"0");
						return  json;
					}
					if(count>-1 && count<1000) {
						
						flag=false;
					}
					log.info("信用超期明细当前数量为{}",total);
					//出错200次，跳出循环处理
					if(errorCount >200) {
						break;
					}
				}else {
					errorCount++;
					//Thread.sleep(10000);
					log.info("error count ====={}",errorCount);
				}
			}while(flag);
			
			log.info("信用超期明细入库记录条数{}", dataSize);
			log.info("出错总次数:{}", errorCount);
			json.put(STATUS, 200);
		} catch (Exception e) {
			log.error("信用超期明细表数据湖取数保存失败!{}",e.toString());
			json.put(STATUS, 0);
		}
		return  json;
	}
	public List<OdsCrCreditPayOverdue>  StringToDate(Object object) {
		JSONArray array = JSONArray.parseArray(object.toString());
		
		List<OdsCrCreditPayOverdue> list = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {
			OdsCrCreditPayOverdue obj = JSONObject.parseObject(array.get(i).toString(),OdsCrCreditPayOverdue.class);	
			
			
			String f_fkzt = obj.getF_fkzt();
            String f_sfxzf = obj.getF_sfxzf();
            String f_djzt_gx = obj.getF_djzt_gx();
            String f_sta_fvep = obj.getF_sta_fvep();
            String f_djlx= obj.getF_djlx();
            String sys_source= obj.getSys_source();
            String tj= sys_source +f_djlx;
            
			if((!"物业成本系统销售类退款-先付后审".equals(tj)) &&(!"物业成本系统地区-日常报销类型-销售类退款（物业）".equals(tj)) && 
				("先付款后审核".equals(f_sfxzf))   &&  ((!"共享已审核".equals(f_djzt_gx) && !"单据退回至共享".equals(f_djzt_gx) && !"已作废".equals(f_djzt_gx)) ||
				( f_djzt_gx==null || "".equals(f_djzt_gx)  ) )  &&  ((!"1".equals(f_sta_fvep) &&!"0".equals(f_sta_fvep) &&
				!"3".equals(f_sta_fvep) ) || (f_sta_fvep==null || "".equals(f_sta_fvep) ) ) && ("已支付".equals(f_fkzt))  ) {
				list.add(obj);
			}
		} 
		return list;
	}
	public List<OdsCrCreditPay>  StringToDate2(Object object) {
		
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
						System.err.println("---信用超期明细时间转换异常--- ");
						e.printStackTrace();
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
						System.err.println("---信用超期明细时间转换异常--- ");
						e.printStackTrace();
					}
					
				}else {
					log.error("jrgx_timew和f_entsc_time为空");
				}
				
			}
		} 
		return list;
	}
}
