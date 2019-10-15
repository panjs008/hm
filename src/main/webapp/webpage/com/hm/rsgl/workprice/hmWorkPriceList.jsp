<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmWorkPriceList" checkbox="true" sortOrder="desc" sortName="kdDate" pagination="true" pageSize="20"  fitColumns="true" title="" actionUrl="hmWorkPriceController.do?datagrid&type=${param.type}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="日期" query="true"  field="kdDate" rowspan="2" queryMode="group"  width="120"></t:dgCol>

      <t:dgCol title="主班组" rowspan="2" query="true" field="workGroupName"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="主车间" rowspan="2" query="true" field="mainWorkName"  queryMode="single"  width="120"></t:dgCol>

      <%--<t:dgCol title="工号" rowspan="2" field="workNo"  queryMode="single"  width="120"></t:dgCol>--%>
      <t:dgCol title="姓名" rowspan="2" query="true" field="realName"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="正常班"   colspan="4"></t:dgCol>
      <t:dgCol title="加班"   colspan="4"  ></t:dgCol>
      <t:dgCol title="合计" rowspan="2"  field="gzhj" formatterjs="setPoint"  queryMode="single"  width="120" newColumn="true"></t:dgCol>
      <t:dgCol title="出勤时间"  rowspan="2" field="zcb"  queryMode="single"  width="110"></t:dgCol>
      <t:dgCol title="实时单价"  field="ssPrice"  queryMode="ssPrice"  width="110"></t:dgCol>
      <t:dgCol title="特殊单价"  field="tsPrice"   queryMode="single"  width="110"></t:dgCol>
      <t:dgCol title="统一单价"  field="tyPrice"   queryMode="single"  width="110"></t:dgCol>

      <t:dgCol title="出勤时间"  field="jiab"  queryMode="single"  width="110"></t:dgCol>
      <t:dgCol title="实时单价"  field="ssPriceT"    queryMode="ssPrice"  width="110"></t:dgCol>
      <t:dgCol title="特殊单价"  field="tsPriceT"   queryMode="single"  width="110"></t:dgCol>
      <t:dgCol title="统一单价"  field="tyPriceT"  queryMode="single"  width="110" ></t:dgCol>

      <t:dgToolBar title="录入" icon="fa fa-plus" url="hmWorkPriceController.do?goAdd&winTitle=录入公班工时"  funname="add" width="1200" height="630"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="hmWorkPriceController.do?goUpdate&winTitle=编辑公班工时" funname="update"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmWorkPriceController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="hmWorkPriceController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/hm/rsgl/workprice/hmWorkPriceList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
     $("#hmWorkPriceListtb").find("input[name='kdDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
     $("#hmWorkPriceListtb").find("input[name='kdDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });


 function setPoint(val,row,index){
     if(val != null && val != ''){
         return parseInt(val);
     }else{
         return '';
     }
 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hmWorkPriceController.do?upload', "hmWorkPriceList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hmWorkPriceController.do?exportXls","hmWorkPriceList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmWorkPriceController.do?exportXlsByT","hmWorkPriceList");
}

 </script>