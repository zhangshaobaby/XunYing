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
<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/admincp101.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/dateTimePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path %>/js/ajaxupload.3.6.js"></script>
<script type="text/javascript" src="<%=path %>/js/formVali.js"></script>
<script type="text/javascript" src="<%=path%>/js/dateTimePicker/WdatePicker.js"></script>
<title>1717tou.com</title>
<script>
	$(document).ready(function(){

    });
</script>
</head>

<body>
	<div style="background:#FFFFFF;">
		<div class="admincp_r1">
			<span>屌丝宝动态日利率管理</span>
		</div>
		<form name="form_admincp" method="post" action="${ctx}/auth/dynamicRate/add"  onsubmit="return subForm();">
		<input type="hidden" name="id" value="${model.id }"/>
		<div class="admincp_r2">
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">日期</div>
				<div class="admincp_r2_name2"><input type="text" name="startTime" value="${model.startTime }"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly"/></div>
				<div class="admincp_r2_name3"></div>
			</div>
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">日利率</div>
				<div class="admincp_r2_name2"><input type="text" name="day_rate"  value="${model.day_rate*100 }" />%</div>
				<div class="admincp_r2_name3"></div>
			</div>
			<!-- 图片添加信息结束-->		
			<div class="admincp_r2_button">
				<div class="admincp_r2_button1"></div>
				<div class="admincp_r2_button2"><input type="submit" name="submit1"  value="添加" /></div>
				<div class="admincp_r2_button3"></div>
			</div>
		</div>
		</form>
	</div>
</body>
</html>