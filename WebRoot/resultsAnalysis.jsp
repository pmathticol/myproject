<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*,com.webcrawler.entity.ResultValues"  errorPage=""%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>��ӭʹ��51SE����</title>
</head> 
<jsp:useBean id="barChart" scope="session"class="com.webcrawler.util.BarChartResultsAnalysis" /> 
<jsp:useBean id="pieChart" scope="session"class="com.webcrawler.util.PieChartResultsAnalysis" /> 
<body>
<%
   String keyword = (String)session.getAttribute("keyword");
   ResultValues resultValues = (ResultValues) session
				.getAttribute("resultValues");
				 int total = resultValues.getTotalExtractTermNum();
  int aftterExtra = resultValues.getAfterExtractTermNum();
  int baidu =resultValues.getAfterExtractbaiduTermNum();
  int sogou = resultValues.getAfterExtractsogouTermNum();
  int soso = resultValues.getAfterExtractsosoTermNum();
  double rate =( 1-aftterExtra*1.00/total)*100;
 %> 
<h2 align="center">�ؼ���"<%=keyword %>"�������ͳ�Ʒ���ͼ</h2> 
<%String barchartViewer = barChart.getBarChartViewer(request, response);%> 
<img src="<%=barchartViewer%>" border=0 usemap="#imageMap1"> 
<% String  piechartViewer  = pieChart.getPieChartViewer(request, response);%> 
<img src="<%= piechartViewer %>" border=0 usemap="#imageMap2"> 
<% 
  if(baidu!=0 && sogou!=0 &&soso!=0){
%>
<h3>����������£�</h3>

 <h4>������ҳ��ȡ<%=total %>����¼,ȥ���ظ���¼������ʾ<%=aftterExtra %>����¼,ȥ����Ϊ<%=rate %>%.</h4>
 <%
 }
  %>
</body> 
</html> 