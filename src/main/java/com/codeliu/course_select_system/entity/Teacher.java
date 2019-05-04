package com.codeliu.course_select_system.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author liuxiaoping
 * @Description 教师表对应的实体类
 * @Date 20:55 2019/1/31
 * @Param
 * @return
 **/
public class Teacher {
    // 教师id
    private String teacherId;
    // 教师姓名
    private String teacherName;
    // 教师性别
    private String teacherSex;
    // 教师出生时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date teacherBirthYear;
    // 教师学位
    private String teacherDegree;
    // 教师职称
    private String teacherTitle;
    // 教师入职时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date teacherGrade;
    // 院系id
    private Integer collegeId;

    public Teacher() {}

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(String teacherSex) {
        this.teacherSex = teacherSex;
    }

    public Date getTeacherBirthYear() {
        return teacherBirthYear;
    }

    public void setTeacherBirthYear(Date teacherBirthYear) {
        this.teacherBirthYear = teacherBirthYear;
    }

    public String getTeacherDegree() {
        return teacherDegree;
    }

    public void setTeacherDegree(String teacherDegree) {
        this.teacherDegree = teacherDegree == null ? null : teacherDegree.trim();
    }

    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle == null ? null : teacherTitle.trim();
    }

    public Date getTeacherGrade() {
        return teacherGrade;
    }

    public void setTeacherGrade(Date teacherGrade) {
        this.teacherGrade = teacherGrade;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherSex=" + teacherSex +
                ", teacherBirthYear=" + teacherBirthYear +
                ", teacherDegree='" + teacherDegree + '\'' +
                ", teacherTitle='" + teacherTitle + '\'' +
                ", teacherGrade=" + teacherGrade +
                ", collegeId=" + collegeId +
                '}';
    }
}