package com.longfor.fsscreportdb.ods.nc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreportdb.ods.nc.entity.Auxiliarybalance;
import com.longfor.fsscreportdb.ods.nc.entity.OdsNcAuxiliaryBalance;
import com.longfor.fsscreportdb.ods.nc.mapper.AuxiliarybalanceMapper;
import com.longfor.fsscreportdb.ods.nc.service.IAuxiliarybalanceService;
import com.longfor.fsscreportdb.ods.nc.service.IOdsNcAuxiliaryBalanceService;

/**
 * <p>
 * 辅助余额表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-17
 */
@Service
public class AuxiliarybalanceServiceImpl extends ServiceImpl<AuxiliarybalanceMapper, Auxiliarybalance> implements IAuxiliarybalanceService {

	@Autowired
	private AuxiliarybalanceMapper mapper;
	
	@Autowired IOdsNcAuxiliaryBalanceService service;
	
	@Transactional
	@Override
	public JSONObject saveList(String uuid) {
		
		JSONObject json = new JSONObject();
		
		try {
			QueryWrapper<Auxiliarybalance> wrapper = new QueryWrapper<Auxiliarybalance>();
			wrapper.eq("BATCHERNUMBER",uuid);
			List<Auxiliarybalance> list = mapper.selectList(wrapper);
			List<OdsNcAuxiliaryBalance> arrayList = new ArrayList<OdsNcAuxiliaryBalance>();
			for (int i = 0; i < list.size(); i++) {
				
				Auxiliarybalance auxiliarybalance = list.get(i);
				OdsNcAuxiliaryBalance balance = new OdsNcAuxiliaryBalance().convertFrom(auxiliarybalance);//实体类互转
				arrayList.add(balance);
				if(arrayList.size()==1000) {
					if(service.saveBatch(arrayList)) {
						arrayList.clear();
					}else {
						json.put("result", "辅助余额表取数失败！" );
						json.put("status", "-1" );
						break;
					}
				}
			}
			
			if(arrayList.size()>0) {
				service.saveBatch(arrayList);
			}
			
			json.put("result", "辅助余额表取数成功！" );
			json.put("status", "200" );
		} catch (Exception e) {
			json.put("result", "辅助余额表取数异常！" );
			json.put("status", "-2" );
			e.printStackTrace();
		}
		return json;
	}
	
	@Override
	public boolean saveByCvs(List<Auxiliarybalance> list) {
		
		boolean saveBatch = this.saveBatch(list);
		
		return saveBatch;
		
	}
		
		
}
