<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
    var flag = 0;
    var currentFlag = 0;

    $('#addBtn').linkbutton({
        iconCls: 'icon-add'
    });
    $('#delBtn').linkbutton({
        iconCls: 'icon-remove'
    });


    $('#addBtn').bind('click', function(){
        flag++;
        $("#seqNum").html(flag);
        var tr =  $("#add_jeecgOrderProduct_table_template tr").clone();
        $("#add_jeecgOrderProduct_table").append(tr);

        $("#add_jeecgOrderProduct_table").find("[id='ck00']").attr("id","ck"+flag);
        $("#ck"+flag).val(flag);
        $("#add_jeecgOrderProduct_table").find("[id='workNo00']").attr("id","workNo"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='realName00']").attr("id","realName"+flag);
        $("#add_jeecgOrderProduct_table").find("[id='sex00']").attr("id","sex"+flag);

        resetTrNum('add_jeecgOrderProduct_table');
        $("#workNo"+flag).attr("onclick","staffLook("+flag+")");
        $("#realName"+flag).attr("onclick","staffLook("+flag+")");

        $("#orderMxListID").val($("#mxtb").find("tr").length-1);
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
        $('#addBtn').click();
    });


</script>
<style>
    .table-c table{border-right:1px solid #ddd;border-bottom:1px solid #ddd}
    .table-c table td{border-left:1px solid #ddd;border-top:1px solid #ddd;height: 36px;}
</style>
<!-- 添加明细模版-->
<table style="width:100%;display: none;border: 1px;" cellpadding="0" cellspacing="2" border="0">
    <tbody id="add_jeecgOrderProduct_table_template">
        <tr>
            <td align="center"><input style="width: 30px;" id="ck00" type="checkbox" name="ck" /></td>
            <td align="center" width="30"><span id="seqNum"></span></td>
            <td align="center"><input id="workNo00" nullmsg="请输入工号！" datatype="*"  errormsg="请输入工号" name="orderMxList[#index#].workNo" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="realName00" nullmsg="请输入姓名！" datatype="*"  errormsg="请输入姓名" name="orderMxList[#index#].realName" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center">
                <select id="sex00" name="orderMxList[#index#].sex" style="width:70px;">
                    <option value="">选择</option>
                    <option value="01">男</option>
                    <option value="02">女</option>
                </select>
            </td>
            <td align="center"><input id="startTime00" nullmsg="请输入上班时间！"  datatype="*" class="Wdate" value="08::00" onClick="WdatePicker({dateFmt:'HH:mm'})" errormsg="请输入上班时间" name="orderMxList[#index#].startTime" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="endTime00" nullmsg="请输入下班时间！"  datatype="*"  class="Wdate" value="21::20" onClick="WdatePicker({dateFmt:'HH:mm'})" errormsg="请输入下班时间" name="orderMxList[#index#].endTime" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="zcb00" nullmsg="请输入正常班！"  datatype="d"  errormsg="请输入正常班" name="orderMxList[#index#].zcb" maxlength="100" type="text"
                                      style="width: 86%;" ignore="ignore"></td>
            <td align="center"><input id="jiab00" nullmsg="请输入加班！"  datatype="d"  errormsg="请输入加班" name="orderMxList[#index#].jiab" maxlength="100" type="text"
                                  style="width: 86%;" ignore="ignore"></td>
        </tr>
    </tbody>

</table>
<div style="display:none">
    <input id="workNo" name="workNo" type="text" />
    <input id="realName" name="realName" type="text" />
    <input id="sex" name="sex" type="text" />

    <t:choose  hiddenName="id"  hiddenid="id" url="hmStaffController.do?select&flag=0" name="hmStaffList" width="820px" height="500px"
               icon="icon-search" title="选择员工" textname="id,workNo,realName,sex" isclear="true" isInit="true"></t:choose>
</div>
<div style="padding: 3px; height: 25px; width:100%;margin-bottom:4px " class="datagrid-toolbar"><a id="addBtn" href="#"></a><a id="delBtn" href="#"></a></div>
<input id="orderMxListID" type="hidden" name="dataRowsVal" value=""/>
<div id="hsUnitDiv"></div>
<div class="table-c">
    <table id="mxtb" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#F8F8F8" style="height: 32px;" >
            <td align="center"  width="30" ><input type="checkbox" onclick="selectAll(this.checked)" /></td>
            <td align="center" width="30" >序号</td>
            <td align="center"  width="60">工号</td>
            <td align="center"  width="60">姓名</td>
            <td align="center"   width="70">性别</td>
            <td  align="center"  width="70">上班时间</td>
            <td  align="center"  width="70">下班时间</td>
            <td  align="center"  width="70">正常班</td>
            <td  align="center"  width="70">加班</td>
        </tr>
        <tbody id="add_jeecgOrderProduct_table">

        </tbody>

    </table>
</div>
<script type="text/javascript">
    function staffLook(indexFlag){
        currentFlag = indexFlag;
        $("#chkInfoForStaff").click();
    }

    function returnToStaff(){
        var a1 =  $('#workNo').val().split(",");
        var a2 =  $('#realName').val().split(",");
        var a3 =  $('#sex').val().split(",");
        for(var i = 0 ; i < a1.length-1 ; i++){
            $('#addBtn').click();
        }
        for(var i = 0 ; i < a1.length ; i++){
            $('#workNo'+(flag-a1.length+i+1)).val(a1[i]);
            $('#realName'+(flag-a1.length+i+1)).val(a2[i]);
            $('#sex'+(flag-a1.length+i+1)).val(a3[i]);
        }
    }
</script>
