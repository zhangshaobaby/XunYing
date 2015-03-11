<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>一起一起投_领跑互联网理财，安全诚信,严格风控,互联网投资新选择。这是让您放心的互联网金融平台</title>
<meta name="keywords" content="一起一起投,1717tou,屌丝宝,安全理财,互联网理财,互联网金融,互联网投资,安全投资,网络理财,网络金融,个人理财,互联网信托,资产管理,有限合伙"/>
<meta name="description" content="一起一起投提供安全,可靠的互联网理财平台.严格风控,第三方资金托管给予您更安心的投资理财环境.灵活投资方案,低投资门槛,更好的理财方案,为您量身打造有保障互联网信托、有限合伙、资产管理多种安全理财投资,诚信的网络理财平台.1717TOU,我们一起一起投" />
<meta name="author" content="北京凌云吉信信息技术有限公司" />
<meta property="wb:webmaster" content="6fe1fec39faa2035" />
<link href="${ctx}/css/index1.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/index_popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var environment = {
	userVo : '',
	role : '',
	userId : '',
	basePath : '',
	userCash : '',
	globalPath : 'localhosot/1717tou'
};
</script>

<script src="${ctx }/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx }/js/jquery.knob.js"></script>
<!--[if lt IE 9]>
<script src="${ctx }/js/html5.js" type="text/javascript"></script>
<script src="${ctx }/js/excanvas.js" type="text/javascript"></script>
<script src="${ctx }/js/excanvas.compiled.js" type="text/javascript"></script>
<![endif]-->
<script src="/js/myknob.js"></script>
<script>
	$(function() {
         $(window).on('load', function () {
                $('.knob').knob();
                $(".percentText").css("margin-top","-45px");
            });
       });
</script>
<script src="${ctx }/js/global-1.1.0.min.js" type="text/javascript"></script>
<script src="${ctx }/js/home.js" type="text/javascript"></script>
<!--返回顶部的特效-->
 <script type="text/javascript">
 /*控制菜单项屌丝宝的样式*/
$(function(){
	$(".img1").mouseover(function(){
	$(".img3").css("z-index","1");
	});
	$(".img4").mouseover(function(){
	$(".img3").css("z-index","5");
	$(".img2").css("z-index","4");
	});
	$(".img1").click(function(){
	$(".img3").css("z-index","1");
	});
	$(".img4").click(function(){
	$(".img3").css("z-index","5");
	$(".img2").css("z-index","4");
	});
	})
 	/*弹出框内容*/
$(function(){
	current = 1;
	button = 1;
	images = 3;
	width = 710;
	
	$('#p1').animate({"left":"0px"},400,"swing");
	
	$("#next").click(function(){
		button = current;
		current++
		if(current==(images + 1)){
			current = 1
		}
		animateLeft(current, button)
	});
	
	$("#previous").click(function(){
		button = current;
		current--
		if(current == 0){
			current = images
		}
		animateRight(current, button)
	});
	
	$("#abuttons li").mouseover(function(){
		button = current;
		clickButton = $(this).attr('id');
		current = parseInt(clickButton.slice(1));
		if(current > button){
			animateLeft(current, button)
		}
		if(current < button){
			animateRight(current, button)
		}
	});
	
	function animateLeft(current, button){
		$('#p' + current).css("left", width + "px");
		$('#p' + current).animate({"left": "0px"},400,"swing");
		$('#p' + button).animate({"left": -width + "px"},400,"swing");
		setbutton()
	}
	
	function animateRight(current, button) {
		$('#p' + current).css("left", -width + "px");
		$('#p' + current).animate({"left": "0px"},400,"swing");
		$('#p' + button).animate({"left": width + "px"},400,"swing");
		setbutton()
	}
	
	function setbutton(){
		$('#a' + button).children("a").removeClass("current");
		$('#a' + current).children("a").addClass("current");
	}

});

/*控制弹出层*/
jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover').slideDown(200);
	})
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover').slideUp(200);
	})
	
 /*控制一键安装到手机弹出层*/
	$('.theme-login2').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover2').slideDown(200);
	})
	$('.theme-popover2 .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover2').slideUp(200);
	})
	

})

