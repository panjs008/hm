<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="projectList" checkbox="true" pagination="true" pageSize="20" fitColumns="true" title="" actionUrl="hmProjectController.do?datagrid" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
      <c:forEach var="list" items="${categoryEntities}" varStatus="status">
          <t:dgCol title="${list.name}"  field="${list.code}"  queryMode="single" query="${list.queryed eq 0 ? 'true':'false'}"  width="${list.column_type eq '4' ? '220':(list.column_type eq '0' || list.column_type eq '2') ? '80':'150'}"></t:dgCol>
     </c:forEach>

  </t:datagrid>
  </div>
 </div>
<div id="tempSearchColums" style="display: none;width:120px">
    <div name="searchColums">
        <select id="a01a01a03" name="a01a01a03" datatype="*" >
            <option value="-1">选择</option>
            <c:forEach var="g" items="${gglb}">
                <option value="${g.name}">${g.name}</option>
            </c:forEach>
        </select>
    </div>
</div>
 <script type="text/javascript">
 $(document).ready(function(){
     $("#projectListtb").find("div[name='searchColums']").find("form#projectListForm").append($("#tempSearchColums div[name='searchColums']").html());
     $("#tempSearchColums").html('');
     $("#a01a01a03").css("width","120px");  $("#projectListtb").find("div[name='searchColums']").find("form#projectListForm").append($("#tempSearchColums div[name='searchColums']").html());
     $("#tempSearchColums").html('');
     $("#a01a01a03").css("width","120px");
 });
 

//导入
function ImportXls() {
    $.dialog({
        content: 'url:hmProjectController.do?upload',
        zIndex: getzIndex(),
        title : 'Excel导入',
        cache:false,
        lock : true,
        width: 600,
        height: 300
    });
}

//导出
function ExportXls() {
	JeecgExcelExport("hmProjectController.do?exportXls","projectList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("hmProjectController.do?exportXlsByT","projectList");
}

 </script>