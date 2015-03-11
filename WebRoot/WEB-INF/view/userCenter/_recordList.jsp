<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="网站描述" />
		<meta name="keywords" content="关键字" />
		<link href="${ctx }/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/user_my_jilu.css" rel="stylesheet"
			type="text/css" />
		<link href="${ctx }/css/user_touzi_popup.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="${ctx }/js/jquery-1.8.3.min.js"></script>
		<script src="${ctx }/js/formValidator-4.1.3.js"
			type="text/javascript" charset="UTF-8"></script>
		<script src="${ctx }/js/formValidatorRegex.js"
			type="text/javascript" charset="UTF-8"></script>
		<script type="text/javascript" src="${ctx }/js/base.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
	    $.formValidator.initConfig({formID:"creditForm",theme:"ArrowSolidBox",submitOnce:true,onSuccess:function () {
	    submitForm();
	}
		,ajaxPrompt : '有数据正在异步验证，请稍等...'
	});
	$("#creditDealAmt").formValidator({onShow:"请输入转让金额",onFocus:"转让金额不能大于价值的120%，不能低于价值的80%，小数点不能超过2位。",onCorrect:"正确"}).regexValidator({regExp:"(?!^0\\d*$)^\\d{1,16}(\.\\d{1,2})?$",onError:"金额填写有误"});
	$("#transPwd").formValidator({onShowText:"请输入交易密码",onShow:"请输入交易密码",onFocus:"请输入交易密码",onCorrect:""})
.ajaxValidator({async:true, url:"${ctx }/user/checkpaypwd", success:function (result) {
		if (result =="0") {
			return true;
		}
		return "交易密码错误,请重新输入";
}})
	 $("#creditePhone").formValidator({ajax:true,onShow:"请输入验证码",onFocus:"请输入验证码",onCorrect:"请输入验证码"}).ajaxValidator({async:true, url:"${ctx 

}/checksessionattr?attrname=creditAssignPhoneCode", success:function (result) {		
if (result=="true") {
			return true;
		}
		return "验证码输入错误,请重新输入";
}})
});
			function submitForm(){
	     var formobj=$("#creditForm");
		 var url = jQuery(formobj).attr("action");
	     var data = jQuery(formobj).serialize();
	jQuery.ajax({type:"POST", url:url, data:data, beforeSend:function () {
	}, success:function (result) {
	 if(result=="success"){
	    $('.zhrang_step3').css("display","none");
		$('.zhrang_step4').css("display","block");
	 }else{
	    alert("发布失败");
	 }
	}, error:function (result) {
		alert("发布失败");
	}});
	}
	function changePhoneCode() {
	jQuery.ajax({type:"POST",url:"${ctx }/user/creditAssignPhoneCode",
		success:function (result) {
		if (result == "success") {
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
	$(function(){
		$(".user_cont_r_2a li").click(function() {
			$(this).addClass("sel").siblings().removeClass("sel");
			window.location.href=$(this).find("a").attr("href")
		});
		$(".user_cont_r_2a li").eq(${type}).addClass("sel").siblings().removeClass("sel");
	});
	$(document).ready(function(){
		$(".repayed").each(function(index,item){
			var id_state = $(item).attr("id");
			var state = id_state.split("_")[1];
			if(state==1||state==2){
				//还款中 还款完成  计算已还款数额
				$.post("${ctx}/user/projectRepay",{id:id_state.split("_")[0]},function(data){
					$(item).html(data);
				});
			}else if (state==4){
			//考虑变成固定金额
				$(item).html($(item).prev().html());	
			}else{
				$(item).html("尚未开始还款");	
			}
		});
	});
	function checkContract(id){
		$.ajax({
		    type:"POST",
		    async:false,
			url:"${ctx}/user/createContract",
			data:"id="+id,
			success:function(data){
				if(data!="false"){
					window.open("${ctx}/user/downloadPdf?id="+id);
				}
			}
		});	
	}
</script>
		<script type="text/javascript">
/*控制弹出层ds5*/
jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover').slideDown(200);
	})
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover').slideUp(200);
	})
	
	$('.theme-login2').click(function(){
		$('.theme-popover-mask2').css("display","block");
		$('.theme-popover2').slideDown(200);
	})
	$('.theme-poptit2 .close').click(function(){
		$('.theme-popover-mask2').css("display","none");
		$('.theme-popover2').slideUp(200);
	})
	 <!-- 产品转让弹出层-->
	 $('.theme-login-zhrang').click(function(){
		$('.theme-popover-mask-zhrang').css("display","block");
		$('.theme-popover-zhrang').slideDown(200);
	})
	$('.theme-poptit-zhrang .close').click(function(){
		$('.theme-popover-mask-zhrang').css("display","none");
		$('.theme-popover-zhrang').slideUp(200);
	})
	$('#endcre').click(function(){
		$('.theme-popover-mask-zhrang').css("display","none");
		$('.theme-popover-zhrang').slideUp(200);
	}) 
	$('.step1_btn').click(function(){
		$('.zhrang_step1').css("display","none");
		$('.zhrang_step2').css("display","block");
		$('.theme-popover-mask-zhrangsure').css("display","none");
		$('.theme-popover-zhrangsure').slideUp(200);
	})
	$('.step2_btn').click(function(){
		$('.zhrang_step2').css("display","none");
		$('.zhrang_step3').css("display","block");
	})
		 <!-- 产品转让确定弹出层-->
	 $('.theme-login-zhrangsure').click(function(){
	     $("#creditDelAmtSure").html($("#creditDealAmt").val());
		$('.theme-popover-mask-zhrangsure').css("display","block");
		$('.theme-popover-zhrangsure').slideDown(200);
	})
	$('.theme-poptit-zhrangsure .close').click(function(){
		$('.theme-popover-mask-zhrangsure').css("display","none");
		$('.theme-popover-zhrangsure').slideUp(200);
	}) 

})

