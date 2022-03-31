package com.caster.homework.service.impl;

import com.caster.homework.dao.ExchangeRateRepository;
import com.caster.homework.entity.ExchangeRate;
import com.caster.homework.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    @Override
    public List<ExchangeRate> findAll() {
        return exchangeRateRepository.findAll();
    }

    @Override
    public List<ExchangeRate> findByCoinIdAndTimeId(Long coinId, Long timeId) {
        return exchangeRateRepository.findByCoinIdAndTimeId(timeId);
    }

    @Override
    public ExchangeRate insert(ExchangeRate record) {
        return exchangeRateRepository.save(record);
    }
}
