<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>手术室</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ops_roomController.do?save">
			<input id="id" name="id" type="hidden" value="${ops_roomPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							手术室编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fid" name="fid" 
							   value="${ops_roomPage.fid}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							手术室名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fname" name="fname" 
							   value="${ops_roomPage.fname}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							手术室IP:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fip" name="fip" 
							   value="${ops_roomPage.fip}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>