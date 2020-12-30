package com.ex.evil_log.xml_aop_jdk;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAspect {

    private static final Logger logger = LoggerFactory.getLogger(MyAspect.class);

    public void before(Pointcut pointcut){
        logger.info("前置通知========================="+pointcut);
    }
    public void afterReturning(Object res){
        logger.info("后置通知========================="+res);
    }
    public Object aorund(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("环绕前通知=========================");
        Object obj=proceedingJoinPoint.proceed();
        logger.info("环绕后通知=========================");
        return obj;
    }
    public void after(){
        logger.info("最终通知=========================");
    }
    public void afterThrowable(Throwable e){
        logger.error("异常通知========================="+e.getMessage());
    }

}
