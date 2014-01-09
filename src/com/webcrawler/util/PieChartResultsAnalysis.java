package com.webcrawler.util;

import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
//import org.jfree.util.Rotation;
//import org.jfree.*;

import com.webcrawler.entity.ResultValues;

public class PieChartResultsAnalysis {

	private DefaultPieDataset getPieChartDataset(ResultValues resultValues) {
		// �������ݼ�
		DefaultPieDataset dataset = new DefaultPieDataset();
		double baidu = resultValues.getAfterExtractbaiduTermNum
      
		() *1.0/ resultValues.getAfterExtractTermNum();
		double sogou = resultValues.getAfterExtractsogouTermNum

		() *1.0/ resultValues.getAfterExtractTermNum();
		double soso = resultValues.getAfterExtractsosoTermNum

		() *1.0/ resultValues.getAfterExtractTermNum();
	    if((baidu>0)&&(sogou>0)&&(soso>0)){
		dataset.setValue("�ٶ�", baidu);
		dataset.setValue("�ѹ�", sogou);
		dataset.setValue("����", sogou);
	    }
		return dataset;
	}

	public String getPieChartViewer(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		ResultValues resultValues = (ResultValues) session
				.getAttribute("resultValues");
		DefaultPieDataset dataset = getPieChartDataset(resultValues);
		JFreeChart chart = ChartFactory.createPieChart3D("������Դ�ֲ�ͼ", dataset, true, true, false); 
		PiePlot3D pieplot3d = (PiePlot3D) chart.getPlot(); 
		pieplot3d.setStartAngle(150D);  
		pieplot3d.setDirection(Rotation.CLOCKWISE); 
		pieplot3d.setForegroundAlpha(0.5F); 
		pieplot3d.setLabelGenerator(new StandardPieSectionLabelGenerator(   
	                StandardPieToolTipGenerator.DEFAULT_TOOLTIP_FORMAT));   
		pieplot3d.setLabelGenerator(new StandardPieSectionLabelGenerator(   
	                "{0}={1}({2})", NumberFormat.getNumberInstance(),   
	                new DecimalFormat("0.00%")));   

		pieplot3d.setNoDataMessage("��������ʾ"); 
		       
		ChartRenderingInfo info = null; 
		try { 
		//Create RenderingInfo object 
		response.setContentType("text/html"); 
		info = new ChartRenderingInfo(new StandardEntityCollection()); 
		BufferedImage chartImage = chart.createBufferedImage(640, 400, info); 
		// putting chart as BufferedImage in session, 
		// thus making it available for the image reading action Action. 
		session.setAttribute("chartImage2", chartImage); 
		PrintWriter writer = new PrintWriter(response.getWriter()); 
		ChartUtilities.writeImageMap(writer, "imageMap2", info, true); 
		writer.flush(); 
		} catch (Exception e) { } 

		String pathInfo = "http://"; 
		pathInfo += request.getServerName(); 
		int port = request.getServerPort(); 
		pathInfo += ":"+String.valueOf(port); 
		pathInfo += request.getContextPath(); 
		String chartViewer = pathInfo + "/servlet/PieResultsAnalysisServlet"; 
		return chartViewer; 
	}

}
