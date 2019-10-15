<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="emkApprovalList" checkbox="false" pagination="true" fitColumns="true" pageSize="15" title="审批业务表" sortName="commitTime" sortOrder="desc" actionUrl="emkApprovalController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" width="80" ></t:dgCol>
      <t:dgCol title="单号"  field="workNum"  query="true" queryMode="single"  width="140"></t:dgCol>
      <t:dgCol title="工单类型"  field="type"
               replace="意向询盘单_0,验厂申请单_1,报价单_2,样品需求单_3,原料面料样品材料采购_4,入库申请单_5,出库申请单_6,缝制辅料样品材料采购_7,订单表_8,原料面料采购需求单_9,缝制辅料采购需求单_10,原料面料开发费付款申请单_12,缝制辅料开发费付款申请单_13,包装辅料开发费付款申请单_14,测试申请表_15,测试费用付款申请单_16,验货申请表_17,采购生产进度表_19"
               queryMode="single"  width="140"></t:dgCol>
      <t:dgCol title="提交人"  field="createName"    queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>

   <t:dgCol title="申请人ID"  field="commitId" hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="提交时间"  field="commitTime"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="当前节点名称"  field="processName" formatterjs="formatProcessName" queryMode="single"  width="180"></t:dgCol>
   <t:dgCol title="当前节点代码"  field="processNode"  hidden="true" queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="formId"  field="formId"  hidden="true" queryMode="single"  width="120"></t:dgCol>

      <t:dgCol title="节点状态"  field="status" formatterjs="formatColor"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审核人"  field="bpmSher"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="审核状态"  field="bpmStatus" formatterjs="formatBpmStateColor" queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="审核人ID"  field="bpmSherId"  hidden="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="审核时间"  field="bpmDate"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title=""  field="state1" hidden="true"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title=""  field="state2" hidden="true"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title=""  field="state3" hidden="true"  queryMode="single"  width="100"></t:dgCol>
      <t:dgFunOpt funname="goToProcess(formId,commitId,processNode,status,type,state1,state2,state3)" title="审批"  urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>
      <%--<t:dgToolBar title="录入" icon="fa fa-plus" url="emkApprovalController.do?goAdd" funname="add"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" url="emkApprovalController.do?goUpdate" funname="update"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" url="emkApprovalController.do?goUpdate" funname="detail"></t:dgToolBar>
      <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
      <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/emk/approval/approval/emkApprovalList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });

 function formatColor(val,row){
     if(row.status=="1"){
         return '<span style="color:	#0000FF;">已提交</span>';
     }else if(row.status=="2"){
         return '<span style="color:	#00FF00;">完成</span>';
     }else if(row.status=="3"){
         return '<span style="color:	#0000FF;">业务经理通过</span>';
     }else if(row.status=="4"){
         return '<span style="color:	#FF0000;">回退业务经理</span>';
     }else if(row.status=="5"){
         return '<span style="color:	#0000FF;">技术经理通过</span>';
     }else if(row.status=="6"){
         return '<span style="color:	#0000FF;">回退技术经理</span>';
     }else if(row.status=="7"){
         return '<span style="color:	#0000FF;">染色部经理通过</span>';
     }else if(row.status=="8"){
         return '<span style="color:	#0000FF;">回退染色部经理</span>';
     }else if(row.status=="9"){
         return '<span style="color:	#0000FF;">缝制部经理通过</span>';
     }else if(row.status=="10"){
         return '<span style="color:	#0000FF;">回退缝制部经理</span>';
     }else if(row.status=="11"){
         return '<span style="color:	#0000FF;">烫标整烫组长通过</span>';
     }else if(row.status=="12"){
         return '<span style="color:	#0000FF;">回退烫标整烫组长</span>';
     }else if(row.status=="13"){
         return '<span style="color:	#0000FF;">包装组长通过</span>';
     }else if(row.status=="14"){
         return '<span style="color:	#0000FF;">回退包装组长</span>';
     }else if(row.status=="15"){
         return '<span style="color:	#0000FF;">采购部经理通过</span>';
     }else if(row.status=="16"){
         return '<span style="color:	#0000FF;">回退采购部经理</span>';
     }else if(row.status=="17"){
         return '<span style="color:	#0000FF;">生产计划部通过</span>';
     }else if(row.status=="18"){
         return '<span style="color:	#0000FF;">回退生产计划部</span>';
     }else if(row.status=="19"){
         return '<span style="color:	#0000FF;">生产总负责人通过</span>';
     }else if(row.status=="20"){
         return '<span style="color:	#0000FF;">报价</span>';
     }else if(row.status=="21"){
         return '<span style="color:	#0000FF;">回退业务员</span>';
     }else if(row.status=="22"){
         return '<span style="color:	#0000FF;">技术员通过</span>';
     }else if(row.status=="23"){
         return '<span style="color:	#0000FF;">回退技术员</span>';
     }else if(row.status=="24"){
         return '<span style="color:	#0000FF;">采购员通过</span>';
     }else if(row.status=="25"){
         return '<span style="color:	#0000FF;">财务通过</span>';
     }else if(row.status=="26"){
         return '<span style="color:	#0000FF;">财务经理通过</span>';
     }else if(row.status=="27"){
         return '<span style="color:	#0000FF;">回退财务</span>';
     }else if(row.status=="30"){
         return '<span style="color:	#0000FF;">价格确认</span>';
     }else if(row.status=="31"){
         return '<span style="color:	#0000FF;">打样</span>';
     }else if(row.status=="33"){
         return '<span style="color:	#0000FF;">生产跟单员通过</span>';
     }else if(row.status=="35"){
         return '<span style="color:	#0000FF;">业务员通过</span>';
     }else if(row.status=="36"){
         return '<span style="color:	#0000FF;">回退采购员</span>';
     }else if(row.status=="37"){
         return '<span style="color:	#0000FF;">采购员执行</span>';
     }else if(row.status=="38"){
         return '<span style="color:	#0000FF;">采购员进度</span>';
     }else if(row.status=="39"){
         return '<span style="color:	#0000FF;">入库申请【采购员】</span>';
     }else if(row.status=="40"){
         return '<span style="color:	#0000FF;">采购员质检通过</span>';
     }else if(row.status=="41"){
         return '<span style="color:	#0000FF;">采购部经理通过</span>';
     }else if(row.status=="44"){
         return '<span style="color:	#0000FF;">生成预购销合同</span>';
     }else if(row.status=="46"){
         return '<span style="color:	#0000FF;">正式购销合同生成</span>';
     }else if(row.status=="47"){
         return '<span style="color:	#0000FF;">付款核准单</span>';
     }else if(row.status=="48"){
         return '<span style="color:	#0000FF;">总经理通过</span>';
     }else if(row.status=="49"){
         return '<span style="color:	#0000FF;">回退正式购销合同</span>';
     }else if(row.status=="51"){
         return '<span style="color:	#0000FF;">验厂经理通过</span>';
     }else if(row.status=="52"){
         return '<span style="color:	#0000FF;">执行验厂</span>';
     }else if(row.status=="53"){
         return '<span style="color:	#0000FF;">验厂报告</span>';
     }else if(row.status=="56"){
         return '<span style="color:	#0000FF;">通知发货</span>';
     }else if(row.status=="57"){
         return '<span style="color:	#0000FF;">采购员质检</span>';
     }else if(row.status=="58"){
         return '<span style="color:	#0000FF;">接收入库</span>';
     }else if(row.status=="60"){
         return '<span style="color:	#0000FF;">执行出库</span>';
     }else if(row.status=="61"){
         return '<span style="color:	#0000FF;">业务跟单员通过</span>';
     }else if(row.status=="62"){
         return '<span style="color:	#0000FF;">安排测试员</span>';
     }else if(row.status=="63"){
         return '<span style="color:	#0000FF;">测试报告通过</span>';
     }else if(row.status=="64"){
         return '<span style="color:	#0000FF;">回退业务跟单员</span>';
     }else if(row.status=="65"){
         return '<span style="color:	#0000FF;">更新测试进度</span>';
     }else if(row.status=="66"){
         return '<span style="color:	#0000FF;">质检经理通过</span>';
     }else if(row.status=="67"){
         return '<span style="color:	#0000FF;">执行验货</span>';
     }else if(row.status=="68"){
         return '<span style="color:	#0000FF;">验货报告</span>';
     }else if(row.status=="70"){
         return '<span style="color:	#0000FF;">完成采购</span>';
     }else if(row.status=="71"){
         return '<span style="color:	#0000FF;">染色</span>';
     }else if(row.status=="72"){
         return '<span style="color:	#0000FF;">完成染色</span>';
     }else if(row.status=="73"){
         return '<span style="color:	#0000FF;">完成裁剪</span>';
     }else if(row.status=="74"){
         return '<span style="color:	#0000FF;">完成缝制</span>';
     }else if(row.status=="75"){
         return '<span style="color:	#0000FF;">完成包装</span>';
     }else if(row.status=="76"){
         return '<span style="color:	#0000FF;">完成船样</span>';
     }else if(row.status=="77"){
         return '<span style="color:	#0000FF;">订舱通知单</span>';
     }else if(row.status=="78"){
         return '<span style="color:	#0000FF;">回退订舱通知单</span>';
     }else if(row.status=="79"){
         return '<span style="color:	#0000FF;">订舱</span>';
     }else if(row.status=="80"){
         return '<span style="color:	#0000FF;">验货申请</span>';
     }else if(row.status=="81"){
         return '<span style="color:	#0000FF;">完成原料布料</span>';
     }else if(row.status=="82"){
         return '<span style="color:	#0000FF;">出口通知单</span>';
     }else if(row.status=="83"){
         return '<span style="color:	#0000FF;">回退出口通知单</span>';
     }else if(row.status=="84"){
         return '<span style="color:	#0000FF;">离厂通知单</span>';
     }else if(row.status=="85"){
         return '<span style="color:	#0000FF;">仓库装货放行</span>';
     }else if(row.status=="86"){
         return '<span style="color:	#0000FF;">财务在复核</span>';
     }else{
         return '创建';
     }
 }
 function formatBpmStateColor(val,row){
     if(row.bpmStatus=="1"){
         return '<span style="color:	#FF0000;">驳回</span>';
     }else{
         return '<span style="color:	#0000FF;">通过</span>';
     }
 }
