package com.codeliu.course_select_system.service.impl;

import com.codeliu.course_select_system.dao.CourseMapper;
import com.codeliu.course_select_system.entity.Course;
import com.codeliu.course_select_system.entity.PageVO;
import com.codeliu.course_select_system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName liuxiaoping
 * @Description TODO
 * @Author liu
 * @Date 2019/2/1 9:53
 * @Version 1.0
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Integer getCountCourse() {
        return courseMapper.getCountCourse();
    }

    @Override
    public List<Map<String, Object>> getCourseList(Integer toPageNo) {
        PageVO pageVO = new PageVO();
        pageVO.setToPageNo(toPageNo);

        return courseMapper.getCourseList(pageVO);
    }

    @Override
    public List<Map<String, Object>> findByCourName(String courseName) {
        return courseMapper.findByCourName(courseName);
    }

    @Override
    public Integer addCourse(Course course) {
        // 执行插入操作
        int result = courseMapper.addCourse(course);

        return result;
    }

    @Override
    public Course getCourInfoById(Integer courseId) {
        if(courseId == null) {
            return null;
        }

        Course course = courseMapper.getCourInfoById(courseId);
        return course;
    }

    @Override
    public Integer updateCour(Course course) {
        Integer result = courseMapper.updateCour(course);
        return result;
    }

    @Override
    public Integer deleteCour(Integer courseId) {
        Integer result = courseMapper.deleteCour(courseId);
        return result;
    }

    @Override
    public Integer getCourById(Integer courseId) {
        Integer id = null;
        id = courseMapper.getCourById(courseId);
        return id;
    }

    @Override
    public Integer getCourseByThree(Course course) {
        Integer id = null;
        id = courseMapper.getCourseByThree(course);
        return id;
    }

    @Override
    public Integer changeIsOn(Integer isOn, Integer courseId) {
        return courseMapper.changeIsOn(isOn, courseId);
    }

    @Override
    public Integer delCourseByTec(String teacherId) {
        return courseMapper.delCourseByTec(teacherId);
    }

    @Override
    public List<Map<String, Object>> getTeacCourseList(String teacherId) {
        return courseMapper.getTeacCourseList(teacherId);
    }

    @Override
    public Integer getTeacCourseNum(String teacherId) {
        return courseMapper.getTeacCourseNum(teacherId);
    }

    @Override
    public List<Map<String, Object>> findTeacCourByName(String courseName, String teacherId) {
        return courseMapper.findTeacCourByName(courseName, teacherId);
    }

    @Override
    public List<Map<String, Object>> getOpenCourseList(String studentId, Integer toPageNo, Integer pageSize) {
        return courseMapper.getOpenCourseList(studentId, toPageNo, pageSize);
    }

    @Override
    public Integer getCountOpenCourse(String studentId) {
        return courseMapper.getCountOpenCourse(studentId);
    }

    @Override
    public List<Map<String, Object>> selectOpenCourseList(String courseName, String studentId) {
        return courseMapper.selectOpenCourseList(courseName, studentId);
    }

    @Override
    public Map getCourseById(Integer courseId) {
        return courseMapper.getCourseById(courseId);
    }
}
