package com.tuling.message;

public class TulingReq {
	private String key = "b819398655bc451a8dbc32a429d922ec";
	private String apiUrl = "http://www.tuling123.com/openapi/api";
	private String info;
	private String userid; //对应微信号，作为机器人回答的上下文
	private String loc;   //非必须。位置信息，请求地理位置相关内容时使用，编码方式UTF-8
	
	public TulingReq(String info,String userid) {
		super();
		this.info = info;
		this.userid = userid;
	}
	
	
	public TulingReq() {
		super();
	}


	public String getKey() {
		return key;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getApiUrl() {
		return apiUrl;
	}


	public String getLoc() {
		return loc;
	}


	public void setLoc(String loc) {
		this.loc = loc;
	}

}
