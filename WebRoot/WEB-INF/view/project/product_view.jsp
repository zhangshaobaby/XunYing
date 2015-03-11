<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8"/>
<link href="${ctx }/css/main.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/product.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/product_popup.css"rel="stylesheet" type="text/css" />
<title>1717tou.com</title>
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<!--[if lt IE 9]>
<script src="${ctx }/js/html5.js" type="text/javascript"></script>
<script src="${ctx }/js/excanvas.js" type="text/javascript"></script>
<script src="${ctx }/js/excanvas.compiled.js" type="text/javascript"></script>
<![endif]-->
<script src="${ctx }/js/jquery.min1.js"></script>
<script src="${ctx }/js/jquery.knob.js"></script>

<script type="text/javascript">
	/*控制弹出层ds5*/
	window.onload=function() {$('.knob').knob();
		$('.theme-login').click(function(){
			$('.theme-popover-mask').css("display","block");
			$('.theme-popover').slideDown(200);
		})
		$('.theme-poptit .close').click(function(){
			$('.theme-popover-mask').css("display","none");
			$('.theme-popover').slideUp(200);
		})
		$('.theme-login3').click(function(){
			$('.theme-popover-mask1').css("display","block");
			$('.theme-popover3').slideDown(200);
		})
		$('.close1').click(function(){
			$('.theme-popover-mask3').css("display","none");
			$('.theme-popover3').slideUp(200);
		})
		$('.theme-login1').click(function(){
			$('.theme-popover-mask1').css("display","block");
			$('.theme-popover1').slideDown(200);
		})
		$('.close1').click(function(){
			$('.theme-popover-mask1').css("display","none");
			$('.theme-popover1').slideUp(200);
		})
		$("#td1").mousemove();
		changeCode();
		
		//倒计时剩余时间
		releasetime();
		
		$("#td1").click();
	}
	var basepath="${ctx}";
	var moneyflag= false;
	var identityflag=false;
	var isallow=false;
	var EndTime = new Date("${model.end_time}".replace(/-/g,"/"));
	var StartTime = new Date("${model.start_time}".replace(/-/g,"/"));
	var NowTime = new Date("${date}");
	var t = (StartTime.getTime() - NowTime.getTime()>0) ? (StartTime.getTime() - NowTime.getTime()) : (EndTime.getTime() - NowTime.getTime());
	var count = 0;
	$(document).ready(function(){
		if(StartTime.getTime() - NowTime.getTime()>0){
			$("#tagTime").prev().html("预购时间");
		}
		if(EndTime.getTime() - NowTime.getTime()<0){
			$("#tagTime").html("募集结束");
		}
	});
	function releasetime(){
		count++;
		t-=1000;
		if(t>=0&&"${model.state==0}"){
			if ("${release}"=="0"||"${release}"=="0.00"){
				$("#tagTime").html("募集结束");
			}else{
				var d=Math.floor(t/1000/60/60/24);
				var h=Math.floor(t/1000/60/60%24);
				var m=Math.floor(t/1000/60%60);
				var s=Math.floor(t/1000%60);
				$("#tagTime").html(d+"天"+h+"时"+m+"分"+s+"秒");
				setTimeout(function(){releasetime()},1000)
			}
		}else if(t<0&&"${model.state==0}") {
			//$("#tagTime").html("募集结束");
			if(count!=1)window.location.href=window.location.href;
		}
	}
	function changeCode() {
		var img = document.getElementById("imgCode");
		if(img!=null)
		img.src = basepath + "/touziidentify?" + new Date();
	}
	
	var protocolTag24 = false;
	var protocolTag6 = false;
	function checkProtocol24(){
	 protocolTag24 = true;
	 	hideProtocol();
	}
	function checkProtocol6(){
	 	protocolTag6 = true;
	 	if($("#check_alert").html()=="您未接受风险提示")
	 		$("#check_alert").html("");
	 	hideProtocol();
	}
	function hideProtocol24(){
	 	if($("#check_alert").html()=="您未接受风险提示")
	 		$("#check_alert").html("");
		protocolTag24 = false;
	 	hideProtocol();
	}
   function hideProtocol6(){
	 protocolTag6 = false;
	 	hideProtocol();
	}

	function hideProtocol(){
		$('.close1').click();
	}
	function chk(){
	  var chk_value =[];  
	  $('input[name="isagree"]:checked').each(function(){  
	   chk_value.push($(this).val());  
	  });  
	  
	  if(chk_value.length==0){
	  isallow =false;
	  }else{
	   isallow =true;
	  }
	  //弹出层
	  if(moneyflag){
	  	var checkId = parseInt($("#transAmt").val())>=240000?"24":parseInt($("#transAmt").val())>=60000?"6":"0";
	  	if(checkId == "24"){
		  	if(!protocolTag24){
				if(parseInt($("#transAmt").val())>=240000)
					$('.theme-login1').click()
				else if (parseInt($("#transAmt").val())>=60000)
					$('.theme-login3').click();
			}
		}
	  	if(checkId == "6"){
		  	if(!protocolTag6){
				if(parseInt($("#transAmt").val())>=240000)
					$('.theme-login1').click()
				else if (parseInt($("#transAmt").val())>=60000)
					$('.theme-login3').click();
			}
		}
	  }
	} 
