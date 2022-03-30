package com.caster.homework.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Exchange_Rate_Time")
public class ExchangeRateTime {

    @Id
    @GeneratedValue()
    private Long id;

    private LocalDateTime updated;
    private LocalDateTime updatedISO;
    private LocalDateTime updateduk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coin_id")
    private Coin coin;
}
