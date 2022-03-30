package com.caster.homework.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Entity
public class ExchangeRate extends AbstractPersistable<Long> {

    private String code;
    private String symbol;
    private String rate;
    private String description;
    private BigDecimal rate_float;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coin_id")
    private Coin coin;
}
