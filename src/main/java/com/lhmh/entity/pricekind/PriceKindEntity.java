package com.lhmh.entity.pricekind;

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
 * @Description: 收费标准
 * @author zhangdaihao
 * @date 2017-11-26 10:42:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "lh_price_kind", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class PriceKindEntity implements java.io.Serializable {
	/**医院编码*/
	private java.lang.String comId;
	/**价格编码*/
	private java.lang.String id;
	/**价格*/
	private java.lang.String priceName;
	/**描述*/
	private java.lang.String note;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  医院编码
	 */
	@Column(name ="COM_ID",nullable=false,length=32)
	public java.lang.String getComId(){
		return this.comId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  医院编码
	 */
	public void setComId(java.lang.String comId){
		this.comId = comId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  价格编码
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
	 *@param: java.lang.String  价格编码
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  价格
	 */
	@Column(name ="PRICE_NAME",nullable=true,length=20)
	public java.lang.String getPriceName(){
		return this.priceName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  价格
	 */
	public void setPriceName(java.lang.String priceName){
		this.priceName = priceName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="NOTE",nullable=true,length=200)
	public java.lang.String getNote(){
		return this.note;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setNote(java.lang.String note){
		this.note = note;
	}
}
