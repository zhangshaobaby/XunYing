<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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
<link href="${ctx}/css/index.css" rel="stylesheet" type="text/css" />
<script>
	var time = 5;
	function test(){
		if(time>0){
			time-=1;
			setTimeout(test,1000);
		}else{
			window.location.href="${ctx}/index";		
		}
	}
	setTimeout(test,1000);
</script>
<title>1717tou.com</title>
</head>

<body>
	<%@ include file="../common/head.jsp" %>
	<!-- 注册信息开始 -->
	<div class="register">
		<form name="form-reg" method="post" action="#">
		<div class="register_top"><span>更改密码</span></div>
		
		<div class="register_cont">
			<div class="register_cont_suc">
				<div class="register_cont_suc1">
					密码修改成功，请妥善保管好您的账号密码。
				</div>
				<div class="register_cont_suc3">
					<img src="${ctx }/images/icon_anquan.png" />你的信息通过256GSC加密保护，数据传输安全
				</div>
			</div>
			
		</div>
		</form>
	</div>
	<!-- 注册信息结束 -->
	<%@ include file="../common/foot.jsp" %>
</body>
</html>
