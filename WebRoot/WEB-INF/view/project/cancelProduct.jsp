<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>解冻列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="<%=path%>/js/jquery-1.7.js"></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=path%>/css/admincp3.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
var basepath="<%=path%>";
$(document).ready(function () {
if(${successTender}==${totalTender}){
//修改产品状态为撤销
var projectid="${projectid}";
var data="id="+projectid+"&state=4";
		jQuery.ajax({type:"POST", url:basepath + "/auth/project/updatePro", data:data, success:function (result) {
			 if (result==true) {
				$("#bdiv").append("已完成。更改佣金状态，请稍后。");
				//更改佣金状态为已撤销
				$.post(basepath + "/auth/brokerage/update",{id:projectid},function(data){
					if(data=="ok"){
						alert("佣金状态更改成功");
					}
				});
			} else {
				alert("修改产品状态出错");
			}
		}});
  
}else{
 $("#bdiv").append("<input type='button'  onclick='cancelPro()'  value='重试'>");
}
});
//撤销产品
	function cancelPro(){
	window.location.reload();
	}
</script>
	</head>

	<body  onunload="window.opener.location.reload();">
		<center>
			<div id="p3text"></div>
			<div class="xingwin">
			<div class="xingwin1">
				<span class="span1">总解冻标数：</span><span class="span2">${totalTender}</span>
			</div>
			<div class="xingwin2">
				<span class="span1">成功解冻标数：</span><span class="span2">${successTender}</span>
			</div>
			<div class="xingwin2">
				<span class="span1">失败解冻标数：</span><span class="span2">${errorTender}</span>
			</div>
			<div class="xingwin2">
				<span class="span1">等待反馈标数：</span><span class="span2">${waitTender}</span>
			</div>
			<div id="bdiv" class="xingwin2">
				
			</div>
		</center>
	</body>
</html>
