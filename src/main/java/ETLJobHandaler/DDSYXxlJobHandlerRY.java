package ETLJobHandaler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.ods.service.IOdsZdGxptrepemployeeService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 重点税源定时任务---2020-01-18
 * @author zhaoxin
 * @date 2020-01-18
*/
@JobHandler(value = "DDSYXxlJobHandlerRY")
@Component
@Slf4j
public class DDSYXxlJobHandlerRY extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());


	@Autowired
	private IOdsZdGxptrepemployeeService odsZdGxptrepemployeeService;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	JSONObject result=null;
    	
    	log.info("共享平台公司职工数表接口处理开始");
        // 共享平台公司职工数表接口处理开始
        XxlJobLogger.log("{}","共享平台公司职工数表接口处理开始");
        result = odsZdGxptrepemployeeService.saveList();
        XxlJobLogger.log("共享平台公司职工数表处理结束{}", String.valueOf(result.get("status")));
        log.info("共享平台公司职工数表接口处理结束");
        
        return SUCCESS;
    }
}
