<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="网站描述" />
		<meta name="keywords" content="关键字" />
		<link href="${ctx }/css/user_my_touzixiang.css" rel="stylesheet" type="text/css" />
	
	
		<script src="${ctx }/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<title>1717tou.com</title>
		<script type="text/javascript">
		var page = 0;
		var total = ${fn:length(recordList)};
		total = total%4==0?total/4:((total/4)+1);
		total = parseInt(total);
		var page2 = 0;
		var total2 = ${fn:length(newsList)};
		total2 = total2%4==0?total2/4:((total2/4)+1);
		total2 = parseInt(total2);
		$(document).ready(function(){
			_turnPage(page);
			_turnPage2(page2);
		});
		//假分页
		function _turnPage(){
			var id = 0;
			if(page<0)page=0;
			if(page>=total)page=total-1;
			$(".user_cont_r_3_b2").each(function(index,item){
				//plan_0
				if(index >= page * 4 && index < (page+1) * 4){
					$(item).show()
				}else{
					$(item).hide();
				}
			});
			$("#nowPage").html((page+1)+"/"+total+"页");
		}
		//假分页
		function _turnPage2(){
			var id = 0;
			if(page2<0)page2=0;
			if(page2>=total2)page2=total2-1;
			$(".user_cont_r_3_bbb2").each(function(index,item){
				//plan_0
				if(index >= page2 * 4 && index < (page2+1) * 4){
					$(item).show()
				}else{
					$(item).hide();
				}
			});
			$("#nowPage2").html((page2+1)+"/"+total2+"页");
		}
		$(function(){
			$(".li_1").click(function() {
				$(this).addClass("sel").siblings().removeClass("sel");
				$(".user_cont_r_31").css("display","block");
				$(".user_cont_r_3").css("display","none");
			});
			$(".li_2").click(function() {
				$(this).addClass("sel").siblings().removeClass("sel");
				$(".user_cont_r_3").css("display","block");
				$(".user_cont_r_31").css("display","none");
			});
		});
		</script>
	</head>
	<body>

				<div class="user_cont_rr" style="background:#FFFFFF;">
                  <div class="user_cont_rrr">
					    <div class="user_cont_r_1"><span>资产详情</span></div>
						<div class="user_cont_r_2"> 
                         <div class="user_cont_r_2a">
                         	 <div class="user_cont_r_2al">
                             	<h3><cc:subLength var = "${model.name }" length = "20"/></h3>
                             	<table class="table1" cellspacing=0 border="0">
                                    <tr>
                                        <td class="table1l">预期年化率</td>
                                        <td class="table1r"><span>${model.rate }%</span></td>
                                    </tr>
                                    <tr>
                                        <td class="table1l">投资期限</td>
                                        <td class="table1r"><span>${model.time_limit }<c:if test="${model.delay_time_limit!=null&&model.delay_time_limit!=0 }">+${model.delay_time_limit }</c:if></span>月募集</td>
                                    </tr>
                                    <tr>
                                        <td class="table1l">募集金额</td>
                                        <td class="table1r"><span><c:choose>
								  		<c:when test="${model.total_money/10000==0}">								  											  		
			                              <span>${model.total_money==null?0:model.total_money}</span>元
								  		</c:when>
								  		<c:otherwise>
								  		  <span>${model.total_money/10000 }</span>万元
								  		</c:otherwise>
								  	</c:choose></span></td>
                                    </tr>
                                    <tr>
                                        <td class="table1l">收益方式</td>
                                        <td class="table1r"><span>${model.repay_content}</span></td>
                                    </tr>
                                    <tr>
                                        <td class="table1l">产品类型</td>
                                        <td class="table1r">
											<c:choose>
												<c:when test="${model.type==0}">信托(信满盈)</c:when>
												<c:when test="${model.type==1}">资管(资涨通)</c:when>
												<c:when test="${model.type==2}">基金(金多宝)</c:when>
												<c:when test="${model.type==3}">屌丝宝</c:when>
												<c:otherwise>unknown</c:otherwise>
											</c:choose>
										</td>
                                    </tr>
                                    <tr>
                                        <td class="table1l">目前投资人数</td>
                                        <td class="table1r"><span>${model.pay_number }</span>人</td>
                                    </tr>
									<tr>
                                        <td class="table1l">成立时间</td>
                                        <td class="table1r"><span>${model.start_time }</span></td>
                                    </tr>
                                </table>
                             </div>
                              <div class="user_cont_r_2ar">
                               <div class="user_cout_r_2ar1">
                               		<c:choose>
										<c:when test="${model.state==0}"><img src="${ctx }/images/user_touzi4.png" /></c:when>
										<c:when test="${model.state==1}"><img src="${ctx }/images/user_touzi2.png" /></c:when>
										<c:when test="${model.state==2}"><img src="${ctx }/images/user_touzi3.png" /></c:when>
										<c:when test="${model.state==3}"><img src="${ctx }/images/user_touzi1.png" /></c:when>
									</c:choose>
                               </div>
                               <div class="user_cout_r_2ar2">您的投资金额<span style="color:#e33340;margin-left:10px;">${sum==null?0:sum }元</span></div>
                               <div class="user_cout_r_2ar3">当前收款金额<span style="color:#e33340;margin-left:10px;">${repaySum }元</span></div>
                               <div class="user_cout_r_2ar4">
                               		<c:if test="${recordList[fn:length(recordList)-1].updateTime!=null }">
                               		(信息更新日期：
                               		<span>${recordList[fn:length(recordList)-1].updateTime }</span>
                               		)
                               		</c:if>
                               	</div>
                               </div>
                           </div>
                           <div class="user_cont_r_2a1">
                               <ul>
                                    <li class="li_1 sel"><a href="javascript:void(0);">还款记录</a></li>
                                    <li class="li_2"><a href="javascript:void(0);">相关公告</a></li>													
                                </ul>
                            </div>
                           
                            
						  </div> 
                          
                        
                    <div class="tables">  
  <!--   -----------------------------还款记录-----------------------------------------  -->   
                          <div class="user_cont_r_31 ">
							<table class="user_cont_r_3b" cellspacing=0>
                               <tr class="tittle">
                                <th>收款时间</th>
                                <th>收款金额（元）</th>
								<th>收款性质</th>
                               </tr>
                               <!-- 交易记录循环开始-->
                               <c:forEach items="${recordList}" var = "record" varStatus="index">
                               <tr <c:if test="${index.index%2==1}"> style="color:#353131;background-color:#f6f6f6;"</c:if> class="user_cont_r_3_b2">
								<td>${record[0] }</td>
								<td><span style="color:#e33340;">${record[1] }</span></td>
								<td>
									<c:choose>
										<c:when test="${record[2]==0 }">
											本金
										</c:when>
										<c:otherwise>
											利息
										</c:otherwise>
									</c:choose>
								</td>
							   </tr>
							   </c:forEach>
                              <!-- 交易记录循环结束-->
                            </table>														
							<div class="user_cont_r_2d">
								<a href="javascript:void(0);" onclick="page=0;_turnPage();">首页</a>
								<a href="javascript:void(0);" onclick="page=page-1;_turnPage();">上页</a>
								<span id="nowPage"></span>
								<a href="javascript:void(0);" onclick="page=page+1;_turnPage();">下页</a>
								<a href="javascript:void(0);" onclick="page=total;_turnPage();">末页</a>
							</div>
							</div>	
 <!--   -----------------------------相关公告-----------------------------------------  -->   
                        <div class="user_cont_r_3 ">
							<table class="user_cont_r_3b" cellspacing=0>
                               <tr class="tittle">
                                <th>公告标题</th>
                                <th>发布时间</th>
                               </tr>
                               <!-- 交易记录循环开始-->
                               <c:forEach items="${newsList}" var = "record" varStatus="index">
                               <tr<c:if test="${index.index%2==1 }"> style="color:#353131;background-color:#f6f6f6;"</c:if> class="user_cont_r_3_bbb2">
								<td class="tab_gg">
									<a target="_blank" href="<c:choose><c:when test="${fn:indexOf(record.href,'http://')==-1}">${ctx}/${record.href}</c:when><c:otherwise>${record.href}</c:otherwise></c:choose>">
										${record.name }
									</a>
								</td>
								<td>${record.createTime }</td>
							   </tr>
							   </c:forEach>
                              <!-- 交易记录循环结束-->
                            </table>											
							<div class="user_cont_r_2d">
								<a href="javascript:void(0);" onclick="page2=0;_turnPage2();">首页</a>
								<a href="javascript:void(0);" onclick="page2=page2-1;_turnPage2();">上页</a>
								<span id="nowPage2"></span>
								<a href="javascript:void(0);" onclick="page2=page2+1;_turnPage2();">下页</a>
								<a href="javascript:void(0);" onclick="page2=total2;_turnPage2();">末页</a>
							</div>
						</div>	
                        
                       
				
                     


						</div>	
                   </div>	
					</div>	
</body>
</html>
