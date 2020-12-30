package com.ex.evil_log.xml_aop_jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerImp implements CustomerDao {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDao.class);
    @Override
    public String save() {
        logger.info("保存操作********");
        return "save-return";
    }

    @Override
    public void update() {
        logger.info("更新操作********");
    }

    @Override
    public void delete() {
        logger.info("删除操作********");
    }

    @Override
    public void increase() {
        logger.info("增加操作********");
    }

    @Override
    public void findone() {
        logger.info("查找一个操作********");
    }

    @Override
    public void findall() {
        logger.info("查找所有操作********");
    }
}
