package com.codeliu.course_select_system.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author liuxiaoping
 * @Description 学生表对应的实体类
 * @Date 20:54 2019/1/31
 * @Param
 * @return
 **/
public class Student {
    // 学生id
    private String stuId;
    // 学生姓名
    private String stuName;
    // 学生性别
    private String stuSex;
    // 学生出生时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stuBirthYear;
    // 学生入学时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date stuGrade;
    // 院系id
    private Integer collegeId;

    public Student() {}

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId == null ? null : stuId.trim();
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public Date getStuBirthYear() {
        return stuBirthYear;
    }

    public void setStuBirthYear(Date stuBirthYear) {
        this.stuBirthYear = stuBirthYear;
    }

    public Date getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(Date stuGrade) {
        this.stuGrade = stuGrade;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuSex=" + stuSex +
                ", stuBirthYear=" + stuBirthYear +
                ", stuGrade=" + stuGrade +
                ", collegeId=" + collegeId +
                '}';
    }
}