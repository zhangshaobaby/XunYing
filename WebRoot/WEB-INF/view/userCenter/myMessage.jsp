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
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/css/user_my_jilu.css" rel="stylesheet" type="text/css" />
<script src="<%=path %>/js/jquery-1.7.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path %>/js/base.js"></script>
<title>1717tou.com</title>
</head>
<script type="text/javascript">
function haveLook(haveLook){
		 $("#haveLook").val(haveLook);
		 document.forms[0].submit();
		}
function sethaveLook(){
	jQuery.ajax({type:"POST", url: "<%=path %>/user/setMesHaveLook", success:function (result) {
		if (result ==true) {
	        window.location.reload();
		} else {
		}
	}});
}
		
$(function(){
var haveLook="${haveLook}";
	$(".user_cont_r_2a li").each(function(index,item){
				var oldclass=$(item).attr("class");
				if(oldclass=="li_1"&&(haveLook==null||haveLook=="")){
				 $(item).addClass("sel");
				}else if(oldclass=="li_2"&&(haveLook=="1")){
				 $(item).addClass("sel");
				}
				else if(oldclass=="li_3"&&(haveLook=="0")){
				 $(item).addClass("sel");
				}
			});
})
</script>
<body  >
	<div class="user_cont_rr" style="background:#FFFFFF;">
               <div class="user_cont_rrr">
		    <div class="user_cont_r_1"><span>我的消息</span></div>
			<div class="user_cont_r_2"> 
                      <div class="user_cont_r_2a">
                        <ul>
					
                             <li class="li_1"><a href="javascript:haveLook('')" >全部消息</a></li>
					<li class="li_2"><a href="javascript:haveLook('1')" >已读消息</a></li>
					<li class="li_3"><a href="javascript:haveLook('0')" >未读消息</a></li>																
				</ul>  
                     
                        </div>
                        <div class="user_cont_r_2b">
		         <img style="cursor: pointer" onclick="bizDelAll('<%=path %>/user/delmes','checkboxname')" src="<%=path %>/images/button_02.png"> <img style="cursor: pointer" onclick="sethaveLook()" src="<%=path %>/images/button_03.png">
				</div>
			  </div>   
                       
                       <div class="user_cont_r_3" >
				<table class="user_cont_r_3b" cellspacing=0 style="font-size:13px;">
                            <tr class="tittle">
                             <th class="user_cont_r_3b1"><input  type="checkbox" name="checkall" onchange="checkAll(this,'checkboxname')" /></th>
                             <th class="user_cont_r_3b2">消息类型</th>
					<th class="user_cont_r_3b3">消息内容</th>
					<th class="user_cont_r_3b4">发送时间</th>
					<th class="user_cont_r_3b5">状态</th>
                            </tr>
                            <!-- 交易记录循环开始-->
                            <form  method="post"  action="<%=path %>/user/mymessages">
                            <input type="hidden"  name="haveLook"  value="${haveLook}" id="haveLook"/>  
                            </form>
    <c:forEach var="item" items="${page.result}">
                            <tr class="user_cont_r_3c">
					<td class="user_cont_r_x_3c1"><input type="checkbox" value="${item.id }" name="checkboxname" /></td>
					<td class="user_cont_r_x_3c2">${item.type.dictName }</td>
					<td class="user_cont_r_x_3c3">
						<a target="_blank" href="<%=path %>/user/mesDetail?id=${item.id}&currPage=${page.currPage}">
							<cc:subLength var ="${item.content}" length="11"/>
						</a>
					</td>
					<td class="user_cont_r_x_3c4">${item.createTime}</td>
					<td class="user_cont_r_x_3c5"><c:choose><c:when test="${item.haveLook==0}">未读</c:when><c:otherwise>已读</c:otherwise></c:choose></td>
				  </tr>
				  </c:forEach>
                           <!-- 交易记录循环结束-->
                         </table>														
				<jsp:include page="../common/page.jsp"></jsp:include>
			</div>	
		</div>	
		
	</div>
</body>
</html>
