package com.longfor.fsscreportdb.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;



/**
 * @Desc   http工具类
 * @author chenziyao
 * @Date   2020-08-05
 *  */
public class HttpUtils {
	
	private  GetProperties properties = new GetProperties();
	protected Logger log = LoggerFactory.getLogger(getClass());

	
	/**根据url加上认证的token信息，取出数据
     * 远程连接
     * 
	 * @throws Exception 
     */ 
	public  JSONObject getDataByToken(String Url)  {
        HttpURLConnection connection = null;
        String string =null;
        JSONObject object = null;
        ByteArrayOutputStream stream=null;
        InputStream is=null;
        try {
        	 //=========== connection连接时出错，返回值设定不成功事项修正===by zhaoxin==20210322==begin
        	 object = new JSONObject();
        	 //=========== connection连接时出错，返回值设定不成功事项修正===by zhaoxin==20210322==end
        	 URL url = new URL(Url);
             connection = (HttpURLConnection) url.openConnection();
             connection.setRequestMethod("POST");//设置请求方式为POST
             connection.setDoOutput(true);//允许写出
             connection.setDoInput(true);//允许读入
             connection.setUseCaches(false);//不使用缓存
             connection.connect();//连接
            
	         if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { 
	        	 is = connection.getInputStream();
		         stream = new ByteArrayOutputStream();
		         byte[] data = new byte[1024]; 
		         int count = -1; 
		         while ((count = is.read(data, 0, 1024)) != -1) { 
		        	 stream.write(data, 0, count); 
		         } 
		         string = new String(stream.toByteArray(), "UTF-8");
		         object = JSONObject.parseObject(string);
	          } 
        } catch (Exception e) {
        	if(object!=null) {
        		object.put("result", -1);
        	}
        	log.error("getDataByToken()远程调用失败+==={}",e.toString());
        	// e.printStackTrace();
        } finally {
            if (connection != null) {
            	try {
            		if(is !=null) {
            			is.close();
            		}
            		if(stream!=null) {
            			stream.close();
            		}
	            	connection.disconnect();
				} catch (IOException e) {
					
					log.error("getDataByToken()远程调用失败+==={}",e.toString());
				}
            
            }
        }
		return object;
    }
	/**
	 * 
	 * @param uri 根据body的参数获取认证的token
	 * @return 返回token数据
	 */
	public  JSONObject getToken(Map<String,Object> map) {
        JSONObject object = null;
        HttpURLConnection connection = null;
        ByteArrayOutputStream stream=null;
        InputStream is=null;
		try {
			String string =null;
			object = null;
			URL url = new URL(map.get("token").toString());
			// 打开和URL之间的连接
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");//设置请求方式为POST
			connection.setDoOutput(true);//允许写出
			connection.setDoInput(true);//允许读入
			connection.setUseCaches(false);//不使用缓存
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 建立实际的连接
			connection.connect();
			// 得到请求的输出流对象
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes( map.get("content").toString());
			out.flush();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { 
				 is = connection.getInputStream();
			     stream = new ByteArrayOutputStream();
			     byte[] data = new byte[1024]; 
			     int count = -1; 
			     while ((count = is.read(data, 0, 1024)) != -1) { 
			    	 stream.write(data, 0, count); 
			     } 
			     string = new String(stream.toByteArray(), "UTF-8");
		         object = JSONObject.parseObject(string);
			     stream.close();
			 }
			// 关闭连接
			connection.disconnect();
		} catch (Exception e) {
			if(object!=null) {
				object.put("result", -1);
			}
        	log.error("getToken()远程调用失败+==={}",e.toString());
        } finally {
            if (connection != null) {
            	try {
            		if(is !=null) {
            			is.close();
            		}
            		if(stream!=null) {
            			stream.close();
            		}
	            	connection.disconnect();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
            
            }
        }
		return object;
	}
	/**
	 * 变量放入map中，方便入参
	 * @return
	 */
	public Map<String,Object> getParmMap() {
		
		StringBuilder content = new StringBuilder();
		Map<String,Object>  map = new HashMap<String,Object>();
		content.append("client_id=");
		content.append(properties.getProperties("client_id"));
		content.append("&client_secret=");
		content.append(properties.getProperties("client_secret"));
		content.append("&grant_type=");
		content.append(properties.getProperties("grant_type"));
		map.put("content",content);
		map.put("token",properties.getProperties("token"));
		
		return map;
	}
	}