</script>
</head>

<body>
	<%@ include file="../common/head_html.jsp"%>
	<!-- 单个产品信息开始 -->
<div class="allproduct"> 
    <div class="allproduct_dh">
    	<a href="${ctx }/index">首页</a>&gt;&nbsp; 
    	<a href="${ctx }/project/list?type=${model.type }">
    		<c:choose>
				<c:when test="${model.type==0}">信托(信满盈)</c:when>
				<c:when test="${model.type==1}">资管(资涨通)</c:when>
				<c:when test="${model.type==2}">基金(金多宝)</c:when>
				<c:when test="${model.type==3}">屌丝宝</c:when>
				<c:otherwise>unknown</c:otherwise>
			</c:choose>
    	</a>&gt;&nbsp;
    	<span><cc:subLength var = "${model.name}" length = "20"/></span>
    </div>

		<div class="product">
			<div class="product_cont">
				<div class="product_cont1">
					<div class="product_cont1_l">
						<div class="product_cont1_ll">
							<c:choose>
								<c:when test="${company!=null}">
									<c:if test="${company.logo==null||company.logo==''}">
										<img src="${ctx }/images/company_default.png"/>
									</c:if>
									<c:if test="${company.logo!=null&&company.logo!=''}">
										<img src="${picpath }/${company.logo}"/>
										${company.company_name}
									</c:if>
									
								</c:when>
								<c:when test="${company2!=null}">
									<c:if test="${company2.logo==null||company2.logo==''}">
										<img src="${ctx }/images/company_default.png"/>
									</c:if>
									<c:if test="${company2.logo!=null&&company2.logo!=''}">
										<img src="${picpath }/${company2.logo}"/>
											${company2.company_name}
									</c:if>
								
								</c:when>

							</c:choose>
                      	</div>
						<div title="${model.name }" class="product_cont1_l_1" style="word-wrap:break-word; word-break:break-all;"><c:choose><c:when test="${model.name==null||model.name==''}">
									暂无标题
									</c:when>
									<c:when test="${fn:length(model.name)>14}">
									${fn:substring(model.name,0,14)}..
									</c:when>
									<c:otherwise>
									${model.name}
									</c:otherwise>
									</c:choose></div>
					
						<div class="product_cont1_l_2">
							<table cellspacing="0" >
								<tr class="product_cont1_l_2a">
									<td class="product_cont1_l_2a_l">发布日期</td>
									<td class="product_cont1_l_2a_r">${model.publictime}</td>
								</tr>
								<tr class="product_cont1_l_2a">
									<td class="product_cont1_l_2a_l">收益方式</td>
									<td class="product_cont1_l_2a_r">
										${model.repay_content}
									</td>
								</tr>
								<tr class="product_cont1_l_2a">
									<td class="product_cont1_l_2a_l">产品类型</td>
									<td class="product_cont1_l_2a_r">
										<c:choose>
											<c:when test="${model.type==0}">信托(信满盈)</c:when>
											<c:when test="${model.type==1}">资管(资涨通)</c:when>
											<c:when test="${model.type==2}">基金(金多宝)</c:when>
											<c:when test="${model.type==3}">屌丝宝</c:when>
											<c:otherwise>unknown</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</table>
						</div>
<c:if test="${model.type==3}">
						<div class="product_cont1_l_3">
							<div class="product_cont1_l_3a">募集金额
								<span style=" font-size:14px;color:#0060a5; float:right;">
								<c:choose>	
										<c:when test="${model.total_money>=10000}">${model.total_money/10000}</c:when>
										<c:otherwise>${model.total_money}</c:otherwise>
									</c:choose>
									<c:if test="${model.total_money>=10000}">万</c:if>
									元	
								</span>
								</div>
                            <div class="product_cont1_l_3b">预期年化利率<br/>产品期限</div>
                            <div class="nian2">
