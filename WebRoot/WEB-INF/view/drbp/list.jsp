<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="网站描述" />
		<meta name="keywords" content="关键字" />
		<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/admincp11.css" rel="stylesheet"
			type="text/css" />
		<script src="${ctx }/js/jquery-1.7.js" type="text/javascript"></script>
		<title>1717tou.com</title>
<script>
	function deleteborrower(id){
		$.post("${ctx}/auth/dynamicRateByProject/delete",{id:id},function(data){
			$("#pageForm").submit();
		});
	}
</script>
	</head>

	<body>
<div style="background: #FFFFFF;">
			<div class="admincp_r1">
				<span>开放日管理</span>
			</div>
			<div class="admincp_r3">
				<c:if test="${tag==null}">
				<a href="${ctx }/auth/drbp/toAdd?pid=${id }">
					<img src="${ctx }/images/button_add.png" />
				</a>
				</c:if>
			</div>
			<div class="admincp_r2">
				<div class="admincp_r2_a">
					<div class="admincp_r2_a1">
						开放月
					</div>
					<div class="admincp_r2_a2">
						开放日
					</div>
					<div class="admincp_r2_a3">
						年利率
					</div><c:if test="${tag==null}">
					<div class="admincp_r2_a4">
						操作
					</div></c:if>
				</div>
				<!-- 交易记录循环开始-->
				<c:forEach items="${list}" var="drbp">
					<div class="admincp_r2_b">
						<div class="admincp_r2_b1">
							${drbp.month}
						</div>
						<div class="admincp_r2_b2">
							${drbp.startTime}
						</div>
						<div class="admincp_r2_b3">
							${drbp.rate }
						</div>
							<c:if test="${tag==null}">
						<div class="admincp_r2_b4">
							<a href="${ctx }/auth/drbp/toAdd?id=${drbp.id }">修改</a>|
							<a href="">删除</a>
						</div>
							</c:if>
					</div>
				</c:forEach>
				<!-- 交易记录循环结束-->
			</div>
		</div>		
	</body>
</html>
