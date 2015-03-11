<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>屌丝选女神pc版</title>
    <link href="<%=path %>/css/activtity/nvshen.css" type="text/css" rel="stylesheet">
    <link href="<%=path %>/css/activtity/nvshen_popup.css" type="text/css" rel="stylesheet">
     <link href="<%=path %>/css/activtity/more_girl_popup.css" type="text/css" rel="stylesheet">
    <script  src="<%=path %>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
     <script type="text/javascript" src="${ctx }/js/ajaxupload.3.6.js"></script>
    <script type="text/javascript">
    current = 1;
	button = 1;
	images = 3;
	width = 906;







/*控制弹出层*/
$(function() {
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
/*   发送密码*/
	$('.theme-login5').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover5').slideDown(200);
	})
	$('.theme-popover5 .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover5').slideUp(200);
	})
/*  验证码*/
	$('.theme-login6').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover6').slideDown(200);
	})
	$('.theme-popover6 .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover6').slideUp(200);
	})
/*	选女生弹出层*/
		$('.theme-popover-xiu .close').click(function(){
		
		 	$('.theme-popover-mask').css("display","none");
			$('.theme-popover-xiu').slideUp(200);
			
		})
		
		initialNew();
})
   function initialNew(){
    	var ss="#btnUphead";
       	var button = $(ss), interval;
        new AjaxUpload(button, {
            //action: 'upload-test.php',文件上传服务器端执行的地址
            action: '<cc:picUploadHost/>/activityvote/upload',
            name: 'files',
            onSubmit: function (file, ext) {
                if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF)$/.test(ext))) {
                    alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
                    return false;
                }
					
                button.text('上传中');

                this.disable();

                interval = window.setInterval(function () {
                    var text = button.text();
                    if (text.length < 10) {
                        button.text(text + '|');
                    } else {
                        button.text('上传中');
                    }
                }, 200);
            },
            onComplete: function (file, response) {
                //file 本地文件名称，response 服务器端传回的信息
                window.clearInterval(interval);
                button.text('点击上传');
                // enable upload button
                this.enable();
                var k = response.replace("<PRE>", "").replace("</PRE>", "");
                k = k.replace("<pre>", "").replace("</pre>", "");
                if (k == '-1') {
                    alert('您上传的文件太大啦!请不要超过1G');
                }
                else {
                    $("#showpic").val(k);
                    $('.add_pic').css("display","none");
                    $('.show_pic').css("display","block");
                    document.getElementById("imgshow").src="${picpath}/"+k;
                    
                    
                }
            }
        });
	}
function removeUp(obj){
		$('.add_pic').css("display","block");
        $('.show_pic').css("display","none");
	}









  function signed(){
    type="sign";
 if(user==null||user==""){
  $('.theme-popover-mask').css("display","block");
  $('.theme-popover3').slideDown(200);
 }else{
	usersign();
 }
}
function usersign(){
jQuery.ajax({type:"POST", url: "<%=path %>/user/usersigned", success:function (result) {
		if (result ==true) {
		alert("签到成功");
		    window.location.href=window.location.href;
		} else {
			alert("签到失败");
		}
	}});
} 


function xiezhen(tname,imgUrl,xuanyan)
{
 $("#slide").empty();
 $("#tname").html(tname);
 $("#xuanyan").html(xuanyan);
 var urls=imgUrl.split(",");
 for(var i=0;i<urls.length;i++ ){
 var pdiv= $("<div style='display: table-cell;vertical-align:middle;text-align:center;' id=\"p"+(i+1)+"\"></div>").appendTo("#slide");
 var img= $("<img  style='vertical-align:middle;' src=\""+imagehost+"/activity/vote/"+urls[i]+"\" />").appendTo(pdiv);
 }
 images=urls.length;
 $('#p1').animate({"left":"0px"},400,"swing");
 
}
function bao(headimg,id)
{
 $("#glheadimg").attr("src",imagehost+"/activity/vote/"+headimg);
 type="bao";
 fullid=id;
 if(user==null||user==""){
  $('.theme-popover-mask').css("display","block");
  $('.theme-popover3').slideDown(200);
 }else{var diaosibi=document.getElementById("dsb").innerHTML;;
 if(Number(diaosibi)>=100)
	{
		vote(fullid);
	}
	 else
	 {
	 	alert("屌丝币不足，请兑换");
	 	document.location.href="<%=path %>/paradise?tabid=2";
	 } 
 }
}
function closebao(){
		var diaosibi=document.getElementById("dsb").innerHTML;
		if(Number(diaosibi)>=100)
		{
			vote(fullid);
			alert("成功");
		}
		 else
		 {
		  alert("屌丝币不足，请兑换");
		 	document.location.href="<%=path %>/paradise?tabid=2";
		 } 
	}
