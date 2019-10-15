<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>工作表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>
	<script type="text/javascript">
		//编写自定义JS代码
		$(document).ready(function(){
			$("#workTimeDetailId").load("hmWorkPriceController.do?workTimeList");
			BindDeptSelect("mainWorkId","hmWorkController.do?findDept&len=9",1,$("#mainWorkName").val()+","+$("#mainWorkCode").val());
			$("#mainWorkId").change(function(){
				var itemarr = $("#mainWorkId").val().split(","); //字符分割
				$("#mainWorkCode").val(itemarr[1]);
				$("#mainWorkName").val(itemarr[0]);
				BindDeptSelect("workGroupId","hmWorkController.do?findDept&len=12&pCode="+$("#mainWorkCode").val(),1,$("#workGroupName").val()+","+$("#workGroupCode").val());
			});
			$("#workGroupId").change(function(){
				var itemarr = $("#workGroupId").val().split(","); //字符分割
				$("#workGroupCode").val(itemarr[1]);
				$("#workGroupName").val(itemarr[0]);
			});

		});

		function addWorkTime(gdNamesCodeArr,gdNamesArr,zcbArr,jiabArr,tyzcbArr,tyjiabArr,type){
			$("#add_jeecgOrderProduct_tableWT").html("");
			flagWT = 0;
			for(var i = 0 ; i < gdNamesCodeArr.length ; i++){
				if(gdNamesCodeArr[i] != null && gdNamesCodeArr[i] != ""){
					$('#addBtnWT').click();
					$('#workNo'+flagWT).val(gdNamesCodeArr[i]);
					$('#realName'+flagWT).val(gdNamesArr[i]);
					$('#tsPrice'+flagWT).val(zcbArr[i]);
					$('#tsPriceT'+flagWT).val(jiabArr[i]);
					$('#tyPrice'+flagWT).val(tyzcbArr[i]);
					$('#tyPriceT'+flagWT).val(tyjiabArr[i]);
					$('#type'+flagWT).val(type);
				}
			}


		}
		function returnToVal(){
			$.ajax({
				url : "hmStaffController.do?getStaffSelectArr2&r=gdNamesCode",
				type : 'post',
				cache : false,
				data: $("#formobj").serializeArray(),
				success : function(data) {
					var d = $.parseJSON(data);
					$("#gdNamesCode").val(d.gdNamesCode);
					$("#gdNames").val(d.gdNames);
					var gdNamesCodeArr = d.gdNamesCode.split(",");
					var gdNamesArr = d.gdNames.split(",");
					var zcbsArr = d.zcbs.split(",");
					var jiabsArr = d.jiabs.split(",");
					var tyzcbsArr = d.tyzcbs.split(",");
					var tyjiabsArr = d.tyjiabs.split(",");
					addWorkTime(gdNamesCodeArr,gdNamesArr,zcbsArr,jiabsArr,tyzcbsArr,tyjiabsArr,"5");

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
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmWorkPriceController.do?doAdd" tiptype="1">
	<input id="id" name="id" type="hidden" value="${hmOrderPage.id }"/>
	<input id="type" name="type" type="hidden" value="${param.type }"/>

	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" value="${kdDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly name="kdDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					主车间:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="mainWorkId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="mainWorkCode" name="mainWorkCode" value="${hmWorkPage.mainWorkCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="mainWorkName" name="mainWorkName" value="${hmWorkPage.mainWorkName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">主车间</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					主班组:
				</label>
			</td>
			<td class="value">
				<select class="form-control select2" id="workGroupId"  datatype="*"  >
					<option value=''>请选择</option>
				</select>
				<input id="workGroupCode" name="workGroupCode" value="${hmWorkPage.workGroupCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="workGroupName" name="workGroupName" value="${hmWorkPage.workGroupName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">主班组</label>
			</td>

		</tr>
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
			<td class="value" >
				<input id="groupCode" name="groupCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="groupName" name="groupName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">班组</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					是否连班:
				</label>
			</td>
			<td class="value">
				<select name="isJiab" id="isJiab">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否连班</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					人员:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="gdNames" onclick="staffLook();" style="width:95%;height:30px" class="inputxt" rows="3" name="gdNames"></textarea>
				<input name="gdNamesCode"   type="hidden"  id="gdNamesCode"/>
				<div style="display: none"><t:choose  hiddenName="gdNamesCode"  hiddenid="gdNamesCode" url="hmStaffController.do?hmStaffSelect4" name="hmStaffList" width="800px" height="500px"
													  icon="icon-search" title="选择人员" textname="gdNames,workCode,workName,groupCode,groupName" isclear="true" isInit="true"></t:choose></div>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">人员</label>
			</td>
		</tr>

	</table>
	<div id="workTimeDetailId" style="overflow-x:hidden;overflow-y: hidden"></div>
</t:formvalid>
</body>
<script src = "webpage/com/hm/rsgl/work/hmWork.js"></script>
