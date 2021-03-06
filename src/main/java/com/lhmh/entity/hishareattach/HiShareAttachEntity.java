package com.lhmh.entity.hishareattach;

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
 * @Description: 附件表
 * @author zhangdaihao
 * @date 2017-08-26 22:46:47
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hi_share_attach", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class HiShareAttachEntity implements java.io.Serializable {
	/**医院编号*/
	private java.lang.String comId;
	/**单据编号*/
	private java.lang.String infoId;
	/**行号*/
	private java.lang.Integer seq;
	/**文件类型*/
	private java.lang.String fileType;
	/**文件路径*/
	private java.lang.String fileDocId;
	/**文件名*/
	private java.lang.String fileName;
	/**是否使用0:否,1:是*/
	private java.lang.String isMrb;
	/**id*/
	private java.lang.String id;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  医院编号
	 */
	@Column(name ="COM_ID",nullable=false,length=32)
	public java.lang.String getComId(){
		return this.comId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  医院编号
	 */
	public void setComId(java.lang.String comId){
		this.comId = comId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据编号
	 */
	@Column(name ="INFO_ID",nullable=false,length=32)
	public java.lang.String getInfoId(){
		return this.infoId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据编号
	 */
	public void setInfoId(java.lang.String infoId){
		this.infoId = infoId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  行号
	 */
	@Column(name ="SEQ",nullable=false)
	public java.lang.Integer getSeq(){
		return this.seq;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  行号
	 */
	public void setSeq(java.lang.Integer seq){
		this.seq = seq;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件类型
	 */
	@Column(name ="FILE_TYPE",nullable=true,length=2)
	public java.lang.String getFileType(){
		return this.fileType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件类型
	 */
	public void setFileType(java.lang.String fileType){
		this.fileType = fileType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件路径
	 */
	@Column(name ="FILE_DOC_ID",nullable=true,length=30)
	public java.lang.String getFileDocId(){
		return this.fileDocId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件路径
	 */
	public void setFileDocId(java.lang.String fileDocId){
		this.fileDocId = fileDocId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件名
	 */
	@Column(name ="FILE_NAME",nullable=true,length=128)
	public java.lang.String getFileName(){
		return this.fileName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件名
	 */
	public void setFileName(java.lang.String fileName){
		this.fileName = fileName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否使用0:否,1:是
	 */
	@Column(name ="IS_MRB",nullable=true,length=1)
	public java.lang.String getIsMrb(){
		return this.isMrb;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否使用0:否,1:是
	 */
	public void setIsMrb(java.lang.String isMrb){
		this.isMrb = isMrb;
	}
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
}
