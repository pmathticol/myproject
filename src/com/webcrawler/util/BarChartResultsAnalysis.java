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
		dataset.addValue(resultValues.getBaiduTermNum(), "总数", "百度");
		dataset.addValue(resultValues.getAfterExtractbaiduTermNum(), "显示数",
				"百度");
		dataset.addValue(resultValues.getSogouTermNum(), "总数", "搜狗");
		dataset.addValue(resultValues.getAfterExtractsogouTermNum(), "显示数",
				"搜狗");
		dataset.addValue(resultValues.getSosoTermNum(), "总数", "搜搜");
		dataset
				.addValue(resultValues.getAfterExtractsosoTermNum(), "显示数",
						"搜搜");

		return dataset;
	}

	public String getBarChartViewer(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		ResultValues resultValues = (ResultValues) session
				.getAttribute("resultValues");

		DefaultCategoryDataset dataset = getBarChartDataset(resultValues);
		// create the chart...
		JFreeChart chart = ChartFactory.createBarChart3D("词条来源统计", // 图表标题
				"搜索来源", // 目录轴的显示标签
				"数量", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是 false)
				false, // 是否生成工具
				false // 是否生成 URL 链接
				);

		// set the background color for the chart...
		chart.setBackgroundPaint(Color.cyan);
		CategoryPlot plot = chart.getCategoryPlot();// 获得图表区域对象
		// 设置图表的纵轴和横轴org.jfree.chart.axis.CategoryAxis
		org.jfree.chart.axis.CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLowerMargin(0.1);// 设置距离图片左端距离此时为10%
		domainAxis.setUpperMargin(0.1);// 设置距离图片右端距离此时为百分之10
		domainAxis.setCategoryLabelPositionOffset(10);// 图表横轴与标签的距离(10像素)
		domainAxis.setCategoryMargin(0.2);// 横轴标签之间的距离20%
		// domainAxis.setMaximumCategoryLabelLines(1);
		// domainAxis.setMaximumCategoryLabelWidthRatio(0);
		org.jfree.chart.renderer.category.BarRenderer3D renderer;
		renderer = new org.jfree.chart.renderer.category.BarRenderer3D();
		// 设定柱子的属性
		org.jfree.chart.axis.ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setUpperMargin(0.1);// 设置最高的一个柱与图片顶端的距离(最高柱的10%)
		// 显示每个柱的数值，并修改该数值的字体属性
		renderer
				.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelFont(new Font("黑体", Font.BOLD, 12));
		renderer.setBaseItemLabelPaint(Color.black);// 字体为黑色
		renderer.setBaseItemLabelsVisible(true);
		// renderer.setItemLabelGenerator(new
		// StandardCategoryItemLabelGenerator());
		// renderer.setItemLabelFont(new Font("黑体",Font.BOLD,12));//12号黑体加粗
		// renderer.setItemLabelPaint(Color.black);//字体为黑色
		// renderer.setItemLabelsVisible(true);
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
		// 数值显示位置
		plot.setRenderer(renderer);

		plot.setForegroundAlpha(1.0f);// 设置柱的透明度

		plot.setRenderer(renderer);// 使用我们设计的效果
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
