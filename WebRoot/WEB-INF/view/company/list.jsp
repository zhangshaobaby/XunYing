<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
  String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title></title>
<script src="<%=path %>/js/jquery-1.7.js"></script>
<script>
	function selectAll(obj){
		$("input[name='ids']").each(function(index,item){
			if($(obj).attr("checked")=="checked")
				$(item).attr("checked","checked");
			else
				$(item).removeAttr("checked")
		});
	}
	function deleteAll(){
		var ids = "";
		$("input[name='ids']").each(function(index,item){
			if($(item).attr("checked")=="checked")
				ids+=$(item).val()+","
		});
		if(ids.length>0){
			ids=ids.substring(0,ids.length-1);
			$.post("<%=path%>/company/delete",{ids:ids},function(data){
				alert("删除成功");
				window.location.href=window.location.href;				
			});
		}
	}
	function deletes(id){
		alert(id)
		$.post("<%=path%>/company/delete",{ids:id},function(data){
			alert("删除成功");
			window.location.href=window.location.href;				
		});
	}
</script>
</head>
<body >
 <div class="r_cs">
 <h2>公司列表</h2>
 <form action="<%=path %>/company/list" id="queryForm" name="queryForm" method="post">
  <div class="searchform">
  <input type="text"  name="company_name" id="searchname"  value="${company.company_name}" onclick="showWrite('searchname')"/>
	<input type="button" value="搜索" class="searchbox" onclick="query()"/>
  </div>
 </form>
 <input type="button" value="批量删除" onclick="deleteAll()"/>
 <table width="98%" border="1px solid" align="center" cellpadding="5" cellspacing="0" class="bge">
  <tr>
  	<th><input type="checkbox"onchange="selectAll(this)"/></th>
    <th>名称</th>
    <th>操作</th>
  </tr>
  <c:forEach items="${page.result}" var="company" varStatus="index">
   <tr style="text-align:center;">
   	<td><input type="checkbox" value="${company.id }" name="ids"/></td>
    <td>
    	<p>${company.company_name }</p>
    </td>
    <td>
    	<a href="<%=path %>/company/toAdd?id=${company.id}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
    	<a href="javascript:void(0);" onclick="deletes(${company.id})">删除</a>
    </td>
     </tr>
  </c:forEach>
</table>
</div>
</body>
</html>