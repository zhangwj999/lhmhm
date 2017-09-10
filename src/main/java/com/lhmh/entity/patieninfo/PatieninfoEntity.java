package com.lhmh.entity.patieninfo;

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
 * @Description: 病人信息
 * @author zhangdaihao
 * @date 2015-08-15 18:01:59
 * @version V1.0   
 *
 */
@Entity
@Table(name = "patieninfo", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class PatieninfoEntity implements java.io.Serializable {
	/**公司号*/
	private java.lang.String comId;
	/**住院号*/
	private java.lang.String fid;
	/**姓名*/
	private java.lang.String fname;
	/**年龄*/
	private java.lang.String fage;
	/**性别*/
	private java.lang.String fsex;
	/**科别*/
	private java.lang.String ftype;
	/**备注*/
	private java.lang.String fmemo;
	/**id*/
	private java.lang.String id;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司号
	 */
	@Column(name ="COM_ID",nullable=false,length=30)
	public java.lang.String getComId(){
		return this.comId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  住院号
	 */
	@Column(name ="F_ID",nullable=false,length=20)
	public java.lang.String getFid(){
		return this.fid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  住院号
	 */
	public void setFid(java.lang.String fid){
		this.fid = fid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="F_NAME",nullable=false,length=50)
	public java.lang.String getFname(){
		return this.fname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setFname(java.lang.String fname){
		this.fname = fname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年龄
	 */
	@Column(name ="F_AGE",nullable=false,length=50)
	public java.lang.String getFage(){
		return this.fage;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年龄
	 */
	public void setComId(java.lang.String comId){
		this.comId = comId;
	}
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年龄
	 */
	public void setFage(java.lang.String fage){
		this.fage = fage;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="F_SEX",nullable=false,length=50)
	public java.lang.String getFsex(){
		return this.fsex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setFsex(java.lang.String fsex){
		this.fsex = fsex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科别
	 */
	@Column(name ="F_TYPE",nullable=false,length=50)
	public java.lang.String getFtype(){
		return this.ftype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科别
	 */
	public void setFtype(java.lang.String ftype){
		this.ftype = ftype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="F_MEMO",nullable=false,length=50)
	public java.lang.String getFmemo(){
		return this.fmemo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setFmemo(java.lang.String fmemo){
		this.fmemo = fmemo;
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