function formatProcessName(val,row){
    if(row.processNode != null){
        if(row.processNode.indexOf('-') > 0){
            var processNameVal = row.processNode.substring(0,row.processNode.indexOf('-'));
            return processNameVal;
        }
    }
}
 function goToProcess(id,createBy,processName,state,type,state1,state2,state3){
     var height =window.top.document.body.offsetHeight*0.85;
     var processNameVal='',node='',w=1280;
     var processUrl,initProcessName;
     if(type == '0'){
         processUrl = 'com/emk/storage/enquiry/emkEnquiry-process';
         initProcessName = '业务部审核';
         w = 1320;
     }else if(type == '1'){
         processUrl = 'com/emk/storage/factoryarchives/emkFactoryArchives-process';
         initProcessName = '【业务员】工厂信息表';
     }else if(type == '2'){
         processUrl = 'com/emk/storage/price/emkPrice-process';
         initProcessName = '报价单【业务员】';
     }else if(type == '3'){
         processUrl = 'com/emk/storage/samplerequired/emkSampleRequired-process';
         initProcessName = '样品需求【业务跟单员】';
     }else if(type == '4'){
         processUrl = 'com/emk/storage/material/emkMaterial-process';
         initProcessName = '原料面料需求开发单';
         w = 1300;
     }else if(type == '7'){
         processUrl = 'com/emk/storage/accessories/emkAccessories-process';
         initProcessName = '缝制辅料需求开发单';
         w = 1300;
     }else if(type == '5'){
         processUrl = 'com/emk/bound/minstorage/emkMInStorage-process';
         initProcessName = '入库申请单';
     }else if(type == '6'){
         processUrl = 'com/emk/bound/moutstorage/emkMOutStorage-process';
         initProcessName = '出库申请单';
     }else if(type == '8'){
         processUrl = 'com/emk/bill/proorder/emkProOrder-process';
         initProcessName = '订单表';
     }else if(type == '9'){
         processUrl = 'com/emk/bill/materialrequired/emkMaterialRequired-process';
         initProcessName = '原料面料采购需求单';
     }else if(type == '12'){
         processUrl = 'com/emk/bill/materialcontract/emkMaterialContract-process';
         initProcessName = '原料面料开发费付款申请单';
     }else if(type == '15'){
         processUrl = 'com/emk/produce/test/emkTest-process';
         initProcessName = '测试申请表';
     }else if(type == '16'){
         processUrl = 'com/emk/produce/testcost/emkTestCost-process';
         initProcessName = '测试费用付款申请';
     }else if(type == '17'){
         processUrl = 'com/emk/check/check/emkCheck-process';
         initProcessName = '验货申请表';
     }else if(type == '19'){
         processUrl = 'com/emk/produce/produce/emkProduce-process';
         initProcessName = '采购生产进度表';
         w = 1410;
     }
     if(state == '30'){
         initProcessName = processNameVal;
     }
     if(processName != null){
         if(processName.indexOf('-') > 0){
             processNameVal = processName.substring(0,processName.indexOf('-'));
             node = processName.substring(processName.indexOf('-')+1);
             initProcessName = processNameVal;
         }
     }
     createwindow("流程进度--当前环节："+initProcessName, "flowController.do?goProcess&node="+node+"&state="+state+"&processUrl="+processUrl+"&id=" + id, w, height);

 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'emkApprovalController.do?upload', "emkApprovalList");
}

//导出
function ExportXls() {
	JeecgExcelExport("emkApprovalController.do?exportXls","emkApprovalList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("emkApprovalController.do?exportXlsByT","emkApprovalList");
}

 </script>