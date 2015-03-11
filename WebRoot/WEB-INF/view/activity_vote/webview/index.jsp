<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>一起一起投-屌丝选女神</title>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link href="<%=path %>/css/webview/vote/girl.css" type="text/css" rel="stylesheet">
    <link href="<%=path %>/css/webview/vote/popup.css"rel="stylesheet" type="text/css" />
	<script  src="<%=path %>/js/jquery-1.7.js" type="text/javascript"></script>
    <script  src="<%=path %>/js/activityvote/girl.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
var fullid,code,registphone,arr;
var curIndex=0; 
$(function(){

	$(".li_1").click(function() {
			$(".li_1_img").attr("src","<%=path %>/images/activityvote/popup/font1.png");
			$(".li_2_img").attr("src","<%=path %>/images/activityvote/popup/font2_1.png");
			$(".zhuce_con2_r").css("display","block");
			$(".zhuce_con2_l").css("display","none");
		});
	$(".li_2").click(function() {
			$(".li_1_img").attr("src","<%=path %>/images/activityvote/popup/font1_1.png");
			$(".li_2_img").attr("src","<%=path %>/images/activityvote/popup/font2.png");
			$(".zhuce_con2_l").css("display","block");
			$(".zhuce_con2_r").css("display","none");
		});
		
	});