<span style="color:#e33340; position:relative;top:0px;left:65px;width: 35px;display: inline-block;text-align: center"><cc:toMoney var="${drbp[0].rate }"/>%</span>
<span style="color:#e33340;position:relative;top:0px;left:95px;width: 35px;display: inline-block;text-align: center"><cc:toMoney var="${drbp[1].rate }"/>%</span>
<span style="color:#e33340;position:relative;top:0px;left:120px;width: 35px;display: inline-block;text-align: center"><cc:toMoney var="${drbp[2].rate }"/>%</span>
<span style="color:#e33340;position:relative;top:0px;left:145px;width: 35px;display: inline-block;text-align: center"><cc:toMoney var="${drbp[3].rate }"/>%</span>
<div class="nian2_1" style="color:#0060a5;margin-top:15px;">
<span style="margin-left:0px;">成立日</span>
<span>${drbp[0].month }个月</span>
<span>${drbp[1].month }个月</span>
<span style="text-align:right;">${drbp[2].month }个月</span>
<span style="text-align:right;">${drbp[3].month }个月</span>
</div>
                                        
                          	</div>
						</div>
</c:if>
<c:if test="${model.type!=3}">
	<div class="product_cont1_l_9">
		<div class="product_cont1_l_9a">
			预期年化率<br/>
			<span style="color:#ea4c59; font-size:26px; ">
				<cc:toMoney var="${model.rate}"/>%
			</span>
		</div>
		<div class="product_cont1_l_9a">
			产品期限<br/>
			<span style=" font-size:26px; font-weight:bold;color:#8aaecf;">
			${model.time_limit}
			<c:if test="${model.delay_time_limit!=null&&model.delay_time_limit!=0 }">+${model.delay_time_limit }</c:if>
			</span>个月
		</div>
		<div class="product_cont1_l_9a">
			募集金额<br/>
			<span style=" font-size:26px;color:#8aaecf; font-weight:bold;">
			<c:choose>	
			<c:when test="${model.total_money>=10000}"><span style="font-size: 26px; font-weight: bold;">${model.total_money/10000}</span></c:when>
			<c:otherwise><span style="font-size: 26px; font-weight: bold;">${model.total_money}</span></c:otherwise>
			</c:choose>
			</span>
			<c:if test="${model.total_money>=10000}">万</c:if>
			元
		</div>
	</div>
