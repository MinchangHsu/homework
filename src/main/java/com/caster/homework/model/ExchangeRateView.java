package com.caster.homework.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExchangeRateView {
    private Long id;
    private String code;
    private String symbol;
    private String rate;
    private String description;
    private BigDecimal rate_float;

    @Override
    public String toString() {
        return "ExchangeRateView{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", symbol='" + symbol + '\'' +
                ", rate='" + rate + '\'' +
                ", description='" + description + '\'' +
                ", rate_float=" + rate_float +
                '}';
    }
}
