package com.codeliu.course_select_system.dao;

import com.codeliu.course_select_system.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author liuxiaoping
 * @Description Role dao
 * @Date 9:09 2019/2/1
 * @Param
 * @return
 **/
@Mapper
public interface RoleMapper {

    /**
     * @Author liuxiaoping
     * @Description 通过id查找role
     * @Date 14:43 2019/2/1
     * @Param [roleId]
     * @return Role
     **/
    Role findById(Integer roleId);
}