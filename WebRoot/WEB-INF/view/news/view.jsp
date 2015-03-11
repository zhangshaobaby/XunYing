<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${dict.dictName }公告</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/pingtai1.css">

</head>
<body>
<%@ include file="../common/head_html.jsp"%>
	<div class="pingtai">
		<div class="pingtai1">
			<a href="${ctx }/">首页</a>&gt;
			<a href="${ctx }/news/list?type=${dict.id }">
				${dict.dictName }
			</a>&gt;
			<span>${news.name }</span>
		</div>
        
		<div class="pingtai2">
			<div class="pingtai2_1">分享到：<div class="bdsharebuttonbox" style="float:right;margin-top:-5px;margin-left:5px;"><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a href="#" class="bds_more" data-cmd="more"></a></div>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["tsina","weixin","qzone","tqq","renren"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["tsina","weixin","qzone","tqq","renren"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script></div>
            <div class="pingtai_cont">
                <div class="pingtai2_2">${news.name }</div>
                <div class="pingtai2_3">${news.createTime }</div>
<!---------------------------------------------------------图片--------------------------------------------------------->                 
                <c:set var="imgs" value="${fn:split(news.imgUrl, ',')}" />
                <div class="pingtai2_img">
                <c:if test="${news.imgUrl!=''&&news.imgUrl!=null}">
				<c:forEach items="${imgs}" var = "img" varStatus="inde">
                	<img src="<cc:picHost/>/${img }" width=772px height=275px />
               	</c:forEach>
               	</c:if>
                </div>
                <div class="pingtai2_4">
                	${news.content }
               </div>
               <div style="clear:both"></div>
                 <br/>   <br/>

            </div>
        </div>
	</div>
<%@ include file="../common/foot.jsp"%>
</body>
</html>