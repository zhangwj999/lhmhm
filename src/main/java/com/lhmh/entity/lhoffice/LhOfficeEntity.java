package com.lhmh.entity.lhoffice;

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
 * @Description: 科室管理
 * @author zhangdaihao
 * @date 2017-06-11 11:29:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "LH_OFFICE", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LhOfficeEntity implements java.io.Serializable {
	/**医院编号*/
	private java.lang.String comId;
	/**科室编号*/
	private java.lang.String officeId;
	/**科室名称*/
	private java.lang.String officeName;
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
	 *@return: java.lang.String  科室编号
	 */
	@Column(name ="OFFICE_ID",nullable=false,length=10)
	public java.lang.String getOfficeId(){
		return this.officeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室编号
	 */
	public void setOfficeId(java.lang.String officeId){
		this.officeId = officeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室名称
	 */
	@Column(name ="OFFICE_NAME",nullable=false,length=50)
	public java.lang.String getOfficeName(){
		return this.officeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室名称
	 */
	public void setOfficeName(java.lang.String officeName){
		this.officeName = officeName;
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
