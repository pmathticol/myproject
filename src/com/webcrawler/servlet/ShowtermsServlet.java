package com.webcrawler.servlet;

import java.io.IOException;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webcrawler.searchterm.Spider;

public class ShowtermsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("GBK2312");
		
//		HttpSession session = request.getSession();
//        String table1 = (String) session.getAttribute("table1");
//			String table2 = (String) session.getAttribute("table2");
//	
//			
//			String []seo = (String[]) session.getAttribute("seo");
//			
//			boolean baiduflag=false,sogouflag=false,sosoflag=false,yahooflag=false;
//
//			for(int i=0;i<seo.length;i++){
//				if(seo[i].equals("baidu")){
//					baiduflag = true;
//				}else if(seo[i].equals("sogou")){
//					sogouflag =true;
//				}else if(seo[i].equals("soso")){
//					sosoflag =true;
//				}
//				
//			}
//			
//			String sortway = (String) session.getAttribute("sortway");
//			
//			
//		    
//			
//			Spider.addExtractWeb(baiduflag, sogouflag, sosoflag, yahooflag,termMap, keyword, pageNum);
//		
//
//		
//			Comparator<Map.Entry<String, JudgeValues>> comparator = null;
//			comparator = new ComparatorTermByWeight();
//			if(sortway.equals("byweight")){ 
//				comparator = new ComparatorTermByWeight();
//			}else if(sortway.equals("byfrequency")){
//				comparator = new ComparatorTermByFrequency();
//			}else {
//				list = doTerm.selectAllTerms();
//				session.setAttribute("list", list);
//				request.getRequestDispatcher("showtermslist.jsp").forward(request, response);
//			}
//			
//			
//			
//			List<Map.Entry<String, JudgeValues>> infoIds = new ArrayList<Map.Entry<String, JudgeValues>>( 
//					termMap.getTermMap().entrySet()); 
//			Collections.sort(infoIds,comparator );
//			
//			list = doTerm.selectAllTerms(infoIds);
//		
//			session.setAttribute("termMap",termMap);
			request.getRequestDispatcher("CompareServlet").forward(request, response);
		}
//	}

}
