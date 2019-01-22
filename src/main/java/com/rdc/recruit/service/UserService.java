package com.rdc.recruit.service;

import com.rdc.recruit.dao.UserMapper;
import com.rdc.recruit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String add(User user){
        System.out.println(user.getName());
        if(1!=userMapper.add(user))
            return "报名失败！";
        else
            return "报名成功！";
    }
}
