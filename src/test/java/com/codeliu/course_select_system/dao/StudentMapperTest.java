package com.codeliu.course_select_system.dao;

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
public class StudentMapperTest {

    private Logger logger = LoggerFactory.getLogger(UserMapperTest.class);

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testUpdateStu() {
        Double d = 6.0;
        Integer a = Integer.parseInt(new java.text.DecimalFormat("0").format(d));
        logger.warn("a = " + a);
    }
}
