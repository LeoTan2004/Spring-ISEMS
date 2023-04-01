package com.csb;

import com.csb.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringIsemsApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(userMapper.getByUsername("13575104321"));
    }

}
