package ETLJobHandaler;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxl.job.core.biz.model.ReturnT;

import groovy.util.logging.Slf4j;

/**
 * 成本明细历史TEST
 * @author zhaoxin
 * @date 2021/03/23 
*/
@Slf4j
public class CBMXHISXxlJobHandlerTest  {
	
	protected Logger log = LoggerFactory.getLogger(getClass());

	@InjectMocks
    private CBMXHISXxlJobHandler cbMXHISXxlJobHandler;
	
	@Test
	public void updataCbtz() {
		
		try {
			cbMXHISXxlJobHandler = new CBMXHISXxlJobHandler();
			ReturnT<String>  result = cbMXHISXxlJobHandler.execute(null);
			int resultint = result.getCode();
			Assert.assertEquals("zheng", 200, resultint);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
