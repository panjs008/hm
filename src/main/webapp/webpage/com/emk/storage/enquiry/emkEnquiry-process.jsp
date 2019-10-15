<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>报修单</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script>
        $(document).ready(function(){
            var height =window.top.document.body.offsetHeight*0.79;
            <c:forEach items="${priceEntityList}" var="price" varStatus="status">
                $("#priceFrm${status.index}").css("height",height);
            </c:forEach>
            <c:forEach items="${sampleRequiredEntityList}" var="sampleRequired" varStatus="status">
                $("#sampleRequiredFrm${status.index}").css("height",height);
            </c:forEach>
        });

    </script>

</head>
<body>
<t:tabs id="repairTabId" iframe="false" heigth="${param.hVal}"  tabPosition="top" fit="true" >
    <t:tab title="任务处理" id="workFrm"  heigth="600px"  width="100%" href="emkEnquiryController.do?goWork&node=${param.node}&id=${param.id}" icon="fa fa-crosshairs"></t:tab>
    <t:tab title="基本信息" id="baseFrm"  heigth="600px"  width="100%" iframe="emkEnquiryController.do?goUpdate2&id=${param.id}" icon="fa fa-calendar"></t:tab>
    <%--<t:tab title="任务处理" id="workFrm"  heigth="480px"  width="100%" icon="" href="uRepairController.do?goWork&id=${param.id}"></t:tab>--%>
    <c:if test="${param.node eq 'jgqrTask' || param.node eq 'bjTask' || param.node eq 'dybjTask' || param.node eq 'dy2Task' || param.node eq 'hjTask' || param.node eq 'dyTask' || param.node eq 'dy2Task'}">
        <c:if test="${fn:length(priceEntityList)  > 0 }">
            <c:forEach items="${priceEntityList}" var="price" varStatus="status">
                <t:tab title="${price.kdDate}报价单" id="priceFrm${status.index}"  heigth="${param.hVal}"  width="100%" href="emkPriceController.do?goUpdate2&hVal2=${param.hVal2}&hVal=${param.hVal}&id=${price.id}" icon="fa fa-calendar"></t:tab>
            </c:forEach>
        </c:if>
        <c:if test="${fn:length(sampleRequiredEntityList)  > 0 }">
            <c:forEach items="${sampleRequiredEntityList}" var="sampleRequired" varStatus="status">
                <t:tab title="${sampleRequired.kdDate}样品需求单" id="sampleRequiredFrm${status.index}"  heigth="${param.hVal}"  width="100%" href="emkSampleRequiredController.do?goUpdate2&hVal2=${param.hVal2}&hVal=${param.hVal}&id=${sampleRequired.id}" icon="fa fa-calendar"></t:tab>
            </c:forEach>
        </c:if>
    </c:if>

    <t:tab title="流程图" id="processFrm"  heigth="600px" width="100%" icon="fa fa-sitemap" iframe="flowController.do?process&processUrl=com/emk/storage/enquiry/process&id=${param.id}"></t:tab>
</t:tabs>

</body>

