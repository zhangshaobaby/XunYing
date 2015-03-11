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
<link href="${ctx }/css/admincp10.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/js/jquery-1.7.js" type="text/javascript"></script>
<script src="${ctx }/js/page.js" type="text/javascript"></script>
<title>1717tou.com</title>
<script>
	function changeState(id,state){
		$.post("${ctx}/auth/advert/stateChange",{id:id,state:state},function(data){
			$("#pageForm").submit();
		});
	}
</script>
</head>

<body>
	<form action="${ctx }/auth/advert/list" method="post" id="pageForm">
		<input type="hidden" name="currPage" id="currPage" value="${page.currPage}"/>
		<input type="hidden" name="prePage" id="prePage" value="${page.prePage}"/>
		<input type="hidden" name="nextPage" id="nextPage" value="${page.nextPage}"/>
		<input type="hidden" name="totalPage" id="totalPage" value="${page.totalPage}"/>
		<input type="hidden" name="rows" id="rows" value="${page.rows }"/>
	</form>
	<div style="background:#FFFFFF;">
		<div class="admincp_r1"><img src="${ctx}/images/admincp_r10.png" /></div>
		<div class="admincp_r3"><a href="${ctx }/auth/advert/toAdd"><img src="${ctx}/images/button_add.png" /></a></div>
		<div class="admincp_r2">
			<!-- 循环开始 -->
				<c:forEach items="${page.result}" var="advert">
					<div class="admincp_r2_main">
						<div class="admincp_r2_main_a">
							<img src="${picpath }/${advert.imgUrl }" />
						</div>
						<div class="admincp_r2_main_b">
							<div class="admincp_r2_main_b1">
								${advert.name }
							</div>
							<div class="admincp_r2_main_b2">
								<p>开始日期：${advert.start_time }</p><p>结束日期：${advert.end_time }</p>
							</div>
						</div>
						<div class="admincp_r2_main_c">
							<c:choose>
								<c:when test="${advert.state==0}"><a href="#" onclick="changeState(${advert.id },1)">点击上线</a></c:when>
								<c:when test="${advert.state==1}"><a href="#" onclick="changeState(${advert.id },0)">点击下线</a></c:when>
							</c:choose>
							  <a href="${ctx }/auth/advert/toAdd?id=${advert.id }">编辑</a>
						</div>
					</div>
				</c:forEach>
			<!-- 循环结束 -->
			<div class="admincp_r4">
				<a href="javascript:void(0);" onclick="firstPage()">首页</a>
				<a href="javascript:void(0);" onclick="prePage()">上一页</a>
				<a href="javascript:void(0);" onclick="nextPage()">下一页</a>
				<a href="javascript:void(0);" onclick="finalPage()	">尾页</a>
			</div>
		</div>
	</div>
</body>
</html>