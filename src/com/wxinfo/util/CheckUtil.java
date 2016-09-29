package com.wxinfo.util;

import java.util.Arrays;  
  
/** 
 * 连接微信接口工具类: 
 * 开发者通过检验signature对请求进行校验（下面有校验方式）。 
 * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容， 
 * 则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下： 
 *  1）将token、timestamp、nonce三个参数进行字典序排序 
 *  2）将三个参数字符串拼接成一个字符串进行sha1加密 
 *  3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信 
 * 
 * */  
public class CheckUtil {  
      
      
    private static final String token = "tsdiginfo";  
  
    //获取三个参数  
    public static boolean checkSignature(String signature,String timestamp,String nonce){  
          
        //数组  
        String[] arr=new String[]{token,timestamp,nonce};  
        //排序(将token、timestamp、nonce三个参数进行字典序排序)  
        Arrays.sort(arr);  
        //生成字符串  
        StringBuffer content = new StringBuffer();  
        for (int i = 0; i < arr.length; i++) {  
            content.append(arr[i]);   
        }  
          
        //sha1加密(将三个参数字符串拼接成一个字符串进行sha1加密)  
        String temp = sha1Dom.getSha1(content.toString());  
        //返回signature(开发者获得加密后的字符串可与signature对比，标识该请求来源于微信)  
        return temp.equals(signature);  
          
    }  
  
}  