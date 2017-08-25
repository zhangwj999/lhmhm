<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="patieninfoList" title="病人信息" actionUrl="patieninfoController.do?datagrid" queryMode="group" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="住院号" field="fid" query="true"></t:dgCol>
   <t:dgCol title="姓名" field="fname" query="true"></t:dgCol>
   <t:dgCol title="年龄" field="fage" ></t:dgCol>
   <t:dgCol title="性别" field="fsex" query="true" dictionary="sex"   ></t:dgCol>
   <t:dgCol title="科别" field="ftype" query="true" replace="${OfficesReplace}"></t:dgCol>
   <t:dgCol title="备注" field="fmemo" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="patieninfoController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="patieninfoController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="patieninfoController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="patieninfoController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>