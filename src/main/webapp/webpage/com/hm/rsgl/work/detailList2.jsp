<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flag = ${fn:length(workEntityList)};
    var currentFlag = ${fn:length(workEntityList)};


    $('#addBtn').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn').linkbutton({
        iconCls: 'icon-remove'
    });
    $('#copyBtn').linkbutton({
        iconCls: 'icon-save'
    });

    function appendTr(){
        flag++;
        $("#seqNum").html(flag);
        var tr =  $("#add_jeecgOrderProduct_table_template tr").clone();
        $("#add_jeecgOrderProduct_table").append(tr);
        <c:forEach var="list" items="${categoryEntities}" varStatus="status">
            $("#add_jeecgOrderProduct_table").find("[id='${list.code}00']").attr("id","${list.code}"+flag);
            <c:if test="${list.name eq '标准单价'}">
                $("#${list.code}"+flag).attr("onkeyup","setWeightAndPrice("+flag+")");
            </c:if>
            <c:if test="${status.index eq 0}">
                $("#${list.code}"+flag).attr("onclick","projectLook("+flag+")");
            </c:if>
        </c:forEach>

        $("#add_jeecgOrderProduct_table").find("[id='ck00']").attr("id","ck"+flag);
        $("#ck"+flag).val(flag);

        $("#add_jeecgOrderProduct_table").find("[id='total00']").attr("id","total"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='hsUnitValue00']").attr("id","hsUnitValue"+flag);

        $("#add_jeecgOrderProduct_table").find("[id='unit00']").attr("id","unit"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='weight00']").attr("id","weight"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='hjWeight00']").attr("id","hjWeight"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='hjMoney00']").attr("id","hjMoney"+flag);

        resetTrNum('add_jeecgOrderProduct_table');
        $("#unit"+flag).attr("onchange","setWeightAndPrice("+flag+")");
        $("#total"+flag).attr("onkeyup","setWeightAndPrice("+flag+")");
        $("#weight"+flag).attr("onkeyup","setWeightAndPrice("+flag+")");

        $("#orderMxListID").val($("#mxtb").find("tr").length-1);
    }

    $('#copyBtn').bind('click', function(){
        if($("input[type='checkbox']").is(':checked')){
            $('input[name="ck"]:checked').each(function(){
                var posi = $(this).val();
                appendTr();
                <c:forEach var="list" items="${categoryEntities}" varStatus="status">
                    $("#${list.code}"+flag).val($("#${list.code}"+posi).val());
                </c:forEach>
                $("#total"+flag).val($("#total"+posi).val());
                $("#unit"+flag).val($("#unit"+posi).val());
                $("#weight"+flag).val($("#weight"+posi).val());
                $("#hjWeight"+flag).val($("#hjWeight"+posi).val());
                $("#hjMoney"+flag).val($("#hjMoney"+posi).val());
                setUnit(flag);
            });
        }else{
            tip("请勾选工艺工序");
        }
    });


    $('#addBtn').bind('click', function(){
        appendTr();
    });
    $('#delBtn').bind('click', function(){
        var chk_value =[];
        $("html,body").animate({scrollTop:400},1);
        $('input[name="ck"]:checked').each(function(){
            chk_value.push($(this).val());
        });

        $("#add_jeecgOrderProduct_table").find("input:checked").parent().parent().remove();
        resetTrNum('add_jeecgOrderProduct_table');
        $("#orderMxListID").val($("#mxtb").find("tr").length-1);


    });
    $(document).ready(function(){
        $(".datagrid-toolbar").parent().css("width","auto");
        //将表格的表头固定
        $("#jeecgOrderProduct_table").createhftable({
            height:'200px',
            width:'auto',
            fixFooter:false
        });
        <c:if test="${param.orderId eq null || param.orderId eq ''}">
            $('#addBtn').click();
        </c:if>
    });
    function setUnit(ii){
        <c:forEach var="list" items="${categoryEntities}" varStatus="status">
            <c:if test="${list.name eq '单位'}">
                var hsVal = 0;
                if($("#${list.code}"+ii).val() == '吨'){
                    if($("#unit"+ii).val() == '斤'){
                        hsVal = 0.0005;
                    }else if($("#unit"+ii).val() == '公斤'){
                        hsVal = 0.001;
                    }else if($("#unit"+ii).val() == '吨'){
                        hsVal = 1;
                    }else if($("#unit"+ii).val() == '千克'){
                        hsVal = 0.001;
                    }else if($("#unit"+ii).val() == '克'){
                        hsVal = 0.000001;
                    }
                }else if($("#${list.code}"+ii).val() == '公斤'){
                    if($("#unit"+ii).val() == '吨'){
                        hsVal = 1000;
                    }else if($("#unit"+ii).val() == '公斤'){
                        hsVal = 1;
                    }else if($("#unit"+ii).val() == '斤'){
                        hsVal = 0.5;
                    }else if($("#unit"+ii).val() == '千克'){
                        hsVal = 1;
                    }else if($("#unit"+ii).val() == '克'){
                        hsVal = 0.001;
                    }
                }else if($("#${list.code}"+ii).val() == '斤'){
                    if($("#unit"+ii).val() == '吨'){
                        hsVal = 2000;
                    }else if($("#unit"+ii).val() == '公斤'){
                        hsVal = 2;
                    }else if($("#unit"+ii).val() == '斤'){
                        hsVal = 1;
                    }else if($("#unit"+ii).val() == '千克'){
                        hsVal = 2;
                    }else if($("#unit"+ii).val() == '克'){
                        hsVal = 0.002;
                    }
                }else if($("#${list.code}"+ii).val() == '千克'){
                    if($("#unit"+ii).val() == '吨'){
                        hsVal = 1000;
                    }else if($("#unit"+ii).val() == '公斤'){
                        hsVal = 1;
                    }else if($("#unit"+ii).val() == '斤'){
                        hsVal = 0.5;
                    }else if($("#unit"+ii).val() == '千克'){
                        hsVal = 1;
                    }else if($("#unit"+ii).val() == '克'){
                        hsVal = 0.001;
                    }
                }else if($("#${list.code}"+ii).val() == '克'){
                    if($("#unit"+ii).val() == '吨'){
                        hsVal = 1000000;
                    }else if($("#unit"+ii).val() == '公斤'){
                        hsVal = 1000;
                    }else if($("#unit"+ii).val() == '斤'){
                        hsVal = 500;
                    }else if($("#unit"+ii).val() == '千克'){
                        hsVal = 1000;
                    }else if($("#unit"+ii).val() == '克'){
                        hsVal = 1;
                    }
                }else{
                    hsVal = 1;
                }
                $("#hsUnitValue"+ii).val(hsVal);
//                $("#hsUnitDiv").html("<input type='hidden' id='hsUnitValue"+ii+"' value='"+hsVal+"'/>")
                //setWeightAndPrice(ii);
        </c:if>
        </c:forEach>

    }
    function setWeightAndPrice(ii){
        setUnit(ii);
        var weightV = toDecimal3(parseFloat($("#weight"+ii).val())*parseFloat($('#hsUnitValue'+ii).val())*parseFloat($("#total"+ii).val()));
        if(!isNaN(weightV)){
            $("#hjWeight"+ii).val(weightV);
            <c:forEach var="list" items="${categoryEntities}" varStatus="status">
                <c:if test="${list.name eq '标准单价'}">
                    var priceV = toDecimal(parseFloat($("#${list.code}"+ii).val())*weightV);
                    if(!isNaN(priceV)){
                        $("#hjMoney"+ii).val(priceV);
                    }
                </c:if>
            </c:forEach>
        }
    }

