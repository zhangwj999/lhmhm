package com.lhmh.entity.lhpatientnouse;

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
 * @Description: 主键
 * @author zhangdaihao
 * @date 2017-07-09 19:43:28
 * @version V1.0   
 *
 */
@Entity
@Table(name = "LH_PATIENTNOUSE", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LhpatientnouseEntity implements java.io.Serializable {
	/**医院编号*/
	private java.lang.String comId;
	/**住院号*/
	private java.lang.String patientId;
	/**姓名*/
	private java.lang.String patientName;
	/**年龄*/
	private java.lang.String patientAge;
	/**性别 枚举SEX*/
	private java.lang.String patientSex;
	/**科室*/
	private java.lang.String officeId;
	/**医保卡号*/
	private java.lang.String medicalCard;
	/**患者唯一编号*/
	private java.lang.String patientPk;
	/**备注*/
	private java.lang.String fMemo;
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
	 *@return: java.lang.String  住院号
	 */
	@Column(name ="PATIENT_ID",nullable=false,length=20)
	public java.lang.String getPatientId(){
		return this.patientId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  住院号
	 */
	public void setPatientId(java.lang.String patientId){
		this.patientId = patientId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="PATIENT_NAME",nullable=false,length=50)
	public java.lang.String getPatientName(){
		return this.patientName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setPatientName(java.lang.String patientName){
		this.patientName = patientName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年龄
	 */
	@Column(name ="PATIENT_AGE",nullable=false,length=50)
	public java.lang.String getPatientAge(){
		return this.patientAge;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年龄
	 */
	public void setPatientAge(java.lang.String patientAge){
		this.patientAge = patientAge;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别 枚举SEX
	 */
	@Column(name ="PATIENT_SEX",nullable=false,length=50)
	public java.lang.String getPatientSex(){
		return this.patientSex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别 枚举SEX
	 */
	public void setPatientSex(java.lang.String patientSex){
		this.patientSex = patientSex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室
	 */
	@Column(name ="OFFICE_ID",nullable=true,length=2)
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
	 *@return: java.lang.String  医保卡号
	 */
	@Column(name ="MEDICAL_CARD",nullable=false,length=20)
	public java.lang.String getMedicalCard(){
		return this.medicalCard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  医保卡号
	 */
	public void setMedicalCard(java.lang.String medicalCard){
		this.medicalCard = medicalCard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  患者唯一编号
	 */
	@Column(name ="PATIENT_PK",nullable=false,length=20)
	public java.lang.String getPatientPk(){
		return this.patientPk;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  患者唯一编号
	 */
	public void setPatientPk(java.lang.String patientPk){
		this.patientPk = patientPk;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="F_MEMO",nullable=false,length=50)
	public java.lang.String getFMemo(){
		return this.fMemo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setFMemo(java.lang.String fMemo){
		this.fMemo = fMemo;
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
