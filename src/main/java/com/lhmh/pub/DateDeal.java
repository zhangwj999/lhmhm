   	   package com.lhmh.pub;
    	   
    	   import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
    	    
  public class DateDeal {
	  
  	   public static void main(String[] args) {
		   Date date1=new Date();
			Date date2=new Date();
			String bdate="2015-07-03 08:00:51";
			String edate="2015-09-10 23:44:53";
			String s ="192.168.1.229_01_1_20150910_201231_1.mp4";
			int blen=s.length()-21;

			System.out.println(s.substring(blen,blen+15));
			//list=20150911:2015-07-03 08:00:51:2015-09-10 23:44:53:00:127.0.0.1:aaa=0
			String f_date="111";
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(bdate);
				date2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(edate);
				bdate = new SimpleDateFormat("yyyyMMdd").format(date1);
				edate = new SimpleDateFormat("yyyyMMdd").format(date2);
								
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			Calendar dd ;
			int sss=0;
			try {
				sss = getDaySpace(bdate,edate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  System.out.println("f_1date--"+sss);
			for (int i=0;i<=sss;i++){
				f_date=new SimpleDateFormat("yyyyMMdd").format(date1);
				   System.out.println("fx1date--"+f_date);
				dd = Calendar.getInstance();   
				dd.setTime(date1);
				dd.add(Calendar.DATE, 1);
				date1=dd.getTime();
			}
	
			//int time = date1.getDate();
			while (!date1.after(date2)){
				f_date=new SimpleDateFormat("yyyyMMdd").format(date1);
				   System.out.println("f_date--"+f_date);
				dd = Calendar.getInstance();   
				dd.setTime(date1);
				dd.add(Calendar.DATE, 1);
				date1=dd.getTime();
			}
			   System.out.println("f_date--"+f_date);
	   }
  	   
  		public static int getDaySpace(String date1, String date2)
  	            throws ParseException {

  	        int result = 0;

  	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

  	        Calendar c1 = Calendar.getInstance();
  	        Calendar c2 = Calendar.getInstance();

  	        c1.setTime(sdf.parse(date1));
  	        c2.setTime(sdf.parse(date2));

  	        result = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);

  	        return result == 0 ? 1 : Math.abs(result);

  	    }

   //   删除文件夹 
    	   //   param folderPath 文件夹完整绝对路径
    	    
    	   public static void delFolder(String folderPath) {
    	   try {
    	      delAllFile(folderPath); // 删除完里面所有内容
    	      String filePath = folderPath;
    	      filePath = filePath.toString();
    	      File myFilePath = new File(filePath);
    	      myFilePath.delete(); // 删除空文件夹
    	   } catch (Exception e) {
    	      e.printStackTrace();
    	   }
    	   }
    	    
    	   //   删除指定文件夹下所有文件 
    	   //   param path 文件夹完整绝对路径 
    	   public static boolean delAllFile(String path) {
    	   boolean flag = false;
    	   File file = new File(path);
    	   if (!file.exists()) {
    	      return flag;
    	   }
    	   if (!file.isDirectory()) {
    	      return flag;
    	   }
    	   String[] tempList = file.list();
    	   File temp = null;
    	   for (int i = 0; i < tempList.length; i++) {
    	      if (path.endsWith(File.separator)) {
    	       temp = new File(path + tempList[i]);
    	      } else {
    	       temp = new File(path + File.separator + tempList[i]);
    	      }
    	      if (temp.isFile()) {
    	       temp.delete();
    	      }
    	      if (temp.isDirectory()) {
    	       delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
    	       delFolder(path + "/" + tempList[i]);// 再删除空文件夹
    	       flag = true;
    	      }
    	   }
    	   return flag;
    	   } 
    	   }