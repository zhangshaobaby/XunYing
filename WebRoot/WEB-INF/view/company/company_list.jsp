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
<link href="${ctx }/css/admincp5.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/js/jquery-1.7.js" type="text/javascript"></script>
<title>1717tou.com</title>
<script type="text/javascript" src="<%=path %>/js/base.js"></script>
</head>

<body>
	<form action="${ctx }/auth/company/xtdbList" method="post" id="pageForm">
	<input type="hidden"  name="type" value="${type}" />
	</form>
	<div style="background:#FFFFFF;">
		<div class="admincp_r1">
			<c:choose>
				<c:when test="${company.type==1}">
					<img src="${ctx}/images/admincp_r5.png" />
				</c:when>
				<c:otherwise>
					<img src="${ctx}/images/admincp_r6.png" />
				</c:otherwise>
			</c:choose>
		</div>
		<div class="admincp_r3"><a href="${ctx }/auth/company/toAdd?type=${type}"><img src="${ctx}/images/button_add.png" /></a></div>
		<div class="admincp_r2">
			<!-- 产品开始 -->
				<c:forEach items="${page.result}" var="company" varStatus="index">
				<div class="admincp_r2_main">
					<div class="admincp_r2_main_a">
						<div class="admincp_r2_main_a1">${company.company_name }</div>
						<div class="admincp_r2_main_a2">发布日期：${company.createTime }</div>
					</div>
					<div class="admincp_r2_main_b"><a href="${ctx }/auth/company/toAdd?id=${company.id}">修改</a></div>
				</div>
				</c:forEach>
			<!-- 产品结束 -->

			 <jsp:include page="../common/page.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>