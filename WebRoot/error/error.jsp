<%@page language="java" contentType="text/html" pageEncoding="GBK" %>
<%@ page isErrorPage="true" %>

	<head>
		<title>��ӭʹ��51SE����</title>
</head>
<h1>404 �㶮��........</h1>
 <span id="time" style="background:red">5</span>���Ӻ��Զ���ת����ҳ���������ת��������������
        <script language="JavaScript1.2" type="text/javascript">
            <!--
            function delayURL(url) {               //��ʱ��ת
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
    <a href="index.jsp">����</a>
    <script type="text/javascript">
    delayURL("index.jsp");
    </script>    

