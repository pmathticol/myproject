package com.webcrawler.util;

import java.io.IOException;
import java.io.StringReader;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;


//@SuppressWarnings("deprecation")
//@SuppressWarnings("deprecation")
public class MyHighLighter {

//	private String indexPath = "F:\\index";
	private Analyzer analyzer;
	private IndexSearcher searcher;

	public MyHighLighter() {
		analyzer = new MMAnalyzer();
	}
	public MyHighLighter(String keyword) {
		analyzer = new MMAnalyzer();
		MMAnalyzer.addWord(keyword);
	}
    
	public void createIndex(String fileText,String indexPath) throws IOException { // 该方法建立索引
		IndexWriter writer = new IndexWriter(indexPath, analyzer, true);
		
		Document doc = new Document();
		
	//	@SuppressWarnings("deprecation")
		Field field = new Field("contents", fileText, Field.Store.YES,
				Field.Index.TOKENIZED);
		doc.add(field);

		writer.addDocument(doc);
		writer.optimize();
		writer.close();
	}

	//@SuppressWarnings("deprecation")
	public String search(String fieldText, String keyword,String indexPath)
			throws CorruptIndexException, IOException, ParseException { // 检索的方法，并实现高亮显示
		searcher = new IndexSearcher(indexPath);
		String highLightText=null;
		QueryParser queryParse = new QueryParser("contents", analyzer); // 构造QueryParser，解析用户输入的检索关键字
		Query query = queryParse.parse(keyword);
		Hits hits = searcher.search(query);
		for (int i = 0; i < hits.length(); i++) {
			Document doc = hits.doc(i);
			String text = doc.get("contents");
			SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(
					"<font color='red'>", "</font>");
			Highlighter highlighter = new Highlighter(simpleHTMLFormatter,
					new QueryScorer(query));
			highlighter.setTextFragmenter(new SimpleFragmenter(text.length()));
			if (text != null) {
				TokenStream tokenStream = analyzer.tokenStream("contents",
						new StringReader(text));
				try {
					highLightText = highlighter.getBestFragment(tokenStream,
							text);
				} catch (InvalidTokenOffsetsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		searcher.close();
		return highLightText;
	}
//	public static void main(String []args) throws CorruptIndexException, IOException, ParseException{
//		
//		MyHighLighter mhl = new MyHighLighter();
//		String ttitle = mhl.search("contents", "algorithm","index1");
//	}
}
