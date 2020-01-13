package com.fh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fh.dao")
public class SpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot.class,args);
    }
}
