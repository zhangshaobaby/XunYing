<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/admincp10.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/js/jquery-1.7.js" type="text/javascript"></script>
<script src="${ctx }/js/base.js" type="text/javascript"></script>
<title>1717tou.com</title>
<script>
	function changeState(id,state){
		$.post("${ctx}/auth/news/stateChange",{id:id,state:state},function(data){
			$("#pageForm").submit();
		});
	}
</script>
</head>

<body>
	<form action="${ctx }/auth/news/list" method="post" id="pageForm">
	</form>
	<div style="background:#FFFFFF;">
		<div class="admincp_r1"><span>新闻管理</span></div>
		<div class="admincp_r3"><a href="${ctx }/auth/news/toAdd"><img src="${ctx}/images/button_add.png" /></a></div>
		<div class="admincp_r2">
			<!-- 循环开始 -->
				<c:forEach items="${page.result}" var="news">
					<div class="admincp_r2_main">
						<div class="admincp_r2_main_a">
							<c:set var="imgs" value="${fn:split(news.imgUrl, ',')}" />
							<c:forEach items="${imgs}" var = "img" begin = "0" end = "2">
							<img src="${picpath }/${img }" style="width:110px;"/>
							</c:forEach>
						</div>
						<div class="admincp_r2_main_b">
							<div class="admincp_r2_main_b1">
								<cc:subLength var = "${news.name}" length = "20"/>
							</div>
							<div class="admincp_r2_main_b2">
								<p>开始日期：${news.start_time }</p>
								<!-- <p>结束日期：${news.end_time }</p> -->
							</div>
						</div>
						<div class="admincp_r2_main_c">
							<c:choose>
								<c:when test="${news.state==0}"><a href="javascript:void(0);" onclick="changeState(${news.id },1)">上线</a></c:when>
								<c:when test="${news.state==1}"><a href="javascript:void(0);" onclick="changeState(${news.id },0)">下线</a></c:when>
							</c:choose>
							  <a href="${ctx }/auth/news/toAdd?id=${news.id }">编辑</a>
						</div>
					</div>
				</c:forEach>
			<!-- 循环结束 -->
			<div class="admincp_r4">
				<jsp:include page="../common/page.jsp"></jsp:include>
			</div>
		</div>
	</div>
</body>
</html>