package com.webcrawler.util;

public class Similarity {
	
   public static double getSimilarityofDoc(String a,String b){
	   double sim=0;
	   sim = JElucene.outerProduct(a, b)/(Math.sqrt(JElucene.getScore(a))*Math.sqrt(JElucene.getScore(b)));
	   return sim;
   }
   
}
