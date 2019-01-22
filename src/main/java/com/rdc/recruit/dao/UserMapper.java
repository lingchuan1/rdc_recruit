package com.rdc.recruit.dao;

import com.rdc.recruit.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user(studentId,userName,sex,professional_class,direction) values (#{studentId},#{name},#{sex},#{professionClass},#{direction})")
    int add(User user);
}
