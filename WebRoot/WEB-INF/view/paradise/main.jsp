<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>屌丝币乐园</title>
	<link  href="${ctx }/css/paradise.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx }/css/diaosibi_popup.css" rel="stylesheet" type="text/css" />

    <script src="${ctx }/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	 $('.para2_tit li').mouseover(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.para2_con>div').eq($(this).index()).addClass('current').siblings().removeClass('current');
	}); 
	/*控制弹出层*/

	$('#theme-login').click(function(){
		$('#theme-popover-mask').css("display","block");
		$('#theme-popover').slideDown(200);
	})
	$('#theme-popover_close').click(function(){
		$('#theme-popover-mask').css("display","none");
		$('#theme-popover').slideUp(200);
		})
	
	
	$('#theme-login1').click(function(){
		scoreDetail(1);
		$('#theme-popover-mask1').css("display","block");
		$('#theme-popover1').slideDown(200);
	})
	$('#theme-popover1_close').click(function(){
		$('#theme-popover-mask1').css("display","none");
		$('#theme-popover1').slideUp(200);
		})
	});
	function scoreDetail(pageNum){
		$.post("${ctx }/paradise/scoreDetail",{currPage:pageNum},function(data){
			var html="";
			$(".scoreDetail").remove();
			for(var i = 0 ;i < data.result.length ; i ++ ){
			//日期 屌丝 摘要
				html+="<tr class='scoreDetail'>";
				html+="<td style='color:#e33340;'>"+data.result[i].score+"</td>";
				html+="<td style='color:#0060a5;' title='"+data.result[i].summary+"'>"+data.result[i].summary.substring(0,16)+(data.result[i].summary.length>16?"..":"")+"</td>";
				html+="<td>"+data.result[i].createTime+"</td>";
				html+="</tr>";
			}
			$(html).appendTo($("#scoreTab"));
			$("#currPage").val(data.currPage);
			$("#prePage").val(data.prePage);
			$("#nextPage").val(data.nextPage);
			$("#totalPage").val(data.totalPage);
			$("#nowPage1").html(data.currPage+"/"+data.totalPage);
		});
	}
	function signed(){
		jQuery.ajax({type:"POST", url: "${ctx }/user/usersigned", success:function (result) {
			if (result ==true) {
			alert("签到成功");
			    window.location.reload();
			} else {
				alert("签到失败");
			}
		}});
	}
	function copyCopy(){
		var href = window.location.href;
		copyToClipboard(href.substring(0,href.lastIndexOf("/")+1)+"user/register?uid=${sessionScope.user.id}");
	}
	function copyToClipboard(txt) {
		if (window.clipboardData) {
			window.clipboardData.clearData();
			clipboardData.setData("Text", txt);
			alert("复制成功！");

		} else if (navigator.userAgent.indexOf("Opera") != -1) {
			window.location = txt;
		} else if (window.netscape) {
			try {
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			} catch (e) {
				prompt("被浏览器拒绝，请按ctrl+c直接复制文本框中内容",txt)
				//alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将 'signed.applets.codebase_principal_support'设置为'true'");
			}
			var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
			if (!clip)
				return;
			var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
			if (!trans)
				return;
			trans.addDataFlavor("text/unicode");
			var str = new Object();
			var len = new Object();
			var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
			var copytext = txt;
			str.data = copytext;
			trans.setTransferData("text/unicode", str, copytext.length * 2);
			var clipid = Components.interfaces.nsIClipboard;
			if (!clip)
				return false;
			clip.setData(trans, null, clipid.kGlobalClipboard);
			alert("复制成功！");
		}
	}
	//lucky page
	var page = 0;
	var total = ${fn:length(luckyList)};
	total = total%8==0?total/8:((total/8)+1)
	total = parseInt(total);
	$(document).ready(function(){
		_turnPage(page);
	});
	function _turnPage(){
		var id = 0;
		if(page<0)page=0;
		if(page>=total)page=total-1;
		$(".xunhuan").each(function(index,item){
			id = index;
			if(id >= page * 8 && id < (page+1) * 8){
				$(item).show()
			}else{
				$(item).hide();
			}
		});
		$("#nowPage").html(parseInt(page)+1+"/"+total);
	}
	//lucky change
	function sureChange(){
		var ids = "";
		$("input[name='luckyId']:checked").each(function(index,item){
			ids+=$(item).val()+",";
		});
		if(ids!="")
			ids = ids.substring(0,ids.length-1);
		$.post("${ctx}/paradise/changeScore",{ids:ids},function(data){
			if(data=="ok")
				alert("积分兑换成功")
				window.location.href=window.location.href;
		});
	}
