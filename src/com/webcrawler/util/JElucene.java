package com.webcrawler.util;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

public class JElucene {

	public static HashMap<String , Integer> setM(String str){
	MMAnalyzer analyzer = new MMAnalyzer();
	Reader reader ;
	reader = new StringReader(str);
	TokenStream stream = analyzer.tokenStream(null, 
			reader);
	 HashMap<String , Integer> m = new HashMap<String, Integer>();
	try {
		for(Token t =stream.next();t!=null;t = stream.next()){
		  Integer value = m.get(t.term());
		  m.put(t.term(), value==null?1:value+1);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return m;
    } 

    public static int getScore(String str){
    	int score=0;
    	for (Iterator it = setM(str).entrySet().iterator();it.hasNext();) {
    		Map.Entry element = (Entry) it.next();
    		int sword = (Integer) element.getValue();
    		score += sword*sword;
    	}
    	return score;	
    }
    
    public static int outerProduct(String a,String b){
		int out =0;
		for (Iterator it = setM(a).entrySet().iterator();it.hasNext();) {
			Map.Entry element = (Entry) it.next();
			String word = (String) element.getKey();
			int aV = (Integer) element.getValue();
		 
			Integer bV =setM(b).get(word);
		//	System.out.println(word+" "+aV+" "+bV);
			if(bV!=null){
				out +=aV*bV;
			}else{
				out +=0;
			}
		}
	
		return out;
	}

}