function toupiao(id)
{
 type="vote";
 fullid=id;
 if(user==null||user==""){
  $('.theme-popover-mask').css("display","block");
  $('.theme-popover3').slideDown(200);
 }else{
 var diaosibi=document.getElementById("dsb").innerHTML;
 if(Number(diaosibi)>=100)
	{
		vote(fullid);
	}
	 else
	 {
	  alert("屌丝币不足，请兑换");
	 	document.location.href="<%=path %>/paradise?tabid=2";
	 } 
 }
}
  function vote(fullid){
	jQuery.ajax({type:"POST", url: "<%=path %>/activityvote/notloginvote",data:"persionid="+fullid, success:function (result) {
       if(type=="bao" ){
      		 	$('.theme-popover-mask').css("display","block");
		        $('.theme-popover4').slideDown(200);
	    	if(result!=""&&result!="buzu"){
	        $("#resultTd").html("英雄魅力电眼十万伏特"); 
	        $("#resultTd1").html("继续亮瞎我吧！"); 
	        document.getElementById("dsb").innerHTML=document.getElementById("dsb").innerHTML-100;
			}else if(result=="buzu"){
			 alert("屌丝币不足，请兑换");
			  document.location.href="<%=path %>/paradise?tabid=2";
			}else
			{
				$("#resultTd").html("哎呀！投票失败啦！");
				$("#resultTd1").html(""); 
			}
	    }else{
	    	if(result!=""&&result!="buzu"){
	        alert("成功");
	        window.location.reload();
			}else if(result=="buzu"){
			 alert("屌丝币不足，请兑换");
				document.location.href="<%=path %>/paradise?tabid=2";
			}else
			{
				alert("失败");
			}
	    }
	

	}});
}
var fullid,code,registphone,arr,type;
function showinfor(id,name,blood,height,constellation,weight,vitalstatistics,summery,age,imgurl)
{
	fullid=id;
	document.getElementById("name").innerHTML=name;
	document.getElementById("age").innerHTML="年龄："+age;
	document.getElementById("blood").innerHTML="血型："+blood;
	document.getElementById("height").innerHTML="身高："+height;
	document.getElementById("constellation").innerHTML="星座："+constellation;
	document.getElementById("weight").innerHTML="体重："+weight;
	document.getElementById("vitalstatistics").innerHTML="三围："+vitalstatistics;
	document.getElementById("summery").innerHTML="宣言："+summery;
	var innerh="<ul>";
	arr=imgurl.split(",");
}


