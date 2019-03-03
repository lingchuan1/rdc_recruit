package com.rdc.recruit.dao;

import com.rdc.recruit.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user(student_id,name,sex,profession_class,direction,contact,introduction,ip) values (#{studentId},#{name},#{sex},#{professionClass},#{direction},#{contact},#{introduction},#{ip})")
    int add(User user);

    @Select("select *from user where id >= (select floor(max(id)*rand()) from user) order by id limit 1;")
    @Results(
            {
                    @Result(id=true,column = "id",property = "id"),
                    @Result(column="student_id",property = "studentId"),
                    @Result(column="profession_class",property = "professionClass")
            }
    )
    User selectRand();

    @Select("select *from user where direction = #{direction}")
    @Results(
            {
                    @Result(id=true,column = "id",property = "id"),
                    @Result(column="student_id",property = "studentId"),
                    @Result(column="profession_class",property = "professionClass")
            }
    )
    ArrayList<User> selectByDirection(String direction);

    @Select("select count(*) from user where ip = #{ip}")
    int ipCount(String ip);

    @Select("select count(*) from user where student_id = #{studentId}")
    int studentIdCount(String studentId);
}
