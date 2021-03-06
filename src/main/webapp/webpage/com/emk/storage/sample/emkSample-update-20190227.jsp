<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>样品单</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<%@include file="/context/header2.jsp"%>

	<script type="text/javascript">
		//编写自定义JS代码
		$(function() {
			$("#type").change(function(){
				varval = $('#type').val();
				if(varval == 'cy'){
					$("#orderNo").attr("ignore","ignore");
					$("#pirceNo").removeAttr("ignore");
				}else{
					/* $("#settb").css("display","none");
					 $("#settb2").css("display","none");

					 $("#tk0").attr("ignore","ignore");
					 $("#settb").find(":text,textarea").attr("ignore","ignore");
					 $("#settb2").find(":text,textarea").attr("ignore","ignore");*/
					$("#pirceNo").attr("ignore","ignore");
					$("#orderNo").removeAttr("ignore");
				}
			});


		});



		function uploadSuccess(d,file,response){
			var src = d.attributes.url;
			$("#customSampleUrl").val(d.attributes.url);
			$("#customSample").val(d.attributes.name);

			$("#uploadimg").attr('src',d.attributes.url);

		}
		function uploadSuccess2(d,file,response){
			var src = d.attributes.url;
			$("#sampleSizeUrl").val(d.attributes.url);
			$("#sampleSize").val(d.attributes.name);
			$("#ccbId").html(d.attributes.name);

			$("#uploadimg2").attr('src',d.attributes.url);

		}

	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="emkYptzdController.do?doUpdate" tiptype="1">
	<input id="id" name="id" type="hidden" value="${emkSamplePage.id }"/>
	<input id="flag" name="flag" type="hidden" value="${emkSamplePage.flag}"/>
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right" style="width: 18%">
					<label class="Validform_label">
						报价单号:
					</label>
				</td>
				<td class="value" style="width: 32%">
					<input id="pirceNo" name="pirceNo" datatype="*" value="${emkSamplePage.pirceNo}" type="text" style="width: 150px" class="inputxt"  />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">报价单号</label>
				</td>
				<td align="right" style="width: 18%">
					<label class="Validform_label">
						订单号:
					</label>
				</td>
				<td class="value" style="width: 32%">
					<input id="orderNo" name="orderNo" datatype="*" value="${emkSamplePage.orderNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">订单号</label>
				</td>
			</tr>
			<tr>
                <td align="right" style="width: 18%">
                    <label class="Validform_label">
						样品通知单号:
                    </label>
                </td>
                <td class="value" style="width: 32%">
                    <input id="sampleNum" name="sampleNum" readonly value="${emkSamplePage.sampleNum}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
                        <span class="Validform_checktip"></span>
                        <label class="Validform_label" style="display: none;">样品通知单号</label>
                    </td>

				<td align="right" style="width: 18%">
					<label class="Validform_label">
						工厂:
					</label>
				</td>
				<td class="value" style="width: 32%">
					<t:dictSelect id="factoryCode" field="factoryCode" typeGroupCode="gongchang" datatype="*" defaultVal="${emkSamplePage.factoryCode}" hasLabel="false" title="工艺类型"></t:dictSelect>
					<span class="Validform_checktip"></span>
					<label class="Validform_label" style="display: none;">工厂</label>
				</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务部门:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="businesseDeptName" name="businesseDeptName" value="${emkSamplePage.businesseDeptName }" readonly type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesseDeptId" name="businesseDeptId"  value="${emkSamplePage.businesseDeptId }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务部门</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="businesserId" datatype="*" >
					<option value=''>请选择</option>
				</select>
				<input id="businesser" name="businesser" readonly value="${emkSamplePage.businesser }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="businesserName" name="businesserName" value="${emkSamplePage.businesserName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
		</tr>

		<tr>

			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户代码:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusNum" name="cusNum" readonly type="text" value="${emkSamplePage.cusNum }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户代码</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					客户名称:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<input id="cusName" name="cusName" readonly type="text" value="${emkSamplePage.cusName }" style="width: 150px" class="inputxt"  datatype="*"/>
				<t:choose  hiddenName="cusNum"  hiddenid="cusNum" url="ymkCustomController.do?select" name="ymkCustomList" width="700px" height="500px"
						   icon="icon-search" title="选择客户" textname="cusName,businesseDeptName,businesseDeptId,businesser,businesserName,tracer,tracerName,developer,developerName,bz" isclear="true" isInit="true"></t:choose>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">客户名称</label>
			</td>
		</tr>
		<tr>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					业务跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="tracerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="tracer" name="tracer" value="${emkSamplePage.tracer }" readonly type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="tracerName" name="tracerName" value="${emkSamplePage.tracerName }"  type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>
			<td align="right" style="width: 18%">
				<label class="Validform_label">
					生产跟单员:
				</label>
			</td>
			<td class="value" style="width: 32%">
				<select class="form-control select2" id="developerId"  >
					<option value=''>请选择</option>
				</select>
				<input id="developer" name="developer" readonly value="${emkSamplePage.developer }" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="developerName" name="developerName"  value="${emkSamplePage.developerName }" type="hidden"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">业务员</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					样品类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="type" field="type" typeGroupCode="sampletype" datatype="*" defaultVal="${emkSamplePage.type}" hasLabel="false" title="样品类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">样品类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					版次:
				</label>
			</td>
			<td class="value">
				<input id="version" name="version"  value="${emkSamplePage.version}" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">版次</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					打样需求单号:
				</label>
			</td>
			<td class="value">
				<input id="xqdh" name="xqdh" type="text" value="${emkSamplePage.xqdh }" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">打样需求单编号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					款式大类:
				</label>
			</td>
			<td class="value">
				<input id="proTypeTree" value="${emkSamplePage.proTypeName}">
				<input id="proTypeName" name="proTypeName" value="${emkSamplePage.proTypeName}" datatype="*"  type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<input id="proType" name="proType" type="hidden" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款式大类</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					款号:
				</label>
			</td>
			<td class="value">
				<input id="sampleNo" name="sampleNo" value="${emkSamplePage.sampleNo}" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">款号</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					工艺类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect id="gyzl" field="gyzl" typeGroupCode="gylx" datatype="*" defaultVal="${emkSamplePage.gyzl}" hasLabel="false" title="工艺类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">工艺类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					通知单日期:
				</label>
			</td>
			<td class="value">
				<input id="kdTime" name="kdTime" value="${emkSamplePage.kdTime}" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">通知单日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					交货时间:
				</label>
			</td>
			<td class="value">
				<input id="receviceDate" name="receviceDate" value="${emkSamplePage.receviceDate}" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'kdTime\');}'})" type="text" style="width: 150px" class="Wdate"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">交货时间</label>
			</td>

		</tr>


		<tr>
			<td align="right">
				<label class="Validform_label">
					布面克重:
				</label>
			</td>
			<td class="value">
				<input id="weight" name="weight"  value="${emkSamplePage.weight}" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面克重</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					布面成分:
				</label>
			</td>
			<td class="value">
				<input id="chengf" name="chengf"  value="${emkSamplePage.chengf}" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">布面成分</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					客户原样:
				</label>
			</td>
			<td class="value">
				<input id="customSample" name="customSample" value="${emkSamplePage.customSample}" type="hidden" />
				<img id="uploadimg" src="${emkSamplePage.customSampleUrl eq '' ? 'images/bjlogo.png':emkSamplePage.customSampleUrl}" width="150" height="150">
				<t:upload name="instruction" id="instruction" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess" >
				</t:upload>[<a href="javascript:findDetail('${emkSamplePage.customSampleUrl }')">${emkSamplePage.customSample }</a>]
				<span id="khyyId"></span>
				<input id="customSampleUrl" name="customSampleUrl" value="${emkSamplePage.customSampleUrl}"  type="hidden" />
			</td>

			<td align="right">
				<label class="Validform_label">
					尺寸表:
				</label>
			</td>
			<td class="value">
				<img id="uploadimg2" src="${emkSamplePage.sampleSizeUrl eq '' ? 'images/bjlogo.png':emkSamplePage.sampleSizeUrl}" width="150" height="150">
				<t:upload name="instruction2" id="instruction2" dialog="false" extend="*.jpg;*.png;*.gif;*.ico;*.dwg" buttonText="添加文件" queueID="instructionfile" view="false" auto="true" uploader="systemController.do?saveFiles"  onUploadSuccess="uploadSuccess2" >
				</t:upload>[<a href="javascript:findDetail('${emkSamplePage.customSampleUrl }')">${emkSamplePage.customSample }</a>]
				<span id="ccbId"></span>
				<input id="sampleSizeUrl" name="sampleSizeUrl" type="hidden" value="${emkSamplePage.sampleSizeUrl}"/>
				<input id="sampleSize" name="sampleSize" type="hidden" value="${emkSamplePage.sampleSize}"/>
			</td>

		</tr>
		<tr>
			<td colspan="4" id="instructionfile" class="value">
			</td>
		</tr>


			<%--	<tr>
                  <td align="right">
                      <label class="Validform_label">
                          包装方案:
                      </label>
                  </td>
                  <td class="value" colspan="3">
                      <textarea  id="packing" style="width:95%;height:70px" class="inputxt" rows="5" name="packing"></textarea>
                      <span class="Validform_checktip"></span>
                      <label class="Validform_label" style="display: none;">包装方案</label>
                  </td>
              </tr>
          <tr>
              <td align="right">
                  <label class="Validform_label">
                      工艺要求:
                  </label>
              </td>
              <td class="value" colspan="3">
                  <textarea  id="requiretext" style="width:95%;height:70px" class="inputxt" rows="5" name="requiretext"></textarea>
                      <span class="Validform_checktip"></span>
                      <label class="Validform_label" style="display: none;">要求</label>
                  </td>
              </tr>

              <tr>
                  <td align="right">
                      <label class="Validform_label">
                          备注:
                      </label>
                  </td>
                  <td class="value" colspan="3">
                      <textarea  id="remark" style="width:95%;height:70px" class="inputxt" rows="5" name="remark"></textarea>
                      <span class="Validform_checktip"></span>
                      <label class="Validform_label" style="display: none;">备注</label>
                  </td>
              </tr>--%>

		<tr>
			<td align="right">
				<label class="Validform_label">
					下机克重:
				</label>
			</td>
			<td class="value">
				<input id="xjkz" name="xjkz" datatype="d" value="${emkSamplePage.xjkz}" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">下机克重</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					单件所需时间:
				</label>
			</td>
			<td class="value">
				<input id="djsxTime" name="djsxTime" value="${emkSamplePage.djsxTime}"  type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单件所需时间</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					单位:
				</label>
			</td>
			<td class="value">
				<input id="unit" name="unit"  type="text" value="${emkSamplePage.unit}" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">单位</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					机台日产量:
				</label>
			</td>
			<td class="value">
				<input id="jtrcl" name="jtrcl"  type="text" value="${emkSamplePage.jtrcl}" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">机台日产量</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					前道损耗率:
				</label>
			</td>
			<td class="value">
				<input id="qdshl" name="qdshl" datatype="d" value="${emkSamplePage.qdshl}" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">前道损耗率</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					后道损耗率:
				</label>
			</td>
			<td class="value">
				<input id="hdshl" name="hdshl"  datatype="d" value="${emkSamplePage.hdshl}" type="text" style="width: 150px"  ignore="ignore" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">后道损耗率</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script src = "webpage/com/emk/storage/sample/emkSample.js"></script>
<script>
	$(document).ready(function() {
		$("#instruction-button").css("width","70px");
		$("#instruction2-button").css("width","70px");
		$('#proTypeTree').combotree({
			url : 'emkProductTypeController.do?setPOfficeInfo&selfId=${emkProductTypePage.id}',
			panelHeight: 200,
			width: 157,
			onClick: function(node){
				$("#proType").val(node.id);
				$("#proTypeName").val(node.text);

			}
		});
	});
</script>