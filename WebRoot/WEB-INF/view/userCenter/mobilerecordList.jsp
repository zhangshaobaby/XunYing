<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="description" content="网站描述" />
	<meta name="keywords" content="关键字" />
	<link href="${ctx }/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }/css/user_my_jilu.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }/css/user_touzi_popup.css"rel="stylesheet" type="text/css" />
	<script src="${ctx }/js/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/js/base.js"></script>
	<title>1717tou.com</title>
	<script type="text/javascript">
		$(function(){
			$(".user_cont_r_2a li").click(function() {
				$(this).addClass("sel").siblings().removeClass("sel");
				window.location.href=$(this).find("a").attr("href")
			});
			$(".user_cont_r_2a li").eq(${type}).addClass("sel").siblings().removeClass("sel");
		});
		function repayEarly(id){
			if(confirm("是否确认提前赎回?")){
				$.post("${ctx}/user/repayEarly",{id:id},function(data){
					if(data!="false"){
						alert("申请成功,系统将在3-5个工作日内将款项退还到您的账户。");
						window.location.href=window.location.href;
					}else{
						alert("申请已经提交,正在处理中")
					}
				});
			}
		}

		
		function checkContract(id){
			$.ajax({
			    type:"POST",
			    async:false,
				url:"${ctx}/user/createContract",
				data:"id="+id,
				success:function(data){
					if(data!="false"){
						window.open("${ctx}/user/downloadPdf?id="+id);
					}
				}
			});	
		}
	</script>
	<script type="text/javascript">
	/*控制弹出层ds5*/
 jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').css("display","block");
		$('.theme-popover').slideDown(200);
	})
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').css("display","none");
		$('.theme-popover').slideUp(200);
	})
	
	$('.theme-login2').click(function(){
		$('.theme-popover-mask2').css("display","block");
		$('.theme-popover2').slideDown(200);
	})
	$('.theme-poptit2 .close').click(function(){
		$('.theme-popover-mask2').css("display","none");
		$('.theme-popover2').slideUp(200);
	})

})
//加载日期收益
function loadlucreData(pname,rateDate,transAmt,pid){
//设置项目名称 投资金额等
$("#pTranamt").html(transAmt);
$("#pname").html(pname);
$("#rateStartDate").html(rateDate);
$("#rateDate").val(rateDate);
$("#pid").val(pid);
    $("#transAmt").val(transAmt);
    var formobj=document.forms["ratePageForm"];
    var url = jQuery(formobj).attr("action");
    var data = jQuery(formobj).serialize();
    if(url.indexOf("?")>0){
	 	 url=url+"&"+data;
	 	}else{
	 	 url=url+"?"+data;
	}
	    //计算投资总收益
    	$.ajax({
			    type:"POST",
			    async:false,
				url: "${ctx}/project/gettototallunre",
				data:data,
				success:function(result){
				 $("#totallunre").html(result["totallunre"]);
				 $("#sdayyearrate").html(result["sdayyearrate"]);
				 $("#tenshondlunre").html(result["tenthondslunre"]);
				}
			});	
	
    createTableData(url);
}
//创建数据即分页
function createTableData(url){
$("#lucreTable").empty();
$("#user_cont_r_2d").empty();
    var thtrele=$("<tr></tr>").appendTo("#lucreTable");
	var thtdele=$("<th>收益所属日期</td>").appendTo(thtrele);
	var thtdele=$("<th>收益（元）</td>").appendTo(thtrele);
	$.ajax({
			    type:"POST",
			    async:false,
				url:url,
				success:function(page){
				if(page==null){
					var trele=$("<tr></tr>").appendTo("#lucreTable");
					var tdele=$("<td cols='2'>获取数据失败</td>").appendTo(trele);
					}else{
					jQuery.each(page.result, function(i, item) {
				    var trele=$("<tr></tr>").appendTo("#lucreTable");
					var tdele=$("<td>"+item['date']+"</td>").appendTo(trele);
					var tdele=$("<td>"+item['lucre']+"</td>").appendTo(trele);
			       });
			       //分页
				$("#totalRecord").val(page.totalRecord);
			   	setPageFun(page,"user_cont_r_2d","ratePageForm");
					}
				}
			});	
}

	</script>
