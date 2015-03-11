<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>完成页面</title>
<!--<meta http-equiv='refresh' content='3;URL={$href}'>-->
<style type="text/css">
	body{ margin:0px; padding:0px; font-size:12px;background:#FFFFFF;  font-size:13px; color:#666666;}
	a{ font-size:13px; color:#2571be; text-decoration:none;}
	#cont{ width:60%; height:200px; border:1px solid #CCCCCC; margin:0px auto; margin-top:100px; background:#FFFFFF;}
	#cont_main{ width:80%; height:120px;  margin:0px auto; margin-top:50px;}
	#cont_main_l{ width:20%; height:100px; float:left; text-align:center;}
	#cont_main_r{ width:80%; height:120px; float:left; line-height:30px;}
	#cont_main_r .a{ font-size:16px; font-weight:bold;}
</style>
<Script src="<%=path%>/js/jquery-1.7.js">
</Script>
</head>

<body onunload="window.opener.location.reload();">
	<div style="background:#FFFFFF; height:600px;">
		<div id="cont">
			<div id="cont_main">
				<div id="cont_main_l"><img src="<%=path%>/images/icon_success.png" /></div>
				<div id="cont_main_r">
					<span class="a">操作完成！！</span><br />
					<span>提示你可以进行以下操作： <a  id="callbacka" href="javascript:window.close()">点击关闭</a></span><br />
				</div>
			</div>
		</div>
	</div>
</body>
</html>

