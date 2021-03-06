package com.jding.test;

import com.jding.test.userlog.UserLog;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class Init1 implements CommandLineRunner {

    private static final Logger logger = UserLog.SystemLog();

    @Override
    public void run(String... args) throws Exception {
        logger.trace("trance","test");
        logger.debug("debug","test");
        logger.info("info","test");
        logger.warn("warn","test");
        logger.error("error","error");
        System.out.println(1);
    }
}
