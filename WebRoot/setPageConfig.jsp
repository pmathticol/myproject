<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>51SE����ҳ������</title>
		<script language="javascript" type="text/javascript">
		
         function closeWindow(){
       
			 window.close();
		}
		</script>
	</head>
	<body>
	<form action="SetPageConfigServlet" method="post">
	 <h3>������Դ����</h3>
	   <h4>�趨��ϣ����ȡÿ����վ���ݵ�ҳ��:
				<select name="PN">
					<option value=2 selected>
						��ȡǰ2ҳ
					</option>
					<option value=4>
						��ȡǰ4ҳ
					</option>
					<option value=6>
						��ȡǰ6ҳ
					</option>
					<option value=8>
						��ȡǰ8ҳ
					</option>
				</select>
				</h4>
	 <h3>���������ʾ����</h3>
	   <h4>�趨��ϣ�����������ʾ������:
				<select name="NR">
					<option value=10 selected>
						ÿҳ��ʾ10��
					</option>
					<option value=20>
						ÿҳ��ʾ20��
					</option>
					<option value=50>
						ÿҳ��ʾ50��
					</option>
					<option value=100>
						ÿҳ��ʾ100��
					</option>
				</select>
				</h4>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="��������" onclick="closeWindow()"/>
  </form>
	</body>
</html>