<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="mx_main_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmWorkPriceList" checkbox="false" sortOrder="desc" sortName="kdDate" pagination="true" pageSize="20"  fitColumns="false" title="" actionUrl="hmWorkPriceController.do?datagrid&showEnd=1&kdDate=${param.month}-${param.day}&workNo=${param.workNo}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="类别"  field="workType"  hidden="true" queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title=""  field="zcb"  hidden="true" queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title=""  field="jiab"  hidden="true" queryMode="single"  width="120"></t:dgCol>


      <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="日期" field="kdDate" frozenColumn="true" query="true" rowspan="3" queryMode="group"  width="80"></t:dgCol>
      <t:dgCol title="主班组" rowspan="3" frozenColumn="true" query="true" field="workGroupName"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="主车间" rowspan="3" frozenColumn="true" query="true" field="mainWorkName"  queryMode="single"  width="90"></t:dgCol>
      <t:dgCol title="姓名" rowspan="3" frozenColumn="true" field="realName"  queryMode="single"  width="60"></t:dgCol>

      <t:dgCol title="计时公班" colspan="5" ></t:dgCol>
      <t:dgCol title="计件工资" colspan="12" newColumn="true"></t:dgCol>

      <t:dgCol title="正常班"   colspan="2"></t:dgCol>
      <t:dgCol title="加班"   colspan="2"  ></t:dgCol>
      <t:dgCol title="合计" rowspan="2"   field="gzhj4" formatterjs="setPoint" queryMode="single"  width="60"></t:dgCol>

      <t:dgCol title="计时工资"   colspan="5"></t:dgCol>
      <t:dgCol title="计件工时"  colspan="3"></t:dgCol>

      <t:dgCol title="计件一" rowspan="2"    formatterjs="setPoint"  field="gzHj0"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="计件二" rowspan="2"   formatterjs="setPoint"  field="gzHj1"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="计件三" rowspan="2"    formatterjs="setPoint"  field="gzHj2"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="计件四" rowspan="2"  newColumn="true"  formatterjs="setPoint"  field="gzHj3"  queryMode="single"  width="60"></t:dgCol>

      <t:dgCol title="出勤"  field="zcb0"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="单价"  field="ssPrice" formatterjs="formatPrice"   width="60"></t:dgCol>
      <t:dgCol title="出勤"  field="jiab0"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="单价"  field="ssPriceT" formatterjs="formatPrice2"   width="60"></t:dgCol>

      <t:dgCol title="正常班"  field="zsZcb"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="单价"  field="zsZcbPrice"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="加班"  field="zsJiab"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="单价"  field="zsJiabPrice"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="合计"  field="zshj" formatterjs="setPoint" queryMode="single"  width="60"></t:dgCol>

      <t:dgCol title="正常班"  field="zcb1"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="加班"  field="jiab1"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="合计"  field="zjhj" formatterjs="setPoint0"  queryMode="single"  width="60"></t:dgCol>

       <%--<t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
<div data-options="region:'east',
	title:'生产具体内容',
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
</div>
 <script src = "webpage/com/hm/rsgl/workprice/hmWorkPriceList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
     $("#hmWorkPriceListtb").find("input[name='kdDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
     $("#hmWorkPriceListtb").find("input[name='kdDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});

     var title = "生产具体内容";
     if(li_east == 0 || $('#mx_main_list').layout('panel','east').panel('options').title != title){
         $('#mx_main_list').layout('expand','east');
     }
     $('#mx_main_list').layout('panel','east').panel('setTitle', title);
     $('#mx_main_list').layout('panel','east').panel('resize', {width: 600});

     $('#proDetialListpanel').panel("refresh", "hmWorkController.do?forWorkPrice&workDate=${param.month}-${param.day}&workNo=${param.workNo}" );
 });
 function formatPrice(val,row,index){
     if(row.ssPrice != null && row.ssPrice != ''){
         return parseInt(row.ssPrice);
     }else{
         if(row.tsPrice != null && row.tsPrice != ''){
             return parseInt(row.tsPrice);
         }else{
             if(row.tyPrice != null && row.tyPrice != ''){
                 return parseInt(row.tyPrice);
             }else{
                 return '';
             }
         }
     }
 }
 function formatPrice2(val,row,index){
     if(row.ssPriceT != null && row.ssPriceT != ''){
         return parseInt(row.ssPriceT);
     }else{
         if(row.tsPriceT != null && row.tsPriceT != ''){
             return parseInt(row.tsPriceT);
         }else{
             if(row.tyPriceT != null && row.tyPriceT != ''){
                 return parseInt(row.tyPriceT);
             }else{
                 return '';
             }
         }
     }
 }
 function setPoint(val,row,index){
     if(val != null && val != ''){
         return parseInt(val);
     }else{
         return '';
     }
 }
 function setPoint0(val,row,index){
     if(val != null && val != ''){
         return formatDecimal(val,1);
     }else{
         return '';
     }
 }
 function formatDecimal(num, decimal) {
     num = num.toString()
     let index = num.indexOf('.')
     if (index !== -1) {
         num = num.substring(0, decimal + index + 1)
     } else {
         num = num.substring(0)
     }
     return parseFloat(num).toFixed(decimal)
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