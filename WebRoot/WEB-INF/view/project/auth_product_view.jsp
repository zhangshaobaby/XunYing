<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.getContextPath()}" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="网站描述" />
		<meta name="keywords" content="关键字" />
		<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
		<!--[if lt IE 9]>
		<script src="${ctx }/js/html5.js" type="text/javascript"></script>
		<script src="${ctx }/js/excanvas.js" type="text/javascript"></script>
		<script src="${ctx }/js/excanvas.compiled.js" type="text/javascript"></script>
		<![endif]-->
		<script src="<%=path%>/js/jquery-1.7.js"></script>
		<script src="<%=path%>/js/formValidator-4.1.3.js" type="text/javascript" charset="UTF-8"></script>
		<script src="${ctx }/js/jquery.knob.js"></script>
		<script src="${ctx }/js/myknob.js"></script>
		<title>1717tou.com</title>
		<script type="text/javascript">
			
			var basepath="<%=path%>";
			var moneyflag= false;
			var identityflag=false;
			$(document).ready(function(){
				changeCode();
			})
			function changeCode() {
				var img = document.getElementById("imgCode");
				img.src = basepath + "/touziidentify?" + new Date();
			}

			function beforeSend(){
				if(moneyflag==false){
					alert("请正确填写投资金额");
					return false;
				}
				if(identityflag ==false){
					alert("验证码填写错误");
					return false;
				}
				return true;
			}

</script>
	</head>

	<body>
		<%@ include file="../common/head.jsp"%>

		<!-- 单个产品信息开始 -->
		<div class="product">
			<div class="product_top">
				<div class="product_top_l">
					<span>我要投资 &gt; <c:choose>
							<c:when test="${model.type==0}">信托(信满盈)</c:when>
							<c:when test="${model.type==1}">资管(资涨通)</c:when>
							<c:when test="${model.type==2}">基金(金多宝)</c:when>
							<c:when test="${model.type==3}">屌丝宝</c:when>
							<c:otherwise>unknown</c:otherwise>
						</c:choose>
					</span>
				</div>
				<div class="product_top_r">
					
				</div>
			</div>

			<div class="product_cont">
				<div class="product_cont1">
					<div class="product_cont1_l">
						<div class="product_cont1_l_1">
							${model.name }
						</div>
						<div class="product_cont1_l_2">
							<div class="product_cont1_l_2a">
								预期年化率
								<br />
								<span style="color: #ea4c59; font-size: 26px; font-weight: bold;">${model.rate}%</span>
							</div>
							<div class="product_cont1_l_2a">
								投资期限
								<br />
								<span style="font-size: 26px; font-weight: bold;">${model.time_limit}
								<c:if test="${model.delay_time_limit!=null&&model.delay_time_limit!=0 }">+${model.delay_time_limit }</c:if>
								</span>个月
							</div>
							<div class="product_cont1_l_2a">
								募集金额
								<br />
							
								<c:choose>	
								<c:when test="${model.total_money>=10000}"><span style="font-size: 26px; font-weight: bold;">${model.total_money/10000}</span>万</c:when>
								<c:otherwise><span style="font-size: 26px; font-weight: bold;">${model.total_money}</span>元</c:otherwise>
								</c:choose>
								
								
					         
							</div>
						</div>
						<div class="product_cont1_l_3">
							发布日期：${model.pstartTime}&nbsp;&nbsp;&nbsp;&nbsp;还款日期：${model.pendTime }
						</div>
						<div class="product_cont1_l_4">
							担保机构：${company.company_name}
							<br />
							加入条件：最低${low }
							<br />
							单笔投资上限：${high }
						</div>
						<div class="product_cont1_l_5">
							还款方式：
							${model.repay_content}
						</div>
						<div class="product_cont1_l_6">
							<div class="product_cont1_l_6_a">
								<input class="knob" data-height="130" data-width="130"
									data-fgColor="#ea4c59" data-thickness=".15" data-readOnly=true
									value="${percent }" data-min="0" data-max="100.01"
									style="display: none; width: 0px; visibility: hidden;" />
								<div class="product_cont1_l_6_a1">
									众筹进度
									<br />
									${percent }%
								</div>
							</div>
							<div class="product_cont1_l_6_b">
								<div class="product_cont1_l_6_b1">
									<span>投资人数：</span><font>${model.pay_number }</font>人投资此项目
									<br />
									<span>还款状态</span>：企业正常
									<c:choose>
										<c:when test="${model.state==0}">众筹中</c:when>
										<c:when test="${model.state==1}">还款中</c:when>
										<c:when test="${model.state==2}">还清</c:when>
									</c:choose>
									<br/>
									<span>剩余可投资金额：</span> <font>${release}元</font>
								</div>
							</div>
						</div>
					</div>
				

				</div>

				<div class="product_cont2">
					<div class="product_cont2_menu">
						<ul>
							<li onmousemove="changestyle(this.id)" id="td1">
								项目描述
							</li>
							<li onmousemove="changestyle(this.id)" id="td2">
								安全保障
							</li>
							<li onmousemove="changestyle(this.id)" id="td3">
								相关资料
							</li>
							<li onmousemove="changestyle(this.id)" id="td4">
								投标情况
							</li>
						</ul>
					</div>
					<div class="product_cont2_cont" id="product_cont2_content"></div>

					<div class="product_cont2_nocont" id="product_cont2_cont_td1">
						<h4>
							项目描述
						</h4>
						${model.content }
					</div>
					<div class="product_cont2_nocont" id="product_cont2_cont_td2">
						<h4>
							安全保障
						</h4>
						${model.control_content }
					</div>
					<div class="product_cont2_nocont" id="product_cont2_cont_td3">
						<h4>
							相关资料
						</h4>
						<c:forEach var="att" items="${companyAttachment}">
							${att.name }:<a href="${picpath}/${att.url }">点击查看</a>
							<br />
						</c:forEach>
					</div>

					<div class="product_cont2_nocont" id="product_cont2_cont_td4">
						<h4>
							投标情况
						</h4>
						<table width="100%" id="tenderList" align="center" cellspacing="1" bgcolor="#cccccc" border="0">
							<tr align="center" valign="middle" height="40" bgcolor="#ffffff">
								<td>名字</td>
								<td>金额</td>
								<td>时间</td>
							</tr>
						</table>
						<span id="moreList" onclick="turnPage()">查看更多</span>
					</div>
				</div>
			</div>
		</div>
		<!-- 单个产品信息结束 -->

		<%@ include file="../common/foot.jsp"%>

		<script type="text/javascript">
	function changestyle(id){
		var cur_menu=document.getElementById(id);
		var con=document.getElementById("product_cont2_content");
		cur_menu.className="product_cont2_menu2";
		for(i=1; i<=4; i++){
			if(String("td"+i)!=id)	document.getElementById(String("td"+i)).className="product_cont2_menu1";	
		}
		//至此是更改菜单的样式;	
		con.innerHTML=document.getElementById(String("product_cont2_cont_"+id)).innerHTML;
		
		
	}
