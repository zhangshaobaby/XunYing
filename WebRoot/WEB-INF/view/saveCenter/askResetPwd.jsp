<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
		<link href="<%=path%>/css/user_anquan.css" rel="stylesheet" type="text/css" />
		<script src="${ctx }/js/jquery.min.js"></script>
		<script src="<%=path%>/js/formValidator-4.1.3.js"
			type="text/javascript" charset="UTF-8"></script>
		<script src="<%=path%>/js/formValidatorRegex.js"
			type="text/javascript" charset="UTF-8"></script>
		<title>1717tou.com</title>
		<script>
var basepath="<%=path%>";
	function sendCode(){
			if($("#loginPwd").val()!=""){
				$.post("${ctx}/user/getlostPayCode",{phone:$("#phone").val()},function(data){
					//success  error
					$("#showtime").show();
					if(data=="1"){
						$("#phoneTip").html("消息发送成功。");
						getTime(10);
					}else if(data=="-2"){
						$("#phoneTip").html("消息发送失败，请稍后重试。");
						getTime(10);
					}else if(data=="-3"){
						$("#phoneTip").html("手机不存在。");
						getTime(10);
					}
				});
			}
			}
	
	$(document).ready(function(){
		$.formValidator.initConfig({formID:"form_changePwd",theme:"ArrowSolidBox",submitOnce:true,
		 ajaxPrompt : '有数据正在异步验证，请稍等...'
	});
	  $("#password").formValidator({onShow:"请输入密码",onFocus:"至少6个长度",onCorrect:"密码合法"}).inputValidator({min:6,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码格式有错"})//.regexValidator({regExp:"username",dataType:"enum",onError:"用户名格式不正确"})
	    .ajaxValidator({
		dataType : "json",
		async : true,
		url : basepath + "/user/checkpwd.action",
		success : function(data){
            if( data == "0" ) return true;
			return "密码不正确";
		},
		buttons: $("#button"),
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "密码不正确，请重新填写",
		onWait : "正在对用户密码进行合法性校验，请稍候..."
	});
	});
	function getTime(i){
if(i>=0){
 $("#register_cont_yz_time").show();
 $("#agenSendSpan").html("再次发送");
  $("#secondtime").html(i);
  i--;
 setTimeout("getTime("+i+")",1000);
 
}else{
  $("#showtime").hide();
    $("#phoneTip").empty();
    $("#phoneTip").html("<a href='javascript:sendCode()'><img src='<%=path%>/images/button_getyz.png'></img></a>");
}

}
</script>
	</head>

	<body>
		<!-- 用户信息右部信息开始 -->
		<div style="background: #FFFFFF;">
			<div class="user_cont_r_1"><span>安全中心</span></div>
			<div class="user_cont_r_2">
				<div class="user_cont_r_2b">


					<div class="user_cont_r_2b1">
						<div class="user_cont_r_2b1a">
							<img src="<%=path%>/images/icon_false.png" />
						</div>
						<div class="user_cont_r_2b1b">
							支付密码
						</div>
						<div class="user_cont_r_2b1c">
							忘记支付密码，现在进行找回
						</div>
						<div class="user_cont_r_2b1d"></div>
					</div>
					<!-- 内容开始 -->
					<div class="user_cont_r_2b_pass">
						<div class="user_cont_r_2b_pass1">
							<form name="form_anquan4" method="post"
								action="<%=path%>/user/dolostPayCode" id="form_anquan4">
								<div class="user_cont_r_2b_pass1_main">
									<div class="user_cont_r_2b_pass1_main1">
										<div class="user_cont_r_2b_pass1_main1_l"></div>
										<div class="user_cont_r_2b_pass1_main1_r1">
											为确保账户安全，请输入登陆密码和手机验证码进行找回
										</div>
									</div>
									<div class="user_cont_r_2b_pass1_main1">
										<div class="user_cont_r_2b_pass1_main1_l">
											登陆密码
										</div>
										<div class="user_cont_r_2b_pass1_main1_c">
											<input type="password" name="pwd" id="password" />
										</div>
										<div class="user_cont_r_2b_pass1_main1_r">
											<div id="passwordTip"></div>
										</div>
									</div>

									<div class="user_cont_r_2b_pass1_main1">
										<div class="user_cont_r_2b_pass1_main1_l">
											手机号码
										</div>
										<div class="user_cont_r_2b_pass1_main1_c">
											<input type="text" name="phone" readonly="readonly"
												id="phone" value="${sessionScope.user.phone}" />
										</div>
										<div class="user_cont_r_2b_pass1_main1_r">
											<div id="phoneTip">
												<a href="javascript:sendCode()"><img
														src="<%=path%>/images/button_getyz.png"></img> </a>
											</div>
										</div>
									</div>
									<div id="showtime" style="display: none"
										class="register_cont_yz_time1">
										<font id="secondtime"></font>秒后重新获取
									</div>
									<div class="user_cont_r_2b_pass1_main1">
										<div class="user_cont_r_2b_pass1_main1_l">
											短信验证码
										</div>
										<div class="user_cont_r_2b_pass1_main1_c">
											<input type="password" name="code" id="code" />
										</div>
										<div class="user_cont_r_2b_pass1_main1_r"></div>
									</div>

									<div class="user_cont_r_2b_pass1_main3">
										<div class="user_cont_r_2b_pass1_main3_l"></div>
										<div class="user_cont_r_2b_pass1_main3_r">
											<input type="submit" name="button" id="button" value="下一步" />
										</div>
									</div>

								</div>
							</form>
						</div>
					</div>
					<!-- 内容结束 -->



				</div>
			</div>
		</div>
		<!-- 用户信息右部信息结束 -->
	</body>
</html>
