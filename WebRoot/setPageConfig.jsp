<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>51SE搜索页面设置</title>
		<script language="javascript" type="text/javascript">
		
         function closeWindow(){
       
			 window.close();
		}
		</script>
	</head>
	<body>
	<form action="SetPageConfigServlet" method="post">
	 <h3>词条来源数量</h3>
	   <h4>设定您希望提取每个网站数据的页数:
				<select name="PN">
					<option value=2 selected>
						提取前2页
					</option>
					<option value=4>
						提取前4页
					</option>
					<option value=6>
						提取前6页
					</option>
					<option value=8>
						提取前8页
					</option>
				</select>
				</h4>
	 <h3>搜索结果显示条数</h3>
	   <h4>设定您希望搜索结果显示的条数:
				<select name="NR">
					<option value=10 selected>
						每页显示10条
					</option>
					<option value=20>
						每页显示20条
					</option>
					<option value=50>
						每页显示50条
					</option>
					<option value=100>
						每页显示100条
					</option>
				</select>
				</h4>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="保存设置" onclick="closeWindow()"/>
  </form>
	</body>
</html>