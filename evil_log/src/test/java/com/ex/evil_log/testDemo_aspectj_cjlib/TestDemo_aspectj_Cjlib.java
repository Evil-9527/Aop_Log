package com.ex.evil_log.testDemo_aspectj_cjlib;


import com.ex.evil_log.aspectj_aop_cjlib.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:TestDemo_Annotatoon_Cjlib.xml")
public class TestDemo_aspectj_Cjlib {

    @Resource(name = "customer")  //类型为Customer
    private Customer customer;

    @Test
    public void demo1() {
        customer.delete();
        customer.save();
        customer.update();
        customer.find();

    }
}
