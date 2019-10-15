<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmCheckPayList" checkbox="true" pagination="true" pageSize="20" fitColumns="true" title="" actionUrl="hmCheckPayController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="月份"  field="month" query="true"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="工号"  field="workNo"  queryMode="single"  width="60"></t:dgCol>
   <t:dgCol title="姓名"  field="realName" query="true" queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="部门名称"  field="deptName" query="true"  queryMode="single"  width="110"></t:dgCol>
      <t:dgCol title="考勤时间"  field="checkTime"  queryMode="single"  width="110"></t:dgCol>
   <t:dgCol title="打卡时间"  field="clockTime"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="扣费类型"  field="payType" query="true"  replace="迟到_0,早退_1,旷工_2,缺卡_3" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="时间/分钟"  field="longTime"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="明细"  field="precent"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="扣款金额"  field="payMoney"  queryMode="single"  width="80"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="hmCheckPayController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="hmCheckPayController.do?goUpdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmCheckPayController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="hmCheckPayController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/hm/kq/checkpay/hmCheckPayList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
     $("#hmCheckPayListtb").find("input[name='month']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM'});});
     $("#hmCheckPayListtb").find("input[name='month']").val('${month}');
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hmCheckPayController.do?upload', "hmCheckPayList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hmCheckPayController.do?exportXls","hmCheckPayList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmCheckPayController.do?exportXlsByT","hmCheckPayList");
}

 </script>