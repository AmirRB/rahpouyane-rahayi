package com.rahpouyan.rahayi.demo.controller;

import com.rahpouyan.rahayi.demo.Information;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = Information.frontAddress)
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
