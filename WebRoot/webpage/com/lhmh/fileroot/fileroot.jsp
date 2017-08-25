<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>文档上传磁盘路径</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="filerootController.do?save">
			<input id="id" name="id" type="hidden" value="${filerootPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							机器IP:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="mip" name="mip" 
							   value="${filerootPage.mip}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							磁盘路径:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rootId" name="rootId" 
							   value="${filerootPage.rootId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							文件URL:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fileUrl" name="fileUrl" ignore="ignore"
							   value="${filerootPage.fileUrl}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							文件名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fileName" name="fileName" ignore="ignore"
							   value="${filerootPage.fileName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							部门编码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="deptId" name="deptId" ignore="ignore"
							   value="${filerootPage.deptId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>