<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%
  String path = request.getContextPath();
%>

<!doctype html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="网站描述" />
<meta name="keywords" content="关键字" />
<link href="${ctx }/css/index.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/baiduEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/baiduEditor/third-party/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/baiduEditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/baiduEditor/umeditor.min.js"></script>
<script type="text/javascript" src="${ctx }/baiduEditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${ctx}/js/ajaxupload.3.6.js"></script>
<script type="text/javascript" src="${ctx}/js/formVali.js"></script>
<script type="text/javascript" src="${ctx}/js/dateTimePicker/WdatePicker.js"></script>

<title>1717tou.com</title>
<script>
	var tag=${fn:length(caList)};
	$(document).ready(function(){
		$("#borrowType").change(function(){
			if($(this).val()!=""){
				 $.ajax({
				    type:"POST",
				    async:false,
					url:"${ctx}/auth/borrower/search",
					data:"type="+$("#borrowType").val(),
					success:function(data){
						var html = "<option value=''>--请选择--</option>";
						for(var i = 0 ; i < data.length; i++){
							html+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
						}
						$("#borrowId").html(html);
						}
					});
			}
		});
		<c:if test="${model.borrowType!=null}">
			$("#borrowType").change();
			$("#borrowId").val(${model.borrowId})
		</c:if>
		$("#borrowId").change(function(){
			$("#borrowName").val($(this).find("option:selected").text());
		});
		for(var i = 0 ;i < tag ; i++){
        	initialNew(i);
        	tag--;
        }
    });
    ///
    function uploading(obj){
    	//img_view0
    	var index = $(obj).attr("id");
    }
    ///
	function addNew(){
		var html="";
		html+="<div class='addcp_cont_file'>";
		html+="<div class='addcp_cont_file1'></div>";
		html+="<div class='addcp_cont_file2'>";
		html+="<div class='addcp_cont_file2_a'><span id='btnUp"+tag+"' onclick='uploading(this)'>点击上传</span></div>";
		html+="<div class='addcp_cont_file2_a'><input type='text' name='leixing"+tag+"' /><span style='cursor:pointer' onclick='deleteImgs("+tag+")'>删除此组</span></div>";
		html+="<div class='addcp_cont_file2_b' id='img_view"+tag+"'>";
		//html+="<!-- 新增图片开始 -->";
		//html+="<div class='addcp_cont_file2_c'>";
		//html+="<div class='addcp_cont_file2_c1'><img src='${ctx}/images/filename.png' /></div>";
		//html+="<div class='addcp_cont_file2_c2' onclick='removeUp(this)'>删除上传</div>";
		//html+="</div>";
		//html+="<!-- 新增图片结束 -->";
		html+="</div>";
		html+="</div>";
		html+="<div class='addcp_cont_file3'></div>";
		html+="</div>";
		$("#upload_container").append(html);
		initialNew(tag);
	}
	function deleteImgs(index){
		$("#img_view"+index).parent().parent().remove();
	}
	function removeUp(obj){
		$(obj).parent().remove();
	}
	function initialNew(index){
		var button = $('#btnUp'+index), interval;
        new AjaxUpload(button, {
            //action: 'upload-test.php',文件上传服务器端执行的地址
            action: '${ctx}/auth/project/uploadForName',
            name: 'files',
            onSubmit: function (file, ext) {
            	//before submit 
            	//var length = $("#img_view"+index).find("img").length;
            	//if size == 0
				//if(length<4){
					var html="";
	            	html+="<div class='addcp_cont_file2_c'>";
					html+="<div class='addcp_cont_file2_c1'>";
					html+="<input type='hidden' name='imgUrl"+index+"'><img src='${ctx}/images/filename.png' /><input type='text' name='imgName"+index+"' style='width:100px;height:15px;line-height:15px;font-size:12px;'/></div>";
					html+="<div class='addcp_cont_file2_c2' onclick='removeUp(this)'>删除上传</div>";
					html+="</div>";
					$("#img_view"+index).append(html);
				//}else{
				//	alert("同一类型下最多上传四张图片")
				//}
            	//if size > 5
            	
            	//create view element
            	
                //if (!(ext && /^(jpg|jpeg|JPG|JPEG)$/.test(ext))) {
                //    alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
                //    return false;
                //}

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
                var path_realName = k.split("|");
                k = path_realName[0];
                var realName = path_realName[1];
                if (k == '-1') {
                    alert('您上传的文件太大啦!请不要超过1000M');
                }
                else {
                    //$("#txtFileName").val(k);
                    if(k.indexOf(".jpg")==-1&&k.indexOf(".jpeg")==-1&&k.indexOf(".png")==-1&&k.indexOf(".gif")==-1&&k.indexOf(".JPG")==-1&&k.indexOf(".JPEG")==-1&&k.indexOf(".PNG")==-1&&k.indexOf(".GIF")==-1)
                		$("#img_view"+index).find("img:last").attr("src", "${ctx}/images/filename.png");
                	else
                    	$("#img_view"+index).find("img:last").attr("src", "${picpath}/"+k);
                   	$("#img_view"+index).find("input:last").val(realName);
                    $("#img_view"+index).find("img:last").prev().val(k);
                }
            }
        });
        tag++;
	}
	function changeHalfRateView(obj){
		//if($(obj).val()==0){
		//	$("#xtArea").show();
		//}else{
		//	$("#xtArea").hide();
		//}
		if($(obj).val()==3){
			$("#xtid").val("0");
			$("#xt").val("");
			$("#xtArea").hide();
			$("#half_rateView").show();
			$("#drbp").show();
			$("#self_highest_money").parent().parent().show();
			$("#repay_type0").remove();
			$("#repay_type1").remove();
			$("#repay_type2").remove();
			$("#repay_type3").remove();
		}else{
			$("#drbp").hide();
			$("#half_rateView").hide();
			$("#self_highest_money").parent().parent().hide();
			var html="";
			$("#repay_type0").remove();
			$("#repay_type1").remove();
			$("#repay_type2").remove();
			$("#repay_type3").remove();
			$("#repay_type4").remove();
			$("#repay_type5").remove();
			html+="<option id='repay_type0' value='1' <c:if test='${model.repay_type==1}'> selected='selected'</c:if>>按月付息，到期还本</option>";
			html+="<option id='repay_type1' value='3' <c:if test='${model.repay_type==3}'> selected='selected'</c:if>>按季付息，到期还本</option>";
			html+="<option id='repay_type2' value='6' <c:if test='${model.repay_type==6}'> selected='selected'</c:if>>按半年付息，到期还本</option>";
			html+="<option id='repay_type3' value='12' <c:if test='${model.repay_type==12}'> selected='selected'</c:if>>按年付息，到期还本</option>";
			html+="<option id='repay_type4' value='-1' <c:if test='${model.repay_type==-1}'> selected='selected'</c:if>>到期本息全还</option>";
			html+="<option id='repay_type5' value='-2'<c:if test='${model.repay_type==-2}'> selected='selected'</c:if>>自定义</option>";
			$(html).appendTo($("#repay_type"))
		}
	}
	function _subForm(){
		var boolean = true;
		$("#xt").val($("#xtid").find("option:selected").text());
		$("#db").val($("#dbid").find("option:selected").text());
		$("#tag").val(tag);
		$("#content").val(UM.getEditor('myEditor').getContent())
		$("#control_content").val(UM.getEditor('myEditor2').getContent());
		if(parseFloat($("#total_money").val())<parseFloat($("#highest_money").val())){
			$("#highest_money").focus();
			$("#highest_money").css("border","1px solid red");
			return false;
		}else{
			$("#highest_money").css("border","1px solid #999999");
		}
		if(um2.getContentTxt()==""){
			um2.focus();
			return false;
		}
		if($("#cardNo").val()==""){
			$("#cardNo").focus();
			return false;
		}
		if($("#summary").val()==""){
			$("#summary").focus();
			return false;
		}
		if($("#start_time").val()!=""&&$("#end_time").val()!=""&&$("#rateTime").val()!=""){
			var start = new Date($("#start_time").val().replace(/-/g,"/"));
			var end = new Date($("#end_time").val().replace(/-/g,"/"));
			if(start.getTime() - end.getTime() >= 0){
				$("#start_time").focus();
				return false;
			}
		}
		if ($("#borrowId").val()==null||$("#borrowId").val()==""){
			$("#borrowId").focus();
			$("#borrowId").css("border","1px solid red");
			return false;
		}else{
			if(subForm()){
				$("#submitBtn").remove();
				return true;
			}else{
				return false;
			}
		}
	}
	function _verify(id,state){
		$.post("${ctx}/auth/project/_verify",{id:id,state:state},function(data){
			alert("审核完成");
			window.location.href="${ctx}/auth/project/preVerify";
		});
	}
	$(document).ready(function(){
		changeHalfRateView($("#pType"));
		$("#borrowType").change();
		if("${model.borrowId}"!="")
		$("#borrowId option").each(function(index,item){
			if($(item).val()=="${model.borrowId}"){
				$(item).attr("selected","selected");
			}
		});
	})
	function _changeDayRate(obj){
		var day_rate = $(obj).val()/365;
		$("#day_rate").val(day_rate)
	}
	function changeRepayContent(obj){
		$("#repay_content").val($(obj).find("option:selected").html());
	}
