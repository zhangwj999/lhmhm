package com.lhmh.entity.lhdoctor;

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
 * @Description: 专家库管理
 * @author zhangdaihao
 * @date 2017-07-18 20:17:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "LH_DOCTOR", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LhdoctorEntity implements java.io.Serializable {
	/**医院编号*/
	private java.lang.String comId;
	/**医生编号*/
	private java.lang.String doctorId;
	/**医生姓名*/
	private java.lang.String doctorName;
	/**科室*/
	private java.lang.String officeId;
	/**职务，岗位，取枚举POSITIONTYPE*/
	private java.lang.String positionId;
	/**收费类型标准，取枚举PAYKIND*/
	private java.lang.String payKind;
	/**用户编号*/
	private java.lang.String doctorPk;
	/**联系方式*/
	private java.lang.String tel;
	/**优势擅长*/
	private java.lang.String fmemo;
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
	 *@return: java.lang.String  医生编号
	 */
	@Column(name ="DOCTOR_ID",nullable=false,length=32)
	public java.lang.String getDoctorId(){
		return this.doctorId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  医生编号
	 */
	public void setDoctorId(java.lang.String doctorId){
		this.doctorId = doctorId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  医生姓名
	 */
	@Column(name ="DOCTOR_NAME",nullable=false,length=50)
	public java.lang.String getDoctorName(){
		return this.doctorName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  医生姓名
	 */
	public void setDoctorName(java.lang.String doctorName){
		this.doctorName = doctorName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室
	 */
	@Column(name ="OFFICE_ID",nullable=true,length=32)
	public java.lang.String getOfficeId(){
		return this.officeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室
	 */
	public void setOfficeId(java.lang.String officeId){
		this.officeId = officeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职务，岗位，取枚举POSITIONTYPE
	 */
	@Column(name ="POSITION_ID",nullable=true,length=10)
	public java.lang.String getPositionId(){
		return this.positionId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职务，岗位，取枚举POSITIONTYPE
	 */
	public void setPositionId(java.lang.String positionId){
		this.positionId = positionId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收费类型标准，取枚举PAYKIND
	 */
	@Column(name ="PAY_KIND",nullable=true,length=10)
	public java.lang.String getPayKind(){
		return this.payKind;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收费类型标准，取枚举PAYKIND
	 */
	public void setPayKind(java.lang.String payKind){
		this.payKind = payKind;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户编号
	 */
	@Column(name ="DOCTOR_PK",nullable=true,length=32)
	public java.lang.String getDoctorPk(){
		return this.doctorPk;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户编号
	 */
	public void setDoctorPk(java.lang.String doctorPk){
		this.doctorPk = doctorPk;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系方式
	 */
	@Column(name ="TEL",nullable=true,length=50)
	public java.lang.String getTel(){
		return this.tel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系方式
	 */
	public void setTel(java.lang.String tel){
		this.tel = tel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  优势擅长
	 */
	@Column(name ="F_MEMO",nullable=true,length=255)
	public java.lang.String getFmemo(){
		return this.fmemo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  优势擅长
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
