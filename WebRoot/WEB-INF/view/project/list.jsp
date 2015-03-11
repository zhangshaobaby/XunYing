<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品列表_领跑互联网理财，安全诚信,严格风控,互联网投资新选择。这是让您放心的互联网金融平台</title>
<meta name="keywords" content="产品列表，金多宝，资涨通，信满盈，屌丝宝，安全投资，高收益投资，互联网信托，资管，有限合伙"/>
<meta name="description" content="产品列表里，您可以体验到一起一起投金多宝，资涨通，信满盈，屌丝宝四大产品，让您在专业权威互联网理财平台享受安心互联网投资理财服务，多种低门槛，高收益，灵活高效的互联网金融产品，给你更多的选择。众多权威专业合作机构在产品还是安全收益，都能给更高的保障。" />
<meta name="author" content="北京凌云吉信信息技术有限公司" />
<link href="${ctx }/css/product_list.css" rel="stylesheet" type="text/css" />
<title>1717tou.com</title>
<script src="${ctx }/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx }/js/base.js" type="text/javascript"></script>
<script type="text/javascript">

var environment = {
	userVo : '',
	role : '',
	userId : '',
	basePath : '',
	userCash : '',
	globalPath : 'localhosot/1717tou'
};
</script>
<!--[if lt IE 9]>
<script src="${ctx }/js/html5.js" type="text/javascript"></script>
<script src="${ctx }/js/excanvas.js" type="text/javascript"></script>
<script src="${ctx }/js/excanvas.compiled.js" type="text/javascript"></script>
<![endif]-->
<script src="${ctx }/js/jquery.knob.js"></script>
<script src="${ctx }/js/myknob.js"></script>
<script src="${ctx }/js/global-1.1.0.min.js" type="text/javascript"></script>
<script src="${ctx }/js/home.js" type="text/javascript"></script>
<script>
/*控制菜单项屌丝宝的样式*/
$(function(){
  $(window).on('load', function () {
                $('.knob').knob();
                $(".percentText").css("margin-top","-50px");
            });

	})
</script>



<script type="text/javascript">
        var NowTime = new Date("${nowDate}");
		function timer(seconddiff,endtime,elid){
		var EndTime = new Date(endtime);
	    var intDiff= (EndTime.getTime() - NowTime.getTime())/1000;
	    intDiff=intDiff-seconddiff;
				var day=0,
					hour=0,
					minute=0,
					second=0;//时间默认值		
				if(intDiff > 0){
					day = Math.floor(intDiff / (60 * 60 * 24));
					hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
					minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
					second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
					if (hour <= 9) hour = '0' + hour;
				if (minute <= 9) minute = '0' + minute;
				if (second <= 9) second = '0' + second;
					$("#"+elid).html("还剩"+day+"天"+hour+"时"+minute+"分"+second+"秒结束");
					seconddiff++;
				   setTimeout("timer('"   +   seconddiff   +   "','"   +   endtime   +   "','"   +   elid   +   "')",   1000); 
				}else{
					$("#"+elid).html("已经结束");			
				}
		}
	</script>
<script type="text/javascript">
   $(document).ready(function(){
  var listjson='${listjson}';
  var list=eval(listjson);
  $(list).each(function(i,item){
  if(item.percent!=100&&item.state==0&&item.rate_type==1&&item.diffnow==-1){
   timer(0,item.end_time.replace(/-/g,"/"),"daojishi"+item.id);
  }
 });
 })
</script>
</head>

<body>
	
	<!-- 信息列表开始 -->
