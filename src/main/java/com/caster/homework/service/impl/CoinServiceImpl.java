package com.caster.homework.service.impl;

import com.caster.homework.dao.CoinRepository;
import com.caster.homework.dao.ExchangeRateRepository;
import com.caster.homework.dao.ExchangeRateTimeRepository;
import com.caster.homework.entity.Coin;
import com.caster.homework.entity.ExchangeRate;
import com.caster.homework.entity.ExchangeRateTime;
import com.caster.homework.model.CoinView;
import com.caster.homework.model.ExchangeRateView;
import com.caster.homework.model.RateInfoView;
import com.caster.homework.service.CoinService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoinServiceImpl implements CoinService {

    @Autowired
    CoinRepository coinRepository;

    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    @Autowired
    ExchangeRateTimeRepository exchangeRateTimeRepository;

    @Override
    public void fetchingData(String urlStr) throws IOException, ParseException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser jsonParser = factory.createParser(result.toString());
        JsonNode node = mapper.readTree(jsonParser);

        Coin coin = new Coin();
        coin.setChartName(node.get("chartName").asText());
        coin.setDisclaimer(node.get("disclaimer").asText());
        coinRepository.save(coin);


        JsonNode timeNode = node.get("time");
        ExchangeRateTime exchangeRateTime = new ExchangeRateTime();
        exchangeRateTime.setCoin(coin);
        exchangeRateTime.setUpdated(parseUpdated(timeNode.get("updated").asText())); // Mar 30, 2022 12:55:00 UTC
        exchangeRateTime.setUpdatedISO(parseUpdatedISO(timeNode.get("updatedISO").asText())); // Mar 30, 2022 12:55:00 UTC
        exchangeRateTime.setUpdateduk(parseUpdateduk(timeNode.get("updateduk").asText())); // Mar 30, 2022 12:55:00 UTC

        exchangeRateTimeRepository.save(exchangeRateTime);

        List<ExchangeRate> exchangeRateList = new ArrayList<>();
        Iterator<JsonNode> jsonNodeIterator =  node.get("bpi").iterator();
        while(jsonNodeIterator.hasNext()){
            JsonNode jsonNodeObj = jsonNodeIterator.next();
            ExchangeRate rate = mapper.treeToValue(jsonNodeObj, ExchangeRate.class);
            exchangeRateList.add(rate);
            rate.setExchangeRateTime(exchangeRateTime);
        }
        exchangeRateList.forEach(o -> exchangeRateRepository.save(o));
    }

    @Override
    public CoinView transferData(String urlStr) throws IOException, ParseException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser jsonParser = factory.createParser(result.toString());
        JsonNode node = mapper.readTree(jsonParser);

        CoinView view = new CoinView();
        Coin coin = new Coin();
        coin.setChartName(node.get("chartName").asText());
        coin.setDisclaimer(node.get("disclaimer").asText());
        BeanUtils.copyProperties(coin, view);

        List<RateInfoView> list = new ArrayList<>();

        JsonNode timeNode = node.get("time");
        RateInfoView rateInfoView = new RateInfoView();
        ExchangeRateTime exchangeRateTime = new ExchangeRateTime();
        exchangeRateTime.setCoin(coin);
        exchangeRateTime.setUpdated(parseUpdated(timeNode.get("updated").asText())); // Mar 30, 2022 12:55:00 UTC
        exchangeRateTime.setUpdatedISO(parseUpdatedISO(timeNode.get("updatedISO").asText())); // Mar 30, 2022 12:55:00 UTC
        exchangeRateTime.setUpdateduk(parseUpdateduk(timeNode.get("updateduk").asText())); // Mar 30, 2022 12:55:00 UTC
        BeanUtils.copyProperties(exchangeRateTime, rateInfoView);

        List<ExchangeRate> exchangeRateList = new ArrayList<>();
        Iterator<JsonNode> jsonNodeIterator =  node.get("bpi").iterator();
        while(jsonNodeIterator.hasNext()){
            JsonNode jsonNodeObj = jsonNodeIterator.next();
            ExchangeRate rate = mapper.treeToValue(jsonNodeObj, ExchangeRate.class);
            exchangeRateList.add(rate);
            rate.setExchangeRateTime(exchangeRateTime);
        }
        List<ExchangeRateView> exchangeRateViews = exchangeRateList.stream().map(o -> {
            ExchangeRateView exchangeRateView = new ExchangeRateView();
            BeanUtils.copyProperties(o, exchangeRateView);
            return exchangeRateView;
        }).collect(Collectors.toList());

        rateInfoView.setExchangeRateList(exchangeRateViews);
        list.add(rateInfoView);
        view.setRateInfo(list);
        return view;
    }

    @Override
    public List<Coin> findAll() {
        return coinRepository.findAll();
    }

    @Override
    public Coin insert(Coin coin) {
       return coinRepository.save(coin);
    }

    @Override
    public void deleterById(Long coinId) {
        Coin coin = coinRepository.findById(coinId).get();
        exchangeRateTimeRepository.findByCoinId(coinId).stream().map(o -> {
            o.setExchangeRateList(exchangeRateRepository.findByCoinIdAndTimeId(o.getId()));
            return o;
        }).collect(Collectors.toList());
        coin.setExchangeRateTimes(exchangeRateTimeRepository.findByCoinId(coinId));
        coinRepository.delete(coin);
    }

    @Override
    public Coin findById(Long coinId) {
        return coinRepository.findById(coinId).get();
    }

    public LocalDateTime parseUpdated(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss Z", Locale.US);
        sdf.setTimeZone((TimeZone.getTimeZone("UTC")));
        Date date = sdf.parse(dateStr);
        return date.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
    }

    public LocalDateTime parseUpdatedISO(String dateStr){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'H:m:sXXX").withZone(ZoneId.of("UTC"));
        return LocalDateTime.parse(dateStr, dateFormat);
    }

    public LocalDateTime parseUpdateduk(String dateStr) throws ParseException {
        SimpleDateFormat sdf4 = new SimpleDateFormat("MMM dd, yyyy 'at' HH:mm Z", Locale.US);
        sdf4.setTimeZone((TimeZone.getTimeZone("BST")));
        Date date = sdf4.parse(dateStr);
        return date.toInstant().atZone(ZoneId.of("UTC+1")).toLocalDateTime();
    }
}
