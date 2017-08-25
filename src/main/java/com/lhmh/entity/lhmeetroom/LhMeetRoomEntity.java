package com.lhmh.entity.lhmeetroom;

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
 * @Description: 会诊室管理
 * @author zhangdaihao
 * @date 2017-06-11 11:27:51
 * @version V1.0   
 *
 */
@Entity
@Table(name = "LH_MEETROOM", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LhMeetRoomEntity implements java.io.Serializable {
	/**所属单位*/
	private java.lang.String comId;
	/**会诊室编号*/
	private java.lang.String roomId;
	/**会诊室名称*/
	private java.lang.String roomName;
	/**会诊室类型，取枚举ROOMTYPE*/
	private java.lang.String roomType;
	/**会诊室IP*/
	private java.lang.String roomIp;
	/**备注*/
	private java.lang.String note;
	/**id*/
	private java.lang.String id;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属单位
	 */
	@Column(name ="COM_ID",nullable=false,length=32)
	public java.lang.String getComId(){
		return this.comId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属单位
	 */
	public void setComId(java.lang.String comId){
		this.comId = comId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会诊室编号
	 */
	@Column(name ="ROOM_ID",nullable=false,length=2)
	public java.lang.String getRoomId(){
		return this.roomId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会诊室编号
	 */
	public void setRoomId(java.lang.String roomId){
		this.roomId = roomId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会诊室名称
	 */
	@Column(name ="ROOM_NAME",nullable=false,length=50)
	public java.lang.String getRoomName(){
		return this.roomName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会诊室名称
	 */
	public void setRoomName(java.lang.String roomName){
		this.roomName = roomName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会诊室类型，取枚举ROOMTYPE
	 */
	@Column(name ="ROOM_TYPE",nullable=false,length=2)
	public java.lang.String getRoomType(){
		return this.roomType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会诊室类型，取枚举ROOMTYPE
	 */
	public void setRoomType(java.lang.String roomType){
		this.roomType = roomType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会诊室IP
	 */
	@Column(name ="ROOM_IP",nullable=true,length=20)
	public java.lang.String getRoomIp(){
		return this.roomIp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会诊室IP
	 */
	public void setRoomIp(java.lang.String roomIp){
		this.roomIp = roomIp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="NOTE",nullable=true,length=50)
	public java.lang.String getNote(){
		return this.note;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setNote(java.lang.String note){
		this.note = note;
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
