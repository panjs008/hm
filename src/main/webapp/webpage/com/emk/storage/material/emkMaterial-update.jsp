<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品需求开发单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {
			/*BindSelect("cgerId","ymkCustomController.do?findUserList&userKey=采购员",0,"");
			 $("#cgerId").change(function(){
			 var itemarr = $("#cgerId").val().split(","); //字符分割
			 $("#cger").val(itemarr[0]);
			 $("#cgerName").val(itemarr[1]);
			 });*/
		});
		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);
			$("#customSampleId").html("[<a href=\"javascript:findDetail('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");

			$("#uploadimg0").attr('src',d.attributes.url);
		}
		function setEndTime0() {
			var d1  =  $("#kdDate").val();
			var d2  =  $("#dhjqDate").val();
			$("#leaveDhjqDays").val(DateDiff(d1,d2));
		}
	</script>
</head>
<body>
<iframe scrolling="no" id="processFrm" frameborder="0" style="overflow-x: hidden;overflow-y: hidden"  src="${webRoot}/context/progress.jsp?finishColums=${countMap.finishColums}&Colums=${countMap.Colums}&recent=${recent}" width="100%" height="20px"></iframe>

<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkMaterialController.do?doUpdate" tiptype="1">
	<input id="materailId" name="materailId" type="hidden" value="${emkMaterialPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td class="value" align="right" colspan="5">
				<label class="Validform_label">
					原料面料需求开发单编号:
				</label>
			</td>
			<td class="value" colspan="2" >
				<input id="materialNo" name="materialNo" type="text" value="${emkMaterialPage.materialNo }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">原料面料需求开发单编号</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkMaterialPage.cusNum }" style="width: 130px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" >
				<input id="businesseDeptName" name="businesseDeptName" value="${emkMaterialPage.businesseDeptName }" readonly type="text" style="width: 130px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkMaterialPage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td  align="right">
				<label class="Validform_label">
					样品通知单编号:
				</label>
			</td>
			<td class="value" >
				<input id="sampleNum" name="sampleNum" type="text" value="${emkMaterialPage.sampleNum }" style="width: 130px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品通知单编号</label>
			</td>

			<td class="value" rowspan="6">
				<input id="customSample" name="customSample" type="hidden" value="${emkMaterialPage.customSample }"/>
				<img id="uploadimg0" src="${emkMaterialPage.customSampleUrl eq null ? 'images/bjlogo.png':emkMaterialPage.customSampleUrl}" width="180" height="180">
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload>
				<c:if test="${emkMaterialPage.customSampleUrl ne null && emkMaterialPage.customSampleUrl ne ''}">[<a href="javascript:findDetail('${emkMaterialPage.customSampleUrl }')">${emkMaterialPage.customSample }</a>]</c:if>
				<span id="customSampleId"></span>
				<input id="customSampleUrl" name="customSampleUrl" type="hidden" value="${emkMaterialPage.customSampleUrl }"/>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" type="text" style="width: 130px" value="${emkMaterialPage.sampleNo }" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" style="width: 138px" id="businesserId"  datatype="*">
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly type="hidden" value="${emkMaterialPage.businesser }" style="width: 130px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName"  type="hidden"  value="${emkMaterialPage.businesserName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					打样通知单日期:
				</label>
			</td>
			<td class="value" >
				<input id="sampleDate" name="sampleDate" readonly value="${emkMaterialPage.sampleDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 130px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样通知单日期</label>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl"  typeGroupCode="gylx" datatype="*" defaultVal="${emkMaterialPage.gyzl }" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业务跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2"  style="width: 138px" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly type="hidden" value="${emkMaterialPage.tracer }" style="width: 130px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName"  type="hidden"  value="${emkMaterialPage.tracerName }" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					打样需求单编号:
				</label>
			</td>
			<td class="value" >
				<input id="xqdh" name="xqdh" type="text" value="${emkMaterialPage.xqdh }" style="width: 130px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样需求单编号</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkMaterialPage.proTypeName }">
				<input id="proTypeName" name="proTypeName" datatype="*"  value="${emkMaterialPage.proTypeName }" type="hidden" style="width: 130px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" value="${emkMaterialPage.proType }" style="width: 130px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2"  style="width: 138px" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly type="hidden" value="${emkMaterialPage.developer }" style="width: 130px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  type="hidden" value="${emkMaterialPage.developerName }"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					打样需求单日期:
				</label>
			</td>
			<td class="value" >
				<input id="kdDate" name="dyxqdDate" readonly value="${emkMaterialPage.dyxqdDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 130px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样需求单日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					描述:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="sampleNoDesc" name="sampleNoDesc" value="${emkMaterialPage.sampleNoDesc }"  type="text" style="width: 130px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">描述</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					版次:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="version" name="version"  value="${emkMaterialPage.version }" type="text" style="width: 130px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">版次</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					样品交期:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="ysDate" name="ypjqDate" readonly value="${emkMaterialPage.ypjqDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTime})"  type="text" style="width: 130px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品交期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					距样品交期剩余天数:
				</label>
			</td>
			<td class="value" colspan="2">
				<input id="levelDays" name="leaveYpjqDays" value="${emkMaterialPage.leaveYpjqDays }" readonly datatype="n" type="text" style="width: 130px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距交期剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否已有订单:
				</label>
			</td>
			<td class="value">
				<input name="isOrder" type="radio"  datatype="*" <c:if test="${emkMaterialPage.isOrder eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isOrder" type="radio" datatype="*"  <c:if test="${emkMaterialPage.isOrder eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否已有订单</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					大货交期:
				</label>
			</td>
			<td class="value">
				<input id="dhjqDate" name="dhjqDate" readonly value="${emkMaterialPage.dhjqDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}',onpicked:setEndTime0})" type="text" style="width: 130px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">大货交期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					距交货期剩余天数:
				</label>
			</td>
			<td class="value" colspan="2">
				<input id="leaveDhjqDays" name="leaveDhjqDays" value="${emkMaterialPage.leaveDhjqDays }" readonly datatype="n" type="text" style="width: 130px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">距交期剩余天数</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否现有适合原料面料:
				</label>
			</td>
			<td class="value" colspan="6">
				<input name="isHave" type="radio" datatype="*" <c:if test="${emkMaterialPage.isHave eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isHave" type="radio" datatype="*"  <c:if test="${emkMaterialPage.isHave eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否现有适合原料面料</label>
			</td>
		</tr>
	</table>
	<t:tabs id="enquiryDetail" iframe="false"  tabPosition="top" fit="false">
		<t:tab href="emkMaterialController.do?orderMxList&sampleId=${emkMaterialPage.id}" icon="icon-search" title="原料面料" id="detail"></t:tab>
	</t:tabs>

	<table style="width: 100%;margin-top: 20px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right" width="16%">
				<label class="Validform_label">
					需求开发交期:
				</label>
			</td>
			<td class="value">
				<input id="requiredJqDate" name="requiredJqDate" value="${emkMaterialPage.requiredJqDate }" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdDate\');}'})"   type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">需求开发交期</label>
			</td>
			<td align="right" width="16%">
				<label class="Validform_label">
					完成时间剩余天数:
				</label>
			</td>
			<td class="value" colspan="4">
				<input id="leaveFinishDays" name="leaveFinishDays"  value="${emkMaterialPage.leaveFinishDays }"  datatype="n" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">完成时间剩余天数</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					打样原因:
				</label>
			</td>
			<td class="value" colspan="6">
				<textarea  id="sampleReason" style="width:95%;height:70px" class="inputxt" rows="3" name="sampleReason">${emkMaterialPage.sampleReason }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样原因</label>
			</td>
		</tr>

	</table>
</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$("#instruction0-button").css("width","70px");
		$("#instruction-button").css("width","70px");
		$("#instruction2-button").css("width","70px");
		$("#instruction3-button").css("width","70px");
		$("#gyzl").css("width","138px");

		$('#proTypeTree').combotree({
			url : 'emkProductTypeController.do?setPOfficeInfo&selfId=${emkProductTypePage.id}',
			panelHeight: 200,
			width: 138,
			onClick: function(node){
				$("#proType").val(node.id);
				$("#proTypeName").val(node.text);

			}
		});
	});
</script>