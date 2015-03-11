<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8"/>
	<meta http-equiv="pragma" content="no-cache" />
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/main.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/user_allview.css">
	<script src="<%=path %>/js/jquery-1.7.js" type="text/javascript"></script>
<script src="<%=path %>/js/Highcharts-4.0.1/js/highcharts.js"></script>
<script src="<%=path %>/js/Highcharts-4.0.1/js/modules/exporting.js"></script>
<script type="text/javascript">
function signed(){
	jQuery.ajax({type:"POST", url: "<%=path %>/user/usersigned", success:function (result) {
		if (result ==true) {
		alert("签到成功");
		    window.location.reload();
		} else {
			alert("签到失败");
		}
	}});


}

function showRecord(){
	window.parent.location.href='<%=path %>/user/userCenter?record'
}
$(function () {
    $('#moneyPiePic').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: '资金比例',
            data: [
                ['可用余额',   ${AvlBalNumber}],
                ['持有项目',   ${pbdNumber}]
            ]
        }]
    });
    
    $('#proPiePic').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: '项目比例',
            data: [
                ['资涨通',   ${zizhangprocount}],
                ['信满盈',       ${xmprocount}],
                ['金多宝',   ${jdprocount}],
                ['屌丝宝',     ${dsprocount}],
            ]
        }]
    })
});
</script>
	<title>user_allview</title>
