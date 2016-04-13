package com.kdyzm.entity.resp;

import com.kdyzm.entity.resp.base.BaseResponseMessage;

public class TextMessage extends BaseResponseMessage{
	// 回复的消息内容  
    private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}  
    
}
