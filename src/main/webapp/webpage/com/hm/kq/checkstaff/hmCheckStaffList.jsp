<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmCheckStaffList" checkbox="true" pageSize="20" pagination="true" fitColumns="true" title="" actionUrl="hmCheckStaffController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="工号" hidden="true"  field="workNo"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="部门"  query="true" field="deptName"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="车间"  query="true" field="workName"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="班组"  field="groupName"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="姓名" query="true" field="realName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="职务"  field="job" dictionary="job"  queryMode="single"  width="100"></t:dgCol>
      <%--<t:dgCol title="班次"  field="bcName"  dictionary="bclx" queryMode="single"  width="100"></t:dgCol>--%>
      <t:dgCol title="假日"  field="holiday"  dictionary="jiari" queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="性别"  field="sex"  dictionary="sex" queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="员工类别"  field="yglb" dictionary="yglb"  queryMode="single"  width="80"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="hmCheckStaffController.do?goAdd&winTitle=录入考勤配置" funname="add" width="900"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="hmCheckStaffController.do?goUpdate&winTitle=编辑考勤配置" funname="update" width="900"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmCheckStaffController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="hmCheckStaffController.do?goUpdate" funname="detail" width="1000"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/hm/kq/checkstaff/hmCheckStaffList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hmCheckStaffController.do?upload', "hmCheckStaffList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hmCheckStaffController.do?exportXls","hmCheckStaffList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmCheckStaffController.do?exportXlsByT","hmCheckStaffList");
}

 </script>