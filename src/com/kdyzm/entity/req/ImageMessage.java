package com.kdyzm.entity.req;

import com.kdyzm.entity.req.base.BaseRequestMessage;
/**
 * 普通的图片消息
 * @author QCZhengrjA1
 *
 */
public class ImageMessage extends BaseRequestMessage{
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	
}
