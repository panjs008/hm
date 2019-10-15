<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flagWT = ${fn:length(workPriceEntityList)};
    var currentflagWTWT = ${fn:length(workPriceEntityList)};

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

        $("#add_jeecgOrderProduct_tableWT").find("[id='dck00']").attr("id","dck"+flagWT);
        $("#ck"+flagWT).val(flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='workNo00']").attr("id","workNo"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='realName00']").attr("id","realName"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='sex00']").attr("id","sex"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='hjV00']").attr("id","hjV"+flagWT);

        $("#add_jeecgOrderProduct_tableWT").find("[id='zcb00']").attr("id","zcb"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='jiab00']").attr("id","jiab"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='zjhj00']").attr("id","zjhj"+flagWT);

        $("#add_jeecgOrderProduct_tableWT").find("[id='zszcb00']").attr("id","zszcb"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='zszcbPrice00']").attr("id","zszcbPrice"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='zsjiab00']").attr("id","zsjiab"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='zsjiabPirce00']").attr("id","zsjiabPirce"+flagWT);

        $("#add_jeecgOrderProduct_tableWT").find("[id='type00']").attr("id","type"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='zz00']").attr("id","zz"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='yglb00']").attr("id","yglb"+flagWT);
        $("#add_jeecgOrderProduct_tableWT").find("[id='zshj00']").attr("id","zshj"+flagWT);

        resetTrNum('add_jeecgOrderProduct_tableWT');
        $("#zcb"+flagWT).attr("onkeyup","sethjV("+flagWT+")");
        $("#jiab"+flagWT).attr("onkeyup","sethjV("+flagWT+")");
        $("#zszcb"+flagWT).attr("onkeyup","sethjV0("+flagWT+")");
        $("#zszcbPrice"+flagWT).attr("onkeyup","sethjV0("+flagWT+")");
        $("#zsjiab"+flagWT).attr("onkeyup","sethjV0("+flagWT+")");
        $("#zsjiabPirce"+flagWT).attr("onkeyup","sethjV0("+flagWT+")");

        $("#orderMxListIDWT").val($("#mxtbWT").find("tr").length-1);
    });
    $('#delBtnWT').bind('click', function(){
        var chk_value =[];
        $("html,body").animate({scrollTop:400},1);
        $('input[name="dck"]:checked').each(function(){
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
    function sethjV0(ii){
        var a = parseFloat($("#zszcb"+ii).val())*parseFloat($("#zszcbPrice"+ii).val());
        var b = parseFloat($('#zsjiab'+ii).val())*parseFloat($('#zsjiabPirce'+ii).val())/60;
        var hjV = toDecimal((!isNaN(a) ? a:0)+(!isNaN(b) ? b:0));
        if(!isNaN(hjV)){
            $("#zshj"+ii).val(hjV);
        }
    }
    function sethjV(ii){
        var hjV = toDecimal(parseFloat($("#zcb"+ii).val())+parseFloat($('#jiab'+ii).val())/60*$("#hjV"+ii).val());
        if($('#jiab'+ii).val() == null || $('#jiab'+ii).val() ==''){
            hjV = toDecimal(parseFloat($("#zcb"+ii).val()));
        }
        if(!isNaN(hjV)){
            $("#zjhj"+ii).val(hjV);
        }
    }
    
</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_tableWT_template">
        <tr>
            <td align="center"><input style="width: 30px;" id="ck00" type="checkbox" name="dck" /></td>
            <td align="center" width="30"><span id="seqNumWT"></span></td>
            <td align="center">
                <input id="workNo00" nullmsg="请输入工号！" datatype="*"  errormsg="请输入工号" name="orderMxList[#index#].workNo" maxlength="100" type="hidden"
                       style="width: 86%;" ignore="ignore">
                <input id="realName00" nullmsg="请输入姓名！" datatype="*"  errormsg="请输入姓名" name="orderMxList[#index#].realName" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center">
                <select id="sex00" name="orderMxList[#index#].sex" style="width:86%;">
                    <option value="">选择</option>
                    <option value="01">男</option>
                    <option value="02">女</option>
                </select>
            </td>
            <td align="center"><input id="startTime00" nullmsg="请输入上班时间！"  datatype="*" class="Wdate" value="08::00" onClick="WdatePicker({dateFmt:'HH:mm'})" errormsg="请输入上班时间" name="orderMxList[#index#].startTime" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="endTime00" nullmsg="请输入下班时间！"  datatype="*"  class="Wdate" value="21::20" onClick="WdatePicker({dateFmt:'HH:mm'})" errormsg="请输入下班时间" name="orderMxList[#index#].endTime" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="zszcb00" nullmsg="请输入正常班！"  datatype="d" errormsg="请输入正常班" name="orderMxList[#index#].zszcb" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="zszcbPrice00" nullmsg="请输入单价！"  datatype="d"  errormsg="请输入单价" name="orderMxList[#index#].zszcbPrice" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="zsjiab00" nullmsg="请输入加班！"  datatype="d"  errormsg="请输入加班" name="orderMxList[#index#].zsjiab" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="zsjiabPirce00" nullmsg="请输入单价！"  datatype="d"  errormsg="请输入单价" name="orderMxList[#index#].zsjiabPirce" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="zshj00" nullmsg="请输入合计！"  datatype="d"  errormsg="请输入合计" name="orderMxList[#index#].zshj" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="zcb00" nullmsg="请输入正常班！"  datatype="d"  value="9" errormsg="请输入正常班" name="orderMxList[#index#].zcb" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="jiab00" nullmsg="请输入加班！"  datatype="d"  errormsg="请输入加班" name="orderMxList[#index#].jiab" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center">
                <input id="hjV00" type="hidden" />
                <input id="type00" type="hidden" name="orderMxList[#index#].type" />
                <input id="zz00" type="hidden" name="orderMxList[#index#].isZz" />
                <input id="yglb00" type="hidden" name="orderMxList[#index#].yglb" value="0"/>

                <input id="zjhj00" nullmsg="请输入合计！"  datatype="d" value="9" errormsg="请输入合计" name="orderMxList[#index#].zjhj" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
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
<input id="orderMxListIDWT" type="hidden" name="dataRowsValWork" value="${fn:length(workPriceEntityList)}"/>
<div class="table-c">
    <table id="mxtbWT" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center" rowspan="2" width="30" ></td>
            <td align="center" rowspan="2" width="30" >序号</td>
            <%--<td align="center" rowspan="2" width="80">工号</td>--%>
            <td align="center" rowspan="2" width="70">姓名</td>
            <td align="center"  rowspan="2" width="70">性别</td>
            <td  align="center" rowspan="2" width="50">上班时间</td>
            <td  align="center" rowspan="2" width="50">下班时间</td>
            <td align="center" colspan="5" width="120">计时工时</td>
            <td align="center" colspan="3" width="100">计件工时</td>
            </tr>
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td  align="center"  width="30">正常班</td>
            <td  align="center"  width="25">单价</td>
            <td  align="center"  width="25">加班</td>
            <td  align="center"  width="25">单价</td>
            <td  align="center"  width="30">合计</td>
            <td  align="center"  width="30">正常班</td>
            <td  align="center"  width="25">加班</td>
            <td  align="center"  width="30">合计</td>
        </tr>
        <tbody id="add_jeecgOrderProduct_tableWT">
            <c:if test="${fn:length(workPriceEntityList)  > 0 }">
                <c:forEach items="${workPriceEntityList}" var="poVal" varStatus="status">
                    <tr>
                        <td align="center"><input style="width: 40px;" type="checkbox" name="dck" value="${poVal.id}"/></td>
                        <td align="center" width="40">${status.index+1}</td>
                        <td align="center">
                            <input  nullmsg="请输入工号！" id="workNo${status.index}"  errormsg="请输入工号" value="${poVal.workNo}" name="orderMxList[${status.index}].workNo" maxlength="100" type="hidden"
                                    style="width: 86%;" ignore="ignore">
                            <input  nullmsg="请输入姓名！" id="realName${status.index}"  errormsg="请输入姓名" value="${poVal.realName}" name="orderMxList[${status.index}].realName" maxlength="100" type="text"
                                                   style="width: 86%;" ignore="ignore"></td>
                        <td align="center">
                            <select  id="sex${status.index}" name="orderMxList[${status.index}].sex" style="width:70px;">
                                <option value="">选择</option>
                                <option value="01" ${poVal.sex eq '01' ? 'selected':''}>男</option>
                                <option value="02" ${poVal.sex eq '02' ? 'selected':''}>女</option>
                            </select>
                        </td>
                        <td align="center"><input  nullmsg="请输入上班时间！"   errormsg="请输入上班时间" value="${poVal.startTime}"
                                                   class="Wdate" value="08::00" onClick="WdatePicker({dateFmt:'HH:mm'})" name="orderMxList[${status.index}].startTime" maxlength="100" type="text" style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input  nullmsg="请输入下班时间！"  errormsg="请输入下班时间" value="${poVal.endTime}"
                                                   class="Wdate" value="08::00" onClick="WdatePicker({dateFmt:'HH:mm'})" name="orderMxList[${status.index}].endTime" maxlength="100" type="text" style="width: 86%;" ignore="ignore"></td>

                        <td align="center"><input id="zszcb${status.index}" nullmsg="请输入正常班！" onkeyup="sethjV0(${status.index})" value="${poVal.zsZcb}"  datatype="d" errormsg="请输入正常班" name="orderMxList[${status.index}].zszcb" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input id="zszcbPrice${status.index}" nullmsg="请输入单价！" onkeyup="sethjV0(${status.index})" value="${poVal.zsZcbPrice}"   datatype="d"  errormsg="请输入单价" name="orderMxList[${status.index}].zszcbPrice" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input id="zsjiab${status.index}" nullmsg="请输入加班！" onkeyup="sethjV0(${status.index})" value="${poVal.zsJiab}"  datatype="d"  errormsg="请输入加班" name="orderMxList[${status.index}].zsjiab" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input id="zsjiabPirce${status.index}" nullmsg="请输入单价！" onkeyup="sethjV0(${status.index})" value="${poVal.zsJiabPrice}"   datatype="d"  errormsg="请输入单价" name="orderMxList[${status.index}].zsjiabPirce" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input id="zshj${status.index}" nullmsg="请输入合计！" value="${poVal.zshj}"  datatype="d"  errormsg="请输入合计" name="orderMxList[${status.index}].zshj" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input id="zcb${status.index}" nullmsg="请输入正常班！" onkeyup="sethjV(${status.index})" value="${poVal.zcb}"  datatype="d"  value="9" errormsg="请输入正常班" name="orderMxList[${status.index}].zcb" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center"><input id="jiab${status.index}" nullmsg="请输入加班！" onkeyup="sethjV(${status.index})" value="${poVal.jiab}"  datatype="d"  errormsg="请输入加班" name="orderMxList[${status.index}].jiab" maxlength="100" type="text"
                                                  style="width: 86%;" ignore="ignore"></td>
                        <td align="center">
                            <input id="hjV${status.index}" value="${poVal.isZz eq '1' ? '1.2':'1.02'}" type="hidden" />
                            <input name="orderMxList[${status.index}].type" value="${poVal.type}" type="hidden" />

                            <input id="zjhj${status.index}" nullmsg="请输入合计！"  datatype="d"  value="${(poVal.zjhj eq null && poVal.zjhj eq '') ? '9':poVal.zjhj}"  errormsg="请输入合计" name="orderMxList[${status.index}].zjhj" maxlength="100" type="text"
                                   style="width: 86%;" ignore="ignore"></td>
                    </tr>

                </c:forEach>
            </c:if>
        </tbody>

    </table>
</div>
<script type="text/javascript">
    function staffLook(indexflagWT){
        currentflagWT = indexflagWT;
        $("#chkInfoForStaff").click();
    }

    /*function returnToStaff(){
        var a1 =  $('#workNo').val().split(",");
        var a2 =  $('#realName').val().split(",");
        var a3 =  $('#sex').val().split(",");
        for(var i = 0 ; i < a1.length-1 ; i++){
            $('#addBtnWT').click();
        }
        for(var i = 0 ; i < a1.length ; i++){
            $('#workNo'+(flagWT-a1.length+i+1)).val(a1[i]);
            $('#realName'+(flagWT-a1.length+i+1)).val(a2[i]);
            $('#sex'+(flagWT-a1.length+i+1)).val(a3[i]);
        }
    }*/
</script>
