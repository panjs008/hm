<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>请假单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
		function returnToStaff(){

		}
		function setEndTime() {
			var d1  =  $("#beginTime").val();
			var d2  =  $("#endTime").val();
			var v;
			console.log($('input:radio:checked').val());
			if($('input:radio:checked').val() == '0'){
				d1 = d1.substr(0,10);
				d2 = d2.substr(0,10);
				console.log(d1);
				v = DateDiff(d1,d2);
			}else{
				d1 = d1.substr(11,13);
				d2 = d2.substr(11,13);
				console.log(d1);
				v = parseInt(d2)-parseInt(d1);
			}
			if(!isNaN(v)){
				$("#longTime").val(v);
			}
		}

		function DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2002-12-18格式
			var  aDate,  oDate1,  oDate2,  iDays
			aDate  =  sDate1.split("-")
			oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2002格式
			aDate  =  sDate2.split("-")
			oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])
			iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数
			return  iDays+1;
		}
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmLevalController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${hmLevalPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					姓名:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="staffId" name="staffId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="realName" name="realName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmLevalPage.realName}'/>
				<t:choose  hiddenName="staffId"  hiddenid="id" url="hmStaffController.do?select" name="hmStaffList" width="800px" height="500px"
						   icon="icon-search" title="选择固定人员" textname="id,workNo,realName,deptCode,deptName" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">姓名</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					工号:
				</label>
			</td>
			<td class="value">
				<input id="workNo" name="workNo" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${hmLevalPage.workNo }"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					部门:
				</label>
			</td>
			<td class="value">
				<input id="deptCode" name="deptCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmLevalPage.deptCode}'/>
				<input id="deptName" name="deptName" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmLevalPage.deptName}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">部门</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					申请日期:
				</label>
			</td>
			<td class="value">
				<input id="applyDate" name="applyDate" readonly value="${hmLevalPage.applyDate }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">申请日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					请假类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="levalType" field="levalType" typeGroupCode="qjlx"  datatype="*" defaultVal="${hmLevalPage.levalType }" hasLabel="false" title="请假类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">请假类型</label>
			</td>


		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					时间单位:
				</label>
			</td>
			<td class="value" colspan="3">
				<input  name="timeType" type="radio" ${hmLevalPage.timeType eq '0' ? 'checked':''} onclick="setEndTime();" datatype="*" value="0">
				按天
				<input  name="timeType" type="radio" ${hmLevalPage.timeType eq '1' ? 'checked':''} onclick="setEndTime();" datatype="*" value="1">
				按小时
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">时间单位</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					开始时间:
				</label>
			</td>
			<td class="value">
				<input id="beginTime" name="beginTime" type="text" value="${hmLevalPage.beginTime }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH'})"  style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开始时间</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					结束时间:
				</label>
			</td>
			<td class="value">
				<input id="endTime" name="endTime" type="text" value="${hmLevalPage.endTime }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH',minDate:'#F{$dp.$D(\'beginTime\');}',onpicked:setEndTime})" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">结束时间</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					时长:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="longTime" name="longTime" readonly value="${hmLevalPage.longTime }" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">时长</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					事由:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea  id="reason" style="width:95%;height:70px" class="inputxt" rows="5" name="reason">${hmLevalPage.reason }</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">事由</label>
			</td>
		</tr>


	</table>
</t:formvalid>
</body>
<script src = "webpage/com/hm/kq/leval/hmLeval.js"></script>