</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_table_template">
    <tr>
        <td align="center"><input style="width: 30px;" id="ck00" type="checkbox" name="ck" value="0"/></td>
        <td align="center" width="40"><span id="seqNum"></span></td>
        <c:forEach var="list" items="${categoryEntities}" varStatus="status">
            <td align="center" >
                <input id="${list.code}00" nullmsg="请输入${list.name}！" datatype="${list.column_type eq '2' ? 'd':'*'}"  errormsg="请输入${list.name}" name="orderMxList[#index#].${list.code}" maxlength="100" type="text"
                       style="width: ${(list.column_type eq '0' || list.column_type eq '2') ? 86:94}%;" ignore="ignore"/>
            </td>
        </c:forEach>
        <td align="center"><input id="total00" nullmsg="请输入数量！" datatype="d"  errormsg="请输入数值" name="orderMxList[#index#].total" maxlength="100" type="text"
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center">
            <input id="hsUnitValue00" type="hidden" />
            <select id="unit00" name="orderMxList[#index#].unit" style="width:70px;">
                <option value="">选择</option>
                <option value="吨">吨</option>
                <option value="公斤">公斤</option>
                <option value="斤">斤</option>
                <option value="千克">千克</option>
                <option value="克">克</option>
            </select>
            <%--<input id="unit00" nullmsg="请输入单位！"  datatype="*"  errormsg="请输入单位" name="orderMxList[#index#].unit" maxlength="100" type="text"
                                  style="width: 86%;" ignore="ignore">--%>
        </td>
        <td align="center"><input id="weight00" nullmsg="请输入每件重量！"  datatype="d"  errormsg="请输入每件重量" name="orderMxList[#index#].weight" maxlength="100" type="text"
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="hjWeight00" nullmsg="请输入合计重量！"  datatype="d"  errormsg="请输入合计重量" name="orderMxList[#index#].hjWeight" maxlength="100" type="text"
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="hjMoney00" nullmsg="请输入合计金额！"  datatype="d"  errormsg="请输入合计金额" name="orderMxList[#index#].hjMoney" maxlength="100" type="text"
                                  style="width: 86%;" ignore="ignore"></td>
    </tr>
    </tbody>