</c:if>
						<div class="product_cont1_l_4">
							<div class="product_cont1_l_6_a">
								<input class="knob" data-height="100" data-width="100" data-fgColor="#ea4c59" data-thickness=".15" data-readOnly=true value="${percent}" data-min="0" data-max="100.01" style="display:none; width:10px;visibility:hidden;"/>
								<div class="product_cont1_l_6_a1">众筹进度<br />${percent }%</div>
							</div>
							<table cellspacing="0" >
								<tr class="product_cont1_l_4a"<c:if test="${model.type==3}"> style = "height:25px;"</c:if>>
									<td class="product_cont1_l_4a_l">目前投资人数</td>
									<td class="product_cont1_l_4a_r">${model.pay_number }人</td>
								</tr>
								<c:if test="${model.type==3}">
									<tr class="product_cont1_l_4a"<c:if test="${model.type==3}"> style = "height:25px;"</c:if>>
										<td class="product_cont1_l_4a_l">单人剩余可投金额</td>
										<td class="product_cont1_l_4a_r">${release}元</td>
									</tr>
								</c:if>
								<tr class="product_cont1_l_4a"<c:if test="${model.type==3}"> style = "height:25px;"</c:if>>
									<td class="product_cont1_l_4a_l">产品剩余金额</td>
									<td class="product_cont1_l_4a_r">${model.total_money-model._now_money}元</td>
								</tr>
								<tr class="product_cont1_l_4a"<c:if test="${model.type==3}"> style = "height:25px;"</c:if>>
									<td class="product_cont1_l_4a_l">剩余时间</td>
									<td class="product_cont1_l_4a_r" id="tagTime">
										
									</td>
								</tr>
							</table>
						</div>
						
					
					</div>
					<c:choose>
						<c:when test="${model.state==0&&percent<100&&sessionScope.user!=null&&dateTag==1&&startTag!=1}">
					<div class="product_cont1_r">
					<!-- 投资信息开始 -->
					<form name="formtouzi" id="subForm" method="post" target="_blank" action="${ctx }/huifu/initiativetender" onsubmit="return beforeSend();"  onclick="target='HELP_COMMON'">
						<input type="hidden" name="projectId" value="${model.id}" />
						<input type="hidden" name="timeLimit" value="${model.time_limit+model.delay_time_limit }" />
						<input type="hidden" name="luckyId" id="luckyId" />
						<div class="product_cont1_r_main">
							<div class="product_cont1_r1">
								<img src="${ctx}/images/product6.png"/>				
							</div>
							<div class="product_cont1_r2">
								<table cellspacing="0" >
								<tr class="product_cont1_r_2a">
									<td class="product_cont1_r_2a_l">单笔投资最少</td>
									<td class="product_cont1_r_2a_r">${low}</td>
								</tr>
								<tr class="product_cont1_r_2a">
									<td class="product_cont1_r_2a_l">单笔投资最大</td>
									<td class="product_cont1_r_2a_r">${high}</td>
								</tr>
								
							</table>
							</div>
							<div class="product_cont1_r3">
							<script>
								$(document).ready(function(){
									if(parseFloat("${model.hongbaorate}")==0){
										//$("#luckyClick").unbind("click");
										//$("#luckyClick").attr("onclick","");
										//$("#luckyNum").html("该产品不支持使用红包");
									}
								});
							</script>
							<table cellspacing="0" >
								<tr class="product_cont1_r_3a">
									<td class="product_cont1_r_3a_l">可用余额</td>
									<td class="product_cont1_r_3a_r">
									
									<c:choose>
										<c:when test="${self_money!=null }">
											<span style="color:#2571be">${self_money }</span>元
											<a target="_blank" href="${ctx }/user/recharge">
												<img class="btn" src="${ctx }/images/product4.png"/>
											</a>
										</c:when>
										<c:otherwise>
											<a href="${ctx }/dologin">请先登录</a>
										</c:otherwise>
									</c:choose>
									</td>
								</tr>
								<tr class="product_cont1_r_3a">
									<td class="product_cont1_r_3a_l">您的红包</td>
									<td class="product_cont1_r_3a_r" id="luckyNum">
									
									<c:choose>
										<c:when test="${self_money!=null }">
										  <span id="luckyspan">
											<span style="color:#e33340">${luckyCount}</span>个
									      </span>
									      <a id="luckyClick" class="btn btn-primary btn-large theme-login" onclick="showLuckyMoney()">
												<img class="btn" src="${ctx }/images/product2.png"/>
											</a>
										</c:when>	
										<c:otherwise>
											<a href="${ctx }/dologin">请先登录</a>
										</c:otherwise>
									</c:choose>
									</td>
								</tr>
								<tr class="product_cont1_r_3a">
									<td class="product_cont1_r_3a_l">我的投资金额</td>
									<td class="product_cont1_r_3a_r">
										<input type="text" class="touzi" name="transAmt" value="请输入投资金额" id="transAmt" style="color:#666;" onfocus="clearhongbao()"/>元
									</td>
								</tr>
								<tr class="product_cont1_r_3a">
									<td class="product_cont1_r_3a_l">请输入验证码</td>
									<td class="product_cont1_r_3a_r">
										<input type="text" class="yanzhengma" name="identify" id="identify" />&nbsp;
										<img src="" id="imgCode" onclick="changeCode()" />
										<a href="javascript:void(0)" onclick="changeCode()">刷新</a></td>
								</tr>
								
							</table>	
							</div>

							<div class="product_cont1_r5">
								<input type="checkbox" checked="checked" id="isagree" name="isagree"/>
								我同意《<a target="_blank" href="${ctx }/user/contracts?id=${model.id }"><c:if test="${model.type!='3'}">委托认购</c:if><c:if test="${model.type=='3'}">屌丝宝</c:if>理财产品协议</a>》
							</div>
							<div class="product_cont1_r6">
							<!-- <img src="${ctx }/images/product3.png" alt=""  name="submit" onclick="chk();"/>-->
							<c:choose>
							<c:when test="${release>0}">
							<input type="submit" name="submit" value="" style="width:150px;height:50px;background:Url(${ctx }/images/product3.png);border:0px;" onclick="chk()"/>
							</c:when>
							<c:otherwise>
								<input type="button" style="width:150px;height:50px;background:Url(${ctx }/images/muji.png);border:0px;" />
							</c:otherwise>
							</c:choose>
						    </div>
						  <div><span id="check_alert"  style="color:#e33340"></span></div>
						</div>
						</form>
					</div>
						</c:when>
						<c:when test="${sessionScope.user==null}">
							<div class="product_cont1_rd">
								<div class="font"><span>想参与投资吗？快登录吧！</span></div>
								<div class="img"><a href="${ctx }/dologin"><img src="${ctx }/images/pro1_login.png"/></a></div>
							</div>
                      	</c:when>
                      	<c:when test="${startTag==1}">
							<div class="product_cont1_rd">
								<div class="font"><span>产品预购中，请静心等待！</span></div>
								<div class="img2"><span>预购中</span></div>
							</div>
						</c:when>
						<c:when test="${percent==100&&model.state==0}">
							<div class="product_cont1_rd">
								<div class="font"><span>产品募集已满，下次不要犹豫！</span></div>
								<div class="img2"><span>募集已满</span></div>
							</div>
						</c:when>
						<c:when test="${dateTag!=1&&model.state==0}">
							<div class="product_cont1_rd">
								<div class="font"><span>产品募集截止，下次不要犹豫！</span></div>
								<div class="img2"><span>募集截止</span></div>
							</div>
                      	</c:when>
						<c:when test="${model.state==3}">
							<div class="product_cont1_rd">
								<div class="font"><span>产品成立中，下次不要犹豫！</span></div>
								<div class="img2"><span>成立中</span></div>
							</div>
						</c:when>
						<c:when test="${model.state==1}">
							<div class="product_cont1_rd">
								<div class="font"><span>产品还款中，下次不要犹豫！</span></div>
								<div class="img2"><span>还款中</span></div>
							</div>
						</c:when>
						<c:when test="${model.state==4}">
							<div class="product_cont1_rd">
								<div class="font"><span>产品已撤销，下次不要犹豫！</span></div>
								<div class="img2"><span>已撤销</span></div>
							</div>
						</c:when>
						<c:when test="${model.state==2}">
							<div class="product_cont1_rd">
								<div class="font"><span>产品已经完结，下次不要犹豫！</span></div>
								<div class="img2"><span>已经完结</span></div>
							</div>
						</c:when>
                      	</c:choose>
					</div>
				<div class="product_cont2">
					<div class="product_cont2_menu">

						<ul>
							<li onclick="changestyle(this.id)" id="td1" class="product_cont2_menu2"><a href="javascript:">项目介绍</a></li>
							<li onclick="changestyle(this.id)" id="td2" ><a href="javascript:">风控信息</a></li>
							<li onclick="changestyle(this.id)" id="td3"><a href="javascript:">相关资料</a></li>
							<li onclick="changestyle(this.id)" id="td4"><a href="javascript:">投资情况</a></li>
						</ul>
					</div>
					<div class="product_cont2_cont" id="product_cont2_content" style="word-wrap:break-word; word-break:break-all;"></div>
					
					<div class="product_cont2_nocont" id="product_cont2_cont_td1">			
						${fn:replace(model.content,"white-space: nowrap;", "")}
						<div style="clear:both;"></div>
					</div>
					<div class="product_cont2_nocont" id="product_cont2_cont_td2">
						${fn:replace(model.control_content,"white-space: nowrap;", "")}
						<div style="clear:both;"></div>
					</div>
					<div class="product_cont2_nocont" id="product_cont2_cont_td3">
						<c:forEach var="attr" items="${companyAttachment}">
						<h4>${attr.name }</h4>
						
							<script type="text/javascript">
								var imgUrl = "${attr.url}";
								var imgName ="${attr.names}"; 
								imgUrl = imgUrl.split(",");
								imgName = imgName.split(",");
								for(var i =0 ; i <　imgUrl.length - 1; i++){
									document.write("<div class='hetong'><img style='width:210px;height:130px;border:5px solid #fff' src='${picpath}/"+imgUrl[i]+"' onerror='changePic(this)' onclick='window.open(\"${picpath}/"+imgUrl[i]+"\")'/><div class='text'>"+imgName[i].substring(0,18)+(imgName[i].length>18?"..":"")+"</div></div>");
								}
							</script>
						
						<div style="clear:both"></div>
						</c:forEach>
					</div>
					
					<div class="product_cont2_nocont" id="product_cont2_cont_td4">
                    <table class="product_table" cellspacing="0" id="tenderList">
						<tr>	
							<th>投资人</th>
							<th>投资金额</th>
							<th>状态</th>
							<th>投标时间</th>
						</tr>
							<!-- 循环开始-->
					<!-- 循环结束-->
					</table>														
							<div class="user_cont_r_2d">
								<span id="moreList" onclick="turnPage()">查看更多</span>
							</div>
				            <div style="clear:both;"></div>
					</div>
				</div>
			</div>
		</div>

	<!-- 单个产品信息结束 -->
    
    
    
     <!-- 弹出层的内容-->                         
	<div class="theme-popover">
		<div class="theme-poptit">
			<h3>请选择要使用的红包</h3>
			<a href="javascript:;" title="关闭" class="close">[关闭]</a>
		</div>     
		<div class="pop_middle">
			<span>
				1.该产品可投资使用的红包比例为<cc:toMoney var = "${model.hongbaorate }"/>%
			</span><br/>
			<span id="moneyLimit">
				2.您投资的金额不高于，暂时没有可使用的红包
			</span>
			<table cellspacing=0 border="0" id="luckyM">
				<tr class="tittle">
					<th>金额（元）</th>
					<th>有效期</th>
					<th>选择</th>
				</tr>
				<!-- 循环开始-->
				<!-- 循环结束-->
			</table>
			<div>
				<img class="quxiao" onclick="cancel()" src="${ctx }/images/product12.png"/>
				<img class="queren" onclick="ensure()" src="${ctx }/images/product11.png"/>
			</div>
			<div style="clear:both"></div>
		</div>
	</div>
	<div class="theme-popover-mask"></div>
	<!--弹出层结束-->
	<!--弹出层结束-->
     <!-- 投资24万元提示弹出层的内容-->                         
                            <div class="theme-popover1">
                                 <div class="theme-poptit1">

                                      <h3>风险提示书</h3>
                               <a href="javascript:;" class="close1">[关闭]</a>
                                 </div>  
                                 <div style="clear:both"></div>   
                                 <div class="pop_middle1">
                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亲爱的<span>${sessionScope.user.username }</span>,您在本产品投资金额已累计超过<span style="color:#e33340;">24万元</span>，金额巨大，为了让您的投资更加安心省心，我们将为您推荐私属经纪人服务，为您提供最贴心到位的服务。如想了解详情，请联系我们<span style="color:#0060a5;text-decoration:underline;">4000-57-3090</span>。
                                   		<div class="pop_middle1_1">
                                               已阅<a target="_blank" href="${ctx }/user/danger">《风险投资书》</a>
                                         </div>
                                         <div class="pop_middle1_2">
                                           <input class="touzi2" type="button" value="继续投资" onclick="checkProtocol24()"/>
                                           <input class="quxiao2" type="button" value="取消投资" onclick="hideProtocol24()"/>
                                         </div>
                                 </div>
                               
                            </div>
                            
  <div class="theme-popover-mask1"></div>
