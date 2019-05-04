package com.codeliu.course_select_system.controller;

import com.codeliu.course_select_system.entity.Course;
import com.codeliu.course_select_system.entity.PageVO;
import com.codeliu.course_select_system.entity.Ranks;
import com.codeliu.course_select_system.entity.SelectCourse;
import com.codeliu.course_select_system.service.CourseService;
import com.codeliu.course_select_system.service.RanksService;
import com.codeliu.course_select_system.service.SelectCourseService;
import com.codeliu.course_select_system.util.JedisAdapter;
import com.codeliu.course_select_system.util.RecommendUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 学生控制器
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SelectCourseService selectCourseService;

    @Autowired
    private JedisAdapter jedisAdapter;

    @Autowired
    private RanksService ranksService;

    /**
     * 显示学生可选的选修课程列表
     * 必修课直接进入已选课程
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/course/list")
    public String courseList(Model model, HttpServletRequest request) {

        // 获取当前登录学生的编号
        Subject subject = SecurityUtils.getSubject();
        String studentId = (String) subject.getPrincipal();

        Integer page = null;
        if(request.getParameter("page") != null) {
            // 获取链接中给的page
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Map<String, Object>> courseList = null;

        // 获取可选课程数
        Integer courseNum = courseService.getCountOpenCourse(studentId);
        // 获取已选课程数
        Integer selectCourseNum = selectCourseService.getSelectCourseNum(studentId);
        // 获取已修课程数
        Integer overCourseNum = selectCourseService.getOverCourseNum(studentId);
        // 获取推荐课程数
        List<Map<String, Object>> recomCourseList = new ArrayList<>();
        recomCourseList = selectCourseService.getTopTenEvaCourse();

        List<Integer> courseIdList = new ArrayList<>();
        courseIdList = selectCourseService.courseIdList(studentId);

        Iterator<Map<String, Object>> iterator = recomCourseList.iterator();

        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            if(courseIdList.contains(map.get("course_id"))) {
                iterator.remove();
            }
        }

        // 页码对象
        PageVO pageVO = new PageVO();
        // 设置总页数
        pageVO.setTotalCount(courseNum);
        if(page == null || page == 0) {
            // 默认显示第一页的数据
            pageVO.setToPageNo(1);
            courseList = courseService.getOpenCourseList(studentId,0, 5);
        } else {
            pageVO.setToPageNo(page);
            courseList = courseService.getOpenCourseList(studentId, (page - 1) * 5, 5);
        }

        model.addAttribute("courseNum", courseNum);
        model.addAttribute("overCourseNum", overCourseNum);
        model.addAttribute("selectCourseNum", selectCourseNum);
        model.addAttribute("courseList", courseList);
        model.addAttribute("pageVo", pageVO);
        model.addAttribute("recommendCourseNum", recomCourseList.size());

        return "student/showCourse";
    }

    /**
     * 搜索可选课程
     * @param courseName
     * @param model
     * @return
     */
    @PostMapping("/selectOpenCourse")
    public String selectOpenCourse(String courseName, Model model) {

        // 获取当前登录学生的编号
        Subject subject = SecurityUtils.getSubject();
        String studentId = (String) subject.getPrincipal();

        if(courseName == null) {
            return null;
        }

        List<Map<String, Object>> courseList = null;
        courseList = courseService.selectOpenCourseList(courseName, studentId);

        // 获取已选课程数
        Integer selectCourseNum = selectCourseService.getSelectCourseNum(studentId);
        // 获取已修课程数
        Integer overCourseNum = selectCourseService.getOverCourseNum(studentId);
        // 获取推荐课程数
        List<Map<String, Object>> recomCourseList = new ArrayList<>();
        recomCourseList = selectCourseService.getTopTenEvaCourse();

        List<Integer> courseIdList = new ArrayList<>();
        courseIdList = selectCourseService.courseIdList(studentId);

        Iterator<Map<String, Object>> iterator = recomCourseList.iterator();

        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            if(courseIdList.contains(map.get("course_id"))) {
                iterator.remove();
            }
        }

        model.addAttribute("courseList", courseList);
        model.addAttribute("overCourseNum", overCourseNum);
        model.addAttribute("courseNum", courseList.size());
        model.addAttribute("selectCourseNum", selectCourseNum);
        model.addAttribute("recommendCourseNum", recomCourseList.size());
        return "student/showCourse";
    }

    /**
     * 选课操作
     * @param courseId
     * @return
     */
    @PostMapping("/select/{courseId}")
    @ResponseBody
    public Integer stuSelectCourse(@PathVariable Integer courseId) {

        Integer code = 0;

        Subject subject = SecurityUtils.getSubject();
        String studentId = (String) subject.getPrincipal();

        // 查看courseId是否正确
        Integer c_id = courseService.getCourById(courseId);
        if(c_id == null) {
            code = 1;
            return code;
        }

        // 执行插入
        Integer result = selectCourseService.addOneRecord(courseId, studentId);

        if(result != 1) {
            code = -1;
            return code;
        }

        // 插入ranks表
        Ranks ranks = new Ranks();
        ranks.setStudentId(studentId);
        ranks.setCourseId(courseId);
        ranksService.addRank(ranks);

        return code;
    }

    /**
     * 已选课程列表
     * @param model
     * @return
     */
    @GetMapping("/select/course/list")
    public String selectCourseList(Model model, HttpServletRequest request) {
        // 获取当前登录学生的编号
        Subject subject = SecurityUtils.getSubject();
        String studentId = (String) subject.getPrincipal();

        Integer page = null;
        if(request.getParameter("page") != null) {
            // 获取链接中给的page
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Map<String, Object>> courseList = null;

        // 获取可选课程数
        Integer courseNum = courseService.getCountOpenCourse(studentId);
        // 获取已选课程数
        Integer selectCourseNum = selectCourseService.getSelectCourseNum(studentId);
        // 获取已修课程数
        Integer overCourseNum = selectCourseService.getOverCourseNum(studentId);
        // 获取推荐课程数
        List<Map<String, Object>> recomCourseList = new ArrayList<>();
        recomCourseList = selectCourseService.getTopTenEvaCourse();

        List<Integer> courseIdList = new ArrayList<>();
        courseIdList = selectCourseService.courseIdList(studentId);

        Iterator<Map<String, Object>> iterator = recomCourseList.iterator();

        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            if(courseIdList.contains(map.get("course_id"))) {
                iterator.remove();
            }
        }

        // 页码对象
        PageVO pageVO = new PageVO();
        // 设置总页数
        pageVO.setTotalCount(selectCourseNum);
        if(page == null || page == 0) {
            // 默认显示第一页的数据
            pageVO.setToPageNo(1);
            courseList = selectCourseService.getSelectCourseList(studentId,0, 5);
        } else {
            pageVO.setToPageNo(page);
            courseList = selectCourseService.getSelectCourseList(studentId, (page - 1) * 5, 5);
        }

        model.addAttribute("courseNum", courseNum);
        model.addAttribute("overCourseNum", overCourseNum);
        model.addAttribute("selectCourseNum", selectCourseNum);
        model.addAttribute("courseList", courseList);
        model.addAttribute("pageVo", pageVO);
        model.addAttribute("recommendCourseNum", recomCourseList.size());

        return "student/selectCourse";
    }

    /**
     * 搜索已选课程
     * @param courseName
     * @param model
     * @return
     */
    @PostMapping("/selectCourse")
    public String selectCourse(String courseName, Model model) {

        // 获取当前登录学生的编号
        Subject subject = SecurityUtils.getSubject();
        String studentId = (String) subject.getPrincipal();

        if(courseName == null) {
            return null;
        }

        List<Map<String, Object>> courseList = null;
        courseList = selectCourseService.selectCourseList(studentId, courseName);

        // 获取可选课程数
        Integer courseNum = courseService.getCountOpenCourse(studentId);
        // 获取已修课程数
        Integer overCourseNum = selectCourseService.getOverCourseNum(studentId);
        // 获取推荐课程数
        List<Map<String, Object>> recomCourseList = new ArrayList<>();
        recomCourseList = selectCourseService.getTopTenEvaCourse();

        List<Integer> courseIdList = new ArrayList<>();
        courseIdList = selectCourseService.courseIdList(studentId);

        Iterator<Map<String, Object>> iterator = recomCourseList.iterator();

        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            if(courseIdList.contains(map.get("course_id"))) {
                iterator.remove();
            }
        }

        model.addAttribute("courseList", courseList);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("overCourseNum", overCourseNum);
        model.addAttribute("selectCourseNum", courseList.size());
        model.addAttribute("recommendCourseNum", recomCourseList.size());
        return "student/selectCourse";
    }

    /**
     * 退课操作
     * @param courseId
     * @return
     */
    @PostMapping("/out/{courseId}")
    @ResponseBody
    public Integer outCourse(@PathVariable Integer courseId) {
        Integer code = 0;

        Subject subject = SecurityUtils.getSubject();
        String studentId = (String) subject.getPrincipal();

        // 查看courseId是否正确
        Integer c_id = courseService.getCourById(courseId);
        if(c_id == null) {
            code = 1;
            return code;
        }

        Course course = courseService.getCourInfoById(courseId);
        // 必修课不能退
        if("必修课".equals(course.getCourseType())) {
            code = 2;
            return code;
        }

        // 执行插入
        Integer result = selectCourseService.outCourse(studentId, courseId);

        if(result != 1) {
            code = -1;
            return code;
        }

        // 删除ranks
        Ranks ranks = new Ranks();
        ranks.setStudentId(studentId);
        ranks.setCourseId(courseId);
        ranksService.deleteRank(ranks);

        return code;
    }

    /**
     * 已修课程列表
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/over/course/list")
    public String overCourseList(Model model, HttpServletRequest request) {
        // 获取当前登录学生的编号
        Subject subject = SecurityUtils.getSubject();
        String studentId = (String) subject.getPrincipal();

        Integer page = null;
        if(request.getParameter("page") != null) {
            // 获取链接中给的page
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Map<String, Object>> courseList = null;

        // 获取可选课程数
        Integer courseNum = courseService.getCountOpenCourse(studentId);
        // 获取已选课程数
        Integer selectCourseNum = selectCourseService.getSelectCourseNum(studentId);
        // 获取已修课程数
        Integer overCourseNum = selectCourseService.getOverCourseNum(studentId);
        // 获取推荐课程数
        List<Map<String, Object>> recomCourseList = new ArrayList<>();
        recomCourseList = selectCourseService.getTopTenEvaCourse();

        List<Integer> courseIdList = new ArrayList<>();
        courseIdList = selectCourseService.courseIdList(studentId);

        Iterator<Map<String, Object>> iterator = recomCourseList.iterator();

        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            if(courseIdList.contains(map.get("course_id"))) {
                iterator.remove();
            }
        }

        // 页码对象
        PageVO pageVO = new PageVO();
        // 设置总页数
        pageVO.setTotalCount(overCourseNum);
        if(page == null || page == 0) {
            // 默认显示第一页的数据
            pageVO.setToPageNo(1);
            courseList = selectCourseService.getOverCourseList(studentId,0, 5);
        } else {
            pageVO.setToPageNo(page);
            courseList = selectCourseService.getOverCourseList(studentId, (page - 1) * 5, 5);
        }

        model.addAttribute("courseNum", courseNum);
        model.addAttribute("overCourseNum", overCourseNum);
        model.addAttribute("selectCourseNum", selectCourseNum);
        model.addAttribute("courseList", courseList);
        model.addAttribute("pageVo", pageVO);
        model.addAttribute("recommendCourseNum", recomCourseList.size());

        return "student/overCourse";
    }

    /**
     * 搜索已修课程
     * @param courseName
     * @param model
     * @return
     */
    @PostMapping("/selectOverCourse")
    public String selectOverCourse(String courseName, Model model) {

        // 获取当前登录学生的编号
        Subject subject = SecurityUtils.getSubject();
        String studentId = (String) subject.getPrincipal();

        if(courseName == null) {
            return null;
        }

        List<Map<String, Object>> courseList = null;
        courseList = selectCourseService.selectOverCourseList(studentId, courseName);

        // 获取可选课程数
        Integer courseNum = courseService.getCountOpenCourse(studentId);
        // 获取已选课程数
        Integer selectCourseNum = selectCourseService.getSelectCourseNum(studentId);
        // 获取推荐课程数
        List<Map<String, Object>> recomCourseList = new ArrayList<>();
        recomCourseList = selectCourseService.getTopTenEvaCourse();

        List<Integer> courseIdList = new ArrayList<>();
        courseIdList = selectCourseService.courseIdList(studentId);

        Iterator<Map<String, Object>> iterator = recomCourseList.iterator();

        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            if(courseIdList.contains(map.get("course_id"))) {
                iterator.remove();
            }
        }

        model.addAttribute("courseList", courseList);
        model.addAttribute("courseNum", courseNum);
        model.addAttribute("overCourseNum", courseList.size());
        model.addAttribute("selectCourseNum", selectCourseNum);
        model.addAttribute("recommendCourseNum", recomCourseList.size());
        return "student/overCourse";
    }

    /**
     * 获取重置密码页面
     * @param model
     * @return
     */
    @GetMapping("/passwordRest")
    public String passwordRestUI(Model model) {

        // 获取当前登录学生的编号
        Subject subject = SecurityUtils.getSubject();
        String studentId = (String) subject.getPrincipal();

        // 获取可选课程数
        Integer courseNum = courseService.getCountOpenCourse(studentId);
        // 获取已选课程数
        Integer selectCourseNum = selectCourseService.getSelectCourseNum(studentId);
        // 获取已修课程数
        Integer overCourseNum = selectCourseService.getOverCourseNum(studentId);
        // 获取推荐课程数

        model.addAttribute("courseNum", courseNum);
        model.addAttribute("overCourseNum", overCourseNum);
        model.addAttribute("selectCourseNum", selectCourseNum);

        return "student/passwordRest";
    }

    /**
     * 综合推荐课程列表
     * @param model
     * @return
     */
    @GetMapping("/multiple/course/list")
    public String recommendMultipleCourse(Model model) {

        // 获取当前登录学生的编号
        Subject subject = SecurityUtils.getSubject();
        String studentId = (String) subject.getPrincipal();

        // 获取可选课程数
        Integer courseNum = courseService.getCountOpenCourse(studentId);
        // 获取已选课程数
        Integer selectCourseNum = selectCourseService.getSelectCourseNum(studentId);
        // 获取已修课程数
        Integer overCourseNum = selectCourseService.getOverCourseNum(studentId);

        // 获取评分数据
        List<Map<String, Object>> rankList = ranksService.getRankList();

        Set<String> courseSet = new HashSet<>();
        List<Map<String, Object>> courseList = new ArrayList<>();

        // 调用方法生成dat文件
        boolean flag = RecommendUtils.generatDatFile(studentId, rankList);

        // 获取该学生已选修课程id列表
        List<Integer> courseIdList = new ArrayList<>();
        courseIdList = selectCourseService.courseIdList(studentId);

        // 生成文件成功
        if(flag) {
            // 调用方法生成推荐数据
            boolean result = RecommendUtils.BaseUserRecommender(studentId);
            //boolean result = RecommendUtils.BaseItemRecommender(studentId);
            if(result) {
                // 按推荐系数递减获取所有课程
                courseSet = jedisAdapter.zrevrange(studentId, 0, -1);
                Iterator<String> iterator = courseSet.iterator();
                while(iterator.hasNext()) {
                    Integer courseId = Integer.parseInt(iterator.next());
                    if(courseIdList.contains(courseId)) {
                        continue;
                    }
                    // 查询课程信息
                    Map<String, Object> map = courseService.getCourseById(courseId);
                    courseList.add(map);
                    // 有10门推荐课程，就返回
                    if(courseList.size() >= 10) {
                        break;
                    }
                }
            }
        }

        model.addAttribute("courseNum", courseNum);
        model.addAttribute("overCourseNum", overCourseNum);
        model.addAttribute("selectCourseNum", selectCourseNum);
        model.addAttribute("pick", 1);
        model.addAttribute("courseList", courseList);
        model.addAttribute("recommendCourseNum", courseList.size());

        return "student/recommendCourse";
    }

    /**
     * 按评分推荐课程列表
     * 获取评分最高的10门课程
     * @param model
     * @return
     */
    @GetMapping("/evaluate/course/list")
    public String recommendEvaluateCourse(Model model) {

        // 获取当前登录学生的编号
        Subject subject = SecurityUtils.getSubject();
        String studentId = (String) subject.getPrincipal();

        // 获取可选课程数
        Integer courseNum = courseService.getCountOpenCourse(studentId);
        // 获取已选课程数
        Integer selectCourseNum = selectCourseService.getSelectCourseNum(studentId);
        // 获取已修课程数
        Integer overCourseNum = selectCourseService.getOverCourseNum(studentId);

        List<Map<String, Object>> courseList = new ArrayList<>();
        courseList = selectCourseService.getTopTenEvaCourse();

        List<Integer> courseIdList = new ArrayList<>();
        courseIdList = selectCourseService.courseIdList(studentId);

        Iterator<Map<String, Object>> iterator = courseList.iterator();

        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            if(courseIdList.contains(map.get("course_id"))) {
                iterator.remove();
            }
        }

        model.addAttribute("courseNum", courseNum);
        model.addAttribute("overCourseNum", overCourseNum);
        model.addAttribute("selectCourseNum", selectCourseNum);
        model.addAttribute("pick", 2);
        model.addAttribute("courseList", courseList);
        model.addAttribute("recommendCourseNum", courseList.size());

        return "student/recommendCourse";
    }

    /**
     * 按总选修人数推荐课程列表
     * @param model
     * @return
     */
    @GetMapping("/num/course/list")
    public String recommendNumCourse(Model model) {

        // 获取当前登录学生的编号
        Subject subject = SecurityUtils.getSubject();
        String studentId = (String) subject.getPrincipal();

        // 获取可选课程数
        Integer courseNum = courseService.getCountOpenCourse(studentId);
        // 获取已选课程数
        Integer selectCourseNum = selectCourseService.getSelectCourseNum(studentId);
        // 获取已修课程数
        Integer overCourseNum = selectCourseService.getOverCourseNum(studentId);

        List<Map<String, Object>> courseList = new ArrayList<>();
        courseList = selectCourseService.getTopTenNumCourse();

        List<Integer> courseIdList = new ArrayList<>();
        courseIdList = selectCourseService.courseIdList(studentId);

        Iterator<Map<String, Object>> iterator = courseList.iterator();

        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            if(courseIdList.contains(map.get("course_id"))) {
                iterator.remove();
            }
        }

        model.addAttribute("courseNum", courseNum);
        model.addAttribute("overCourseNum", overCourseNum);
        model.addAttribute("selectCourseNum", selectCourseNum);
        model.addAttribute("pick", 3);
        model.addAttribute("courseList", courseList);
        model.addAttribute("recommendCourseNum", courseList.size());

        return "student/recommendCourse";
    }

    /**
     * 给某门已修课程评分
     * @param courseId
     * @return
     */
    @PostMapping("/evaluation/{courseId}")
    @ResponseBody
    public Integer addEvaluation(@PathVariable("courseId") Integer courseId,
                                 Float evaluation) {

        Integer code = 0;

        Subject subject = SecurityUtils.getSubject();
        String studentId = (String) subject.getPrincipal();

        // 查看courseId是否正确
        Integer c_id = courseService.getCourById(courseId);
        if(c_id == null) {
            code = 1;
            return code;
        }

        SelectCourse selectCourse = new SelectCourse();
        selectCourse.setCourseId(courseId);
        selectCourse.setEvaluation(evaluation);
        selectCourse.setStudentId(studentId);

        // 执行
        Integer result = selectCourseService.addEvaluation(selectCourse);

        if(result != 1) {
            code = -1;
            return code;
        }

        // 更新ranks
        Ranks ranks = new Ranks();
        ranks.setStudentId(studentId);
        ranks.setCourseId(courseId);
        ranks.setRank(evaluation);
        ranksService.updateRank(ranks);

        return code;
    }
}
