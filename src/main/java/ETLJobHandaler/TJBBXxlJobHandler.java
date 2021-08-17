package ETLJobHandaler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.ods.service.IOdsMdmdataService;
import com.longfor.fsscreportdb.ods.service.IOdsNcdataService;
import com.longfor.fsscreportdb.ods.service.IOdsTjbbnckmService;
import com.longfor.fsscreportdb.ods.service.IOdsYydataService;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 统计报表定时任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "TJBBXxlJobHandler")
@Component
@Slf4j
public class TJBBXxlJobHandler extends IJobHandler {
	
	
	@Value("${kettel.host1}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	@Autowired
	private IOdsMdmdataService odsMdmdataService;
	
	@Autowired
	private IOdsNcdataService odsNcdataService;
	
	@Autowired
	private IOdsTjbbnckmService odsTjbbnckmService;
	
	@Autowired
	private IOdsYydataService odsYydataService;
	
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	JSONObject result=null;
        // 7 项目经营情况主数据数据接口
        XxlJobLogger.log("{}","项目经营情况主数据数据接口处理开始");
        result = odsMdmdataService.saveList();
        XxlJobLogger.log("项目经营情况主数据数据接口同步处理结束{}", String.valueOf(result.get("status")));
        
        // 8 项目经营情况NC数据接口
        XxlJobLogger.log("{}","项目经营情况NC数据接口处理开始");
        result = odsNcdataService.saveList();
        XxlJobLogger.log("项目经营情况NC数据接口同步处理结束{}", String.valueOf(result.get("status")));
       
        // 9 统计报表NC科目表
        XxlJobLogger.log("{}","统计报表NC科目表处理开始");
        result = odsTjbbnckmService.saveList();
        XxlJobLogger.log("统计报表NC科目表同步处理结束{}", String.valueOf(result.get("status")));
        
        
        // 11 项目经营情况运营数据接口
        XxlJobLogger.log("{}","项目经营情况运营数据接口处理开始");
        result = odsYydataService.saveList();
        XxlJobLogger.log("项目经营情况运营数据接口同步处理结束{}", String.valueOf(result.get("status")));
       
        
        // 4 ETL调启         
        KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
        kettleUtilRemote.callRemote(
        		ketelHost1, 
        		ketelPort,   ketelJobPath + "/DIMENSION/J_ODS_CR_CREDIT_PAY_OVERDUE.kjb");
        
        
        return SUCCESS;
    }
}
