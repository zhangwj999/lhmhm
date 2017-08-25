<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>视频回放</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <link
	href="<%=path%>/webpage/com/lhmh/FinancialRegistration/tablecloth/tablecloth.css"
	rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript"
	src="<%=path%>/webpage/com/lhmh/FinancialRegistration/tablecloth/tablecloth.js"></script>
<div>
<table cellspacing="0" cellpadding="0">
<tr height="25px">
	<td align="right"><button id="start" onclick="start()" value="开始视频">开始</button></td>	
    <td align="right"><button id="stop" onclick="stop()" value="结束视频">结束</button></td>    
  </tr>
  	<input type="text" id="tbegin" name="tbegin" value="1111"/>	
    <input type="text" id="tend" name="tend" value=""/>	</table>
</div>  
 <div id="playzone" width="550px" height="300" >

</div>
 <script type="text/javascript">
	//forReturn();
	function start(){
	   // document.getElementById("tbegin").value="aaaa";
		var tbegin=document.getElementById("tbegin").value;
		parent.document.getElementById("begin").value=tbegin;
	
	}
	
	function stop(){
		document.getElementById("tend").value="bbb";
		var tend=document.getElementById("tend").value;
		parent.document.getElementById("end").value=tend;
	}
</script>
</body>
</html>