function longin()
	{
		var phone=document.getElementById("phone").value;
		var pwd=document.getElementById("pwd").value;
		if(phone!=""&&pwd!=""&&phonecheck)
		{
		$.ajax({
	    	type:"POST",
	    	async:false,
			url:"${ctx}/activityvote/loginquickpc",
			data:"phone="+phone+"&pwd="+pwd+"&persionid="+fullid+"&type="+type,
			success:function(result){
		 if(result=="loginfalse"){
		    alert("用户名或密码错误");	
		    return;
			}
	           if(type=="bao" ){
	  	        $('.theme-popover-mask').css("display","block");
	  	        $('.theme-popover3').css("display","none");
		        $('.theme-popover4').slideDown(200);
	    	if(result!=""&&result!="buzu"){
	        $("#resultTd").html("英雄魅力电眼十万伏特"); 
	         $("#resultTd1").html("继续亮瞎我吧！"); 
	          document.getElementById("dsb").innerHTML=result;
			}else if(result=="buzu"){
			 alert("屌丝币不足，请兑换");
			 document.location.href="<%=path %>/paradise?tabid=2";
			  
			}else
			{
				$("#resultTd").html("哎呀！投票失败啦！");
				$("#resultTd1").html(""); 
			}
	    }
	    else if(type=="sign"){
	 		if (result =="success") {
		    alert("签到成功"); 
		} else {
			alert("签到失败,每天仅能签到一次");
		}
		 window.location.href=window.location.href;
	    }
	      else{
	    	if(result!=""&&result!="buzu"){
	        alert("成功");
	        window.location.reload();
			}else if(result=="buzu"){
			 alert("屌丝币不足，请兑换");
				document.location.href="<%=path %>/paradise?tabid=2";
			}else
			{
				alert("失败");
			}
	    }
			}
			
		});
		}
		else
		{
			alert("请输入正确手机号码和密码");
		}
	}
	function regist()
	{
	  var vercode=document.getElementById("vercode").value;
	  if(regphonecheck==false){
	  	alert("请确认手机号码的正确性");
	  	return;
	  }
	  
	  if(vercode!=""&&codecheck)
	  {
	   if (document.getElementById("iagree").checked)
	   {
	   			$.post("${ctx}/activityvote/registquick",{phone:registphone,vercode:vercode,hiddencode:"pccode",persionid:fullid,type:type},function(data){
				if(type=="bao"){
					$('.theme-popover-mask').css("display","block");
		  	        $('.theme-popover5').css("display","none");
			        $('.theme-popover4').slideDown(200);
			        
					if(data.indexOf(",")>0){
						 $("#resultTd").html("英雄魅力电眼十万伏特"); 
						  $("#resultTd1").html("继续亮瞎我吧！"); 
						}else{
							$("#resultTd").html("哎呀！注册失败啦！");
							$("#resultTd1").html(""); 
						}
				} 
				else if(type=="sign"){
			 		if (data =="success") {
				    alert("签到成功");
					} else {
						alert("签到失败,每天仅能签到一次");
					}
					window.location.href=window.location.href;
			    }
				else{
					if(data.indexOf(",")>0){
					alert("注册成功，密码已经发送到您的手机");
					window.location.href=window.location.href;
					}else{
						alert("注册失败");
					}
				}
				});
	   }
	   else
	   {
	   	alert("请阅读一起一起投注册协议");
	   }
	  }
	  else
	  {
	  	alert("请输入正确验证码");
	  }
	}
	
</script>
	<script type="text/javascript">
	 var basepath="<%=path%>";
 var phonecheck=false;
 var regphonecheck=false;
 var codecheck=false;
 var user="${sessionScope.user}";
 var imagehost="<cc:picHost/>";
