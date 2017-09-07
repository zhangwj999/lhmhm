package com.lhmh.pub;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.lhmh.entity.apply.ApplyEntity;
import com.lhmh.entity.hishareattach.HiShareAttachEntity;


/**
 * 一些操作的公共类
 * @author 张文杰 2017-8-26 22:18:19
 *
 */
public class PubTool{
	private static final Logger logger = Logger.getLogger(PubTool.class);
	/**
	 * 往指定路径写文件
	 * @param filePath 路径
	 * @param is 二进制流
	 * @return
	 */
	@SuppressWarnings("finally")
	public static boolean writeFile( String filePath, InputStream is ){
		if( logger.isDebugEnabled() ){
			logger.debug( "writeFile begin" );
		}
		boolean writeDone = true;
		BufferedOutputStream bs = null;
		try {
			File f = new File( filePath );
			bs = new BufferedOutputStream( new FileOutputStream( f ) );
	        BASE64Decoder decoder = new BASE64Decoder();
	        if( logger.isDebugEnabled() ){
	        	logger.debug( "writeFile is.available() == " + is.available() );
	        }
	        is.read( new byte[0] );
			byte[] bytes = new byte[is.available()];
			is.read( bytes );
	        BASE64Encoder encoder = new BASE64Encoder();
	        String s = encoder.encode( bytes );//返回Base64编码过的字节数组字符串
	        logger.debug( s );
			bs.write( decoder.decodeBuffer( s ) );
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
			if( logger.isDebugEnabled() ){
				logger.debug( "writeFile end" );
			}
			return writeDone;
		}
	}
	
	//复制文件而已
	public static boolean copyFile( String src, String dest ) throws Exception{
		logger.debug( "图片从  " + src + " 复制到 " + dest );
		File srcf = new File( src );
		File destf = new File( dest );
		BufferedInputStream is = new BufferedInputStream( new FileInputStream(srcf) );
		BufferedOutputStream os = new BufferedOutputStream( new FileOutputStream(destf) );
		byte[] bs = new byte[1024];
		while( ( is.read( bs ) ) != -1 ){
			os.write( bs );
		}
		os.flush();
		is.close();
		os.close();
		srcf.delete();
		return true;
	}
	/**
	 * 保存一条附件表记录，一些公用的校验
	 * @param applyId 申请ID
	 * @param path 缓存路径
	 * @param fileName 文件名称
	 * @param SystemService 调用服务的东西
	 * @return
	 */
	public synchronized static HiShareAttachEntity saveAttachEntity( String applyId, 
			String path, String fileName, SystemService systemService ) throws Exception{
		System.out.println( "saveAttachEntity applyId== " + applyId );
		// 部门编码
		TSUser user = ResourceUtil.getSessionUserName();
		TSDepart dept = user.getTSDepart();
		
		String sql = "SELECT ROOT_ID FROM FILEROOT WHERE DEPT_ID = '"+dept.getId()+"'";
		List<String> fileId = systemService.findListbySql(sql);

		String sql1 = "SELECT MAX( CONVERT( SEQ, SIGNED ) ) SEQ FROM HI_SHARE_ATTACH WHERE INFO_ID = '" + applyId + "'";
		List<BigInteger> seqList = systemService.findListbySql(sql1);
		
		int seq = 0;
		if(seqList != null && seqList.size() > 0 && seqList.get(0) != null && !"null".equals(seqList.get(0))){
			seq = seqList.get(0).intValue() + 1;
		}
	
		// 文件名 命名 applyId + seq + .jpeg，路径 ROOT_ID
		if( null == fileName || fileName.isEmpty() )
			fileName = applyId + seq + ".jpeg";
		
		List<ApplyEntity> applys = systemService.findByProperty(
				ApplyEntity.class, "applyId", applyId );
		ApplyEntity apply = applys.get( 0 );
		String docBase = fileId.get( 0 );
		if( docBase == null || "".equals( docBase ) ){
			throw new RuntimeException( "申请单号" + apply + "对应的文件夹根目录未配置！" );
		}
		if( !docBase.endsWith( "/" ) ){
			docBase += "/";
		}
		File f = new File( docBase );
		if( !f.exists() ){
			f.mkdir(); //文件夹不存在先创建文件夹
		}
		copyFile( path, docBase + fileName );
		HiShareAttachEntity attach = new HiShareAttachEntity();
		attach.setComId( apply.getComId() );
		attach.setInfoId( applyId );
		attach.setSeq( seq );
		attach.setFileType( "1" ); // 文件类型    1：申请资料  2：完成资料
		attach.setFileDocId( docBase );
		attach.setFileName( fileName );
		attach.setIsMrb( "1" ); //'是否使用0:否,1:是',
		systemService.save( attach );
		return attach;
	}
	
	//删除单据下的文件
	public static boolean delAttachByApplyId( String applyId, SystemService systemService ){
		// 删除文件
		
		// 删除附件表
		return true;
	}
	
	
	//删除一条附件
	public static boolean delAttachById( String id, SystemService systemService ){
		List<HiShareAttachEntity> list = systemService.findByProperty(
				HiShareAttachEntity.class, "id", id );
		if( list == null || list.size() == 0 )
			return true;
		HiShareAttachEntity attach = list.get( 0 );
		String path = attach.getFileDocId() + attach.getFileName();
		File f = new File( path );
		f.delete();
		systemService.delete( attach );
		// 删除附件表
		return true;
	}
	
	// 查询单据所有的附件
	public static List listAttachByApplyId( String applyId, SystemService systemService ){
		List<HiShareAttachEntity> list = systemService.findByProperty(
				HiShareAttachEntity.class, "infoId", applyId );
		return list;
	}
}