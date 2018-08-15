package com.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(
            value = {NullPointerException.class,
                    ArithmeticException.class})
    public ModelAndView handleException(HttpServletRequest request,
                                        Exception e) {
        System.err.println(request.getRequestURL() + " isteği gerçekleştirilirken " +
                "bir hata oluştu. Hata mesajı: " + e.getMessage());
        return new ModelAndView("error");
    }

}