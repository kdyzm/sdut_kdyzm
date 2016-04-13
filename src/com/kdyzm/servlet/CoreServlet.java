package com.kdyzm.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kdyzm.service.CoreService;
import com.kdyzm.utils.SignUtil;
import com.kdyzm.utils.StringUtils;

/**
 * 核心请求处理类
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("你好");
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		printRequestContent(request);
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		if (StringUtils.isNullOrEmpty(timestamp) || StringUtils.isNullOrEmpty(nonce)
				|| StringUtils.isNullOrEmpty(signature)) {
			System.out.print("没有验证参数！返回失败的结果！");
			pw.print("");
			pw.close();
			pw=null;
			return;
		}
		if (!SignUtil.checkSignature(signature, timestamp, nonce)) {
			System.out.println("参数错误！校验和失败！");
			pw.print("");
			pw.close();
			pw=null;
			return;
		} else {
			System.out.println("校验成功！");
			String respMessage = CoreService.processRequest(request);
			pw.print(respMessage);
			System.out.print("得到的计算结果：" + respMessage);
			pw.println(echostr);
			pw.close();
			pw = null;
		}
	}

	private void printRequestContent(HttpServletRequest request) {
		System.out.println("收到新的POST请求！" + request.getParameterMap());
		/*
		 * try { InputStream is = request.getInputStream(); BufferedReader br =
		 * new BufferedReader(new InputStreamReader(is)); String str = null;
		 * while ((str = br.readLine()) != null) { System.out.println("收到的消息：" +
		 * str); } } catch (IOException e) { e.printStackTrace(); }
		 */
	}
}
