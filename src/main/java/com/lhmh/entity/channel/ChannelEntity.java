package com.lhmh.entity.channel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 通道管理
 * @author zhangdaihao
 * @date 2015-09-11 08:31:50
 * @version V1.0   
 *
 */
@Entity
@Table(name = "channel", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ChannelEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**通道编号*/
	private java.lang.String chanId;
	/**通道名称*/
	private java.lang.String chanName;
	/**手术室*/
	private java.lang.String officeId;
	/**IP地址*/
	private java.lang.String ip;
	/**端口*/
	private java.lang.Integer port;
	/**登录用户*/
	private java.lang.String user;
	/**登录密码*/
	private java.lang.String pass;
	/**存储IP*/
	private java.lang.String nvrip;
	/**type*/
	private java.lang.String type;
	/**status*/
	private java.lang.String status;
	private java.lang.String omc;	
	private java.lang.String cpk;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  通道编号
	 */
	@Column(name ="CHAN_ID",nullable=false,length=20)
	public java.lang.String getChanId(){
		return this.chanId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  通道编号
	 */
	public void setChanId(java.lang.String chanId){
		this.chanId = chanId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  通道名称
	 */
	@Column(name ="CHAN_NAME",nullable=true,length=100)
	public java.lang.String getChanName(){
		return this.chanName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  通道名称
	 */
	public void setChanName(java.lang.String chanName){
		this.chanName = chanName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手术室
	 */
	@Column(name ="OFFICE_ID",nullable=true,length=30)
	public java.lang.String getOfficeId(){
		return this.officeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手术室
	 */
	public void setOfficeId(java.lang.String officeId){
		this.officeId = officeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  IP地址
	 */
	@Column(name ="IP",nullable=true,length=20)
	public java.lang.String getIp(){
		return this.ip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  IP地址
	 */
	public void setIp(java.lang.String ip){
		this.ip = ip;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  端口
	 */
	@Column(name ="PORT",nullable=true,precision=10,scale=0)
	public java.lang.Integer getPort(){
		return this.port;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  端口
	 */
	public void setPort(java.lang.Integer port){
		this.port = port;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登录用户
	 */
	@Column(name ="USER",nullable=true,length=20)
	public java.lang.String getUser(){
		return this.user;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登录用户
	 */
	public void setUser(java.lang.String user){
		this.user = user;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登录密码
	 */
	@Column(name ="PASS",nullable=true,length=32)
	public java.lang.String getPass(){
		return this.pass;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登录密码
	 */
	public void setPass(java.lang.String pass){
		this.pass = pass;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  存储IP
	 */
	@Column(name ="NVRIP",nullable=true,length=20)
	public java.lang.String getNvrip(){
		return this.nvrip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  存储IP
	 */
	public void setNvrip(java.lang.String nvrip){
		this.nvrip = nvrip;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  type
	 */
	@Column(name ="TYPE",nullable=true,length=20)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  type
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  status
	 */
	@Column(name ="OMC",nullable=true,length=1)
	public java.lang.String getOmc(){
		return this.omc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  status
	 */
	public void setOmc(java.lang.String omc){
		this.omc = omc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  status
	 */
	@Column(name ="CPK",nullable=true,length=1)
	public java.lang.String getCpk(){
		return this.cpk;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  status
	 */
	public void setCpk(java.lang.String cpk){
		this.cpk = cpk;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  type
	 */
	@Column(name ="STATUS",nullable=true,length=20)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  type
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
}
