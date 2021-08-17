package lh.fr;

import ETLJobHandaler.LhChatGroupJobHandler;
import ETLJobHandaler.WLQLPushMessageJobHandler;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreportdb.ods.controller.MyTestController;
import com.longfor.fsscreportdb.ods.controller.SendMsgController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import ETLJobHandaler.DDSYXxlJobHandler;

import java.util.List;


/**
 * 帆软服务上传测试应用
 *
 */
@SpringBootApplication(scanBasePackages = "com.longfor.fsscreportdb.ods.controller," + "ETLJobHandaler,"
		+ "com.longfor.fsscreportdb.config," + "com.longfor.fsscreportdb.reconciliation.service,"
		+ "com.longfor.fsscreportdb.Thread.*," + "com.longfor.fsscreportdb.reconciliation.service.impl,"
		+ "com.longfor.fsscreportdb.oa.service," + "com.longfor.fsscreportdb.oa.service.impl,"
		+ "com.longfor.fsscreportdb.ods.service," + "com.longfor.fsscreportdb.ods.service.impl,"
		+ "com.longfor.fsscreportdb.ods.nc.service, " + "com.longfor.fsscreportdb.ods.nc.service.impl ")

@EnableScheduling
public class App extends SpringBootServletInitializer {

	public static ConfigurableApplicationContext configurableApplicationContext = null;

	public static void main(String[] args) {

		configurableApplicationContext = SpringApplication.run(App.class, args);
		MyTestController mtc = configurableApplicationContext.getBean(MyTestController.class);
		mtc.executeFunc("0");

		// GDFXxlJobHandler djTHXxlJobHandler =
		// (GDFXxlJobHandler)
		// configurableApplicationContext.getBean("GDFXxlJobHandler");
		//
		// NCTRANSXxlJobHandler ncTRANSXxlJobHandler =
		// (NCTRANSXxlJobHandler)
		// configurableApplicationContext.getBean("NCTRANSXxlJobHandler");
		//

		//
		// GDFXxlJobHandler djTHXxlJobHandler =
		// (GDFXxlJobHandler)
		// configurableApplicationContext.getBean("GDFXxlJobHandler");
		//
		//
	/*	WLQLPushMessageJobHandler ssss = (WLQLPushMessageJobHandler) configurableApplicationContext.getBean("WLQLPushMessageJobHandler");


		LhChatGroupJobHandler djTHXxlJobHandler =  (LhChatGroupJobHandler) configurableApplicationContext.getBean("LhChatGroupJobHandler");*/

	//
		//
		//
		try {


			SendMsgController coller = (SendMsgController)configurableApplicationContext.getBean(SendMsgController.class);
			coller.getFinancePerson("1");

/*			JSONObject result = djTHXxlJobHandler.getManagersByRoleAndArea("北京", "C1");
			if (result != null && "0".equals(result.getString("resultCode"))) {
				List<String> userList = JSONArray.parseArray(result.getString("userList"), String.class);
				for (String s: userList) {
					System.out.println(s);
				}
			}*/
			//djTHXxlJobHandler.execute(null);
			//ReturnT<String> execute = djTHXxlJobHandler.execute(null);
			// String param = "2020-09-30";
			// ZJZCXxlJobHandler_ACBA djTHXxlJobHandler =
			// (ZJZCXxlJobHandler_ACBA)
			// configurableApplicationContext.getBean("ZJZCXxlJobHandler_ACBA");
			// djTHXxlJobHandler.execute(param);
			
			// 单据退回12月份数据取得
/*			String param = "2020,12";
			
			// 银行直扣 12月份取得
			param = "2020,10,2020,12";
			
			
			// 往来清理
			param = "2020Q4,11111";*/
//			WLQLCQHXxlJobHandler djTHXxlJobHandler =
//						 (WLQLCQHXxlJobHandler)
//						 configurableApplicationContext.getBean("WLQLCQHXxlJobHandler");
//						 djTHXxlJobHandler.execute(param);
			
			
			// 
//			param = "05101,2020,12";
//			DJYENCXxlJobHandler djTHXxlJobHandler =
//			 (DJYENCXxlJobHandler)
//			 configurableApplicationContext.getBean("DJYENCXxlJobHandler");
//			 djTHXxlJobHandler.execute(param);
			
//			param = "";
//			DDSYXxlJobHandlerRY ddSYXxlJobHandlerRY =
//			 (DDSYXxlJobHandlerRY)
//			 configurableApplicationContext.getBean("DDSYXxlJobHandlerRY");
//			ddSYXxlJobHandlerRY.execute(param);
			
			
			// String param = "1";
			// DDSYXxlJobHandlerQS ddSYXxlJobHandlerQS =
			// (DDSYXxlJobHandlerQS)
			// configurableApplicationContext.getBean("DDSYXxlJobHandlerQS");
			// ddSYXxlJobHandlerQS.execute(param);
			
//			param = "";
//			NCXxlJobHandlerZK djTHXxlJobHandler =
//			 (NCXxlJobHandlerZK)
//			 configurableApplicationContext.getBean("NCXxlJobHandlerZK");
//			 djTHXxlJobHandler.execute(param);
			
			// NC往来清理数据取得
//			param = "";
//			NCXxlJobHandler ncXxlJobHandler =
//			 (NCXxlJobHandler)
//			 configurableApplicationContext.getBean("NCXxlJobHandler");
//			ncXxlJobHandler.execute(param);
			
			// 成本台账历史数据取得
//			param = "2020,06";
//			CBMXHISXxlJobHandler ncXxlJobHandler =
//			 (CBMXHISXxlJobHandler)
//			 configurableApplicationContext.getBean("CBMXHISXxlJobHandler");
//			ncXxlJobHandler.execute(param);
//			
//			
//			param = "";
//			SKJ03XxlJobHandler ncXxlJobHandler =
//			 (SKJ03XxlJobHandler)
//			 configurableApplicationContext.getBean("SKJ03XxlJobHandler");
//			ncXxlJobHandler.execute(param);
//			
			

		} catch (Exception e) {

			e.printStackTrace();
		}

		//System.out.println("=============2=SC===20210322=============");

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
