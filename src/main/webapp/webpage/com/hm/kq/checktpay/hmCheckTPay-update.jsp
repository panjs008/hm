<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>实际扣费表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmCheckTPayController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${hmCheckTPayPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								工号:
							</label>
						</td>
						<td class="value">
						    <input id="workNo" name="workNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckTPayPage.workNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
						    <input id="realName" name="realName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckTPayPage.realName}'/>
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
						    <input id="deptCode" name="deptCode" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckTPayPage.deptCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门代码</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								部门名称:
							</label>
						</td>
						<td class="value">
						    <input id="deptName" name="deptName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckTPayPage.deptName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部门名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								旷工天数:
							</label>
						</td>
						<td class="value">
						    <input id="kgDay" name="kgDay" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckTPayPage.kgDay}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">旷工天数</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								旷工金额:
							</label>
						</td>
						<td class="value">
						    <input id="kgMoney" name="kgMoney" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckTPayPage.kgMoney}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">旷工金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								迟到金额:
							</label>
						</td>
						<td class="value">
						    <input id="cdMoney" name="cdMoney" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckTPayPage.cdMoney}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">迟到金额</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								早退金额:
							</label>
						</td>
						<td class="value">
						    <input id="zdMoney" name="zdMoney" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckTPayPage.zdMoney}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">早退金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								缺卡金额:
							</label>
						</td>
						<td class="value">
						    <input id="qkMoney" name="qkMoney" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckTPayPage.qkMoney}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">缺卡金额</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								请假时长:
							</label>
						</td>
						<td class="value">
						    <input id="qjTime" name="qjTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckTPayPage.qjTime}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">请假时长</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								请假金额:
							</label>
						</td>
						<td class="value">
						    <input id="qjMoney" name="qjMoney" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmCheckTPayPage.qjMoney}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">请假金额</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/kq/checktpay/hmCheckTPay.js"></script>		
