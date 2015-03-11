package com.zc.base.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
@Aspect//声明为切面
public class zhangshaoAop {
 //切入点
 @Pointcut("(execution(* com.zc.base.dao..*(..)))")
 private void anyMethod(){}//声明一个切入点名称
 
 //前置通知
 @Before("anyMethod()")
 public void doAccessCheck(JoinPoint joinp){
  System.out.println("前置通知");
 System.out.println(joinp.getArgs()) ;
 System.out.println(joinp.getTarget());
 System.out.println(joinp.getThis());
 }
 //后置通知
 @AfterReturning(pointcut="anyMethod()",returning="name")
 public void doAfterReturningCheck(String name){
  System.out.println("后置通知"+name);
 }
 //最终通知
 @After("anyMethod()")
 public void doAfter(){
  System.out.println("最终通知");
 }


}
