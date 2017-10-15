package com.lhmh.pub;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

public class HwpfTest {  
    
   public static void main( String args[] ) throws Exception {  
      String templatePath = "D:\\apply_template.doc";  
      InputStream is = new FileInputStream(templatePath);
      System.out.println( is.available() );
      HWPFDocument doc = new HWPFDocument(is);
//      System.out.println( is.available() );
      System.out.println(  doc.characterLength() );
      System.out.println( doc.getDataStream().length );
      System.out.println( doc.getTableStream().length );
      Range range = doc.getRange();
      System.out.println( range.text() );
      //把range范围内的${reportDate}替换为当前的日期  
//      range.replaceText("${name}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));  
      OutputStream os = new FileOutputStream("D:\\write.doc");  
      //把doc输出到输出流中  
      doc.write(os);  
      os.flush();
      os.close();
//      closeStream(os);  
//      closeStream(is);  
   }  
   /** 
    * 关闭输入流 
    * @param is 
    */  
   private static void closeStream(InputStream is) {  
      if (is != null) {  
         try {  
            is.close();  
         } catch (IOException e) {  
            e.printStackTrace();  
         }  
      }  
   }  
   
   /** 
    * 关闭输出流 
    * @param os 
    */  
   private static void closeStream(OutputStream os) {  
      if (os != null) {  
         try {  
            os.close();  
         } catch (IOException e) {  
            e.printStackTrace();  
         }  
      }  
   }  
    
   
}  