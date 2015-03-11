<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="${ctx }/css/main.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/user_my_jilu.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/user_touzi_popup.css"rel="stylesheet" type="text/css" />

<script src="${ctx }/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<title>1717tou.com</title>
<script type="text/javascript">
	/*控制弹出层ds5*/
	jQuery(document).ready(function($) {
		setTimeout(function(){
			$('.theme-login').click(function(){
				$('.theme-popover-mask').css("display","block");
				$('.theme-popover').slideDown(200);
			})
			$('.theme-poptit .close').click(function(){
				$('.theme-popover-mask').css("display","none");
				$('.theme-popover').slideUp(200);
			})
		},2000);
	})
	function showDiv(){
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover').slideDown(200);
	}
	function closeDiv(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover').slideUp(200);	
	}
	$(document).ready(function(){
		$(".repayEarlyReward").each(function(index,item){
			$.post("${ctx}/user/repayEarlyReward",{id:$(item).attr("id")},function(data){
				var html = "";
				var html2 = "";
				var html3 = "";
				if(data[0]!=0&&data[0]!=1&&data[1]!=2){
					html += "<span style='color:#e33340;'>"+data[2]+"</span>";
					html += "|";
					html += "<span style='color:#0060a5;'>"+data[1]+"个月</span>";
					//if($(item).attr("id")==217)(data[0]=5)
					if(data[0]=="3"){
						//撤
						html2 += "<a onclick='cancelRepayEarly("+$(item).attr("id")+");'>";
						html2 += "<img src='${ctx }/images/shenqing2.png'>";
						html2 += "</a>";
						html3 += "还款中";
					}					
					if(data[0]=="5"){
						//申
						html2 += "<a onclick='repayEarlyDetail("+$(item).attr("id")+");showDiv();'>";
						html2 += "<img src='${ctx }/images/shenqing3.png'>";
						html2 += "</a>";
						html3 += "还款中";
					}
					if(data[0]=="4"||data[0]=="7"){
						//不
						html2 += "<img src='${ctx }/images/shenqing4.png'>";
						html3 += "还款中";
					}
					if(data[0]=="6"){
						//已
						html2 += "<img src='${ctx }/images/shenqing1.png'>";
						html3 += "已还款";
					}
					if(data[0]=="8"){
						html2 += "<img src='${ctx }/images/shenqing1.png'>";
						html3 += "投标中";
					}
					if(data[0]=="9"){
						html2 += "<img src='${ctx }/images/shenqing1.png'>";
						html3 += "未成立";
					}
					$(item).html(html);
					$(item).parent().find(".user_cont_r_t_3c5").html(html2);
					$(item).parent().find(".user_cont_r_t_3c6").html(html3);
					if(data[0]=="6"){
						$(item).parent().css("color","#bebebe");
						$(item).parent().find(".user_cont_r_t_3c1 a").css("color","#bebebe");
						$(item).parent().find(".user_cont_r_t_3c4 span").css("color","#bebebe");
					}
				}
			})
		})
	});
	function repayEarlyDetail(id){
		$("#pidForRe").val(id);
		//项目名称	投资金额	起息日	到期日	预计到期本息	
		$.post("${ctx}/user/repayEarlyDetail",{id:id},function(data){
			$(".nian2 span :lt(4)").each(function(index,item){
				$(item).html(data[index].rate+"%");
			});
			$(".nian2 span").eq(5).html(data[0].month+"个月");
			$(".nian2 span").eq(6).html(data[1].month+"个月");
			$(".nian2 span").eq(7).html(data[2].month+"个月");
			//$("#timeTag").css("right","")
			$("#name").html($("#"+id).parent().find(".user_cont_r_t_3c1").attr("title"));
			$("#money").html($("#"+id).parent().find(".user_cont_r_t_3c3").html());
			$("#rateTime").html($("#"+id).parent().find(".user_cont_r_t_3c2").html());
			$("#endTime").html($("#"+id).parent().find(".user_cont_r_t_3cxxxx").html());
			var totalRepay = parseFloat($("#money").html())*(1+(data[data[4]].rate/1200*data[data[4]].month));
			totalRepay += "";
			if(totalRepay.indexOf(".")!=-1)
				totalRepay = totalRepay.substring(0,totalRepay.indexOf(".")+3);
			
			$("#totalRepay").html($("#"+id).find("span").eq(0).html())
			
			$("#startTime").html(data[data[4]].startTime);
			$("#rate").html(data[data[4]].rate);
			$("#firstTotalRepay").html(totalRepay);
			$("#startTime2").html(data[data[4]].startTime);
			if(data[4]==data.length-2){
				$("#nextPanel").hide();
			}else{
				var StartTime = new Date();
				var NowTime = new Date(data[data[4]+1].startTime.replace(/-/g,"/"));
				var t = NowTime.getTime() - StartTime.getTime();
				$("#diffDate").html(Math.floor(t/1000/60/60/24));
				$("#nextRate").html(data[data[4]+1].rate);
				totalRepay = parseFloat($("#money").html())*(1+(data[data[4]+1].rate/1200*data[data[4]+1].month));
				totalRepay += "";
				if(totalRepay.indexOf(".")!=-1)
					totalRepay = totalRepay.substring(0,totalRepay.indexOf(".")+3);
				$("#nextTotalRepay").html(totalRepay);
				$("#nextPanel").show();
			}
			var NowTime = new Date();
			var EndTime = new Date(data[3].startTime.replace(/-/g,"/"));
			var t = EndTime.getTime() - NowTime.getTime();
			t = Math.floor(t/1000/60/60/24);
			var total = data[3].month*30;
		
			$("#timeTag").css("right",(t/total)*330);
		});
	}
	function repayEarly(){
		$.post("${ctx}/user/repayEarly",{id:$("#pidForRe").val()},function(data){
			if(data!="ok"){
				alert(data)
			}else{
				alert("申请成功");
				window.location.href=window.location.href;
			}
		});
		
	}
	function cancelRepayEarly(id){
		if(confirm("是否确认撤销赎回？")){
			$.post("${ctx}/user/cancelRepayEarly",{id:id},function(data){
				if(data!="ok"){
					alert(data)
				}else{
					alert("申请成功");
					window.location.href=window.location.href;
				}
			});
		}
	}
