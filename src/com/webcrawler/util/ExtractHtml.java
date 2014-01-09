package com.webcrawler.util;
import java.util.regex.Pattern;

/**
 * ��ȡhtml���ı�������
 *
 */
public class ExtractHtml {

	public static String getContent(String content) {
		 return getNoHTMLString(content,1024);
	}
	
	public static String getNoHTMLString(String content,int p){

	if(null==content) return "";
	if(0==p) return "";

	java.util.regex.Pattern p_script; 
	java.util.regex.Matcher m_script; 
	java.util.regex.Pattern p_style; 
	java.util.regex.Matcher m_style; 
	java.util.regex.Pattern p_html; 
	java.util.regex.Matcher m_html; 

	try { 
	String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
	//����script��������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script> } 
	String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
	//����style��������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style> } 
	String regEx_html = "<[^>]+>"; //����HTML��ǩ��������ʽ 

	p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	m_script = p_script.matcher(content); 
	content = m_script.replaceAll(""); //����script��ǩ

	p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	m_style = p_style.matcher(content); 
	content = m_style.replaceAll(""); //����style��ǩ 

	p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	m_html = p_html.matcher(content); 

	content = m_html.replaceAll(""); //����html��ǩ 
	}catch(Exception e) { 
	return "";
	} 

	if(content.length()>p){
	content = content.substring(0, p);
	}
	return content;
	}

}
  