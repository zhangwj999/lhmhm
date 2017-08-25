<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>专家库管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lhdoctorController.do?save">
			<input id="id" name="id" type="hidden" value="${lhdoctorPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							医院编号:
						</label>
					</td>
					<td class="value">
						<select id="comId" nullmsg="请选择公司" name="comId" datatype="*">
							<c:forEach items="${comsList}" var="coms">
								<option value="${coms.comId}" <c:if test="${lhdoctorPage.comId==coms.comId}">selected="selected"</c:if>
								 >${coms.comName}</option>
							</c:forEach>
							</select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							科室:
						</label>
					</td>
					<td class="value">
					<select id="officeId" nullmsg="请选择科室" name="officeId" datatype="*">					
					<c:forEach items="${officeList}" var="office">
							<option value="${office.officeId}" <c:if test="${lhdoctorPage.officeId==office.officeId}">selected="selected"</c:if>
							 >${office.officeName}</option>
						</c:forEach>
					</select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							职务岗位:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="positionId" typeGroupCode="postype"
                		defaultVal="${lhdoctorPage.positionId}" hasLabel="false" title=""></t:dictSelect>
					
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							医生编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="doctorId" name="doctorId" 
							   value="${lhdoctorPage.doctorId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							医生姓名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="doctorName" name="doctorName" 
							   value="${lhdoctorPage.doctorName}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							收费类型标准:
						</label>
					</td>
					<td class="value">
					<t:dictSelect field="payKind" typeGroupCode="paykind"
                		defaultVal="${lhdoctorPage.payKind}" hasLabel="false" title=""></t:dictSelect>

						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="doctorPk" name="doctorPk" ignore="ignore"
							   value="${lhdoctorPage.doctorPk}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							联系方式:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="tel" name="tel" ignore="ignore"
							   value="${lhdoctorPage.tel}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							优势擅长:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fmemo" name="fmemo" ignore="ignore"
							   value="${lhdoctorPage.fmemo}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>