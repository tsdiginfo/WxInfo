package com.tuling.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.message.resp.Article;
import com.message.resp.NewsMessage;
import com.message.resp.TextMessage;
import com.wxinfo.util.MessageUtil;

public class TulingResp {
	/*	��������
	������  {code ,text}  100000  TL_TEXT_DATA
	������ {code, text,url} 200000  TL_LINK_DATA
	������ {code,text,list [{article,source,icon,detailurl},{...}]}  302000  TL_NEWS_DATA	
          ������ {code,text,list[{name,icon,info,detailurl},{...}] }  308000  TL_TUWEN_DATA*/
	public static String convertToWxMessage(String tulingResult,String fromUserName,String toUserName){
		String resultWxMessage = "";
		//����tulingResult
        JSONTokener  jsonTokener = new JSONTokener(tulingResult);
        JSONObject jObject = (JSONObject) jsonTokener.nextValue(); 
        Integer resultCode = jObject.getInt("code");
       
        if(TulingRespCode.TL_TEXT_DATA == resultCode){//�������������Ϣ ��ת����΢��TextMessage
        	TextMessage textMessage = new TextMessage();  
        	textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setContent("С�Żش�" + jObject.getString("text"));
            resultWxMessage = MessageUtil.textMessageToXml(textMessage);
        }else if (TulingRespCode.TL_LINK_DATA == resultCode) { //�����������
        	TextMessage linkMessage = new TextMessage();
        	linkMessage.setToUserName(fromUserName);  
        	linkMessage.setFromUserName(toUserName);  
        	linkMessage.setCreateTime(new Date().getTime());  
        	linkMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT); 
        	linkMessage.setContent("С�Żش�" + jObject.getString("text") + " <a href =\"" + jObject.getString("url")+ "\">��ҳ��</a>");
            resultWxMessage = MessageUtil.textMessageToXml(linkMessage);
		}else if (TulingRespCode.TL_NEWS_DATA == resultCode) { //�����������. ��Ӧ΢�ŵ�ͼ����Ϣ
			NewsMessage newsMessage = new NewsMessage();  
            newsMessage.setToUserName(fromUserName);  
            newsMessage.setFromUserName(toUserName);  
            newsMessage.setCreateTime(new Date().getTime());  
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
            List<Article> articleList = new ArrayList<Article>();  
            JSONArray jsonArray = jObject.getJSONArray("list");
            //΢�Żظ���������ͼ����Ϣ��Ϣ��Ĭ�ϵ�һ��itemΪ��ͼ,ע�⣬���ͼ��������10���򽫻�����Ӧ
            //���̫�࣬��ֻ�ظ�4��
            if(jsonArray.length() > 4){
            	for(int i=0 ;i<4;i++){
                	JSONObject object = (JSONObject)jsonArray.get(i);
                	Article article = new Article();  
                	article.setTitle(object.getString("article"));  
                    article.setDescription("");  
                    article.setPicUrl(object.getString("icon"));  
                    article.setUrl(object.getString("detailurl"));  
                    articleList.add(article);  
                    //System.out.println("jsonArray:::: "  + jObject.getString("source"));
                }
            }else{
            	for(int i=0 ;i<jsonArray.length();i++){
                	JSONObject object = (JSONObject)jsonArray.get(i);
                	Article article = new Article();  
                	article.setTitle(object.getString("article"));  
                    article.setDescription("");  
                    article.setPicUrl(object.getString("icon"));  
                    article.setUrl(object.getString("detailurl"));  
                    articleList.add(article);  
                    //System.out.println("jsonArray:::: "  + jObject.getString("source"));
                }
            }
            // ����ͼ����Ϣ����  
            newsMessage.setArticleCount(articleList.size());  
            // ����ͼ����Ϣ������ͼ�ļ���  
            newsMessage.setArticles(articleList);  
            // ��ͼ����Ϣ����ת����xml�ַ���  
            resultWxMessage = MessageUtil.newsMessageToXml(newsMessage);  
		}else if (TulingRespCode.TL_TUWEN_DATA == resultCode) { //����ǲ�����. ��Ӧ΢�ŵ�ͼ����Ϣ
			NewsMessage newsMessage = new NewsMessage();  
            newsMessage.setToUserName(fromUserName);  
            newsMessage.setFromUserName(toUserName);  
            newsMessage.setCreateTime(new Date().getTime());  
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
            List<Article> articleList = new ArrayList<Article>();  
            JSONArray jsonArray = jObject.getJSONArray("list");
          //΢�Żظ���������ͼ����Ϣ��Ϣ��Ĭ�ϵ�һ��itemΪ��ͼ,ע�⣬���ͼ��������10���򽫻�����Ӧ
            //���̫�࣬��ֻ�ظ�4��
            if(jsonArray.length() > 4){
            	for(int i=0 ;i<4;i++){
            		JSONObject object = (JSONObject)jsonArray.get(i);
                 	Article article = new Article();  
                 	article.setTitle(object.getString("name"));  
                     article.setDescription("");  
                     article.setPicUrl(object.getString("icon"));  
                     article.setUrl(object.getString("detailurl"));  
                     articleList.add(article);  
                     //System.out.println("jsonArray:::: "  + jObject.getString("source"));
                }
            }else{
            	 for(int i=0 ;i<jsonArray.length();i++){
                 	JSONObject object = (JSONObject)jsonArray.get(i);
                 	Article article = new Article();  
                 	article.setTitle(object.getString("name"));  
                     article.setDescription("");  
                     article.setPicUrl(object.getString("icon"));  
                     article.setUrl(object.getString("detailurl"));  
                     articleList.add(article);  
                     //System.out.println("jsonArray:::: "  + jObject.getString("source"));
                 }
            }
           
            // ����ͼ����Ϣ����  
            newsMessage.setArticleCount(articleList.size());  
            // ����ͼ����Ϣ������ͼ�ļ���  
            newsMessage.setArticles(articleList);  
            // ��ͼ����Ϣ����ת����xml�ַ���  
            resultWxMessage = MessageUtil.newsMessageToXml(newsMessage);  
		}else {  //Ĭ�ϻظ�
			TextMessage textMessage = new TextMessage();  
        	textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setContent("С�Żش�" + "����С����˵�������������ѧϰ���Ժ������԰�");
            resultWxMessage = MessageUtil.textMessageToXml(textMessage);
		}
        return resultWxMessage;
	}
}
