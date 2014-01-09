<%@page language="java" contentType="text/html" pageEncoding="GBK" %>
<%@ page isErrorPage="true" %>

	<head>
		<title>欢迎使用51SE搜索</title>
</head>
<h1>404 你懂得........</h1>
 <span id="time" style="background:red">5</span>秒钟后自动跳转到首页，如果不跳转，请点击下面链接
        <script language="JavaScript1.2" type="text/javascript">
            <!--
            function delayURL(url) {               //定时跳转
          var delay = document.getElementById("time").innerHTML;
            if(delay > 0) {
            delay--;
            document.getElementById("time").innerHTML = delay;
          } else {
              window.top.location.href = url;
              }
          setTimeout("delayURL('" + url + "')", 1000); 
        }
        //-->
    </script>
    <a href="index.jsp">返回</a>
    <script type="text/javascript">
    delayURL("index.jsp");
    </script>    

