<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>考勤配置表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  $(document).ready(function(){
	  $("#bcName").val('${hmCheckStaffPage.bcName }');
	  $("#sex").val('${hmCheckStaffPage.sex }');

  });

  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmCheckStaffController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${hmCheckStaffPage.id }"/>
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
						    <input id="realName" name="realName" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckStaffPage.realName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								部门:
							</label>
						</td>
						<td class="value">
							<input id="deptName" name="deptName" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckStaffPage.deptName}'/>
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
						    <input id="workName" name="workName" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckStaffPage.workName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">车间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								班组:
							</label>
						</td>
						<td class="value">
							<input id="groupName" name="groupName" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckStaffPage.groupName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">班组</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<label class="Validform_label">
								职务:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="job" field="job" typeGroupCode="job"  datatype="*" defaultVal="${hmCheckStaffPage.job}" hasLabel="false" title="职务"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">职务</label>
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
						<td class="value" colspan="3">
							<t:dictSelect id="yglb" field="yglb" typeGroupCode="yglb"  datatype="*" defaultVal="${hmCheckStaffPage.yglb}" hasLabel="false" title="员工类别"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">员工类别</label>
						</td>
					</tr>
					<tr>
						<td align="right" >
							<label class="Validform_label">
								班次:
							</label>
						</td>
						<td class="value">
							<select id="bcName" name="bcName" >
								<c:forEach items="${workClassEntityList}" var="wc" varStatus="status">
									<option value="${wc.bcName}">${wc.bcName}</option>
								</c:forEach>
							</select>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">班次</label>
						</td>
						<td align="right" >
							<label class="Validform_label">
								假日:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="holiday" field="holiday" typeGroupCode="jiari"  datatype="*" defaultVal="${hmCheckStaffPage.holiday}" hasLabel="false" title="假日"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">假日</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/kq/checkstaff/hmCheckStaff.js"></script>		
