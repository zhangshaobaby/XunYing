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
<link href="${ctx }/baiduEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx }/baiduEditor/third-party/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/baiduEditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/baiduEditor/umeditor.min.js"></script>
<script type="text/javascript" src="${ctx }/baiduEditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=path %>/js/ajaxupload.3.6.js"></script>


<title>1717tou.com</title>
<script>
	$(document).ready(function(){
		initialNew();
		
		 var um = UM.getEditor('myEditor');
	})
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
                    $("#img_view").find(".addcp_cont_file2_c").show();
                }
            }
        });
	}
	function removeUp(obj){
		$(obj).parent().find(".addcp_cont_file2_c1").find("img").attr("src","");
		$("#logo").val("");
		$(obj).parent().hide();
	}
	function subForm(){
		var boolean = true;
		$("#company_content").val(UM.getEditor('myEditor').getContent());
		$("input[type='text']").each(function(index,item){
			if($(item).val()==""){
				$(item).focus();
				$(item).css("border","1px solid red")
				boolean = false;
			}else{
				$(item).css("border","1px solid #999999")
			}
		});
		return boolean;
	}
</script>
</head>

<body>
	<!-- 产品发布开始 -->
	
	<form action="<%=path %>/auth/company/add" id="queryForm" name="queryForm" method="post" onsubmit="return subForm();">
		<input type="hidden" name="id" value="${model.id }"/>
	 	<input type="hidden" name="flag" value="0"/>
		<input type="hidden" name="createTime" value="${model.createTime }"/>
		<input type="hidden"  name="type" value="${model.type}" />
		<div class="addcp">
			<div class="addcp_top"><span>添加公司</span></div>
			<div class="addcp_cont">
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">公司名称</div>
					<div class="addcp_cont_name2">
						<input type="text" name="company_name" value="${model.company_name }" style="width:400px;"/>
					</div>
					<div class="addcp_cont_name3"></div>
					
				</div>

				<div class="addcp_cont_info">
					<div class="addcp_cont_info1">公司详情</div>
					<div class="addcp_cont_info2">
					<script type="text/plain" id="myEditor" style="width:550px;min-height:210px;max-height:210px;">
					<p>${model.company_content}</p>
					</script>
					<input type="hidden" name="company_content" id="company_content"/>
					</div>
					<div class="addcp_cont_info3"></div>
				</div>
				<div id="upload_container">
						<!-- 新增一组内容开始 -->
						<div class="addcp_cont_file">
							<div class="addcp_cont_file1">公司logo</div>
							<div class="addcp_cont_file2">
								<div class="addcp_cont_file2_a"><span id="btnUp" onclick='uploading(this)'>点击上传</span></div>
								<div class="addcp_cont_file2_b" id="img_view">
									<!-- 新增图片开始 -->
									<div class="addcp_cont_file2_c">
										<div class="addcp_cont_file2_c1">
											<input type='hidden' id="logo" name='logo' value='${model.logo }'/>
											<img src="${picpath}/${model.logo }" />
										</div>
										<div class="addcp_cont_file2_c2" onclick="removeUp(this)">删除上传</div>
									</div>
									<!-- 新增图片结束 -->
								</div>
							</div>
							<div class="addcp_cont_file3"></div>
						</div>
						<!-- 新增一组内容结束 -->
				</div>
				<div class="addcp_cont_button">
					<div class="addcp_cont_button1"></div>
					<div class="addcp_cont_button2"><input type="submit" name="submit1"  value="发布" /></div>
					<div class="addcp_cont_button3"></div>
				</div>
				
			</div>
		</div>
	</form>
	<!-- 产品发布结束 -->
</body>
</html>