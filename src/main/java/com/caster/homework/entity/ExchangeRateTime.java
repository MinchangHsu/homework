package com.caster.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Exchange_Rate_Time")
public class ExchangeRateTime implements Serializable {

    @Id
    @GeneratedValue()
    private Long id;

    private LocalDateTime updated;
    private LocalDateTime updatedISO;
    private LocalDateTime updateduk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Coin coin;

    @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE, targetEntity = ExchangeRate.class)
    private List<ExchangeRate> exchangeRateList;
}
