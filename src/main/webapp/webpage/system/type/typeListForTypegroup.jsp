<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <t:datagrid name="typeValueList" title="common.type.list" pageSize="15" sortOrder="asc" sortName="typecode"
                    actionUrl="systemController.do?typeGrid&typegroupid=${typegroupid}" idField="id"
                    queryMode="group">
            <t:dgCol title="common.code" field="id" hidden="true" width="120"></t:dgCol>
            <t:dgCol title="common.type.name" field="typename" width="200"></t:dgCol>
            <t:dgCol title="common.type.code" field="typecode" width="${param.typegroupname eq '考勤设置' ? 50:150}"></t:dgCol>
            <c:if test="${param.typegroupname eq '系数设置' || param.typegroupname eq '扣费' || param.typegroupname eq '满勤奖'}">
                <t:dgCol title="参数值" field="remark" width="150"></t:dgCol>
            </c:if>
            <c:if test="${param.typegroupname eq '考勤设置'}">
                <t:dgCol title="状态" field="remark" replace="禁用_0,启用_1" width="40"></t:dgCol>
            </c:if>
            <%--<t:dgCol title="common.operation" field="opt" width="150"></t:dgCol>--%>
            <%--<t:dgDelOpt url="systemController.do?delType&id={id}" title="common.delete" urlclass="ace_button"  urlfont="fa-trash-o"></t:dgDelOpt>--%>
            <t:dgToolBar title="common.add.param" langArg="common.type" icon="icon-add" url="systemController.do?addorupdateType&typegroupname=${param.typegroupname}&typegroupid=${typegroupid}" funname="add"></t:dgToolBar>
            <t:dgToolBar title="common.edit.param" langArg="common.type" icon="icon-edit" url="systemController.do?addorupdateType&typegroupname=${param.typegroupname}&typegroupid=${typegroupid}" funname="update"></t:dgToolBar>
        </t:datagrid>
    </div>
</div>
<script>
    function addType(title,addurl,gname,width,height) {
        add(title,addurl,gname,width,height);
    }
</script>

