<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="${ctx }/css/main.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/user_my_jilu.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/user_touzi_popup.css"rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx }/js/base.js"></script>
<script type="text/javascript">
	$(function(){
		$(".user_cont_r_2a li").click(function() {
			$(this).addClass("sel").siblings().removeClass("sel");
			window.location.href=$(this).find("a").attr("href")
		});
		$(".user_cont_r_2a li").eq(${type}).addClass("sel").siblings().removeClass("sel");
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
<title>1717tou.com</title>
</head>

<body>
	<form id="pageForm" action="${ctx}/user/recordList" method="post">
		<input type="hidden" name="type" id="type" value="${type }"/>
	</form>
	<div class="user_cont_rr" style="background:#FFFFFF;">
		<div class="user_cont_rrr">
		<div class="user_cont_r_1"><span>我的投资</span></div>
		<div class="user_cont_r_2"> 
			<div class="user_cont_r_2a">
				<ul>		
					<li id="tag0" class="li_2"><a href="${ctx}/user/recordList?type=0" >信满盈</a></li>
					<li id="tag1" class="li_3"><a href="${ctx}/user/recordList?type=1" >资涨通</a></li>	
					<li id="tag2" class="li_4"><a href="${ctx}/user/recordList?type=2" >金多宝</a></li>
					<li id="tag3" class="li_5"><a href="${ctx}/user/recordList?type=3" >屌丝宝</a></li>															
				</ul>                   
			</div>
		</div> 
		<div class="tables">  
			<div class="user_cont_r_3 ying">
				<table class="user_cont_r_3b" cellspacing=0>
					<tr class="tittle">
						<th class="user_cont_r_3b1">项目名称|编号</th>
						<th class="user_cont_r_3b2">起息日</th>
						<th >到期日（预期）</th>
						<th class="user_cont_r_3b3">投资金额（元）</th>
						<th class="user_cont_r_3b4">已回款（元）</th>
						<th class="user_cont_r_3b5">转让（全部）</th>
						<th class="user_cont_r_3b6">状态</th>
						<th class="user_cont_r_3b7">查看详情</th>
					</tr>
					<!-- 交易记录循环开始-->
					<c:forEach items="${page.result}" var="record">
					<tr class="user_cont_r_t_3c">
						<td title="${record.name}" class="user_cont_r_t_3c1">
							<a href="${ctx }/project/view?id=${record.id }" target="_blank"><cc:subLength var="${record.name}" length="8"/></a>
						</td>
						<td class="user_cont_r_t_3c2">${record.rateTime}</td>
						<td class="">${record.pendTime}</td>
						<td class="user_cont_r_t_3c3">${record.transAmt}</td>
						<td class="user_cont_r_t_3c4">${record.currentmoney}</td>
						<td class="user_cont_r_t_3c5">
							<c:choose>
							<c:when test="${record.state==1&&record.startdays>60 }">
								<img src="${ctx }/images/user_touzi_zr.png"  onclick="window.open('${ctx }/project/projectTenderlist?projectid=${record.id }')" />
							</c:when>
							<c:otherwise>
								<img src="${ctx }/images/user_touzi_zr_h.png" />
							</c:otherwise>
							</c:choose>
						</td>
						<td class="user_cont_r_t_3c6">
							<c:choose>
								<c:when test="${record.state==0||record.state==3 }">投标中</c:when>
								<c:when test="${record.state==1}">还款中</c:when>
								<c:when test="${record.state==4}">未成立</c:when>
								<c:when test="${record.state==2}">已还款</c:when>
							</c:choose>
						</td>
						<td class="user_cont_r_t_3c7" style="text-align:left">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="${ctx }/user/recordDetail?id=${record.id }">
							<img src="${ctx }/images/user_touzi_02.png" />
							</a>
							<c:if test="${(record.state==1||record.state==2)&&record.pstarttime!=null&&record.pendTime!=null}">
							<a href="${ctx }/user/createContract?id=${record.id }" target="_blank">合同</a>
							</c:if>
						</td>
					</tr>
					</c:forEach>
				<!-- 交易记录循环结束-->
				</table>														
				<div class="user_cont_r_2d">
					<jsp:include page="../common/page.jsp"></jsp:include>
				</div>
			</div>	
		</div>	
	</div>	
</div>	
			
</body>
</html>