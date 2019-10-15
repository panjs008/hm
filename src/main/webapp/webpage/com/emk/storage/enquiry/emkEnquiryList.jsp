<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkEnquiryList" checkbox="false" pagination="true" sortOrder="desc" sortName="kdDate" pageSize="20" fitColumns="false" title="" actionUrl="emkEnquiryController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="当前流程节点"  field="processName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>

      <t:dgCol title="操作" field="opt" width="100" frozenColumn="true"></t:dgCol>
      <t:dgCol title="意向单号"  field="enquiryNo" frozenColumn="true" query="true" queryMode="single"  width="135"></t:dgCol>

      <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="客户代码" query="true" field="cusNum"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="客户名称" query="true" field="cusName"  queryMode="single"  width="200"></t:dgCol>
      <t:dgCol title="业务部门"  field="businesseDeptName"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="业务员" query="true" field="businesserName"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="款号"  query="true" field="sampleNo"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="描述"  field="sampleNoDesc"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="图片"  field="customSampleUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>

      <t:dgCol title="提交日期"  field="kdDate"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="大货交期"  field="ysDate"  queryMode="single"  width="120"></t:dgCol>
      <%--<t:dgCol title="原样"  field="oldImageUrl" imageSize="30,30"  image="true"  queryMode="single"  width="50"></t:dgCol>--%>

      <t:dgCol title="业务跟单员"  field="tracer"  queryMode="single"  width="90"></t:dgCol>

      <%--<t:dgCol title="工艺种类"  field="gyzl"  dictionary="gylx" queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="款式大类"  field="proTypeName"  queryMode="single"  width="70"></t:dgCol>--%>
   <t:dgCol title="数量"  field="sumTotal"  queryMode="single"  width="60"></t:dgCol>
   <%--<t:dgCol title="币种"  field="bz"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="总金额"  field="sumMoney"  queryMode="single"  width="60"></t:dgCol>--%>
      <t:dgCol title="意向订单状态"  field="state" formatterjs="formatColor"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title=""  field="state1" hidden="true"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title=""  field="state2" hidden="true"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title=""  field="state3" hidden="true"  queryMode="single"  width="100"></t:dgCol>

      <%--<t:dgFunOpt funname="queryDetail1(id,enquiryNo,state)" title="明细" urlclass="ace_button" urlfont="fa-list-alt"></t:dgFunOpt>--%>
      <t:dgFunOpt funname="goToProcess(id,createBy,processName,state,state1,state2,state3)" title="流程进度" operationCode="process" urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>

      <t:dgToolBar title="录入" icon="fa fa-plus" operationCode="add"  url="emkEnquiryController.do?goAdd&winTitle=录入意向询盘单" funname="add" height="650" width="1000"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" operationCode="edit" url="emkEnquiryController.do?goUpdate&winTitle=编辑意向询盘单" funname="update" height="650" width="1000"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" operationCode="look" url="emkEnquiryController.do?goUpdate&winTitle=查看意向询盘单" funname="detail" height="650" width="1000"></t:dgToolBar>
      <t:dgToolBar title="提交" operationCode="submit" icon="fa fa-arrow-circle-up"  funname="doSubmitV"></t:dgToolBar>
      <%--<t:dgToolBar title="流程进度" operationCode="process" icon="fa fa-plus"  funname="goToProcess"></t:dgToolBar>--%>

      <t:dgToolBar title="删除" operationCode="delete"  icon="fa fa-remove"  url="emkEnquiryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="导出" operationCode="exp" icon="fa fa-arrow-circle-right"  funname="ExportXls"></t:dgToolBar>
      <t:dgToolBar title="导出PDF" icon="fa fa-arrow-circle-right"  funname="doPrintPDF"></t:dgToolBar>


  </t:datagrid>
  </div>
 </div>
<%--<div data-options="region:'east',
	title:'清单明细',
	collapsed:true,
	split:true,
	border:false,
	onExpand : function(){
		li_east = 1;
	},
	onCollapse : function() {
	    li_east = 0;
	}"
     style="width: 500px; overflow: hidden;" id="eastPanel">
    <div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false" id="proDetialListpanel"></div>