</script>
<style type="text/css"> 
body { 


} 
</style>


</head>

<body>
	<!-- 
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
 -->
	<!-- 产品发布开始 -->
	
	<form action="${ctx}/auth/project/add" id="queryForm" method="post" onsubmit="return _subForm();">
		<div class="addcp">
			<input type="hidden" name="tag" id="tag"/>
			<input type="hidden" name="_tag" id="_tag" value="${tag }"/>
			<input type="hidden" name="id" value="${model.id }"/>
			<input type="hidden" name="pay_number" value="${model.pay_number }"/>
		 	<input type="hidden" name="flag" value="0"/>
			<input type="hidden" name="createTime" value="${model.createTime }"/>
			<div class="addcp_top"><span>产品发布</span></div>
			<div class="addcp_cont">
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">产品名称</div>
					<div class="addcp_cont_name2">
						<input type="text" name="name" value="${model.name }" />
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">产品种类</div>
					<div class="addcp_cont_name2">
						<div class="addcp_cont_select1">
							<div class="addcp_cont_select2">
							<select name="type" id="pType" onchange="changeHalfRateView(this)">
								<option value="0"<c:if test="${model.type==0}"> selected="selected"</c:if>>信托(信满盈)</option>
								<option value="1"<c:if test="${model.type==1}"> selected="selected"</c:if>>资管(资涨通)</option>
								<option value="2"<c:if test="${model.type==2}"> selected="selected"</c:if>>基金(金多宝)</option>
								<option value="3"<c:if test="${model.type==3}"> selected="selected"</c:if>>屌丝宝</option>
							</select>
							</div>
						</div>
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">行业类型</div>
					<div class="addcp_cont_name2">
						<input type="text" name="tradeType" class="allowNull" value="${model.tradeType }" />
					</div>
					<div class="addcp_cont_name3"></div>
				</div>
                <div class="addcp_cont_name" id="xtArea">
                    <div class="addcp_cont_name1">发布方</div>
                    <div class="addcp_cont_name2">
                        <div class="addcp_cont_select1">
                            <div class="addcp_cont_select2">
                                <select name="xtid" id="xtid">
                                	<option value="0">--请选择--</option>
                                    <c:forEach items="${comList}" var="com">
                                        <option value="${com.id}"<c:if test="${model.xtid==com.id}"> selected="selected"</c:if>>${com.company_name}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="xt_name" id="xt"/>
                            </div>
                        </div>
                    </div>
                    <div class="addcp_cont_name3"><span style="color:red">*</span></div>
                </div>
                <div class="addcp_cont_name">
                    <div class="addcp_cont_name1">担保公司</div>
                    <div class="addcp_cont_name2">
                        <div class="addcp_cont_select1">
                            <div class="addcp_cont_select2">
                                <select name="dbid" id="dbid" class="allowNull">
                                	<option value="0">--请选择--</option>
                                    <c:forEach items="${comList2}" var="com">
                                        <option value="${com.id}"<c:if test="${model.dbid==com.id}"> selected="selected"</c:if>>${com.company_name}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="db_name" id="db"/>
                            </div>
                        </div>
                    </div>
                    <div class="addcp_cont_name3"></div>
                </div>
                <div class="addcp_cont_name">
                    <div class="addcp_cont_name1">受托方</div>
                    <div class="addcp_cont_name2">
                        <div class="addcp_cont_select1">
                            <div class="addcp_cont_select2">
                                <select name="borrowType" id="borrowType">
                                    <option value="">--请选择--</option>
                                    <option value="0"<c:if test="${model.borrowType==0 }"> selected = "selected"</c:if>>公司</option>
                                    <!-- <option value="1"<c:if test="${model.borrowType==1 }"> selected = "selected"</c:if>>个人</option> -->
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="addcp_cont_name3"><span style="color:red">*</span></div>
                </div>
                <div class="addcp_cont_name">
                    <div class="addcp_cont_name1"></div>
                    <div class="addcp_cont_name2">
                        <div class="addcp_cont_select1">
                            <div class="addcp_cont_select2">
                                <select name="borrowId" id="borrowId">
                                
                                </select>
                                <input type="hidden" name="borrowName" id="borrowName" value="${model.borrowName }"/>
                            </div>
                        </div>
                    </div>
                    <div class="addcp_cont_name3"><span style="color:red">*</span></div>
                </div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">募集资金</div>
					<div class="addcp_cont_name2">
						<input type="text" id="total_money"  name="total_money" class="double_vali" value="${model.total_money }" />元
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">推荐人奖励</div>
					<div class="addcp_cont_name2">
						<input type="text" id="commission"  name="commission" class="double_vali" value="${model.commission }" />%
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">顾问服务费</div>
					<div class="addcp_cont_name2">
						<input type="text" id="commission_agent"  name="commission_agent" class="double_vali" value="${model.commission_agent }" />%
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<!-- <div class="addcp_cont_name">
					<div class="addcp_cont_name1">已募集资金</div>
					<div class="addcp_cont_name2"> -->
						<input type="hidden" name="now_money" class="double_vali" value="${model.now_money }" />
						<input type="hidden" name="_now_money" class="double_vali" value="${model.now_money }" />
					<!--  </div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div> -->
				<!-- <div class="addcp_cont_name">
					<div class="addcp_cont_name1">已还资金</div>
					<div class="addcp_cont_name2"> -->
						<input type="hidden" name="repay_money" class="double_vali" class="double_vali" value="${model.repay_money }" />
					<!-- </div>
					<div class="addcp_cont_name3"></div>
				</div>-->
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">投资期限</div>
					<div class="addcp_cont_name2">
						<input type="text" name="time_limit" class="int_vali" value="${model.time_limit }" style="width:80px;" />&nbsp;+&nbsp;<input type="text" name="delay_time_limit" class="int_vali" value="${model.delay_time_limit==null?0:model.delay_time_limit }" style="width:80px;" />月
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<div class="addcp_cont_name" id="drbp">
					<div class="addcp_cont_name1">开放日管理</div>
					<div class="addcp_cont_name2">
						<a href="${ctx }/auth/drbp/list?id=${model.id }&tag=${tag }" target="_blank">点击管理</a>
					</div>
					<div class="addcp_cont_name3"></div>
				</div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">募集开始时间</div>
					<div class="addcp_cont_name2">
						<input type="text" id="start_time" name="start_time" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2024-12-30',lang:'zh-cn'})" readonly="readonly"  value="${model.start_time }" />
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">募集结束时间</div>
					<div class="addcp_cont_name2">
						<input type="text" id="end_time" name="end_time" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2024-12-30',lang:'zh-cn'})" readonly="readonly"  value="${model.end_time }" />
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">预计计息时间</div>
					<div class="addcp_cont_name2">
						<input type="text" id="rateTime" name="rateTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'2024-12-30',lang:'zh-cn'})" readonly="readonly"  value="${model.rateTime }"/>
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				
				<input type="hidden" name="day_rate" id="day_rate" class="double_vali" value="${model.day_rate }" />
				
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">预期年化率</div>
					<div class="addcp_cont_name2">
						<input type="text" name="rate" id="rate" class="double_vali" value="${model.rate }" onblur="_changeDayRate(this)"/>%
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">最大红包使用比例</div>
					<div class="addcp_cont_name2">
						<input type="text" name="hongbaorate" id="hongbaorate" class="double_vali" value="${model.hongbaorate}"/>%
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">单笔最低金额</div>
					<div class="addcp_cont_name2">
						<input type="text" name="lowest_money" class="int_vali" value="${model.lowest_money }" />元
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">单笔最高金额</div>
					<div class="addcp_cont_name2">
						<input type="text" name="highest_money" id="highest_money" class="int_vali" value="${model.highest_money }" />元
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">总计投资最高金额</div>
					<div class="addcp_cont_name2">
						<input type="text" name="self_highest_money" id="self_highest_money" class="allowNull int_vali" value="${model.self_highest_money }" />元
					</div>
					<div class="addcp_cont_name3"><span style="color:red"></span></div>
				</div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">还款间隔</div>
					<div class="addcp_cont_name2">
						<div class="addcp_cont_select1">
							<div class="addcp_cont_select2">
							<select name="repay_type" id="repay_type" onchange="changeRepayContent(this)">
								<option id="repay_type0" value="1"<c:if test="${model.repay_type==1}"> selected="selected"</c:if>>按月付息，到期还本</option>
								<option id="repay_type1" value="3"<c:if test="${model.repay_type==3}"> selected="selected"</c:if>>按季付息，到期还本</option>
								<option id="repay_type2" value="6"<c:if test="${model.repay_type==6}"> selected="selected"</c:if>>按半年付息，到期还本</option>
								<option id="repay_type3" value="12"<c:if test="${model.repay_type==12}"> selected="selected"</c:if>>按年付息，到期还本</option>
								<option id="repay_type4" value="-1"<c:if test="${model.repay_type==-1}"> selected="selected"</c:if>>到期本息全还</option>
								<option id="repay_type5" value="-2"<c:if test="${model.repay_type==-2}"> selected="selected"</c:if>>自定义</option>
							</select>
							</div>
						</div>
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<div class="addcp_cont_name">
					<div class="addcp_cont_name1">还款方式描述</div>
					<div class="addcp_cont_name2">
						<input id="repay_content" name="repay_content" value="${model.repay_content }"/>
					</div>
					<div class="addcp_cont_name3"><span style="color:red">*</span></div>
				</div>
				<div class="addcp_cont_info" style="height:100px;">
					<div class="addcp_cont_info1" style="height:100px;">资金卡号</div>
					<div class="addcp_cont_info2" style="height:100px;">
						<textarea style="height:75px;width:300px;" name="cardNo" id="cardNo">${model.cardNo}</textarea>
					</div>
					<div class="addcp_cont_info3" style="height:100px;"></div>
				</div>
				<div class="addcp_cont_info" style="height:100px;">
					<div class="addcp_cont_info1"  style="height:100px;">产品摘要(20字)</div>
					<div class="addcp_cont_info2" style="height:100px;">
					<textarea style="height:75px;width:300px;" name="summary" id="summary">${model.summary}</textarea>
					
					</div>
					<div class="addcp_cont_info3" style="height:100px;"></div>
				</div>
				<div class="addcp_cont_info">
					<div class="addcp_cont_info1">项目描述</div>
					<div class="addcp_cont_info2">
					<textarea id="myEditor" style="width:550px;min-height:210px;max-height:210px;">${model.content}</textarea>
					<input type="hidden" name="content" id="content"/>
					</div>
					<div class="addcp_cont_info3"></div>
				</div>
				<div class="addcp_cont_info">
					<div class="addcp_cont_info1">风控措施</div>
					<div class="addcp_cont_info2">
					<textarea id="myEditor2" style="width:550px;min-height:210px;max-height:210px;">
					${model.control_content}
					</textarea>
					<input type="hidden" name="control_content" id="control_content"/>
					</div>
					<div class="addcp_cont_info3"></div>
				</div>
				
				<div class="addcp_cont_upload">
					<div class="addcp_cont_upload1"></div>
					<!--<div class="addcp_cont_upload2">
						<!-- <a href="#"><img src="${ctx}/images/upload.png" /></a> 
					</div>-->
					<!-- 点击上传图片前可隐藏 -->
					<div class="addcp_cont_upload3"><a href="javascript:void(0);" onClick="addNew()">新增一组</a></div>
					<!-- 结束 -->				
				</div>
				<div id="upload_container">
					<c:forEach items="${caList}" var = "ca" varStatus="index">
						<!-- 新增一组内容开始 -->
						<div class="addcp_cont_file">
							<div class="addcp_cont_file1"></div>
							<div class="addcp_cont_file2">
								<div class="addcp_cont_file2_a"><span id="btnUp${index.index }" onclick='uploading(this)'>点击上传</span></div>
								<div class="addcp_cont_file2_a"><input type="text" name="leixing${index.index }" value="${ca.name }"/><span style='cursor:pointer' onclick='deleteImgs(${index.index })'>删除此组</span></div>
								<div class="addcp_cont_file2_b" id="img_view${index.index }">
									<!-- 新增图片开始 -->
									<c:set var="imgs" value="${fn:split(ca.url, ',')}" />
									<c:set var="names" value="${fn:split(ca.names, ',')}" />
									<c:forEach items="${imgs}" var = "img" varStatus="inde">
										<div class="addcp_cont_file2_c">
											<div class="addcp_cont_file2_c1">
												<input type='hidden' name='imgUrl${index.index }' value='${img }'/>
												<img src="${picpath}/${img }" onerror="this.src='${ctx}/images/filename.png'" />
												<input type='text' name='imgName${index.index }' value="${names[inde.index] }" style='width:100px;height:15px;line-height:15px;font-size:12px;'/>
											</div>
											<div class="addcp_cont_file2_c2" onClick="removeUp(this)">删除上传</div>
										</div>
									</c:forEach>
									<!-- 新增图片结束 -->
								</div>
							</div>
							<div class="addcp_cont_file3"></div>
						</div>
						<!-- 新增一组内容结束 -->
					</c:forEach>
				</div>
				<div class="addcp_cont_button">
					<div class="addcp_cont_button1"></div>
					<div class="addcp_cont_button2" style="width:450px;">
						<c:choose>
							<c:when test="${tag==1}">
								<input type="button" id="submitBtn" value="返回" onclick="javascript:history.go(-1)"/>
							</c:when>
							<c:when test="${tag==11}">
								<input type="button" id="submitBtn" value="返回" onclick="javascript:history.go(-1)"/>
								<input type='button' onclick='_verify(${model.id},0)' value='审核通过'/>
								<input type='button' onclick='_verify(${model.id},-2)'value='审核拒绝'/>
							</c:when>
							<c:otherwise><input type="submit" id="submitBtn"  value="发布产品" /></c:otherwise>
						</c:choose>
						
					</div>
					<div class="addcp_cont_button3" style="width:200px;"></div>
				</div>
				
			</div>
		</div>
	</form>
	<!-- 产品发布结束 -->
	<script type="text/javascript">
	    //实例化编辑器
	    
	    var um = UM.getEditor('myEditor');
	    var um2 = UM.getEditor('myEditor2');
	</script>
</body>
</html>