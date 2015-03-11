<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>实名认证</title>
    
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
		<Script src="<%=path%>/js/realAuthent.js"></Script>
  </head>
  
  <body>
   <div style="background:#FFFFFF;">
					<div class="user_cont_r_1"><span>安全中心</span></div>
					<div class="user_cont_r_2">
						<div class="user_cont_r_2b">

							
							<div class="user_cont_r_2b1">
								<div class="user_cont_r_2b1a"><img src="images/icon_false.png" /></div>
								<div class="user_cont_r_2b1b">实名认证</div>
								<div class="user_cont_r_2b1c">充值前，必须完成实名认证</div>
								<div class="user_cont_r_2b1d"></div>
							</div>
							<!-- 内容开始 -->
							<div class="user_cont_r_2b_name">
									<div class="user_cont_r_2b_name1">
								<form name="form_anquan1" method="post" action="<%=path%>/user/identifAuthent" id="form_anquan1">
									<div class="user_cont_r_2b_name1_main">
										<div class="user_cont_r_2b_name1_main1">
											<div class="user_cont_r_2b_name1_main1_l">用户名</div>
											<div class="user_cont_r_2b_name1_main1_c">${sessionScope.user.username}</div>
											<div class="user_cont_r_2b_name1_main1_r"></div>
										</div>
										
										<div class="user_cont_r_2b_name1_main1">
											<div class="user_cont_r_2b_name1_main1_l">真实姓名</div>
											<div class="user_cont_r_2b_name1_main1_c"><input type="text" name="realName" id="us" /></div>
											<div class="user_cont_r_2b_name1_main1_r"><div id="usTip"></div></div>
										</div>
										
										<div class="user_cont_r_2b_name1_main1">
											<div class="user_cont_r_2b_name1_main1_l">身份证号码</div>
											<div class="user_cont_r_2b_name1_main1_c"><input name="identification" type="text" id="sfzh" /></div>
											<div class="user_cont_r_2b_name1_main1_r"><div id="sfzhTip"></div></div>
										</div>
										
										<div class="user_cont_r_2b_name1_main2">
											<div class="user_cont_r_2b_name1_main2_l"></div>
											<div class="user_cont_r_2b_name1_main2_r">
												<input type="checkbox" checked="checked"  id="agree1" />我已经同意并签署《一起投服务协议》<br />
												<input type="checkbox" checked="checked"  id="agree2" />我已经同意并签署《委托收付资金协议》
											</div>
										</div>
										
										<div class="user_cont_r_2b_name1_main3">
											<div class="user_cont_r_2b_name1_main3_l"></div>
											<div class="user_cont_r_2b_name1_main3_r">
												<input type="submit" value="提交实名认证" /> <a href="#">上传认证证件</a>
											</div>
										</div>
										
									</div>
								</form>
								</div>
								<div class="user_cont_r_2b_name2">
									<ul>
										<li><span style="color:#ea4c59; font-weight:bold;">温馨提示</span></li>
										<li>1.请谨慎填写身份验证信息，因为调用公安局的验证接口需要支付5元费用，爱投资会为用户垫付首次验证费用</li>
										<li>2.您还可选择通过上传身份证的形式通过身份验证，通过 上传证件认证 完成实名认证，然后等待客服人员审核通过</li>
										<li>3.未满18周岁，实名认证无法通过</li>
										<li>4.爱投资会对用户的所有资料进行严格保密</li>
										<li>5.实名认证过程遇到问题，请联系客服：400-000-0000</li>
									</ul>
								</div>
								
							</div>
							
							
								<!-- 内容结束 -->
							
						</div>
					
					</div>
					
				</div>
  </body>
</html>
