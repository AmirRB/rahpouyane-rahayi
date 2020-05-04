package com.rahpouyan.rahayi.demo.commom;


import org.apache.log4j.Logger;
import org.hibernate.StaleObjectStateException;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

public class ExceptionWrapper {
    private static Logger logger = Logger.getLogger(CustomException.class);

    public static String getMessage(Exception e) {
        e.printStackTrace();

        if (e instanceof IdentifierGenerationException) {
            String err = "this api need id";
            logger.error(err);
            return err;
        }
        else if (e instanceof ObjectOptimisticLockingFailureException) {
            String err = "Row was updated or deleted by another transaction";
            logger.error(err);
            return err;
        }
        else if (e.getMessage().equals("could not execute statement; SQL [n/a]; constraint [RAHPOUYANERAHAYI.SYS_C0014720]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement")){
            String err = "images repeted";
            logger.error(err);
            return err;
        }
        else {
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }
}
