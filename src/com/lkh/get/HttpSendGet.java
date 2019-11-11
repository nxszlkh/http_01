package com.lkh.get;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class HttpSendGet {
	private static Logger log = Logger.getLogger(HttpSendGet.class);

	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */

	public static String sendGet(String url, String param, byte[] body, int timeout) {
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		try {
			String urlName = url + "?" + param;
			log.info(String.format("send http GET request [%s]", urlName));
			URL realUrl = new URL(urlName);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setReadTimeout(timeout);
			// 设置通用的请求属性
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
//			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64)");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.getOutputStream().write(body);
			conn.getOutputStream().flush();
			// 建立实际的连接
			// conn.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
			
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			char[] buffer = new char[1024];
			int len;
			while ((len = in.read(buffer, 0, 1024)) > 0) {
				result.append(buffer, 0, len);
			}
			return result.toString();
		} catch (Exception e) {
			log.error(String.format("发送GET请求出现异常：%s", e.getMessage()), e);
			return null;
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				log.error(ex.getMessage(), ex);
			}
		}
	}
}
