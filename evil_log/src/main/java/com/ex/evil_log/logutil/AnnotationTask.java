package com.ex.evil_log.logutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 基于注解的定时任务
 */
@Component
public class AnnotationTask {

    @Scheduled(cron = "0/1 * * * * ?")
    public void task() {
        Logger log= LoggerFactory.getLogger(AnnotationTask.class);
        log.trace("======trace");
        log.debug("======debug");
        log.info("======info");
        log.warn("======warn");
        log.error("======error");

        String name = "Aub";
        String message = "3Q";
        String[] fruits = { "apple", "banana" };

        // logback提供的可以使用变量的打印方式，结果为"Hello,Aub!"
        log.info("Hello,{}!", name);

        // 可以有多个参数,结果为“Hello,Aub! 3Q!”
        log.info("Hello,{}!   {}!", name, message);

        // 可以传入一个数组，结果为"Fruit:  apple,banana"
        log.info("Fruit:  {},{}", fruits);
    }
}
