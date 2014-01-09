<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="java.sql.*"%>
<%@page import="com.webcrawler.entity.*"%>
<%@page import="com.webcrawler.util.MyHighLighter"%>

<%
	int intPageSize = 0;
	int intRowCount;//记录总数
	int intPageCount;//总页数
	int intPage; //页面参数
	String strPage;//定义变量用来传递page
	int i, j, mm;

	//取得当前页码
	strPage = request.getParameter("page");
	Cookie[] AllCookies = request.getCookies();
	boolean intpageCountflag = true;
	//注意在第一次进行加载的时候，只是告诉它有Cookie存在，而没有进行添加。
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
		//定义每页显示10条记录 
		intPageSize = 10;
	}

	//判断初始页面，如果没有显示记录就置为第一页
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
	//取得记录总数保存于intRowCount变量中
	intRowCount = list.size();

	//计算出总页数（记录总数+每页显示的记录-1）/(每页显示的记录）
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
				关于
				<font face="宋体">&nbsp;<em><%=keyword==null?"":keyword%></em>&nbsp;</font> 词条的相关内容
			</th>
			<th colspan="3">
				词条来源
			</th>
		</tr>
		<tr>
			<th>
				百度
			</th>
			<th>
				搜狗
			</th>
			<th>
				搜搜
			</th>
			<th>
				<font size="2" face="仿宋_GB2312" color="#2F4F4F">频率</font>
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
				<font size=2 face="仿宋_GB2312" color="Black"><%=abstractContent%></font>
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
				<font size="3" face="仿宋_GB2312" color="#2F4F4F"><strong><em><%=ch%></em></strong>
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
		<a href="searchpage.jsp?page=<%=intPage - 1%>">上一页</a> &nbsp;
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
		<a href="searchpage.jsp?page=<%=intPage + 1%> ">下一页</a> &nbsp;&nbsp;第<%=strPage == null ? 1 : strPage%>页
		<%
 	}
 %>
	</h4>
</ul>




