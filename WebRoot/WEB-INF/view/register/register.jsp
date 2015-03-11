<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/reg.css" />
		<Script src="<%=path%>/js/jquery-1.7.js"></Script>
		<script src="<%=path%>/js/formValidator-4.1.3.js" type="text/javascript" charset="UTF-8"></script>
        <script src="<%=path%>/js/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>
		<Script src="<%=path%>/js/secritpwd.js"></Script>
		<Script src="<%=path%>/js/regist.js"></Script>
		<script type="text/javascript">
var basepath='<%=path%>';
 var msg='${message.msgCode}';
 $(document).ready(function(){
  new changeCode();
	jQuery("#registermes").empty();
	if(msg!="success"){
	var mestring="${message.msgString}";
	jQuery("<p>" +mestring+ "</p>").appendTo(jQuery("#registermes"));
	 }
	 })
</script>
<style type="text/css">
 .hideEle {
   	display:none;
}
</style>
		
		<title>1717tou.com</title>
	</head>

	<body>
<%@ include file="../common/head.jsp" %>

		<!-- 注册信息开始 -->
	<div class="reg">
		<div class="reg1">首页><span>用户注册</span></div>
		<div class="reg2">
			<h3>用户注册</h3>
			<div class="reg2_1"><img src="<%=path%>/images/reg1_01.png"></div>

			<div class="register">
		<form name="form-reg" id="form-reg" method="post" action="<%=path%>/user/saveUser">
	       <c:if test="${type==2}">
              <div style="margin-left:300px;margin-bottom:-25px;color:#e33304;">欢迎进入经纪人注册接口！</div>
			</c:if>
		
		<input  type="hidden" name="type" value="${type}"/>
		<div class="register_cont">
			<div class="register_cont_name">
				<div class="register_cont_name1"><font color="#FF0000">*</font> 用户名</div>
				<div class="register_cont_name2"><input type="text" id="us" name="username" value="" /></div>
				<div class="register_cont_name3"><div id="usTip">4-18个字符，一个汉字为2个字符，推荐使用中文会员名</div></div>

			</div>
			<div class="register_cont_pass">
				<div class="register_cont_pass1"><font color="#FF0000">*</font> 密码</div>
				<div class="register_cont_pass2"><input type="password" id="password1" name="pwd" value=""/></div>

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
			<div class="register_cont_pass_next"<c:if test="${_user!=null}"> style="display:none"</c:if>>
				<div class="register_cont_pass_next1">推荐人手机号</div>
				<div class="register_cont_pass_next2"><input  name="agent" id="agent" value="<c:if test="${_user!=null}">${_user.phone }</c:if>"/></div>
				<div class="register_cont_pass_next3"><div id="agentTip"></div></div>
			</div>
				<div class="register_cont_yanzheng">
				<div class="register_cont_yanzheng1"><font color="#FF0000">*</font> 验证码</div>
				<div class="register_cont_yanzheng2"><input name="code" id="code" value=""/></div>
				<div class="register_cont_yanzheng3"><img src="" id="imgCode" onclick="changeCode()" /><a href="javascript:changeCode()" class="shuaxin"style="margin-right:10px;">刷新</a>
				<div id="codeTip" style="float:left;padding-top:10px"></div>
				</div>
			</div>

			<div class="register_cont_agree">
				<div class="register_cont_agree1"></div>
				<div class="register_cont_agree2">
					<input type="checkbox" checked="checked" id="iagree" name="iagree" style="width:12px;height:12px;margin-right: 5px;" />我同意《<a href="${ctx }/user/contract" target="_blank">一起一起投注册协议</a>》
				</div>
				<div class="register_cont_agree3"></div>
			</div>
			<div class="register_cont_button">
				<div class="register_cont_button1"></div>
				<div class="register_cont_button2">
					
					<input type="submit" name="button" id="button" class="register_cont_submit" value="下一步"  />
					
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
