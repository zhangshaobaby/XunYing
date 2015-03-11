/**
 * 说明： 配置信息仅供Demo使用，开发请根据实际情况配置
 * 
 *汇付天下有限公司
 *
 * Copyright (c) 2006-2013 ChinaPnR,Inc.All Rights Reserved.
 */
package com.zc.base.util;

import java.io.Serializable;

public class SignUtils implements Serializable {
    private static long  serialVersionUID        = 3640874934537168392L;
    /** APP端下载apk路径**/
    public static String APK_PATH="http://218.244.157.101/apk/upsoft.xml";
    /** 后台管理员是否可撤销表**/
    public static Boolean ISCANCEL             =false;
    /** 内外部版本 true 内部版 false 外部版**/
    public static Boolean AUTH_Filter             =false;
    /** MD5签名类型 **/
    public static String SIGN_TYPE_MD5           = "M";

    /** RSA签名类型 **/
    public static String SIGN_TYPE_RSA           = "R";

    /** RSA验证签名成功结果 **/
    public static int    RAS_VERIFY_SIGN_SUCCESS = 0;

    /** 商户号 **/
    public static String RECV_MER_ID             = "530202";
   
    
    /** 客户号 **/
    public static String MER_CUST_ID             = "6000060000180254";
 

    /** 平台子账号 **/
    public static String MER_CUST_ACCT_ID             = "MDT000001";

    /** 商户公钥文件地址 **/
    public static String MER_PUB_KEY_PATH        = "D:/key/PgPubk.key";   
    // public static String MER_PUB_KEY_PATH        = "/data/PgPubk.key";

   /** 商户私钥文件地址 **/
    public static String MER_PRI_KEY_PATH        = "D:/key/MerPrK530202.key";
    //  public static String MER_PRI_KEY_PATH        = "/data/MerPrK530202.key";
 
    
    /** 对汇付开放的地址 **/
   //public static String PUBLIC_HOST="http://218.244.157.101:8080/mgt/";
    //public static String PUBLIC_HOST="http://218.244.157.101:8080/";
     public static String PUBLIC_HOST="http://luyangwx.eicp.net:42846/openMvc/";

    /** 图片存储路径和访问路径**/
     public static String PIC_HOST="http://218.244.157.101:8080/image";
     public static String PIC_REALPAHT="/data/tomcat/webapps/image/";
    
    //public static String PIC_HOST="http://localhost:8080/image";
    //public static String PIC_REALPAHT="D:/apache-tomcat-7.0.50/webapps/image/";
     /** 阀值 **/
     public static double TENDER_FLOATI_RATE = 1.0;     
     /** 汇付HOST **/
 	public static  String HTTP_HOST = "http://mertest.chinapnr.com/muser/publicRequests";

}
