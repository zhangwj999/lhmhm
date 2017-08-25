<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>通道管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="channelController.do?save">
			<input id="id" name="id" type="hidden" value="${channelPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
					<td align="right">
						<label class="Validform_label">
							设备序号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="cpk" name="cpk" 
							   value="${channelPage.cpk}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				<td align="right">
						<label class="Validform_label">
							手术室:
						</label>
					</td>
					<td class="value">
						<select id="officeId" nullmsg="请填写手术室" name="officeId" datatype="*">
						<c:forEach items="${officesList}" var="terminal">
							<option value="${terminal.fid }" 
							<c:if test="${channelPage.officeId==terminal.fid}">selected="selected"</c:if>>${terminal.fname}</option>
						</c:forEach>
					</select> 
				</tr>
				<tr>
				<td align="right">
						<label class="Validform_label">
							视频编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="chanId" name="chanId" 
							   value="${channelPage.chanId}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
					</td>					
	
					
			
					<td align="right">
						<label class="Validform_label">
							视频名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="chanName" name="chanName" ignore="ignore"
							   value="${channelPage.chanName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
				
					
					<td align="right">
						<label class="Validform_label">
							IP地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ip" name="ip" ignore="ignore"
							   value="${channelPage.ip}">
						<span class="Validform_checktip"></span>
					</td>

					<td align="right">
						<label class="Validform_label">
							端口:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="port" name="port" ignore="ignore"
							   value="${channelPage.port}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
						</tr>
				<tr>		
					<td align="right">
						<label class="Validform_label">
							登录用户:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="user" name="user" ignore="ignore"
							   value="${channelPage.user}">
						<span class="Validform_checktip"></span>
					</td>

					<td align="right">
						<label class="Validform_label">
							登录密码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="pass" name="pass" ignore="ignore"
							   value="${channelPage.pass}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>	
					<td align="right">
						<label class="Validform_label">
							设备类型:
						</label>
					</td>
					<td class="value">
					<t:dictSelect field="omc" typeGroupCode="devicetype"
                		defaultVal="${channelPage.omc}" hasLabel="false" title=""></t:dictSelect>
						</td>			
					<td align="right">
						<label class="Validform_label">
							存储IP:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="nvrip" name="nvrip" ignore="ignore"
							   value="${channelPage.nvrip}">
						<span class="Validform_checktip"></span>
					</td>

					

				</tr>
				<tr>			

								
					<td align="right">
						<label class="Validform_label">
							通道状态:
						</label>
					</td>
					<td class="value">
					<t:dictSelect field="status" typeGroupCode="status"
                		defaultVal="${channelPage.status}" hasLabel="false" title="性别(没有label)"></t:dictSelect>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>