</script>

</head>
<body>
	<input type="hidden" name="currPage" id="currPage"/>
	<input type="hidden" name="prePage" id="prePage"/>
	<input type="hidden" name="nextPage" id="nextPage"/>
	<input type="hidden" name="totalPage" id="totalPage"/>
	<%@ include file="../common/head_html.jsp"%>
	<div class="para">
		<div class="para1"><a href="${ctx }/index">首页</a>&gt;<span>屌丝币乐园</span></div>
		<div class="para2">
          <div class="para2_top">
             <div class="para2_top1">
             <a href="${ctx }/activityvote/index?type=pc">
             <img src="${ctx }/images/banner_diaosi.png" width="745px" height="190px"/>
             </a>
             </div>
              <div class="para2_top2">
                  <div class="para2_top2_01"> 
                    <span>屌丝币余额</span>
                  	<div class="qdao"><a name="signDiv"></a>
                  	<c:choose>
                  		<c:when test="${signed==false}">
                  			<img src="${ctx }/images/para2.png" onclick="signed()" />
                  		</c:when>
                  		<c:when test="${signed==true}">
                  			<img src="${ctx }/images/para2222.png"/>
                  		</c:when>
                  		<c:otherwise>
							<img src="${ctx }/images/para2.png" onclick="window.location.href='${ctx }/dologin'"/>
						</c:otherwise>
                  	</c:choose>
                  	</div>
                  </div>
                  <div class="para2_top2_02">
                  	<c:choose>
                  		<c:when test="${sessionScope.user!=null}">
                  			<span>${score }</span> c
                  		</c:when>
                  		<c:otherwise>
                  			请先登录
                  		</c:otherwise>
                  	</c:choose>
                  	
                  </div>
                  <div class="para2_top2_03">
                  	<span class="para2_top2_03l" id="theme-login1"><a href="javascript:void(0);">查看获得详情</a></span>
                    <span class="para2_top2_03r" style="display: none"><a href="javascript:void(0);">查看积分礼品</a></span>
                  </div>
              
             </div>
          </div>
        
<!--------------------------------------------------------- 屌丝币tab轮换--------------------------------------------------------->   
			<ul class="para2_tit">
            <li class="para2_tit1  <c:if test="${tabid=='1'}">active</c:if>">屌丝币商城</li>
            <!-- <li class="para2_tit2 ">屌丝币抽奖</li> -->
            <li class="para2_tit3  <c:if test="${tabid=='2'}">active</c:if>">赢取屌丝币</li>
            </ul>
            <div class="para2_con">
