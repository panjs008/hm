<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="tools"></t:base>
<t:datagrid name="hmWorkList" checkbox="false" nowrap="false" pagination="false" pageSize="15" sortOrder="desc" sortName="workDate" fitColumns="false"  title="" actionUrl="hmWorkController.do?datagrid&forWp=1&workDate=${param.workDate}&workNo=${param.workNo}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="主车间"  frozenColumn="true" field="mainWorkName"  queryMode="single"  width="90"></t:dgCol>
    <t:dgCol title="主班组"  frozenColumn="true" field="workGroupName"  queryMode="single"  width="90"></t:dgCol>

    <t:dgCol title="项目名称"  frozenColumn="true" field="xmmc"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="工艺流程"  field="gylc"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="数量"   field="total"   queryMode="single"  width="55"></t:dgCol>
    <t:dgCol title="每件重量"    field="weight"  queryMode="single"  width="65"></t:dgCol>
    <t:dgCol title="单价"  field="price"  queryMode="single"  width="55"></t:dgCol>
    <t:dgCol title="合计重量"   field="hjWeight"  queryMode="single"  width="68"></t:dgCol>
    <t:dgCol title="合计金额"   field="hjMoney"  queryMode="single"  width="68"></t:dgCol>

</t:datagrid>
