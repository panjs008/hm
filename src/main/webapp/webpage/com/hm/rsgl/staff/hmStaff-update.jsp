<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>人员信息表</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/selectDept.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
		$(document).ready(function(){
			$("#state").val(${hmStaffPage.state });
			$("#sex").val('${hmStaffPage.sex }');

		});
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmStaffController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${hmStaffPage.id }"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					工号:
				</label>
			</td>
			<td class="value">
				<input id="workNo" name="workNo" type="text" readonly style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.workNo}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					姓名:
				</label>
			</td>
			<td class="value">
				<input id="realName" name="realName" type="text" style="width: 150px" class="inputxt" datatype="*"  validType="hm_staff,real_name,id" value='${hmStaffPage.realName}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">姓名</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					部门:
				</label>
			</td>
			<td class="value">
				<select id="deptCode" name="deptCode"  style="width:155px;">
					<option>--部门--</option>
				</select>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">部门</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					车间:
				</label>
			</td>
			<td class="value">
				<select id="workCode" name="workCode" style="width:155px;">
					<option>--选择--</option>
				</select>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">车间</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					班组:
				</label>
			</td>
			<td class="value">
				<select id="groupCode" name="groupCode" style="width:155px;">
					<option>--选择--</option>
				</select>

				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">班组</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					职务:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="job" field="job" typeGroupCode="job"  datatype="*" defaultVal="${hmStaffPage.job}" hasLabel="false" title="职务"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">职务</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					级别:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="jb" field="jb" typeGroupCode="jb"  datatype="*" defaultVal="${hmStaffPage.jb}" hasLabel="false" title="级别"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">级别</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					薪酬类别:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="xclb" field="xclb" typeGroupCode="xclb"  datatype="*" defaultVal="${hmStaffPage.xclb}" hasLabel="false" title="薪酬类别"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">薪酬类别</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					性别:
				</label>
			</td>
			<td class="value">
				<select id="sex" name="sex">
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
				<%--<t:dictSelect id="sex" field="sex" typeGroupCode="sex"  defaultVal="${hmStaffPage.sex}" hasLabel="false" title="性别"></t:dictSelect>--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">性别</label>
			</td>

		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					员工类别:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="yglb" field="yglb" typeGroupCode="yglb"  datatype="*" defaultVal="${hmStaffPage.yglb}" hasLabel="false" title="员工类别"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">员工类别</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					带工:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="taker" field="taker" typeGroupCode="dg"  defaultVal="${hmStaffPage.taker}" hasLabel="false" title="带工"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">带工</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					固定/临时:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="workType" field="workType" typeGroupCode="gdls"  defaultVal="${hmStaffPage.workType}" hasLabel="false" title="性别"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">固定临时</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					入职日期:
				</label>
			</td>
			<td class="value">
				<input id="rzDate" name="rzDate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 150px" class="Wdate"  ignore="ignore"  value='${hmStaffPage.rzDate}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">入职日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					转正日期:
				</label>
			</td>
			<td class="value">
				<input id="zzDate" name="zzDate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 150px" class="Wdate"  ignore="ignore"  value='${hmStaffPage.zzDate}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">转正日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					手机号:
				</label>
			</td>
			<td class="value">
				<input id="telphone" name="telphone" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.telphone}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">手机号</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					身份证号码:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="idCard" name="idCard" onkeyup="getBirthdayFromIdCard()"  onafterpaste="getBirthdayFromIdCard()" type="text" datatype="*" style="width:150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.idCard}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">身份证号码</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					出生日期:
				</label>
			</td>
			<td class="value">
				<input id="birthDay" name="birthDay"  type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" class="Wdate"  ignore="ignore"  value='${hmStaffPage.birthDay}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">出生日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工龄:
				</label>
			</td>
			<td class="value">
				<input id="workYear" name="workYear" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.workYear}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工龄</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					月休天数:
				</label>
			</td>
			<td class="value">
				<input id="sleepDays" name="sleepDays" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.sleepDays}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">月休天数</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					民族:
				</label>
			</td>
			<td class="value">
				<input id="mz" name="mz" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.mz}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">民族</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					学历:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="xueli" field="xueli" typeGroupCode="xueli"  defaultVal="${hmStaffPage.xueli}" hasLabel="false" title="学历"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">学历</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					家庭电话:
				</label>
			</td>
			<td class="value">
				<input id="housePhone" name="housePhone" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.housePhone}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">家庭电话</label>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					紧急联系人电话:
				</label>
			</td>
			<td class="value">
				<input id="jjlxr" name="jjlxr" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.jjlxr}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">紧急联系人电话</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					转正提醒:
				</label>
			</td>
			<td class="value">
				<input id="zzDays" name="zzDays" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.zzDays}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">转正提醒</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					政治面貌:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="zzmm" field="zzmm" typeGroupCode="zzmm"  defaultVal="${hmStaffPage.zzmm}" hasLabel="false" title="政治面貌"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">政治面貌</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否内宿:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="isNs" field="isNs" typeGroupCode="isNs"  defaultVal="${hmStaffPage.isNs}" hasLabel="false" title="政治面貌"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否内宿</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					户籍所在地:
				</label>
			</td>
			<td class="value">
				<input id="hjszd" name="hjszd" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.hjszd}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">户籍所在地</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					电子邮件:
				</label>
			</td>
			<td class="value">
				<input id="email" name="email" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.email}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">电子邮件</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					离职日期:
				</label>
			</td>
			<td class="value" colspan="5">
				<input id="lzDate" name="lzDate"  type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" class="Wdate"  ignore="ignore"  value='${hmStaffPage.lzDate}'/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">离职日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					状态:
				</label>
			</td>
			<td class="value" colspan="5">
				<select id="state" name="state" >
					<option value="0">在职</option>
					<option value="1">离职</option>
				</select>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">离职类别</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script src = "webpage/com/hm/rsgl/staff/hmStaff.js"></script>
