<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>考勤异常数据</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmCheckUnusualController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${hmCheckUnusualPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								月份:
							</label>
						</td>
						<td class="value">
						    <input id="month" name="month" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckUnusualPage.month}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">月份</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								工号:
							</label>
						</td>
						<td class="value">
						    <input id="workNo" name="workNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckUnusualPage.workNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工号</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
						    <input id="realName" name="realName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckUnusualPage.realName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								部门代码:
							</label>
						</td>
						<td class="value">
						    <input id="deptCode" name="deptCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckUnusualPage.deptCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门代码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								部门名称:
							</label>
						</td>
						<td class="value">
						    <input id="deptName" name="deptName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckUnusualPage.deptName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								考勤时间:
							</label>
						</td>
						<td class="value">
						    <input id="checkTime" name="checkTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckUnusualPage.checkTime}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">考勤时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								上班时间:
							</label>
						</td>
						<td class="value">
						    <input id="workTime" name="workTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckUnusualPage.workTime}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上班时间</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/kq/checkunusual/hmCheckUnusual.js"></script>		
