package com.caster.homework.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "coin")
public class Coin {

    @Id
    @GeneratedValue()
    private Long id;

    private String disclaimer;
    private String chartName;

    @OneToMany(mappedBy = "id", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = ExchangeRateTime.class)
    private Set<ExchangeRateTime> exchangeRateTimes;

    @OneToMany(mappedBy = "id", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = ExchangeRate.class)
    private Set<ExchangeRate> exchangeRateList;

}
