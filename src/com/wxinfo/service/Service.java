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
 * ���ķ�����
 * @author Administrator
 *
 */

public class Service {
	 /** 
     * ����΢�ŷ��������� 
     *  
     * @param request 
     * @return 
     */  
	private static String TULING_API_URL = "http://www.tuling123.com/openapi/api";
	private static String TULING_API_KEY = "b819398655bc451a8dbc32a429d922ec";//ͼ���APIKEY
		
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
            //String respContent = "�������쳣�����Ժ��ԣ�";  
        	respContent = "�������쳣�����Ժ��ԣ�";  
  
            // xml�������  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // ���ͷ��ʺţ�open_id��  
            String fromUserName = requestMap.get("FromUserName");  
            // �����ʺ�  
            String toUserName = requestMap.get("ToUserName");  
            // ��Ϣ����  
            String msgType = requestMap.get("MsgType");  
  
            // �ظ��ı���Ϣ  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
  
            // �ı���Ϣ  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            	
                respContent = "�����͵����ı���Ϣ����˵���ǣ�" + requestMap.get("Content");
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
            // ͼƬ��Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "�����͵���ͼƬ��Ϣ��";  
            }  
            // ����λ����Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "�����͵��ǵ���λ����Ϣ��";  
            }  
            // ������Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "�����͵���������Ϣ��";  
            }  
            // ��Ƶ��Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "�����͵�����Ƶ��Ϣ����˵���ǣ�" + requestMap.get("Recognition");  
                
                //���û����˵�API
                //�����˻ظ��ַ���
                
//                String requesturl = "http://www.tuling123.com/openapi/api?key= b819398655bc451a8dbc32a429d922ec&info="+requestMap.get("Recognition"); 
//                HttpGet request1 = new HttpGet(requesturl); 
//                HttpResponse response = HttpClients.createDefault().execute(request1); 
//                //200����ȷ�ķ����� 
//                if(response.getStatusLine().getStatusCode()==200){ 
//                	String result = EntityUtils.toString(response.getEntity()); 
//                	System.out.println("���ؽ����"+result); 
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
                
                //��ΰ��ַ���תΪJSON ?
                //JSONObject jsonObject = new JSONObject(responseString);
                //text = jsonObject.getString("text");
                //Ŀǰֻ��ȡ�ظ����Ϊ�ı���Ϣ�Ľ������Ϊ����ֻ����Json��textֵ������ȡ
                //�ɸ���responseString��codeֵ����ȡ��Ӧ�Ľ������URL��
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
            JSONObject jsonObject = new JSONObject(responseString);
            if(jsonObject.getInt("code") == 100000){     //�ش�Ϊ�ı�
            	 text = jsonObject.getString("text");
            	 textMessage.setContent(respContent + "�����˵Ļش��ǣ�" + text);  
                 respMessage = MessageUtil.textMessageToXml(textMessage);  
            }
            else if(jsonObject.getInt("code") == 200000){    //�ش�Ϊ�����ӵĸ�ʽ
            	text = jsonObject.getString("text");
            	url_text = jsonObject.getString("url");
            	textMessage.setContent(respContent + "�����˵Ļش��ǣ�" + text + "  ����Ϊ��" + url_text);  
                respMessage = MessageUtil.textMessageToXml(textMessage);  
            }
            else{
            	textMessage.setContent(respContent + "�����˵Ļش��ǣ�" + text + "  ����Ϊ��" + responseString);  
                respMessage = MessageUtil.textMessageToXml(textMessage);  
            }
           
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return respMessage;  
    }
}
