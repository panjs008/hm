<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmAttenderList" checkbox="true" pageSize="20" pagination="true" fitColumns="true" title="" actionUrl="hmAttenderController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="月份"  field="month"  queryMode="single" query="true" width="90"></t:dgCol>
      <t:dgCol title="姓名"  query="true" field="realName"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="部门"  query="true" field="deptName"  queryMode="single"  width="170"></t:dgCol>
      <t:dgCol title="部门代码"  hidden="true" field="deptCode"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="车间" query="true" field="workName"  queryMode="single"  width="170"></t:dgCol>
      <t:dgCol title="车间代码" hidden="true"  field="workCode"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="班组" query="true" field="groupName"  queryMode="single"  width="170"></t:dgCol>
      <t:dgCol title="班组代码"  hidden="true"  field="groupCode"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="职务"  field="job"  dictionary="job" queryMode="single"  width="130"></t:dgCol>
      <t:dgCol title="级别"  field="jb"  dictionary="jb" queryMode="single"  width="80"></t:dgCol>
      <t:dgCol title="薪酬类别"  field="xclb"  dictionary="xclb" queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="性别"  field="sex" dictionary="sex"  queryMode="single"  width="65"></t:dgCol>
      <t:dgCol title="员工类别"  field="yglb" query="true" dictionary="yglb"  queryMode="single"  width="100"></t:dgCol>
   <t:dgCol title="出勤天数"  field="cqDay"  queryMode="single"  width="95"></t:dgCol>
      <t:dgCol title="请假"  field="hourSalary" hidden="true" queryMode="single"  width="180"></t:dgCol>
      <t:dgCol title="请假"  field="hours" hidden="true" queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="请假"  field="qjMoney" formatterjs="setQj"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="迟到"  field="cdMoney"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="旷工"  field="kgMoney"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="出勤奖"  field="mqj"  queryMode="single"  width="80"></t:dgCol>
      <t:dgToolBar title="生成出勤表" icon="fa fa-arrow-circle-down" funname="doSc"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="hmAttenderController.do?goUpdate" funname="update" width="900" height="500"></t:dgToolBar>
      <t:dgToolBar title="计入工资表" icon="fa fa-edit" url="hmAttenderController.do?goUpdate" funname="doCal" width="900" height="500"></t:dgToolBar>
      <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmAttenderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="hmAttenderController.do?goUpdate" funname="detail" width="900" height="500"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/hm/kq/attender/hmAttenderList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
     $("#hmAttenderListtb").find("input[name='month']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM'});});
     $("#hmAttenderListtb").find("input[name='month']").val('${month}');
 });
 function setQj(val,row,index){
     if(val != '' && row.hours != ''){
         return row.hourSalary+'*'+row.hours+'='+row.qjMoney;
     }else{
         return '';
     }

 }
 function doSc() {
     $.dialog.confirm('您是否确定生成月度出勤表?', function (r) {
         if (r) {
             var index = layer.load(1, {
                 skin:"layui-layer-sys1",
                 shade: [0.1,'#fff'] //0.1透明度的白色背景
             });
             $.ajax({
                 url : "hmAttenderController.do?doAdd&month="+$("#hmAttenderListtb").find("input[name='month']").val(),
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#hmAttenderList').datagrid('reload');
                     }
                     layer.close(index);
                 }
             });
         }
     });
 }
 function doCal() {
     var rows = $('#hmAttenderList').datagrid("getChecked");
     if(rows.length==0){
         tip("请选择条目");
         return false;
     }
     if(rows.length>1){
         tip("只能选择一条");
         return false;
     }
     $.dialog.confirm('您是否确定计入工资表?', function (r) {
         if (r) {
             var index = layer.load(1, {
                 skin:"layui-layer-sys1",
                 shade: [0.1,'#fff'] //0.1透明度的白色背景
             });
             $.ajax({
                 url : "hmAttenderController.do?doCal&id="+rows[0].id,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#hmAttenderList').datagrid('reload');
                     }
                     layer.close(index);
                 }
             });
         }
     });
 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hmAttenderController.do?upload', "hmAttenderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hmAttenderController.do?exportXls","hmAttenderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmAttenderController.do?exportXlsByT","hmAttenderList");
}

 </script>