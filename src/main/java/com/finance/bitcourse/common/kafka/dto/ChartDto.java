package com.finance.bitcourse.common.kafka.dto;

import com.finance.bitcourse.coin.entity.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class ChartDto {

    private Double trendChangeRate;
    private Double averageChangePeriod;
    private Double averageChangeRate;
    private Integer type;
    private String time;

    public CommonUpDivergence toCUD(Candle candle) {
        return CommonUpDivergence
                .builder()
                .trendChangeRate(trendChangeRate)
                .averageChangePeriod(averageChangePeriod)
                .averageChangeRate(averageChangeRate)
                .candles(candle)
                .build();
    }

    public CommonDownDivergence toCDD(Candle candle) {
        return CommonDownDivergence
                .builder()
                .trendChangeRate(trendChangeRate)
                .averageChangePeriod(averageChangePeriod)
                .averageChangeRate(averageChangeRate)
                .candles(candle)
                .build();
    }

    public HiddenUpDivergence toHUD(Candle candle) {
        return HiddenUpDivergence
                .builder()
                .trendChangeRate(trendChangeRate)
                .averageChangePeriod(averageChangePeriod)
                .averageChangeRate(averageChangeRate)
                .candles(candle)
                .build();
    }

    public HiddenDownDivergence toHDD(Candle candle) {
        return HiddenDownDivergence
                .builder()
                .trendChangeRate(trendChangeRate)
                .averageChangePeriod(averageChangePeriod)
                .averageChangeRate(averageChangeRate)
                .candles(candle)
                .build();
    }
}
