<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>债权转让列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form action="<%=path%>/creditAssign/list"></form>
<c:forEach items="${page.result}" var="item">
    <div class="span3 project-card">
                                    <div class="project-item">
                                        <div class="project-item-content">
                                            <h6 class="project-brand">
                                                <i class="guarantee-logo-rongtou"></i>
                                                <a href="<%=path%>/Project/Brand/6" class="black-link brand-title" target="_blank">河北融投</a>
                                                <span>先息后本</span>
                                                    </h6>

                                            <h4 class="project-name">
                                                <a href="<%=path%>/creditAssign/toBuyCreditAssign?id=${item.id }" class="black-link" target="_blank">债权转让项目&nbsp;${item.id }</a>
                                            </h4>
                                            <p class="project-type">
                                                <a href="<%=path%>/project/view?id=${item.project.id }" class="black-link" target="_blank">企业经营贷&nbsp;1110-1-1</a>
                                            </p>
                                            <p class="project-info">
                                                <span class="project-area">河北 </span>
                                                ·
                                                <span class="project-company">电机制造</span>
                                            </p>
                                            <div class="progress project-progress">
                                                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 5.2025%">
                                                        </div>
                                                    </div>
                                            <p class="project-info">
                                                <span class="project-current-money">500.00</span>
                                                        /
                                                        <span class="project-sum-money">9,610.70</span>
                                                    </p>
                                            <p class="project-detail">
                                                公司成立于2009年10月，注册资本2000万元。公司主营防爆电机、特种电机、非标机械电气设备的生产、销售。有经验丰富的...
                                                    </p>
                                            <div class="project-other">
                                                <div class="creditassign-other-left">
                                                    <span class="creditassign-first" title="每100份原债权折让的金额数">
                                                    0.76</span>%
                                                    <br/><small>折让比例</small>
                                                </div>
                                                <div class="creditassign-other-left">
                                                    <span class="creditassign-remain" title="原标预期年化率">11</span>%
                                                    <br/><small>原标预期年化率</small>
                                                </div>
                                                <div class="creditassign-other-right">
                                                    <span class="creditassign-remain" title="该债权剩余天数">319</span>
                                                    <br/><small>剩余天数</small>
                                                </div>
                                            </div>
                                        </div>
                                        <a class="project-item-btn" href="<%=path%>/creditAssign/toBuyCreditAssign?id=${item.id }" target="_blank">
                                            立即认购</a>
                                    </div>
                                </div>
                                </c:forEach>
                                <jsp:include page="../common/page.jsp"></jsp:include>
  </body>
</html>
