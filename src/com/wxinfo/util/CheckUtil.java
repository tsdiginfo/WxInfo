package com.wxinfo.util;

import java.util.Arrays;  
  
/** 
 * ����΢�Žӿڹ�����: 
 * ������ͨ������signature���������У�飨������У�鷽ʽ���� 
 * ��ȷ�ϴ˴�GET��������΢�ŷ���������ԭ������echostr�������ݣ� 
 * �������Ч����Ϊ�����߳ɹ����������ʧ�ܡ�����/У���������£� 
 *  1����token��timestamp��nonce�������������ֵ������� 
 *  2�������������ַ���ƴ�ӳ�һ���ַ�������sha1���� 
 *  3�������߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢�� 
 * 
 * */  
public class CheckUtil {  
      
      
    private static final String token = "tsdiginfo";  
  
    //��ȡ��������  
    public static boolean checkSignature(String signature,String timestamp,String nonce){  
          
        //����  
        String[] arr=new String[]{token,timestamp,nonce};  
        //����(��token��timestamp��nonce�������������ֵ�������)  
        Arrays.sort(arr);  
        //�����ַ���  
        StringBuffer content = new StringBuffer();  
        for (int i = 0; i < arr.length; i++) {  
            content.append(arr[i]);   
        }  
          
        //sha1����(�����������ַ���ƴ�ӳ�һ���ַ�������sha1����)  
        String temp = sha1Dom.getSha1(content.toString());  
        //����signature(�����߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��)  
        return temp.equals(signature);  
          
    }  
  
}  