</script>
</head>
<body >
	<input type="hidden" value="" id="pidForRe"/>
				<div class="user_cont_rr" style="background:#FFFFFF;">
                  <div class="user_cont_rrr">
					    <div class="user_cont_r_1"><span>我的投资</span></div>
						<div class="user_cont_r_2"> 
                         <div class="user_cont_r_2a">
                           <ul>
								<li class="li_2" id="tag0"><a href="${ctx}/user/recordList?type=0" >信满盈(${num['0'] })</a></li>
						<li class="li_3" id="tag1"><a href="${ctx}/user/recordList?type=1" >资涨通(${num['1'] })</a></li>	
						<li class="li_4" id="tag2"><a href="${ctx}/user/recordList?type=2" >金多宝(${num['2'] })</a></li>
						<li class="li_5 sel" id="tag3"><a href="${ctx}/user/recordList?type=3" >屌丝宝(${num['3'] })</a></li>	
							</ul>                   
                           </div>
                          
						  </div> 
                    <div class="tables">  
<!--屌丝宝tab开始-->                          
						<div class="user_cont_r_3 dsbao">
                          <table class="user_cont_r_3b" cellspacing=0>
                        		<tr class="bg_hui">
                                <th class="user_cont_r_3b1">项目名称|编号</th>
                                <th class="user_cont_r_3b2">起息日</th>
                                <th >到期日(预期)</th>
								<th class="user_cont_r_3b3">投资金额(元)</th>
								<th class="user_cont_r_3b4">预期金额(元)|期限</th>
								<th class="user_cont_r_3b5">提前赎回(全部)</th>
                                <th class="user_cont_r_3b6">状态</th>
                                <th class="user_cont_r_3b7">查看详情</th>
                               </tr>
                               <!-- 交易记录循环开始-->
                               <c:forEach items="${page.result}" var="record" varStatus="index">
                               
                               <tr<c:if test="${index.index%2==1 }"> class="bg_hui"</c:if><c:if test="${record[6]==2}"> style="color:#bebebe;"</c:if>>
								<td title="${record[1]}" class="user_cont_r_t_3c1">
									<a href="${ctx }/project/view?id=${record[0] }"<c:if test="${record[6]==2}"> style="color:#bebebe;"</c:if> target="_blank"><cc:subLength var="${record[1]}" length="8"/></a>
								</td>
								<td class="user_cont_r_t_3c2">${record[2]}</td>
                                <td class="user_cont_r_t_3cxxxx">${record[3]}</td>
								<td class="user_cont_r_t_3c3">${record[4] }</td>
								<td class="user_cont_r_t_3c4 repayEarlyReward" id="${record[0] }">
									<!-- 
									<span style="color:#e33340;">100,168.08</span>
									|
									<span style="color:#0060a5;">12个月</span>
									 -->
								</td>
								<td class="user_cont_r_t_3c5 dsbao_5">
									
								</td>
                                <td class="user_cont_r_t_3c6">
                                	
                                </td>
                                <td class="user_cont_r_t_3c7 dsbao_7">
                               		<a href="${ctx }/user/createContract?id=${record[0] }" target="_blank">
                                		<a href="${ctx }/user/recordDetail?id=${record[0] }">
                                			<span style="color:#0060a5">详情</span>
                                		</a>
                                		<c:if test="${(record[6]==1||record[6]==2)&&record[8]!=null&&record[3]!=null}">
                                		<a href="${ctx }/user/createContract?id=${record[0] }" target="_blank">
                                		<span style="color:#e33340;">
                                		|合同
										</span>
										</a>
                                		</c:if>
                               		</a>
                                </td>
							  </tr>
							  </c:forEach>
                               <!-- 交易记录循环结束-->
                              
                            </table>												
							<div class="user_cont_r_2d">
								<jsp:include page="../common/page.jsp"></jsp:include>
							</div>
                         </div>
                            
