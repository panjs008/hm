<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="hmWorkDayList"  pagination="true" pageSize="50" sortOrder="desc" sortName="workDate" fitColumns="false" onClick="editRow" title="" actionUrl="hmWorkController.do?listProductDayByJdbc&mainWorkCode=${param.mainWorkCode}&workGroupCode=${param.workGroupCode}&workDate=${param.workDate}&unit=${param.unit}&ggfl=${param.ggfl}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="日期"  field="workDate"  queryMode="group"  width="90"></t:dgCol>
   <%-- <t:dgCol title="主车间"  field="mainWorkName"  query="true"  queryMode="single"  width="150"></t:dgCol>
    <t:dgCol title="主班组"  field="workGroupName"  query="true"  queryMode="single"  width="150"></t:dgCol>--%>
    <%--<t:dgCol title="重量"   field="hjtotal"   queryMode="single"  width="90"></t:dgCol>
    <t:dgCol title="单位"  field="unit"  queryMode="single"  width="60"></t:dgCol>--%>
    <c:if test="${fn:contains('吨,千克,克,公斤,斤', param.unit)}">
        <c:forEach var="list" items="${unitList}" varStatus="status">
            <t:dgCol title="${list.unit}"  field="${list.unit}" queryMode="single"  width="70" ></t:dgCol>
        </c:forEach>
    </c:if>
    <c:if test="${!fn:contains('吨,千克,克,公斤,斤', param.unit)}">
        <t:dgCol title="重量"   field="hjtotal"   queryMode="single"  width="90"></t:dgCol>
        <t:dgCol title="单位"  field="unit"  queryMode="single"  width="60"></t:dgCol>
    </c:if>
    <t:dgCol title="规格分类"  field="ggfl"  queryMode="single"  width="130"></t:dgCol>

</t:datagrid>

 <script type="text/javascript">
 $(document).ready(function(){
    /* $("#hmWorkListtb").find("input[name='workDate_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
     $("#hmWorkListtb").find("input[name='workDate_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
     $("#hmWorkListtb").find("input[name='xmmc']").css("width","208px");*/
 });
 function formatUnit(val,row,index){
     return '吨';
 }
 </script>