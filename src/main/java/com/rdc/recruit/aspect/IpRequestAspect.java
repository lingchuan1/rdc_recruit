package com.rdc.recruit.aspect;

import com.rdc.recruit.annotation.IpRequest;
import com.rdc.recruit.util.AccessUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class IpRequestAspect {

    private final Map<String,Integer> ipMap = new HashMap<>();

    @Before("@annotation(ipRequest)")
    public void doBefore(JoinPoint joinPoint,IpRequest ipRequest){
        //获取到请求的属性
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取到请求对象
        HttpServletRequest request = attributes.getRequest();

        String ip = AccessUtil.getIpAddress(request);
        if(!ipMap.containsKey(ip)){
            ipMap.put(ip,1);
        }else{
            ipMap.put(ip,ipMap.get(ip)+1);
        }
        if(ipMap.get(ip) >= ipRequest.count() )
            throw new RuntimeException();
    }
}