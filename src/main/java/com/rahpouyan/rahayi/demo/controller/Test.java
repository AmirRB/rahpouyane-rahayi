package com.rahpouyan.rahayi.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {


    @RequestMapping("/test")
    public String testLog() {
        Logger logger = Logger.getLogger(Test.class);
        logger.debug("ana debug");
        logger.info("ana info");
        logger.warn("ana warn");
        logger.error("ana error");
        logger.fatal("ana fatal");

        return "hi";
    }

}