<!--------------------------------------------------------- 屌丝币商城--------------------------------------------------------->   
               <div <c:if test="${tabid=='1'}">class="current"</c:if> >              
 					<div class="para2_con_left">
                    	<div class="para2_con_left1"><img src="${ctx }/images/para3.png"/>物以稀为贵,各种珍贵限量礼品等你来领取！</div>
                       <!-- 产品循环-->
                        <div class="para2_con_left2" >
                           <img src="${ctx }/images/para4.png" />
                           <div class="black_bg"><span>屌丝兔公仔</span></div>
                           <div class="para2_con_left2_01">
                           		<div class="para2_con_left2_01l">兑换价格：<span>3000</span>c<br/>剩余数量：<span>95768</span></div>
                           		<div class="para2_con_left2_01r2">敬请期待</div>
                        	</div>
                        </div>
                          <!-- 产品循环结束-->
                        <div class="user_cont_r_2d">
                        <!-- <span style="color:#b1b1b1;">上一页</span> 1/15页 <span style="color:#0060a5;"> 下一页 最后一页</span>-->
                        </div>
                        
                        
                    </div>
                    <div class="para2_con_right">
                    	<div class="para2_con_right1">
                        	<div class="para2_con_right1_tit">屌丝币获取方法</div>
                            <table class="para2_con_right1_con" cellspacing="0">
                            	<tr>
                                	<td>1、</td>
                                	<td>每日签到获得；</td>
                                </tr>
                                <tr>
                                	<td>2、</td>
                                	<td>红包兑换获得（兑换比例1：1）；</td>
                                </tr>
                                <tr>
                                	<td>3、</td>
                                	<td>购买理财产品获得，具体获得数量为：<br/>购买金多宝：购买金额*3%的比例获得；<br/>购买资涨通：购买金额*3%的比例获得；<br/> 购买信满盈：购买金额*3%的比例获得；<br/>  购买屌丝宝：购买金额*2%的比例获得；</td>
                                </tr>
                                <tr>
                                	<td>4、</td>
                                	<td>推荐给好友注册并投资；</td>
                                </tr>
                                <tr>
                                	<td>5、</td>
                                	<td>下载并登陆手机客户端。</td>
                                </tr>
                                
                            </table>
                        </div>
                        <div class="para2_con_right2">
                        	<div class="para2_con_right2_tit">商城兑换规则</div>
                            <table class="para2_con_right2_con" cellspacing="0">
                            	<tr>
                                	<td>1、</td>
                                	<td>请在商品兑换有效期内进行兑换;</td>
                                </tr>
                                <tr>
                                	<td>2、</td>
                                	<td>兑换商品前请提供具体联系方式和地址;</td>
                                </tr>
                                <tr>
                                	<td>3、</td>
                                	<td>兑换的商品不开具发票，且不予办理退货或更换;</td>
                                </tr>
                                <tr>
                                	<td>4、</td>
                                	<td>兑换成功后一星期内送货;</td>
                                </tr>
                                <tr>
                                	<td>5、</td>
                                	<td>如有问题请联系客服电话：400-057-3090;</td>
                                </tr>
                                <tr>
                                	<td>6、</td>
                                	<td>所有解释权归一起一起投所有;</td>
                                </tr>
                        
                            </table>
                        </div>
                    </div>
                </div>
<!--------------------------------------------------------- 屌丝币抽奖--------------------------------------------------------->                 
				<!-- 
                 <div>              
 				这是屌丝币抽奖
                </div> -->
