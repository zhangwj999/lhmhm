package com.lhmh.entity.ops_room;

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
 * @Description: 手术室
 * @author zhangdaihao
 * @date 2015-08-15 18:05:03
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ops_room", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class Ops_roomEntity implements java.io.Serializable {
	/**手术室编号*/
	private java.lang.String fid;
	/**手术室名称*/
	private java.lang.String fname;
	/**手术室IP*/
	private java.lang.String fip;
	/**id*/
	private java.lang.String id;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手术室编号
	 */
	@Column(name ="F_ID",nullable=false,length=2)
	public java.lang.String getFid(){
		return this.fid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手术室编号
	 */
	public void setFid(java.lang.String fid){
		this.fid = fid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手术室名称
	 */
	@Column(name ="F_NAME",nullable=false,length=50)
	public java.lang.String getFname(){
		return this.fname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手术室名称
	 */
	public void setFname(java.lang.String fname){
		this.fname = fname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手术室IP
	 */
	@Column(name ="F_IP",nullable=false,length=20)
	public java.lang.String getFip(){
		return this.fip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手术室IP
	 */
	public void setFip(java.lang.String fip){
		this.fip = fip;
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
