package com.rdc.recruit.dao;

import com.rdc.recruit.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user(student_id,name,sex,profession_class,direction,contact,introduction) values (#{studentId},#{name},#{sex},#{professionClass},#{direction},#{contact},#{introduction})")
    int add(User user);
}
