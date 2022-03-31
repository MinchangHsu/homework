package com.caster.homework.controller;

import com.caster.homework.controller.model.request.InsertCoinReq;
import com.caster.homework.entity.Coin;
import com.caster.homework.entity.ExchangeRate;
import com.caster.homework.entity.ExchangeRateTime;
import com.caster.homework.model.CoinView;
import com.caster.homework.model.ExchangeRateView;
import com.caster.homework.model.RateInfoView;
import com.caster.homework.service.CoinService;
import com.caster.homework.service.ExchangeRateService;
import com.caster.homework.service.ExchangeRateTimeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coin")
public class CoinDeskController {

    @Autowired
    CoinService coinService;

    @Autowired
    ExchangeRateService exchangeRateService;

    @Autowired
    ExchangeRateTimeService exchangeRateTimeService;

    @GetMapping("/fetching")
    public ResponseEntity fetchingCoinDeskData() throws IOException {
        final String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        try {
            coinService.fetchingData(url);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(200);
    }

    @GetMapping("/transfer")
    public ResponseEntity transferCoinDeskData() throws IOException {
        final String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        CoinView view = null;
        try {
            view = coinService.transferData(url);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(view);
    }

    @GetMapping("/list")
    public ResponseEntity getCoinList() {
        List<CoinView> coinViews = coinService.findAll().stream().map(o -> {
            CoinView view = new CoinView();
            BeanUtils.copyProperties(o, view);
            List<RateInfoView> list = exchangeRateTimeService.findByCoinId(view.getId()).stream().map(p -> {
                RateInfoView rateInfoView = new RateInfoView();
                BeanUtils.copyProperties(p, rateInfoView);
                List<ExchangeRateView> exchangeRateViews = exchangeRateService.findByCoinIdAndTimeId(view.getId(), p.getId()).stream().map(q -> {
                    ExchangeRateView exchangeRateView = new ExchangeRateView();
                    BeanUtils.copyProperties(q, exchangeRateView);
                    return exchangeRateView;
                }).collect(Collectors.toList());
                rateInfoView.setExchangeRateList(exchangeRateViews);
                return rateInfoView;
            }).collect(Collectors.toList());
            view.setRateInfo(list);

            return view;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(coinViews);
    }

    @PostMapping("")
    public ResponseEntity insert(@RequestBody InsertCoinReq req) {
        // todo: insert coin data
        Coin coin = new Coin();
        BeanUtils.copyProperties(req, coin);
        coinService.insert(coin);

        ExchangeRateTime exchangeRateTime = new ExchangeRateTime();
        BeanUtils.copyProperties(req.getExchangeRateTimeReq(), exchangeRateTime);
        exchangeRateTime.setCoin(coin);
        exchangeRateTimeService.insert(exchangeRateTime);

        req.getExchangeRateList().forEach(o -> {
            ExchangeRate exchangeRate = new ExchangeRate();
            BeanUtils.copyProperties(o, exchangeRate);
            exchangeRate.setExchangeRateTime(exchangeRateTime);
            exchangeRateService.insert(exchangeRate);
        });
        return ResponseEntity.ok(200);
    }

    @DeleteMapping("/{coinId}")
    public ResponseEntity deleteById(@PathVariable Long coinId) {
        // todo: delete by id coin data
        coinService.deleterById(coinId);
        return ResponseEntity.ok(200);
    }

    @PutMapping("/{coinId}")
    public ResponseEntity updateById(@PathVariable Long coinId, @RequestBody InsertCoinReq req) {
        // todo: delete by id coin data
        Coin coin = coinService.findById(coinId);
        BeanUtils.copyProperties(req, coin, "id");
        coinService.insert(coin);

        ExchangeRateTime exchangeRateTime = new ExchangeRateTime();
        BeanUtils.copyProperties(req.getExchangeRateTimeReq(), exchangeRateTime);
        exchangeRateTime.setCoin(coin);
        exchangeRateTimeService.insert(exchangeRateTime);

        req.getExchangeRateList().forEach(o -> {
            ExchangeRate exchangeRate = new ExchangeRate();
            BeanUtils.copyProperties(o, exchangeRate);
            exchangeRate.setExchangeRateTime(exchangeRateTime);
            exchangeRateService.insert(exchangeRate);
        });
        return ResponseEntity.ok(200);
    }
}
