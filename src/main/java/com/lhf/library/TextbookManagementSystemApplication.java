package com.lhf.library;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lhf.library.mapper")
public class TextbookManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextbookManagementSystemApplication.class, args);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