</script>
		<script type="text/javascript">
 function confirmPro(projectid){
 $("#proinfo").empty();
  	$.ajax({
		    type:"POST",
		    async:false,
			url:"${ctx}/creditAssign/creditinfor?projectid="+projectid,
			success:function(data){
			var map=eval(data);
			$("<td>"+map.proname+ "</td>").appendTo("#proinfo");
			$("<td>"+map.transAmt+ "</td>").appendTo("#proinfo");
			$("<td>"+map.ownDays+"/"+map.totalDays+ "</td>").appendTo("#proinfo");
			$("<td>"+map.rate+ "%</td>").appendTo("#proinfo");
			$("<td>"+map.incomeMoney+ "</td>").appendTo("#proinfo");
			$("<td>"+map.surplusMoney+ "</td>").appendTo("#proinfo");
			$("<td>"+map.surplusDays+ "</td>").appendTo("#proinfo");
			 $("#totalPrice").html(map.totalPrice);
			 $("#projectid").val(map.id);
			 $("#diffDays").val(map.surplusDays);
			 $("#creditAmt").val(map.transAmt);
			 $("#creditAmtSure").html(map.transAmt);
			 $("#creditDealAmt").val(map.totalPrice);
			}
		});	
 
 }



</script>
		<title>1717tou.com</title>
	</head>

	<body>
		<form id="pageForm" action="${ctx}/user/recordList" method="post">
			<input type="hidden" name="type" id="type" value="${type }" />
		</form>
		<div class="user_cont_rr" style="background: #FFFFFF;">
			<div class="user_cont_rrr">
				<div class="user_cont_r_1">
					<span>我的投资</span>
				</div>
				<div class="user_cont_r_2">
					<div class="user_cont_r_2a">
						<ul>
							<li id="tag0" class="li_2">
								<a href="${ctx}/user/recordList?type=0">信满盈(${num['0'] })</a>
							</li>
							<li id="tag1" class="li_3">
								<a href="${ctx}/user/recordList?type=1">资涨通(${num['1'] })</a>
							</li>
							<li id="tag2" class="li_4">
								<a href="${ctx}/user/recordList?type=2">金多宝(${num['2'] })</a>
							</li>
							<li id="tag3" class="li_5">
								<a href="${ctx}/user/recordList?type=3">屌丝宝(${num['3'] })</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="tables">
					<div class="user_cont_r_3 ying">
						<table class="user_cont_r_3b" cellspacing=0>
							<tr class="tittle">
								<th class="user_cont_r_3b1">
									项目名称|编号
								</th>
								<th class="user_cont_r_3b2">
									起息日
								</th>
								<th>
									到期日（预期）
								</th>
								<th class="user_cont_r_3b3">
									剩余投资额/原投资额（元）
								</th>
								<th class="user_cont_r_3b4">
									收益金额（元）
								</th>
								<th class="user_cont_r_3b5">
									转让（全部）
								</th>
								<th class="user_cont_r_3b6">
									状态
								</th>
								<th class="user_cont_r_3b7">
									查看详情
								</th>
							</tr>
							<!-- 交易记录循环开始-->
							<c:forEach items="${page.result}" var="record">
								<tr class="user_cont_r_t_3c">
									<td title="${record[1]}" class="user_cont_r_t_3c1">
										<a href="${ctx }/project/view?id=${record[0] }"
											target="_blank"><cc:subLength var="${record[1]}"
												length="8" />
										</a>
									</td>
									<td class="user_cont_r_t_3c2">
										${record[2] }
									</td>
									<td class="">
										${record[3] }
									</td>
									<td class="user_cont_r_t_3c3">
										${record[4] }/${record[10] }
									</td>
									<td class="user_cont_r_t_3c4 repayed"
										id="${record[0] }_${record[6] }">
										0.00
									</td>
									<td class="user_cont_r_t_3c5">
										<c:choose>
											<c:when test="${record[6]==1&&record[7]>60 }">
												<a class="theme-login-zhrang"><img
														src="${ctx }/images/user_touzi_zr.png"
														onclick="confirmPro('${record[0]}')" />
												</a>
											</c:when>
											<c:otherwise>
												<img src="${ctx }/images/user_touzi_zr_h.png" />
											</c:otherwise>
										</c:choose>
									</td>
									<td class="user_cont_r_t_3c6">
										<c:choose>
											<c:when test="${record[6]==0||record[6]==3 }">投标中</c:when>
											<c:when test="${record[6]==1}">还款中</c:when>
											<c:when test="${record[6]==4}">未成立</c:when>
											<c:when test="${record[6]==2}">已还款</c:when>
										</c:choose>
									</td>
									<td class="user_cont_r_t_3c7" >
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${ctx }/user/recordDetail?id=${record[0] }"> <span style="color:#0060a5">详情</span> </a>
										<c:if
											test="${(record[6]==1||record[6]==2)&&record[8]!=null&&record[3]!=null}">
											<a href="${ctx }/user/createContract?id=${record[0] }"
												target="_blank"><span style="color:#e33340;">合同</span></a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
							<!-- 交易记录循环结束-->
						</table>
						<div class="user_cont_r_2d">
							<jsp:include page="../common/page.jsp"></jsp:include>
						</div>
						<!-- 产品转让弹出层-->
						<form action="${ctx }/creditAssign/add" method="post"
			id="creditForm">
						<div class="theme-popover-zhrang">
							<div class="theme-poptit-zhrang">


								<a href="javascript:;" title="关闭" class="close">[关闭]</a>
							</div>
							<div class="zhrang_con">
							
							<input type="hidden" id="creditAmt" name="creditAmt"
						value="" />
					<input type="hidden" name="sellCustId"
						value="${sessionScope.user.huifuID}" />
					<input type="hidden" id="projectid"  name="project.id"
						value="" />
					<input type="hidden" id="diffDays" name="diffDays"
						value="" />
								<!-- 产品转让第一步-->
								<div class="zhrang_step1">
									<div class="zhrang_step1a">
										<img src="images/zhrang_step1.png" />
									</div>
									<div class="zhrang_step1b">
										<h3>
											项目详情
										</h3>
										<table cellspacing=0 border="0">
											<tr class="color_hui">
												<td>
													项目名称
												</td>
												<td>
													项目本金（元）
												</td>
												<td>
													持有期限/
													<br />
													总期限（日）
												</td>
												<td>
													收益率
												</td>
												<td>
													收益金额(元)
												</td>
												<td>
													剩余预计收益(元)
												</td>
												<td>
													剩余期限(日)
												</td>
											</tr>
											<tr class="hei" id="proinfo">
												
											</tr>
										</table>
									</div>
									<div class="zhrang_step1c">
										<h3>
											转让信息
										</h3>
										<table cellspacing=0 border="0">
											<tr>
												<td class="zhrang_step1cl">
													当前价值
												</td>
												<td class="zhrang_step1cm">
													<span style="color: #e33340;" id="totalPrice">0.00</span> 元
												</td>
												<td class="zhrang_step1cr">
													<span style="color: #979797;">(项目本金+当前利息)</span>
												</td>
											</tr>
											<tr>
												<td class="zhrang_step1cl">
													转让价格
												</td>
												<td class="zhrang_step1cm">
													<span ><input style="width: 50px" id="creditDealAmt"  

