<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="java.sql.*"%>
<%@page import="com.webcrawler.entity.*"%>
<%@page import="com.webcrawler.util.MyHighLighter"%>

<%
	int intPageSize = 0;
	int intRowCount;//��¼����
	int intPageCount;//��ҳ��
	int intPage; //ҳ�����
	String strPage;//���������������page
	int i, j, mm;

	//ȡ�õ�ǰҳ��
	strPage = request.getParameter("page");
	Cookie[] AllCookies = request.getCookies();
	boolean intpageCountflag = true;
	//ע���ڵ�һ�ν��м��ص�ʱ��ֻ�Ǹ�������Cookie���ڣ���û�н�����ӡ�
	if (AllCookies == null) {
		intpageCountflag = false;
	} else {
		for (mm = 0; mm < AllCookies.length; mm++) {
			String cookiename = AllCookies[mm].getName();
			if (cookiename.equals("NR")) {
				intPageSize = Integer.parseInt(AllCookies[mm]
						.getValue());
				intpageCountflag = true;
				break;
			}
		}
		if (mm == AllCookies.length) {
			intpageCountflag = false;
		}
	}

	if (!intpageCountflag) {
		//����ÿҳ��ʾ10����¼ 
		intPageSize = 10;
	}

	//�жϳ�ʼҳ�棬���û����ʾ��¼����Ϊ��һҳ
	if (strPage == null) {
		intPage = 1;
	} else {
		intPage = Integer.parseInt(strPage);
		if (intPage < 1)
			intPage = 1;
	}
%>
<%
	String keyword = (String) session.getAttribute("keyword");
	List<Term> list = (List<Term>) session.getAttribute("list");
	Iterator iter = list.iterator();
	//ȡ�ü�¼����������intRowCount������
	intRowCount = list.size();

	//�������ҳ������¼����+ÿҳ��ʾ�ļ�¼-1��/(ÿҳ��ʾ�ļ�¼��
	intPageCount = (intRowCount + intPageSize - 1) / intPageSize;
	if (intPage > intPageCount) {
		intPage = intPageCount;
	}
	i = (intPage - 1) * intPageSize;
	for (j = 0; j < i; j++) {
		iter.next();
	}
%>
<table width="618">
	<thead>
		<tr>
			<th colspan="8" rowspan="2" align="left">
				����
				<font face="����">&nbsp;<em><%=keyword==null?"":keyword%></em>&nbsp;</font> �������������
			</th>
			<th colspan="3">
				������Դ
			</th>
		</tr>
		<tr>
			<th>
				�ٶ�
			</th>
			<th>
				�ѹ�
			</th>
			<th>
				����
			</th>
			<th>
				<font size="2" face="����_GB2312" color="#2F4F4F">Ƶ��</font>
			</th>
		</tr>
	</thead>
	<tbody id="tab">
		<%
			i = 0;

			MyHighLighter mhl = new MyHighLighter();
			MyHighLighter mh2 = new MyHighLighter();
			while (i < intPageSize && iter.hasNext()) {
				Term term = (Term) iter.next();
				String title = term.getTitle();
				String linkurl = term.getLinkUrl();
				String abstractContent = term.getAbstractContent();
				String fromsource = term.getFromSource();
				mhl.createIndex(title  ,"index1");
				String ttitle = mhl.search("contents", keyword,"index1");
				mh2.createIndex(abstractContent, "index2");
				String tabstractContent = mh2.search("contents", keyword,"index2");
				if (ttitle != null) {
					title = ttitle;
				}
				if (tabstractContent != null) {
					abstractContent = tabstractContent;
				}
		%>
		<tr>
			<td colspan="8" align="left">
				<h4>
					<a href=<%=linkurl%>><%=title%></a>
				</h4>
				<font size=2 face="����_GB2312" color="Black"><%=abstractContent%></font>
				<br />
			</td>
			<%
				int m = 0;
					char ch;
					for (m = 0; m < fromsource.length() - 1; m++) {
						ch = fromsource.charAt(m);
			%>
			<td width="37" align="center">
				<h3 style="color: #00CC00">
					<strong><%=ch%></strong>
				</h3>
			</td>
			<%
				}
					ch = fromsource.charAt(m);
			%>
			<td width="37" align="center">
				<font size="3" face="����_GB2312" color="#2F4F4F"><strong><em><%=ch%></em></strong>
				</font>
			</td>
		</tr>
		<%
			i++;
			}
		%>


	</tbody>
</table>
<ul id="navigation">
	<h4>
		<%
			if (intPage > 1) {
		%>
		<a href="searchpage.jsp?page=<%=intPage - 1%>">��һҳ</a> &nbsp;
		<%
			}
			for (int onepage = 1; onepage <= intPageCount; onepage++) {
		%>
		<a href="searchpage.jsp?page=<%=onepage%> "><%=onepage%></a> &nbsp;
		<%
			}
		%>
		<%
			if (intPage < intPageCount) {
		%>
		<a href="searchpage.jsp?page=<%=intPage + 1%> ">��һҳ</a> &nbsp;&nbsp;��<%=strPage == null ? 1 : strPage%>ҳ
		<%
 	}
 %>
	</h4>
</ul>




