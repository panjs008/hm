<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>考勤扣款表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmCheckPayController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${hmCheckPayPage.id }"/>
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
							部门名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="deptName" name="deptName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
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
							迟到时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="longTime" name="longTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">迟到时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							扣款金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="payMoney" name="payMoney" type="text" style="width: 150px" class="inputxt"  datatype="/^(-?\d+)(\.\d+)?$/"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">扣款金额</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/kq/checkpay/hmCheckPay.js"></script>		
