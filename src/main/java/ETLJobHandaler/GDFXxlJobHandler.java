package ETLJobHandaler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.ods.service.IOdsWrWorkRoomDetailService;
import com.longfor.fsscreportdb.utils.KettleUtilRemote;
import com.longfor.fsscreportdb.utils.StringUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 工抵房定时任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "GDFXxlJobHandler")
@Component
@Slf4j
public class GDFXxlJobHandler extends IJobHandler {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${kettel.host1}")
	String ketelHost1;
	
	@Value("${kettel.port}")
	String ketelPort;
	
	@Value("${kettel.job.path}")
	String ketelJobPath;
	
	@Autowired
	private IOdsWrWorkRoomDetailService odsWrWorkRoomDetailService;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	
    	
    	
	    	log.info("工抵房处理开始");
	    	
	        // 工抵房明细表 
	        XxlJobLogger.log("{}","工抵房明细表处理开始");
	        JSONObject result = odsWrWorkRoomDetailService.saveList();
	        XxlJobLogger.log("工抵房明细表同步处理结束{}", String.valueOf(result.get("status")));
	        
	        // 4 ETL调启  工抵房每天
	        XxlJobLogger.log("{}","工抵房明细表ETL每天处理开始");
	        KettleUtilRemote kettleUtilRemote = new KettleUtilRemote();
	        kettleUtilRemote.callRemote(
	        		ketelHost1, 
	        		ketelPort,   ketelJobPath + "/WR/J_WR_ANALYSE.kjb");
	        XxlJobLogger.log("{}","工抵房明细表ETL每天处理完了");
	        
	        // 工抵房汇总版
	        if(StringUtil.isSesionLockMonth()) {
	        	
	            XxlJobLogger.log("{}","工抵房总表锁板ETL处理开始");
	            kettleUtilRemote.callRemote(
	            		ketelHost1, 
	            		ketelPort,  ketelJobPath +  "/WR/J_DW_WR_WORK_ROOM_SUMMARY_LOCK.kjb");
	            XxlJobLogger.log("{}","工抵房总表锁板ETL处理完了"); 
	            log.info("工抵房锁版处理完了");
	        }
	        
	        // 每年01-01、04-01、07-01、10-01对上季度数据锁板
	        if(StringUtil.isSesionLock()) {
	            
	        	log.info("工抵房锁版处理开始");
	        	
	        	XxlJobLogger.log("{}","逾期分析锁板ETL处理开始");
	            kettleUtilRemote.callRemote(
	            		ketelHost1, 
	            		ketelPort,  ketelJobPath +  "/WR/J_DW_WR_OVERDUE_ANALYSIS_LOCK.kjb");
	            XxlJobLogger.log("{}","逾期分析锁板ETL处理完了");
	        }
	        
	        log.info("工抵房处理结束");

        
        
        
        return SUCCESS;
    }
   
}
