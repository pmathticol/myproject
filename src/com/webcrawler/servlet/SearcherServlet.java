package com.webcrawler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webcrawler.doterm.DoTerm;

public class SearcherServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");
		request.setCharacterEncoding("GBK");
		String keyWord = null;
		// String keyWord = request.getParameter("keyword");
		String flag = request.getParameter("flag");
		if (flag != null) {
			keyWord = new String(request.getParameter("keyword").getBytes(
					"iso-8859-1"), "GBK");
		} else {
			keyWord = request.getParameter("keyword");
		}
		String[] seo = request.getParameterValues("seo");
		String sortway = request.getParameter("sortway");
		System.out.println("����ؼ���  :--" + keyWord);

		if (keyWord == null||keyWord.equals("")) {
			// ����ʧ��
			request.getRequestDispatcher("searchpage.jsp").forward(request,
					response);
		} else {
			// �����ɹ��Ļ�ת��ShowtermsServlet
		
			try {
				HttpSession session = request.getSession();
				DoTerm doTerm = new DoTerm();
				session.setAttribute("keyword", keyWord);
				session.setAttribute("seo", seo);
				session.setAttribute("sortway", sortway);
				String table = "term0";
				String tableName = doTerm.isFindKeyword(keyWord);
				System.out.println("���ؼ����Ƿ���ڣ�");
				if (tableName == null) {
					System.out.println("�ؼ��ֲ����ڣ�������洢�ı���");
					Cookie[] AllCookies = request.getCookies();
					int num = 0;
					if (AllCookies != null) {

						for (int mm = 0; mm < AllCookies.length; mm++) {
							String cookiename = AllCookies[mm].getName();
							if (cookiename.equals("table")) {
								num = Integer.parseInt(AllCookies[mm].getValue());
								table = "term" + num;
								break;
							}
						}
						Cookie pncookie = new Cookie("table", (++num % 6) + "");
						// pncookie.setMaxAge(60 * 60 * 24);
						response.addCookie(pncookie);
					}
					session.setAttribute("table", table);
					doTerm.clearTerms(table);
					doTerm.addKeyword(keyWord, table);
					System.out.println("�ùؼ��ֽ����洢�ڱ�" + table);
					request.getRequestDispatcher("SpidertermsServlet").forward(
							request, response);
				} else {
					System.out.println("�ùؼ����Ѿ����ڣ��ڱ�" + tableName);
					session.setAttribute("table", tableName);
					request.getRequestDispatcher("CompareServlet").forward(request,
							response);

				}
				   } catch (Exception e) {
				   request.getRequestDispatcher("error/databaselinkerror.jsp").forward(
						request, response);
				   }		

		}
	}
}
