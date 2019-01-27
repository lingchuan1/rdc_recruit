package com.rdc.recruit.entity;

public class User {

    private Integer id;
    private String studentId;  //学号
    private String name;  //姓名
    private int sex;  //性别
    private String professionClass;  //专业班级
    private String direction;  //方向(前端，后台，安卓，大数据)
    private String contact;  //手机号码
    private String introduction;  //自我介绍

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProfessionClass() {
        return professionClass;
    }

    public void setProfessionClass(String professionClass) {
        this.professionClass = professionClass;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", professionClass='" + professionClass + '\'' +
                ", direction='" + direction + '\'' +
                ", contact='" + contact + '\'' +
                ", produce='" + introduction + '\'' +
                '}';
    }
}
