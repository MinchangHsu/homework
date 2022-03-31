package com.caster.homework.controller.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InsertExchangeRateReq {
    private Long id;
    private String code;
    private String symbol;
    private String rate;
    private String description;
    private BigDecimal rate_float;
}
