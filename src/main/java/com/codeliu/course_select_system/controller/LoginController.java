package com.codeliu.course_select_system.controller;

import com.codeliu.course_select_system.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName liuxiaoping
 * @Description TODO
 * @Author liu
 * @Date 2019/2/3 20:59
 * @Version 1.0
 */
@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * @Author liuxiaoping
     * @Description 跳转到login.html页面
     * @Date 21:03 2019/2/5
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping({"/", "/login"})
    public String loginUI() {
        return "login";
    }

    /**
     * @Author liuxiaoping
     * @Description 处理登陆
     * @Date 21:47 2019/2/5
     * @Param [model]
     * @return java.lang.String
     **/
    @PostMapping("/login")
    public String login(User user, Model model) {
        // 获取用户输入的用户名和密码
        String username = user.getUserName();
        String password = user.getUserPassword();

        // shiro实现登陆
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();

        try {
            // 如果获取不到用户名，则登陆失败，会抛出异常
            subject.login(token);
            // 管理员
            if(subject.hasRole("admin")) {
                return "redirect:/admin/student/list";
            } else if(subject.hasRole("teacher")) {
                // 老师
                return "redirect:/teacher/open/course/list";
            } else if(subject.hasRole("student")) {
                // 学生
                return "redirect:/student/course/list";
            }
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名不存在!");
        } catch (Exception e) {
            model.addAttribute("msg", "密码错误!");
        }

        // 返回登录界面
        return "login";
    }

    /**
     * @Author liuxiaoping
     * @Description 退出登录
     * @Date 11:01 2019/2/16
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}