<!--投资提示弹出层结束-->
  <!-- 投资6万元提示弹出层的内容-->   
<div class="theme-popover3">
                                 <div class="theme-poptit1">

                                      <h3>风险提示书</h3>
                               <a href="javascript:;" class="close1">[关闭]</a>
                                 </div>  
                                 <div style="clear:both"></div>   
                                 <div class="pop_middle1">
                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亲爱的<span>${sessionScope.user.username }</span>,您在本产品投资金额已累计超过<span style="color:#e33340;">6万元</span>，金额巨大，为了让您更好的分摊投资风险，建议您通过投资合理投资配置向本平台其他产品进行投资，并能获得更好的安全性更好的收益。
                                   		<div class="pop_middle1_1">
                                              已阅<a target="_blank" href="${ctx }/user/danger">《风险投资书》</a>
                                         </div>
                                         <div class="pop_middle1_2">
                                           <input class="touzi2" type="button" value="继续投资" onclick="checkProtocol6()"/>
                                           <input class="quxiao2" type="button" value="取消投资" onclick="hideProtocol6()"/>
                                         </div>
                                 </div>
                               
                            </div>
                            
  <div class="theme-popover-mask1"></div>
	<script type="text/javascript">
	function clearhongbao(){
		$("#luckyId").val("");
	}

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
						html+="<td>"+list[i][2]+"元</td>";
						html+="<td>成功</td>";
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
		function showLuckyMoney(){
			if(!/^\d+$/.test($("#transAmt").val())){
				$("#transAmt").blur();
				$("#transAmt").val("");
				$("#transAmt").focus();
			}else{
				$.post("${ctx}/project/luckyMoneyList",{transAmt:$("#transAmt").val(),type:${model.type},hongbaorate:${model.hongbaorate}},function(data){
					$(".xunhuan").each(function(index,item){$(item).remove()})
					$("#moneyLimit").show();
					if(!isNaN(data)){
						var moneyList = data/${model.hongbaorate}
						$("#moneyLimit").html("2.您投资的金额不高于"+data/${model.hongbaorate}*100+"，暂时没有可使用的红包 ");
					}else{
						if(data=="noLucky"){
							$("#moneyLimit").html("暂无可使用红包");
						}else{
							var obj = eval("("+data+")");
							var html="";
							if(obj==null||obj.length==0){
							 html+="<tr class='xunhuan'>";
							 html+="<td cols='4'>无可用红包</td>";
							 html+="</tr>";
							}else{
								for(var i=0;i<obj.length;i++){
								html+="<tr class='xunhuan'>";
								html+="<td>"+obj[i][1]+"</td>";
								//html+="<td>"+obj[i][4]+"</td>";
								html+="<td>"+obj[i][2].substring(0,10)+" 至 "+obj[i][3].substring(0,10)+"</td>";
								html+="<td><input type='radio' name='luckyRadio' value='"+obj[i][0]+','+obj[i][1];
								if($("#luckyId").val()==''&i==0){
								  html+="' checked='checked";
								}
								if(obj[i][0]==$("#luckyId").val()){
									html+="' checked='checked";
								}
								html+="'/></td>";
								html+="</tr>";
							}
							}
							$("#luckyM").append($(html));
							$("#moneyLimit").hide();
							}
						//zhangwin_play();
					}
				});
			}
		}
		function ensure(){
			$("#luckyId").val($("input[name='luckyRadio']:checked").val());
			$('.theme-popover-mask').css("display","none");
			$('.theme-popover').slideUp(200);
             var mon=$("#luckyId").val();
             var _mon="${luckyCount}";             
             if(mon!=null){
             if(mon.indexOf(",")>-1){
               mon=mon.substring(mon.indexOf(",")+1);
             }else{
               mon=null;
             }             
                if(mon!=null){
                $("#luckyspan").html("使用<span style='color:#e33340'>"+mon+"</span>元红包");
                }else{
                 $("#luckyspan").html("<span style='color:#e33340'>"+_mon+"</span>个");
                }
             }else{
              $("#luckyspan").html("<span style='color:#e33340'>"+_mon+"</span>个");
             }
		
		}
		function cancel(){
			var _mon="${luckyCount}";
		    $("#luckyspan").html("<span style='color:#e33340'>"+_mon+"</span>个");
			$('.theme-popover-mask').css("display","none");
			$('.theme-popover').slideUp(200);
		
		}
		function remindShow(divId,content){
			$("#remindDiv").css("top",($("#"+divId).offset().top-33));
			$("#remindDiv").css("left",($("#"+divId).offset().left));
			$("#remindDiv").html(content);
			$("#remindDiv").show();
		}
		function remindHide(){
			$("#remindDiv").fadeOut(3000);
		}
		$(document).ready(function(){
			$("#transAmt").focus(function(){
				if($(this).val()=="请输入投资金额"){
					$(this).val("");
				}
			});
		});
		function totalshouyi_(money){
			var rate="${model.rate }";
			var timelimit="${model.time_limit }";
			var shouyi=rate/100;
			var totalshouyi=shouyi*(timelimit/12);
			totalshouyi=totalshouyi*money;
			totalshouyi = totalshouyi+"";
			if(totalshouyi.indexOf(".")!=-1)
				totalshouyi = totalshouyi.indexOf(".")<(totalshouyi.length-3)?totalshouyi.substring(0,totalshouyi.length-(totalshouyi.length-2-totalshouyi.indexOf("."))+1):totalshouyi;
			remindShow("transAmt","预期收益"+totalshouyi+"元");
		}
		$("#transAmt").blur(function(){
			if($(this).val()==""){
				$(this).val("请输入投资金额");
			}else{
				$(this).val($(this).val().replace(/(^\s*)|(\s*$)/g, ""));
				var highest_money="${high }";
				var lowest_money="${low }";
				var self_money="${self_money}";
				//先判断是否是数字
				if(/^[0-9]+$/.test($(this).val())){
					//再判断数字是否符合投资金额区间
					var money=parseInt($(this).val());
					var highest=parseInt(highest_money);
					var lowest=parseInt(lowest_money);
					if(lowest<=money&money<=highest){
						moneyflag=true;
						totalshouyi_(money);
					}else{
						moneyflag=false;
						remindShow("transAmt","请填写最低投资额于最高投资额之间的数字");
					    $("#remindDiv").hide();
					}
					if(money>highest){
					    moneyflag=false;
						remindShow("transAmt","投资金额超出上限");
					}		
					if(money>self_money){
						moneyflag=false;
						remindShow("transAmt","您的金额不足");
					}
				}else{
					remindShow("transAmt","请正确填写投资金额");
				}
				remindHide();
			}
		});
		$("#transAmt").keyup(function(){
			if($(this).val()==""){
				$(this).val("请输入投资金额");
			}else{
				$(this).val($(this).val().replace(/(^\s*)|(\s*$)/g, ""));
				var highest_money="${high }";
				var self_money="${self_money}";
				var lowest_money="${low }";
				//先判断是否是数字
				if(/^[0-9]+$/.test($(this).val())){
				//再判断数字是否符合投资金额区间
					var money=parseInt($(this).val());
					var highest=parseInt(highest_money);
					var lowest=parseInt(lowest_money);
					if(lowest<=money&money<=highest){
						moneyflag=true;
						totalshouyi_(money);
					}else{
						moneyflag=false;
						remindShow("transAmt","请填写最低投资额于最高投资额之间的数字");					
					} 
					if(money>highest){
					    moneyflag=false;
						remindShow("transAmt","投资金额超出上限");
					}		
					if(money>self_money){
						moneyflag=false;
						remindShow("transAmt","您的金额不足");
					}

					
				}
			}
		});
		$("#identify").keyup(function(){
		var idetityV=$("#identify").val();
		if(idetityV==""||/^\w{1,3}$/.test(idetityV)){
		return;
		}
			var touziidentify;
			jQuery.ajax({type:"POST", url:basepath + "/getsessionattr", data:"attrname=touziidentify", success:function (result) {
					touziidentify=result;
					if($("#identify").val()==touziidentify){
						identityflag=true;
					}else{
						remindShow("identify","验证码填写错误");
						remindHide();
					}
				}
			});
		});
		
		function beforeSend(){
			$("#transAmt").blur();
			$("#identify").blur();

			    //先判断是否是正整数数字	
				if(/^[0-9]*[1-9][0-9]*$/.test($("#transAmt").val())){

				}else{
				$("#check_alert").html("请填写整数投资金额");			
				return false;
				}
			if(moneyflag==false){
				//alert("请正确填写投资金额");
				$("#check_alert").html("请正确填写投资金额");			
				return false;
			}
			if(identityflag ==false){
				//alert("验证码填写错误");
					$("#check_alert").html("验证码填写错误");		
				return false;
			}
			if(isallow ==false){
				//alert("您未接受《一起一起投投资协议》");
					$("#check_alert").html("您未接受投资协议");		
				return false;
			}
			var checkId = parseInt($("#transAmt").val())>=240000?"24":parseInt($("#transAmt").val())>=60000?"6":"0";
		  	if(checkId == "24"){
			  	if(protocolTag24 ==false){
			  				$("#check_alert").html("您未接受风险提示");	
					//alert("您未接受弹出协议");
					return false;
				}
			}
		  	if(checkId == "6"){
			  	if(protocolTag6 ==false){
			      	$("#check_alert").html("您未接受风险提示");	
					//alert("您未接受弹出协议");
					return false;
				}
			}
			$("#check_alert").html("");
			return true;
		}
		function changePic(obj){
			var src= $(obj).attr("src") ;
			src = src.substring(src.lastIndexOf(".")+1);
			if(src.length>3)
				src = src.substring(0,3);
			if(src=="doc"||src=="DOC"){
				$(obj).attr("src","${ctx}/images/word.png")
			}else if (src=="pdf"||src=="PDF"){
				$(obj).attr("src","${ctx}/images/pdf.png")
			}else{
				$(obj).attr("src","${ctx}/images/x.png")
			}
		}
	</script>	
	<div id="remindDiv" style="background:#aaa;display:none;padding:7px 14px;position:absolute;height:15px;width:auto;top:0px;left:0px;">test</div>

	<a class="theme-login1"></a>
	<a class="theme-login3"></a>
	</div>
	<%@ include file="../common/foot.jsp"%>
</body>
</html>