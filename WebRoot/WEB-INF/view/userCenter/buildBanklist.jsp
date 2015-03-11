<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/css/bank.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/base.js"></script>
<title>1717tou.com</title>
</head>
<body >
<form action="<%=path %>/user/buildBanklist" id="form1" method="post">

</form>

				<div class="user_cont_rr" style="background:#FFFFFF;">
                  <div class="user_cont_rrr">
					    <div class="user_cont_r_1"><span>银行卡</span></div>
						<div class="user_cont_r_2"> 
                            <div class="user_cont_r_2a"><a target="_blank" href="<%=path%>/huifu/UserBindCard"><img src="<%=path %>/images/bank111.png" /></a>
						     </div> 
						  </div>   
                         <div style="padding:10px 0px"> 已绑定银行卡</div>
                          <div class="user_cont_r_3">
							<table class="user_cont_r_3b" cellspacing=0>
                               <tr class="tittle">
                                <th class="user_cont_r_3b1">银行名称</th>
                                <th class="user_cont_r_3b2">银行卡号</th>
								<th class="user_cont_r_3b3">开户姓名</th>
                               </tr>
                               <!-- 交易记录循环开始-->
                               <c:forEach var="item" items="${page.result}">
                               <tr class="user_cont_r_h_3c">
								<td class="user_cont_r_h_3c1">${item.bankName }</td>
								<td class="user_cont_r_h_3c2">${item.cardNumber.substring(0,4)}*********${item.cardNumber.substring(15)}</td>
								<td class="user_cont_r_h_3c3">${item.user.realName }</td>
							  </tr>
                             </c:forEach>
                              
                              <!-- 交易记录循环结束-->
                            </table>														
							
						</div>	
					</div>	
					<jsp:include page="../common/page.jsp"></jsp:include>
				</div>
				
</body>
</html>