<%@ include file="../common/head_html.jsp"%>
<div class="allproduct"> 
    <div class="allproduct_dh"><a href="${ctx }/index">首页</a> >产品列表><span style="color:#e33340"> <c:choose> <c:when test="${project==null||project.type==null||project.type==0}">
								信满盈
								  </c:when>
								    <c:when test="${project.type==2}">
								  金多宝
								  </c:when>
								    <c:when test="${project.type==1}">
								 资涨通
								  </c:when>
								    <c:when test="${project.type==3}">
								  屌丝宝
								  </c:when>
								  </c:choose></span></div>
     <!-- tab轮换-->
		<div class="product">
			<div class="product_cont">				
				<div class="product_cont2">
					<div class="product_cont2_menu">
						<ul>
							<li onclick="clickType('0')"  id="td0"><a href="javascript:"><img class="img1" src="${ctx }/images/indexgai3.png" /></a></li>
							<li onclick="clickType('2')"  id="td2"><a href="javascript:"><img class="img2" src="${ctx }/images/indexgai4.png" /></a></li>
							<li onclick="clickType('1')"  id="td1"><a href="javascript:"><img class="img3"src="${ctx }/images/indexgai5.png" /></a></li>
                            <li onclick="clickType('3')"  id="td3"><a href="javascript:"><img class="img4"src="${ctx }/images/indexgai5_2.png" /></a></li>
                            <div style="clear:both;"></div>
						</ul>
                       
					</div>
					<div class="product_cont2_cont" id="product_cont2_content"></div>
				
				
					<c:choose>
				<c:when test="${project.type!=null&&project.type==0}">
				<!--	信 满盈tab开始-->
				      <div class="product_cont2_nocont_a1">
                         <span style="margin-left:30px;">更高安全、更高收益的“无忧理财”</span><span></span>
                        <div style="clear:both;"></div>
                        </div>
				</c:when>
				
				<c:when test="${project.type!=null&&project.type==2}">
					<!--	金多宝tab开始-->
				<div class="jin_product_cont2_nocont_a1">
                         <span style="margin-left:30px;">强化风控，更高一级的投资决策选择</span><span></span>
                        </div>
				</c:when>
					
				<c:when test="${project.type!=null&&project.type==1}">
				<!--	资涨通tab开始-->
				       <div class="zi_product_cont2_nocont_a1 ">
                         <span style="margin-left:30px;">投资多样化，更多的选择</span><span></span>
                        </div>
				</c:when>
				
				<c:otherwise>
				<!--	屌丝宝tab开始-->
					 <div class="diao_product_cont2_nocont_a1 ">
                         <span style="margin-left:30px;">预计到期收益 8%-11%；开放赎回，安全放心；限量发行，先到先得</span>
                        </div>
				</c:otherwise>
					</c:choose>
						<div class="list1">
                        	<form action="${ctx }/project/list" method="post">
							<input type="hidden" id="type" name="type" value="${project.type}" />
								<table class="check">
									<tr>
										<td class="check1">
											项目状态
											<select class="state" name="state">
												<option value="">
													全部
												</option>
												<option value="-3" <c:if test="${project.state==-3}">selected</c:if>>
													预发布
												</option>
												<option value="0" <c:if test="${project.state==0}">selected</c:if>>
													可投资
												</option>
												<option value="1" <c:if test="${project.state==1}">selected</c:if>>
													还款中
												</option>
												<option value="2" <c:if test="${project.state==2}">selected</c:if>>
													已还款
												</option>
											</select>
										</td>
										<td class="check1">
											项目期限
											<select class="time_limit" name="time_limit">
												<option value="">
													全部
												</option>
												<option value="1" <c:if 

test="${project.time_limit==1}">selected</c:if>>
													3个月以下
												</option>
												<option value="2" <c:if 

test="${project.time_limit==2}">selected</c:if>>
													3-6个月
												</option>
												<option value="3" <c:if 

test="${project.time_limit==3}">selected</c:if>>
													6-12个月
												</option>
												<option value="4" <c:if 

test="${project.time_limit==4}">selected</c:if>>
													12个月以上
												</option>
											</select>
										</td>
										<td class="check1">
											项目收益
											<select class="rate" name="rate">
												<option value="" >
													全部
												</option>
												<option value="1" <c:if test="${project.rate==1}">selected</c:if>>
													10%以下
												</option>
												<option value="2" <c:if test="${project.rate==2}">selected</c:if>>
													10%-15%
												</option>
												<option value="3" <c:if test="${project.rate==3}">selected</c:if>>
													15%-20%
												</option>
											</select>
										</td>
										<td class="check1">
											项目规模
											<select class="total_money" name="total_money">
												<option  value="">
													全部
												</option>
												<option value="1" <c:if 

