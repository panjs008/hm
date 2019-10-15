<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<div id="main_list" class="easyui-layout" fit="true">
	<div region="center" style="padding:0px;border:0px">
		<t:datagrid name="hmWorkPriceList" checkbox="false" sortName="yglx" pagination="true" pageSize="30" fitColumns="false" title="" actionUrl="hmWorkPriceController.do?datagrid&orderId=${param.orderId}" idField="id" fit="true" btnCls="bootstrap"  queryMode="group">
			<t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<t:dgCol title="主键"  field="orderId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
			<%--<t:dgCol title="日期"  field="kdDate"  queryMode="single"  width="90"></t:dgCol>--%>
			<t:dgCol title="班组类型"  rowspan="2" frozenColumn="true" replace="本组_0,其他借调_1,固定借调_2,临时借调_3"  field="type" queryMode="single"  width="70"></t:dgCol>
			<t:dgCol title="工号" rowspan="2" frozenColumn="true"  query="true" field="workNo"  queryMode="single"  width="50"></t:dgCol>
			<t:dgCol title="姓名"  rowspan="2" frozenColumn="true" query="true" field="realName"  queryMode="single"  width="60"></t:dgCol>
			<t:dgCol title="性别"  rowspan="2" frozenColumn="true" field="sex" dictionary="sex" queryMode="single"  width="45"></t:dgCol>
			<t:dgCol title="类别"  rowspan="2" frozenColumn="true" field="yglx" dictionary="yglb" queryMode="single"  width="45"></t:dgCol>
			<t:dgCol title="组长"  rowspan="2" frozenColumn="true" field="isZz" replace="是_1,否_0"  queryMode="single"  width="50"></t:dgCol>

			<t:dgCol title="计时工资"   colspan="5"></t:dgCol>
			<t:dgCol title="计件工时"  colspan="3"></t:dgCol>
			<t:dgCol title="计件工资" colspan="3"></t:dgCol>
			<t:dgCol title="工资合计" rowspan="2"  newColumn="true"  formatterjs="setPoint"  field="gzHj"  queryMode="single"  width="80"></t:dgCol>


			<t:dgCol title="正常班"  field="zsZcb"  queryMode="single"  width="58"></t:dgCol>
			<t:dgCol title="单价"  field="zsZcbPrice"  queryMode="single"  width="55"></t:dgCol>
			<t:dgCol title="加班"  field="zsJiab"  queryMode="single"  width="55"></t:dgCol>
			<t:dgCol title="单价"  field="zsJiabPrice"  queryMode="single"  width="55"></t:dgCol>
			<t:dgCol title="合计"  field="zshj" formatterjs="setPoint" queryMode="single"  width="55"></t:dgCol>

			<t:dgCol title="正常班"  field="zcb"  queryMode="single"  width="58"></t:dgCol>
			<t:dgCol title="加班"  field="jiab"  queryMode="single"  width="55"></t:dgCol>
			<t:dgCol title="合计"  field="zjhj" formatterjs="setPoint0"  queryMode="single"  width="55"></t:dgCol>

			<t:dgCol title="工资"  field="wages"  queryMode="single"  width="60"></t:dgCol>
			<t:dgCol title="可分配工资"  field="kfWages"  queryMode="single"  width="70"></t:dgCol>
			<t:dgCol title="提成"  field="choucheng"  queryMode="single"  width="60"></t:dgCol>

		</t:datagrid>
	</div>
</div>
<div data-options="region:'east',
	title:'生产具体内容',
	collapsed:true,
	split:true,
	border:false,
	onExpand : function(){
		li_east = 1;
	},
	onCollapse : function() {
	    li_east = 0;
	}"
	 style="width: 500px; overflow: hidden;" id="eastPanel">
	<div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false" id="proDetialListpanel"></div>
</div>
<script src = "webpage/com/hm/rsgl/workprice/hmWorkPriceList.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var title = "生产具体内容";
		if(li_east == 0 || $('#main_list').layout('panel','east').panel('options').title != title){
			$('#main_list').layout('expand','east');
		}
		$('#main_list').layout('panel','east').panel('setTitle', title);
		$('#main_list').layout('panel','east').panel('resize', {width: 500});

		$('#proDetialListpanel').panel("refresh", "hmWorkController.do?forOrder&orderId=${param.orderId}" );
	});
	function setPoint(val,row,index){
		if(val != null && val != ''){
			return parseInt(val);
		}else{
			return '';
		}
	}
	function setPoint0(val,row,index){
		if(val != null && val != ''){
			return formatDecimal(val,1);
		}else{
			return '';
		}
	}
	function formatDecimal(num, decimal) {
		num = num.toString()
		let index = num.indexOf('.')
		if (index !== -1) {
			num = num.substring(0, decimal + index + 1)
		} else {
			num = num.substring(0)
		}
		return parseFloat(num).toFixed(decimal)
	}
</script>