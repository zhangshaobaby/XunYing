<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改我的资料</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/ziliao.css"/>
<script src="${ctx }/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx }/js/city.js" type="text/javascript"></script>
<script type="text/javascript">
/*控制弹出层*/
jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.sc_img').slideDown(200);
	})
	$(' .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.sc_img').slideUp(200);
		$('.upimg').slideUp(200);
	})
	$('.queren').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.sc_img').css("display","none");
		$('.upimg').slideDown(200);
	})
	<c:choose>
		<c:when test="${user.placeId!=null}">
			var ids = "${user.placeId}";
			readyCity(ids.split(",")[0],ids.split(",")[0]);
		</c:when>
		<c:otherwise>readyCity(null,null);</c:otherwise>
	</c:choose>
	
})
function subForm(){
	$("#place").val($("._province").find("option:selected").html()+"-"+$("._city").find("option:selected").html());
	$("#form").submit();
}
function cancel(){
	$(".tu_wz").hide();
	$(".needToChange").show();
	$(".needToChange").prev().hide();
	$("#textTitle").css("height","")
}
function editDetail(){
	$("#textTitle").css("height","100")
	$(".tu_wz").show();
	$(".needToChange").hide();
	$(".needToChange").prev().show();
}

	function sureHeadPic(){
		var rent = $("#dragDiv").css("top")+","+$("#dragDiv").css("left")+","+$("#dragDiv").css("width")+","+$("#dragDiv").css("height");
		rent = rent.replace(/[a-z]/g,"")
		$.post("<cc:picUploadHost/>/user/sureHeadPic",{path:$("#txtFileName").val(),rent:rent},function(data){
			//window.location.href=window.location.href;
		});
	}
</script>
</head>
<body>
<input type="hidden" id="txtFileName"/>
<div class="user_cont_rr"  style="background:#FFFFFF;">
		  <div class="user_cont_rrr">
				<div class="user_cont_r_1"><span>我的资料</span></div>
				<div class="user_cont_r_2"> 
			  <div class="user_cont_r_2a1">
			   <ul>
					<li class="li_1 sel"><a href="#">个人资料</a></li>
					<li class="li_2"><a href="#">安全中心</a></li>												
				</ul>
			</div>
		</div>
		<form action="${ctx }/user/editDetail" method="post" id="form">
      <div class="tables">
	  
	  <div class="grzl">
	 <div class="grzl_l">
	 <div class="tu">
	 <img src="${ctx}/images/ud01_03.png"/>
	 <span class="tu_wz" style="display:none;"><a class="theme-login btn">更换头像</a></span>
	 <c:if test="${user.type==2}">
	 <p>(理财顾问)</p>
	 </c:if>
	 </div></div>
	  <table class="user_cont_r_3b" cellspacing=0>
     <tr>
	 <td><span class="zl_l">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 名：</span></td>
	 <td><span class="zl_r">${user.realName }</span></td>
	 </tr>
	 <tr>
	 <td><span class="zl_l">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 别：</span></td>
	 <td><span class="zl_r">${user.identification }</span></td>
	</tr>
	 <tr><td><span class="zl_l">用&nbsp; 户&nbsp; 名：</span></td><td><span class="zl_r">${user.username }</span></td></tr>
	 <tr><td><span class="zl_l">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 称：</span></td>
	 <td>
	 	<span class="zl_r" style="display:none;">
	 		<input type="text" name="ninkName" value="${user.ninkName }" class="wbk"/>
	 	</span>
	 	<span class="zl_r needToChange">
	 		${user.ninkName }
	 	</span>
	 </td></tr>
	 <tr><td><span class="zl_l">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 话：</span></td>
	 <td><span class="zl_r">${user.phone }</span></td></tr>
     <tr>
     <td><span class="zl_l">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 箱：</span></td> 
     <td><span class="zl_r">${user.email }</span></td></tr>
	 <tr>
	 <td><span class="zl_l">所&nbsp; 在&nbsp; 地：</span></td>
	 <td>
		 <span class="zl_r" style="display:none;">
			 <select name="placeId" class="_province">
			 </select> 
			 <select name="placeId" class="_city">
			 </select>
			 <input type="hidden" id="place" name="place" value="${user.place }"></input>
		 </span>
		 <span class="zl_r needToChange">
	 		${user.place }
	 	</span>
	 </td>
	 </tr>
	 <tr>
	 <td><span class="zl_l">联系地址：</span></td>
	 <td>
	 	<span class="zl_r" style="display:none;">
	 		<input type="text" name="address" value="${user.address }" class="wbk"/>
	 	</span>
	 	<span class="zl_r needToChange">
	 		${user.address }
	 	</span>
	 </td>
	 </tr>
	 <tr>
	 <td><span class="zl_l">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 编：</span></td>
	 <td>
	 	<span class="zl_r" style="display:none;">
	 		<input type="text" name="postalCode" value="${user.postalCode }" class="wbk"/>
	 	</span>
	 	<span class="zl_r needToChange">
	 		${user.postalCode }
	 	</span>
	 </td>
	 </tr>
	 <tr>
	 <td><span class="zl_l" id="textTitle" style="">个人简介：</span></td>
	 <td>
	 	<span class="zl_r" style="display:none;">
	 		<textarea class="js_wbk" name="content">${user.content }</textarea>
	 	</span>
	 	<span class="zl_r needToChange">
	 		${user.content }
	 	</span>
	 </td>
	 </tr>
	  </table>
	<div class="bottom1" style="display:none;">
     <img src="${ctx}/images/ud02_03.png" onclick="cancel()"/>
     <img src="${ctx}/images/ud02_05.png" class="an" onclick="subForm()"/>
	 <div class="bottom_sj">上次更新时间：${user.updateTime }</div>
    </div>
  	<div class="bottom needToChange">
	 <img src="${ctx }/images/ud01_07.png" onclick="editDetail()"/>
	 <br/>
	 <div class="bottom_sj">上次更新时间：${user.updateTime }</div>
  	</div>
	  </div>
	  <div class="anzx"></div>
	</div>	
	</form>
