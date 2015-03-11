<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="${ctx }/css/main.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/user_my_jilu.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx }/js/base.js"></script>
<title>1717tou.com</title>
<script type="text/javascript">
	$(document).ready(function(){
		$(".user_cont_r_2a li").click(function() {
			$(this).addClass("sel").siblings().removeClass("sel");
		});
		$("#tag${luckyMoney.state}").click();
	})
</script>
</head>
<body >
	<form action="${ctx }/user/luckyMoney" method="post">
	<input type="hidden" name="_state" id="_state" value="${state}"/>
	</form>
	<div class="user_cont_rr" style="background:#FFFFFF;">
		<div class="user_cont_rrr">
		    <div class="user_cont_r_1"><span>我的红包</span></div>
			<div class="user_cont_r_2"> 
				<div class="user_cont_r_2a">
					<ul>
						<li class="li_1" id="tag"><a href="${ctx }/user/luckyMoney" target="framecont">全部</a></li>
						<li class="li_2" id="tag0"><a href="${ctx }/user/luckyMoney?_state=0" target="framecont">未使用</a></li>
						<li class="li_3" id="tag3"><a href="${ctx }/user/luckyMoney?_state=3" target="framecont">已使用</a></li>	
						<li class="li_3" id="tag1"><a href="${ctx }/user/luckyMoney?_state=1" target="framecont">已冻结</a></li>																
						<li class="li_3" id="tag4"><a href="${ctx }/user/luckyMoney?_state=4" target="framecont">已兑换</a></li>																
					</ul>  
				</div>
			</div>   
			<div class="user_cont_r_3">
				<table class="user_cont_r_3b" cellspacing=0>
					<tr class="tittle">
						<th class="user_cont_r_3b1">金额（元）</th>
						<th class="user_cont_r_3b3">使用产品</th>
						<th class="user_cont_r_3b4">有效期</th>
						<th class="user_cont_r_3b5">状态</th>
					</tr>
					<!-- 交易记录循环开始-->
					<c:forEach items="${page.result}" var="lucky" varStatus="index">
						<tr class="user_cont_r_h_3c">
							<td class="user_cont_r_h_3c1">${lucky.money }</td>
							<td class="user_cont_r_h_3c3">
								<c:if test="${fn:indexOf(lucky.type,0)!=-1}"><img src="${ctx}/images/xin.png" alt="信满盈"/></c:if>
								<c:if test="${fn:indexOf(lucky.type,1)!=-1}"><img src="${ctx}/images/zi.png" alt="资涨通"/></c:if>
								<c:if test="${fn:indexOf(lucky.type,2)!=-1}"><img src="${ctx}/images/jin.png" alt="金多宝"/></c:if>
								<c:if test="${fn:indexOf(lucky.type,3)!=-1}"><img src="${ctx}/images/diao.png" alt="屌丝宝"/></c:if>
							</td>
							<td class="user_cont_r_h_3c4">
								${fn:substring(lucky.start_time, 0, 10)}
								至${fn:substring(lucky.end_time, 0, 10)}
							</td>
							<td class="user_cont_r_h_3c5">
								<script>
									if(Date.parse("${lucky.end_time}")<new Date()&&"${lucky.state}"=="0"){
										document.write("已失效")								
									}else if("${lucky.state}"=="0"){
										document.write("未使用")	
									}else if("${lucky.state}"=="1"){
										document.write("已冻结")	
									}else if("${lucky.state}"=="3"){
										document.write("已使用")	
									}else if("${lucky.state}"=="4"){
										document.write("已兑换")	
									}
								</script>
							</td>
						</tr>
					</c:forEach>
                    <!-- 交易记录循环结束-->
				</table>														
				<div class="user_cont_r_2d">
					<jsp:include page="../common/page.jsp"></jsp:include>
				</div>
				 <div class="user_cont_r_8">
						<h3>   温馨提示：</h3>
						<div class="con">
							请根据不同类型具体产品规定的可投资使用的红包比例（即：在该次投资中，可使用红包金额占投资总金额的百分比）进行合理使用和分配。【一起一起投】

						</div>
					</div>
			</div>	
		</div>	
	</div>
</body>
</html>