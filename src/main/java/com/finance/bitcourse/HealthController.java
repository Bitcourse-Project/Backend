package com.finance.bitcourse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@EnableKafka
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
