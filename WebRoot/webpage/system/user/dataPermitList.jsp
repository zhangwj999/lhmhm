<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addUserDataPermitEntityBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delUserDataPermitEntityBtn').linkbutton({
	    iconCls: 'icon-remove'  
	}); 
	$('#addUserDataPermitEntityBtn').bind('click', function(){   
			 var tr =  $("#add_userDataPermitEntity_table_template tr").clone();
	 	 $("#add_userDataPermitEntity_table").append(tr);
	 	 resetTrNum('add_userDataPermitEntity_table');
	});  
	$('#delUserDataPermitEntityBtn').bind('click', function(){   
	  	$("#add_userDataPermitEntity_table").find("input:checked").parent().parent().remove();   
	    resetTrNum('add_userDataPermitEntity_table'); 
	}); 
	$(document).ready(function(){
		$(".datagrid-toolbar").parent().css("width","auto");
	});
	//人员
	$(function() {
		$.ajax({ //一个Ajax过程 
           type : "get", //以post方式与后台沟通
           async : false,
           url : 'userController.do?getUserList',
//            dataType : 'text',//
           success : function(json) {
        	   	json = eval( '('+json+')' );
	         	var html1="<option ></option>";
	         	var userList = json;
				for(var i=0;i<userList.length;i++){
					var user = eval(userList[i]);
					html1+="<option value='"+user.id+"'>"+user.userName+"</option>"; 
				}
	         	$("#dataUserId0").append(html1);         	 
           }
		});
	});
	//部门
	$(function() {
		$.ajax({ //一个Ajax过程 
           type : "get", //以post方式与后台沟通
           async : false,
           url : 'userController.do?getOrgList',
//            dataType : 'text',//
           success : function(json) {
	         	var html1="<option ></option>";
				for(var i=0;i<json.length;i++){
					var user = json[i];
					html1+="<option value='"+user.id+"'>"+user.departname+"</option>"; 
				}
	         	$("#dataDepartId0").append(html1);         	 
           }
		});
	});
	function setInvalidOpt(obj){
		var type = $(obj).val();
		var dataOrgId = $(obj).parents("tr").find("select[name*='dataOrgId']");
		var dataUserId = $(obj).parents("tr").find("select[name*='dataUserId']");
		if(type == null || type === undefined){
			dataOrgId.removeAttr("disabled");
			dataUserId.removeAttr("disabled");
		}else if('1'==type){
			dataOrgId.removeAttr("disabled");
			dataUserId.attr("disabled","true");
		}else if('2'==type){
			dataOrgId.attr("disabled","true");
			dataUserId.removeAttr("disabled");
		}
	}
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addUserDataPermitEntityBtn" href="#">添加</a> <a id="delUserDataPermitEntityBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="userDataPermitEntity_table" width="65%">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">权限类型</td>
				  <td align="left" bgcolor="#EEEEEE">部门权限</td>
				  <td align="left" bgcolor="#EEEEEE">人员权限</td>
	</tr>
	<tbody id="add_userDataPermitEntity_table">	
	<c:if test="${fn:length(userDataPermitEntityList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left">
				  	<select name="userDataPermitEntityList[0].datapermitType" style="width:120px;" onchange="setInvalidOpt(this)">
				  		<option></option>
				  		<option value="1">部门权限</option>
				  		<option value="2">人员权限</option>
				  	</select>
				  </td>
				  <td align="left"><select name="userDataPermitEntityList[0].dataOrgId" id="dataDepartId0"  style="width:120px;"></select></td>
				  <td align="left"><select name="userDataPermitEntityList[0].dataUserId"  id="dataUserId0"  style="width:120px;"></select></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(userDataPermitEntityList)  > 0 }">
		<c:forEach items="${userDataPermitEntityList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<td align="center">
					<input name="userDataPermitEntityList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				  	<input name="userDataPermitEntityList[${stuts.index }].datapermitType" value="${poVal.datapermitType}"  type="hidden" />
				  	
					  	<c:if test="${poVal.datapermitType=='1'}"><input style="width:120px;" readonly="readonly" type="text" value="部门权限"/></c:if>
					  	<c:if test="${poVal.datapermitType=='2'}"><input style="width:120px;" readonly="readonly" type="text" value="人员权限"/></c:if>
 				  
			   	</td>
				<td align="left">
					<input type="hidden" name="userDataPermitEntityList[${stuts.index}].dataOrgId"  value="${poVal.dataOrgId}" maxlength="32" style="width:120px;" />
					<input style="width:120px;" type="text" readonly="readonly" value="${orgList[stuts.index]}" />
				</td>
				<td align="left">
					<input type="hidden" name="userDataPermitEntityList[${stuts.index }].dataUserId"  value="${poVal.dataUserId}"  maxlength="32"  style="width:120px;" />
					<input style="width:120px;" type="text" readonly="readonly" value="${userList[stuts.index]}"/>
				</td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>