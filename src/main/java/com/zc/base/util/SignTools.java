/**
 * 说明： 配置信息仅供Demo使用，开发请根据实际情况配置
 * 
 *汇付天下有限公司
 *
 * Copyright (c) 2006-2013 ChinaPnR,Inc.All Rights Reserved.
 */
package com.zc.base.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import chinapnr.SecureLink;

public class SignTools implements Serializable {
    private static long  serialVersionUID        = 3640874934537168392L;
    //屌丝宝提前还款  T+N
    public static int TN_TIME = 3;
 	  /**
     * RSA方式加签
     * 
     * @param custId
     * @param forEncryptionStr
     * @param charset
     * @return
     * @throws Exception 
     */
    public static String encryptByRSA(String forEncryptionStr) throws Exception {
        SecureLink sl = new SecureLink();
        int result = sl.SignMsg(SignUtils.RECV_MER_ID, SignUtils.MER_PRI_KEY_PATH, forEncryptionStr);
        if (result < 0) {
            // 打印日志 
            throw new Exception();
        }
        return sl.getChkValue();
    }
//    public static void main(String[]args){
//    	String chkValue= "UserRegister0006000060000180254lyjx_chengkai6000060000198227http://luyangwx.eicp.net:42846/openMvc/huifucallback/printRegistResult947316106017971689http://luyangwx.eicp.net:42846/openMvc/success.jsp";
//    	try{
//    		System.out.println(encryptByRSA(chkValue));
//    		System.out.println(verifyByRSA(chkValue, "1E4BBFB055DE834B01BA535BC1789E2E615BC0F26523C3A864764FE44ABCF8754770B793D9CDB010E553AFD9DE4DFDE09D73B85D87079ADB5313C7835EB8027E9F5A5977AF8127BBE9EB8919AED464136C800741FE8829A644A620D576F229A6D1A3E57DEB40322C8188AB44C62FB34E81C4C91631902481F0371A3A027EE3ED"));
//    	}catch(Exception e ){
//    		
//    	}
//    }
    public static boolean verifyByRSA(String forEncryptionStr, String chkValue) throws Exception {
        try {
            int verifySignResult = new SecureLink().VeriSignMsg(SignUtils.MER_PUB_KEY_PATH, forEncryptionStr, chkValue);
            return verifySignResult == SignUtils.RAS_VERIFY_SIGN_SUCCESS;
        } catch (Exception e) {
            // 打印日志
            throw new Exception();
        }
    }
    /**
     * 计算总利率
     * @param start
     * @param end
     * @param dayRate
     * @param money
     * @return
     */
    public static BigDecimal getTotalRate(Date d1,Date d2,BigDecimal dayRate){
    	try{
    		long diff = d1.getTime() - d2.getTime();
    		//日期时间差(日)
    		long days = diff / (1000 * 60 * 60 * 24); 
    		days = days > 0 ?days:(days*-1);
    		return dayRate.multiply(new BigDecimal(days));
    	}catch(Exception e){
    		
    	}
    	return null;
    }
    /**
     * 按日计算收益率
     * @param start
     * @param end
     * @param dayRate
     * @param money
     * @return
     */
    public static BigDecimal getRate(Date d1,Date d2,BigDecimal dayRate,BigDecimal money){
    	try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			long diff = d1.getTime() - d2.getTime();
			//日期时间差(日)
			long days = diff / (1000 * 60 * 60 * 24); 
			days = days > 0 ?days:(days*-1);
			return money.multiply(dayRate.multiply(new BigDecimal(days)));
		}catch(Exception e){
			
		}
    	return null;
    }
    /**
     * 按年计算收益率
     * @param start
     * @param end
     * @param rate
     * @param money
     * @return
     */
    public static BigDecimal getRateByYearRate(Date d1,Date d2,BigDecimal rate,BigDecimal money){
    	try{
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		long diff = d1.getTime() - d2.getTime();
    		//日期时间差(日)
    		long days = diff / (1000 * 60 * 60 * 24); 
    		days = days > 0 ?days:(days*-1);
    		return money.multiply(rate.multiply(new BigDecimal(days))).divide(new BigDecimal(365),2,BigDecimal.ROUND_DOWN);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
    }
    /**
     * 按年计算收益率
     * @param start
     * @param end
     * @param rate
     * @param money
     * @return
     */
    public static BigDecimal getRateByYearRate(String date1,String date2,BigDecimal rate,BigDecimal money){
    	try{
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		Date d1 = df.parse(date1);
    		Date d2 = df.parse(date2);
    		long diff = d1.getTime() - d2.getTime();
    		//日期时间差(日)
    		long days = diff / (1000 * 60 * 60 * 24); 
    		days = days > 0 ?days:(days*-1);
    		return money.multiply(rate.multiply(new BigDecimal(days))).divide(new BigDecimal(365),2,BigDecimal.ROUND_DOWN);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static int dateDiffMonth(Date date,Date _date){
 /*   	Date beforedate=date;
    	Date before_date=_date;
    	if(date.compareTo(_date)==1){
    		date.setTime(date.getTime()+_date.getTime());
    		_date.setTime(date.getTime()-_date.getTime());
    		date.setTime(date.getTime()-_date.getTime());
    	}
//    	System.out.println(df.format(date));
//    	System.out.println(df.format(_date));
    	int i = 0;
    	for(;;i++){
    		if(date.compareTo(_date)!=-1){
    			break;
    		}
    		date.setMonth(date.getMonth()+1);
    	}
    	date=beforedate;
    	_date=before_date;
    	return i;*/
    	        long to =date.getTime();
    	        long from = _date.getTime();
    	        Long  diffdate=(to - from) / (1000 * 60 * 60 * 24);
    	        return Integer.parseInt(diffdate.toString());
	
    }
   
    /**
     *  //获取两个日期之间的天数差
     *  endDate:结束时间
     *  stateDate 开始时间
     */
    public static long getdiffDays(Date stateDate,Date endDate){
    	  long quot = 0;
    	  try {
    	   quot = endDate.getTime() - stateDate.getTime();
    	   quot = quot / 1000 / 60 / 60 / 24;
    	  } catch (Exception e) {
    	   e.printStackTrace();
    	  }
    	  return quot;
    	 }
    public static String getProjectTypeName(int typeid){
     if(typeid==0)
    	 return "信满盈";
     if(typeid==1)
    	 return "资涨通";
     if(typeid==2)
    	 return "金多宝";
     if(typeid==3)
    	 return "屌丝宝";
     return "";
    }
    public static void main(String[]args) throws Exception{
    	Date date = new Date();
    	date.setDate(date.getDate()-1);
    	System.out.println(SignTools.getRateByYearRate(new Date(), date, new BigDecimal(36.5), new BigDecimal(1000)));
    }
}
