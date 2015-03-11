<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">   
<html xmlns="http://www.w3.org/1999/xhtml">  
<head>
<title>1717投委托认购理财或投资产品协议预览</title>
<meta name="viewport" content="width=device-width, initial-scale=0.5, maximum-scale=1.0, minimum-scale=0.5, user-scalable=no, target-densitydpi=device-dpi" />
<style type="text/css" mce_bogus="1">
body {font-family: SimSun;}
body{font-size:13px;background:#f6f6f6; color:#666666;font-family: SimSun;}
*{margin:0;padding:0;}
.underline{border-bottom: 1px solid #000;}
.ser_top h3{text-align: center;font-size: 20px;padding-top:20px;padding-bottom:20px;}
.ser_top0{margin:0px 10px;padding:20px;border-bottom: 1px solid #aaa;}
.ser_top0 a:link,.ser_top0 a:visited{float:right;color:#0060a5; text-decoration:underline;}
.ser_top2 ul{list-style-type:none;margin:0px 20px 0px 30px;}
.ser_top2 li{height:30px;}
.ser_top1{margin:0px 20px;}
.ser_con{margin:0px 20px;}
.ser_con1{padding: 10px 0px;}
.ser_con1 .title{font-size: 14px;font-weight: bold;}
.ser_con1 .mid1{padding:5px 0px;}
.ser_con1 .mid1_1{margin-left: 10px;}
.ser_con .end{font-weight: bold;margin-bottom: 20px;font-size: 14px;}
</style>
<%

String[] ANDROID_SPECIFIC_SUBSTRING = { "Android"};
String[] IPHONE_SPECIFIC_SUBSTRING = { "iPad","iPhone"};
String userAgent = request.getHeader("user-agent"); 
int android_iphone=0;//0未知 1android 2iphone
 	for (String mobile: ANDROID_SPECIFIC_SUBSTRING){
    		if (userAgent.contains(mobile) || userAgent.contains(mobile.toUpperCase()) || userAgent.contains(mobile.toLowerCase())){
    			android_iphone=1;
    			}
    }
    for (String mobile: IPHONE_SPECIFIC_SUBSTRING){
    		if (userAgent.contains(mobile) || userAgent.contains(mobile.toUpperCase()) || userAgent.contains(mobile.toLowerCase())){
    			android_iphone=2;
    			}
    } 
%>
</head>
<body>
<%if(android_iphone==0){%>
  <%@ include file="../common/head.jsp"%>
<%} %>
<div style="width:100%;margin:0px auto;background-color: #fff;">
<div class="ser_top">
<div class="ser_top0">
法律法规
</div>
<h3>委托认购理财或投资产品协议</h3>

<div class="ser_top2">
<ul>
<li><strong>甲方/受托人：</strong><span class="underline">${name}</span></li>
<li>注册地址：<span class="underline">${address}</span></li>
<li>法定代表人：<span class="underline">${owner}</span></li>
<li><strong>乙方/委托人：</strong><span class="underline">${user.realName}</span></li>
<li>身份证号：<span class="underline">${user.identification}</span></li>
<li>一起一起投网站用户名：<span class="underline">${user.username}</span></li>
</ul>
<div class="ser_top1">甲、乙双方本着平等互利、诚实信用原则，根据《中华人民共和国合同法》及其他有关法律、法规的规定，协商一致<br/>如下：</div>
</div>
</div>
<div class="ser_con">
<div class="ser_con1">
<div class="title">第一条	定义</div>
<div class="mid1">1.1	释义：就本合同而言，除非上下文另有要求，下列词语应具有如下规定的含义：</div>
<div class="mid1_1">1.1.1	【一起一起投】：指甲方所有并运营管理的网站，域名为：<span class="underline">${host}</span>。</div>
<div class="mid1_1">1.1.2	受托人/甲方：指<span class="underline">${companyName}</span>。</div>
<div class="mid1_1">1.1.3	委托人/乙方：指通过甲方的【一起一起投】注册账户成为会员，自主的选择参与购买相关理财或投资产品的自然人。</div>
<div class="mid1_1">1.1.4	理财或投资产品：指本协议第三部分所述理财或投资产品。</div>
<div class="mid1_1">1.1.5	理财或投资产品提供方：指本协议第三部分所述理财或投资产品提供方。</div>
<div class="mid1_1">1.1.6	合作机构：指与甲方签订合作协议的机构，主要包括第三方支付公司等。</div>
<div class="mid1_1">1.1.7	监管账户：【以乙方名义在第三方支付机构开立的电子账户 】。</div>
<div class="mid1_1">1.1.8	中国：中华人民共和国（就本合同而言不包括香港特别行政区、澳门特别行政区和台湾地区）。</div>

<div class="mid1">1.2	其他定义</div>
<div class="mid1_1">1.2.1	本协议中未定义的词语或简称与其他理财或投资产品文件中相关词语或简称的定义相同。</div>
<div class="mid1_1">1.2.2	除非其他理财或投资产品文件中另有特别定义，本标准条款已定义的词语或简称在其他理财或投资产品文件中的含义与本协议的定义相同。</div>
<div class="mid1_1">1.2.3	本协议所称“以上”、“以下”、“以内”，均包括本数。</div>					
</div>
<div class="ser_con1">
<div class="title">第二条	协议投资目的</div>
<div class="mid1">2.1	乙方基于【一起一起投】所列相关信息，乙方自主选购理财或投资产品。</div>
<div class="mid1">2.2	乙方自愿将与本协议第三条所述“认购金额”等值的资金，委托甲方以甲方指定的第三方的名义购买约定的理财或投资产品；甲方同意接受乙方的委托，以甲方指定的第三方的名义购买约定的理财或投资产品。</div>

</div>
<div class="ser_con1">
<div class="title">第三条	理财或投资产品基本情况（具体【一起一起投】平台披露的理财或投资   产品信息为准）</div>
<div class="mid1">3.1	理财或投资产品名称：<span class="underline">${project.name}</span> </div>
<div class="mid1">3.2	产品期限：<span class="underline">${project.time_limit}+${project.delay_time_limit}</span> 个月</div>
<div class="mid1">3.3	认购金额：<span class="underline">${sum}</span> 元</div>
<div class="mid1">3.4	预期年化收益率：<span class="underline">${project.rate}</span> %</div>
<div class="mid1">3.5   成立计息日期以网站公告为准</div>
<div class="mid1">3.6	收益分配方式：<span class="underline">${repay_type}</span> </div>


</div>
<div class="ser_con1">
<div class="title">第四条	认购资金的冻结、流标、满标以及认购资金划转</div>
<div class="mid1">4.1	认购资金的冻结 </div>
<div class="mid1_1">4.1.1	乙方点击“确认投资”按钮确认后，即视为乙方已经向甲方发出不可撤销的授权指令，授权甲方委托相应的第三方支付机构，冻结一定数额的资金。</div>
<div class="mid1_1">4.1.2	冻结资金的金额与 “认购金额”等值。</div>
<div class="mid1_1">4.1.3	资金冻结后，除甲、乙双方协商一致或本协议另有约定外，乙方不得以任何理由要求返还、使用、提取等其他任何方式自由处分该冻结资金。</div>
<div class="mid1">4.2	流标 </div>
<div class="mid1_1">4.2.1	在认购期限届满当日，该理财或投资产品认购份数或认购金额未达到最低认购份数/认购金额的，或因其他原因导致理财或产品未购买成功的，视为流标。</div>
<div class="mid1_1">4.2.2	流标后，甲方应将上述冻结资金解冻，将与“认购金额”等值的资金解冻。</div>
<div class="mid1_1">4.2.3	流标后，甲方无须向乙方承担赔偿责任，也无须向乙方支付利息。<br/>
4.2.4	资金解冻后，乙方可自由处分该解冻资金。<br/>
4.2.5	流标后<span class="underline">10</span>个工作日内，甲方应完成上述解冻事宜。<br/>
4.2.6	资金冻结期间，乙方被冻结资金不产生任何收益。<br/>
</div>
<div class="mid1">4.3 认购资金划转 </div>
<div class="mid1_1">4.3.1	在认购期限届满当日，该理财或投资产品认购份数和认购金额达到最低认购份数和认购金额的，视为满标。<br/>
4.3.2	满标后，甲方可划转监管资金中与“认购金额”等值的资金，用于以甲方指定的第三方的名义购买约定的理财或投资产品，并以甲方指定的第三方的名义签署相关协议。<br/>
4.3.3	认购资金划转完毕，即视为理财或投资产品购买成功。<br/>
4.3.4	甲方在满标后1个工作日内完成理财或投资产品认购资金的划转。<br/>
</div>



</div>

<div class="ser_con1">
<div class="title">第五条	收益</div>
<div class="mid1">5.1	乙方委托甲方以甲方指定的第三方的名义购买理财或投资产品后，理财或投资产品提供方定期返还的收益（扣除费用后）将作为理财或投资产品的收益返还给乙方。</div>
<div class="mid1">
5.2	乙方全权委托甲方或甲方指定的第三方对理财或投资产品所得收益进行管理，并按期返还乙方。
</div><div class="mid1">
5.3	甲方不向乙方承诺保证该收益的实现。若该理财或投资产品发生收益的，甲方应向乙方披露真实信息并按本协议约定返回乙方；若该理财或投资产品未产生收益的，则甲方无须向乙方返还收益。
</div>

</div>
<div class="ser_con1">
<div class="title">第六条	甲方收费</div>
<div class="mid1">6.1	甲方代理本协议项下理财或投资产品委托购买事宜，不收取任何费用。</div>
</div>
<div class="ser_con1">
<div class="title">第七条	理财或投资产品购买合同的签署及持有</div>
<div class="mid1">7.1	乙方全权委托甲方以甲方指定的第三方的名义签署相应的理财或投资 产品认购合同及其他相关文件。</div>
<div class="mid1">
7.2	乙方基于对甲方的信任，自愿委托甲方持有并保管本合同项下的理财或投资产品购买合同及其他相关文件。</div>				
</div>
<div class="ser_con1">
<div class="title">第八条	违约责任</div>
<div class="mid1">8.1	任何一方违反本协议的约定，视为该方违约。违约方应向对方赔偿因其违约行为而遭受的直接损失。</div>
<div class="mid1">8.2	甲方的违约责任：除前述违约赔偿一般原则以外，甲方应赔偿乙方因以下事项而遭受的全部损失（包括但不限于律师费、诉讼费、交通费等）：</div>
<div class="mid1_1">(1)	与第三方恶意串通，损害乙方利益的；<br/>
(2)	未按约定进行代理行为，或利用资金从事违法行为，导致乙方利益遭受损失的；<br/>
(3)	怠于行使理财或投资产品认购合同约定的权利，给乙方带来损失的；<br/>
(4)	违反协议约定的其他行为。<br/>
</div>
<div class="mid1">8.3	乙方的违约责任除前述违约赔偿一般原则以外，乙方应赔偿甲方因以下事项而遭受的全部损失（包括但不限于律师费、诉讼费、交通费等）：</div>
<div class="mid1_1">(1)	乙方认购款存在权利瑕疵，导致甲方或相关第三方受到起诉或任何调查，导致理财或投资产品利益无法实现的；<br/>
(2)	乙方的行为损害债权人利益，导致甲方或相关第三方受到起诉或任何调查，导致理财或投资产品利益无法实现的；<br/>
(3)	违反协议约定的其他行为。

</div>
</div>
<div class="ser_con1">
<div class="title">第九条	认购资金合法性的声明及保证</div>
<div class="mid1">9.1	乙方认购理财或投资产品未损害委托人的债权人的合法利益：认购资金是其合法所有的具有完全支配权的财产，如果第三方对资金归属、合法性问题发生争议，由乙方自行负责解决；</div><div class="mid1">
9.2	甲方不对认购资金的合法性具有或承担任何责任，也不对委托人是否遵守相关法律法规负有或承担任何责任。</div>				
</div>

<div class="ser_con1">
<div class="title">第十条	风险提示</div>
<div class="mid1">10.1	甲方不就理财或投资产品做保本和最低收益承诺，甲方提醒乙方理财或投资产品具有一定的投资风险，乙方应当根据自身风险承受能力，谨慎识别、评估、选择相关理财或投资产品。</div>
<div class="mid1">10.2	甲方提醒乙方宏观经济运行、国家政策、证券市场监管政策等的变化，均可能导致市场价格波动，从而影响理财或投资产品的收益。</div>
<div class="mid1">10.3	甲方提醒乙方理财或投资产品提供方受经验、技能等因素的限制，可能会影响该理财或投资产品项目管理，导致理财或投资产品资产遭受损失。</div>
<div class="mid1">10.4	流动性风险：乙方投资于本项理财或投资产品，在约定的到期日前，不能提前赎回该理财或投资产品，对于因此可能导致的资金不能及时回笼的情形，乙方须充分认知并合理安排自身资金。</div>
<div class="mid1">
10.5	其他风险：除上述风险外，不排除其他政治、经济、自然灾害等不可抗力因素对理财或投资产品产生影响的可能。</div><div class="mid1">
10.6	风险承担：乙方应自行承担因签订本协议而可能导致的相关风险，甲方对此不承担任何责任。</div><div class="mid1">
10.7	乙方同意该协议，即表明已认真阅读并理解本协议的所有内容，并愿意依法承担相关的理财或投资产品投资风险。
</div>
</div>
<div class="ser_con1">
<div class="title">第十一条	法律适用</div>
<div class="mid1">11.1	本协议的订立、生效、履行、解释、修改和终止等事项均适用中国法律。</div>
</div>
<div class="ser_con1">
<div class="title">第十二条	特别事项</div>
<div class="mid1">12.1	乙方授权（不可撤销）甲方处理此协议项下的与第三方发生的纠纷事宜。</div>
</div>
<div class="ser_con1">
<div class="title">第十三条	协议的变更</div>
<div class="mid1">13.1	本协议的任何变更，均需以【一起一起投】平台电子文本形式作出。</div>
</div>
<div class="ser_con1">
<div class="title">第十四条	协议完整性</div>
<div class="mid1">14.1	若本协议中的任何一项或多项目条款违反法律的强制性规定，则该项条文无效，无效部分不影响本协议其他条款的效力。除非协议目的因此而不能实现或甲乙双方经协商达成一致，否则甲乙双方不得以此为要求解除协议。</div>
</div>
<div class="ser_con1">
<div class="title">第十五条	保密</div>
<div class="mid1">15.1	双方就本协议所述内容及向对方提供的有关自身的及理财或投资产品提供方的所有相关信息予以保密。未经允许，不得以任何方式向任何他人或第三方披露相关内容。</div>
</div>
<div class="ser_con1">
<div class="title">第十六条	争议解决</div>
<div class="mid1">16.1	双方在本协议履行过程中发生的任何争议，均应友好协商解决；协商不成的，任何一方均有权向甲方所在地人民法院提起诉讼解决。</div>
</div>
<div class="ser_con1">
<div class="title">第十七条	成立与生效</div>
<div class="mid1">17.1	乙方通过在【一起一起投】上点击 “确认投资”后，即视为本协议成立并生效。</div>
</div>
<div class="end">（以下无正文）</div>

</div>

</div>
<%if(android_iphone==0){%>
   <%@ include file="../common/foot.jsp"%>
<%}%>
</body>
</html>