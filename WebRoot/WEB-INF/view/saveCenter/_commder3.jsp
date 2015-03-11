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
				$("#keDiv").show();
			}else{
				loc="tuijian";
				$(".user_cont_r_1").find("span").html("我的推荐");
				$("#tuiDiv").show();
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
							<li class="li_2"><a href="${ctx }/user/tuijian">我的奖励</a></li>													
							<li class="li_3"><a href="${ctx }/user/tuijian">奖励明细</a></li>													
							<li class="li_4 sel"><a href="${ctx }/user/tuijian">我的帮助</a></li>											
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
				<div class="user_cont_r_5" id="tuiDiv">
					<img src="${ctx }/images/tuijian_2.png"/>
					<div class="nr">
						注释：“我”推荐用户甲注册一起一起投（甲注册时，添加介绍人手机是“我”在一起一起投注册的手机号），当甲在平台上成功购买信满盈、资涨通、金多宝的任何理财产品时，平台将给予“我”相应的推荐奖励，推荐奖励是其每笔投资金额0.1%的现金奖励，多次投资，奖励金额也将累计计算，可在“我的推荐”查看详情，并随时申请提取。
					</div>
					<div style="clear:both;"></div>	
				</div>
				<div class="user_cont_r_5" id="keDiv">
					<img src="${ctx }/images/kehu_2.png"/>
					<div class="nr">
						注释：“我”（经纪人）推荐用户甲（普通用户）注册一起一起投（甲注册时，介绍人手机添加的是“我”在一起一起投注册的手机号），即默认“我”是甲的理财顾问，当甲在一起一起投平台上成功购买除屌丝宝外的任何理财产品时，平台将给予“我”相应的推荐奖励和服务奖励，推荐奖励是其每笔投资金额0.1%的现金奖励，服务奖励是其每笔投资金额0.2%的现金奖励，多次投资，奖励金额也将累计计算，可在“我的推荐/客户”查看详情，并随时申请提取；当我的客户甲再推荐用户乙（普通用户）注册一起一起投（乙注册时，介绍人手机添加的是甲在一起一起投注册的手机号），平台将默认“我”同时是乙的理财顾问，乙在平台成功购买除屌丝宝外的任何理财产品时，平台会给予甲相应的推荐奖励，并给予“我”相应的服务励奖，可在“我的客户”查看详情，并随时申请提取。
             		</div>
					<div style="clear:both;"></div>	
				</div>
</body>
</html>