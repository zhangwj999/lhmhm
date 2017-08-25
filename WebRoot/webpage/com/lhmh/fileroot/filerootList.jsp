<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="filerootList" title="文档上传磁盘路径" actionUrl="filerootController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="机器IP" field="mip" ></t:dgCol>
   <t:dgCol title="磁盘路径" field="rootId" ></t:dgCol>
   <t:dgCol title="文件URL" field="fileUrl" ></t:dgCol>
   <t:dgCol title="文件名称" field="fileName" ></t:dgCol>
   <t:dgCol title="部门编码" field="deptId" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="filerootController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="filerootController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="filerootController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="filerootController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>