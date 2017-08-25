<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="lhPatieninfoList" title="病人管理" actionUrl="lhPatieninfoController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="医院编号" field="comId" ></t:dgCol>
   <t:dgCol title="住院号" field="fId" ></t:dgCol>
   <t:dgCol title="姓名" field="fName" ></t:dgCol>
   <t:dgCol title="年龄" field="fAge" ></t:dgCol>
   <t:dgCol title="性别 枚举SEX" field="fSex" ></t:dgCol>
   <t:dgCol title="科室OFFICE_ID" field="fType" ></t:dgCol>
   <t:dgCol title="医保卡号" field="medicalCard" ></t:dgCol>
   <t:dgCol title="患者唯一识别编号" field="patientPk" ></t:dgCol>
   <t:dgCol title="备注" field="fMemo" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="lhPatieninfoController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="lhPatieninfoController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="lhPatieninfoController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="lhPatieninfoController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>