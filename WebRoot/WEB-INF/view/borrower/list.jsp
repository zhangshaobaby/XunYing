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
		<script src="${ctx }/js/base.js" type="text/javascript"></script>
		<title>1717tou.com</title>
<script>
	function changeState(id,state){
		$.post("${ctx}/auth/borrower/stateChange",{id:id,state:state},function(data){
			$("#pageForm").submit();
		});
	}
	function deleteborrower(id){
		$.post("${ctx}/auth/borrower/deleteborrower",{id:id},function(data){
			$("#pageForm").submit();
		});
	}
</script>
	</head>

	<body>
	<form action="${ctx }/auth/borrower/list" method="post" id="pageForm"></form>
<div style="background: #FFFFFF;">
			<div class="admincp_r1">
				<span>受托方管理</span>
			</div>
			<div class="admincp_r3">
				<a href="${ctx }/auth/borrower/toAdd"><img src="${ctx }/images/button_add.png" /> </a>
			</div>
			<div class="admincp_r2">
				<div class="admincp_r2_a">
					<div class="admincp_r2_a1">
						公司名称
					</div>
					<div class="admincp_r2_a2">
						营业执照编号
					</div>
					<div class="admincp_r2_a3">
						状态/金额
					</div>
					<div class="admincp_r2_a4">
						操作
					</div>
				</div>
				<!-- 交易记录循环开始-->
				<c:forEach items="${page.result}" var="borrower">
					<div class="admincp_r2_b">
						<div class="admincp_r2_b1">
							<cc:subLength var="${borrower.name}" length="11"/>
						</div>
						<div class="admincp_r2_b2">
							${borrower.busiCode}
						</div>
						<div class="admincp_r2_b3">
							<c:choose>
							   <c:when test="${borrower.usrCustId==null||borrower.usrCustId==''}">	
						     &nbsp;						 
									<a href="javascript:void();"
										onclick="deleteborrower(${borrower.id})">删除</a>
			              </c:when>
							<c:when test="${borrower.state==0}">
								<!--  a href="javascript:void();"
									onclick="changeState

(${borrower.id },1)">上线</a>-->下线
							</c:when>
							<c:when test="${borrower.state==1}">
							<!--  	<a href="javascript:void();"
									onclick="changeState

(${borrower.id },0)">下线</a>--><cc:selectBalance value="${borrower.usrCustId}"/>
							</c:when>
						  </c:choose>
						
						
						</div>
						<div class="admincp_r2_b4">
						<c:choose>
							<c:when test="${borrower.usrCustId==null||borrower.usrCustId==''}">
								<a target = "_blank" href="${ctx }/auth/huifu/CorpRegister?id=${borrower.id }" ><img src="${ctx }/images/button_dsfzc.png" /> </a>
							</c:when>
							<c:otherwise>
								<a target="_blank" href="${ctx }/auth/huifu/enterpriseUserBindCard?usrCustId=${borrower.usrCustId}">绑卡</a>
								<a target="_blank" href="${ctx }/cash/auth/cashApplaylist?usrCustId=${borrower.usrCustId}">取现</a>
								<a target="_blank" href="${ctx }/auth/operator/enterpriserecharge?usrCustId=${borrower.usrCustId}">充值</a>
					
							</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:forEach>
				<!-- 交易记录循环结束-->
 <jsp:include page="../common/page.jsp"></jsp:include>
			</div>
		</div>		
	</body>
</html>
