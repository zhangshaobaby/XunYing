
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
	/*投票*/
	$('#regtd').click(function(){
	  $('.theme-popover3').css("display","none");
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover5').slideDown(200);
	})
	$('#logtd').click(function(){
	    $('.theme-popover5').css("display","none");
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover3').slideDown(200);
	})
	
	$('.theme-popover3 .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover3').slideUp(200);
	})
/*写真*/
	$('.theme-login2').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover2').slideDown(200);
	})
	$('.theme-popover2 .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover2').slideUp(200);
	})
/*登录注册*/
	$('.theme-login3').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover3').slideDown(200);
	})
	$('.theme-popover3 .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover3').slideUp(200);
	})
/*报女神*/
	$('.theme-popover4 .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover4').slideUp(200);
	})
<!--   发送密码-->
	$('.theme-login5').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover5').slideDown(200);
	})
	$('.theme-popover5 .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover5').slideUp(200);
	})
<!--  验证码-->
	$('.theme-login6').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover6').slideDown(200);
	})
	$('.theme-popover6 .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover6').slideUp(200);
	})

})

