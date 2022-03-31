package com.caster.homework.service.impl;

import com.caster.homework.dao.ExchangeRateTimeRepository;
import com.caster.homework.entity.ExchangeRateTime;
import com.caster.homework.service.ExchangeRateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateTimeServiceImpl implements ExchangeRateTimeService {
    @Autowired
    ExchangeRateTimeRepository exchangeRateTimeRepository;
    @Override
    public List<ExchangeRateTime> findByCoinId(Long coinId) {
        return exchangeRateTimeRepository.findByCoinId(coinId);
    }

    @Override
    public ExchangeRateTime insert(ExchangeRateTime record) {
        return exchangeRateTimeRepository.save(record);
    }
}
