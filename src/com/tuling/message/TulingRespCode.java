package com.tuling.message;

import java.awt.List;

public  class TulingRespCode {
/*	��������
	������  {code ,text}  100000  TL_TEXT_DATA
	������ {code, text,url} 200000  TL_LINK_DATA
	������ {code,text,list [{article,source,icon,detailurl},{...}]}  302000  TL_NEWS_DATA	
          ������ {code,text,list[{name,icon,info,detailurl},{...}] }  308000  TL_TUWEN_DATA*/
	
	
	/*100000  �ı�������
	200000  ��ַ������
	301000  С˵
	302000  ����
	304000  Ӧ�á����������
	305000  �г�
	306000  ����
	307000  �Ź�
	308000  �Ż�
	309000  �Ƶ�
	310000  ��Ʊ
	311000  �۸�
	312000  ����
	40001   key�ĳ��ȴ���32λ��
	40002   ��������Ϊ��
	40003   key������ʺ�δ����
	40004   �����������������
	40005   �ݲ�֧�ָù���
	40006   ������������
	40007   ���������ݸ�ʽ�쳣
	50000   �������趨�ġ�ѧ�û�˵�������ߡ�Ĭ�ϻش� */
	public static  int TL_FORMAT_DATA = 50000;
	public static  int TL_TEXT_DATA = 100000;
	public static  int TL_LINK_DATA  = 200000;
	public static  int TL_NOVEL_DATA = 301000;
	public static  int TL_NEWS_DATA = 302000;
	public static  int TL_APP_DATA  = 304000;
	public static  int TL_TRAIN_DATA = 305000;
	public static  int TL_AIRPORT_DATA = 306000;
	public static  int TL_TUAN_DATA = 307000;
	public static  int TL_TUWEN_DATA = 308000;
	public static  int TL_HOTEL_DATA = 309000;
	public static  int TL_LOTTERY_DATA = 310000;
	public static  int TL_PRICE_DATA = 311000;
	public static  int TL_RESTAURANT_DATA = 312000;
	
	public static  int TL_ERROR_LENGTH = 40001;
	public static  int TL_ERROR_EMPTY = 40002;
	public static  int TL_ERROR_INVALID = 40003;
	public static  int TL_ERROR_OUTLIMIT = 40004;
	public static  int TL_ERROR_NOTSUPPORT = 40005;
	public static  int TL_ERROR_SERVERUPDATE = 40006;
	public static  int TL_ERROR_SERVERERROR = 40007;


}
