<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">

	// 关闭
	function closeit() {
		window.opener.location.href = window.opener.location.href;
		window.close();
	}
	
	function forDowns1(obj,name){
		var path = $(obj).parent().parent().parent().children("td").eq(2).html();
		path.replace(/\\/g,"\\\\")
		var url = path + "\\" + name;
		window.open(url);
	}
	
	function forDowns(obj,name){
		var path = $(obj).parent().parent().parent().children("td").eq(2).html();
		path.replace(/\\/g,"\\\\")
		var url = path + "\\" + name;
		window.location.href = "applyController.do?download&url="+url+"&name="+name; 
	}
	
	// 删除
	function forDelete(pk,status,type){
		if(status != "00" && status != "21" && status != "30" && status != "31" && status != "32" && status != "33"){
			tip("只能删除制单、退回和会诊报告阶段文件！");
			return false;
		}
		
		if(status == "30" || status == "31" || status == "32" || status == "33"){
			if(type == "申请资料"){
				tip("会诊报告阶段不能删除申请资料！");
				return false;
			}
		}
				
		
		$.dialog.confirm("确认要删除当前文件记录吗？", function(r) {		
			if(r){
				document.forms[0].action = "applyController.do?deletefile&id="+pk;
				document.forms[0].submit();
	  		}
		});
	}
	
</script>

<title>会诊申请单明细</title>
<link href="<%=path%>/webpage/com/lhmh/pub/apply/apply.css" rel="stylesheet" type="text/css" media="screen" />
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="margin: 20px 20px 0px 20px;" scroll="no">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="applyController.do?save">
		<input id="id" name="id" type="hidden" value="${applyPage.id}">
		<div>
			<div id="title">资料明细</div>
			<input class="btn" type="button" onclick="closeit()" value="关闭">
			<div class="wire"></div>
			<div style="color:#66b904;float:left;margin-bottom:10px;">${msg}</div>
			<input type="hidden" name="urlpath" id="urlpath" />
			<table cellpadding="0" cellspacing="1" class="formtable center">
				<thead>
					<th>会诊单号</th>
					<th>会诊资料类型</th>
					<th>文档路径</th>
					<th>文档名称</th>
					<th>操作</th>
				</thead>
				<tbody>
					<c:forEach items="${filepathPage}" var="listObj" varStatus="i">						
						<tr>
							<td>${listObj.INFO_ID}</td>
							<td>${listObj.FILE_TYPE}</td>
							<td>${listObj.FILE_DOC_ID}</td>
							<td>${listObj.FILE_NAME}</td>
							<td width="20%">
								<div>
									<c:if test="${status != 01 and status != 20 and status != 80 and status != 90}">
										<div class="onsp" onclick="forDelete('${listObj.ID}','${status}','${listObj.FILE_TYPE}')">删除</div>
									</c:if>
								  	<div class="onsp" onclick="forDowns(this,'${listObj.FILE_NAME}')">下载</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</t:formvalid>
</body>