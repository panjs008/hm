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
		var gdNamesCodeArr,gdNamesArr,sexsArr,jobArr,yglbArr,otherJdNamesCodeArr,otherJdNamesArr,
				sexsArr1,jobArr1,yglbArr1,gdJdNamesCodeArr,gdJdNamesArr,sexsArr2,jobArr2,yglbArr2,lsJdNamesCodeArr,lsJdNamesArr,sexsArr3,jobArr3,yglbArr3;
		$(document).ready(function(){
			$("#workTimeDetailId").load("hmOrderController.do?workTimeList&orderId=${hmOrderPage.id }");
			$("#detailId").load("hmWorkController.do?detailList2&orderId=${hmOrderPage.id }");
			var groupCodeArr = '${hmOrderPage.groupCode}'.split(",");
			var groupNameArr = '${hmOrderPage.groupName}'.split(",");

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

			$.ajax({
				url : "hmStaffController.do?getStaffSelectArr&r=gdNamesCode",
				type : 'post',
				cache : false,
				data: $("#formobj").serializeArray(),
				success : function(data) {
					var d = $.parseJSON(data);
					if(d.gdNamesCode != null && d.gdNamesCode != ''){
						gdNamesCodeArr = d.gdNamesCode.split(",");
						gdNamesArr = d.gdNames.split(",");
						sexsArr = d.sexs.split(",");
						jobArr = d.jobs.split(",");
						yglbArr = d.yglbs.split(",");

					}

				}
			});
			$.ajax({
				url : "hmStaffController.do?getStaffSelectArr&r=otherJdNamesCode",
				type : 'post',
				cache : false,
				success : function(data) {
					var d = $.parseJSON(data);
					if(d.gdNamesCode != null && d.gdNamesCode != ''){
						otherJdNamesCodeArr = d.gdNamesCode.split(",");
						otherJdNamesArr = d.gdNames.split(",");
						sexsArr1 = d.sexs.split(",");
						jobArr1 = d.jobs.split(",");
						yglbArr1 = d.yglbs.split(",");}

				}
			});
			$.ajax({
				url : "hmStaffController.do?getStaffSelectArr&r=gdJdNamesCode",
				type : 'post',
				cache : false,
				success : function(data) {
					var d = $.parseJSON(data);
					if(d.gdNamesCode != null && d.gdNamesCode != ''){
						gdJdNamesCodeArr = d.gdNamesCode.split(",");
						gdJdNamesArr = d.gdNames.split(",");
						sexsArr2 = d.sexs.split(",");
						jobArr2 = d.jobs.split(",");
						yglbArr2 = d.yglbs.split(",");
					}
				}
			});
			$.ajax({
				url : "hmStaffController.do?getStaffSelectArr&r=lsJdNamesCode",
				type : 'post',
				cache : false,
				success : function(data) {
					var d = $.parseJSON(data);
					if(d.gdNamesCode != null && d.gdNamesCode != ''){
						lsJdNamesCodeArr = d.gdNamesCode.split(",");
						lsJdNamesArr = d.gdNames.split(",");
						sexsArr3 = d.sexs.split(",");
						jobArr3 = d.jobs.split(",");
						yglbArr3 = d.yglbs.split(",");
					}
				}
			});
		});
		function deleteRepetion(arr){
			var arrTable = {},arrData = [];
			for (var i = 0; i < arr.length; i++) {
				if( !arrTable[ arr[i] ]){
					arrTable[ arr[i] ] = true;
					arrData.push(arr[i])
				}
			}
			return arrData;
		}
		function uq(array) {
			var del = [];
			for (var i = 0, arrayLen = array.length; i < arrayLen; i++) {
				for (var j = 0, delLen = del.length; j < delLen; j++ ) {
					if (array[i] === del[j]) {
						break;
					}
				}
				if (j === delLen) {
					del.push(array[i])
				}
			}
			return del;
		}
		function addWorkTimeDetail(gdNamesCodeArrV,gdNamesArrV,sexsArrV,jobArrV,yglbArrV,type){
			if(gdNamesCodeArrV != null && gdNamesCodeArrV != ""){
				for(var i = 0 ; i < gdNamesCodeArrV.length ; i++){
					if(gdNamesCodeArrV[i] != null && gdNamesCodeArrV[i] != ""){
						$('#addBtnWT').click();
						$('#workNo'+flagWT).val(gdNamesCodeArrV[i]);
						$('#realName'+flagWT).val(gdNamesArrV[i]);
						$('#sex'+flagWT).val(sexsArrV[i]);
						if(jobArrV[i] != null){
							if(jobArrV[i].indexOf('组长') >0 && jobArrV[i].indexOf('副组长')<0){
								$('#hjV'+flagWT).val('2.2');
								$('#zz'+flagWT).val('1');

							}else{
								$('#hjV'+flagWT).val('1.05');
							}
						}

						$('#type'+flagWT).val(type);
						$('#yglb'+flagWT).val(yglbArrV[i]);
					}
				}
			}
		}
		function addWorkTime(){
			$("#add_jeecgOrderProduct_tableWT").html("");
			flagWT = 0;

			addWorkTimeDetail(gdNamesCodeArr,gdNamesArr,sexsArr,jobArr,yglbArr,"0");
			addWorkTimeDetail(otherJdNamesCodeArr,otherJdNamesArr,sexsArr1,jobArr1,yglbArr1,"1");
			addWorkTimeDetail(gdJdNamesCodeArr,gdJdNamesArr,sexsArr2,jobArr2,yglbArr2,"2");
			addWorkTimeDetail(lsJdNamesCodeArr,lsJdNamesArr,sexsArr3,jobArr3,yglbArr3,"3");


		}
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
					if(d.gdNamesCode != null && d.gdNamesCode != ''){
						gdNamesCodeArr = d.gdNamesCode.split(",");
						gdNamesArr = d.gdNames.split(",");
						sexsArr = d.sexs.split(",");
						jobArr = d.jobs.split(",");
						yglbArr = d.yglbs.split(",");
						addWorkTime();
						$("#workCode").val(d.workCode);
						$("#workName").val(d.workName);
						$("#groupCode").val(d.groupCode);
						$("#groupName").val(d.groupName);

					}

				}
			});
		}
		function returnToStaff1(){
			$.ajax({
				url : "hmStaffController.do?getStaffSelectArr&r=otherJdNamesCode",
				type : 'post',
				cache : false,
				success : function(data) {
					var d = $.parseJSON(data);
					$("#otherJdNamesCode").val(d.gdNamesCode);
					$("#otherJdNames").val(d.gdNames);
					if(d.gdNamesCode != null && d.gdNamesCode != ''){
						otherJdNamesCodeArr = d.gdNamesCode.split(",");
						otherJdNamesArr = d.gdNames.split(",");
						sexsArr1 = d.sexs.split(",");
						jobArr1 = d.jobs.split(",");
						yglbArr1 = d.yglbs.split(",");
						addWorkTime();

					}

				}
			});
		}
		function returnToStaff2(){
			$.ajax({
				url : "hmStaffController.do?getStaffSelectArr&r=gdJdNamesCode",
				type : 'post',
				cache : false,
				success : function(data) {
					var d = $.parseJSON(data);
					$("#gdJdNamesCode").val(d.gdNamesCode);
					$("#gdJdNames").val(d.gdNames);
					if(d.gdNamesCode != null && d.gdNamesCode != ''){
						gdJdNamesCodeArr = d.gdNamesCode.split(",");
						gdJdNamesArr = d.gdNames.split(",");
						sexsArr2 = d.sexs.split(",");
						jobArr2 = d.jobs.split(",");
						yglbArr2 = d.yglbs.split(",");
						addWorkTime();
					}

				}
			});
		}
		function returnToStaff3(){
			$.ajax({
				url : "hmStaffController.do?getStaffSelectArr&r=lsJdNamesCode",
				type : 'post',
				cache : false,
				success : function(data) {
					var d = $.parseJSON(data);
					$("#lsJdNamesCode").val(d.gdNamesCode);
					$("#lsJdNames").val(d.gdNames);
					if(d.gdNamesCode != null && d.gdNamesCode != ''){
						lsJdNamesCodeArr = d.gdNamesCode.split(",");
						lsJdNamesArr = d.gdNames.split(",");
						sexsArr3 = d.sexs.split(",");
						jobArr3 = d.jobs.split(",");
						yglbArr3 = d.yglbs.split(",");
						addWorkTime();
					}


				}
			});
		}

		function staffLook(){
			$("#chkInfoForStaff").click();
		}
		function staffLook1(){
			$("#chkInfoForStaff1").click();
		}
		function staffLook2(){
			$("#chkInfoForStaff2").click();
		}
		function staffLook3(){
			$("#chkInfoForStaff3").click();
		}

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmOrderController.do?doUpdate" tiptype="1">
	<input id="oid" name="oid" type="hidden" value="${hmOrderPage.id }"/>
	<input id="type" name="type" type="hidden" value="${param.type }"/>

	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					日期:
				</label>
			</td>
			<td class="value">
				<input id="kdDate" value="${hmOrderPage.kdDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly name="kdDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
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
				<input id="mainWorkCode" name="mainWorkCode" value="${hmOrderPage.mainWorkCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="mainWorkName" name="mainWorkName" value="${hmOrderPage.mainWorkName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />

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
				<input id="workGroupCode" name="workGroupCode" value="${hmOrderPage.workGroupCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="workGroupName" name="workGroupName" value="${hmOrderPage.workGroupName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />

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

				<input id="workCode" name="workCode" value="${hmOrderPage.workCode}" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="workName" name="workName" value="${hmOrderPage.workName}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">车间</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					班组:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="groupCode" name="groupCode" value="${hmOrderPage.groupCode}" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="groupName" name="groupName" value="${hmOrderPage.groupName}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">班组</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					固定人员:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="gdNames" onclick="staffLook();" style="width:95%;height:30px" class="inputxt" rows="3" name="gdNames">${hmOrderPage.gdNames}</textarea>
				<input name="gdNamesCode"   type="hidden"  id="gdNamesCode" value="${hmOrderPage.gdNamesCode}"/>
				<div style="display: none"><t:choose  hiddenName="gdNamesCode"  hiddenid="gdNamesCode" url="hmStaffController.do?hmStaffSelect0" name="hmStaffList" width="800px" height="500px"
													  icon="icon-search" title="选择固定人员" textname="gdNames,workCode,workName,groupCode,groupName" isclear="true" isInit="true"></t:choose></div>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">固定人员</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					其他借调人员:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="otherJdNames" onclick="staffLook1();" style="width:95%;height:30px" class="inputxt" rows="3" name="otherJdNames">${hmOrderPage.otherJdNames}</textarea>
				<input name="otherJdNamesCode"   type="hidden"  id="otherJdNamesCode" value="${hmOrderPage.otherJdNamesCode}"/>
				<div style="display: none">
					<t:choose  hiddenName="otherJdNamesCode"  hiddenid="otherJdNamesCode" url="hmStaffController.do?hmStaffSelect1" name="hmStaffList1" width="800px" height="500px"
							   icon="icon-search" title="选择其他借调人员" textname="otherJdNames" isclear="true" isInit="true"></t:choose>
				</div>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">固定人员</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					固定借调人员:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="gdJdNames" onclick="staffLook2();" style="width:95%;height:30px" class="inputxt" rows="3" name="gdJdNames">${hmOrderPage.gdJdNames}</textarea>
				<input name="gdJdNamesCode"   type="hidden"  id="gdJdNamesCode" value="${hmOrderPage.gdJdNamesCode}"/>
				<div style="display: none">
					<t:choose  hiddenName="gdJdNamesCode"  hiddenid="gdJdNamesCode" url="hmStaffController.do?hmStaffSelect2" name="hmStaffList2" width="800px" height="500px"
							   icon="icon-search" title="选择固定借调人员" textname="gdJdNames" isclear="true" isInit="true"></t:choose>
				</div>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">固定借调人员</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					临时借调人员:
				</label>
			</td>
			<td class="value" colspan="5">
				<textarea  id="lsJdNames" onclick="staffLook3();" style="width:95%;height:30px" class="inputxt" rows="3" name="lsJdNames">${hmOrderPage.lsJdNames}</textarea>
				<input name="lsJdNamesCode"   type="hidden"  id="lsJdNamesCode" value="${hmOrderPage.lsJdNamesCode}"/>
				<div style="display: none">
					<t:choose  hiddenName="lsJdNamesCode"  hiddenid="lsJdNamesCode" url="hmStaffController.do?hmStaffSelect3" name="hmStaffList3" width="800px" height="500px"
							   icon="icon-search" title="选择临时借调人员" textname="lsJdNames" isclear="true" isInit="true"></t:choose>
				</div>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">临时借调人员</label>
			</td>
		</tr>

	</table>
	<div id="workTimeDetailId" style="overflow-x:hidden;overflow-y: hidden"></div>

	<div id="detailId" style="overflow-x:hidden;overflow-y: hidden"></div>

</t:formvalid>
</body>
<script src = "webpage/com/hm/rsgl/work/hmWork.js"></script>
