package com.webcrawler.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

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
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

import com.webcrawler.entity.ResultValues;

public class BarChartResultsAnalysis {

	private DefaultCategoryDataset getBarChartDataset(ResultValues resultValues) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(resultValues.getBaiduTermNum(), "����", "�ٶ�");
		dataset.addValue(resultValues.getAfterExtractbaiduTermNum(), "��ʾ��",
				"�ٶ�");
		dataset.addValue(resultValues.getSogouTermNum(), "����", "�ѹ�");
		dataset.addValue(resultValues.getAfterExtractsogouTermNum(), "��ʾ��",
				"�ѹ�");
		dataset.addValue(resultValues.getSosoTermNum(), "����", "����");
		dataset
				.addValue(resultValues.getAfterExtractsosoTermNum(), "��ʾ��",
						"����");

		return dataset;
	}

	public String getBarChartViewer(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		ResultValues resultValues = (ResultValues) session
				.getAttribute("resultValues");

		DefaultCategoryDataset dataset = getBarChartDataset(resultValues);
		// create the chart...
		JFreeChart chart = ChartFactory.createBarChart3D("������Դͳ��", // ͼ�����
				"������Դ", // Ŀ¼�����ʾ��ǩ
				"����", // ��ֵ�����ʾ��ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������ false)
				false, // �Ƿ����ɹ���
				false // �Ƿ����� URL ����
				);

		// set the background color for the chart...
		chart.setBackgroundPaint(Color.cyan);
		CategoryPlot plot = chart.getCategoryPlot();// ���ͼ���������
		// ����ͼ�������ͺ���org.jfree.chart.axis.CategoryAxis
		org.jfree.chart.axis.CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLowerMargin(0.1);// ���þ���ͼƬ��˾����ʱΪ10%
		domainAxis.setUpperMargin(0.1);// ���þ���ͼƬ�Ҷ˾����ʱΪ�ٷ�֮10
		domainAxis.setCategoryLabelPositionOffset(10);// ͼ��������ǩ�ľ���(10����)
		domainAxis.setCategoryMargin(0.2);// �����ǩ֮��ľ���20%
		// domainAxis.setMaximumCategoryLabelLines(1);
		// domainAxis.setMaximumCategoryLabelWidthRatio(0);
		org.jfree.chart.renderer.category.BarRenderer3D renderer;
		renderer = new org.jfree.chart.renderer.category.BarRenderer3D();
		// �趨���ӵ�����
		org.jfree.chart.axis.ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setUpperMargin(0.1);// ������ߵ�һ������ͼƬ���˵ľ���(�������10%)
		// ��ʾÿ��������ֵ�����޸ĸ���ֵ����������
		renderer
				.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelFont(new Font("����", Font.BOLD, 12));
		renderer.setBaseItemLabelPaint(Color.black);// ����Ϊ��ɫ
		renderer.setBaseItemLabelsVisible(true);
		// renderer.setItemLabelGenerator(new
		// StandardCategoryItemLabelGenerator());
		// renderer.setItemLabelFont(new Font("����",Font.BOLD,12));//12�ź���Ӵ�
		// renderer.setItemLabelPaint(Color.black);//����Ϊ��ɫ
		// renderer.setItemLabelsVisible(true);
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
		// ��ֵ��ʾλ��
		plot.setRenderer(renderer);

		plot.setForegroundAlpha(1.0f);// ��������͸����

		plot.setRenderer(renderer);// ʹ��������Ƶ�Ч��
		ChartRenderingInfo info = null;

		try {
			// Create RenderingInfo object
			response.setContentType("text/html");
			info = new ChartRenderingInfo(new StandardEntityCollection());
			BufferedImage chartImage = chart
					.createBufferedImage(640, 400, info);
			// putting chart as BufferedImage in session,
			// thus making it available for the image reading action Action.
			session.setAttribute("chartImage1", chartImage);
			PrintWriter writer = new PrintWriter(response.getWriter());
			ChartUtilities.writeImageMap(writer, "imageMap1", info, true);
			writer.flush();
		} catch (Exception e) {
		}

		String pathInfo = "http://";
		pathInfo += request.getServerName();
		int port = request.getServerPort();
		pathInfo += ":" + String.valueOf(port);
		pathInfo += request.getContextPath();
		String chartViewer = pathInfo + "/servlet/BarResultsAnalysisServlet";
		return chartViewer;
	}
}
