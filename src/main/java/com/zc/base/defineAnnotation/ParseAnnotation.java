package com.zc.base.defineAnnotation;

import java.lang.reflect.Method;

public class ParseAnnotation {
    public String  parseMethod(Method method){
	   NeedInterceptor needInterceptor = method.getAnnotation(NeedInterceptor.class);
	   if(needInterceptor==null){
		   return null;
	   }
       String value = "";
       if(needInterceptor != null){
    	   value = needInterceptor.value();
       }
       return value;
     }
    public String  parseOpenHFPayMethod(Method method){
    	NeedOpenHFPayInterceptor needInterceptor = method.getAnnotation(NeedOpenHFPayInterceptor.class);
 	   if(needInterceptor==null){
 		   return null;
 	   }
        String value = "";
        if(needInterceptor != null){
     	   value = needInterceptor.value();
        }
        return value;
      }
}
