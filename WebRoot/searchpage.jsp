<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
	<%@ page import="java.sql.*"%>
	<%@page import="com.webcrawler.entity.*"%>
	<%@page import="com.webcrawler.doterm.DoTerm"%>
	<%@ page errorPage="error/error.jsp"%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

		<title>欢迎使用51SE搜索</title>

		<link rel="stylesheet" type="text/css" href="css/searchpagestyle.css" />

		<style>
input {
	border: 0;
	padding: 0
}

.s_ipt_wr {
	width: 250px;
	height: 30px;
	display: inline-block;
	margin-right: 5px;
	background: url(http://s1.bdstatic.com/r/www/img/i-1.0.0.png) no-repeat
		-304px 0;
	border: 1px solid #b6b6b6;
	border-color: #9a9a9a #cdcdcd #cdcdcd #9a9a9a;
	vertical-align: top
}

.s_ipt {
	width: 300px;
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

.s_btn_h {
	background-position: -100px 0
}

.s_btn_wr {
	width: 97px;
	height: 34px;
	display: inline-block;
	background: url(http://s1.bdstatic.com/r/www/img/i-1.0.0.png) no-repeat
		-202px 0; *
	position: relative;
	z-index: 0;
	vertical-align: top
}

.STYLE1 {
	color: #cc2222
}

.adminbutton {
	border: #07A1EE 1px solid;
	background: #BFE7FA;
	CURSOR:hand
}

style type  ="text /css">#www___net {
	font-size: 90%
}

#www___net ul {
	list-style: none;
	margin: 0;
	padding: 0;
	padding-top: 1em;
}

#www___net li {
	display: inline;
}

#www___net a:link,#www___net a:visited {
	margin-right: 0.2em;
	padding: 0.2em 0.6em 0.2em 0.6em;
	color: #A62020;
	background-color: #FCE6EA;
	text-decoration: none;
	border-top: 1px solid #FFFFFF;
	border-left: 1px solid #FFFFFF;
	border-bottom: 1px solid #717171;
	border-right: 1px solid #717171;
}

#www___net a:hover {
	border-top: 1px solid #717171;
	border-left: 1px solid #717171;
	border-bottom: 1px solid #FFFFFF;
	border-right: 1px solid #FFFFFF;
}
</style>
	</head>
	<body text>
		<div id="maincontainer">
			<h4 align="right">
				<a href="index.jsp"> 返回首页 </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</h4>
			<div id="header">
				<form action="SearcherServlet" method="post">
					<table width="815" height="109" border="0">
						<tr>

							<td width="172" rowspan="2">
								<h1 align="center">
									51SE
								</h1>
								&nbsp;
							</td>
							<td width="633" height="61">
								&nbsp; &nbsp;

								<span class="s_ipt_wr"> <input type="text" name="keyword"
										id="kw" maxlength="100" class="s_ipt" value="<%=session.getAttribute("keyword") %>"/> </span>
								<span class="s_btn_wr"><input type="submit" value="搜索"
										id="su" class="s_btn"
										onmousedown="this.className='s_btn s_btn_h'"
										onmouseout="this.className='s_btn'" /> </span>&nbsp;
							</td>
						</tr>
						<tr>
							<td height="40">
								<h4>
									&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 搜索来源：
									<input type="checkbox" name="seo" value="baidu"
										checked="checked" />
									百度
									<input type="checkbox" name="seo" value="sogou" />
									搜狗
									<input type="checkbox" name="seo" value="soso" />
									搜搜
									<!-- 	<input type="checkbox" name="seo" value="yahoo" />
									雅虎&nbsp;-->
								</h4>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<div id="contentwrapper">
				<div id="contentcolumn">
					<jsp:include page="showtermslist.jsp"></jsp:include>

				</div>
			</div>

			<div id="leftcolumn">
				<h4>
					导航栏
				</h4>

				<form action="CompareServlet" method="post">
					<input type="hidden" name="command" />
					<ul>

						<h3 class="STYLE1">
							词条排序方式
						</h3>
						<li>
							<input type="submit" onclick="command.value='byweight';"
								value="权值优先" class="btn;adminbutton"
								onMouseOut="this.style.backgroundColor=''"
								onMouseOver="this.style.backgroundColor='#808080';this.style.CURSOR='hand'" 
								style= "CURSOR:hand" />

						</li>
						<li>
							<input type="submit" onclick="command.value='byfrequency';"
								value="频率优先" class="btn;adminbutton"
								onMouseOut="this.style.backgroundColor=''"
								onMouseOver="this.style.backgroundColor='#808080'" 
                                style= "CURSOR:hand" />
						</li>
						<!-- 						<li>
							<input type="submit" onclick="command.value='bykeyword';"
								value="关键字频率优先" class="btn" />

						</li> -->
					</ul>
				</form>
				<div id="www___net">
					<ul>
						<li>
						&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;<a href="resultsAnalysis.jsp">搜索结果统计分析</a>
						</li>
						<br>
							<br>
								<li>
							&nbsp;&nbsp;&nbsp; &nbsp; &nbsp;<a href="webComparison.jsp">网页相似度比较</a>
								</li>
								<br>
									<br>
					</ul>
				</div>
				<ul>

					<h3 class="STYLE1">
						搜索历史
					</h3>

					<ul>
						<%
							DoTerm doTerm = new DoTerm();
							List<String> list = doTerm.selectAlltems();
							for (String hiskeyword : list) {
								if (hiskeyword != null) {
						%>

						<li>
							<a
								href="SearcherServlet?keyword=<%=hiskeyword%>&flag=<%=hiskeyword%>"><%=hiskeyword%></a>
						</li>
						<%
							}
							}
						%>
						<a href="ClearTermTableServlet">清空历史记录</a>
					</ul>

				</ul>
			</div>

			<div id="push"></div>

		</div>

		<div id="footer">
			<div class="innertube">
				&copy; Copyright 2012
				<a href="http://hi.baidu.com/meifangting123/home">http://hi.baidu.com/meifangting123/home</a> - Design by
				<a href="http://839085482@QQ.COM">frommars</a>
			</div>
		</div>

	</body>
</html>