function checkPhone(){
var phoneNum=$("#phone").val();
if(phoneNum==""||/^\w{1,10}$/.test(phoneNum)){
		return;
		}
 var reltest=/^\d{11}$/;
 $("#phoneTip").empty();
 if(reltest.test(phoneNum)){
  $("#phoneTip").append("正确");
   phonecheck=true;
  }else{
	  $("#phoneTip").append("请输入11位有效手机号码");
  }
}
function checkregPhone(){
var phoneNum=$("#rephone").val();
 	if(phoneNum==""||/^\w{1,10}$/.test(phoneNum)){
		return;
		}
 var reltest=/^\d{11}$/;
 $("#regphoneTip").empty();
 if(reltest.test(phoneNum)){
 	jQuery.ajax({
 	    type:"POST",
		url : basepath + "/user/checkphone",
		data:"phone="+phoneNum,
		success : function(result){
            if(result == "noexsit" ) 
            {
              regphonecheck=true;
              registphone=phoneNum;
              $("#regphoneTip").html("正确");
            }else{
			$("#regphoneTip").html("该号码已注册，请更换号码");
            }
           
		}
 	})
  }else{
	  $("#regphoneTip").append("请输入11位有效手机号码");
  }
}
function codecheckf(val){
 $("#vercodeTip").empty();
 	if(val==""||/^\w{1,5}$/.test(val)){
		return;
		}
  	jQuery.ajax({
 	    type:"POST",
		url : basepath +"/checksessionattr?attrname=votephonecode",
		data: "code="+val,
		success : function(result){
            if(result ==true ) 
            {
              codecheck=true;
              $("#vercodeTip").html("正确");
            }else{
            $("#vercodeTip").html("请输入正确的验证码");
            }
		}
 	})
 }
 function changePhoneCode() {
     if(regphonecheck==false){
	  	alert("请确认手机号码的正确性");
	  	return;
	  }
	jQuery.ajax({type:"POST",url:"${ctx}/activityvote/sendcode",
			data:"phone="+registphone, success:function (result) {
		if (result == "1") {
		   $("#agenSendSpan").removeClass("yan").addClass("refa");
		   $("#agenSendSpan").html("重发(<span id='secondtime'></span>)");
			getTime(60);
		} else {
			alert("\u9a8c\u8bc1\u7801\u53d1\u9001\u5931\u8d25!\u8bf7\u7a0d\u540e\u91cd\u8bd5");
			getTime(60);
		}
	}});
}
        function getTime(i) {
	    if (i >= 0) {
		$("#secondtime").html(i);
		i--;
		setTimeout("getTime(" + i + ")", 1000);
	} else {
		$("#agenSendSpan").empty();
		$("#agenSendSpan").removeClass("refa").addClass("yan");
		$("#agenSendSpan").html("<a style='color:#fff' href='javascript:changePhoneCode()'>重发</a>");
	}
}
function fenxiang()
{
	window.open("http://service.weibo.com/share/share.php?url="+encodeURIComponent(location.href)+"&title="+encodeURIComponent('一起一起投-屌丝选女神')+"&appkey=1343713053");
}
function jixubaoclose()
{
	window.location.reload();
}

 
	
		function show(f)
	{
		if(user==null||user=="")
		 {
		 	$('.theme-popover-mask').css("display","block");
		 	$('.theme-popover3').slideDown(200);
		 }
		 else
		 {
		 	$('.theme-popover-mask').css("display","block");
			$('.theme-popover-xiu').slideDown(200);
			document.getElementById("username").value="";
			document.getElementById("summery").value="";
		 }
		if(f=="1")
		{
			 document.getElementById("sname").innerHTML="秀女神";
			 document.getElementById("syw").innerHTML="愿望：";
		}
		else if(f=="2")
		{
			document.getElementById("sname").innerHTML="做女神";
			document.getElementById("syw").innerHTML="宣言：";
		}
		
	}
	function tijiao()
	{
		var username=document.getElementById("username").value;
		var summery=document.getElementById("summery").value;
		var showpic=document.getElementById("showpic").value;
		if(username=="")
		{
			alert("请输入昵称");
		}
		else
		{
			if(summery=="")
			{
				alert("请输入愿望/宣言");
			}
			else
			{
				if(showpic=="")
				{
					alert("请上传图片");
				}
				else
				{
						jQuery.ajax({
				 	    type:"GET",
							url : basepath +"/activityvote/presave?username="+encodeURI(encodeURI(username))+"&summery="+encodeURI(encodeURI(summery))+"&showpic="+showpic,
						success : function(result){
				            if(result =="true" ) 
				            {
				            	window.location.href=window.location.href;
				              //windows.location.reload();
				            }else{
				            alert("上传失败，请重新上传");
				            }
						}
				 		});
				}
			}
		}
	} 
	function showupload(obj)
	{
		obj.style.display="none";
	}
