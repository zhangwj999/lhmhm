<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="lhMeetRoomList" title="会诊室管理" actionUrl="lhMeetRoomController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="公司" field="comId" replace="${comsReplace}"  query="true"></t:dgCol>
    <t:dgCol title="会诊室类型" field="roomType"  dictionary="roomtype"  query="true"></t:dgCol>
   <t:dgCol title="会诊室编号" field="roomId"  query="true" ></t:dgCol>
   <t:dgCol title="会诊室名称" field="roomName"  query="true"></t:dgCol>
   <t:dgCol title="会诊室IP" field="roomIp" ></t:dgCol>
   <t:dgCol title="备注" field="note" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lhMeetRoomController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="lhMeetRoomController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lhMeetRoomController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lhMeetRoomController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>