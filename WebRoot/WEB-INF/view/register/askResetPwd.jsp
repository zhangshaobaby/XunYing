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
<script src="${ctx }/js/jquery.min.js"></script>
<title>1717tou.com</title>
<script>
function checkPhone(){
var phoneNum=$("#phone").val();
 var reltest=/^\d{11}$/;
 $("#phoneTip").empty();
 if(reltest.test(phoneNum)){
  $("#phoneTip").append("正确");
   phonecheck=true;
  }else{
	  $("#phoneTip").append("请输入11位有效手机号码");
  }
}

var phonecheck=false;
	function changePhoneCode() {
     if(phonecheck==false){
	  	alert("请确认手机号码的正确性");
	  	return;
	  }
		 $.post("${ctx}/user/askResetPwd",{phone:$("#phone").val()},function(data){
					//success  error
					if(data=="1"){
						$("#agenSendSpan").removeClass("yan").addClass("refa");
		                $("#agenSendSpan").html("重发(<span id='secondtime'></span>)");
			             getTime(60);
					}else if(data=="-1"){
						$("#msgs").html("未找到匹配手机号");
					}else{
						alert("\u9a8c\u8bc1\u7801\u53d1\u9001\u5931\u8d25!\u8bf7\u7a0d\u540e\u91cd\u8bd5");
			            getTime(60);
					}
				});
}
	    function getTime(i) {
	    if (i >= 0) {
		$("#secondtime").html(i);
		i--;
		setTimeout("getTime(" + i + ")", 1000);
	} else {
		$("#agenSendSpan").empty();
		$("#agenSendSpan").removeClass("refa").addClass("yan");
		$("#agenSendSpan").html("<a style='color:#fff' href='javascript:changePhoneCode()'>重发</a>");
	}
}
	
	$(document).ready(function(){
		var href=window.location.href;
		var msg = href.substring(href.indexOf("msgss")+6);
		$("#msgs").html(msg==-1?"没有匹配用户":msg==-2?"验证码错误":"")
	});
</script>
<style type="text/css">
.yan{width:60px;height:35px;line-height:35px;font-size: 12px;background: #0060a5;color:#fff;float:left;margin-right: 10px;border:none;margin-top: 3px;text-align: center}
.refa{width:60px;height:35px;line-height:35px;font-size: 12px;background: #eee;float:left;margin-right: 10px;border:none;margin-top: 3px;text-align: center}
</style>
</head>

<body>
	<%@ include file="../common/head.jsp" %>
	
	<!-- 注册信息开始 -->
	<div class="register" style="height:400px">
		<form name="form-reg" method="post" action="${ctx }/user/doAskResetPwd">
		<div class="register_top"><span>忘记密码</span></div>
		<div class="register_cont">
			<div class="register_cont_pass">
				<div class="register_cont_pass1"><font color="#FF0000">*</font> 手机</div>
				<div class="register_cont_pass2"><input type="text" name="phone" id="phone" value="" onblur="checkPhone()"/></div>
				<div class="register_cont_pass3"><span class="yan" id="agenSendSpan" ><a style="color:#fff" href="javascript:changePhoneCode()">点击发送</a></span><div id="phoneTip" ></div></div>
			</div>
			
			<div class="register_cont_yanzheng">
				<div class="register_cont_yanzheng1"><font color="#FF0000">*</font> 验证码</div>
				<div class="register_cont_yanzheng2"><input type="text" name="code" id="code" value=""/></div>
				<div class="register_cont_yanzheng3" id="msgs"></div>
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