function showinfor(id,name,blood,height,constellation,weight,vitalstatistics,summery,age,imgurl)
{
	$('.theme-popover-mask').css("display","block");
	$('.theme-popover').slideDown(200);
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
function piclist()
{
	$('.theme-popover-mask').css("display","block");
	$('.theme-popover').slideUp(200);
	$('.theme-popover7').slideDown(200);
	document.getElementById("showpic").src="${picpath}/"+arr[0];
}
function changeImg() 
{ 
	var obj=document.getElementById("showpic"); 
	if (curIndex>=arr.length-1) 
	{ 
		curIndex=0; 
	} 
	else 
	{ 
		curIndex+=1; 
	} 
	obj.src="${picpath}/"+arr[curIndex]; 
} 
function xiangbao()
{
	$('.theme-popover-mask').css("display","block");
	$('.theme-popover').slideUp(200);
	$('.theme-popover2').slideDown(200);
}
function jujue(f)
{
	$.post("${ctx}/activityvote/notloginvote",{persionid:fullid},function(data){
		if(data!=""&&data!="buzu"){
		document.getElementById("share_num").innerHTML=data;
		}else if(data=="buzu"){
		document.location.href="<%=path %>/paradise?tabid=2";
		}
	});
	$('.theme-popover-mask').css("display","block");
	if(f=="1")
	{
		$('.theme-popover2').slideUp(200);
	}
	else if(f=="2")
	{
		$('.theme-popover4').slideUp(200);
	}
	else if(f=="3")
	{
		$('.theme-popover6').slideUp(200);
	}
	$('.theme-popover3').slideDown(200);
}
function zhichi()
{
	var uid=document.getElementById("uid").value;
	if(uid!="")
	{//已经登录
		$.post("${ctx}/activityvote/notloginvote",{persionid:fullid},function(data){
					if(data!=""&&data!="buzu"){
					document.getElementById("share_num").innerHTML=data;
					$('.theme-popover-mask').css("display","block");
					$('.theme-popover2').slideUp(200);
					$('.theme-popover3').slideDown(200);
					}else if(data=="buzu"){
					document.location.href="<%=path %>/paradise?tabid=2";
					}
				});
	}
	else
	{
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover2').slideUp(200);
		$('.theme-popover4').slideDown(200);
	}
}
function checkphone()
	{
		var phone=document.getElementById("rephone").value;
		if(phone!="")
		{
			$.post("${ctx}/activityvote/phonecheck",{phone:phone},function(data){
			if(data==""){
			document.getElementById("phonemul").innerHTML="手机号码已经使用";
			}else{
			$('.theme-popover-mask').css("display","block");
			$('.theme-popover6').slideDown(200);
			$('.theme-popover4').slideUp(200);
			code=data;
			registphone=phone;
			var ph_num=phone.substring(7,11);
			document.getElementById("ph_num").innerHTML=ph_num;
			}
		});
		}
		else
		{
			alert("请输入手机号码");
		}
	}
	function longin()
	{
		var phone=document.getElementById("phone").value;
		var pwd=document.getElementById("pwd").value;
		if(phone!=""&&pwd!="")
		{
		$.ajax({
	    	type:"POST",
	    	async:false,
			url:"${ctx}/activityvote/loginquick",
			data:"phone="+phone+"&pwd="+pwd+"&persionid="+fullid,
			success:function(data){
				if(data!=""&&data!="buzu"){
					document.getElementById("share_num").innerHTML=data;
					$('.theme-popover-mask').css("display","block");
					$('.theme-popover3').slideDown(200);
					$('.theme-popover4').slideUp(200);
				}else if(data=="buzu"){
					//alert("屌丝币不足");
					document.location.href="<%=path %>/paradise?tabid=2";
				}else
				{
					//alert("失败");
				}
			}
		});
		}
		else
		{
			alert("请输入手机号码和密码");
		}
	}
	
	function regist()
	{
	  var vercode=document.getElementById("vercode").value;
	  if(vercode==code)
	  {
	  	$.post("${ctx}/activityvote/registquick",{phone:registphone,vercode:vercode,hiddencode:code,persionid:fullid},function(data){
			if(data.indexOf(",")>0){
				$('.theme-popover-mask').css("display","block");
				$('.theme-popover6').slideUp(200);
				$('.theme-popover5').slideDown(200);
				document.getElementById("uid").value=data.split(",")[1];
			}else{alert("fail");
				alert("失败");
			}
		});
	  }
	  else
	  {
	  	alert("请输入正确验证码");
	  }
	}
	function sendcode()
	{
		$.ajax({
	    	type:"POST",
	    	async:false,
			url:"${ctx}/activityvote/sendcode",
			data:"phone="+registphone+"&code="+code,
			success:function(data){
				if (data == "1") 
				{
					alert("验证码已经发送");
				}
			}
		});
	}
	function reloadmess()
	{
	 	window.location.reload();
	}
</script>
<body>

<input type="hidden" name="uid" id="uid" value="${uid }"  >
 	<div class="girl">
    	<div class="girl1"><img class="full_w" src="<%=path %>/images/activityvote/girl1.png"/></div>
        <div class="girl2">
        	<a class=" theme-login"><img  class="beau_g1" src="${picpath}/${list[0].headpic }" onclick='showinfor("${list[0].id }","${list[0].username }","${list[0].blood }","${list[0].height }","${list[0].constellation }","${list[0].weight }","${list[0].vitalstatistics }","${list[0].summery }","${list[0].age }","${list[0].imgUrl }");'/></a>
           <a class=" theme-login"><img class="beau_g2" src="${picpath}/${list[1].headpic }" onclick='showinfor("${list[1].id }","${list[1].username }","${list[1].blood }","${list[1].height }","${list[1].constellation }","${list[1].weight }","${list[1].vitalstatistics }","${list[1].summery }","${list[1].age }","${list[1].imgUrl }");'/></a>
          	<a class=" theme-login"><img class="beau_g3" src="${picpath}/${list[2].headpic }" onclick='showinfor("${list[2].id }","${list[2].username }","${list[2].blood }","${list[2].height }","${list[2].constellation }","${list[2].weight }","${list[2].vitalstatistics }","${list[2].summery }","${list[2].age }","${list[2].imgUrl }");'/></a>
             <a class=" theme-login"><img class="beau_g4" src="${picpath}/${list[3].headpic }" onclick='showinfor("${list[3].id }","${list[3].username }","${list[3].blood }","${list[3].height }","${list[3].constellation }","${list[3].weight }","${list[3].vitalstatistics }","${list[3].summery }","${list[3].age }","${list[3].imgUrl }");'/></a>
            <img class="full_w" src="<%=path %>/images/activityvote/girl2.png"/>
         </div>
        <div class="girl3">
        	<a class=" theme-login" ><img class="beau_g5" src="${picpath}/${list[4].headpic }" onclick='showinfor("${list[4].id }","${list[4].username }","${list[4].blood }","${list[4].height }","${list[4].constellation }","${list[4].weight }","${list[4].vitalstatistics }","${list[4].summery }","${list[4].age }","${list[4].imgUrl }");'/></a>
            <a class=" theme-login"><img class="beau_g6" src="${picpath}/${list[5].headpic }" onclick='showinfor("${list[5].id }","${list[5].username }","${list[5].blood }","${list[5].height }","${list[5].constellation }","${list[5].weight }","${list[5].vitalstatistics }","${list[5].summery }","${list[5].age }","${list[5].imgUrl }");'/></a>
            <a class=" theme-login"><img class="beau_g7" src="${picpath}/${list[6].headpic }" onclick='showinfor("${list[6].id }","${list[6].username }","${list[6].blood }","${list[6].height }","${list[6].constellation }","${list[6].weight }","${list[6].vitalstatistics }","${list[6].summery }","${list[6].age }","${list[6].imgUrl }");'/></a>
            <a class=" theme-login"><img class="beau_g8" src="${picpath}/${list[7].headpic }" onclick='showinfor("${list[7].id }","${list[7].username }","${list[7].blood }","${list[7].height }","${list[7].constellation }","${list[7].weight }","${list[7].vitalstatistics }","${list[7].summery }","${list[7].age }","${list[7].imgUrl }");'/></a>
        	<img class="full_w" src="<%=path %>/images/activityvote/girl3.png"/>
        </div>
        <div class="girl4"><img class="full_w" src="<%=path %>/images/activityvote/girl4.png"/></div>  
         <div class="girl5">
         	<div class="girl5_l">&nbsp;</div>
            <div class="girl5_m">
            <!--循环开始-->
           		<div class="girl5_m1">
                   <img class="tiao"  src="<%=path %>/images/activityvote/girl_tiao.png" style="margin-left:-${list[0].persent }%"/>
                   <div class="piao" style="margin-right:${list[0].persent }%">
                       <div class="piaol"><img class="beau_g01" src="${picpath}/${list[0].headpic }"/></div>
                       <div class="piaor">
                           <span class="num">${list[0].totalvote }票</span>
                           <span class="piaor_name">${list[0].username }</span>
                       </div>
                   </div>
               </div>
               <div class="girl5_m1">
                   <img class="tiao"  src="<%=path %>/images/activityvote/girl_tiao.png" style="margin-left:-${list[1].persent }%"/>
                   <div class="piao" style="margin-right:${list[1].persent }%">
                       <div class="piaol"><img class="beau_g01" src="${picpath}/${list[1].headpic }"/></div>
                       <div class="piaor">
                           <span class="num">${list[1].totalvote }票</span>
                           <span class="piaor_name">${list[1].username }</span>
                       </div>
                   </div>
               </div>
               <div class="girl5_m1">
                   <img class="tiao"  src="<%=path %>/images/activityvote/girl_tiao.png" style="margin-left:-${list[2].persent }%"/>
                   <div class="piao" style="margin-right:${list[2].persent }%">
                       <div class="piaol"><img class="beau_g01" src="${picpath}/${list[2].headpic }"/></div>
                       <div class="piaor">
                           <span class="num">${list[2].totalvote }票</span>
                           <span class="piaor_name">${list[2].username }</span>
                       </div>
                   </div>
               </div>
               <div class="girl5_m1">
                   <img class="tiao"  src="<%=path %>/images/activityvote/girl_tiao.png" style="margin-left:-${list[3].persent }%"/>
                   <div class="piao" style="margin-right:${list[3].persent }%">
                       <div class="piaol"><img class="beau_g01" src="${picpath}/${list[3].headpic }"/></div>
                       <div class="piaor">
                           <span class="num">${list[3].totalvote }票</span>
                           <span class="piaor_name">${list[3].username }</span>
                       </div>
                   </div>
               </div>
               <div class="girl5_m1">
                   <img class="tiao"  src="<%=path %>/images/activityvote/girl_tiao.png" style="margin-left:-${list[4].persent }%"/>
                   <div class="piao" style="margin-right:${list[4].persent }%">
                       <div class="piaol"><img class="beau_g01" src="${picpath}/${list[4].headpic }"/></div>
                       <div class="piaor">
                           <span class="num">${list[4].totalvote }票</span>
                           <span class="piaor_name">${list[4].username }</span>
                       </div>
                   </div>
               </div>
               <div class="girl5_m1">
                   <img class="tiao"  src="<%=path %>/images/activityvote/girl_tiao.png" style="margin-left:-${list[5].persent }%"/>
                   <div class="piao" style="margin-right:${list[5].persent }%">
                       <div class="piaol"><img class="beau_g01" src="${picpath}/${list[5].headpic }"/></div>
                       <div class="piaor">
                           <span class="num">${list[5].totalvote }票</span>
                           <span class="piaor_name">${list[5].username }</span>
                       </div>
                   </div>
               </div>
               <div class="girl5_m1">
                   <img class="tiao"  src="<%=path %>/images/activityvote/girl_tiao.png" style="margin-left:-${list[6].persent }%"/>
                   <div class="piao" style="margin-right:${list[6].persent }%">
                       <div class="piaol"><img class="beau_g01" src="${picpath}/${list[6].headpic }"/></div>
                       <div class="piaor">
                           <span class="num">${list[6].totalvote }票</span>
                           <span class="piaor_name">${list[6].username }</span>
                       </div>
                   </div>
               </div>
               <div class="girl5_m1">
                   <img class="tiao"  src="<%=path %>/images/activityvote/girl_tiao.png" style="margin-left:-${list[7].persent }%"/>
                   <div class="piao" style="margin-right:${list[7].persent }%">
                       <div class="piaol"><img class="beau_g01" src="${picpath}/${list[7].headpic }"/></div>
                       <div class="piaor">
                           <span class="num">${list[7].totalvote }票</span>
                           <span class="piaor_name">${list[7].username }</span>
                       </div>
                   </div>
               </div>
            <!--循环结束-->
            </div>
            <div class="girl5_r">&nbsp;</div>
         </div>  
        <div style="clear:both;"></div>
        
    </div>
<!--    详细资料弹出层开始-->
  <div class="theme-popover">
  	<div class="theme-tit">
        <img  src="<%=path %>/images/activityvote/popup/pop_bg1.png"/>   
        <div class="nv_btn1" > <a  class="close" ><img  src="<%=path %>/images/activityvote/popup/pop_close.png"/> </a></div>  
    </div> 
    <div class="theme-con">
                      
            <h3><span id="name"></span></h3>
          	 <div class="mes_con" >
         
                 <div class="mes1l" id="age"></div>
                 <div class="mes1r" id="blood"></div>
                 <div class="mes1l" id="height"></div>
                 <div class="mes1r" id="constellation"></div>
                 <div class="mes1l" id="weight"></div>
                 <div class="mes1r" id="vitalstatistics"></div>
                 <div class="mes2" id="summery"></div>
             </div>
            <div class="mes_btn1" id="bao"> <a   onclick="xiangbao();">我想抱她</a></div>
            <div class="mes_btn2" > <a   id="look" onclick="javascript:piclist();" >查看写真</a></div>  
    </div>                 
  </div>
                            
  <div class="theme-popover-mask"></div>
  <!-- 写真 -->
<div class="theme-popover7">
  	<div class="theme-tit">
  		<img src=""id="showpic" /> 
        <img  onclick="changeImg();"  src="<%=path %>/images/activityvote/popup/right.png" style="position:absolute;top:40%;right:10px;z-index:44444;width:20%"/>
  		 
        <div class="nv_btn1" > <a  class="close" ><img  src="<%=path %>/images/activityvote/popup/pop_close.png"/> </a></div>  
       
    </div> 
  </div>
<!--   写真结束 女神很高兴弹出层-->
 <div class="theme-popover2">
  	<div class="theme-tit">
        <img  src="<%=path %>/images/activityvote/popup/pop_bg2.png"/> 
        <div class="nv_btn1" > <a  class="close" ><img  src="<%=path %>/images/activityvote/popup/pop_close.png"/> </a></div>  
    </div> 
    <div class="theme-con2">
             <div class="nv_btn2" ><a   href="javascript:zhichi();" ><img  src="<%=path %>/images/activityvote/popup/btn1.png"/></a> </div>
           <div class="nv_btn3" ><a ><img onclick="jujue('1');" src="<%=path %>/images/activityvote/popup/btn2.png"/></a> </div>
    </div>                 
  </div>
<!--    分享弹出层-->
 <div class="theme-popover3">
  	<div class="theme-tit">
        <img  src="<%=path %>/images/activityvote/popup/pop_bg4.png"/> 
         <span class="share_num" id="share_num"></span>
    </div> 
    <div class="theme-con3">
             <div class="share_btn1" ><a  class="close theme-login8" ><img  src="<%=path %>/images/activityvote/popup/btn3.png"/></a> </div>
             <div class="share_btn2" ><a  class="close" href="<%=path %>/activityvote/index"><img  src="<%=path %>/images/activityvote/popup/btn5.png"/></a> </div>
    </div>
  </div>
<!--    分享弹出层结束-->
<!--    一键注册-->
 <div class="theme-popover4">
  	<div class="theme-tit">
        <img  src="<%=path %>/images/activityvote/popup/pop_bg3.png"/> 
    </div>   
    <div class="zhuce_con">
        <ul class="zhuce_con1">
        	<li class="li_1"> <img  class="li_1_img" src="<%=path %>/images/activityvote/popup/font1.png"/></li>
            <li class="li_2"> <img  class="li_2_img" src="<%=path %>/images/activityvote/popup/font2_1.png"/></li>
        </ul>
    	<div class="zhuce_con2">
        <!--    一键注册-->
            <div class="zhuce_con2_r">
            	<div class="zhuce_con2_r1">
               		<span>手机号：</span><input type="text" value="" name="rephone" id="rephone">
               		<div><span id="phonemul"></span></div>
                </div><!-- class="close theme-login6" -->
                 <div class="zhuce_btn3" ><a ><img onclick="checkphone();"  src="<%=path %>/images/activityvote/popup/btn4.png" /></a> </div>
          		 <div class="zhuce_btn4" ><a ><img onclick="jujue('2');" src="<%=path %>/images/activityvote/popup/btn5.png"/></a> </div>
    		</div> 
            <!--   快速登录-->
            <div class="zhuce_con2_l">
    			<div class="zhuce_con2_l1"><!--class="close theme-login3"  -->
               		<span>手机号：</span><input type="text" name="phone" id="phone" value="">
                </div>
                <div class="zhuce_con2_l1">
               		<span>&nbsp;&nbsp;&nbsp;密码：</span><input type="password" name="pwd" id="pwd" value="">
                </div>
                 <div class="zhuce_btn3" ><a><img  src="<%=path %>/images/activityvote/popup/btn6.png" onclick="longin();"/></a> </div>
          		 <div class="zhuce_btn4" ><a><img onclick="jujue('2');" src="<%=path %>/images/activityvote/popup/btn5.png"/></a> </div>
    		</div>   
    	</div>  
    </div>     
  </div>
<!--   一键注册层结束--
<!--    密码弹出层-->
 <div class="theme-popover5">
  	<div class="theme-tit">
        <img  src="<%=path %>/images/activityvote/popup/pop_bg5.png"/> 
     	<div class="nv_btn1_1" > <a  class="close" ><img  src="<%=path %>/images/activityvote/popup/pop_close.png"/> </a></div> 
    </div> 
    <div class="theme-con3">
             <div class="mima_btn3" ><a  href="<%=path%>/index"><img  src="<%=path %>/images/activityvote/popup/btn7.png"/></a> </div>
             <!-- <div class="mima_btn4" ><a href="<%=path%>/forwordAction/forwordToView?path=saveCenter/changePwd"><img  src="<%=path %>/images/activityvote/popup/btn8.png"/></a> </div> -->
          	
    </div>                 
  </div>
<!--    密码弹出层结束-->
<!--    验证码弹出层-->
 <div class="theme-popover6">
  	<div class="theme-tit">
        <img  src="<%=path %>/images/activityvote/popup/pop_bg6.png"/> 
      <span class="ph_num" id="ph_num"></span>
    </div> 
    <div class="yzm_fasong">
    <input type="text" name="vercode" id="vercode"><img  onclick="sendcode();" src="<%=path %>/images/activityvote/popup/btn10.png"/>
    </div>
    <div class="yzm-con3"><!-- class="close theme-login5" -->
             <div class="yzm_btn1" ><a><img  src="<%=path %>/images/activityvote/popup/btn9.png" onclick="regist();"/></a> </div>
             <div class="yzm_btn2" ><a><img onclick="jujue('3');" src="<%=path %>/images/activityvote/popup/btn5.png"/></a> </div>
    </div>           
  </div>
<!--    验证码弹出层结束-->
<!--    分享指示弹出层-->
 <div class="theme-popover8">
 <img class="close" src="<%=path %>/images/activityvote/popup/pop_close.png" onclick="reloadmess();"/>
        <img class="fenxiang" src="<%=path %>/images/activityvote/popup/pc_help.png"/> 
  </div>
<!--    分享指示弹出层结束-->
</body>
</html>