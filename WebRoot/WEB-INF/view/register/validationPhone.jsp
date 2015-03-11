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
<script type="">
 var basepath='<%=path %>';
 var msg='${msg}';
</script>
<Script TYPE="text/javascript" src="<%=path %>/js/jquery-1.7.js"></Script>
<script src="<%=path%>/js/formValidator-4.1.3.js" type="text/javascript" charset="UTF-8"></script>
<script src="<%=path%>/js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
<title>1717tou.com</title>
  	<script type="text/javascript">
    var basepath='<%=path%>';
    $(document).ready(function(){
    $.formValidator.initConfig({formID:"form-reg", theme:"ArrowSolidBox", submitOnce:true, ajaxPrompt:"\u6709\u6570\u636e\u6b63\u5728\u5f02\u6b65\u9a8c\u8bc1\uff0c\u8bf7\u7a0d\u7b49..."});
    
    $("#phone").formValidator({onShow:"请输入你的手机号码",onFocus:"请输入你的手机号码",onCorrect:"正确"}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的,请确认"}).regexValidator({regExp:"^1[3-9]{1}[0-9]{9}$",onError:"你输入的手机号码格式不正确"}).ajaxValidator({
		async : true,
		url : basepath + "/user/checkphone",
		success : function(result){
            if(result == "noexsit" ) 
            {
             phonerRectcorrect=true;
              return true;
            }
			return "该号码已注册，请更换号码";
		},
		buttons: $("#button"),
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "该号码已注册，请更换号码",
		onWait : "正在对手机进行合法性校验，请稍候..."
	});
$("#code").formValidator({ajax:true,onShow:"请输入验证码",onFocus:"请输入验证码",onCorrect:"请输入验证码"}).inputValidator({min:4,onError:"请输入4位验证码"}).ajaxValidator({async:true, url:basepath +"/checksessionattr?attrname=phonecode", success:function (result) {		
if (result=="true") {
			return true;
		}
		return "验证码输入错误,请重新输入";
}})
	})
</script>
<script type="text/javascript">
function changePhoneCode() {
	jQuery.ajax({type:"POST", url:basepath + "/user/getPhoneCode",data:"phone="+$("#phone").val(), success:function (result) {
		if (result == "success") {
		   $("#agenSendSpan").removeClass("yan").addClass("refa");
		   $("#agenSendSpan").html("重发(<span id='secondtime'></span>)");
			getTime(60);
		} else {
			alert("\u9a8c\u8bc1\u7801\u53d1\u9001\u5931\u8d25!\u8bf7\u7a0d\u540e\u91cd\u8bd5");
			getTime(60);
		}
	}});
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
</script>
</head>

<body>
<%@ include file="../common/head.jsp" %>
	<!-- 头部内容结束 -->

	<!-- 注册信息开始 -->
<div class="reg">
		<div class="reg1">首页><span>用户注册</span></div>
		<div class="reg2">
			<h3>用户注册</h3>
			<div class="reg2_1"><img src="<%=path%>/images/reg1_01_2.png"></div>
			<div class="register">
		<form name="form-reg" id="form-reg" method="post" action="<%=path%>/user/checkPhoneCode">
	<input type="hidden" name="id" value="${sessionScope.user.id}" />
		<div class="register_cont">
			
			<div class="register_cont_name">
				<div class="register_cont_name1"><font color="#FF0000">*</font> 手机号</div>
				<div class="register_cont_name2"><input type="text" name="phone" id="phone"  value="" style="width:230px;"/></div>
				<span class="yan" id="agenSendSpan" ><a style="color:#fff" href="javascript:changePhoneCode()">立即验证</a></span>
				<div class="register_cont_name3"style="margin-left:10px;"><div id="phoneTip">这是提示……</div></div>

			</div>
			<div style="width:500px;height:40px;line-height:40px;margin-left: 150px;">信息已发送给你的手机，请输入短信中的验证码，此服务免费。</div>
			
			<div class="register_cont_yanzheng">
				<div class="register_cont_yanzheng1"><font color="#FF0000">*</font> 验证码</div>
				<div class="register_cont_yanzheng2"><input style="width:250px" type="text" id="code"   name="code" value=""/></div>
				<div class="register_cont_name3" style="margin-left:160px;"><div id="codeTip">这是提示……</div></div>
			</div>
			
		<div style="width:500px;height:40px;"></div>
			
			<div class="register_cont_button">
				<div class="register_cont_button1"></div>
				<div class="register_cont_button2">
					
					<input  type="submit" class="register_cont_submit" value="下一步"  />
					
				</div>
				<div class="register_cont_button3"></div>
			</div>
			
			<div class="register_cont_md5">
				<div class="register_cont_md51"></div>
				<div class="register_cont_md52">
					<img src="<%=path%>/images/icon_anquan.png" />你的信息通过256GSC加密保护，数据传输安全
				</div>
				<div class="register_cont_md53"></div>
			</div>
			
		</div>
		</form>
	</div>
		</div>
			
	</div>
<!-- 注册信息结束 -->
	<%@ include file="../common/foot.jsp" %>
	
	
	
	
</body>
</html>
