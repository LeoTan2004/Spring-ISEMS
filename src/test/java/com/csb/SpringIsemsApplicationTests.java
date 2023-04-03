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
        System.out.println(userMapper.getByUsername("13575104321"));
        User user = new User(1L, "nihao", "woshinibaba", "lee", "wuhuqifei");
        userMapper.insert(user);
        User nihao = userMapper.getByUsername("nihao");
        System.out.println(nihao);
        user.setDescription("huhahuha");
        userMapper.updateById(user);
        System.out.println(userMapper.getByUsername("nihao"));

    }

}
