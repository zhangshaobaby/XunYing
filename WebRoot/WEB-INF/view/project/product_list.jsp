<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
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
<link href="${ctx }/css/admincp3.css" rel="stylesheet" type="text/css" />
<link  href="${ctx }/css/alertStyle.css"rel="stylesheet" type="text/css"/>
<!--[if lt IE 9]>
<script src="${ctx }/js/html5.js" type="text/javascript"></script>
<script src="${ctx }/js/excanvas.js" type="text/javascript"></script>
<script src="${ctx }/js/excanvas.compiled.js" type="text/javascript"></script>
<![endif]-->
<script src="${ctx }/js/jquery-1.7.js"></script>
<script src="${ctx }/js/jquery.knob.js"></script>
<script src="${ctx }/js/myknob.js"></script>
<script src="${ctx }/js/modal.js"></script>
<script type="text/javascript" src="<%=path %>/js/base.js"></script>
<script type="text/javascript" src="${ctx }/js/dateTimePicker/WdatePicker.js"></script>
<title>1717tou.com</title>
<script>
	var _id = 0;
	function loans(id,obj){
		//if(confirm("是否确认购买？")){
			_id = id;
			xingwin_play();
			$(".xingwin3").hide();
			//$("#cpcx").hide();
			$.post("${ctx}/auth/project/checkLoans",{id:id},function(data){
				var obj = data.split(",");
				$(".span2").each(function(index,item){
					$(item).html(obj[index]);
				});
				if(obj[0]==obj[1]){
					$(".xingwin3").show();
					//$("#cpcx").show();
				}
			});
			//$(obj).unbind("click");
			//$(obj).removeAttr("onclick");
			//$.post("${ctx}/auth/huifu/Loans",{id:id},function(data){
			//	$(obj).val("打款中");
			//});
		//}	
	}
	function reloans(){
		$.post("${ctx}/auth/huifu/Loans",{id:_id},function(data){
			alert("发送中，请稍后查询发送状态");
		});
	}
	function searchState(){
		$.ajax({
		    type:"POST",
		    url:"${ctx}/auth/project/transState",
		    data:"id="+_id,
		    async: false,
		    success:function(msg){
					alert("发送中，请稍后查询发送状态");
					loans(_id,null);
			}
		});
	}
	function takeOff(id){
	 if(confirm("您确定要下架吗？")){
		$.post("${ctx}/auth/project/takeOff",{id:id},function(data){
			if(data=="error"){
				alert("该产品已经有用户投标~请先撤销产品。")
			}else if(data=="success"){
				alert("成功下架")
				window.location.href=window.location.href
			}
		});
	 }
	}
	function changeState(){
		if(confirm("是否确认修正产品信息？")){
			if($("#time1").val()!=""&&$("#time2").val()!=""&&$("#time3").val()!=""){
				$.post("${ctx}/auth/project/resetStartEndTime",{id:_id,time1:$("#time1").val(),time2:$("#time2").val(),time3:$("#time3").val()},function(data){
					if(confirm("修改成功，是否生成还款计划？")){
						$.post("${ctx}/auth/project/createRepaymentPlan",{id:_id},function(data){
							if(data=="outOfDate"){
								alert("已经开始还款 重新生成还款计划被拒绝。");
							}else if(data=="auto"){
								alert("本产品设置为自定义还款计划，请前往还款页面手动插入还款计划。")
							}else if(data=="sended"){
								alert("还款计划生成成功。开始生成佣金记录");
								//返还佣金
								$.ajax({
				                type:"POST",
				                url:"${ctx}/auth/brokerage/add",
				                data:"projectid="+_id,
				                 async: false,
				                  success:function(msg){
								    if(msg==1){
								     alert("佣金生成成功");
								    }else{
								     alert("佣金生成失败");
								     window.location.href=window.location.href;
								    }
									}	
								});
									//发送短信通知
							$.ajax({
			                type:"POST",
			                url:"${ctx}/auth/brokerage/sendmes",
			                data:"projectid="+_id,
			                 async: false,
			                  success:function(msg){
							      if(msg==1){
							     alert("发送消息成功");
							    }else{
							     alert("发送消息失败");
							    }
				}
			});
			
							}else{
								alert("还款计划生成失败。");
							}
						});
					}
					xingwin_close();
					
				});
			}
		}
	}
	function returnMoney(){
		if(confirm("是否确认退回本金？")){
			$.post("${ctx}/auth/project/returnMoney",{id:_id},function(data){
				if(data=="ok"){
					alert("本金还款计划生成成功，生成佣金状态，请稍后。")
					$.post("${ctx}/auth/brokerage/update",{id:_id},function(data){
					if(data=="ok"){
						alert("佣金状态更改成功");
						window.location.href=window.location.href;
					}
				});
				}else{
					alert("计划生成失败");
				}
			})
		}
	}
	//撤销产品
	function cancelPro(id){
if(confirm("是否撤销产品")){
window.open("${ctx }/auth/project/cancelProduct?projectid="+id);
}
	}
	//撤销产品
	function cancelProb(){
	if(confirm("是否撤销产品")){
    window.open("${ctx }/auth/project/cancelProduct?projectid="+_id);
}	
	}
	//产品推荐 projectid:产品ID,mendval:推荐的值，Y或者N 
	function recommend(projectid,mendval){
		$.post("${ctx}/auth/project/updatePro?id="+projectid+"&recommend="+mendval,function(data){
			if(data==true){
			 alert("操作成功");
			 window.location.reload();
			 }else{
			 alert("操作失败,请稍后重试，或者联系开发人员");
			 }
			});
	}
	
