package com.longfor.fsscreportdb.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.longfor.fsscreportdb.common.mapper.CommonDao;

@Component("DelMonthlyClosingUtils")
public  class DelMonthlyClosingUtils{

    private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private CommonDao dao;
	
	private static DelMonthlyClosingUtils instance;
	
	private DelMonthlyClosingUtils(){

    }
	public static DelMonthlyClosingUtils getInstance(){
		
        if(instance==null){
            instance=new DelMonthlyClosingUtils();
        }
        return instance;
	}
	
	public synchronized Integer deleteDateByType(String type,String orgCode,String month,String detailKemu) throws Exception{
		
		StringBuilder delSql = new StringBuilder();
		
		// 入口参数
		log.info("deleteDateByType()type:{}" , type);
		log.info("deleteDateByType()orgCode:{}" , orgCode);
		log.info("deleteDateByType()month:{}" , month);
		log.info("deleteDateByType()detailKemu:{}" , detailKemu);
		
		
		//如果等于1 月关批量删除 
		if( type.equals("1") ) {
			
			log.info("月关批量删除");
			delSql.append("delete from rprt_billdata");
			
		}else if( type.equals("2") ){//月关单家汇总（参数：日期（2020-11），账套（00001））
			
			log.info("月关汇总删除");
			
			if( StringUtils.isNotBlank(detailKemu)) {
				
				String detailKemus = StringUtil.getListStringIds(detailKemu); //得到科目明细的数组
				
				delSql.append("delete from rprt_billdata ");
				delSql.append(" where data_date = '");
				delSql.append(month);
				delSql.append("' and substr(org_codenm, 1, ");
				delSql.append("instr(org_codenm, '-', 1) - 1) = '");
				delSql.append(orgCode);
				delSql.append("' and rprt_modul in ");
				delSql.append("(select rprt_modul_name ");
				delSql.append("from dw_ca_rprt_modul_mapping a, ");
				delSql.append("din_dim_value b  ");
				delSql.append("where a.dim_value_id = b.dim_value_id ");
				delSql.append("and b.use_flag = 1 ");
				delSql.append("and b.dim_value_name in(");
				delSql.append(detailKemus);
				delSql.append(") )");
				
			}else {
				
				delSql.append("delete from rprt_billdata ");
				delSql.append(" where data_date =  '");
				delSql.append(month);
				delSql.append("'  and substr(org_codenm, 1, ");
				delSql.append("instr(org_codenm, '-', 1) - 1) = '");
				delSql.append(orgCode);
				delSql.append("'");
			}
			
		}else if( type.equals("3")){
			
			log.info("月关明细删除：{}",detailKemu);
			if("科目检查-进项税".equals(detailKemu)) { //1
				
				delSql.append("delete from rprt_inputtax_details ");
				delSql.append("where substr(org_codenm, 1,  ");
				delSql.append("instr(org_codenm, '-', 1) - 1) = '");
				delSql.append(orgCode);
				delSql.append("' and data_date = '");
				delSql.append(month);
				delSql.append("'");
			}else if("科目检查-固定资产".equals(detailKemu)) {//2
				
				delSql.append("delete from rprt_asset ");
				delSql.append("where substr(org_codenm, 1,  ");
				delSql.append("instr(org_codenm, '-', 1) - 1) = '");
				delSql.append(orgCode);
				delSql.append("' and data_date = '");
				delSql.append(month);
				delSql.append("'");
			}else if("科目检查-预收预收&预收发票".equals(detailKemu)) {//3
				
				delSql.append("delete from rprt_advancereceiptconsole ");
				delSql.append("where substr(org_codenm, 1,  ");
				delSql.append("instr(org_codenm, '-', 1) - 1) = '");
				delSql.append(orgCode);
				delSql.append("' and data_date = '");
				delSql.append(month);
				delSql.append("'");
			}else if("科目检查-内外部单位往来科目客商辅助".equals(detailKemu)) {//4
				
				delSql.append("delete from rprt_merchants ");
				delSql.append("where substr(org_codenm, 1,  ");
				delSql.append("instr(org_codenm, '-', 1) - 1) = '");
				delSql.append(orgCode);
				delSql.append("' and data_date = '");
				delSql.append(month);
				delSql.append("'");
			}else if("协同检查".equals(detailKemu)) {//5
				
				delSql.append("delete from rprt_publiccoll ");
				delSql.append("where substr(org_codenm, 1,  ");
				delSql.append("instr(org_codenm, '-', 1) - 1) = '");
				delSql.append(orgCode);
				delSql.append("' and data_date = '");
				delSql.append(month);
				delSql.append("'");
				delSql.append("'");
			}else if("科目检查-损益类科目".equals(detailKemu)) {//6
				
				delSql.append("delete from rprt_profitlosssub ");
				delSql.append("where substr(org_codenm, 1,  ");
				delSql.append("instr(org_codenm, '-', 1) - 1) = '");
				delSql.append(orgCode);
				delSql.append("' and data_date = '");
				delSql.append(month);
				delSql.append("'");
				
			}else if("现流分析-长期借款".equals(detailKemu)) {//7
				
				delSql.append("delete from rprt_longtermborrow ");
				delSql.append("where substr(org_codenm, 1,  ");
				delSql.append("instr(org_codenm, '-', 1) - 1) = '");
				delSql.append(orgCode);
				delSql.append("' and data_date = '");
				delSql.append(month);
				delSql.append("'");
				
			}else if("现流分析-保理检查".equals(detailKemu)) {//8
				
				delSql.append("delete from rprt_factoring_details ");
				delSql.append("where substr(org_codenm, 1,  ");
				delSql.append("instr(org_codenm, '-', 1) - 1) = '");
				delSql.append(orgCode);
				delSql.append("' and data_date = '");
				delSql.append(month);
				delSql.append("'");
				
			}else if("现流分析-工程造价、研发设计、报建支出、工程相关（含税）".equals(detailKemu)) {//9
				
				delSql.append("delete from rprt_gcyfbj_details ");
				delSql.append("where substr(org_codenm, 1,  ");
				delSql.append("instr(org_codenm, '-', 1) - 1) = '");
				delSql.append(orgCode);
				delSql.append("' and data_date = '");
				delSql.append(month);
				delSql.append("'");
				
			}else if("现流分析-增值税及附加、土增税、企业所得税".equals(detailKemu)) {//10
				
				delSql.append("delete from rprt_xjlfx ");
				delSql.append("where substr(org_codenm, 1,  ");
				delSql.append("instr(org_codenm, '-', 1) - 1) = '");
				delSql.append(orgCode);
				delSql.append("' and data_date = '");
				delSql.append(month);
				delSql.append("'");
			}
			
		}
		
		log.info("delete SQL=={}" , delSql.toString());
		Integer delete = dao.delete(delSql.toString());
		if(delete > 0) {
			log.info("月关删除条数为：{}",delete);
		}
		return delete;
	}
}