test="${project.total_money==1}">selected</c:if>>
													200万以下
												</option>
												<option value="2" <c:if 

test="${project.total_money==2}">selected</c:if>>
													200万-500万
												</option>
												<option value="3" <c:if 

test="${project.total_money==3}">selected</c:if>>
													500万-1000万
												</option>
												<option value="4" <c:if 

test="${project.total_money==4}">selected</c:if>>
													1000万以上
												</option>
											</select>
										</td>
										<td class="check1">
											<img onclick="document.forms[0].submit();"
												src="${ctx }/images/product_list8.png" />
										</td>
									</tr>
								</table>
							</form>
                    
					<c:choose>
						<c:when test="${project.type!=null&&project.type==3}">		
							  <!-- 屌丝宝循环开始-->
							   <c:forEach var="item" items="${page.result}" varStatus="i">
				            <div class="list1_bao">
								  <div class="tit"><a  title="${item.name}" href="${ctx }/project/view?id=${item.id }"><c:choose><c:when 
test="${item.name==null||item.name==''}">
									暂无标题
									</c:when>
									<c:when test="${fn:length(item.name)>14}">
									${fn:substring(item.name,0,14)}..
									</c:when>
									<c:otherwise>
									${item.name}
									</c:otherwise>
									</c:choose>
									</a></div>
								  <div class="con2">
								  	  <c:choose>
								  <c:when test="${item.type==0}">
								  <input class="knob" data-height="74" data-width="74" data-fgColor="#ea4c59" data-thickness=".15" data-readOnly=true value="${item.percent}" data-min="0" data-max="100.01" style="display:none; width:0px;visibility:hidden;"/>
								  </c:when>
								    <c:when test="${item.type==1}">
								  <input class="knob" data-height="74" data-width="74" data-fgColor="#eb7c23" data-thickness=".15" data-readOnly=true value="${item.percent}" data-min="0" data-max="100.01" style="display:none; width:0px;visibility:hidden;"/>
								  </c:when>
								    <c:when test="${item.type==2}">
								  <input class="knob" data-height="74" data-width="74" data-fgColor="#319DD0" data-thickness=".15" data-readOnly=true value="${item.percent}" data-min="0" data-max="100.01" style="display:none; width:0px;visibility:hidden;"/>
								  </c:when>
								    <c:when test="${item.type==3}">
								  <input class="knob" data-height="74" data-width="74" data-fgColor="#eec433" data-thickness=".15" data-readOnly=true value="${item.percent}" data-min="0" data-max="100.01" style="display:none; width:0px;visibility:hidden;"/>
								  </c:when>
								  </c:choose>
								  	
									<div class="percentText">${item.percent}%</div>
								  </div>
                                   <div class="con3">
								  	<table cellspacing="0">
                                    <tr><td>预计年化率</td><td>项目期限</td><td>项目金额</td></tr>
                                    <tr><td><span style="color:#e33340;">${item.rate}</span></td><td><span style="color:#0060a5;">${item.time_limit}个月

