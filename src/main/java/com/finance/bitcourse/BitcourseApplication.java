package com.finance.bitcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableKafka
public class BitcourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitcourseApplication.class, args);
    }

}
