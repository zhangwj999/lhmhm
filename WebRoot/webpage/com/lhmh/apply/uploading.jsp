<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>资料上传</title>
<t:base type="jquery,easyui,tools"></t:base>
<script type="text/javascript">
<%
	String applyId = (String)request.getAttribute("applyId");
	String fileId = (String)request.getAttribute("fileId");
	String comId = (String)request.getAttribute("comId");
	String seq = (String)request.getAttribute("seq");
	String fileType = (String)request.getAttribute("fileType");
	String msg = (String)request.getAttribute("msg");
	if(msg == null || "null".equals(msg)){
		msg = "";
	}
%>

	// 关闭
	function closeit() {
		window.opener.location.href = window.opener.location.href;
		window.close();
	}
</script>
<link href="<%=path%>/webpage/com/lhmh/pub/apply/apply.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	<form name="form1" METHOD="POST" ACTION="./webpage/com/lhmh/apply/saveapplyfile.jsp" ENCTYPE="multipart/form-data">
		<input class="btn" type="button" onclick="closeit()" value="关闭">
		<div style="color:#F00;"><%=msg%></div>
		<div>请选择你要上传的文件：</div>
		<input name="attach" type="FILE" id="attach" size="50"><br/>
		<input name="applyId" type="hidden" value="<%=applyId%>">
		<input name="fileId" type="hidden" value="<%=fileId%>">
		<input name="comId" type="hidden" value="<%=comId%>">
		<input name="seq" type="hidden" value="<%=seq%>">
		<input name="fileType" type="hidden" value="<%=fileType%>">
		<input type="submit" value="提交">
	</form>

</body>
</html>

