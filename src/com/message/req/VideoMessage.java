package com.message.req;

/**
 * 视频信息
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class VideoMessage extends BaseMessage {
	//视频ID 
	private String MediaId;
	// 视频消息的缩略图ID
	private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
