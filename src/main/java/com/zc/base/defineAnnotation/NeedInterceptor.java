package com.zc.base.defineAnnotation;

import java.lang.annotation.*;
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NeedInterceptor {
	 public String value() default "no";
}
