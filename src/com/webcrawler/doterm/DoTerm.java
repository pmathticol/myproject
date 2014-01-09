package com.webcrawler.doterm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webcrawler.connectdb.DBConnection;
import com.webcrawler.entity.ResultValues;
import com.webcrawler.entity.Term;


public class DoTerm {
	private Connection con = null;	//保存数据库连接
	private PreparedStatement pstmt = null;	//执行SQL语句
	private ResultSet rs = null;	//保存查询结果集
	
	/**
	 * 清空表中所有的数据
	 * @param table 
	 */
	public int clearTerms(String table){
		DBConnection DB = new DBConnection();
		String sql = "truncate table "+table;
		int result = 0;
		try {
			con = DB.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			  DB.close();
		}
		return result;
	}
	/**
	 * 添加词条
	 * @param table 
	 */
	public int addTerm(Term term, String table){
		DBConnection DB = new DBConnection();
		String sql = "insert into "+table+"(title,linkurl,abstractcontent,fromsource,weight) values (?,?,?,?,?)";
		int result = 0;
		try {
			con = DB.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, term.getTitle());
			pstmt.setString(2, term.getLinkUrl());
			pstmt.setString(3, term.getAbstractContent());
			pstmt.setString(4, term.getFromSource());
			pstmt.setDouble(5, term.getWeight());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			  DB.close();
		}
		return result;
	}
	public int addKeyword(String keyword, String table){
		DBConnection DB = new DBConnection();
		String sql = "update term set keyword = ?  where allterm = ?";
		int result = 0;
		try {
			con = DB.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, table);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			  DB.close();
		}
		return result;
	}
	public void display(List<Term> list){
		for (Term term : list) {
			System.out.println(term.getTitle()+" "+term.getFromSource());
		}
	}
	public List selectShowTerms(ArrayList<String> uniqueList,String table,ResultValues resultValues,boolean baiduflag, boolean sogouflag,
			boolean sosoflag) {
		int baiduNum =0;
		int sogouNum =0;
		int sosoNum =0;
		DBConnection DB = new DBConnection();
		
		String sql = "select *  from "+table+" where linkurl = ? ";
		List list = new ArrayList();
			try {
				con = DB.getConnection();
				pstmt = con.prepareStatement(sql);
			for(String url:uniqueList){
				pstmt.setString(1,url);
				rs = pstmt.executeQuery();
				while(rs.next()){
					String tsql = "select  count(*) as num from "+table+" where linkurl =  '"+url+"'";
					PreparedStatement tpstmt = con.prepareStatement(tsql);
					ResultSet trs = tpstmt.executeQuery();
					trs.next();
					int frequency = trs.getInt("num");
					Term term = new Term();
					term.setTitle(rs.getString("title"));
					term.setLinkUrl(rs.getString("linkurl"));
					term.setAbstractContent(rs.getString("abstractcontent"));
					String img = rs.getString("fromsource");
			
					if(img.equals("baidu")&&baiduflag==true){
						 term.setFromSource("√  "+frequency);
						 ++baiduNum;
						
					}
					else if(img.equals("sogou")&&sogouflag==true){
						term.setFromSource(" √ "+frequency);
						++sogouNum;
						
					}else if(img.equals("soso")&&sosoflag==true){
						term.setFromSource("  √"+frequency);
						++sosoNum;
						
					}else{
						continue;
					}
					resultValues.setAfterExtractbaiduTermNum(baiduNum);
					resultValues.setAfterExtractsogouTermNum(sogouNum);
					resultValues.setAfterExtractsosoTermNum(sosoNum);
				    resultValues.setAfterExtractTermNum(baiduNum+sogouNum+sosoNum);
					list.add(term);
					break;		
				}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
			   DB.close();
			}
			
		return list;
	}
	public ResultValues countTerms(String table){
		ResultValues resultValues = new ResultValues();
		DBConnection DB = new DBConnection();
		String sql = "select count(*) from "+table;
		String baidusql = "select count(*) as num from  "+table+" where fromsource = 'baidu' ";
		String sogousql = "select count(*) as num from "+table+" where fromsource = 'sogou' ";
		String sososql = "select count(*) as num from  "+table+" where fromsource = 'soso' ";
		String yahoosql = "select count(*) as num from  "+table+" where fromsource = 'yahoo' ";
		
		
			try {
				con = DB.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				resultValues.setTotalExtractTermNum(rs.getInt(1));
			//	System.out.println(rs.getInt(1));
				
				pstmt = con.prepareStatement(baidusql);
				rs = pstmt.executeQuery();
				rs.next();
				resultValues.setBaiduTermNum(rs.getInt(1));
			 //   System.out.println(rs.getInt(1));
			    
				pstmt = con.prepareStatement(sogousql);
				rs = pstmt.executeQuery();
				rs.next();
				resultValues.setSogouTermNum(rs.getInt(1));
				// System.out.println(rs.getInt(1));
				 
				pstmt = con.prepareStatement(sososql);
				rs = pstmt.executeQuery();
				rs.next();
				resultValues.setSosoTermNum(rs.getInt(1));
				// System.out.println(rs.getInt(1));
				 
				pstmt = con.prepareStatement(yahoosql);
				rs = pstmt.executeQuery();
				rs.next();
				resultValues.setYahooTermNum(rs.getInt(1));
				// System.out.println(rs.getInt(1));
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
			   DB.close();
			}
			return resultValues;
	}
	public String isFindKeyword(String keyWord) {
		DBConnection DB = new DBConnection();
		String tableString = null;
		String sql = "select allterm from term where keyword = ?";
		int result = 0;
		try {
			con = DB.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyWord);
			rs = pstmt.executeQuery();
			while(rs.next()){
				tableString = rs.getString("allterm");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			  DB.close();
		}
		return tableString;
	}
	public ArrayList<String> selectUniqueLinkurl(String table, String compareKey) {
		DBConnection DB = new DBConnection();
		String sql ="";
		if(compareKey.equals("weight")){
		 sql = "select linkurl from "+table +" group by linkurl order by weight asc";
		}else{
	     sql = "select linkurl, count(*) as num from "+table +" group by linkurl order by num desc";
		}
		ArrayList<String> list = new ArrayList();
			try {
				con = DB.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
		        while(rs.next()){
		        	list.add(rs.getString("linkurl"));
		        }
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				  DB.close();
			}
			return list;
	}
	public List<String> selectAlltems(){
		DBConnection DB = new DBConnection();
		
	    String  sql = "select keyword from term";
		ArrayList<String> list = new ArrayList<String>();
			try {
				con = DB.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
		        while(rs.next()){
		        	list.add(rs.getString("keyword"));
		        }
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				  DB.close();
			}
			return list;
		
	}
	public void uddateTerm() {
		
		DBConnection DB = new DBConnection();
		String sql = "update term set keyword = ?  where allterm = ?";
		try {
			con = DB.getConnection();
       		for(int i=0;i<6;i++){
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, null);
			pstmt.setString(2, "term"+i);
		    int  result = pstmt.executeUpdate();
       		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			  DB.close();
		}
		
	}
}
