package com.csb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.csb.mapper")
public class SpringIsemsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIsemsApplication.class, args);
    }

}
