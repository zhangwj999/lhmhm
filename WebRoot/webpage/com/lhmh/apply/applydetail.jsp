<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">

	// 初始化将下拉框默认不可选择
	window.onload = function(){
		var sels = document.getElementsByTagName("select");
		for (var i = 0; i < sels.length; i++) {
			var sel = sels[i];
			sel.disabled = "disabled";
		}
	}
	
	// tab页
	var myclick = function(v) {
		var llis = document.getElementsByTagName("li");
		for (var i = 0; i < llis.length; i++) {
			var lli = llis[i];
			if (lli == document.getElementById("tab" + v)) {
				lli.style.backgroundColor = "#23a8e0";
				lli.style.color = "#FFFFFF";
			} else {
				lli.style.backgroundColor = "#F8F9FD";
				lli.style.color = "#23a8e0";
			}
		}

		var divs = document.getElementsByClassName("tab_css");
		for (var i = 0; i < divs.length; i++) {

			var divv = divs[i];

			if (divv == document.getElementById("tab" + v + "_content")) {
				divv.style.display = "block";
			} else {
				divv.style.display = "none";
			}
		}

	}

	// 关闭
	function closeit() {
		window.opener.location.href = window.opener.location.href;
		window.close();
	}
</script>

<title>会诊申请单明细</title>
<link href="<%=path%>/webpage/com/lhmh/pub/apply/apply.css" rel="stylesheet" type="text/css" media="screen" />
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="margin: 20px 20px 0px 20px;" scroll="no">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="applyController.do?save">
		<input id="id" name="id" type="hidden" value="${applyPage.id}">
		<div>
			<div id="title">会诊单明细</div>
			<input class="btn" type="button" onclick="closeit()" value="关闭">
			<div class="wire"></div>
			<table cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right"><label class="Validform_label">医院名称：</label></td>
					<td class="value">
						<input name="comId"  type="hidden" value="${applyPage.comId}" id="comId"> 
						<t:dictSelect field="comId" hasLabel="false" defaultVal="${applyPage.comId}" dictTable="LH_COM" dictField="COM_ID" dictText="COM_NAME"/> 
					</td>
					<td align="right"><label class="Validform_label">科室：</label></td>
					<td class="value">
						<t:dictSelect field="officeId" hasLabel="false" defaultVal="${applyPage.officeId}" dictTable="LH_OFFICE" dictField="OFFICE_ID" dictText="OFFICE_NAME"/> 
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label">病人：</label></td>
					<td class="value">
						<input name="patientName" class="inputxt"  id="patientName" value="${applyPage.patientName}" disabled="disabled"/>
				      	<span class="Validform_checktip"></span>
					</td>
					<td align="right"><label class="Validform_label">会诊室：</label></td>
					<td class="value">
						<t:dictSelect id="roomId" field="roomId" hasLabel="false" defaultVal="${applyPage.roomId}" dictTable="LH_MEETROOM" dictField="ROOM_ID" dictText="ROOM_NAME"/> 
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label">拟请会诊单位：</label></td>
					<td class="value">
						<t:dictSelect field="apcomId" hasLabel="false" defaultVal="${applyPage.apcomId}" dictTable="LH_COM" dictField="COM_ID" dictText="COM_NAME"/> 
					</td>
					<td align="right"><label class="Validform_label">拟请会诊科室：</label></td>
					<td class="value">
						<t:dictSelect field="apofficeId" hasLabel="false" defaultVal="${applyPage.apofficeId}" dictTable="LH_OFFICE" dictField="OFFICE_ID" dictText="OFFICE_NAME"/> 
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label">拟请会诊专家：</label></td>
					<td class="value" nowrap>
						<input name="doctorName" class="inputxt" value="${applyPage.doctorName}" id="doctorName" disabled="disabled" /> 
						<span class="Validform_checktip"></span>
					</td>
					<td align="right"><label class="Validform_label">拟会诊时间：</label></td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"  style="width: 150px" id="apdate1" name="apdate1" ignore="ignore" value="${applyPage.apdate1}" disabled="disabled">
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label">科主任：</label></td>
					<td class="value">
						<input name="managerName" class="inputxt"  id="managerName" value="${applyPage.managerName}" disabled="disabled"/>
				      	<span class="Validform_checktip"></span>
					</td>
					<c:choose >
						<c:when test="${applyPage.returnResion != null}">
							<td align="right"><label class="Validform_label">退回原因：</label></td>
							<td class="value">
								<t:dictSelect field="returnResion" hasLabel="false" defaultVal="${applyauditPage.returnResion}" 
									dictTable="LH_FEEDBACK_REASON" dictField="ID" dictText="NAME"/>
							</td>
						</c:when>
						<c:otherwise>
							<td align="right"></td>
							<td class="value"></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<c:choose >
					<c:when test="${applyPage.status == '30' or applyPage.status == '31'}">
						<tr>
							<td align="right"><label class="Validform_label">预计开始时间：</label></td>
							<td class="value">
								<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss'})"  style="width: 150px" id="pbeginDate" name="pbeginDate" value="${applyPage.pbeginDate}" disabled="disabled">
								<span class="Validform_checktip"></span>
							</td>
							<td align="right"><label class="Validform_label">预计结束时间：</label></td>
							<td class="value">
								<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss'})"  style="width: 150px" id="pendDate" name="pendDate" value="${applyPage.pendDate}" disabled="disabled">
								<span class="Validform_checktip"></span>
							</td>
						</tr>
					</c:when>
					<c:when test="${applyPage.status == '32' or applyPage.status == '33' or applyPage.status == '90'}">
						<tr>
							<td align="right"><label class="Validform_label">预计开始时间：</label></td>
							<td class="value">
								<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss'})"  style="width: 150px" id="pbeginDate" name="pbeginDate" value="${applyPage.pbeginDate}" disabled="disabled">
								<span class="Validform_checktip"></span>
							</td>
							<td align="right"><label class="Validform_label">预计结束时间：</label></td>
							<td class="value">
								<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss'})"  style="width: 150px" id="pendDate" name="pendDate" value="${applyPage.pendDate}" disabled="disabled">
								<span class="Validform_checktip"></span>
							</td>
						</tr>
						<tr>
							<td align="right"><label class="Validform_label">开始时间：</label></td>
							<td class="value">
								<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss'})"  style="width: 150px" id="beginDate" name="beginDate" value="${applyPage.beginDate}" disabled="disabled">
								<span class="Validform_checktip"></span>
							</td>
							<td align="right"><label class="Validform_label">结束时间：</label></td>
							<td class="value">
								<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyyMMdd HH:mm:ss'})"  style="width: 150px" id="endDate" name="endDate" value="${applyPage.endDate}" disabled="disabled">
								<span class="Validform_checktip"></span>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
				<tr>
					<td align="right"><label class="Validform_label">病史摘要：</label></td>
					<td class="value" colspan="3">
						<textarea class="inputxt" id="hisSummary"name="hisSummary" ignore="ignore" disabled="disabled" style="width:70%;height:70px;">${applyPage.hisSummary}</textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label">目前初步诊断与处理：</label></td>
					<td class="value" colspan="3">
						<textarea class="inputxt" id="curDetail" name="curDetail" ignore="ignore" disabled="disabled" style="width:70%;height:70px;">${applyPage.curDetail}</textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label">会诊理由和目的：</label></td>
					<td class="value" colspan="3">
						<textarea class="inputxt" id="meetPurpose" name="meetPurpose" ignore="ignore" disabled="disabled" style="width:70%;height:70px;">${applyPage.meetPurpose}</textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<c:choose >
					<c:when test="${applyPage.status == '90'}">
						<tr>
							<td align="right"><label class="Validform_label">会诊结果：</label></td>
							<td class="value" colspan="3">
								<textarea class="inputxt" id="rptDetail" name="rptDetail" ignore="ignore" disabled="disabled" style="width:70%;height:70px;">${applyPage.rptDetail}</textarea>
								<span class="Validform_checktip"></span>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	</t:formvalid>
</body>