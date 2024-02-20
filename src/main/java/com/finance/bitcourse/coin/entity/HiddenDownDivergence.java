package com.finance.bitcourse.coin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class HiddenDownDivergence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double trendChangeRate;

    private Double averageChangePeriod;

    private Double averageChangeRate;

    @OneToMany(mappedBy = "hiddenDownDivergence")
    private List<Candle> candles = new ArrayList<>();

}
