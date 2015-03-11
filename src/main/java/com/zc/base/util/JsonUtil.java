package com.zc.base.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;


/**
 * @author 作者 E-mail:
 * @version 创建时间：Aug 10, 2009 6:58:43 PM
 * 类说明
 */
@SuppressWarnings("unchecked")
public class JsonUtil {
	
	static String string2Json(String s) { 
	    StringBuilder sb = new StringBuilder(s.length()+20); 
	    sb.append('\"'); 
	    for (int i=0; i<s.length(); i++) { 
	        char c = s.charAt(i); 
	        switch (c) { 
	        case '\"': 
	            sb.append("\\\""); 
	            break; 
	        case '\\': 
	            sb.append("\\\\"); 
	            break; 
//	        case '/': 
//	            sb.append("\\/"); 
//	            break; 
	        case '\b': 
	            sb.append("\\b"); 
	            break; 
	        case '\f': 
	            sb.append("\\f"); 
	            break; 
	        case '\n': 
	            sb.append("\\n"); 
	            break; 
	        case '\r': 
	            sb.append("\\r"); 
	            break; 
	        case '\t': 
	            sb.append("\\t"); 
	            break; 
	        default: 
	            sb.append(c); 
	        } 
	    } 
	    sb.append('\"'); 
	    return sb.toString(); 
	 } 
	
	static String number2Json(Number number) { 
	    return number.toString(); 
	 } 

	static String boolean2Json(Boolean bool) { 
	    return bool.toString(); 
	 } 

	 static String array2Json(Object[] array) { 
		    if (array.length==0) 
		        return "[]"; 
		    StringBuilder sb = new StringBuilder(array.length << 4); 
		    sb.append('['); 
		    for (Object o : array) { 
		        sb.append(toJson(o)); 
		        sb.append(','); 
		    } 
		    // 将最后添加的 ',' 变为 ']': 
		    sb.setCharAt(sb.length()-1, ']'); 
		    return sb.toString(); 
		 } 

	 static String map2Json(Map<String, Object> map) { 
		    if (map.isEmpty()) 
		        return "{}"; 
		    StringBuilder sb = new StringBuilder(map.size() << 4); 
		    sb.append('{'); 
		    Set<String> keys = map.keySet(); 
		    for (String key : keys) { 
		        Object value = map.get(key); 
		        sb.append('\"'); 
		        sb.append(key); 
		        sb.append('\"'); 
		        sb.append(':'); 
		        sb.append(toJson(value)); 
		        sb.append(','); 
		    } 
		    // 将最后的 ',' 变为 '}': 
		    sb.setCharAt(sb.length()-1, '}'); 
		    return sb.toString(); 
		 } 

	 public static String toJson(Object o) { 
		    if (o==null) 
		        return "null"; 
		    if (o instanceof String) 
		        return string2Json((String)o); 
		    if (o instanceof Boolean) 
		        return boolean2Json((Boolean)o); 
		    if (o instanceof Number) 
		        return number2Json((Number)o); 
		    if (o instanceof Map) 
		        return map2Json((Map<String, Object>)o); 
		    if (o instanceof Object[]) 
		        return array2Json((Object[])o); 
		    throw new RuntimeException("Unsupported type: " + o.getClass().getName()); 
		 } 
		 
	public static String toUtf8(String obj){
		try {
			return java.net.URLEncoder.encode(obj,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	public static void writerJson(HttpServletResponse response,JSONObject json){
        response.setContentType("application/json;e=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(json.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(out!=null){
                out.flush();
                out.close();
            }
        }
        
        
    }

}