/*控制弹出层*/
jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover').slideDown(200);
	})
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover').slideUp(200);
	})

})
	$(document).ready(function(){
	//首先将#back-to-top隐藏
	$("#back-to-top").hide();
	//当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
	$(function () {
		$(window).scroll(function(){
		if ($(window).scrollTop()>100){
		$("#back-to-top").fadeIn(500);
		}
		else
		{
		$("#back-to-top").fadeOut(500);
		}
		});
		//当点击跳转链接后，回到页面顶部位置
		$("#back-to-top").click(function(){
		$('body,html').animate({scrollTop:0},100);
		return false;
		});
		});
		});
  </script>


	<script type="text/javascript">
		var interval ;
		var day=0,
			hour=0,
			minute=0,
			second=0;//时间默认值	
			intDiff = 0;	
		function timer(){
			//if(intDiff>0)
			interval = window.setInterval(function(){
					$(".uncount").each(function(index,item){
						var relea = $(item).attr("id");
						if(relea!=""&&!isNaN(relea)){
							var intDiff = parseInt(relea)/1000;
							if(intDiff>0){
								day = Math.floor(intDiff / (60 * 60 * 24));
								hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
								minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
								second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
								hour=hour<10?("0"+hour):hour;
								minute=minute<10?("0"+minute):minute;
								second=second<10?("0"+second):second;
								$(item).html("还剩"+day+"天  "+hour+":"+minute+":"+second+"结束");
								$(item).attr("id",(intDiff-1)*1000);
							}else{
								$(item).attr("id","over");
								$(item).html("已结束");
							}
						}
					});	
			},1000);
		}
		$(document).ready(function(){
			timer();
			var strCookie=document.cookie; 
			var account= strCookie.indexOf("unnameTag");
			if(account==-1){
				var date = new Date();
				date.setTime(date.getTime() + (60 * 60 * 1000 * 24));
//				date.setTime(date.getTime() + (10000));
				//$.cookie('example', 'foo', { expires: date });
				document.cookie="unnameTag=tag; expires="+date.toGMTString(); 
				$("#unnameTag").click();
			}
		})
		
	</script>
<title>1717tou.com</title>
</head>

<body>
	<!-- 幻灯片开始 -->
		<%@ include file="common/head_html.jsp"%>
		
<div class="feat-area">
    <ul class="slider" id="slider">
		<c:forEach items="${advertList}" var="advert">
    		<li data-opacity="0.2" style="background:${advert.color} url(${picpath}/${advert.imgUrl }) no-repeat center center"><a href="${advert.href }" target="_blank">${advert.name }</a></li>
    	</c:forEach>
    </ul>
    <div class="gain" id="userGain">
      <div class="gain-cont">
		  <p class="reg">
		  	<c:choose>
		  		<c:when test="${pList4[0].flag!=100&&pList4[0].state==0&&pList4[0].rate_type==1}">
		  			<a href="${ctx }/project/view?id=${pList4[0].id }"><img src="${ctx }/images/indexgai2.png" /></a>
		  		</c:when>
		  		<c:otherwise>
		  			<img src="${ctx }/images/indexgai2_2.png" />
		  		</c:otherwise>
		  	</c:choose>
		  </p>
		</div>
		<div class="gain-bottom">
			<div class="gain-bottomr uncount" id="${pList4[0].recommend }" ></div>
		</div>
    </div>
