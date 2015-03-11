<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/css/user_my_jilu.css" rel="stylesheet" type="text/css" />
<script src="<%=path %>/js/jquery-1.7.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path %>/js/base.js"></script>
<script type="text/javascript">
var currPage="${currPage}";
function setMesHaveLookbyId(id){
	jQuery.ajax({type:"POST", url: "<%=path %>/user/setMesHaveLookbyId?id="+id, success:function (result) {
		if (result ==true) {
		   
	        window.opener.parent.location="<%=path %>/user/userCenter?menueType=message&currPage="+currPage;
		} else {
		}
	}});
}
$(function(){
var id="${mes.id}";
setMesHaveLookbyId(id);
})
</script>
<title>1717tou.com</title>
</head>	
 <body >
 	<jsp:include page="../common/head.jsp"></jsp:include>
				<div class="user_cont_rr" style="background:#FFFFFF;min-height:420px;height:auto;">
                  <div class="user_cont_rrr">
					    <div class="user_cont_r_1"><span>我的消息</span></div>
					</div>
					<div>${mes.content }</div>
				</div>
					<jsp:include page="../common/foot.jsp"></jsp:include>
</body>
</html>
