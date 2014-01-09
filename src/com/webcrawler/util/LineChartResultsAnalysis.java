package com.webcrawler.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartResultsAnalysis {
	double sim = 0;

	private DefaultCategoryDataset getLineChartDataset(String url1, String url2) {
		String a = TextExtract.parse(ContectWithUrl.getContect(url1));
		String b = TextExtract.parse(ContectWithUrl.getContect(url2));
		DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
		// 各曲线名称
		String series1 = "网址一";
		String series2 = "网址二";
		int count = 0;
		HashMap<String, Integer> map1 = JElucene.setM(a);
		HashMap<String, Integer> map2 = JElucene.setM(b);
		sim = Similarity.getSimilarityofDoc(a, b);
		Map.Entry[] entries = getSortedHashtableByValue(map1);
		for (int i = 0; i < entries.length; i++) {
			Map.Entry element = entries[i];
			String word = (String) element.getKey();
			int aV = (Integer) element.getValue();

			Integer bV = JElucene.setM(b).get(word);
			linedataset.addValue(aV, series1, word);
			if (bV != null) {
				linedataset.addValue(bV, series2, word);
			} else {
				linedataset.addValue(0, series2, word);
			}
			++count;
			if (count == 20) {
				break;
			}
		}

		return linedataset;

	}
//Hashmap 按值给键从大到小排序
	public static Map.Entry[] getSortedHashtableByValue(Map map) {
		Set set = map.entrySet();
		Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
				.size()]);
		Arrays.sort(entries, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Long key1 = Long.valueOf(((Map.Entry) arg0).getValue()
						.toString());
				Long key2 = Long.valueOf(((Map.Entry) arg1).getValue()
						.toString());
				return key2.compareTo(key1);
			}
		});
		return entries;
	}

	public String getLineChartViewer(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		String url1 = (String) session.getAttribute("url1");
		String url2 = (String) session.getAttribute("url2");
		DefaultCategoryDataset linedataset = getLineChartDataset(url1, url2);
		session.setAttribute("sim", sim);
		JFreeChart chart = ChartFactory.createLineChart("网页词频比较折线图", 
																		
				"词", 
				"频率", 
				linedataset, 
				PlotOrientation.VERTICAL, 
				true, 
				true, 
				false 
				);
		CategoryPlot plot = chart.getCategoryPlot();
		// customise the range axis...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);
		rangeAxis.setUpperMargin(0.20);
		rangeAxis.setLabelAngle(Math.PI / 2.0);

		ChartRenderingInfo info = null;
		try {
			// Create RenderingInfo object
			response.setContentType("text/html");
			info = new ChartRenderingInfo(new StandardEntityCollection());
			BufferedImage chartImage = chart.createBufferedImage(1240, 400,
					info);
			// putting chart as BufferedImage in session,
			// thus making it available for the image reading action Action.
			session.setAttribute("chartImage3", chartImage);
			PrintWriter writer = new PrintWriter(response.getWriter());
			ChartUtilities.writeImageMap(writer, "imageMap3", info, true);
			writer.flush();
		} catch (Exception e) {
		}

		String pathInfo = "http://";
		pathInfo += request.getServerName();
		int port = request.getServerPort();
		pathInfo += ":" + String.valueOf(port);
		pathInfo += request.getContextPath();
		String chartViewer = pathInfo + "/servlet/LineResultsAnalysisServlet";
		return chartViewer;
	}

}
