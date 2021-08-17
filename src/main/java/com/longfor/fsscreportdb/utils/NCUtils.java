package com.longfor.fsscreportdb.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NCUtils {
	
	public static List<String> kemuCodeListBalance;
	public static List<String> kemuCodeListDetail;
	public static List<String> kemuCodeListBalanceDetail;
	public static Map<String,String> kemuFuzhu;
	public static Map<String,String> kemuFuzhu2;//单科目
	public static Map<String,String> kemuFuzhuN;
	
	public static List<String>  kemuCodeListDetail_MX01;
	public static List<String>  kemuCodeListDetail_MX02;
	
	  // 余额类科目
    public static List<String> initKemuCodeListBalance() {
    	
    	if(kemuCodeListBalance == null) {
    		kemuCodeListBalance = new ArrayList<String>();
    		kemuCodeListBalance.add("113399"); // 科目余额
       		kemuCodeListBalance.add("218199"); // 科目余额
       		kemuCodeListBalance.add("113303");
       		kemuCodeListBalance.add("113304");
       		kemuCodeListBalance.add("113308");
       		kemuCodeListBalance.add("115101");
       		kemuCodeListBalance.add("115102");
       		kemuCodeListBalance.add("115104");
       		kemuCodeListBalance.add("113301");
       		kemuCodeListBalance.add("218102");
       		kemuCodeListBalance.add("21210401");
       		kemuCodeListBalance.add("212102");
       		kemuCodeListBalance.add("218101");
       		kemuCodeListBalance.add("11519998");
       		kemuCodeListBalance.add("11519999");
       		kemuCodeListBalance.add("21810399");
       		
    	}
    	
   		
		return kemuCodeListBalance;
    }
    
		
		
	// 科目余额明细
	public static List<String> initKemuCodeListBalanceDetail() {
		
		if(kemuCodeListBalanceDetail == null) {
			kemuCodeListBalanceDetail = new ArrayList<String>();
			kemuCodeListBalanceDetail.add("113399");
			kemuCodeListBalanceDetail.add("218199");
		}
		
		return kemuCodeListBalanceDetail;
	}
	
	// 科目辅助对应关系
	public static Map<String,String> initKemuFuzhu() {
		
   		if(kemuFuzhu==null ) {
   			kemuFuzhu = new HashMap<String,String>();
   			kemuFuzhu.put("113303", "<String>RY</String>");
   	   		kemuFuzhu.put("113304", "<String>KS</String><String>RY</String>");
   	   		kemuFuzhu.put("113308", "<String>KS</String><String>RY</String>");
   	   		kemuFuzhu.put("115101", "<String>KS</String><String>RY</String>");
   	   		kemuFuzhu.put("115102", "<String>KS</String><String>RY</String>");
   	   		kemuFuzhu.put("115104", "<String>KS</String><String>RY</String>");	
   	   		kemuFuzhu.put("113301", "<String>KS</String>");
   	   		kemuFuzhu.put("218102", "<String>KS</String>");
   	   		kemuFuzhu.put("21210401", "<String>KS</String><String>RY</String><String>DJH</String>");
   	   		kemuFuzhu.put("212102", "<String>KS</String>");
   	   		kemuFuzhu.put("218101", "<String>KS</String>");
   	   		kemuFuzhu.put("11519998", "<String>KS</String><String>XM</String>");
   	   		kemuFuzhu.put("11519999", "<String>KS</String><String>XM</String>");
   		}
		return kemuFuzhu;
		
	}
		// 科目辅助对应关系
		public static Map<String,String> initKemuFuzhuN() {
			
	   		if(kemuFuzhuN==null ) {
	   			kemuFuzhuN = new HashMap<String,String>();
	   			kemuFuzhuN.put("113303", "<FZX>RY</FZX>");
		   		kemuFuzhuN.put("113304", "<FZX>KS</FZX><FZX>RY</FZX>");
		   		kemuFuzhuN.put("113308", "<FZX>KS</FZX><FZX>RY</FZX>");
		   		kemuFuzhuN.put("115101", "<FZX>KS</FZX><FZX>RY</FZX>");
		   		kemuFuzhuN.put("115102", "<FZX>KS</FZX><FZX>RY</FZX>");
		   		kemuFuzhuN.put("115104", "<FZX>KS</FZX><FZX>RY</FZX>");	
		   		kemuFuzhuN.put("113301", "<FZX>KS</FZX>");
		   		kemuFuzhuN.put("218102", "<FZX>KS</FZX>");
		   		kemuFuzhuN.put("21210401", "<FZX>KS</FZX><FZX>RY</FZX><FZX>DJH</FZX>");
		   		kemuFuzhuN.put("212102", "<FZX>KS</FZX>");
		   		kemuFuzhuN.put("218101", "<FZX>KS</FZX>");
		   		kemuFuzhuN.put("11519998", "<FZX>KS</FZX><FZX>XM</FZX>");
		   		kemuFuzhuN.put("11519999", "<FZX>KS</FZX><FZX>XM</FZX>");
	   		}
	   		
	   		
			return kemuFuzhuN;
			
		}
		
	// 明细类科目
   	public static List<String> initKemuCodeListDetail() {
   		if(kemuCodeListDetail == null) {
   			kemuCodeListDetail = new ArrayList<String>();
   			kemuCodeListDetail.add("212102");
   	   		kemuCodeListDetail.add("218101");
   	   		kemuCodeListDetail.add("11519998");
   	   		kemuCodeListDetail.add("11519999");
   		} 
//   		kemuCodeListDetail.add("113303");
//   		kemuCodeListDetail.add("113304");
//   		kemuCodeListDetail.add("113308");
//   		kemuCodeListDetail.add("115101");
//   		kemuCodeListDetail.add("115102");
//   		kemuCodeListDetail.add("115104");
//   		kemuCodeListDetail.add("113301");
//   		kemuCodeListDetail.add("218102");
//    		kemuCodeListDetail.add("21210401");
   		
   		
		return kemuCodeListDetail;

   	}
   	
   	
   	public static List<String> initKemuCodeListDetail_MX01() {
   		if(kemuCodeListDetail_MX01 == null) {
   			kemuCodeListDetail_MX01 = new ArrayList<String>();
   			kemuCodeListDetail_MX01.add("212102");
   	   		kemuCodeListDetail_MX01.add("218101");
   	   		kemuCodeListDetail_MX01.add("11519998");
   	   		kemuCodeListDetail_MX01.add("11519999");
   		} 
   		
   		
		return kemuCodeListDetail_MX01;
   	}
   	
   	
   	public static List<String> initKemuCodeListDetail_MX02() {
   		if(kemuCodeListDetail_MX02 == null) {
   			kemuCodeListDetail_MX02 = new ArrayList<String>();
   			kemuCodeListDetail_MX02.add("113303");
   	   		kemuCodeListDetail_MX02.add("113304");
   	   		kemuCodeListDetail_MX02.add("113308");
   	   		kemuCodeListDetail_MX02.add("115101");
   	   		kemuCodeListDetail_MX02.add("115102");
   	   		kemuCodeListDetail_MX02.add("115104");
   	   		kemuCodeListDetail_MX02.add("113301");
   	   		kemuCodeListDetail_MX02.add("218102");
   	   		kemuCodeListDetail_MX02.add("21210401");
   		} 
   		
   		
		return kemuCodeListDetail_MX02;

   	}
   	
}
