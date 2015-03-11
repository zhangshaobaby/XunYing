<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="网站描述" />
		<meta name="keywords" content="关键字" />
		<link href="<%=path %>/css/index.css" rel="stylesheet" type="text/css" />
		<Script src="<%=path%>/js/jquery-1.7.js"></Script>
		<script src="<%=path%>/js/formValidator-4.1.3.js" type="text/javascript" charset="UTF-8"></script>
        <script src="<%=path%>/js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
        <script type="text/javascript">
         $(document).ready(function(){
          $.formValidator.initConfig({formID:"form1",theme:"ArrowSolidBox",submitOnce:true,
          ajaxPrompt : '有数据正在异步验证，请稍等...'
	});     
         	 $("#amount").formValidator({onShow:"请输入充值金额",onFocus:"金额必须大于0，小数点后不超过2位。",onCorrect:"正确"}).inputValidator({min:1,onError:"请填写充值金额"}).regexValidator({regExp:"(?!^0\\d*$)^\\d{1,16}(\.\\d{1,2})?$",onError:"金额填写有误"});
   
         })
        </script>
		<title>1717tou.com</title>
	</head>
	<body>

	<c:choose>
	   <c:when test="${gateBusiId=='B2C'}">
		 <jsp:include page="../userCenter/rechargeContent.jsp"></jsp:include>
		</c:when>
		<c:when test="${gateBusiId=='B2B'}">
		 <jsp:include page="../userCenter/rechargeContentB2B.jsp"></jsp:include>
		</c:when>
	</c:choose>	


	</body>
</html>

