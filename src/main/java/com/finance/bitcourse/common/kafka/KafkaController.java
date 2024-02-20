package com.finance.bitcourse.common.kafka;

import com.finance.bitcourse.common.kafka.dto.ChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaProducerService producer;

    @MessageMapping("/pub")
    // kafka/pub
    public String sendMessage(@RequestBody ChartDto chartDto) {
        producer.sendMessage("one-hour-chart", chartDto);
        return "ok";
    }
}