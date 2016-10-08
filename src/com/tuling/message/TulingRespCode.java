package com.tuling.message;

import java.awt.List;

public  class TulingRespCode {
/*	返回数据
	文字类  {code ,text}  100000  TL_TEXT_DATA
	链接类 {code, text,url} 200000  TL_LINK_DATA
	新闻类 {code,text,list [{article,source,icon,detailurl},{...}]}  302000  TL_NEWS_DATA	
          菜谱类 {code,text,list[{name,icon,info,detailurl},{...}] }  308000  TL_TUWEN_DATA*/
	
	
	/*100000  文本类数据
	200000  网址类数据
	301000  小说
	302000  新闻
	304000  应用、软件、下载
	305000  列车
	306000  航班
	307000  团购
	308000  优惠
	309000  酒店
	310000  彩票
	311000  价格
	312000  餐厅
	40001   key的长度错误（32位）
	40002   请求内容为空
	40003   key错误或帐号未激活
	40004   当天请求次数已用完
	40005   暂不支持该功能
	40006   服务器升级中
	40007   服务器数据格式异常
	50000   机器人设定的“学用户说话”或者“默认回答” */
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
