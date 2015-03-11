
function validation() {
	jQuery("#msgValue").empty();
	var username = jQuery("#username").val();
	var password = jQuery("#password").val();
	if (username == "") {
		jQuery("<p>\u7528\u6237\u540d\u4e0d\u80fd\u4e3a\u7a7a\uff01</p>").appendTo(jQuery("#msgValue"));
		return "emptyUse";
	}
	if (password == "") {
		jQuery("<p>\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a\uff01</p>").appendTo(jQuery("#msgValue"));
		return "emptyPwd";
	}
	return "true";
}

function submitForm() {
	var valiValue = validation();
	if (valiValue == "true") {
	document.forms["loginForm"].submit();
	}
}
//记住我
function saveMe(){
var username = jQuery("#username").val();
SetCookie("username",username);
}

function SetCookie(name,value)//两个参数，一个是cookie的名子，一个是值
{
 
    var Days = 30; //此 cookie 将被保存 30 天
 
    var exp = new Date();    //new Date("December 31, 9998");
 
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
 
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
 
}
 function getCookie(name)//取cookies函数        
 
{
 
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
 
     if(arr != null) return unescape(arr[2]); return null;
}


