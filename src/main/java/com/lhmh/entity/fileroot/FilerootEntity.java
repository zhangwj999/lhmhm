package com.lhmh.entity.fileroot;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 文档上传磁盘路径
 * @author zhangdaihao
 * @date 2017-07-26 22:43:40
 * @version V1.0   
 *
 */
@Entity
@Table(name = "fileroot", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FilerootEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**机器IP*/
	private java.lang.String mip;
	/**磁盘路径*/
	private java.lang.String rootId;
	/**文件URL*/
	private java.lang.String fileUrl;
	/**文件名称*/
	private java.lang.String fileName;
	/**部门编码*/
	private java.lang.String deptId;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=true,length=32)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机器IP
	 */
	@Column(name ="MIP",nullable=false,length=32)
	public java.lang.String getMip(){
		return this.mip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机器IP
	 */
	public void setMip(java.lang.String mip){
		this.mip = mip;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  磁盘路径
	 */
	@Column(name ="ROOT_ID",nullable=false,length=32)
	public java.lang.String getRootId(){
		return this.rootId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  磁盘路径
	 */
	public void setRootId(java.lang.String rootId){
		this.rootId = rootId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件URL
	 */
	@Column(name ="FILE_URL",nullable=true,length=250)
	public java.lang.String getFileUrl(){
		return this.fileUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件URL
	 */
	public void setFileUrl(java.lang.String fileUrl){
		this.fileUrl = fileUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件名称
	 */
	@Column(name ="FILE_NAME",nullable=true,length=100)
	public java.lang.String getFileName(){
		return this.fileName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件名称
	 */
	public void setFileName(java.lang.String fileName){
		this.fileName = fileName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门编码
	 */
	@Column(name ="DEPT_ID",nullable=true,length=32)
	public java.lang.String getDeptId(){
		return this.deptId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门编码
	 */
	public void setDeptId(java.lang.String deptId){
		this.deptId = deptId;
	}
}
