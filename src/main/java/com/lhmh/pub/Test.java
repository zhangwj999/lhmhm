package com.lhmh.pub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Test {

	/**
	 * @param args
	 */
	public static void maind(String[] args) {
		Connection conn = null;  


		
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");//加入oracle的驱动，“”里面是驱动的路径    
			String url = "jdbc:db2://10.110.1.71:1521:v6db";// 数据库连接，oracle代表链接的是oracle数据库；thin:@MyDbComputerNameOrIP代表的是数据库所在的IP地址（可以保留thin:）；1521代表链接数据库的端口号；ORCL代表的是数据库名称  
			String UserName = "v6user";// 数据库用户登陆名 ( 也有说是 schema 名字的 )  
			String Password = "inspur69";// 密码 
			conn = DriverManager.getConnection(url, UserName, Password);
			Statement pmts = conn.createStatement();
			ResultSet set = pmts.executeQuery("select 'aafff' as aa from dual");
			while (set.next()){
				System.out.println("sucess"+set.getString(1));
			}
			System.out.println("sucess");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Connection conn = null;  
        List command = new ArrayList<String>();
        command.add( "ffmpeg");
        command.add("-i");
        command.add("19.mp4");
        command.add("-ab");
        command.add("56");
        command.add("-ar");
        command.add("22050");
        command.add("-b");
        command.add("500");
        command.add("-r");
        command.add("15");
        command.add("-s");
        command.add("1280x720");
        command.add("aaav.mp4");
		
        System.out.println("wwwww"+command.toString());
		
		
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//加入oracle的驱动，“”里面是驱动的路径    
			String url = "jdbc:oracle:thin:@10.110.1.71:1521:v6db";// 数据库连接，oracle代表链接的是oracle数据库；thin:@MyDbComputerNameOrIP代表的是数据库所在的IP地址（可以保留thin:）；1521代表链接数据库的端口号；ORCL代表的是数据库名称  
			String UserName = "v6user";// 数据库用户登陆名 ( 也有说是 schema 名字的 )  
			String Password = "inspur69";// 密码 
			conn = DriverManager.getConnection(url, UserName, Password);
			Statement pmts = conn.createStatement();
			ResultSet set = pmts.executeQuery("select 'aafff' as aa from dual");
			while (set.next()){
				System.out.println("sucess"+set.getString(1));
			}
			System.out.println("sucess");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main1(String[] args) {
		// TODO Auto-generated method stub

		List list1=new ArrayList();
		Map map1=new HashMap();
		map1.put("x","qd");
		map1.put("id",23);
		map1.put("name","11");
		list1.add(map1);
	Map map2=new HashMap();		
		map2.put("id",15);
		map2.put("name","22");
		//list1.add(map2);
		Map map3=new HashMap();		
		map3.put("id",17);
		map3.put("name","33");
		//list1.add(map3);
		System.out.println("list1="+list1);
		List list2=new ArrayList();
		int j=0;
		for(int i=0;i<1;i++){
			Map newmap =(Map)list1.get(i);
			int id=(Integer)newmap.get("id");
			while(id>10){
				j++;
				Map map =new HashMap();//(Map)list1.get(i);
				  Set  set=newmap.entrySet();  
				Iterator   iterator=set.iterator();   

		          while (iterator.hasNext()) { 

		            Map.Entry  mapentry = (Map.Entry) iterator.next();   

		            System.out.println(mapentry.getKey()+"/"+ mapentry.getValue());  
		            map.put(mapentry.getKey(), mapentry.getValue());
		          }  
				int newid=10;
				int s=id-newid;
				map.put("id",s); 
				map.put("name","新新新新"+s); 
				list2.add(map);
				id=s;
			
			}
			System.out.println("list21="+list2);
			Map newmap1 =(Map)list1.get(i);
			newmap1.put("id",id); 
			newmap1.put("name","新"+id); 
			list2.add(newmap1);
		}
		System.out.println("list2="+list2);
	
	}

}
