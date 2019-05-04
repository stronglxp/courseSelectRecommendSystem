package com.codeliu.course_select_system.service.impl;

import com.codeliu.course_select_system.dao.StudentMapper;
import com.codeliu.course_select_system.entity.PageVO;
import com.codeliu.course_select_system.entity.Student;
import com.codeliu.course_select_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Integer getCountStudent() {
        return studentMapper.getCountStudent();
    }

    @Override
    public List<Map<String, Object>> getStudentList(Integer toPageNo) {
        PageVO pageVO = new PageVO();
        pageVO.setToPageNo(toPageNo);

        return studentMapper.getStudentList(pageVO);
    }

    @Override
    public List<Map<String, Object>> findByName(String studentName) {

        return studentMapper.findByName(studentName);
    }

    @Override
    public Integer addStudent(Student student) {

        // 执行插入操作
        int result = studentMapper.addStudent(student);

        return result;
    }

    @Override
    public Student getStuInfoById(String stuId) {

        if(stuId == null) {
            return null;
        }

        Student student = studentMapper.getStuInfoById(stuId);
        return student;
    }

    @Override
    public Integer updateStu(Student student) {
        Integer result = studentMapper.updateStu(student);
        return result;
    }

    @Override
    public Integer deleteStu(String stuId) {
        Integer result = studentMapper.deleteStu(stuId);
        return result;
    }

    @Override
    public String getStuById(String stuId) {
        String id = null;
        id = studentMapper.getStuById(stuId);
        return id;
    }

    @Override
    public List<String> getCollegeStu(Integer collegeId) {

        List<String> stuIdList = null;
        stuIdList = studentMapper.getCollegeStu(collegeId);
        return stuIdList;
    }
}