</script>
</head>

<body>
	<form action="${ctx }/auth/project/preList" method="post" id="pageForm">
		
	</form>
	<div style="background:#FFFFFF;">
		<div class="admincp_r1">
			<span>产品管理</span>
		</div>
		<div class="admincp_r2">
			<!-- 产品开始 -->
			<c:forEach items="${page.result}" var="project" varStatus="index">
			<div class="admincp_r2_main">
				<div class="admincp_r2_main1">
					<!-- 进度环开始 -->
						<div class="admincp_r2_main1_a">
							<input class="knob" data-height="70" data-width="70" data-fgColor="#ea4c59" data-thickness=".15" data-readOnly=true value="${project.flag}" data-min="0" data-max="100.01" style="display:none; width:0px;visibility:hidden;"/>
						
							<div class="admincp_r2_main1_b" >${project.flag}%</div>
						</div>
					
				<div style="height: 60px">
					<c:choose>
							<c:when test="${project.recommend!=null&&project.recommend=='Y'}">
						 <a href="javascript:recommend('${project.id}','N')" > <img  src="${ctx}/images/norecom.png"/>	</a>
							</c:when>
							<c:otherwise>
						 <a href="javascript:recommend('${project.id}','Y')" > <img  src="${ctx}/images/recom.png"/>	</a>
							</c:otherwise>
						</c:choose>
					<a data-toggle="modal" href="#forgetform" onclick="clearHtml('${project.id }');">增加</a>
				</div>
					
				</div>
			
				
				<div class="admincp_r2_main2">
					<div class="admincp_r2_main2_a">			
						<a target="_blank" href="${ctx }/project/view?id=${project.id }&tag=1">
							<cc:subLength var="${project.name}" length="18"/>	
						</a>						
					  <c:choose>
						<c:when test="${project.state==0}">
							<img src="${ctx}/images/icon_mujizhong.png" />
						</c:when>
						<c:when test="${project.state==3}">
							<img src="${ctx}/images/icon_mujizhong2.png" />
						</c:when>
						<c:when test="${project.state==1}">
							<img src="${ctx}/images/icon_mujizhong3.png" />
						</c:when>
						<c:when test="${project.state==2}">
							<img src="${ctx}/images/icon_mujizhong4.png" />
						</c:when>
					   </c:choose>
					   <!--  a href="${ctx }/auth/project/tenderDetail?id=${project.id }">投标详情</a>	-->					
					</div>
					<div class="admincp_r2_main2_b">
						发布日期：${project.publictime}&nbsp;&nbsp;&nbsp;&nbsp;投资期限：${project.time_limit}个月&nbsp;&nbsp;&nbsp;&nbsp;募集金额：${project.total_money/10000 }万
					</div>
					<div class="admincp_r2_main2_c">
						募集时间：${project.start_time}到${project.end_time}&nbsp;&nbsp;&nbsp;&nbsp;预期年化率：<font>${project.rate }%</font>
					</div>
				</div>
				<div class="admincp_r2_main3">
					<!-- 审核button -->
					<div class="admincp_r2_main3">
						<c:choose>
							<c:when test="${project.flag<100&&project.end_time<nowDate&&project.state==0}">
								<input class='admincp_r2_main3_input1' type="button" value="产品撤销" onclick="cancelPro('${project.id }')" />
							</c:when>
							<c:when test="${project.flag<100&&project.end_time<nowDate&&project.state==4}">
								<input class='admincp_r2_main3_input2' type="button" value="产品撤销" />
							</c:when>
							<c:when test="${project.flag==100&&project.state==3}">
								<input class='admincp_r2_main3_input1' onclick='loans(${project.id },this)' type="button" value="放款中" style="cursor:pointer;" />
							</c:when>
							<c:when test="${project.flag==100&&project.state==1}">
								<input class='admincp_r2_main3_input2' type="button" value="还款中" />
							</c:when>
							<c:when test="${project.flag==100&&project.state==0}">
								<input class='admincp_r2_main3_input1' onclick='loans(${project.id },this)' type="button" value="申请购买" style="cursor:pointer;" />
							</c:when>
							<c:when test="${project.flag<100&&project.state==0}">
								<input class='admincp_r2_main3_input2' type="button" value="申请购买" />
							</c:when>
							 <c:when test="${project.state==2}">
								<input class='admincp_r2_main3_input2' type="button" value="已还款" />
							</c:when>
							<c:when test="${project.state==4}">
								<input class='admincp_r2_main3_input2' type="button" value="已撤销" " />
							</c:when>
							<c:otherwise>
								<input class='admincp_r2_main3_input2' type="button" value="申请购买" />
							</c:otherwise>
						</c:choose>
						<c:if test="${project.state<=0||project.state==2||project.state==4}">
						<input class='admincp_r2_main3_input1' type="button" value="产品下架" onclick="takeOff('${project.id }')" />
						</c:if>
						<!-- <input class="admincp_r2_main3_input1" type="button" value="产品上线"/>
						<input class="admincp_r2_main3_input1" type="button" value="产品下线"/> -->
					</div>
				</div>
			</div>
			</c:forEach>
			<!-- 产品结束 -->
			<jsp:include page="../common/page.jsp"></jsp:include>
		</div>
	</div>
	<div class="xing" id="xing" style="display:none;">
		<div class="xingwin">
			<div class="xingwin_close"><a href="javascript:void(0);" onclick="window.location.href=window.location.href;"><img src="${ctx}/images/icon_close.png" /></a></div>
			<div class="xingwin1">
				<span class="span1">订单总数：</span><span class="span2">0</span>
			</div>
			<div class="xingwin2">
				<span class="span1">成功订单：</span><span class="span2">0</span>
			</div>
			<div class="xingwin2">
				<span class="span1">失败订单：</span><span class="span2">0</span>
			</div>
			<div class="xingwin2">
				<a href="javascript:reloans();"><img src="${ctx}/images/icon_cxxd.png" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:searchState()"><img src="${ctx}/images/icon_sdcx.png" /></a>
				<!-- <a id="cpcx" style="display:none;" href="javascript:void(0)"><input  class='admincp_r2_main3_input1' type="button" value="产品撤销" onclick="cancelProb()" /></a> -->
			</div>
			
			<div class="xingwin3" style="display:none;">
				<span>产品时间修正</span><br />
				计息时间<input type="text" id="time3" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly"/><br/>
				开始&nbsp;
				<input type="text" id="time1" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly"/>&nbsp;-&nbsp;
				<input type="text" id="time2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly"/>&nbsp;截止<br />
				<a href="javascript:changeState();">
					<img src="${ctx}/images/icon_queding.png" />
				</a>
			</div>
			<div class="xingwin3" style="display:none;height:90px;" >
				<span>产品购买失败退还用户本金</span><br />
				<a href="javascript:returnMoney();">
					<img src="${ctx}/images/icon_queding.png" />
				</a>
			</div>
		</div>
	</div>
	
	
