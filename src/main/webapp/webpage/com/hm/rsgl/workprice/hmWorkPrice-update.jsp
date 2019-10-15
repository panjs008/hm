<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>工价表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
	 <%@include file="/context/header2.jsp"%>
	 <script type="text/javascript">
	  //编写自定义JS代码
	  $(document).ready(function(){
		  BindDeptSelect("mainWorkId","hmWorkController.do?findDept&len=9",1,$("#mainWorkName").val()+","+$("#mainWorkCode").val());
		  $("#mainWorkId").change(function(){
			  var itemarr = $("#mainWorkId").val().split(","); //字符分割
			  $("#mainWorkCode").val(itemarr[1]);
			  $("#mainWorkName").val(itemarr[0]);
			  BindDeptSelect("workGroupId","hmWorkController.do?findDept&len=12&pCode="+$("#mainWorkCode").val(),1,$("#workGroupName").val()+","+$("#workGroupCode").val());

		  });
		  $("#isJiab").val('${hmWorkPricePage.isJiab }');

		  $("#workGroupId").change(function(){
			  var itemarr = $("#workGroupId").val().split(","); //字符分割
			  $("#workGroupCode").val(itemarr[1]);
			  $("#workGroupName").val(itemarr[0]);
		  });
	  });
	  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="hmWorkPriceController.do?doUpdate" tiptype="1">
					<input id="id" name="id" type="hidden" value="${hmWorkPricePage.id }"/>
		<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								日期:
							</label>
						</td>
						<td class="value">
							<input id="kdDate" value="${hmWorkPricePage.kdDate}" name="kdDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly name="kdDate" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">日期</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								是否连班:
							</label>
						</td>
						<td class="value">
							<select name="isJiab" id="isJiab">
								<option value="0">否</option>
								<option value="1">是</option>
							</select>

							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否连班</label>
						</td>
						</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								主车间:
							</label>
						</td>
						<td class="value">
							<select class="form-control select2" id="mainWorkId"  datatype="*"  >
								<option value=''>请选择</option>
							</select>
							<input id="mainWorkCode" name="mainWorkCode" value="${hmWorkPricePage.mainWorkCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
							<input id="mainWorkName" name="mainWorkName" value="${hmWorkPricePage.mainWorkName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />

							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">主车间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								主班组:
							</label>
						</td>
						<td class="value">
							<select class="form-control select2" id="workGroupId"  datatype="*"  >
								<option value=''>请选择</option>
							</select>
							<input id="workGroupCode" name="workGroupCode" value="${hmWorkPricePage.workGroupCode }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
							<input id="workGroupName" name="workGroupName" value="${hmWorkPricePage.workGroupName }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />

							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">主班组</label>
						</td>

					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								工号:
							</label>
						</td>
						<td class="value">
						    <input id="workNo" name="workNo" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkPricePage.workNo}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工号</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								姓名:
							</label>
						</td>
						<td class="value">
							<input id="realName" name="realName" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkPricePage.realName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">姓名</label>
						</td>
					</tr>

					<tr>
						<td class="value" align="left" colspan="4">
							<label class="Validform_label">
								正常班
							</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								出勤时间
							</label>
						</td>
						<td class="value">
						    <input id="zcb" name="zcb" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkPricePage.zcb}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">正常班</label>
						</td>

						<td align="right">
							<label class="Validform_label">
								实时单价
							</label>
						</td>
						<td class="value">
							<input id="ssPrice" name="ssPrice" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkPricePage.ssPrice}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">实时单价</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								特殊单价
							</label>
						</td>
						<td class="value">
							<input id="tsPrice" name="tsPrice" datatype="d" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkPricePage.tsPrice}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">特殊单价</label>
						</td>

						<td align="right">
							<label class="Validform_label">
								统一单价
							</label>
						</td>
						<td class="value">
							<input id="tyPrice" name="tyPrice" datatype="d" readonly  type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkPricePage.tyPrice}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">统一单价</label>
						</td>
					</tr>
					<tr>
						<td class="value" align="left" colspan="4">
							<label class="Validform_label">
								加班
							</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								出勤时间:
							</label>
						</td>
						<td class="value">
						    <input id="jiab" name="jiab" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkPricePage.jiab}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">出勤时间</label>
						</td>
						<td align="right">
							<label class="Validform_label">
								实时单价
							</label>
						</td>
						<td class="value">
							<input id="ssPriceT" name="ssPriceT" datatype="d" type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkPricePage.ssPriceT}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">实时单价</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								特殊单价
							</label>
						</td>
						<td class="value">
							<input id="tsPriceT" name="tsPriceT" datatype="d" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkPricePage.tsPriceT}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">特殊单价</label>
						</td>

						<td align="right">
							<label class="Validform_label">
								统一单价
							</label>
						</td>
						<td class="value">
							<input id="tyPriceT" name="tyPriceT" datatype="d" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore"  value='${hmWorkPricePage.tyPriceT}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">统一单价</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/hm/rsgl/workprice/hmWorkPrice.js"></script>		
