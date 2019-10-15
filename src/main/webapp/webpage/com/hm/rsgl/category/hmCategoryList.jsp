<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmCategoryList" checkbox="false" singleSelect="true"  pageSize="20" pagination="true" fitColumns="true" title="" sortOrder="asc" sortName="orderNum" actionUrl="hmCategoryController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="上级code"  field="parentCode"  hidden="true"   queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="字段名称"  field="name" query="true"    queryMode="single"  width="150"></t:dgCol>
      <t:dgCol title="字段编码"  field="code"    queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="字段类型"  field="columnType"  replace="短文本_0,长文本_1,数值_2,日期_3,文本域_4,下拉框_5,正数值_6,负数值_7"  queryMode="single"  width="100"></t:dgCol>
      <t:dgCol title="序号"  field="orderNum"    queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="是否必填"  field="required"  replace="否_1,是_0"  queryMode="single"  width="81"></t:dgCol>
      <t:dgCol title="是否查询"  field="queryed"  replace="否_1,是_0"  queryMode="single"  width="81"></t:dgCol>

      <t:dgCol title="操作" field="opt" width="220"></t:dgCol>
      <t:dgFunOpt funname="doPre(code)" title="往前" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>
      <t:dgFunOpt funname="doNext(code)" title="往后" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>
      <t:dgFunOpt funname="doTop(code)" title="置顶" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>
      <t:dgFunOpt funname="doBottom(code)" title="置底" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>

      <t:dgToolBar title="录入" icon="fa fa-plus" url="hmCategoryController.do?goAdd" funname="add"></t:dgToolBar>
      <t:dgToolBar title="编辑" icon="fa fa-edit" url="hmCategoryController.do?goUpdate" funname="update"></t:dgToolBar>
      <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmCategoryController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
      <t:dgToolBar title="查看" icon="fa fa-search" url="hmCategoryController.do?goUpdate" funname="detail"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
<div id="tempSearchColums" style="display: none;width:120px">
    <div name="searchColums">
        <select id="parentCode" name="parentCode" datatype="*" >
            <option value="A01A01">生产内容表</option>
            <option value="A01A02">薪酬信息预设表</option>
        </select>
    </div>
</div>
 <script src = "webpage/com/hm/rsgl/category/hmCategoryList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
     $("#hmCategoryListtb").find("div[name='searchColums']").find("form#hmCategoryListForm").append($("#tempSearchColums div[name='searchColums']").html());
     $("#tempSearchColums").html('');
     $("#parentCode").css("width","120px");
 });

 function doPre(code) {
     $.ajax({
         url : "hmCategoryController.do?doPre&code="+code,
         type : 'post',
         cache : false,
         data: null,
         success : function(data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 $('#hmCategoryList').datagrid('reload');
             }else{
                 var msg = d.msg;
                 tip(msg);
             }
         }
     });
 }

 function doNext(code) {
     $.ajax({
         url : "hmCategoryController.do?doNext&code="+code,
         type : 'post',
         cache : false,
         data: null,
         success : function(data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 $('#hmCategoryList').datagrid('reload');
             }else{
                 var msg = d.msg;
                 tip(msg);
             }
         }
     });

 }

 function doTop(code) {
     $.ajax({
         url : "hmCategoryController.do?doTop&code="+code,
         type : 'post',
         cache : false,
         data: null,
         success : function(data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 $('#hmCategoryList').datagrid('reload');
             }else{
                 var msg = d.msg;
                 tip(msg);
             }
         }
     });

 }

 function doBottom(code) {
     $.ajax({
         url : "hmCategoryController.do?doBottom&code="+code,
         type : 'post',
         cache : false,
         data: null,
         success : function(data) {
             var d = $.parseJSON(data);
             if (d.success) {
                 $('#hmCategoryList').datagrid('reload');
             }else{
                 var msg = d.msg;
                 tip(msg);
             }
         }
     });

 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hmCategoryController.do?upload', "hmCategoryList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hmCategoryController.do?exportXls","hmCategoryList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmCategoryController.do?exportXlsByT","hmCategoryList");
}

 </script>