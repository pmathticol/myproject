package com.webcrawler.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.urls.StandardPieURLGenerator;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

import com.keypoint.PngEncoder;
import com.webcrawler.entity.ResultValues;

public class WebComparsionServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		     String url1 = request.getParameter("url1");
		     String url2 = request.getParameter("url2");
		     if(url1==null||url2==null){
		    		//搜索失败
					PrintWriter out = response.getWriter();
					out.println("<script>alert('请输入需要比较的网址!');history.back();</script>");
					out.flush();
					out.close();
		     }else{
		    	 HttpSession session = request.getSession();
		    	 session.setAttribute("url1", url1);
		    	 session.setAttribute("url2", url2);
		    	 request.getRequestDispatcher("webComparison.jsp").forward(request, response);
		    	 
		     }
		  
		
	}

	

}
