<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		<link href="<%=path%>/css/main.css" rel="stylesheet" type="text/css" />		
		<link href="<%=path%>/css/user_my_jilu.css" rel="stylesheet" type="text/css" />
		<Script src="<%=path%>/js/jquery-1.7.js"></Script>
		<script type="text/javascript" src="<%=path %>/js/base.js"></script>
		<Script src="<%=path%>/js/dateTimePicker/WdatePicker.js"></Script>
		<title>1717tou.com</title>
		<script type="text/javascript">
		function transtype(typeid){
		 $("#transtype").val(typeid);
		document.forms[0].submit();
		}
		function dateInterval(dateInterval){
		 $("#dateInterval").val(dateInterval);
		 $("#start_time").val('');
		 $("#end_time").val('');
		 document.forms[0].submit();
		}
		function  submitForm(){
		 //如果时间段提交 将最近参数置空
		 $("#dateInterval").val('');
		 document.forms[0].submit();
		}
		</script>
		<script type="text/javascript">
$(function(){
var transtype="${transtype}";
var dateInterval="${dateInterval}";
	$(".user_cont_r_2a  #zij  li").each(function(index,item){
				var oldclass=$(item).attr("class");
				if(oldclass=="li_1"&&(transtype==null||transtype=="")){
				 $(item).addClass("sel");
				}else if(oldclass=="li_2"&&(transtype=="33")){
				 $(item).addClass("sel");
				}
				else if(oldclass=="li_3"&&(transtype=="34")){
				 $(item).addClass("sel");
				}
			});
	         $(".user_cont_r_2a  #datein  li").each(function(index,item){
				var oldclass=$(item).attr("class");
				if(oldclass=="li_1"&&(dateInterval==null||dateInterval=="")){
				$(item).addClass("sel");
				}else if(oldclass=="li_2"&&(dateInterval=="7")){
				 $(item).addClass("sel");
				}
				else if(oldclass=="li_3"&&(dateInterval=="30")){
				 $(item).addClass("sel");
				}
				else if(oldclass=="li_4"&&(dateInterval=="90")){
				 $(item).addClass("sel");
				}
			    
			});
		
	});
</script>
		
	</head>

	<body>

				<div class="user_cont_rr" style="background:#FFFFFF;">
				<div class="user_cont_rrr">
					    <div class="user_cont_r_1"><span>资金记录</span></div>
						<div class="user_cont_r_2"> 
                         <div class="user_cont_r_2a">
                           <ul id="zij">
							<li class="li_1"><a href="javascript:transtype('')" >资金明细</a></li>
		                    <li class="li_2"><a href="javascript:transtype('33')" >充值记录</a></li>
		                   <li class="li_3"><a href="javascript:transtype('34')" >提现记录</a></li>															
							</ul>  
                             <ul id="datein">
								<li class="li_1"><a href="javascript:dateInterval('')" >全部</a></li>
			<li class="li_2"><a href="javascript:dateInterval('7')" >最近一周</a></li>
			<li class="li_3"><a href="javascript:dateInterval('30')" >一个月</a></li>
			<li class="li_4"><a href="javascript:dateInterval('90')" >三个月</a></li>															
							</ul>  
                           </div>
                           <div class="user_cont_r_2b">
								<form name="form1" action="<%=path%>/user/translist"
						method="post">
						<input  id="dateInterval"  type="hidden" value="${dateInterval}"  name="dateInterval"/>
						<input  id="transtype"  type="hidden" value="${transtype}"  name="transtype"/>
						<span> <input onClick='WdatePicker()' type="text" id="start_time"
								name="start_time" class="input_1" value="${start_time }" /> - <input
								onClick='WdatePicker()' type="text" name="end_time" id="end_time"
								class="input_1" value="${end_time }" /><img src="<%=path%>/images/search.png" onclick="submitForm()" /> 
							</span>
					</form>
							</div>
						  </div>   
                          
                          <div class="user_cont_r_3">
							<table class="user_cont_r_3b" cellspacing=0>
                               <tr class="tittle">
                                <th class="user_cont_r_3b1">时间</th>
                                <th class="user_cont_r_3b2">交易类型|单号</th>
								<th class="user_cont_r_3b3">资金（元）</th>
								<th class="user_cont_r_3b4">可用金额（元）</th>
								<th class="user_cont_r_3b5">摘要</th>
                               </tr>
                               <!-- 交易记录循环开始-->
                              <c:forEach var="item" items="${page.result}">
					  <tr class="user_cont_r_3c">
						<td class="user_cont_r_3c1">
							${item.createTime}
						</td>
					
						<td class="user_cont_r_3c2">
								${item.type.dictName}
								<br />
						  <jsp:useBean id="now" class="java.util.Date" /> 
						<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyMMdd" />${item.ordId}
						</td>
						<c:choose>
						<c:when test="${item.type.remark==null}">
						<td class="user_cont_r_3c3">
                         		${item.type.remark}${item.transAmt}   
						</td>
						</c:when>
						<c:when test="${item.type.remark=='+'}">
						<td class="user_cont_r_3c3" style="color: green;">
                         		${item.type.remark}${item.transAmt}   
						</td>
						</c:when>
						<c:otherwise>
						<td class="user_cont_r_3c3" style="color: red;">
                         		${item.type.remark}${item.transAmt}   
						</td>
						</c:otherwise>
						</c:choose>
						
						<td class="user_cont_r_3c4">
							${item.avlBal}
						</td>
						<td class="user_cont_r_2c5">
							${item.summary}
						</td>
					</tr>
				</c:forEach>
                              <!-- 交易记录循环结束-->
                            </table>														
							
				<jsp:include page="../common/page.jsp"></jsp:include>

						</div>	
						</div>
					
				</div>

	</body>
</html>