<!--------------------------------------------------------- 兑换屌丝币--------------------------------------------------------->                 
				<div <c:if test="${tabid=='2'}">class="current"</c:if>>              
 				     <div class="para2_con_left">
                    	<div class="para2_con_left1"><img src="${ctx }/images/para6.png"/></div>
                       <table class="para2_con_left1_dui" cellspacing="0">
                            	<tr class="tr_hui">
                                	<th>赢取途径</th>
                                	<th>可赢取屌丝币（c）</th>
                                    <th>操作</th>
                                </tr>
                                <tr>
                                	<td>每一元红包兑换</td>
                                	<td class="color_red">1</td>
                                    <td><a  id = "theme-login" class=" theme-login"><span class="para2_con_left2_dui">立即兑换</span></a></td>
                                </tr>
                                 <tr class="tr_hui">
                                	<td>每购买100元屌丝宝产品</td>
                                	<td class="color_red">2</td>
                                    <td>
	                                    <span class="para2_con_left2_dui" style="background-color:#aaa;" >
	                                   		立即购买
	                                    </span>
                                    </td>
                                </tr>
                                 <tr>
                                	<td>每购买100元信满盈产品</td>
                                	<td class="color_red">3</td>
                                    <td><span  class="para2_con_left2_dui"><a href="${ctx }/project/list?type=0" target="_blank">立即购买</a></span></td>
                                </tr>
                               <tr class="tr_hui">
                                	<td>每购买100元金多宝产品</td>
                                	<td class="color_red">3</td>
                                    <td><span  class="para2_con_left2_dui"><a href="${ctx }/project/list?type=2" target="_blank">立即购买</a></span></td>
                                </tr>
                                <tr>
                                	<td>每购买100元资涨通产品</td>
                                	<td class="color_red">3</td>
                                    <td><span  class="para2_con_left2_dui"><a href="${ctx }/project/list?type=1" target="_blank">立即购买</a></span></td>
                                </tr>
                                <tr class="tr_hui">
                                	<td>推荐给好友</td>
                                	<td class="color_red">500</td>
                                    <td>
                                    	<c:choose>
                                    		<c:when test="${sessionScope.user!=null}">
                                    			<span class="para2_con_left2_dui" onclick="copyCopy()">
                                    				复制链接
                                    			</span>
                                    		</c:when>
                                    		<c:otherwise>
	                                    		<span class="para2_con_left2_dui" style="background-color:#aaa;" onclick="$(this).html('请先登录')">复制链接</span>
                                    		</c:otherwise>
                                    	</c:choose>
                                    </td>
                                </tr>
                                <tr>
                                	<td>下载并登陆App</td>
                                	<td class="color_red">300</td>
                                    <td><span  class="para2_con_left2_dui" style="background-color:#aaa;">立即下载</span></td>
                                </tr>
                                <tr class="tr_hui">
                                	<td>每日签到</td>
                                	<td class="color_red">10</td>
                                    <td><span class="para2_con_left2_dui">
                                    <c:choose>
				                  		<c:when test="${signed==false}">
				                  			<a href="javascript:signed()">立即签到</a>
				                  		</c:when>
				                  		<c:when test="${signed==true}">
				                  			已签到
				                  		</c:when>
				                  		<c:otherwise>
											<a href="${ctx }/dologin">请先登录</a>
										</c:otherwise>
				                  	</c:choose>
                                    
                                    </span></td>
                                </tr>
                                
                            </table>
                        
                        
                    </div>
                    <div class="para2_con_right">
                    	<div class="para2_con_right1">
                        	<div class="para2_con_right1_tit">屌丝币获取方法</div>
                            <table class="para2_con_right1_con" cellspacing="0">
                            	<tr>
                                	<td>1、</td>
                                	<td>每日签到获得；</td>
                                </tr>
                                <tr>
                                	<td>2、</td>
                                	<td>红包兑换获得（兑换比例1：1）；</td>
                                </tr>
                                <tr>
                                	<td>3、</td>
                                	<td>购买理财产品获得，具体获得数量为：<br/>购买金多宝：购买金额*3%的比例获得；<br/>购买资涨通：购买金额*3%的比例获得；<br/> 购买信满盈：购买金额*3%的比例获得；<br/>  购买屌丝宝：购买金额*2%的比例获得；</td>
                                </tr>
                                <tr>
                                	<td>4、</td>
                                	<td>推荐给好友注册并投资；</td>
                                </tr>
                                <tr>
                                	<td>5、</td>
                                	<td>下载并登陆手机客户端。</td>
                                </tr>
                                
                            </table>
                        </div>
                        <div class="para2_con_right2">
                        	<div class="para2_con_right2_tit">商城兑换规则</div>
                            <table class="para2_con_right2_con" cellspacing="0">
                            	<tr>
                                	<td>1、</td>
                                	<td>请在商品兑换有效期内进行兑换;</td>
                                </tr>
                                <tr>
                                	<td>2、</td>
                                	<td>兑换商品前请提供具体联系方式和地址;</td>
                                </tr>
                                <tr>
                                	<td>3、</td>
                                	<td>兑换的商品不开具发票，且不予办理退货或更换;</td>
                                </tr>
                                <tr>
                                	<td>4、</td>
                                	<td>兑换成功后一星期内送货;</td>
                                </tr>
                                <tr>
                                	<td>5、</td>
                                	<td>如有问题请联系客服电话：400-057-3090;</td>
                                </tr>
                                <tr>
                                	<td>6、</td>
                                	<td>所有解释权归一起一起投所有;</td>
                                </tr>
                        
                            </table>
                        </div>
                    </div>
                    <!--   以下是红包兑换弹出层的内容-->                         
