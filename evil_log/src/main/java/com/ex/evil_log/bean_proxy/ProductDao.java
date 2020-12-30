package com.ex.evil_log.bean_proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);
    
    public void save(){
        logger.info("保存操作******");
    }
    public void update(){
        logger.info("更新操作******");
    }
    public void delete(){
        logger.info("删除操作******");
    }
    public void find(){
        logger.info("查询操作******");
    }
}

