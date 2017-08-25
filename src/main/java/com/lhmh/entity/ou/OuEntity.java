package com.lhmh.entity.ou;

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
 * @Description: 验证维护
 * @author zhangdaihao
 * @date 2017-07-10 21:39:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ou", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class OuEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**sy*/
	private java.lang.String sy;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,precision=10,scale=0)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  sy
	 */
	@Column(name ="SY",nullable=true,length=255)
	public java.lang.String getSy(){
		return this.sy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  sy
	 */
	public void setSy(java.lang.String sy){
		this.sy = sy;
	}
}
