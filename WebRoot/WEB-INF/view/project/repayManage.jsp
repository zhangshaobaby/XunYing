<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/admincp12.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/js/jquery-1.7.js"></script>
<script type="text/javascript" src="${ctx }/js/base.js"></script>
<script type="text/javascript" src="${ctx }/js/dateTimePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if("${project.type}"!=""){
			var id = "id"+"${project.type}";
			var menus=document.getElementById('tabmenu').getElementsByTagName("li");  
			if("id"+0==id){
				$(".admincp_r_search").hide();
			}else{
				$(".admincp_r_search").show();
			}
			for(i=0;i<3;i++){	       
				'id'+i==id?menus[i].className="on":menus[i].className="";
				//'id'+i==id?document.getElementById("content"+i).className="admincp_r2":document.getElementById("content"+i).className="admincp_r2_no";
			}
		}
	});
	function change(id){
		var menus=document.getElementById('tabmenu').getElementsByTagName("li");  
		if("id"+0==id){
			$(".admincp_r_search").hide();
		}else{
			$(".admincp_r_search").show();
		}
		for(i=0;i<3;i++){	       
			'id'+i==id?menus[i].className="on":menus[i].className="";
			//'id'+i==id?document.getElementById("content"+i).className="admincp_r2":document.getElementById("content"+i).className="admincp_r2_no";
		}
		$("#type").val(id.substring(2));
		//清空搜索栏
		$("#startTime").val("");
		$("#endTime").val("");
		$("#borrowName").val("");
		$("#name").val("");
		$("#pageForm").submit();
	}
</script>
<title>1717tou.com</title>
</head>
<body>
	<div style="background:#FFFFFF;">
		<div class="admincp_r1"><span>还款管理</span></div>
		<div class="admincp_r_nav">
			<ul id="tabmenu">
				<li class="on" onclick="change('id0')">还款产品</li>
				<li onclick="change('id1')">还款记录</li>
				<li onclick="change('id2')">还款计划</li>
			</ul>
		</div>

		<div class="admincp_r2" id="content2">
			<div class="admincp_r_search" style="display:none;">
				<form id="pageForm" name="pageForm" method="post" action="${ctx }/auth/project/repayManage">
					<input type="hidden" name="type" id="type" value="${project.type }"/><!-- 记录/计划/all -->
					<input type="hidden" name="borrowType" id="borrowType" value="0"/>

					<div class="admincp_r_search2">
						开始日期：<input type="text" id="startTime"  name="startTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly" value="${startTime }"/>  
						结束日期：<input type="text" id="endTime" name="endTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly" value="${endTime }"/>
					</div>
					<div class="admincp_r_search3">
						<div class="admincp_r_search31">
							受托方：<input type="text" id="borrowName" name="borrowName" value="${project.borrowName }"/>
						</div>
	
					</div>
				    <div class="admincp_r_search3">
						<div class="admincp_r_search31">
							产品名：<input type="text" id="name" name="name" value="${project.name }"/>
						</div>
						<div class="admincp_r_search32">
							<input type="submit" name="sousuo" value="搜索" />
						</div>
					</div>
				</form>
			</div>
			
			<!-- 循环开始 -->
			<c:forEach items="${page.result}" var="p">
				<div class="admincp_r2_b">
					<div class="admincp_r2_b_l">
						<div class="admincp_r2_b_l1">
							<a href="${ctx }/auth/project/repayDetail?id=${p.id }" title="${p.name }">
							<cc:subLength var="${p.name }" length="18"/>
							
							</a>
							<c:if test="${project.type==1||project.type==2}">
							<c:if test="${p.repayCount>0}">(利息还款)</c:if>
							<c:if test="${p.repayCount==0}">(本金还款)</c:if>
							</c:if>
						</div>
						<div class="admincp_r2_b_l2">
							受托方：<cc:subLength var = "${p.borrowName }" length="8"/>&nbsp;&nbsp;
							还款方式： 
							${p.repay_type}
							<c:if test="${project.type==null||project.type==0}">
							预期还款：&nbsp;
							<c:choose>
							<c:when test="${p.totalRepay>10000}">
							<font>${p.totalRepay/10000 }</font>&nbsp;万元 
							</c:when>
							<c:otherwise>
							<font>${p.totalRepay==null?0:p.totalRepay }</font>&nbsp;元 
							</c:otherwise>
							</c:choose>
							实际还款：&nbsp;
							<c:choose>
							<c:when test="${p.repay_money>10000}">
							<font>${p.repay_money/10000 }</font>&nbsp;万元
							</c:when>
							<c:otherwise>
							<font>${p.repay_money }</font>&nbsp;元 
							</c:otherwise>
							</c:choose>
							<br />
							产品开始时间：${p.pstartTime}
							</c:if>
							<c:if test="${project.type==1}">
							此次还款金额：&nbsp;<font>${p.repayMoney}</font>&nbsp;元 &nbsp;&nbsp;	<c:if test="${p.repayCount>0}">第<font>${p.repayCount}</font>批次利息还款</c:if><br />
							计划还款时间：${p.repayTime}&nbsp;&nbsp;
							实际还款时间：${p.updateTime}
							</c:if>
							<c:if test="${project.type==2}">
							计划还款金额：&nbsp;<font>${p.repayMoney}</font>&nbsp;元<br />
							计划还款时间：${p.repayTime}
							</c:if>
						</div>
					</div>
					<c:choose>
					
						<c:when test="${project.type==0||project.type==null}">
							<c:choose>
								<c:when test="${p.state==2}">
									<img src="${ctx }/images/icon_done.gif" />
								</c:when>
								<c:otherwise>
									<img src="${ctx }/images/icon_undone.png" />
								</c:otherwise>
							</c:choose>
						</c:when>
	
					</c:choose>
					<!-- 
					
					 -->
				</div>
			</c:forEach>
			<!-- 循环结束 -->

			<jsp:include page="../common/page.jsp"></jsp:include>

		</div>
	</div>
</body>
</html>
