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
System.out.println("����mysql����ɹ�..."); 
} catch (IOException e) { 
	System.out.println("����mysql����ʧ��..."); 
e.printStackTrace(); 
} 
} 

public void stop(){ 
try { 
//logger.info("�ر�mysql����..."); 
Runtime.getRuntime().exec("cmd /c  net stop MySQL55"); 
//logger.info("�ر�mysql����ɹ�..."); 
} catch (IOException e) { 
//logger.info("�ر�mysql����ʧ��..."); 
e.printStackTrace(); 
} 
} 

//public void backup(){ 
//try { 
////logger.info("�������ݿ�..."); 
//String path = new StringUtil().getWebRoot()+"bat/backup.bat"; 
//Runtime.getRuntime().exec("cmd /c "+path); 
////logger.info("�������ݿ�ɹ�..."); 
//} catch (IOException e) { 
////logger.info("�������ݿ�ʧ��..."); 
//e.printStackTrace(); 
//} 
//} 

public static void main(String args[]) throws IOException{ 
StartMysql.getInstance().start(); 
//StartMysql.getInstance().stop(); 
} 
} 

