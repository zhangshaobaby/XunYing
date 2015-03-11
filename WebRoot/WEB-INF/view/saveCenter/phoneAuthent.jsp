<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'phoneAuthent.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/index.css">
		<link href="<%=path%>/css/user_anquan.css" rel="stylesheet" type="text/css" />
		<Script src="<%=path%>/js/jquery-1.7.js"></Script>
		<script type="text/javascript">
 var basepath="<%=path%>";
 var phonecheck=false;
 var codecheck=false;
function checkPhone(){
var phoneNum=$("#phone").val();
 var reltest=/^\d{11}$/;
 $("#phoneMes").empty();
 if(reltest.test(phoneNum)){
   phoneAuthent();
  }else{
	  $("#phoneMes").append("请输入11位有效手机号码");
  }
}
function showyanzhengimg(){
	$("#phoneMes").empty();
  $("<a href='javascript:checkPhone()'><img src='images/button_getyz.png' /></a>").appendTo("#phoneMes");
}

function phoneAuthent() {
	var phone = $("#phone").val();
	jQuery.ajax({type:"POST", url:basepath + "/user/bindPhoneCode", data:"phone=" + phone, success:function (result) {
		if (result == "success") {
         $("#phoneMes").empty();
		 $("#phoneMes").append("已发送验证码请查收");
		 phonecheck=true;
		 } else if(result =="-1") {
		     alert("手机号已存在");
		} else {
			alert("短信息发送失败");
		}
	}});
}
function codeempty(val){
 $("#phoneCodeTip").empty();
 if(val==""){
 $("#phoneCodeTip").html("请输入验证码");
 codecheck=false;
 }else{
 codecheck=true;
 }
}
function beforsubmit(){
 if(phonecheck&&codecheck){
  return true;
 }else{
 return false;
 }

}

</script>
	</head>

	<body>
		<div style="background: #FFFFFF;">
			<div class="user_cont_r_1"><span>安全中心</span></div>
			<div class="user_cont_r_2">
				<div class="user_cont_r_2b">
					<div class="user_cont_r_2b1">
						<div class="user_cont_r_2b1a">
							<img src="images/icon_false.png" />
						</div>
						<div class="user_cont_r_2b1b">
							手机认证
						</div>
						<div class="user_cont_r_2b1c">
							绑定手机号码，账户资金变动实时通知
						</div>
						<div class="user_cont_r_2b1d"></div>
					</div>
					<!-- 内容开始 -->
					<div class="user_cont_r_2b_tel">
						<div class="user_cont_r_2b_tel1">
							<form name="form_anquan2" method="post"
								action="<%=path%>/user/phoneAuthent" onsubmit="return  beforsubmit()" >
								<input type="hidden" name="id" value="${sessionScope.user.id}" />
								<div class="user_cont_r_2b_tel1_main">

									<div class="user_cont_r_2b_tel1_main1">
										<div class="user_cont_r_2b_tel1_main1_l">
											手机号码
										</div>
										<div class="user_cont_r_2b_tel1_main1_c">
											<input type="text" id="phone" name="phone"
												onfocus="showyanzhengimg()" />
										</div>
										<div id="phoneMes" class="user_cont_r_2b_tel1_main1_r">
											请输入11位有效手机号码
										</div>
									</div>

									<div class="user_cont_r_2b_tel1_main1">
										<div class="user_cont_r_2b_tel1_main1_l">
											短信验证码
										</div>
										<div class="user_cont_r_2b_tel1_main1_c">
											<input type="text" name="phoneCode" onblur="codeempty(this.value)" />
										</div>
										<div id="phoneCodeTip" class="user_cont_r_2b_tel1_main1_r"></div>
									</div>

									<div class="user_cont_r_2b_tel1_main3">
										<div class="user_cont_r_2b_tel1_main3_l"></div>
										<div class="user_cont_r_2b_tel1_main3_r">
											<input type="submit" value="下一步" />
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
	</body>
</html>
