<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
<script src="${ctx }/js/jquery-1.7.js" type="text/javascript"></script>
<title>1717tou.com</title>
</head>

<body>
	<!-- 头部内容开始 -->
	<%@ include file="common/head.jsp" %>
	<!-- 头部内容结束 -->
	<!-- 幻灯片开始 -->
		
		
<div class="feat-area">
    <ul class="slider" id="slider">
    	<c:forEach items="${advertList}" var="advert">
    		<li data-opacity="0.2" style="background:${advert.color} url(${picpath}/${advert.imgUrl }) no-repeat center center"><a href="${advert.href }" target="_blank">${advert.name }</a></li>
    	</c:forEach>
    </ul>
    <div class="gain" id="userGain">
              <div class="gain-cont">
                <h2>一起一起投年化收益率</h2>
                <p class="f"><span style="color:#fff; font-size:13px">最高</span>13.24%</p>
                <p><em>30</em> 倍活期存款收益 <span class="light-fc">3.27</span> 倍定期存款收益 </p>
                <p class="reg"><a href="${ctx }/user/register">免费注册</a></p>
                <p class="tar">已有账号? <a href="${ctx }/user/dologin">立即登录</a></p> 
              </div>
              <div class="opacity"></div>

    </div>
</div>


		
	<!-- 幻灯片结束 -->
	
	
	<div class="main">
		<!-- 首页内容1开始 -->
		<div class="main1">
			<div class="main1_mid">
				<div class="main1_mid1"><img src="images/anquan1.png" /></div>
				<div class="main1_mid2"><img src="images/anquan2.png" /></div>
				<div class="main1_mid3">
					投资者交易资金托管于商务部旗下的第三方分支支付机构“国付宝”内，平台资金与信息流分离，用户资金能够得到安全的保障。
				</div>
			</div>
			<div class="main1_mid" style="margin-left:29px;">
				<div class="main1_mid1"><img src="images/gaoshouyi1.png" /></div>
				<div class="main1_mid2"><img src="images/gaoshouyi2.png" /></div>
				<div class="main1_mid3">
					投资者交易资金托管于商务部旗下的第三方分支支付机构“国付宝”内，平台资金与信息流分离，用户资金能够得到安全的保障。
				</div>
			</div>
			<div class="main1_mid"  style="margin-left:29px;">
				<div class="main1_mid1"><img src="images/menkandi1.png" /></div>
				<div class="main1_mid2"><img src="images/menkandi2.png" /></div>
				<div class="main1_mid3">
					投资者交易资金托管于商务部旗下的第三方分支支付机构“国付宝”内，平台资金与信息流分离，用户资金能够得到安全的保障。
				</div>
			</div>
			
		</div>
		<!-- 首页内容1结束 -->
			
		<!-- 首页内容2开始 -->
		<div class="main2">
			<div class="main2_l">
				<div class="main2_l_mid">
					<div class="main2_l_mid1"><span>屌丝宝</span>&nbsp;&nbsp;屌丝理财神器</div>
					<div class="main2_l_mid2">8%-12%高收益，天天返利，超低门槛，100元起投，存取自由&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx }/project/list?type=3&vsi=1">>>了解更多</a></div>
					<div class="main2_l_mid3"><a href="${ctx }/project/list?type=3&vsi=1"><img src="images/qianggou.png" /></a></div>
				</div>
			</div>
			
			<div class="main2_r">
				<div class="main2_r_mid">
					<div class="main2_r_mid1">众筹人数</div>
					<div class="main2_r_mid2">突破${totaluser}</div>
					<div class="main2_r_mid4">总投资额已达到${totalinvest }</div>
					<div class="main2_r_mid5">累计效益已达到${totalbenefit}</div>
				</div>
			</div>
		</div>
		<!-- 首页内容2结束 -->
			
		<!-- 首页产品开始 -->
		<div class="main3">
			<div class="main3_a">
				<div class="main3_a_l">
					<span>信满盈</span>&nbsp;|&nbsp; 投资方向：银监会监管的大型信托公司信托集合计划
				</div>
				<div class="main3_a_r"><a href="${ctx }/project/list?type=0&vsi=1">更多>></a></div>
			</div>
			<!-- 二级产品信息开始 -->
			<c:forEach items="${pList1}" var="p">
			<div class="main3_b">
				<div class="main3_b1">${p.name }</div>
				<div class="main3_b2">年利率${p.rate }%</div>
				<div class="main3_b3">期限${p.time_limit }个月</div>
				<div class="main3_b4">
					<div class="main3_b4_l"><img src="${ctx }/images/icon_cp.png" /></div>
					<div class="main3_b4_r">${p.db_name}担保</div>
				</div>
				<div class="main3_b5">
					<!-- images/status2.png -->
					<a href="${ctx }/project/view?id=${p.id }">
					<c:choose>
						<c:when test="${p.state==0}">
							<img src="${ctx }/images/status0.png" />
						</c:when>
						<c:when test="${p.state==1}">
							<img src="${ctx }/images/status1.png" />
						</c:when>
						<c:when test="${p.state==2}">
							<img src="${ctx }/images/status2.png" />
						</c:when>
					</c:choose>
					</a>
				</div>
			</div>
			</c:forEach>
			<!-- 二级产品信息结束 -->
		</div>
		<!-- 首页产品结束 -->
	</div>
		<!-- 首页产品开始 -->
		<div class="main3">
			<div class="main3_a">
				<div class="main3_a_l">
					<span>资涨通</span>&nbsp;|&nbsp; 投资方向：银监会监管的券商资产管理计划
				</div>
				<div class="main3_a_r"><a href="${ctx }/project/list?type=1&vsi=1">更多>></a></div>
			</div>
			<!-- 二级产品信息开始 -->
			<c:forEach items="${pList2}" var="p">
			<div class="main3_b">
				<div class="main3_b1">${p.name }</div>
				<div class="main3_b2">年利率${p.rate }%</div>
				<div class="main3_b3">期限${p.time_limit }个月</div>
				<div class="main3_b4">
					<div class="main3_b4_l"><img src="${ctx }/images/icon_cp.png" /></div>
					<div class="main3_b4_r">${p.db_name}担保</div>
				</div>
				<div class="main3_b5">
					<!-- images/status2.png -->
					<a href="${ctx }/project/view?id=${p.id }">
					<c:choose>
						<c:when test="${p.state==0}">
							<img src="${ctx }/images/status0.png" />
						</c:when>
						<c:when test="${p.state==1}">
							<img src="${ctx }/images/status1.png" />
						</c:when>
						<c:when test="${p.state==2}">
							<img src="${ctx }/images/status2.png" />
						</c:when>
					</c:choose>
					</a>
				</div>
			</div>
			</c:forEach>
			
		</div>
		<!-- 首页产品结束 -->
		<!-- 首页产品开始 -->
		<div class="main3">
			<div class="main3_a">
				<div class="main3_a_l">
					<span>金多宝</span>&nbsp;|&nbsp; 投资方向：大型担保公司的担保的阳光私募资金
				</div>
				<div class="main3_a_r"><a href="${ctx }/project/list?type=2&vsi=1">更多>></a></div>
			</div>
			<!-- 二级产品信息开始 -->
			<c:forEach items="${pList3}" var="p">
			<div class="main3_b">
				<div class="main3_b1">${p.name }</div>
				<div class="main3_b2">年利率${p.rate }%</div>
				<div class="main3_b3">期限${p.time_limit }个月</div>
				<div class="main3_b4">
					<div class="main3_b4_l"><img src="${ctx }/images/icon_cp.png" /></div>
					<div class="main3_b4_r">${p.db_name}担保</div>
				</div>
				<div class="main3_b5">
					<!-- images/status2.png -->
					<a href="${ctx }/project/view?id=${p.id }">
					<c:choose>
						<c:when test="${p.state==0}">
							<img src="${ctx }/images/status0.png" />
						</c:when>
						<c:when test="${p.state==1}">
							<img src="${ctx }/images/status1.png" />
						</c:when>
						<c:when test="${p.state==2}">
							<img src="${ctx }/images/status2.png" />
						</c:when>
					</c:choose>
					</a>
				</div>
			</div>
			</c:forEach>
			
		</div>
		<!-- 首页产品结束 -->
	
	
	<%@ include file="common/foot.jsp" %>
</body>
</html>
