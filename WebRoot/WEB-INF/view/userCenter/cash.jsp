<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
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
	<link href="<%=path%>/css/main.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/user_tixian1.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/product_popup.css"rel="stylesheet" type="text/css" />
<script src="<%=path%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="<%=path%>/js/formValidator-4.1.3.js"
			type="text/javascript" charset="UTF-8"></script>
		<script src="<%=path%>/js/formValidatorRegex.js"
			type="text/javascript" charset="UTF-8"></script>
		<script type="text/javascript">
        var basepath='<%=path%>';
         $(document).ready(function(){
          $.formValidator.initConfig({formID:"form1",theme:"ArrowSolidBox",submitOnce:true,
          ajaxPrompt : '有数据正在异步验证，请稍等...'
	});     
	 var avl="${AvlBal}".replace(/,/g, "");
         	 $("#transAmt").formValidator({onShow:"请输入取现金额",onFocus:"取现金额不能大于余额，小数点不能超过2位。",onCorrect:"正确"}).inputValidator({min:1,max:avl,type:"value",onError:"请您正确填写取现金额"}).regexValidator({regExp:"(?!^0\\d*$)^\\d{1,16}(\.\\d{1,2})?$",onError:"金额填写有误"});
          	 $("#cashPhoneCode").formValidator({ajax:true,onShow:"请输入验证码",onFocus:"请输入验证码",onCorrect:"请输入验证码"}).inputValidator({min:4,onError:"请输入6位验证码"}).ajaxValidator({async:true, url:"<%=path%>/checksessionattr?attrname=cashPhoneCode", success:function (result) {		
if (result=="true") {
			return true;
		}
		return "验证码输入错误,请重新输入";
}})
         })
         
         function changePhoneCode() {
	jQuery.ajax({type:"POST", url:basepath + "/user/cashPhoneCode", success:function (result) {
		if (result == "success") {
		 $("#agenSendSpan").removeClass("yan").addClass("refa");
		 $("#agenSendSpan").html("重新获取(<span id='secondtime'></span>)");
			getTime(30);
		} else {
			alert("\u9a8c\u8bc1\u7801\u53d1\u9001\u5931\u8d25!\u8bf7\u7a0d\u540e\u91cd\u8bd5");
			getTime(30);
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
		$("#agenSendSpan").html("<a style='color:#fff' href='javascript:changePhoneCode()'>重新获取</a>");
	}
}

function selectbankcard(){
 var sbank=$("input:radio:checked");
 var sbankvalue=$(sbank).val();
 var dissbankvalue=$(sbank).attr("disvalue");
 $("#printBank").html(dissbankvalue);
 $("#bankcardid").val(sbankvalue);
}
function cashCharge(obj){
try{
 var val=$(obj).val(); 
 var investment="${AlltransAmt}";
 var surplus=0;
 if(val!=""){
  surplus=parseFloat(val)-parseFloat(investment);
 }
 if(surplus<=0){
  $("#cashCharge").html("0.00");
 }else{
   var  surplus=surplus.toFixed(2);
   //计算取款手续费
 var  cashCharge=surplus/1000*2.5
 $("#cashCharge").html(cashCharge.toFixed(2));
 $("#cashChargeInp").val(cashCharge.toFixed(2));
 }
}
catch (e) {

		}

}

         
        </script>
        <script type="text/javascript"> 
 
/*控制弹出层ds5*/
jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover').slideDown(200);
	})
	$('#queding').click(function(){
	     selectbankcard();
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover').slideUp(200);
	})
	$('#close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover').slideUp(200);
	})
})
 
