package com.lhmh.pub;  
  
import java.io.File;  
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.Date;
import java.util.HashMap;
import java.util.List;  
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("fileSearchService")
@Transactional  
public class FileSearchService extends CommonServiceImpl implements FileSearchServiceI{  
	/**   
     * 递归查找文件   
     * @param baseDirName  查找的文件夹路径   
     * @param targetFileName  需要查找的文件名   
     * @param fileList  查找到的文件集合   
     */    
    public static void findFiles(String baseDirName,String targetFileName, List fileList, String url, List filereal){
         File baseDir = new File(baseDirName);       // 创建一个File对象   
        if (!baseDir.exists() || !baseDir.isDirectory()) {  // 判断目录是否存在   
            System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");  
            return;
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
                findFiles(tempFile.getAbsolutePath(), targetFileName, fileList,url,filereal);  
            }else if(tempFile.isFile()){  
                tempName = tempFile.getName();  
                if(wildcardMatch(targetFileName, tempName)){    
                	 // System.out.print(78+"=="+tempName+":"+tempFile.getAbsoluteFile()+"\n");
                    // 匹配成功，将文件名添加到结果集   
                    //fileList.add(tempFile.getAbsoluteFile()); 
                	  Map map=new HashMap();
                	  map.put("real", tempFile.getAbsoluteFile());
                	  map.put("url", url+"/"+tempName);
                	  fileList.add(tempName); 
                	  filereal.add(map);

                }  
            }  
        }  
    
    }
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
    public static void checkDates(String begin, String end, List resultList,List realList) {
    	for (int i = resultList.size()-1; i > -1; i--) {  
            String tempName = (String) resultList.get(i);  
            //获取文件时间
            int blen=tempName.length()-21;
            String date=tempName.substring(blen,blen+15).replaceAll("_", "");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
				Date date1 = sdf.parse(date);
				Date date2 = sdf1.parse(begin);
				Date date3 = sdf1.parse(end);
			/*	  System.out.println(date1.getTime());
				  System.out.println(date2.getTime());
				  System.out.println(date3.getTime());*/
				if (date1.getTime()>=date2.getTime() && date1.getTime()<=date3.getTime()) {
					continue;
	            }else{
	            	resultList.remove(i);
	            	realList.remove(i);
	            }
		      
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
           

    	}
		
	}
    private static void checkDates(String begin, String end, List resultList) {
    	for (int i = resultList.size()-1; i > -1; i--) {  
            String tempName = (String) resultList.get(i);  
            //获取文件时间
            String time=tempName.substring(27,33);
            int current=Integer.parseInt(time);
            int begini=Integer.parseInt(begin);
            int endi=Integer.parseInt(end);
           
            if (current>=begini && current<=endi) {
            	continue;
            }else{
            	resultList.remove(i);
            }

    	}
		
	}
    private static void checkDates1(String begin, String end, List resultList) {
    	for (int i = resultList.size()-1; i > -1; i--) {  
            String tempName = (String) resultList.get(i);  
            //获取文件时间
            String time=tempName.substring(27,33);
            int current=Integer.parseInt(time);
            int begini=Integer.parseInt(begin);
            int endi=Integer.parseInt(end);
           
            if (current>=begini && current<=endi) {
            	continue;
            }else{
            	resultList.remove(i);
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
    /*暂时不用*/
    private static boolean timeCompare(String pattern, String str) {    
    	 File file = new File("D:\\a");
    	 long time = file.lastModified();//返回文件最后修改时间，是以个long型毫秒数
    	 String ctime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(time));
    	 System.out.println(ctime);
		return false;

    }
    
    public static void main(String[] args) {  
         //    在此目录中找文件      
        String baseDIR = "E:/LHMH_rec/2007/";      
        //    找扩展名为txt的文件      
        String fileName = "192.168.1.64*_20150704**.mp4";    
        String begin="010101";//"2015-07-04 010101";
        String end="090501";//"2015-07-04 100501";
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
}  
