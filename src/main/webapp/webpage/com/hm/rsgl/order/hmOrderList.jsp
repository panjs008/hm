<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmOrderList" checkbox="false" sortName="orderNo"  pageSize="20" sortOrder="desc" pagination="true" fitColumns="true" title="" actionUrl="hmOrderController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="日期" query="true"  field="kdDate"  queryMode="group"  width="120"></t:dgCol>
      <t:dgCol title="工单编号"  field="orderNo"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="主班组" query="true" field="workGroupName"  queryMode="single"  width="110"></t:dgCol>
   <t:dgCol title="主班组代码"  hidden="true" field="workGroupCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="主车间" query="true" field="mainWorkName"  queryMode="single"  width="110"></t:dgCol>
   <t:dgCol title="主车间代码"  hidden="true" field="mainWorkCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="车间"  field="workName"  queryMode="single"  width="200"></t:dgCol>
   <t:dgCol title="车间代码"  hidden="true" field="workCode"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="班组"  field="groupName"  queryMode="single"  width="200"></t:dgCol>
   <t:dgCol title="班组代码"  hidden="true" field="groupCode"  queryMode="single"  width="120"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="hmOrderController.do?goAdd&type=1&winTitle=录入计件（形式二）" funname="add" width="1200" height="630"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="hmOrderController.do?goUpdate&winTitle=编辑计件（形式二）" funname="update" width="1200" height="630"></t:dgToolBar>
      <t:dgToolBar title="统计" icon="fa fa-search" url="hmOrderController.do?goUpdate" funname="look" width="1200" height="630"></t:dgToolBar>
      <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmOrderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/hm/rsgl/order/hmOrderList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
     $("#hmOrderListtb").find("input[name='kdDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
     $("#hmOrderListtb").find("input[name='kdDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });

 function look(){
     var height =window.top.document.body.offsetHeight*0.85;
     var rowsData = $('#hmOrderList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择数据');
         return;
     }
     createdetailwindow('计件形式二' , 'hmOrderController.do?goLook&orderId='+rowsData[0].id , 1350, height);
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hmOrderController.do?upload', "hmOrderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hmOrderController.do?exportXls","hmOrderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmOrderController.do?exportXlsByT","hmOrderList");
}

 </script>