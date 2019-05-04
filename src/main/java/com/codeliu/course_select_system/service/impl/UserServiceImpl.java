package com.codeliu.course_select_system.service.impl;

import com.codeliu.course_select_system.dao.UserMapper;
import com.codeliu.course_select_system.entity.User;
import com.codeliu.course_select_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName liuxiaoping
 * @Description TODO
 * @Author liu
 * @Date 2019/2/1 9:55
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String name) {


        // 参数错误直接返回
        if(name == null || "".equals(name)) {
            return null;
        }

        // 调用sql取得结果
        User user = userMapper.findByName(name);

        return user;
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int delUser(String userName) {
        return userMapper.delUser(userName);
    }

    @Override
    public Integer updateUser(User user) {

        Integer result = null;
        result = userMapper.updateUser(user);
        return result;
    }
}
