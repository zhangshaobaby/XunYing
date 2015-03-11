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
		$(document).ready(function(){
			var loc = window.location.href;
			if(loc.indexOf("kehu")!=-1){
				loc="kehu";
				$(".user_cont_r_1").find("span").html("我的客户");
			}else{
				loc="tuijian";
				$(".user_cont_r_1").find("span").html("我的推荐");
			}
			$("#form1").attr("action",window.location.href);
			$(".li_1").find("a").attr("href","${ctx }/user/"+loc);
			$(".li_2").find("a").attr("href","${ctx }/user/"+loc+"2");
			$(".li_3").find("a").attr("href","${ctx }/user/"+loc+"3");
			$(".li_4").find("a").attr("href","${ctx }/user/"+loc+"4");
		});
		</script>
	</head>
	<body>
		<form name="form1" id="form1" action="${ctx }/user/tuijian" method="post"></form>
		<div class="user_cont_rr" style="background:#FFFFFF;">
		 	<div class="user_cont_rrr">
	  		<div class="user_cont_r_1"><span>我的客户</span></div>
			<div class="user_cont_r_2"> 
				<div class="user_cont_r_2a">
			   		<div class="user_cont_r_2al"> 
				   		<span class="user_cont_r_2al1">累计客户人数：<span >${agentCount }</span>人</span>
				    	<span class="user_cont_r_2al2">累计客户奖励：<span >${sumbro}</span>元</span></div>
				    	<div class="user_cont_r_2ar"> 可提取奖励：<span>${sumablehqlbro}</span>元</div>
				  	</div>
					<div class="user_cont_r_2a1">
						<ul>
							<li class="li_1 sel"><a href="${ctx }/user/kehu">用户信息</a></li>
							<li class="li_2"><a href="${ctx }/user/tuijian">我的奖励</a></li>													
							<li class="li_3"><a href="${ctx }/user/tuijian">奖励明细</a></li>													
							<li class="li_4"><a href="${ctx }/user/tuijian">我的帮助</a></li>													
						</ul>
					</div>
		  	</div> 
                          
                        
			<!-------------------------------用户信息层-----------------------------------------  -->   
           	<div class="tables">  
            	<div class="user_cont_r_31 ">
					<table class="user_cont_r_3b" cellspacing=0>
						<tr class="tittle">
							<th class="user_cont_r_3b1">用户名</th>
							<th class="user_cont_r_3b3">姓名</th>
							<th class="user_cont_r_3b4">手机号</th>
							<th class="user_cont_r_3b5">注册时间</th>
                     	</tr>
						<!-- 交易记录循环开始-->
						<c:forEach varStatus="i" var="item" items="${page.result}">
						<tr class="user_cont_r_t_3c" <c:if test="${ i.index%2==1}"> style="color:#353131;background-color:#f6f6f6;"</c:if>>
							<td class="user_cont_r_t_3c1">${item.username }</td>
							<td class="user_cont_r_t_3c3">${item.realName }</td>
							<td class="user_cont_r_t_3c4">${item.phone }</td>
							<td class="user_cont_r_t_3c5">${fn:substring(item.createTime,0,10) }</td>
						</tr>
						</c:forEach>
						<!-- 交易记录循环结束-->
					</table>														
					<div class="user_cont_r_2d">
						<jsp:include page="../common/page.jsp"></jsp:include>														                            
					</div>
				</div>	
 <!--   -----------------------------我的奖励层-----------------------------------------  -->   
                       
                     
                  <!--   以下是提交全部奖金 弹出层的内容-->                         
                            <div class="theme-popover">
                                 <div class="theme-poptit">
                                      
                                      <h3>提取奖金</h3>
                                      <a href="javascript:;" title="关闭" class="close">[关闭]</a>
                                 </div>     
                                 <div class="pop_middle">
                                     <div class="pop_middle_1">
                                     ${sessionScope.user.username }您好：<br/>
                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请确认对"${fpname}"等累计项目<span style="color:#e33340;">${countablehqlbro}</span>个,共计奖励金<span style="color:#e33340;">${sumablehqlbro}</span>元的领取申请，我们将在<span style="color:#0060a5;">3</span>个工作日内，将资金转到您的一账户，并及时短信通知，请注意查收。</div>
                                    
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
                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请确认对"<span id="proname"></span>"奖励金<span id="brokeramt" style="color:#e33340;"></span>元的的领取申请，我们将在<span style="color:#0060a5;">3</span>个工作日内，将资金转到您的一账户，并及时短信通知，请注意查收。</div>
                                    
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
					</div>	
					
</body>
</html>