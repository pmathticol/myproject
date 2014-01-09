package com.webcrawler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webcrawler.searchterm.Spider;

public class SpidertermsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("GBK");

		HttpSession session = request.getSession();
		System.out.println("�ؼ��ֲ����ڣ�������ȡ����");
		int pageNum = 0;
		Cookie[] AllCookies = request.getCookies();
		boolean intpageCountflag = true;
		// ע���ڵ�һ�ν��м��ص�ʱ��ֻ�Ǹ�������Cookie���ڣ���û�н�����ӡ�
		if (AllCookies == null) {
			intpageCountflag = false;
		} else {
			int mm;
			for (mm = 0; mm < AllCookies.length; mm++) {
				String cookiename = AllCookies[mm].getName();
				if (cookiename.equals("PN")) {
					pageNum = Integer.parseInt(AllCookies[mm].getValue());
					intpageCountflag = true;
					break;
				}
			}
			if (mm == AllCookies.length) {
				intpageCountflag = false;
			}
		}

		if (!intpageCountflag) {

			pageNum = 2;
		}
		System.out.println("��ȡ��ǰҳ��������Ϣ:" + "����ȡԴ��վǰ" + pageNum + "ҳ��Ϣ");

		String keyword = (String) session.getAttribute("keyword");
		// String table1 = (String) session.getAttribute("table1");
		// String table2 = (String) session.getAttribute("table2");
		String table = (String) session.getAttribute("table");
		// Spider.addExtractWeb(termMap, keyword, pageNum,table1,table2);
		System.out.println("��ʼ��ȡ��Ϣ����������������������");
		Spider.addExtractWeb(keyword, pageNum, table);
		request.getRequestDispatcher("CompareServlet").forward(request,
				response);
	}

}
