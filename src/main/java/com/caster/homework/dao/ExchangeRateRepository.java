package com.caster.homework.dao;

import com.caster.homework.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    @Query(value = "select * from exchange_rate where exchange_rate_time_id = ?1", nativeQuery = true)
    List<ExchangeRate> findByCoinIdAndTimeId(Long timeId);
}
