package com.rahpouyan.rahayi.demo.commom;

import org.apache.log4j.Logger;

public class CustomException extends Exception {
    private static Logger logger = Logger.getLogger(CustomException.class);
    public CustomException(String msg) {
        super(msg);
    }
}
