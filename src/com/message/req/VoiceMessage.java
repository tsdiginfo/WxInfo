package com.message.req;

/** 
 * ��Ƶ��Ϣ 
 *  
 * @author liufeng 
 * @date 2013-05-19 
 */  
public class VoiceMessage extends BaseMessage {
	// ý��ID  
	private String MediaId;
	// ������ʽ  
	private String Format;
	// �������ı���Ϣ
	private String Recognition;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getRecognition() {
		return Recognition;
	}

	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
}
