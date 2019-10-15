<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>客户手册</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
		function uploadSuccess0(d,file,response){
			var src = d.attributes.url;
			$("#peopleFileUrl").val(d.attributes.url);
			$("#peopleFile").val(d.attributes.name);
			$("#peopleFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}
		function downloadFile(url) {
			window.open("${webRoot}/"+url);
		}
		function uploadSuccess1(d,file,response){
			var src = d.attributes.url;
			$("#zlFileUrl").val(d.attributes.url);
			$("#zlFile").val(d.attributes.name);
			$("#zlFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}
		function uploadSuccess2(d,file,response){
			var src = d.attributes.url;
			$("#fkFileUrl").val(d.attributes.url);
			$("#fkFile").val(d.attributes.name);
			$("#fkFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}
		function uploadSuccess3(d,file,response){
			var src = d.attributes.url;
			$("#checkHuoFileUrl").val(d.attributes.url);
			$("#checkHuoFile").val(d.attributes.name);
			$("#checkHuoFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}
		function uploadSuccess4(d,file,response){
			var src = d.attributes.url;
			$("#testFileUrl").val(d.attributes.url);
			$("#testFile").val(d.attributes.name);
			$("#testFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}
		function uploadSuccess5(d,file,response){
			var src = d.attributes.url;
			$("#pactFileUrl").val(d.attributes.url);
			$("#pactFile").val(d.attributes.name);
			$("#pactFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}
		function uploadSuccess6(d,file,response){
			var src = d.attributes.url;
			$("#sampleFileUrl").val(d.attributes.url);
			$("#sampleFile").val(d.attributes.name);
			$("#sampleFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}function uploadSuccess7(d,file,response){
			var src = d.attributes.url;
			$("#sendHuoFileUrl").val(d.attributes.url);
			$("#sendHuoFile").val(d.attributes.name);
			$("#sendHuoFileId").html("[<a href=\"javascript:downloadFile('"+d.attributes.url+"')\">"+d.attributes.name+"</a>]");
		}




	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkCustomUseController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkCustomUsePage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">

		<tr>
			<td align="right" >
				<label class="Validform_label">
					母客户名称:
				</label>
			</td>
			<td class="value">
				<input id="cusName" name="cusName" readonly value="${emkCustomUsePage.cusName }" type="text" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">母客户名称</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					子客户名称:
				</label>
			</td>
			<td class="value">
				<input id="subCusName" name="subCusName" value="${emkCustomUsePage.subCusName }" type="text" style="width: 150px" class="inputxt"  datatype="*"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">子客户名称</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="businesserId" datatype="*" style="width: 120px">
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" value="${emkCustomUsePage.businesser }" readonly type="hidden"  class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName" value="${emkCustomUsePage.businesserName }"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					母客户代码:
				</label>
			</td>
			<td class="value" >
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkCustomUsePage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">母客户代码</label>
			</td>
			<td align="right" >
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" >
				<select class="form-control select2" id="tracerId"  style="width: 120px">
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" readonly value="${emkCustomUsePage.tracer }" type="hidden"  class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName" value="${emkCustomUsePage.tracerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="developerId"  style="width: 120px">
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" value="${emkCustomUsePage.developer }" readonly type="hidden"  class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName" value="${emkCustomUsePage.developerName }"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">生产跟单员</label>
			</td>
		</tr>


		<tr>
			<td align="left" colspan="6" class="value">
				<label class="Validform_label">
					人权验厂
				</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有验厂手册:
				</label>
			</td>
			<td class="value">
				<input name="isPeopleAdvice" type="radio" onclick="setReruire('peopleAdvice',0);" datatype="*" <c:if test="${emkCustomUsePage.isPeopleAdvice eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isPeopleAdvice" onclick="setReruire('peopleAdvice',1);" type="radio" datatype="*"  <c:if test="${emkCustomUsePage.isPeopleAdvice eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有人权验厂手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					验厂手册:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="peopleFile" name="peopleFile" value="${emkCustomUsePage.peopleFile }" type="hidden" />
				<input id="peopleFileUrl" name="peopleFileUrl"  value="${emkCustomUsePage.peopleFileUrl }" type="hidden" />
				<t:upload name="instruction0" id="instruction0" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess0" >
				</t:upload><span id="peopleFileId"></span>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					人权验厂说明:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="peopleAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="peopleAdvice">${emkCustomUsePage.peopleAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">人权验厂说明</label>
			</td>
		</tr>

		<tr>
			<td align="left" colspan="6" class="value">
				<label class="Validform_label">
					质量验厂
				</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有验厂手册:
				</label>
			</td>
			<td class="value">
				<input name="isZlAdvice" type="radio" datatype="*" onclick="setReruire('zlAdvice',0);"  <c:if test="${emkCustomUsePage.isZlAdvice eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isZlAdvice" type="radio" onclick="setReruire('zlAdvice',1);"  datatype="*"  <c:if test="${emkCustomUsePage.isZlAdvice eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有质量验厂手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					验厂手册:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="zlFile" name="zlFile" type="hidden" value="${emkCustomUsePage.zlFile }" />
				<input id="zlFileUrl" name="zlFileUrl" type="hidden" value="${emkCustomUsePage.zlFileUrl }" />
				<t:upload name="instruction1" id="instruction1" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess1" >
				</t:upload><span id="zlFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					质量验厂说明:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="zlAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="zlAdvice">${emkCustomUsePage.zlAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">质量验厂说明</label>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="6" class="value">
				<label class="Validform_label">
					反恐验厂
				</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有反恐验厂手册:
				</label>
			</td>
			<td class="value">
				<input name="isFkAdvice" type="radio" datatype="*" onclick="setReruire('fkAdvice',0);" <c:if test="${emkCustomUsePage.isFkAdvice eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isFkAdvice" type="radio" onclick="setReruire('fkAdvice',1);" datatype="*"  <c:if test="${emkCustomUsePage.isFkAdvice eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有反恐验厂手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					反恐验厂手册:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="fkFile" name="fkFile" type="hidden" value="${emkCustomUsePage.fkFile }" />
				<input id="fkFileUrl" name="fkFileUrl" type="hidden" value="${emkCustomUsePage.fkFileUrl }" />
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
				</t:upload><span id="fkFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					反恐验厂说明:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="fkAdvice" style="width:95%;height:70px" class="inputxt" rows="5" name="fkAdvice">${emkCustomUsePage.fkAdvice }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">反恐验厂说明反恐验厂说明</label>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="6" class="value">
				<label class="Validform_label">
					验货
				</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有验货手册:
				</label>
			</td>
			<td class="value">
				<input name="isCheckHuo" type="radio" datatype="*" onclick="setReruire('checkHuo',0);" <c:if test="${emkCustomUsePage.isCheckHuo eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isCheckHuo" type="radio" onclick="setReruire('checkHuo',1);" datatype="*"  <c:if test="${emkCustomUsePage.isCheckHuo eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有验货手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					验货手册:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="checkHuoFile" name="checkHuoFile" type="hidden" value="${emkCustomUsePage.checkHuoFile }" />
				<input id="checkHuoFileUrl" name="checkHuoFileUrl" type="hidden" value="${emkCustomUsePage.checkHuoFileUrl }" />
				<t:upload name="instruction3" id="instruction3" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess3" >
				</t:upload><span id="checkHuoFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					验货说明:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="checkHuo" style="width:95%;height:70px" class="inputxt" rows="5" name="checkHuo">${emkCustomUsePage.checkHuo }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">验货手册说明</label>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="6" class="value">
				<label class="Validform_label">
					测试
				</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有测试手册:
				</label>
			</td>
			<td class="value">
				<input name="isTest" type="radio" datatype="*" onclick="setReruire('test',0);" <c:if test="${emkCustomUsePage.isTest eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isTest" type="radio" onclick="setReruire('test',1);" datatype="*"  <c:if test="${emkCustomUsePage.isTest eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有测试手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					测试手册:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="testFile" name="testFile" type="hidden" value="${emkCustomUsePage.testFile }" />
				<input id="testFileUrl" name="testFileUrl" type="hidden" value="${emkCustomUsePage.testFileUrl }" />
				<t:upload name="instruction4" id="instruction4" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess4" >
				</t:upload><span id="testFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					测试说明:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="test" style="width:95%;height:70px" class="inputxt" rows="5" name="test">${emkCustomUsePage.test }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">测试手册说明</label>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="6" class="value">
				<label class="Validform_label">
					包装
				</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有包装手册:
				</label>
			</td>
			<td class="value">
				<input name="isPact" type="radio" datatype="*" onclick="setReruire('pact',0);" <c:if test="${emkCustomUsePage.isPact eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isPact" type="radio" onclick="setReruire('pact',1);" datatype="*"  <c:if test="${emkCustomUsePage.isPact eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有包装手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					包装手册:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="pactFile" name="pactFile" type="hidden" value="${emkCustomUsePage.pactFile }" />
				<input id="pactFileUrl" name="pactFileUrl" type="hidden" value="${emkCustomUsePage.pactFileUrl }" />
				<t:upload name="instruction5" id="instruction5" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess5" >
				</t:upload><span id="pactFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					包装说明:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="pact" style="width:95%;height:70px" class="inputxt" rows="5" name="pact">${emkCustomUsePage.pact }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">包装手册说明</label>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="6" class="value">
				<label class="Validform_label">
					样品
				</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有样品手册:
				</label>
			</td>
			<td class="value">
				<input name="isSample" type="radio" datatype="*" onclick="setReruire('sample',0);"  <c:if test="${emkCustomUsePage.isSample eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isSample" type="radio" onclick="setReruire('sample',1);"  datatype="*"  <c:if test="${emkCustomUsePage.isSample eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有样品手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					样品手册:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="sampleFile" name="sampleFile" type="hidden" value="${emkCustomUsePage.sampleFile }" />
				<input id="sampleFileUrl" name="sampleFileUrl" type="hidden" value="${emkCustomUsePage.sampleFileUrl }" />
				<t:upload name="instruction6" id="instruction6" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess6" >
				</t:upload><span id="sampleFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					样品说明:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="sample" style="width:95%;height:70px" class="inputxt" rows="5" name="sample">${emkCustomUsePage.sample }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品手册说明</label>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="6" class="value">
				<label class="Validform_label">
					发货
				</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否有发货手册:
				</label>
			</td>
			<td class="value">
				<input name="isSendHuo" type="radio" datatype="*" onclick="setReruire('sendHuo',0);" <c:if test="${emkCustomUsePage.isSendHuo eq '0'}">checked="true"</c:if> value="0">
				是
				&nbsp;&nbsp;<input name="isSendHuo" type="radio" onclick="setReruire('sendHuo',1);" datatype="*"  <c:if test="${emkCustomUsePage.isSendHuo eq '1'}">checked="true"</c:if> value="1">
				否
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否有发货要求手册</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					发货手册:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="sendHuoFile" name="sendHuoFile" type="hidden" value="${emkCustomUsePage.sendHuoFile }" />
				<input id="sendHuoFileUrl" name="sendHuoFileUrl" type="hidden" value="${emkCustomUsePage.sendHuoFileUrl }" />
				<t:upload name="instruction7" id="instruction7" dialog="false" extend="*.jpg;*.png;*.doc;*.txt;*.xls" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess7" >
				</t:upload><span id="sendHuoFileId"></span>
			</td>
		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					发货说明:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="sendHuo" style="width:95%;height:70px" class="inputxt" rows="5" name="sendHuo">${emkCustomUsePage.sendHuo }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发货要求手册说明</label>
			</td>
		</tr>

		<tr>
			<td align="right" >
				<label class="Validform_label">
					指定货代名称:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="zdhdmc" name="zdhdmc"  type="text" style="width: 150px" value="${emkCustomUsePage.zdhdmc }"  class="inputxt"  ignore="ignore" />
				<label class="Validform_label" style="display: none;">指定货代名称</label>
			</td>

		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					业务联系人
				</label>
			</td>
			<td class="value" colspan="5">
				<table style="width:890px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr >
						<td width="50" height="32" align="center"></td>
						<td width="120" >&nbsp;&nbsp;主联系人</td>
						<td width="120" >&nbsp;&nbsp;联系人二</td>
						<td width="120">&nbsp;&nbsp;联系人三</td>
						<td width="120">&nbsp;&nbsp;联系人四</td>
						<td width="120">&nbsp;&nbsp;联系人五</td>
						<td width="360" colspan="3">&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td width="50" height="32" align="center">姓名</td>
						<td class="value" height="32">
							<input id="zlxr" name="zlxr" type="text" datatype="*" value="${ymkCustomPage.zlxr }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">主联系人</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr2" name="ywlxr2" type="text" value="${ymkCustomPage.ywlxr2 }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人二</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr3" name="ywlxr3" type="text"  value="${ymkCustomPage.ywlxr3 }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人三</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr4" name="ywlxr4" type="text"  value="${ymkCustomPage.ywlxr4 }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人四</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr5" name="ywlxr5" type="text"  value="${ymkCustomPage.ywlxr5 }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人五</label>
						</td>
						<td class="value" colspan="3" height="32">
					</tr>
					<tr>
						<td width="50" height="32" align="center">邮箱</td>
						<td class="value">
							<input id="email" name="email" type="text" value="${ymkCustomPage.email }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">邮箱</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr2Email" name="ywlxr2Email" type="text" datatype="e" value="${ymkCustomPage.ywlxr2Email }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人二</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr3Email" name="ywlxr3Email" type="text"  datatype="e" value="${ymkCustomPage.ywlxr3Email }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人三</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr4Email" name="ywlxr4Email" type="text"  datatype="e" value="${ymkCustomPage.ywlxr4Email }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人四</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr5Email" name="ywlxr5Email" type="text"  datatype="e" value="${ymkCustomPage.ywlxr5Email }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人五</label>
						</td>
						<td class="value" colspan="3" height="32">
					</tr>
					<tr>
						<td width="50" height="32" align="center">电话</td>
						<td class="value">
							<input id="workphone" name="workphone" value="${ymkCustomPage.workphone }" type="text" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">办公电话</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr2Telphone" name="ywlxr2Telphone"  type="text" value="${ymkCustomPage.ywlxr2Telphone }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人二</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr3Telphone" name="ywlxr3Telphone"  type="text"  value="${ymkCustomPage.ywlxr3Telphone }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人三</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr4Telphone" name="ywlxr4Telphone"  type="text"  value="${ymkCustomPage.ywlxr4Telphone }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人四</label>
						</td>
						<td class="value" height="32">
							<input id="ywlxr5Telphone" name="ywlxr5Telphone"  type="text"  value="${ymkCustomPage.ywlxr5Telphone }" style="width: 90px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">联系人五</label>
						</td>
						<td class="value" colspan="3" height="32">
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					其他说明:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="other" style="width:95%;height:70px" class="inputxt" rows="5" name="sendHuo">${emkCustomUsePage.other }</textarea>
				<label class="Validform_label" style="display: none;">其他说明</label>
			</td>
		</tr>
		<tr>
			<td align="right" >
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="remark" name="remark"  type="text" style="width: 150px" value="${emkCustomUsePage.remark }"  class="inputxt"  ignore="ignore" />
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script>
	$(document).ready(function() {
		$("#instruction0-button").css("width","70px");
		$("#instruction1-button").css("width","70px");
		$("#instruction2-button").css("width","70px");
		$("#instruction3-button").css("width","70px");
		$("#instruction4-button").css("width","70px");
		$("#instruction5-button").css("width","70px");
		$("#instruction6-button").css("width","70px");
		$("#instruction7-button").css("width","70px");

	});
</script>