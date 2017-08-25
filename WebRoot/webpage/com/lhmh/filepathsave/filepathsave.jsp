<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>上传资料路径信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="filepathsaveController.do?save">
			<input id="id" name="id" type="hidden" value="${filepathsavePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							医院编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="comId" name="comId" 
							   value="${filepathsavePage.comId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							单据编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="infoId" name="infoId" 
							   value="${filepathsavePage.infoId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							行号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="seq" name="seq" 
							   value="${filepathsavePage.seq}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							文件类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fileType" name="fileType" ignore="ignore"
							   value="${filepathsavePage.fileType}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							文件路径:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fileDocId" name="fileDocId" ignore="ignore"
							   value="${filepathsavePage.fileDocId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							文件名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fileName" name="fileName" ignore="ignore"
							   value="${filepathsavePage.fileName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否使用0:否,1:是:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isMrb" name="isMrb" ignore="ignore"
							   value="${filepathsavePage.isMrb}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>