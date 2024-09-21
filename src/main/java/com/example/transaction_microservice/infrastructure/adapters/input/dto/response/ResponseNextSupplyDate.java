package com.example.transaction_microservice.infrastructure.adapters.input.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ResponseNextSupplyDate {
    private LocalDateTime nextSupplyDate;
}
