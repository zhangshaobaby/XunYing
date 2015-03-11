<%@ page contentType="text/html;charset=UTF-8" %>
<%
 String androidpath = "http://download.1717tou.com:8080/apk/data/tou1717.apk";

 String iphonepath = "https://itunes.apple.com/us/app/yi-qi-yi-qi-tou/id938627062?l=zh&amp;ls=1&amp;mt=8";

 
String[] ANDROID_SPECIFIC_SUBSTRING = { "Android"};
String[] IPHONE_SPECIFIC_SUBSTRING = { "iPad","iPhone"};
String userAgent = request.getHeader("user-agent"); 
int android_iphone=0;//0未知 1android 2iphone
 	for (String mobile: ANDROID_SPECIFIC_SUBSTRING){
    		if (userAgent.contains(mobile) || userAgent.contains(mobile.toUpperCase()) || userAgent.contains(mobile.toLowerCase())){
    			android_iphone=1;
    			}
    }
    for (String mobile: IPHONE_SPECIFIC_SUBSTRING){
    		if (userAgent.contains(mobile) || userAgent.contains(mobile.toUpperCase()) || userAgent.contains(mobile.toLowerCase())){
    			android_iphone=2;
    			}
    } 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<title>下载页面</title>
<link rel="stylesheet" type="text/css" href="css/xzym.css"/>
<script type="text/javascript">
function getOs(type){
   if(navigator.appVersion.indexOf("MicroMessenger")>0) {  
       document.getElementById("aa").style.display="block";
      
        return true;
   }else{
     window.location=type;
   }  
     
} 
</script>
</head>
<body>

<div class="zaym">

<div class="top">
<div align="center" id="aa" style="display:none"><img src="images/pc_help.png" width="100%"/></div>
 
<div class="tp">
<div class="xz">

<a  href="#" onclick="getOs('<%=iphonepath%>')">
 <img src="images/03_03.gif" class="tu" border="0"/>
</a>
<a  href="#" onclick="getOs('<%=androidpath%>')">
 <img src="images/02_06.gif" border="0"/>
</a>
</div>
</div>
</div>
<div class="zj_tu"><div class="zj_img"><img src="images/04_02.gif"/></div>
</div>
<div class="zj_tu1"><div class="zj_img"><img src="images/04_03.gif"/></div></div>
<div class="zj_tu2"><div class="zj_img"><img src="images/04_04.gif"/></div></div>


<div class="db">
<div class="rwm">
<img src="images/rwm_03.gif" class="rwm_t"/>
<span class="zt">下载客户端</span>
<div class="dxz">
<a  href="#" onclick="getOs('<%=iphonepath%>')">
 <img src="images/06_06.gif" class="si" border="0"/>
</a>
<a  href="#" onclick="getOs('<%=androidpath%>')">
 <img src="images/06_03.gif" class="right" border="0"/>
</a>
</div>
</div>
</div>

</div>
</body>
</html>
