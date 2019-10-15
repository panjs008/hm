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
		var hsVal = 0;
		$(document).ready(function(){
			$("#unit").val('${hmWorkPage.unit}');
			$("#stNit").val('${hmWorkPage.stNit}');
			setUnit();
			/*var groupCodeArr = '${hmWorkPage.groupCode}'.split(",");
			var groupNameArr = '${hmWorkPage.groupName}'.split(",");
			html = "";
			for(var i = 0 ; i < groupCodeArr.length ; i++){
				html += '<option value='+groupCodeArr[i]+'>'+groupNameArr[i]+'</option>';
			}
			$("#workGroupCode").html(html);
			$("#workGroupCode").val('${hmWorkPage.workGroupCode}');
			var mainWorkCodeArr = '${hmWorkPage.workCode}'.split(",");
			var mainWorkNameArr = '${hmWorkPage.workName}'.split(",");
			html = "";
			for(var i = 0 ; i < mainWorkCodeArr.length ; i++){
				html += '<option value='+mainWorkCodeArr[i]+'>'+mainWorkNameArr[i]+'</option>';
			}
			$("#mainWorkCode").html(html);
			$("#mainWorkCode").val('${hmWorkPage.mainWorkCode}');*/
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
					/*var groupCodeArr = d.groupCode.split(",");
					var groupNameArr = d.groupName.split(",");
					var html = "";
					for(var i = 0 ; i < groupNameArr.length ; i++){
						html += '<option value='+groupCodeArr[i]+'>'+groupNameArr[i]+'</option>';
					}
					$("#workGroupCode").html(html);

					var mainWorkCodeArr = d.workCode.split(",");
					var mainWorkNameArr = d.workName.split(",");
					html = "";
					for(var i = 0 ; i < mainWorkCodeArr.length ; i++){
						html += '<option value='+mainWorkCodeArr[i]+'>'+mainWorkNameArr[i]+'</option>';
					}
					$("#mainWorkCode").html(html);*/
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

		function setUnit(){
			if($("#unit").val() == '吨'){
				if($("#stNit").val() == '斤'){
					hsVal = 0.0005;
				}else if($("#stNit").val() == '公斤'){
					hsVal = 0.001;
				}else if($("#stNit").val() == '吨'){
					hsVal = 1;
				}else if($("#stNit").val() == '千克'){
					hsVal = 0.001;
				}else if($("#stNit").val() == '克'){
					hsVal = 0.000001;
				}
			}else if($("#unit").val() == '公斤'){
				if($("#stNit").val() == '吨'){
					hsVal = 1000;
				}else if($("#stNit").val() == '公斤'){
					hsVal = 1;
				}else if($("#stNit").val() == '斤'){
					hsVal = 0.5;
				}else if($("#stNit").val() == '千克'){
					hsVal = 1;
				}else if($("#stNit").val() == '克'){
					hsVal = 0.001;
				}
			}else if($("#unit").val() == '斤'){
				if($("#stNit").val() == '吨'){
					hsVal = 2000;
				}else if($("#stNit").val() == '公斤'){
					hsVal = 2;
				}else if($("#stNit").val() == '斤'){
					hsVal = 1;
				}else if($("#stNit").val() == '千克'){
					hsVal = 2;
				}else if($("#stNit").val() == '克'){
					hsVal = 0.002;
				}
			}else if($("#unit").val() == '千克'){
				if($("#stNit").val() == '吨'){
					hsVal = 1000;
				}else if($("#stNit").val() == '公斤'){
					hsVal = 1;
				}else if($("#stNit").val() == '斤'){
					hsVal = 0.5;
				}else if($("#stNit").val() == '千克'){
					hsVal = 1;
				}else if($("#stNit").val() == '克'){
					hsVal = 0.001;
				}
			}else if($("#unit").val() == '克'){
				if($("#stNit").val() == '吨'){
					hsVal = 1000000;
				}else if($("#stNit").val() == '公斤'){
					hsVal = 1000;
				}else if($("#stNit").val() == '斤'){
					hsVal = 500;
				}else if($("#stNit").val() == '千克'){
					hsVal = 1000;
				}else if($("#stNit").val() == '克'){
					hsVal = 1;
				}
			}else{
				hsVal = 1;
			}
			//setWeightAndPrice();
		}

		function setWeightAndPrice(){
			setUnit();
			var weightV = toDecimal3(parseFloat($("#weight").val())*hsVal*parseFloat($("#total").val()));
			if(!isNaN(weightV)){
				$("#hjWeight").val(weightV);
				var priceV = toDecimal(parseFloat($("#price").val())*weightV);
				if(!isNaN(priceV)){
					$("#hjMoney").val(priceV);
				}
			}
		}

		function projectLook(){
			$("#chkInfoForProject").click();
		}

		function returnToProject(){
			$('#xmmc').val($("#a01a01a01").val());
			$('#gylc').val($("#a01a01a02").val());
			$('#ggfl').val($("#a01a01a03").val());
			$('#unit').val($("#a01a01a04").val());
			$('#price').val($("#a01a01a05").val());
			setUnit();
		}
	</script>
</head>
<body>
<div style="display:none">
	<c:forEach var="list" items="${categoryEntities}" varStatus="status">
		<input id="${list.code}" name="${list.code}" type="text"/>
	</c:forEach>

	<input id="id" name="id" type="text" />
	<t:choose  hiddenName="id"  hiddenid="id" url="hmProjectController.do?select1" name="projectList" width="820px" height="500px"
			   icon="icon-search" title="选择工序工艺" textname="id,${columns}" isclear="true" isInit="true"></t:choose>
</div>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmWorkController.do?doUpdate" tiptype="1">
	<input id="wid" name="wid" type="hidden" value="${hmWorkPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					日期:
				</label>
			</td>
			<td class="value">
				<input id="workDate" value="${hmWorkPage.workDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly name="workDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
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

				<input id="workCode" name="workCode" value="${hmWorkPage.workCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="workName" name="workName" value="${hmWorkPage.workName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">车间</label>
			</td>

			<td align="right">
				<label class="Validform_label">
					班组:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="groupCode" name="groupCode" value="${hmWorkPage.groupCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="groupName" name="groupName" value="${hmWorkPage.groupName }" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
				<textarea  id="gdNames" onclick="staffLook();" style="width:95%;height:30px" class="inputxt" rows="3"  name="gdNames">${hmWorkPage.gdNames }</textarea>
				<input name="gdNamesCode"   type="hidden"  id="gdNamesCode" value="${hmWorkPage.gdNamesCode }"/>
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
				<textarea  id="otherJdNames" onclick="staffLook1();" style="width:95%;height:30px" class="inputxt" rows="3" name="otherJdNames">${hmWorkPage.otherJdNames }</textarea>
				<input name="otherJdNamesCode"   type="hidden"  id="otherJdNamesCode" value="${hmWorkPage.otherJdNamesCode }"/>
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
				<textarea  id="gdJdNames" onclick="staffLook2();" style="width:95%;height:30px" class="inputxt" rows="3" name="gdJdNames">${hmWorkPage.gdJdNames }</textarea>
				<input name="gdJdNamesCode"   type="hidden"  id="gdJdNamesCode" value="${hmWorkPage.gdJdNamesCode }"/>
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
				<textarea  id="lsJdNames" onclick="staffLook3();" style="width:95%;height:30px" class="inputxt" rows="3" name="lsJdNames">${hmWorkPage.lsJdNames }</textarea>
				<input name="lsJdNamesCode"   type="hidden"  id="lsJdNamesCode" value="${hmWorkPage.lsJdNamesCode }"/>
				<div style="display: none">
					<t:choose  hiddenName="lsJdNamesCode"  hiddenid="lsJdNamesCode" url="hmStaffController.do?hmStaffSelect3" name="hmStaffList3" width="800px" height="500px"
							   icon="icon-search" title="选择临时借调人员" textname="lsJdNames" isclear="true" isInit="true"></t:choose>
				</div>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">临时借调人员</label>
			</td>
		</tr>

			<tr>
				<td align="right">
					<label class="Validform_label">
						项目名称:
					</label>
				</td>
				<td class="value">
					<input id="xmmc" name="xmmc" value="${hmWorkPage.xmmc }" type="text" onclick="projectLook()" style="width: 150px" class="inputxt"  datatype="*"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">项目名称</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						工艺流程:
					</label>
				</td>
				<td class="value">
					<input id="gylc" name="gylc" value="${hmWorkPage.gylc }" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">工艺流程</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						规格分类:
					</label>
				</td>
				<td class="value">
					<input id="ggfl" name="ggfl" value="${hmWorkPage.ggfl }" type="text" style="width: 150px" class="inputxt"  datatype="*"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">规格分类</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						单位:
					</label>
				</td>
				<td class="value">
					<select id="unit" name="unit" onchange="setUnit();"  style="width:70px;">
						<option value="">选择</option>
						<option value="吨">吨</option>
						<option value="公斤">公斤</option>
						<option value="斤">斤</option>
						<option value="千克">千克</option>
						<option value="克">克</option>
					</select>
					<%--<input id="unit" name="unit" value="${hmWorkPage.unit }" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />--%>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">单位</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						标准单价:
					</label>
				</td>
				<td class="value" colspan="3">
					<input id="price" name="price" onkeyup="setWeightAndPrice()" value="${hmWorkPage.price }" type="text" style="width: 150px" class="inputxt"  datatype="d"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">标准单价</label>
				</td>

			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						手填单位:
					</label>
				</td>
				<td class="value">
					<select id="stNit" name="stNit" onchange="setWeightAndPrice();" style="width:70px;">
						<option value="">选择</option>
						<option value="吨">吨</option>
						<option value="公斤">公斤</option>
						<option value="斤">斤</option>
						<option value="千克">千克</option>
						<option value="克">克</option>
					</select>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">手填单位</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						数量:
					</label>
				</td>
				<td class="value">
					<input id="total" name="total" onkeyup="setWeightAndPrice()" value="${hmWorkPage.total }" type="text" style="width: 150px" class="inputxt"  datatype="d"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">数量</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						每件重量:
					</label>
				</td>
				<td class="value">
					<input id="weight" name="weight" onkeyup="setWeightAndPrice()" value="${hmWorkPage.weight }" type="text" style="width: 150px" class="inputxt"  datatype="d"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">每件重量</label>
				</td>
			</tr>
			<tr>
				<td align="right">
					<label class="Validform_label">
						合计重量:
					</label>
				</td>
				<td class="value">
					<input id="hjWeight" name="hjWeight" value="${hmWorkPage.hjWeight }" type="text" style="width: 150px" class="inputxt"  datatype="d"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">合计重量</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						合计金额:
					</label>
				</td>
				<td class="value" colspan="3">
					<input id="hjMoney" name="hjMoney" value="${hmWorkPage.hjMoney }" type="text" style="width: 150px" class="inputxt"  datatype="d"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">合计金额</label>
				</td>
			</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							人数:
						</label>
					</td>
					<td class="value">
					     	 <input id="peoples" name="peoples" value="${hmWorkPage.peoples }" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">人数</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							人均工资:
						</label>
					</td>
					<td class="value">
					     	 <input id="capitaWages" name="capitaWages" value="${hmWorkPage.capitaWages }" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">人均工资</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							固借人数:
						</label>
					</td>
					<td class="value">
						<input id="gdPeoples" name="gdPeoples" type="text" value="${hmWorkPage.gdPeoples }" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
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
						<input id="kfWages" name="kfWages" type="text" value="${hmWorkPage.kfWages }"  style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">可分配工资</label>
					</td>

					<td align="right">
						<label class="Validform_label">
							本组人数:
						</label>
					</td>
					<td class="value">
					     	 <input id="localPeoples" name="localPeoples" value="${hmWorkPage.localPeoples }" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">本组人数</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							平均提成:
						</label>
					</td>
					<td class="value">
					     	 <input id="choucheng" name="choucheng" value="${hmWorkPage.choucheng }" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">平均提成</label>
						</td>
					</tr>
	</table>

</t:formvalid>
</body>
<script src = "webpage/com/hm/rsgl/work/hmWork.js"></script>
