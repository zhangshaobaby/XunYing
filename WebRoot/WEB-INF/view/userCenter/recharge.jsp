<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="网站描述" />
		<meta name="keywords" content="关键字" />
		<title>1717tou.com</title>
	</head>
	<body>
		<!-- 头部内容开始 -->
		<jsp:include page="../common/head.jsp"></jsp:include>
		<!-- 头部内容结束 -->
       <jsp:include page="rechargeContent.jsp"></jsp:include>
		<!-- 底部开始 -->
		<jsp:include page="../common/foot.jsp"></jsp:include>
		<!-- 底部结束 -->
	</body>
</html>

