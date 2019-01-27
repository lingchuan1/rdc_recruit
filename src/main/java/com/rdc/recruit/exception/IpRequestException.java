package com.rdc.recruit.exception;

public class IpRequestException extends RuntimeException {
    public IpRequestException(){
        super("你的ip地址访问超过限制次数，已加入黑名单。");
    }
    public IpRequestException(String message){
        super(message);
    }
}
