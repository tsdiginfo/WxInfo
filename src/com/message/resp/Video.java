package com.message.resp;

/**
 * 视频model
 * 
 * @author liufeng
 * @date 2013-09-11
 */
public class Video {
	// 通过素材管理中的接口上传多媒体文件，得到的id
	private String MediaId;
	// 锟斤拷锟斤拷图锟斤拷媒锟斤拷id
	//private String ThumbMediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	/*public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}*/
}
