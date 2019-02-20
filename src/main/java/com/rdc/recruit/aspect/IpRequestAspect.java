package com.rdc.recruit.aspect;

import com.rdc.recruit.annotation.IpRequest;
import com.rdc.recruit.exception.IpRequestException;
import com.rdc.recruit.util.AccessUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class IpRequestAspect {

//    private final Map<String, Integer> ipMap = new HashMap<>();
//    private final Map<String, Integer> limitedIpMap = new HashMap<>();

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Before("@annotation(ipRequest)")
    public void doBefore(JoinPoint joinPoint, IpRequest ipRequest) throws IpRequestException {
        //获取到请求的属性
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取到请求对象
        HttpServletRequest request = attributes.getRequest();

        String ip = AccessUtil.getIpAddress(request);
        System.out.println(ip);
//        if (limitedIpMap.containsKey(ip))
//            throw new IpRequestException("你的ip地址在黑名单中。");
//        else {
//            if (!ipMap.containsKey(ip)) {
//                ipMap.put(ip, 1);
//            } else {
//                ipMap.put(ip, ipMap.get(ip) + 1);
//            }
//            if (ipMap.get(ip) >= ipRequest.count()){
//                limitedIpMap.put(ip,1);
//                throw new IpRequestException();}
//        }

        //30秒内允许最多访问10次
        long count = redisTemplate.opsForValue().increment(ip);
        if(count>ipRequest.count()){
            throw new IpRequestException();
        }else{
            if(count == 1){
                redisTemplate.expire(ip,ipRequest.timeout(),TimeUnit.SECONDS);
            }
        }
    }
}