<!--点击更换头像的显示层-->
<script>
	function up(){
		$("#up").click();
	}
	function upload(){
		$("#formUp").submit();
	}
	var picPath="";
	$(document).ready(function(data){
   		var iframe = document.getElementById("if");  
	    if (iframe.attachEvent) {  
	        iframe.attachEvent("onload", function() {  
	          	//以下操作必须在iframe加载完后才可进行  
	           	if(iframe.contentWindow.location.href.indexOf("showPic")!=-1){
             		picPath = iframe.contentWindow.location.href;
	             	picPath = picPath.substring(picPath.indexOf("?path=")+6);
	             	picPath = decodeURIComponent(picPath);
				}
	        });  
	    } else {  
	        iframe.onload = function() {  
                 //以下操作必须在iframe加载完后才可进行  
	             if(iframe.contentWindow.location.href.indexOf("showPic")!=-1){
	             	picPath = iframe.contentWindow.location.href;
	             	picPath = picPath.substring(picPath.indexOf("?path=")+6);
	             	picPath = decodeURIComponent(picPath);
	             }
	        };  
	    }  
	});
</script>
<form id="formUp" method="post" target="if" enctype="multipart/form-data" action="<cc:picUploadHost/>/project/upload">
	<input type="hidden" name="cb" value="http://localhost:8080/openMvc/showPic"/>
	<div class="sc_img">
		<div class="user_cont_r_1" style=" width:460px;"><span>更换头像</span></div>
		<div class="sc_kj">
			<input id="up" type="file" name="files" style="display:none;" onchange="upload()"/>
			<img src="${ctx}/images/ud01_04.png" onclick="up()"/>
			<p>(您可以选择一张本地图片上传制作头像。)</p>
		</div>		
		<div class="foot_an">
			<a href="javascript:;"><img src="${ctx}/images/ud01_08.png" class="queren"/></a>
			<a href="javascript:;" class="close"><img src="${ctx}/images/ud01_09.png" class="an quxiao"/></a>
		</div>
	</div>
</form>
<iframe id="if" name="if" src="about:blank" frameborder='0'></iframe>
 <div class="theme-popover-mask"></div>	
 <!--选择图片的大小层-->
 <div class="upimg" style="display:none;">
 <div class="user_cont_r_1" style=" width:460px;"><span>更换头像</span></div>
 <style type="text/css">
#rRightDown,#rLeftDown,#rLeftUp,#rRightUp,#rRight,#rLeft,#rUp,#rDown{position:absolute;background:#F00;width:5px; height:5px; z-index:500; font-size:0;}
#dragDiv{border:1px solid #000000;}
</style>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="50%">
	<div id="bgDiv" style="width:300px; height:400px; margin-left:10px;">
        <div id="dragDiv">
          <div id="rRightDown" style="right:0; bottom:0;"> </div>
          <div id="rLeftDown" style="left:0; bottom:0;"> </div>
          <div id="rRightUp" style="right:0; top:0;"> </div>
          <div id="rLeftUp" style="left:0; top:0;"> </div>
          <div id="rRight" style="right:0; top:50%;"> </div>
          <div id="rLeft" style="left:0; top:50%;"> </div>
          <div id="rUp" style="top:0; left:50%;"> </div>
          <div id="rDown" style="bottom:0;left:50%;"></div>
        </div>
      </div></td>
    <td>
	<div id="viewDiv" style="width:300px; height:400px; margin:0 20px; position:relative;">
<span style="position:absolute; padding:150px 20px 0 40px; text-align:center; font-size:14px; color:#333333;">头像效果</span>
	</div>
	</td>
	
  </tr>
</table>
<div class="foot_an">
	<a href="javascript:;" onclick="sureHeadPic()"><img src="${ctx}/images/ud01_08.png"/></a>
	<a href="javascript:;" class="close"><img src="${ctx}/images/ud01_09.png" class="an quxiao"/></a>
</div>
<script type="text/javascript"><!--
var isIE = (document.all) ? true : false;

var $$ = function (id) {
	return "string" == typeof id ? document.getElementById(id) : id;
};

var Class = {
	create: function() {
		return function() { this.initialize.apply(this, arguments); }
	}
}

