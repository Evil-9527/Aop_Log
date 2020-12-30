package com.ex.evil_log.testDemo_xml_jdk;


import com.ex.evil_log.xml_aop_jdk.CustomerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:TestDemo_Xml_JDK.xml")
public class TestDemo_Xml_JDK{


    @Autowired
    private CustomerDao customerDao;

    @Test
    public void demo1(){
        customerDao.save();
        customerDao.increase();
        customerDao.delete();
        customerDao.findall();
        customerDao.increase();
        customerDao.findone();
    }
}
