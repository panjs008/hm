<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>客户提醒表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" tiptype="1" layout="table" action="ymkCustomAlertController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${ymkCustomAlertPage.id }"/>
	  <input id="customId" name="customId" type="hidden" value="${param.customId }"/>

	  <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="200px;">
						<label class="Validform_label">
							提醒时间:
						</label>
					</td>
					<td class="value">
						<input id="alertTime" name="alertTime" datatype="*"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">提醒时间</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							提醒内容:
						</label>
					</td>
					<td class="value">
						<textarea id="alertContent" datatype="*"  style="width:90%;height:60px" class="inputxt" rows="3" name="alertContent"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提醒内容</label>
						</td>
				</tr>

				<%--<tr>
					<td align="right">
						<label class="Validform_label">
							提醒人员ID:
						</label>
					</td>
					<td class="value">
					     	 <input id="alertUserIds" name="alertUserIds" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提醒人员ID</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							提醒人员:
						</label>
					</td>
					<td class="value">
					     	 <input id="alertUserNames" name="alertUserNames" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">提醒人员</label>
						</td>
				</tr>--%>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/service/customalert/ymkCustomAlert.js"></script>		