</head>
<body>
	<form id="pageForm" action="${ctx}/user/recordList" method="post">
		<input type="hidden" name="type" id="type" value="${type }"/>
	</form>
	<div class="user_cont_rr" style="background:#FFFFFF;">
		<div class="user_cont_rrr">
			<div class="user_cont_r_1"><span>我的投资</span></div>
			<div class="user_cont_r_2"> 
				<div class="user_cont_r_2a">
					<ul>

						<li class="li_2" id="tag0"><a href="${ctx}/user/recordList?type=0" >信满盈</a></li>
						<li class="li_3" id="tag1"><a href="${ctx}/user/recordList?type=1" >资涨通</a></li>	
						<li class="li_4" id="tag2"><a href="${ctx}/user/recordList?type=2" >金多宝</a></li>
						<li class="li_5" id="tag3"><a href="${ctx}/user/recordList?type=3" >屌丝宝</a></li>															
					</ul>                   
				</div>
			</div>
			<div class="user_cont_r_3">
				<table class="user_cont_r_3b" cellspacing=0>
					<tr class="tittle">
						<th class="user_cont_r_3b1">项目名称|编号</th>
						<th class="user_cont_r_3b2">起息日</th>
						<th class="user_cont_r_3b3">投资金额（元）</th>
						<th class="user_cont_r_3b4">目前价值（元）</th>
						<th class="user_cont_r_3b5">提现（全部）</th>
						<th class="user_cont_r_3b6">状态</th>
						<th class="user_cont_r_3b7">查看收益详情</th>
					</tr>
				<!-- 交易记录循环开始-->
					<c:forEach items="${page.result}" var="record">
					<!-- p.id,p.name,p.rateTime,p.pendTime,sum(t.transAmt),count(t.id),p.state -->
					<tr class="user_cont_r_t_3c">
						<td title="${record.name}" class="user_cont_r_t_3c1">
							<a href="${ctx }/project/view?id=${record.id }" target="_blank"><cc:subLength var="${record.name}" length="8"/></a>
						</td>
						<td class="user_cont_r_t_3c2">${record.rateTime}</td>
						<td class="user_cont_r_t_3c3">${record.transAmt}</td>
						<td class="user_cont_r_t_3c4">
						<span>${record.currentmoney}</span>
						</td>
						<td class="user_cont_r_t_3c5">
							<c:choose>
									<c:when test="${record.canrepay==1}"><img src="${ctx }/images/user_touzi_01_h.png"/></c:when>
									<c:when test="${record.canrepay==0}"><a href="javascript:void(0)" onclick="repayEarly(${record.id })"><img src="${ctx }/images/user_touzi_01.png"/></a></c:when>
							</c:choose>
						</td>
						<td class="user_cont_r_t_3c6">
							<c:choose>
								<c:when test="${record.state==0||record.state==3 }">投标中</c:when>
								<c:when test="${record.state==1}">还款中</c:when>
								<c:when test="${record.state==4 }">未成立</c:when>
								<c:when test="${record.state==2}">已还款</c:when>
							</c:choose>
						</td>
						<td class="user_cont_r_t_3c7">
							<a  class="theme-login2" href="javascript:loadlucreData('${record.name}','${record.rateTime}','${record.transAmt}','${record.id}')">
								<img src="${ctx }/images/user_touzi_02.png" />
							</a>
							<c:if test="${(record.state==1||record.state==2)&&record.pstarttime!=null&&record.pendTime!=null}">
							<a href="${ctx }/user/createContract?id=${record.id }" target="_blank">合同</a>
							</c:if>
						</td>
					</tr>
					</c:forEach>
				<!-- 交易记录循环结束-->
				</table>														
				<div class="user_cont_r_2d">
					<jsp:include page="../common/page.jsp"></jsp:include>
				</div>
				<div class="theme-popover-mask"></div>
<!--   以下是屌丝收益出详情弹出层-->   
<!-- 弹出层的form表单   -->                   
<form id="ratePageForm" name="ratePageForm" action="${ctx}/project/getdslucre" method="post">
<input type="hidden" id="transAmt" name="transAmt" value=""/>
<input type="hidden" id="rateDate" name="rateDate" value=""/>
<input type="hidden" id="pid" name="pid" value=""/>
<input type="hidden" id="totalRecord" name="page.totalRecord" value=""/>
 </from>  


                            <div class="theme-popover2">
                                 <div class="theme-poptit2">
                                      
                                      <h3>收益详情</h3>
                                      <a href="javascript:;" title="关闭" class="close">[关闭]</a>
                                 </div>     
                                 <div class="pop_middle2">
                                    <table cellspacing=0 border="0" class="pop_middle2_top">
                                       <tr>
                                            <td>项目名称</td>
                                            <td><span id="pname"></span></td>
                                       </tr>
                                       <tr>
                                            <td>投资金额</td>
                                            <td><span id="pTranamt" style="color:#e33340"></span>元</td>
                                       </tr>
                                       <tr>
                                            <td>起息日期</td>
                                            <td><span id="rateStartDate" style="color:#0060a5"></span></td>
                                       </tr>
                                        <tr>
                                            <td>目前价值</td>
                                            <td><span id="totallunre" style="color:#e33340">0.00</span> 元</td>
                                       </tr>
                                       <tr>
                                            <td>七日年化利率</td>
                                            <td><span id="sdayyearrate"  style="color:#e33340">0.00</span>%</td>
                                       </tr>
                                       <tr>
                                            <td>万份收益</td>
                                            <td><span id="tenshondlunre" style="color:#e33340">0.00</span>元</td>
                                       </tr>
                                       
                                    </table>
                                     <table  id="lucreTable"  cellspacing=0 border="0" class="pop_middle2_bom">
                                      
                                      <!-- 数据循环开始-->
                                                                    
                                    </table>
                          <div id="user_cont_r_2d" class="user_cont_r_2d">
                        
							</div>

                                 </div>
                               
                            </div>
                                    
            <div class="theme-popover-mask2"></div>	
			</div>	
		</div>	
	</div>
</body>