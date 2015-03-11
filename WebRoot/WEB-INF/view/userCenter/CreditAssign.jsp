<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
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
  <form action="<%=path %>/creditAssign/add" method="post">
  <input type="hidden" name="creditAmt" value="${tender.transAmt}" />
  <input type="hidden" name="sellCustId" value="${sessionScope.user.huifuID}" />
  <input type="hidden" name="tender.id" value="${tender.id}" />
  <input type="hidden" name="diffDays" value="${diffDays}" />
 <table>
 <tr>
 <td>原投标本金</td>
 <td>${tender.transAmt}</td>
 </tr>
 <tr>
 <td>推荐转让价</td>
 <td>${defealtPrice}</td>
 </tr>
 <tr>
 <td>请输入转让金额：</td>
 <td><input  name="creditDealAmt"  /></td>
 </tr>
 </table>
 <input type="submit" value="确定"/>
  </form>
  </body>
</html>
