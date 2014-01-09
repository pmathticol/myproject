package com.webcrawler.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webcrawler.doterm.DoTerm;

public class ClearTermTableServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          DoTerm doTerm = new DoTerm();
          doTerm.uddateTerm();
      	request.getRequestDispatcher("searchpage.jsp").forward(request,
				response);
	}

}