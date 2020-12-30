package com.ex.evil_log.aspectj_aop_cjlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customer {

    private static final Logger logger = LoggerFactory.getLogger(Customer.class);
    public String save(){
        logger.info("保存操作");
        return "Customer - return save";
    }
    public void find(){
        logger.info("=================查找操作================");
    }
    public void update(){
        logger.info("=================更新操作================");
        //        int i = 1/0;
    }
    public String delete(){
        logger.info("=================删除操作================");
        return "hello";
    }

}
