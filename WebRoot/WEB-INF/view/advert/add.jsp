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
<script type="text/javascript" src="<%=path%>/js/dateTimePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path %>/js/ajaxupload.3.6.js"></script>
<script type="text/javascript" src="<%=path %>/js/formVali.js"></script>
<title>1717tou.com</title>
<script>
	$(document).ready(function(){
       	initialNew();
    });
    function initialNew(){
		var button = $('#btnUp'), interval;
        new AjaxUpload(button, {
            //action: 'upload-test.php',文件上传服务器端执行的地址
            action: '${ctx}/auth/project/upload',
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
                button.text('点击上传');
                // enable upload button
                this.enable();
                var k = response.replace("<PRE>", "").replace("</PRE>", "");
                if (k == '-1') {
                    alert('您上传的文件太大啦!请不要超过150K');
                }
                else {
                    $("#txtFileName").val(k);
                    $("#img_view").find("img:last").attr("src", "${picpath}/"+k);
                    $("#img_view").find("img:last").prev().val(k);
                }
            }
        });
	}
</script>
</head>

<body>
	<div style="background:#FFFFFF;">
		<div class="admincp_r1"><img src="${ctx}/images/admincp_r10.png" /></div>
		<!--<div class="admincp_r3">当前位置：广告管理->广告添加</div>-->
		
		<form name="form_admincp" method="post" action="${ctx}/auth/advert/add"  onsubmit="return subForm();">
		<input type="hidden" name="id" value="${advert.id }"/>
		<div class="admincp_r2">
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">广告名称</div>
				<div class="admincp_r2_name2"><input type="text" name="name" value="${advert.name }" /></div>
				<div class="admincp_r2_name3"></div>
				
			</div>
			
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">广告url</div>
				<div class="admincp_r2_name2"><input type="text" name="href"  value="${advert.href }" /></div>
				<div class="admincp_r2_name3"></div>
				
			</div>
			
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">广告背景颜色</div>
				<div class="admincp_r2_name2"><input type="text" name="color"  value="${advert.color }" /></div>
				<div class="admincp_r2_name3"></div>
			</div>
			
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">开始时间</div>
				<div class="admincp_r2_name2"><input type="text" name="start_time" value="${advert.start_time }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly"  /></div>
				<div class="admincp_r2_name3"></div>
			</div>
			<div class="admincp_r2_name">
				<div class="admincp_r2_name1">结束时间</div>
				<div class="admincp_r2_name2"><input type="text" name="end_time" value="${advert.end_time }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly"  /></div>
				<div class="admincp_r2_name3"></div>
			</div>
		
			<!-- 图片添加信息开始-->		
			<div class="admincp_r2_file">
				<div class="admincp_r2_file1"></div>
				<div class="admincp_r2_file2">
					<div class="admincp_r2_file2_a"><span id="btnUp">点击上传</span></div>
					<div class="admincp_r2_file2_b" id="img_view">
						<input type="hidden" id="imgUrl" name="imgUrl" value="${advert.imgUrl }"/>
						<img src="${picpath}/${advert.imgUrl }" />
					</div>
				</div>
				<div class="admincp_r2_file3"></div>
			</div>
			<!-- 图片添加信息结束-->		
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