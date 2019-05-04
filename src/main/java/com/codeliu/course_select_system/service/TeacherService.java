package com.codeliu.course_select_system.service;

import com.codeliu.course_select_system.entity.Teacher;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName liuxiaoping
 * @Description TODO
 * @Author liu
 * @Date 2019/2/1 9:19
 * @Version 1.0
 */
public interface TeacherService {

    /**
     * @Author liuxiaoping
     * @Description 获取教师总人数
     * @Date 11:07 2019/2/16
     * @Param []
     * @return java.lang.Integer
     **/
    Integer getCountTeacher();

    /**
     * 获取教师列表
     * @param toPageNo
     * @return
     */
    List<Map<String, Object>> getTeacherList(Integer toPageNo);

    /**
     * 通过姓名查找教师
     * @param teacherName
     * @return
     */
    List<Map<String, Object>> findByTeacName(String teacherName);

    /**
     * 添加一个教师
     * @param teacher
     * @return
     */
    Integer addTeacher(Teacher teacher);

    /**
     * 通过教师id查找教师
     * @param teacherId
     * @return
     */
    String getTeacById(String teacherId);

    /**
     * 通过教师id查找教师信息
     * @param teacherId
     * @return
     */
    Teacher getTeacInfoById(String teacherId);

    /**
     * 更新教师信息
     * @param teacher
     * @return
     */
    Integer updateTeac(Teacher teacher);

    /**
     * 删除教师
     * @param teacherId
     * @return
     */
    Integer deleteTeac(String teacherId);

    /**
     * 获取教师id和姓名
     * @return
     */
    List<Teacher> getTeacIdAndName();

    /**
     * 通过教师姓名查询教师id
     * @param teacherName
     * @return
     */
    String getTeacByName(String teacherName);
}
