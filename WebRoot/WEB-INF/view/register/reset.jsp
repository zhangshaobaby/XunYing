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
<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/reg.css" />
<script src="<%=path%>/js/jquery-1.7.js"></script>
<script src="<%=path%>/js/reset.js"></script>
<script src="<%=path%>/js/secritpwd.js"></script>
<script type="text/javascript">
var environment = {
	userVo : '',
	role : '',
	userId : '',
	basePath : '',
	userCash : '',
	globalPath : 'localhosot/1717tou'
};
var basepath='<%=path%>';
 var msg='${msg}';
</script>

<title>1717tou.com</title>
</head>

<body>
	<%@ include file="../common/head.jsp" %>
	
	<!-- 注册信息开始 -->
	<div class="register">
		<form name="form-reg" id="form-reg" method="post" action="${ctx }/user/doReset">
	
		<div class="register_top"><span>忘记密码</span></div>
		<div class="register_cont">
		
		    <div class="register_cont_pass">
				<div class="register_cont_pass1"><font color="#FF0000">*</font> 密码</div>
				<div class="register_cont_pass2"><input type="password" id="password1" name="password1" value=""/></div>

				<div class="register_cont_pass3"><div id="password1Tip"></div></div>
				
			</div>
		 <div class="register_cont_pass">
				<div class="register_cont_pass1"></div>
				<div style=" height:60px; float:left;line-height:60px;">[6-16个字符，建议使用字母加数字或符号的组合密码]</div>
				
			</div>
			<div class="register_cont_pass_next">
				<div class="register_cont_pass_next1"><font color="#FF0000">*</font>确认密码</div>
				<div class="register_cont_pass_next2"><input type="password" name="password2" id="password2" value=""/></div>
				<div class="register_cont_pass_next3"><div id="password2Tip"></div></div>
			</div>
			<div class="register_cont_button">
				<div class="register_cont_button1"></div>
				<div class="register_cont_button2">
					<input type="submit" name="submit1" class="register_cont_submit" value="下一步" />
				</div>
				<div class="register_cont_button3"></div>
			</div>
		</div>
		</form>
	</div>
	<!-- 注册信息结束 -->
	
	<%@ include file="../common/foot.jsp" %>
</body>
</html>
