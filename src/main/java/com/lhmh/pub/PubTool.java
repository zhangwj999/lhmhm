package com.lhmh.pub;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


/**
 * 一些操作的公共类
 * @author 张文杰 2017-8-26 22:18:19
 *
 */
public class PubTool{
	
	/**
	 * 往指定路径写文件
	 * @param filePath 路径
	 * @param is 二进制流
	 * @return
	 */
	@SuppressWarnings("finally")
	public static boolean writeFile( String filePath, InputStream is ){
		boolean writeDone = true;
		BufferedOutputStream bs = null;
		try {
			File f = new File( filePath );
			bs = new BufferedOutputStream( new FileOutputStream( f ) );
			byte[] bytes = new byte[1024];
			while( ( is.read( bytes ) ) != -1 ){
				bs.write( bytes );
			}
			bs.flush();
		} catch (Exception e) {
			e.printStackTrace();
			writeDone = false;
		}finally{
			try {
				bs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return writeDone;
		}
	}
}