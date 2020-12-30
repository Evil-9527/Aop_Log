package com.ex.evil_log.springaop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentImp implements StudentDao {
    
    private static final Logger logger= LoggerFactory.getLogger(StudentImp.class);
            
    @Override
    public void save() {
        logger.info("保存操作*****");
    }

    @Override
    public void find() {
        logger.info("查找操作*****");
    }

    @Override
    public void update() {
        logger.info("更新操作*****");
    }

    @Override
    public void delete() {
        logger.info("删除操作*****");
    }
}
