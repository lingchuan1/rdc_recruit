package com.rdc.recruit.service;

import com.rdc.recruit.config.LogConfig;
import com.rdc.recruit.dao.UserMapper;
import com.rdc.recruit.entity.User;
import com.rdc.recruit.util.StringUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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
        if (userMapper.studentIdCount(user.getStudentId()) >= 2){
            return "你已报名超过两次";
        }
        if (userMapper.ipCount(user.getIp()) >= 5){
            return "你已报名太多次";
        }
        if(1!=userMapper.add(user)) {
            LogConfig.logger.warn(user.getName() + "报名失败！");
            return "报名失败";
        }
        else
            return "1";
    }

    public User selectRand(){
        User user = userMapper.selectRand();
        return user;
    }

    public void getList(String direction) throws IOException, InvalidFormatException {
        ArrayList<User> users = userMapper.selectByDirection(direction);
        String path = "E:/excel/" + direction +".xlsx";
        InputStream is = Files.newInputStream(Paths.get(path));

        Workbook workbook = WorkbookFactory.create(is);
        Sheet sheet = workbook.getSheetAt(0);
        int i = 0;
        for (User user : users) {
            Row row = sheet.createRow(++i);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(user.getName());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(user.getStudentId());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(user.getContact());
            Cell cell7 = row.createCell(3);
            cell7.setCellValue(user.getProfessionClass());
            Cell cell4 = row.createCell(4);
            if (user.getSex() == 0) {
                cell4.setCellValue("男");
            } else {
                cell4.setCellValue("女");
            }
            Cell cell5 = row.createCell(5);
            cell5.setCellValue(user.getDirection());
            Cell cell6 = row.createCell(6);
            cell6.setCellValue(user.getIntroduction());
        }
        OutputStream os = Files.newOutputStream(Paths.get(path));
        workbook.write(os);

    }
}
