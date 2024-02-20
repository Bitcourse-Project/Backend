package com.finance.bitcourse.common.kafka.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class ChartDto {

    private String trendChangeRate;
    private String averageChangePeriod;
    private String averageChangeRate;
    private String type;
}
