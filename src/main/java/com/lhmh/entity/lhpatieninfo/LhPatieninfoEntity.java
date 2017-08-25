package com.lhmh.entity.lhpatieninfo;

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
 * @Description: 病人管理
 * @author zhangdaihao
 * @date 2017-06-11 11:35:03
 * @version V1.0   
 *
 */
@Entity
@Table(name = "PATIENINFO", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LhPatieninfoEntity implements java.io.Serializable {
	/**医院编号*/
	private java.lang.String comId;
	/**住院号*/
	private java.lang.String fId;
	/**姓名*/
	private java.lang.String fName;
	/**年龄*/
	private java.lang.String fAge;
	/**性别 枚举SEX*/
	private java.lang.String fSex;
	/**科室OFFICE_ID*/
	private java.lang.String fType;
	/**医保卡号*/
	private java.lang.String medicalCard;
	/**患者唯一识别编号*/
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
	@Column(name ="F_ID",nullable=false,length=20)
	public java.lang.String getFId(){
		return this.fId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  住院号
	 */
	public void setFId(java.lang.String fId){
		this.fId = fId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="F_NAME",nullable=false,length=50)
	public java.lang.String getFName(){
		return this.fName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setFName(java.lang.String fName){
		this.fName = fName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年龄
	 */
	@Column(name ="F_AGE",nullable=false,length=50)
	public java.lang.String getFAge(){
		return this.fAge;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年龄
	 */
	public void setFAge(java.lang.String fAge){
		this.fAge = fAge;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别 枚举SEX
	 */
	@Column(name ="F_SEX",nullable=false,length=50)
	public java.lang.String getFSex(){
		return this.fSex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别 枚举SEX
	 */
	public void setFSex(java.lang.String fSex){
		this.fSex = fSex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室OFFICE_ID
	 */
	@Column(name ="F_TYPE",nullable=true,length=50)
	public java.lang.String getFType(){
		return this.fType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室OFFICE_ID
	 */
	public void setFType(java.lang.String fType){
		this.fType = fType;
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
	 *@return: java.lang.String  患者唯一识别编号
	 */
	@Column(name ="PATIENT_PK",nullable=false,length=20)
	public java.lang.String getPatientPk(){
		return this.patientPk;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  患者唯一识别编号
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
