package ETLJobHandaler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.ods.service.IOdsPtPlatformareafkdwService;
import com.longfor.fsscreportdb.ods.service.IOdsPtZjareabankorgService;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 主调度任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "PTDQXxlJobHandler")
@Component
@Slf4j
public class PTDQXxlJobHandler extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${kettel.host1}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	@Autowired
	private IOdsPtPlatformareafkdwService odsPtPlatformareafkdwService;
	
	@Autowired
	private IOdsPtZjareabankorgService odsPtZjareabankorgService;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	JSONObject result=null;
        
    	log.info("工抵房处理开始");
    	
         // 1 平台地区付款单位服务类
         XxlJobLogger.log("{}"," 平台地区付款单位接口处理开始");
         result = odsPtPlatformareafkdwService.saveList();
    	 XxlJobLogger.log(" 平台地区付款单位同步处理结束{}", String.valueOf(result.get("status")));
        
        // 2 资金银行账户-地区-组织对照关系
        XxlJobLogger.log("{}","资金银行账户-地区-组织对照关系接口处理开始");
        result = odsPtZjareabankorgService.saveList();
        XxlJobLogger.log("资金银行账户-地区-组织对照关系同步处理结束{}", String.valueOf(result.get("status")));
        
        // 3 ETL调启 (主数据) 
        XxlJobLogger.log("{}","ETL调启 (主数据) ETL处理开始");
        KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
        kettleUtilRemote.callRemote(
        		ketelHost1, 
        		ketelPort,   ketelJobPath + "/DIMENSION/J_DW_DIMENSION.kjb");
        XxlJobLogger.log("{}","ETL调启 (主数据) ETL处理完了");
        
        log.info("工抵房处理结束");
        
        return SUCCESS;
    }
}