<!--   以下是屌丝宝申请赎回 弹出层的内容-->                         
                            <div class="theme-popover">
                                 <div class="theme-poptit">
                                      
                                      <h3>申请赎回</h3>
                                      <a href="javascript:;" title="关闭" class="close">[关闭]</a>
                                 </div>     
                                 <div class="pop_middle">
                                    <table cellspacing=0 border="0">
                                       <tr>
                                            <td>项目名称</td>
                                            <td><span id="name"></span></td>
                                       </tr>
                                       <tr>
                                            <td>投资金额</td>
                                            <td><span style="color:#e33340;" id="money"></span>元</td>
                                       </tr>
                                       <tr>
                                            <td>起息日</td>
                                            <td><span id="rateTime" style="color:#0060a5"></span></td>
                                       </tr>
                                       <tr>
                                            <td>到期日</td>
                                            <td><span id="endTime" style="color:#0060a5"></span></td>
                                       </tr>
                                        <tr>
                                            <td>预计到期本息</td>
                                            <td><span style="color:#e33340;" id="totalRepay"></span>元</td>
                                       </tr>                                                                            				
                                    </table>
                                    <div class="nian">
                                      <div class="nian1">预计年化率</div>
                                      <div class="nian2">
                                       	<span style="color:#e33340; position:relative;top:5px;left:90px;width: 35px;display: inline-block;text-align: center">5.55%</span>
                                        <span style="color:#e33340;position:relative;top:5px;left:130px;width: 35px;display: inline-block;text-align: center">6.55%</span>
                                        <span style="color:#e33340;position:relative;top:5px;left:175px;width: 35px;display: inline-block;text-align: center">7.55%</span>
                                        <span style="color:#e33340;position:relative;top:5px;left:215px;width: 35px;display: inline-block;text-align: center">8.40%</span>
                                        <span style="color:#0060a5;position:relative;top:40px;left:-150px;">起息日</span> 
                                        <span style="color:#0060a5;position:relative;top:40px;left:-110px;width: 35px;display: inline-block;text-align: center">3个月</span>
                                        <span style="color:#0060a5;position:relative;top:40px;left:-60px;width: 35px;display: inline-block;text-align: center">6个月</span>
                                        <span style="color:#0060a5;position:relative;top:40px;left:-10px;width: 35px;display: inline-block;text-align: center">9个月</span>
                                        <span style="color:#0060a5;position:relative;top:40px;left:30px;">到期日</span>
                                         <img style="color:#0060a5;position:relative;top:13px;right:120px;" id="timeTag" src="${ctx }/images/nian_jiao.png"/>
                                      </div>
                                      <div class="nian3"><span style="color:#e33340;">*</span>注： <img style="color:#0060a5;" src="${ctx }/images/nian_jiao.png"/> 为当前时间点</div>
                                    </div>
                                     <div class="pop1_ts">
                                     	提示：您将于下一次开放赎回日（<span id="startTime" style="color:#0060a5"></span>）以<span style="color:#e33340;" id="rate">7</span>%的预期收益率申请赎回您的投资，
                                     	预计本息<span id="firstTotalRepay" style="color:#e33340;"></span>元，预计到账时间<span id="startTime2" style="color:#0060a5"></span>，
                                     	<span id="nextPanel">
                                     	您只要坚持<span style="color:#e33340;" id="diffDate">90</span>天就能享受<span style="color:#e33340;" id="nextRate">8.4</span>%
                                     	的投资收益，预计到期本息<span id="nextTotalRepay" style="color:#e33340;"></span>元，
                                     	</span>
                                     	确定要提前赎回吗？
                                     </div>
                                    <div class="pop1_footer">
                                    <img class="tj" src="${ctx }/images/dsbao1.png" onclick="repayEarly()"/>
                                    <img class="qx" src="${ctx }/images/dsbao1_qux.png"  onclick="closeDiv()"/>
                                  
                                    </div>
                                 </div>
                               
                            </div>
                            
  <div class="theme-popover-mask"></div>
