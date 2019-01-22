package com.rdc.recruit.controller;

import com.rdc.recruit.entity.User;
import com.rdc.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String add(User user){
        System.out.println(user);
        return userService.add(user);
    }
}
