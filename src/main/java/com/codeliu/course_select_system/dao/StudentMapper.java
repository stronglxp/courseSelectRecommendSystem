package com.codeliu.course_select_system.dao;

import com.codeliu.course_select_system.entity.PageVO;
import com.codeliu.course_select_system.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author liuxiaoping
 * @Description Student  dao
 * @Date 9:09 2019/2/1
 * @Param
 * @return
 **/
@Mapper
public interface StudentMapper {

    /**
     * @Author liuxiaoping
     * @Description 获取学生总人数
     * @Date 20:39 2019/2/13
     * @Param []
     * @return java.lang.Integer
     **/
    Integer getCountStudent();

    /**
     * @Author liuxiaoping
     * @Description 获取所有学生信息，进行分页
     * @Date 20:41 2019/2/13
     * @Param [pageVO]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getStudentList(PageVO pageVO);

    /**
     * @Author liuxiaoping
     * @Description 通过姓名查找学生
     * @Date 11:39 2019/2/16
     * @Param [studentName]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> findByName(String studentName);

    /**
     * @Author liuxiaoping
     * @Description 添加一个学生
     * @Date 14:46 2019/2/16
     * @Param [student]
     * @return java.lang.Integer
     **/
    Integer addStudent(Student student);

    /**
     * @Author liuxiaoping
     * @Description 通过学号查找学生
     * @Date 14:57 2019/2/16
     * @Param [stuId]
     * @return java.lang.String
     **/
    String getStuById(String stuId);

    /**
     * @Author liuxiaoping
     * @Description 通过学号查询学生所有信息
     * @Date 16:45 2019/2/16
     * @Param [stuId]
     * @return Student
     **/
    Student getStuInfoById(String stuId);

    /**
     * @Author liuxiaoping
     * @Description 更新学生信息
     * @Date 17:38 2019/2/16
     * @Param [student]
     * @return java.lang.Integer
     **/
    Integer updateStu(Student student);

    /**
     * 删除学生
     * @param stuId
     * @return
     */
    Integer deleteStu(String stuId);

    /**
     * 获取某院系所有学生id
     * @param collegeId
     * @return
     */
    List<String> getCollegeStu(Integer collegeId);
}