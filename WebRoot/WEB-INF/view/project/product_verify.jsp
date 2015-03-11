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
<link href="${ctx }/css/admincp2.css" rel="stylesheet" type="text/css" />
<link  href="${ctx }/css/alertStyle.css"rel="stylesheet" type="text/css"/>
<script src="${ctx }/js/jquery-1.7.js"></script>
<script src="${ctx }/js/jquery.knob.js"></script>
<!--[if lt IE 9]>
<script src="${ctx }/js/html5.js" type="text/javascript"></script>
<script src="${ctx }/js/excanvas.js" type="text/javascript"></script>
<script src="${ctx }/js/excanvas.compiled.js" type="text/javascript"></script>
<![endif]-->
<script>
            $(function() {
                $(".knob").knob();

                // Example of infinite knob, iPod click wheel
                var val,up=0,down=0,i=0
                    ,$idir = $("div.idir")
                    ,$ival = $("div.ival")
                    ,incr = function() { i++; $idir.show().html("+").fadeOut(); $ival.html(i); }
                    ,decr = function() { i--; $idir.show().html("-").fadeOut(); $ival.html(i); };
                $("input.infinite").knob(
                                    {
                                    'min':0
                                    ,'max':20
                                    ,'stopper':false
                                    ,'change':function(v){
                                                if(val>v){
                                                    if(up){
                                                        decr();
                                                        up=0;
                                                    }else{up=1;down=0;}
                                                }else{
                                                    if(down){
                                                        incr();
                                                        down=0;
                                                    }else{down=1;up=0;}
                                                }
                                                val=v;
                                            }
                                    }
                                    );


            });
  </script>
  <script type="text/javascript" src="<%=path %>/js/base.js"></script>
<title>1717tou.com</title>
</head>

<body>
	<form action="${ctx }/auth/project/preVerify" method="post" id="pageForm">
	</form>
	<div style="background:#FFFFFF;">
		<div class="admincp_r1"><span>产品审核</span></div>
		<div class="admincp_r2">
			<!-- 产品开始 -->
			<c:forEach items="${page.result}" var="_project" varStatus="index">
			<div class="admincp_r2_main">
				<div class="admincp_r2_main1">
					<!-- 进度环开始 -->
							<div class="admincp_r2_main1_a">
								<input class="knob" data-height="70" data-width="70" data-fgColor="#ea4c59" data-thickness=".15" data-readOnly=true value="${_project.flag}" data-min="0" data-max="100.01" style="display:none; width:0px;visibility:hidden;"/>
							
								<div class="admincp_r2_main1_b" >${_project.flag}%</div>
							</div>
					
				</div>
				<div class="admincp_r2_main2">
					<div class="admincp_r2_main2_a">
						<a href="${ctx }/auth/project/toAddPre?id=${_project.id }&tag=1">
						<cc:subLength var="${_project.name}" length="18"/>
						</a>
						<a target="_blank" href="${ctx }/project/view?id=${_project.id }&tag=1">(预览)</a>
					</div>
					<div class="admincp_r2_main2_b">
						创建日期：${_project.createTime}&nbsp;&nbsp;&nbsp;&nbsp;投资期限：${_project.time_limit}个月&nbsp;&nbsp;&nbsp;&nbsp;募集金额：${_project.total_money/10000 }万
					</div>
					<div class="admincp_r2_main2_c">
						募集时间：${_project.start_time}到${_project.end_time}&nbsp;&nbsp;&nbsp;&nbsp;预期年化率：<font>${_project.rate }%</font>
					</div>
				</div>
				<div class="admincp_r2_main3">
					<!-- 审核button -->
					<c:choose>
		    			<c:when test="${_project.state==-1}">
		    				<a href="${ctx }/auth/project/toAddPre?id=${_project.id }&tag=11">
		    					<img src="${ctx}/images/icon_shenhe1.png" />
		    				</a>
		    			</c:when>
		    			<c:when test="${_project.state!=-2}">
		    				<img src="${ctx}/images/icon_shenhe2.png" />  审核成功
		    			</c:when>
		    			<c:when test="${_project.state==-2}">
		    				<img src="${ctx }/images/icon_shenhe3.png"/>审核失败
		    			</c:when>
		    		</c:choose>
		    		<c:if test="${_project.state == -1 || _project.state == -2}">
		    		<!-- update -->
		    		</c:if>
				</div>
			</div>
			</c:forEach>
			<!-- 产品结束 -->
			<jsp:include page="../common/page.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>