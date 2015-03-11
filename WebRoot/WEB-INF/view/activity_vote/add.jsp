<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx }/js/dateTimePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/ajaxupload.3.6.js"></script>
<script type="text/javascript" src="${ctx }/js/formVali.js"></script>
<title>1717tou.com</title>
<script>
	$(document).ready(function(){
		initialNew('1');
       	initialNew('2');
       	initialNew('3')
    });
    function initialNew(f){
    	var ss;
    	if(f=="1")
    	{
    		ss = "#btnUphead";
    	}
    	else if(f=="2")
    	{
    		ss = "#btnUp";
    	}else {
         	ss = "#btnUpShow";
    	}
    	var button = $(ss), interval;
        new AjaxUpload(button, {
            //action: 'upload-test.php',文件上传服务器端执行的地址
            action: '<cc:picUploadHost/>/activityvote/upload',
            name: 'files',
            onSubmit: function (file, ext) {
                if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF)$/.test(ext))) {
                    alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
                    return false;
                }
				var html="";
	            	html+="<div class='addcp_cont_file2_c'>";
					html+="<div class='addcp_cont_file2_c1'>";
					if(f=='1')
			    	{
			    		html+="<input type='hidden' name='headpic'><img src='${ctx}/images/filename.png' /></div>";
			    	}
			    	else if(f=='2')
			    	{
			    		html+="<input type='hidden' name='imgUrl'><img src='${ctx}/images/filename.png' /></div>";
			    	}
			    	else
			    	{
			    		html+="<input type='hidden' name='showpic'><img src='${ctx}/images/filename.png' /></div>";
			    	}
					html+="<div class='addcp_cont_file2_c2' onclick='removeUp(this)'>删除上传</div>";
					html+="</div>";
					if(f=='1')
			    	{
			    		$("#img_view_head").append(html);
			    	}
			    	else if (f=='2')
			    	{
			    		$("#img_view").append(html);
			    	}else{
			    	   $("#img_view_showpic").append(html);
			    	  }
					
					
                button.text('上传中');

                this.disable();

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
                k = k.replace("<pre>", "").replace("</pre>", "");
                if (k == '-1') {
                    alert('您上传的文件太大啦!请不要超过1G');
                }
                else {
                    $("#txtFileName").val(k);
                    if(f=='1')
			    	{
			    		$("#img_view_head").find("img:last").attr("src", "${picpath}/"+k);
                    	$("#img_view_head").find("img:last").prev().val(k);
			    	}
			    	else if(f=='2')
			    	{
			    		$("#img_view").find("img:last").attr("src", "${picpath}/"+k);
                    	$("#img_view").find("img:last").prev().val(k);
			    	}else{
			    	 $("#img_view_showpic").find("img:last").attr("src", "${picpath}/"+k);
                     $("#img_view_showpic").find("img:last").prev().val(k);
			    	}
                    
                }
            }
        });
	}
	function removeUp(obj){
		$(obj).parent().remove();
	}
</script>
</head>

