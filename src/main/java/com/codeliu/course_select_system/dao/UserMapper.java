package com.codeliu.course_select_system.dao;

import com.codeliu.course_select_system.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author liuxiaoping
 * @Description User  dao
 * @Date 9:10 2019/2/1
 * @Param
 * @return
 **/
@Mapper
public interface UserMapper {

    /**
     * @Author liuxiaoping
     * @Description 调用sql查询用户
     * @Date 12:01 2019/2/1
     * @Param [username]
     * @return User
     **/
    User findByName(String username);

    /**
     * @Author liuxiaoping
     * @Description 增加一个学生
     * @Date 15:10 2019/2/16
     * @Param [user]
     * @return int
     **/
    int addUser(User user);

    /**
     * 删除一个用户
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