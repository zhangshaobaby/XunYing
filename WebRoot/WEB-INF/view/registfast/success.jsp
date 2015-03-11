<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel=stylesheet href=jquery.mobile-1.3.2.min.css>
<script src=jquery-1.8.3.js></script>
<script src=jquery.mobile-1.3.2.js></script>

</head>
<body>
<div data-role="page" id="pageone" >
  <div data-role="header">
    <h1>一起一起投-注册</h1>
  </div>
  <div data-role="content">
  <h1> <c:if test="${result=='0'}"> 
  		注册成功!
  		</c:if> 
  		<c:if test="${result!='0'}"> 
  		注册失败!
  		</c:if> 
  </h1>
  <c:if test="${result=='0'}"> 
  		<a href="http://download.1717tou.com" data-role="button" data-inline="true">点击进入下载页面</a>
  </c:if>
  
  </div>
  <div data-role="footer">
	 <h1>©2014一起一起投</h1>
  </div>
</div> 

</body>
</html>
