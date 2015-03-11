<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>菜单列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=path %>/css/layout.css" rel="stylesheet" type="text/css" />
	<Script src="<%=path%>/js/jquery-1.7.js"></Script>
    <script type="text/javascript" src="<%=path %>/js/base.js"></script>
  </head>
  <body>
  <div class="container_list_tit"></div>
 <table  class="container_list_table"  cellpadding="0" cellspacing="0" border="1" bordercolor="skyblue">
 <tr class="container_list_table_firstRow">
 <td class="container_list_table_checkbox"><input type="checkbox" onclick="checkAll(this,'checkbox')" /></td>
<td class="container_list_table_num">序号</td>
 <td>菜单编码</td>
 <td>菜单名称</td>
 <td>操作</td>
 </tr>
<c:forEach varStatus="i" var="menu" items="${page.result}">
<tr >
<td>
<input type="checkbox" name="checkbox" value="${ menu.id }" />
</td>
<td>${ i.count }</td>
<td><c:out value="${menu.menuCode}" /></td>
<td><c:out value="${menu.menuName}" /></td>
	<td>
		<input type="button" value="修改"  onclick="bizEdit('<%=path %>/auth/menu/editMenu','${ menu.id }')" >
		<input type="button" value="删除"  onclick="bizDel('<%=path %>/auth/menu/delMenu','${ menu.id }')">
						</td>
</tr>
</c:forEach>
 </table>
<div class="container_list_shop">
					<div class="container_list_shop_btn">
						<input onclick="bizEdit('<%=path %>/auth/menu/editMenu?parentMenu.id=${parentMenu.id }','')"   value="添加" type="button" >
						<input value="删除" type="button"  onclick="bizDelAll('<%=path %>/auth/menu/delMenu','checkbox')">
					</div>
					<div class="container_list_shop_fenye" id="container_shop_fenye" name="listPage" title="" cnName="列表分页">
						共 ${ page.totalRecord } 条记录
					　<c:if test="${ page.currPage == 1}">
					 首页&nbsp;上页
					 </c:if>
					   <c:if test="${ page.currPage>1}">
						<a href="javascript:nextPage('<%=path%>/auth/menu/listMenu.action','1')">首页</a>　
						<a href="javascript:nextPage('<%=path%>/auth/menu/listMenu.action','${ page.prePage}')" >上页</a> 
						</c:if>
						${ page.currPage }/${ page.totalPage } 
						<c:if test="${ page.currPage == page.totalPage}">
						下页&nbsp;尾页
						</c:if>
						<c:if test="${ page.currPage < page.totalPage}">
						<a href="javascript:nextPage('<%=path%>/auth/menu/listMenu.action','${ page.nextPage}')"  >下页</a>　
						<a href="javascript:nextPage('<%=path%>/auth/menu/listMenu.action','${ page.totalPage}')"  >尾页</a>
						</c:if>
						
						<input type="text" value="" id="goPage" />
						<a href="javascript:goPage('<%=path%>/auth/menu/listMenu.action','${ page.totalPage}')" onclick=""><input class="container_list_shop_fenye_go" type="button" value=">>"  /></a>
					</div>
				</div>
  </body>
</html>