</script>
</head>
<body>
<%@ include file="../common/head_html.jsp"%>
<div class="page1">
	<div class="page1_con">
    	<div class="page1_con1">
        	 <img  src="<%=path%>/images/activityvote/pc/nvshen1.png"/>
            <a href="#piaozi"><img class="piaozi" src="<%=path%>/images/activityvote/pc/nvshen1_03.png"/></a>
            <a href="#nshen"><img   class="nshen" src="<%=path%>/images/activityvote/pc/nvshen1_05.png"/></a>
        </div>
        <div class="page1_con2">
        <div style="position:absolute;top:200px;left:170px;width:170px;z-index:3;">  
        <a class="theme-login-xiu" style="cursor: pointer;" href="javascript:show('1');"><img src="<%=path%>/images/activityvote/pc/xiugirl.png"/></a></div>
            <div style="position:absolute;top:200px;right:150px;width:170px;z-index:3;"> 
            <a class="theme-login-xiu" style="cursor: pointer;" href="javascript:show('2');"> <img src="<%=path%>/images/activityvote/pc/dogirl.png"/></a></div>
         <div  style="position:absolute;top:350px;float: left;"><img  src="<%=path%>/images/activityvote/pc/left.png"    /></div>
        <div style="position:absolute;top:250px; left:320px;float: left;"><img   src="<%=path%>/images/activityvote/pc/middle.png" /></div>
        <div style="position:absolute;top:350px; left:720px;float: left;"><img   src="<%=path%>/images/activityvote/pc/right.png" /></div>
        </div>
    
    </div>
</div>
<div class="page2" id="piaozi">
    <div class="page2_con">
    	<div class="page2_con1">
        	<a class=" theme-login"><img  src="<%=path%>/images/activityvote/pc/youxi.png"/></a>
        </div>
        
         <div class="page2_con2">
            <div  class="btn1"><span>${hongbao }</span></div>
            <div  class="btn2"><a href="javascript:void(0)"><c:choose><c:when test="${signed==false}"><img onclick="signed()" src="<%=path%>/images/activityvote/pc/btn2.png" alt=""></c:when><c:otherwise><img  src="<%=path%>/images/activityvote/pc/btn22.png" alt=""></c:otherwise></c:choose></a></div>
            <div  class="btn3"> <a href="<%=path%>/paradise" target="_blank"><img  src="<%=path%>/images/activityvote/pc/btn3.png"/></a></div>
            <div  class="btn4"><span id="dsb">${diaosibi }</span></div>
        </div>
        
        
        
        <!-- <div class="page2_con2">
            <div class="hongbao"><span>${hongbao }</span></div>
            <div class="diaosibi"><span id="dsb">${diaosibi }</span></div>
        	<img class="btn1" src="<%=path%>/images/activityvote/pc/btn1.png"/>
            <a href="javascript:void(0)" class="btn2"><c:choose><c:when test="${signed==false}"><img onclick="signed()" src="<%=path%>/images/activityvote/pc/btn2.png" alt=""></c:when><c:otherwise><img  src="<%=path%>/images/activityvote/pc/btn22.png" alt=""></c:otherwise></c:choose></a>
            <a href="<%=path%>/paradise" target="_blank" class="btn3"><img  src="<%=path%>/images/activityvote/pc/btn3.png"/></a>
            <img class="btn4" src="<%=path%>/images/activityvote/pc/btn4.png"/>
        </div> -->
        
         <div class="page2_con3">
        	<a href="${ctx }/project/list?type=0" target="_blank"><img  src="<%=path%>/images/activityvote/pc/yuansu_14.png"/></a> 
     	</div>
    </div>

</div>
<div class="page3" id="nshen">
	<div class="page3_con">
    <img class="gai"  src="<%=path%>/images/activityvote/pc/gai.png"/>
     <a class="more" style="color: white;"  href="${ctx }/activityvote/index?type=pcmore">More</a>
    	<table class="page3_con1" cellspacing="0" border="0">
        	<c:forEach items="${list}" var="item" varStatus="i">
        	<c:if test="${i.index==0||i.index==2||i.index==4}">
        	<tr>
        	</c:if>
					<td>
					<img class="girl" src="<cc:picHost/>/activity/vote/${item.showpic}"/>
                        <div class="girl_con">
                        	<div class="girl_con1">
                            	<div class="girl_con1l">
                            	<c:if test="${ fn:length(item.username)<=20 }">${item.username}</c:if>  
                           		<c:if test="${ fn:length(item.username)>20 }">
                           		${fn:substring(item.username,0,20)}...</c:if></div>
                                <div class="girl_con1r"> <a href="javascript:toupiao('${item.id}');"> <img src="<%=path%>/images/activityvote/pc/xin.png" /> </a> <span>${item.totalvote}</span></div>
                            </div>
                        	<div class="girl_con2">
                            	<c:if test="${ fn:length(item.summery)<=20 }">${item.summery}</c:if>  
                           		<c:if test="${ fn:length(item.summery)>20 }">${fn:substring(item.summery,0,20)}...</c:if>
                            </div>
                        </div>
                    </td>
	  	    <c:if test="${i.index==1||i.index==3||i.index==5}">
        	</tr>
        	</c:if>
    	</c:forEach>
           </table>
    </div>
