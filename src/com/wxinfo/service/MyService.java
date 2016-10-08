package com.wxinfo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.httpclient.util.HttpClientUtil;
import com.message.resp.Article;
import com.message.resp.NewsMessage;
import com.message.resp.TextMessage;
import com.message.resp.Music;
import com.message.resp.MusicMessage;
import com.tuling.message.TulingReq;
import com.tuling.message.TulingResp;
import com.wxinfo.util.MessageUtil;

/**
 * 核心服务类
 * @author Administrator
 *
 */

public class MyService {
	 /** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
		
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
        	respContent = "请求处理异常，请稍候尝试！";  
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
            //默认回复
        	TextMessage textMessage = new TextMessage();  
        	textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setContent("小信回答：" + "俺还小，您说的这个还得慢慢学习，以后再来试吧");
            
            // 如果微信用户 输入的是文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            	String info = requestMap.get("Content");
        		String userid = fromUserName;
        		TulingReq tulingReq = new TulingReq(info,userid);
        		
        		HttpClientUtil httpClientUtil =  new HttpClientUtil(); 
        		Map<String,String> createMap = new HashMap<String,String>();  
                createMap.put("key",tulingReq.getKey());  
                createMap.put("info",tulingReq.getInfo());
                createMap.put("userid",tulingReq.getUserid());
                String tulingResult = httpClientUtil.doPost(tulingReq.getApiUrl(),createMap);  
                //System.out.println("result:"+tulingResult);  
                //System.out.println("convertToWxMessage:"+TulingResp.convertToWxMessage(tulingResult,"张三","李四"));
                respContent = TulingResp.convertToWxMessage(tulingResult,fromUserName,toUserName);
            } 
            //如果微信用户 输入的是 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
            	String info = requestMap.get("Recognition");
        		String userid = fromUserName;
        		TulingReq tulingReq = new TulingReq(info,userid);
        		HttpClientUtil httpClientUtil =  new HttpClientUtil(); 
        		Map<String,String> createMap = new HashMap<String,String>();  
                createMap.put("key",tulingReq.getKey());  
                createMap.put("info",tulingReq.getInfo());
                createMap.put("userid",tulingReq.getUserid());
                String tulingResult = httpClientUtil.doPost(tulingReq.getApiUrl(),createMap);  
                //System.out.println("result:"+tulingResult);  
                //System.out.println("convertToWxMessage:"+TulingResp.convertToWxMessage(tulingResult,"张三","李四"));
                respContent = TulingResp.convertToWxMessage(tulingResult,fromUserName,toUserName);
            }  
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = MessageUtil.textMessageToXml(textMessage);
            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = MessageUtil.textMessageToXml(textMessage); 
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = MessageUtil.textMessageToXml(textMessage);
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
            respMessage = respContent;  
           
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return respMessage;  
    }
}