<!--  屌丝宝申请赎回 结束-->
<!--   以下是屌丝收益出详情弹出层-->                         
                            <div class="theme-popover2">
                                 <div class="theme-poptit2">
                                      
                                      <h3>收益详情</h3>
                                      <a href="javascript:;" title="关闭" class="close">[关闭]</a>
                                 </div>     
                                 <div class="pop_middle2">
                                    <table cellspacing=0 border="0" class="pop_middle2_top">
                                       <tr>
                                            <td>项目期数</td>
                                            <td><span>屌丝宝001期-d10001</span></td>
                                       </tr>
                                       <tr>
                                            <td>投资金额</td>
                                            <td><span style="color:#e33340">100,000,00</span>元</td>
                                       </tr>
                                       <tr>
                                            <td>起息日期</td>
                                            <td><span style="color:#0060a5">2014-09-01</span></td>
                                       </tr>
                                       <tr>
                                            <td>总收益额</td>
                                            <td><span style="color:#e33340">0.48</span>元</td>
                                       </tr>
                                       <tr>
                                            <td>七日年化利率</td>
                                            <td><span style="color:#e33340">0.1312%</span></td>
                                       </tr>
                                       <tr>
                                            <td>万份收益</td>
                                            <td><span style="color:#e33340">231.3102</span>元</td>
                                       </tr>
                                    </table>
                                     <table cellspacing=0 border="0" class="pop_middle2_bom">
                                       <tr class="color_hui">
                                            <th>收益所属日期</th>
                                            <th>收益（元）</th>
                                       </tr>
                                      <!-- 数据循环开始-->
                                       <tr>
                                            <td>2014-09-02</td>
                                            <td><span>0.06元</span></td>
                                       </tr> 
                                       <tr class="color_hui">
                                            <td>2014-09-02</td>
                                            <td><span>0.06元</span></td>
                                       </tr>                   
                                       <tr>
                                            <td>2014-09-02</td>
                                            <td><span>0.06元</span></td>
                                       </tr>                   
                                       <tr class="color_hui">
                                            <td>2014-09-02</td>
                                            <td><span>0.06元</span></td>
                                       </tr>                   
                                       <tr>
                                            <td>2014-09-02</td>
                                            <td><span>0.06元</span></td>
                                       </tr>                   
                                       <tr class="color_hui">
                                            <td>2014-09-02</td>
                                            <td><span>0.06元</span></td>
                                       </tr>                   
                                                                              
                                    </table>
                          <div class="user_cont_r_2d">
								1/10页 下一页  最后一页
							</div>

                                 </div>
                               
                            </div>                          
  <div class="theme-popover-mask2"></div>

  
						</div>	
                   </div>	
					</div>	
</body>
</html>
