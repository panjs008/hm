<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>出勤表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmAttenderController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${hmAttenderPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							姓名:
						</label>
					</td>
					<td class="value">
					     	 <input id="realName" name="realName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							工号:
						</label>
					</td>
					<td class="value">
					     	 <input id="workNo" name="workNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工号</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							部门:
						</label>
					</td>
					<td class="value">
					     	 <input id="deptName" name="deptName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							部门代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="deptCode" name="deptCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门代码</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							车间:
						</label>
					</td>
					<td class="value">
					     	 <input id="workName" name="workName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">车间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							车间代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="workCode" name="workCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">车间代码</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							班组:
						</label>
					</td>
					<td class="value">
					     	 <input id="groupName" name="groupName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">班组</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							班组代码:
						</label>
					</td>
					<td class="value">
					     	 <input id="groupCode" name="groupCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">班组代码</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							薪酬类别:
						</label>
					</td>
					<td class="value">
					     	 <input id="xclb" name="xclb" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">薪酬类别</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							职务:
						</label>
					</td>
					<td class="value">
					     	 <input id="job" name="job" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
					     	 <input id="jb" name="jb" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">级别</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							员工类别:
						</label>
					</td>
					<td class="value">
					     	 <input id="yglb" name="yglb" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">员工类别</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							出勤天数:
						</label>
					</td>
					<td class="value">
					     	 <input id="cqDay" name="cqDay" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出勤天数</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							请假:
						</label>
					</td>
					<td class="value">
					     	 <input id="qjMoney" name="qjMoney" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">请假</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							迟到:
						</label>
					</td>
					<td class="value">
					     	 <input id="cdMoney" name="cdMoney" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">迟到</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							旷工:
						</label>
					</td>
					<td class="value">
					     	 <input id="kgMoney" name="kgMoney" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">旷工</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							出勤奖:
						</label>
					</td>
					<td class="value">
					     	 <input id="mqj" name="mqj" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出勤奖</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							月份:
						</label>
					</td>
					<td class="value">
					     	 <input id="month" name="month" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">月份</label>
						</td>
					</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/kq/attender/hmAttender.js"></script>		
