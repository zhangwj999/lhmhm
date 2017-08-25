package com.lhmh.entity.doctor;

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
 * @Description: 医护人员
 * @author zhangdaihao
 * @date 2015-12-01 12:50:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "doctor", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DoctorEntity implements java.io.Serializable {
	/**编号*/
	private java.lang.String fbh;
	/**姓名*/
	private java.lang.String fname;
	/**科别*/
	private java.lang.String ftype;
	/**备注*/
	private java.lang.String fmemo;
	/**id*/
	private java.lang.String id;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编号
	 */
	@Column(name ="F_BH",nullable=false,length=32)
	public java.lang.String getFbh(){
		return this.fbh;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setFbh(java.lang.String fbh){
		this.fbh = fbh;
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
	 *@return: java.lang.String  科别
	 */
	@Column(name ="F_TYPE",nullable=true,length=2)
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
	@Column(name ="F_MEMO",nullable=true,length=255)
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
