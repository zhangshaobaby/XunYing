<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx }/css/head.css">
<link rel="Shortcut Icon" href="${ctx}/images/diaosi.ico">
	<script>
		$(document).ready(function(data){
			$.post("${ctx}/user/getMessages",function(data){
				if(data!="")
				if(data>=10){
				       $("#emailpic").attr("src","${ctx }/images/icon_05.png");
                       $("#emailnum").removeClass("topemail_num").addClass("email_num");
						$("#emailnum").html("9+");					
					}else if(data>0){
					    $("#emailpic").attr("src","${ctx }/images/icon_05.png");
						$("#emailnum").html(data);
					}else {					
						$("#emailpic").attr("src","${ctx }/images/icon_05_2.png"); 		
					}
			});
			$(".weixin").hover(
				function () { 
					$(".weixin_img").attr("src","${ctx}/images/icon_04_2.png");
					$('.weixin_pop').css("display","block"); 
				},
				function () { 
					$(".weixin_img").attr("src","${ctx}/images/icon_04.png");$('.weixin_pop').css("display","none"); }
				);
				$(".weibo").hover(
					function () { 
						$(".weibo_img").attr("src","${ctx}/images/icon_02_2.png"); $('.weibo_pop').css("display","block");
					},
					function () { 
						$(".weibo_img").attr("src","${ctx}/images/icon_02.png");$('.weibo_pop').css("display","none"); 
					}
				);
				$("#emailpic").hover(
					function () { 
						if($("#emailpic").attr("src").indexOf("icon_05.png")!=-1)
							$("#emailpic").attr("src","${ctx}/images/icon_051.png");
						else
							$("#emailpic").attr("src","${ctx}/images/icon_05_3.png");
					},
					function () { 
						if($("#emailpic").attr("src").indexOf("icon_051.png")!=-1)
							$("#emailpic").attr("src","${ctx}/images/icon_05.png");
						else
							$("#emailpic").attr("src","${ctx}/images/icon_05_2.png");
					}
      			);
				$(".phonelogin").hover(function () { $('.phone_login').css("display","block"); },function () { $('.phone_login').css("display","none"); });
		});
	</script>
	<div class="newtop" style="margin:0 auto;">
		<div class="newtop1">
			<div class="newtop1_main">
				<div class="newtop1_main_l">
					<img src="${ctx}/images/icon_01.png">客服热线：
					<span class="num">400 057 3090</span>
					<span>服务时间：9:00-21:00</span>
					<span>关注我们:</span>
					<a class="weibo" href="http://weibo.com/p/1006065219541374/home?from=page_100606&mod=TAB#place" target="_blank"> <img src="${ctx }/images/icon_02.png" class="weibo_img"/></a>
					<a class="weixin"> <img src="${ctx }/images/icon_04.png" class="weixin_img"/></a>
                    <div><img class="weibo_pop" src="${ctx}/images/weibo_pop.png"/><img class="weixin_pop" src="${ctx}/images/weixin_pop.png"/></div>
				</div>
				<div class="newtop1_main_r">
				<c:choose>
					<c:when test="${sessionScope.user==null}">
						<a href="${ctx}/dologin">登陆</a>&nbsp;&nbsp;
						<a href="${ctx}/user/register">注册</a>&nbsp;&nbsp;
					</c:when>
					<c:otherwise>
					欢迎你，<span class = "welcomeAhref" ><a href="${ctx}/user/userCenter"><span>${sessionScope.user.username}</span></a></span>!
								&nbsp;&nbsp;
								<a href="${ctx}/outLogin">退出</a>&nbsp;&nbsp;
					</c:otherwise>
				</c:choose>
				<a href="http://download.1717tou.com/"><img src="${ctx}/images/icon_03.png" alt="">手机登陆</a>&nbsp;&nbsp;
				<a href="${ctx}/user/userCenter">个人中心</a>&nbsp;&nbsp;
				<a href="${ctx}/user/userCenter?message">&nbsp;&nbsp;
					<img style="margin-bottom:10px;" src="${ctx}/images/icon_05_2.png" id="emailpic" name="emailpic" alt="">
					<div class="topemail_num" id="emailnum"></div>
				</a>				
				<div><img class="phone_login" src="${ctx}/images/qidai.png"/></div>
			</div>			
		</div>
		<div class="newtop2">
			<div class="newtop2_l"><a href="${ctx }/index"><img src="${ctx }/images/logo.png"></a></div>
			<div class="newtop2_c">
				<ul>
					<li>&nbsp;&nbsp;<a href="${ctx }/index" style="color:#353131;font-size:14px;">首&nbsp;&nbsp;页</a></li>
					<li><a target="_blank" href="${ctx }/paradise" style="color:#353131;font-size:14px;">屌丝币乐园</a></li>
					<li><a target="_blank" href="${ctx }/huodong/newinfor" style="color:#353131;font-size:14px;">新手指引</a></li>
					<li><a target="_blank" href="${ctx }/huodong/anquaninfor" style="color:#353131;font-size:14px;">安全保障</a></li>
					<li><a target="_blank" href="${ctx }/huodong/about" style="color:#353131;font-size:14px;">关于我们</a></li>
					<li><a target="_blank" href="${ctx}/project/list"><img src="${ctx }/images/touzi.png" /></a></li>
				</ul>
			</div>
		
		</div>
	</div>
			<div style="clear:both"></div>
			</div>
<!-- 头部内容结束 -->