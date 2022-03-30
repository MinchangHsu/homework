package com.caster.homework.service.impl;

import com.caster.homework.dao.CoinRepository;
import com.caster.homework.entity.Coin;
import com.caster.homework.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinServiceImpl implements CoinService {

    @Autowired
    CoinRepository coinRepository;

    @Override
    public List<Coin> findAll() {
        return coinRepository.findAll();
    }
}