</head>
<body>
	
				<div class="user_cont_rr" style="background:#FFFFFF;">
                  <div class="user_cont_rrr">
                    <div class="user_cont_r1">
                       <div class="user_cont_r1_l">
                       <h3 class="title">
                    ${period}，${sessionScope.user.username}！</h3>
                    <c:choose>
                     <c:when test="${realNameAuthent==true}">
                       <img title="实名认证" src="<%=path %>/images/card1_1.png" alt="">
                     </c:when>
                     <c:otherwise>
                       <img title="实名认证" src="<%=path %>/images/card1_2.png" alt="">
                     </c:otherwise>
                    </c:choose>
                    <c:choose>
                     <c:when test="${phone==true}">
                       <img title="手机认证" src="<%=path %>/images/card3_1.png" alt="">
                     </c:when>
                     <c:otherwise>
                       <img title="手机认证" src="<%=path %>/images/card3_2.png" alt="">
                     </c:otherwise>
                    </c:choose>
                     <c:choose>
                     <c:when test="${question==true}">
                       <img title="密保设置" src="<%=path %>/images/card2_1.png" alt="">
                     </c:when>
                     <c:otherwise>
                       <img title="密保设置" src="<%=path %>/images/card2_2.png" alt="">
                     </c:otherwise>
                    </c:choose>
                       	<span class="user">
                       	安全等级：
                       	<c:choose>
							<c:when test="${ulevel<=1}"><span class="user_a">低</span>
                            <img src="<%=path %>/images/levelLow.png" /></c:when>
							<c:when test="${ulevel==2}"><span class="user_a">中</span>
                        <img src="<%=path %>/images/levelzhong.png" /></c:when>
							<c:when test="${ulevel>=3}"><span class="user_a">高</span>
                        <img src="<%=path %>/images/levelHigh.png" /></c:when>
							</c:choose>
                       	
                        <span class="user_c"><a href="<%=path%>/user/authentList" target="framecont">[提升]</a></span>
                       	</span>
                       	<span class="san">第三方支付账号：${sessionScope.user.huifuAccount}</span>
                       </div>
                       <div class="user_cont_r1_r">
                         <div class="a">
                       	 <span>${hbnum}</span> 红包 <a href="<%=path%>/user/luckyMoney" target="framecont"><img src="<%=path %>/images/user_allview5.png" alt=""></a>
                       	 </div>
                       	 <div class="b">
                       	 <span>${score}</span> 积分 <c:choose><c:when test="${signed==false}"><img onclick="signed()" src="<%=path %>/images/para2.png" alt=""></c:when><c:otherwise>已签到</c:otherwise></c:choose>
                       	 </div>
                       </div>
                      </div>
                       <div class="user_cont_r2">
					    <div class="user_cont_r2_1"><span>资产详情</span>
					    </div>
					    <table class="user_cont_r2_2" cellspacing=0 border="0">
                            
                               <tr class="user_cont_r2_2a">
								<td class="user_cont_r2_2a1">总资产</td>
								<td class="user_cont_r2_2a2">${totalAmt }元</td>
								<td class="user_cont_r2_2a3"><a target="_blank"  href="<%=path %>/project/list"><img src="<%=path %>/images/user_allview2.png" alt=""></a></td>
								
							  </tr>
                               <tr class="user_cont_r2_2b">
								<td class="user_cont_r2_2a1">可用余额</td>
								<td class="user_cont_r2_2a2">${AvlBal}元</td>
								<td class="user_cont_r2_2a3"><a target="_blank"  href="<%=path %>/user/recharge"><img src="<%=path %>/images/user_allview3.png" alt=""></a>  <a target="_blank"  href="<%=path%>/cash/enchashment"><img src="<%=path %>/images/user_allview4.png" alt=""></a></td>	
							  </tr>
                             <!--  <tr class="user_cont_r2_2c">
								<td class="user_cont_r2_2a1">冻结资金</td>
								<td class="user_cont_r2_2a2">${FreBal}元</td>
								<td class="user_cont_r2_2a3"></td>	
							  </tr>
							   --> 
                              <tr class="user_cont_r2_2d">
								<td class="user_cont_r2_2a1">持有项目</td>
								<td class="user_cont_r2_2a2">${pbd}元</td>
								<td class="user_cont_r2_2a3">(<span><a  target="framecont"  style="color: #57f;" onclick="showRecord()" href="javascript:void(0);">${procount }笔</a></span>)</td>	
							  </tr>
                              <tr class="user_cont_r2_2e">
								<td class="user_cont_r2_2a1">累计盈利</td>
								<td class="user_cont_r2_2a2">${pureplan}元</td>
								<td class="user_cont_r2_2a3"></td>	
							  </tr>
                              
                           </table>	
                           <div id="moneyPiePic" class="user_cont_r2_3">
                          
                           </div>									
					   </div>
					   <div class="user_cont_r3">
					    <div class="user_cont_r3_1"><span>项目资金配比</span>
					    </div>
					    <table class="user_cont_r2_2" cellspacing=0 border="0">
                            
                               <tr class="user_cont_r2_2a">
								<td class="user_cont_r2_2a1">总投入</td>
								<td class="user_cont_r2_2a2">${pbd}元</td>
								<td class="user_cont_r2_2a3"></td>
								
							  </tr>
                               <tr class="user_cont_r2_2b">
								<td class="user_cont_r2_2a1">资涨通产品</td>
								<td class="user_cont_r2_2a2">${zizhangprocount}元</td>
								<td class="user_cont_r2_2a3"></td>	
							  </tr>
                               <tr class="user_cont_r2_2c">
								<td class="user_cont_r2_2a1">信满盈产品</td>
								<td class="user_cont_r2_2a2">${xmprocount}元</td>
								<td class="user_cont_r2_2a3"></td>	
							  </tr>
                              <tr class="user_cont_r2_2d">
								<td class="user_cont_r2_2a1">金多宝产品</td>
								<td class="user_cont_r2_2a2">${jdprocount}元</td>
								<td class="user_cont_r2_2a3"></td>	
							  </tr>
                           <tr class="user_cont_r2_2d">
								<td class="user_cont_r2_2a1">屌丝宝产品</td>
								<td class="user_cont_r2_2a2">${dsprocount}元</td>
								<td class="user_cont_r2_2a3"></td>	
							  </tr>
                              
                           </table>	
                           <div   id="proPiePic" class="user_cont_r2_3">
                          
                           </div>									
					   </div>
					   <div class="clear"></div>
				 

				</div>
</body>
</html>
