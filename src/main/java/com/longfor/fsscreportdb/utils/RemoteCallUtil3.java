package com.longfor.fsscreportdb.utils;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("all")
public class RemoteCallUtil3 {
	
	
	private  final    Logger log = LoggerFactory.getLogger(RemoteCallUtil3.class);
	
	public   JSONObject sendSyncRequest(Map<String,String> map) {
		GetProperties properties = new GetProperties();
		JSONObject json = new JSONObject();
	    HttpURLConnection httpConn = null;
	    BufferedReader reader = null;
	    log.info("远程连接:"+properties.getProperties("ncss"));
	    try {
	    	URL url = new URL(properties.getProperties("ncss"));
	        httpConn = (HttpURLConnection) url.openConnection();
	        httpConn.setRequestProperty("Content-Type","text/xml; charset=utf-8"); // 设置在header中
	        httpConn.setRequestMethod("POST");
	        httpConn.setDoOutput(true);
	        httpConn.setDoInput(true);
	        httpConn.connect();
	        OutputStream outputStream = httpConn.getOutputStream();
	        String param= makeParameter( map);
	        outputStream.write(param.getBytes());
	        
	        Thread.sleep(15000);
	        
        	json.put("result", "远程调用成功");
        	json.put("status", "200");
        	
            return json;

	    } catch ( Exception e ) {
	    	
	    	log.error("远程调用异常{}",e.toString());
	        json.put("result", "远程调用异常");
	        return json;
	        
	    } finally {
	    	if (httpConn!=null || null != reader) {
	            try {
	            	httpConn.disconnect();
	                httpConn = null;
	            } catch (Exception e) {
	            	
	            }
	        }
	    }
	}
	 
	
	private   String makeParameter(Map<String,String> map) {
		
		String xml="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ilh=\"http://report.hk.itf.nc/ILhReport\">"
				+ "		<soapenv:Header/>"
				+ "			<soapenv:Body>"
					+map.get("xml")
				+ "		</soapenv:Body>"
				+ "</soapenv:Envelope>";
		log.info("远程调用入口xml参数：{}",xml);
		
		return xml;
	}
	
}