<body>
	<div class="addcp">
		<div class="addcp_top"><span>女神添加</span></div>
		<!--<div class="admincp_r3">当前位置：新闻管理->新闻添加</div>-->
		
		<form name="form_admincp" method="post" action="${ctx}/activityvote/addorupdate"  onsubmit="return subForm();">
		<input type="hidden" name="id" value="${persion.id }"/>
		<div class="addcp_cont">
			<div class="addcp_cont_name">
				<div class="addcp_cont_name1">姓名</div>
				<div class="addcp_cont_name2">
					<input name="username" value="${persion.username }"/>
				</div>
				<div class="addcp_cont_name3"><span style="color:red">*</span></div>
			</div>
			<div class="addcp_cont_name">
				<div class="addcp_cont_name1">年龄</div>
				<div class="addcp_cont_name2">
					<input name="age" value="${persion.age }"/>
				</div>
				<div class="addcp_cont_name3"><span style="color:red">*</span></div>
			</div>
			<div class="addcp_cont_name">
				<div class="addcp_cont_name1">血型</div>
				<div class="addcp_cont_name2">
					<input name="blood" value="${persion.blood }"/>
				</div>
				<div class="addcp_cont_name3"><span style="color:red"></span></div>
			</div>
			
			
			
			
			<div class="addcp_cont_name">
				<div class="addcp_cont_name1">身高</div>
				<div class="addcp_cont_name2">
					<input name="height" value="${persion.height }"/>
				</div>
				<div class="addcp_cont_name3"><span style="color:red">*</span></div>
			</div>
			<div class="addcp_cont_name">
				<div class="addcp_cont_name1">星座</div>
				<div class="addcp_cont_name2">
					<input name="constellation" value="${persion.constellation }"/>
				</div>
				<div class="addcp_cont_name3"><span style="color:red">*</span></div>
			</div>
			<div class="addcp_cont_name">
				<div class="addcp_cont_name1">体重</div>
				<div class="addcp_cont_name2">
					<input name="weight"  value="${persion.weight }"/><br/>
				</div>
				<div class="addcp_cont_name3"><span style="color:red"></span></div>
			</div>
			<div class="addcp_cont_name">
				<div class="addcp_cont_name1">三围</div>
				<div class="addcp_cont_name2">
					<input name="vitalstatistics"  value="${persion.vitalstatistics }"/>
				</div>
				<div class="addcp_cont_name3"><span style="color:red">*</span></div>
			</div>
			<div class="addcp_cont_name">
				<div class="addcp_cont_name1">宣言</div>
				<div class="addcp_cont_name2">
					<input name="summery"  value="${persion.summery }"/>
				</div>
				<div class="addcp_cont_name3"><span style="color:red">*</span></div>
			</div>
			
			
			
			
			<!-- 头像添加信息结束-->	
			<!-- 图片添加信息开始-->		
			<div id="upload_container">
			<div class="addcp_cont_file">
				<div class="addcp_cont_file1"></div>
				<div class="addcp_cont_file2">
					<div class="addcp_cont_file2_a"><span id="btnUphead">点击上传头像</span></div>
					<div class="addcp_cont_file2_a"></div>
					<div class="addcp_cont_file2_b" id="img_view_head">
						<!-- 新增图片开始 -->
						<div class="addcp_cont_file2_c">
								<c:if test="${not empty persion.headpic}">
								<div class="addcp_cont_file2_c1">
									<input type='hidden' name='headpic' value='${persion.headpic }'/>
									<img width="80px" height="80px" src="${picpath}/${persion.headpic }" />
								</div>
								<div class="addcp_cont_file2_c2" onclick="removeUp(this)">删除上传</div>
								</c:if>
							</div>
						<!-- 新增图片结束 -->
					</div>
				</div>
					<div class="addcp_cont_file2">
					<div class="addcp_cont_file2_a"><span id="btnUpShow">上传展示照</span></div>
					<div class="addcp_cont_file2_a"></div>
					<div class="addcp_cont_file2_b" id="img_view_showpic">
						<!-- 新增图片开始 -->
						<div class="addcp_cont_file2_c">
								<c:if test="${not empty persion.showpic}">
								<div class="addcp_cont_file2_c1">
									<input type='hidden' name='showpic' value='${persion.showpic }'/>
									<img width="80px" height="80px" src="${picpath}/${persion.showpic }" />
								</div>
								<div class="addcp_cont_file2_c2" onclick="removeUp(this)">删除上传</div>
								</c:if>
							</div>
						<!-- 新增图片结束 -->
					</div>
				</div>
				<div class="addcp_cont_file3"></div>
			</div>
			</div>
			<!-- 头像添加信息结束-->	
			<!-- 图片添加信息开始-->		
			<div id="upload_container">
			<div class="addcp_cont_file">
				<div class="addcp_cont_file1"></div>
				<div class="addcp_cont_file2">
					<div class="addcp_cont_file2_a"><span id="btnUp">点击上传写真</span></div>
					<div class="addcp_cont_file2_a"></div>
					<div class="addcp_cont_file2_b" id="img_view">
						<!-- 新增图片开始 -->
						<c:set var="imgs" value="${fn:split(persion.imgUrl, ',')}" />
						<c:if test="${imgs[0]!=''}">
						<c:forEach items="${imgs}" var = "img" varStatus="inde">
							<div class="addcp_cont_file2_c">
								<div class="addcp_cont_file2_c1">
									<input type='hidden' name='imgUrl' value='${img }'/>
									<img src="${picpath}/${img }" onclick="this.src='${ctx}/images/filename.png'" />
								</div>
								<div class="addcp_cont_file2_c2" onclick="removeUp(this)">删除上传</div>
							</div>
						</c:forEach>
						</c:if>
						<!-- 新增图片结束 -->
					</div>
				</div>
				<div class="addcp_cont_file3"></div>
			</div>
			</div>
			<!-- 图片添加信息结束-->		
			<div class="addcp_cont_button">
				<div class="addcp_cont_button1"></div>
				<div class="addcp_cont_button2"><input type="submit" name="submit1"  value="提交" /></div>
				<div class="addcp_cont_button3"></div>
			</div>
		</div>
		</form>
	</div>
</body>
</html>
