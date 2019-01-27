package com.rdc.recruit.resolver;

import com.rdc.recruit.exception.IpRequestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionResolver {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String handle(Exception e){
        e.printStackTrace();
        if (e instanceof IpRequestException){
            return "你提交的太频繁啦";
        }
        return e + e.getMessage();
    }
}
