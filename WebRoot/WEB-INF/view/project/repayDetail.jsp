<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<script src="${ctx }/js/jquery-1.7.js"></script>
<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/admincp121.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/dateTimePicker/WdatePicker.js"></script>
<title>1717tou.com</title>
<script>
	
	var page = 0;
	var page2 =0;
	var total = ${fn:length(planList)};
	total = total%4==0?total/4:((total/4)+1)
	var total2 = ${fn:length(planList2)};
	total2 = total2%4==0?total2/4:((total2/4)+1)
	total = parseInt(total);
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
		$(".admincp_r3_c2").each(function(index,item){
			//plan_0
			id = index;
			if(id >= page * 4 && id < (page+1) * 4){
				$(item).show()
			}else{
				$(item).hide();
			}
		});
		$("#nowPage").html(parseInt(page)+1+"/"+total);
	}
	function _turnPage2(){
		var id = 0;
		if(page2<0)page2=0;
		if(page2>=total2)page2=total2-1;
		$(".admincp_r4_c2").each(function(index,item){
			//plan_0
			id = $(item).attr("id");
			id = id.substring(6);
			if(id >= page2 * 4 && id < (page2+1) * 4){
				$(item).show()
			}else{
				$(item).hide();
			}
		});
		$("#nowPage2").html(parseInt(page2)+1+"/"+total2);
	}
	var _id = "${model.id}";
	var _repayCount = 0;
	var _repayTime = "";
	var _m = 0;
	function ignore(repayCount,obj,repayTime){
		$.post("${ctx}/auth/repaymentPlan/ignore",{id:_id,repayCount:repayCount,repayTime:repayTime},function(data){
			if(data>0){
				$(obj).parent().parent().remove();
				$("#allCount").html(parseInt($("#allCount").html())-1);
				total = $(".admincp_r3_c2").length%4==0?$(".admincp_r3_c2").length/4:(($(".admincp_r3_c2").length/4)+1)
				total = parseInt(total);
				_turnPage(page);
				}
		});
	}
	function overPlan(obj){
		$.post("${ctx}/auth/repaymentPlan/overPlan",{id:_id},function(data){
			if(data=="true"){
				alert("产品已成功完结。")
				$(obj).removeAttr("onclick");
				$(obj).unbind("click");
				$(obj).val("已完结");
			}else
				alert("仍有未还清的还款计划。")
		});
	}
	function repay(repayCount,obj,repayTime){
		var id = _id;
		_repayCount = repayCount;
		_repayTime = repayTime;
		var m = $(obj).parent().parent().find(".admincp_r3_c2_c").html();
		m = m.substring(0,m.length-1);
		_m = m;		
		if("${avlBal}".replace(/,/g,"")-m>=0){
			//弹出层
			$.post("${ctx}/auth/project/checkRepaymentPlans",{id:id,repayCount:repayCount,repayTime:repayTime},function(data){
				var obj = data.split(",");
				$(".span2").each(function(index,item){
					$(item).html(obj[index]);
				});
				xingwin_play();
			});
		}else{
			alert("账户余额不足");
		}
	}
	//重新下单
	function reRepay(){
		//更新project的repayCount
		$.post("${ctx}/auth/huifu/Repayment",{id:_id,repayCount:_repayCount,repayTime:_repayTime},function(data){
			alert("发送中，系统将在3秒后自动查询结果。");
              reLoading();
		});
	}
	//深度查询
	function searchState(){
		$.post("${ctx}/auth/project/transRepayState",{id:_id},function(data){
			alert("深度查询中，系统将在3秒后自动查询结果。");reLoading();
		});
	}
	var t = 3;
	function reLoading(){
		t--;
		if(t==0){
			$.post("${ctx}/auth/project/checkRepaymentPlans",{id:_id,repayCount:_repayCount,repayTime:_repayTime},function(data){
				var obj = data.split(",");
				$(".span2").each(function(index,item){
				if(index==0&&obj[index]==0){
				$(item).html("已完成");
				}else{
				$(item).html(obj[index]);
				}
				});
				xingwin_play();
			});
			$.post("${ctx}/auth/project/updateRepayCount",{id:_id},function(data){
			});
			t = 3;
		}else{
			setTimeout(reLoading,1000);
		}
	}
	function verify(repayCount,id,repayTime,obj){
		$(obj).unbind("click");
		$(obj).removeAttr("onclick");
		$.post("${ctx}/auth/project/RepaymentPlanVerify",{id:id,repayCount:repayCount,repayTime:repayTime},function(data){
			alert("申请成功，等待审核。")
			window.location.href = window.location.href;
		});
	}
	function addRepaymentPlan(){
		var booleanTag = false;
		if($("#repayCount").val()!=0){
			//验证非空
			if($("#time1").val()!=""&&$("#time2").val()!=""){
				booleanTag=true;
			}else{
				alert("请输入时间。");
			}
		}else{
			if($("#time3").val()!="")
				booleanTag=true;
		}
		if(booleanTag){
			$.ajax({
			    	type:"POST",
			    	async:false,
					url:"${ctx}/auth/project/addRepaymentPlan",
					//Long id,Integer repayCount,String time1,String time2
					data:"id="+${model.id}+"&repayCount="+$("#repayCount").val()+"&time1="+$("#time1").val()+"&time2="+$("#time2").val()+"&rate="+$("#rate").val(),
					success:function(data){
								if(data=="success")
									window.location.href = window.location.href;
								else if (data=="rateError")
									alert("日利率输入错误")
								else
									alert("生成失败，请检查利息计算开始/结束时间。")							}
					});
		}
 		
	}
	/*控制弹出层*/
	jQuery(document).ready(function($) {
		$('.theme-login').click(function(){
			$('.theme-popover-mask').css("display","block");
			$('.theme-popover').slideDown(200);
		})
		$('.close').click(function(){
			$('.theme-popover-mask').css("display","none");
			$('.theme-popover').slideUp(200);
		})
	})
