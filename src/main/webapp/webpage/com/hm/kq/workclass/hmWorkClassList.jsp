<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmWorkClassList" checkbox="false" pagination="true" fitColumns="false" title="" actionUrl="hmWorkClassController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="班次名称"  field="bcName"  query="true" queryMode="single"  width="120"></t:dgCol>
   <%--<t:dgCol title="代码"  field="cCode"  queryMode="single"  width="120"></t:dgCol>--%>
   <t:dgCol title="打卡次数"  field="times"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="时长"  field="workTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="加班时长"  field="jiaTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="休息时长"  field="xiuTime"  queryMode="single"  width="120"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="hmWorkClassController.do?goAdd&winTitle=录入班次表" funname="add" height="500" width="800"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="hmWorkClassController.do?goUpdate&winTitle=编辑班次表" funname="update" height="500" width="800"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmWorkClassController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="hmWorkClassController.do?goUpdate&winTitle=查看班次表" funname="detail" height="500" width="800"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/hm/kq/workclass/hmWorkClassList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hmWorkClassController.do?upload', "hmWorkClassList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hmWorkClassController.do?exportXls","hmWorkClassList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmWorkClassController.do?exportXlsByT","hmWorkClassList");
}

 </script>