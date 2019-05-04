package com.codeliu.course_select_system.entity;

/**
 * 用户-课程-评分
 * 用于推荐
 */
public class Ranks {

    private String studentId;
    private Integer courseId;
    private Float rank;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Float getRank() {
        return rank;
    }

    public void setRank(Float rank) {
        this.rank = rank;
    }


    @Override
    public String toString() {
        return "Ranks{" +
                "studentId='" + studentId + '\'' +
                ", courseId=" + courseId +
                ", rank=" + rank +
                '}';
    }
}
