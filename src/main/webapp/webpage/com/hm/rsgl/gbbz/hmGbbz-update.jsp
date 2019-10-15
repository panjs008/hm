<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>公班标准</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmGbbzController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${hmGbbzPage.id }"/>
			<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								员工类型:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="yglx" field="yglx" typeGroupCode="yglb"  datatype="*" defaultVal="${hmGbbzPage.yglx }" hasLabel="false" title="员工类别"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">员工类型</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								性别:
							</label>
						</td>
						<td class="value">
							<t:dictSelect id="sex" field="sex" typeGroupCode="sex"  datatype="*" defaultVal="${hmGbbzPage.sex}" hasLabel="false" title="性别"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">性别</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								正常班:
							</label>
						</td>
						<td class="value">
						    <input id="zcb" name="zcb" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmGbbzPage.zcb}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">正常班</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								加班:
							</label>
						</td>
						<td class="value">
						    <input id="jiab" name="jiab" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmGbbzPage.jiab}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">加班</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/rsgl/gbbz/hmGbbz.js"></script>		
