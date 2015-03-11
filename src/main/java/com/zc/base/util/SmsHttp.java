package com.zc.base.util;

import java.io.UnsupportedEncodingException;

public class SmsHttp {
	public String strSourceAdd = "";                   //子通道号，可为空（预留参数一般为空）
	public String strTim =  ""; //定时发送时间
	public String strReg =  "";   //注册号（由华兴软通提供）
	public String strPwd =  "";                 //密码（由华兴软通提供）
	
	public String strUname = ""; //用户名，不可为空
	public String strMobile = "";            //手机号，不可为空
	public String strRegPhone = "";             //座机，不可为空
	public String strFax = "";               //传真，不可为空
	public String strEmail =  "";       //电子邮件，不可为空
	public String strPostcode = "";               //邮编，不可为空
	public String strCompany =  "";    //公司名称，不可为空
	public String strAddress =  "";//公司地址，不可为空
	
	public String strNewPwd = "";
	
	public String myname="经纪人联盟";

      //以下参数为服务器URL,以及发到服务器的参数，不用修改
    String strRegUrl =  "";
    String strBalanceUrl =  "";
    String strSmsUrl =  "";
    String strSchSmsUrl =  "";
    String strStatusUrl =  "";
    String strUpPwdUrl =  "";
    String strRegParam =  "";
    String strBalanceParam =  "";
    String strSmsParam =  "";
    String strSchSmsParam =  "";
    String strStatusParam=  "";
    String strUpPwdParam =  "";
    
    
    
	public static String strRes  = new String();
	
public SmsHttp() {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	strSmsUrl="http://www.ydqxt.com:8080/sendsms.asp";
	strUname="1717tou";
	strPwd="diaosibao";
	strSmsParam="username=" + strUname+ "&password=" + strPwd ;
}




//发送短消息
public boolean SendMsg(String strPhone, String strContent) throws UnsupportedEncodingException  
{  
	  //发送短信
	//String SmsParam=strSmsParam+ "&phone=" + strPhone + "&content=" +  HttpSend.paraTo16(strContent+"【"+myname+"】");    
	
	String SmsParam=strSmsParam+ "&mobile=" + strPhone+"&message=" +  HttpSend.paraTo16(strContent);          
	try {
    	strRes=HttpSend.postSend(strSmsUrl, SmsParam);
        System.out.println("strRes1="+strRes);

        if(Integer.parseInt(strRes)>0){
        	return true;		
        }
//        if(strRes.indexOf("result=0")<0){
//        	return false;		
//        }        
//        strRes = HttpSend.postSend(strStatusUrl, strStatusParam);
//        System.out.println("strRes2="+strRes);
//        if(strRes.indexOf("result=0")<0){
//        	return false;		
//        }
        
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;		
	}
    
	return false;
	
} 

public static void main(String[] args){
	SmsHttp tt;
	try {
		tt = new SmsHttp();
		tt.SendMsg("15010363903", "【一起一起投】陆洋您好，您刚通过一起一起投平台向自己的资金帐号充值：40元成功。");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


}

}