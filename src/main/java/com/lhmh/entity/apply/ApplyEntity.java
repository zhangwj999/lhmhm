package com.lhmh.entity.apply;

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
 * @Description: 会诊申请
 * @author zhangdaihao
 * @date 2017-06-23 19:17:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "lh_apply", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ApplyEntity implements java.io.Serializable {
	/**医院编号*/
	private java.lang.String comId;
	/**申请单号*/
	private java.lang.String applyId;
	/**申请日期*/
	private java.lang.String date1;
	/**病人*/
	private java.lang.String patientId;
	/**病人*/
	private java.lang.String patientName;
	/**科室*/
	private java.lang.String officeId;
	/**拟请会诊单位*/
	private java.lang.String apcomId;
	/**拟请会诊科室*/
	private java.lang.String apofficeId;
	/**拟会诊时间*/
	private java.lang.String apdate1;
	/**会诊室*/
	private java.lang.String roomId;
	/**申请单状态枚举APSTATUS*/
	private java.lang.String status;
	/**申请医师*/
	private java.lang.String crtUser;
	/**申请医师*/
	private java.lang.String crtName;
	/**科主任*/
	private java.lang.String manager;
	/**科主任*/
	private java.lang.String managerName;
	/**退回原因*/
	private java.lang.String returnResion;
	/**预计开始时间*/
	private java.lang.String pbeginDate;
	/**预计结束时间*/
	private java.lang.String pendDate;
	/**费用*/
	private BigDecimal free;
	/**费用总计*/
	private BigDecimal totalFree;
	/**开始时间*/
	private java.lang.String beginDate;
	/**结束时间*/
	private java.lang.String endDate;
	/**报告人*/
	private java.lang.String rptUser;
	/**报告人*/
	private java.lang.String rptName;
	/**报告类型，枚举RTPTYPE*/
	private java.lang.String rptType;
	/**会诊医生要求，如，主任医师等*/
	private java.lang.String doctorDetail;
	/**病史摘要*/
	private java.lang.String hisSummary;
	/**目前初步诊断与处理*/
	private java.lang.String curDetail;
	/**会诊理由和目的*/
	private java.lang.String meetPurpose;
	/**会诊结果*/
	private java.lang.String rptDetail;
	/**id*/
	private java.lang.String id;
	/**拟请专家编号*/
	private java.lang.String doctorId;
	/**拟请专家姓名*/
	private java.lang.String doctorName;
	
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
	 *@return: java.lang.String  申请单号
	 */
	@Column(name ="APPLY_ID",nullable=false,length=32)
	public java.lang.String getApplyId(){
		return this.applyId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请单号
	 */
	public void setApplyId(java.lang.String applyId){
		this.applyId = applyId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请日期
	 */
	@Column(name ="DATE1",nullable=false,length=32)
	public java.lang.String getDate1(){
		return this.date1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请日期
	 */
	public void setDate1(java.lang.String date1){
		this.date1 = date1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  病人
	 */
	@Column(name ="PATIENT_ID",nullable=false,length=50)
	public java.lang.String getPatientId(){
		return this.patientId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  病人
	 */
	public void setPatientId(java.lang.String patientId){
		this.patientId = patientId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  病人
	 */
	@Column(name ="PATIENT_NAME",nullable=false,length=50)
	public java.lang.String getPatientName(){
		return this.patientName;
	}
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  病人
	 */
	public void setPatientName(java.lang.String patientName){
		this.patientName = patientName;
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
	 *@return: java.lang.String  拟请会诊单位
	 */
	@Column(name ="APCOM_ID",nullable=false,length=32)
	public java.lang.String getApcomId(){
		return this.apcomId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拟请会诊单位
	 */
	public void setApcomId(java.lang.String apcomId){
		this.apcomId = apcomId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  拟请会诊科室
	 */
	@Column(name ="APOFFICE_ID",nullable=false,length=32)
	public java.lang.String getApofficeId(){
		return this.apofficeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拟请会诊科室
	 */
	public void setApofficeId(java.lang.String apofficeId){
		this.apofficeId = apofficeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  拟会诊时间
	 */
	@Column(name ="APDATE1",nullable=true,length=32)
	public java.lang.String getApdate1(){
		return this.apdate1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拟会诊时间
	 */
	public void setApdate1(java.lang.String apdate1){
		this.apdate1 = apdate1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会诊室
	 */
	@Column(name ="ROOM_ID",nullable=true,length=50)
	public java.lang.String getRoomId(){
		return this.roomId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会诊室
	 */
	public void setRoomId(java.lang.String roomId){
		this.roomId = roomId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请单状态枚举APSTATUS
	 */
	@Column(name ="STATUS",nullable=true,length=50)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请单状态枚举APSTATUS
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请医师
	 */
	@Column(name ="CRT_USER",nullable=false,length=50)
	public java.lang.String getCrtUser(){
		return this.crtUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请医师
	 */
	public void setCrtUser(java.lang.String crtUser){
		this.crtUser = crtUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请医师
	 */
	@Column(name ="CRT_NAME",nullable=false,length=50)
	public java.lang.String getCrtName(){
		return this.crtName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请医师
	 */
	public void setCrtName(java.lang.String crtName){
		this.crtName = crtName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科主任
	 */
	@Column(name ="MANAGER",nullable=true,length=50)
	public java.lang.String getManager(){
		return this.manager;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科主任
	 */
	public void setManager(java.lang.String manager){
		this.manager = manager;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科主任
	 */
	@Column(name ="MANAGER_NAME",nullable=true,length=50)
	public java.lang.String getManagerName(){
		return this.managerName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科主任
	 */
	public void setManagerName(java.lang.String managerName){
		this.managerName = managerName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退回原因
	 */
	@Column(name ="RETURN_RESION",nullable=true,length=100)
	public java.lang.String getReturnResion(){
		return this.returnResion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退回原因
	 */
	public void setReturnResion(java.lang.String returnResion){
		this.returnResion = returnResion;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预计开始时间
	 */
	@Column(name ="PBEGIN_DATE",nullable=true,length=32)
	public java.lang.String getPbeginDate(){
		return this.pbeginDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预计开始时间
	 */
	public void setPbeginDate(java.lang.String pbeginDate){
		this.pbeginDate = pbeginDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预计结束时间
	 */
	@Column(name ="PEND_DATE",nullable=true,length=32)
	public java.lang.String getPendDate(){
		return this.pendDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预计结束时间
	 */
	public void setPendDate(java.lang.String pendDate){
		this.pendDate = pendDate;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  费用
	 */
	@Column(name ="FREE",nullable=true,precision=18,scale=2)
	public BigDecimal getFree(){
		return this.free;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  费用
	 */
	public void setFree(BigDecimal free){
		this.free = free;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  费用总计
	 */
	@Column(name ="TOTAL_FREE",nullable=true,precision=18,scale=2)
	public BigDecimal getTotalFree(){
		return this.totalFree;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  费用总计
	 */
	public void setTotalFree(BigDecimal totalFree){
		this.totalFree = totalFree;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开始时间
	 */
	@Column(name ="BEGIN_DATE",nullable=true,length=32)
	public java.lang.String getBeginDate(){
		return this.beginDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开始时间
	 */
	public void setBeginDate(java.lang.String beginDate){
		this.beginDate = beginDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结束时间
	 */
	@Column(name ="END_DATE",nullable=true,length=32)
	public java.lang.String getEndDate(){
		return this.endDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结束时间
	 */
	public void setEndDate(java.lang.String endDate){
		this.endDate = endDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报告人
	 */
	@Column(name ="RPT_USER",nullable=true,length=50)
	public java.lang.String getRptUser(){
		return this.rptUser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报告人
	 */
	public void setRptUser(java.lang.String rptUser){
		this.rptUser = rptUser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报告人
	 */
	@Column(name ="RPT_NAME",nullable=true,length=50)
	public java.lang.String getRptName(){
		return this.rptName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报告人
	 */
	public void setRptName(java.lang.String rptName){
		this.rptName = rptName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报告类型，枚举RTPTYPE
	 */
	@Column(name ="RPT_TYPE",nullable=true,length=2)
	public java.lang.String getRptType(){
		return this.rptType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报告类型，枚举RTPTYPE
	 */
	public void setRptType(java.lang.String rptType){
		this.rptType = rptType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会诊医生要求，如，主任医师等
	 */
	@Column(name ="DOCTOR_DETAIL",nullable=true,length=200)
	public java.lang.String getDoctorDetail(){
		return this.doctorDetail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会诊医生要求，如，主任医师等
	 */
	public void setDoctorDetail(java.lang.String doctorDetail){
		this.doctorDetail = doctorDetail;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  拟请会诊专家ID
	 */
	@Column(name ="DOCTOR_ID",nullable=true,length=200)
	public java.lang.String getDoctorId(){
		return this.doctorId;
	}
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拟请会诊专家ID
	 */
	public void setDoctorId(java.lang.String doctorId){
		this.doctorId = doctorId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  拟请会诊专家Name
	 */
	@Column(name ="DOCTOR_NAME",nullable=true,length=200)
	public java.lang.String getDoctorName(){
		return this.doctorName;
	}
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拟请会诊专家Name
	 */
	public void setDoctorName(java.lang.String doctorName){
		this.doctorName = doctorName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  病史摘要
	 */
	@Column(name ="HIS_SUMMARY",nullable=true,length=255)
	public java.lang.String getHisSummary(){
		return this.hisSummary;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  病史摘要
	 */
	public void setHisSummary(java.lang.String hisSummary){
		this.hisSummary = hisSummary;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  目前初步诊断与处理
	 */
	@Column(name ="CUR_DETAIL",nullable=true,length=255)
	public java.lang.String getCurDetail(){
		return this.curDetail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  目前初步诊断与处理
	 */
	public void setCurDetail(java.lang.String curDetail){
		this.curDetail = curDetail;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会诊理由和目的
	 */
	@Column(name ="MEET_PURPOSE",nullable=true,length=255)
	public java.lang.String getMeetPurpose(){
		return this.meetPurpose;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会诊理由和目的
	 */
	public void setMeetPurpose(java.lang.String meetPurpose){
		this.meetPurpose = meetPurpose;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会诊结果
	 */
	@Column(name ="RPT_DETAIL",nullable=true,length=255)
	public java.lang.String getRptDetail(){
		return this.rptDetail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会诊结果
	 */
	public void setRptDetail(java.lang.String rptDetail){
		this.rptDetail = rptDetail;
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

	@Override
	public String toString() {
		return "ApplyEntity [comId=" + comId + ", applyId=" + applyId
				+ ", date1=" + date1 + ", patientId=" + patientId
				+ ", patientName=" + patientName + ", officeId=" + officeId
				+ ", apcomId=" + apcomId + ", apofficeId=" + apofficeId
				+ ", apdate1=" + apdate1 + ", roomId=" + roomId + ", status="
				+ status + ", crtUser=" + crtUser + ", crtName=" + crtName
				+ ", manager=" + manager + ", managerName=" + managerName
				+ ", returnResion=" + returnResion + ", pbeginDate="
				+ pbeginDate + ", pendDate=" + pendDate + ", free=" + free
				+ ", totalFree=" + totalFree + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + ", rptUser=" + rptUser
				+ ", rptName=" + rptName + ", rptType=" + rptType
				+ ", doctorDetail=" + doctorDetail + ", hisSummary="
				+ hisSummary + ", curDetail=" + curDetail + ", meetPurpose="
				+ meetPurpose + ", rptDetail=" + rptDetail + ", id=" + id
				+ ", doctorId=" + doctorId + ", doctorName=" + doctorName + "]";
	}
	
}
