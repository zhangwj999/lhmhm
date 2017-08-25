<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="channelList" title="通道管理" actionUrl="channelController.do?datagrid"  queryMode="group" idField="id" fit="true">
   <t:dgCol title="序号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="设备序号" field="cpk" ></t:dgCol>
   <t:dgCol title="手术室" field="officeId" query="true" replace="${OfficesReplace}"></t:dgCol>
   <t:dgCol title="视频编号" field="chanId" ></t:dgCol>
   <t:dgCol title="视频名称" field="chanName" ></t:dgCol>
   <t:dgCol title="IP地址" field="ip" ></t:dgCol>
   <t:dgCol title="端口" field="port" ></t:dgCol>
   <t:dgCol title="设备类型" field="omc" dictionary="devicetype" query="true"></t:dgCol>
      <t:dgCol title="存储IP" field="nvrip" ></t:dgCol>   
   <t:dgCol title="是否启用" field="status" dictionary="status" query="true" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="channelController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="channelController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="channelController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="channelController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>