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
	  $("#detailId").load("hmWorkController.do?detailList&workId=${hmWorkPage.id }");
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
  function returnToVal(){
	  /*var gdNamesCodeS = '${sessionScope.gdNamesCodeS}'.split(",");
	  var gdNamesCodeArr = $("#gdNamesCode").val().split(",");
	  if(gdNamesCodeS.length>0){
		  gdNamesCodeArr = gdNamesCodeArr.concat(gdNamesCodeS);//合并成一个数组
	  }
	  var result = uq(gdNamesCodeArr);*/
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
  function returnToStaff1(){
	  $.ajax({
		  url : "hmStaffController.do?getStaffSelectArr&r=otherJdNamesCode",
		  type : 'post',
		  cache : false,
		  success : function(data) {
			  var d = $.parseJSON(data);
			  $("#otherJdNamesCode").val(d.gdNamesCode);
			  $("#otherJdNames").val(d.gdNames);
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmWorkController.do?doAdd" tiptype="1">
					<input id="id" name="id" type="hidden" value="${hmWorkPage.id }"/>
	  <input id="type" name="type" type="hidden" value="${param.type }"/>

	  <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							日期:
						</label>
					</td>
					<td class="value">
					     	 <input id="workDate" value="${kdDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly name="workDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
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
					<td class="value" colspan="3">
						<input id="groupCode" name="groupCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
						<input id="groupName" name="groupName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
					<textarea  id="gdNames" onclick="staffLook();" style="width:95%;height:30px" class="inputxt" rows="3" name="gdNames"></textarea>
					<input name="gdNamesCode"   type="hidden"  id="gdNamesCode"/>
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
						<textarea  id="otherJdNames" onclick="staffLook1();" style="width:95%;height:30px" class="inputxt" rows="3" name="otherJdNames"></textarea>
						<input name="otherJdNamesCode"   type="hidden"  id="otherJdNamesCode"/>
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
					<textarea  id="gdJdNames" onclick="staffLook2();" style="width:95%;height:30px" class="inputxt" rows="3" name="gdJdNames"></textarea>
					<input name="gdJdNamesCode"   type="hidden"  id="gdJdNamesCode"/>
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
					<textarea  id="lsJdNames" onclick="staffLook3();" style="width:95%;height:30px" class="inputxt" rows="3" name="lsJdNames"></textarea>
					<input name="lsJdNamesCode"   type="hidden"  id="lsJdNamesCode"/>
					<div style="display: none">
						<t:choose  hiddenName="lsJdNamesCode"  hiddenid="lsJdNamesCode" url="hmStaffController.do?hmStaffSelect3" name="hmStaffList3" width="800px" height="500px"
								   icon="icon-search" title="选择临时借调人员" textname="lsJdNames" isclear="true" isInit="true"></t:choose>
						</div>

					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">临时借调人员</label>
				</td>
			</tr>


			<%--	<tr>
					<td align="right">
						<label class="Validform_label">
							人数:
						</label>
					</td>
					<td class="value">
					     	 <input id="peoples" name="peoples" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">人数</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							人均工资:
						</label>
					</td>
					<td class="value">
					     	 <input id="capitaWages" name="capitaWages" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">人均工资</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							固借人数:
						</label>
					</td>
					<td class="value">
						<input id="gdPeoples" name="gdPeoples" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">固借人数</label>
					</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							可分配工资:
						</label>
					</td>
					<td class="value">
						<input id="kfWages" name="kfWages" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">可分配工资</label>
					</td>

					<td align="right">
						<label class="Validform_label">
							本组人数:
						</label>
					</td>
					<td class="value">
					     	 <input id="localPeoples" name="localPeoples" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">本组人数</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							平均提成:
						</label>
					</td>
					<td class="value">
					     	 <input id="choucheng" name="choucheng" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">平均提成</label>
						</td>
					</tr>--%>
			</table>
	 		 <div id="detailId" style="overflow-x:hidden;overflow-y: hidden"></div>

		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/rsgl/work/hmWork.js"></script>
