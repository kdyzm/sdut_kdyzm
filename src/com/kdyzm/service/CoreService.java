package com.kdyzm.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kdyzm.entity.resp.TextMessage;
import com.kdyzm.utils.MessageUtil;
import com.kdyzm.utils.Object2XmlUtil;

public class CoreService {

	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			String respContext = "请求处理异常，请稍后尝试";

			// Xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方账号
			String fromUserName = requestMap.get("FromUserName");
			// 发送方账号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage message = new TextMessage();
			message.setFromUserName(toUserName);
			message.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			message.setToUserName(fromUserName);
			message.setCreateTime(new Date().getTime());
			message.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContext = "您发送的是文本消息";
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContext = "您发送的图片消息";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContext = "您发送的是链接消息";
			}
			// 位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContext = "您发送的是位置消息";
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContext = "您发送的是语音消息";
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContext = "感谢您的订阅--狂盗一枝梅";
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					System.out.print("取消关注的时间触发！");
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					respContext = "自定义菜单单击事件触发！";
				}
			}
			message.setContent(respContext);
			respMessage = Object2XmlUtil.textMessage2Xml(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}

}
