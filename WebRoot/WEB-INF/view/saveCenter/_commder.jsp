<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="网站描述" />
		<meta name="keywords" content="关键字" />
		<link href="${ctx }/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/user_my_kehu.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/user_tuijian_popup.css" rel="stylesheet" type="text/css" />
		<script src="${ctx }/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="${ctx }/js/base.js" type="text/javascript"></script>
		<title>1717tou.com</title>
	
		<script type="text/javascript">
		$(function(){
			$(".user_cont_r_3").show();
		});
		$(document).ready(function(){
			var loc = window.location.href;
			if(loc.indexOf("kehu")!=-1){
				loc="kehu";
				$(".user_cont_r_1").find("span").html("我的客户");
			}else{
				loc="tuijian";
				$(".user_cont_r_1").find("span").html("我的推荐");
			}
			$(".li_1").find("a").attr("href","${ctx }/user/"+loc);
			$(".li_2").find("a").attr("href","${ctx }/user/"+loc+"2");
			$(".li_3").find("a").attr("href","${ctx }/user/"+loc+"3");
			$(".li_4").find("a").attr("href","${ctx }/user/"+loc+"4");
			$("#form1").attr("action",window.location.href);
		});
		/*控制弹出层*/
		jQuery(document).ready(function($) {
			$('.theme-login').click(function(){
				$('.theme-popover-mask').css("display","block");
				$('.theme-popover').slideDown(200);
			})
			$('.theme-popover .close').click(function(){
				$('.theme-popover-mask').css("display","none");
				$('.theme-popover').slideUp(200);
			})
			
			$('.theme-login2').click(function(){
				$('.theme-popover-mask2').css("display","block");
				$('.theme-popover2').slideDown(200);
			})
			$('.theme-popover2 .close').click(function(){
				$('.theme-popover-mask2').css("display","none");
				$('.theme-popover2').slideUp(200);
			})
		})
		var id=0;
		function commercashall(){
			jQuery.ajax({type:"POST", url: "${ctx}/user/commercashall", success:function (result) {
				if (result ==true) {
			        window.location.reload();
				} else {
				 alert("申请失败,请稍后重试");
				}
			}});
		}
		function commercash(){
			jQuery.ajax({type:"POST", url: "${ctx}/user/commercash?id="+id, success:function (result) {
				if (result ==true) {
			        window.location.reload();
				} else {
				 alert("申请失败,请稍后重试");
				}
			}});
		}
		function setCurId(bid,pname,pamt){
			id=bid;
			$("#proname").html(pname);
			$("#brokeramt").html(pamt);
		}
		</script>
	</head>
	<body>
		<form id="form1" name="form1" action="${ctx }/user/tuijian" method="post"></form>
		<div class="user_cont_rr" style="background:#FFFFFF;">
		 	<div class="user_cont_rrr">
	  		<div class="user_cont_r_1"><span>我的奖励</span></div>
			<div class="user_cont_r_2"> 
				<div class="user_cont_r_2a">
			   		<div class="user_cont_r_2al"> 
				   		<span class="user_cont_r_2al1">累计客户人数：<span >${agentCount }</span>人</span>
				    	<span class="user_cont_r_2al2">累计客户奖励：<span >${sumbro}</span>元</span></div>
				    	<div class="user_cont_r_2ar"> 可提取奖励：<span>${sumablehqlbro}</span>元</div>
				  	</div>
					<div class="user_cont_r_2a1">
						<ul>
							<li class="li_1"><a href="${ctx }/user/kehu">用户信息</a></li>
							<li class="li_2 sel"><a href="${ctx }/user/tuijian">我的奖励</a></li>													
							<li class="li_3"><a href="${ctx }/user/tuijian">奖励明细</a></li>													
							<li class="li_4"><a href="${ctx }/user/tuijian">我的帮助</a></li>											
						</ul>
					</div>
		           	
		           		<c:choose>
		           			<c:when test="${sumablehqlbro=='0'}">
		           				<span class="user_cont_r_2bc">无可支取奖励</span>
		           			</c:when>
		           			<c:otherwise>
		           				<div class="user_cont_r_2b">
		           				<a style="COLOR:#FFF;"  class="theme-login ">提取全部奖金</a>
	           					</div>
		           			</c:otherwise> 
		           		</c:choose>
		           
		  	</div> 
			<div class="user_cont_r_3 ">
				<table class="user_cont_r_3b" cellspacing=0>
					<tr class="tittle">
						<th class="user_cont_r_3b1">客户用户名</th>
                        <th class="user_cont_r_3b2">投资项目</th>
                        <th class="user_cont_r_3b3">投资金额（元）</th>
						<th class="user_cont_r_3b4">投资日期</th>
						<th class="user_cont_r_3b4">奖励日期</th>
						<th class="user_cont_r_3b5">奖励金额（元）</th>
						<th class="user_cont_r_3b6">状态</th>
					</tr>
					<!-- 交易记录循环开始--><!--  style="color:#353131;background-color:#f6f6f6;"提取中    style="color:#353131;"<img src="${ctx }/images/tuijian2.png">-->
					<c:forEach varStatus="i" var="item" items="${page.result}">
					<tr class="user_cont_r_t_3c" <c:choose><c:when test="${item.state.id==63}">style="color:#b1b1b1;"</c:when><c:otherwise> style="color:#353131;"</c:otherwise> </c:choose> >
						<td class="user_cont_r_t_3c1">${item.investUser.username}</td>
						<td class="user_cont_r_t_3c2">${item.project.name }</td>
						<td class="user_cont_r_t_3c3">${item.transAmt}</td>
						<td class="user_cont_r_t_3c4">${fn:substring(item.createTime,0,10)}</td>
						<td class="user_cont_r_t_3c4">${fn:substring(item.rewardTime,0,10)}</td>
						<td class="user_cont_r_t_3c5">${item.brokerage}</td>
						<td class="user_cont_r_t_3c6"><c:choose><c:when test="${item.state.id==60}"  ><a onclick="setCurId('${item.id}','${item.project.name }','${item.brokerage}')" class="theme-login2"><img src="${ctx }/images/tuijian2.png"></a></c:when><c:when test="${item.state.id==61}"  >提取中</c:when><c:when test="${item.state.id==62}"  ><a  onclick="setCurId('${item.id}','${item.project.name }','${item.brokerage}')"  class="theme-login2"><img src="${ctx }/images/tuijian3.png"></a></c:when><c:when test="${item.state.id==63}"  >已提取</c:when><c:when test="${item.state.id==68}"  >用户购买中</c:when><c:when test="${item.state.id==69}"  >用户购买失败</c:when></c:choose></td>
					</tr>
					</c:forEach>
                    <!-- 交易记录循环结束-->
				</table>														
				<div class="user_cont_r_2d">
					<jsp:include page="../common/page.jsp"></jsp:include>											                            </div>
				</div>	
