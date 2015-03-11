<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="网站描述" />
		<meta name="keywords" content="关键字" />
		<link href="<%=path%>/css/main.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/user_chongzhi.css" rel="stylesheet" type="text/css" />
<script src="<%=path%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
		<script src="<%=path%>/js/formValidator-4.1.3.js"
			type="text/javascript" charset="UTF-8"></script>
		<script src="<%=path%>/js/formValidatorRegex.js"
			type="text/javascript" charset="UTF-8"></script>
		<script type="text/javascript">
		  var wyjson='[{"id":"8","openBankId":"CMB"},{"id":"6","openBankId":"ICBC"},{"id":"27","openBankId":"PSBC"},{"id":"14","openBankId":"CMBC"},{"id":"9","openBankId":"CCB"},{"id":"12","openBankId":"BOC"},{"id":"29","openBankId":"SPDB"},{"id":"18","openBankId":"CIB"},{"id":"7","openBankId":"ABC"},{"id":"13","openBankId":"BOCOM"},{"id":"17","openBankId":"CEB"},{"id":"15","openBankId":"BOS"}]';  
        var kjjson='[{"id":"8","openBankId":"CMB"},{"id":"6","openBankId":"ICBC"},{"id":"19","openBankId":"CITIC"},{"id":"23","openBankId":"HXB"},{"id":"9","openBankId":"CCB"},{"id":"12","openBankId":"BOC"},{"id":"29","openBankId":"SPDB"},{"id":"18","openBankId":"CIB"},{"id":"7","openBankId":"ABC"},{"id":"13","openBankId":"BOCOM"},{"id":"17","openBankId":"CEB"},{"id":"21","openBankId":"GDB"}]';
         $(document).ready(function(){
          var wybanklist=  eval(wyjson);
         var data= eval(wybanklist);
          createTr(data,"wytable");
          showLimit("CMB");
          $.formValidator.initConfig({formID:"form1",theme:"ArrowSolidBox",submitOnce:true,
          ajaxPrompt : '有数据正在异步验证，请稍等...'
	});     
         	 $("#amount").formValidator({onShow:"请输入充值金额",onFocus:"金额必须大于0，小数点后不超过2位。",onCorrect:"正确"}).inputValidator({min:1,onError:"请填写充值金额"}).regexValidator({regExp:"(?!^0\\d*$)^\\d{1,16}(\.\\d{1,2})?$",onError:"金额填写有误"});
         })
        </script>
        <script type="text/javascript">
           function showLimit(bankid){
                selectid = bankid;
                $(".limitdetail").hide();
                if(bankid != null){
                    $("#lt_1_" + bankid).show();
                }
            }
         function   changeBank(ele){
         //设置支付方式
         $("#gateBusiId").val($(ele).val());
         var wybanklist=  eval(wyjson);
         var kjbanklist= eval(kjjson);
         var data;
         $("#wytable").empty();
          if($(ele).val()=="QP"){
           data=kjbanklist;
          }else{
          data=wybanklist;
           }
            //循环网银数据
           createTr(data,"wytable");
        
        }
        function createTr (data,tableid){
               var trel=null;
            $(data).each(function(i,item){
           
            if(i%4==0){
              trel=$("<tr id=> </tr>").appendTo($("#"+tableid));
            }
            var tdel=$("<td> </td>").appendTo($(trel));
            if(i==0){
            var inputel=$("<input  value=\""+item.id+"\" type='radio' onclick=\"showLimit('"+item.openBankId+"')\"  name='bankId.id' checked='checked'/ ><img src='<%=path%>/images/bank/"+item.openBankId+".gif'   />").appendTo($(tdel));
            }else{
            var inputel=$("<input  value=\""+item.id+"\" type='radio' onclick=\"showLimit('"+item.openBankId+"')\"  name='bankId.id' /><img src='<%=path%>/images/bank/"+item.openBankId+".gif'  />").appendTo($(tdel));
            }
            
           });
        
        }
        
        
        </script>
        <style type="text/css">

.table-bordered {
  border: 1px solid #dddddd;
  border-collapse: separate;
  *border-collapse: collapse;
  border-left: 0;
  -webkit-border-radius: 4px;
     -moz-border-radius: 4px;
          border-radius: 4px;
}

.table-bordered th,
.table-bordered td {
  border-left: 1px solid #dddddd;
}

