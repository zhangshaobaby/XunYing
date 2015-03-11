<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>平台动态</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/pingtai.css">
    <script src="${ctx }/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="${ctx }/js/base.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function(){
		$(".pingtai2_tit1").click(function(){
			$(".pingtai2_tit1").addClass("pingtai2_tit1_h");
			$(".pingtai2_tit2").removeClass("pingtai2_tit2_h");
			$(".pingtai2_tit3").removeClass("pingtai2_tit3_h");
		});
		$(".pingtai2_tit2").click(function(){
			$(".pingtai2_tit1").removeClass("pingtai2_tit1_h");
			$(".pingtai2_tit2").addClass("pingtai2_tit2_h");
			$(".pingtai2_tit3").removeClass("pingtai2_tit3_h");
		});
		$(".pingtai2_tit3").click(function(){
			$(".pingtai2_tit1").removeClass("pingtai2_tit1_h");
			$(".pingtai2_tit2").removeClass("pingtai2_tit2_h");
			$(".pingtai2_tit3").addClass("pingtai2_tit3_h");
		});
		$(".pingtai2_tit"+${model.type-64}).click();
		$(".pingtai2_tit1").click(function(){
			window.location.href="${ctx}/news/list?type=65";
		});
		$(".pingtai2_tit2").click(function(){
			window.location.href="${ctx}/news/list?type=66";
		});
		$(".pingtai2_tit3").click(function(){
			window.location.href="${ctx}/news/list?type=67";
		});
	})
	</script>
</head>
<body>
	<%@ include file="../common/head_html.jsp"%>
	<form action="${ctx}/news/list"><input type="hidden" name="type" value="${model.type }"/></form>
	<div class="pingtai">
		<div class="pingtai1"><a href="${ctx }/">首页</a>&gt;<span>平台动态</span></div>
		<div class="pingtai2">
			<ul class="pingtai2_tit">
	            <li class="pingtai2_tit1 pingtai2_tit1_h" style="cursor:pointer;">平台动态</li>
	            <li class="pingtai2_tit2" style="cursor:pointer;">产品公告</li>
	            <li class="pingtai2_tit3" style="cursor:pointer;">媒体新闻</li>
            </ul>
            <div class="pingtai2_con">
				 <ul class="pingtai2_pingtai">
            		<c:forEach items="${page.result}" var="news">
                 	<li>
	                 	<a href="${news.href }" target="_blank">
		                 	<span class="time2">
		                 		<cc:subLength var="${news.name }" length="60"/>
		                 	</span>
							<span class="time1">${news.createTime }</span>
						</a>
					</li>
					</c:forEach>
                 </ul>
            </div>
            <div class="user_cont_r_2d">
           		<jsp:include page="../common/page.jsp"></jsp:include>
            </div>
		</div>
	</div>
	<%@ include file="../common/foot.jsp"%>
</body>
</html>