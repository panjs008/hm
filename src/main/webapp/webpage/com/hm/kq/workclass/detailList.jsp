<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flag = ${fn:length(hmWorkClassDetailEntityList)};
    var currentFlag = ${fn:length(hmWorkClassDetailEntityList)};

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
        $("#seqNum").html(flag+parseInt($("#listSize").val()));
        var tr =  $("#add_jeecgOrderProduct_table_template tr").clone();
        $("#add_jeecgOrderProduct_table").append(tr);
        $("#add_jeecgOrderProduct_table").find("[id='workSTime00']").attr("id","workSTime"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='workETime00']").attr("id","workETime"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='longTime00']").attr("id","longTime"+flag);

        resetTrNum('add_jeecgOrderProduct_table');
        $("#orderMxListID").val($("#mxtb").find("tr").length-1);
    }



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
        <c:if test="${param.workClassId eq null || param.workClassId eq ''}">
            $('#addBtn').click();
        </c:if>
    });

    function intervalTime0(){
        var startTime = $("#workSTime"+flag).val();
        var endTime = $("#workETime"+flag).val();

        var date1 = new Date("2016/01/01 "+startTime+":00");  //开始时间
        var date2 = new Date("2016/01/01 "+endTime+":00");   //结束时间

        var date3 = date2.getTime() - date1.getTime();  //时间差的毫秒数
        //计算出小时数
        var leave1 = date3 % (24 * 3600 * 1000);    //计算天数后剩余的毫秒数
        var hours = Math.floor(leave1 / (3600 * 1000));
        //计算相差分钟数
        var leave2 = leave1 % (3600 * 1000);        //计算小时数后剩余的毫秒数
        var minutes = Math.floor(leave2 / (60 * 1000));
        $("#longTime"+flag).val(hours+toDecimal2(minutes/60));
    }

    function intervalTime(index){
        var startTime = $("#workSTime"+index).val();
        var endTime = $("#workETime"+index).val();

        var date1 = new Date("2016/01/01 "+startTime+":00");  //开始时间
        var date2 = new Date("2016/01/01 "+endTime+":00");   //结束时间

        var date3 = date2.getTime() - date1.getTime();  //时间差的毫秒数
        //计算出小时数
        var leave1 = date3 % (24 * 3600 * 1000);    //计算天数后剩余的毫秒数
        var hours = Math.floor(leave1 / (3600 * 1000));
        //计算相差分钟数
        var leave2 = leave1 % (3600 * 1000);        //计算小时数后剩余的毫秒数
        var minutes = Math.floor(leave2 / (60 * 1000));
        $("#longTime"+index).val(hours+toDecimal2(minutes/60));
       /* //计算出相差天数
        var days = Math.floor(date3 / (24 * 3600 * 1000));

        //计算相差秒数
        var leave3 = leave2 % (60 * 1000);      //计算分钟数后剩余的毫秒数
        var seconds = Math.round(leave3 / 1000);
        alert(" 用时： " + days + "天 " + hours + "小时 " + minutes + " 分钟" + seconds + " 秒")*/
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
        <td align="center"><input style="width: 25px;" id="ck00" type="checkbox" name="ck" value="0"/></td>
        <td align="center" width="40"><span id="seqNum"></span></td>
        <td align="center"><input id="workTime00" nullmsg="请输入时间！"  datatype="*" class="Wdate" value="08:00" onClick="WdatePicker({dateFmt:'HH:mm'})" errormsg="请输入时间" name="orderMxList[#index#].workTime" maxlength="100" type="text"
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="stTime00" nullmsg="请输入开始！"  datatype="*" class="Wdate" value="08:00" onClick="WdatePicker({dateFmt:'HH:mm'})" errormsg="请输入开始" name="orderMxList[#index#].stTime" maxlength="100" type="text"
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="edTime00" nullmsg="请输入结束！"  datatype="*" class="Wdate" value="12:00" onClick="WdatePicker({dateFmt:'HH:mm'})" errormsg="请输入结束" name="orderMxList[#index#].edTime" maxlength="100" type="text"
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center">
            <select id="late00" name="orderMxList[#index#].late" style="width:70px;">
                <option value="">选择</option>
                <option value="0">迟到</option>
                <option value="1">早退</option>
                <option value="2">不计费</option>
            </select>
        </td>
        <td align="center"><input id="workSTime00" nullmsg="请输入上班时间！"  datatype="*" class="Wdate" value="08:00" onClick="WdatePicker({dateFmt:'HH:mm'})" errormsg="请输入上班时间" name="orderMxList[#index#].workSTime" maxlength="100" type="text"
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="workETime00" nullmsg="请输入下班时间！"  datatype="*" class="Wdate" value="12:00" onClick="WdatePicker({dateFmt:'HH:mm',onpicked:function(){intervalTime0()}})" errormsg="请输入下班时间" name="orderMxList[#index#].workETime" maxlength="100" type="text"
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center"><input id="longTime00" nullmsg="请输入时长！"  datatype="d"  errormsg="请输入时长" name="orderMxList[#index#].longTime" maxlength="100" type="text"
                                  style="width: 86%;" ignore="ignore"></td>
        <td align="center">
            <select  name="orderMxList[#index#].workContent" style="width:70px;">
                <option value="0">工作</option>
                <option value="1">休息</option>
            </select>
        </td>
        <td align="center">
            <select  name="orderMxList[#index#].workType" style="width:70px;">
                <option value="0">上班</option>
                <option value="1">下班</option>
                <option value="2">不计费</option>
            </select>
        </td>
    </tr>
    </tbody>

</table>

<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtn" href="#"></a><a id="delBtn" href="#"></a></div>
<input id="listSize" type="hidden" value="${fn:length(hmWorkClassDetailEntityList)}"/>
<input id="orderMxListID" type="hidden" name="dataRowsVal" value="${fn:length(hmWorkClassDetailEntityList)}"/>
<div id="hsUnitDiv"></div>
<div class="table-c">
    <table id="mxtb" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="25"  ><input type="checkbox" onclick="selectAll(this.checked)"/></td>
            <td align="center" width="30" >序号</td>
            <td align="center"  width="40" >时间</td>
            <td align="center"   width="50">开始</td>
            <td align="center"   width="50">结束</td>
            <td  align="center"  width="60" >迟到早退</td>
            <td align="center"   width="50">上班</td>
            <td align="center"   width="50">下班</td>
            <td  align="center"  width="50" >时长</td>
            <td  align="center"  width="50" >内容</td>
            <td  align="center"  width="50" >打卡类型</td>
        </tr>

        <tbody id="add_jeecgOrderProduct_table">
            <c:forEach items="${hmWorkClassDetailEntityList}" var="poVal" varStatus="status">
                <tr>
                    <td align="center"><input style="width: 40px;" type="checkbox" name="ck" value="${poVal.id}"/>
                    </td>
                    <td align="center" width="40">${status.index+1}</td>
                    <td align="center"><input id="workTime${status.index}" nullmsg="请输入时间！"  datatype="*" class="Wdate" value="${poVal.workTime}" onClick="WdatePicker({dateFmt:'HH:mm'})" errormsg="请输入时间" name="orderMxList[${status.index}].workTime" maxlength="100" type="text"
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入开始！"  datatype="*" class="Wdate" value="${poVal.stTime}" onClick="WdatePicker({dateFmt:'HH:mm'})" errormsg="请输入开始" name="orderMxList[${status.index}].stTime" maxlength="100" type="text"
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input  nullmsg="请输入结束！"  datatype="*" class="Wdate" value="${poVal.edTime}" onClick="WdatePicker({dateFmt:'HH:mm'})" errormsg="请输入结束" name="orderMxList[${status.index}].edTime" maxlength="100" type="text"
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center">
                        <select name="orderMxList[${status.index}].late" style="width:70px;">
                            <option value="">选择</option>
                            <option value="0" ${poVal.late eq '0' ? 'selected':''}>迟到</option>
                            <option value="1" ${poVal.late eq '1' ? 'selected':''}>早退</option>
                            <option value="2" ${poVal.late eq '2' ? 'selected':''}>不计费</option>

                        </select>
                    </td>
                    <td align="center"><input id="workSTime${status.index}" nullmsg="请输入上班时间！"  datatype="*" class="Wdate" value="${poVal.workSTime}"  onClick="WdatePicker({dateFmt:'HH:mm'})" errormsg="请输入上班时间" name="orderMxList[${status.index}].workSTime" maxlength="100" type="text"
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input id="workETime${status.index}" nullmsg="请输入下班时间！"  datatype="*" class="Wdate" value="${poVal.workETime}"  onClick="WdatePicker({dateFmt:'HH:mm',onpicked:function(){intervalTime(${status.index})}})" errormsg="请输入下班时间" name="orderMxList[${status.index}].workETime" maxlength="100" type="text"
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center"><input id="longTime${status.index}" nullmsg="请输入时长！"  datatype="d"  errormsg="请输入时长" value="${poVal.longTime}"  name="orderMxList[${status.index}].longTime" maxlength="100" type="text"
                                              style="width: 86%;" ignore="ignore"></td>
                    <td align="center">
                        <select  name="orderMxList[${status.index}].workContent" style="width:70px;">
                            <option value="0" ${poVal.workContent eq '0' ? 'selected':''}>工作</option>
                            <option value="1" ${poVal.workContent eq '1' ? 'selected':''}>休息</option>
                        </select>
                    </td>
                    <td align="center">
                        <select  name="orderMxList[${status.index}].workType" style="width:70px;">
                            <option value="0" ${poVal.workType eq '0' ? 'selected':''}>上班</option>
                            <option value="1" ${poVal.workType eq '1' ? 'selected':''}>下班</option>
                            <option value="2" ${poVal.workType eq '2' ? 'selected':''}>不计费</option>
                        </select>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

