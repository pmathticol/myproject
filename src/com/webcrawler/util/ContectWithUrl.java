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
			// ����urlString����һ��ָ��Web��������Դ
			URL url = new URL(urlString);
			// ����һ������������Դ������
			// URLConnection conn = url.openConnection();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty(
							"User-Agent",
							"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");// ���ӱ�ͷ��ģ�����������ֹ����
			conn.setRequestProperty("Accept", "text/html");// ֻ����text/html���ͣ���ȻҲ���Խ���ͼƬ,pdf,*/*���⣬����tomcat/conf/web���涨����Щ
			 conn.setConnectTimeout(30000);
			if( conn.getResponseCode()==200){
			// ����������
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			// ���ж�ȡ�ı��ŵ�������
			while ((line = reader.readLine()) != null)
				html_text.append(line + "\n");
			reader.close();
			}
		} catch (MalformedURLException e) {
			System.out.println("��Ч��URL: " + urlString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String content = html_text.toString();
		return content;
	}

}
