<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>班次表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
		$(document).ready(function(){
			$("#detailId").load("hmWorkClassController.do?detailList&workClassId=${hmWorkClassPage.id }");
		});

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmWorkClassController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${hmWorkClassPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					班次名称:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="bcName" name="bcName" type="text" value="${hmWorkClassPage.bcName }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<%--<t:dictSelect id="bcName" field="bcName" typeGroupCode="bclx"  datatype="*" defaultVal="${hmWorkClassPage.bcName }" hasLabel="false" title="班次名称"></t:dictSelect>--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">班次名称</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					打卡次数:
				</label>
			</td>
			<td class="value">
				<input id="times" name="times" datatype="n" type="text" value="${hmWorkClassPage.times }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打卡次数</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					时长:
				</label>
			</td>
			<td class="value">
				<input id="workTime" datatype="d" name="workTime" value="${hmWorkClassPage.workTime }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">时长</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					加班时长:
				</label>
			</td>
			<td class="value">
				<input id="jiaTime" datatype="d" name="jiaTime" value="${hmWorkClassPage.jiaTime }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">加班时长</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					休息时长:
				</label>
			</td>
			<td class="value">
				<input id="xiuTime" name="xiuTime" datatype="d" value="${hmWorkClassPage.xiuTime }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">休息时长</label>
			</td>
		</tr>


	</table>
	<div id="detailId" style="overflow-x:hidden;overflow-y: hidden"></div>
</t:formvalid>
</body>
<script src = "webpage/com/hm/kq/workclass/hmWorkClass.js"></script>
