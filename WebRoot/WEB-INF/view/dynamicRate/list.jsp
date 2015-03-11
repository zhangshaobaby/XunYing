<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="网站描述" />
		<meta name="keywords" content="关键字" />
		<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/admincp11.css" rel="stylesheet" type="text/css" />
		<script src="${ctx }/js/jquery-1.7.js" type="text/javascript"></script>
		<script src="${ctx }/js/base.js" type="text/javascript"></script>
		<title>1717tou.com</title>
		<script>
			function deleteRate(id){
				$.post("${ctx}/auth/dynamicRate/delete",{id:id},function(data){
					window.location.href=window.location.href;
				});
			}
		</script>
	</head>

	<body>
	<form action="${ctx }/auth/dynamicRate/list" method="post" id="pageForm"></form>
		<div style="background: #FFFFFF;">
			<div class="admincp_r1">
				<span>屌丝宝动态日利率管理</span>
			</div>
			<div class="admincp_r3">
				<a href="${ctx }/auth/dynamicRate/toAdd"><img src="${ctx }/images/button_add.png" /> </a>
			</div>
			<div class="admincp_r2">
				<div class="admincp_r2_a">
					<div class="admincp_r2_a1">
						序号
					</div>
					<div class="admincp_r2_a2">
						日利率
					</div>
					<div class="admincp_r2_a3">
						日期
					</div>
					<div class="admincp_r2_a4">
						操作
					</div>
				</div>
				<!-- 交易记录循环开始-->
				<c:forEach items="${page.result}" var="rate" varStatus="index">
					<div class="admincp_r2_b" id="rate_${rate[0] }">
						<div class="admincp_r2_b1">
							${index.index + 1}
						</div>
						<div class="admincp_r2_b2">
							<script>
								document.write("${rate[1]}".replace(/\.0*$|0*$/,""));
							</script>
							%
						</div>
						<div class="admincp_r2_b3">
							${rate[2]}
						</div>
						<div class="admincp_r2_b4">
							<a href="${ctx }/auth/dynamicRate/toAdd?id=${rate[0] }">修改</a>
							<a href="javascript:void(0);" onclick="deleteRate('${rate[0] }')">删除</a>
						</div>
					</div>
				</c:forEach>
				<!-- 交易记录循环结束-->
				<jsp:include page="../common/page.jsp"></jsp:include>
			</div>
		</div>		
	</body>
</html>
