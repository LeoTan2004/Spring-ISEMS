package com.csb;

import com.csb.mapper.UserMapper;
import com.csb.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringIsemsApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        userMapper.insert(new User(){{
            this.setUsername("1332123333");
        }});

    }

}
