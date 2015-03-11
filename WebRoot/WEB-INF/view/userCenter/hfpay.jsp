<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>开通第三方支付</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <jsp:include page="../common/head.jsp"></jsp:include>
  <div style="text-align: center;line-height: 280;height: 343px;margin: 0px auto;"><a  href="<%=path%>/huifu/regist?username=${sessionScope.user.username}"><img style="vertical-align:middle;margin-top: 95px;" src="<%=path %>/images/hfpay_03.png" /></a></div>
  <jsp:include page="../common/foot.jsp"></jsp:include>
  </body>
</html>
