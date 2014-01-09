package com.webcrawler.searchterm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CrawlerWeb {
	String url;
	int pageNum;

	public CrawlerWeb(String url, int pageNum) {
		super();
		this.url = url;
		this.pageNum = pageNum;
	}

	public String getUrl() {
		return url;
	}

	public int getPageNum() {
		return pageNum;
	}

}

public class Spider {

	private static ArrayList<CrawlerWeb> url_collection = new ArrayList<CrawlerWeb>();
	private static ExecutorService service = Executors
			.newFixedThreadPool(16000);

	public static void addExtractWeb(String keyword, int pagenum, String table)
			throws IOException {

		for (int i = 0; i < pagenum; i++) {
			url_collection.add(new CrawlerWeb("http://www.baidu.com/" + "s?wd="
					+ keyword + "&pn=" + i * 10 + "&usm=1", i));
		}
		for (int i = 1; i <= pagenum; i++) {	
			url_collection
					.add(new CrawlerWeb(
							"http://www.sogou.com/"
									+ "web?query="
									+ keyword
									+ "&page="
									+ i
									+ "&p=40040100&dp=1&w=01019900&dr=1&_asf=www.sogou.com&_ast=1318830848",
							i));
		}

		for (int i = 1; i <= pagenum; i++) {
			url_collection.add(new CrawlerWeb("http://www.soso.com/" + "q?w="
					+ keyword
					+ "&lr=&sc=web&ch=w.p&num=10&gid=&cin=&site=&sf=0&sd=0&pg="
					+ i, i));
		}
		while (Spider.url_collection.size() != 0) {
			CrawlerWeb crawlerWeb = url_collection.remove(0);
			System.out.println("当前爬去的网址是:" + crawlerWeb.getUrl());
			webCrawler(crawlerWeb.getUrl(), crawlerWeb.getPageNum(), table);

		}
		try {
			Thread.currentThread();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void webCrawler(final String url_str, final int pageNum,
			final String table) {
		service.execute(new Runnable() {
			public void run() {
				try {
					ExtractTermsFromBaiDu
							.getTermsInfor(url_str, pageNum, table);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ExtractTermsFromSogou
							.getTermsInfor(url_str, pageNum, table);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					ExtractTermsFromSoso.getTermsInfor(url_str, pageNum, table);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
// public static  void main(String []args){
//	 
//	 Spider sp=new Spider();
//	 sp.webCrawler("http://baike.soso.com/v1261888.htm", 3, "term0");
// }

}
