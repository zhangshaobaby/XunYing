<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	function change(url){
	 window.location.href=url;
	}
 
	function descshow(){
		var admincp_r2_b_action = document.getElementById('admincp_r2_b_action');
		admincp_r2_b_action.style.display='block';
		
	
	}
	
	function deschide(){
		var admincp_r2_b_action = document.getElementById('admincp_r2_b_action');
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
 
			<div style="background:#FFFFFF;">
				<div class="admincp_r1"><span>资金审核</span></div>
				<div class="admincp_r_nav">
					<ul id="tabmenu">
						<li  onclick="change('<%=path%>/cash/auth/waitEnaminecashApplaylist')">提现</li>
						<li class="on" onclick="change('<%=path%>//auth/borrower/waitHuankuanApplaylist')">还款</li>
					</ul>
				</div>
						
				<div class="admincp_r2" id="content1">
					<div class="admincp_r2_c">
						<div class="admincp_r2_c1">产品描述</div>
						<div class="admincp_r2_c2">提款金额（￥）</div>
						<div class="admincp_r2_c3">时间</div>
						<div class="admincp_r2_c4">操作</div>
					</div>
					
					<!-- 循环开始 -->
					<div class="admincp_r2_d">
						<div class="admincp_r2_d1">
							中荣信托-中城建十二局信托贷款集合资金信托<br />
							产品批次：CX20140819 还款属性：利息
						</div>
						<div class="admincp_r2_d2">123456</div>
						<div class="admincp_r2_d3">2014/08/19</div>
						<div class="admincp_r2_d4">
							<div class="admincp_r2_d4_l">
								<img src="images/button_tongguo1.png" style="position:absolute; left:0px;" onmousemove="descshow1()"
								 onmouseout="deschide1()"/>
								<img src="images/button_tongguo.png" />
							</div>
							<div class="admincp_r2_d4_r"><img src="images/button_butongguo.png" /></div>
							<div class="admincp_r2_d_action" id="admincp_r2_d_action" style="display:none;">这里是上次操作的描述这里是上次操作的描述这里是上次操作的描述这里是上次操作的描述</div>
						</div>
						
						
					</div>
					
					<div class="admincp_r2_d">
						<div class="admincp_r2_d1">
							中荣信托-中城建十二局信托贷款集合资金信托<br />
							产品批次：CX20140819 还款属性：利息
						</div>
						<div class="admincp_r2_d2">123456</div>
						<div class="admincp_r2_d3">2014/08/19</div>
						<div class="admincp_r2_d4">
							<a href="#"><img src="images/button_tongguo.png" /></a>&nbsp;
							<a href="#"><img src="images/button_butongguo.png" /></a>
						</div>
					</div>
					<!-- 循环结束 -->
					
					
					
 
					<div class="admincp_r4">分页 1 2 3 4 5 7</div>
 
				</div>
				
				
				
				
			</div>
 
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
	
</body>
</html>




