$(document).ready(function(){
	$.formValidator.initConfig({formID:"form_anquan1",theme:"ArrowSolidBox",submitOnce:true,
		ajaxPrompt : '有数据正在异步验证，请稍等...'
	});

	$("#us").formValidator({onShowText:"请输入真实姓名",onShow:"请输入真实姓名",onFocus:"至少2个文字符,最多10个字符",onCorrect:"该姓名有效"}).inputValidator({min:2,max:10,onError:"你输入的姓名非法,请确认"})//.regexValidator({regExp:"username",dataType:"enum",onError:"用户名格式不正确"})
	$("#sfzh").formValidator({ajax:true,onShow:"请输入15或18位的身份证",onFocus:"输入15或18位的身份证",onCorrect:"输入正确"}).functionValidator({fun:isCardID});
});
