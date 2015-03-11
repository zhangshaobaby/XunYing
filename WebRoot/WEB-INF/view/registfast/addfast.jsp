<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<script type="text/javascript">
var code="";
	function add()
	{
		 var phone=$("#cmobile").val();
		 var vercode=$("#vercode").val();
		 if(phone=="")
		{
			alert("请输入手机号码");
			$("#cmobile").focus().select();
		}
		else
		{
			if(vercode=="")
			{
				alert("请输入验证码");
				$("#vercode").focus().select();
			}
			else
			{
				if(vercode==code)
				{
					$("#saveForm").submit();
				}
				else
				{
					alert("验证码输入有误，请重新输入");
					$("#vercode").focus().select();
				}
			}
		}
	}
	function sendmes()
	{
		var phone=$("#cmobile").val();
		if(phone=="")
		{
			alert("请输入手机号码");
		}
		else
		{
			$.post("<%=path %>/activityvote/phonecheck",{phone:phone},function(data){
				if(data!="")
				{
					code=data;
					alert("验证码已经发送");
				}
				else
				{
					alert("手机号码已经注册");
			 	}
			});
		}
		
	}
</script>
</head>
<body>
<div data-role="page" id="pageone" >
  <div data-role="header">
    <h1>一起一起投-注册</h1>
  </div>
  <div data-role="content">
	<form   action="<%=path %>/activityvote/registfastadd" method="post" id="saveForm" name="saveForm">
	 <div data-role="fieldcontain">
	  <label for="cmobile">手机号码(必填):</label>
	  <input type="tel"  required="required" name="cmobile" id="cmobile"  placeholder="手机号码"  data-clear-btn="true" size="11">
	  <label for="vercode">验证码(必填):</label>
	  <input type="number"  required="required" name="vercode" id="vercode"  placeholder="验证码"  data-clear-btn="true" size="6">
	  <a href="javascript:sendmes();" data-role="button"  data-inline="true">获取验证码</a>
	  <label for="hiddencode" class="ui-hidden-accessible"></label>
  		<input type="hidden" name="hiddencode" id="hiddencode" required="required">
	  <input id="sub"  type="button" data-inline="true" value="提交" onclick="add();">
	 </div>
	</form>
  </div>
  <div data-role="footer">
	 <h1>©2014一起一起投</h1>
  </div>
</div> 

</body>
</html>
