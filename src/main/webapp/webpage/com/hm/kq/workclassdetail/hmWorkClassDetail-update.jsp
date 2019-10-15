<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>班次明细表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmWorkClassDetailController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${hmWorkClassDetailPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								班次ID:
							</label>
						</td>
						<td class="value">
						    <input id="workClassId" name="workClassId" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkClassDetailPage.workClassId}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">班次ID</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								时间:
							</label>
						</td>
						<td class="value">
						    <input id="cTime" name="cTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkClassDetailPage.cTime}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								开始:
							</label>
						</td>
						<td class="value">
						    <input id="sTime" name="sTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkClassDetailPage.sTime}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">开始</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								结束:
							</label>
						</td>
						<td class="value">
						    <input id="eTime" name="eTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkClassDetailPage.eTime}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">结束</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								迟到早退:
							</label>
						</td>
						<td class="value">
						    <input id="late" name="late" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkClassDetailPage.late}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">迟到早退</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								上班时间:
							</label>
						</td>
						<td class="value">
						    <input id="workSTime" name="workSTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkClassDetailPage.workSTime}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">上班时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								下班时间:
							</label>
						</td>
						<td class="value">
						    <input id="workETime" name="workETime" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkClassDetailPage.workETime}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">下班时间</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								时长:
							</label>
						</td>
						<td class="value">
						    <input id="longTime" name="longTime" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkClassDetailPage.longTime}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">时长</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								内容:
							</label>
						</td>
						<td class="value">
						    <input id="workContent" name="workContent" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkClassDetailPage.workContent}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">内容</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/kq/workclassdetail/hmWorkClassDetail.js"></script>		
