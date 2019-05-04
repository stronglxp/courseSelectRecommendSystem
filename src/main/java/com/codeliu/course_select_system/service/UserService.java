package com.codeliu.course_select_system.service;

import com.codeliu.course_select_system.entity.User;

/**
 * @InterfaceName liuxiaoping
 * @Description TODO
 * @Author liu
 * @Date 2019/2/1 9:19
 * @Version 1.0
 */
public interface UserService {

    /**
     * @Author liuxiaoping
     * @Description 根据用户名查找用户
     * @Date 14:52 2019/2/1
     * @Param [name]
     * @return User
     **/
    User findByName(String name);

    /**
     * @Author liuxiaoping
     * @Description 添加用户
     * @Date 16:55 2019/2/16
     * @Param [user]
     * @return int
     **/
    int addUser(User user);

    /**
     * 删除用户
     * @param userName
     * @return
     */
    int delUser(String userName);

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    Integer updateUser(User user);
}