<!--   以下是提交全部奖金 弹出层的内容-->                         
                            <div class="theme-popover">
                                 <div class="theme-poptit">
                                      
                                      <h3>提取奖金</h3>
                                      <a href="javascript:;" title="关闭" class="close">[关闭]</a>
                                 </div>     
                                 <div class="pop_middle">
                                     <div class="pop_middle_1">
                                     ${sessionScope.user.username }您好：<br/>
                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请确认对"${fpname}"等累计项目<span style="color:#e33340;">${allablehqlbro}</span>个,共计奖励金<span style="color:#e33340;">${sumablehqlbro}</span>元的领取申请，我们将在<span style="color:#0060a5;">3</span>个工作日内确认并将资金转到您的账户，谢谢您的使用。</div>
                                    
                                  <div class="pop_middle_2">
                                  <a href="javascript:commercashall()" class="close"><img class="queren" src="${ctx }/images/product11.png"></a>
                                     <a href="javascript:;" class="close"><img class="quxiao" src="${ctx }/images/product12.png"></a>
                                   </div>  
                                   

                                 </div>
                                 </div>
                            
  <div class="theme-popover-mask"></div>
<!--   以下申请提取    弹出层-->                         
                            <div class="theme-popover2">
                                 <div class="theme-poptit2">
                                      
                                      <h3>提取奖金</h3>
                                      <a href="javascript:;" title="关闭" class="close">[关闭]</a>
                                 </div>     
                                 <div class="pop_middle2">
                                     <div class="pop_middle_1">
                                  ${sessionScope.user.username}您好：<br/>
                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请确认对"<span id="proname"></span>"奖励金<span id="brokeramt" style="color:#e33340;"></span>元的的领取申请，我们将在<span style="color:#0060a5;">3</span>个工作日内确认并将资金转到您的账户，谢谢您的使用。</div>
                                    
                                  <div class="pop_middle_2">
                                  <a href="javascript:commercash()" class="close"><img class="queren" src="${ctx }/images/product11.png"></a>
                                     <a href="javascript:;" class="close"><img class="quxiao" src="${ctx }/images/product12.png"></a>
                                   </div>  
                                   

                                 </div>
                                 </div>
                            
  <div class="theme-popover-mask2"></div>
 <!-- 弹出框结束-->
						</div>	
                   </div>	
					
</body>
</html>