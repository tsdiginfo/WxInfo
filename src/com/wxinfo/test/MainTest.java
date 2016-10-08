package com.wxinfo.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.httpclient.util.HttpClientUtil;
import com.tuling.message.TulingReq;
import com.tuling.message.TulingResp;

public class MainTest {
	private static  Logger logger = Logger.getLogger(MainTest.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("main");
		logger.info("main");   //调用日志
		//http post 发送给机器人API
		String info = "鱼香肉丝";
		String userid = "12345678";
		TulingReq tulingReq = new TulingReq(info,userid);
		
		HttpClientUtil httpClientUtil =  new HttpClientUtil(); 
		Map<String,String> createMap = new HashMap<String,String>();  
        createMap.put("key",tulingReq.getKey());  
        createMap.put("info",tulingReq.getInfo());
        createMap.put("userid",tulingReq.getUserid());
        String tulingResult = httpClientUtil.doPost(tulingReq.getApiUrl(),createMap);  
        System.out.println("result:"+tulingResult);  
        
        System.out.println("convertToWxMessage:"+TulingResp.convertToWxMessage(tulingResult,"张三","李四")); 
        
		//解析tulingResult
        /*JSONTokener  jsonTokener = new JSONTokener(tulingResult);
        JSONObject object = (JSONObject) jsonTokener.nextValue();  
        System.out.println("test:::: "  + object.getInt("code"));
        System.out.println(object.getString("text"));
        JSONArray jsonArray = object.getJSONArray("list");
        for(int i=0 ;i<jsonArray.length();i++){
        	JSONObject jObject = (JSONObject)jsonArray.get(i);
            System.out.println("jsonArray:::: "  + jObject.getString("source"));
        }*/
        

	}

}
