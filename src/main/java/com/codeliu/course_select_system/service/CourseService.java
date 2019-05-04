package com.codeliu.course_select_system.service;

import com.codeliu.course_select_system.entity.Course;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName liuxiaoping
 * @Description TODO
 * @Author liu
 * @Date 2019/2/1 9:18
 * @Version 1.0
 */
public interface CourseService {

    /**
     * @Author liuxiaoping
     * @Description 获取课程总数
     * @Date 11:11 2019/2/16
     * @Param []
     * @return java.lang.Integer
     **/
    Integer getCountCourse();

    /**
     * 获取课程列表
     * @param toPageNo
     * @return
     */
    List<Map<String, Object>> getCourseList(Integer toPageNo);

    /**
     * 搜索课程
     * @param courseName
     * @return
     */
    List<Map<String, Object>> findByCourName(String courseName);

    /**
     * 添加课程
     * @param course
     * @return
     */
    Integer addCourse(Course course);

    /**
     * 通过课程id查找课程
     * @param courseId
     * @return
     */
    Integer getCourById(Integer courseId);

    /**
     * 通过课程id查找课程所有信息
     * @param courseId
     * @return
     */
    Course getCourInfoById(Integer courseId);

    /**
     * 更新课程信息
     * @param course
     * @return
     */
    Integer updateCour(Course course);

    /**
     * 删除课程
     * 如果已经有学生选了，则不能删除
     * @param courseId
     * @return
     */
    Integer deleteCour(Integer courseId);

    /**
     * 通过course_code, course_name, teacher_id查询course
     * @param course
     * @return
     */
    Integer getCourseByThree(Course course);

    /**
     * 是否开启选课
     * @param isOn
     * @param courseId
     * @return
     */
    Integer changeIsOn(Integer isOn, Integer courseId);

    /**
     * 删除指定教师开设的所有课程
     * @param teacherId
     * @return
     */
    Integer delCourseByTec(String teacherId);

    /**
     * 获取指定教师的课程列表
     * @param teacherId
     * @return
     */
    List<Map<String, Object>> getTeacCourseList(String teacherId);

    /**
     * 获取某个教师教授的课程数目
     * @param teacherId
     * @return
     */
    Integer getTeacCourseNum(String teacherId);

    /**
     * 搜索某个教师的课程
     * @param courseName
     * @param teacherId
     * @return
     */
    List<Map<String, Object>> findTeacCourByName(String courseName, String teacherId);

    /**
     * 查找所有已经开启选课的选修课
     * @param studentId
     * @param toPageNo
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> getOpenCourseList(String studentId, Integer toPageNo, Integer pageSize);

    /**
     * 获取选修课程总数
     * @param studentId
     * @return
     */
    Integer getCountOpenCourse(String studentId);

    /**
     * 搜索已经开启选课
     * @param courseName
     * @param studentId
     * @return
     */
    List<Map<String, Object>> selectOpenCourseList(String courseName, String studentId);

    /**
     * 根据课程id查询课程信息
     * @param courseId
     * @return
     */
    Map getCourseById(Integer courseId);
}
