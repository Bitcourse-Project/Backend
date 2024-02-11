package com.finance.bitcourse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthController {

    @GetMapping("/health")
    public String health() {

        String OK = "OK";

        log.info("{}", OK);
        return OK;
    }
}
