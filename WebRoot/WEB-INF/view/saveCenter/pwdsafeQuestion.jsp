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
    
    <title>更改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/index.css">
	<link href="<%=path%>/css/user_anquan.css" rel="stylesheet" type="text/css" />
	<Script src="<%=path%>/js/jquery-1.7.js"></Script>
	<Script src="<%=path%>/js/select.js"></Script>

  </head>  

  <body>
 <!-- 用户信息右部信息开始 -->
				<div style="background:#FFFFFF;">
					<div class="user_cont_r_1"><span>安全中心</span></div>
					<div class="user_cont_r_2">
						<div class="user_cont_r_2b">

							
							<div class="user_cont_r_2b1">
								<div class="user_cont_r_2b1a"><img src="images/icon_false.png" /></div>
								<div class="user_cont_r_2b1b">密保问题</div>
								<div class="user_cont_r_2b1c">轻松找回登录密码</div>
								<div class="user_cont_r_2b1d"></div>
							</div>
							<!-- 内容开始 -->
							<div class="user_cont_r_2b_pass">
								<div class="user_cont_r_2b_mibao1">
								<form name="form_anquan5" id="form_anquan5"  method="post" action="<%=path%>/user/savePwdQuest">
									<input type="hidden" name="user.id" value="${sessionScope.user.id}" />
									<input type="hidden" name="id" value="${question.id}" />
									<div class="user_cont_r_2b_mibao1_main">
										<div class="user_cont_r_2b_mibao1_main1">
											<div class="user_cont_r_2b_mibao1_main1_l"></div>
											<div class="user_cont_r_2b_mibao1_main1_r">请牢记密码问题与答案，必要时可快速找回密码</div>
										</div>
										
										<div class="user_cont_r_2b_mibao1_main1">
											<div class="user_cont_r_2b_mibao1_main1_l">请选择问题</div>
											
												
											<div class="user_cont_r_2b_mibao1_main1_c">
												<select name="question" id="question">
												<c:forEach var="op" items="${questions}">
												 <option <c:if test="${op.id==question.question}">selected</c:if>  value="${op.id}">${op.dictName}</option>									
												</c:forEach>
												</select>
											</div>
												
											
											<script type="text/javascript">
  													loadSelect(document.form_anquan5.question);
											</script>
										</div>
										
										<div class="user_cont_r_2b_mibao1_main1">
											<div class="user_cont_r_2b_mibao1_main1_l">请输入答案</div>
											<div class="user_cont_r_2b_mibao1_main1_r"><input name="answer"  value="${question.answer}"/></div>
										</div>
										
										<div class="user_cont_r_2b_mibao1_main3">
											<div class="user_cont_r_2b_mibao1_main3_l"></div>
											<div class="user_cont_r_2b_mibao1_main3_r">
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
				<!-- 用户信息右部信息结束 -->
  </body>
</html>