<div class="theme-popover" id="theme-popover">
	<div class="theme-poptit">
		<h3>红包兑换（比例1:1）</h3>
	</div>     
	<div class="pop_middle">
		<table cellspacing=0 border="0">
			<tr class="tittle color_hui">
				<th>金额（元）</th>
				<th>日期</th>
				<th>屌丝币（c）</th>
				<th>选择</th>
			</tr>
		    <!-- 循环开始-->
		    <c:forEach items="${luckyList}" var="lucky">
			<tr class="xunhuan">
				<td style="color:#e33340;">${lucky[1] }</td>
				<td>${fn:substring(lucky[3],0,10) }</td>
				<td style="color:#0060a5;">${lucky[1] }</td>
				<td><input type="checkbox" name="luckyId" value="${lucky[0] }"></td>
			</tr>
			</c:forEach>
			<!-- 循环结束-->
		</table>
		<div>
			<div style="float:right;margin:10px 0px;">
				<span style="color:#0060a5;">
					<span style="cursor:pointer;" onclick="page=0;_turnPage();">首页</span>
					<span style="cursor:pointer;" onclick="page=page-1;_turnPage();">上页</span>
				</span> 
				<span id="nowPage">
					<script>document.write((parseInt(page)+1)+"/"+total)</script>
				</span>页 
				<span style="color:#0060a5">
					<span style="cursor:pointer;" onclick="page=page+1;_turnPage();">下页</span>
					<span style="cursor:pointer;" onclick="page=total;_turnPage();">末页</span>
				</span>
			</div>
			<div style="clear:both"></div>
			<a href="javascript:;" class="close" id="theme-popover_close" >
				<img class="quxiao" src="${ctx }/images/product12.png">
			</a>
			<a href="javascript:;" class="close" id="theme-popover_close" >
				<img class="queren" src="${ctx }/images/product11.png" onclick="sureChange()" >
			</a>
 
		</div>
   		<div style="clear:both"></div>
	</div>
</div>
<div id="theme-popover-mask" class="theme-popover-mask"></div>
<!-- 弹出框结束-->	





                </div>
            </div>
            
		</div>
			
	</div>
    <%@ include file="../common/foot.jsp"%>
    <!--   以下是屌丝币流水弹出层的内容-->                         
<div class="theme-popover" id="theme-popover1">
	<div class="theme-poptit">
		<h3>屌丝币获得详情</h3>
	</div>     
	<div class="pop_middle">
		<table cellspacing=0 border="0" id="scoreTab">
			<tr class="tittle color_hui">
				<th>屌丝币（c）</th>
				<th>摘要</th>
				<th>日期</th>
			</tr>
		    <!-- 循环开始-->
		    	
			<!-- 循环结束-->
		</table>
		<div>
			<div style="float:right;margin:10px 0px;">
				<span style="color:#0060a5;">
					<span style="cursor:pointer;" onclick="scoreDetail(1);">首页</span>
					<span style="cursor:pointer;" onclick="scoreDetail($('#prePage').val());">上页</span>
				</span> 
				<span id="nowPage1">
					
				</span>页 
				<span style="color:#0060a5">
					<span style="cursor:pointer;" onclick="scoreDetail($('#nextPage').val());">下页</span>
					<span style="cursor:pointer;" onclick="scoreDetail($('#totalPage').val());">末页</span>
				</span>
			</div>
			<div style="clear:both"></div>
			<a href="javascript:;" class="close" id="theme-popover1_close">
				<img class="queren" src="${ctx }/images/product11.png" >
			</a>
 
		</div>
   		<div style="clear:both"></div>
	</div>
</div>
<div class="theme-popover-mask" id="theme-popover-mask1"></div>
<!-- 弹出框结束-->	
</body>
</html>