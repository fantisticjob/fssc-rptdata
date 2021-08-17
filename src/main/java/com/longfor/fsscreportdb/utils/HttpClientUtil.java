package com.longfor.fsscreportdb.utils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class HttpClientUtil {
    
   
    

    //private String getRoleCode="http://api.longfor.sit/iam-openapi-sit/public/v2/role/getByBizCode";
    
    //private String  getUserName="http://api.longfor.sit/iam-openapi-sit/public/v2/user/getListByRoleCode";
	
	public static void main(String[] args) {
		
		String userName="heqiong1";
		//userName="luomh";
		//userName="wangyitian";
		String json = doPostJson();
		System.err.println(json.toString());
		 
		
	}
	
	
	
	 
	/**
	 * 远程调用公共类
	 * @param userName
	 * @return
	 */
    public static  String  doPostJson()  {
    	
    	//String urls="http://api.longfor.sit/iam-openapi-sit/public/v2/org/getOrgInfoByUsername";
    	//String urls="http://api.longfor.sit/iam-openapi-sit/public/v2/user/getUserListByOrgCodes";
    	String urls="http://api.longfor.sit/iam-openapi-sit/public/v2/user/getByOrgRoleCodes";
    	//String urls="http://api.longfor.sit/iam-openapi-sit/public/v2/user/getUserListByOrgIds";
    	
    	
    	List<String> orgCodeList = new ArrayList<String>();
    	List<String> roleCodeList = new ArrayList<String>();
    	
    	
    	orgCodeList.add("1000005651");
    	
    	roleCodeList.add("R_UC_115571");
        
    	JSONObject object = new JSONObject();
        object.put("orgCodeList", orgCodeList);
        object.put("roleCodeList", roleCodeList);
        object.put("bizCode", "XZ");
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(urls);
            // 创建请求内容
            httpPost.setHeader("HTTP Method","POST");
            httpPost.setHeader("Connection","Keep-Alive");
            httpPost.setHeader("Content-Type","application/json;charset=utf-8");
            httpPost.setHeader("X-Gaia-Api-key", "9935d769-5d1e-4d34-ad09-e89d52aa51cf");

            StringEntity entity = new StringEntity(object.toJSONString());

            entity.setContentType("application/json;charset=utf-8");
            httpPost.setEntity(entity);

            // 执行http请求
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
         } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                if(response!=null){
                    response.close();	
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }
  
    


}