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
    
    <title>分页</title>
    
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
   	<div  class="action_fenye">共 ${ page.totalRecord } 条记录
					　<c:if test="${ page.currPage == 1}">
					 首页&nbsp;上页
					 </c:if>
					   <c:if test="${ page.currPage>1}">
						<a href="javascript:nextPageToForm('1')">首页</a>　
						<a href="javascript:nextPageToForm('${ page.prePage}')" >上页</a> 
						</c:if>
						${ page.currPage }/${ page.totalPage } 
						<c:if test="${ page.currPage == page.totalPage}">
						下页&nbsp;尾页
						</c:if>
						<c:if test="${ page.currPage < page.totalPage}">
						<a href="javascript:nextPageToForm('${ page.nextPage}')"  >下页</a>　
						<a href="javascript:nextPageToForm('${ page.totalPage}')"  >尾页</a>
						</c:if>
						<input type="text"  value="" id="goPage" />
						<input type="button" value=">>"  onclick="goPageToForm('${ page.totalPage}')" />
				</div>
  </body>
</html>
