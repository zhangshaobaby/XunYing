<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新手指引_领跑互联网理财，安全诚信,严格风控,互联网投资新选择。这是让您放心的互联网金融平台</title>
<meta name="keywords" content="一起一起投，1717Tou,新手指引，高收益，低门槛，安全透明，灵活高效，合作伙伴众多，金多宝，信满盈，资涨通，屌丝宝，互联网投掷，互联网理财"/>
<meta name="description" content="新手指引是投资新手关于一起一起投的介绍说明，介绍一起一起投四大产品金多宝，信满盈，资涨通，屌丝宝。一起一起投高收益，低门槛，安全透明，灵活高效，合作伙伴众多的平台优势。一起一起投，严格风控、安全互联网理财、安心互联网投资新平台" />
<meta name="author" content="北京凌云吉信信息技术有限公司" />
	<link rel="stylesheet" type="text/css" href="${ctx }/css/new_intro.css"/>
    <script src="${ctx }/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){

	current = 1;
	button = 1;
	images = 4;
	width = 998;
	
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
</script>


</head>
<body>
<jsp:include page="common/head.jsp"></jsp:include>
	<div class="new_intro">
		<div class="new_intro1">
			<div class="new_intro1_1"><img src="${ctx }/images/new_01.png" /></div>
			<div class="new_intro1_2">&nbsp;&nbsp;&nbsp;&nbsp;一起一起投是一个以互利共生为核心理念的互联网金融交易平台。<br/>
&nbsp;&nbsp;&nbsp;&nbsp;我们以诚信为根基、共生为信条、互联网精神为本质出发点，在安全透明、符合金融业法律法规的基本条件下，我们的平台将全身心致力于通过互联网金融创新来帮助投资者创造出无限可能的投资理财模式，提供更加智慧、更加安心的金融理财产品和服务。通过降低投资门槛，高度分散风险，真正实现让每个投资者都能够与大型金融投资机构，一起投资、一起成长！<br/>
&nbsp;&nbsp;&nbsp;&nbsp;改变生活、实现梦想，就从现在开始！</div>
			<div class="new_intro1_3"><img src="${ctx }/images/new_02.png" /></div>
			<div class="new_intro1_4"><img src="${ctx }/images/new_03.png" /></div>
			<div class="new_intro1_5"><img src="${ctx }/images/new_04.png" /></div>
			<div class="new_intro1_6"><img src="${ctx }/images/new_05.png" /></div>
			<div class="new_intro1_7">
			<a class="diao" href="${ctx }/huodong/intro/" target="_blank">了解更多></a>
			<a class="xin" href="${ctx }/huodong/intro/" target="_blank">了解更多></a>
			<a class="zi" href="${ctx }/huodong/intro/" target="_blank">了解更多></a>
			<a class="jin" href="${ctx }/huodong/intro/"target="_blank">了解更多></a>
			</div>
			<div class="new_intro1_8"><img src="${ctx }/images/new_07.png" /></div>
			<div class="new_intro1_9">
       <!--     特效开始-->
            		<div id="wrap_slide">
                            <div class="slide_box">
                                <ul id="abuttons">
                                    <li id="a1" class="li1" ><a class="current"></a></li>
                                    <li id="a2" class="li2"><a></a></li>
                                    <li id="a3" class="li3"><a></a></li>
                                    <li id="a4" class="li4"><a></a></li>
                                
                                </ul>
                            </div>
                            <div id="slide">
                                <div id="p1">
                                    <img src="${ctx }/images/new_st1.png" />
                                </div>
                                <div id="p2">
                                    <img src="${ctx }/images/new_st2.png" />
                                </div>
                                <div id="p3">
                                    <img src="${ctx }/images/new_st3.png" />
                                </div>
                                <div id="p4">
                                    <img src="${ctx }/images/new_st4.png" />
                                </div>
                                
                            </div>
                            <div id="previous">Previous</div>
                            <div id="next">Next</div>
                </div>
             <!--     特效结束-->
            </div>
			<div class="new_intro1_10"><a href="https://www.1717tou.com/user/register"><img src="${ctx }/images/new_zhuce.png" /></a></div>
		</div>
	</div>
		<jsp:include page="common/foot.jsp"></jsp:include>
</body>
</html>