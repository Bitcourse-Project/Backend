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
public class Candle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Coin coin;

    private int time;

    @ManyToOne
    @JoinColumn(name = "common_up_divergence")
    private CommonUpDivergence commonUpDivergence;

    @ManyToOne
    @JoinColumn(name = "common_down_divergence")
    private CommonDownDivergence commonDownDivergence;

    @ManyToOne
    @JoinColumn(name = "hidden_up_divergence")
    private HiddenUpDivergence hiddenUpDivergence;

    @ManyToOne
    @JoinColumn(name = "hidden_down_divergence")
    private HiddenDownDivergence hiddenDownDivergence;

}
