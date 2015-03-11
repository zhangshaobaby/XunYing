<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>更改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/index.css">
	<link href="<%=path%>/css/user_anquan.css" rel="stylesheet" type="text/css" />
	<Script src="<%=path%>/js/jquery-1.7.js"></Script>	
		<script src="<%=path%>/js/formValidator-4.1.3.js" type="text/javascript" charset="UTF-8"></script>
        <script src="<%=path%>/js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
	<Script src="<%=path%>/js/secritpwd.js"></Script>
  <script type="text/javascript">
  var basepath="<%=path%>";
  $(document).ready(function(){
	$.formValidator.initConfig({formID:"form_anquan4",theme:"ArrowSolidBox",submitOnce:true,
			 onSuccess: function() { 
		  var username = $("#username").val();
		var password = jQuery("#password1").val();
		var enc_str = Encrypt(username, password);
		jQuery("#password1").val(enc_str);
		 document.forms(0).submit();
		 }, 
		ajaxPrompt : '有数据正在异步验证，请稍等...'
	});
	$("#password").formValidator({onShow:"请输入密码",onFocus:"至少6个长度",onCorrect:"密码合法"}).inputValidator({min:6,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码格式有错"})//.regexValidator({regExp:"username",dataType:"enum",onError:"用户名格式不正确"})
	    .ajaxValidator({
		dataType : "json",
		async : true,
		url : basepath + "/user/checkpaypwd",
		success : function(data){
            if( data == "0" ) return true;
			return "密码不正确";
		},
		buttons: $("#button"),
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "密码不正确，请重新填写",
		onWait : "正在对用户密码进行合法性校验，请稍候..."
	});
	$("#password1").formValidator({onShowFixText:"6~16个字符，包括字母、数字、特殊符号，区分大小写",onShow:"请输入密码",onFocus:"至少6个长度",onCorrect:"密码合法"}).inputValidator({min:6,max:16,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码长度错误,请确认"}).passwordValidator({compareID:"us"});
	$("#password2").formValidator({onShowFixText:"请再次输入密码",onShow:"输再次输入密码",onFocus:"至少1个长度",onCorrect:"密码一致"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"}).compareValidator({desID:"password1",operateor:"=",onError:"2次密码不一致,请确认"});
});

</script>

  </head>  
  <body>
 <div style="background:#FFFFFF;">
					<div class="user_cont_r_1"><span>安全中心</span></div>
					<div class="user_cont_r_2">
						<div class="user_cont_r_2b">

							
							<div class="user_cont_r_2b1">
								<div class="user_cont_r_2b1a"><img src="images/icon_false.png" /></div>
								<div class="user_cont_r_2b1b">支付密码</div>
								<div class="user_cont_r_2b1c">支付密码已启用，默认与登录密码相同，请及时修改</div>
								<div class="user_cont_r_2b1d"></div>
							</div>
							<!-- 内容开始 -->
							<div class="user_cont_r_2b_pass">
								<div class="user_cont_r_2b_pass1">
								<form name="form_anquan4" method="post" action="<%=path%>/user/setPayPassword" id="form_anquan4">
								<input type="hidden" name="id" value="${sessionScope.user.id}" />
								<input type="hidden" id="username" name="username" value="${sessionScope.user.username}" />
									<div class="user_cont_r_2b_pass1_main">
										<div class="user_cont_r_2b_pass1_main1">
											<div class="user_cont_r_2b_pass1_main1_l"></div>
											<div class="user_cont_r_2b_pass1_main1_r1">为确保账户安全，请将支付密码修改为与登录密码不一致</div>
										</div>
											<div class="user_cont_r_2b_pass1_main1">
											<div class="user_cont_r_2b_pass1_main1_l">当前密码</div>
											<div class="user_cont_r_2b_pass1_main1_c"><input type="password" name="paypwd" id="password" /></div>
											<div class="user_cont_r_2b_pass1_main1_r"><div id="passwordTip"></div></div>
										</div>
										
										<div class="user_cont_r_2b_pass1_main1">
											<div class="user_cont_r_2b_pass1_main1_l">新密码</div>
											<div class="user_cont_r_2b_pass1_main1_c"><input type="password" name="newpwd" id="password1" /></div>
											<div class="user_cont_r_2b_pass1_main1_r"><div id="password1Tip"></div></div>
										</div>
										
										<div class="user_cont_r_2b_pass1_main1">
											<div class="user_cont_r_2b_pass1_main1_l">确认新密码</div>
											<div class="user_cont_r_2b_pass1_main1_c"><input type="password"  id="password2" /></div>
											<div class="user_cont_r_2b_pass1_main1_r"><div id="password2Tip"></div></div>
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
  </body>
</html>
