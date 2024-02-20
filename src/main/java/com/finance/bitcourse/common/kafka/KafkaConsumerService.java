package com.finance.bitcourse.common.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.bitcourse.common.kafka.dto.ChartDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final SimpMessagingTemplate template;

    @KafkaListener(topics = "one-hour-chart", groupId = "my-group-id")
    public void listenOneHourChart(ChartDto chartDto) throws JsonProcessingException {

        System.out.println(chartDto);

        Map<String, String> msg = new HashMap<>();
        msg.put("trend_change_rate", chartDto.getTrendChangeRate());
        msg.put("average_change_period", chartDto.getAverageChangePeriod());
        msg.put("average_change_rate", chartDto.getAverageChangeRate());

        ObjectMapper mapper = new ObjectMapper();

        template.convertAndSend("/topic/my-group-id", mapper.writeValueAsString(msg));

    }
}
