<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
  String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="${ctx}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/admincp121.css" rel="stylesheet" type="text/css" />
<title>1717tou.com</title>
<script src="${ctx }/js/jquery-1.7.js"></script>
<script>
	function resend(){
		$.post("${ctx}/auth/huifu/Loans",{id:${project.id},function(){
			alert("已重新发送");
		});
	}
</script>
</head>
<body>
	<div style="background:#FFFFFF;">
		<div class="admincp_r3">
			<div class="admincp_r3_a">
				<div class="admincp_r3_a1">打款管理</div>
			</div>
			<div class="admincp_r3_b">
				<div class="admincp_r3_b2">
					共${allCount }多少标  已成功${allCount-unCount }标   失败${unCount }标
					<a href="javascript:void(0)" onclick="resend()">重新发送</a>
				</div>
			</div>
		<div class="admincp_r5"><a href="#">更多>></a></div>
	</div>
</body>
</html>