</script>

</head>
<body>
	<div style="background:#FFFFFF;">
		<div class="admincp_r1"><span><a href="javascript:history.go(-1);">还款管理</a>&gt;详情</span></div>
	
		<div class="admincp_r2">
			<div class="admincp_r2_l">
				<div class="admincp_r2_l_1"><a href="${ctx }/project/view?id=${model.id }&tag=1" title="${model.name }"  target="_blank" ><cc:subLength var="${model.name }" length="20"/></a></div>
				<div class="admincp_r2_l_2">
					<div class="admincp_r2_l_2a">预期年化率<br /><span style="color:#ea4c59; margin-left:35px;">${model.rate }%</span></div>
					<div class="admincp_r2_l_2a">投资期限<br /><span>${model.time_limit }<c:if test="${model.delay_time_limit!=null&&model.delay_time_limit!=0 }">+${model.delay_time_limit }</c:if></span>个月</div>
					<div class="admincp_r2_l_2a">募集金额<br />
					<c:choose>
					<c:when test="${model.total_money>10000}">
					<span>${model.total_money/10000 }</span>万
					</c:when>
					<c:otherwise>
					<span>${model.total_money==null?0:model.total_money}</span>元
					</c:otherwise>
					</c:choose>
					</div>
				</div>
				<div class="admincp_r2_l_3">
					收益方式：${model.repay_content}
				</div>
				<div class="admincp_r2_l_4">
                   产品类型：
				<c:choose>
					<c:when test="${model.type==0}">信托(信满盈)</c:when>
					<c:when test="${model.type==1}">资管(资涨通)</c:when>
					<c:when test="${model.type==2}">基金(金多宝)</c:when>
					<c:when test="${model.type==3}">屌丝宝</c:when>
					<c:otherwise>unknown</c:otherwise>
				</c:choose><br/>
				加入条件：最低${model.lowest_money }元<br/>
				单笔投资上限：${model.highest_money }元<br/>
				
				</div>
				<div class="admincp_r2_l_5">
		    	产品周期：${model.rateTime }&nbsp;&nbsp;至&nbsp;&nbsp;${model.pendTime }
				</div>
				
			</div>
			<div class="admincp_r2_r">
			<!-- 还款信息开始 -->
			<form name="form1" method="post" action="">
				<div class="admincp_r2_r_main">
					<div class="admincp_r2_r1">
						预期还款金额：<span>${model.totalRepay==null?0:model.totalRepay}元</span>						
					</div>
					<div class="admincp_r2_r1">
						已经还款：<span>${model.repay_money}元</span>						
					</div>
					<div class="admincp_r2_r1">
						<span id="balance"></span>
					</div>
					<div class="admincp_r2_r2">
						<c:choose>
							<c:when test="${model.state==1}"><input type="button" onclick="overPlan(this)" value="产品完结"/></c:when>
							<c:when test="${model.state==2}"><input type="button" value="已完结"/></c:when>
						</c:choose>
						<!-- <input type="text" name="money"  value=""  />&nbsp;元 -->
					</div>
				</div>
				<div class="admincp_r2_r_button">
					<a class="theme-login">
						<input type="button" value="追加还款计划" name="submit"/>
					</a>
					<!-- <input type="button" name="submit" value="追加还款计划" disabled="disabled"/> -->
				</div>
				
			</form>
			<!-- 还款信息结束 -->	
	
			</div>
			
		</div>	
		
		<div class="admincp_r3">
			<div class="admincp_r3_a">
				<div class="admincp_r3_a1">还款管理</div>
			</div>
			<div class="admincp_r3_c">
				<div class="admincp_r3_c1">
					<div class="admincp_r3_c1_a">还款时间</div>
					<div class="admincp_r3_c1_b">计划还款金额</div>
					<div class="admincp_r3_c1_c">剩余还款金额</div>
					<div class="admincp_r3_c1_d">还款性质</div>
					<div class="admincp_r3_c1_e">操作</div>
				</div>
				<!-- 还款计划 -->
				<c:forEach items="${planList}" var="plan" varStatus="index">
					<div class="admincp_r3_c2" id="plan_${index.index }" >
						<div class="admincp_r3_c2_a" id="re${index.index }" onmouseover="remindShow('re${index.index }','计息期间：${plan.start_time }至${plan.end_time }')" onmouseout="remindHide()">${plan.repayTime }</div>
						<div class="admincp_r3_c2_b">${plan.repayMoney }元</div>
						<div class="admincp_r3_c2_c">${plan.repay_money }元</div>
						<div class="admincp_r3_c2_d">
							<c:choose>
								<c:when test="${plan.repayCount!=0}">利息</c:when>
								<c:otherwise>本金</c:otherwise>
							</c:choose>
						</div>
						<div class="admincp_r3_c2_e">
							<!-- ${plan.verifyState} -->
							<c:choose>
								<c:when test="${plan.verifyState==1}">
									<a href="javascript:void(0);" onclick="repay(${plan.repayCount},this,'${plan.repayTime }')"><img src="${ctx }/images/icon_huankuan.png" /></a>&nbsp;
								</c:when>
								<c:when test="${plan.verifyState==0}">
									等待审核
								</c:when>
								<c:when test="${plan.ctype == '1'}">
								       <a href="javascript:void(0)" class="shenhe" onclick="verify(${plan.repayCount },${model.id },'${plan.repayTime }',this)">提交审核</a>
  							    </c:when>
								<c:otherwise>
									<a href="javascript:void(0)" class="shenhe" onclick="verify(${plan.repayCount },${model.id },'${plan.repayTime }',this)">提交审核</a>
									<a href="javascript:void(0);"  class="huankuan" onclick="ignore(${plan.repayCount},this,'${plan.repayTime}')">忽略还款</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="admincp_r5">
			共 <span id="allCount">${fn:length(planList)}</span> 条记录
		 	<a href="javascript:void(0);" onclick="page=0;_turnPage();">首页</a>
			<a href="javascript:void(0);" onclick="page=page-1;_turnPage();">上页</a>
			<span id="nowPage">
			<script>document.write((parseInt(page)+1)+"/"+total)</script>
			</span>
			<a href="javascript:void(0);" onclick="page=page+1;_turnPage();">下页</a>
			<a href="javascript:void(0);" onclick="page=total;_turnPage();">末页</a>
		</div>
		<div class="admincp_r4">
			<div class="admincp_r4_a">
				<div class="admincp_r4_a1">还款记录</div>
			</div>
			<div class="admincp_r4_c">
				<div class="admincp_r4_c1">
					<div class="admincp_r4_c1_a">还款时间</div>
					<div class="admincp_r4_c1_b">计划还款金额</div>
					<div class="admincp_r4_c1_c">还款性质</div>
				</div>
				<!-- 还款记录 -->
				<c:forEach items="${planList2}" var="plan" varStatus="index"> 
				<div class="admincp_r4_c2" id="plan2_${index.index }">
					<div class="admincp_r4_c2_a">${plan.repayTime }</div>
					<div class="admincp_r4_c2_b">${plan.repayMoney }元</div>
					<div class="admincp_r4_c2_c">
						<c:choose>
							<c:when test="${plan.repayCount!=0}">利息</c:when>
							<c:otherwise>本金</c:otherwise>
						</c:choose>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
		<!-- 
		<div class="admincp_r5"><a href="#">更多>></a></div>
		 -->
		 <div class="admincp_r5">
		 	共 ${fn:length(planList2)} 条记录
		 	<a href="javascript:void(0);" onclick="page2=1;_turnPage2();">首页</a>
			<a href="javascript:void(0);" onclick="page2=page2-1;_turnPage2();">上页</a>
			<span id="nowPage2">
			<script>document.write((parseInt(page2)+1)+"/"+total2)</script>
			</span>
			<a href="javascript:void(0);" onclick="page2=page2+1;_turnPage2();">下页</a>
			<a href="javascript:void(0);" onclick="page2=total2;_turnPage2();">末页</a>
		 </div>
	</div>
	<div class="xing" id="xing" style="display:none;">
		<div class="xingwin">
			<div class="xingwin_close">
				<a href="javascript:void(0)" onclick="xingwin_close()"><img src="${ctx}/images/icon_close.png" /></a>
			</div>
			<div class="xingwin1">
				<span class="span1">订单总数：</span><span class="span2">5636</span>
			</div>
			<div class="xingwin2">
				<span class="span1">成功订单：</span><span class="span2">5636</span>
			</div>
			<div class="xingwin2">
				<span class="span1">失败订单：</span><span class="span2">51</span>
			</div>
			<div class="xingwin2">
				<a href="javascript:reRepay();"><img src="${ctx}/images/icon_cxxd.png" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:searchState()"><img src="${ctx}/images/icon_sdcx.png" /></a>
			</div>
		</div>
	</div>
	<script language="javascript">
		function xingwin_close(){
			var xing = document.getElementById('xing');
			xing.style.display="none";	
			window.location.href=window.location.href;
		}
		function xingwin_play(){
			var xing = document.getElementById('xing');
			xing.style.display="block";	
		}
		function remindShow(divId,content){
			$("#remindDiv").css("top",($("#"+divId).offset().top));
			$("#remindDiv").css("left",($("#"+divId).offset().left+50));
			$("#remindDiv").html(content);
			$("#remindDiv").show();
		}
		function remindHide(){
			$("#remindDiv").fadeOut(0);
		}
	</script>
	<!--   以下是屌丝宝投资赎回 弹出层的内容-->                         
    <div class="theme-popover">
         <div class="theme-poptit">
              <h3>追加还款计划</h3>
              <select style="margin-top:14px;" id = "repayCount">
              	<option selected value="1">利息</option>
              	<option value="0">本金</option>
              </select>
              <a href="javascript:;" title="关闭" class="close">[关闭]</a>
         </div>     
         <div class="pop_middle">
            <table cellspacing=0 border="0">
               <tr style="height:30px;">
                    <td>计算日利率</td>
                    <td><input type="text" id="rate" class="time1" value="${model.day_rate*100 }" style="width:188px" maxlength="8" />%</td>
               </tr>
               <tr style="height:30px;">
                    <td>还款起止时间</td>
                    <td><input type="text" id="time1" class="time1" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'2024-12-30',lang:'zh-cn'})" readonly="readonly" /></td>
               </tr>
               <tr style="height:30px;">
                    <td>还款终止时间</td>
                    <td><input type="text" id="time2" class="time1" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'2024-12-30',lang:'zh-cn'})" readonly="readonly" /></td>
               </tr>

            </table>
            <div>
            <div class="pop_bottom"><a href="javascript:;" title="关闭" class="close"><input type="button" value="确定" onclick="addRepaymentPlan()"/></a></div>
            </div>
         </div>
    </div>
	<div class="theme-popover-mask"></div>
	<div id="remindDiv" style="background:#eee;display:none;padding:7px 14px;position:absolute;height:15px;width:auto;top:0px;left:0px;">test</div>
</body>
</html>
