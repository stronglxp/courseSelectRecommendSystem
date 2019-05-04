package com.codeliu.course_select_system.dao;

import com.codeliu.course_select_system.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(UserMapperTest.class);

    @Test
    public void testFindByName() {
        User user = userMapper.findByName("B15041212");
        logger.info("user = {}" + user);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUserName("B15041210");
        user.setUserSalt("1");
        user.setUserPassword("2");
        user.setRoleId(3);

        int num = userMapper.addUser(user);

        logger.info("num = {}" + num);
    }
}
