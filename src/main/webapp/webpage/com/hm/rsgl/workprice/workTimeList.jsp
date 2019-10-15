<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flagWT = 0;
    var currentflagWTWT = 0;

    $('#addBtnWT').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtnWT').linkbutton({
        iconCls: 'icon-remove'
    });


    $('#addBtnWT').bind('click', function(){
        flagWT++;
        $("#seqNumWT").html(flagWT);
        var tr =  $("#add_jeecgOrderProduct_tableWT_template tr").clone();
        $("#add_jeecgOrderProduct_tableWT").append(tr);

        $("#add_jeecgOrderProduct_tableWT").find("[id='ck00']").attr("id","ck"+flagWT);
        $("#ck"+flagWT).val(flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='workNo00']").attr("id","workNo"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='realName00']").attr("id","realName"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='zcb00']").attr("id","zcb"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='jiab00']").attr("id","jiab"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='tsPrice00']").attr("id","tsPrice"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='tyPrice00']").attr("id","tyPrice"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='tsPriceT00']").attr("id","tsPriceT"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='tyPriceT00']").attr("id","tyPriceT"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='type00']").attr("id","type"+flagWT);

        resetTrNum('add_jeecgOrderProduct_tableWT');


        $("#orderMxListIDWT").val($("#mxtbWT").find("tr").length-1);
    });
    $('#delBtnWT').bind('click', function(){
        var chk_value =[];
        $("html,body").animate({scrollTop:400},1);
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });

        $("#add_jeecgOrderProduct_tableWT").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_tableWT');
        $("#orderMxListIDWT").val($("#mxtbWT").find("tr").length-1);


    });
    $(document).ready(function(){
        $(".datagrid-toolbar").parent().css("width","auto");
        //将表格的表头固定
        $("#jeecgOrderProduct_table").createhftable({
            height:'200px',
            width:'auto',
            fixFooter:false
        });
        <c:if test="${param.orderId eq null || param.orderId eq '' || fn:length(workPriceEntityList) eq 0 }">
            $('#addBtnWT').click();
        </c:if>
    });

    
</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_tableWT_template">
        <tr>
            <td align="center"><input style="width: 30px;" id="ck00" type="checkbox" name="ck" /></td>
            <td align="center" width="30"><span id="seqNumWT"></span></td>
            <td align="center">
                <input id="workNo00" nullmsg="请输入工号！"  errormsg="请输入工号" name="orderMxList[#index#].workNo" maxlength="100" type="hidden"
                       style="width: 86%;" ignore="ignore">
                <input id="realName00" nullmsg="请输入姓名！" datatype="*"  errormsg="请输入姓名" name="orderMxList[#index#].realName" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="zcb00" nullmsg="请输入出勤时间！"  datatype="d"  value="9" errormsg="请输入出勤时间" name="orderMxList[#index#].zcb" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="ssPrice00" nullmsg="请输入实时单价！"  datatype="d"  value="" errormsg="请输入实时单价" name="orderMxList[#index#].ssPrice" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="tsPrice00" nullmsg="请输入特殊单价！" readonly datatype="d"  value="" errormsg="请输入特殊单价" name="orderMxList[#index#].tsPrice" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="tyPrice00" nullmsg="请输入统一单价！" readonly datatype="d"  value="" errormsg="请输入统一单价" name="orderMxList[#index#].tyPrice" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="jiab00" nullmsg="请输入出勤时间！"  datatype="d"  errormsg="请输入出勤时间" name="orderMxList[#index#].jiab" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="ssPriceT00" nullmsg="请输入实时单价！"  datatype="d"  value="" errormsg="请输入实时单价" name="orderMxList[#index#].ssPriceT" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="tsPriceT00" nullmsg="请输入特殊单价！" readonly datatype="d"  value="" errormsg="请输入特殊单价" name="orderMxList[#index#].tsPriceT" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="tyPriceT00" nullmsg="请输入统一单价！" readonly datatype="d"  value="" errormsg="请输入统一单价" name="orderMxList[#index#].tyPriceT" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore">
                <input id="type00" type="hidden" name="orderMxList[#index#].type" />
            </td>


        </tr>
    </tbody>

</table>
<div style="display:none">
    <input id="workNo" name="workNo" type="text" />
    <input id="realName" name="realName" type="text" />
    <input id="sex" name="sex" type="text" />

    <t:choose  hiddenName="id"  hiddenid="id" url="hmStaffController.do?select&flagWT=0" name="hmStaffList" width="820px" height="500px"
               icon="icon-search" title="选择员工" textname="id,workNo,realName,sex" isclear="true" isInit="true"></t:choose>
</div>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtnWT" href="#"></a><a id="delBtnWT" href="#"></a></div>
<input id="orderMxListIDWT" type="hidden" name="dataRowsValWork" />
<div class="table-c">
    <table id="mxtbWT" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center" rowspan="2" width="30" ><input type="checkbox" onclick="selectAll(this.checked)" /></td>
            <td align="center" rowspan="2" width="30" >序号</td>
            <%--<td align="center" rowspan="2" width="80">工号</td>--%>
            <td align="center" rowspan="2" width="70">姓名</td>
            <td align="center" colspan="4" width="150">正常班</td>
            <td align="center" colspan="4" width="150">加班</td>
            </tr>
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td  align="center"  width="30">出勤时间</td>
            <td  align="center"  width="30">实时单价</td>
            <td  align="center"  width="30">特殊单价</td>
            <td  align="center"  width="30">统一单价</td>
            <td  align="center"  width="30">出勤时间</td>
            <td  align="center"  width="30">实时单价</td>
            <td  align="center"  width="30">特殊单价</td>
            <td  align="center"  width="30">统一单价</td>
        </tr>
        <tbody id="add_jeecgOrderProduct_tableWT">
          <%--  <c:if test="${fn:length(workPriceEntityList)  > 0 }">
                <c:forEach items="${workPriceEntityList}" var="poVal" varStatus="status">
                    <tr>
                        <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/></td>
                        <td align="center" width="40">${status.index+1}</td>
                        <td align="center"><input  nullmsg="请输入工号！" id="workNo${status.index}"  errormsg="请输入工号" value="${poVal.workNo}" name="orderMxList[${status.index}].workNo" maxlength="100" type="text"
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入姓名！" id="realName${status.index}"  errormsg="请输入姓名" value="${poVal.realName}" name="orderMxList[${status.index}].realName" maxlength="100" type="text"
                                                   style="width: 86%;" ignore="ignore"></td>


                        <td align="center"><input id="zcb${status.index}" nullmsg="请输入正常班！" onkeyup="sethjV(${status.index})" value="${poVal.zcb}"  datatype="d"  value="9" errormsg="请输入正常班" name="orderMxList[${status.index}].zcb" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input id="jiab${status.index}" nullmsg="请输入加班！" onkeyup="sethjV(${status.index})" value="${poVal.jiab}"  datatype="d"  errormsg="请输入加班" name="orderMxList[${status.index}].jiab" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center">
                            <input name="orderMxList[${status.index}].type" value="${poVal.type}" type="hidden" />
                       </td>
                    </tr>
                </c:forEach>
            </c:if>--%>
        </tbody>

    </table>
</div>
<script type="text/javascript">
    function staffLook(indexflagWT){
        currentflagWT = indexflagWT;
        $("#chkInfoForStaff").click();
    }

</script>
