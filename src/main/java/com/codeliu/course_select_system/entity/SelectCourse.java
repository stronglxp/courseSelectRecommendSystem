package com.codeliu.course_select_system.entity;

/**
 * @Author liuxiaoping
 * @Description 学生选课表对应的实体类
 * @Date 20:50 2019/1/31
 * @Param
 * @return
 **/
public class SelectCourse {

    // 课程id
    private Integer courseId;
    // 学生id
    private String studentId;
    // 分数
    private Float mark;
    // 评价
    private Float evaluation;

    public SelectCourse() {}

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }

    public Float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Float evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        return "SelectCourse{" +
                "courseId=" + courseId +
                ", studentId='" + studentId + '\'' +
                ", mark=" + mark +
                ", evaluation=" + evaluation +
                '}';
    }
}