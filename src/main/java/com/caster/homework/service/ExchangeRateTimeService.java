package com.caster.homework.service;

import com.caster.homework.entity.ExchangeRateTime;

import java.util.List;

public interface ExchangeRateTimeService {
    List<ExchangeRateTime> findByCoinId(Long coinId);

    ExchangeRateTime insert(ExchangeRateTime record);

}
