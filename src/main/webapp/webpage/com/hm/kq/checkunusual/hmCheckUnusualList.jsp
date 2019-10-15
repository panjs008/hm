<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="hmCheckUnusualList" pagination="true"  pageSize="20" fitColumns="true" title="" actionUrl="hmCheckUnusualController.do?listUnusualByJdbc" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="月份"  field="month" hidden="true" query="true" queryMode="single"  width="50"></t:dgCol>
   <%--<t:dgCol title="工号"  field="workNo"  queryMode="single"  width="50"></t:dgCol>--%>
   <t:dgCol title="姓名"  field="realName"   query="true" queryMode="single"  width="70"></t:dgCol>
   <t:dgCol title="部门名称"  field="deptName" query="true" queryMode="single"  width="100"></t:dgCol>
      <c:forEach var="list" items="${cols}" varStatus="status">
          <t:dgCol title="${list.name}"  field="${list.code}" extendParams="styler:fmtype" formatterjs="setT"   queryMode="single"  width="35" ></t:dgCol>
      </c:forEach>
       <t:dgToolBar title="删除"  icon="fa fa-remove" url="hmCheckUnusualController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="fa fa-search" url="hmCheckUnusualController.do?goUpdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="导出" icon="fa fa-arrow-circle-right" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<div id="tempSearchColums" style="display: none;width:100px;">
    <div name="searchColums">
        <table align="right" style="margin-right: 10px;">
            <tr>
                <td>
                    <div style="background-color: #FF0000;border-radius: 5px;color:#fff;width: 30px;height: 25px">&nbsp;</div>
                </td>
                <td>
                    &nbsp;&nbsp;旷工&nbsp;&nbsp;
                </td>
                <td>
                    <div style="background-color: #f89406;border-radius: 5px;color:#fff;width: 30px;height: 25px">&nbsp;</div>
                </td>
                <td>
                    &nbsp;&nbsp;缺卡&nbsp;&nbsp;
                </td>
                <td>
                    <div style="background-color: #3a87ad;border-radius: 5px;color:#fff;width: 30px;height: 25px">&nbsp;</div>
                </td>
                <td>
                    &nbsp;&nbsp;无效
                </td>
            </tr>
        </table>

    </div>
</div>
 <script src = "webpage/com/hm/kq/checkunusual/hmCheckUnusualList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
     $("#hmCheckUnusualListtb").find("input[name='month']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM'});});
     $("#hmCheckUnusualListtb").find("input[name='month']").val('${month}');
     $("#hmCheckUnusualListtb").find("div[name='searchColums']").find("form#hmCheckUnusualListForm").append($("#tempSearchColums div[name='searchColums']").html());
     $("#tempSearchColums").html('');
 });
 function fmtype(val,row,index){
     var s1 = 'background-color: #f89406;border-radius: 5px;color:#fff;';
     var s2 = 'background-color: #3a87ad;border-radius: 5px;color:#fff';
     var s3 = 'background-color: #FF0000;border-radius: 5px;color:#fff';

     if (val.indexOf('2') >=0) {
         return s3;
     }else if (val.indexOf('1') >=0) {
         return s1;
     }/*else if (val.indexOf('0') >0 && val.indexOf('0') >1) {
         return s2;
     }*/
 }
 function setT(val,row,index){
     return '';
 }
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'hmCheckUnusualController.do?upload', "hmCheckUnusualList");
}

//导出
function ExportXls() {
	JeecgExcelExport("hmCheckUnusualController.do?exportXls","hmCheckUnusualList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmCheckUnusualController.do?exportXlsByT","hmCheckUnusualList");
}

 </script>