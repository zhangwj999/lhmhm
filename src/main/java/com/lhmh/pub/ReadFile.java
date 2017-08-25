package com.lhmh.pub;

//sum
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class ReadFile {
  public static void main(String[] args) {
    try {
    	//System.out.println("aa");
      Scanner scanner = new Scanner(new File("E://sum.in"));
      Writer writer = new FileWriter("E://sum.out");
      String str="";
      //取第一个值，也是第一行的值
      String line = scanner.nextLine();
      int n=Integer.parseInt(line.split(" ")[0]);
      //取中奖号码，放入Vector
      String oline= "x "+ scanner.nextLine() +" ";
      //String[] ls=line.split(" ");
      //Vector comm=new Vector();
     // for (int l=0;l<7;l++){
    	  
     // }
      int count=0;
      int[] one = new int[]{0,0,0,0,0,0,0};
      for (int i = 0; i < n; i++) {
    	  line=scanner.nextLine();
		  count=0;
    	  String[] ls=line.split(" ");
    	  for (int j=0;j<ls.length;j++){
    		  String pk=ls[j];
    		  //System.out.println(pk);
    		  if (oline.indexOf(" "+pk+" ")>0){
    			//  System.out.println("true");
    			  count++;
    		  }

    	  }
		  if (count>0)
			  one[count-1]=(one[count-1]+1);
    	 // str += scanner.nextInt();
    	  
        }
       
      //line= scanner.nextLine();
    // String[] ls=line.split(" ");
     
     
     // int n = scanner.nextInt();
     // int s = 0;
      
   //   for (int i = 0; i < n; i++) {
   //     s += scanner.nextInt();
    //  }
   //   
      writer.write(String.valueOf(one[6]+" "+one[5]+" "+one[4]+" "+one[3]+" "+one[2]+" "+one[1]+" "+one[0]+" "));
     
      scanner.close();
      writer.close();
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
