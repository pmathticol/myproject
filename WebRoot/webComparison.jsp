<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
	<jsp:useBean id="lineChart" scope="session"class="com.webcrawler.util.LineChartResultsAnalysis" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>欢迎使用51SE搜索--</title>
		<style type="text/css">
		.s_ipt {
	width: 405px;
	height: 22px;
	font: 16px/ 22px arial;
	margin: 5px 0 0 7px;
	background: #fff;
	outline: none;
	-webkit-appearance: none
}
.s_btn {
	width: 95px;
	height: 32px;
	padding-top: 2px\9;
	font-size: 14px;
	background: #ddd url(http://s1.bdstatic.com/r/www/img/i-1.0.0.png);
	cursor: pointer
}
/*Eric Meyer's based CSS tab*/
#tablist {
	padding: 3px 0;
	margin-left: 0;
	margin-bottom: 0;
	margin-top: 0.1em;
	font: bold 12px Verdana;
}

#tablist li {
	list-style: none;
	display: inline;
	margin: 0;
}

#tablist li a {
	text-decoration: none;
	padding: 3px 0.5em;
	margin-left: 3px;
	border: 1px solid #778;
	border-bottom: none;
	background: white;
}

#tablist li a:link,#tablist li a:visited {
	color: navy;
}

#tablist li a:hover {
	color: #000000;
	background: #C1C1FF;
	border-color: #227;
}

#tablist li a.current {
	background: lightyellow;
}
</style>
	</head>

	<body>
	<h4 align="right">
				<a href="searchpage.jsp"> 返回搜索页面 </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</h4>
	<h3>输入比较网址:</h3>
		<form action="WebComparsionServlet" method="post">
		网址一：<input type="text" name="url1" id="kw" maxlength="100"
			class="s_ipt" />（左侧）<br/>
		网址二：<input type="text" name="url2" id="kw" maxlength="100"
			class="s_ipt" />（右侧）
			<input type="submit" value="比较" id="su" class="s_btn" />
			</form>
   <%
      String keyword1 =(String) session.getAttribute("url1");
      String keyword2 = (String) session.getAttribute("url2");
      
   %>
		<table width="1366" height="300" border="1">
			<tr>
				<td width="660" height="300">
					<iframe id="tabiframe1" src="<%=keyword1==null?"index.jsp":keyword1 %>" width="95%" height="300px"></iframe>
					&nbsp;
				</td>
				<td width="660">
					<iframe id="tabiframe2" src="<%=keyword2==null?"index.jsp":keyword2 %>" width="95%" height="300px"></iframe>
					&nbsp;
				</td>
			</tr>
		</table>
<h3>词频出现频率折线图分部：</h3>
<% String  linechartViewer  = lineChart.getLineChartViewer(request,response);
 %> 
<img src="<%= linechartViewer %>" border=0 usemap="#imageMap3"> 
<br>根据计算,网页相似度为:<%=(Double)session.getAttribute("sim") %>
	</body>
</html>
