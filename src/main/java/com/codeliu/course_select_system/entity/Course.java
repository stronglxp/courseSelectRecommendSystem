package com.codeliu.course_select_system.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author liuxiaoping
 * @Description 课程表对应的实体类
 * @Date 20:47 2019/1/31
 * @Param
 * @return
 **/
public class Course {
    // 课程id
    private Integer courseId;
    // 课程编号
    private String courseCode;
    // 课程名称
    private String courseName;
    // 教师id
    private String teacherId;
    // 开课时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date courseTime;
    // 开课地点
    private String classRoom;
    // 课程学时
    private Integer courseWeek;
    // 课程类型
    private String courseType;
    // 院系id
    private Integer collegeId;
    // 学分
    private Float score;
    // 是否开启选课
    private Integer isOn;

    public Course() {}

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public Date getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Date courseTime) {
        this.courseTime = courseTime;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom == null ? null : classRoom.trim();
    }

    public Integer getCourseWeek() {
        return courseWeek;
    }

    public void setCourseWeek(Integer courseWeek) {
        this.courseWeek = courseWeek;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType == null ? null : courseType.trim();
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getIsOn() {
        return isOn;
    }

    public void setIsOn(Integer isOn) {
        this.isOn = isOn;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", courseTime=" + courseTime +
                ", classRoom='" + classRoom + '\'' +
                ", courseWeek=" + courseWeek +
                ", courseType='" + courseType + '\'' +
                ", collegeId=" + collegeId +
                ", score=" + score +
                ", isOn=" + isOn +
                '}';
    }
}