var onShowHtml = "";
var onFocusHtml = "<span><span class='$class$_top'>$data$</span><span class='$class$_bot'></span></span>";
var onErrorHtml = "<span><span class='$class$_top'>$data$</span><span class='$class$_bot'></span></span>";
var onCorrectHtml = "<span class='$class$'></span>";
var onShowClass = "input_public";
var onFocusClass = "input_public input_focus";
var onErrorClass = "input_public input_error";
var onCorrectClass = "input_public input_correct";


//初始状态，加其它几种状态
var passwordStrengthStatusHtml = [
'<P id=passStrong class="pswState">强度：<EM class=st1>弱</EM><B class="progressImage prog0"></B><EM class=st2>强</EM></P>',
'<P id=passStrong class="pswState">强度：<EM class=st1>弱</EM><B class="progressImage prog1"></B><EM class=st2>强</EM></P>',
'<P id=passStrong class="pswState">强度：<EM class=st1>弱</EM><B class="progressImage prog2"></B><EM class=st2>强</EM></P>',
'<P id=passStrong class="pswState">强度：<EM class=st1>弱</EM><B class="progressImage prog3"></B><EM class=st2>强</EM></P>'
							  ];

var passwordStrengthText = ['密码强度：弱','密码强度：中','密码强度：强']
//密码强度校验规则(flag=1(数字)+2(小写)+4(大写)+8(特殊字符)的组合，value里的0表示跟密码一样长,1表示起码1个长度)
var passwordStrengthRule = [
	{level:1,rule:[
				   {flag:1,value:[0]},			//数字
				   {flag:2,value:[0]},				//小写字符
				   {flag:4,value:[0]}			//大写字符
				  ]
	},
	{level:2,rule:[
				   {flag:8,value:[0]},				//特符
				   {flag:9,value:[1,1]},		//数字(>=1)+特符>=1)
				   {flag:10,value:[1,1]},		//小写(>=1)+特符>=1)
				   {flag:12,value:[1,1]},		//大写(>=1)+特符>=1)
				   {flag:3,value:[1,1]},	//数字(>=1)+小写(>=1)
				   {flag:5,value:[1,1]},	//数字(>=1)+大写(>=1)
				   {flag:6,value:[1,1]}			//小写(>=1)+大写(>=1)
				  ]
	},
	{level:3,rule:[
				   {flag:11,value:[1,1,1]},	//数字(>=1)+小写(>=1)+特符(>=1)
				   {flag:13,value:[1,1,1]},	//数字(>=1)+大写(>=1)+特符(>=1)
				   {flag:14,value:[1,1,1]},	//小写(>=1)+大写(>=1)+特符(>=1)
				   {flag:7,value:[1,1,1]}	//数字(>=1)+小写(>=1)+大写(>=1)
				  ]
	}
];