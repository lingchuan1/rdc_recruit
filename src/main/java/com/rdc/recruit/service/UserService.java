package com.rdc.recruit.service;

import com.rdc.recruit.dao.UserMapper;
import com.rdc.recruit.entity.User;
import com.rdc.recruit.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String add(User user){
        if(StringUtil.isEmpty(user.getName())||StringUtil.isEmpty(user.getStudentId())||StringUtil.isEmpty(user.getContact())||
                StringUtil.isEmpty(user.getProfessionClass())||StringUtil.isEmpty(user.getDirection()))
            return "不能为空";
        StringUtil.StringFilter(user.getProfessionClass());
        if(user.getName().length() > 15)
            return "名字太长";
        if(user.getProfessionClass().length() > 25)
            return "专业班级太长";
        if(user.getDirection().length() > 10)
            return "方向太长";
        if(user.getIntroduction()!=null && user.getIntroduction().length() > 500)
            return "介绍太长";
        if(!StringUtil.isLegalStuId(user.getStudentId()))
            return "学号错误";
        if(!StringUtil.isLegalPhone(user.getContact()))
            return "手机号错误";
        if(!StringUtil.isLegalName(user.getName()))
            return "姓名错误";
        if(1!=userMapper.add(user))
            return "报名失败";
        else
            return "1";
    }

    public User selectRand(){
        User user = userMapper.selectRand();
        return user;
    }
}
