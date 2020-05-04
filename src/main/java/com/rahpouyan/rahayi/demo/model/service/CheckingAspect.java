package com.rahpouyan.rahayi.demo.model.service;

import com.rahpouyan.rahayi.demo.commom.CustomException;
import com.rahpouyan.rahayi.demo.commom.ExceptionWrapper;
import com.rahpouyan.rahayi.demo.model.entity.Tavalod;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
@Aspect
public class CheckingAspect {

    @Around("execution( * com.rahpouyan.rahayi.demo.model.service.TavalodService.*(com.rahpouyan.rahayi.demo.model.entity.Tavalod,..))")
    public Object executeTavalodService(ProceedingJoinPoint point) throws Throwable {
        System.out.println("executeTavalodService");
        for (Object arg : point.getArgs()) {
            if (arg instanceof Tavalod) {

                Tavalod tavalod = (Tavalod) arg;
                if (tavalod.getName() == null) {
                    throw new CustomException("name is null");
                }

                if (tavalod.getType() == null) {
                    throw new CustomException("type is null");
                }

                if (tavalod.getImageFileName() == null) {
                    throw new CustomException("imageFileName is null");
                }

                if (tavalod.getDay() == 0) {
                    throw new CustomException("day is null");
                }

                if (tavalod.getMonth() == 0) {
                    throw new CustomException("month is null");
                }

                if (tavalod.getYear() == 0) {
                    throw new CustomException("year is null");
                }

                if (tavalod.getEnterYear() == 0 || tavalod.getEnterYear() == null) {
                    throw new CustomException("year is null");
                }

            }
        }
        return point.proceed();
    }

    @Around("execution( * com.rahpouyan.rahayi.demo.controller.TavalodController.*(..))")
    public Object executeTavalodController(ProceedingJoinPoint point) throws Throwable {
        System.out.println("executeTavalodController");

        try {
            return point.proceed();
        } catch (Exception e) {
            for (Object arg : point.getArgs()) {
                if(arg instanceof RedirectAttributes) {
                    ((RedirectAttributes) arg).addAttribute("error",ExceptionWrapper.getMessage(e));
                    return "redirect:/tavalod/error";
                }
            }
            return "redirect:/tavalod/error";
        }
    }

}
