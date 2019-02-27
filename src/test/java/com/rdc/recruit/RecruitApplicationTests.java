package com.rdc.recruit;

import com.rdc.recruit.service.UserService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecruitApplicationTests {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() throws IOException {

    }

    @Test
    public void test() throws IOException, InvalidFormatException {
        userService.getList("大数据");
    }
}

