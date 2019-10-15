<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmTsjsbList" checkbox="true" pagination="true" pageSize="20"  fitColumns="true" title="" actionUrl="hmTsjsbController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="工号"  field="workNo"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="姓名"  query="true" field="realName"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="部门"  query="true" field="deptName"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="车间" query="true" field="workName"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="班组" query="true" field="groupName"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="职务"  field="job"  dictionary="job" queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="员工类别"  field="yglb"  dictionary="yglb" queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="薪酬类别"  field="xclb"  dictionary="xclb" queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="正常班"  field="zcb"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="加班"  field="jiab"  queryMode="single"  width="90"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="hmTsjsbController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="hmTsjsbController.do?goUpdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmTsjsbController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="hmTsjsbController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/hm/rsgl/tsjsb/hmTsjsbList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hmTsjsbController.do?upload', "hmTsjsbList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hmTsjsbController.do?exportXls","hmTsjsbList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmTsjsbController.do?exportXlsByT","hmTsjsbList");
}

 </script>