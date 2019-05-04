package com.codeliu.course_select_system.entity;

/**
 * @Author liuxiaoping
 * @Description 院系表对应的实体类
 * @Date 20:42 2019/1/31
 * @Param
 * @return
 **/
public class College {

    // 院系id
    private Integer collegeId;
    // 院系名称
    private String collegeName;

    public College() {}

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName == null ? null : collegeName.trim();
    }

    @Override
    public String toString() {
        return "College{" +
                "collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                '}';
    }
}