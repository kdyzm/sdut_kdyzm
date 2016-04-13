package com.kdyzm.utils;

import java.io.Writer;

import com.kdyzm.entity.resp.Article;
import com.kdyzm.entity.resp.MusicMessage;
import com.kdyzm.entity.resp.NewsMessage;
import com.kdyzm.entity.resp.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 对象转换成Xml的工具类
 * 
 * @author QCZhengrjA1
 *
 */
public class Object2XmlUtil {
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 普通文本消息对象转换成XML
	 * 
	 * @param message
	 * @return
	 */
	public static String textMessage2Xml(TextMessage message) {
		xstream.alias("xml", TextMessage.class);
		return xstream.toXML(message);
	}

	/**
	 * 音乐消息转换成xml
	 * 
	 * @param musicMessage
	 * @return
	 */
	public static String musicMessage2Xml(MusicMessage musicMessage) {
		xstream.alias("xml", MusicMessage.class);
		return xstream.toXML(musicMessage);
	}

	/**
	 * 图文消息转换成为Xml
	 * 
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessage2Xml(NewsMessage newsMessage) {
		xstream.alias("xml", NewsMessage.class);
		xstream.alias("item", Article.class);
		return xstream.toXML(newsMessage);
	}
}
