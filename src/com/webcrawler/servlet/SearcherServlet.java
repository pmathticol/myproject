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
		System.out.println("输入关键字  :--" + keyWord);

		if (keyWord == null||keyWord.equals("")) {
			// 搜索失败
			request.getRequestDispatcher("searchpage.jsp").forward(request,
					response);
		} else {
			// 搜索成功的话转向ShowtermsServlet
		
			try {
				HttpSession session = request.getSession();
				DoTerm doTerm = new DoTerm();
				session.setAttribute("keyword", keyWord);
				session.setAttribute("seo", seo);
				session.setAttribute("sortway", sortway);
				String table = "term0";
				String tableName = doTerm.isFindKeyword(keyWord);
				System.out.println("检查关键字是否存在？");
				if (tableName == null) {
					System.out.println("关键字不存在，设置其存储的表名");
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
					System.out.println("该关键字将被存储在表" + table);
					request.getRequestDispatcher("SpidertermsServlet").forward(
							request, response);
				} else {
					System.out.println("该关键字已经存在，在表" + tableName);
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
