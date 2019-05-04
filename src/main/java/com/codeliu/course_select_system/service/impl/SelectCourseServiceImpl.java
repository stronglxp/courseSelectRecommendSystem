package com.codeliu.course_select_system.service.impl;

import com.codeliu.course_select_system.dao.SelectCourseMapper;
import com.codeliu.course_select_system.entity.SelectCourse;
import com.codeliu.course_select_system.service.SelectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName liuxiaoping
 * @Description TODO
 * @Author liu
 * @Date 2019/2/1 9:54
 * @Version 1.0
 */
@Service
public class SelectCourseServiceImpl implements SelectCourseService {

    @Autowired
    private SelectCourseMapper selectCourseMapper;

    @Override
    public Map<String, Integer> isSelect(Integer courseId) {

        Map<String, Integer> map = new HashMap<>();

        map = selectCourseMapper.isSelect(courseId);
        return map;
    }

    @Override
    public Integer delCourse(String studentId) {
        return selectCourseMapper.delCourse(studentId);
    }

    @Override
    public List<Map<String, Object>> getMarkList(Integer courseId, Integer toPageNo, Integer pageSize) {
        return selectCourseMapper.getMarkList(courseId, toPageNo, pageSize);
    }

    @Override
    public Integer getMarkNum(Integer courseId) {
        return selectCourseMapper.getMarkNum(courseId);
    }

    @Override
    public Integer addMark(SelectCourse selectCourse) {
        return selectCourseMapper.addMark(selectCourse);
    }

    @Override
    public Integer addRecord(List<String> stuIdList, Integer courseId) {
        return selectCourseMapper.addRecord(stuIdList, courseId);
    }

    @Override
    public Integer addOneRecord(Integer courseId, String studentId) {
        return selectCourseMapper.addOneRecord(courseId, studentId);
    }

    @Override
    public List<Map<String, Object>> getSelectCourseList(String studentId, Integer toPageNo, Integer pageSize) {
        return selectCourseMapper.getSelectCourseList(studentId, toPageNo, pageSize);
    }

    @Override
    public Integer getSelectCourseNum(String studentId) {
        return selectCourseMapper.getSelectCourseNum(studentId);
    }

    @Override
    public List<Map<String, Object>> selectCourseList(String studentId, String courseName) {
        return selectCourseMapper.selectCourseList(studentId, courseName);
    }

    @Override
    public Integer outCourse(String studentId, Integer courseId) {
        return selectCourseMapper.outCourse(studentId, courseId);
    }

    @Override
    public List<Map<String, Object>> getOverCourseList(String studentId, Integer toPageNo, Integer pageSize) {
        return selectCourseMapper.getOverCourseList(studentId, toPageNo, pageSize);
    }

    @Override
    public Integer getOverCourseNum(String studentId) {
        return selectCourseMapper.getOverCourseNum(studentId);
    }

    @Override
    public List<Map<String, Object>> selectOverCourseList(String studentId, String courseName) {
        return selectCourseMapper.selectOverCourseList(studentId, courseName);
    }

    @Override
    public Integer addEvaluation(SelectCourse selectCourse) {
        return selectCourseMapper.addEvaluation(selectCourse);
    }

    @Override
    public List<Map<String, Object>> getTopTenEvaCourse() {
        return selectCourseMapper.getTopTenEvaCourse();
    }

    @Override
    public List<Integer> courseIdList(String studentId) {
        return selectCourseMapper.courseIdList(studentId);
    }

    @Override
    public List<Map<String, Object>> getTopTenNumCourse() {
        return selectCourseMapper.getTopTenNumCourse();
    }

}
