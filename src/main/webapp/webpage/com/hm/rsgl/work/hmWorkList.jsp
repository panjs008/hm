<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
<t:datagrid name="hmWorkList" checkbox="true" pagination="true" pageSize="20" sortOrder="desc" sortName="workDate" fitColumns="false" onClick="editRow" title="" actionUrl="hmWorkController.do?datagrid&type=0" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="日期" frozenColumn="true" query="true" field="workDate"  queryMode="group"  width="90"></t:dgCol>
   <t:dgCol title="主班组" frozenColumn="true" field="workGroupName"  query="true"  queryMode="single"  width="90"></t:dgCol>
   <t:dgCol title="主班组代码"  field="workGroupCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <t:dgCol title="主车间" frozenColumn="true" field="mainWorkName"  query="true"  queryMode="single"  width="90"></t:dgCol>
    <t:dgCol title="姓名"  field="realName" hidden="true" query="true"  queryMode="single"  width="200"></t:dgCol>

    <t:dgCol title="主车间代码"  field="mainWorkCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="车间" field="workName" queryMode="single"  width="200"></t:dgCol>
   <t:dgCol title="车间代码"  field="workCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="班组"  field="groupName"  queryMode="single"  width="200"></t:dgCol>
   <t:dgCol title="班组代码"  field="groupCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="固定人员"   field="gdNames"  queryMode="single"  width="200"></t:dgCol>
   <t:dgCol title="固定人员代码"   field="gdNamesCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="其他借调人员"   field="otherJdNames"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="其他借调人员代码"  field="otherJdNamesCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="固定借调人员"   field="gdJdNames"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="固定借调人员代码"  field="gdJdNamesCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="临时借调人员"   field="lsJdNames"  queryMode="single"  width="160"></t:dgCol>
   <t:dgCol title="临时借调人员代码"  field="lsJdNamesCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>

      <%--<t:dgCol title="生产内容"  newColumn="true" colspan="5"></t:dgCol>--%>
      <t:dgCol title="项目名称"  field="xmmc" query="true"    queryMode="single"  width="160"></t:dgCol>
      <t:dgCol title="工艺流程"  field="gylc" queryMode="single"  width="160"></t:dgCol>
      <t:dgCol title="规格分类"  field="ggfl"  queryMode="single"  width="160"></t:dgCol>
      <t:dgCol title="单位"  field="unit"  queryMode="single"  width="60"></t:dgCol>
      <t:dgCol title="标准单价"  field="price"  query="true"  queryMode="single"  width="70"></t:dgCol>

   <%--   <t:dgCol title="数量"   field="total" extendParams="editor:'text'"   queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="手填单位"  extendParams="editor:'text'"  field="stNit"  queryMode="single"  width="70"></t:dgCol>--%>
    <t:dgCol title="数量"   field="total"   queryMode="single"  width="70"></t:dgCol>
    <t:dgCol title="手填单位"  field="stNit"  queryMode="single"  width="70"></t:dgCol>
      <t:dgCol title="每件重量"    field="weight"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="合计重量"   field="hjWeight"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="合计金额"   field="hjMoney"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="人数"   field="peoples"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="人均工资"   field="capitaWages"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="固借人数"   field="gdPeoples"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="可分配工资"   field="kfWages"  queryMode="single"  width="80"></t:dgCol>
   <t:dgCol title="本组人数"   field="localPeoples"  queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="平均提成"   field="choucheng"  queryMode="single"  width="70"></t:dgCol>


      <t:dgToolBar title="录入" icon="fa fa-plus" url="hmWorkController.do?goAdd&type=0&winTitle=录入计件（形式一）" funname="add" width="1200" height="630"></t:dgToolBar>
       <t:dgToolBar title="编辑" icon="fa fa-edit" url="hmWorkController.do?goUpdate&winTitle=编辑计件（形式一）" funname="update" width="1200" height="630"></t:dgToolBar>
      <t:dgToolBar title="保存" icon="fa fa-save" funname="saveData"></t:dgToolBar>
        <t:dgToolBar title="重新生成日工资" icon="fa fa-arrow-circle-down" funname="doRe"></t:dgToolBar>

       <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmWorkController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="hmWorkController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导入" icon="fa fa-arrow-circle-left" funname="ImportXls"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/hm/rsgl/work/hmWorkList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
     $("#hmWorkListtb").find("input[name='workDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
     $("#hmWorkListtb").find("input[name='workDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
     $("#hmWorkListtb").find("input[name='xmmc']").css("width","208px");

 });

 function editRow(title,addurl,gname){
     var rows=$('#hmWorkList').datagrid("getChecked");
     if(rows.length==0){
         tip("请选择条目");
         return false;
     }
     for(var i=0;i<rows.length;i++){
         var index= $('#hmWorkList').datagrid('getRowIndex', rows[i]);
         $('#hmWorkList').datagrid('beginEdit', index);
     }

 }
 function doRe() {
     var rowsData = $('#hmWorkList').datagrid('getSelections');
     var ids = [];
     if (!rowsData || rowsData.length == 0) {
         tip('请选择数据');
         return;
     }
     for (var i = 0; i < rowsData.length; i++) {
         ids.push(rowsData[i].id);
     }
     $.dialog.confirm('您是否确定重新生成日工资数据?', function (r) {
         if (r) {
             $.ajax({
                 url : "hmWorkController.do?doRe&ids="+ids,
                 type : 'post',
                 cache : false,
                 data: null,
                 success : function(data) {
                     var d = $.parseJSON(data);
                     tip(d.msg);
                     if (d.success) {
                         $('#hmWorkList').datagrid('reload');
                     }
                 }
             });
         }
     });
 }
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hmWorkController.do?upload', "hmWorkList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hmWorkController.do?exportXls","hmWorkList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmWorkController.do?exportXlsByT","hmWorkList");
}

 </script>