</span></td><td><span style="color:#e33340;"><cc:formatToTenThousand 
value="${item.total_money}"/>万元</span></td></tr>
                                    </table>
								  </div>
                                   <div class="con4">   <a href="${ctx }/project/view?id=${item.id }">   <c:choose>
									   		<c:when test="${item.diffnow==1}">
												<img src="${ctx }/images/listp2_${item.type}.png" alt=""/>
									   		</c:when>
											<c:when test="${item.percent!=100&&item.state==0&&item.rate_type==1}">
												<img src="${ctx }/images/listp1_${item.type}.png" alt=""/>
											</c:when>
											<c:when test="${item.percent==100&&item.state==0}">
												<img src="${ctx }/images/p4.png" alt=""/>
											</c:when>
											<c:when test="${item.state==0&&item.rate_type!=1}">
												<img src="${ctx }/images/p5.png" alt=""/>
											</c:when>
											<c:when test="${item.state==3}">
												<img src="${ctx }/images/p3.png" alt=""/>
											</c:when>
											<c:when test="${item.state==1}">
												<img src="${ctx }/images/p7.png" alt=""/>
											</c:when>
											<c:when test="${item.state==4}">
												<img src="${ctx }/images/p6.png" alt=""/>
											</c:when>
											<c:when test="${item.state==2}">
												<img src="${ctx }/images/p8.png" alt=""/>
											</c:when>
										</c:choose>
									</a>
									<span id="daojishi${item.id}">
									<c:if test="${item.diffnow==1||(item.percent!=100&&item.state==0&&item.rate_type==1)}">
								  	${item.start_time }开售
								  	</c:if></span></div>
							</div>
							<c:if test="${(i.index+1)%4!=0}">
							<div class="line_y1" ></div>
						    </c:if>
						    <c:if test="${(i.index+1)%4==0&&i.index!=7}">
                            <div class="line_x"></div><div class="line_x"></div><div class="line_x"></div><div class="line_x"></div>
                    </c:if>
                            </c:forEach>
                             <!-- 循环结束-->
                            </c:when>
                           
								<c:otherwise>
							  <!--信托产品 循环开始-->
                       <c:forEach var="item" items="${page.result}" varStatus="i" >
							<div class="list1_1">
								  <div class="top"><img style="width:21px ;height:21px" src="<cc:picHost/>/${item.comicon}" alt="" 
onerror="this.src='${ctx }/images/product1.png'"  />
										${item.comname}|
										${item.repay_content }
										</div>
								  <h3 style="word-wrap:break-word; word-break:break-all;">
										<a  title="${item.name}" href="${ctx }/project/view?id=${item.id }"><c:choose><c:when 
test="${item.name==null||item.name==''}">
									暂无标题
									</c:when>
									<c:when test="${fn:length(item.name)>14}">
									${fn:substring(item.name,0,14)}..
									</c:when>
									<c:otherwise>
									${item.name}
									</c:otherwise>
									</c:choose>
									</a>
									</h3>
								  <div class="con1" style="word-wrap:break-word; word-break:break-all;">
	                       <c:choose>
									<c:when test="${item.summary==null||item.summary==''}">
									暂无简介
									</c:when>
									<c:when test="${fn:length(item.summary)>62}">
									${fn:substring(item.summary,0,62)}..
									</c:when>
									<c:otherwise>
									${item.summary}
									</c:otherwise>
									</c:choose></div>
								  <div class="con2">
								  <c:choose>
								  <c:when test="${item.type==0}">
								  <input class="knob" data-height="74" data-width="74" data-fgColor="#ea4c59" data-thickness=".15" data-readOnly=true value="${item.percent}" data-min="0" data-max="100.01" style="display:none; width:0px;visibility:hidden;"/>
								  </c:when>
								    <c:when test="${item.type==1}">
								  <input class="knob" data-height="74" data-width="74" data-fgColor="#319DD0" data-thickness=".15" data-readOnly=true value="${item.percent}" data-min="0" data-max="100.01" style="display:none; width:0px;visibility:hidden;"/>
								  </c:when>
								    <c:when test="${item.type==2}">
								  <input class="knob" data-height="74" data-width="74" data-fgColor="#eb7c23" data-thickness=".15" data-readOnly=true value="${item.percent}" data-min="0" data-max="100.01" style="display:none; width:0px;visibility:hidden;"/>
								  </c:when>
								    <c:when test="${item.type==3}">
								  <input class="knob" data-height="74" data-width="74" data-fgColor="#eec433" data-thickness=".15" data-readOnly=true value="${item.percent}" data-min="0" data-max="100.01" style="display:none; width:0px;visibility:hidden;"/>
								  </c:when>
								  </c:choose>
								  	
									<div class="percentText">${item.percent}%</div>
								  </div>
                                   <div class="con3">
								  	<table cellspacing="0">
                                    <tr><td>预计年化率</td><td>项目期限</td><td>项目金额</td></tr>
                                    <tr><td><span style="color:#e33340;">${item.rate}</span></td><td><span style="color:#0060a5;">${item.time_limit}个月</span></td><td><span style="color:#e33340;"><cc:formatToTenThousand 
