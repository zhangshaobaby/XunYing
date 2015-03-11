<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=0.5, maximum-scale=1.0, minimum-scale=0.5, user-scalable=no, target-densitydpi=device-dpi" />
<title>项目详情</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/xmxq.css" />
<script src="${ctx }/js/jquery.min1.js"></script>
<script type="text/javascript">
    var basepath="${ctx}";
	var moneyflag= false;
	var identityflag=false;
	var isallow=false;
	var EndTime = new Date("${model.end_time}".replace(/-/g,"/"));
	var StartTime = new Date("${model.start_time}".replace(/-/g,"/"));
	var NowTime = new Date("${date}");
	var t = (StartTime.getTime() - NowTime.getTime()>0) ? (StartTime.getTime() - NowTime.getTime()) : (EndTime.getTime() - NowTime.getTime());
	var count = 0;
$(document).ready(function(){
		if(StartTime.getTime() - NowTime.getTime()>0){
			$("#tagTime").prev().html("预购时间");
		}
		if(EndTime.getTime() - NowTime.getTime()<0){
			$("#tagTime").html("募集结束");
		}
		    releasetime();
		    turnPage();
	});
	function releasetime(){
		count++;
		t-=1000;
		if(t>=0&&"${model.state==0}"){
			if ("${release}"=="0"||"${release}"=="0.00"){
				$("#tagTime").html("募集结束");
			}else{
				var d=Math.floor(t/1000/60/60/24);
				var h=Math.floor(t/1000/60/60%24);
				var m=Math.floor(t/1000/60%60);
				var s=Math.floor(t/1000%60);
				$("#tagTime").html("<span class='nr_sj'>"+d+"</span>天<span class='nr_sj'>"+h+"</span>时<span class='nr_sj'>"+m+"</span>分<span class='nr_sj'>"+s+"</span>秒");
				setTimeout(function(){releasetime()},1000)
			}
		}else if(t<0&&"${model.state==0}") {
			//$("#tagTime").html("募集结束");
			if(count!=1)window.location.href=window.location.href;
		}
	}
	var page = 1;
	var next = true;
	function turnPage(){
		if(next){
			$.post("${ctx}/project/tenderList",{id:${model.id},currPage:page},function(data){
				var obj = eval("("+data+")");
				var list = obj[0];
				if(page==obj[1]){
					next = false;
					$("#moreList").hide();
				}else{
					page = page + 1;
				}
				var html="";
				for(var i = 0 ; i < list.length ; i++){
					html+="<tr align='center' valign='middle' height='40' bgcolor='#FFFFFF'>";
						html+="<td>"+list[i][0]+"***"+list[i][1]+"</td>";
						html+="<td>"+list[i][2]+"元</td>";
						html+="<td>"+list[i][3]+"</td>";
					html+="</tr>"
				}
				$("#tenderList").append($(html));
			});
		}
	}
		function changePic(obj){
			var src= $(obj).attr("src") ;
			src = src.substring(src.lastIndexOf(".")+1);
			if(src.length>3)
				src = src.substring(0,3);
			if(src=="doc"||src=="DOC"){
				$(obj).attr("src","${ctx}/images/word.png")
			}else if (src=="pdf"||src=="PDF"){
				$(obj).attr("src","${ctx}/images/pdf.png")
			}else{
				$(obj).attr("src","${ctx}/images/x.png")
			}
		}
</script>

</head>
<body>
<div class="main">
<div class="newstop">
<c:choose><c:when test="${model.name==null||model.name==''}">
									暂无标题
									</c:when>
									<c:when test="${fn:length(model.name)>14}">
									${fn:substring(model.name,0,14)}..
									</c:when>
									<c:otherwise>
									${model.name}
									</c:otherwise>
									</c:choose></div>
<div class="newlist">

<div class="news_l">
<div class="cent">
<div class="db">
<span class="lb">预计年化率</span><br/>
<span class="lb_sz">${model.rate}</span><span class="bfh">%</span>
</div>
</div>
</div>

