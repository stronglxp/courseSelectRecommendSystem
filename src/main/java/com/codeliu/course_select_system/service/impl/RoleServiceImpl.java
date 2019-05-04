package com.codeliu.course_select_system.service.impl;

import com.codeliu.course_select_system.dao.RoleMapper;
import com.codeliu.course_select_system.entity.Role;
import com.codeliu.course_select_system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName liuxiaoping
 * @Description TODO
 * @Author liu
 * @Date 2019/2/1 9:53
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role findById(Integer roleId) {

        if(roleId == null) {
            return null;
        }

        Role role = roleMapper.findById(roleId);

        return role;
    }
}