</div>
<div class="page3_2">
	<a href="${ctx }/activityvote/index?type=pcmore"><img  src="<%=path%>/images/activityvote/pc/page3_2.png"/></a>
</div>
<div class="page4">
	<a href="http://www.1717tou.com" target="_blank"><img  src="<%=path%>/images/activityvote/pc/yuansu_21.png"/></a>
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
					<td class="l1"><img style="margin-right:20px" src="<%=path%>/images/activityvote/pc/index_b3.png" alt="" /><img src="<%=path%>/images/activityvote/pc/index_b4.png" alt="" />
					</td>
					<td class="m1"><img src="<%=path%>/images/activityvote/pc/index_b1.png" alt="" /><img src="<%=path%>/images/activityvote/pc/index_b2.png" alt="" /></td>
					<td class="r1"><span style="font-size: 24px;">400 057 3090
</span><br/><span style="font-size:14px;">服务时间：9:00-21:00</span><br/><br/>客服邮箱<br/>srevice@1717tou.com</td>
				</tr>
			</table>
		</div>
	</div>
    
<!--   活动规则弹出层开始-->
  <div class="theme-popover">	
  
           <a  class="close" ><img  src="<%=path%>/images/activityvote/pc/popup/pop_close.png"/></a>
  </div>
                            
  <div class="theme-popover-mask"></div>