</script>
		<script type="text/javascript">changestyle("td1");</script>
		<script>
			var page = 1;
			var next = true;
			function turnPage(){
				if(next){
					$.post("${ctx}/project/tenderList",{id:${model.id},currPage:page},function(data){
						var obj = eval("("+data+")");
						var list = obj[0];
						if(page==obj[1]){
							next = false;
							$("#moreList").hide();
						}else{
							page = page + 1;
						}
						var html="";
						for(var i = 0 ; i < list.length ; i++){
							html+="<tr align='center' valign='middle' height='40' bgcolor='#FFFFFF'>";
								html+="<td>"+list[i][0]+"***"+list[i][1]+"</td>";
								html+="<td>"+list[i][2]+"</td>";
								html+="<td>"+list[i][3]+"</td>";
							html+="</tr>"
						}
						$("#tenderList").append($(html));
					});
				}
			}
			$(document).ready(function(){
				turnPage();
			});
			
		</script>
		<div class="zhang" id="zhang" style="display:none;">
			<div class="zhangwin">
				<div class="zhangwin_close"><a href="javascript:void(0);" onclick="zhangwin_close()"><img src="${ctx }/images/icon_close.png" /></a></div>
				<div class="zhangwin1"></div>
				<!--  
				<div class="zhangwin2">
					<div class="zhangwin2_a">
					<span>50元</span>代金券（满100000可以用）
					</div>
					<div class="zhangwin2_b">
					期限：2014/10/01 至 2014/10/01
					</div>
				</div>
				-->
				<div style="text-align:center" id="zhang_loading">loading</div>
			</div>
		</div>
		<script language="javascript">
			function zhangwin_close(){
				var zhang = document.getElementById('zhang');
				zhang.style.display="none";	
			}
			function zhangwin_play(){
				var zhang = document.getElementById('zhang');
				zhang.style.display="block";
			}
			function showLuckyMoney(){
				if(!/^\d+$/.test($("#transAmt").val())){
					$("#transAmt").blur();
					$("#transAmt").val("");
					$("#transAmt").focus();
					return;
				}
				$.post("${ctx}/project/luckyMoneyList",{transAmt:$("#transAmt").val(),type:${model.type}},function(data){
					var obj = eval("("+data+")");
					var html="";
					for(var i=0;i<obj.length;i++){
						html+="<div class='zhangwin2' onclick='ensure("+obj[i][0]+")'>";
						html+="<div class='zhangwin2_a'>";
						html+="<span>"+obj[i][1]+"元</span>代金券（满"+obj[i][4]+"可以用）";
						html+="</div>";
						html+="<div class='zhangwin2_b'>";
						html+="期限："+obj[i][2]+" 至 "+obj[i][3]+"";
						html+="</div>";
						html+="</div>";
					}
					$("#zhang_loading").hide();
					$(html).appendTo($(".zhangwin"))
					zhangwin_play();
				});
			}
			function ensure(id){
				$("#luckyId").val(id);
				zhangwin_close();
			}
		</script>
	</body>
</html>