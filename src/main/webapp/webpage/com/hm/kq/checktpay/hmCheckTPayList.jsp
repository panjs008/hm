<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmCheckTPayList" checkbox="true" pageSize="20" pagination="true" fitColumns="false" title="" actionUrl="hmCheckTPayController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="月份"    field="month" query="true"    queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="工号"  field="workNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="姓名" query="true" field="realName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="部门名称" query="true"  field="deptName"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="旷工天数"  field="kgDay"  formatterjs="setMoney" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="旷工金额"  field="kgMoney" formatterjs="setMoney"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="迟到金额"  field="cdMoney"  formatterjs="setMoney" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="早退金额"  field="zdMoney"  formatterjs="setMoney" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="缺卡金额"  field="qkMoney"  formatterjs="setMoney" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="请假时长"  field="qjTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="请假金额"  field="qjMoney" formatterjs="setMoney"  queryMode="single"  width="120"></t:dgCol>
       <t:dgToolBar title="查看" icon="fa fa-search" url="hmCheckTPayController.do?goUpdate" funname="detail"></t:dgToolBar>
      <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmCheckTPayController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/hm/kq/checktpay/hmCheckTPayList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
     $("#hmCheckTPayListtb").find("input[name='month']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM'});});
 });

 function setMoney(val,row,index){
     if(val != null && val != ''){
         return parseInt(val);
     }else{
         return '';
     }
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hmCheckTPayController.do?upload', "hmCheckTPayList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hmCheckTPayController.do?exportXls","hmCheckTPayList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmCheckTPayController.do?exportXlsByT","hmCheckTPayList");
}

 </script>