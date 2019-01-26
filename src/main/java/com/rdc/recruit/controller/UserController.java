package com.rdc.recruit.controller;

import com.rdc.recruit.annotation.IpRequest;
import com.rdc.recruit.bean.CheckPicture;
import com.rdc.recruit.entity.User;
import com.rdc.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
//@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @IpRequest
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public String add(User user){
        System.out.println(user);
        return userService.add(user);
    }

    @RequestMapping(value = "/getCheckPicture",method = RequestMethod.GET)
    public CheckPicture getCheckPicture(HttpSession session) throws IOException {
        return userService.getCheckPicture(session);
    }
}
