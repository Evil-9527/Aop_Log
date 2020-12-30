package com.ex.evil_log.test_springaop;


import com.ex.evil_log.springaop.StudentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Test_SpringAop.xml")
public class Test_SpringAop {
   @Resource(name="StudentDaoProxy")
    //@Resource(name="Student")
    private StudentDao studentDao;

    @Test
    public void demo1(){
        studentDao.save();
        studentDao.find();
        studentDao.delete();
        studentDao.update();
    }
}
