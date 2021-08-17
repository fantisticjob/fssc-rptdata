package ETLJobHandaler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.longfor.fsscreportdb.common.mapper.CommonDao;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

import groovy.util.logging.Slf4j;

/**
 * 成本数据删除
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "CBMXHISDELETEXxlJobHandler")
@Component
@Slf4j
public class CBMXHISDELETEXxlJobHandler extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private CommonDao commonDao;

	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	
    	log.info("成本数据删除开始");
    	
    	// 按账套删除成本初始化数据： TYPE:1   参数：账套、时间起、时间止
    	String[] psarray = param.split(",");
    	if("1".equals(psarray[0]) && psarray.length == 4) {
    		
    		// 成本台账项目付款单明细表
    		StringBuilder sb = new StringBuilder();
    		sb.append("delete from dw_ct_project_payment t ");
    		sb.append("  where t.company_name ='");
    		sb.append(psarray[1]);
    		sb.append("'");
    		sb.append("  and t.datekey between '");
    		sb.append(psarray[2]);
    		sb.append("' and '");
    		sb.append(psarray[3]);
    		sb.append("'");
    		log.info(sb.toString());
    		commonDao.delete(sb.toString());
    		
    		// 成本台账合同分期明细表
    		sb = new StringBuilder();
    		sb.append("delete from dw_ct_contract_info t ");
    		sb.append("  where t.company_name ='");
    		sb.append(psarray[1]);
    		sb.append("'");
    		sb.append("  and t.datekey between '");
    		sb.append(psarray[2]);
    		sb.append("' and '");
    		sb.append(psarray[3]);
    		sb.append("'");
    		log.info(sb.toString());
    		commonDao.delete(sb.toString());
    		
    		// 成本台账分期付款单明细表
    		sb = new StringBuilder();
    		sb.append("delete from dw_ct_installment_payment t ");
    		sb.append("  where t.company_name ='");
    		sb.append(psarray[1]);
    		sb.append("'");
    		sb.append("  and t.datekey between '");
    		sb.append(psarray[2]);
    		sb.append("' and '");
    		sb.append(psarray[3]);
    		sb.append("'");
    		log.info(sb.toString());
    		commonDao.delete(sb.toString());
    		
    		// 成本汇总表
    		sb = new StringBuilder();
    		sb.append("delete from dw_ct_cost_total t ");
    		sb.append("  where t.company_name ='");
    		sb.append(psarray[1]);
    		sb.append("'");
    		sb.append("  and t.datekey between '");
    		sb.append(psarray[2]);
    		sb.append("' and '");
    		sb.append(psarray[3]);
    		sb.append("'");
    		log.info(sb.toString());
    		commonDao.delete(sb.toString());
    		
    		// 成本开发间接费人工确认表
    		sb = new StringBuilder();
    		sb.append("delete from dw_ct_check_localdebit_jjfy t ");
    		sb.append("  where t.company_name ='");
    		sb.append(psarray[1]);
    		sb.append("'");
    		sb.append("  and t.datekey between '");
    		sb.append(psarray[2]);
    		sb.append("' and '");
    		sb.append(psarray[3]);
    		sb.append("'");
    		log.info(sb.toString());
    		commonDao.delete(sb.toString());
    		
        // --删除单条成本初始化数据：参数：id1,id2,id3,id4,id5	
    	} else if ("2".equals(psarray[0]) && psarray.length == 6) {
    		
    		StringBuilder sb = new StringBuilder();
    		sb.append("delete from dw_ct_project_payment t ");
    		sb.append("  where t.id =");
    		sb.append(psarray[1]);
    		log.info(sb.toString());
    		commonDao.delete(sb.toString());
    		
    		sb = new StringBuilder();
    		sb.append("delete from dw_ct_contract_info t ");
    		sb.append("  where t.id =");
    		sb.append(psarray[2]);
    		log.info(sb.toString());
    		commonDao.delete(sb.toString());
    		
    		sb = new StringBuilder();
    		sb.append("delete from dw_ct_installment_payment t ");
    		sb.append("  where t.id =");
    		sb.append(psarray[3]);
    		log.info(sb.toString());
    		commonDao.delete(sb.toString());
    		
    		sb = new StringBuilder();
    		sb.append("delete from dw_ct_cost_total t ");
    		sb.append("  where t.id =");
    		sb.append(psarray[4]);
    		log.info(sb.toString());
    		commonDao.delete(sb.toString());
    		
////   		sb = new StringBuilder(); 
//    		sb.append("delete from dw_ct_check_localdebit_jjfy t ");
//    		sb.append("  where t.id =");
//    		sb.append(psarray[5]);
//    		log.info(sb.toString());
//    		commonDao.delete(sb.toString());
    	}

    		
    	log.info("成本数据删除处理完了");   
        
        return SUCCESS;
    }
}
