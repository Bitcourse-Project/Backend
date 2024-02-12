package com.finance.bitcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BitcourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitcourseApplication.class, args);
    }

}
