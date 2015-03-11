<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/diaosibi_popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx }/js/dateTimePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/ajaxupload.3.6.js"></script>
<script type="text/javascript" src="${ctx }/js/formVali.js"></script>
<title>1717tou.com</title>
<script>
	$(document).ready(function(){
       	initialNew();
       	/*控制弹出层*/
       	if("${news.ptype}"==""){
		$(".unionProject").hide();
		}
		$('.theme-login').click(function(){
			$('#theme-popover-mask').css("display","block");
			$('#theme-popover').slideDown(200);
		})
		$('.theme-popover .close').click(function(){
			$('#theme-popover-mask').css("display","none");
			$('#theme-popover').slideUp(200);
		})
    });
    function initialNew(){
		var button = $('#btnUp'), interval;
        new AjaxUpload(button, {
            //action: 'upload-test.php',文件上传服务器端执行的地址
            action: '${ctx}/auth/project/upload',
            name: 'files',
            onSubmit: function (file, ext) {
                if (!(ext && /^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF)$/.test(ext))) {
                    alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
                    return false;
                }
				var html="";
	            	html+="<div class='addcp_cont_file2_c'>";
					html+="<div class='addcp_cont_file2_c1'>";
					html+="<input type='hidden' name='imgUrl'><img src='${ctx}/images/filename.png' /></div>";
					html+="<div class='addcp_cont_file2_c2' onclick='removeUp(this)'>删除上传</div>";
					html+="</div>";
					$("#img_view").append(html);
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
                k = k.replace("<pre>", "").replace("</pre>", "");
                if (k == '-1') {
                    alert('您上传的文件太大啦!请不要超过1G');
                }
                else {
                    $("#txtFileName").val(k);
                    $("#img_view").find("img:last").attr("src", "${picpath}/"+k);
                    $("#img_view").find("img:last").prev().val(k);
                }
            }
        });
	}
	function removeUp(obj){
		$(obj).parent().remove();
	}
	function getProject(pageNum){
		var ptype = $("#ptype option:selected").val();
		if(ptype!=""){
			$.post("${ctx}/auth/news/getProject",{type:ptype,currPage:pageNum},function(data){
				var html="";
				$(".projectDetail").remove();
				var ids = $("#pid").val();
				for(var i = 0 ;i < data.result.length ; i ++ ){
				//日期 屌丝 摘要
					html+="<tr class='projectDetail'>";
					html+="<td style='color:#e33340;'>"+data.result[i].name+"</td>";
					html+="<td><input type='checkbox' name='pids' ";
					if(ids.indexOf(","+data.result[i].id+",")!=-1){
						html+="checked";
					}
					html+=" value='"+data.result[i].id+"'/></td>";
					html+="</tr>";
				}
				$(html).appendTo($("#scoreTab"));
				$("#currPage").val(data.currPage);
				$("#prePage").val(data.prePage);
				$("#nextPage").val(data.nextPage);
				$("#totalPage").val(data.totalPage);
				$("#nowPage1").html(data.currPage+"/"+data.totalPage);
				$('#theme-popover-mask').css("display","block");
				$('#theme-popover').slideDown(200);
			});
		}else{
			alert("请先选择类别");
		}
	}
	function chooseProject(obj){
		if($(obj).val()=="66"){
			//展示
			$(".unionProject").show();
		}else{
			//隐藏  清空
			$("#clearType").attr("selected","selected");
			$("#pid").val("");
			$(".unionProject").hide();
		}
	}
	function sure(){
		var ids = "";
		$("input[name='pids']:checked").each(function(){
			ids+=$(this).val()+","
		});
		if(ids.length>0)
			$("#pid").val(","+ids);
	}
</script>
</head>

