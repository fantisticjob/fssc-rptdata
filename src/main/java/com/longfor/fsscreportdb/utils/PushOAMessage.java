package com.longfor.fsscreportdb.utils;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
	
public class PushOAMessage {
	
	
	
	/**
	 * 远程调用公共类
	 * @param userName
	 * @return
	 */
    public static  String  doPostJson(String account,String uuid,String title,String businessType,String url)  {
    	
    	GetProperties properties = new GetProperties();

        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
        	
        	//工抵房流程
        	JSONObject object = new JSONObject();
    		object.put("todoId",uuid);
    		object.put("systemNo", "CWGXPT");//代办所属系统
    		object.put("title",title );//代办标题
    		object.put("todoStatus", "0");//0:待审批，1:已审批，2:已取消 3:已删除 4:提交中 5:审批失败
    		object.put("pubUserAccount", "cwgx");//发送人账号
    		// object.put("businessType", "工抵房自查看板");//代办业务类型
    		object.put("businessType", businessType);//待办业务类型
    		object.put("appvUserAccount", account);//审批人
    		object.put("createTime", new Date().getTime());//推送时间
    		object.put("todoType", "0");//待办类型 0待办，1通知，2传阅，3催办，4归档，5预警，6协商(必传)
    		//object.put("spare", "www.baidu.com");
    		//object.put("isOpen", "1");
    		
    		object.put("target", url);
    		
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(properties.getProperties("oaurl"));
            // 创建请求内容
            httpPost.setHeader("HTTP Method","POST");
            httpPost.setHeader("Connection","Keep-Alive");
            httpPost.setHeader("Content-Type","application/json;charset=utf-8");
            //测试 - sit
            //httpPost.setHeader("X-Gaia-Api-key", "0cda6d9e-7a91-4c1f-b8f3-d150aa60db89");
            // 生产
            //httpPost.setHeader("X-Gaia-Api-key", "67d5f63d-8278-46ae-abfd-65b98550ba3e");
            //UAT
            httpPost.setHeader("X-Gaia-Api-key", "45d0d487-38bb-4769-ad14-877df131bdec");

            StringEntity entity = new StringEntity(object.toJSONString(), Charset.forName("UTF-8"));

            // StringEntity entity = new StringEntity(object.toJSONString());

            entity.setContentType("application/json;charset=utf-8");
            httpPost.setEntity(entity);

            // 执行http请求
            response = httpClient.execute(httpPost);
            
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {http://fr.longhu.net/webroot/decision/view/report?viewlet=%E8%B4%A2%E5%8A%A1%E5%85%B1%E4%BA%AB%E5%B9%B3%E5%8F%B0/Work_is_worth_room/LH_overdue_list-bf.cpt&op=write
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
       public static void main(String[] args) {
    	String title = "【待办222】请填写数据截止为2020-12-31的工抵房逾期分析明细表";
    	String businessType ="FSSGXYY_GDFZCKB";
    	String uuid = StringUtil.getUUID();
    	String account="majianjing";
		//String url="www.baidu.com";
		String url =
                "http://fr.longhu.net/webroot/decision/view/report?viewlet=%E8%B4%A2%E5%8A%A1%E5%85%B1%E4%BA%AB%E5%B9%B3%E5%8F%B0/Work_is_worth_room/LH_overdue_list-bf.cpt&op=write";

           JSONObject json = JSONObject.parseObject(new String(PushOAMessage.doPostJson(account,uuid,title,businessType, url)));

    	
    	System.err.println(json.toJSONString());
	}
}
