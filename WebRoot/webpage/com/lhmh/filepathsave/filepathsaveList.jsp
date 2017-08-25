<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
<%
	String applyId = (String)request.getAttribute("applyId");
%>
</script>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="filepathsaveList" title="上传资料路径信息" actionUrl="filepathsaveController.do?datagrid" idField="id" fit="true">
   <input type="hidden" name="applyId" id="applyId" value="<%=applyId %>"/>
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="医院编号" field="comId" ></t:dgCol>
   <t:dgCol title="单据编号" field="infoId"></t:dgCol>
   <t:dgCol title="行号" field="seq" ></t:dgCol>
   <t:dgCol title="文件类型" field="fileType" ></t:dgCol>
   <t:dgCol title="文件路径" field="fileDocId" ></t:dgCol>
   <t:dgCol title="文件名" field="fileName" ></t:dgCol>
   <t:dgCol title="是否使用0:否,1:是" field="isMrb" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="filepathsaveController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="filepathsaveController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="filepathsaveController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="filepathsaveController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>