<body>
	<div class="addcp">
		<div class="addcp_top"><span>新闻添加</span></div>
		<!--<div class="admincp_r3">当前位置：新闻管理->新闻添加</div>-->
		<input type="hidden" name="currPage" id="currPage"/>
		<input type="hidden" name="prePage" id="prePage"/>
		<input type="hidden" name="nextPage" id="nextPage"/>
		<input type="hidden" name="totalPage" id="totalPage"/>
		<form name="form_admincp" method="post" action="${ctx}/auth/news/doAdd"  onsubmit="return subForm();">
		<input type="hidden" name="id" value="${news.id }"/>
		<div class="addcp_cont">
			<div class="addcp_cont_name">
				<div class="addcp_cont_name1">新闻名称</div>
				<div class="addcp_cont_name2">
					<input type="text" name="name" value="${news.name }" />
				</div>
				<div class="addcp_cont_name3"><span style="color:red">*</span></div>
			</div>
			<div class="addcp_cont_name">
				<div class="addcp_cont_name1">新闻分类</div>
				<div class="addcp_cont_name2">
					<select name="type" style="border:1px solid #999999;margin-top:13px;" onchange="chooseProject(this)">
						<c:forEach items="${newsType}" var="type">
							<option value="${type.id }" <c:if test="${type.id==news.type }">selected</c:if>>${type.dictName }</option>
						</c:forEach>
					</select>
				</div>
				<div class="addcp_cont_name3"><span style="color:red">*</span></div>
			</div>
			<div class="addcp_cont_name unionProject">
				<div class="addcp_cont_name1">关联产品</div>
				<div class="addcp_cont_name2">
					<select id="ptype" name="ptype" style="border:1px solid #999999;margin-top:13px;">
						<option value="" id="clearType">--请选择--</option>
						<option value="0" <c:if test="${news.ptype==0 }">selected</c:if>>信满盈</option>
						<option value="1" <c:if test="${news.ptype==1 }">selected</c:if>>资涨通</option>
						<option value="2" <c:if test="${news.ptype==2 }">selected</c:if>>金多宝</option>
						<option value="3" <c:if test="${news.ptype==3 }">selected</c:if>>屌丝宝</option>
					</select>
				</div>
				<div class="addcp_cont_name3"><span style="color:red"></span></div>
			</div>
			<div class="addcp_cont_name unionProject">
				<div class="addcp_cont_name1"></div>
				<div class="addcp_cont_name2">
					<input type="hidden" id="pid" name="pid" value="${news.pid }"/>
					<span onclick="getProject(1)">选择</span>
				</div>
				<div class="addcp_cont_name3"><span style="color:red"></span></div>
			</div>
			<div class="addcp_cont_name">
				<div class="addcp_cont_name1">新闻url</div>
				<div class="addcp_cont_name2">
					<input type="text" name="href" class="allowNull" value="${news.href }" />
				</div>
				<div class="addcp_cont_name3"><span style="color:red"></span></div>
			</div>
			<!-- 
			<div class="addcp_cont_name">
				<div class="addcp_cont_name1">排序</div>
				<div class="addcp_cont_name2">
					<input type="text" name="orderTag" value="${news.orderTag }" />
				</div>
				<div class="addcp_cont_name3"><span style="color:red">*</span></div>
			</div>
			 -->
			<div class="addcp_cont_name" style="height:300px;">
				<div class="addcp_cont_name1" style="height:300px;">新闻内容</div>
				<div class="addcp_cont_name2" style="height:300px;">
					<textarea name="content" style="height:250px;width:250px;">${news.content }</textarea>
				</div>
				<div class="addcp_cont_name3"><span style="color:red">*</span></div>
			</div>
			<div class="addcp_cont_name">
				<div class="addcp_cont_name1">开始时间</div>
				<div class="addcp_cont_name2">
					<input type="text" name="start_time" value="${news.start_time }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly"  />
				</div>
				<div class="addcp_cont_name3"><span style="color:red">*</span></div>
			</div>
			<div class="addcp_cont_name" style="display:none;">
				<div class="addcp_cont_name1">结束时间</div>
				<div class="addcp_cont_name2">
					<input type="text" name="end_time" value="2099-12-31" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" readonly="readonly"  />
				</div>
				<div class="addcp_cont_name3"><span style="color:red">*</span></div>
			</div>
			<!-- 图片添加信息开始-->		
			<div id="upload_container">
			<div class="addcp_cont_file">
				<div class="addcp_cont_file1"></div>
				<div class="addcp_cont_file2">
					<div class="addcp_cont_file2_a"><span id="btnUp">点击上传</span></div>
					<div class="addcp_cont_file2_a"></div>
					<div class="addcp_cont_file2_b" id="img_view">
						<!-- 新增图片开始 -->
						<c:set var="imgs" value="${fn:split(news.imgUrl, ',')}" />
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
				<div class="addcp_cont_button2"><input type="submit" name="submit1"  value="添加" /></div>
				<div class="addcp_cont_button3"></div>
			</div>
		</div>
		</form>
	</div>
	<!--   以下是屌丝币流水弹出层的内容-->                         
<div class="theme-popover" id="theme-popover">
	<div class="theme-poptit">
		<h3>选择绑定产品</h3>
	</div>     
	<div class="pop_middle">
		<table cellspacing=0 border="0" id="scoreTab">
			<tr class="tittle color_hui">
				<th>产品名</th>
				<th>操作</th>
			</tr>
		    <!-- 循环开始-->
		    	
			<!-- 循环结束-->
		</table>
		<div>
			<div style="float:right;margin:10px 0px;">
				<span style="color:#0060a5;">
					<span style="cursor:pointer;" onclick="getProject(1);">首页</span>
					<span style="cursor:pointer;" onclick="getProject($('#prePage').val());">上页</span>
				</span> 
				<span id="nowPage1">
					
				</span>页 
				<span style="color:#0060a5">
					<span style="cursor:pointer;" onclick="getProject($('#nextPage').val());">下页</span>
					<span style="cursor:pointer;" onclick="getProject($('#totalPage').val());">末页</span>
				</span>
			</div>
			<div style="clear:both"></div>
			<a href="javascript:;" class="close" id="theme-popover1_close" onclick="sure()">
				<img class="queren" src="${ctx }/images/product11.png" />
			</a>
 
		</div>
   		<div style="clear:both"></div>
	</div>
</div>
<div class="theme-popover-mask" id="theme-popover-mask"></div>
<!-- 弹出框结束-->	
</body>
</html>