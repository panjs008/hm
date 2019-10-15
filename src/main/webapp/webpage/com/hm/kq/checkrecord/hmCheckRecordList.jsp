<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmCheckRecordList" checkbox="true" pagination="true" sortName="checkTime" sortOrder="asc" fitColumns="true" pageSize="20" title="" actionUrl="hmCheckRecordController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="月份"  field="month"   query="true" queryMode="single"  width="50"></t:dgCol>
      <t:dgCol title="姓名" query="true"  field="realName"  queryMode="single"  width="50"></t:dgCol>
      <t:dgCol title="工号"  field="workNo"  queryMode="single"  width="50"></t:dgCol>
   <t:dgCol title="部门" query="true" field="deptName"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="部门代码" hidden="true" field="deptCode"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="考勤日期"  field="kdTime"  queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="考勤时间"  field="checkTime"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="打卡时间"  field="clockTime" query="true"  queryMode="group"  width="80"></t:dgCol>
   <t:dgCol title="打开结果"  field="clockResult"  queryMode="single"  width="250"></t:dgCol>
       <t:dgToolBar title="录入" icon="fa fa-plus" url="hmCheckRecordController.do?goAdd" funname="add"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="hmCheckRecordController.do?goUpdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmCheckRecordController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="hmCheckRecordController.do?goUpdate" funname="detail"></t:dgToolBar>
      <t:dgToolBar title="计算" icon="fa fa-arrow-circle-down" funname="doJs"></t:dgToolBar>
      <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <%--<t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/hm/kq/checkrecord/hmCheckRecordList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
     $("#hmCheckRecordListtb").find("input[name='clockTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
     $("#hmCheckRecordListtb").find("input[name='clockTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
     $("#hmCheckRecordListtb").find("input[name='month']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM'});});
     $("#hmCheckRecordListtb").find("input[name='month']").val('${month}');

 });
 function doJs() {
     $.dialog.confirm('您是否确定计算考勤记录?', function (r) {
         if (r) {
             var index = layer.load(1, {
                 skin:"layui-layer-sys1",
                 shade: [0.1,'#fff'] //0.1透明度的白色背景
             });
             $.ajax({
                 url : "hmCheckRecordController.do?doAdd&month="+$("#hmCheckRecordListtb").find("input[name='month']").val(),
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#hmCheckRecordList').datagrid('reload');
                     }
                     layer.close(index);
                 }
             });
         }
     });
 }

 //导入
 function ImportXls() {
     $.dialog({
         content: 'url:hmCheckRecordController.do?upload',
         zIndex: getzIndex(),
         title : '考勤记录导入',
         cache:false,
         lock : true,
         width: 600,
         height: 300
     });

 }

//导出
function ExportXls() {
	JeecgExcelExport("hmCheckRecordController.do?exportXls","hmCheckRecordList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmCheckRecordController.do?exportXlsByT","hmCheckRecordList");
}

 </script>