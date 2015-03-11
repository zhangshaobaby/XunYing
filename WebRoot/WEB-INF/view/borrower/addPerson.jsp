<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/admincp101.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxupload.3.6.js"></script>
<title>1717tou.com</title>
</head>

<body>
	<div style="background:#FFFFFF;">
		<div class="admincp_r1"><img src="${ctx}/images/admincp_r10.png" /></div>
		<!--<div class="admincp_r3">当前位置：广告管理->广告添加</div>-->
		
		<form name="form_admincp" method="post" action="${ctx}/auth/borrower/addPerson">
		<div class="admincp_r2">
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">借款人账号</div>
				<div class="admincp_r2_name2"><input type="text" name="name" value="" /></div>
				<div class="admincp_r2_name3"></div>
			</div>
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
