package com.codeliu.course_select_system.service;

import com.codeliu.course_select_system.entity.SelectCourse;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName liuxiaoping
 * @Description TODO
 * @Author liu
 * @Date 2019/2/1 9:19
 * @Version 1.0
 */
public interface SelectCourseService {

    /**
     * 查看该课程是否有选课记录
     * @param courseId
     * @return
     */
    Map<String, Integer> isSelect(Integer courseId);

    /**
     * 删除指定学生的选课记录
     * @param studentId
     * @return
     */
    Integer delCourse(String studentId);

    /**
     * 获取某门课程的分数列表
     * @param courseId
     * @param toPageNo
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> getMarkList(Integer courseId, Integer toPageNo, Integer pageSize);

    /**
     * 获取选某门课的学生人数
     * @param courseId
     * @return
     */
    Integer getMarkNum(Integer courseId);

    /**
     * 打分操作
     * @param selectCourse
     * @return
     */
    Integer addMark(SelectCourse selectCourse);

    /**
     * 批量增加选课记录
     * @param stuIdList
     * @param courseId
     * @return
     */
    Integer addRecord(List<String> stuIdList, Integer courseId);

    /**
     * 增加一条选课记录
     * @param courseId
     * @param studentId
     * @return
     */
    Integer addOneRecord(Integer courseId, String studentId);

    /**
     * 查找所有已选课程
     * @param studentId
     * @param toPageNo
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> getSelectCourseList(String studentId, Integer toPageNo,
                                                  Integer pageSize);

    /**
     * 获取某个学生的已选课程数
     * @param studentId
     * @return
     */
    Integer getSelectCourseNum(String studentId);

    /**
     * 搜索已选课程
     * @param studentId
     * @param courseName
     * @return
     */
    List<Map<String, Object>> selectCourseList(String studentId, String courseName);

    /**
     * 退课
     * @param studentId
     * @param courseId
     * @return
     */
    Integer outCourse(String studentId, Integer courseId);

    /**
     * 获取某学生已修课程列表
     * @param studentId
     * @param toPageNo
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> getOverCourseList(String studentId, Integer toPageNo,
                                                  Integer pageSize);

    /**
     * 获取某学生已修课程数目
     * @param studentId
     * @return
     */
    Integer getOverCourseNum(String studentId);

    /**
     * 搜索已修课程
     * @param studentId
     * @param courseName
     * @return
     */
    List<Map<String, Object>> selectOverCourseList(String studentId, String courseName);

    /**
     * 某个学生给某门已修的课程评分
     * @param selectCourse
     * @return
     */
    Integer addEvaluation(SelectCourse selectCourse);

    /**
     * 获取评分最高的十门选修课
     * @return
     */
    List<Map<String, Object>> getTopTenEvaCourse();

    /**
     * 获取选修人数最高的十门选修课
     * @return
     */
    List<Map<String, Object>> getTopTenNumCourse();

    /**
     * 获取某个学生已选的课程id列表
     * @param studentId
     * @return
     */
    List<Integer> courseIdList(String studentId);

}