var Extend = function(destination, source) {
	for (var property in source) {
		destination[property] = source[property];
	}
}

var Bind = function(object, fun) {
	return function() {
		return fun.apply(object, arguments);
	}
}

var BindAsEventListener = function(object, fun) {
	var args = Array.prototype.slice.call(arguments).slice(2);
	return function(event) {
		return fun.apply(object, [event || window.event].concat(args));
	}
}

var CurrentStyle = function(element){
	return element.currentStyle || document.defaultView.getComputedStyle(element, null);
}

function addEventHandler(oTarget, sEventType, fnHandler) {
	if (oTarget.addEventListener) {
		oTarget.addEventListener(sEventType, fnHandler, false);
	} else if (oTarget.attachEvent) {
		oTarget.attachEvent("on" + sEventType, fnHandler);
	} else {
		oTarget["on" + sEventType] = fnHandler;
	}
};

function removeEventHandler(oTarget, sEventType, fnHandler) {
    if (oTarget.removeEventListener) {
        oTarget.removeEventListener(sEventType, fnHandler, false);
    } else if (oTarget.detachEvent) {
        oTarget.detachEvent("on" + sEventType, fnHandler);
    } else { 
        oTarget["on" + sEventType] = null;
    }
};

//图片切割
var ImgCropper = Class.create();
ImgCropper.prototype = {
  //容器对象,控制层,图片地址
  initialize: function(container, handle, url, options) {
	this._Container = $$(container);//容器对象
	this._layHandle = $$(handle);//控制层
	this.Url = url;//图片地址
	
	this._layBase = this._Container.appendChild(document.createElement("img"));//底层
	this._layCropper = this._Container.appendChild(document.createElement("img"));//切割层
	this._layCropper.onload = Bind(this, this.SetPos);
	//用来设置大小
	this._tempImg = document.createElement("img");
	this._tempImg.onload = Bind(this, this.SetSize);
	
	this.SetOptions(options);
	
	this.Opacity = Math.round(this.options.Opacity);
	this.Color = this.options.Color;
	this.Scale = !!this.options.Scale;
	this.Ratio = Math.max(this.options.Ratio, 0);
	this.Width = Math.round(this.options.Width);
	this.Height = Math.round(this.options.Height);
	
	//设置预览对象
	var oPreview = $$(this.options.Preview);//预览对象
	if(oPreview){
		oPreview.style.position = "relative";
		oPreview.style.overflow = "hidden";
		this.viewWidth = Math.round(this.options.viewWidth);
		this.viewHeight = Math.round(this.options.viewHeight);
		//预览图片对象
		this._view = oPreview.appendChild(document.createElement("img"));
		this._view.style.position = "absolute";
		this._view.onload = Bind(this, this.SetPreview);
	}
	//设置拖放
	this._drag = new Drag(this._layHandle, { Limit: true, onMove: Bind(this, this.SetPos), Transparent: true });
	//设置缩放
	this.Resize = !!this.options.Resize;
	if(this.Resize){
		var op = this.options, _resize = new Resize(this._layHandle, { Max: true, onResize: Bind(this, this.SetPos) });
		//设置缩放触发对象
		op.RightDown && (_resize.Set(op.RightDown, "right-down"));
		op.LeftDown && (_resize.Set(op.LeftDown, "left-down"));
		op.RightUp && (_resize.Set(op.RightUp, "right-up"));
		op.LeftUp && (_resize.Set(op.LeftUp, "left-up"));
		op.Right && (_resize.Set(op.Right, "right"));
		op.Left && (_resize.Set(op.Left, "left"));
		op.Down && (_resize.Set(op.Down, "down"));
		op.Up && (_resize.Set(op.Up, "up"));
		//最小范围限制
		this.Min = !!this.options.Min;
		this.minWidth = Math.round(this.options.minWidth);
		this.minHeight = Math.round(this.options.minHeight);
		//设置缩放对象
		this._resize = _resize;
	}
	//设置样式
	this._Container.style.position = "relative";
	this._Container.style.overflow = "hidden";
	this._layHandle.style.zIndex = 200;
	this._layCropper.style.zIndex = 100;
	this._layBase.style.position = this._layCropper.style.position = "absolute";
	this._layBase.style.top = this._layBase.style.left = this._layCropper.style.top = this._layCropper.style.left = 0;//对齐
	//初始化设置
	this.Init();
  },
  //设置默认属性
  SetOptions: function(options) {
    this.options = {//默认值
		Opacity:	50,//透明度(0到100)
		Color:		"",//背景色
		Width:		0,//图片高度
		Height:		0,//图片高度
		//缩放触发对象
		Resize:		false,//是否设置缩放
		Right:		"",//右边缩放对象
		Left:		"",//左边缩放对象
		Up:			"",//上边缩放对象
		Down:		"",//下边缩放对象
		RightDown:	"",//右下缩放对象
		LeftDown:	"",//左下缩放对象
		RightUp:	"",//右上缩放对象
		LeftUp:		"",//左上缩放对象
		Min:		false,//是否最小宽高限制(为true时下面min参数有用)
		minWidth:	50,//最小宽度
		minHeight:	50,//最小高度
		Scale:		false,//是否按比例缩放
		Ratio:		0,//缩放比例(宽/高)
		//预览对象设置
		Preview:	"",//预览对象
		viewWidth:	0,//预览宽度
		viewHeight:	0//预览高度
    };
    Extend(this.options, options || {});
  },
  //初始化对象
  Init: function() {
	//设置背景色
	this.Color && (this._Container.style.backgroundColor = this.Color);
	//设置图片
	this._tempImg.src = this._layBase.src = this._layCropper.src = this.Url;
	//设置透明
	if(isIE){
		this._layBase.style.filter = "alpha(opacity:" + this.Opacity + ")";
	} else {
		this._layBase.style.opacity = this.Opacity / 100;
	}
	//设置预览对象
	this._view && (this._view.src = this.Url);
	//设置缩放
	if(this.Resize){
		with(this._resize){
			Scale = this.Scale; Ratio = this.Ratio; Min = this.Min; minWidth = this.minWidth; minHeight = this.minHeight;
		}
	}
  },
  //设置切割样式
  SetPos: function() {
	//ie6渲染bug
	with(this._layHandle.style){ zoom = .9; zoom = 1; }
	//获取位置参数
	var p = this.GetPos();
	//按拖放对象的参数进行切割
	this._layCropper.style.clip = "rect(" + p.Top + "px " + (p.Left + p.Width) + "px " + (p.Top + p.Height) + "px " + p.Left + "px)";
	//设置预览
	this.SetPreview();
  },
  //设置预览效果
  SetPreview: function() {
	if(this._view){
		//预览显示的宽和高
		var p = this.GetPos(), s = this.GetSize(p.Width, p.Height, this.viewWidth, this.viewHeight), scale = s.Height / p.Height;
		//按比例设置参数
		var pHeight = this._layBase.height * scale, pWidth = this._layBase.width * scale, pTop = p.Top * scale, pLeft = p.Left * scale;
		//设置预览对象
		with(this._view.style){
			//设置样式
			width = pWidth + "px"; height = pHeight + "px"; top = - pTop + "px "; left = - pLeft + "px";
			//切割预览图
			clip = "rect(" + pTop + "px " + (pLeft + s.Width) + "px " + (pTop + s.Height) + "px " + pLeft + "px)";
		}
	}
  },
  //设置图片大小
  SetSize: function() {
	var s = this.GetSize(this._tempImg.width, this._tempImg.height, this.Width, this.Height);
	//设置底图和切割图
	this._layBase.style.width = this._layCropper.style.width = s.Width + "px";
	this._layBase.style.height = this._layCropper.style.height = s.Height + "px";
	//设置拖放范围
	this._drag.mxRight = s.Width; this._drag.mxBottom = s.Height;
	//设置缩放范围
	if(this.Resize){ this._resize.mxRight = s.Width; this._resize.mxBottom = s.Height; }
  },
  //获取当前样式
  GetPos: function() {
	with(this._layHandle){
		return { Top: offsetTop, Left: offsetLeft, Width: offsetWidth, Height: offsetHeight }
	}
  },
  //获取尺寸
  GetSize: function(nowWidth, nowHeight, fixWidth, fixHeight) {
	var iWidth = nowWidth, iHeight = nowHeight, scale = iWidth / iHeight;
	//按比例设置
	if(fixHeight){ iWidth = (iHeight = fixHeight) * scale; }
	if(fixWidth && (!fixHeight || iWidth > fixWidth)){ iHeight = (iWidth = fixWidth) / scale; }
	//返回尺寸对象
	return { Width: iWidth, Height: iHeight }
  }
}
// --></script>
<script type="text/javascript"><!--
//拖放程序
var Drag = Class.create();
Drag.prototype = {
  //拖放对象
  initialize: function(drag, options) {
	this.Drag = $$(drag);//拖放对象
	this._x = this._y = 0;//记录鼠标相对拖放对象的位置
	this._marginLeft = this._marginTop = 0;//记录margin
	//事件对象(用于绑定移除事件)
	this._fM = BindAsEventListener(this, this.Move);
	this._fS = Bind(this, this.Stop);
	
	this.SetOptions(options);
	
	this.Limit = !!this.options.Limit;
	this.mxLeft = parseInt(this.options.mxLeft);
	this.mxRight = parseInt(this.options.mxRight);
	this.mxTop = parseInt(this.options.mxTop);
	this.mxBottom = parseInt(this.options.mxBottom);
	
	this.LockX = !!this.options.LockX;
	this.LockY = !!this.options.LockY;
	this.Lock = !!this.options.Lock;
	
	this.onStart = this.options.onStart;
	this.onMove = this.options.onMove;
	this.onStop = this.options.onStop;
	
	this._Handle = $$(this.options.Handle) || this.Drag;
	this._mxContainer = $$(this.options.mxContainer) || null;
	
	this.Drag.style.position = "absolute";
	//透明
	if(isIE && !!this.options.Transparent){
		//填充拖放对象
		with(this._Handle.appendChild(document.createElement("div")).style){
			width = height = "100%"; backgroundColor = "#fff"; filter = "alpha(opacity:0)"; fontSize = 0;
		}
	}
	//修正范围
	this.Repair();
	addEventHandler(this._Handle, "mousedown", BindAsEventListener(this, this.Start));
  },
  //设置默认属性
  SetOptions: function(options) {
	this.options = {//默认值
		Handle:			"",//设置触发对象（不设置则使用拖放对象）
		Limit:			false,//是否设置范围限制(为true时下面参数有用,可以是负数)
		mxLeft:			0,//左边限制
		mxRight:		9999,//右边限制
		mxTop:			0,//上边限制
		mxBottom:		9999,//下边限制
		mxContainer:	"",//指定限制在容器内
		LockX:			false,//是否锁定水平方向拖放
		LockY:			false,//是否锁定垂直方向拖放
		Lock:			false,//是否锁定
		Transparent:	false,//是否透明
		onStart:		function(){},//开始移动时执行
		onMove:			function(){},//移动时执行
		onStop:			function(){}//结束移动时执行
	};
	Extend(this.options, options || {});
  },
  //准备拖动
  Start: function(oEvent) {
	if(this.Lock){ return; }
	this.Repair();
	//记录鼠标相对拖放对象的位置
	this._x = oEvent.clientX - this.Drag.offsetLeft;
	this._y = oEvent.clientY - this.Drag.offsetTop;
	//记录margin
	this._marginLeft = parseInt(CurrentStyle(this.Drag).marginLeft) || 0;
	this._marginTop = parseInt(CurrentStyle(this.Drag).marginTop) || 0;
	//mousemove时移动 mouseup时停止
	addEventHandler(document, "mousemove", this._fM);
	addEventHandler(document, "mouseup", this._fS);
	if(isIE){
		//焦点丢失
		addEventHandler(this._Handle, "losecapture", this._fS);
		//设置鼠标捕获
		this._Handle.setCapture();
	}else{
		//焦点丢失
		addEventHandler(window, "blur", this._fS);
		//阻止默认动作
		oEvent.preventDefault();
	};
	//附加程序
	this.onStart();
  },
  //修正范围
  Repair: function() {
	if(this.Limit){
		//修正错误范围参数
		this.mxRight = Math.max(this.mxRight, this.mxLeft + this.Drag.offsetWidth);
		this.mxBottom = Math.max(this.mxBottom, this.mxTop + this.Drag.offsetHeight);
		//如果有容器必须设置position为relative来相对定位，并在获取offset之前设置
		!this._mxContainer || CurrentStyle(this._mxContainer).position == "relative" || (this._mxContainer.style.position = "relative");
	}
  },
  //拖动
  Move: function(oEvent) {
	//判断是否锁定
	if(this.Lock){ this.Stop(); return; };
	//清除选择
	window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
	//设置移动参数
	var iLeft = oEvent.clientX - this._x, iTop = oEvent.clientY - this._y;
	//设置范围限制
	if(this.Limit){
		//设置范围参数
		var mxLeft = this.mxLeft, mxRight = this.mxRight, mxTop = this.mxTop, mxBottom = this.mxBottom;
		//如果设置了容器，再修正范围参数
		if(!!this._mxContainer){
			mxLeft = Math.max(mxLeft, 0);
			mxTop = Math.max(mxTop, 0);
			mxRight = Math.min(mxRight, this._mxContainer.clientWidth);
			mxBottom = Math.min(mxBottom, this._mxContainer.clientHeight);
		};
		//修正移动参数
		iLeft = Math.max(Math.min(iLeft, mxRight - this.Drag.offsetWidth), mxLeft);
		iTop = Math.max(Math.min(iTop, mxBottom - this.Drag.offsetHeight), mxTop);
	}
	//设置位置，并修正margin
	if(!this.LockX){ this.Drag.style.left = iLeft - this._marginLeft + "px"; }
	if(!this.LockY){ this.Drag.style.top = iTop - this._marginTop + "px"; }
	//附加程序
	this.onMove();
  },
  //停止拖动
  Stop: function() {
	//移除事件
	removeEventHandler(document, "mousemove", this._fM);
	removeEventHandler(document, "mouseup", this._fS);
	if(isIE){
		removeEventHandler(this._Handle, "losecapture", this._fS);
		this._Handle.releaseCapture();
	}else{
		removeEventHandler(window, "blur", this._fS);
	};
	//附加程序
	this.onStop();
  }
};
// --></script>
<script type="text/javascript"><!--
//缩放程序
var Resize = Class.create();
Resize.prototype = {
  //缩放对象
  initialize: function(obj, options) {
	this._obj = $$(obj);//缩放对象
	
	this._styleWidth = this._styleHeight = this._styleLeft = this._styleTop = 0;//样式参数
	this._sideRight = this._sideDown = this._sideLeft = this._sideUp = 0;//坐标参数
	this._fixLeft = this._fixTop = 0;//定位参数
	this._scaleLeft = this._scaleTop = 0;//定位坐标
	
	this._mxSet = function(){};//范围设置程序
	this._mxRightWidth = this._mxDownHeight = this._mxUpHeight = this._mxLeftWidth = 0;//范围参数
	this._mxScaleWidth = this._mxScaleHeight = 0;//比例范围参数
	
	this._fun = function(){};//缩放执行程序
	
	//获取边框宽度
	var _style = CurrentStyle(this._obj);
	this._borderX = (parseInt(_style.borderLeftWidth) || 0) + (parseInt(_style.borderRightWidth) || 0);
	this._borderY = (parseInt(_style.borderTopWidth) || 0) + (parseInt(_style.borderBottomWidth) || 0);
	//事件对象(用于绑定移除事件)
	this._fR = BindAsEventListener(this, this.Resize);
	this._fS = Bind(this, this.Stop);
	
	this.SetOptions(options);
	//范围限制
	this.Max = !!this.options.Max;
	this._mxContainer = $$(this.options.mxContainer) || null;
	this.mxLeft = Math.round(this.options.mxLeft);
	this.mxRight = Math.round(this.options.mxRight);
	this.mxTop = Math.round(this.options.mxTop);
	this.mxBottom = Math.round(this.options.mxBottom);
	//宽高限制
	this.Min = !!this.options.Min;
	this.minWidth = Math.round(this.options.minWidth);
	this.minHeight = Math.round(this.options.minHeight);
	//按比例缩放
	this.Scale = !!this.options.Scale;
	this.Ratio = Math.max(this.options.Ratio, 0);
	
	this.onResize = this.options.onResize;
	
	this._obj.style.position = "absolute";
	!this._mxContainer || CurrentStyle(this._mxContainer).position == "relative" || (this._mxContainer.style.position = "relative");
  },
  //设置默认属性
  SetOptions: function(options) {
    this.options = {//默认值
		Max:		false,//是否设置范围限制(为true时下面mx参数有用)
		mxContainer:"",//指定限制在容器内
		mxLeft:		0,//左边限制
		mxRight:	9999,//右边限制
		mxTop:		0,//上边限制
		mxBottom:	9999,//下边限制
		Min:		false,//是否最小宽高限制(为true时下面min参数有用)
		minWidth:	50,//最小宽度
		minHeight:	50,//最小高度
		Scale:		false,//是否按比例缩放
		Ratio:		0,//缩放比例(宽/高)
		onResize:	function(){}//缩放时执行
    };
    Extend(this.options, options || {});
  },
  //设置触发对象
  Set: function(resize, side) {
	var resize = $$(resize), fun;
	if(!resize) return;
	//根据方向设置
	switch (side.toLowerCase()) {
	case "up" :
		fun = this.Up;
		break;
	case "down" :
		fun = this.Down;
		break;
	case "left" :
		fun = this.Left;
		break;
	case "right" :
		fun = this.Right;
		break;
	case "left-up" :
		fun = this.LeftUp;
		break;
	case "right-up" :
		fun = this.RightUp;
		break;
	case "left-down" :
		fun = this.LeftDown;
		break;
	case "right-down" :
	default :
		fun = this.RightDown;
	};
	//设置触发对象
	addEventHandler(resize, "mousedown", BindAsEventListener(this, this.Start, fun));
  },
  //准备缩放
  Start: function(e, fun, touch) {	
	//防止冒泡(跟拖放配合时设置)
	e.stopPropagation ? e.stopPropagation() : (e.cancelBubble = true);
	//设置执行程序
	this._fun = fun;
	//样式参数值
	this._styleWidth = this._obj.clientWidth;
	this._styleHeight = this._obj.clientHeight;
	this._styleLeft = this._obj.offsetLeft;
	this._styleTop = this._obj.offsetTop;
	//四条边定位坐标
	this._sideLeft = e.clientX - this._styleWidth;
	this._sideRight = e.clientX + this._styleWidth;
	this._sideUp = e.clientY - this._styleHeight;
	this._sideDown = e.clientY + this._styleHeight;
	//top和left定位参数
	this._fixLeft = this._styleLeft + this._styleWidth;
	this._fixTop = this._styleTop + this._styleHeight;
	//缩放比例
	if(this.Scale){
		//设置比例
		this.Ratio = Math.max(this.Ratio, 0) || this._styleWidth / this._styleHeight;
		//left和top的定位坐标
		this._scaleLeft = this._styleLeft + this._styleWidth / 2;
		this._scaleTop = this._styleTop + this._styleHeight / 2;
	};
	//范围限制
	if(this.Max){
		//设置范围参数
		var mxLeft = this.mxLeft, mxRight = this.mxRight, mxTop = this.mxTop, mxBottom = this.mxBottom;
		//如果设置了容器，再修正范围参数
		if(!!this._mxContainer){
			mxLeft = Math.max(mxLeft, 0);
			mxTop = Math.max(mxTop, 0);
			mxRight = Math.min(mxRight, this._mxContainer.clientWidth);
			mxBottom = Math.min(mxBottom, this._mxContainer.clientHeight);
		};
		//根据最小值再修正
		mxRight = Math.max(mxRight, mxLeft + (this.Min ? this.minWidth : 0) + this._borderX);
		mxBottom = Math.max(mxBottom, mxTop + (this.Min ? this.minHeight : 0) + this._borderY);
		//由于转向时要重新设置所以写成function形式
		this._mxSet = function(){
			this._mxRightWidth = mxRight - this._styleLeft - this._borderX;
			this._mxDownHeight = mxBottom - this._styleTop - this._borderY;
			this._mxUpHeight = Math.max(this._fixTop - mxTop, this.Min ? this.minHeight : 0);
			this._mxLeftWidth = Math.max(this._fixLeft - mxLeft, this.Min ? this.minWidth : 0);
		};
		this._mxSet();
		//有缩放比例下的范围限制
		if(this.Scale){
			this._mxScaleWidth = Math.min(this._scaleLeft - mxLeft, mxRight - this._scaleLeft - this._borderX) * 2;
			this._mxScaleHeight = Math.min(this._scaleTop - mxTop, mxBottom - this._scaleTop - this._borderY) * 2;
		};
	};
	//mousemove时缩放 mouseup时停止
	addEventHandler(document, "mousemove", this._fR);
	addEventHandler(document, "mouseup", this._fS);
	if(isIE){
		addEventHandler(this._obj, "losecapture", this._fS);
		this._obj.setCapture();
	}else{
		addEventHandler(window, "blur", this._fS);
		e.preventDefault();
	};
  },
  //缩放
  Resize: function(e) {
	//清除选择
	window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
	//执行缩放程序
	this._fun(e);
	//设置样式，变量必须大于等于0否则ie出错
	with(this._obj.style){
		width = this._styleWidth + "px"; height = this._styleHeight + "px";
		top = this._styleTop + "px"; left = this._styleLeft + "px";
	}
	//附加程序
	this.onResize();
  },
  //缩放程序
  //上
  Up: function(e) {
	this.RepairY(this._sideDown - e.clientY, this._mxUpHeight);
	this.RepairTop();
	this.TurnDown(this.Down);
  },
  //下
  Down: function(e) {
	this.RepairY(e.clientY - this._sideUp, this._mxDownHeight);
	this.TurnUp(this.Up);
  },
  //右
  Right: function(e) {
	this.RepairX(e.clientX - this._sideLeft, this._mxRightWidth);
	this.TurnLeft(this.Left);
  },
  //左
  Left: function(e) {
	this.RepairX(this._sideRight - e.clientX, this._mxLeftWidth);
	this.RepairLeft();
	this.TurnRight(this.Right);
  },
  //右下
  RightDown: function(e) {
	this.RepairAngle(
		e.clientX - this._sideLeft, this._mxRightWidth,
		e.clientY - this._sideUp, this._mxDownHeight
	);
	this.TurnLeft(this.LeftDown) || this.TurnUp(this.RightUp);
  },
  //右上
  RightUp: function(e) {
	this.RepairAngle(
		e.clientX - this._sideLeft, this._mxRightWidth,
		this._sideDown - e.clientY, this._mxUpHeight
	);
	this.RepairTop();
	this.TurnLeft(this.LeftUp) || this.TurnDown(this.RightDown);
  },
  //左下
  LeftDown: function(e) {
	this.RepairAngle(
		this._sideRight - e.clientX, this._mxLeftWidth,
		e.clientY - this._sideUp, this._mxDownHeight
	);
	this.RepairLeft();
	this.TurnRight(this.RightDown) || this.TurnUp(this.LeftUp);
  },
  //左上
  LeftUp: function(e) {
	this.RepairAngle(
		this._sideRight - e.clientX, this._mxLeftWidth,
		this._sideDown - e.clientY, this._mxUpHeight
	);
	this.RepairTop(); this.RepairLeft();
	this.TurnRight(this.RightUp) || this.TurnDown(this.LeftDown);
  },
  //修正程序
  //水平方向
  RepairX: function(iWidth, mxWidth) {
	iWidth = this.RepairWidth(iWidth, mxWidth);
	if(this.Scale){
		var iHeight = Math.round(iWidth / this.Ratio);
		if(this.Max && iHeight > this._mxScaleHeight){
			iWidth = Math.round((iHeight = this._mxScaleHeight) * this.Ratio);
		}else if(this.Min && iHeight < this.minHeight){
			var tWidth = Math.round(this.minHeight * this.Ratio);
			if(tWidth < mxWidth){ iHeight = this.minHeight; iWidth = tWidth; }
		}
		this._styleHeight = iHeight;
		this._styleTop = this._scaleTop - iHeight / 2;
	}
	this._styleWidth = iWidth;
  },
  //垂直方向
  RepairY: function(iHeight, mxHeight) {
	iHeight = this.RepairHeight(iHeight, mxHeight);
	if(this.Scale){
		var iWidth = Math.round(iHeight * this.Ratio);
		if(this.Max && iWidth > this._mxScaleWidth){
			iHeight = Math.round((iWidth = this._mxScaleWidth) / this.Ratio);
		}else if(this.Min && iWidth < this.minWidth){
			var tHeight = Math.round(this.minWidth / this.Ratio);
			if(tHeight < mxHeight){ iWidth = this.minWidth; iHeight = tHeight; }
		}
		this._styleWidth = iWidth;
		this._styleLeft = this._scaleLeft - iWidth / 2;
	}
	this._styleHeight = iHeight;
  },
  //对角方向
  RepairAngle: function(iWidth, mxWidth, iHeight, mxHeight) {
	iWidth = this.RepairWidth(iWidth, mxWidth);	
	if(this.Scale){
		iHeight = Math.round(iWidth / this.Ratio);
		if(this.Max && iHeight > mxHeight){
			iWidth = Math.round((iHeight = mxHeight) * this.Ratio);
		}else if(this.Min && iHeight < this.minHeight){
			var tWidth = Math.round(this.minHeight * this.Ratio);
			if(tWidth < mxWidth){ iHeight = this.minHeight; iWidth = tWidth; }
		}
	}else{
		iHeight = this.RepairHeight(iHeight, mxHeight);
	}
	this._styleWidth = iWidth;
	this._styleHeight = iHeight;
  },
  //top
  RepairTop: function() {
	this._styleTop = this._fixTop - this._styleHeight;
  },
  //left
  RepairLeft: function() {
	this._styleLeft = this._fixLeft - this._styleWidth;
  },
  //height
  RepairHeight: function(iHeight, mxHeight) {
	iHeight = Math.min(this.Max ? mxHeight : iHeight, iHeight);
	iHeight = Math.max(this.Min ? this.minHeight : iHeight, iHeight, 0);
	return iHeight;
  },
  //width
  RepairWidth: function(iWidth, mxWidth) {
	iWidth = Math.min(this.Max ? mxWidth : iWidth, iWidth);
	iWidth = Math.max(this.Min ? this.minWidth : iWidth, iWidth, 0);
	return iWidth;
  },
  //转向程序
  //转右
  TurnRight: function(fun) {
	if(!(this.Min || this._styleWidth)){
		this._fun = fun;
		this._sideLeft = this._sideRight;
		this.Max && this._mxSet();
		return true;
	}
  },
  //转左
  TurnLeft: function(fun) {
	if(!(this.Min || this._styleWidth)){
		this._fun = fun;
		this._sideRight = this._sideLeft;
		this._fixLeft = this._styleLeft;
		this.Max && this._mxSet();
		return true;
	}
  },
  //转上
  TurnUp: function(fun) {
	if(!(this.Min || this._styleHeight)){
		this._fun = fun;
		this._sideDown = this._sideUp;
		this._fixTop = this._styleTop;
		this.Max && this._mxSet();
		return true;
	}
  },
  //转下
  TurnDown: function(fun) {
	if(!(this.Min || this._styleHeight)){
		this._fun = fun;
		this._sideUp = this._sideDown;
		this.Max && this._mxSet();
		return true;
	}
  },
  //停止缩放
  Stop: function() {
	removeEventHandler(document, "mousemove", this._fR);
	removeEventHandler(document, "mouseup", this._fS);
	if(isIE){
		removeEventHandler(this._obj, "losecapture", this._fS);
		this._obj.releaseCapture();
	}else{
		removeEventHandler(window, "blur", this._fS);
	}
  }
};
// --></script>
<style type="text/css"><!--
#rRightDown,#rLeftDown,#rLeftUp,#rRightUp,#rRight,#rLeft,#rUp,#rDown{
	position:absolute;
	background:#FFF;
	border: 1px solid #333;
	width: 6px;
	height: 6px;
	z-index:500;
	font-size:0;
	opacity: 0.5;
	filter:alpha(opacity=50);
}

