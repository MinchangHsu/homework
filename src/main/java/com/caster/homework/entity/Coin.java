package com.caster.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "coin")
public class Coin {

    @Id
    @GeneratedValue()
    private Long id;
    private String disclaimer;
    private String chartName;

    @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE, targetEntity = ExchangeRateTime.class, orphanRemoval = true)
    private List<ExchangeRateTime> exchangeRateTimes;

}
