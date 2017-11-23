<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>会诊申请单新增</title>
<link href="<%=path%>/webpage/com/lhmh/pub/apply/apply.css" rel="stylesheet" type="text/css" media="screen" />
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="margin: 20px 20px 0px 20px;" scroll="no">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="applyController.do?save">
		<input id="id" name="id" type="hidden" value="${applyPage.id }">
		<div>
			<div id="title">会诊单新增</div>
			<input class="btn" type="button" onclick="closeit()" value="关闭">
			<input class="btn" type="button" onclick="present()" value="保存并提交">
			<input class="btn" type="button" onclick="submit()" value="保存">
				<table cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right"><label class="Validform_label">医院名称：</label></td>
						<td class="value">
							<input name="comId"  type="hidden" value="${applyPage.comId}" id="comId"> 
							<t:dictSelect field="comId" hasLabel="false" defaultVal="${applyPage.comId}" dictTable="LH_COM" dictField="COM_ID" dictText="COM_NAME"/> 
						</td>
						<td align="right"><label class="Validform_label">科室：</label></td>
						<td class="value">
							<t:dictSelect id="officeId" field="officeId" hasLabel="false" defaultVal="${applyPage.officeId}" dictTable="LH_OFFICE" dictField="OFFICE_ID" dictText="OFFICE_NAME"/> 
						</td>
					</tr>
					<tr>
						<td align="right"><label class="Validform_label">病人：</label></td>
						<td class="value">
							<input name="patientId"  type="hidden" value="${applyPage.patientId}" id="patientId"> 
							<input name="patientName" class="inputxt"  id="patientName" value="${patientName}" readonly="readonly" datatype="s1-32" />
					 		<t:choose hiddenName="patientId" hiddenid="fId" url="applyController.do?patients" name="patientList" icon="icon-search" title="病人列表" textname="fName" isclear="true" inputTextname="patientName"></t:choose>
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
							<t:dictSelect id="apcomId" field="apcomId" hasLabel="false" defaultVal="${applyPage.apcomId}" dictTable="LH_COM" dictField="COM_ID" dictText="COM_NAME"/> 
						</td>
						<td align="right"><label class="Validform_label">拟请会诊科室：</label></td>
						<td class="value">
							<t:dictSelect id="apofficeId" field="apofficeId" hasLabel="false" defaultVal="${applyPage.apofficeId}" dictTable="LH_OFFICE" dictField="OFFICE_ID" dictText="OFFICE_NAME"/> 
						</td>
					</tr>
					<tr>
						<td align="right"><label class="Validform_label">拟请会诊专家：</label></td>
						<td class="value" nowrap>
							<input name="doctorId" type="hidden" value="${applyPage.doctorId}" id="doctorId">
							<input name="doctorName" class="inputxt" value="${doctorName}" id="doctorName" readonly="readonly" datatype="*" /> 
							<t:choose hiddenName="doctorId" hiddenid="id" url="applyController.do?doctors" name="doctorList" icon="icon-search" title="专家列表" textname="doctorName" isclear="true"></t:choose>
							<span class="Validform_checktip">角色可多选</span>
						</td>
						<td align="right"><label class="Validform_label">拟会诊时间：</label></td>
						<td class="value">
							<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"  style="width: 150px" id="apdate1" name="apdate1" ignore="ignore" value="${applyPage.apdate1}">
						</td>
					</tr>
					<tr>
						<td align="right"><label class="Validform_label">科主任：</label></td>
						<td class="value">
							<input name="manager"  type="hidden" value="${applyPage.manager}" id="manager"> 
							<input name="managerName" class="inputxt"  id="managerName" value="${fname}" readonly="readonly" datatype="s1-32"  />
					 		<t:choose hiddenName="manager" hiddenid="id" url="applyController.do?managers" name="managerList" icon="icon-search" title="科主任列表" textname="fname" isclear="true" inputTextname="managerName"></t:choose>
					      	<span class="Validform_checktip"></span>
						</td>
						<td align="right"></td>
						<td class="value"></td>
					</tr>
					<tr>
					<td align="right"><label class="Validform_label">病史摘要：</label></td>
					<td class="value" colspan="3">
						<textarea class="inputxt" id="hisSummary"name="hisSummary" ignore="ignore" style="width:70%;height:70px;"></textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label">目前初步诊断与处理：</label></td>
					<td class="value" colspan="3">
						<textarea class="inputxt" id="curDetail" name="curDetail" ignore="ignore" style="width:70%;height:70px;"></textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label">会诊理由和目的：</label></td>
					<td class="value" colspan="3">
						<textarea class="inputxt" id="meetPurpose" name="meetPurpose" ignore="ignore" style="width:70%;height:70px;"></textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				</table>
			</div>
	</t:formvalid>
</body>
<script type="text/javascript">
	console.log( window.frameElement )
	// 初始化将下拉框默认不可选择
	window.onload = function(){
		var sels = document.getElementsByTagName("select");
		sels[0].disabled = "disabled";
		
		$( '#comId' ).on( 'change', office )
		$( '#apcomId' ).on( 'change', apoffice )
// 		var sel = document.getElementById("comId");
// 		sel.addEventListener("onchange", office(), false);
// 		var selap = document.getElementById("apcomId");
// 		selap.addEventListener("onchange", apoffice(), false);
		
// 		$("#comId").attr("onchange","office()");
// 		$("#apcomId").attr("onchange","apoffice()");
	}

	// 会诊单位 onchange 方法
	function office(){
		var comId = $("#comId").val();
		$.ajax({
			type : "get",
			async: false,
			url : "applyController.do?changeOffice&comId="+comId,
			success : function(data, textStatus){
				var selre = data.substring(data.indexOf("option")-1,data.indexOf("obj")-12);
				var sel = selre.replace(/[\\]/g,"");
				$("#officeId").html(sel);
			},
			complete : function(XMLHttpRequest, textStatus) {
				if (textStatus == 'success') {
				} else {
					jAlert("请求出错!textStatus=" + textStatus);
					return false;
				}
			}
		});
	}

	// 拟请会诊单位 onchange 方法
	function apoffice(){
		var comId = $("#apcomId").val();
		$.ajax({
			type : "get",
			async: false,
			url : "applyController.do?changeOffice&comId="+comId,
			success : function(data, textStatus){
				var selre = data.substring(data.indexOf("option")-1,data.indexOf("obj")-12);
				var sel = selre.replace(/[\\]/g,"");
				$("#apofficeId").html(sel);
			},
			complete : function(XMLHttpRequest, textStatus) {
				if (textStatus == 'success') {
				} else {
					jAlert("请求出错!textStatus=" + textStatus);
					return false;
				}
			}
		});
	}

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

	function submit() {
		document.getElementById("formobj").submit();
	}

	function present() {
		document.forms[0].action = "applyController.do?savepresent";
		document.forms[0].submit();
	}

	function closeit() {
		window.opener.location.href = window.opener.location.href;
		window.close();
	}
</script>