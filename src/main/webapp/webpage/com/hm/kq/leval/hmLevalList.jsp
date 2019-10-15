<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmLevalList" checkbox="false" pagination="true" fitColumns="true" title="" actionUrl="hmLevalController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="工号"  field="workNo"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="姓名"  field="realName" query="true"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="部门"  field="deptName"  queryMode="single"  width="110"></t:dgCol>
      <t:dgCol title="申请日期"  field="applyDate" query="true"  queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="请假类型" dictionary="qjlx"  field="levalType"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="时长"  field="longTime"  queryMode="single"  width="40"></t:dgCol>
      <t:dgCol title="时间单位"  replace="天_0,小时_1" field="timeType"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="开始时间"  formatterjs="setTime" field="beginTime"  queryMode="single"  width="110"></t:dgCol>
   <t:dgCol title="结束时间"  formatterjs="setTime" field="endTime"  queryMode="single"  width="110"></t:dgCol>
   <t:dgCol title="事由"  field="reason"  queryMode="single"  width="250"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="hmLevalController.do?goAdd&winTitle=录入请假单" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="hmLevalController.do?goUpdate&winTitle=编辑请假单" funname="update"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmLevalController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="hmLevalController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/hm/kq/leval/hmLevalList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
     $("#hmLevalListtb").find("input[name='applyDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
     $("#hmLevalListtb").find("input[name='applyDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });

 function setTime(val,row,index){
     if(val != null && val != ''){
         return val +":00:00";
     }else{
         return '';
     }
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hmLevalController.do?upload', "hmLevalList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hmLevalController.do?exportXls","hmLevalList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmLevalController.do?exportXlsByT","hmLevalList");
}

 </script>