.table-bordered caption + thead tr:first-child th,
.table-bordered caption + tbody tr:first-child th,
.table-bordered caption + tbody tr:first-child td,
.table-bordered colgroup + thead tr:first-child th,
.table-bordered colgroup + tbody tr:first-child th,
.table-bordered colgroup + tbody tr:first-child td,
.table-bordered thead:first-child tr:first-child th,
.table-bordered tbody:first-child tr:first-child th,
.table-bordered tbody:first-child tr:first-child td {
  border-top: 0;
}

.table-bordered thead:first-child tr:first-child > th:first-child,
.table-bordered tbody:first-child tr:first-child > td:first-child,
.table-bordered tbody:first-child tr:first-child > th:first-child {
  -webkit-border-top-left-radius: 4px;
          border-top-left-radius: 4px;
  -moz-border-radius-topleft: 4px;
}

.table-bordered thead:first-child tr:first-child > th:last-child,
.table-bordered tbody:first-child tr:first-child > td:last-child,
.table-bordered tbody:first-child tr:first-child > th:last-child {
  -webkit-border-top-right-radius: 4px;
          border-top-right-radius: 4px;
  -moz-border-radius-topright: 4px;
}

.table-bordered thead:last-child tr:last-child > th:first-child,
.table-bordered tbody:last-child tr:last-child > td:first-child,
.table-bordered tbody:last-child tr:last-child > th:first-child,
.table-bordered tfoot:last-child tr:last-child > td:first-child,
.table-bordered tfoot:last-child tr:last-child > th:first-child {
  -webkit-border-bottom-left-radius: 4px;
          border-bottom-left-radius: 4px;
  -moz-border-radius-bottomleft: 4px;
}

.table-bordered thead:last-child tr:last-child > th:last-child,
.table-bordered tbody:last-child tr:last-child > td:last-child,
.table-bordered tbody:last-child tr:last-child > th:last-child,
.table-bordered tfoot:last-child tr:last-child > td:last-child,
.table-bordered tfoot:last-child tr:last-child > th:last-child {
  -webkit-border-bottom-right-radius: 4px;
          border-bottom-right-radius: 4px;
  -moz-border-radius-bottomright: 4px;
}

.table-bordered tfoot + tbody:last-child tr:last-child td:first-child {
  -webkit-border-bottom-left-radius: 0;
          border-bottom-left-radius: 0;
  -moz-border-radius-bottomleft: 0;
}

.table-bordered tfoot + tbody:last-child tr:last-child td:last-child {
  -webkit-border-bottom-right-radius: 0;
          border-bottom-right-radius: 0;
  -moz-border-radius-bottomright: 0;
}

.table-bordered caption + thead tr:first-child th:first-child,
.table-bordered caption + tbody tr:first-child td:first-child,
.table-bordered colgroup + thead tr:first-child th:first-child,
.table-bordered colgroup + tbody tr:first-child td:first-child {
  -webkit-border-top-left-radius: 4px;
          border-top-left-radius: 4px;
  -moz-border-radius-topleft: 4px;
}

.table-bordered caption + thead tr:first-child th:last-child,
.table-bordered caption + tbody tr:first-child td:last-child,
.table-bordered colgroup + thead tr:first-child th:last-child,
.table-bordered colgroup + tbody tr:first-child td:last-child {
  -webkit-border-top-right-radius: 4px;
          border-top-right-radius: 4px;
  -moz-border-radius-topright: 4px;
}
       .table thead th:first-child {
        padding-left: 20px;
    }
    .table tbody td:first-child {
        padding-left: 20px;
    }
    .table thead th {
        padding: 10px 8px;
        font-size: 12px;
        background-color: #f3f3f3;
        color: #333;
    }
    .table th,
.table td {
  padding: 8px;
  line-height: 20px;
  text-align: left;
  vertical-align: top;
  border-top: 1px solid #dddddd;
}
    
        .wall
        {
            margin-top: 20px;
        }
        .table {
            margin-top: 10px;
        }
        .tel {
            float: right;
        }

        .tab-content {
            overflow:visible;
        }

        .limitdetail {
            display: none;
            margin-left: 50px;
        }
        .control-label.recharge-label {
            width: 100px;
            text-align: left;
            font-size: 16px;
        }
        .controls.recharge-controls {
            margin-left: 120px;
        }

    </style>
		<title>1717tou.com</title>
	</head>

	<body>
		<!-- 信息开始 -->
		<div class="user_cont_rr">
		 <form name="form1" id="form1" method="post" action="<%=path%>/huifu/recharge">
		 	<input type="hidden" name="huifuID" 
value="${huifuID}" />
				<input type="hidden" name="userid" 
value="${userid}" />
				<input type="hidden" id="gateBusiId" name="gateBusiId" 
