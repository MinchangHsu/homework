package com.caster.homework.service;

import com.caster.homework.model.CoinView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.ParseException;

@SpringBootTest
public class CoinTests {

    @Autowired
    CoinService coinService;

    @Test
    public void callCoinDeskApi() throws IOException, ParseException {
        coinService.fetchingData("https://api.coindesk.com/v1/bpi/currentprice.json");
    }
    @Test
    public void callCoinDeskApiTransFer() throws IOException, ParseException {
        CoinView view = coinService.transferData("https://api.coindesk.com/v1/bpi/currentprice.json");
        System.out.println(view);
    }
}
