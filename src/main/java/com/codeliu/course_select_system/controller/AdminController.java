package com.codeliu.course_select_system.controller;

import com.codeliu.course_select_system.entity.*;
import com.codeliu.course_select_system.service.*;
import com.codeliu.course_select_system.util.DataUtils;
import com.codeliu.course_select_system.util.ExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName liuxiaoping
 * @Description admin相关的方法
 * @Author liu
 * @Date 2019/2/11 19:27
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private UserService userService;

    @Autowired
    private SelectCourseService selectCourseService;


    /*----------------学生操作-----------------*/

    /**
     * @Author liuxiaoping
     * @Description 显示学生信息
     * @Date 20:52 2019/2/13
     * @Param [model, page]
     * @return java.lang.String
     **/
    @RequestMapping("/student/list")
    public String showStudent(Model model, HttpServletRequest request) {

        Integer page = null;
        if(request.getParameter("page") != null) {
            // 获取链接中给的page
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Map<String, Object>> studentList = null;

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总人数
        int teacherNum = teacherService.getCountTeacher();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        // 页码对象
        PageVO pageVO = new PageVO();
        // 设置总页数
        pageVO.setTotalCount(studentNum);
        if(page == null || page == 0) {
            // 默认显示第一页的数据
            pageVO.setToPageNo(1);
            studentList = studentService.getStudentList(1);
        } else {
            pageVO.setToPageNo(page);
            studentList = studentService.getStudentList(page);
        }

        model.addAttribute("studentNum", studentNum);
        model.addAttribute("teacherNum", teacherNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeNum);
        model.addAttribute("studentList", studentList);
        model.addAttribute("pageVo", pageVO);

        return "admin/showStudent";
    }

    /**
     * @Author liuxiaoping
     * @Description 搜索学生
     * @Date 11:21 2019/2/16
     * @Param [studentName, model]
     * @return java.lang.String
     **/
    @PostMapping("/selectStudent")
    public String selectStudent(String studentName, Model model) {
        if(studentName == null) {
            return null;
        }

        // 获取教师总人数
        int teacherNum = teacherService.getCountTeacher();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        List<Map<String, Object>> studentList = null;
        studentList = studentService.findByName(studentName);
        model.addAttribute("studentList", studentList);
        // 返回搜索的结果数
        model.addAttribute("studentNum", studentList.size());
        model.addAttribute("teacherNum", teacherNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeNum);
        return "admin/showStudent";
    }

    /**
     * @Author liuxiaoping
     * @Description 增加学生页面，获取所有的院系供选择
     * @Date 14:02 2019/2/16
     * @Param [model]
     * @return java.lang.String
     **/
    @GetMapping("/addStudent")
    public String addStudentUI(Model model) {
        List<College> collegeList = null;

        collegeList = collegeService.getCollegeList();

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总人数
        int teacherNum = teacherService.getCountTeacher();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        model.addAttribute("collegeList", collegeList);
        model.addAttribute("studentNum", studentNum);
        model.addAttribute("teacherNum", teacherNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeNum);

        return "admin/addStudent";

    }

    /**
     * @Author liuxiaoping
     * @Description 获取用户输入的信息，执行插入学生操作
     * @Date 14:36 2019/2/16
     * @Param [student, model]
     * @return java.lang.String
     **/
    @PostMapping("/addStudent")
    public String addStudent(Student student, Model model) {

        if(student == null) {
            model.addAttribute("msg", "添加失败");
            // 返回添加学生的页面
            return "admin/addStudent";
        }

        // 判断college_id是否正确
        Integer collegeId = collegeService.getCollegeById(student.getCollegeId());
        if(collegeId == null) {
            model.addAttribute("msg", "院系不存在!");
            // 返回添加学生的页面
            return "admin/addStudent";
        }

        // 判断学号是否重复
        String stuId = studentService.getStuById(student.getStuId());
        // 存在重复的
        if(stuId != null) {
            model.addAttribute("msg", "学号重复!");
            // 返回添加学生的页面
            return "admin/addStudent";
        }

        // 调用方法插入
        int result1 = studentService.addStudent(student);

        if(result1 != 1) {
            model.addAttribute("msg", "添加失败!");
            // 返回添加学生的页面
            return "admin/addStudent";
        }

        // 把该学生添加到登录表
        User user = new User();
        user.setUserName(student.getStuId());
        // 学生角色
        user.setRoleId(3);
        // 设置盐
        String salt = DataUtils.getSalt();
        user.setUserSalt(salt);
        // 设置初始密码123456
        user.setUserPassword(DataUtils.getMD5Str("123456", salt));
        // 插入一个user
        int result2 = userService.addUser(user);

        if(result1 == 1 && result2 == 1) {
            model.addAttribute("msg", "添加成功!");
            // 返回添加学生的页面
            return "admin/addStudent";
        }

        model.addAttribute("msg", "添加失败");
        return "admin/addStudent";
    }

    /**
     * 通过excel文件，批量增加学生
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/upload/student")
    public String uploadStudentExcel(HttpServletRequest request) {


        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartHttpServletRequest.getFile("studentFile");
        if(file.isEmpty()) {
            return "redirect:/admin/student/list";
        }

        try {
            InputStream inputStream = file.getInputStream();
            List<List<Object>> list = ExcelUtils.getStudentListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();

            logger.error("list = " + list);

            for (int i = 0; i < list.size(); i++) {
                List<Object> studentList = list.get(i);

                Student student = new Student();

                student.setStuId(studentList.get(0).toString());
                student.setStuName(studentList.get(1).toString());
                student.setStuSex(studentList.get(2).toString());
                logger.warn("3 = " + student);
                // 格式化时间
                Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(studentList.get(3).toString());
                student.setStuBirthYear(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(date)));
                logger.warn("4 = " + student);
                date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(studentList.get(4).toString());
                student.setStuGrade(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(date)));

                logger.warn("1 = " + student);

                // 通过院系名称查询院系id
                Integer collegeId = collegeService.getCollegeByName(studentList.get(5).toString());
                // 院系有误，直接跳过这条记录
                if(collegeId == null || collegeId == 0) {
                    continue;
                }

                student.setCollegeId(collegeId);
                logger.warn("2 = " + student);
                // 执行插入操作
                studentService.addStudent(student);

                // 把该学生添加到登录表
                User user = new User();
                user.setUserName(student.getStuId());
                // 学生角色
                user.setRoleId(3);
                // 设置盐
                String salt = DataUtils.getSalt();
                user.setUserSalt(salt);
                // 设置初始密码123456
                user.setUserPassword(DataUtils.getMD5Str("123456", salt));
                // 插入一个user
                userService.addUser(user);
            }
        } catch (Exception e) {
            return "redirect:/admin/student/list";
        }

        return "redirect:/admin/student/list";
    }

    /**
     * @Author liuxiaoping
     * @Description 跳转到修改学生的页面，并携带该学生的信息
     * @Date 16:50 2019/2/16
     * @Param [model, request]
     * @return java.lang.String
     **/
    @GetMapping("/editStudent")
    public String editStudentUI(Model model, HttpServletRequest request) {

        String stuId = null;

        // 获取链接中的参数
        if(request.getParameter("id") != null) {
            stuId = request.getParameter("id");
        }

        Student student = studentService.getStuInfoById(stuId);

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总人数
        int teacherNum = teacherService.getCountTeacher();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        List<College> collegeList = null;

        // 获取所有院系
        collegeList = collegeService.getCollegeList();

        model.addAttribute("collegeList", collegeList);
        model.addAttribute("studentNum", studentNum);
        model.addAttribute("teacherNum", teacherNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeNum);
        model.addAttribute("student", student);

        return "admin/editStudent";
    }

    /**
     * @Author liuxiaoping
     * @Description 接收数据，修改学生信息
     * @Date 17:35 2019/2/16
     * @Param [model, student]
     * @return java.lang.String
     **/
    @PostMapping("/editStudent")
    public String editStudent(Model model, Student student) {

        if(student == null) {
            model.addAttribute("msg", "更新失败!");
            // 返回添加学生的页面
            return "admin/editStudent";
        }

        // 判断college_id是否正确
        Integer collegeId = collegeService.getCollegeById(student.getCollegeId());
        if(collegeId == null) {
            model.addAttribute("msg", "院系不存在!");
            // 返回添加学生的页面
            return "admin/editStudent";
        }

        // 调用方法更新信息
        Integer result = studentService.updateStu(student);

        if(result != null && result == 1) {
            model.addAttribute("msg", "更新成功!");
            // 返回添加学生的页面
            return "admin/editStudent";
        }

        model.addAttribute("msg", "更新失败!");
        // 把id传过去，才能跳转到/admin/editStudent接口
        model.addAttribute("student", student);

        return "admin/editStudent";
    }

    /**
     * 删除一个学生
     * @param model
     * @param stuId
     * @return
     */
    @PostMapping("/removeStudent")
    public String removeStudent(Model model, @RequestParam("stuId") String stuId) {

        Integer result = studentService.deleteStu(stuId);

        // 删除成功
        if(result != null && result == 1) {
            // 顺带把user表的数据删除
            userService.delUser(stuId);
            // 删除学生的选课
            selectCourseService.delCourse(stuId);
            model.addAttribute("msg", "删除成功!");
        } else {
            model.addAttribute("msg", "删除失败!");
        }

        return "admin/showStudent";
    }

    /*----------------教师操作-----------------*/

    /**
     * 获取教师列表
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/teacher/list")
    public String showTeacher(Model model, HttpServletRequest request) {
        Integer page = null;
        if(request.getParameter("page") != null) {
            // 获取链接中给的page
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Map<String, Object>> teacherList = null;

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总人数
        int teacherNum = teacherService.getCountTeacher();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        // 页码对象
        PageVO pageVO = new PageVO();
        // 设置总页数
        pageVO.setTotalCount(teacherNum);
        if(page == null || page == 0) {
            // 默认显示第一页的数据
            pageVO.setToPageNo(1);
            teacherList = teacherService.getTeacherList(1);
        } else {
            pageVO.setToPageNo(page);
            teacherList = teacherService.getTeacherList(page);
        }

        model.addAttribute("studentNum", studentNum);
        model.addAttribute("teacherNum", teacherNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeNum);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("pageVo", pageVO);

        return "admin/showTeacher";
    }

    /**
     * 搜索教师
     * @param teacherName
     * @param model
     * @return
     */
    @PostMapping("/selectTeacher")
    public String selectTeacher(String teacherName, Model model) {
        if(teacherName == null) {
            return null;
        }

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        List<Map<String, Object>> teacherList = null;
        teacherList = teacherService.findByTeacName(teacherName);
        model.addAttribute("teacherList", teacherList);
        // 返回搜索的结果数
        model.addAttribute("studentNum", studentNum);
        model.addAttribute("collegeNum", collegeNum);
        model.addAttribute("teacherNum", teacherList.size());
        model.addAttribute("courseNum", courseNum);
        return "admin/showTeacher";
    }

    /**
     * 增加教师页面，提供所有的院系供选择
     * @param model
     * @return
     */
    @GetMapping("/addTeacher")
    public String addTeacherUI(Model model) {
        List<College> collegeList = null;

        collegeList = collegeService.getCollegeList();

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总人数
        int teacherNum = teacherService.getCountTeacher();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        model.addAttribute("collegeList", collegeList);
        model.addAttribute("studentNum", studentNum);
        model.addAttribute("teacherNum", teacherNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeNum);

        return "admin/addTeacher";

    }

    /**
     * 获取用户输入的信息，执行插入教师操作
     * @param teacher
     * @param model
     * @return
     */
    @PostMapping("/addTeacher")
    public String addTeacher(Teacher teacher, Model model) {

        if(teacher == null) {
            model.addAttribute("msg", "添加失败");
            // 返回添加教师的页面
            return "admin/addTeacher";
        }

        // 判断college_id是否正确
        Integer collegeId = collegeService.getCollegeById(teacher.getCollegeId());
        if(collegeId == null) {
            model.addAttribute("msg", "院系不存在!");
            // 返回添加学生的页面
            return "admin/addTeacher";
        }

        // 查看教师id是否存在重复
        String teacherId = null;
        teacherId = teacherService.getTeacById(teacher.getTeacherId());

        // 存在重复的
        if(teacherId != null) {
            model.addAttribute("msg", "工号重复!");
            // 返回添加教师的页面
            return "admin/addTeacher";
        }

        // 调用方法插入
        int result1 = teacherService.addTeacher(teacher);

        if(result1 != 1) {
            model.addAttribute("msg", "添加失败!");
            // 返回添加教师的页面
            return "admin/addTeacher";
        }

        // 把该教师添加到登录表
        User user = new User();
        user.setUserName(teacher.getTeacherId());
        // 教师角色
        user.setRoleId(2);
        // 设置盐
        String salt = DataUtils.getSalt();
        user.setUserSalt(salt);
        // 设置初始密码123456
        user.setUserPassword(DataUtils.getMD5Str("123456", salt));
        // 插入一个user
        int result2 = userService.addUser(user);

        if(result1 == 1 && result2 == 1) {
            model.addAttribute("msg", "添加成功!");
            // 返回添加教师的页面
            return "admin/addTeacher";
        }

        model.addAttribute("msg", "添加失败");
        return "admin/addTeacher";
    }

    /**
     * 通过excel文件，批量增加教师
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/upload/teacher")
    public String uploadTeacherExcel(HttpServletRequest request) {


        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartHttpServletRequest.getFile("teacherFile");
        if(file.isEmpty()) {
            return "redirect:/admin/teacher/list";
        }

        try {
            InputStream inputStream = file.getInputStream();
            List<List<Object>> list = ExcelUtils.getTeacherListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();

            logger.error("list = " + list);

            for (int i = 0; i < list.size(); i++) {
                List<Object> teacherList = list.get(i);

                Teacher teacher = new Teacher();

                teacher.setTeacherId(teacherList.get(0).toString());
                teacher.setTeacherName(teacherList.get(1).toString());
                teacher.setTeacherSex(teacherList.get(2).toString());
                // 格式化时间
                Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(teacherList.get(3).toString());
                teacher.setTeacherBirthYear(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(date)));

                teacher.setTeacherDegree(teacherList.get(4).toString());
                teacher.setTeacherTitle(teacherList.get(5).toString());

                date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(teacherList.get(6).toString());
                teacher.setTeacherGrade(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(date)));

                // 通过院系名称查询院系id
                Integer collegeId = collegeService.getCollegeByName(teacherList.get(7).toString());
                // 院系有误，直接跳过这条记录
                if(collegeId == null || collegeId == 0) {
                    continue;
                }

                teacher.setCollegeId(collegeId);
                // 执行插入操作
                teacherService.addTeacher(teacher);

                // 把该教师添加到登录表
                User user = new User();
                user.setUserName(teacher.getTeacherId());
                // 教师角色
                user.setRoleId(2);
                // 设置盐
                String salt = DataUtils.getSalt();
                user.setUserSalt(salt);
                // 设置初始密码123456
                user.setUserPassword(DataUtils.getMD5Str("123456", salt));
                // 插入一个user
                userService.addUser(user);
            }
        } catch (Exception e) {
            return "redirect:/admin/teacher/list";
        }

        return "redirect:/admin/teacher/list";
    }

    /**
     * 跳转到修改教师的页面，并携带该教师的信息
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/editTeacher")
    public String editTeacherUI(Model model, HttpServletRequest request) {

        String teacherId = null;

        // 获取链接中的参数
        if(request.getParameter("id") != null) {
            teacherId = request.getParameter("id");
        }

        Teacher teacher = teacherService.getTeacInfoById(teacherId);

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总人数
        int teacherNum = teacherService.getCountTeacher();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        List<College> collegeList = null;

        // 获取所有院系
        collegeList = collegeService.getCollegeList();

        model.addAttribute("collegeList", collegeList);
        model.addAttribute("studentNum", studentNum);
        model.addAttribute("teacherNum", teacherNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeNum);
        model.addAttribute("teacher", teacher);

        return "admin/editTeacher";
    }

    /**
     * 接收数据，修改教师信息
     * @param model
     * @param teacher
     * @return
     */
    @PostMapping("/editTeacher")
    public String editTeacher(Model model, Teacher teacher) {

        if(teacher == null) {
            model.addAttribute("msg", "更新失败!");
            // 返回添加教师的页面
            return "admin/editTeacher";
        }

        // 判断college_id是否正确
        Integer collegeId = collegeService.getCollegeById(teacher.getCollegeId());
        if(collegeId == null) {
            model.addAttribute("msg", "院系不存在!");
            // 返回添加学生的页面
            return "admin/editTeacher";
        }

        // 调用方法更新信息
        Integer result = teacherService.updateTeac(teacher);

        if(result != null && result == 1) {
            model.addAttribute("msg", "更新成功!");
            // 返回添加教师的页面
            return "admin/editTeacher";
        }

        model.addAttribute("msg", "更新失败!");
        // 把id传过去，才能跳转到/admin/editTeacher接口
        model.addAttribute("teacher", teacher);

        return "admin/editTeacher";
    }

    /**
     * 删除一个教师
     * @param model
     * @param teacherId
     * @return
     */
    @PostMapping("/removeTeacher")
    public String removeTeacher(Model model, @RequestParam("teacherId") String teacherId) {

        Integer result = teacherService.deleteTeac(teacherId);

        // 删除成功
        if(result != null && result == 1) {
            // 顺带把user表的数据删除
            userService.delUser(teacherId);
            // 删除该教师开设的课程
            courseService.delCourseByTec(teacherId);
            model.addAttribute("msg", "删除成功!");
        } else {
            model.addAttribute("msg", "删除失败!");
        }

        return "admin/showTeacher";
    }

    /*----------------课程操作-----------------*/

    /**
     * 获取课程列表
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/course/list")
    public String showCourse(Model model, HttpServletRequest request) {

        Integer page = null;
        if(request.getParameter("page") != null) {
            // 获取链接中给的page
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Map<String, Object>> courseList = null;

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总人数
        int teacherNum = teacherService.getCountTeacher();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        // 页码对象
        PageVO pageVO = new PageVO();
        // 设置总页数
        pageVO.setTotalCount(courseNum);
        if(page == null || page == 0) {
            // 默认显示第一页的数据
            pageVO.setToPageNo(1);
            courseList = courseService.getCourseList(1);
        } else {
            pageVO.setToPageNo(page);
            courseList = courseService.getCourseList(page);
        }

        model.addAttribute("studentNum", studentNum);
        model.addAttribute("teacherNum", teacherNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeNum);
        model.addAttribute("courseList", courseList);
        model.addAttribute("pageVo", pageVO);

        return "admin/showCourse";
    }

    /**
     * 搜索课程
     * @param courseName
     * @param model
     * @return
     */
    @PostMapping("/selectCourse")
    public String selectCourse(String courseName, Model model) {
        if(courseName == null) {
            return null;
        }

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总数
        int teacherNum = teacherService.getCountTeacher();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        List<Map<String, Object>> courseList = null;
        courseList = courseService.findByCourName(courseName);
        model.addAttribute("courseList", courseList);
        // 返回搜索的结果数
        model.addAttribute("studentNum", studentNum);
        model.addAttribute("collegeNum", collegeNum);
        model.addAttribute("courseNum", courseList.size());
        model.addAttribute("teacherNum", teacherNum);
        return "admin/showCourse";
    }

    /**
     * 增加课程页面，提供所有的院系和教师供选择
     * @param model
     * @return
     */
    @GetMapping("/addCourse")
    public String addCourseUI(Model model) {
        List<College> collegeList = null;
        // 获取所有院系
        collegeList = collegeService.getCollegeList();

        // 获取所有教师
        List<Teacher> teacherList = null;
        teacherList = teacherService.getTeacIdAndName();

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总人数
        int teacherNum = teacherService.getCountTeacher();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        model.addAttribute("collegeList", collegeList);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("studentNum", studentNum);
        model.addAttribute("teacherNum", teacherNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeNum);

        return "admin/addCourse";

    }

    /**
     * 获取用户输入的信息，执行插入课程操作
     * @param course
     * @param model
     * @return
     */
    @PostMapping("/addCourse")
    public String addCourse(Course course, Model model) {

        if(course == null) {
            model.addAttribute("msg", "添加失败!");
            // 返回添加课程的页面
            return "admin/addCourse";
        }

        // 判断college_id是否正确
        Integer collegeId = collegeService.getCollegeById(course.getCollegeId());
        if(collegeId == null) {
            model.addAttribute("msg", "院系不存在!");
            // 返回添加课程的页面
            return "admin/addCourse";
        }

        // 查看教师id是否存在
        String teacherId = null;
        teacherId = teacherService.getTeacById(course.getTeacherId());

        // 为空，说明教师id错误
        if(teacherId == null) {
            model.addAttribute("msg", "教师信息错误!");
            // 返回添加课程的页面
            return "admin/addCourse";
        }

        // 判断课程是否重复(同一门课程可以有多个教师教师course_code, course_name, teacher_id联合)
        Integer courseId = null;
        courseId = courseService.getCourseByThree(course);

        // 存在重复的
        if(courseId != null) {
            model.addAttribute("msg", "信息有误!");
            // 返回添加课程的页面
            return "admin/addCourse";
        }

        // 调用方法插入
        int result = courseService.addCourse(course);

        if(result != 1) {
            model.addAttribute("msg", "添加失败!");
            // 返回添加课程的页面
            return "admin/addCourse";
        }

        model.addAttribute("msg", "添加成功!");
        return "admin/addCourse";
    }

    /**
     * 通过excel文件，批量增加课程
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/upload/course")
    public String uploadCourseExcel(HttpServletRequest request) {


        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartHttpServletRequest.getFile("courseFile");
        if(file.isEmpty()) {
            return "redirect:/admin/course/list";
        }

        try {
            InputStream inputStream = file.getInputStream();
            List<List<Object>> list = ExcelUtils.getCourseListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();

            for (int i = 0; i < list.size(); i++) {
                List<Object> courseList = list.get(i);
                logger.warn("courseList = " + courseList);

                Course course = new Course();

                course.setCourseCode(courseList.get(0).toString());
                course.setCourseName(courseList.get(1).toString());
                // 通过教师姓名查教师id
                String teacherId = teacherService.getTeacByName(courseList.get(2).toString());
                // 教师信息错误，直接跳过这条记录
                if(teacherId == null) {
                    continue;
                }
                course.setTeacherId(teacherId);

                // 格式化时间
                Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(courseList.get(3).toString());
                course.setCourseTime(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(date)));

                course.setClassRoom(courseList.get(4).toString());
                course.setCourseWeek(Integer.parseInt(new DecimalFormat("0").format(Double.parseDouble(courseList.get(5).toString()))));

                course.setCourseType(courseList.get(6).toString());
                // 通过院系名称查询院系id
                Integer collegeId = collegeService.getCollegeByName(courseList.get(7).toString());
                // 院系有误，直接跳过这条记录
                if(collegeId == null || collegeId == 0) {
                    continue;
                }
                course.setCollegeId(collegeId);
                course.setScore(Float.parseFloat(new DecimalFormat("0").format(Double.parseDouble(courseList.get(8).toString()))));
                // 默认不开启选课
                course.setIsOn(0);

                logger.error("course = " + course);

                // 判断课程是否重复(同一门课程可以有多个教师教师course_code, course_name, teacher_id联合)
                Integer courseId = null;
                courseId = courseService.getCourseByThree(course);

                // 存在重复的
                if(courseId != null) {
                    // 跳过不添加
                    continue;
                }

                // 执行插入操作
                courseService.addCourse(course);
            }
        } catch (Exception e) {
            return "redirect:/admin/course/list";
        }

        return "redirect:/admin/course/list";
    }

    /**
     * 跳转到修改课程的页面，并携带该课程的信息
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/editCourse")
    public String editCourseUI(Model model, HttpServletRequest request) {

        Integer courseId = null;

        // 获取链接中的参数
        if(request.getParameter("id") != null) {
            courseId = Integer.parseInt(request.getParameter("id"));
        }

        Course course = courseService.getCourInfoById(courseId);

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总人数
        int teacherNum = teacherService.getCountTeacher();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        List<College> collegeList = null;

        // 获取所有院系
        collegeList = collegeService.getCollegeList();

        List<Teacher> teacherList = null;
        // 获取所有教师
        teacherList = teacherService.getTeacIdAndName();

        model.addAttribute("teacherList", teacherList);
        model.addAttribute("collegeList", collegeList);
        model.addAttribute("studentNum", studentNum);
        model.addAttribute("teacherNum", teacherNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeNum);
        model.addAttribute("course", course);

        return "admin/editCourse";
    }

    /**
     * 接收数据，修改课程信息
     * @param model
     * @param course
     * @return
     */
    @PostMapping("/editCourse")
    public String editCourse(Model model, Course course) {

        if(course == null) {
            model.addAttribute("msg", "更新失败!");
            // 返回更新课程的页面
            return "admin/editCourse";
        }

        // 判断college_id是否正确
        Integer collegeId = collegeService.getCollegeById(course.getCollegeId());
        if(collegeId == null) {
            model.addAttribute("msg", "院系不存在!");
            // 返回更新课程的页面
            return "admin/editCourse";
        }

        // 判断teacher_id是否正确
        String teacherId = null;
        teacherId = teacherService.getTeacById(course.getTeacherId());

        // 不存在
        if(teacherId == null) {
            model.addAttribute("msg", "教师信息错误!");
            // 返回更新课程的页面
            return "admin/editCourse";
        }

        // 判断课程是否重复(同一门课程可以有多个教师教师course_code, course_name, teacher_id联合)
        Integer courseId = null;
        courseId = courseService.getCourseByThree(course);

        // 存在重复的
        if(courseId != null && courseId != course.getCourseId()) {
            model.addAttribute("msg", "信息有误!");
            // 返回更新课程的页面
            return "admin/editCourse";
        }

        // 调用方法更新信息
        Integer result = courseService.updateCour(course);

        if(result != null && result == 1) {
            model.addAttribute("msg", "更新成功!");
            // 返回更新课程的页面
            return "admin/editCourse";
        }

        model.addAttribute("msg", "更新失败!");
        // 把id传过去，才能跳转到/admin/editCourse接口
        model.addAttribute("course", course);

        return "admin/editCourse";
    }

    /**
     * 删除一个课程
     * @param model
     * @param courseId
     * @return
     */
    @PostMapping("/removeCourse")
    public String removeCourse(Model model, @RequestParam("courseId") Integer courseId) {

        // 判断该课程是否已经有学生选了，有则无法删除
        Map<String, Integer> map = new HashMap<>();
        map = selectCourseService.isSelect(courseId);

        if(map != null && map.size() == 1) {
            model.addAttribute("msg", "该课程已经有学生选课，不能删除!");
            return "admin/showCourse";
        }

        Integer result = courseService.deleteCour(courseId);

        // 删除成功
        if(result != null && result == 1) {
            model.addAttribute("msg", "删除成功!");
        } else {
            model.addAttribute("msg", "删除失败!");
        }

        return "admin/showCourse";
    }

    /**
     * 课程是否开启选课
     * @param isOn
     * @param courseId
     */
    @PostMapping("/editStatus")
    public String editStatus(@RequestParam("isOn") Integer isOn,
                           @RequestParam("courseId") Integer courseId) {

        Integer result = courseService.changeIsOn(isOn, courseId);
        logger.info("result = {}" + result);

        // 开启成功，如果是必修课，则自动添加到该院系所有学生的已选课列表
        if(isOn == 1 && result == 1) {
            Course course = courseService.getCourInfoById(courseId);

            if("必修课".equals(course.getCourseType())) {
                // 获取该院系所有学生id
                List<String> stuIdList = studentService.getCollegeStu(course.getCollegeId());

                // 有学生，则执行插入
                if(stuIdList != null & stuIdList.size() > 0) {
                    selectCourseService.addRecord(stuIdList, courseId);
                }
            }
        }

        return "admin/showCourse";

    }

    /*----------------院系操作-----------------*/
    /**
     * 获取院系列表
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/college/list")
    public String showCollege(Model model, HttpServletRequest request) {

        Integer page = null;
        if(request.getParameter("page") != null) {
            // 获取链接中给的page
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Map<String, Object>> collegeList = null;

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总人数
        int teacherNum = teacherService.getCountTeacher();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        // 页码对象
        PageVO pageVO = new PageVO();
        // 设置总页数
        pageVO.setTotalCount(collegeNum);
        if(page == null || page == 0) {
            // 默认显示第一页的数据
            pageVO.setToPageNo(1);
            collegeList = collegeService.getCollegeListByPage(1);
        } else {
            pageVO.setToPageNo(page);
            collegeList = collegeService.getCollegeListByPage(page);
        }

        model.addAttribute("studentNum", studentNum);
        model.addAttribute("teacherNum", teacherNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeNum);
        model.addAttribute("collegeList", collegeList);
        model.addAttribute("pageVo", pageVO);

        return "admin/showCollege";
    }

    /**
     * 搜索院系
     * @param collegeName
     * @param model
     * @return
     */
    @PostMapping("/selectCollege")
    public String selectCollege(String collegeName, Model model) {
        if(collegeName == null) {
            return null;
        }

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总数
        int teacherNum = teacherService.getCountTeacher();
        // 获取院系总数
        int courseNum = courseService.getCountCourse();

        List<Map<String, Object>> collegeList = null;
        collegeList = collegeService.findByCollName(collegeName);
        model.addAttribute("collegeList", collegeList);
        // 返回搜索的结果数
        model.addAttribute("studentNum", studentNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeList.size());
        model.addAttribute("teacherNum", teacherNum);

        return "admin/showCollege";
    }

    /**
     * 增加院系
     * @param collegeName
     */
    @PostMapping("/addCollege")
    @ResponseBody
    public Integer addCollege(@RequestParam("collegeName") String collegeName) {

        Integer code = 0;

        // 查看院系名称是否重复
        String cn = collegeService.getCollegeName(collegeName);
        if(cn != null) {
            code = 1;
            return code;
        }

        Integer result = collegeService.addCollege(collegeName);

        if(result == null || result != 1) {
            code = 2;
            return code;
        }

        logger.info("result = {}" + result);
        return code;
    }

    /**
     * 修改院系
     * @param college
     * @return
     */
    @PostMapping("/updateCollege")
    @ResponseBody
    public Integer updateCollege(College college) {

        Integer code = 0;

        // 查看院系名称是否重复
        String cn = collegeService.getCollegeName(college.getCollegeName());
        if(cn != null) {
            code = 1;
            return code;
        }

        Integer result = collegeService.updateCollege(college);

        if(result == null || result != 1) {
            code = 2;
            return code;
        }

        logger.info("result = {}" + result);
        return code;
    }

    /*----------------其他操作-----------------*/

    /**
     * 返回重置密码的界面
     * @return
     */
    @GetMapping("/userPasswordRest")
    public String userPasswordRestUI(Model model) {

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总人数
        int teacherNum = teacherService.getCountTeacher();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        model.addAttribute("studentNum", studentNum);
        model.addAttribute("teacherNum", teacherNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeNum);

        return "admin/userPasswordRest";
    }

    /**
     * 接收数据重置用户密码
     * @param user
     * @return
     */
    @PostMapping("/userPasswordRest")
    @ResponseBody
    public Integer userPasswordRest(User user) {

        Integer code = 0;

        User u = userService.findByName(user.getUserName());

        // 有该用户
        if(u != null) {
            // 不能修改管理员的密码
            if(u.getRoleId() == 1) {
                code = 1;
                return code;
            }
            // 设置盐
            String salt = DataUtils.getSalt();
            user.setUserSalt(salt);
            // 获取输入的密码
            String password = user.getUserPassword();
            // 设置加盐后的密码
            user.setUserPassword(DataUtils.getMD5Str(password, salt));

            Integer result = userService.updateUser(user);

            if(result == null || result != 1) {
                code = 2;
                return code;
            }
        } else {
            // 没有该用户
            code = 3;
            return code;
        }
        return code;
    }

    /**
     * 修改个人密码页面
     * @return
     */
    @GetMapping("/passwordRest")
    public String passwordRestUI(Model model) {

        // 获取学生总人数
        int studentNum = studentService.getCountStudent();
        // 获取教师总人数
        int teacherNum = teacherService.getCountTeacher();
        // 获取课程总数
        int courseNum = courseService.getCountCourse();
        // 获取院系总数
        int collegeNum = collegeService.getCountCollege();

        model.addAttribute("studentNum", studentNum);
        model.addAttribute("teacherNum", teacherNum);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("collegeNum", collegeNum);

        return "admin/passwordRest";
    }

}