value="${gateBusiId}" />
                  <div class="user_cont_rrr">
					    <div class="user_cont_r_1"><span>充值</span></div>
					    <div >请选择充值方式：<select onchange="changeBank(this)" ><option value="${gateBusiId}">网银充值</option><!-- <option value="QP">快捷充值</option> --></select></div>
						<div class="user_cont_r_2">选择充值网银</div>   
                         <table class="user_cont_r_3" cellspacing="0" id="wytable"> 
                         	
                         </table>
                      
					</div>
					
					 <div id="lt_1_CMB" class="limitdetail">
                    <span>请关注您的充值金额是否超限: </span><span class="tel">招商银行客服热线：<b>95555</b></span>
                    <table class="table table-bordered">
                        <thead>
                            <tr >
                                <th width="85" style="text-align: right">单笔限额(元)</th>
                                <th width="85" style="text-align: right">每日限额(元)</th>
                                <th width="165">需要满足条件</th>
                                <th>备注</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="text-align: right">500</td>
                                <td style="text-align: right">500</td>
                                <td>开通大众版网上银行</td>
                                <td rowspan="2" style="vertical-align: middle">进行借记卡大额支付，可选择招商银行专业版支付及手机支付渠道进行交易；<a href="http://www.cmbchina.com/main/noticeinfo.aspx?guid=dd55a9aa-b033-4413-8954-2ea3083b62e2" target="_blank">详情参见</a></td>
                            </tr>
                            <tr>
                                <td style="text-align: right">无限额</td>
                                <td style="text-align: right">无限额</td>
                                <td>开通专业版网上银行</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="lt_1_ICBC" class="limitdetail">
                    <span>请关注您的充值金额是否超限: </span><span class="tel">工商银行客服热线：<b>95588</b></span>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th width="85" style="text-align: right">单笔限额(元)</th>
                                <th width="85" style="text-align: right">每日限额(元)</th>
                                <th width="165">需要满足条件</th>
                                <th>备注</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="text-align: right">300</td>
                                <td style="text-align: right">300</td>
                                <td>柜面注册存量静态支付密码客户</td>
                                <td rowspan="4" style="vertical-align: middle">如您是柜面注册存量静态支付密码客户，当您所做的网上支付交易达到总支付限额后，您将不能继续进行网上支付交易。如您仍需进行网上支付交易，请您到中国工商银行营业网点申领电子银行口令卡或个人客户证书（U盾），当您成为电子银行口令卡客户或证书客户后，您将不再受到支付限额的限制。</td>
                            </tr>
                            <tr>
                                <td style="text-align: right">2,000</td>
                                <td style="text-align: right">5,000</td>
                                <td>电子银行口令卡动态支付密码客户--通过手机短信认证</td>
                            </tr>
                            <tr>
                                <td style="text-align: right">500</td>
                                <td style="text-align: right">1,000</td>
                                <td>电子银行口令卡动态支付密码客户--无手机短信认证</td>
                            </tr>
                            <tr>
                                <td style="text-align: right">无限额</td>
                                <td style="text-align: right">无限额</td>
                                <td>证书客户（U盾客户）</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="lt_1_CCB" class="limitdetail">
                    <p>请关注您的充值金额是否超限: <span class="tel">建设银行客服热线：<b>95533</b></span></p>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th width="85" style="text-align: right">单笔限额(元)</th>
                                <th width="85" style="text-align: right">每日限额(元)</th>
                                <th width="165">需要满足条件</th>
                                <th>备注</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="text-align: right">1,000</td>
                                <td style="text-align: right">1,000</td>
                                <td>动态口令卡（刮刮卡）客户、短信动态口令客户、账号支付（无需开通网银）</td>
                                <td>除文件证书客户不开放支付限额外，其他未提及的支付限额均以此限额为准。</td>
                            </tr>
                            <tr>
                                <td style="text-align: right">3,000</td>
                                <td style="text-align: right">3,000</td>
                                <td>文件证书+刮刮卡客户</td>
                                <td rowspan="2" style="vertical-align: middle">账号支付是建行客户小额线上支付方式，无需开通建行网上银行，使用账号支付前，需在建行预留您正在使用的手机号。</td>
                            </tr>
                            <tr>
                                <td style="text-align: right">100万</td>
                                <td style="text-align: right">100万</td>
                                <td>USB Key客户</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="lt_1_ABC" class="limitdetail">
                    <p>请关注您的充值金额是否超限: <span class="tel">农业银行客服热线：<b>95599</b></span></p>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th width="85" style="text-align: right">单笔限额(元)</th>
                                <th width="85" style="text-align: right">每日限额(元)</th>
                                <th width="165">需要满足条件</th>
                                <th>备注</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="text-align: right">1,000</td>
                                <td style="text-align: right">3,000</td>
                                <td>动态口令卡</td>
                                <td rowspan="3" style="vertical-align: middle">中国农业银行于2007年11月1日推出动态口令卡服务。动态口令卡是一种新型安全认证产品。动态口令卡与IE证书绑定后，使用IE证书对外支付将不受上述限额限制，请至农业银行网点申领。</td>
                            </tr>
                            <tr>
                                <td style="text-align: right">50万</td>
                                <td style="text-align: right">100万</td>
                                <td>一代K版（USBN证书用户）</td>
                            </tr>
                            <tr>
                                <td style="text-align: right">100万</td>
                                <td style="text-align: right">500万</td>
                                <td>二代K版（USBN证书用户）</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="lt_1_BOC" class="limitdetail">
                    <p>请关注您的充值金额是否超限: <span class="tel">中国银行客服热线：<b>95566</b></span></p>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th width="85" style="text-align: right">单笔限额(元)</th>
                                <th width="85" style="text-align: right">每日限额(元)</th>
                                <th width="165">需要满足条件</th>
                                <th>备注</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="text-align: right">1万</td>
                                <td style="text-align: right">10万</td>
                                <td>动态口令卡</td>
                                <td>如需使用中国银行网上支付服务，请先到我行营业网点申请开通新版中国银行网上银行,并申请安全认证工具“中银e令”并妥善保管。</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="lt_1_BOCOM" class="limitdetail">
                    <p>请关注您的充值金额是否超限: <span class="tel">交通银行客服热线：<b>95559</b></span></p>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th width="85" style="text-align: right">单笔限额(元)</th>
                                <th width="85" style="text-align: right">每日限额(元)</th>
                                <th width="165">需要满足条件</th>
                                <th>备注</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="text-align: right">5万</td>
                                <td style="text-align: right">5万</td>
                                <td>短信密码客户</td>
                                <td rowspan="2" style="vertical-align: middle">
                                    1、您需要到交通银行营业网点柜面申请成为个人网上银行手机注册用户或者证书认证用户，并开通网上支付功能。<br/>
                                    2、用户在开通网上支付功能后，需到交通银行个人网上银行激活该功能方可使用。<br/>3、客户可根据自身需求登录个人网银进行日累计限额调整。</td>
                            </tr>
                            <tr>
                                <td style="text-align: right">100万</td>
                                <td style="text-align: right">100万</td>
                                <td>证书客户</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="lt_1_PSBC" class="limitdetail">
                    <p>请关注您的充值金额是否超限: <span class="tel">邮政银行客服热线：<b>95585</b></span></p>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th width="85" style="text-align: right">单笔限额(元)</th>
                                <th width="85" style="text-align: right">每日限额(元)</th>
                                <th width="165">需要满足条件</th>
                                <th>备注</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="text-align: right">1万</td>
                                <td style="text-align: right">1万</td>
                                <td>短信动态密码版客户<br/>（签约用户）</td>
                                <td rowspan="2" style="vertical-align: middle">您必须在邮政储蓄网点进行网上支付业务申请、注册和开通，并开通短信动态密码认证功能。</td>
                            </tr>
                            <tr>
                                <td style="text-align: right">200万</td>
                                <td style="text-align: right">200万</td>
                                <td>证书客户</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="lt_1_SPDB" class="limitdetail">
                    <p>请关注您的充值金额是否超限: <span class="tel">浦发银行客服热线：<b>95528</b></span></p>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th width="85" style="text-align: right">单笔限额(元)</th>
                                <th width="85" style="text-align: right">每日限额(元)</th>
                                <th width="165">需要满足条件</th>
                                <th>备注</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="text-align: right">5万</td>
                                <td style="text-align: right">5万</td>
                                <td>动态密码版客户<br/>（签约用户）</td>
                                <td rowspan="2" style="vertical-align: middle">如您是动态密码客户或数字证书客户，您需要开通网上支付功能，即开通个人网上银行。</td>
                            </tr>
                            <tr>
                                <td style="text-align: right">无限额</td>
                                <td style="text-align: right">无限额</td>
                                <td>证书客户<br/>（浏览器版和usbkey版）</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="lt_1_CEB" class="limitdetail">
                    <p>请关注您的充值金额是否超限: <span class="tel">光大银行客服热线：<b>95595</b></span></p>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th width="85" style="text-align: right">单笔限额(元)</th>
                                <th width="85" style="text-align: right">每日限额(元)</th>
                                <th width="165">需要满足条件</th>
                                <th>备注</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="text-align: right">5,000</td>
                                <td style="text-align: right">5,000</td>
                                <td>手机动态密码（令牌）</td>
                                <td rowspan="2" style="vertical-align: middle">
                                    1、办理数字证书支付签约需开通个人网上银行专业版，并登录网上银行专业版进行签约。<br/>
                                    2、办理银行卡直接支付签约可通过我行网点柜台办理，也可登录个人网上银行专业版进行签约。
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">20万</td>
                                <td style="text-align: right">50万</td>
                                <td>网银专业版<br/>阳光网盾</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="lt_1_CMBC" class="limitdetail">
                    <p>请关注您的充值金额是否超限: <span class="tel">民生银行客服热线：<b>95568</b></span></p>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th width="85" style="text-align: right">单笔限额(元)</th>
                                <th width="85" style="text-align: right">每日限额(元)</th>
                                <th width="165">需要满足条件</th>
                                <th>备注</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="text-align: right">300</td>
                                <td style="text-align: right">300</td>
                                <td>无证书客户<br/>（个人网上银行大众版）</td>
                                <td rowspan="3" style="vertical-align: middle">自2008年11月20日起，大众版支付限额由原日累计300元改为累计支付300元。</td>
                            </tr>
                            <tr>
                                <td style="text-align: right">5,000</td>
                                <td style="text-align: right">5,000</td>
                                <td>贵宾版（浏览器证书）</td>
                            </tr>
                            <tr>
                                <td style="text-align: right">20万</td>
                                <td style="text-align: right">50万</td>
                                <td>贵宾版（U宝）</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="lt_1_CIB" class="limitdetail">
                    <p>请关注您的充值金额是否超限: <span class="tel">兴业银行客服热线：<b>95561</b></span></p>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th width="85" style="text-align: right">单笔限额(元)</th>
                                <th width="85" style="text-align: right">每日限额(元)</th>
                                <th width="165">需要满足条件</th>
                                <th>备注</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="text-align: right">1,000或5,000</td>
                                <td style="text-align: right">1,000或5,000</td>
                                <td>个人网银自主开通</td>
                                <td rowspan="2" style="vertical-align: middle">
                                    1、证书客户或短信保护客户直接在个人网银“网上购物”板块“开通/关闭网上购物支付”里日限额只能选1000或5000元，总额度最大100万元；<br/>
                                    2、客户在柜面或者使用证书可自行设置日限额和总额度（最大100万元）；<br/>
                                    3、e卡默认开通网上支付功能，日限额5000元，总额度由客户申请e卡时自行设置（最大100万元），但是可以采取以上两种方式修改。
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align: right">无限额</td>
                                <td style="text-align: right">无限额</td>
                                <td>柜面或<br/>证书客户个人网银开通</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div id="lt_1_BOS" class="limitdetail">
                    <p>请关注您的充值金额是否超限: <span class="tel">上海银行客服热线：<b>95594</b></span></p>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th width="85" style="text-align: right">单笔限额(元)</th>
                            <th width="85" style="text-align: right">每日限额(元)</th>
                            <th width="165">需要满足条件</th>
                            <th>备注</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td style="text-align: right">5,000</td>
                            <td style="text-align: right">5,000</td>
                            <td>专业版文件证书</td>
                            <td rowspan="2" style="vertical-align: middle">可至上海银行营业网点申请开通个人网上银行专业版，获得文件证书或usbkey证书，成为专业版客户。</td>
                        </tr>
                        <tr>
                            <td style="text-align: right">5万</td>
                            <td style="text-align: right">5万</td>
                            <td>专业版USBKEY证书</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
				<!-- 详情 -->	
					
					<div  class="user_cont_r_6">
						<span>输入充值金额</span><input id="amount" name="amount" value="" type="text" /><div id="amountTip"  style="width:480px;height:30px;float:left;margin-left:10px;font-size:12px;" ></div>
						
						
					</div>
					<div class="user_cont_r_7"><input type="submit" value="确认充值"/></div>
					<div class="user_cont_r_8">
						<h3>温馨提示：</h3>
						<div class="con">
							1.投资人充值过程全程免费，不收取任何手续费；<br/>
2.充值必须为银行借记卡，不支持存折、信用卡充值；<br/>
3.充值期间，请勿关闭浏览器，待充值成功并返回首页后，所充资金才能入账，如有疑问，请联系客服；<br/>
4.充值需要开通银行卡网上支付功能，如有疑问请咨询开户行客服；
						</div>
					</div>
									</form>
				</div>
		<!-- 信息结束 -->
	</body>
</html>
