package com.webcrawler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ContectWithUrl {
	public static String getContect(String urlString) {
		StringBuffer html_text = new StringBuffer();
		try {
			// 根据urlString创建一个指向Web的网络资源
			URL url = new URL(urlString);
			// 创建一个到该网络资源的连接
			// URLConnection conn = url.openConnection();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty(
							"User-Agent",
							"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");// 增加报头，模拟浏览器，防止屏蔽
			conn.setRequestProperty("Accept", "text/html");// 只接受text/html类型，当然也可以接受图片,pdf,*/*任意，就是tomcat/conf/web里面定义那些
			 conn.setConnectTimeout(30000);
			if( conn.getResponseCode()==200){
			// 创建输入流
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			// 逐行读取文本放到缓冲区
			while ((line = reader.readLine()) != null)
				html_text.append(line + "\n");
			reader.close();
			}
		} catch (MalformedURLException e) {
			System.out.println("无效的URL: " + urlString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String content = html_text.toString();
		return content;
	}

}
