package com.lhmh.pub;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;

import com.lhmh.entity.apply.ApplyEntity;
import com.lhmh.entity.hishareattach.HiShareAttachEntity;


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
	
	/**
	 * 保存一条附件表记录，一些公用的校验
	 * @param applyId 申请ID
	 * @param InputStream 文档流
	 * @param SystemService 调用服务的东西
	 * @return
	 */
	public static boolean saveAttachEntity( String applyId, 
			InputStream is, SystemService systemService ) throws Exception{
		System.out.println( "saveAttachEntity applyId== " + applyId );
		
		// 部门编码
		TSUser user = ResourceUtil.getSessionUserName();
		TSDepart dept = user.getTSDepart();
		
		String sql = "SELECT ROOT_ID FROM FILEROOT WHERE DEPT_ID = '"+dept.getId()+"'";
		List<String> fileId = systemService.findListbySql(sql);

		String sql1 = "SELECT MAX(SEQ) SEQ FROM HI_SHARE_ATTACH WHERE INFO_ID = '" + applyId + "'";
		List<String> seqList = systemService.findListbySql(sql1);
		
		int seq = 0;
		if(seqList != null && seqList.size() > 0 && seqList.get(0) != null && !"null".equals(seqList.get(0))){
			seq = Integer.parseInt(seqList.get(0)) + 1;
		}
	
		// 文件名 命名 applyId + seq + .jpeg，路径 ROOT_ID
		String fileName = applyId + seq + ".jpeg";
		
		List<ApplyEntity> applys = systemService.findByProperty(
				ApplyEntity.class, "applyId", applyId );
		ApplyEntity apply = applys.get( 0 ) ;
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
		boolean writeDone = PubTool.writeFile( docBase + fileName, is );
		HiShareAttachEntity attach = new HiShareAttachEntity();
		if( writeDone ){
			attach.setComId( apply.getComId() );
			attach.setInfoId( applyId );
			attach.setSeq( seq + "" );
			attach.setFileType( "1" ); // 文件类型    1：申请资料  2：完成资料
			attach.setFileDocId( docBase + fileName );
			attach.setFileName( fileName );
			attach.setIsMrb( "1" ); //'是否使用0:否,1:是',
		}
		systemService.save( attach );
		return true;
	}
}