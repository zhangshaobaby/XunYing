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

		<title>操作员编辑</title>

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
		<form action="<%=path%>/auth/operator/saveOperator" id="dataform" method="post">
			<!-- 隐藏字段功能信息 -->
			<input type="hidden" name="id" value="${operator.id }" />
				<div class="edit_editinfo_property">
				<div class="edit_editinfo_property_lable">
					登录名：
				</div>
				<div class="edit_editinfo_property_value">
					<input type="text" name="userid" value="${ operator.userid}" />
				</div>
			</div>
					<div class="edit_editinfo_property">
				<div class="edit_editinfo_property_lable">
					登录密码：
				</div>
				<div class="edit_editinfo_property_value">
					<input type="text" name="pwd" value="${ operator.pwd}" />
				</div>
			</div>
			<div class="edit_editinfo_property">
				<div class="edit_editinfo_property_lable">
					操作员名称：
				</div>
				<div class="edit_editinfo_property_value">
					<input type="text" name="operatorname" value="${ operator.operatorname}" />
				</div>
			</div>
			<div class="edit_editinfo_property">
				<div class="edit_editinfo_property_lable">
					操作员描述：
				</div>
				<div class="edit_editinfo_property_value">
					<input type="text" name="operatordesc" value="${ operator.operatordesc}" />
				</div>
			</div>
			
			<div class="container_search_btn" id="container_search_btn"
				name="searchTitle" title="">
				<input type="button" value="保存" onClick="submitForm()" />
			</div>
		</form>
	</body>
</html>
