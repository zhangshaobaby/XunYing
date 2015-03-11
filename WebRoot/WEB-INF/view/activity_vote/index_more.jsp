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
    <link href="<%=path %>/css/activtity/more_girl.css" type="text/css" rel="stylesheet">
    <link href="<%=path %>/css/activtity/more_girl_popup.css" type="text/css" rel="stylesheet">
    <link href="<%=path %>/css/activtity/nvshen_popup.css" type="text/css" rel="stylesheet">
    <script  src="<%=path %>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script  src="<%=path %>/js/base.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx }/js/ajaxupload.3.6.js"></script>
    <script type="text/javascript">
    var fullid,code,registphone,arr,type;	
	 var basepath="<%=path%>";
	 var phonecheck=false;
	 var regphonecheck=false;
	 var codecheck=false;
	 var user="${sessionScope.user}";
	 var imagehost="<cc:picHost/>";	
	 
	 
  $(function(){
	/*	选女生弹出层*/
	
		$('.theme-popover-xiu .close').click(function(){
		
		 	$('.theme-popover-mask').css("display","none");
			$('.theme-popover-xiu').slideUp(200);
			
		})
	/*	做女生弹出层*/
	$('.theme-login-do').click(function(){
		if(user==null||user=="")
		 {
		 	$('.theme-popover-mask').css("display","block");
		 	$('.theme-popover3').slideDown(200);
		 }
		 else
		 {
		 	$('.theme-popover-mask').css("display","block");
			$('.theme-popover-do').slideDown(200);
		 }
		
	})
	$('.theme-popover-do .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover-do').slideUp(200);
	})
	
	
	$('.theme-popover3 .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover3').slideUp(200);
	})
	$('.theme-popover5 .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover5').slideUp(200);
	})
	
	
	
	
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
	})
			
	 
	function toupiao(id)
	{
	 fullid=id;
	 if(user==null||user==""){
	  $('.theme-popover-mask').css("display","block");
	  $('.theme-popover3').slideDown(200);
	 }else{
	 var diaosibi=document.getElementById("dsb").value;
	 if(Number(diaosibi)>=100)
		{
			vote(fullid);
		}
		 else
		 { alert("屌丝币不足，请兑换");
		 	document.location.href="<%=path %>/paradise?tabid=2";
		 } 
	 }
	}
	function vote(fullid){
	jQuery.ajax({type:"POST", url: "<%=path %>/activityvote/notloginvote",data:"persionid="+fullid, success:function (result) {
       if(result!=""&&result!="buzu"){
	        alert("成功");
	        window.location.href="<%=path %>/activityvote/index?type=pcmore";
			}else if(result=="buzu"){
			 alert("屌丝币不足，请兑换");
				document.location.href="<%=path %>/paradise?tabid=2";
			}else{
				alert("失败");
			}
	}});
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
			data:"phone="+phone+"&pwd="+pwd+"&persionid="+fullid,
			success:function(result){
		 if(result=="loginfalse"){
		    alert("用户名或密码错误");	
		    return;
			}
	    	if(result!=""&&result!="buzu"){
	        alert("成功");
	        window.location.href="<%=path %>/activityvote/index?type=pcmore";
			}else if(result=="buzu"){
			 alert("屌丝币不足，请兑换");
				document.location.href="<%=path %>/paradise?tabid=2";
			}else
			{
				alert("失败");
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
	   			$.post("${ctx}/activityvote/registquick",{phone:registphone,vercode:vercode,hiddencode:"pccode",persionid:fullid},function(data){
				if(data.indexOf(",")>0){
				alert("注册成功，密码已经发送到您的手机");
				window.location.href="<%=path %>/activityvote/index?type=pcmore";
				}else{
					alert("注册失败");
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
$(document).ready(function(){
		initialNew();
    });
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
			$('.add_pic').css("display","block");
       		$('.show_pic').css("display","none");
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
				            	window.location.href="<%=path %>/activityvote/index?type=pcmore";
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
    </script>
   
	
</head>
<body >
<%@ include file="../common/head_html.jsp"%>
<input type="hidden" id="dsb" value="${diaosibi }"  />
<div class="page1">
 <img class="page1_bg" src="<%=path%>/images/activityvote/pc/page3_3.png"/>
	<div class="page1_con">
	`	<div class="page1_con1">    	
            <a class="theme-login-xiu" style="cursor: pointer;" href="javascript:show('1');"><img class="xiugirl" src="<%=path%>/images/activityvote/pc/xiugirl.png"/></a>
            <a class="theme-login-xiu" style="cursor: pointer;" href="javascript:show('2');"><img   class="dogirl" src="<%=path%>/images/activityvote/pc/dogirl.png"/></a>
        </div>
    </div>
</div>
<div style="background-color: #a73453;width: 100%">
<div class="page2">
<img class="page2_bg" src="<%=path%>/images/activityvote/pc/page2_bg.png"/>
</div>
<form action="${ctx}/activityvote/index" method="post">
<input  type="hidden" name="type" value="pcmore"/>
<div class="page3" >
	<table class="page3_con1" cellspacing="0" border="0">
               <c:forEach items="${list}" var="item" varStatus="i">
	        	<c:if test="${i.index==0||i.index==4||i.index==8}">
	        	<tr>
	        	</c:if>
					<td>
                    	<img class="girl" src="<cc:picHost/>/activity/vote/${item.showpic}"/>

                        <div class="girl_con">
                        	<div class="girl_con1">
                            	<div class="girl_con1l">姓名：<span><c:if test="${ fn:length(item.username)<=6 }">${item.username}</c:if>  
                           		<c:if test="${ fn:length(item.username)>6 }">
                           		${fn:substring(item.username,0,6)}...</c:if></span></div>
                                <div class="girl_con1r">
	                                <a href="javascript:toupiao('${item.id}');">
	                                <img src="<%=path%>/images/activityvote/pc/xin2.png"/> 
	                                </a><span>${item.totalvote}</span>票
                                </div>
                            </div>
                        	<div class="girl_con2">
                               <div class="girl_con22">愿望/宣言：</div>
                            	<span title="${item.summery}" style="font-size: 12px"> 
                            		<c:if test="${ fn:length(item.summery)<=23 }">${item.summery}</c:if>  
                            		<c:if test="${ fn:length(item.summery)>23 }">${fn:substring(item.summery,0,23)}...</c:if>  
                            	</span>
                            </div>
                        </div>
                    </td>
	 			<c:if test="${i.index==3||i.index==7||i.index==11}">
	        	</tr>
	        	</c:if>
	    	</c:forEach>
			</table>

</div>
</div>

<div class="page4">
	<div class="page4_con">
	${ page.currPage }/${ page.totalPage } 
	　<c:if test="${ page.currPage == 1}">
	<span class="spe"> 首页</span>&nbsp;<span class="spe">上一页</span>
	 </c:if>
	   <c:if test="${ page.currPage>1}">
		<a href="javascript:nextPageToForm('1')"> <span class="spe"> 首页 </span></a>　
		<a href="javascript:nextPageToForm('${ page.prePage}')" ><span class="spe">上一页</span></a> 
		</c:if>
		<c:if test="${ page.currPage == page.totalPage}">
		<span class="spe">下一页</span>&nbsp;<span class="spe">尾页</span>
		</c:if>
		<c:if test="${ page.currPage < page.totalPage}">
		<a href="javascript:nextPageToForm('${ page.nextPage}')"  ><span class="spe">下一页</span></a>　
		<a href="javascript:nextPageToForm('${ page.totalPage}')"  ><span class="spe">尾页</span></a>
		</c:if>
	</div>
</div>
</form>
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
                    <div  class="add_pic_l"><img  src="<%=path%>/images/activityvote/pc/popup/button_pic.png"></div>
                    <div  class="add_pic_r">
                    
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
<jsp:include page="../common/foot.jsp"></jsp:include>
</body>
</html>
