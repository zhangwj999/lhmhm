<%@ page language="java" import="java.util.*" contentType="application/msword;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	// 关闭
	function closeit() {
		window.opener.location.href = window.opener.location.href;
		window.close();
	}
</script>

<title>会诊申请单明细</title>
<link href="<%=path%>/webpage/com/lhmh/pub/apply/apply.css" rel="stylesheet" type="text/css" media="screen" />
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
    <body>
        <table border="1" cellspacing="0" style="margin:auto;">
            <tr><td colspan="4" align="center">会诊单</td></tr>
            <tr>
            	<td width="15%">医院名称：</td>
            	<td width="35%">${applyPage.comId}</td>
            	<td width="15%">科室：</td>
            	<td width="35%">${applyPage.officeId}</td>
            </tr>
            <tr>
				<td>病人：</td>
				<td>${applyPage.patientName}</td>
				<td>会诊室：</td>
				<td>${applyPage.roomId}</td>
			</tr>
			<tr>
				<td>拟请会诊单位：</td>
				<td>${applyPage.apcomId}</td>
				<td>拟请会诊科室：</td>
				<td>${applyPage.apofficeId}</td>
			</tr>
			<tr>
				<td>拟请会诊专家：</td>
				<td>${applyPage.doctorName}</td>
				<td>拟会诊时间：</td>
				<td>${applyPage.apdate1}</td>
			</tr>
				<tr>
					<td>科主任：</td>
					<td>${applyPage.managerName}</td>
					<c:choose >
						<c:when test="${applyPage.returnResion != null}">
							<td>退回原因：</td>
							<td>${applyPage.returnResion}</td>
						</c:when>
						<c:otherwise>
							<td></td>
							<td></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<c:choose >
					<c:when test="${applyPage.status == '30' or applyPage.status == '31'}">
						<tr>
							<td>预计开始时间：</td>
							<td>${applyPage.pbeginDate}</td>
							<td>预计结束时间：</td>
							<td>${applyPage.pendDate}</td>
						</tr>
					</c:when>
					<c:when test="${applyPage.status == '32' or applyPage.status == '33' or applyPage.status == '90'}">
						<tr>
							<td>预计开始时间：</td>
							<td>${applyPage.pbeginDate}</td>
							<td>预计结束时间：</td>
							<td>${applyPage.pendDate}</td>
						</tr>
						<tr>
							<td>开始时间：</td>
							<td>${applyPage.beginDate}</td>
							<td>结束时间：</td>
							<td>${applyPage.endDate}</td>
						</tr>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
				<tr height="30">
					<td>病史摘要：</td>
					<td colspan="3">${applyPage.hisSummary}</td>
				</tr>
				<tr height="30">
					<td>目前初步诊断与处理：</td>
					<td colspan="3">${applyPage.curDetail}</td>
				</tr>
				<tr height="30">
					<td>会诊理由和目的：</td>
					<td colspan="3">${applyPage.meetPurpose}</td>
				</tr>
				<c:choose >
					<c:when test="${applyPage.status == '90'}">
						<tr height="30">
							<td>会诊结果：</td>
							<td colspan="3">${applyPage.rptDetail}</td>
						</tr>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
        </table>
    </body>
</html>
