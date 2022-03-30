package com.caster.homework.controller;

import com.caster.homework.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/coin")
public class CoinDeskController {

    @Autowired
    CoinService coinService;

    @GetMapping("/fetching")
    public ResponseEntity fetchingCoinDeskData() throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }
        System.out.println(result.toString());


        return ResponseEntity.ok(coinService.findAll());
    }

    @GetMapping("/list")
    public ResponseEntity getCoinList() {
        return ResponseEntity.ok(coinService.findAll());
    }

    @PostMapping("")
    public ResponseEntity insert() throws IOException {
        // todo: insert coin data
        return ResponseEntity.ok(200);
    }

    @DeleteMapping("/{coinId}")
    public ResponseEntity deleteById(@PathVariable Integer coinId) throws IOException {
        // todo: delete by id coin data
        return ResponseEntity.ok(200);
    }

    @PutMapping("/{coinId}")
    public ResponseEntity updateById(@PathVariable Integer coinId) throws IOException {
        // todo: delete by id coin data
        return ResponseEntity.ok(200);
    }

}
