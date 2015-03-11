/**
 * 说明：Demo仅提供加签、验签和简单的接口调用参考
 * 具体的开发请以平台情况和接口文档为准
 * 
 *汇付天下有限公司
 *
 * Copyright (c) 2006-2013 ChinaPnR,Inc.All Rights Reserved.
 */
package com.zc.base.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.zc.bsm.vo.MerCash;
import com.zc.bsm.vo.QueryCardInfo;
import com.zc.bsm.vo.QueryTransStat;
import com.zc.bsm.vo.returnVo.CorpRegisterReturn;
import com.zc.bsm.vo.returnVo.MerCashReturn;
import com.zc.bsm.vo.returnVo.QueryCardInfoReturn;
import com.zc.bsm.vo.returnVo.QueryTransStatReturn;


public class TransSubmit {
    
    //如果关注性能问题可以考虑使用HttpClientConnectionManager
    public static String doPost(Map<String, String> params) throws ClientProtocolException, IOException {
        String result = null;
        List<NameValuePair> nvps = HttpClientHandler.buildNameValuePair(params);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        EntityBuilder builder = EntityBuilder.create();
        try {
            HttpPost httpPost = new HttpPost(SignUtils.HTTP_HOST);
            builder.setParameters(nvps);
            httpPost.setEntity(builder.build());
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
                HttpEntity entity = response.getEntity();
                if (response.getStatusLine().getReasonPhrase().equals("OK")
                    && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return result;
    }
    public static void main(String[]args){
    TransSubmit ts=new TransSubmit();
    QueryTransStat tr=new QueryTransStat();
    tr.setOrdId("15");
    tr.setQueryTransType("LOANS");
    tr.setOrdDate("20140811");
    ts.getQueryTransStat(tr);
    }
    public static boolean validate(Object object,Class clz){
    	Map<String, String> params = new HashMap<String, String>();
    	StringBuilder chkValue = new StringBuilder();
    	Field fieldlist[] = clz.getDeclaredFields();
    	try{
    		Field c = clz.getDeclaredField("checkCode");
    		c.setAccessible(true);
    		String checkCode = c.get(object).toString();
    		checkCode = checkCode.replaceAll("\\s", "");
    		checkCode = "+"+checkCode;
    		if(!checkCode.endsWith("+"))
    			checkCode+="+";
    		
    		
    		for(Field f : fieldlist){
    			f.setAccessible(true);
    			if(!f.getName().equals("checkCode")&&!f.getName().equals("ChkValue")){
    				//拼接chkValue
    				if(checkCode.indexOf("+"+f.getName()+"+")!=-1){
    				//	System.out.println(f.getName());
    					String fvalue=null;
    	    			if(f.get(object)!=null){
    	    				fvalue=f.get(object).toString();
    	    			}
    					if(f.getName().contains("RetUrl")&&fvalue!=null){
    						fvalue=URLDecoder.decode(fvalue, "UTF-8");
    					}
    					chkValue.append(StringUtils.trimToEmpty(fvalue));
    				}
    			}
    		}
    		c = clz.getDeclaredField("ChkValue");
    		c.setAccessible(true);
    		//System.out.println("chkValue"+chkValue);
    		return SignTools.verifyByRSA(chkValue.toString(), c.get(object).toString());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return false;
    }
    public static Map getMap(Object object,Class clz){
    	Map<String, String> params = new HashMap<String, String>();
    	StringBuilder chkValue = new StringBuilder();
    	Field fieldlist[] = clz.getDeclaredFields();
    	try{
    		Field c = clz.getDeclaredField("checkCode");
    		c.setAccessible(true);
    		String checkCode = c.get(object).toString();
    		checkCode = checkCode.replaceAll("\\s", "");
    		checkCode = "+"+checkCode;
	    	for(Field f : fieldlist){
	    		f.setAccessible(true);
	    		if(f.get(object)!=null&&!f.get(object).toString().trim().equals("")&&!f.getName().equals("checkCode")){
	    			//拼接map
	    			params.put(f.getName(), f.get(object).toString());
		    		//拼接chkValue
		    		if(checkCode.indexOf(f.getName()+"+")!=-1){
		    			chkValue.append(f.get(object).toString());
		    		}
	    		}
	    	}
	    	params.put("ChkValue", SignTools.encryptByRSA(chkValue.toString()));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return params;
    }
    public static String getHref(Object object,Class clz){
    	StringBuilder host = new StringBuilder(SignUtils.HTTP_HOST);
    	host.append("?");
    	StringBuilder chkValue = new StringBuilder();
    	Field fieldlist[] = clz.getDeclaredFields();
    	try{
    		Field c = clz.getDeclaredField("checkCode");
    		c.setAccessible(true);
    		String checkCode = c.get(object).toString();
    		checkCode = checkCode.replaceAll("\\s", "");
    		checkCode = "+"+checkCode;
	    	for(Field f : fieldlist){
	    		f.setAccessible(true);
                if(!f.getName().equals("checkCode")){
                	if(f.get(object)!=null&&!f.get(object).toString().equals("")){
			    		//拼接href
		    			host.append(f.getName());
			    		host.append("=");
		    			host.append(f.get(object));
			    		host.append("&");
			    		//拼接chkValue
			    		if(checkCode.indexOf(f.getName()+"+")!=-1){
			    			String fvalue=null;
			    			if(f.get(object)!=null){
			    				fvalue=f.get(object).toString();
			    			}
			    			chkValue.append(StringUtils.trimToEmpty(fvalue));
			    		}
                	}
	    		}
	    	}
	    	host.append("ChkValue="+SignTools.encryptByRSA(chkValue.toString()));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return host.toString();
    }
//    public static void main(String[]args){
//    	UserRegister ur = new UserRegister();
//    	ur.setRetUrl("123");
//    	ur.setBgRetUrl("456");
//    	ur.setMerPriv("789");
//    	ur.setMerCustId("1234567890");
//    	System.out.println(ur.getParam());
//    }
    /**
     * 模拟放款接口请求参数
     * @throws Exception 
     * 
     */
    private Map<String, String> getParams() throws Exception {
        String version = "10";
        String cmdId = "Loans";
        String merCustId = "6000060000002526";
        String ordId = "201405140000006";
        String ordDate = "20140514";
        String outCustId = "6000060000009547";
        String transAmt = "0.01";
        String fee = "0.00";
        String subOrdId = "00000000003";
        String subOrdDate = "20140424";
        String inCustId = "6000060000009574";
        String divDetails = "";
        String isDefault = "N";
        String bgRetUrl = "http://192.168.22.204/p2p_trade_demo-JAVA/loans";
        // 若为中文，请用Base64转码
        String merPriv = HttpClientHandler.getBase64Encode("11");

        Map<String, String> params = new HashMap<String, String>();
        params.put("Version", version);
        params.put("CmdId", cmdId);
        params.put("MerCustId", merCustId);
        params.put("OrdId", ordId);
        params.put("OrdDate", ordDate);
        params.put("OutCustId", outCustId);
        params.put("TransAmt", transAmt);
        params.put("Fee", fee);
        params.put("SubOrdId", subOrdId);
        params.put("SubOrdDate", subOrdDate);
        params.put("InCustId", inCustId);
        params.put("DivDetails", divDetails);
        params.put("IsDefault", isDefault);
        params.put("BgRetUrl", bgRetUrl);
        params.put("MerPriv", merPriv);

        // 组装加签字符串原文
        // 注意加签字符串的组装顺序参 请照接口文档
        StringBuffer buffer = new StringBuffer();
        buffer.append(StringUtils.trimToEmpty(version)).append(StringUtils.trimToEmpty(cmdId))
            .append(StringUtils.trimToEmpty(merCustId)).append(StringUtils.trimToEmpty(ordId))
            .append(StringUtils.trimToEmpty(ordDate)).append(StringUtils.trimToEmpty(outCustId))
            .append(StringUtils.trimToEmpty(transAmt)).append(StringUtils.trimToEmpty(fee))
            .append(StringUtils.trimToEmpty(subOrdId)).append(StringUtils.trimToEmpty(subOrdDate))
            .append(StringUtils.trimToEmpty(inCustId)).append(StringUtils.trimToEmpty(divDetails))
            .append(StringUtils.trimToEmpty(isDefault)).append(StringUtils.trimToEmpty(bgRetUrl))
            .append(StringUtils.trimToEmpty(merPriv));
        String plainStr = buffer.toString();
     //   System.out.println(plainStr);
        params.put("ChkValue", SignTools.encryptByRSA(plainStr));

        return params;
    }
    /**
     * 模拟用户注册接口请求参数
     * @throws Exception 
     * 
     */
    private String getUserRegisterParams() throws Exception {
    	String Version = "10";
    	String CmdId = "UserRegister";
    	//530202
    	String MerCustId = "6000060000180254";
    	String BgRetUrl = "http://localhost:8080/";
    	String RetUrl = "http://localhost:8080/test";
    	String UsrId = "mytest005";
    	String UsrName = "fdsafdsafz";
    	String IdType = "00"; 
    	String IdNo = "340703198809182111";
    	String UsrMp = "15210645722" ;
    	String UsrEmail = "343535134@qq.com";
    	String MerPriv = "呵呵";
    	String CharSet = "UTF-8";
        // 若为中文，请用Base64转码
    	MerPriv = HttpClientHandler.getBase64Encode(MerPriv);

    	StringBuilder param = new StringBuilder();
        param.append(SignUtils.HTTP_HOST).append("?")
        .append("Version=").append(Version)
        .append("&CmdId=").append(CmdId)
        .append("&MerCustId=").append(MerCustId)
        .append("&BgRetUrl=").append(BgRetUrl)
        .append("&RetUrl=").append(RetUrl)
        .append("&UsrId=").append(UsrId)
        .append("&UsrName=").append(UsrName)
        .append("&IdType=").append(IdType)
        .append("&IdNo=").append(IdNo)
        .append("&UsrMp=").append(UsrMp)
        .append("&UsrEmail=").append(UsrEmail)
        .append("&MerPriv=").append(MerPriv)
        .append("&CharSet=").append(CharSet);
        
        // 组装加签字符串原文
        // 注意加签字符串的组装顺序参 请照接口文档
        StringBuffer buffer = new StringBuffer();
        buffer.append(StringUtils.trimToEmpty(Version)).append(StringUtils.trimToEmpty(CmdId))
            .append(StringUtils.trimToEmpty(MerCustId)).append(StringUtils.trimToEmpty(BgRetUrl))
            .append(StringUtils.trimToEmpty(RetUrl))
            .append(StringUtils.trimToEmpty(UsrId))
            .append(StringUtils.trimToEmpty(UsrName)).append(StringUtils.trimToEmpty(IdType))
            .append(StringUtils.trimToEmpty(IdNo)).append(StringUtils.trimToEmpty(UsrMp))
            .append(StringUtils.trimToEmpty(UsrEmail))
            .append(StringUtils.trimToEmpty(MerPriv));
           // .append(StringUtils.trimToEmpty(CharSet));
        String plainStr = buffer.toString();
        System.out.println(plainStr);
    	param.append("&ChkValue="+SignTools.encryptByRSA(plainStr));
    	return param.toString();
    }
    /**
     * 获取交易状态
     * @param qs
     * @return
     */
    public QueryTransStatReturn getQueryTransStat(QueryTransStat qs){
    	try {
    		Map<String, String> params = qs.getParam();
    		String jsonString = doPost(params);
    		Gson gson = new Gson();
    		QueryTransStatReturn qsr=gson.fromJson(jsonString, QueryTransStatReturn.class);
    		return qsr;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    //获取代取现结果

    public MerCashReturn getMerCash(MerCash hfcash){
    	try {
    		Map<String, String> params = hfcash.getParam();
    		String jsonString = doPost(params);
    		Gson gson = new Gson();
    		MerCashReturn qsr=gson.fromJson(jsonString, MerCashReturn.class);
    		return qsr;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    public QueryCardInfoReturn QueryCardInfo(QueryCardInfo queryCardInfo){
    	try {
	    	Map<String, String> params = queryCardInfo.getParam();
	    	String jsonString = doPost(params);
			Gson gson = new Gson();
			QueryCardInfoReturn _return = gson.fromJson(jsonString,QueryCardInfoReturn.class );
			return _return;
    	}catch(Exception e){
    		return null;
    	}
    }
    public static List<Map<String,Object>> getmapobj(List<Object> list){
		List<Map<String,Object>> maplist=new ArrayList<Map<String,Object>>();
		for(Object obj:list){
			Map<String,Object> map=new HashMap<String, Object>();
			Class entity=obj.getClass();
			Field[] fieldlist=entity.getDeclaredFields();
    		for(Field f : fieldlist){
    			f.setAccessible(true);
    			Object fvalue=null;
    			try {
					if(f.get(obj)!=null){
						fvalue=f.get(obj);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
    			map.put(f.getName(), fvalue);
    		}
    		maplist.add(map);
		}
		return maplist;
	}
    public static boolean doPost(CorpRegisterReturn sreturn) {
        // 1. 接收异步返回的消息
    	// 2. 对于后台返回方式，为了表示商户订单系统已经收到交易应答，商户必须在应答接收页面输出一段特殊的字符串,
    	//    特殊字符串的具体说明可参见《商户专属平台接口规范》的5.1.3交易类接口应答接受规范
        // 3. 注意，异步返回对信息做了url encode，在进行验签前需要先decode处理
        String cmdId = sreturn.getCmdId();
        System.out.println("cmdId="+cmdId);
        String respCode = sreturn.getRespCode();
        System.out.println("respCode="+respCode);
        String merCustId = sreturn.getMerCustId();
        System.out.println("merCustId="+merCustId);
        String UsrId = sreturn.getUsrId();
        System.out.println("UsrId="+UsrId);
        String UsrName = sreturn.getUsrName();
        System.out.println("UsrName="+UsrName);
        String UsrCustId=sreturn.getUsrCustId();
        System.out.println("UsrCustId="+UsrCustId);
        String AuditStat = sreturn.getAuditStat();
        System.out.println("AuditStat="+AuditStat);
        String trxid=sreturn.getTrxId();
        System.out.println("trxid="+trxid);
        String openBankId=sreturn.getOpenBankId();
        System.out.println("openBankId="+openBankId);
        String CardId=sreturn.getCardId();
        System.out.println("CardId="+CardId);
        String bgRetUrl=sreturn.getBgRetUrl();
        System.out.println("bgRetUrl="+bgRetUrl);
        String RespExt=sreturn.getRespExt();
        System.out.println("RespExt="+RespExt);
        try {
            bgRetUrl = URLDecoder.decode(bgRetUrl, "UTF-8");
            System.out.println("URLDecoder.decode(bgRetUrl, 'UTF-8')="+bgRetUrl);
            UsrName = URLDecoder.decode(UsrName, "UTF-8");            
            System.out.println("URLDecoder.decode(UsrName, 'UTF-8')="+UsrName);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String chkValue = sreturn.getChkValue();

        StringBuffer buffer = new StringBuffer();
        buffer.append(StringUtils.trimToEmpty(cmdId)).append(StringUtils.trimToEmpty(respCode))
            .append(StringUtils.trimToEmpty(merCustId)).append(StringUtils.trimToEmpty(UsrId))
            .append(StringUtils.trimToEmpty(UsrName)).append(StringUtils.trimToEmpty(UsrCustId))
            .append(StringUtils.trimToEmpty(AuditStat)).append(StringUtils.trimToEmpty(trxid))
            .append(StringUtils.trimToEmpty(openBankId))
            .append(StringUtils.trimToEmpty(CardId)).append(StringUtils.trimToEmpty(bgRetUrl))
            .append(StringUtils.trimToEmpty(RespExt));
        String plainStr = buffer.toString();
        System.out.println("校验的字符串="+plainStr);
        System.out.println("chkValue="+chkValue);
        boolean flag = false;
        try {
            flag = SignTools.verifyByRSA(plainStr, chkValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!flag) {
            System.out.println("验证签名失败...");
        }else{
        	 System.out.println("验证签名成功...");
        }
        return flag;

    }
    

	
    /**
     * 模拟接口调用 - 放款
     * 根据接口文档使用post方式
     * @throws Exception 
     * 
     */
//    public static void main(String[] args) throws Exception {
//        TransSubmit ts = new TransSubmit();
//        String param = ts.getUserRegisterParams();
//        // 1. result 为同步返回的结果(jason格式)，可转换成对应的实体对象
//        // 2. 注意：此返回结果中没有使用encode，所以不需要做decode处理
//        // 3. 验证签名的方式与异步应答的验签相同，可参照异步应答接收的处理方式
//		String result = ts.doPost(param);
//        System.out.println(param);
//    }
}
