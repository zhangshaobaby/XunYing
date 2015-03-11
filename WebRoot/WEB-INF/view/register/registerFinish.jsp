<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="<%=path %>/css/reg.css" rel="stylesheet" type="text/css" />
<title>1717tou.com</title>
</head>

<body>
<%@ include file="../common/head.jsp" %>

	
	<!-- 注册信息开始 -->
		<div class="reg">
		<div class="reg1">首页><span>用户注册</span></div>
		<div class="reg2">
			<h3>用户注册</h3>
			<div class="reg2_1"><img src="<%=path %>/images/reg1_01_3.png"/></div>
			<div class="reg2_9"><img src="<%=path %>/images/yzsuccess.png" /></div>
			<div class="reg2_10"><a  href="<%=path%>/huifu/regist?username=${sessionScope.user.username}" target="_top"><img src="<%=path %>/images/hfpay_03.png" /></a></div>
		    <div style="padding:20px;margin-top: 25px;margin-right:20px;">
                    <div style="font-size:16px;font-weight:bold;">温馨提示：</div>
                    <div style=" margin:10px 0px 20px 20px;line-height: 25px;">                   
                    开通第三方支付过程中，如果出现身份证号与姓名不匹配，可能是以下情况：<br/>                    
                    1.姓名变更；<br/>
                    2.户籍地址变更；<br/>
                    3.现役军人；<br/>                    
                    请到户籍所在地更新证件或使用其他证件。<br/>
                    </div>
            </div>		
		</div>
	</div>
	<!-- 注册信息结束 -->
	<%@ include file="../common/foot.jsp" %>
	
	
</body>
</html>
