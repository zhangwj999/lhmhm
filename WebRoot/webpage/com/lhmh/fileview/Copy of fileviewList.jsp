<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="fileviewList" title="视频回放" actionUrl="fileviewController.do?datagrid" idField="id" fit="true" >
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="通道" field="channelId" ></t:dgCol>
   <t:dgCol title="手术室" field="officeId" ></t:dgCol>
   <t:dgCol title="开始时间" field="begintime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="结束时间" field="endtime"  formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="查询日期" field="date1" formatter="yyyy-MM-dd"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="fileviewController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="fileviewController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="fileviewController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="fileviewController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
  <div id="fileviewListtb" style="padding: 3px; height: 25px">
<div style="float: left;"><a href="#" id="add" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('用户录入','fileviewController.do?addorupdate','userList2')">用户录入</a> <a href="#"
	class="update" plain="true" icon="icon-edit" onclick="update('用户编辑','fileviewController.do?addorupdate','userList2')">用户编辑</a></div>
<div align="right">用户名: <input class="easyui-validatebox" name="userName" style="width: 80px"> 真实姓名: <input class="easyui-validatebox" name="realName" style="width: 80px"> <a
	href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="userListsearch();">查询</a></div>
</div>
</div>
 </div>