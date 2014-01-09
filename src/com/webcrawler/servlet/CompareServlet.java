package com.webcrawler.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webcrawler.doterm.DoTerm;
import com.webcrawler.entity.ResultValues;

public class CompareServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("GBK");

		DoTerm doTerm = new DoTerm();
		HttpSession session = request.getSession();

		String[] seo = (String[]) session.getAttribute("seo");
		boolean baiduflag = false, sogouflag = false, sosoflag = false;
		if (seo == null) {
			baiduflag = true;
			sogouflag = true;
			sosoflag = true;
			
		} else {
			for (int i = 0; i < seo.length; i++) {
				if (seo[i].equals("baidu")) {
					baiduflag = true;
				} else if (seo[i].equals("sogou")) {
					sogouflag = true;
				} else if (seo[i].equals("soso")) {
					sosoflag = true;
				}
			}
		}
		String sumbitVaule = (String) request.getParameter("command");
		String compareKey = null;
		if (sumbitVaule != null) {
			if (sumbitVaule.equals("byweight")) {
				compareKey = "weight";
			} else if (sumbitVaule.equals("byfrequency")) {
				compareKey = "frequency";
			} 
		} else {
			compareKey = "weight";
		}

		String table = (String) session.getAttribute("table");

		ResultValues resultValues = doTerm.countTerms(table);

		ArrayList<String> uniqueList = doTerm.selectUniqueLinkurl(table,
				compareKey);
		List showlist = doTerm.selectShowTerms(uniqueList, table, resultValues,
				baiduflag, sogouflag, sosoflag);
		session.setAttribute("list", showlist);
		session.setAttribute("resultValues", resultValues);
		System.out.println("******正在显示信息中........................");
		request.getRequestDispatcher("searchpage.jsp").forward(request,
				response);
	}

}