name="creditDealAmt" value="" /></span> 元
												</td>
												<td class="zhrang_step1cr">
													<span style="color: #979797;">(当前转让金额即为投资的项目本金)

</span><span style="color: red;margin-left: 50px" id="creditDealAmtTip">sss</span>
												</td>
											</tr>
											<tr>
												<td class="zhrang_step1cl"></td>
												<td class="zhrang_step1cm"></td>
												<td class="zhrang_step1cr">
													<span style="color: #e33340;">(注：转让金额即为投资的本金<br 

/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;撤销转让条件：如转让时效内无人投资，可撤销转让)</span>
												</td>
											</tr>
											<tr>
												<td class="zhrang_step1cl">
													转让时效
												</td>
												<td class="zhrang_step1cm">
													<span style="color: #0060a5;">当天有效</span>
												</td>
												<td class="zhrang_step1cr">
													<span style="color: #979797;"> </span>
												</td>

											</tr>
										</table>
									</div>
									<div class="zhrang_step1d">
										<div class="zhrang_btn theme-login-zhrangsure">
											下一步
										</div>
									</div>
								</div>
								<!-- 产品转让第二步-->
								<div class="zhrang_step2">
									<div class="zhrang_step2a">
										<img src="images/zhrang_step2.png" />
									</div>
									<div class="zhrang_step2b">
										<h3>
											1.个人借款债权转让风险提示
										</h3>
										<div class="zhrang_step2_ifr">
											个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风