</script>
        
		<title>1717tou.com</title>
	</head>

	<body>
	<jsp:include page="../common/head.jsp"></jsp:include>
	<form name="form1" action="<%=path%>/huifu/cash" method="post"
					id="form1">
	<input type="hidden" value="0.00" id="cashChargeInp" name="servFee" />				
	<div class="user_cont_rr">
                  <div class="user_cont_rrr">
					    <div class="user_cont_r_1"><span>提现</span></div>
						<div class="user_cont_r_2">
							绑定的提现银行卡 
							<span id="printBank" class="bang">${defaltbank.bankName}${fn:substring(defaltbank.cardNumber,0,4)}*********${fn:substring(defaltbank.cardNumber,15,100)}</span>
							<a class="btn btn-primary btn-large theme-login"><img src="<%=path%>/images/tixian3.png" /></a>
						</div>   
							<a class="btn btn-primary btn-large" href="<%=path %>/huifu/UserBindCard">添加银行卡</a>&nbsp;&nbsp;&nbsp;(添加完成后请刷新本页面)
                      <div class="tixian1">
                      	<div class="tixian1_1">填写提现金额</div>
                      	<table class="tixian1_2" cellspacing="0">
                      		<tr>
                      			<td class="tx_l">可用余额</td>
                      			<td class="tx_r"><span style="color:#e33340" >${AvlBal}</span>元&nbsp;&nbsp;&nbsp;&nbsp;(其中投资回款余额为：<span style="color:#e33340">${AlltransAmt}</span>元)</td>
                      		</tr>
                      		<tr>
                      			<td class="tx_l">提现额</td>
                      			<td class="tx_r"><input onblur="cashCharge(this)" type="text" id="transAmt" name="transAmt" /><span>元</span><div id="transAmtTip" style="float:right;width:590px;height:30px;font-size:12px;margin-right:200px;"></div></td>
                      		</tr>
                      		<tr>
                      			<td class="tx_l">提现费用</td>
                      			<td class="tx_r"><span style="color:#e33340">1.00</span>元<span style="color:#aaa;">(由汇付天下收取)</span></td>
                      		</tr>
                      		<tr>
                      			<td class="tx_l">提现服务费</td>
                      			<td class="tx_r"><span id="cashCharge" style="color:#e33340">0.00</span>元</td>
                      		</tr>
                      		<tr>
                      			<td class="tx_l">预计到账日期</td>
                      			<td class="tx_r">T+3个工作日(T日21:00之前申请)</td>
                      		</tr>
                      		<tr>
                      			<td class="tx_l">手机验证码</td>
                      			<td class="tx_r"><input id="cashPhoneCode" type="text" name="code" /><span class="yan" id="agenSendSpan" ><a style="color:#fff" href="javascript:changePhoneCode()">获取验证码</a></span><div id="cashPhoneCodeTip" style="float:right;width:490px;height:30px;font-size:12px;margin-right:50px;"></div></td>
                      		</tr>
                      	</table>
                      </div>   
                 
					<div class="user_cont_r_7"><input type="submit" value="申请提现" /></div>
					<div class="user_cont_r_8">
						<h3>温馨提示：</h3>
						<div class="con">
							1.身份认证、开通汇付天下第三方资管账户、完成提现银行卡绑定后，才能进行提现操作；<br/>
							2.汇付天下将收取<span style="color:#e33340">1元/笔</span>的提现手续费；<br/>
							3.充值资金未投资直接提现，将收取提现金额的0.25%作为转账费用；<br/>
							4.资金到达用户账户的当日即可申请提现，除偏远地区外的大部分银行卡，当日取现三个工作日内到账；<br/>
							5.遇到问题请联系【在线客服】，或者致电客服热线：400-890-3980 
						</div>
					</div>
	    <!-- 弹出层的内容-->                         
                            <div class="theme-popover">
                                 <div class="theme-poptit">
                                      
                                      <h3 class="bank">请选择提现银行卡</h3>
                                 </div>     
                                 <div class="pop_middle">
                                    <table cellspacing=0 >
                                     <tr class="tittle">
                                <th>发卡银行</th>
                                <th>卡号</th>
								<th class="check">选择</th>
							
								
                               </tr>
                               <!-- 循环开始-->
                             
                              <c:forEach items="${buildbank}" var="item">
                                <tr class="xunhuan">
								<td><img class="yh" src="<%=path%>/images/bank/${item.bankId}.gif"/></td>
								<td>${fn:substring(item.cardNumber,0,4)}*********${fn:substring(item.cardNumber,15,100)}</td>
								<td><input disvalue="${item.bankName} ${fn:substring(item.cardNumber,0,4)}*********${fn:substring(item.cardNumber,15,100)}"  value="${item.cardNumber}" type="radio" id="bankcardid" name="bankcardid" <c:if test="${item.cardNumber==defaltbank.cardNumber}">checked='checked'</c:if>></td>
							  </tr>
										</c:forEach>
                              <!-- 循环结束-->
                              
                                    </table>
                                    <div>
                                    <a id="close"  href="javascript:;" title="确定" class="close"><img class="quxiao" src="<%=path%>/images/product12.png"></a>
                                    <a id="queding" href="javascript:;" title="关闭" class="close"><img class="queren" src="<%=path%>/images/product11.png"></a>
                                  
                                    </div>
                                    <div style="clear:both"></div>
                                 </div>
                               
                            </div>
                            
  <div class="theme-popover-mask"></div>
<!--弹出层结束-->				
				</div>
                </div>
                </form>
                <jsp:include page="../common/foot.jsp"></jsp:include>
	</body>
</html>
