package com.rdc.recruit.service;

import com.rdc.recruit.bean.CheckPicture;
import com.rdc.recruit.dao.UserMapper;
import com.rdc.recruit.entity.User;
import com.rdc.recruit.util.CheckImgUtil;
import com.rdc.recruit.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String add(User user,Integer X,Integer Y,HttpSession session){
        System.out.println(session);
        int oriX = (int) session.getAttribute("oriX");
        int oriY = (int) session.getAttribute("oriY");
        if(X<oriX-10 || X>oriX+10 || Y<oriY-10 || Y>oriY+10)
            return "验证码验证失败";
        if(StringUtil.isEmpty(user.getName())||StringUtil.isEmpty(user.getStudentId())||StringUtil.isEmpty(user.getContact())||
                StringUtil.isEmpty(user.getProfessionClass())||StringUtil.isEmpty(user.getDirection()))
            return "必填信息不能为空";
        if(user.getName().length() > 10)
            return "名字太长";
        if(user.getProfessionClass().length() > 25)
            return "专业班级太长";
        if(user.getDirection().length() > 10)
            return "方向太长";
        if(user.getIntroduction()!=null && user.getIntroduction().length() > 500)
            return "介绍太长";
        if(!StringUtil.isLegalStuId(user.getStudentId()))
            return "请填写正确的学号";
        if(!StringUtil.isLegalPhone(user.getContact()))
            return "请填写正确的手机号";
        if(1!=userMapper.add(user))
            return "报名失败";
        else
            return "报名成功";
    }


    public CheckPicture getCheckPicture(HttpSession session) throws IOException {
        Random random = new Random();
        int oriPicture = random.nextInt(3)+1;
        File originalFile = new File(oriPicture + ".jpg");
        CheckPicture checkPicture = CheckImgUtil.pictureCut(originalFile,"jpg");
        session.setAttribute("oriX",checkPicture.getX());
        session.setAttribute("oriY",checkPicture.getY());
        return checkPicture;
    }
}
