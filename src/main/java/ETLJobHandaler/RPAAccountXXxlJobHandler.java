package ETLJobHandaler;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.longfor.fsscreportdb.reconciliation.entity.RprtTaskYg;
import com.longfor.fsscreportdb.reconciliation.service.IRprtTaskYgService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import groovy.util.logging.Slf4j;

/**
 * 成本明细定时任务
 * @author zhaoxin
 * @date 2020/8/7 
*/
@JobHandler(value = "RPAAccountXXxlJobHandler")
@Component
@Slf4j
public class RPAAccountXXxlJobHandler extends IJobHandler {
	
	
	@Autowired
	private IRprtTaskYgService irs;
	
    @Override
    public ReturnT<String> execute(String param) throws Exception {
    	XxlJobLogger.log("RPAAccountXXxlJobHandler()入口参数",param);
    	
    	try {
			if(param !=null &&!"".equals(param)) {
				int i = Integer.parseInt(param);//得到允许时间差
				
				List<RprtTaskYg> RprtTaskYg = irs.getListBean();//取到rpa结束时间为空的所有数据
				
				for (int j = 0; j < RprtTaskYg.size(); j++) {
					
					RprtTaskYg task = RprtTaskYg.get(j);
					Date begintime = task.getBegintime();//得到rpa开始时间
					long time = begintime.getTime();
					Date date = new Date();
					long time2 = date.getTime();
					long time3=(time2-time)/1000/60;//得到时间差毫秒值
					
					if(time3>i) {
						task.setEndtimeIra(date);
						task.setFinishStatus("2");
						task.setBegintime(null);
						if(irs.updateById(task)) {
							XxlJobLogger.log("RPAAccountXXxlJobHandler(updateById)更新成功");
						}
					}
					
				}
			}
		} catch (Exception e) {
			XxlJobLogger.log("RPAAccountXXxlJobHandler(异常)",e.toString());
		}
        return SUCCESS;
    }
}
