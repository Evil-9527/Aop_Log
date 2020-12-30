package com.ex.evil_log.bean_proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyCglibProxy implements MethodInterceptor {

    private  ProductDao productdao;
    private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);
    public MyCglibProxy(ProductDao ProductDao){
        this.productdao = ProductDao;
    }
    public Object createPoxy(){
        //1.创建核心类
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(ProductDao.class);
        //3.设置回调
        enhancer.setCallback(this);
        //4.生成代理
         Object proxy= enhancer.create();
         return proxy;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if("save".equals(method.getName())){
            logger.info("权限校验*******");

            return methodProxy.invokeSuper(proxy,args);
        }
        return methodProxy.invokeSuper(proxy,args);
    }
}
