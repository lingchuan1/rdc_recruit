package com.rdc.recruit.service;

import com.rdc.recruit.bean.CheckPicture;
import com.rdc.recruit.dao.UserMapper;
import com.rdc.recruit.entity.User;
import com.rdc.recruit.util.CheckImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Random;

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


    public CheckPicture getCheckPicture() throws IOException {
        Random random = new Random();
        int oriPicture = random.nextInt(3);
        File originalFile = new File(oriPicture + ".jpg");
        CheckPicture checkPicture = CheckImgUtil.pictureCut(originalFile,"jpg");
        return checkPicture;
    }
}
