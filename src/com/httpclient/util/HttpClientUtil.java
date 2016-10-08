package com.httpclient.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/*
 * 利用HttpClient进行post请求的工具类
 * HttpClient包里面需要logger，logger包依赖commons-logging-1.0.4.jar
 */
public class HttpClientUtil {
	public String doPost(String url,Map<String,String> map){
		String charset = "utf-8";
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		CloseableHttpResponse response = null;
		try{
			//httpClient = new SSLClient();
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			// 创建httppost  
			httpPost = new HttpPost(url);
			//创建参数队列 
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String,String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
			}
			if(list.size() > 0){
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
				httpPost.setEntity(entity);
			}
			response = httpClient.execute(httpPost);
			if(response != null){
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,charset);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return result;
	}
}