</div>--%>
 <script src = "webpage/com/emk/storage/enquiry/emkEnquiryList.js"></script>
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
     }else if(row.state=="4"){
         return '<span style="color:	#FF0000;">回退业务经理</span>';
     }else if(row.state=="5"){
         return '<span style="color:	#0000FF;">技术经理通过</span>';
     }else if(row.state=="6"){
         return '<span style="color:	#0000FF;">回退技术经理</span>';
     }else if(row.state=="7"){
         return '<span style="color:	#0000FF;">染色部经理通过</span>';
     }else if(row.state=="8"){
         return '<span style="color:	#0000FF;">回退染色部经理</span>';
     }else if(row.state=="9"){
         return '<span style="color:	#0000FF;">缝制部经理通过</span>';
     }else if(row.state=="10"){
         return '<span style="color:	#0000FF;">回退缝制部经理</span>';
     }else if(row.state=="11"){
         return '<span style="color:	#0000FF;">烫标整烫组长通过</span>';
     }else if(row.state=="12"){
         return '<span style="color:	#0000FF;">回退烫标整烫组长</span>';
     }else if(row.state=="13"){
         return '<span style="color:	#0000FF;">包装组长通过</span>';
     }else if(row.state=="14"){
         return '<span style="color:	#0000FF;">回退包装组长</span>';
     }else if(row.state=="15"){
         return '<span style="color:	#0000FF;">采购部经理通过</span>';
     }else if(row.state=="16"){
         return '<span style="color:	#0000FF;">回退采购部经理</span>';
     }else if(row.state=="17"){
         return '<span style="color:	#0000FF;">生产计划部通过</span>';
     }else if(row.state=="18"){
         return '<span style="color:	#0000FF;">回退生产计划部</span>';
     }else if(row.state=="19"){
         return '<span style="color:	#0000FF;">生产总负责人通过</span>';
     }else if(row.state=="20"){
         return '<span style="color:	#0000FF;">报价</span>';
     }else if(row.state=="21"){
         return '<span style="color:	#0000FF;">回退业务员</span>';
     }else if(row.state=="22"){
         return '<span style="color:	#0000FF;">技术员通过</span>';
     }else if(row.state=="23"){
         return '<span style="color:	#0000FF;">回退技术员</span>';
     }else if(row.state=="24"){
         return '<span style="color:	#0000FF;">采购员通过</span>';
     }else if(row.state=="25"){
         return '<span style="color:	#0000FF;">财务通过</span>';
     }else if(row.state=="26"){
         return '<span style="color:	#0000FF;">财务经理通过</span>';
     }else if(row.state=="27"){
         return '<span style="color:	#0000FF;">回退财务</span>';
     }else if(row.state=="30"){
         return '<span style="color:	#0000FF;">价格确认</span>';
     }else if(row.state=="31"){
         return '<span style="color:	#0000FF;">打样</span>';
     }else if(row.state=="33"){
         return '<span style="color:	#0000FF;">生产跟单员通过</span>';
     }else{
         return '创建';
     }
 }

 function goToProcess(id,createBy,processName,state,state1,state2,state3){
     var height =window.top.document.body.offsetHeight*0.85;
     var processNameVal='',node='';
     if(processName != null){
         if(processName.indexOf('-') > 0){
             processNameVal = processName.substring(0,processName.indexOf('-'));
             node = processName.substring(processName.indexOf('-')+1);
         }
     }
     if(state == '0'){
         createwindow("流程进度--当前环节：业务部审核", "flowController.do?goProcess&state="+state+"&node="+node+"&processUrl=com/emk/storage/enquiry/emkEnquiry-process&id=" + id, 1320, height);
     }else {
         createdetailwindow("流程进度--当前环节：" + processNameVal, "flowController.do?goProcess&state="+state+"&node="+node+"&processUrl=com/emk/storage/enquiry/emkEnquiry-process&id=" + id, 1320, height);
     }
 }
 function queryDetail1(id,eNo,state){
     $('#emkEnquiryList').datagrid('unselectAll');
     var title = "询盘订单明细："+eNo;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 500});

     $('#proDetialListpanel').panel("refresh", "emkEnquiryDetailController.do?list&state="+state+"&enquiryId=" + id);
 }

 function doSubmitV() {
     var rowsData = $('#emkEnquiryList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要提交的询盘订单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定提交询盘订单?', function(r) {
         if (r) {
             $.ajax({
                 url : "emkEnquiryController.do?doSubmit&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#emkEnquiryList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }

 function doPrintPDF() {
     var rowsData = $('#emkEnquiryList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择需要导出PDF的询盘订单');
         return;
     }
     for ( var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定导出PDF的询盘订单?', function(r) {
         if (r) {
             window.location.href = "emkEnquiryController.do?doPrintPDF&ids="+ids;
         }
     });
 }

//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkEnquiryController.do?upload', "emkEnquiryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkEnquiryController.do?exportXls","emkEnquiryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkEnquiryController.do?exportXlsByT","emkEnquiryList");
}

 </script>