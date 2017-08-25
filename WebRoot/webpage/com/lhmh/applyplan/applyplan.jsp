<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>会诊安排</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="applyplanController.do?save">
			<input id="id" name="id" type="hidden" value="${applyplanPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right"><label class="Validform_label">拟请会诊专家：</label></td>
					<td class="value" nowrap>
						<input name="doctorId" type="hidden" value="${applyplanPage.doctorId}" id="doctorId">
						<input name="doctorName" class="inputxt" value="${applyplanPage.doctorName}" id="doctorName" readonly="readonly" datatype="*" /> 
						<t:choose hiddenName="doctorId" hiddenid="id" url="applyController.do?doctors" name="doctorList" icon="icon-search" title="专家列表" textname="doctorName" isclear="true"></t:choose>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label">会诊室：</label></td>
					<td class="value">
						<t:dictSelect id="roomId" field="roomId" hasLabel="false" defaultVal="${applyplanPage.roomId}" dictTable="LH_MEETROOM" dictField="ROOM_ID" dictText="ROOM_NAME"/> 
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label">预计开始时间：</label></td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss'})"  style="width: 150px" id="pbeginDate" name="pbeginDate" value="">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label">预计结束时间：</label></td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss'})"  style="width: 150px" id="pendDate" name="pendDate" value="">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>