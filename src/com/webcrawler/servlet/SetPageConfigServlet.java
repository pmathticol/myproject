package com.webcrawler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webcrawler.doterm.DoTerm;

public class SetPageConfigServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DoTerm doTerm = new DoTerm();
		String pnvalue = (String) request.getParameter("PN");
		String nrvaule = (String) request.getParameter("NR");

		Cookie pncookie = new Cookie("PN", pnvalue);
		Cookie nrcookie = new Cookie("NR", nrvaule);
		
		pncookie.setMaxAge(60 * 60 * 24);
		nrcookie.setMaxAge(60 * 60 * 24);
		
		response.addCookie(pncookie);
		response.addCookie(nrcookie);
		System.out.println("设置改变,清空缓存记录");
		doTerm.uddateTerm();
		response.sendRedirect("index.jsp");
	}

}