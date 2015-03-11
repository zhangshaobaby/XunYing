<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<meta http-equiv="pragma" content="no-cache" />
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script src="<%=path %>/js/jquery-1.7.js" type="text/javascript"></script>
<title>1717tou.com</title>
</head>
<script type="text/javascript"> 
		var choosen_index;
	var choosen;
	var choosen_xiaoxi=0;

	$(document).ready(function(){
		jump();
		$(".user_cont_l_2b").find("li").click(function(){
			var html = $(this).html();
			var cur_index;
			 //设置choosen 为第一个菜单
			var menuclass = $(this).attr("class");

		
			$(".user_cont_l_2b").find("li").each(function(index,item){
				if(choosen_index==null&&index==0){
				 choosen=$(item);
				 choosen_index = index;
				}
				if(choosen_xiaoxi==1&&index==3){
				 choosen=$(item);
				 choosen_index = index;
				 choosen_xiaoxi=0;
				}
				if(choosen_xiaoxi==2&&index==1){
				 choosen=$(item);
				 choosen_index = index;
				 choosen_xiaoxi=0;
				}
			    if(html==$(item).html()){
					cur_index = index;
				}
			});
			//如果2次点击同一个菜单 不做变化	
			if(cur_index==choosen_index){
		     	return;
			}
		   //设置上一个被选中的菜单 样式还原为灰色
		   var lastMenuClass=choosen.attr("class");
		   if(lastMenuClass.indexOf("_h")>0){
		   	 lastMenuClass=lastMenuClass.substring(0,lastMenuClass.indexOf("_h"));
		   }
			choosen.attr("class",lastMenuClass);
			//设置当前被选中的菜单样式
           if(menuclass.indexOf("_h")==-1){
				menuclass=menuclass+"_h";
				$(this).attr("class",menuclass)
			}
			choosen=$(this);
			choosen_index=cur_index;
		});
	})
		
		
       function jump() {  
       var menueType=getParam("menueType");
       if (location.search == "?true") {
            document.getElementById("framecont").src = "<%=path%>/user/authentList"; 
            }
           else if (location.search== "?message"||menueType=="message"){
	           $(".li_3").addClass("li_3_h").removeClass("li_3");
	           $(".li_1_h").addClass("li_1").removeClass("li_1_h");
        
				choosen_xiaoxi=1;
			var currPage=getParam("currPage");
		var url="<%=path%>/user/mymessages";
		if(currPage!=null&&!currPage==""){
		url=url+"?currPage="+currPage
		}
           	 document.getElementById("framecont").src =url; 
           }else if (location.search == "?record"){
	           $(".li_6").addClass("li_6_h").removeClass("li_6");
	           $(".li_1_h").addClass("li_1").removeClass("li_1_h");
        
				choosen_xiaoxi=2;
           	 document.getElementById("framecont").src = "<%=path%>/user/recordList?type=0&randomid="+Math.random(); 
           }
           document.getElementById("framecont").height="500px";
             
        }
        
        
	function changeFrameUrl(url){
	document.getElementById("framecont").src =url;
	}
	function getParam(param){
	var url=window.parent.location.href;
	var paramUrl = url.substr(url.indexOf("?")+1,url.length).replace("#","");
	var arrParam = paramUrl.split("&"); 
	for(var i = 0; i<arrParam.length; i++){
		if(arrParam[i].split("=")[0] == param){
			return arrParam[i].split("=")[1];
		}
	}
	return "";
}
</script>
<%     double number=Math.random()*100;  %>	
<body  >
	<!-- 头部内容开始 -->
	<jsp:include page="../common/head.jsp"></jsp:include>
	<!-- 头部内容结束 -->
	
	<!-- 用户信息开始 -->
					<!-- 用户信息开始 -->
	<div class="user">			
			<div class="user_cont" id="leftcont" >
				
					<div class="user_cont_l_2">
						<div class="user_cont_l_2b">
							<ul>
								<a href="<%=path%>/user/userEstate?randomid=<%=number%>" target="framecont"><li class="li_1_h">账户总览</li></a>
								<a href="<%=path %>/user/recordList?type=0&randomid=<%=number%>" target="framecont" ><li class="li_6" >我的投资</li></a>
								<a href="<%=path%>/user/translist?randomid=<%=number%>" target="framecont" ><li class="li_2">资金记录</li></a>
								<!-- <a href="<%=path%>/user/buildBanklist?randomid=<%=number%>" target="framecont" ><li class="li_5" >银行卡</li></a> -->
								<a href="<%=path%>/user/mymessages?randomid=<%=number%>" target="framecont" ><li class="li_3" >我的消息</li></a>
								<a href="<%=path %>/user/luckyMoney?randomid=<%=number%>" target="framecont" ><li class="li_4" >我的红包</li></a>
								<a href="<%=path%>/user/tuijian?randomid=<%=number%>" target="framecont"><li class="li_5">我的推荐</li></a>
								<c:if test="${sessionScope.user.type==2}">
								<a href="<%=path%>/user/kehu?randomid=<%=number%>" target="framecont"><li class="li_5">我的客户</li></a>
								</c:if>
								<!-- <li class="li_8"><a href="user_touzi.html" target="framecont">积分记录</a></li> -->
							</ul>
						</div>
					</div>
					
					
				</div>
				<div class="user_cont_r">	
					
			     <iframe  frameborder="0" id="framecont" width="100%"  src="<%=path %>/user/userEstate?randomid=<%=number%>" name="framecont" scrolling="no" ></iframe>
				</div>
				<div style="clear:both"></div>
			</div>
		
	
	<!-- 用户信息结束 -->
		
	<!-- 底部开始 -->
	<jsp:include page="../common/foot.jsp"></jsp:include>
	<!-- 底部结束 -->
<script type="text/javascript" language="javascript">
		$("#framecont").load(function() {
    var clientHeight = $("#framecont").contents().find("body").height();
    if(clientHeight<700){
    clientHeight=700;
    }
    $(this).height(clientHeight);
    $("#leftcont").height(clientHeight);
});
</script>
	
	
	
	
</body>
</html>


