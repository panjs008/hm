<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>字段表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  $(function() {
	  $("#columnType").val("${hmCategoryPage.columnType}");
	  $("#required").val("${hmCategoryPage.required }");
	  $("#queryed").val("${hmCategoryPage.queryed }");
  });
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmCategoryController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${hmCategoryPage.id }"/>
			<input id="parentCode" name="parentCode" type="hidden" value="${hmCategoryPage.parentCode }">
			<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							序号:
						</label>
					</td>
					<td class="value">
						<input id="orderNum" name="orderNum" type="text" value="${hmCategoryPage.orderNum}"  datatype="n" style="width: 150px" class="inputxt" >
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">字段类型</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							字段类型:
						</label>
					</td>
					<td class="value">
						<select id="columnType" name="columnType" datatype="*">
							<option value="0" />短文本</option>
							<option value="1" />长文本</option>
							<option value="2" />数值</option>
							<option value="6" />正数值</option>
							<option value="7" />负数值</option>
							<option value="3" />日期</option>
							<option value="5" />下拉框</option>
							<option value="4" />文本域</option>
						</select>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">字段类型</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型名称:
						</label>
					</td>
					<td class="value">
						<input id="name" name="name" value="${hmCategoryPage.name}" ${(hmCategoryPage.name eq '基本工资' || hmCategoryPage.name eq '岗位补贴') ? 'readonly':''}  type="text" datatype="*" style="width: 150px" class="inputxt" >
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">类型名称</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							字段编码:
						</label>
					</td>
					<td class="value">
						<input id="code" name="code" value="${hmCategoryPage.code}" datatype="*" readonly type="text" style="width: 150px" class="inputxt" >
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">类型编码</label>
					</td>

				</tr>

				<tr>
					<td align="right"><label class="Validform_label"> 是否必填:
					</label></td>
					<td class="value"><select id="required" name="required">
						<option value="1" />否</option>
						<option value="0" />是</option>
					</select></td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label"> 是否查询:
					</label></td>
					<td class="value"><select id="queryed" name="queryed">
						<option value="1" />否</option>
						<option value="0" />是</option>
					</select></td>
				</tr>



			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/rsgl/category/hmCategory.js"></script>		