</table>
<div style="display:none">
    <c:forEach var="list" items="${categoryEntities}" varStatus="status">
        <input id="${list.code}" name="${list.code}" type="text"/>
    </c:forEach>

    <input id="id" name="id" type="text" />
    <t:choose  hiddenName="id"  hiddenid="id" url="hmProjectController.do?select" name="projectList" width="820px" height="500px"
               icon="icon-search" title="选择工序工艺" textname="id,${columns}" isclear="true" isInit="true"></t:choose>
</div>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtn" href="#"></a><a id="copyBtn" href="#"></a><a id="delBtn" href="#"></a></div>
<%--<table border="0" cellpadding="2" cellspacing="0" id="jeecgOrderProduct_table">--%>
<input id="orderMxListID" type="hidden" name="dataRowsVal" value="${fn:length(workEntityList)}"/>
<div id="hsUnitDiv"></div>
<div class="table-c">
    <table id="mxtb" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="30" ><input type="checkbox" onclick="selectAll(this.checked)" /></td>
            <td align="center" width="30" >序号</td>
            <c:forEach var="list" items="${categoryEntities}" varStatus="status">
                <td  align="center"  width="${(list.column_type eq '4') ? '220':((list.column_type eq '0' || list.column_type eq '2') || list.column_type eq '2') ? '60':'200'}">${list.name}</td>
            </c:forEach>
            <td align="center"  width="60">数量</td>
            <td align="center"   width="70">手填单位</td>
            <td  align="center"  width="70">每件重量</td>
            <td  align="center"  width="70">合计重量</td>
            <td  align="center"  width="70">合计金额</td>

        </tr>

        <tbody id="add_jeecgOrderProduct_table">
        <c:if test="${fn:length(workEntityList)  > 0 }">
            <c:forEach items="${workEntityList}"  var="poVal" varStatus="status">
                <tr>
                    <td align="center"><input style="width: 40px;" id="ck${status.index}" type="checkbox" name="ck" value="${status.index}"/></td>
                    <td align="center" width="40">${status.index+1}</td>

                    <c:forEach var="list" items="${categoryEntities}" varStatus="status1">
                        <td align="center" >
                            <c:if test="${list.name eq '项目名称'}">
                                <input id="${list.code}${status.index}" nullmsg="请输入${list.name}！" datatype="${list.column_type eq '2' ? 'd':'*'}"  errormsg="请输入${list.name}" name="orderMxList[${status.index}].${list.code}" maxlength="100" type="text"
                                       style="width: ${(list.column_type eq '0' || list.column_type eq '2') ? 86:94}%;" value="${poVal.xmmc}" ignore="ignore"/>
                            </c:if>
                            <c:if test="${list.name eq '工艺流程'}">
                                <input id="${list.code}${status.index}" nullmsg="请输入${list.name}！" datatype="${list.column_type eq '2' ? 'd':'*'}"  errormsg="请输入${list.name}" name="orderMxList[${status.index}].${list.code}" maxlength="100" type="text"
                                       style="width: ${(list.column_type eq '0' || list.column_type eq '2') ? 86:94}%;" value="${poVal.gylc}" ignore="ignore"/>
                            </c:if>
                            <c:if test="${list.name eq '规格分类'}">
                                <input id="${list.code}${status.index}" nullmsg="请输入${list.name}！" datatype="${list.column_type eq '2' ? 'd':'*'}"  errormsg="请输入${list.name}" name="orderMxList[${status.index}].${list.code}" maxlength="100" type="text"
                                       style="width: ${(list.column_type eq '0' || list.column_type eq '2') ? 86:94}%;" value="${poVal.ggfl}" ignore="ignore"/>
                            </c:if>
                            <c:if test="${list.name eq '单位'}">
                                <input id="${list.code}${status.index}" nullmsg="请输入${list.name}！" datatype="${list.column_type eq '2' ? 'd':'*'}"  errormsg="请输入${list.name}" name="orderMxList[${status.index}].${list.code}" maxlength="100" type="text"
                                       style="width: ${(list.column_type eq '0' || list.column_type eq '2') ? 86:94}%;" value="${poVal.unit}" ignore="ignore"/>
                            </c:if>
                            <c:if test="${list.name eq '标准单价'}">
                                <input id="${list.code}${status.index}" onkeyup="setWeightAndPrice(${status.index});" nullmsg="请输入${list.name}！" datatype="${list.column_type eq '2' ? 'd':'*'}"  errormsg="请输入${list.name}" name="orderMxList[${status.index}].${list.code}" maxlength="100" type="text"
                                       style="width: ${(list.column_type eq '0' || list.column_type eq '2') ? 86:94}%;" value="${poVal.price}" ignore="ignore"/>
                            </c:if>
                            <c:if test="${list.name eq '备注'}">
                                <input id="${list.code}${status.index}" nullmsg="请输入${list.name}！" datatype="${list.column_type eq '2' ? 'd':'*'}"  errormsg="请输入${list.name}" name="orderMxList[${status.index}].${list.code}" maxlength="100" type="text"
                                       style="width: ${(list.column_type eq '0' || list.column_type eq '2') ? 86:94}%;" value="" ignore="ignore"/>
                            </c:if>
                        </td>
                    </c:forEach>
                    <td align="center"><input id="total${status.index}" nullmsg="请输入数量！" onkeyup="setWeightAndPrice(${status.index});" value="${poVal.total}" datatype="d"  errormsg="请输入数值" name="orderMxList[${status.index}].total" maxlength="100" type="text"
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center">
                        <input id="hsUnitValue${status.index}" type="hidden"/>
                        <select id="unit${status.index}" name="orderMxList[${status.index}].unit" onchange="setWeightAndPrice(${status.index});" style="width:70px;">
                            <option value="">选择</option>
                            <option value="吨" ${poVal.stNit eq '吨' ? 'selected':''}>吨</option>
                            <option value="公斤" ${poVal.stNit eq '公斤' ? 'selected':''}>公斤</option>
                            <option value="斤" ${poVal.stNit eq '斤' ? 'selected':''}>斤</option>
                            <option value="千克" ${poVal.stNit eq '千克' ? 'selected':''}>千克</option>
                            <option value="克" ${poVal.stNit eq '克' ? 'selected':''}>克</option>
                        </select>
                        <script>
                            setUnit(${status.index});
                        </script>
                    </td>
                    <td align="center"><input id="weight${status.index}" nullmsg="请输入每件重量！" onkeyup="setWeightAndPrice(${status.index});" value="${poVal.weight}" datatype="d"  errormsg="请输入每件重量" name="orderMxList[${status.index}].weight" maxlength="100" type="text"
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input id="hjWeight${status.index}" nullmsg="请输入合计重量！" value="${poVal.hjWeight}" datatype="d"  errormsg="请输入合计重量" name="orderMxList[${status.index}].hjWeight" maxlength="100" type="text"
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input id="hjMoney${status.index}" nullmsg="请输入合计金额！" value="${poVal.hjMoney}" datatype="d"  errormsg="请输入合计金额" name="orderMxList[${status.index}].hjMoney" maxlength="100" type="text"
                                              style="width: 86%;" ignore="ignore"></td>
                </tr>

            </c:forEach>
        </c:if>
        </tbody>

    </table>
</div>
<script type="text/javascript">
    function projectLook(indexFlag){
        currentFlag = indexFlag;
        $("#chkInfoForProject").click();
    }

    function returnToProject(){

        <c:forEach var="list" items="${categoryEntities}" varStatus="status">
            var a1 =  $('#${list.code}').val().split(",");

            <c:if test="${status.index eq 0}">
                if(flag == 1){
                    $("#add_jeecgOrderProduct_table").html("");
                }
                for(var i = 0 ; i < a1.length ; i++){
                    $('#addBtn').click();
                }
            </c:if>
            for(var i = 0 ; i < a1.length ; i++){
                $('#${list.code}'+(flag-a1.length+i+1)).val(a1[i]);
            }
        </c:forEach>

    }
</script>
