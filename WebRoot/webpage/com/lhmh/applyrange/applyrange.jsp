<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>远程会诊</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="applyrangeController.do?save" >
			<input id="id" name="id" type="hidden" value="${applyrangePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right"><label class="Validform_label">会诊结果：</label></td>
					<td class="value" colspan="3">
						<textarea class="inputxt" id="rptDetail" name="rptDetail" style="width:70%;height:70px;" datatype="*">${applyrangePage.rptDetail}</textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>