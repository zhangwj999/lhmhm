<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="lhdoctorList" title="专家库管理" actionUrl="lhdoctorController.do?datagrid"  queryMode="group" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="医院" field="comId" replace="${comsReplace}"  query="true"></t:dgCol>
   <t:dgCol title="科室" field="officeId"  replace="${officesReplace}" query="true"></t:dgCol>
   <t:dgCol title="医生编号" field="doctorId" query="true"></t:dgCol>
   <t:dgCol title="医生姓名" field="doctorName" query="true"></t:dgCol>
   <t:dgCol title="职务岗位" field="positionId"  dictionary="postype"></t:dgCol>
   <t:dgCol title="收费类型标准" field="payKind" replace="${payKindReplace}"></t:dgCol>
   <t:dgCol title="用户编号" field="doctorPk" ></t:dgCol>
   <t:dgCol title="联系方式" field="tel" ></t:dgCol>
   <t:dgCol title="优势擅长" field="fmemo" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lhdoctorController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="lhdoctorController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lhdoctorController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lhdoctorController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>