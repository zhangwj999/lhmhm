package com.lhmh.pub;  
  
import java.io.File;  
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.Date;
import java.util.List;  
  
public class SearchFile {  
  
    public static void main(String[] args) {  
         //    在此目录中找文件      
        String baseDIR = "E:/LHMH_rec/20150704/";      
        //    找扩展名为txt的文件      
        String fileName = "192.168.1.64*_20150704**.mp4";    
        String begin="2015-07-04 08:00:00";//"2015-07-04 010101";
        String end="2015-07-04 09:44:53";//"2015-07-04 100501";
        //转成日期，获取时间值
        
        List resultList = new ArrayList();  
        findFiles(baseDIR, fileName,resultList);    
        //
        checkDates(begin,end,resultList);
        if (resultList.size() == 0) {     
            System.out.println("No File Fount.");     
        } else {     
            for (int i = 0; i < resultList.size(); i++) {     
                System.out.println(resultList.get(i));//显示查找结果。       
            }     
        }     
  
    }  
  
    private static void checkDates(String begin, String end, List resultList) {
    	for (int i = resultList.size()-1; i > -1; i--) {  
            String tempName = (String) resultList.get(i);  
            //获取文件时间
            String time=tempName.substring(27,33);
            String date=tempName.substring(18,26)+tempName.substring(27,33);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
				Date date1 = sdf.parse(date);
				Date date2 = sdf1.parse(begin);
				Date date3 = sdf1.parse(end);
				  System.out.println(date1.getTime());
				  System.out.println(date2.getTime());
				  System.out.println(date3.getTime());
				if (date1.getTime()>=date2.getTime() && date1.getTime()<=date3.getTime()) {
					   System.out.println("susse");
				}
		      
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            
           // SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //Date date2 = sdf.parse("2008-08-08 12:10:12");  


   
            //int current=Integer.parseInt(time);
           // int begini=Integer.parseInt(begin);
            //int endi=Integer.parseInt(end);
           
            /*if (current>=begini && current<=endi) {
            	continue;
            }else{
            	resultList.remove(i);
            }*/

    	}
		
	}

	/**   
     * 递归查找文件   
     * @param baseDirName  查找的文件夹路径   
     * @param targetFileName  需要查找的文件名   
     * @param fileList  查找到的文件集合   
     */    
    public static void findFiles(String baseDirName, String targetFileName, List fileList) {     
        
        File baseDir = new File(baseDirName);       // 创建一个File对象   
        if (!baseDir.exists() || !baseDir.isDirectory()) {  // 判断目录是否存在   
            System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");  
        }  
        String tempName = null;     
        //判断目录是否存在      
        File tempFile;  
        File[] files = baseDir.listFiles();  
        int count=0;
        for (int i = 0; i < files.length; i++) {  
            tempFile = files[i]; 
            count++;
            if(tempFile.isDirectory()){  
                findFiles(tempFile.getAbsolutePath(), targetFileName, fileList);  
            }else if(tempFile.isFile()){  
                tempName = tempFile.getName();  
           	  //  long time = tempFile.lastModified();//返回文件最后修改时间，是以个long型毫秒数
           //	 String ctime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(time));
        	// System.out.println("tempName="+ctime);
              //  tempFile
                //if(wildcardMatch(targetFileName, tempName)){    
                	  System.out.print(78+"=="+tempName+":"+tempFile.getAbsoluteFile()+"\n");
                    // 匹配成功，将文件名添加到结果集   
                    //fileList.add(tempFile.getAbsoluteFile());  
                	  fileList.add(tempName); 

                //}  
            }  
        }  
    }  

    private static boolean checkDates(String begin, String end, String fileName) {
            //获取文件时间
            String time=fileName.substring(27,33);
            int current=Integer.parseInt(time);
            int begini=Integer.parseInt(begin);
            int endi=Integer.parseInt(end);
           
            if (current>=begini && current<=endi) {
            	return true;
            }else{
            	return false;
            }		
	}
    private static boolean timeCompare(String pattern, String str) {    
    	 File file = new File("D:\\a");
    	 long time = file.lastModified();//返回文件最后修改时间，是以个long型毫秒数
    	 String ctime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(time));
    	 System.out.println(ctime);
		return false;

    }
 
    /**   
     * 通配符匹配   
     * @param pattern    通配符模式   
     * @param str    待匹配的字符串   
     * @return    匹配成功则返回true，否则返回false   
     */    
    private static boolean wildcardMatch(String pattern, String str) {     
        int patternLength = pattern.length();     
        int strLength = str.length();     
        int strIndex = 0;     
        char ch;     
        for (int i = 0; i < patternLength; i++) {     
            ch = pattern.charAt(i);     
            if (ch == '*') {     
                //通配符星号*表示可以匹配任意多个字符      
                while (strIndex < strLength) {     
                    if (wildcardMatch(pattern.substring(i + 1),     
                            str.substring(strIndex))) {     
                        return true;     
                    }     
                    strIndex++;     
                }     
            } else if (ch == '?') {     
                //通配符问号?表示匹配任意一个字符      
                strIndex++;     
                if (strIndex > strLength) {     
                    //表示str中已经没有字符匹配?了。      
                    return false;     
                }     
            } else {     
                if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {     
                    return false;     
                }     
                strIndex++;     
            }     
        }     
        return (strIndex == strLength);     
    }   
    private void aaaa(){
    	//将缓冲文件夹中的文件删除
    	   String s = "D:\\txt";//\\inBuffer\\"+ fileInfo[0] +"\\" + fileID;//文件的绝对路径
    	   File file = new File(s);
    	   if(file.exists()){
    	    boolean d = file.delete();
    	    if(d){
    	     System.out.print("删除成功！");
    	    }else{
    	     System.out.print("删除失败！");
    	    }
    	   }  

 


    }
}  
