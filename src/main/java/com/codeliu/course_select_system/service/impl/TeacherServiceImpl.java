package com.codeliu.course_select_system.service.impl;

import com.codeliu.course_select_system.dao.TeacherMapper;
import com.codeliu.course_select_system.entity.PageVO;
import com.codeliu.course_select_system.entity.Teacher;
import com.codeliu.course_select_system.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName liuxiaoping
 * @Description TODO
 * @Author liu
 * @Date 2019/2/1 9:55
 * @Version 1.0
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Integer getCountTeacher() {
        return teacherMapper.getCountTeacher();
    }

    @Override
    public List<Map<String, Object>> getTeacherList(Integer toPageNo) {
        PageVO pageVO = new PageVO();
        pageVO.setToPageNo(toPageNo);

        return teacherMapper.getTeacherList(pageVO);
    }

    @Override
    public List<Map<String, Object>> findByTeacName(String teacherName) {
        return teacherMapper.findByTeacName(teacherName);
    }

    @Override
    public Integer addTeacher(Teacher teacher) {
        // 执行插入操作
        int result = teacherMapper.addTeacher(teacher);

        return result;
    }

    @Override
    public Teacher getTeacInfoById(String teacherId) {
        if(teacherId == null) {
            return null;
        }

        Teacher teacher = teacherMapper.getTeacInfoById(teacherId);
        return teacher;
    }

    @Override
    public Integer updateTeac(Teacher teacher) {
        Integer result = teacherMapper.updateTeac(teacher);
        return result;
    }

    @Override
    public Integer deleteTeac(String teacherId) {
        Integer result = teacherMapper.deleteTeac(teacherId);
        return result;
    }

    @Override
    public String getTeacById(String teacherId) {
        String id = null;
        id = teacherMapper.getTeacById(teacherId);
        return id;
    }

    @Override
    public List<Teacher> getTeacIdAndName() {
        return teacherMapper.getTeacIdAndName();
    }

    @Override
    public String getTeacByName(String teacherName) {
        return teacherMapper.getTeacByName(teacherName);
    }
}
