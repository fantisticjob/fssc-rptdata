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
 * 往来清理初期化删除
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "WLQLCQHXxlJobHandler")
@Component
@Slf4j
public class WLQLCQHXxlJobHandler extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private CommonDao commonDao;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	
    	log.info("往来清理初期化删除开始");

    	log.info("param==" + param);
    	String[] inputs = param.split(",");
    	
    	if(inputs.length!=2) {
    		log.info("入口参数不对。");
    		return SUCCESS;
    	}
    	
    	// 季度
    	String seson = inputs[0];
    	// 账套
    	String accounts = inputs[1];
    	
    	// 01
    	StringBuilder sb = new StringBuilder();
    	sb.append("update dw_cp_clear_up_total t");
    	sb.append(" set t.start_date = ''  ");
    	sb.append(" where t.quarter = '");
    	sb.append(seson);
    	sb.append("' ");
    	sb.append(" and t.accounts_id = '");
    	sb.append(accounts);
    	sb.append("' ");
    	log.info("NO1" + sb.toString());
    	commonDao.update(sb.toString());
    	log.info("NO1");

    	// 02
    	sb = new StringBuilder();
    	sb.append("delete from dw_cp_clear_up_detail t ");
    	sb.append(" where t.quarter = '");
    	sb.append(seson);
    	sb.append("' ");
    	sb.append(" and t.accounts_id = '");
    	sb.append(accounts);
    	sb.append("' ");
    	sb.append(" and t.csh_flag = '1' ");
    	log.info("NO2" + sb.toString());
    	commonDao.update(sb.toString());
    	log.info("NO2");

    	// 03
    	sb = new StringBuilder();
    	sb.append("update dw_cp_clear_up_blance t ");
    	sb.append("set t.reseon        = '',  ");
    	sb.append("t.is_un_general = '',  ");
    	sb.append("t.pt_user       = '',  ");
    	sb.append("t.clear_time    = '',  ");
    	sb.append("t.respon_user   = '',  ");
    	sb.append("t.respon_dept   = '',  ");
    	sb.append("t.re_mark       = '',  ");
    	sb.append("t.dq_user       = ''   ");
    	sb.append(" where t.quarter = '");
    	sb.append(seson);
    	sb.append("' ");
    	sb.append(" and t.accounts_id = '");
    	sb.append(accounts);
    	sb.append("' ");
    	
    	log.info("NO3" + sb.toString());
    	commonDao.update(sb.toString());
    	log.info("NO3");
    	
    	log.info("往来清理初期化删除结束");   
        
        return SUCCESS;
    }
}
