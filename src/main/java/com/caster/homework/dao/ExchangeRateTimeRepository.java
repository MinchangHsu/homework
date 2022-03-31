package com.caster.homework.dao;

import com.caster.homework.entity.ExchangeRateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExchangeRateTimeRepository extends JpaRepository<ExchangeRateTime, Long> {
    @Query(value="select * from exchange_rate_time where coin_id = ?1", nativeQuery=true)
    List<ExchangeRateTime> findByCoinId(Long coinId);
}