险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人

借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债

权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让

风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个

人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款

债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转

让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险

个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借

款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险个人借款债权转让风险
										</div>
									</div>
									<div class="zhrang_step2b">
										<h3>
											2.个人借款债权转让申请规则
										</h3>
										<div class="zhrang_step2_ifr">

										</div>
									</div>
									<div class="zhrang_step2b">
										<h3>
											3.个人借款债权转让协议
										</h3>
										<div class="zhrang_step2_ifr">

										</div>
									</div>
									<div class="zhrang_step1d">
										<div class="zhrang_btn step2_btn">
											同意并继续
										</div>
									</div>
								</div>
								<!-- 产品转让第三步-->
								<div class="zhrang_step3">
									<div class="zhrang_step3a">
										<img src="images/zhrang_step3.png" />
									</div>
									<div class="zhrang_step3b">
										<table cellspacing=0 border="0">
											<tr>
												<td class="zhrang_step3bl">
													交易密码
												</td>
												<td class="zhrang_step3br">
													<input name="paypwd" id="transPwd"  class="step3_input" /><span 

id="transPwdTip"></span>
												</td>
											</tr>
											<tr>
												<td class="zhrang_step3bl">
													手机动态码
												</td>
												<td class="zhrang_step3br">
													<input name="code" id="creditePhone" class="step3_input" 

/><span class="yan" id="agenSendSpan">
             <a style="color:#fff" href="javascript:changePhoneCode()">发送验证码</a></span><div id="creditePhoneTip"></div>
												</td>
											</tr>
										</table>
									</div>

									<div class="zhrang_step1d">
										<input type="submit" class="zhrang_btn step3_btn" value="下一步" />
									</div>
								</div>
								<!-- 产品转让第四步-->
								<div class="zhrang_step4">
									<div class="zhrang_step4a">
										<img src="images/zhrang_step4.png" />
									</div>
									<div class="zhrang_step4b">
										恭喜您，转让发布成功！
									</div>

									<div class="zhrang_step1d">
										<div class="zhrang_btn" id="endcre">
											确定
										</div>
									</div>
								</div>
								<!-- 产品转让第四步结束-->
							</div>

						</div>
						<div class="theme-popover-mask-zhrang"></div>
						<!-- 产品转让弹出层结束-->
						<!-- 确定转让信息弹出层-->
						<div class="theme-popover-zhrangsure">
							<div class="theme-poptit-zhrangsure">

								<h3>
									确认转让信息
								</h3>
								<a href="javascript:;" title="关闭" class="close">[关闭]</a>
							</div>
							<div class="zhrang_step1c_sure">
								<table cellspacing=0 border="0">
									<tr>
										<td class="zhrang_step1_surel">
											转让金额
										</td>
										<td class="zhrang_step1_surem">
											<span id="creditAmtSure" >0.00</span> 元
										</td>
										<td class="zhrang_step1_surer">
											<span style="color: #979797;">(当前转让金额即为投资的项目本金)</span>
										</td>
									</tr>
									<tr>
										<td class="zhrang_step1_surel">
											转让价格
										</td>
										<td class="zhrang_step1_surem">
											<span style="color: #e33340;" id="creditDelAmtSure">0.00</span> 元
										</td>
										<td class="zhrang_step1_surer">
											<span style="color: #979797;">("0"手续费转让)</span>
										</td>
									</tr>
									<tr>
										<td class="zhrang_step1_surel">
											转让时效
										</td>
										<td class="zhrang_step1_surem">
											<span style="color: #0060a5;">当天有效</span>
										</td>
										<td class="zhrang_step1_surer">
											<span style="color: #979797;">(如转让时效内无人投资，自动撤销)</span>
										</td>

									</tr>
								</table>
							</div>
							<div class="zhrang_step1d">
								<div class="zhrang_btn step1_btn">
									确定
								</div>
							</div>



						</div>
						<div class="theme-popover-mask-zhrangsure"></div>
						<!-- 确定转让信息弹出层结束-->
</form>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>