<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>菜单编辑</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link href="<%=path%>/css/layout.css" rel="stylesheet"
			type="text/css" />
			<Script src="<%=path%>/js/jquery-1.7.js"></Script>
		<script type="text/javascript" src="<%=path %>/js/base.js"></script>
	</head>

	<body>
		<form action="<%=path%>/auth/menu/saveMenu" id="dataform" method="post">
			<!-- 隐藏字段功能信息 -->
			<input type="hidden" name="id" value="${menu.id }" />
				<!--父菜单 -->
			<input type="hidden" name="parentMenu.id" value="${menu.parentMenu.id }" />
			<div class="edit_editinfo_property">
				<div class="edit_editinfo_property_lable">
					菜单编码：
				</div>
				<div class="edit_editinfo_property_value">
					<input type="text" name="menuCode" value="${ menu.menuCode}"
						class="easyui-validatebox" required="true"
						validType="length[1,255]" missingMessage="功能名称不能为空!" />
					<span>*</span>
				</div>
			</div>
			<div class="edit_editinfo_property">
				<div class="edit_editinfo_property_lable">
					菜单名称：
				</div>
				<div class="edit_editinfo_property_value">
					<input type="text" name="menuName" value="${ menu.menuName}" />
				</div>
			</div>
			<div class="edit_editinfo_property">
				<div class="edit_editinfo_property_lable">
					菜单显示名称：
				</div>
				<div class="edit_editinfo_property_value">
					<input type="text" name="menuLabel" value="${ menu.menuLabel}" />
				</div>
			</div>
				<div class="edit_editinfo_property">
				<div class="edit_editinfo_property_lable">
					执行路径：
				</div>
				<div class="edit_editinfo_property_value">
					<input type="text" name="url" value="${ menu.url}" />
				</div>
			</div>
			<div class="edit_editinfo_property">
				<div class="edit_editinfo_property_lable">
					菜单样式：
				</div>
				<div class="edit_editinfo_property_value">
					<input type="text" name="menuClass" value="${ menu.menuClass}" />
				</div>
			</div>
			<div class="container_search_btn" id="container_search_btn"
				name="searchTitle" title="">
				<input type="button" value="保存" onClick="submitForm()" />
			</div>
		</form>
	</body>
</html>
