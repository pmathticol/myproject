package com.webcrawler.util; 

import java.io.IOException; 

public class StartMysql { 

private static StartMysql instance =null ; 

private StartMysql(){ 
} 

public static StartMysql getInstance(){ 
if(instance == null){ 
instance = new StartMysql(); 
} 
return instance ; 
} 

public void start(){ 
try { 
Runtime.getRuntime().exec("cmd /c net start MySQL55"); 
System.out.println("启动mysql服务成功..."); 
} catch (IOException e) { 
	System.out.println("启动mysql服务失败..."); 
e.printStackTrace(); 
} 
} 

public void stop(){ 
try { 
//logger.info("关闭mysql服务..."); 
Runtime.getRuntime().exec("cmd /c  net stop MySQL55"); 
//logger.info("关闭mysql服务成功..."); 
} catch (IOException e) { 
//logger.info("关闭mysql服务失败..."); 
e.printStackTrace(); 
} 
} 

//public void backup(){ 
//try { 
////logger.info("备份数据库..."); 
//String path = new StringUtil().getWebRoot()+"bat/backup.bat"; 
//Runtime.getRuntime().exec("cmd /c "+path); 
////logger.info("备份数据库成功..."); 
//} catch (IOException e) { 
////logger.info("备份数据库失败..."); 
//e.printStackTrace(); 
//} 
//} 

public static void main(String args[]) throws IOException{ 
StartMysql.getInstance().start(); 
//StartMysql.getInstance().stop(); 
} 
} 

