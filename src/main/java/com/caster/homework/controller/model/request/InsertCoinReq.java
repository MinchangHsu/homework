package com.caster.homework.controller.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class InsertCoinReq {

    private String disclaimer;
    private String chartName;
    private List<InsertExchangeRateReq> exchangeRateList;
    private InsertExchangeRateTimeReq exchangeRateTimeReq;
}
