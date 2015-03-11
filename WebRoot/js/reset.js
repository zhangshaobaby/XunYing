
var userVail = "false", pwdVail = "false";
$(document).ready(function () {
	$.formValidator.initConfig({formID:"form-reg", theme:"ArrowSolidBox", submitOnce:true, onSuccess:function () {

		document.forms["form-reg"].submit();
	}, ajaxPrompt:"\u6709\u6570\u636e\u6b63\u5728\u5f02\u6b65\u9a8c\u8bc1\uff0c\u8bf7\u7a0d\u7b49..."});
	$("#us").formValidator({onShowText:"\u7531\u5b57\u6bcd\u3001\u6570\u5b57\u3001\u4e0b\u5212\u7ebf\u7ec4\u6210", onShow:"\u8bf7\u8f93\u5165\u7528\u6237\u540d", onFocus:"\u7528\u6237\u540d\u81f3\u5c116\u4e2a\u5b57\u7b26,\u6700\u591a16\u4e2a\u5b57\u7b26", onCorrect:"\u8be5\u7528\u6237\u540d\u53ef\u4ee5\u6ce8\u518c"}).inputValidator({min:6, max:16, onError:"\u4f60\u8f93\u5165\u7684\u7528\u6237\u540d\u975e\u6cd5,\u8bf7\u786e\u8ba4"}).regexValidator({regExp:"^\\w+$", onError:"\u7528\u6237\u540d\u683c\u5f0f\u4e0d\u6b63\u786e"}).ajaxValidator({async:true, url:basepath + "/user/checkUser", success:function (result) {
		if (result == "noexsit") {
			return true;
		}
		return "该用户名已存在,请重新设置";
	}, buttons:$("#button"), error:function (jqXHR, textStatus, errorThrown) {
		alert("\u670d\u52a1\u5668\u6ca1\u6709\u8fd4\u56de\u6570\u636e\uff0c\u53ef\u80fd\u670d\u52a1\u5668\u5fd9\uff0c\u8bf7\u91cd\u8bd5" + errorThrown);
	}, onError:"\u8be5\u7528\u6237\u540d\u4e0d\u53ef\u7528\uff0c\u8bf7\u66f4\u6362\u7528\u6237\u540d", onWait:"\u6b63\u5728\u5bf9\u7528\u6237\u540d\u8fdb\u884c\u5408\u6cd5\u6027\u6821\u9a8c\uff0c\u8bf7\u7a0d\u5019..."}).defaultPassed();
	$("#password1").formValidator({onShowFixText:"6~16\u4e2a\u5b57\u7b26\uff0c\u5305\u62ec\u5b57\u6bcd\u3001\u6570\u5b57\u3001\u7279\u6b8a\u7b26\u53f7\uff0c\u533a\u5206\u5927\u5c0f\u5199", onShow:"\u8bf7\u8f93\u5165\u5bc6\u7801", onFocus:"\u81f3\u5c111\u4e2a\u957f\u5ea6", onCorrect:"\u5bc6\u7801\u5408\u6cd5"}).inputValidator({min:6, max:16, empty:{leftEmpty:false, rightEmpty:false, emptyError:"\u5bc6\u7801\u4e24\u8fb9\u4e0d\u80fd\u6709\u7a7a\u7b26\u53f7"}, onError:"\u5bc6\u7801\u957f\u5ea6\u9519\u8bef,\u8bf7\u786e\u8ba4"}).passwordValidator({compareID:"us"});
	$("#password2").formValidator({onShowFixText:"请再次输入密码", onShow:"请再次输入密码", onFocus:"请再次输入密码", onCorrect:"\u5bc6\u7801\u4e00\u81f4"}).inputValidator({min:1, empty:{leftEmpty:false, rightEmpty:false, emptyError:"\u91cd\u590d\u5bc6\u7801\u4e24\u8fb9\u4e0d\u80fd\u6709\u7a7a\u7b26\u53f7"}, onError:"\u91cd\u590d\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a,\u8bf7\u786e\u8ba4"}).compareValidator({desID:"password1", operateor:"=", onError:"2\u6b21\u5bc6\u7801\u4e0d\u4e00\u81f4,\u8bf7\u786e\u8ba4"});
	$("#agent").formValidator({empty:true,onShow:"推荐人的手机号码，可以为空",onFocus:"你要是输入了，必须输入正确",onCorrect:"谢谢你的合作"}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的,请确认"}).regexValidator({regExp:"^1[3-9]{1}[0-9]{9}$",onError:"你输入的手机号码格式不正确"}).ajaxValidator({async:true, url:basepath + "/user/checkagent", success:function (result) {
		if (result == "exsit") {
			return true;
		}
		return "无此推荐人。";
	}});
	$("#code").formValidator({ajax:true,onShow:"请输入验证码",onFocus:"请输入验证码",onCorrect:"请输入验证码"}).inputValidator({min:4,onError:"请输入4位验证码"}).ajaxValidator({async:true, url:basepath +"/checksessionattr?attrname=identify", success:function (result) {		
if (result=="true") {
			return true;
		}
		return "验证码输入错误,请重新输入";
}})
//$("#testcode").formValidator({ajax:true,onShow:"请输入口令",onFocus:"请输入口令",onCorrect:"请输入口令"}).regexValidator({regExp:"^1718$",onError:"口令输入错误"});
	$(":checkbox[name='iagree']").formValidator({tipID:"iagreeTip",onShow:"请接受注册协议",onFocus:"请接受注册协议",onCorrect:"谢谢"}).inputValidator({min:1,onError:"请同意注册协议"});
});
function changeCode() {
	var img = document.getElementById("imgCode");
	img.src = basepath + "/identify?" + new Date();
}

function validationEmptyCode() {
	var phoneCode = $("#phoneCode").val();
	if (phoneCode == "") {
		alert("\u8bf7\u8f93\u5165\u624b\u673a\u9a8c\u8bc1\u7801");
		return "false";
	}
}
function checkPhoneCode() {
	var result = validationEmptyCode();
	if (result == "false") {
		return;
	} else {
		var data=jQuery("#form-reg").serialize();
		jQuery.ajax({type:"POST", url:basepath + "/user/checkPhoneCode", data:data, success:function (result) {
			jQuery("#msgValue").empty();
			if (result.msgCode == "success") {
				window.location.href = basepath + "/user/registerFinish";
			} else {
				jQuery("<html>" + result.msgString + "</html>").appendTo(jQuery("#msgValue"));
			}
		}});
	}
}
function changePhoneCode() {
	var phone = $("#phone").val();
	jQuery.ajax({type:"POST", url:basepath + "/user/getPhoneCode.action", data:"phone=" + phone, success:function (result) {
		if (result == "success") {
			getTime(5);
		} else {
			alert("\u9a8c\u8bc1\u7801\u53d1\u9001\u5931\u8d25!\u8bf7\u7a0d\u540e\u91cd\u8bd5");
		}
	}});
}
function changePhone() {
	$("#reg2_b").hide();
	$("#reg2_a").show();
}


