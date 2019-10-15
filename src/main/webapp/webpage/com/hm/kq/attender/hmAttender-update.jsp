<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>出勤表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  $(document).ready(function(){
	  $("#sex").val('${hmAttenderPage.sex }');
  });
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmAttenderController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${hmAttenderPage.id }"/>
		<table style="width: 100%" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								月份:
							</label>
						</td>
						<td class="value">
							<input id="month" name="month" readonly type="text" style="width: 150px" class="Wdate"  ignore="ignore"  value='${hmAttenderPage.month}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">月份</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								部门:
							</label>
						</td>
						<td class="value">
							<input id="deptName" name="deptName" type="text" readonly style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmAttenderPage.deptName}'/>
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
							<input id="workName" name="workName" type="text" readonly style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmAttenderPage.workName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">车间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								班组:
							</label>
						</td>
						<td class="value">
							<input id="groupName" name="groupName" type="text" readonly style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmAttenderPage.groupName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">班组</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
							<input id="realName" name="realName" type="text" readonly style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmAttenderPage.realName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								性别:
							</label>
						</td>
						<td class="value">
							<select id="sex" name="sex" readonly="readonly">
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
								级别:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="jb" field="jb" typeGroupCode="jb" readonly="readonly" datatype="*" defaultVal="${hmAttenderPage.jb}" hasLabel="false" title="级别"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">级别</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								薪酬类别:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="xclb" field="xclb" typeGroupCode="xclb" readonly="readonly"  datatype="*" defaultVal="${hmAttenderPage.xclb}" hasLabel="false" title="薪酬类别"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">薪酬类别</label>
						</td>
						</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								员工类别:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="yglb" field="yglb" typeGroupCode="yglb" readonly="readonly" datatype="*" defaultVal="${hmAttenderPage.yglb}" hasLabel="false" title="员工类别"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">员工类别</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								出勤天数:
							</label>
						</td>
						<td class="value">
							<input id="cqDay" name="cqDay" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmAttenderPage.cqDay}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出勤天数</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<label class="Validform_label">
								请假:
							</label>
						</td>
						<td class="value">
						    <input id="qjMoney" name="qjMoney" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmAttenderPage.qjMoney}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">请假</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								出勤奖:
							</label>
						</td>
						<td class="value">
							<input id="mqj" name="mqj" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmAttenderPage.mqj}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出勤奖</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								迟到:
							</label>
						</td>
						<td class="value">
						    <input id="cdMoney" name="cdMoney" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmAttenderPage.cdMoney}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">迟到</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								旷工:
							</label>
						</td>
						<td class="value">
						    <input id="kgMoney" name="kgMoney" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmAttenderPage.kgMoney}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">旷工</label>
						</td>
					</tr>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/kq/attender/hmAttender.js"></script>		
