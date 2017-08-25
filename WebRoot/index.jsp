<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<div>
	<button id="dispaly" onclick="display()" value="预览">开始</button>
</div>  
 <div id="playzone" width="550px" height="300" >
	<input type="text" id="testip" name="testip" value="hello"/>	
</div>
 <script type="text/javascript">
	//forReturn();
	function display(){
		
		var jlf= parent.document.getElementById("jlf");
		alert(jlf.value);
		alert(document.getElementById("testip"));
		 jlf=document.getElementById("testip").value;
		alert(jlf.value);
		//document.forms[0].action = "debitAcc_query_init.cmd";
		//window.location.href="http://127.0.0.1:8080/jeecg-framework/index.jsp";
		//document.forms[0].submit();
	}
</script>
</body>
</html>
