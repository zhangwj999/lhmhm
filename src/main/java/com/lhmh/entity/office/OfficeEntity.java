package com.lhmh.entity.office;

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
 * @Description: 科别
 * @author zhangdaihao
 * @date 2015-08-15 17:01:24
 * @version V1.0   
 *
 */
@Entity
@Table(name = "office", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class OfficeEntity implements java.io.Serializable {
	/**科别编号*/
	private java.lang.String fbh;
	/**科别名称*/
	private java.lang.String fname;
	/**id*/
	private java.lang.String id;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科别编号
	 */
	@Column(name ="F_BH",nullable=false,length=2)
	public java.lang.String getFbh(){
		return this.fbh;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科别编号
	 */
	public void setFbh(java.lang.String fbh){
		this.fbh = fbh;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科别名称
	 */
	@Column(name ="F_NAME",nullable=false,length=50)
	public java.lang.String getFname(){
		return this.fname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科别名称
	 */
	public void setFname(java.lang.String fname){
		this.fname = fname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=true,length=255)
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
