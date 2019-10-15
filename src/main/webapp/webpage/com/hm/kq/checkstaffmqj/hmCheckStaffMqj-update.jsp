<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>考勤满勤奖</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmCheckStaffMqjController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${hmCheckStaffMqjPage.id }"/>
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
						    <input id="realName" readonly name="realName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckStaffMqjPage.realName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								工号:
							</label>
						</td>
						<td class="value">
						    <input id="workNo"  readonly name="workNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckStaffMqjPage.workNo}'/>
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
						    <input id="deptName" readonly name="deptName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckStaffMqjPage.deptName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								车间:
							</label>
						</td>
						<td class="value">
							<input id="workName" readonly name="workName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckStaffMqjPage.workName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">车间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								班组:
							</label>
						</td>
						<td class="value">
						    <input id="groupName" readonly name="groupName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckStaffMqjPage.groupName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">班组</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								职务:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="job" field="job" typeGroupCode="job"  datatype="*" defaultVal="${hmCheckStaffMqjPage.job}" hasLabel="false" title="职务"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">职务</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								性别:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="sex" field="sex" typeGroupCode="sex"  defaultVal="${hmCheckStaffMqjPage.sex}" hasLabel="false" title="性别"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">性别</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								员工类别:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="yglb" field="yglb" typeGroupCode="yglb"  datatype="*" defaultVal="${hmCheckStaffMqjPage.yglb}" hasLabel="false" title="员工类别"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">员工类别</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								满勤奖:
							</label>
						</td>
						<td class="value">
						    <input id="mqj" name="mqj" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${hmCheckStaffMqjPage.mqj}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">满勤奖</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								全勤奖:
							</label>
						</td>
						<td class="value">
							<input id="qqj" name="qqj" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore"  value='${hmCheckStaffMqjPage.qqj}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">全勤奖</label>
						</td>

					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/kq/checkstaffmqj/hmCheckStaffMqj.js"></script>		
