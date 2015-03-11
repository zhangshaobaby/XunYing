<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="网站描述" />
		<meta name="keywords" content="关键字" />
		<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
		<link href="${ctx }/css/admincp11.css" rel="stylesheet"
			type="text/css" />
		<script src="${ctx }/js/jquery-1.7.js" type="text/javascript"></script>
		<script src="${ctx }/js/base.js" type="text/javascript"></script>
		<title>1717tou.com</title>
		<script type="text/javascript">
		function cancelTender(id){
		if(confirm("请确认操作?"))
		{
			jQuery.ajax({type:"POST", url:"${ctx }/cash/auth/cancelTender", data:"id="+id, success:function (result) {
			var  message=eval(result);
			 if (message.msgCode=="success") {
				 alert("已完成撤标。");
				 window.location.reload();
			} else {
				alert("撤标失败 "+message.msgString);
			}
		}});
		}
		;

		}
		
		
		</script>
	</head>
<body>
	<form action="${ctx }/auth/project/tenderDetail?id=${id }" method="post" id="pageForm"></form>
<div style="background: #FFFFFF;">
			<div class="admincp_r1">
				<span>投标详情</span>
			</div>
			<div class="admincp_r2">
	<a href="${ctx }/auth/project/preList">返回列表</a> 	<a href="${ctx }/auth/project/tenderDetailPdf?id=${id}">下载PDF</a>
				<div class="admincp_r2_a">
					<div class="admincp_r2_a1" style="width:80px">
						真实姓名
					</div>
					<div class="admincp_r2_a1" style="width:170px">
						身份证
					</div>
					<div class="admincp_r2_a1" style="width:120px">
						手机号
					</div>
					<div class="admincp_r2_a2" style="width:160px">
						时间
					</div>
					<div class="admincp_r2_a3" style="width:260px">
						购买/还款金额/红包
					</div>
					<c:if test="${iscancel==true}">
						<div class="admincp_r2_a3" style="width:100px">
						操作
						</div>
				   </c:if>		
				</div>
				<!-- 交易记录循环开始-->
				<c:forEach items="${page.result}" var="tender">
					<div class="admincp_r2_b">
						<div class="admincp_r2_b1" style="width:80px">
							<cc:subLength var="${tender.realName}" length="11"/><br/>
						</div>
						<div class="admincp_r2_b1" style="width:170px">
							<cc:subLength var="${tender.identification}" length="18"/>
						</div>
						<div class="admincp_r2_b1" style="width:120px">
							<cc:subLength var="${tender.phone}" length="11"/>
						</div>
						<div class="admincp_r2_b2" style="width:160px">
							${tender.createTime}
						</div>
						<div class="admincp_r2_b3" style="width:260px">
							${tender.transAmt }/${tender.repayAmt==null?0:tender.repayAmt}/${tender.luckyMoney }					
						</div>
						<c:if test="${iscancel==true}">
						<c:if test="${tender.pstate==0}">
							<div class="admincp_r2_b3" style="width:100px">							
								<input value="撤标" type="button" onclick="cancelTender('${tender.id}')" />
					     	</div>
					     	</c:if>	
						</c:if>		
						
					</div>
				</c:forEach>
				<!-- 交易记录循环结束-->
 <jsp:include page="../common/page.jsp"></jsp:include>
			</div>
		</div>		
	</body>
</html>
