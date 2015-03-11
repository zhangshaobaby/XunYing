<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	
<html lang="en">
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/css/user_my_jilu.css" rel="stylesheet" type="text/css" />
<script src="<%=path %>/js/jquery-1.7.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path %>/js/base.js"></script>
<title>1717tou.com</title>
</head>
<body  >
	<div class="user_cont_rr" style="background:#FFFFFF;">
               <div class="user_cont_rrr">
		    <div class="user_cont_r_1"><span>女神列表</span></div>
			<div class="user_cont_r_2"> 
                        <div class="user_cont_r_2b">
                        <a href="<%=path %>/activityvote/view?&currPage=${page.currPage}">
							增加
						</a>
				         <img style="cursor: pointer" onclick="bizDelAll('<%=path %>/activityvote/delmes','checkboxname')" src="<%=path %>/images/button_02.png">
						</div>
					  </div>   
                       
                       <div class="user_cont_r_3" >
						<table class="user_cont_r_3b" cellspacing=0 style="font-size:13px;">
                            <tr class="tittle">
                            <th class="user_cont_r_3b1"><input  type="checkbox" name="checkall" onchange="checkAll(this,'checkboxname')" /></th>
                            <th class="user_cont_r_3b1">女神头像</th>
                            <th class="user_cont_r_3b2">女神姓名</th>
							<th class="user_cont_r_3b2">投票数目</th>
							<th class="user_cont_r_3b2">操作</th>
                            </tr>
    						<c:forEach var="item" items="${page.result}">
                            <tr class="user_cont_r_3c">
                            <td class="user_cont_r_x_3c1"><input type="checkbox" value="${item.id }" name="checkboxname" /></td>
							<td class="user_cont_r_x_3c1"><c:if test="${not empty item.headpic}">  <img align="middle" width="80px" height="80px"  src="${picpath}/${item.headpic }" />  </c:if>    </td>
							<td class="user_cont_r_x_3c2">${item.username}</td>
							<td class="user_cont_r_x_3c2">${item.totalvote}</td>
							<td class="user_cont_r_x_3c2">
								<a href="<%=path %>/activityvote/view?id=${item.id}&currPage=${page.currPage}">
									详细信息
								</a>
							</td>
						  </tr>
						  </c:forEach>
                         </table>														
				<jsp:include page="../common/page.jsp"></jsp:include>
			</div>	
		</div>	
		
	</div>
</body>
</html>
