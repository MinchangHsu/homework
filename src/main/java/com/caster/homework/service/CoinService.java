package com.caster.homework.service;

import com.caster.homework.entity.Coin;
import com.caster.homework.model.CoinView;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface CoinService {

    void fetchingData(String url) throws IOException, ParseException;

    CoinView transferData(String url) throws IOException, ParseException;

    List<Coin> findAll();

    Coin insert(Coin coin);

    void deleterById(Long coinId);

    Coin findById(Long coinId);
}