value="${item.total_money}"/>万元</span></td></tr>
                                    </table>
								  </div>
                                   <div class="con4">
                                   <a href="${ctx }/project/view?id=${item.id }">   <c:choose>
									   		<c:when test="${item.diffnow==1}">
												<img src="${ctx }/images/listp2_${item.type}.png" alt=""/>
									   		</c:when>
											<c:when test="${item.percent!=100&&item.state==0&&item.rate_type==1}">
												<img src="${ctx }/images/listp1_${item.type}.png" alt=""/>
											</c:when>
											<c:when test="${item.percent==100&&item.state==0&&item.rate_type==1}">
												<img src="${ctx }/images/p4.png" alt=""/>
											</c:when>
											<c:when test="${item.state==0&&item.rate_type!=1}">
												<img src="${ctx }/images/p5.png" alt=""/>
											</c:when>
											<c:when test="${item.state==3}">
												<img src="${ctx }/images/p3.png" alt=""/>
											</c:when>
											<c:when test="${item.state==1}">
												<img src="${ctx }/images/p7.png" alt=""/>
											</c:when>
											<c:when test="${item.state==4}">
												<img src="${ctx }/images/p6.png" alt=""/>
											</c:when>
											<c:when test="${item.state==2}">
												<img src="${ctx }/images/p8.png" alt=""/>
											</c:when>
										</c:choose>
									</a>
									<span id="daojishi${item.id}">
									<c:if test="${item.diffnow==1||(item.percent!=100&&item.state==0&&item.rate_type==1)}">
								  	${item.start_time }开售
								  	</c:if></span></div>
							</div>
							<c:if test="${(i.index+1)%4!=0}">
                            <div class="line_y"></div>
                    </c:if>
                          <!-- 第二行的虚横线-->
                         		<c:if test="${(i.index+1)%4==0&&i.index!=7}">
                            <div class="line_x"></div><div class="line_x"></div><div class="line_x"></div><div class="line_x"></div>
                    </c:if>
                           
                        </c:forEach>
                          <!-- 循环结束-->
							</c:otherwise>
						
							</c:choose>
							<div style="clear:both"></div>	
                            <div class="user_cont_r_2d">
							<jsp:include page="../common/page.jsp"></jsp:include> 
							</div>
                            <div style="clear:both"></div>	
					    </div>
					</div>
                 	
				</div>
				
			</div>
		</div>
		<jsp:include page="../common/foot.jsp"></jsp:include>
		 <!-- tab轮换结束-->
    <script type="text/javascript">
	function changestyle(id){
		var cur_menu=document.getElementById(id);
		var con=document.getElementById("product_cont2_content");
		cur_menu.className="product_cont2_menu2";
		for(i=0; i<=3; i++){
			if(String("td"+i)!=id)	document.getElementById(String("td"+i)).className="product_cont2_menu1";	
		}
		//至此是更改菜单的样式;		
	}
		function clickType(id){
	$("#type").val(id);
	document.forms[0].submit();
	}
</script>
<script type="text/javascript">
var type="${project.type}";
if(type==null||type==""){
type=0;
}
	if(type==0){
	$(".img1").css("z-index","4");
	$(".img2").css("z-index","3");
	$(".img3").css("z-index","2");
	$(".img4").css("z-index","1");
	}
	if(type==2){
	$(".img1").css("z-index","1");
	$(".img2").css("z-index","4");
	$(".img3").css("z-index","3");
	$(".img4").css("z-index","2");
	}
	if(type==1){	
	$(".img1").css("z-index","1");
	$(".img2").css("z-index","2");
	$(".img3").css("z-index","4");
	$(".img4").css("z-index","3");
	}
	if(type==3){	
	$(".img1").css("z-index","1");
	$(".img2").css("z-index","2");
	$(".img3").css("z-index","3");
	$(".img4").css("z-index","4");
	}
//changestyle("td"+type);
</script>
</body>
</html>

