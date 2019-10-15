<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmCheckStaffMqjList" checkbox="true" pagination="true" pageSize="20" fitColumns="true" title="" actionUrl="hmCheckStaffMqjController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="姓名"  field="realName" query="true"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="工号"  field="workNo"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="部门"  field="deptName" query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="车间"  field="workName" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="班组"  field="groupName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="职务"  field="job"  dictionary="job" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="性别"  field="sex" dictionary="sex" queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="员工类别"  field="yglb" dictionary="yglb" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="满勤奖"  field="mqj"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="全勤奖"  field="qqj"  queryMode="single"  width="90"></t:dgCol>

      <t:dgToolBar title="录入" icon="fa fa-plus" url="hmCheckStaffMqjController.do?goAdd&winTitle=录入考勤满勤奖配置" funname="add" width="900"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="hmCheckStaffMqjController.do?goUpdate&winTitle=编辑考勤满勤奖配置" funname="update" width="900"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmCheckStaffMqjController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="hmCheckStaffMqjController.do?goUpdate" funname="detail" width="900"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/hm/kq/checkstaffmqj/hmCheckStaffMqjList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hmCheckStaffMqjController.do?upload', "hmCheckStaffMqjList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hmCheckStaffMqjController.do?exportXls","hmCheckStaffMqjList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmCheckStaffMqjController.do?exportXlsByT","hmCheckStaffMqjList");
}

 </script>