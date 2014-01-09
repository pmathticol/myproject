package com.webcrawler.searchterm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.webcrawler.doterm.DoTerm;
import com.webcrawler.entity.Term;
import com.webcrawler.util.ContectWithUrl;
import com.webcrawler.util.ExtractHtml;

public class ExtractTermsFromSogou {
	public static DoTerm doTerm = new DoTerm();
	public static String url = "http://www.sogou.com/";

	public static void getTermsInfor(String urlString, int pageNum,
			String table) throws Exception {
		System.out.println("正在提取" + urlString + "搜狗网站的词条的信息..........");
		int weight = 0;
		String title = null;
		String linkUrl = null;
		String abstractContent = null;
		String content = ContectWithUrl.getContect(urlString);
		Pattern pt_text = Pattern.compile("<!-- a -->(.*)<!-- z -->",
				Pattern.MULTILINE | Pattern.DOTALL);
		Matcher curPageInfor = pt_text.matcher(content);

		while (curPageInfor.find()) {
			String curPage = curPageInfor.group(1).trim();

			String[] oneresult = curPage.split("<!--STATUS VR OK-->");

			Pattern pt_term = Pattern
					.compile(
							"<h3 class=\"(.*?)\">(.*?)<(.*?) href=\"(.*?)\"(.*?)>(.*?)</a>(.*?)</h3>(.*)<!--summary_end-->",
							Pattern.MULTILINE | Pattern.DOTALL);
			for (int i = 0; i < oneresult.length; i++) {
				Matcher curResult = pt_term.matcher(oneresult[i]);
				while (curResult.find()) {
					linkUrl = curResult.group(4).trim();
					title = ExtractHtml.getContent(curResult.group(6).trim());
					abstractContent = ExtractHtml.getContent(curResult.group(7)
							.trim()
							+ curResult.group(8).trim());
					Term term = new Term();
					term.setLinkUrl(linkUrl);
					term.setTitle(title);
					term.setFromSource("sogou");
					term.setWeight(++weight + 0.25 + pageNum * 10);
					term.setAbstractContent(abstractContent);
					doTerm.addTerm(term, table);
					System.out.println(title);
				}

			}
		}
	}

}