<!--   活动规则弹出层结束-->
<!--   登陆弹出层开始-->
  <div class="theme-popover3">	
   				<a  class="close" ><img  src="<%=path%>/images/activityvote/pc/popup/pop_close2.png"/></a>
              <table bgcolor="white" border="0px" height="70px" width="100%" style="font-size:18px;">
              <tr><td align="center" bgcolor="#c7c8c3" width="50%">登录</td><td id="regtd" align="center" bgcolor="#e4e5e0" width="50%">注册</td></tr></table>
              <div class="input-wrap">
              <div class="log1"><span style="font-size: 18px">手机号：</span><input type="text" class="phone" id="phone" onkeyup="checkPhone()"  value=""> <div id="phoneTip" style="color: red;margin-top: 5px"></div></div>
              <div class="log2"><span style="font-size: 18px">密&nbsp;&nbsp;&nbsp;码：</span><input id="pwd"  type="password" class="pwd" value=""> </div>
             </div>
             <div class="input-log">
             <input value="登陆" type="button" class="input-log2" onclick="longin()">
             </div>
  </div>
  <!--   注册弹出层开始-->
  <div class="theme-popover5">	
           <a  class="close" ><img  src="<%=path%>/images/activityvote/pc/popup/pop_close2.png"/></a>
              <table bgcolor="white" border="0px" height="70px" width="100%" style="font-size:18px;">
              <tr><td id="logtd" align="center" bgcolor="#e4e5e0" width="50%">登录</td><td align="center" bgcolor="#c7c8c3" width="50%">注册</td></tr>
              </table>
              <div class="input-wrap">
              <div class="reg1"><span style="font-size: 18px">手机号：</span><input type="text" class="phone"  id="rephone" onkeyup="checkregPhone()" > <div id="regphoneTip" style="color: red;margin-top: 5px"></div></div>
             <div class="reg2"><span style="font-size: 18px">验证码：</span>
             <input type="text"  class="yzm" onkeyup="codecheckf(this.value)"  name="vercode" id="vercode" value="">
             <span class="yzm2" id="agenSendSpan">
             <a style="color:#666" href="javascript:changePhoneCode()">发送验证码</a></span><div id="vercodeTip" style="color: red;margin-top: 5px"></div></div>
             </div>
             <div class="input-log">
             <input type="checkbox"class="input-log1" id="iagree" name="iagree">   我同意《<a href="${ctx }/user/contract" target="_blank">一起一起投注册协议</a>》
             <input value="快速注册" type="button" class="input-log2" onclick="regist()" >
             </div>
           
  </div>
  <!--   报女神弹出层开始-->
  <div class="theme-popover4" >	
		  <a  class="close" onclick="jixubaoclose()"><img  src="<%=path%>/images/activityvote/pc/popup/pop_close.png"/></a>
           <table   bgcolor="white" width="100%" height="100%" style=" border:10px solid #000000;border-collapse:collapse;">
           <tr ><td align="center" width="30%" > <img id="glheadimg" style=" border-right:6px solid #000000;" width="200px" height="300px" src="<%=path%>/images/activityvote/pc/popup/xiezhen02.png" /> </td>
           <td align="center">
           <table align="center" cellspacing="0px" cellpadding="0px">
           <tr height="20px" ></tr>
           <tr><td id="resultTd" colspan="2" style="font-size: xx-large;font-weight: bold;color: black;"> 女神在此谢过啦。</td></tr>
            <tr><td id="resultTd1" colspan="2" style="font-size: xx-large;font-weight: bold;color: black; text-align: center;"> </td></tr>
            <tr height="30px" ></tr>
           <tr> 
           <td align="center"> <a href="javascript:fenxiang()"><img src="<%=path%>/images/activityvote/pc/popup/fenxiang.png" /> </a> </td> 
           <td align="center"> <a  href="javascript:closebao()"> <img src="<%=path%>/images/activityvote/pc/popup/jixubao.png" /></a></td>
           </tr>
           </table>
  			</td></tr>
           </table>
  </div>
<!--   秀女生弹出层开始-->
  <div class="theme-popover-xiu">	
             <a  class="close" ><img  src="<%=path%>/images/activityvote/pc/popup/popup_close2.png"/></a>
           <div class="xiugirl_tit" id="sname">
           		
           </div>
           <form name="form_admincp" id="form_admincp" method="post" action="${ctx}/activityvote/addorupdate"  onsubmit="return subForm();">
           <input type="hidden" name="showpic" id="showpic">
           <div class="xiugirl_con">
           		<div class="xiugirl_conl">
                	<div class="xiugirl_conl1">昵称：<input class="nicheng" type="text" name="username" id="username" maxlength="10"/></div>
                    <div class="xiugirl_conl2"><span style="position:absolute;top:0px;" id="syw"></span><textarea maxlength="100" class="yuanwang" name="summery" id="summery"></textarea></div>
                </div>
                <div  class="add_pic">
                 
                    <div  class="add_pic_r" >
                    
                        <input  type="file" id="btnUphead">
                    </div>
                </div>
                <div  class="show_pic">
                   <img id="imgshow" src="<%=path%>/images/activityvote/pc/image04.png"> 
                   <a href="javascript:removeUp();" style="color: #0060a5">重新上传头像</a>
                </div>	
           </div>
           <div class="xiugirl_foot">
            	<input class="xiu_btn" type="button" value="提交" onclick="tijiao();" >
           </div>
         </form>         
  </div>
                            
  <div class="theme-popover-mask"></div>
<!--   秀女生弹出层结束-->
                            
  <div style="position:fixed;top:40%;right:0%;width:100px;z-index:33333;"> <img src="<%=path%>/images/activityvote/pc/1717tou-weibo.png" width=100%/></div>
<div style="position:fixed;top:40%;left:0%;width:100px;z-index:33333;"> <img src="<%=path%>/images/activityvote/pc/1717tou-weixin.png" width=100%/></div>
  <jsp:include page="../common/foot.jsp"></jsp:include>
</body>
</html>

