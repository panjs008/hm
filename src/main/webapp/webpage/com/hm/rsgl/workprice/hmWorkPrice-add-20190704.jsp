<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>工价表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
		$(document).ready(function(){
			$("#detailId").load("hmWorkPriceController.do?detailList");
		});
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmWorkPriceController.do?doAdd" >
	<div id="detailId" style="overflow-x:hidden;overflow-y: hidden"></div>

</t:formvalid>
</body>
<script src = "webpage/com/hm/rsgl/workprice/hmWorkPrice.js"></script>
