package com.kdyzm.entity.req;

import com.kdyzm.entity.req.base.BaseRequestMessage;
/**
 * 普通的文本消息
 * @author QCZhengrjA1
 *
 */
public class TextMessage extends BaseRequestMessage{
	 // 消息内容  
    private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
