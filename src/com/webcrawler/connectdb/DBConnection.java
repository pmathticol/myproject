package com.webcrawler.connectdb;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection{
	public static final String DBDRIVER = "com.mysql.jdbc.Driver" ;
	public static final String DBURL = "jdbc:mysql://localhost:3306/termdb" ;
	public static final String DBUSER = "root" ;
	public static final String DBPASS = "root" ;
	private Connection conn = null ;
	public DBConnection(){
		try{
			Class.forName(DBDRIVER) ;
			this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS) ;
			System.out.println("it is success connect mysql");
		}catch(Exception e){
			e.printStackTrace() ;
		}
	}
	public  Connection getConnection(){
		return this.conn ;
	}
	public void close(){
		if(this.conn!=null){
			try{
				this.conn.close() ;
			}catch(Exception e){}
		}
	}
	public static void main(String args[]){
		
		DBConnection conn=new DBConnection();
		
	}

}
	    