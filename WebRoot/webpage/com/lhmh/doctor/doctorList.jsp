<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="doctorList" title="医护人员" actionUrl="doctorController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="fbh" ></t:dgCol>
   <t:dgCol title="姓名" field="fname" ></t:dgCol>
   <t:dgCol title="科别" field="ftype" dictionary="rylx"></t:dgCol>
   <t:dgCol title="备注" field="fmemo" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="doctorController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="doctorController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="doctorController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="doctorController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>