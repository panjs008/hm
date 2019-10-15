<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkProOrderList" checkbox="false" singleSelect="true" pagination="true" fitColumns="false" sortName="orderNo" sortOrder="desc" title="" actionUrl="emkProOrderController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" width="100" frozenColumn="true"></t:dgCol>
      <t:dgCol title="当前流程节点"  field="processName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>

      <%--<t:dgCol title="状态"  field="state"  formatterjs="formatColor"  queryMode="single"  dictionary="bpm_status"  width="60"></t:dgCol>--%>
   <t:dgCol title="订单号" frozenColumn="true"  field="orderNo" query="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="180"></t:dgCol>
      <t:dgCol title="业务部门"  query="true"  field="businesseDeptName"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="业务员"  query="true"  field="businesserName"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="业务跟单员"  query="true"  field="tracer"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="生产跟单员"  query="true" field="developer"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="款号"  query="true"  field="sampleNo"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="描述"   field="sampleNoDesc"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="图片"  field="customSampleUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>
      <t:dgCol title="订单日期"  query="true" field="orderTime"  queryMode="single"  width="80"></t:dgCol>

      <t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="总数量"  field="sumTotal"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="总体积"  field="sumBoxVolume"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="总毛重"  field="sumBoxMao"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="总净重"  field="sumBoxJz"  queryMode="single"  width="80"></t:dgCol>

      <t:dgCol title="客户单价"  field="price"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户金额"  field="cusJin"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>

      <%--<t:dgCol title="总数量"  field="sumTotal"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="总金额"  field="sumMoney"  queryMode="single"  width="60"></t:dgCol>--%>
      <%--<t:dgFunOpt funname="queryDetail1(id,orderNo,state)" title="明细" urlStyle="background-color:#ec4758;" urlclass="ace_button"></t:dgFunOpt>--%>
      <%--<t:dgFunOpt funname="queryDetail2(id,orderNo,state)" title="主辅料" urlclass="ace_button" ></t:dgFunOpt>--%>
      <%--<t:dgFunOpt funname="queryDetail3(id,orderNo,state)" title="条码" urlStyle="background-color:#ec4758;" urlclass="ace_button" ></t:dgFunOpt>--%>
      <%--<t:dgFunOpt funname="queryDetail4(id,orderNo,state)" title="包装" urlStyle="background-color:#18a689;" urlclass="ace_button" ></t:dgFunOpt>--%>
      <%--<t:dgFunOpt funname="queryDetail5(id,orderNo,state)" title="纸箱" urlStyle="background-color:#ec4758;" urlclass="ace_button" ></t:dgFunOpt>--%>
      <t:dgCol title="来源"  field="formType" replace="手工创建_0,询盘单生成_1" queryMode="single"  width="80"></t:dgCol>
      <t:dgFunOpt funname="goToProcess(id,createBy,processName,state)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>

      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add" url="emkProOrderController.do?goAdd&winTitle=录入订单" funname="add" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkProOrderController.do?goUpdate&winTitle=编辑订单" funname="update" height="600" width="1000"></t:dgToolBar>
      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up" funname="doSubmitV"></t:dgToolBar>
      <t:dgToolBar title="生成采购生产进度表" operationCode="submit" icon="fa fa-arrow-circle-up" funname="doScProduce"></t:dgToolBar>

      <%--<t:dgToolBar title="流程进度" operationCode="process" icon="fa fa-plus" funname="goToProcess"></t:dgToolBar>--%>

      <%--<t:dgToolBar title="审核" icon="fa fa-plus" funname="doSubmitV" height="600" width="1000"></t:dgToolBar>--%>
      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove" url="emkProOrderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
      <t:dgToolBar title="导出PDF" icon="fa fa-arrow-circle-right"  funname="doPrintPDF"></t:dgToolBar>


  </t:datagrid>
  </div>
 </div>

 <script src = "webpage/com/emk/bill/proorder/emkProOrderList.js"></script>		
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
     }else if(row.state=="44"){
         return '<span style="color:	#0000FF;">生成预购销合同</span>';
     }else if(row.state=="46"){
         return '<span style="color:	#0000FF;">正式购销合同生成</span>';
     }else if(row.state=="49"){
         return '<span style="color:	#0000FF;">回退正式购销合同</span>';
     }else{
         return '创建';
     }
 }
 function doPrintPDF() {
     var rowsData = $('#emkProOrderList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要导出PDF的订单表');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定导出PDF的订单表?', function(r) {
         if (r) {
             window.location.href = "emkProOrderController.do?doPrintPDF&ids="+ids;
         }
     });
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
     /*if(createBy == "${CUR_USER.userName}" || ${CUR_USER.userName eq 'admin'}){

     }*/
     if(state == '0'){
         createwindow("流程进度--当前环节：原料面料需求开发申请单", "flowController.do?goProcess&state="+state+"&node="+node+"&processUrl=com/emk/bill/proorder/emkProOrder-process&state="+state+"&id=" + id, 1300, height);
     }else {
         createdetailwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&state="+state+"&node="+node+"&processUrl=com/emk/bill/proorder/emkProOrder-process&state="+state+"&id=" + id, 1300, height);
     }
 }

 function doSubmitV() {
     var rowsData = $('#emkProOrderList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的订单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交订单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkProOrderController.do?doSubmit&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkProOrderList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }
 function doScProduce() {
     var rowsData = $('#emkProOrderList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要生成的采购生产进度表的订单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交生成采购生产进度表?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkProOrderController.do?doScProduce&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkProOrderList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

 function queryDetail1(id,eNo,state){
     var rowsData = $('#emkProOrderList').datagrid('getSelections');
     var title = "订单明细："+eNo+","+rowsData[0].cusName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkEnquiryDetailController.do?list&state="+state+"&enquiryId=" + id);
 }
 function queryDetail2(id,proName,state){
     var rowsData = $('#emkProOrderList').datagrid('getSelections');

     var title = "主辅料清单："+proName+","+rowsData[0].cusName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkSampleDetailController.do?list&sampleType=order&state="+state+"&sampleId=" + id);
 }
 function queryDetail3(id,proName,state){
     var rowsData = $('#emkProOrderList').datagrid('getSelections');
     var title = "条码："+proName+","+rowsData[0].cusName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkProOrderBarcodeController.do?list&state="+state+"&orderId=" + id);
 }

 function queryDetail4(id,proName,state){
     var rowsData = $('#emkProOrderList').datagrid('getSelections');
     var title = "包装："+proName+","+rowsData[0].cusName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "emkProOrderPackageController.do?list&state="+state+"&orderId=" + id);
 }

 function queryDetail5(id,proName,state){
     var rowsData = $('#emkProOrderList').datagrid('getSelections');
     var title = "纸箱："+proName +","+rowsData[0].cusName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 750});

     $('#proDetialListpanel').panel("refresh", "emkProOrderBoxController.do?list&state="+state+"&orderId=" + id);
 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkProOrderController.do?upload', "emkProOrderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkProOrderController.do?exportXls","emkProOrderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkProOrderController.do?exportXlsByT","emkProOrderList");
}

 </script>