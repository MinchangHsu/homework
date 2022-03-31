package com.caster.homework.service;

import com.caster.homework.entity.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {

    List<ExchangeRate> findAll();

    List<ExchangeRate> findByCoinIdAndTimeId(Long coinId, Long timeId);

    ExchangeRate insert(ExchangeRate record);

}
