package com.ex.evil_log.bean_proxy;

import org.junit.Test;

public class Test_Bean_proxy {

    @Test
    public void Test_Bean_proxy1(){
        ProductDao productDao = new ProductDao();
        //代理类
        ProductDao poxy =(ProductDao)new MyCglibProxy(productDao).createPoxy();

        poxy.update();
        poxy.save();
        poxy.delete();
        poxy.find();

//        productDao.save();
//        productDao.update();
//        productDao.delete();
//        productDao.find();
    }
}
