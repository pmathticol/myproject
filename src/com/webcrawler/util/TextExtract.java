package com.webcrawler.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 在线性时间内抽取主题类（新闻、博客等）网页的正文。 采用了<b>基于行块分布函数</b>的方法，为保持通用性没有针对特定网站编写规则。
 */
public class TextExtract {

	private static List<String> lines;
	private final static int blocksWidth;
	private static int threshold;
	private static String html;
	private static boolean flag;
	private static int start;
	private static int end;
	private static StringBuilder text;
	private static ArrayList<Integer> indexDistribution;

	static {
		lines = new ArrayList<String>();
		indexDistribution = new ArrayList<Integer>();
		text = new StringBuilder();
		blocksWidth = 3;// 以Ctext中的行号为轴，取其周围K行合起来称为一个行块Cblock；
		flag = false;
		/* 当待抽取的网页正文中遇到成块的新闻标题未剔除时，只要增大此阈值即可。*/
		/* 阈值增大，准确率提升，召回率下降；值变小，噪声会大，但可以保证抽到只有一句话的正文 */
		threshold = 86;
	}

	public static void setthreshold(int value) {
		threshold = value;
	}

	public static String parse(String _html) {
		return parse(_html, false);
	}

	public static String parse(String _html, boolean _flag) {
		flag = _flag;
		html = _html;
		preProcess();
		return getText();
	}

	private static void preProcess() {
		html = ExtractHtml.getContent(html);
	}

	private static String getText() {
		lines = Arrays.asList(html.split("\n"));
		indexDistribution.clear();

		for (int i = 0; i < lines.size() - blocksWidth; i++) {
			int wordsNum = 0;
			for (int j = i; j < i + blocksWidth; j++) {
				lines.set(j, lines.get(j).replaceAll("\\s+", ""));
				wordsNum += lines.get(j).length();//一个Cblock，去掉其中的所有空白符（\n,\r,\t等）后的字符总数称为该行块的长度
			}
			indexDistribution.add(wordsNum);
		}

		start = -1;
		end = -1;
		boolean boolstart = false, boolend = false;
		text.setLength(0);

		for (int i = 0; i < indexDistribution.size() - 1; i++) {
			if (indexDistribution.get(i) > threshold && !boolstart) {
				if (indexDistribution.get(i + 1).intValue() != 0
						|| indexDistribution.get(i + 2).intValue() != 0
						|| indexDistribution.get(i + 3).intValue() != 0) {
					boolstart = true;
					start = i;
					continue;
				}
			}
			if (boolstart) {
				if (indexDistribution.get(i).intValue() == 0
						|| indexDistribution.get(i + 1).intValue() == 0) {
					end = i;
					boolend = true;
				}
			}
			StringBuilder tmp = new StringBuilder();
			if (boolend) {
				// System.out.println(start+1 + "\t\t" + end+1);
				for (int ii = start; ii <= end; ii++) {
					if (lines.get(ii).length() < 5)
						continue;
					tmp.append(lines.get(ii) + "\n");
				}
				String str = tmp.toString();
				// System.out.println(str);
				if (str.contains("Copyright") || str.contains("版权所有"))
					continue;
				text.append(str);
				boolstart = boolend = false;
			}
		}
		return text.toString();
	}
}
