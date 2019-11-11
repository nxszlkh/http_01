package com.lkh.post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class HttpSendPost {
	private static Logger log = Logger.getLogger(HttpSendPost.class);

	/**
	 * 向指定URL发送POST方法的请求
	 *
	 * @param url  发送请求的URL
	 * @param body 请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPost(String url, byte[] body, int timeout) {
		OutputStream out = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		HttpURLConnection conn = null;
		try {
			URL realUrl = new URL(url);
			log.info(String.format("send http POST request [%s]", url));
			// 打开和URL之间的连接
			conn = (HttpURLConnection) realUrl.openConnection();
			conn.setReadTimeout(timeout);
			// 设置通用的请求属性
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			conn.connect();
			out = conn.getOutputStream();
			out.write(body);
			out.flush();
			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				if (result.length() > 0) {
					result.append("\n");
				}
				result.append(String.format("%s:%s", key, map.get(key)));
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			char[] buffer = new char[1024];
			int len;
			while ((len = in.read(buffer, 0, 1024)) > 0) {
				result.append(buffer, 0, len);
			}
			return new String(result.toString().getBytes(), "utf-8");
		} catch (Exception e) {
			log.error(String.format("发送POST请求出现异常：%s", e.getMessage()), e);
			return null;
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException ex) {
				log.error(ex.getMessage(), ex);
			}
		}
	}
}
