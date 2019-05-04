package com.codeliu.course_select_system.controller;

import com.codeliu.course_select_system.entity.User;
import com.codeliu.course_select_system.service.UserService;
import com.codeliu.course_select_system.util.DataUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 修改当前账户的密码
 */
@Controller
@RequestMapping("/user")
public class ResetPassword {

    @Autowired
    private UserService userService;

    /**
     * 修改本账户密码
     * @param oldPassword
     * @param userPassword
     * @return
     */
    @PostMapping("/passwordRest")
    @ResponseBody
    public Integer passwordRest(@RequestParam("oldPassword") String oldPassword,
                                @RequestParam("userPassword") String userPassword) {

        Integer code = 0;

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        // 检查输入的旧密码是否正确
        User user1 = userService.findByName(username);

        // 没有当前用户，出错
        if(user1 == null) {
            code = 1;
            return code;
        }

        // 输入的旧密码不正确
        if(!DataUtils.getMD5Str(oldPassword, user1.getUserSalt()).equals(user1.getUserPassword())) {
            code = 2;
            return code;
        }

        User user2 = new User();

        String salt = DataUtils.getSalt();
        user2.setUserSalt(salt);
        user2.setUserName(username);
        user2.setUserPassword(DataUtils.getMD5Str(userPassword, salt));

        // 修改密码
        Integer result = userService.updateUser(user2);

        if(result == null || result != 1) {
            code = 3;
            return code;
        }

        return code;
    }
}
