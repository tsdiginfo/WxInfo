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
 * ���ķ�����
 * @author Administrator
 *
 */

public class MyService {
	 /** 
     * ����΢�ŷ��������� 
     *  
     * @param request 
     * @return 
     */  
		
    public static String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        // ���ظ�΢�ŷ�������xml��Ϣ  
        String respXml = null;  
        // �ı���Ϣ����  
        String respContent = null;
        String responseString = "";
        String text = "";
        String url_text = "";
        try {  
            // Ĭ�Ϸ��ص��ı���Ϣ����  
        	respContent = "�������쳣�����Ժ��ԣ�";  
            // xml�������  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
            // ���ͷ��ʺţ�open_id��  
            String fromUserName = requestMap.get("FromUserName");  
            // �����ʺ�  
            String toUserName = requestMap.get("ToUserName");  
            // ��Ϣ����  
            String msgType = requestMap.get("MsgType");  
            //Ĭ�ϻظ�
        	TextMessage textMessage = new TextMessage();  
        	textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setContent("С�Żش�" + "����С����˵�������������ѧϰ���Ժ������԰�");
            
            // ���΢���û� ��������ı���Ϣ
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
                //System.out.println("convertToWxMessage:"+TulingResp.convertToWxMessage(tulingResult,"����","����"));
                respContent = TulingResp.convertToWxMessage(tulingResult,fromUserName,toUserName);
            } 
            //���΢���û� ������� ��Ƶ��Ϣ  
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
                //System.out.println("convertToWxMessage:"+TulingResp.convertToWxMessage(tulingResult,"����","����"));
                respContent = TulingResp.convertToWxMessage(tulingResult,fromUserName,toUserName);
            }  
            // ͼƬ��Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = MessageUtil.textMessageToXml(textMessage);
            }  
            // ����λ����Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = MessageUtil.textMessageToXml(textMessage); 
            }  
            // ������Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = MessageUtil.textMessageToXml(textMessage);
            }  
             
            // �¼�����  
//            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
//                // �¼�����  
//                String eventType = requestMap.get("Event");  
//                // ����  
//                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
//                    respContent = "лл���Ĺ�ע��";  
//                }  
//                // ȡ������  
//                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
//                    // TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ  
//                }  
//                // �Զ���˵�����¼�  
//                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
//                    // TODO �Զ���˵�Ȩû�п��ţ��ݲ����������Ϣ  
//                }  
//            }  
            respMessage = respContent;  
           
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return respMessage;  
    }
}
