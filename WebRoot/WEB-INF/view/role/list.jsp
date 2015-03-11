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
    
    <title>角色列表</title>
    
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
 <td>角色编码</td>
 <td>角色名称</td>
 <td>操作</td>
 </tr>
<c:forEach varStatus="i" var="role" items="${list}">
<tr >
<td>
<input type="checkbox" name="checkbox" value="${ role.id }" />
</td>
<td>${ i.count }</td>
<td><c:out value="${role.roleCode}" /></td>
<td><c:out value="${role.roleName}" /></td>
	<td>
		<input type="button" value="修改"  onclick="bizEdit('<%=path %>/auth/role/editRole','${ role.id }')" >
		<input type="button" value="删除"  onclick="bizDel('<%=path %>/auth/role/delRole','${ role.id }')">
		<input type="button" value="编辑权限"  onclick="bizEdit('<%=path %>/auth/menu/selectMenu','${ role.id }')">
						</td>
</tr>
</c:forEach>
 </table>
<div class="container_list_shop">
					<div class="container_list_shop_btn">
						<input onclick="bizEdit('<%=path %>/auth/role/editRole','')"   value="添加" type="button" >
						<input value="删除" type="button"  onclick="bizDelAll('<%=path %>/auth/role/delRole','checkbox')">
					</div>
				</div>
  </body>
</html>
