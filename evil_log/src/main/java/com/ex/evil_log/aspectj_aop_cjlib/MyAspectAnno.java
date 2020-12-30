package com.ex.evil_log.aspectj_aop_cjlib;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

@Aspect
@Order(0)
public class MyAspectAnno {
    private static final Logger logger = LoggerFactory.getLogger(MyAspectAnno.class);
    @Around(value = "execution(* com.ex.evil_log.aspectj_aop_cjlib.Customer.find(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("=====@Order(0)======find-环绕前通知===========");
        Object obj =joinPoint.proceed();
        logger.info("=====@Order(0)========find-环绕后通知===============");
        return obj;
    }
    @AfterThrowing(value = "execution(* com.ex.evil_log.aspectj_aop_cjlib.Customer..update(..))",throwing = "e")
    public void throwing(Throwable e){
        logger.error("****@Order(0)******update-异常打印通知*********"+e.getMessage());
    }

    @After(value = "execution(* com.ex.evil_log.aspectj_aop_cjlib.Customer..update(..))")
    public void after(){
        logger.info("***@Order(0)******update-最终通知**********");
    }

    /**
     * 重复切点使用Pointcut定义
     */
    @Pointcut(value = "execution(* com.ex.evil_log.aspectj_aop_cjlib.Customer..save(..))")
    private void pointcut1(){}
    @Before(value = "pointcut1()")
    public void before(JoinPoint joinPoint){
        logger.info("===@Order(0)先于1执行===pointcut1-save前置校验通知========="+joinPoint);
    }

    @AfterReturning(value = "pointcut1()",returning = "returning")
    public void After(Object returning){
        logger.info("======@Order(0)后执行==========pointcut1-save后置通知==============="+returning);

    }

}