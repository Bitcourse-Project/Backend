package com.finance.bitcourse.common.kafka;

import com.finance.bitcourse.common.kafka.dto.ChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, ChartDto> kafkaTemplate;

    public void sendMessage(String topic, ChartDto chartDto) {
        kafkaTemplate.send(topic, chartDto);
    }
}