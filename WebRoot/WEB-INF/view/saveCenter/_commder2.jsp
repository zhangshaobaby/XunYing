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
			if(loc=="kehu"){
				$(".user_cont_r_t_3c4").each(function(index,item){
					$(item).html($(item).attr("id")+"%")
				})
			}
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
							<li class="li_2"><a href="${ctx }/user/tuijian">我的奖励</a></li>													
							<li class="li_3 sel"><a href="${ctx }/user/tuijian">奖励明细</a></li>													
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
				<div class="user_cont_r_4" style="display:block">
					<div class="tuijian" style="display: none">
						<img src="${ctx }/images/01_03.png">
					</div>
					<table class="user_cont_r_3b" cellspacing=0>
						<tr class="tittle">
							<th class="user_cont_r_3b1">产品名称</th>
							<th class="user_cont_r_3b3">产品认购时间</th>
							<th class="user_cont_r_3b4">推荐奖励<img class="dj" onclick="$('.tuijian').toggle();" src="${ctx }/images/01_06.png"></th>
						</tr>
						<!-- 交易记录循环开始-->
						<c:forEach varStatus="i" var="item" items="${page.result}">
						<tr class="user_cont_r_t_3c" <c:if test="${i.index%2==1 }"> style="color:#353131;background-color:#f6f6f6;"</c:if>>
							<td class="user_cont_r_t_3c1a"><a href="${ctx }/project/view?id=${item.id }" target="_blank">${item.name }</a></td>
							<td class="user_cont_r_t_3c3">${fn:substring(item.start_time,0,10) }~${fn:substring(item.end_time,0,10) }</td>
							<td class="user_cont_r_t_3c4" id="<cc:toMoney var="${item.commission_agent }"/>"><cc:toMoney var="${item.commission }"/>%</td>
						</tr>
                        </c:forEach>
						<!-- 交易记录循环结束-->
					</table>
																			
					<div class="user_cont_r_2d">
						<jsp:include page="../common/page.jsp"></jsp:include>
					</div>
    			</div>	
					
</body>
</html>