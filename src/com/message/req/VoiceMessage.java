package com.message.req;

/** 
 * 音频消息 
 *  
 * @author liufeng 
 * @date 2013-05-19 
 */  
public class VoiceMessage extends BaseMessage {
	// 媒体ID  
	private String MediaId;
	// 语音格式  
	private String Format;
	// 语音的文本信息
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
