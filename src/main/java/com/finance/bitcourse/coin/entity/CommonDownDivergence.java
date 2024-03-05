package com.finance.bitcourse.coin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CommonDownDivergence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double trendChangeRate;

    private Double averageChangePeriod;

    private Double averageChangeRate;

    @ManyToOne
    private Candle candles;

}