<div class="news_r">
<div class="zj">
<span class="lb">项目期限</span><br/>
<span class="r_sz">${model.time_limit}
								<c:if test="${model.delay_time_limit!=null&&model.delay_time_limit!=0 }">+${model.delay_time_limit }</c:if></span><span class="yf">个月</span>
</div>
</div>

<div class="clear"></div>
<div class="news_x">
<div class="cent1">
<span class="lb">可投金额</span><br/><br/>
<span class="x_sz">${model.total_money-model._now_money}</span>
</div>
</div>

<div class="news_xbr">
<span class="lb">项目金额(元)</span><br/><br/>
<span class="xbr_sz">${model.total_money}</span>
</div>
</div>
<div class="clear"></div>


<div class="gdt">
<div class="gdt_d"><div class="st" style="width:${percent }% "></div></div><span class="bfs">${percent }%</span>
</div>

<div class="clear"></div>
<div class="nr">
<span class="nr_l">发布日期</span>
<span class="nr_r">${model.publictime}</span>
</div>
<div class="nr">
<span class="nr_l">收益方式</span>
<span class="nr_r">${model.repay_content}</span>
</div>
<div class="nr">
<span class="nr_l">产品类型</span>
<span class="nr_r"><c:choose>
											<c:when test="${model.type==0}">信托(信满盈)</c:when>
											<c:when test="${model.type==1}">资管(资涨通)</c:when>
											<c:when test="${model.type==2}">基金(金多宝)</c:when>
											<c:when test="${model.type==3}">屌丝宝</c:when>
											<c:otherwise>unknown</c:otherwise>
										</c:choose></span>
</div>
<div class="nr">
<span class="nr_l">目前投资人数</span>
<span class="nr_r"><span class="nr_s">${model.pay_number }</span>人</span>
</div>
<div class="nr">
<span class="nr_l">剩余时间</span>
<span class="nr_r" id="tagTime"></span>
</div>
<div class="gt"></div>
<!--<div class="gdxq">
<span class="xq">更多详情</span><span class="xq_r">></span></div>-->
<!--<div class="kj"></div>-->
<div style="height:10px;"></div>
<div class="xm_js"><span>项目介绍</span></div>
<div class="xm_nr">
<span>${fn:replace(model.content,"white-space: nowrap;", "")}</span>
</div>
<div class="xm_js"><span>风控信息</span></div>
<div class="xm_nr">
<span>${fn:replace(model.control_content,"white-space: nowrap;", "")}</span>
</div>
<div class="xm_js"><span>相关资料</span></div>
<div class="zil">
<c:forEach var="attr" items="${companyAttachment}">
							<script type="text/javascript">
								var imgUrl = "${attr.url}";
								var imgName ="${attr.names}"; 
								imgUrl = imgUrl.split(",");
								imgName = imgName.split(",");
								for(var i =0 ; i <　imgUrl.length - 1; i++){
									document.write("<div class='hetong'><img style='width:210px;height:130px;border:5px solid #fff' src='${picpath}/"+imgUrl[i]+"' onerror='changePic(this)' onclick='window.open(\"${picpath}/"+imgUrl[i]+"\")'/><div class='text'>"+imgName[i].substring(0,18)+(imgName[i].length>18?"..":"")+"</div></div>");
								}
							</script>
						
						<div style="clear:both"></div>
						</c:forEach>
</div>

<div class="xm_js"><span>投资情况</span></div>
<div class="touz">
<table width="100%">
  <tr>
    <td width="33%"><span>投资人</span></td>
    <td width="33%"><span>投资金额(元)</span></td>
    <td width="33%"><span>投资时间</span></td>
  </tr>
</table>
</div>
<div class="touz1">
<table width="100%" id="tenderList">
  
</table>

</div>
<div class="xt"></div>
</div>
</body>
</html>