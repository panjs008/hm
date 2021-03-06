<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkMOutStorageList" checkbox="false" pagination="true" fitColumns="true" sortOrder="desc" sortName="kdDate"  title="" actionUrl="emkMOutStorageController.do?datagrid&type=0" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
      <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" frozenColumn="true"  width="100"></t:dgCol>

      <%--<t:dgCol title="预采购合同号"  field="yhtNum" queryMode="single" width="105"></t:dgCol>--%>
      <%--<t:dgCol title="采购合同号"  query="true"  field="htNum" queryMode="single" width="105"></t:dgCol>--%>
      <t:dgCol title="出库单号" query="true"   field="ckNo" queryMode="single" width="110"></t:dgCol>
      <t:dgCol title="订单号" query="true"   field="orderNo" queryMode="single" width="100"></t:dgCol>
      <t:dgCol title="出库日期"  field="outDate"  queryMode="single"  width="80"></t:dgCol>
      <%--<t:dgCol title="出货日期"  field="outDate"  queryMode="single"  width="80"></t:dgCol>--%>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务员"  field="businesserName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="跟单员"  field="developer"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户代码" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户名称" field="cusName"  queryMode="single"  width="160"></t:dgCol>
      <t:dgCol title="款号"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="当前流程节点"  field="processName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="来源"  field="formType" replace="手工创建_0,询盘单生成_1" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="100"></t:dgCol>
      <t:dgFunOpt funname="goToProcess(id,createBy,processName,state)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>

      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkMOutStorageController.do?goAdd&type=0&winTitle=添加原料布料出库申请单" funname="add" height="600" width="1200"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkMOutStorageController.do?goUpdate&type=0&winTitle=编辑原料布料出库申请单" funname="update" height="600" width="1200"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkMOutStorageController.do?goUpdate&type=0&winTitle=查看原料布料出库申请单" funname="detail" height="600" width="1200"></t:dgToolBar>

      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up" funname="doSubmitV"></t:dgToolBar>
      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkMOutStorageController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
      <t:dgToolBar title="导出PDF" icon="fa fa-arrow-circle-right"  funname="doPrintPDF"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/bound/moutstorage/emkMOutStorageList.js"></script>		
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
     /*if(createBy == "${CUR_USER.userName}" || ${ROLE.rolecode eq 'cky'}  || ${CUR_USER.userName eq 'admin'}){

     }*/
     if(state == '0'){
         createwindow("流程进度--当前环节：出库申请单", "flowController.do?goProcess&state="+state+"&node="+node+"&processUrl=com/emk/bound/moutstorage/emkMOutStorage-process&id=" + id, 1230, height);
     }else {
         createdetailwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&state="+state+"&node="+node+"&processUrl=com/emk/bound/moutstorage/emkMOutStorage-process&id=" + id, 1230, height);
     }
 }
 function doPrintPDF() {
     var rowsData = $('#emkMOutStorageList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要导出PDF的原料面料出库申请单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定导出PDF的原料面料出库申请单?', function(r) {
         if (r) {
             window.location.href = "emkMOutStorageController.do?doPrintPDF&ids="+ids;
         }
     });
 }
 function doSubmitV() {
     var rowsData = $('#emkMOutStorageList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的原料布料出库申请单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交原料布料出库申请单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkMOutStorageController.do?doSubmit&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkMOutStorageList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkMOutStorageController.do?upload', "emkMOutStorageList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkMOutStorageController.do?exportXls","emkMOutStorageList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkMOutStorageController.do?exportXlsByT","emkMOutStorageList");
}

 </script>