#rLeftDown,#rRightUp{cursor:ne-resize;}
#rRightDown,#rLeftUp{cursor:nw-resize;}
#rRight,#rLeft{cursor:e-resize;}
#rUp,#rDown{cursor:n-resize;}

#rLeftDown{left:-4px;bottom:-4px;}
#rRightUp{right:-4px;top:-4px;}
#rRightDown{right:-4px;bottom:-4px;background-color:#00F;}
#rLeftUp{left:-4px;top:-4px;}
#rRight{right:-4px;top:50%;margin-top:-4px;}
#rLeft{left:-4px;top:50%;margin-top:-4px;}
#rUp{top:-4px;left:50%;margin-left:-4px;}
#rDown{bottom:-4px;left:50%;margin-left:-4px;}

#bgDiv{width:300px; height:400px; border:1px solid #666666; position:relative;}
#dragDiv{border:1px dashed #fff; width:60px; height:60px; top:50px; left:50px; cursor:move; }
--></style>
<script type="text/javascript"><!--
var ic = new ImgCropper("bgDiv", "dragDiv", "http://image.tianjimedia.com/uploadImages/2012/061/YSZ921GIZ524.jpg", {
	Width: 300, Height: 400, Color: "#000",
	Resize: true,
	Right: "rRight", Left: "rLeft", Up:	"rUp", Down: "rDown",
	RightDown: "rRightDown", LeftDown: "rLeftDown", RightUp: "rRightUp", LeftUp: "rLeftUp",
	Preview: "viewDiv", viewWidth: 300, viewHeight: 400,Scale:true
});
// --></script>
 </div>
  <div class="theme-popover-mask"></div>	
</div>
</div>
</body>
</html>