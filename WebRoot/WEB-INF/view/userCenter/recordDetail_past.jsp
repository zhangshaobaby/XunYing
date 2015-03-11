<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<script type="text/javascript" src="${ctx }/js/jquery-1.8.3.min.js"></script>
<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/user_touzi.css" rel="stylesheet" type="text/css" />
<title>1717tou.com</title>
<script>
	var page = 0;
	var total = ${fn:length(recordList)};
	total = total%4==0?total/4:((total/4)+1);
	total = parseInt(total);
	$(document).ready(function(){
		_turnPage(page);
	});
	//假分页
	function _turnPage(){
		var id = 0;
		if(page<0)page=0;
		if(page>=total)page=total-1;
		$(".user_cont_r_3_b2").each(function(index,item){
			//plan_0
			id = $(item).attr("id");
			id = id.substring(6);
			if(id >= page * 4 && id < (page+1) * 4){
				$(item).show()
			}else{
				$(item).hide();
			}
		});
	}
</script>

</head>

<body>
	<div style="background:#FFFFFF;">
		<div class="user_cont_r_1"><span><a href="javascript:window.history.go(-1);">投资记录</a>>投资详情</span></div>
		<div class="user_cont_r_2">
			<div class="user_cont_r_2_l">
			<div class="user_cont_r_2_l_1">
			<cc:subLength var = "${model.name }" length = "16"/>
			</div>
			<div class="user_cont_r_2_l_2">
				<div class="user_cont_r_2_l_2a">预期年化率<br /><span style="color:#ea4c59; margin-left:35px;">${model.rate }%</span></div>
				<div class="user_cont_r_2_l_2a">投资期限<br /><span>${model.time_limit }<c:if test="${model.delay_time_limit!=null&&model.delay_time_limit!=0 }">+${model.delay_time_limit }</c:if></span>个月</div>
				<div class="user_cont_r_2_l_2a">募集金额<br />
				
									<c:choose>
								  		<c:when test="${model.total_money/10000==0}">								  											  		
			                              <span>${model.total_money==null?0:model.total_money}</span>元
								  		</c:when>
								  		<c:otherwise>
								  		  <span>${model.total_money/10000 }</span>万元
								  		</c:otherwise>
								  	</c:choose>
				</div>
			</div>
			<div class="user_cont_r_2_l_4">
				收益方式：
				${model.repay_content}
				<br/>
				产品类型：
				<c:choose>
					<c:when test="${model.type==0}">信托(信满盈)</c:when>
					<c:when test="${model.type==1}">资管(资涨通)</c:when>
					<c:when test="${model.type==2}">基金(金多宝)</c:when>
					<c:when test="${model.type==3}">屌丝宝</c:when>
					<c:otherwise>unknown</c:otherwise>
				</c:choose>
				<br/>
				目前投资人数：${model.pay_number }
			</div>
			<div class="user_cont_r_2_l_5">
				发布日期：${model.start_time }&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</div>
		<div class="user_cont_r_2_r">	
			<div class="user_cont_r_2_r1">
				<c:choose>
					<c:when test="${model.state==0}"><img src="${ctx }/images/user_touzi4.png" /></c:when>
					<c:when test="${model.state==1}"><img src="${ctx }/images/user_touzi2.png" /></c:when>
					<c:when test="${model.state==2}"><img src="${ctx }/images/user_touzi3.png" /></c:when>
					<c:when test="${model.state==3}"><img src="${ctx }/images/user_touzi1.png" /></c:when>
				</c:choose>
			</div>
			<div class="user_cont_r_2_r2">
				您的投资金额：<b>${sum==null?0:sum }元</b><br />
				已还款金额：<b>${repaySum }元</b>	
			</div>
		</div>
		</div>	
		<div class="user_cont_r_3">
			<div class="user_cont_r_3_a">
				<div class="user_cont_r_3_a1">还款记录</div>
			</div>
			<div class="user_cont_r_3_b">
				<div class="user_cont_r_3_b1">
					<div class="user_cont_r_3_b1_a">还款时间</div>
					<div class="user_cont_r_3_b1_b">还款金额</div>
					<div class="user_cont_r_3_b1_c">还款性质</div>
				</div>
				<!-- 循环信息结束四个 -->	
				<c:forEach items="${recordList}" var = "record" varStatus="index">
				<div class="user_cont_r_3_b2" id="record${index.index }">
					<div class="user_cont_r_3_b2_a">${record[0] }</div>
					<div class="user_cont_r_3_b2_b">${record[1] }元</div>
					<div class="user_cont_r_3_b2_c">
						<c:choose>
							<c:when test="${record[2]==0 }">
								本金
							</c:when>
							<c:otherwise>
								利息
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				</c:forEach>
				<!-- 循环信息结束 -->	
			</div>
			<div class="user_cont_r_3_c">
				<a href="javascript:void(0);" onclick="page=1;_turnPage();">首页</a>
				<a href="javascript:void(0);" onclick="page=page-1;_turnPage();">上页</a>
				<a href="javascript:void(0);" onclick="page=page+1;_turnPage();">下页</a>
				<a href="javascript:void(0);" onclick="page=total;_turnPage();">末页</a>
			</div>
		</div>
	</div>
</body>
</html>