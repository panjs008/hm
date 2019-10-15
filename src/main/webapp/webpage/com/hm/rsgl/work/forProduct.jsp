<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
    <t:datagrid name="hmWorkList"  pagination="true" pageSize="20" sortOrder="desc" sortName="workDate" fitColumns="false" title="" actionUrl="hmWorkController.do?listProductByGgflJdbc" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
           <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
           <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
           <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
           <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
           <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
        <t:dgCol title="计算单位" field="calUnit" hidden="true"  query="true" dictionary="dg"   queryMode="single"  width="90"></t:dgCol>
        <t:dgCol title="月份"  field="workDate" query="true"  frozenColumn="true"  queryMode="single"  width="80"></t:dgCol>
        <%--<t:dgCol title="日期"  query="true" field="workDate"  queryMode="group"  width="90"></t:dgCol>--%>
            <t:dgCol title="主车间"  field="mainWorkName" frozenColumn="true"  query="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="主车间Code"  field="mainWorkCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="主班组"  field="workGroupName"  query="true"  queryMode="single"  width="120"></t:dgCol>
            <t:dgCol title="主班组Code"  field="workGroupCode"  hidden="true"  queryMode="single"  width="150"></t:dgCol>
            <t:dgCol title="重量"   field="total" formatterjs="setTotal"  queryMode="single"  width="100"></t:dgCol>
            <t:dgCol title="规格"  field="ggfl"  queryMode="single"  width="140"></t:dgCol>
            <t:dgCol title="单位"  field="unit"  queryMode="single"  width="50"></t:dgCol>
            <t:dgCol title="操作" field="opt" width="100" formatterjs="formatDetail"></t:dgCol>

        <%--<t:dgFunOpt funname="detailBill(mainWorkName,workGroupName,mainWorkCode,workGroupCode)" title="每日明细"  urlclass="ace_button"  urlStyle="background-color:#ec4758;" urlfont="fa-tasks"></t:dgFunOpt>--%>
    </t:datagrid>
  </div>
 </div>
<div data-options="region:'east',
	title:'明细',
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
 <script src = "webpage/com/hm/rsgl/work/hmWorkList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
   /*  $("#hmWorkListtb").find("input[name='workDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
     $("#hmWorkListtb").find("input[name='workDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});*/
     $("#hmWorkListtb").find("input[name='xmmc']").css("width","208px");
     $("#hmWorkListtb").find("input[name='workDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM'});});
     $("#hmWorkListtb").find("input[name='workDate']").val('${month}');

     $("#hmWorkListtb").find("select[name='calUnit']").empty();
      var html = '<option value="吨">吨</option>';
      html += '<option value="公斤">公斤</option>';
      html += '<option value="斤">斤</option>';
      $("#hmWorkListtb").find("select[name='calUnit']").append(html);

 });
 function formatDetail(val,row,index){
     var s = '<a href="javascript:detailBill(\''+row.mainWorkName+'\',\''+row.workGroupName+'\',\''+row.mainWorkCode+'\',\''+row.workGroupCode+'\',\''+row.workDate+'\',\''+row.unit+'\',\''+row.ggfl+'\')"><font color="blue">每日明细</font></a>';
     return s;
 }
 function setTotal(val,row,index){
     var calUnit = $("#hmWorkListtb").find("select[name='calUnit']").val();
     console.log(calUnit);
     if(calUnit == '吨'){
         return val;
     }else if(calUnit == '公斤'){
         return 1000*val;
     }else if(calUnit == '斤'){
         return 2000*val;
     }
 }
/* function formatUnit(val,row,index){
     return '吨';
 }*/
 function detailBill(mainWorkName,workGroupName,mainWorkCode,workGroupCode,workDate,unit,ggfl){
     var title = "每日产量明细："+workDate+"-"+mainWorkName+"-"+workGroupName;
     if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
         $('#main_list').layout('expand','east');
     }
     $('#main_list').layout('panel','east').panel('setTitle', title);
     $('#main_list').layout('panel','east').panel('resize', {width: 540});
     $('#proDetialListpanel').panel("refresh", "hmWorkController.do?forProductDay&mainWorkCode="+mainWorkCode+"&workGroupCode="+workGroupCode+"&workDate="+workDate+"&unit="+unit+"&ggfl="+ggfl);
 }
 </script>