<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>特殊计时员工表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  function returnToStaff(){

  }
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmTsjsbController.do?doAdd" tiptype="1">
	  <input id="tid" name="tid" type="hidden" value="${hmTsjsbPage.id }"/>
	  <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							姓名:
						</label>
					</td>
					<td class="value" colspan="3">
						<input id="staffId" name="staffId" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
						<input id="realName" name="realName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.realName}'/>
						<t:choose  hiddenName="staffId"  hiddenid="id" url="hmStaffController.do?select" name="hmStaffList" width="800px" height="500px"
									icon="icon-search" title="选择固定人员" textname="id,workNo,realName,deptCode,deptName,workCode,workName,groupCode,groupName,job,yglb,xclb" isclear="true" isInit="true"></t:choose>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">姓名</label>
					</td>
				</tr>
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
				<td align="right">
					<label class="Validform_label">
						部门:
					</label>
				</td>
				<td class="value">
					<input id="deptCode" name="deptCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.realName}'/>
					<input id="deptName" name="deptName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.realName}'/>
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
					<input id="workName" name="workName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.realName}'/>
					<input id="workCode" name="workCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.realName}'/>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">车间</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						班组:
					</label>
				</td>
				<td class="value">
					<input id="groupCode" name="groupCode" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.realName}'/>
					<input id="groupName" name="groupName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmStaffPage.realName}'/>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">班组</label>
				</td>
			</tr>

			<tr>
				<td align="right">
					<label class="Validform_label">
						职务:
					</label>
				</td>
				<td class="value">
					<t:dictSelect id="job" field="job" typeGroupCode="job"  datatype="*" defaultVal="default" hasLabel="false" title="职务"></t:dictSelect>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">职务</label>
				</td>
				<td align="right">
					<label class="Validform_label">
						员工类别:
					</label>
				</td>
				<td class="value">
					<t:dictSelect id="yglb" field="yglb" typeGroupCode="yglb"  datatype="*" defaultVal="default" hasLabel="false" title="员工类别"></t:dictSelect>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">员工类别</label>
				</td>
				</tr>
			  <tr>
					<td align="right">
						<label class="Validform_label">
							薪酬类别:
						</label>
					</td>
					<td class="value" colspan="3">
						<t:dictSelect id="xclb" field="xclb" typeGroupCode="xclb"  datatype="*" defaultVal="default" hasLabel="false" title="薪酬类别"></t:dictSelect>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">薪酬类别</label>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							正常班:
						</label>
					</td>
					<td class="value">
					     	 <input id="zcb" name="zcb" type="text" datatype="d" style="width: 150px" class="inputxt" ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">正常班</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							加班:
						</label>
					</td>
					<td class="value">
						<input id="jiab" name="jiab" type="text" datatype="d" style="width: 150px" class="inputxt" ignore="ignore"/>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">加班</label>
					</td>
				</tr>

			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/rsgl/tsjsb/hmTsjsb.js"></script>		
