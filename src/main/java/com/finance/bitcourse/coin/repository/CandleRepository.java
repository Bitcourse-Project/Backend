package com.finance.bitcourse.coin.repository;

import com.finance.bitcourse.coin.entity.Candle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandleRepository extends JpaRepository<Candle, Long> {
    Optional<Candle> findCandleByTime(String time);
}