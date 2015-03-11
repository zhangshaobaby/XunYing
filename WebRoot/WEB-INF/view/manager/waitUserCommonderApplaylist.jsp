<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="<%=path%>/css/index.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/admincp4.css" rel="stylesheet" type="text/css" />
<Script src="<%=path%>/js/jquery-1.7.js"></Script>
		<script type="text/javascript" src="<%=path %>/js/base.js"></script>
<script type="text/javascript">
		function   changestate(id,state,el) {
		//设置路径为空
		$(el).attr("href",";");
		jQuery.ajax( {
		type : "POST",
		url : "<%=path%>/cash/auth/changeStateforUserComonder?id="+id+"&state.id="+state,
		success : function(result) {
			 if(result==true){
			  alert("操作成功");
			 }else{
			 alert("操作失败,请稍后重试");
			 }
			 window.location.reload();
		}
	});
		}
	function change(url){
	 window.location.href=url;
	}
 
	function descshow(id){
		var admincp_r2_b_action = document.getElementById('admincp_r2_b_action'+id);
		admincp_r2_b_action.style.display='block';
		
	
	}
	
	function deschide(id){
		var admincp_r2_b_action = document.getElementById('admincp_r2_b_action'+id);
		admincp_r2_b_action.style.display='none';
		
	
	}
	function descshow1(){
		var admincp_r2_d_action = document.getElementById('admincp_r2_d_action');
		admincp_r2_d_action.style.display='block';
		
	
	}
	
	function deschide1(){
		var admincp_r2_d_action = document.getElementById('admincp_r2_d_action');
		admincp_r2_d_action.style.display='none';
		
	
	}
 
  
  </script>
 
<title>1717tou.com</title>
</head>
 
<body>
<form action="<%=path%>/cash/auth/waitUserCommonderApplaylist" method="post" id="form1" name="form1">
			<div style="background:#FFFFFF;">
				<div class="admincp_r1"><span>推荐奖励审核</span></div>
				
				<div style="margin-left: 200px">证件号： <input name="identification"  value="${identification }"  />项目名称： <input name="proname"  value="${proname}" /> <input type="button" value="搜索" onclick="document.form1.submit()"  /></div>
				
				<div class="admincp_r2" id="content0">
					<div class="admincp_r2_a">
					    <div class="admincp_r2_a0">用户姓名</div>
						<div class="admincp_r2_a1" style="width:175px">用户证件号</div>
						<div class="admincp_r2_a2" style="width:100px">提款金额（￥）</div>
						<div class="admincp_r2_a3">项目名称</div>
						<div class="admincp_r2_a4">时间</div>
						<div class="admincp_r2_a5">操作</div>	
					</div>
					
					<!-- 循环开始 -->
					<c:forEach var="item" items="${page.result}">
					<div class="admincp_r2_b">
					    <div class="admincp_r2_b0" title="${item.revenueUser.phone}" >
                         ${item.revenueUser.realName}
					    </div>
						<div class="admincp_r2_b1" title="${item.revenueUser.identification}" style="width:175px">
						${item.revenueUser.identification}
						</div>
						<div class="admincp_r2_b2" style="width:100px">${item.brokerage}</div>
						<div class="admincp_r2_b3">
							<cc:subLength var="${item.project.name}" length="6"/>
						</div>
						<div class="admincp_r2_b4">${item.createTime}</div>
						<div class="admincp_r2_b5">
							<div class="admincp_r2_b5_l">
								<a href="javascript:changestate('${item.id}','63',this)"><img src="<%=path %>/images/button_tongguo.png" /></a>
							</div>
							<a href="javascript:changestate('${item.id}','62',this)"><div class="admincp_r2_b5_r"><img src="<%=path %>/images/button_butongguo.png" /></div>
							</a>
						</div>
							
					</div>
						</c:forEach>
					
					<!-- 循环结束 -->
   <jsp:include page="../common/page.jsp"></jsp:include>
			
<div class="yiyi" style="display:none;">
	<div class="yiyiwin">
		<div class="yiyiwin1">
			<div class="yiyiwin1_l">请输入意见：</div>
			<div class="yiyiwin1_r"><textarea name="cont" ></textarea></div>
		</div>
		<div class="yiyiwin2">
			<a href="#"><img src="images/icon_queding1.png" /></a>
		</div>
		
	</div>
</div>
	</form>
</body>
</html>




