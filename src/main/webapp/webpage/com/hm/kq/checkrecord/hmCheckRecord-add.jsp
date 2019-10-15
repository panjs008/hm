<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>考勤记录表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmCheckRecordController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${hmCheckRecordPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
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
							姓名:
						</label>
					</td>
					<td class="value">
					     	 <input id="realName" name="realName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
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
				</tr>
				<tr>
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
							月份:
						</label>
					</td>
					<td class="value">
					     	 <input id="month" name="month" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">月份</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							考勤时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="checkTime" name="checkTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">考勤时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							打卡时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="clockTime" name="clockTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">打卡时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							开单时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="kdTime" name="kdTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开单时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							打开结果:
						</label>
					</td>
					<td class="value">
					     	 <input id="clockResult" name="clockResult" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">打开结果</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/kq/checkrecord/hmCheckRecord.js"></script>		
