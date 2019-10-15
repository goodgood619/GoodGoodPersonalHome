package com.good.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice가 @ResponseStatus보다 우선함
// @ControllerAdvice
public class CommonExceptionAdvice {
    @ExceptionHandler(RuntimeException.class)
    public String exceptionHandler(Model model, Exception e){
        model.addAttribute("exception",e);
        return "error/exception";
    }
}
