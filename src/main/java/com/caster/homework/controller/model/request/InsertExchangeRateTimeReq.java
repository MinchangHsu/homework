package com.caster.homework.controller.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InsertExchangeRateTimeReq {

    private Long id;

    @JsonFormat(locale = "zh", pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime updated;

    @JsonFormat(locale = "zh", pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime updatedISO;

    @JsonFormat(locale = "zh", pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime updateduk;
}
