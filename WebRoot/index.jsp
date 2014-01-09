<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage="databaselinkerror.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>欢迎使用51SE搜索</title>
		<style>
html {
	overflow-y: auto
}

body {
	font: 12px arial;
	text-align: center;
	background: #fff
}

body,p,form,ul,li {
	margin: 0;
	padding: 0;
	list-style: none
}

body,form,#fm {
	position: relative
}

td {
	text-align: left
}

img {
	border: 0
}

a {
	color: #00c
}

a:active {
	color: #f60
}

#u {
	color: #999;
	padding: 4px 10px 5px 0;
	text-align: right
}

#u a {
	margin: 0 5px
}

#u .reg {
	margin: 0
}

#m {
	width: 680px;
	margin: 0 auto;
}

#nv a,#nv b,.btn,#lk {
	font-size: 14px
}

#fm {
	padding-left: 90px;
	text-align: left
}

input {
	border: 0;
	padding: 0
}

#nv {
	height: 19px;
	font-size: 16px;
	margin: 0 0 4px;
	text-align: left;
	text-indent: 117px
}

.s_ipt_wr {
	width: 418px;
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

#lg img {
	vertical-align: top;
	margin-bottom: 3px
}

#lk {
	margin: 33px 0
}

#lk span {
	font: 14px "宋体"
}

#lm {
	height: 60px
}

#lh {
	margin: 16px 0 5px;
	word-spacing: 3px
}

.tools {
	position: absolute;
	top: -4px; *
	top: 10px;
	right: -13px;
}

#mHolder {
	width: 62px;
	position: relative;
	z-index: 296;
	display: none
}

#mCon {
	height: 18px;
	line-height: 18px;
	position: absolute;
	cursor: pointer;
	padding: 0 18px 0 0;
	background: url(http://s1.bdstatic.com/r/www/img/bg-1.0.0.gif) no-repeat
		right -134px;
	background-position: right -136px\9

}

#mCon span {
	color: #00c;
	cursor: default;
	display: block
}

#mCon .hw {
	text-decoration: underline;
	cursor: pointer
}

#mMenu a {
	width: 100%;
	height: 100%;
	display: block;
	line-height: 22px;
	text-indent: 6px;
	text-decoration: none;
	filter: none\9

}

#mMenu,#user ul {
	box-shadow: 1px 1px 2px #ccc;
	-moz-box-shadow: 1px 1px 2px #ccc;
	-webkit-box-shadow: 1px 1px 2px #ccc;
	filter: progid :   DXImageTransform.Microsoft.Shadow (   Strength =   2,
		Direction =   135, Color =   "#cccccc" ) \9;
}

#mMenu {
	width: 56px;
	border: 1px solid #9b9b9b;
	list-style: none;
	position: absolute;
	right: 7px;
	top: 28px;
	display: none;
	background: #fff
}

#mMenu a:hover {
	background: #ebebeb
}

#mMenu .ln {
	height: 1px;
	background: #ebebeb;
	overflow: hidden;
	font-size: 1px;
	line-height: 1px;
	margin-top: -1px
}

#cp,#cp a {
	color: #77c
}

#seth {
	display: none;
	behavior: url(#default#homepage)
}

#setf {
	display: none
}

#sekj {
	margin-left: 14px;
}
</style>

	</head>
	<body onload=change_bodyground() background=img/background.jpg>
		<a href="setPageConfig.jsp">
			<h4 align="right">
				页面显示配置
			</h4> </a>
		<form action="SearcherServlet" method="post">
			
			<img src="css/images/51seLogoMaker.jpg" align="middle"/>
		
			<h1 align="center">
				<span class="s_ipt_wr"><input type="text" name="keyword"
						id="kw" maxlength="100" class="s_ipt" /> </span>

				<span class="s_btn_wr"><input type="submit" value="搜索"
						id="su" class="s_btn" onmousedown="this.className='s_btn s_btn_h'"
						onmouseout="this.className='s_btn'" /> </span>


			</h1>
			<h4 align="center">
				词条来源：
				<input type="checkbox" name="seo" value="baidu" checked="checked" />
				百度
				<input type="checkbox" name="seo" value="sogou" />
				搜狗
				<input type="checkbox" name="seo" value="soso" />
				搜搜
			<!-- 	<input type="checkbox" name="seo" value="yahoo" />
				雅虎 -->
			</h4>
			<!--  			<h4 align="center">
				<input type="radio" name="sortway" value="byweight" checked="checked"/>
					权值
				<input type="radio" name="sortway" value="byfrequency"/>
					频率
					<input type="radio" name="sortway" value="bykeyword"/>
					关键字
			</h4>
-->
		</form>
	</body>
</html>