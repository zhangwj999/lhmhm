<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>主键</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lhpatientnouseController.do?save">
			<input id="id" name="id" type="hidden" value="${lhpatientnousePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							医院编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="comId" name="comId" 
							   value="${lhpatientnousePage.comId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							住院号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="patientId" name="patientId" 
							   value="${lhpatientnousePage.patientId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							姓名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="patientName" name="patientName" 
							   value="${lhpatientnousePage.patientName}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							年龄:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="patientAge" name="patientAge" 
							   value="${lhpatientnousePage.patientAge}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							性别 枚举SEX:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="patientSex" name="patientSex" 
							   value="${lhpatientnousePage.patientSex}" datatype="*">
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
							   value="${lhpatientnousePage.officeId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							医保卡号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="medicalCard" name="medicalCard" 
							   value="${lhpatientnousePage.medicalCard}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							患者唯一编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="patientPk" name="patientPk" 
							   value="${lhpatientnousePage.patientPk}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fMemo" name="fMemo" 
							   value="${lhpatientnousePage.fMemo}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>