</div>
	<!-- 幻灯片结束 -->	
	<div class="main">
	<!-- 首页内容1开始 -->
		<div class="main1">
			<div class="main1_1">
				<img src="${ctx }/images/index2_01.png" alt="" />
				<div class="main1_11">以“投资人第一”为原则，按照风控标准帮客户严格把关每个项目；平台不设资金池、不做关联交易，以投资人利益为最高优先，立场一致。</div>
			</div>
			<div class="main1_1">
				<img src="${ctx }/images/index2_02.png" alt="" />
				<div class="main1_11">完全诚实披露产品信息，真正做到信息透明，明示风险、明确资金管理流向，使投资人可随时查询投资项目相关信息，真正实现安心投资。</div>
			</div>
			<div class="main1_1">
				<img src="${ctx }/images/index2_03.png" alt="" />
				<div class="main1_11">专业金融产品研究团队联合国内知名大型国企金融投资机构，一起为帮助投资者全方位监控投资项目，实现最优化收益而不断努力。</div>
			</div>
			<div class="main1_2">
				<img src="${ctx }/images/index2_04.png" alt="" />
				<div class="main1_11">联合多家知名金融机构，真正地帮助用户在降低优秀投资项目门槛的同时，高度分散投资的风险，力求获得更高、更加安全的收益。</div>
			</div>
		</div>
		<!-- 首页内容1结束 -->
		<div class="product">

		
		<div class="jin_zhan">
			<img src="${ctx }/images/indexgai4.png" />
			<div class="jin_product_cont2_nocont_a1">
				<span style="margin-left:30px;">强化风控，更高一级的投资决策选择</span><span><a href="${ctx }/project/list?type=2" style="color:#ffffff">更多>></a></span>
			</div>
			<div class="list1">
				<c:forEach items="${pList3}" var="p" varStatus="index">
						<div class="list1_1">
						  <div class="top">
						  	<c:choose>
						  		<c:when test="${p.code!=null&&p.code!=''}">
						  			<img src="<cc:picHost/>/${p.code }" onerror="this.src='${ctx }/images/company_default.png'"/>
						  		</c:when>
						  		<c:otherwise><img src="${ctx }/images/product1.png" alt=""/></c:otherwise>
						  	</c:choose>
						  	<c:choose>
								<c:when test="${p.xtid!=null}">
									${p.xt_name}
								</c:when>
								<c:when test="${p.dbid!=null}">
									${p.db_name}
								</c:when>
							</c:choose>	
							  |
							${p.repay_content }
						  </div>
						  <h3 style="word-wrap:break-word; word-break:break-all;">
						  	<a href = "${ctx}/project/view?id=${p.id }">
						  		<cc:subLength var = "${p.name }" length="12"/>
						  	</a>
				  		  </h3>
						  <div class="con1" style="word-wrap:break-word; word-break:break-all;">
							<cc:subLength var = "${p.summary }" length="16"/>
						  </div>
						  <div class="con2">
						  	<input class="knob" data-height="74" data-width="74" data-fgColor="#eb7c23" data-thickness=".15" data-readOnly=true value="${p.flag}" data-min="0" data-max="100.01" style="display:none; width:0px;visibility:hidden;"/>
							<div class="percentText">${p.flag}%</div>
						  </div>
						  <div class="con3">
						  	<table cellspacing="0">
                                  <tr><td>预计年化率</td><td>项目期限</td><td>项目金额</td></tr>
                                  <tr>
                                  <td><span style="color:#e33340;">${p.rate }%</span></td>
                                  <td><span style="color:#0060a5;">${p.time_limit }个月</span></td>
                                  <td>
                                  	<span style="color:#e33340;">
								<c:choose>	
									<c:when test="${p.total_money>=10000}">${p.total_money/10000}万</c:when>
									<c:otherwise>${p.total_money}</c:otherwise>
								</c:choose>元
								</span></td></tr>
                                  </table>
						  </div>
						  <div class="con4">
						  	<!-- 很多按钮 -->
					  		<a href="${ctx }/project/view?id=${p.id }">
							   <c:choose>
							   		<c:when test="${p.create_username==1}">
										<img src="${ctx }/images/indexgai8_5.png" alt=""/>
							   		</c:when>
									<c:when test="${p.flag!=100&&p.state==0&&p.rate_type==1}">
										<img src="${ctx }/images/indexgai8_4.png" alt=""/>
									</c:when>
									<c:when test="${p.flag==100&&p.state==0}">
										<img src="${ctx }/images/p4.png" alt=""/>
									</c:when>
									<c:when test="${p.state==0&&p.rate_type!=1}">
										<img src="${ctx }/images/p5.png" alt=""/>
									</c:when>
									<c:when test="${p.state==3}">
										<img src="${ctx }/images/p3.png" alt=""/>
									</c:when>
									<c:when test="${p.state==1}">
										<img src="${ctx }/images/indexgai8_3.png" alt=""/>
									</c:when>
									<c:when test="${p.state==4}">
										<img src="${ctx }/images/indexgai8_2.png" alt=""/>
									</c:when>
									<c:when test="${p.state==2}">
										<img src="${ctx }/images/p8.png" alt=""/>
									</c:when>
								</c:choose>
							</a>
						  	<!-- 预售字幕 -->
						  	<c:choose>
								   		<c:when test="${p.create_username==1}">
											<span style="color:#E33340;" id="${p.recommend }" class="uncount">
											  	<c:if test="${p.create_username==1}">
											  	${p.start_time }开售
											  	</c:if>
										  	</span>
								   		</c:when>
										<c:when test="${p.flag!=100&&p.state==0&&p.rate_type==1}">
											<span style="color:#E33340;" id="${p.recommend }" class="uncount">
											  	<c:if test="${p.create_username==1}">
											  	${p.start_time }开售
											  	</c:if>
										  	</span>
										</c:when>
								  	</c:choose>
						  </div>
					  </div>
					  <c:if test="${index.index!=3}">
					  	<div class="line_y"></div>
					  </c:if>
					</c:forEach>
				<div style="clear:both"></div>
			</div>
		</div>

		
		
		<div class="main3">
			
		</div>	
		<div class="zi_zhan">
			<img src="images/indexgai5_3.png" />
	   		<div class="diao_product_cont2_nocont_a1 " >
	         <span style="margin-left:30px;">预计到期收益 8%-11%；开放赎回，安全放心；限量发行，先到先得</span><span><a href="${ctx }/project/list?type=3" style="color:#ffffff">更多>></a>
	        </div>
	        <div class="list2">
	        	<c:forEach items="${pList4}" var="p" varStatus="index">
                        	<div class="list1_bao">
								  <div class="tit"><a href="${ctx }/project/view?id=${p.id }">${p.name }</a></div>
								  <div class="con2">
								  	<input class="knob" data-height="74" data-width="74" data-fgColor="#eec433" data-thickness=".15" data-readOnly=true value="${p.flag}" data-min="0" data-max="100.01" style="display:none; width:0px;visibility:hidden;"/>
									<div class="percentText">${p.flag}%</div>
								  </div>
                                   <div class="con3">
								  	<table cellspacing="0">
                                    <tr><td>预计年化率</td><td>项目期限</td><td>项目金额</td></tr>
                                    <tr>
                                    	<td><span style="color:#e33340;">${p.rate }%</span></td>
                                    	<td><span style="color:#0060a5;">${p.time_limit }个月</span></td>
                                    	<td>
                                    		<span style="color:#e33340;">
                                    			<c:choose>	
													<c:when test="${p.total_money>=10000}">${p.total_money/10000}万</c:when>
													<c:otherwise>${p.total_money}</c:otherwise>
												</c:choose>元
											</span>
										</td>
									</tr>
                                    </table>
								  </div>
                                   <div class="con4">
                                   <a href="${ctx }/project/view?id=${p.id }">
									   <c:choose>
									   		<c:when test="${p.create_username==1}">
												<img src="${ctx }/images/diaosi3_1.png" alt=""/>
									   		</c:when>
											<c:when test="${p.flag!=100&&p.state==0&&p.rate_type==1}">
												<img src="${ctx }/images/diaosi3.png" alt=""/>
											</c:when>
											<c:when test="${p.flag==100&&p.state==0}">
												<img src="${ctx }/images/p4.png" alt=""/>
											</c:when>
											<c:when test="${p.state==0&&p.rate_type!=1}">
												<img src="${ctx }/images/p5.png" alt=""/>
											</c:when>
											<c:when test="${p.state==3}">
												<img src="${ctx }/images/p3.png" alt=""/>
											</c:when>
											<c:when test="${p.state==1}">
												<img src="${ctx }/images/indexgai8_3.png" alt=""/>
											</c:when>
											<c:when test="${p.state==4}">
												<img src="${ctx }/images/indexgai8_2.png" alt=""/>
											</c:when>
											<c:when test="${p.state==2}">
												<img src="${ctx }/images/p8.png" alt=""/>
											</c:when>
										</c:choose>
									</a>
                                   <c:choose>
								   		<c:when test="${p.create_username==1}">
											<span style="color:#E33340;" id="${p.recommend }" class="uncount">
											  	<c:if test="${p.create_username==1}">
											  	${p.start_time }开售
											  	</c:if>
										  	</span>
								   		</c:when>
										<c:when test="${p.flag!=100&&p.state==0&&p.rate_type==1}">
											<span style="color:#E33340;" id="${p.recommend }" class="uncount">
											  	<c:if test="${p.create_username==1}">
											  	${p.start_time }开售
											  	</c:if>
										  	</span>
										</c:when>
								  	</c:choose>
                                   </div>
							</div>
							<c:if test="${index.index!=3}">
							  	<div class="line_y1"></div>
						  	</c:if>
                            </c:forEach>
	        </div>
		</div>
		<!-- 首页内容2_2开始 -->
        <div class="main2_2">
       		<div class="main2_2l">
            <span class="more1"><a href="${ctx }/news/list?type=65" target="_blank">更多>></a></span>
              <div class="main2_2l1">平台公告</div>
              <ul class="main2_2l2">
              	<c:forEach items="${nList1}" var="news">
				<li>
					<a href="${news.href }" title = "${news.name }" target="_blank">
						<span class="a1">
							<cc:subLength var = "${news.name }"  length = "12"/>
						</span>
						<span class="atime">${fn:substring(news.createTime,0,10) }</span>
					</a>
				</li>
				</c:forEach>
              </ul>
            </div>
            <div class="main2_2m">
            <span class="more2"><a href="${ctx }/news/list?type=66" target="_blank">更多>></a></span>
              <div class="main2_2m1">产品公告</div>
              <ul class="main2_2l2">
              	<c:forEach items="${nList2}" var="news">
				<li>
					<a href="${news.href }" title = "${news.name }" target="_blank">
						<span class="a1">
							<cc:subLength var = "${news.name }"  length = "12"/>
						</span>
						<span class="atime">${fn:substring(news.createTime,0,10) }</span>
					</a>
				</li>
				</c:forEach>
              </ul>
              </div>
            <div class="main2_2r">
            	<span class="more3"><a href="${ctx }/news/list?type=67" target="_blank">更多>></a></span>
              <div class="main2_2r1">媒体新闻</div>
              <ul class="main2_2l2">
              	<c:forEach items="${nList3}" var="news">
				<li>
					<a href="${news.href }" title = "${news.name }" target="_blank">
						<span class="a1">
							<cc:subLength var = "${news.name }"  length = "12"/>
						</span>
						<span class="atime">${fn:substring(news.createTime,0,10) }</span>
					</a>
				</li>
				</c:forEach>
              </ul>
            </div>
        </div>	
		<!-- 首页内容2_2结束 -->
	<div class="main4">
		<h3>合作机构</h3>
		<table class="jigou" cellspacing="0">
			<tr>
				<td><img src="${ctx}/images/jigou/jigou1.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou2.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou3.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou4.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou5.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou6.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou7.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou8.png" alt="" /></td>
			</tr>
			<tr>
				<td><img src="${ctx}/images/jigou/jigou9.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou10.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou11.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou70.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou13.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou14.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou15.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou16.png" alt="" /></td>
			</tr>
            <tr>
				<td><img src="${ctx}/images/jigou/jigou69.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou18.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou19.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou20.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou21.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou22.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou23.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou24.png" alt="" /></td>
			</tr>
            <tr>
				<td><img src="${ctx}/images/jigou/jigou25.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou26.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou27.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou28.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou29.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou30.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou31.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou32.png" alt="" /></td>
			</tr>
            <tr>
				<td><img src="${ctx}/images/jigou/jigou33.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou34.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou35.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou36.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou37.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou38.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou39.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou40.png" alt="" /></td>
			</tr>
            <tr>
				<td><img src="${ctx}/images/jigou/jigou41.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou42.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou43.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou44.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou45.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou46.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou47.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou48.png" alt="" /></td>
			</tr>
            <tr>
				<td><img src="${ctx}/images/jigou/jigou49.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou50.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou51.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou52.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou53.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou54.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou55.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou56.png" alt="" /></td>
			</tr>
             <tr>
				<td><img src="${ctx}/images/jigou/jigou57.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou58.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou59.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou60.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou61.png" alt="" /></td>
				<td><img src="${ctx}/images/jigou/jigou62.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou63.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou64.png" alt="" /></td>
			</tr>
            <tr>
				<td><img src="${ctx}/images/jigou/jigou67.png" alt="" /></td>
                <td><img src="${ctx}/images/jigou/jigou68.png" alt="" /></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
			</tr>
		</table>
	</div>
	<div class="main6">
		<div class="main6_1">
			<table cellspacing="0">
				<tr style="text-align:left;">
					<td class="l1">关注我们</td>
					<td class="m1">手机也能投</td>
					<td class="r1">客服热线</td>
				</tr>
				<tr>
					<td class="l1"><img style="margin-right:20px" src="${ctx}/images/index_b3.png" alt="" /><img src="${ctx}/images/index_b4.png" alt="" />
					</td>
					<td class="m1"><a target="_blank"  href="https://itunes.apple.com/us/app/yi-qi-yi-qi-tou/id938627062?l=zh&ls=1&mt=8"><img src="${ctx}/images/index_b1.png" alt="" /></a><a class="theme-login2"><img src="${ctx}/images/index_b2.png" alt="" /></a></td>
					<td class="r1"><span style="font-size: 24px;">400 057 3090</span><br/><span style="font-size:14px;">服务时间：9:00-21:00</span><br/><br/>客服邮箱<br/>service@1717tou.com</td>
				</tr>
			</table>
		</div>

	</div>
	<p id="back-to-top" style="display: block;"><a href="#top"><span></span></a></p>
    <p id="qq_inline" style="display: block;"><a  href='http://wpa.qq.com/msgrd?V=1&uin=3092976902&Site=一起一起投&Menu=no' target='_blank'><span></span></a></p>
    </div>
    <!--   以下是弹出层的内容-->                         
                            <div class="theme-popover">
                                 <div class="theme-poptit">
                                    <a href="javascript:;" title="关闭" class="close"><img  src="${ctx}/images/index_pop6.png" /></a>
 <div id="wrap_slide">

