<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>病人信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="patieninfoController.do?save">
			<input id="id" name="id" type="hidden" value="${patieninfoPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							住院号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fid" name="fid" 
							   value="${patieninfoPage.fid}" datatype="*">
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
						<input class="inputxt" id="fname" name="fname" 
							   value="${patieninfoPage.fname}" datatype="*">
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
						<input class="inputxt" id="fage" name="fage" 
							   value="${patieninfoPage.fage}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							性别:
						</label>
					</td><td class="value">										
						<t:dictSelect field="fsex" typeGroupCode="sex"
                defaultVal="${patieninfoPage.fsex}" hasLabel="false" title="性别(没有label)"></t:dictSelect>
                </td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							科别:
						</label>
					</td>
					<td class="value">
						<select id="ftype" nullmsg="请填写科别" name="ftype" datatype="*">
						<c:forEach items="${officesList}" var="terminal">
							<option value="${terminal.fbh}" <c:if test="${patieninfoPage.ftype==terminal.fbh}">selected="selected"</c:if>>${terminal.fname}</option>
						</c:forEach>
					</select> 
				</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fmemo" name="fmemo" 
							   value="${patieninfoPage.fmemo}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>