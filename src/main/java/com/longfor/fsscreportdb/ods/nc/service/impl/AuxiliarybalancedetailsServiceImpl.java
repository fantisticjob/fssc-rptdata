package com.longfor.fsscreportdb.ods.nc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.ods.nc.entity.Auxiliarybalancedetails;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcBalanceDetails;
import com.longfor.fsscreportdb.ods.nc.mapper.AuxiliarybalancedetailsMapper;
import com.longfor.fsscreportdb.ods.nc.service.IAuxiliarybalancedetailsService;
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcBalanceDetailsService;

/**
 * <p>
 * 辅助余额明细表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-17
 */
@Service
public class AuxiliarybalancedetailsServiceImpl extends ServiceImpl<AuxiliarybalancedetailsMapper, Auxiliarybalancedetails> implements IAuxiliarybalancedetailsService {
	
	
	private static final  String STATUS="status";
	private static final  String RESULT="result";
	
	@Autowired
	private AuxiliarybalancedetailsMapper mapper;
	
	@Autowired 
	private IOdsNcBalanceDetailsService service;
	
	@Transactional
	@Override
	public JSONObject saveList(String uuid) {
		
		JSONObject json = new JSONObject();
		
		try {
			QueryWrapper<Auxiliarybalancedetails> wrapper = new QueryWrapper<Auxiliarybalancedetails>();
			wrapper.eq("BATCHERNUMBER",uuid);
			List<Auxiliarybalancedetails> list = mapper.selectList(wrapper);
			List<OdsNcBalanceDetails> arrayList = new ArrayList<OdsNcBalanceDetails>();
			for (int i = 0; i < list.size(); i++) {
				
				Auxiliarybalancedetails auxiliarybalance = list.get(i);
				OdsNcBalanceDetails balance = new OdsNcBalanceDetails().convertFrom(auxiliarybalance);//实体类互转
				arrayList.add(balance);
				
				if(arrayList.size()==1000) {
					if(service.saveBatch(arrayList)) {
						arrayList.clear();
					}else {
						json.put(RESULT, "辅助余额明细取数失败！" );
						json.put(STATUS, "-1" );
						break;
					}
				}
			}
			
			if(arrayList.size()>0) {
				service.saveBatch(arrayList);
			}
			
			json.put(RESULT, "辅助余额表取数成功！" );
			json.put(STATUS, "200" );
		} catch (Exception e) {
			json.put(RESULT, "辅助余额表取数异常！" );
			json.put(STATUS, "-2" );
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public boolean saveByCvs(List<Auxiliarybalancedetails> list) {
		
		return this.saveBatch(list);
	}
}
