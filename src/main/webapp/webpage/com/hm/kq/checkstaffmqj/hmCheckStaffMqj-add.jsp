<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>考勤满勤奖配置表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
		function returnToVal(){
			$.ajax({
				url : "hmStaffController.do?getStaffSelectArr&r=gdNamesCode",
				type : 'post',
				cache : false,
				data: $("#formobj").serializeArray(),
				success : function(data) {
					var d = $.parseJSON(data);
					$("#gdNamesCode").val(d.gdNamesCode);
					$("#gdNames").val(d.gdNames);
					$("#workCode").val(d.workCode);
					$("#workName").val(d.workName);
					$("#groupCode").val(d.groupCode);
					$("#groupName").val(d.groupName);

				}
			});
		}
		function staffLook(){
			$("#chkInfoForStaff").click();
		}
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmCheckStaffMqjController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${hmCheckStaffMqjPage.id }" />
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					车间:
				</label>
			</td>
			<td class="value">
				<input id="workCode" name="workCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="workName" name="workName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">车间</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					班组:
				</label>
			</td>
			<td class="value">
				<input id="groupCode" name="groupCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="groupName" name="groupName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">班组</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					满勤奖:
				</label>
			</td>
			<td class="value">
				<input id="mqj" name="mqj" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">车间</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					全勤奖:
				</label>
			</td>
			<td class="value">
				<input id="qqj" name="qqj" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">全勤奖</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					人员:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="gdNames" onclick="staffLook();" style="width:95%;height:90px" class="inputxt" rows="3" name="gdNames"></textarea>
				<input name="gdNamesCode"   type="hidden"  id="gdNamesCode"/>
				<div style="display: none"><t:choose  hiddenName="gdNamesCode"  hiddenid="gdNamesCode" url="hmStaffController.do?hmStaffSelectForBc" name="hmStaffListForBc" width="800px" height="500px"
													  icon="icon-search" title="选择人员" textname="gdNames,workCode,workName,groupCode,groupName" isclear="true" isInit="true"></t:choose></div>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">人员</label>
			</td>
		</tr>



	</table>
</t:formvalid>
</body>
<script src = "webpage/com/hm/kq/checkstaff/hmCheckStaff.js"></script>
