package com.caster.homework.model;

import com.caster.homework.entity.ExchangeRate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RateInfoView {
    private Long id;
    @JsonIgnore
    private LocalDateTime updated;

    @JsonIgnore
    private LocalDateTime updatedISO;

    @JsonIgnore
    private LocalDateTime updateduk;



    private String updatedStr;
    private String updatedISOStr;
    private String updatedukStr;

    @JsonIgnore
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").withZone(ZoneId.of("UTC"));

    public String getUpdatedStr() {
        return df.format(this.updated);
    }

    public String getUpdatedISOStr() {
        return df.format(this.updatedISO);
    }

    public String getUpdatedukStr() {
        return df.format(this.updateduk);
    }

    private List<ExchangeRateView> exchangeRateList;

    @Override
    public String toString() {
        return "RateInfoView{" +
                "id=" + id +
                ", updated=" + updated +
                ", updatedISO=" + updatedISO +
                ", updateduk=" + updateduk +
                ", updatedStr='" + updatedStr + '\'' +
                ", updatedISOStr='" + updatedISOStr + '\'' +
                ", updatedukStr='" + updatedukStr + '\'' +
                ", df=" + df +
                ", exchangeRateList=" + exchangeRateList +
                '}';
    }
}
