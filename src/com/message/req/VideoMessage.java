package com.message.req;

/**
 * ��Ƶ��Ϣ
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class VideoMessage extends BaseMessage {
	//��ƵID 
	private String MediaId;
	// ��Ƶ��Ϣ������ͼID
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