<div class="modal" id="forgetform">
	<a class="close" data-dismiss="modal">×</a>
	<h1>追加投资</h1>
	<div class="forgot-form">
		尚能追加金额为：<span id="moneyAllow"></span>元
		<span style="width:94%;" id="newAdd">
		<input name="username" value="" placeholder="用户名" style="width:35%;"/>
		<input name="money" value="" placeholder="金额" style="width:35%;margin-left:10px;"/>
		</span>
		<!-- <span style="cursor:pointer;" onclick="addT()">+</span> -->
		<div class="clearfix"></div>
		<input type="button" onclick="addTender()" class="forgot button-blue" value="追加投资"/>
	</div>
</div>
	<script language="javascript">
		var __id = 0;
		var _html="";
		_html+="<input name='username' value='' placeholder='用户名' style='width:35%;' onblur='checklength(this)'/>";
		_html+="<input name='money' value='' placeholder='金额' style='width:35%;margin-left:10px;'/>";
		function addT(){
			$("#newAdd").append(_html);
		}
		function checklength(obj){
			if($(obj).val().length<6||$(obj).val().length>16||$(obj).val().indexOf(",")!=-1){
				//$(obj).focus();
				//alert("用户名长度需保持在6-16位.不能包含,等特殊符号");
			}
		}
		function addTender(){
			var username="";
			var money="";
			var tag = "";
			var allowMoney = $("#moneyAllow").html();
			var sum = 0;
			$("input[name='username']").each(function(index,item){
				if($(item).val().length<6||$(item).val().length>16||$(item).val().indexOf(",")!=-1){
					$(item).focus();
					tag+="1";
					alert("用户名长度需保持在6-16位.不能包含,等特殊符号");
					return ;
				}
				username+=$(item).val()+","
			});
			if(username!=""){
				username=username.substring(0,username.length-1);
			}
			$("input[name='money']").each(function(index,item){
				money+=$(item).val()+","
				sum += parseFloat(money);
			});
			if(sum>allowMoney){
				tag+="1";
				alert("追加投资额超出资金缺口")
			}
			if(money!=""){
				money=money.substring(0,money.length-1);
			}
			if(tag==""&&username!=""&&money!=""){
				$.post("${ctx}/auth/project/addTender",{id:__id,_username:username,_money:money},function(data){
					alert("添加成功");
					window.location.href=window.location.href;
				});
			}
		}
		function clearHtml(id){
			$.post("${ctx}/auth/project/checkMoneyAllow",{id:id},function(data){
				$("#moneyAllow").html(data);
			});
			__id = id;
			$("#newAdd").html(_html);
		}
		function newAdd(){
			$(_html).appendTo($("#newAdd"))
		}
		function xingwin_close(){
			var xing = document.getElementById('xing');
			xing.style.display="none";	
		}
		function xingwin_play(){
			var xing = document.getElementById('xing');
			xing.style.display="block";	
		}
	</script>
</body>
</html>