<div id="slide">
		<div id="p1">
			<img src="${ctx}/images/index_pop1.png" />
		</div>
		<div id="p2">
			<img src="${ctx}/images/index_pop2.png" />
		</div>
		<div id="p3">
			<img src="${ctx}/images/index_pop3.png" />
		</div>
		
	</div>

 <div class="slide_box">
		<ul id="abuttons">
			<li id="a1"><a class="current">&nbsp;&nbsp;&nbsp;</a></li>
			<li id="a2"><a>&nbsp;&nbsp;&nbsp;</a></li>
			<li id="a3"><a>&nbsp;&nbsp;&nbsp;</a></li>
		
		</ul>
	</div>	
	<div id="previous">Previous</div>
	<div id="next">Next</div>
</div>
                                   
                                 </div>
                                
                               
                            </div>
                            
  <div class="theme-popover-mask"></div>
  <!--   弹出层的内容结束-->  
    
      
    <!--   以下是一键安装到手机弹出层的内容-->                         
                            <div class="theme-popover2">
       					  	  <div class="xiazai_tit">
									<img src="images/xiazai_ph.png" />一键安装到手机
                           	  </div>
                              <div class="xiazai_con">
                              	 <div class="xiazai_con1">
                                 	 <div class="xiazai_con1l">
                                     	<div class="xiazai_con1l_1">是否将该文件直接下载到手机？</div>
                                        <div class="xiazai_con1l_2">1717tou.apk</div>
                                         <div class="xiazai_con1l_3">apk大小： 3.67MB</div>
                                          <div class="xiazai_con1l_4">扫描右边的二维码也可直接安装</div>
                                     </div>
                                      <div class="xiazai_con1r">
                                     	<img src="images/xiazai_ma.png" />
                                     </div>
                                 </div>
                              </div>
                              <div class="xiazai_foot">
                              	 <div class="xiazai_foot1">
                                 	<div class="xiazai_foot1l">
                                        
                                    </div>
                                    <div class="xiazai_foot1r">
                                    	<div class="xiazai_btn3 close">取消</div>
                                    </div>
                                 </div>
                              </div>
                            </div>
                            
  <div class="theme-popover-mask"></div>
 
	<span id="unnameTag" style="display:none;" class="theme-login"></span>
   <!--   弹出层的内容结束-->  
    <script type="text/javascript">
	function changestyle(id){
		var cur_menu=document.getElementById(id);
		var con=document.getElementById("product_cont2_content");
		cur_menu.className="product_cont2_menu2";
		for(i=1; i<=4; i++){
			if(String("td"+i)!=id){
				document.getElementById(String("td"+i)).className="product_cont2_menu1";
				$("#product_cont2_cont_td"+i).hide();
			}	
		}
		//至此是更改菜单的样式;	
		$("#product_cont2_cont_"+id).show();
	}
</script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#product_cont2_cont_td4").show();
		$("#product_cont2_cont_td3").show();
		$("#product_cont2_cont_td2").show();
		$("#product_cont2_cont_td1").show();
		setTimeout(function(){
						$("#product_cont2_cont_td4").hide();
						$("#product_cont2_cont_td3").hide();
						$("#product_cont2_cont_td2").hide();
						},1000)
		
	});
</script>
	<%@ include file="common/foot.jsp" %>
</body>
</html>

