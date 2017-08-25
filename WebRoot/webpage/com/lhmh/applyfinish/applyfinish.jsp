<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>会诊完成</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="applyfinishController.do?save">
			<input id="id" name="id" type="hidden" value="${applyfinishPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							医院编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="comId" name="comId" 
							   value="${applyfinishPage.comId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							申请单号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="applyId" name="applyId" 
							   value="${applyfinishPage.applyId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							申请日期:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="date1" name="date1" 
							   value="${applyfinishPage.date1}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							病人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="patientId" name="patientId" 
							   value="${applyfinishPage.patientId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							病人名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="patientName" name="patientName" ignore="ignore"
							   value="${applyfinishPage.patientName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							科室:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="officeId" name="officeId" ignore="ignore"
							   value="${applyfinishPage.officeId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							拟请会诊单位:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="apcomId" name="apcomId" 
							   value="${applyfinishPage.apcomId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							拟请会诊科室:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="apofficeId" name="apofficeId" 
							   value="${applyfinishPage.apofficeId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							拟会诊时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="apdate1" name="apdate1" ignore="ignore"
							   value="${applyfinishPage.apdate1}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会诊室:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="roomId" name="roomId" ignore="ignore"
							   value="${applyfinishPage.roomId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							申请单状态枚举APSTATUS:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="status" name="status" ignore="ignore"
							   value="${applyfinishPage.status}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							申请医师:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="crtUser" name="crtUser" 
							   value="${applyfinishPage.crtUser}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							申请医师:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="crtName" name="crtName" 
							   value="${applyfinishPage.crtName}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							科主任:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="manager" name="manager" ignore="ignore"
							   value="${applyfinishPage.manager}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							科主任:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="managerName" name="managerName" ignore="ignore"
							   value="${applyfinishPage.managerName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							退回原因:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="returnResion" name="returnResion" ignore="ignore"
							   value="${applyfinishPage.returnResion}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预计开始时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="pbeginDate" name="pbeginDate" ignore="ignore"
							   value="${applyfinishPage.pbeginDate}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预计结束时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="pendDate" name="pendDate" ignore="ignore"
							   value="${applyfinishPage.pendDate}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							费用:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="free" name="free" ignore="ignore"
							   value="${applyfinishPage.free}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							费用总计:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="totalFree" name="totalFree" ignore="ignore"
							   value="${applyfinishPage.totalFree}" datatype="d">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							开始时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="beginDate" name="beginDate" ignore="ignore"
							   value="${applyfinishPage.beginDate}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							结束时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="endDate" name="endDate" ignore="ignore"
							   value="${applyfinishPage.endDate}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							报告人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rptUser" name="rptUser" ignore="ignore"
							   value="${applyfinishPage.rptUser}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							报告人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rptName" name="rptName" ignore="ignore"
							   value="${applyfinishPage.rptName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							报告类型，枚举RTPTYPE:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rptType" name="rptType" ignore="ignore"
							   value="${applyfinishPage.rptType}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会诊医生要求，如，主任医师等:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="doctorDetail" name="doctorDetail" ignore="ignore"
							   value="${applyfinishPage.doctorDetail}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							病史摘要:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="hisSummary" name="hisSummary" ignore="ignore"
							   value="${applyfinishPage.hisSummary}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							目前初步诊断与处理:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="curDetail" name="curDetail" ignore="ignore"
							   value="${applyfinishPage.curDetail}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会诊理由和目的:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="meetPurpose" name="meetPurpose" ignore="ignore"
							   value="${applyfinishPage.meetPurpose}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会诊结果:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rptDetail" name="rptDetail" ignore="ignore"
							   value="${applyfinishPage.rptDetail}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							拟请会诊专家:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="doctorId" name="doctorId" ignore="ignore"
							   value="${applyfinishPage.doctorId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							拟请会诊专家名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="doctorName" name="doctorName" ignore="ignore"
							   value="${applyfinishPage.doctorName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>