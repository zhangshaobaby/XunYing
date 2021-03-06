$(document).ready(function(){
	$.formValidator.initConfig({formID:"form_changePwd",theme:"ArrowSolidBox",submitOnce:true,
		 onSuccess: function() { 
		  var username = $("#username").val();
		var password = jQuery("#password1").val();
		var enc_str = Encrypt(username, password);
		jQuery("#password1").val(enc_str);
		 document.forms(0).submit();
		 }, 
		 ajaxPrompt : '有数据正在异步验证，请稍等...'
	});

	$("#password").formValidator({onShow:"请输入密码",onFocus:"至少6个长度",onCorrect:"密码合法"}).inputValidator({min:6,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码格式有错"})//.regexValidator({regExp:"username",dataType:"enum",onError:"用户名格式不正确"})
	    .ajaxValidator({
		dataType : "json",
		async : true,
		url : basepath + "/user/checkpwd.action",
		success : function(data){
            if( data == "0" ) return true;
			return "密码不正确";
		},
		buttons: $("#button"),
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "密码不正确，请重新填写",
		onWait : "正在对用户密码进行合法性校验，请稍候..."
	});
	$("#password1").formValidator({onShowFixText:"6~16个字符，包括字母、数字、特殊符号，区分大小写",onShow:"请输入密码",onFocus:"至少6个长度",onCorrect:"密码合法"}).inputValidator({min:6,max:16,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码长度错误,请确认"}).passwordValidator({compareID:"us"});
	$("#password2").formValidator({onShowFixText:"请再次输入密码",onShow:"输再次输入密码",onFocus:"至少1个长度",onCorrect:"密码一致"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"}).compareValidator({desID:"password1",operateor:"=",onError:"2次密码不一致,请确认"});
});

function secritpwd(){
        var username = $("#username").val();
		var password = jQuery("#password1").val();
		var enc_str = Encrypt(username, password);
		jQuery("#password1").val(enc_str);
}

