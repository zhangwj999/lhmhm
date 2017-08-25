package com.lhmh.pub;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

 

public class DownLoad {   
	
  public static void downloadFile(URL theURL, String filePath) throws IOException {  

     File dirFile = new File(filePath);
        if(!dirFile.exists()){//文件路径不存在时，自动创建目录
          dirFile.mkdir();
        }
   //从服务器上获取图片并保存
      URLConnection  connection = theURL.openConnection();
      InputStream in = connection.getInputStream();  
      FileOutputStream os = new FileOutputStream(filePath+"\\123.png"); 
      byte[] buffer = new byte[4 * 1024];  
      int read;  
      while ((read = in.read(buffer)) > 0) {  
          os.write(buffer, 0, read);  
           }  
        os.close();  
        in.close();

   }   

      public static void main(String[] args) {   

        String urlPath = "http://1.1.9.59:8089/image/123.png";   

        String filePath = "d:\\excel";   

        URL url = null;
		try {
			url = new URL(urlPath);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   

          try {   

              downloadFile(url,filePath);   

           } catch (IOException e) {   

            e.printStackTrace();   

         }   

      }   

 
private void sss(){
	//mp3UrlMP3的URL
    String urlPath = "http://1.1.9.59:8089/image/123.png";   
	InputStream in = null;
	try {
		in = new URL(urlPath).openConnection().getInputStream();
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} //创建连接、输入流
	FileOutputStream f = null;
	try {
		f = new FileOutputStream("c:\\mmm.mp3");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//创建文件输出流
	byte [] bb=new byte[1024]; //接收缓存
	int len;
	try {
		while( (len=in.read(bb))>0){ //接收
			try {
				f.write(bb, 0, len);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //写入文件
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		try {
			f.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
//}
 

}

