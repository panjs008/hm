<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list"  class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkAccessoriesList" checkbox="false" pagination="true" fitColumns="false" sortName="kdDate" sortOrder="desc"  title="" actionUrl="emkAccessoriesController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>
      <t:dgCol title="当前流程节点"  field="processName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="缝制辅料开发单号"  frozenColumn="true" field="materialNo" queryMode="single" query="true"  width="120"></t:dgCol>
      <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="200"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="提交日期"  field="kdDate"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="交货日期"  field="dhjqDate"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="款号" query="true"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="描述"  field="sampleNoDesc"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="图片"  field="customSampleUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>

      <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="样品交期"  field="ysDate"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="距样品交期剩余天数"  field="leaveYpjqDays"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="大货交期"  field="dhjqDate"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="距大货交期剩余天数"  field="leaveDhjqDays"  queryMode="single"  width="90"></t:dgCol>

      <t:dgCol title="来源"  field="formType" replace="手工创建_0,询盘单生成_1" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="100"></t:dgCol>

      <t:dgFunOpt funname="goToProcess(id,createBy,processName,state)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>
      <%--<t:dgFunOpt funname="queryDetail(id,materialNo,state)" title="缝制辅料" urlclass="ace_button" urlfont="fa-list-alt"></t:dgFunOpt>--%>
      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkAccessoriesController.do?goAdd&winTitle=录入缝制辅料开发单" funname="add" height="600" width="1250"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkAccessoriesController.do?goUpdate&winTitle=编辑缝制辅料开发单" funname="update" height="600" width="1250"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkAccessoriesController.do?goUpdate&winTitle=查看缝制辅料开发单" funname="detail" height="600" width="1250"></t:dgToolBar>
      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up"  funname="doSubmitV"></t:dgToolBar>
      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkAccessoriesController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
      <t:dgToolBar title="导出PDF" icon="fa fa-arrow-circle-right"  funname="doPrintPDF"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>

 <script src = "webpage/com/emk/storage/accessories/emkAccessoriesList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function formatColor(val,row){
     if(row.state=="1"){
         return '<span style="color:	#0000FF;">已提交</span>';
     }else if(row.state=="2"){
         return '<span style="color:	#00FF00;">完成</span>';
     }else if(row.state=="3"){
         return '<span style="color:	#0000FF;">业务经理通过</span>';
     }else if(row.state=="35"){
         return '<span style="color:	#0000FF;">业务员通过</span>';
     }else if(row.state=="24"){
         return '<span style="color:	#0000FF;">采购员通过</span>';
     }else if(row.state=="15"){
         return '<span style="color:	#0000FF;">采购经理通过</span>';
     }else if(row.state=="37"){
         return '<span style="color:	#0000FF;">采购员执行</span>';
     }else if(row.state=="38"){
         return '<span style="color:	#0000FF;">采购员进度</span>';
     }else if(row.state=="39"){
         return '<span style="color:	#0000FF;">入库申请单</span>';
     }else if(row.state=="41"){
         return '<span style="color:	#0000FF;">采购部经理通过</span>';
     }else if(row.state=="43"){
         return '<span style="color:	#0000FF;">出库申请单</span>';
     }else{
         return '创建';
     }
 }


 function goToProcess(id,createBy,processName,state){
     var height =window.top.document.body.offsetHeight*0.85;
     var processNameVal='',node='';
     if(processName != null){
         if(processName.indexOf('-') > 0){
             processNameVal = processName.substring(0,processName.indexOf('-'));
             node = processName.substring(processName.indexOf('-')+1);
         }
     }
     if(state == '0'){
         createwindow("流程进度--当前环节：缝制辅料需求开发申请单", "flowController.do?goProcess&state="+state+"&node="+node+"&processUrl=com/emk/storage/accessories/emkAccessories-process&id=" + id, 1300, height);
     }else {
         createdetailwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&state="+state+"&node="+node+"&processUrl=com/emk/storage/accessories/emkAccessories-process&id=" + id, 1300, height);
     }

 }
 function doPrintPDF() {
     var rowsData = $('#emkAccessoriesList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要导出PDF的缝制辅料开发单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定导出PDF的缝制辅料开发单?', function(r) {
         if (r) {
             window.location.href = "emkAccessoriesController.do?doPrintPDF&ids="+ids;
         }
     });
 }


 function doSubmitV() {
     var rowsData = $('#emkAccessoriesList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的缝制辅料开发单');
         return;
     }

     $.dialog.confirm('您是否确定提交缝制辅料开发单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkAccessoriesController.do?doSubmit&id="+rowsData[0].id,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkAccessoriesList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

 function queryDetail(id,proName,state){
     $('#emkAccessoriesList').datagrid('unselectAll');
     var title = "缝制辅料："+proName ;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkSampleDetailController.do?list&type=accessories&state="+state+"&sampleId=" + id);
 }
 //导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkAccessoriesController.do?upload', "emkAccessoriesList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkAccessoriesController.do?exportXls","emkAccessoriesList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkAccessoriesController.do?exportXlsByT","emkAccessoriesList");
}

 </script>