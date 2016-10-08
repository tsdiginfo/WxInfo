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
	/*	返回数据
	文字类  {code ,text}  100000  TL_TEXT_DATA
	链接类 {code, text,url} 200000  TL_LINK_DATA
	新闻类 {code,text,list [{article,source,icon,detailurl},{...}]}  302000  TL_NEWS_DATA	
          菜谱类 {code,text,list[{name,icon,info,detailurl},{...}] }  308000  TL_TUWEN_DATA*/
	public static String convertToWxMessage(String tulingResult,String fromUserName,String toUserName){
		String resultWxMessage = "";
		//解析tulingResult
        JSONTokener  jsonTokener = new JSONTokener(tulingResult);
        JSONObject jObject = (JSONObject) jsonTokener.nextValue(); 
        Integer resultCode = jObject.getInt("code");
       
        if(TulingRespCode.TL_TEXT_DATA == resultCode){//如果是文字类消息 则转换成微信TextMessage
        	TextMessage textMessage = new TextMessage();  
        	textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setContent("小信回答：" + jObject.getString("text"));
            resultWxMessage = MessageUtil.textMessageToXml(textMessage);
        }else if (TulingRespCode.TL_LINK_DATA == resultCode) { //如果是链接类
        	TextMessage linkMessage = new TextMessage();
        	linkMessage.setToUserName(fromUserName);  
        	linkMessage.setFromUserName(toUserName);  
        	linkMessage.setCreateTime(new Date().getTime());  
        	linkMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT); 
        	linkMessage.setContent("小信回答：" + jObject.getString("text") + " <a href =\"" + jObject.getString("url")+ "\">打开页面</a>");
            resultWxMessage = MessageUtil.textMessageToXml(linkMessage);
		}else if (TulingRespCode.TL_NEWS_DATA == resultCode) { //如果是新闻类. 对应微信的图文消息
			NewsMessage newsMessage = new NewsMessage();  
            newsMessage.setToUserName(fromUserName);  
            newsMessage.setFromUserName(toUserName);  
            newsMessage.setCreateTime(new Date().getTime());  
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
            List<Article> articleList = new ArrayList<Article>();  
            JSONArray jsonArray = jObject.getJSONArray("list");
            //微信回复：：多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
            //如果太多，则只回复4条
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
            // 设置图文消息个数  
            newsMessage.setArticleCount(articleList.size());  
            // 设置图文消息包含的图文集合  
            newsMessage.setArticles(articleList);  
            // 将图文消息对象转换成xml字符串  
            resultWxMessage = MessageUtil.newsMessageToXml(newsMessage);  
		}else if (TulingRespCode.TL_TUWEN_DATA == resultCode) { //如果是菜谱类. 对应微信的图文消息
			NewsMessage newsMessage = new NewsMessage();  
            newsMessage.setToUserName(fromUserName);  
            newsMessage.setFromUserName(toUserName);  
            newsMessage.setCreateTime(new Date().getTime());  
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
            List<Article> articleList = new ArrayList<Article>();  
            JSONArray jsonArray = jObject.getJSONArray("list");
          //微信回复：：多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
            //如果太多，则只回复4条
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
           
            // 设置图文消息个数  
            newsMessage.setArticleCount(articleList.size());  
            // 设置图文消息包含的图文集合  
            newsMessage.setArticles(articleList);  
            // 将图文消息对象转换成xml字符串  
            resultWxMessage = MessageUtil.newsMessageToXml(newsMessage);  
		}else {  //默认回复
			TextMessage textMessage = new TextMessage();  
        	textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setContent("小信回答：" + "俺还小，您说的这个还得慢慢学习，以后再来试吧");
            resultWxMessage = MessageUtil.textMessageToXml(textMessage);
		}
        return resultWxMessage;
	}
}
