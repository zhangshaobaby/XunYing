<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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
<link href="${ctx }/css/admincp101.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/ajaxupload.3.6.js"></script>
<script type="text/javascript" src="<%=path %>/js/formVali.js"></script>
<title>1717tou.com</title>
</head>

<body>
	<div style="background:#FFFFFF;">
			<div class="admincp_r1">
				<span>受托方管理</span>
			</div>
		
		<form name="form_admincp" method="post" action="${ctx}/auth/borrower/add" onsubmit="return subForm();">
		<input type="hidden" name="id" value="${borrower.id }"/>
		<input type="hidden" name="create_user" value="${borrower.create_user}"/>
		<input type="hidden" name="createTime" value="${borrower.createTime}"/>
		<input type="hidden" name="create_username" value="${borrower.create_username}"/>
		<div class="admincp_r2">
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">登陆名</div>
				<div class="admincp_r2_name2"><input type="text" name="account" value="${borrower.account }" /></div>
				<div class="admincp_r2_name3"></div>
			</div>
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">公司名称</div>
				<div class="admincp_r2_name2"><input type="text" name="name" value="${borrower.name }" /></div>
				<div class="admincp_r2_name3"></div>
			</div>
			
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">组织机构代码</div>
				<div class="admincp_r2_name2"><input name="instuCode" value="${borrower.instuCode}"/></div>
				<div class="admincp_r2_name3"></div>
			</div>
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">营业执照编号</div>
				<div class="admincp_r2_name2"><input name="busiCode" value="${borrower.busiCode}"/></div>
				<div class="admincp_r2_name3"></div>
			</div>
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">税务登记号</div>
				<div class="admincp_r2_name2"><input name="taxCode" value="${borrower.taxCode}"/></div>
				<div class="admincp_r2_name3"></div>
			</div>
			
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">状态</div>
				<div class="admincp_r2_name2">
					<input type="radio" name="state" value="0" style="width:40px;"<c:if test="${borrower.state == 0}"> checked="checked"</c:if>/>下线
					<input type="radio" name="state" value="1" style="width:40px;"<c:if test="${borrower.state == 1}"> checked="checked"</c:if>/>上线
				</div>
				<div class="admincp_r2_name3"></div>
			</div>
			<div class="admincp_r2_button">
				<div class="admincp_r2_button1"></div>
				<div class="admincp_r2_button2"><input type="submit" name="submit1"  value="添加" /></div>
				<div class="admincp_r2_button3"></div>
			</div>
		</div>
		</form>
	</div>
</body>
</html>
