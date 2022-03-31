package com.caster.homework.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue()
    private Long id;
    private String code;
    private String symbol;
    private String rate;
    private String description;
    private BigDecimal rate_float;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ExchangeRateTime exchangeRateTime;
}
