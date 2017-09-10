<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>会诊室管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="lhMeetRoomController.do?save">
			<input id="id" name="id" type="hidden" value="${lhMeetRoomPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							公司:
						</label>
					</td>
					<td class="value">
					<select id="comId" nullmsg="请选择公司" name="comId" datatype="*">
						<c:forEach items="${comsList}" var="coms">
							<option value="${coms.comId}" <c:if test="${lhMeetRoomPage.comId==coms.comId}">selected="selected"</c:if>
							 >${coms.comName}</option>
						</c:forEach>
					</select> 
							</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会诊室类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="roomType" typeGroupCode="roomtype"
                		defaultVal="${lhMeetRoomPage.roomType}" hasLabel="false" title=""></t:dictSelect>
						
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会诊室编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="roomId" name="roomId" 
							   value="${lhMeetRoomPage.roomId}" datatype="s2-16"   >
						<span class="Validform_checktip">编号范围在2~16位字符</span>		    
						
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会诊室名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="roomName" name="roomName" 
							   value="${lhMeetRoomPage.roomName}" datatype="s2-50"   >
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
<!-- 							会诊室IP: -->
							系统登录ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="roomIp" name="roomIp" ignore="ignore"
							   value="${lhMeetRoomPage.roomIp}">
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
						<input class="inputxt" id="note" name="note" ignore="ignore"
							   value="${lhMeetRoomPage.note}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
    <script type="text/javascript">
    // onchange="checkProductId(this)"
 	var id = $("#id").val();
 	function checkProductName(obj){
 		var val = $(obj).val();
		$("#roomName").attr("ajaxurl","lhMeetRoomController.do?checkName&roomName="+encodeURI(encodeURI(val))+"&id="+id);
 	}
 	function checkProductId(obj){
 		var val = $(obj).val();
		$("#roomId").attr("ajaxurl","lhMeetRoomController.do?checkId&roomId="+val+"&id="+id);
 	}
 </script>