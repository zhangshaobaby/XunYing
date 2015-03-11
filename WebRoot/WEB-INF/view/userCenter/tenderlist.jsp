<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="网站描述" />
		<meta name="keywords" content="关键字" />
		<link href="<%=path%>/css/index.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/user_jilu.css" rel="stylesheet"
			type="text/css" />
		<Script src="<%=path%>/js/jquery-1.7.js"></Script>
		<script type="text/javascript" src="<%=path%>/js/base.js"></script>
		<script src="<%=path%>/js/formValidator-4.1.3.js"
			type="text/javascript" charset="UTF-8"></script>
		<Script src="<%=path%>/js/dateTimePicker/WdatePicker.js"></Script>
		<title>1717tou.com</title>
		<script type="" text/javascript>
     $(document).ready(function(){
         //计算总转让本金和转让价格
           $("input[name='checkbox']").change(function(){
           var transAmt=0.00;
           var price=0.00 ;
           var selectTenderIds="";
           $("input[name='checkbox']").each(function(index,item){
               if($(item).is(':checked')){
             var id=$(item).val();
             var transAmtvalue=$("#transAmt"+id).html();
             var pricevalue=$("#price"+id).val();
             transAmt=transAmt+parseFloat(transAmtvalue);
             price=price+parseFloat(pricevalue);
             selectTenderIds=selectTenderIds+id+",";
           }
    
            })
            $("#selectTenderIds").val(selectTenderIds);
            $("#transAmt").html(transAmt);
            $("#transprice").html(price);
        } )

}

)
$(document).ready(function(){
	$.formValidator.initConfig({formID:"creditForm",theme:"ArrowSolidBox",submitOnce:true,
		ajaxPrompt : '有数据正在异步验证，请稍等...'
	});
	$("#transPwd").formValidator({onShowText:"请输入交易密码",onShow:"请输入交易密码",onFocus:"请输入交易密码",onCorrect:""})
.ajaxValidator({async:true, url:"<%=path%>/user/checkpaypwd", success:function (result) {
		if (result =="0") {
			return true;
		}
		return "交易密码错误,请重新输入";
}})
	 $("#creditePhone").formValidator({ajax:true,onShow:"请输入验证码",onFocus:"请输入验证码",onCorrect:"请输入验证码"}).ajaxValidator({async:true, url:"<%=path%>/checksessionattr?attrname=creditAssignPhoneCode", success:function (result) {		
if (result=="true") {
			return true;
		}
		return "验证码输入错误,请重新输入";
}})
});


		function checkAllTender(obj,checkboxName){
           var transAmt=0.00;
           var price=0.00 ;
           var selectTenderIds="";
		var objs = document.getElementsByName(checkboxName);
		for(i=0;i<objs.length;i++){
			objs[i].checked=obj.checked;
		}
     $("input[name='checkbox']").each(function(index,item){
               if($(item).is(':checked')){
             var id=$(item).val();
             var transAmtvalue=$("#transAmt"+id).html();
             var pricevalue=$("#price"+id).val();
             transAmt=transAmt+parseFloat(transAmtvalue);
             price=price+parseFloat(pricevalue);
             selectTenderIds=selectTenderIds+id+",";
           }
    
            })
            $("#selectTenderIds").val(selectTenderIds);
            $("#transAmt").html(transAmt);
            $("#transprice").html(price);
	} 
   function changePhoneCode() {
   $("#agenSendSpan").html("<font id='secondtime'></font>秒后重新获取");
	var phone = $("#phone").val();
	jQuery.ajax({type:"POST", url:"<%=path%>/user/creditAssignPhoneCode.action", data:"phone=" + phone, success:function (result) {
		if (result == "success") {
           $("#yanzhengme").html("已将手机动态验证码发送您的手机${sessionScope.user.phone},请注意查收");
			getTime(10);
		} else {
			   $("#yanzhengme").html("获取验证码出错,请稍后重试,或者联系客服");
	       getTime(10);
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
		$("#agenSendSpan").html("<a href='javascript:changePhoneCode()'>重新获取</a>");
	}
}
 function inputPwd(){
 $('#disDiv').hide();
   changePhoneCode();
   $('#inputPwd').show();
 }

		</script>
	</head>

	<body>
		<form action="<%=path%>/creditAssign/add" method="post"
			id="creditForm">
			<input id="selectTenderIds" type="hidden"  name="selectTenderIds" value="" />
			<div style="background: #FFFFFF;">
				<div class="user_cont_r_1">
					<span>债权转让</span>
				</div>
				<table>
				<tr>
				<td>项目名称</td>
				<td>项目本金</td>
				<td>持有期限/总期限(日)</td>
				<td>收益率</td>
				<td>收益金额</td>
				<td>当前本息</td>
				<td>剩余期限(日)</td>
				</tr>
				<tr>
				<td>${project.name}</td>
				<td>${project.transAmt}</td>
				<td>${project.ownDays}/${project.totalDays}</td>
				<td>${project.rate}</td>
				<td>${project.incomeMoney}</td>
				<td>${project.transAmt}+${project.incomeMoney}</td>
				<td>${project.diffDays}</td>
				</tr>
				</table>
					<input type="hidden" name="creditAmt"
						value="${item.transAmt}" />
					<input type="hidden" name="sellCustId"
						value="${sessionScope.user.huifuID}" />
					<input type="hidden" name="project.id"
						value="${item.id}" />
					<input type="hidden" name="diffDays"
						value="${diffDays}" />
						
				<br />
				<h3>转让信息</h3>
				债权价值：	${project.creditMoney}	
				转让价格：	<input  name="creditDealAmt"
						value="${creditDealAmt}" value="${project.creditMoney}" />
			<!--弹出层密码层 -->
			<div id="inputPwd" style="display: none">
				请输入交易密码：
				<input name="paypwd" id="transPwd" />
				<div id="transPwdTip">ss</div>
				请输入手机动态验证码：
				<input name="code" id="creditePhone" />
				<div id="creditePhoneTip">ss</div>
				<div id="agenSendSpan">
					<c:if test="${sessionScope.user.phone==null}">你暂未绑定手机号，请去安全中心绑定后方可交易</c:if>
					<c:if test="${sessionScope.user.phone!=null}">
						<font id="secondtime"></font>秒后重新获取</c:if>
				</div>
				<div id="yanzhengme"></div>
				<input  type="submit" value="确定" />
				
				
			</div>
		</form>
	</body>
</html>


