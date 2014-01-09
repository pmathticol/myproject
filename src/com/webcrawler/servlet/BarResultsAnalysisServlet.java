package com.webcrawler.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.keypoint.PngEncoder;

public class BarResultsAnalysisServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		BufferedImage chartImage = (BufferedImage) session
				.getAttribute("chartImage1");
		// set the content type so the browser can see this as a picture
		response.setContentType("image/png");
		// send the picture
	//	PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
	//	response.getOutputStream().write(encoder.pngEncode());

	}

	

}
