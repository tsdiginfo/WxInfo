package com.wxinfo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import net.sf.json.JSONObject;


import org.json.JSONObject;

import com.message.resp.Article;
import com.message.resp.NewsMessage;
import com.message.resp.TextMessage;
import com.message.resp.Music;
import com.message.resp.MusicMessage;
import com.wxinfo.util.MessageUtil;

/**
 * 核心服务类
 * @author Administrator
 *
 */

public class Service {
	 /** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
	private static String TULING_API_URL = "http://www.tuling123.com/openapi/api";
	private static String TULING_API_KEY = "b819398655bc451a8dbc32a429d922ec";//图灵的APIKEY
		
    public static String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        // 返回给微信服务器的xml消息  
        String respXml = null;  
        // 文本消息内容  
        String respContent = null;
        String responseString = "";
        String text = "";
        String url_text = "";
        try {  
            // 默认返回的文本消息内容  
            //String respContent = "请求处理异常，请稍候尝试！";  
        	respContent = "请求处理异常，请稍候尝试！";  
  
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
  
            // 回复文本消息  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
  
            // 文本消息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            	
                respContent = "您发送的是文本消息！您说的是：" + requestMap.get("Content");
                String requestString = "key=" + TULING_API_KEY + "&info=" + requestMap.get("Content"); 
                URL url = new URL(TULING_API_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setConnectTimeout(1000);
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(requestString.getBytes("utf-8"));
                outputStream.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                String temp;
                while ((temp = reader.readLine()) != null) {
                	responseString += temp;
                }
               //JSONObject jsonObject = new JSONObject(responseString);
                //text = jsonObject.getString("text"); 
            }  
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "您发送的是图片消息！";  
            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "您发送的是地理位置消息！";  
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "您发送的是链接消息！";  
            }  
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "您发送的是音频消息！您说的是：" + requestMap.get("Recognition");  
                
                //调用机器人的API
                //机器人回复字符串
                
//                String requesturl = "http://www.tuling123.com/openapi/api?key= b819398655bc451a8dbc32a429d922ec&info="+requestMap.get("Recognition"); 
//                HttpGet request1 = new HttpGet(requesturl); 
//                HttpResponse response = HttpClients.createDefault().execute(request1); 
//                //200即正确的返回码 
//                if(response.getStatusLine().getStatusCode()==200){ 
//                	String result = EntityUtils.toString(response.getEntity()); 
//                	System.out.println("返回结果："+result); 
//                }
                
                String requestString = "key=" + TULING_API_KEY + "&info=" + requestMap.get("Recognition"); 
                URL url = new URL(TULING_API_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setConnectTimeout(1000);
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(requestString.getBytes("utf-8"));
                outputStream.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                String temp;
                while ((temp = reader.readLine()) != null) {
                	responseString += temp;
                }
                
                //如何把字符串转为JSON ?
                //JSONObject jsonObject = new JSONObject(responseString);
                //text = jsonObject.getString("text");
                //目前只提取回复结果为文本信息的结果，因为这里只根据Json的text值进行提取
                //可根据responseString的code值，提取相应的结果，如URL等
            }  
            // 事件推送  
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
//                // 事件类型  
//                String eventType = requestMap.get("Event");  
//                // 订阅  
//                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
//                    respContent = "谢谢您的关注！";  
//                }  
//                // 取消订阅  
//                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
//                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
//                }  
//                // 自定义菜单点击事件  
//                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
//                    // TODO 自定义菜单权没有开放，暂不处理该类消息  
//                }  
//            }  
            JSONObject jsonObject = new JSONObject(responseString);
            if(jsonObject.getInt("code") == 100000){     //回答为文本
            	 text = jsonObject.getString("text");
            	 textMessage.setContent(respContent + "机器人的回答是：" + text);  
                 respMessage = MessageUtil.textMessageToXml(textMessage);  
            }
            else if(jsonObject.getInt("code") == 200000){    //回答为带链接的格式
            	text = jsonObject.getString("text");
            	url_text = jsonObject.getString("url");
            	textMessage.setContent(respContent + "机器人的回答是：" + text + "  链接为：" + url_text);  
                respMessage = MessageUtil.textMessageToXml(textMessage);  
            }
            else{
            	textMessage.setContent(respContent + "机器人的回答是：" + text + "  链接为：" + responseString);  
                respMessage = MessageUtil.textMessageToXml(textMessage);  
            }
           
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return respMessage;  
    }
}
