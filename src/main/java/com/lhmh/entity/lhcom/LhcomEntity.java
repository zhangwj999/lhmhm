package com.lhmh.entity.lhcom;

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
 * @date 2017-07-03 21:15:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "lh_com", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LhcomEntity implements java.io.Serializable {
	/**comId*/
	private java.lang.String comId;
	/**comName*/
	private java.lang.String comName;
	/**id*/
	private java.lang.String id;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  comId
	 */
	@Column(name ="COM_ID",nullable=false,length=32)
	public java.lang.String getComId(){
		return this.comId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  comId
	 */
	public void setComId(java.lang.String comId){
		this.comId = comId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  comName
	 */
	@Column(name ="COM_NAME",nullable=false,length=100)
	public java.lang.String getComName(){
		return this.comName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  comName
	 */
	public void setComName(java.lang.String comName){
		this.comName = comName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
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
