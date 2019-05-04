package com.codeliu.course_select_system.controller;

import com.codeliu.course_select_system.entity.PageVO;
import com.codeliu.course_select_system.entity.Ranks;
import com.codeliu.course_select_system.entity.SelectCourse;
import com.codeliu.course_select_system.service.CourseService;
import com.codeliu.course_select_system.service.RanksService;
import com.codeliu.course_select_system.service.SelectCourseService;
import com.codeliu.course_select_system.service.StudentService;
import com.codeliu.course_select_system.util.ExcelUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 教师控制器
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SelectCourseService selectCourseService;

    @Autowired
    private RanksService ranksService;

    private Logger logger = LoggerFactory.getLogger(TeacherController.class);

    /**
     * 该教师开设的课程列表
     * @param model
     * @return
     */
    @GetMapping("/open/course/list")
    public String openCourseList(Model model) {

        // 获取当前登录教师的编号
        Subject subject = SecurityUtils.getSubject();
        String teacherId = (String) subject.getPrincipal();

        List<Map<String, Object>> courseList = null;

        courseList = courseService.getTeacCourseList(teacherId);

        model.addAttribute("courseNum", courseList.size());
        model.addAttribute("courseList", courseList);
        model.addAttribute("teacherId", teacherId);

        return "teacher/showCourse";
    }

    /**
     * 指定课程的学生成绩列表
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/mark/list")
    public String markList(Model model, HttpServletRequest request) {

        // 获取当前登录教师的编号
        Subject subject = SecurityUtils.getSubject();
        String teacherId = (String) subject.getPrincipal();

        Integer courseId = null;
        if(request.getParameter("courseId") != null) {
            // 获取链接中给的courseId
            courseId = Integer.parseInt(request.getParameter("courseId"));
        }

        Integer page = null;
        if(request.getParameter("page") != null) {
            // 获取链接中给的page
            page = Integer.parseInt(request.getParameter("page"));
        }

        Integer markNum = 0;

        markNum = selectCourseService.getMarkNum(courseId);

        logger.info("markNum = {}" + markNum);

        List<Map<String, Object>> markList = null;

        // 页码对象
        PageVO pageVO = new PageVO();
        // 设置总页数
        pageVO.setTotalCount(markNum);

        // 默认显示第一页的数据
        if(page == null || page == 0) {
            pageVO.setToPageNo(1);
            markList = selectCourseService.getMarkList(courseId, 0, 5);
        } else {
            pageVO.setToPageNo(page);
            markList = selectCourseService.getMarkList(courseId, (page - 1) * 5, 5);
        }

        logger.info("markList = {}" + markList);

        Integer courseNum = courseService.getTeacCourseNum(teacherId);

        model.addAttribute("courseId", courseId);
        model.addAttribute("pageVo", pageVO);
        model.addAttribute("markList", markList);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("teacherId", teacherId);

        return "teacher/showMark";
    }

    /**
     * 搜索课程
     * @param model
     * @param courseName
     * @return
     */
    @PostMapping("/selectCourse")
    public String selectCourse(Model model, String courseName) {
        // 获取当前登录教师的编号
        Subject subject = SecurityUtils.getSubject();
        String teacherId = (String) subject.getPrincipal();

        List<Map<String, Object>> courseList = null;

        courseList = courseService.findTeacCourByName(courseName, teacherId);

        model.addAttribute("courseNum", courseList.size());
        model.addAttribute("courseList", courseList);
        model.addAttribute("teacherId", teacherId);

        return "teacher/showCourse";
    }

    /**
     * 打分
     * @param selectCourse
     * @return
     */
    @PostMapping("/mark")
    @ResponseBody
    public Integer mark(SelectCourse selectCourse) {

        Integer code = 0;
        String studentId = selectCourse.getStudentId();
        Integer courseId = selectCourse.getCourseId();

        // 查看student_id是否正确
        String stu_id = null;
        stu_id = studentService.getStuById(studentId);

        // 学生id错误
        if(stu_id == null) {
            code = 1;
            return code;
        }

        Integer course_id = null;
        course_id = courseService.getCourById(courseId);

        // 课程id错误
        if(course_id == null) {
            code = 2;
            return code;
        }

        // 执行插入操作
        Integer num = selectCourseService.addMark(selectCourse);

        logger.info("num = {}" + num);

        if(num != 1) {
            code = -1;
            return code;
        }

        Float mark = selectCourse.getMark();
        boolean flag = "选修课".equals(courseService.getCourInfoById(selectCourse.getCourseId()).getCourseType());
        if(flag) {
            Ranks ranks = new Ranks();
            ranks.setStudentId(studentId);
            ranks.setCourseId(courseId);

            if(mark > 90 && mark <= 100) {
                ranks.setRank(5.0f);
            } else if(mark > 80 && mark <= 90) {
                ranks.setRank(4.0f);
            } else if(mark > 70 && mark <= 80) {
                ranks.setRank(3.0f);
            } else {
                ranks.setRank(2.0f);
            }

            ranksService.updateRank(ranks);
        }

        return code;
    }

    /**
     * 通过excel文件，批量给学生打分
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/upload/mark")
    public String uploadStudentExcel(HttpServletRequest request) {


        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

        Integer courseId = null;
        if(request.getParameter("courseId") != null) {
            // 获取链接中给的courseId
            courseId = Integer.parseInt(request.getParameter("courseId"));
        }

        MultipartFile file = multipartHttpServletRequest.getFile("markFile");
        if(file.isEmpty()) {
            return "redirect:/teacher/mark/list?courseId=" + courseId;
        }

        boolean flag = "选修课".equals(courseService.getCourInfoById(courseId).getCourseType());

        try {
            InputStream inputStream = file.getInputStream();
            List<List<Object>> list = ExcelUtils.getMarkListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();

            logger.error("list = " + list);

            for (int i = 0; i < list.size(); i++) {
                List<Object> studentList = list.get(i);

                SelectCourse selectCourse = new SelectCourse();

                selectCourse.setCourseId(courseId);
                String studentId = studentList.get(0).toString();
                selectCourse.setStudentId(studentId);
                Float mark = Float.parseFloat(studentList.get(1).toString());
                selectCourse.setMark(mark);

                selectCourseService.addMark(selectCourse);
                // 如果是选修课，修改ranks
                if(flag) {
                    Ranks ranks = new Ranks();
                    ranks.setStudentId(studentId);
                    ranks.setCourseId(courseId);

                    if(mark > 90 && mark <= 100) {
                        ranks.setRank(5.0f);
                    } else if(mark > 80 && mark <= 90) {
                        ranks.setRank(4.0f);
                    } else if(mark > 70 && mark <= 80) {
                        ranks.setRank(3.0f);
                    } else {
                        ranks.setRank(2.0f);
                    }

                    ranksService.updateRank(ranks);
                }
            }
        } catch (Exception e) {
            return "redirect:/teacher/mark/list?courseId=" + courseId;
        }

        return "redirect:/teacher/mark/list?courseId=" + courseId;
    }

    /**
     * 修改密码页面
     * @param model
     * @return
     */
    @GetMapping(value = "/passwordRest")
    public String passwordRest(Model model, HttpServletRequest request) {

        String teacherId = null;

        if(request.getParameter("teacherId") != null) {
            teacherId = request.getParameter("teacherId");
        }

        Integer courseNum = courseService.getTeacCourseNum(teacherId);
        model.addAttribute("courseNum", courseNum);

        return "teacher/passwordRest";
    }
}
