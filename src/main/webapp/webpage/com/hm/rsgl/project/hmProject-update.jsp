<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>项目表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmProjectController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${hmProjectPage.id }"/>
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
			<c:forEach var="list" items="${categoryEntities}" varStatus="status">
				<tr>
					<td align="right" width="200px">
						<label class="Validform_label">
								${list.name}:
						</label>
					</td>
					<td class="value">
						<c:if test="${list.required eq '0'}">
							<c:choose>
								<c:when test="${(list.column_type eq '0' || list.column_type eq '2') || list.column_type eq '1'}">
									<input id="${list.code}" style="width: 200px"  name="${list.code}" value="${hmProjectPage[list.code]}" type="text"  datatype="*"   class="inputxt" >
								</c:when>
								<c:when test="${list.column_type eq '2'}">
									<input id="${list.code}" style="width: 200px"  name="${list.code}" value="${hmProjectPage[list.code]}" value="${hmProjectPage[list.code]}" type="text" datatype="n"   class="inputxt" >
								</c:when>
								<c:when test="${list.column_type eq '3'}">
									<input id="${list.code}" name="${list.code}" value="${hmProjectPage[list.code]}" type="text" datatype="*" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 200px"    class="inputxt" >
								</c:when>
								<c:when test="${list.column_type eq '4'}">
									<textarea id="${list.code}" name="${list.code}"  style="width:500px;height:60px"  datatype="*"  class="inputxt">${hmProjectPage[list.code]}</textarea>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${list.required ne '0'}">
							<c:choose>
								<c:when test="${(list.column_type eq '0' || list.column_type eq '2') || list.column_type eq '1'}">
									<input id="${list.code}" style="width: 200px"  name="${list.code}" type="text"  value="${hmProjectPage[list.code]}"  class="inputxt" >
								</c:when>
								<c:when test="${list.column_type eq '2'}">
									<input id="${list.code}" style="width: 200px"  name="${list.code}" type="text"  value="${hmProjectPage[list.code]}"  class="inputxt" >
								</c:when>
								<c:when test="${list.column_type eq '3'}">
									<input id="${list.code}"  name="${list.code}" type="text" value="${hmProjectPage[list.code]}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 200px"    class="inputxt" >
								</c:when>
								<c:when test="${list.column_type eq '4'}">
									<textarea id="${list.code}" name="${list.code}" style="width:500px;height:60px"  class="inputxt">${hmProjectPage[list.code]}</textarea>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:if>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">${list.name}</label>
					</td>

				</tr>
			</c:forEach>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/rsgl/project/hmProject.js"></script>		
