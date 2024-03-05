package com.finance.bitcourse.common.kafka;

import com.finance.bitcourse.coin.entity.Candle;
import com.finance.bitcourse.coin.repository.*;
import com.finance.bitcourse.common.kafka.dto.ChartDto;
import com.finance.bitcourse.common.kafka.dto.ChartResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final SimpMessagingTemplate template;
    private final CandleRepository candleRepository;
    private final CommonUpDivergenceRepository commonUpDivergenceRepository;
    private final CommonDownDivergenceRepository commonDownDivergenceRepository;
    private final HiddenUpDivergenceRepository hiddenUpDivergenceRepository;
    private final HiddenDownDivergenceRepository hiddenDownDivergenceRepository;

    @KafkaListener(topics = "one-hour-chart", groupId = "my-group-id")
    public void listenOneHourChart(ChartDto chartDto) {
        saveAndSendChart(chartDto, "oneHourChart", "one");
    }

    @KafkaListener(topics = "four-hour-chart", groupId = "my-group-id")
    public void listenFourHourChart(ChartDto chartDto) {
        saveAndSendChart(chartDto, "fourHourChart", "four");
    }

    private void saveAndSendChart(ChartDto chartDto, String topic, String candleType) {
        Candle candle = existCandle(chartDto, candleType);

        switch (chartDto.getType()) {
            case 0:
                commonUpDivergenceRepository.save(chartDto.toCUD(candle));
                break;
            case 1:
                commonDownDivergenceRepository.save(chartDto.toCDD(candle));
                break;
            case 2:
                hiddenUpDivergenceRepository.save(chartDto.toHUD(candle));
                break;
            case 3:
                hiddenDownDivergenceRepository.save(chartDto.toHDD(candle));
                break;
            default:
                break;
        }

        template.convertAndSend("/chart/" + topic, ResponseEntity
                .ok()
                .body(ChartResponse.of(chartDto)));
    }

    private Candle existCandle(ChartDto chartDto, String candleType) {
        return candleRepository.findCandleByTime(chartDto.getTime())
                .orElseGet(() -> candleRepository.save(Candle.builder()
                        .type(candleType)
                        .time(chartDto.getTime())
                        .build()));
    }
}

