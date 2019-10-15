<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>通用Excel导入${controller_name}</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	<script type="text/javascript">

		function periodReply(uploadfile1){
			var index = layer.load(1, {
				skin:"layui-layer-sys1",
				shade: [0.1,'#fff'] //0.1透明度的白色背景
			});
			$.ajaxFileUpload({
				url:'${controller_name}.do?importExcel',
				type:"post",
				data:$("#formobj").serialize(),
				secureuri:false,			//一般设置为false
				//fileElementId:'file',		//文件id属性  <input type="file" id="upload" name="upload" />
				fileElementId : [uploadfile1],
				dataType:"JSON",
				success : function(msg) {
					var data = msg.substring(msg.indexOf('{'),msg.indexOf('}')+1);
					var d = $.parseJSON(data);
					if (d.sussess=="true") {
						tip("导入成功");
						W.document.location.reload(true);
					}else{
						tip("导入失败");
					}
					layer.close(index);
				}
			});
		}
	</script>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj"  dialog="true" usePlugin="password" layout="table" action="" tiptype="1" >
	<table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					文件路径:
				</label>
			</td>
			<td class="value">
				<input name="files" id="uploadfile1" style="width:300px;"  type="file"/>
			</td>
		</tr>

	</table>
	<div style="float: right;margin-top:210px;">
		<input class="btn" type="button" value="确定" onclick="periodReply('uploadfile1')" style="background:#18a689 none repeat scroll 0 0;height:30px;width:60px !important;border-radius:5px;color: #fff;" >
	</div>
</t:formvalid>
</body>
</html>
