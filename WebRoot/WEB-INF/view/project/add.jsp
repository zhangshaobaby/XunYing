<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
  String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<link href="<%=path %>/css/erp_sys.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/dateTimePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path %>/js/ajaxupload.3.6.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
        var button = $('#btnUp'), interval;
        new AjaxUpload(button, {
            //action: 'upload-test.php',文件上传服务器端执行的地址
            action: '${ctx}/project/upload/${model.cid}',
            name: 'files',
            onSubmit: function (file, ext) {
                if (!(ext && /^(jpg|jpeg|JPG|JPEG)$/.test(ext))) {
                    alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
                    return false;
                }

                // change button text, when user selects file
                button.text('上传中');

                // If you want to allow uploading only 1 file at time,
                // you can disable upload button
                this.disable();

                // Uploding -> Uploading. -> Uploading...
                interval = window.setInterval(function () {
                    var text = button.text();
                    if (text.length < 10) {
                        button.text(text + '|');
                    } else {
                        button.text('上传中');
                    }
                }, 200);
            },
            onComplete: function (file, response) {
                //file 本地文件名称，response 服务器端传回的信息
                window.clearInterval(interval);
                // enable upload button
                this.enable();
                var k = response.replace("<PRE>", "").replace("</PRE>", "");
                if (k == '-1') {
                    alert('您上传的文件太大啦!请不要超过150K');
                }
                else {
                    $("#txtFileName").val(k);
                    $("<img style='width:60px;height:80px;'/>").appendTo($('#imglist')).attr("src", "${picpath}/"+k);
                }
            }
        });

    });
</script>
</head>

<body >
 <div class="r_cs">
 <h2>添加产品</h2>
 <form action="<%=path %>/project/add" id="queryForm" name="queryForm" method="post">
 	<input type="hidden" name="id" value="${model.id }"/><br/>
 	<input type="hidden" name="flag" value="1"/><br/>
	<input type="hidden" name="createTime" value="${model.createTime }"/><br/>
	产品名称：<input name="name" value="${model.name }"/><br/>
	产品状态:众筹中/还款中/成功退出<input name="state" value="${model.state }"/><br/>
	收益率:<input name="rate" value="${model.rate }"/><br/>
	产品分类:<input name="type" value="${model.type }"/><br/>
	收益分配:<input name="rate_type" value="${model.rate_type }"/><br/>
	募集开始时间:<input name="start_time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly" value="${model.start_time }"/><br/>
	募集结束时间:<input name="end_time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly" value="${model.end_time }"/><br/>
	还款时间:<input name="repay_time" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly" value="${model.repay_time }"/><br/>
	产品期限:<input name="time_limit" value="${model.time_limit }"/>月<br/>
	总募集金额:<input name="total_money" value="${model.total_money }"/><br/>
	已募集:<input name="now_money" value="${model.now_money }"/><br/>
	单笔投资下限:<input name="lowest_money" value="${model.lowest_money }"/><br/>
	单笔投资上限:<input name="highest_money" value="${model.highest_money }"/><br/>
	项目描述:<input name="content" value="${model.content }"/><br/>
	风控措施:<input name="control_content" value="${model.control_content }"/><br/>
	创建人:<input name="create_user" value="${model.create_user }"/><br/>
	上传图片：<input type="text" id="txtFileName" /><div id="btnUp" style="width:300px;" class=btnsubmit >浏览</div>
	<div id="imglist"></div>
	<input type="submit"/>
</form>
 </div>
</body>
</html>
