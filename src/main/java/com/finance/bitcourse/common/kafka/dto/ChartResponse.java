package com.finance.bitcourse.common.kafka.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChartResponse {

    private Double trendChangeRate;
    private Double averageChangePeriod;
    private Double averageChangeRate;
    private Integer type;

    public ChartResponse(
            final Double trendChangeRate,
            final Double averageChangePeriod,
            final Double averageChangeRate,
            final Integer type) {
        this.trendChangeRate = trendChangeRate;
        this.averageChangePeriod = averageChangePeriod;
        this.averageChangeRate = averageChangeRate;
        this.type = type;
    }

    public static ChartResponse of(final ChartDto chartDto) {
        return new ChartResponse(
                chartDto.getTrendChangeRate(),
                chartDto.getAverageChangePeriod(),
                chartDto.getAverageChangeRate(),
                chartDto.getType()
        );
    }
}
