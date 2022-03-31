package com.caster.homework.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoinView {
    private Long id;
    private String disclaimer;
    private String chartName;
    private List<RateInfoView> rateInfo;

    @Override
    public String toString() {
        return "CoinView{" +
                "id=" + id +
                ", disclaimer='" + disclaimer + '\'' +
                ", chartName='" + chartName + '\'' +
                ", rateInfo=" + rateInfo +
                '}';
    }
}
